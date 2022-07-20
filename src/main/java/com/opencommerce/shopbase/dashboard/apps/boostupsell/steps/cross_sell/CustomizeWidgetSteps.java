package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell.CustomizeWidgetPage;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static java.util.Arrays.asList;

public class CustomizeWidgetSteps {
    CustomizeWidgetPage customizeWidgetPage;

    public void resetData() {
        clickBtnResetToDefault();
        confirmResetToDefault();
    }

    private void confirmResetToDefault() {
        customizeWidgetPage.verifyPopupConfirm();
        customizeWidgetPage.clickBtnReset();
    }

    private void clickBtnResetToDefault() {
        customizeWidgetPage.clickBtn("Reset to default");

    }

    @Step
    public void saveSetting() {
        customizeWidgetPage.saveSetting("All changes were successfully saved");
    }

    @Step
    public void enterHeader(String sHeader) {
        if (!sHeader.isEmpty()) {
            customizeWidgetPage.enterHeader(sHeader);
        }
    }

    public void settingFontHeader(String sFontHeader) {
        settingTextStyle("Header", sFontHeader);
    }

    @Step
    public void settingFontProductName(String sFontProductName) {
        settingTextStyle("Product name", sFontProductName);

    }

    public void settingTextStyle(String _labelName, String _value) {
        if (!_value.isEmpty()) {
            String[] t = _value.split(">");
            selectFontSize(_labelName, t[0]);
            settingStyleFont(_labelName, t[1]);
        }
    }

    public void settingStyleFont(String labelName, String value) {
        int numberStyle = customizeWidgetPage.getNumberOfStyle(labelName);
        List<String> listStyleSelected = asList(value.split(","));

        for (int i = 1; i <= numberStyle; i++) {
            String style = customizeWidgetPage.getStyleByIndex(labelName, i);
            if (listStyleSelected.contains(style)) {
                customizeWidgetPage.checkStyleFont(labelName, style, true);
            } else {
                customizeWidgetPage.checkStyleFont(labelName, style, false);
            }
        }
    }

    public void selectFontSize(String labelName, String value) {
        if (!value.isEmpty()) {
            customizeWidgetPage.selectDDLWidget(labelName, value);
        }
    }

    public void settingFontProductPrice(String sFontProductPrice) {
        if (!sFontProductPrice.isEmpty())
            settingTextStyle("Product price", sFontProductPrice);

    }

    public void selectNumberOfProductsToBeShown(String sNumberOfProducts) {
        if (!sNumberOfProducts.isEmpty())
            customizeWidgetPage.selectNumberOfProductsToBeShown(sNumberOfProducts);
    }

    public void selectMaxProductsPerSlide(String maxProducts) {
        if (!maxProducts.isEmpty())
            customizeWidgetPage.selectMaxProductsPerSlide(maxProducts);
    }

    public void settingButtonAddCart(boolean isTurnOnAddCartButton, String sCallToActionText) {
        turnOnAddToCartButton(isTurnOnAddCartButton);
        if (isTurnOnAddCartButton) {
            settingTextStyle("Call to action text", sCallToActionText);

        }

    }

    public void turnOnAddToCartButton(boolean isTurnOnAddCartButton) {
        customizeWidgetPage.turnOnAddToCartButton(isTurnOnAddCartButton);
    }

    @Step
    public void choosePageShowWidget(String pages) {
        if (!pages.isEmpty()) {
            int numberPage = customizeWidgetPage.getNumberOfPage();
            List<String> listPageSlected = asList(pages.split(","));
            for (int i = 1; i <= numberPage; i++) {
                String page = customizeWidgetPage.getPageByIndex(i);
                if (listPageSlected.contains(page)) {
                    customizeWidgetPage.choosePageShowWidget(page, true);
                } else {
                    customizeWidgetPage.choosePageShowWidget(page, false);
                }
            }
        }

    }

    public void backToListOffer() {
        customizeWidgetPage.clickBreadcrumb();
    }


}
