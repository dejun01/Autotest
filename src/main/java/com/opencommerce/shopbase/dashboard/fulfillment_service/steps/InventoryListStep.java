package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.InventoryListPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.PurchaseOrderDetailPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryListStep {

    InventoryListPage inventoryListPage;
    PurchaseOrderDetailPage purchaseOrderDetailPage;

    @Step
    public void enterCondition(String status, String condition, String value) {
        inventoryListPage.clickStatusInventory(status);
        inventoryListPage.clickCondition(condition);
        inventoryListPage.enterValueFilter(value);
    }

    @Step
    public void verifyDataSearchWithCondition(String conditionValue, String title) {
        inventoryListPage.verifyDataSearchWithCondition(conditionValue, title);
    }

    @Step
    public void verifyMessage(String message) {
        inventoryListPage.verifyMessage(message);
    }

    @Step
    public String openFileDownloaded() {
        return inventoryListPage.getPathFileDownloaded();
    }

    @Step
    public void verifyDownloadFileMatchingTemplate(ArrayList expExportProductList) {
        inventoryListPage.verifyDownloadFileMatchingTemplate(expExportProductList);
    }

    @Step
    public void clickButtonOnPopup(String buttonName) {
        inventoryListPage.clickButtonOnPopup(buttonName);
    }

    @Step
    public void verifyNotiExportEqual(String noti, String email) {
        inventoryListPage.verifyNotiExportEqual(noti, email);
    }

    @Step
    public void getProductInventoryDefault() {
        inventoryListPage.getProductInventoryDefault();
    }

    @Step
    public void searchProductWith(String search) {
        inventoryListPage.searchProductWith(search);
    }

    @Step
    public void clickElementProductInInventory() {
        inventoryListPage.clickElementProductInInventory();
    }

    @Step
    public void clickRadioExportWith(String searchFilter) {
        inventoryListPage.clickRadioExportWith(searchFilter);
    }

    @Step
    public void selectProductExport() {
        inventoryListPage.selectProductExport();
    }

    @Step
    public void chooseProductImport() {
        inventoryListPage.chooseProductImport();
    }

    @Step
    public void clickButtonImportStore() {
        int index = purchaseOrderDetailPage.getIndexColumTableQuotation("PRODUCT");
        inventoryListPage.clickActionButton();
        inventoryListPage.clickButtonImportStore("Import to store");
    }

    @Step
    public void verifyImportProductSuccess() {
        inventoryListPage.verifyImportProductSuccess();
    }

    @Step
    public void searchProductImport(String product) {
        inventoryListPage.searchProductImport(product);
    }

    @Step
    public void verifyProductImport(String product) {
        inventoryListPage.verifyProdcut(product);
        inventoryListPage.verifyStatus();
        inventoryListPage.verifyOtherInforOfProduct("TYPE,VENDOR");
    }

    @Step
    public void chooseMultipleProductImport() {
        inventoryListPage.chooseMultipleProductImport();
    }

    @Step
    public void clickButtonImportMultipleStore() {
        inventoryListPage.clickButtonAction();
        inventoryListPage.clickButtonImportMultipleStore();
    }

    @Step
    public void clickButton(String button) {
        inventoryListPage.clickButton(button);
    }

    @Step
    public void verifyShowClearData() {
        inventoryListPage.verifyRadioClearFilter();
        inventoryListPage.verifyInputClearFilter();
        inventoryListPage.verifyButtonClear();
        inventoryListPage.verifyButtonSaveFilet();
    }

    @Step
    public void saveConditionInFilter() {
        inventoryListPage.enterInfoNameFilter();
        inventoryListPage.clickButtonSave();
        clickButton("Filter templates");
        inventoryListPage.verifyInfoSaveFilter();
    }

    @Step
    public void clickProduct(String product) {
        inventoryListPage.clickProduct(product);
    }

    @Step
    public HashMap<String, Integer> getValueItemVarian(String variant) {
        HashMap<String, Integer> data = new HashMap<>();
        int count = inventoryListPage.countcol(variant);
        for (int i = 3; i <= count - 1; i++) {
            data.put(inventoryListPage.getLabelInCol(i, variant), inventoryListPage.getValueInCol(i, variant));
        }
        return data;
    }


    public void searchProductInQuotation(String product) {
        inventoryListPage.searchProductInQuotation(product);
    }

    public void switchTab(String tabName) {
        inventoryListPage.switchTab(tabName);
    }

    public void moveToViewQuotation(String product) {
        inventoryListPage.moveToViewQuotation(product);
    }

    public void purchaseProductWithVariant(String variant, String quantity) {
        inventoryListPage.fillQuantity(variant, quantity);
        inventoryListPage.clickBtn("Purchase");
        inventoryListPage.clickBtn("Pay");
    }

    public void moveToHomePage() {
        inventoryListPage.moveToHomePage();
    }

    public void deleteProductImport() {
        inventoryListPage.clickDeleteButton("Delete product");
        inventoryListPage.clickDeleteButtonOnPopup("Delete product");
    }

    public void moteToProductDetail(String product) {
        inventoryListPage.moteToProductDetail(product);
    }

    public void moveToPage(String moveToPage) {
        inventoryListPage.moveToPage(moveToPage);
    }

    public JsonPath getInventoryByAPI(String product) {
        String accessToken = inventoryListPage.getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String api = "https://" + shop + "/admin/panda-sourcing/warehouse/inventory.json?q=" + product + "";
        return inventoryListPage.getAPISbase(api, accessToken);
    }

    public int getCountInventory(JsonPath getInventoryByAPI, String product, String variant, String status) {
        String result = "result.find{it.product_name=='" + product + "'}.variants.find{it.variant_name=='" + variant + "'}." + status + "";
        return (int) inventoryListPage.getData(getInventoryByAPI, result);
    }
}
