package com.opencommerce.shopbase.partners.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class AffiliateRequestPages extends SBasePageObject {

    public AffiliateRequestPages(WebDriver driver) {
        super(driver);
    }

    public void verifyDisplayBenefit() {
        String xpath = "(//div[@class='item-wrapper border-wrap s-mb24'])[4]";
        verifyElementText(xpath + "//h3", "BENEFITS");
    }

    public void verifyDisplayReferFriendsProgram() {
        verifyElementText("(//div[@class='item-wrapper border-wrap s-mb24'])[5]//h3", "Refer Friends - Get unlimited commission with PrintBase Ambassador Program");
        verifyElementText("(//div[@class='item-wrapper border-wrap s-mb24'])[5]//button", "Apply to be PrintBase Ambassador now");
    }

    public void verifyDisplayGetUnlimitedCommissions() {
        String xpath = "(//div[@class='item-wrapper border-wrap s-mb24'])[2]";
        verifyElementText(xpath + "//h4", "Get unlimited commissions");
        verifyElementText(xpath + "//p", "- Become a PrintBase Ambassador, earn from $0.15 to $1 for each sold item of Referees." +
                "\n- The application period is calculated from 4 months to lifetime depending on Referrer's tier.");
    }

    public void verifyDisplayEarnSimpleCommissions() {
        String xpath = "(//div[@class='item-wrapper border-wrap s-mb24'])[3]";
        verifyElementText(xpath + "//h4", "Earn simple commissions - With endless offers");
        verifyElementText(xpath + "//p", "- Opportunity to earn easy commission by sharing Referral Link to friends or partners for them to use PrintBase." +
                "\n- With comprehensive support from PrintBase, your Referees will have a complete store and order quickly thanks to free up-sell and cross-sell features." +
                "\n- When doing POD business with PrintBase, you and your Referees also receive more attractive incentives from PrintBase Tier Program and PrintBase's partners.");
    }

    public void enterYourName(String yourname) {
        enterInputFieldWithLabel("Your name", yourname);
    }

    public void enterPhone(String phone) {
        enterInputFieldWithLabel("Phone", phone);
    }

    public void choosePlatForm(String platform) {
        selectDdlByXpath("//label[normalize-space()='What platform are you doing POD business on?']//parent::div//following-sibling::div", platform);
    }

    public void chooseTime(String time) {
        selectDdlByXpath("//label[normalize-space()='How long have you been in POD business?']//parent::div//following-sibling::div", time);
    }

    public void chooseOrderVolume(String ordervolume) {
        selectDdlByXpath("//label[normalize-space()='What is your daily order volume?']//parent::div//following-sibling::div", ordervolume);
    }

    public void verifyMsg(String msg) {
        verifyElementText("//div[@class='s-form-item__error']", msg);
    }

    public void verifyMsgSuccess(String msg) {
        verifyElementPresent("//h4[normalize-space()='Thank you for submitting']", true);
        verifyElementText("//h4[normalize-space()='Thank you for submitting']", msg);
        clickOnElement("//button[@class='s-modal-close is-large']");
        refreshPage();
        clickOnElement("//p[normalize-space()='PrintBase Ambassador Program']");
    }

    public void verifyDisplayNoteRequestProcess() {
        verifyElementPresent("//p[normalize-space()='Your application is being process']", true);
    }

    public void verifyStatusApplybutton() {
        verifyElementPresent("//button[@class='s-button s-mr20 is-primary is-disabled']", true);
    }

    public void clickApprove(String emailSU) {
        clickOnElement("//tr//descendant::td[normalize-space()='" + emailSU + "']//following::td[3]//a[normalize-space()='Approve']");
    }

    public void clickReject(String emailSU) {
        clickOnElement("//tr//descendant::td[normalize-space()='" + emailSU + "']//following::td[3]//a[normalize-space()='Reject']");
    }

    public void verifyStatusRequestAfterApprove(String emailSU) {
        String text = "Approved";
        String a = getText("//tr//descendant::td[normalize-space()='" + emailSU + "']//following::td[2]");
        Assertions.assertThat(a).isEqualTo(text);
    }

    public void verifyStatusRequestAfterReject(String emailSU) {
        String text = "Rejected";
        String status = getText("//tr//descendant::td[normalize-space()='" + emailSU + "']//following::td[2]");
        Assertions.assertThat(status).isEqualTo(text);
    }

    public void verifyDisplayPrintBaseAffiliateProgram(boolean isShow) {
        verifyElementPresent("//h3[normalize-space()='Spread the word and earn rewards']", isShow);
    }

    public void verifyIntroPrintBaseAmbassador() {
        String xpath = "(//div[@class='item-wrapper border-wrap s-mb24'])[1]";
        verifyElementText(xpath + "//h2", "PRINTBASE AMBASSADOR");
        verifyElementText(xpath + "//p", "Spread PrintBase to as many friends as possible, elevate Print-On-Demand experience on a modern platform with A to Z solutions. Receive the commission of up to $5,000 per month when the referred person successfully sells.");
        verifyElementText(xpath + "//button[@class='s-button s-mr20 is-primary']", "Apply to be PrintBase Ambassador now");
        verifyElementText(xpath + "//button[@class='s-button is-second']", "Learn more");
    }
}