package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.ProductFeedSyncGMCPages;


public class ProductFeedSyncGMCSteps {
    ProductFeedSyncGMCPages productFeedSyncGMCPages;


    public void enterFeedName(String feedName) {
        productFeedSyncGMCPages.enterInputFieldWithLabel("Feed name", feedName);
    }

    public void setUpAllVariantOrJustSome(String radioButtonLabel) {
        productFeedSyncGMCPages.selectRadioButtonWithLabel(radioButtonLabel, true);
    }

    public void clickCheckBoxExcludeVariationsOfAProduct() {
        productFeedSyncGMCPages.clickCheckBoxExcludeVariationsOfAProduct();
    }

    public void enterKeyWord(String keyWord) {
        productFeedSyncGMCPages.enterInputFieldWithLabel("Enter keyword", keyWord);
    }

    public void setProductTitle(String radioButtonLabel) {
        productFeedSyncGMCPages.selectRadioButtonWithLabel(radioButtonLabel, true);
    }

    public void setProductTitlePreference(String radioButtonLabel) {
        productFeedSyncGMCPages.selectRadioButtonWithLabel(radioButtonLabel, true);
    }

    public void setProductDescriptionPreference(String radioButtonLabel) {
        productFeedSyncGMCPages.selectRadioButtonWithLabel(radioButtonLabel,true);
    }

    public void submitProductsAsCustomProducts(String radioButtonLabel) {
        productFeedSyncGMCPages.selectRadioButtonWithLabel(radioButtonLabel,true);
    }

    public void setupBrandName(String brandName) {
        productFeedSyncGMCPages.enterInputFieldWithLabel("Default Brand Name", brandName);
    }

    public void setConditionExclude(String condition) {
        String xpathConditionExclude = "((//span[normalize-space()='Exclude the variations matching']//parent::label)//following-sibling::div//select)[2]";
        productFeedSyncGMCPages.selectDdlByXpath(xpathConditionExclude,condition);
    }

    public void downloadProductFeedUrl(int rowIndex) {
        int colIndex = productFeedSyncGMCPages.getColumnOfFeedListTableByTable("Product feed URL");
        productFeedSyncGMCPages.downloadProductFeedUrl(rowIndex,colIndex);
    }

    public int getRowFeedUrl(String feedName) {
        return productFeedSyncGMCPages.getRowFeedUrl(feedName);
    }

    public String openFileFeedDownloaded() {
        return productFeedSyncGMCPages.getPathFileDownloaded();
    }

    public void switchToTheFirstTab() {
        productFeedSyncGMCPages.switchToTheFirstTab();
    }

    public void backToProfuctFeedList() {
        productFeedSyncGMCPages.switchToTab("Product feeds");
    }

    public void setConditionMatching(String condition) {
        String xpathConditionMatching = "((//span[normalize-space()='Export only variations of a product matching'])/parent::label//following-sibling::div//select)[2]";
        productFeedSyncGMCPages.selectDdlByXpath(xpathConditionMatching,condition);
    }

    public void enterKeyWordMatching(String keyWord) {
        productFeedSyncGMCPages.enterKeyWordMatching(keyWord);
    }

    public void deleteProductFeed() {
        productFeedSyncGMCPages.deleteAllProductFeed();
        productFeedSyncGMCPages.confirmDeleteFeed();
        productFeedSyncGMCPages.refreshPage();
    }

    public boolean existFeedCanBeDelete() {
        return productFeedSyncGMCPages.existFeedCanBeDelete();
    }

    public void settingGMC(boolean isCheckGMC) {
        productFeedSyncGMCPages.checkGMCMethod(isCheckGMC);
    }

    public void goToProductFeedname(String feedName) {
        productFeedSyncGMCPages.goToProductFeednameIs(feedName);
    }

    public void verifyGMCInAllProductsFeedIsDisabled() {
        productFeedSyncGMCPages.verifyGMCInAllProductsFeedIsDisabled();
    }

    public void verifyFeedStatus(boolean isEnable) {
         productFeedSyncGMCPages.verifyFeedStatus(isEnable);
    }
}
