package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.CreateOfferPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import static com.opencommerce.shopbase.OrderVariable.*;
import static net.serenitybdd.rest.SerenityRest.given;

public class CreateOfferSteps extends CommonSteps {
    CreateOfferPage upsellPage;

    @Step
    public void chooseUpsellOfferType(String offerType) {
        if (!offerType.isEmpty()) {
            upsellPage.chooseUpsellTypeOffer(offerType);
        }
    }

    @Step
    public void enterOfferName(String offerName) {
        if (!offerName.isEmpty()) {
            upsellPage.enterNameOffer(offerName);
        }
    }

    @Step
    public void enterOfferMessage(String offerMessage) {
        if (!offerMessage.isEmpty()) {
            upsellPage.enterMessageOffer(offerMessage);
        }
    }

    public void enterOfferTitle(String offerTitle) {
        if (!offerTitle.isEmpty()) {
            upsellPage.clickLinkTextWithLabel("Offer's name");
            upsellPage.enterOfferTitle(offerTitle);
            upsellPage.clickLinkTextWithLabel("Offer's name");
        }
    }

    @Step
    public void chooseProductWithBlock(String blockName, String targetType, String targetProducts) {
        if (!targetType.isEmpty()) {
            upsellPage.chooseTargetOrRecommendProductType(targetType, blockName);
        }
        if (!targetProducts.isEmpty()) {
            upsellPage.clickButtonSelectCollectionOrProduct(blockName);
            upsellPage.removeAllProductSelected();
            selectCollectionOrProduct(targetProducts);
        }
    }

    public void selectCollectionOrProduct(String targetProducts) {
        upsellPage.removeAllProductSellected();
        upsellPage.addItemOnSelectorPopup(targetProducts);
    }

    public void setDiscountOffer(boolean isDiscount, String discountValue) {
        if(upsellPage.isDiscountExisted()){
            scrollToDiscount();
            turnONOFDiscount(isDiscount);
            if (isDiscount) {
                upsellPage.enterInputFieldWithLabel("Discount percentage", discountValue, 1);
            }
        }
    }

    public void turnONOFDiscount(boolean isDiscount) {
        upsellPage.turnOnOffDiscount(isDiscount);
    }

    public void scrollToDiscount() {
        upsellPage.scrollToDiscount();
    }

    public void clickBtnCreateOffer() {
        upsellPage.clickBtnCreateOffer();
    }


    public void waitUntillPageShown(String page) {
        upsellPage.waitForTextToAppear(page, 20000);
        upsellPage.verifyTextPresent(page, true);

    }

    public void clickButtonName(String button) {
        if (upsellPage.isEnableActionbar()) {
            String xpath = "//button//span[contains(text(),'" + button + "')]";
            upsellPage.waitElementToBeClickable(xpath);
            upsellPage.clickOnElementByJs(xpath);
            upsellPage.waitUntilInvisibleLoading(10);
            upsellPage.waitForEverythingComplete();
        }
    }

    // Bundle
    public void isShowBundleTarget(String isShowWithTarget) {
        if (!isShowWithTarget.isEmpty()) {
            boolean isCheck = Boolean.parseBoolean(isShowWithTarget);
            upsellPage.checkCheckboxWithLabel("Only show this bundle when customers visit target product.", 1, isCheck);
        }
    }
    //Quntity discount

    @Step
    public void setUpDiscountQuantityOffer(String discountQuantity) {
        deleteQuantity();
        if (!discountQuantity.isEmpty()) {
            String[] discount = discountQuantity.split(",");
            for (int i = 0; i < discount.length - 1; i++) {
                inputDiscountQuanity(discount[i], i);
                upsellPage.clickAddMoreDiscount();
            }
            inputDiscountQuanity(discount[discount.length - 1], discount.length - 1);
        }
    }

    public void deleteQuantity() {
        int count = upsellPage.countDiscount();
        for(int i = count; i > 1; i--){
            upsellPage.deleteQuantity(i);
        }
    }

    public void inputDiscountQuanity(String discounts, int index) {
        String[] data = discounts.split(">");
        String quantity = data[0];
        String discount = data[1];
        String typeDiscount = data[2];
        upsellPage.inputQuantity(quantity, index);
        upsellPage.inputDiscount(discount, index);
        upsellPage.chooseTyeDiscount(typeDiscount, index + 1);
    }

    public void setTargetSsmartRule(String targetRule, String products) {
        if (!targetRule.isEmpty()) {
            upsellPage.chooseTargetOrRecommendProductType(targetRule, "Target product rules");
        }
        if (products.equals("All products")) {
            upsellPage.chooseProductsTarrgetRule("All products");
        } else {
            upsellPage.chooseProductsTarrgetRule("Manually selected products");
            upsellPage.clickButtonSelectCollectionOrProduct("Target product rules");
            upsellPage.removeAllProductSellected();
            selectCollectionOrProduct(products);
        }

    }

