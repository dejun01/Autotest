package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.ImportProductCatalogPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ImportProductCatalogStep {

    ImportProductCatalogPage importPage;

    public void verifyProductName(String product) {
        String act = importPage.getProductName();
        assertThat(act).contains(product);
    }

    public void verifyProductPrice(String label, float val, boolean _isBoolean, String product) {
        float act = Float.parseFloat(importPage.getProductPrice(label, product));
        assertThat(act).isEqualTo(val);
        importPage.verifyProperty(label, _isBoolean);
    }

    public void verifyProfitMargin(String label, float val, String product) {
        float act = importPage.getProfitMargin(label, product);
        assertThat(act).isEqualTo(val);
    }

    public void choiceProductImport(String product) {
        importPage.choiceProductImport(product);
    }

    public void EnterPriceVariantImport(String[] priceVariants) {
        importPage.clickLabel("Set prices per variant");
        int index = 0;
        for (String val : priceVariants) {
            index++;
            importPage.clickCheckbox(index);
            importPage.EnterValueVariant(val, index);
        }
    }

    public void clickButton(String buttonName) {
        importPage.clickButton(buttonName);
    }

    public void actionAllProductInImportListPage(String actName) {
        importPage.checkSelectAll("Select all");
        importPage.clickButton("Actions");
        importPage.actionAllProduct(actName);
    }

    public void verifyShipToInImportList(String shipTo) {
        importPage.verifyCountryShow(shipTo);
    }

    public void verifyShippingMethodInImportList(String shippingMethod) {
        String act = importPage.getShippingMethodInImportList();
        assertThat(act).isEqualTo(shippingMethod);
    }

    public void verifyCostVariantInImportList(String variant, String cost) {
        String act = importPage.getCostVariantInImportList(variant);
        assertThat(act).isEqualTo(cost);
    }

    public void verifyFirstItemVariantInImportList(String variant, String firstItem) {
        String act = importPage.getFirstItemVariantInImportList(variant);
        assertThat(act).isEqualTo(firstItem);
    }

    public void verifyAdditionalItemVariantInImportList(String variant, String addtionalItem) {
        String act = importPage.getAdditionalItemVariantInImportList(variant);
        assertThat(act).isEqualTo(addtionalItem);
    }

    public void verifyProfitVariantInImportList(String variant, String profit) {
        String act = importPage.getProfitVariantInImportList(variant);
        assertThat(act).isEqualTo(profit);
    }

    public void selectTabPricing() {
        importPage.selectTabPricing();
    }

    public void clickEnableForBuyers(String product) {
        importPage.clickEnableForBuyers(product);
    }

    public void selectProductToImport(String product) {
        importPage.selectProductToImport(product);
    }

    public void importProductToStore(String product) {
        importPage.importProductToStore(product);
    }
}
