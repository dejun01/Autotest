package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class FulfillmentServicesProductDetailPage extends SBasePageObject {
    public FulfillmentServicesProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getHeader() {
        String xpath = "//div[@class='head']//h3";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public String getButton(String label) {
        String xpath = "//div[@class='list-container']//p[(text()='PlusHub')]//following-sibling::span//child::span[text()='Mapped']";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public String getValueAvailableStock(String label) {
        int count = countElement(label);
        return getText("//tbody/tr/td[" + count + "]/span");
    }

    private int countElement(String label) {
        return countElementByXpath("//tr/th[descendant::div[contains(text(), '" + label + "')]]//preceding-sibling::th") + 1;
    }

    public String getName() {
        String xpath = "//div[@class='mapped-product-data__title']";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public String getValueStock() {
        String xpath = "//div[contains(@class,'mapped-product-data__available-stock')]";
        return getText(xpath);
    }

    public void clickOnActionBtn() {
        String btnName = "//button//span[text()[normalize-space()='Actions']] or //div[text()[normalize-space()='Actions']]";
        waitUntilElementVisible(btnName, 10);
        clickOnElementByJs(btnName);
        waitForEverythingComplete();
    }

    public void clickOnBtnInMoreAction(String page) {
        String xpath = "//span[text()[normalize-space()='" + page + "']]";
        clickOnElement(xpath);
    }

    public String getPage() {
        String xpath = "//div[contains(@class,'mapping-products-header')]/h2";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }


    public void getRemoveBtn(String text) {
        String xpath = "//*[contains(text(),'" + text + "')]";
        Assert.assertTrue(isElementExist(xpath));
        waitUntilElementVisible(xpath, 10);
    }

    public String getQuotation() {
        String xpath = "//div[@class='quotation-detail']//h3";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public void clickBtnMoreOptions() {
        String xpath = "//div[@class='control-bar text-right']";
        waitUntilElementVisible(xpath, 10);
        scrollToElement(xpath);
        focusClickOnElement(xpath);
    }

    public void clickBack() {
        String xpath = "//div[@class='warehouse mapping']//a";
        waitUntilElementVisible(xpath, 10);
        scrollToElement(xpath);
        focusClickOnElement(xpath);
    }

    public String getRequest() {
        String xpath = "//div[@class='m-b p-r-sm']//h1";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public void userRedirectToPage() {
        String xpath = "//div[@class='mapping-products-header']//h2";
        waitUntilElementVisible(xpath, 10);
    }

    public void searchProduct(String product) {
        waitForEverythingComplete();
        waitClearAndTypeThenEnter("//input[@placeholder='Search product']", product);
        waitForEverythingComplete();
    }
}
