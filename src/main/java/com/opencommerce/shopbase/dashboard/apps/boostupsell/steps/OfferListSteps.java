package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.OfferListPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.List;
import java.util.Locale;

import static com.opencommerce.shopbase.OrderVariable.accesstoken;
import static com.opencommerce.shopbase.OrderVariable.shopDomain;
import static java.util.Arrays.asList;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OfferListSteps {
    OfferListPage offerListPage;

    public int getNumberOfOffers() {
        offerListPage.waitNotShowLoadingOnTable();
        offerListPage.waitForPageLoad();
        offerListPage.waitForEverythingComplete();
        return offerListPage.getNumberOfOffers();
    }

    public void checkAllOffers() {
        offerListPage.checkAllOffers();

    }

    public void deleteAllOffers() {
        offerListPage.clickBtnDeleteOnPopup();
        offerListPage.verifyTableNoOffers();
        offerListPage.waitForEverythingComplete();
    }

    public void verifyBtnCreateNewOffer(String lableBtn) {
        offerListPage.verifyNoOffers(lableBtn);
    }

    public void clickBtnAction() {
        offerListPage.clickBtn("Action");
    }

    public void clickBtnOnAction(String action) {
        offerListPage.clickBtnOnAction(action);
    }


    public void searchOffer(String offerName) {
        offerListPage.searchOffer(offerName);
        offerListPage.waitForEverythingComplete();
        offerListPage.waitForPageLoad();
    }

    public void verifyOfferTarget(String offerTarget, int index) {
        int colIndex = offerListPage.getColIndex("products/collections");
        List<String> actual = offerListPage.getListProductOrCollectionTarget(colIndex, index);
        List<String> expectedListProductTarget = asList(offerTarget.split(","));

        assertThat(actual).containsAll(expectedListProductTarget);
        assertThat(actual.size()).isEqualTo(expectedListProductTarget.size());
    }

    public void verifyOfferStatus(String status, int index) {
        offerListPage.waitToUpdateStatus();
        verifyOfferInfor(status, "status", index);
    }

    public void verifyOfferType(String offerType, int index) {
        if (!offerType.isEmpty()) {
            verifyOfferInfor(offerType, "type", index);
        }
    }

    public int getIndexOfferByName(String offerName) {
        return offerListPage.getIndexOfferName(offerName);
    }

    public void verifyOfferInfor(String value, String colName, int rowIndex) {
        int colIndex = offerListPage.getColIndex(colName);
        String actual = offerListPage.getOfferInfor(colIndex, rowIndex);
        assertThat(actual).isEqualTo(value);

    }

    public void verifyNotiSuccess(String noti) {
        offerListPage.verifyNotiShow(noti);
    }

    public void turnOnOFFSmartOffer(boolean status) {
        offerListPage.turnOnToggleSmartOffer(status);
    }

    public void backListUsellOffer() {
        offerListPage.navigateToPreviousPage();
    }

    public JsonArray getListOfferID(String offerTypes) {
        String shop = LoadObject.getProperty("shop");
        int shopID = Integer.parseInt(LoadObject.getProperty("shopid"));
        if (accesstoken.isEmpty()){
            accesstoken = offerListPage.getAccessTokenShopBase();
        }

        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("shop_id", shopID);
        requestParams.addProperty("offer_types", offerTypes.toLowerCase(Locale.ROOT).replace(" ","-"));
        requestParams.addProperty("only_active", false);

        String formatOfferTypes = offerTypes.toLowerCase(Locale.ROOT).replace(" ","-");

        JsonPath listOffer = offerListPage.getAPISbase("https://" + shop + "/admin/offers/list.json?shop_id=" + shopID + "&only_active=false&offer_types=" + formatOfferTypes, accesstoken);

        JsonArray listOfferID = new JsonArray();
        String offerStr = listOffer.toString();
        JSONArray offerArr = (JSONArray) JSONValue.parse(offerStr);

        for(Object o : offerArr){
            JSONObject jsonObject = (JSONObject) o;
            String id = (String) jsonObject.get("id");
            listOfferID.add(id);
        }
        return listOfferID;
    }

    public void deleteAllAOfferByAPI(JsonArray listOfferID) {
        String shop = LoadObject.getProperty("shop");
        int shopID = Integer.parseInt(LoadObject.getProperty("shopid"));
        if (accesstoken.isEmpty()){
            accesstoken = offerListPage.getAccessTokenShopBase();
        }

        Response response = given().header("Content-Type", "application/json").header("x-shopbase-access-token", accesstoken).body(listOfferID.toString()).delete("https://" +shop+ "/admin/offers/delete.json?shop_id=" + shopID);
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
    }
}
