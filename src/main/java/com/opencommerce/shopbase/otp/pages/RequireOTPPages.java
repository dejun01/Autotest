package com.opencommerce.shopbase.otp.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class RequireOTPPages extends SBasePageObject {
    public RequireOTPPages(WebDriver driver) {
        super(driver);
    }

    public void useAPIKeysToConnect() {
        String xPathStatus = "//h4[normalize-space()='Add new Stripe account']//following-sibling::label//input[@type='checkbox']";
        String xPathCheckBox = "//h4[normalize-space()='Add new Stripe account']//following-sibling::label//span[@class='s-check']";
        verifyCheckedThenClick(xPathStatus, xPathCheckBox, true);
    }

    public void showPopupConfirmOTP() {
        String xPath = "//input[@placeholder = 'Enter OTP number']";
        verifyElementPresent(xPath, true);
    }

    public void verifyShowOptionSentOTP() {
        String xPathSMS = "//span[normalize-space()='Get a code sent to your phone number']";
        String xPathEmail = "//span[normalize-space()='Get a code sent to your email address']";
        verifyElementPresent(xPathSMS, true);
        verifyElementPresent(xPathEmail, true);

    }

    public void closePopupConfirmOTP() {
        String xpathClosePopup = "//div[@class='s-modal-header']//child::button";
        clickOnElement(xpathClosePopup);
    }

    public void choosePaymentProvidersIsPaypal() {
        String xpath = "//button[normalize-space()='Activate with API key']";
        clickOnElementByJs(xpath);
    }

    public void choosePaymentProviderName(String paymentProviderName) {
        String xPathPaymentProviderName = "//tbody//tr//td[normalize-space()='" + paymentProviderName + "']";
        clickOnElement(xPathPaymentProviderName);
    }

    public void verifyNotShowPopupOTP() {
        String xPathSMS = "//span[normalize-space()='Get a code sent to your phone number']";
        waitForElementNotPresent(xPathSMS);
    }

    public void verifyWarningMsg() {
        String xpath = "//p[text()=\"If you don't receive a code sent to your phone number/email. Please contact us via Chat.\"]";
        verifyElementVisible(xpath, true);
    }

    public void ChooseAnotherWayMsg(boolean b) {
        String xpath = "//a[text()[normalize-space()='Choose another way to confirm']]";
        verifyElementVisible(xpath, b);
    }

    public void clickChooseAnotherWay() {
        String xpath = "//a[text()[normalize-space()='Choose another way to confirm']]";
        clickOnElement(xpath);
    }

    public String getEmail() {
        String xpath = "//label[text()='Email']//parent::div//descendant::input";
        String value = getTextValue(xpath);
        return value;
    }

    public String getPhone() {
        String xpath = "//label[text()='Phone']//parent::div//descendant::input[2]";
        String value = getTextValue(xpath);
        return value;
    }

    public void verifyLabelSendOTP(String method) {
        String xpath = "//b[text()='" + method + "' or text()='(+84) " + method + "']";
        verifyElementVisible(xpath, true);
    }

    public void selectOtpMethod(String method) {
        String xpath = "//span[text()[normalize-space()='" + method + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
}
