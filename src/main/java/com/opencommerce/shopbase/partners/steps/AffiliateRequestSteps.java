package com.opencommerce.shopbase.partners.steps;

import com.opencommerce.shopbase.partners.pages.AffiliateRequestPages;
import common.utilities.LoadObject;

public class AffiliateRequestSteps {
    AffiliateRequestPages affRequestPages;
    String hiveLink = LoadObject.getProperty("hive");

    public void verifyDisplayPrintBaseReferFriends() {
        affRequestPages.verifyDisplayReferFriendsProgram();
    }

    public void enterYourName(String yourname) {
        affRequestPages.enterYourName(yourname);
    }

    public void enterPhone(String phone) {
        affRequestPages.enterPhone(phone);
    }

    public void choosePlatForm(String platform) {
        affRequestPages.choosePlatForm(platform);
    }

    public void chooseTime(String time) {
        affRequestPages.chooseTime(time);
    }

    public void chooseOrderVolume(String ordervolume) {
        affRequestPages.chooseOrderVolume(ordervolume);
    }

    public void clickSubmitbutton() {
        affRequestPages.clickBtn("Submit");
    }

    public void verifyMsg(String msg) {
        affRequestPages.verifyMsg(msg);
    }

    public void verifyMsgSuccess(String msg) {
        affRequestPages.verifyMsgSuccess(msg);
    }

    public void verifyDisplayNoteRequestProcess() {
        affRequestPages.verifyDisplayNoteRequestProcess();
    }

    public void verifyStatusApplybutton() {
        affRequestPages.verifyStatusApplybutton();
    }

    public void clickApplytobePrintBasebutton() {
        affRequestPages.clickBtn("Apply to be PrintBase Ambassador now");
    }

    public void clickApprove(String emailSU) {
        affRequestPages.clickApprove(emailSU);
    }

    public void verifyDisplayPrintBaseAffiliateProgram(boolean isShow) {
        affRequestPages.verifyDisplayPrintBaseAffiliateProgram(isShow);
    }

    public void clickReject(String emailSU) {
        affRequestPages.clickReject(emailSU);
    }
    public void verifyStatusRequestAfterApprove(String emailSU) {
        affRequestPages.verifyStatusRequestAfterApprove(emailSU);
    }

    public void verifyStatusRequestAfterReject(String emailSU) {
        affRequestPages.verifyStatusRequestAfterReject(emailSU);
    }

    public void verifyIntroPrintBaseAmbassador() {
        affRequestPages.verifyIntroPrintBaseAmbassador();
    }

    public void verifyDisplayGetUnlimitedCommissions() {
        affRequestPages.verifyDisplayGetUnlimitedCommissions();
    }

    public void verifyDisplayEarnSimpleCommissions() {
        affRequestPages.verifyDisplayEarnSimpleCommissions();
    }

    public void verifyDisplayBenefit() {
        affRequestPages.verifyDisplayBenefit();

    }
}
