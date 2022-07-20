package com.opencommerce.shopbase.dashboard.settings.steps;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.settings.pages.SalesChannelPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class SalesChannelSteps extends ScenarioSteps {
    SalesChannelPage salesChannelPage;

    @Step
    public void loginToPinterest(String username, String password) {
        salesChannelPage.loginToPinterest(username, password);
    }

    public void navigateToConversionPagePinterest() {
        salesChannelPage.navigateToConversionPagePinterest();
    }

    public void clickAddEventTypePinterest() {
        salesChannelPage.clickAddEventTypePinterest();
    }

    public void clickToSavePinterest() {
        salesChannelPage.clickToSavePinterest();
    }

    public void addEventPinterest(String event) {
        salesChannelPage.addEventPinterest(event);
    }

    public String getDataEventPinterest(String event) {
        return salesChannelPage.getDataEventPinterest(event);
    }

    public void verifyDataChanged(String init, String increase, String actual) {
        assertThat(Integer.parseInt(init) + Integer.parseInt(increase)).isEqualTo(Integer.parseInt(actual));
    }

    public void switchToNextTab() {
        salesChannelPage.switchToNextTab();
    }

    public void clickEventManager() {
        salesChannelPage.clickEventManager();
    }

    public void clickEventManagerDetail() {
        salesChannelPage.clickEventManagerDetail();
    }

    public boolean isPinterestNoData() {
        return salesChannelPage.isPinterestNoData();
    }

    public void chooseRadioButton(String radioButtonName) {
        salesChannelPage.chooseRadioButton(radioButtonName);
    }

    public void addFeed(String feedType) {
        salesChannelPage.clickBtn("Add product feed");
        salesChannelPage.chooseFeedType(feedType);
    }

    public void enterFeedName(String feedName) {
        salesChannelPage.enterFeedName(feedName);
    }

    public void chooseCollection(String collectionName) {
        salesChannelPage.chooseCollection(collectionName);
    }

    public void checkOnCheckboxExclude() {
        salesChannelPage.checkOnCheckboxExclude();
    }

    public void chooseOption1(String option, int position) {
        salesChannelPage.chooseOption(option, position);
    }

    public void chooseOption2(String option, int position) {
        salesChannelPage.chooseOption(option, position);
    }

    public void chooseOption3(String option, int position) {
        salesChannelPage.chooseOption(option, position);
    }

    public void enterDefaultBrandName(String defaultBrandName) {
        salesChannelPage.enterDefaultBrandName(defaultBrandName);
    }

    public void chooseGoogleProductCategory(String googleProductCategory) {
        salesChannelPage.chooseGoogleProductCategory(googleProductCategory);
    }

    public void chooseGender(String defaultGender) {
        salesChannelPage.selectDdlWithLabel("Default Gender", defaultGender);
    }

    public void chooseAgeGroup(String defaultAgeGroup) {
        salesChannelPage.selectDdlWithLabel("Default Age Group", defaultAgeGroup);
    }

    public void clickFeedSaveButton() {
        salesChannelPage.clickFeedSaveButton();
    }

    public void verifyNumberOfVariant(int actualNumberOfVariant, int actualNumberOfVariantInProductData, String feedType, String feedName) {
        if (feedType.equals("API")) {
            salesChannelPage.openFeedName(feedName);
            salesChannelPage.openManageProductData();
            salesChannelPage.verifyNumberOfVariantInProductData(actualNumberOfVariantInProductData, 0);
        } else {
            salesChannelPage.verifyNumberOfVariant(actualNumberOfVariant, feedName);
        }
    }

    public String getDataInManageProductData(JsonPath rs, String title, String filter) {
        return salesChannelPage.getDataInManageProductData(rs, title, filter);
    }

    public void verifyVariantDetailInManageProductData(String value, String actualValue) {
        assertThat(value).isEqualTo(actualValue);
    }

    public void verifyVariantDetailInManageProductDataNumber(Double value, Double actualValue) {
        assertThat(value).isEqualTo(actualValue);
    }

    public void openInFeedList(String feedNameTemp) {
        salesChannelPage.openInFeedList(feedNameTemp);
    }

    public void openManageProductData() {
        salesChannelPage.openManageProductData();
    }

    public void objectJp(JsonObject requestParams) {
        salesChannelPage.objectJp(requestParams);
    }

    public void deleteAllFeedInFeedList() {
        salesChannelPage.deleteAllFeedInFeedList();
    }

    public JsonPath getDataInManageProductDataFromAPI() {
        return salesChannelPage.getDataInManageProductDataFromAPI();
    }
}
