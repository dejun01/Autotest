package com.opencommerce.shopbase.partners.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.SettingPages;
import com.opencommerce.shopbase.partners.pages.InvitationCodePages;
import common.SBasePageObject;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.restassured.path.json.JsonPath;

import java.util.List;

public class InvitaionCodeSteps {
    InvitationCodePages inviCodepage;
//    String apiURL = LoadObject.getProperty("gapi.url");

    public void enterCode(String code) {
        inviCodepage.enterCode(code);

    }

    public void clickBtnSaveOntheAffiliateDB() {
        inviCodepage.clickBtnSaveOntheAffiliateDB();
    }

    public void verifyInvitationCode(String inviCode) {
        inviCodepage.verifyInvitationCode(inviCode);
    }

    public void verifyStatusPromoterFiled() {
        inviCodepage.verifyStatusPromoterFiled();
    }

    public void enterPromoter(String promoter) {
        inviCodepage.waitForEverythingComplete();
        inviCodepage.enterInputFieldWithLabel("Invitation Code", promoter);
    }

    public void clickBtnSave() {
//        inviCodepage.clickBtn("Add");
        inviCodepage.clickBtnSave();
    }

    public void verifyStatusPromoterFiledAfterAddPromoter() {
        inviCodepage.verifyStatusPromoterFiled();
    }

    public void verifyMsg(String message) {
        inviCodepage.verifyMsg(message);
    }

    public void verifyUserOnTheAffiliateDashboard(String mail) {
        inviCodepage.verifyUserOnTheAffiliateDashboard(mail);
    }

    public void StatusPromoterFiledWhenUserSignupDays() {
        inviCodepage.verifyStatusPromoterFiled();
    }

    public void verifyAccountscreen() {
        inviCodepage.verifyAccountScreen();
    }

    public void enterPhone(String phone) {
        inviCodepage.enterInputFieldWithLabel("Phone", phone);
    }

    public void verifyYourProfileScreen() {
        inviCodepage.verifyYourProfileScreen();
    }

    public void navigateToProfileScreen() {
        String url = inviCodepage.getCurrentUrl();
        String urls[] = url.split("admin");
        String domain = urls[0] + "admin/userprofile";
        inviCodepage.navigateToUrl(domain);
    }

    public void enterEmail(String email) {
        inviCodepage.enterInputFieldWithLabel("Email",email);
    }

    public void enterPass(String pass) {
        inviCodepage.enterInputFieldWithLabel("Password",pass);
    }
}
