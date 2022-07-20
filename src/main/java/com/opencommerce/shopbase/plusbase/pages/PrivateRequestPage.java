package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateRequestPage extends SBasePageObject {
    public PrivateRequestPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getListTexLink() {
        String xpath = "//div[contains(@class,'product-link')]";
        return getListText(xpath);
    }

    public void searchProductName(String prrodName) {
        String xpathSearchBox = "//div[contains(@class, 'sb-tab-panel') and not(contains(@style,'display: none'))]//input[@placeholder='Search by product name, request link']";
        waitTypeAndEnter(xpathSearchBox, prrodName);
        String xpathNoOrder = "//div[text()[normalize-space()=concat('Sorry! We couldn',\"'t find any results\")]]";
        int i = 1;
        while (isElementExist(xpathNoOrder, 5) && i <= 20) {
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
    }

    public String getProductName() {
        String xpath = "//div[contains(@class, 'sb-tab-panel') and not(contains(@style,'display: none'))]//div[contains(@class,'catalog-products-review__product-review__name')]";
        //div[contains(text(),"Sports jogging brand men's quick-drying shorts sex")]
        return getText(xpath).trim();
    }

    public String getLink() {
        String xpath = "//div[contains(@class, 'sb-tab-panel') and not(contains(@style,'display: none'))]//a[contains(@class,'catalog-products-review__product-review__description')]";
        return getText(xpath).trim();
    }

    //
    public String getStatus() {
        String xpath = "//div[contains(@class, 'sb-tab-panel') and not(contains(@style,'display: none'))]//div[contains(@class,'catalog-products-review__product-review__price')]//span";
        return getText(xpath).trim();
    }

    public void clickNameProduct() {
        String xpath = "//div[contains(@class,'catalog-products-review__product-review__name')]";
        clickOnElementByJs(xpath);
    }

    public String getProductNameDetail() {
        String xpath = "//div[@class='sb-text-heading']";
        try {
            verifyElementVisible(xpath, true);
        } catch (Throwable t) {
            waitForEverythingComplete(10);
            refreshPage();
            verifyElementVisible(xpath, true);
        }
        return getText(xpath).trim();
    }

    public String getLinkDetail() {
        String xpath = "//a[@class='ellipsis']";
        return getText(xpath).trim();
    }

    public void getbtImportToStoreDetail(String btImportToStore, String status) {
        String xpath = "//span[normalize-space()='" + btImportToStore + "']//ancestor::button[@disabled='disabled']";
        boolean expStatus = status.equals("disabled") ? true : false;
        verifyElementVisible(xpath, expStatus);
    }

    public void getVariant(String variant) {
        String xpath = "//div[contains(text(),'Select variants to preview product cost & image')]";
        boolean expStatus = variant.isEmpty() ? false : true;
        verifyElementVisible(xpath, expStatus);
    }

    public void getProcessingTime(String processingTime) {
        String xpath = "//div[contains(text(),'Processing time')]//following-sibling::div";
        boolean expStatus = processingTime.isEmpty() ? false : true;
        verifyElementVisible(xpath, expStatus);
    }

    public void getProductCost(String productCost) {
        String xpath = "//div[contains(text(),'Variants, processing time and cost will be available once this request is ready.')]";
        boolean expStatus = productCost.isEmpty() ? true : false;
        verifyElementVisible(xpath, expStatus);
    }

    public void clickOnTab(String tab) {
        String xpath = "//div[contains(@class,'sb-tab-navigation--inside')]//div[contains(text(),'" + tab + "')]";
        clickOnElement(xpath);
    }

    public void selectproduct() {
        waitOnPage();
        clickOnElementByJs("//div[contains(@class,'sb-image__checkbox')]//input");
//        focusClickOnElement("//div[contains(@class,'sb-image__checkbox')]//input");
//        focusClickOnElement();
    }

    public void verifyProductImport(String product) {
        String xpath = "//h3[contains(text(),'" + product + "')]";
        Assert.assertTrue(isElementExist(xpath));
    }

    public void searchProduct(String productName) {
        String xpath = "//input[@placeholder='Search products']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, productName);
        waitForEverythingComplete();
    }

    public Integer getQuantityProduct(String product) {
        String xpath = "//span[contains(text(),'" + product + "')]";
        return countElementByXpath(xpath);
    }

    public void searchProductNameInPrivateRequestList(String productName) {
        String xpath = "//input[@placeholder='Search by product name, request link']";
        waitClearAndTypeThenEnter(xpath, productName);
    }

    public String getQuotationNameOnPrivateProduct() {
        String xpath = "//div[@class='sb-description']//div[contains(@class,'sb-title')]//descendant::div[2]";
        return getText(xpath);
    }

    public void waitCrawlSuccess(String productName, String status) {
        waitABit(10000);
        String xpath = "//div[contains(@class,'sb-tab-panel') and not (contains(@style,'display: none;'))]//div[text()[normalize-space()='" + productName + "']]//ancestor::div[contains(@class,'catalog-products-review__product-review')]//span[text()[normalize-space()='" + status + "']]";
        int i = 0;
        do {
            waitABit(10000);
            refreshPage();
            i++;
            if (i == 5)
                break;
        }
        while (!isElementExist(xpath));
        System.out.println("Crawl sucess");
    }

    public String getProductCostVariant() {
        String element = "//div[text()='Product cost']/parent::div//descendant::div[2]";
        return getText(element);
    }

    public void verifyCountCountrySupport(int count) {
        String element = "//div[contains(@class,'sb-select-menu')]//li";
        int act = countElementByXpath(element);
        assertThat(act).isEqualTo(count);
    }

    public void verifyShowCountry(String shipTo, boolean isCountry) {
        String element = "//div[contains(@class,'sb-select-menu')]//li[text()[normalize-space()='" + shipTo + "']]";
        verifyElementPresent(element, isCountry);
    }

    public void selectCountrySupport(String shipTo) {
        String el = "//div[text()[normalize-space()='Ship to']]/parent::div//button";
        String el1 = "//div[contains(@class,'sb-select-menu')]//li[text()[normalize-space()='" + shipTo + "']]";
        clickOnElement(el);
        clickOnElement(el1);
    }

    public String getFirstItem() {
        String el = "//div[text()[normalize-space()='First item:']]//span";
        return getText(el);
    }

    public String getAdditionalItem() {
        String el = "//div[text()[normalize-space()='Additional item:']]//span";
        return getText(el);
    }
}
