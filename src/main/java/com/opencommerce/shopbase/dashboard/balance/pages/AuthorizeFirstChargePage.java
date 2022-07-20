package com.opencommerce.shopbase.dashboard.balance.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AuthorizeFirstChargePage extends SBasePageObject {
    public AuthorizeFirstChargePage(WebDriver driver) {
        super(driver);
    }


    public void clickOnButtonFirstChargeReview() {
        String xpath = "//a[normalize-space()='First charge review']";
//        waitElementToBeVisible(xpath);
        clickOnElementByJs(xpath);
    }

    public void clickOnButtonFilter() {
        String xpath = "//a[@class='dropdown-toggle sonata-ba-action']//i[@class='fa fa-filter']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickOnButtonBalance() {
        String xpath = "//span[normalize-space()='Balance']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void selectStatus() {
        String xpath = "//a[@class='sonata-toggle-filter sonata-ba-action' and normalize-space()='Status']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickSelectBox() {
        String xpath = "//a[@class='select2-choice'][1]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void selectWatingReview() {
        String xpath = "//div[@class='select2-result-label' and normalize-space()='waiting_review']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);

    }

    public void clickButtonFilter() {
        String xpath = "//button[@type='submit' and @class='btn btn-primary' and normalize-space()='Filter']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickOnId32() {
        String xpath = "//td[@class='sonata-ba-list-field sonata-ba-list-field-integer']//a[@class='sonata-link-identifier']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickButtonApprove() {
        String xpath = "//input[@type='button' and @class='btn btn-success']";
        waitElementToBeVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void clickButtonYes() {
        String xpath = "//button[@class='btn btn-success' and @type='button' and @id='btnSubmit' and normalize-space()='Yes']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickOnId30() {
        String xpath = "";
    }


    public void enterBin(String bin) {
        String xpath = "//input[@id='filter_BillingCard__bin_value']";
        waitClearAndTypeThenEnter(xpath, bin);
    }

    public void selectBin() {
        String xpath = "//a[@class='sonata-toggle-filter sonata-ba-action' and normalize-space()='Bin']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickSeclectBoxBin() {
        String xpath = "//input[@id='filter_BillingCard__bin_value']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickOnId() {
        String xpath = "//td[@class='sonata-ba-list-field sonata-ba-list-field-integer']//a[@class='sonata-link-identifier']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clearStautusCard() {
        String xpath = "//input[@type='button']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void rejectCard() {
        String xpath = "//input[@type='button' and @class='btn btn-danger']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public String getStatus() {
        String xpath = "//td[@class='sonata-ba-list-field sonata-ba-list-field-text' and normalize-space()='active']";
        waitElementToBeVisible(xpath);
        String value = getText(xpath);
        return value;
    }

    public int getIndexOfColumnHive(String columnName) {
        String xpath = "//a[contains(text(),'" + columnName + "')]//parent::th//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public void verifyCardStatusIs(String status) {
        int colIndex = getIndexOfColumnHive("Status");
        String xpath = "//tbody//tr/td[" + colIndex + "]";
        assertThat(getText(xpath)).isEqualTo(status);
    }

    public void selectUserEmail() {
        String xpath = "//a[normalize-space()='User Email']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void enterUserName(String userName) {
        String xpath = "//input[@id='filter_User__email_value']";
        waitClearAndType(xpath, userName);
    }
}