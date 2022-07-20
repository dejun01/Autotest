package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell;

import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell.QuantityDiscountPage;
import net.thucydides.core.annotations.Step;

public class QuantityDiscountOfferSteps {
    QuantityDiscountPage quantityDiscountPage;

    @Step
    public void verifyWidgetQuantityDiscountShow(boolean isShowOffer) {
        quantityDiscountPage.verifyWidgetQuantityShow(isShowOffer);
    }

    @Step
    public void verifyMessageOffer(String messageOffer) {
        if (!messageOffer.isEmpty()) {
            quantityDiscountPage.verifyMessageOfffer(messageOffer);
        }
    }

    @Step
    public void verifyItemsQuantityDiscount(String discountItems) {
        if (!discountItems.isEmpty()) {
            String[] itemsDiscount = discountItems.split(",");
            for (String item : itemsDiscount) {
                quantityDiscountPage.verifyItemsDiscount(item);
            }
        }
    }

    @Step
    public void addToCartOnWidget(String quantity) {
        quantityDiscountPage.clickAddToCartOnWidget(quantity);
    }

    @Step
    public void verifyMessageDiscountOnCartPage(String messageDiscountOnCart) {
        if (!messageDiscountOnCart.isEmpty()) {
            quantityDiscountPage.verifyMessageDiscountOnCartPage(messageDiscountOnCart);
        }
    }

    public void settingBtnAddQTT(boolean stsBtn) {
        quantityDiscountPage.settingToggleBtnAdd(stsBtn);
    }

    public void verifyBtnAddShow(boolean stsBtn) {
        quantityDiscountPage.verifyBtnAddShown(stsBtn);
    }

    @Step
    public void clickButtonToCreateQuantityDiscount(){
        quantityDiscountPage.clickButtonOnSectionQuantity();
        String buttonLabel = quantityDiscountPage.getButtonLabel();
        if(buttonLabel.equalsIgnoreCase("Edit discounts")){
            quantityDiscountPage.clickButtonOnPopup("Add more quantity discounts");
        }
    }

    @Step
    public void selectOfferOnModal(String offerName){
        quantityDiscountPage.selectOfferOnModal(offerName);
    }

    @Step
    public void changeStatusOfferInProduct(String offer, String status){
        boolean isActive = !status.equalsIgnoreCase("Inactive");
        quantityDiscountPage.changeStatusOfferInProduct(offer, isActive);
    }
}
