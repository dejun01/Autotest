package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell.HanpickedProductPage;
import net.thucydides.core.annotations.Step;

public class HanpickedProductSteps {
    HanpickedProductPage handpickedPage;


    public void clickSelectProductRecommend() {
        handpickedPage.clickToSelectProductRecommend();
    }

    public void selectProduct(String product) {
        handpickedPage.addItemOnSelectorPopup(product);
    }

    @Step
    public void clickBtnCreateNew() {
        handpickedPage.clickToCreateNew();
    }


    public void selectTagetProduct(String target_prods) {
        String value[] = target_prods.split(">");
        selectProductType(value[0]);
        if (value.length == 3) {
            openSelector(value[1]);
            selectProductOrCollection(value[2]);
        }
    }

    public void selectProductOrCollection(String value) {
        handpickedPage.addItemOnSelectorPopup(value);

    }

    public void openSelector(String btnName) {
        handpickedPage.openSelectorHanpicked(btnName);
    }

    public void selectProductType(String productType) {
        handpickedPage.selectProductType(productType);
    }

    public void submitOffer() {
        handpickedPage.clickBtn("Submit offer");
        handpickedPage.waitForEverythingComplete();
        handpickedPage.waitForPageLoad();
    }

    public void enterOfferName(String offerName) {
        handpickedPage.enterOfferName(offerName);
    }
}