    public void navigationMenuAppUsell(String menu) {
        upsellPage.navigateUpsell(menu);
    }

    @Step
    public void turnOnOffSmartOffer(String smartType, String status) {
        boolean sts = !status.equals("off");
        upsellPage.onOffToggleSmart(smartType, sts);
        saveSetting();
    }

    public void clickBtnSetUpRule(String smartType) {
        upsellPage.clickBtnSetUpRecommendRule(smartType);
    }

    public void saveSetting() {
        upsellPage.saveChanges();
    }

    @Step
    public void setDiscountOfferSmart(boolean isDiscount, String discount) {
        turnONOFDiscount(isDiscount);
        if (isDiscount) {
            upsellPage.enterInputFieldWithLabel("Discount percentage", discount, 1);
        }
    }

    public void chooseCategory(String blockName, String targetType, String baseCategorys) {
        upsellPage.chooseTargetOrRecommendProductType(targetType, blockName);
        unCheckAllBaseCategory();

        String[] baseCategoryList = baseCategorys.split(",");
        for(String i : baseCategoryList)
        {
            upsellPage.checkBaseCategoryByLabel(i, true);
        }
    }

    @Step
    public void chooseRecommendVariantWith(String recommendVariantWith) {
        upsellPage.chooseRecommendVariantWith(recommendVariantWith);
    }

    public void unCheckAllBaseCategory(){
        int count = upsellPage.countBaseCategory();
        for(int i = 1; i <= count; i++){
            upsellPage.checkBaseCategoryByIndex(i, false);
        }
    }

    @Step
    public void enterOfferPriority(String priority, String offerName) {
        if (!priority.isEmpty()) {
            upsellPage.enterPriorityOffer(priority, offerName);
        }
    }

    @Step
    public void clickToEditOffer(String label) {
        upsellPage.clickToEditOffer(label);
    }

    public void createOfferByAPI(String offerType, String offerName, String offerMessage, String offerTitle, String targetType, JsonArray targetIDs, String recommendType, JsonArray recommendIDs, JsonArray categoryIDs, String recommendVariantWith, boolean isDiscount, String discountValue) {
        String shop = LoadObject.getProperty("shop");
        int shopID = Integer.parseInt(LoadObject.getProperty("shopid"));
        if (accesstoken.isEmpty()){
            accesstoken = upsellPage.getAccessTokenShopBase();
        }

        JsonObject requestParams = new JsonObject();

        requestParams.addProperty("activated", true);
        if (isDiscount){
            requestParams.addProperty("enable_discount", true);
        }

        String discountData;
        if (discountValue.isEmpty() || discountValue.equals("0")){
            discountData = "{}";
        }else {
            long dateTime = System.currentTimeMillis()/1000;
            Timestamp ts=new Timestamp(dateTime);
            String date = new Date(ts.getTime()).toString();
            discountData = "{\"percentage_discount\":" + discountValue + ",\"stock_item_limit\":30,\"text_display_date_time\":\"" + date + "\",\"end_date_time\":" + dateTime + "}";
        }
        requestParams.addProperty("discount_data", discountData);

        // category in-cart offer
        JsonObject baseCategory = new JsonObject();
        baseCategory.addProperty("from_product_with", recommendVariantWith);
        baseCategory.add("from_these_categories", categoryIDs);

        JsonObject product = new JsonObject();
        product.addProperty("recommend_variant_with", recommendVariantWith);

        JsonObject inCart = new JsonObject();
        inCart.add("base_category", baseCategory);
        inCart.add("product", product);

        JsonObject condition = new JsonObject();
        condition.add("in_cart", inCart);

        requestParams.addProperty("offer_message", offerMessage);
        requestParams.addProperty("offer_name", offerName);
        requestParams.addProperty("offer_product_updated", true);
        requestParams.addProperty("offer_title", offerTitle);
        requestParams.addProperty("offer_type", offerType.toLowerCase(Locale.ROOT).replace(" ","-"));
        requestParams.addProperty("target_type", targetType);
        requestParams.add("target_ids", targetIDs);
        requestParams.addProperty("recommend_type", recommendType);
        requestParams.add("recommend_ids", recommendIDs);
        requestParams.addProperty("shop_id", shopID);
        requestParams.add("condition", condition);

        Response response = given().header("Content-Type", "application/json").header("x-shopbase-access-token", accesstoken).body(requestParams.toString()).post("https://" +shop+ "/admin/offers.json");
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
    }

    public int getCategoryIDByName(String category) {
        String shop = LoadObject.getProperty("shop");
        if (accesstoken.isEmpty()){
            accesstoken = upsellPage.getAccessTokenShopBase();
        }

        JsonPath jp = upsellPage.getAPI("https://" + shop + "/admin/pbase-product-base/categories.json?access_token=" + accesstoken);
        return jp.get("cate.findAll{it.title=='" + category + "'}.id[0]");
    }
}
