package com.opencommerce.shopbase.dashboard.authen.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountSecurityPage extends SBasePageObject {
    public AccountSecurityPage(WebDriver driver) {
        super(driver);
    }

    public void changePhone(String phone) {

//        waitClearAndType("//label[text()='Phone']//parent::div//div[@class='s-input']//input", "0962521855");
        String xpath = "//*[normalize-space()='Phone']/following::input[2]";
        XH(xpath).waitUntilEnabled();
        waitClearAndType(xpath, phone);
    }

    public void clickButtonSave() {
        try {
            String xpath = "//span[text()='Save changes']";
            waitElementToBeVisible(xpath);
            clickOnElement(xpath);
        } catch (Throwable t) {
            String xpath = "//span[text()='Save']";
            waitElementToBeVisible(xpath);
            clickOnElement(xpath);
        }
    }

    public void inputOtp(String otpCode) {
        enterInputFieldWithLabel("Enter OTP number", otpCode);

    }


    public void inputPassword(String password) {
        enterInputFieldWithLabel("Password", password);
    }

    public void clickButtonConfirm() {

        clickBtn("Confirm");
    }

    public void changeEmail(String email) {
//        waitClearAndType("//label[text()='Email']//parent::div//div[@class='s-input']//input", "anhtran+2510@beeketing.net");
        waitClearAndType(xPathInputFieldWithLabel(" ", "Email", 1), email);

    }


    public void changeOwnerPhone() {
        waitClearAndType("//label[text()='Phone']//parent::div//div[@class='s-input']//input", "0963834927");

    }


//    public void verifyMsgOtp(String msgotp) {
//        String xpath= ""
//
//
//
//    }

//    public void verifyMsgPassword(String msgpassword) {
//
//    }

    public void verifyError(String msg) {
        String xpath = "//div[text()=\"" + msg + "\"]";
        verifyElementVisible(xpath, true);
    }

    public void verifyChangePhoneNumberSuccessfully(String currentPhone) {
        String xpath = "//*[normalize-space()='Phone']/following::input[2]";
        waitElementToBeVisible(xpath);
        String actualPhone = getTextValue(xpath);
        assertThat(currentPhone).isEqualTo(actualPhone);
    }
}