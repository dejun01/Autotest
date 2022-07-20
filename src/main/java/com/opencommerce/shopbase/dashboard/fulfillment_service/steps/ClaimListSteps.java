package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.ClaimListPage;
import common.CommonPageObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimListSteps {

    ClaimListPage claimListPage;
    CommonPageObject commonPageObject;

    @Step
    public String getClaimInFirst() {
        return claimListPage.getClaimInFirst();
    }

    @Step
    public void searchClaim(String claim) {
        claimListPage.enterInputFieldWithLabel("Search order, claim", claim);
    }

    @Step
    public void goToClaimDetail() {
        claimListPage.goToClaimDetail();
    }


    @Step
    public int getCount() {
        return Integer.parseInt(claimListPage.getCount());
    }

    @Step
    public String getTextClaim() {
        return claimListPage.getTextClaim().trim();
    }

    @Step
    public void verifyInfoClaimInTab(String claimNew, String date, String order, String preferredResolution, String status) {
//        assertThat(claimListPage.getInfoClaimInListClaimPage(claimNew, "DATE")).contains(date);
        assertThat(claimListPage.getInfoClaimInListClaimPage(claimNew, "ORDER")).isEqualTo(order);
        assertThat(claimListPage.getInfoClaimInListClaimPage(claimNew, "PREFERRED RESOLUTION")).isEqualTo(preferredResolution);
        assertThat(claimListPage.getInfoClaimInListClaimPage(claimNew, "STATUS")).isEqualTo(status);
    }

    @Step
    public void cancelClaim(String claim) {
        claimListPage.cancelClaim(claim);
    }

    @Step
    public String getClaimID(String claim) {
        String[] urlCurrent = claimListPage.getClaimID(claim).split("claims/");
        String[] url = urlCurrent[1].split("/order");
        return url[0];
    }

    @Step
    public List<Integer> getClaimLineIds(String shopName, int claimID) {
        String accessToken = claimListPage.getAccessTokenShopBase();
        String api = "https://" + shopName + "/admin/panda-fulfillment/claim/" + claimID + ".json";
        JsonPath js = claimListPage.getAPISbase(api, accessToken);
        Object obj = claimListPage.getData(js, "order_claim_lines.id");
        List<Integer> arrayList = (ArrayList<Integer>) obj;
        return arrayList;
    }

    @Step
    public void approvedClaim(int claimID, String shopName, String action) {
        String url = "https://" + shopName + "/admin/qc-tools/panda/change-claim-status.json";
        Gson gson = new Gson();
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("claim_id", claimID);
        requestParams.addProperty("claim_status", action);
        requestParams.addProperty("refund_amount", 10);
        Map<Integer, Integer> confirmedQuantity = new HashMap<>();
        List<Integer> ids = getClaimLineIds(shopName, claimID);
        ids.forEach((id) -> {
            confirmedQuantity.put(id, 1);
        });
        String test = gson.toJson(confirmedQuantity);
        requestParams.addProperty("confirm_quantity", test);
        claimListPage.postAPI(url, requestParams);
    }

    @Step
    public void enterOrderName(String orderName) {
        claimListPage.refreshPage();
        claimListPage.enterInputFieldWithLabel("Search by order name, transaction id", orderName);
    }

    public void selectOrderName() {
        claimListPage.selectOrderName();
    }

    public void swichToWindow() {
        claimListPage.switchToWindowWithIndex(1);
    }

    public void waitToDataClaimShow() {
        claimListPage.waitToDataClaimShow();
    }
}
