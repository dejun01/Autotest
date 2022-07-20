package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.SocialProofPage;
import common.CommonPageObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class SocialProofSteps {
    SocialProofPage socialProofPage;
    String hiveLink = LoadObject.getProperty("hive");

    public void navigatetoSocialProofListpage() {
        socialProofPage.maximizeWindow();
        socialProofPage.navigateToUrl(hiveLink + "app/socialproof/list");
    }

    public void verifySocialProofInformation() {
        socialProofPage.verifyDisplayID();
        socialProofPage.verifyName();
        socialProofPage.verifyDisplayWaitingtime();
        socialProofPage.verifyDisplayDisplaytime();
        socialProofPage.verifyDisplayClick();
        socialProofPage.verifyDisplayCreatedAt();
        socialProofPage.verifyDisplayUpdatedAt();
        socialProofPage.verifyDisplayEditbutton();
        socialProofPage.verifyDisplayDeletebutton();
        socialProofPage.verifyDisplayCopyScriptbutton();
    }

    public void enterName(String name) {
        socialProofPage.enterName(name);
    }
    public void enterDisplaytime(String displaytime) {
        socialProofPage.enterDisplaytime(displaytime);
    }

    public void enterWaitingtime(String waitingtime) {
        socialProofPage.enterWaitingtime(waitingtime);
    }
    public void enterDelaytime(String delaytime) {
        socialProofPage.enterDelaytime(delaytime);
    }

    public void enterLink(String link) {
        socialProofPage.enterLink(link);
    }

    public void enterTitle(String title) {
        socialProofPage.enterTitle(title);
    }

    public void enterContent(String content) {
        socialProofPage.enterContent(content);
    }

    public void enterTime(String time) {
        socialProofPage.enterTime(time);
    }

    public void uploadImage(String image) {
        socialProofPage.uploadImage(image);
    }

    public void clickCreatebutton() {
        socialProofPage.clickBtn("Create");
    }

    public void clickonEditbutton(String nameSP) {
        socialProofPage.clickEditbutton(nameSP);
    }

    public void clickonUpdatebutton() {
        socialProofPage.clickBtn("Update");
    }

    public void verifyCreateSocialProofSuccess() {
        socialProofPage.verifyCreateSocialProofSuccess();
    }

    public void clickStartUpload() {
        socialProofPage.clickBtn("Start Upload");
    }

    public void clickonAddnewbutton() {
        socialProofPage.clickonAddnew();
    }

    public void verifyNotCreateSocialProof() {
        socialProofPage.verifyNotCreateSocialProof();
    }

    public void clickRemove() {
        socialProofPage.clickBtn("Remove");
    }

    public void verifyMsg(String message) {
        socialProofPage.verifyMsg(message);
    }
    public void verifyErrorMsg(String message) {
        socialProofPage.verifyErrorMsg(message);
    }
    public void clickDetelebutton() {
        socialProofPage.clickDeletebutton();
    }

    public void clickYesbutton() {
        socialProofPage.clickYesdelete();
    }

    public void verifyMsgWhenInputSocialProofExist(String message) {
        socialProofPage.verifyMsgWhenInputSocialProofExist(message);
    }

    public void verifyDisplaySocialProof() {
        socialProofPage.verifyDisplaySocialProof();
    }

    public int getView(String id) {
        return socialProofPage.getView(id);
    }

    public int getClick(String id) {
        return socialProofPage.getClick(id);
    }

    public void verifyViewAndClickAfterSocialProofDisplay(String id) {
        socialProofPage.verifyViewAndClickAfterSocialProofDisplay(id);
    }

    public void clickSocialProof() {
        socialProofPage.clickSocialProof();
    }

    public float getCR(String id) {
        return socialProofPage.getCR(id);
    }
}


