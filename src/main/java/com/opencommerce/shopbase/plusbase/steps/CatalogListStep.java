package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.CatalogListPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogListStep {
    CatalogListPage catalogListPage;

    public void searchProduct(String val, String placeHolder) {
        catalogListPage.enterInputFieldThenEnter(placeHolder, val, 1);
    }

    public void verifyInfoProductWith(String product, boolean status, String baseCost, String sellingPrice) {
        if(status) {
            catalogListPage.verifyProduct(product);
            catalogListPage.verifyBaseCost(baseCost);
            catalogListPage.verifySellingPrice(sellingPrice);
        } else {
            catalogListPage.verifySearchNoData();
        }
    }

    public void clickLabelFilter(String label) {
        catalogListPage.clickLabelFilter(label);
    }

    public void choiceConditions(String label, String conditions) {
        catalogListPage.choiceConditions(label, conditions);
    }

    public void fillValue(String label, String value) {
        catalogListPage.fillValue(label, value);
    }

    public void verifyResultFilter(String label, String conditions, String value) {
        catalogListPage.verifyResultFilter(label, conditions, value);
    }

    public void moveToProductDetail(String product) {
        catalogListPage.moveToProductDetail(product);
    }

    public void verifyProductDetail(String product) {
        catalogListPage.verifyProductDetail(product);
    }

    public void verifyResult(String btnClaer) {
        catalogListPage.verifyResult(btnClaer);
    }

    public void clickCategoryOrSubCategoryName(String option, String name) {
        catalogListPage.clickCategoryOrSubCategoryName(option, name);
    }

    public void removedTagWithValue(String tag) {
        catalogListPage.removedTagWithValue(tag);
    }

    public void moveToScreenInPlusbase(String label) {
        catalogListPage.moveToScreenInPlusbase(label);
    }

    public void verifyImportSuccess(String mess) {
//        if(!catalogListPage.isElementExist("//span[@class='sb-button--loading-state']")) {
        if(!catalogListPage.checkElementExit()) {
            catalogListPage.verifyTextPresent(mess, true);
            catalogListPage.verifyTextPresent("have been added to store", true);
            catalogListPage.verifyTextPresent("Edit product", true);
            catalogListPage.verifyTextPresent("View on store", true);
        }
    }

    public void switchTabLastest() {
        catalogListPage.switchToLatestTab();
    }

    public void verifyVariant(String[] variant) {
        catalogListPage.verifyVariant(variant);
    }

    public void deleteProduct(String label) {
        catalogListPage.clickBtn(label);
        catalogListPage.clickDeleteButton(label);
    }

    public void verifySellingPrice(String[] val) {
        catalogListPage.verifySellingPriceInProduct(val);
    }

    public void verifyProductGroup(String product, String text) {
        catalogListPage.verifyProductGroup(product, text);
    }

    public void clickProduct(String product) {
        catalogListPage.clickProduct(product);
    }
    @Step
    public void clickToProduct(String productName) {
        catalogListPage.clickOnProduct(productName);
    }
    @Step
    public void verifyOutOfStockInCatalogDetail(String productName,String status) {
        assertThat(catalogListPage.getProdName(productName)).contains(productName);
        catalogListPage.verifyButtonImportToStore(status);
    }

    public void clickVariant(String label, String val) {
        catalogListPage.clickVariant(label, val);
    }

    float shippingCurrent = 0;
    float shippingChange = 0;
    float sellingPriceCurrent = 0;
    public void getShippingAndSellingPriceCurrent() {
        sellingPriceCurrent = catalogListPage.getSellingPriceCurrent("Selling price");
        shippingCurrent = catalogListPage.getShippingCurrent("First item");
    }

    public void fillDataMarkup(String product, String label, String variant, String val) {
        if(!val.isEmpty()) {
            if("SHIPPING".equals(label)) {
                shippingChange = shippingCurrent - Float.parseFloat(val);
                shippingCurrent = Float.parseFloat(val);
            }
            if("SELLING PRICE".equals(label)) {
                sellingPriceCurrent = Float.parseFloat(val);
            }
            catalogListPage.fillDataMarkup(product, label, variant, val);
        }

    }

    public void verifyProfitMarginMarkup(String product, String variant) {
        float cost = catalogListPage.getValueMarkup(product, variant, "COST");
        float profitMargin = catalogListPage.getValueMarkup(product, variant, "PROFIT MARGIN");
        float paymentFee = (float) ((sellingPriceCurrent + shippingCurrent)*0.03);
        float processingFee = (float) ((sellingPriceCurrent - cost - paymentFee - shippingChange)*0.04);
        float exp = catalogListPage.roundTwoDecimalPlaces(sellingPriceCurrent - cost - paymentFee - processingFee - shippingChange);
        assertThat(profitMargin).isEqualTo(exp);
    }

    public void clickButton(String buttonName) {
        catalogListPage.clickButtonImport(buttonName);
    }

    public void clickTab(String tabName, String product) {
        catalogListPage.clickTab(tabName, product);
    }

    public void verifyProductName(String product) {
        catalogListPage.verifyTextPresent(product, true);
    }

    public void focusOut() {
        catalogListPage.focusOut();
    }
}
