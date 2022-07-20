package com.opencommerce.shopbase.partners.pages;

import common.SBasePageObject;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class InvitationCodePages extends SBasePageObject {
    public InvitationCodePages(WebDriver driver) {
        super(driver);
    }

    public void enterCode(String code) {
        waitClearAndType("//input[@placeholder='type your custom link']", code);
    }

    public void clickBtnSaveOntheAffiliateDB() {
        clickOnElement("//span[normalize-space()='save']");
    }

    public void verifyInvitationCode(String inviCode) {
        String code = getValue("//div[@class='invitation-code s-input is-disabled']//child::input");
        assertThat(code).isEqualTo(inviCode);
    }

    public void verifyStatusPromoterFiled() {
        verifyElementPresent("//div[@class='col-md-6' and child::label[normalize-space()='Invitation Code']]//input[@disabled='disabled']", true);
    }

    public void verifyMsg(String message) {
        verifyElementText("//div[@class='s-notices is-bottom']", message);
    }

    public void verifyUserOnTheAffiliateDashboard(String mail) {
        waitTypeAndEnter("//input[@placeholder='Search by email']", mail);
        waitForElementToPresent("//div[normalize-space()='" + mail + "']");
        verifyElementText("//div[normalize-space()='" + mail + "']", mail);

    }

    public void verifyAccountScreen() {
        verifyElementPresent("//h1[normalize-space()='Account']", true);
    }

    public void clickBtnSave() {
        clickOnElement("//span[normalize-space()='Save changes']");
    }

    public void verifyYourProfileScreen() {
        verifyElementPresent("//div[normalize-space()='Your profile']", true);
    }

    public void clickViewYourProfile() {
        scrollToElement("//button[normalize-space()='Add staff account']");
        clickOnElement("//a[normalize-space()='View your profile']");
    }
}
