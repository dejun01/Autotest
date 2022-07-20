package com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.pages.MappingProductsPages;
import common.utilities.LoadObject;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.opencommerce.shopbase.OrderVariable.accesstoken;
import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.mappedProducts;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.mappingProdVar;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.aliCost;

public class MappingProductsSteps extends ScenarioSteps {
    String shopDomain = LoadObject.getProperty("shop");

    MappingProductsPages mappingProductsPages;
    private final String BUTTON_IMPORT = "Import";


    @Step
    public void verifyTitleMapProduct(String expectText) {
        mappingProductsPages.waitForPageLoad();
        mappingProductsPages.waitForEverythingComplete();
        mappingProductsPages.verifyTitleMapProduct(expectText);
    }

    @Step
    public void clickBtnImport() {
        mappingProductsPages.clickBtn(BUTTON_IMPORT);
        mappingProductsPages.waitForEverythingComplete(8);
    }

    public String getProductCostByVariantAdded(String aliProdUrl) {
        String request = "https://" + shopDomain + "/admin/ali-dropship-connector/crawl.json?access_token=" + accesstoken;
        String[] varList = {""};
        String variant1 = "", variant2 = "", variant3 = "";
        String aliValue1 = "", aliValue2 = "", aliValue3 = "";
        int countVar = 1;

        if (mappingProdVar.contains("/")) {
            varList = mappingProdVar.replaceAll("\\s+", "").split(":")[1].split("/");
        } else {
            varList = mappingProdVar.replaceAll("\\s+", "").split(":");
        }
        countVar = varList.length;

        switch (countVar) {
            case 1:
                variant1 = varList[0];
                break;
            case 2:
                variant1 = varList[0];
                variant2 = varList[1];
                break;
            case 3:
                variant1 = varList[0];
                variant2 = varList[1];
                variant3 = varList[2];
        }

        aliValue1 = mappedProducts.get(variant1);
        if (!variant2.isEmpty()) {
            aliValue2 = mappedProducts.get(variant2);
        }
        if (!variant3.isEmpty()) {
            aliValue3 = mappedProducts.get(variant3);
        }

        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("url", aliProdUrl);
        Response response = mappingProductsPages.postAPI(request, requestParams);

        String expressionOption = "product.variants[%d].option";
        String expressionPrice = "product.variants[%d].price";
        String AliValue = "";

        String _expressionOption = "";
        String _expressionPrice = "";
        for (int i = 0; i < 254; i++) {
            _expressionOption = String.format(expressionOption, i);
            _expressionPrice = String.format(expressionPrice, i);
            AliValue = response.then().extract().path(_expressionOption).toString();
            if (AliValue.contains(aliValue1) && AliValue.contains(aliValue2) && AliValue.contains(aliValue3)) {
                aliCost = response.then().extract().path(_expressionPrice).toString();
                System.out.println("aliCost = " + aliCost);
                break;
            }
        }
        return aliCost;
    }

    public String getAliProductCostByVariant() {
        return "";
    }

    @Step
    public void inputURLAli(String urlAli) {
        mappingProductsPages.inputURLAli(urlAli);
    }

    @Step
    public void verifyProductMappingSectionDisplay(String expectText, String displayPopup) {
        mappingProductsPages.waitForEverythingComplete();
        mappingProductsPages.verifyProductMappingSectionDisplayed(expectText);
    }

    @Step
    public void verifyShopbaseMappingSectionDisplay(String data, String displayPopup) {
        mappingProductsPages.verifyShopbaseProductSectionDisplayed(data);
    }

    @Step
    public void verifyAliExpressMappingSectionDisplay(String data, String displayPopup) {
        mappingProductsPages.verifyAliExpressProductSectionDisplayed(data);
    }

    @Step
    public void verifyErrorMessageImportDisplay(String exResult) {
        mappingProductsPages.waitForElementVisible();
        mappingProductsPages.verifyErrorMessageDisplayed(exResult);

    }

    @Step
    public void selectOptionSBProduct(String value, boolean bSelect) {
        mappingProductsPages.selectOptionSBProduct(value, bSelect);

    }

    @Step
    public void selectVariantAliExpressProduct(String value, boolean bSelect) {
        mappingProductsPages.selectVariantAliExpressProduct(value, bSelect);
    }

    @Step
    public void clickButtonSaveChanges(String buttonText) {
        mappingProductsPages.clickButtonSaveChanges(buttonText);
    }

    @Step
    public void verifyToastMessageDisplay(String message) {
        mappingProductsPages.verifyToastMessageDisplay(message);
    }

    @Step
    public void verifyUrlDisplay(String url) {
        mappingProductsPages.verifyTextInputField(mappingProductsPages.PLACEHOLDER_INPUT, url);
    }

    @Step
    public void unCheckCheckbox(String option) {
        mappingProductsPages.unCheckCheckbox(option);
    }

    @Step
    public void verifyProductDeleted(String expect) {
        mappingProductsPages.verifyProductDeleted(expect);
    }

    @Step
    public void verifyPopupDisplay(boolean popup) {
        mappingProductsPages.checkPopupVisible(popup);
    }

    @Step
    public void confirmPopupOverride(String btnLabel) {
        mappingProductsPages.confirmPopupOverride(btnLabel);
    }

    @Step
    public void switchToLastestTab() {
        mappingProductsPages.switchToTheLastestTab();
    }

    @Step
    public void switchToTheFirstTab() {
        mappingProductsPages.switchToTheFirstTab();
    }

    public void enterAliProductUrl(String aliLink) {
        mappingProductsPages.enterInputFieldWithLabel("Enter new product link here", aliLink);
    }

    @Step
    public void mappingSbProductWithAliProduct(String option, String values) {
        mappingProductsPages.mappingSbProductWithAliProduct(option, values);
        mappingProductsPages.waitForEverythingComplete();
    }

    public void clickOnSaveChangesBtn() {
        mappingProductsPages.clickBtn("Save changes");
        mappingProductsPages.waitForEverythingComplete();
        mappingProductsPages.waitUntilOrderIsExpanded(orderNumber);
    }

    @Step
    public void selectAliOption() {
        mappingProductsPages.selectAliOption(true);
        mappingProductsPages.clickBtn("Save changes");
        mappingProductsPages.waitForEverythingComplete();
    }

    public void verifyMsgDisplayed(String msg) {
        mappingProductsPages.verifyTextPresent(msg, true);
    }
}
