package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ImportProductCatalogPage extends SBasePageObject {
    public ImportProductCatalogPage(WebDriver driver) {
        super(driver);
    }

    public void verifyProperty(String label, boolean isBoolean) {
        verifyElementVisible("//div[child::div[normalize-space()='" + label + "']]//input[@disabled]", isBoolean);
    }

    public String getProductPrice(String label, String product) {
        return getValue("//div[contains(@class, 'sb-flex-justify-start') and descendant::h3[normalize-space()='" + product + "']]//div[child::div[normalize-space()='" + label + "']]//input");
    }

    public float getProfitMargin(String label, String product) {
        return getPrice("//div[contains(@class, 'sb-flex-justify-start') and descendant::h3[normalize-space()='" + product + "']]//div[child::div[normalize-space()='" + label + "']]//span");
    }

    public void choiceProductImport(String product) {
        clickOnElement("//div[@class = 'sb-card__body' and descendant::h3[normalize-space()='" + product + "']]//label[@for='checkbox']");
    }

    public void clickLabel(String label) {
        clickOnElement("//div[normalize-space()='" + label + "']");
    }

    public void clickCheckbox(int index) {
        checkCheckbox("//div[contains(@class, 'is-scrolling-none')]//tbody/tr[" + index + "]//label[contains(@class,'sb-checkbox')]", true);
    }

    public void EnterValueVariant(String val, int index) {
        waitClearAndType("//div[contains(@class, 'is-scrolling-none')]//tbody/tr[" + index + "]//td[4]//input[@type='number']", val);
    }

    public void clickButton(String buttonName) {
        String xpath = "//button[child::*[contains(text(), '" + buttonName + "')]]";
        waitElementToBeVisible(xpath, 60);
        clickOnElement(xpath);
    }

    public void actionAllProduct(String actName) {
        clickOnElement("//*[text()[normalize-space()='" + actName + "']]");
    }

    public void checkSelectAll(String label) {
        String xpath = "//*[child::*[text()[normalize-space()='" + label + "']]]";
        checkCheckbox(xpath, true);
    }

    public String getProductName() {
        String xpath = "//div[contains(@class,'sb-input__body')]//input[@type='text']";
        return getAttributeValue(xpath, "placeholder");
    }

    public void verifyCountryShow(String shipTo) {
        String element = "//div[contains(@class,'sb-select-menu')]//li[text()[normalize-space()='" + shipTo + "']]";
        verifyElementPresent(element, true);
    }

    public String getShippingMethodInImportList() {
        String el = "//div[text()='Shipping method']/parent::div//button//span[@class='sb-button--label']";
        return getText(el);
    }

    public String getCostVariantInImportList(String variant) {
        int i = getIndexOfColumn("COST");
        String el = "//span[text()='" + variant + "']/ancestor::tr//td[" + i + "]//span";
        return getText(el);
    }

    public String getFirstItemVariantInImportList(String variant) {
        int i = getIndexOfColumn("SHIPPING");
        String el = "//span[text()='" + variant + "']/ancestor::tr//td[" + i + "]//div[text()='1st']/preceding-sibling::div//input";
        return getValue(el);
    }

    public String getAdditionalItemVariantInImportList(String variant) {
        int i = getIndexOfColumn("SHIPPING");
        String el = "//span[text()='" + variant + "']/ancestor::tr//td[" + i + "]//div[text()='2nd']/preceding-sibling::div//input";
        return getValue(el);
    }

    public String getProfitVariantInImportList(String variant) {
        int i = getIndexOfColumn("PROFIT MARGIN");
        String el = "//span[text()='" + variant + "']/ancestor::tr//td[" + i + "]//span";
        return getText(el);
    }

    public void selectTabPricing() {
        String el = "//div[text()='Pricing']";
        clickOnElement(el);
    }

    public void clickEnableForBuyers(String product) {
        String el = "//input[@placeholder='" + product + "']//ancestor::div[@class='sb-card__body']//span[text()[normalize-space()='Enable for buyers to view & purchase this product after it was added to your store']]//preceding-sibling::span";
        clickOnElement(el);
    }

    public void selectProductToImport(String product) {
        String el = "//input[@placeholder='" + product + "']//ancestor::div[@class='sb-card__body']//div[contains(@class,'sb-image__select')]//label";
        clickOnElement(el);
    }

    public void importProductToStore(String product) {
        String el = "//input[@placeholder='" + product + "']//ancestor::div[@class='sb-card__body']//span[text()='Add to store']/parent::button";
        clickOnElement(el);
    }
}
