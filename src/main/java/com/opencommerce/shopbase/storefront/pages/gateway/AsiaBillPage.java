package com.opencommerce.shopbase.storefront.pages.gateway;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class AsiaBillPage extends SBasePageObject {
    public AsiaBillPage(WebDriver driver) {
        super(driver);
    }

    public String xpathExpirationDate = "//input[@id='validDate']";

    public void inputFirstName(String name) {
        String xPath = "//input[@id='firstName']";
        waitClearAndType(xPath, name);
    }

    public void inputLastName(String name) {
        String xPath = "//input[@id='lastName']";
        waitClearAndType(xPath, name);
    }

    public String getFirstName() {
        String xPath = "//input[@id='firstName']";
        return getValue(xPath);
    }

    public String inputLastName() {
        String xPath = "//input[@id='lastName']";
        return getValue(xPath);
    }

    public void inputCardNumber(String cardNumber) {
        String xPath = "//input[@id='cardNo']";
        clickThenTypeCharByChar(xPath, cardNumber);
    }

    public void inputCVV(String cvv) {
        String xPath = "//input[@id='cvv2']";
        clickThenTypeCharByChar(xPath, cvv);
    }

    public void inputCardIssuingBank(String bank) {
        String xPath = "//input[@id='issuingBank']";
        clickThenTypeCharByChar(xPath, bank);
    }

    public void selectExpiredMonth(String month) {
        String xPath_dropdown = "//select[@id='cardExpireMonth']";
        String xPath = xPath_dropdown + "//option[text()[normalize-space()='" + month + "']]";
        clickOnElement(xPath_dropdown);
        clickOnElement(xPath);
    }

    public void selectExpiredYear(String year) {
        String xPath_dropdown = "//select[@id='cardExpireYear']";
        String xPath = xPath_dropdown + "//option[text()[normalize-space()='" + year + "']]";
        clickOnElement(xPath_dropdown);
        clickOnElement(xPath);
    }

    public String getAddress() {
        String xPath = "//textarea[@id='address']";
        return getValue(xPath);
    }

    public String getCity() {
        String xPath = "//input[@id='city']";
        return getValue(xPath);
    }

    public String getState() {
        String xPath = "//input[@id='state']";
        return getValue(xPath);
    }

    public String getPostcode() {
        String xPath = "//input[@id='city']";
        return getValue(xPath);
    }

    public String getCountry() {
        String xPath = "//input[@id='city']";
        return getSelectedValueDdl(xPath);
    }

    public String getEmail() {
        String xPath = "//input[@id='email']";
        return getValue(xPath);
    }

    public String getPhone() {
        String xPath = "//input[@id='phone']";
        return getValue(xPath);
    }

    public void clickOnPayNowBtn() {
        String xPath = "//input[@id='paybutton']";
        clickOnElement(xPath);
    }

    public void clickOnCancelBtn() {
        String xPath = "//input[@id='cancelbutton']";
        clickOnElement(xPath);
    }
}
