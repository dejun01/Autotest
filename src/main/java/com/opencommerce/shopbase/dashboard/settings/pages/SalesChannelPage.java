package com.opencommerce.shopbase.dashboard.settings.pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SalesChannelPage extends SBasePageObject {
    public SalesChannelPage(WebDriver driver) {
        super(driver);
    }

    public void loginToPinterest(String username, String password) {
        String xpathUsername = "//input[@id='email']";
        String xpathPassword = "//input[@id='password']";
        String xpathLogin = "//button//div[text()='Log in']";

        waitClearAndType(xpathUsername, username);
        waitClearAndType(xpathPassword, password);
        clickOnElement(xpathLogin);
        waitForEverythingComplete();
    }

    public void navigateToConversionPagePinterest() {
        String xpathAds = "//div[text()='Ads']";
        String xpathConversion = "//a[text()='Conversions']";
        clickOnElement(xpathAds);
        waitElementToBeVisible(xpathConversion);
        clickOnElement(xpathConversion);
    }

    public void clickAddEventTypePinterest() {
        String xpath = "//button[@aria-label='add a new event type']";
        clickOnElement(xpath);
    }

    public void clickToSavePinterest() {
        String xpath = "//button//div[text()='Save']";
        clickOnElement(xpath);
    }

    public void addEventPinterest(String event) {
        String xpath = "//div[text()='" + event + "']";
        clickOnElement(xpath);
    }

    public String getDataEventPinterest(String event) {
        String xpath = "//div[text()='" + event + "']//ancestor::div[@class='Jea dSH sxy gjz zI7 iyn Hsu']//following-sibling::div[@class='Jea dSH sxy gjz zI7 iyn Hsu']//div[@class='tBJ dyH iFc HH0 pBj zDA IZT swG']";
        String amount = getText(xpath);
        return amount;
    }


    public void clickEventManager() {
        String xpath = "//div[@class='css-k8fyq8']//div[text()='Events Manager']";
        clickOnElement(xpath);
    }

    public void clickEventManagerDetail() {
        String xpath = "//div[@class='events-manager-home-summary-chart']";
        clickOnElement(xpath);
    }


    public boolean isPinterestNoData() {
        String xpath = "//div[text()='No activity for your applied filters.']";
        return isElementVisible(xpath, 5);
    }

    public void chooseRadioButton(String radioButtonName) {
        String xpath = "//div[@class='s-form-item']//span[text()[normalize-space()='" + radioButtonName + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void enterFeedName(String feedName) {
        enterInputFieldWithLabel("Collection name - Product Feed", feedName);
    }

    public void chooseCollection(String collectionName) {
        enterInputFieldWithLabel("Search for collections", collectionName);
        waitForEverythingComplete();
        String checkBox = "//div[contains(@class,product-title) and normalize-space()='Collection Sample']//preceding-sibling::label[@class='s-checkbox']//span[@class='s-check']";
        String checkBoxStatus = "//div[contains(@class,product-title) and normalize-space()='Collection Sample']//preceding-sibling::label[@class='s-checkbox']//input";
        verifyCheckedThenClick(checkBoxStatus, checkBox, true);
    }

    public void checkOnCheckboxExclude() {
        String xpathCheckboxExclude = "//span[normalize-space()='Exclude the variations matching']";
        String xpathCheckboxExcludeStatus = "//span[normalize-space()='Exclude the variations matching']//preceding-sibling::input";
        verifyCheckedThenClick(xpathCheckboxExcludeStatus, xpathCheckboxExclude, true);
    }

    public void chooseOption(String option, int position) {
        String xpathOption = "(//select[@class]/option[normalize-space()='" + option + "'])[" + position + "]";
        clickOnElement(xpathOption);
    }

    public void enterDefaultBrandName(String defaultBrandName) {
        enterInputFieldWithLabel("Your brand name", defaultBrandName);
        waitForEverythingComplete();
    }

    public void chooseGoogleProductCategory(String googleProductCategory) {
        enterInputFieldWithLabel("Search for Google product category", googleProductCategory);
    }

    public void clickFeedSaveButton() {
        String saveChanged = "//span[contains(., 'Save')]//parent::button[contains(@class, 'button')]";
        clickOnElement(saveChanged);
    }

    public void verifyNumberOfVariant(int actualNumberOfVariant, String feedName) {
        String xpath = "(//span[contains(., '" + feedName + "')]//ancestor::td[contains(@class, 'pointer')]//following-sibling::td)[1]";
        Integer numberOfVariant = Integer.parseInt(getText(xpath));
        assertThat(actualNumberOfVariant).isEqualTo(numberOfVariant);
        clickOnElement("//span[contains(., '" + feedName + "')]//parent::a[contains(@href, 'product-feeds')]");
    }

    public void openManageProductData() {
        String xpathButton = "//div[contains(@class, 'manage-product-data')]//a[contains(., 'Manage Product Data')]";
        clickOnElement(xpathButton);
    }

    public void verifyNumberOfVariantInProductData(int actualNumberOfVariantInProductData, int i) {
        waitForPageLoad();
        String checkboxStatus = "//span[@data-label='Select all products']//label";
        String checkDisableCheckbox = getAttributeValue(checkboxStatus, "class");
        int allVariant = 0;
        if (!checkDisableCheckbox.contains("disable")) {
            String checkboxSelectAllVariantStatus = "//span[@data-label='Select all products']//input";
            String checkboxSelectAllVariant = "//span[@data-label='Select all products']//span[@class='s-check']";
            verifyCheckedThenClick(checkboxSelectAllVariantStatus, checkboxSelectAllVariant, true);
            String xpathVariantQuantity = "//span[@class='s-control-label']//span[contains(.,'selected')]";
            String variantsSelected = getText(xpathVariantQuantity);
            allVariant = Integer.parseInt(variantsSelected.split(" ")[i]);
            assertThat(actualNumberOfVariantInProductData).isEqualTo(allVariant);
        }
        else {
            assertThat(actualNumberOfVariantInProductData).isEqualTo(allVariant);
        }
    }

    public JsonPath getDataInManageProductDataFromAPI() {
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/feed/v2/fetch-list-all-products.json?access_token=" + accessToken + "&platform=google&limit=30&page=1&tab=&status=&title=&search=";
        JsonObject requestParams = new JsonObject();
        JsonObject params = new JsonObject();
        requestParams.add("modes", params);
        requestParams.add("values", params);
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        return jp;
    }

    public String getDataInManageProductData(JsonPath rs, String title, String filter) {
        String value = rs.get("result.findAll{it.title=='" + title + "'}." + filter).toString().replaceAll("\\[", "").replaceAll("\\]", "");
        if (filter.equals("description")) {
            value = value.replaceAll("\n", "");
        }
        return value;
    }

    public void openInFeedList(String feedNameTemp) {
        String xpath = "//span[normalize-space()='" + feedNameTemp + "']//parent::a[@href]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public long getVariantid() {
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/feed/v2/fetch-list-all-products.json?access_token=" + accessToken + "&platform=google&limit=30&page=1&tab=&status=&title=&search=";
        JsonObject requestParams = new JsonObject();
        JsonArray ar = new JsonArray();
        requestParams.add("modes", ar);
        requestParams.add("values", ar);
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        String getValue = jp.get("result.findAll{it.title=='Sample Test 1 Black'}.variant_id").toString().replaceAll("\\[", "").replaceAll("\\]", "");
        long value = Long.parseLong(getValue);
        return value;
    }

    public void objectJp(JsonObject requestParams) {
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        long variantId = getVariantid();
        String url = "https://" + shop + "/admin/feed/v2/update-feed-data.json?access_token=" + accessToken + "&published_status=all_products&platform=google&has_error=&export_track_id_tag=false&variant_ids=" + variantId + "&excluded_ids=&tab=&status=&title=&search=";
        JsonObject requestAnotherParams = new JsonObject();
        requestAnotherParams.add("changes", requestParams);
        JsonArray array = new JsonArray();
        requestAnotherParams.add("excluded_ids", array);
        requestAnotherParams.addProperty("platform", "google");
        requestAnotherParams.addProperty("published_status", "all_products");
        requestAnotherParams.addProperty("search", "");
        requestAnotherParams.addProperty("type", "");
        JsonArray anotherArray = new JsonArray();
        anotherArray.add(variantId);
        requestAnotherParams.add("variant_ids", anotherArray);
        requestAnotherParams.add("variants", anotherArray);
        postAPI(url, requestAnotherParams);
    }

    public void deleteAllFeedInFeedList() {
        String xpathFeedList = "//tbody//tr[@class='draggable-item']";
        int countFeed = countElementByXpath(xpathFeedList);
        for (int i = 0; i < countFeed; i++) {
            String xpathDelete = "(//i[contains(@class, 'delete')]//parent::span[@class])";
            String xpathConfirmDelete = "//span[contains(., 'Delete')]//ancestor::button[contains(@class, 'is-danger')]";
            clickOnElement(xpathDelete);
            clickOnElement(xpathConfirmDelete);
        }
    }

    public void chooseFeedType(String feedType) {
        String feedTypeButton = String.format("//div[contains(@class, 'modal-body')]//h5[text()='%s']//ancestor::label//span", feedType);
        boolean isStatus = XH(feedTypeButton).isEnabled();
        if (isStatus) {
            clickOnElement(feedTypeButton);
            clickBtn("Confirm");
        }
    }

    public void openFeedName(String feedName) {
        String xpathFeedName = String.format("//tbody//span[normalize-space()='%s']//parent::a[@href]", feedName);
        clickOnElement(xpathFeedName);
        waitForEverythingComplete();
    }
}
