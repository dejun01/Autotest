package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.BulkUpdatePages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BulkUpdateSteps extends ScenarioSteps {
    BulkUpdatePages bulkUpdatePages;

    @Step
    public void clickBtnCreateAnUpdate() {
        bulkUpdatePages.clickBtn("Create an Update");
    }

    @Step
    public void selectFieldProduct(String fieldProductFilter, int index) {
        bulkUpdatePages.selectFieldProduct(fieldProductFilter, index);
    }

    @Step
    public void selectComparatorProduct(String comparatorProductFilter, int index) {
        bulkUpdatePages.selectComparatorProduct(comparatorProductFilter, index);
    }

    @Step
    public void selectValueProduct(String valueProductFilter, int index) {
        bulkUpdatePages.selectValueProduct(valueProductFilter, index);
    }

    @Step
    public void selectDisjunctiveProduct(String disjunctiveProductFilter) {
        bulkUpdatePages.selectDisjunctiveProduct(disjunctiveProductFilter);
    }

    @Step
    public void clickAddCondition() {
        bulkUpdatePages.clickBtn("Add condition");
    }

    @Step
    public void selectOnlyUpdateSomeVariants() {
        bulkUpdatePages.selectOnlyUpdateSomeVariants();
    }

    @Step
    public void selectDisjunctiveVariant(String disjunctiveVariantFilter) {
        bulkUpdatePages.selectDisjunctiveVariant(disjunctiveVariantFilter);
    }

    @Step
    public void selectFieldVariant(String fieldVariantFilter, int index) {
        bulkUpdatePages.selectFieldVariant(fieldVariantFilter, index);
    }

    @Step
    public void selectComparatorVariant(String comparatorVariantFilter, int index) {
        bulkUpdatePages.selectComparatorVariant(comparatorVariantFilter, index);
    }

    @Step
    public void selectValueVariant(String valueVariantFilter, int index) {
        bulkUpdatePages.selectValueVariant(valueVariantFilter, index);
    }

    @Step
    public void clickAddAction() {
        bulkUpdatePages.clickBtn("Add action");
    }

    @Step
    public void selectAction(String action) {
        bulkUpdatePages.selectAction(action);
    }

    @Step
    public void changeProductDescription(String value) {
        bulkUpdatePages.changeProductDescription(value);
    }

    public void clickPreviewBulkUpdate() {
        bulkUpdatePages.clickPreviewBulkUpdate();
    }

    @Step
    public void verifyPreviewBulkUpdateProductOnPopupPreview(String disjunctiveProductFilter, String fieldProductFilter, String comparatorProductFilter, String valueProductFilter) {
        bulkUpdatePages.verifyPreviewBulkUpdateProductOnPopupPreview(disjunctiveProductFilter, fieldProductFilter, comparatorProductFilter, valueProductFilter);
    }

    @Step
    public void verifyPreviewBulkUpdateVariantOnPopupPreview(String disjunctiveVariantFilter, String fieldVariantFilter, String comparatorVariantFilter, String valueVariantFilter) {
        bulkUpdatePages.verifyPreviewBulkUpdateVariantOnPopupPreview(disjunctiveVariantFilter, fieldVariantFilter, comparatorVariantFilter, valueVariantFilter);
    }


    public void verifyPreviewBulkUpdateActionOnPopupPreview(String actionSelected, String valueAction) {
        bulkUpdatePages.verifyPreviewBulkUpdateActionOnPopupPreview(actionSelected, valueAction);
    }

    @Step
    public void startBulkUpdate() {
        bulkUpdatePages.clickBtn("Start Bulk Update");
        bulkUpdatePages.waitForPageLoad();
    }

    @Step
    public void clickBtnUpdate() {
        bulkUpdatePages.clickBtnUpdate();
    }

    @Step
    public void verifyMessage(String message) {
        bulkUpdatePages.verifyMessage(message);
    }

    public void verifyPreviewBulkUpdateProductOnBulkUpdateList(String disjunctiveProductFilter, String fieldProductFilter, String comparatorProductFilter, String valueProductFilter) {
        bulkUpdatePages.verifyPreviewBulkUpdateProductOnBulkUpdateList(disjunctiveProductFilter, fieldProductFilter, comparatorProductFilter, valueProductFilter);
    }

    public void verifyPreviewBulkUpdateVariantOnBulkUpdateList(String bulkUpdateFor, String disjunctiveVariantFilter, String fieldVariantFilter, String comparatorVariantFilter, String valueVariantFilter) {
        bulkUpdatePages.verifyPreviewBulkUpdateVariantOnBulkUpdateList(bulkUpdateFor, disjunctiveVariantFilter, fieldVariantFilter, comparatorVariantFilter, valueVariantFilter);
    }

    public void verifyPreviewBulkUpdateActionOnBulkUpdateList(String actionSelected, String valueAction) {
        bulkUpdatePages.verifyPreviewBulkUpdateActionOnBulkUpdateList(actionSelected, valueAction);
    }

    @Step
    public void changeProductVendor(String valueAction) {
        bulkUpdatePages.changeProductVendor(valueAction);
    }

    @Step
    public void replaceDecription(String valueOld, String valueUpdate) {
        bulkUpdatePages.replaceDecription(valueOld, valueUpdate);
    }

    @Step
    public void changeProductType(String valueOld, String valueUpdate) {
        bulkUpdatePages.changeProductType(valueOld, valueUpdate);
    }

    @Step
    public void deleteVariant(String value) {
        bulkUpdatePages.deleteVariant(value);
    }

    @Step
    public void addTag(String valueAction) {
        bulkUpdatePages.addTag(valueAction, 0);
    }

    @Step
    public void changePriceTo(String valueAction) {
        bulkUpdatePages.changePriceTo(valueAction);
    }

    @Step
    public void increasePriceByAmount(String valueAction) {
        bulkUpdatePages.increasePriceByAmount(valueAction);
    }

    @Step
    public void decreasePriceByAmount(String valueAction) {
        bulkUpdatePages.decreasePriceByAmount(valueAction);
    }

    @Step
    public void changeCompareAtPriceTo(String valueAction) {
        bulkUpdatePages.changeCompareAtPriceTo(valueAction);
    }

    @Step
    public void decreaseCompareAtPriceByPercentage(String valueAction) {
        bulkUpdatePages.decreaseCompareAtPriceByPercentage(valueAction);
    }

    @Step
    public void roundBeautifyPrice(String valueAction) {
        bulkUpdatePages.roundBeautifyPrice(valueAction);
    }

    @Step
    public void previewBulkUpdate() {
        bulkUpdatePages.clickBtn("Preview Bulk Update");
        bulkUpdatePages.waitForPageLoad();
    }

    @Step
    public void clickUpdate() {
        bulkUpdatePages.clickBtn("Update");
        bulkUpdatePages.waitForPageLoad();
    }

    @Step
    public void deleteCustomOption(String valueAction) {
        bulkUpdatePages.deleteCustomOption(valueAction);
    }

    @Step
    public void verifyBulkUpDateFinished() {
        assertThat(bulkUpdatePages.isBulkFinised()).isEqualTo(true);
    }

    @Step
    public void verifyAfterBulkUpdate(int i, String sContain) {
        assertThat(bulkUpdatePages.getValueAfterBulkUpdate(i)).isEqualTo(sContain);
    }

}
