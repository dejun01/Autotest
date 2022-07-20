package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview;

import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.quickview.AddMoreItemPage;
import net.thucydides.core.annotations.Step;


public class AddMoreItemSteps {

    AddMoreItemPage addMoreItemPage;

    @Step
    public void verifyButtonAddMoreItem(String products) {
        if (!products.isEmpty()) {
            String[] product = products.split(",");
            for (String prod : product) {
                addMoreItemPage.verifyBtnAddMoreItemOnProduct(prod);

            }

        }
    }

    @Step
    public void verifyAddMoreItemPopupShown(String product, String price) {
        verifyAddMoreItemShown();
        addMoreItemPage.verifyTitleProductOnPopup(product);
        addMoreItemPage.verifyPriceProduct(price);
    }

    @Step
    public void verifyAddMoreItemShown() {
        addMoreItemPage.verifyAddmoreItemShown(true);
    }

    public void chooseVariant(String variants) {
        if (!variants.isEmpty()) {
            String[] variant = variants.split(",");
            for (String var : variant) {
                selectVariantOnPopup(var);
            }
        }

    }

    @Step
    public void selectVariantOnPopup(String var) {
        String[] option = var.split(">");
        String nameOption = option[0];
        String valueOption = option[1];
        addMoreItemPage.selectOptionVariantOnPopup(nameOption, valueOption);
    }

    public void inputFieldCustome(String customOption) {
        if (!customOption.isEmpty()) {
            String[] cus = customOption.split(";");
            for (String items : cus) {
                String[] item = items.split(":");
                String option = item[0];
                String value = item[1];
                addMoreItemPage.inputCustomOption(option, value);
            }

        }
    }

    public void inputQuantity(String quantity) {
        if (!quantity.isEmpty()) {
            addMoreItemPage.increaseQuuanatity(quantity);

        }
    }

    public void clickClosePopup() {
        addMoreItemPage.clickBtnClosePopup();
    }

    public void clickBtnAddMore(String prod) {
        addMoreItemPage.clickAddMore(prod);
    }

    public void clickAddToCart() {
        addMoreItemPage.clickAddToCart();
    }

    public void verifyHighlight(String variant) {
        if (!variant.isEmpty()) {
            String[] cus = variant.split(";");
            for (String items : cus) {
                String[] item = items.split(":");
                String option = item[0];
                addMoreItemPage.verifyHighligtOnOption(option);
            }

        }

    }

    public void waitForPopupAddMoreClose() {
        addMoreItemPage.waitForEverythingComplete();
        addMoreItemPage.waitForPopupAddMoreNotShown();
    }
}
