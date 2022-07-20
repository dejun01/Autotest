package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.BulkEditorPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class BulkEditorSteps extends ScenarioSteps {

    BulkEditorPages bulkEditorPages;

    @Step
    public void editTitle(String title, String titleUpdate) {
        bulkEditorPages.editTitle(title, titleUpdate);
    }

    @Step
    public void editTags(String tags, String tagsUpdate) {
        bulkEditorPages.editTags(tags, tagsUpdate);
    }

    @Step
    public void editProductType(String type, String typeUpdate) {
        bulkEditorPages.editProductType(type, typeUpdate);
    }

    @Step
    public void editVendor(String vendor, String vendorUpdate) {
        bulkEditorPages.editVendor(vendor, vendorUpdate);
    }

    @Step
    public void editPrice(String option, String price, String priceUpdate) {
        bulkEditorPages.editPrice(option, price, priceUpdate);
    }

    @Step
    public void editCompareAtPrice(String compareAtPrice, String compareAtPriceUpdate) {
        bulkEditorPages.editCompareAtPrice(compareAtPrice, compareAtPriceUpdate);
    }

    @Step
    public void editCostPerItem(String costPerItem, String costPerItemUpdate) {
        bulkEditorPages.editCostPerItem(costPerItem, costPerItemUpdate);
    }

    @Step
    public void verifyTitle(String titleUpdate) {
        bulkEditorPages.verifyTitle(titleUpdate);
    }

    @Step
    public void verifyProductType(String typeUpdate) {
        bulkEditorPages.verifyProductType(typeUpdate);
    }

    @Step
    public void verifyVendor(String vendorUpdate) {
        bulkEditorPages.verifyVendor(vendorUpdate);
    }

    @Step
    public void clickCompareAtPrice() {
        bulkEditorPages.clickCompareAtPrice();
    }

    @Step
    public void clickSaveChanges() {
        bulkEditorPages.waitForPageLoad();
        bulkEditorPages.clickBtn("Save changes");
        waitABit(3000);
    }

    @Step
    public void editSku(String sku, String skuUpdate) {
        bulkEditorPages.editSku(sku, skuUpdate);
    }

    @Step
    public void editWeight(String weight, String weightUpdate) {
        bulkEditorPages.editWeight(weight, weightUpdate);
    }
}