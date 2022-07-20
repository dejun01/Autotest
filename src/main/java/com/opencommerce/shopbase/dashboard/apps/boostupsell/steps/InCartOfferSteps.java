package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.upsell.InCartOfferPage;
import net.thucydides.core.annotations.Step;

public class InCartOfferSteps {
    InCartOfferPage inCartOfferPage;

    @Step
    public void verifyPriceOfRecommendVariant(String targetProduct, String targetVariant, String price) {
        inCartOfferPage.verifyPriceOfRecommendVariant(targetProduct, targetVariant, price);
    }
    @Step
    public void verifyTitleOfRecommendVariant(String targetProduct, String targetVariant, String recommendVariant) {
        inCartOfferPage.verifyTitleOfRecommendVariant(targetProduct, targetVariant, recommendVariant);
    }

    @Step
    public void verifyProductName(String prodName) {
        inCartOfferPage.verifyProductName(prodName);

    }
    @Step
    public void verifySelectedRecommendVariant(String selectedVariant) {
        inCartOfferPage.verifySelectedRecommendVariant(selectedVariant);
    }

    @Step
    public void checkShowRecommendVariant(String targetProduct, String targetVariant, boolean isShow) {
        inCartOfferPage.checkShowRecommendVariant(targetProduct, targetVariant, isShow);
    }

    @Step
    public void openOffer(String offerName) {
        inCartOfferPage.openOffer(offerName);
    }

    @Step
    public void verifyNotiMessage(String message) {
        inCartOfferPage.verifyNotiMessage(message);
    }

    @Step
    public void verifyOfferType(String offerType) {
        inCartOfferPage.verifyOfferType(offerType);
    }

    @Step
    public void verifyOfferName(String offerName) {
        inCartOfferPage.verifyOfferName(offerName);
    }

    @Step
    public void verifyValueSelected(String blockName, String type, String values) {
        inCartOfferPage.verifyTypeOfCondition(blockName, type);

        String numberProductsSelected = "";
        if (type.equalsIgnoreCase("Specific products") || type.equalsIgnoreCase("Specific collections")) {
            if (values.isEmpty()) {
                numberProductsSelected = "0 product selected";
            } else {
                String[] valueList = values.split(",");

                if (valueList.length == 1) {
                    numberProductsSelected = valueList.length + " product selected";
                } else {
                    numberProductsSelected = valueList.length + " products selected";
                }
            }
        }
        inCartOfferPage.verifyValueSelected(blockName, numberProductsSelected);
    }

    @Step
    public void clickBtnAddOnIncart() {
        inCartOfferPage.clickOnAddToCart();
    }

    @Step
    public void clickOnRecommendProductTitle(String targetProduct, String targetVariant) {
        inCartOfferPage.clickOnRecommendProductTitle(targetProduct, targetVariant);
    }

    @Step
    public void verifyAddToCart(String recommendProduct, String recommendVariant) {
        inCartOfferPage.productRecommendAdded(recommendProduct, recommendVariant);
    }

    public void clickOnRecommendProductImage(String targetProduct, String targetVariant) {
        inCartOfferPage.clickOnRecommendProductImage(targetProduct, targetVariant);
    }

    public void clickBtnAddOnProductIncart(String targetProduct, String targetVariant) {
        inCartOfferPage.clickBtnAddOnIncart(targetProduct, targetVariant);
    }

    public void verifyIncartShown() {
        inCartOfferPage.verifyIncartShown();
    }
}
