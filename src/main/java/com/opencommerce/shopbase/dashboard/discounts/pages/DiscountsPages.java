package com.opencommerce.shopbase.dashboard.discounts.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class DiscountsPages extends SBasePageObject {
    public DiscountsPages(WebDriver driver) {
        super(driver);
    }

    public void clickDelete() {
        clickOnElement("//span[@class='s-dropdown-item' and normalize-space()='Delete discounts']");
    }

    public void selectAllDiscount() {
        clickOnElement("//th[@class='discount-select-header']//span[@class='s-check']");
    }

    public void inputDiscountTitle(String title) {
        waitClearAndType("//div[@class='section']//input",title);
    }

    public void selectDiscountType(String type) {
        selectDdlByXpath("//div[@class='s-select full-width']//select", type);
    }

    public void clickBtnActions() {
        clickOnElement("//button[@class='s-button' and child::span[normalize-space()='Actions']]");
    }

    public int countDiscount() {
        waitForPageLoad();
        return countElementByXpath("//table[@class='table pos-rlt']//td[@class='discount-select']");
    }

    public void selectValueType(String valueType) {
        selectDdlByXpath("//div[@class='s-select']//select",valueType);
    }

    public void inputValue(String discountValue) {
        waitClearAndType("//div[@class='witch-75 s-input']//input",discountValue);
    }

    public void inputMaxValue(String maxValue) {
        waitClearAndType("//div[@class='s-input s-input-group s-input-group--prepend']//input",maxValue);
    }

    public void clickBtnCreateDiscount() {
        clickOnElement("//button[@class='s-button is-primary' and child:: span[contains(text(),'Create')]]");
    }

    public void verifyDiscountCreated() {
       verifyElementPresent("//*[contains(text(),'created successfully')]",true);
    }
}
