package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.AuthenCPPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AuthenCPSteps extends ScenarioSteps {

    AuthenCPPage authenCPPage;

    @Step
    public void enterEmail(String email) {
        authenCPPage.enterInputFieldWithLabel("Email address", email);
    }

    @Step
    public void enterPass(String pass) {
        authenCPPage.enterInputFieldWithLabel("Password", pass);
    }

    @Step
    public void signIn(String email, String pass) {
        enterEmail(email);
        enterPass(pass);
        authenCPPage.clickBtn("Sign in");
        authenCPPage.waitUntilInvisibleLoading(10);
    }

    @Step
    public void verifyMsg(String msg) {
        authenCPPage.verifyTextPresent(msg, true);
    }

    @Step
    public void verifyLoginSucc() {
        authenCPPage.verifyGotoApp();
    }

    @Step
    public void clickLinkSignUp() {
        authenCPPage.clickLinkTextWithLabel("Sign up");
    }

    @Step
    public void signUp(String email, String pass) {
        String title = authenCPPage.getTitle();
        assertThat(title).isEqualTo("CrossPanda - Sign up");
        enterEmail(email);
        enterPass(pass);
        authenCPPage.clickBtn("Sign up");
    }

    @Step
    public void removeAcc(String email) {
    }
    @Step
    public void openCrossPanda() {
        authenCPPage.open();
        authenCPPage.waitForPageLoad();
        authenCPPage.waitForEverythingComplete();
        authenCPPage.maximizeWindow();
//        authenCPPage.verifyTitlePage();
    }
    @Step
    public void refreshPage() {
        authenCPPage.refreshPage();
    }

    public void gotoPage(String page) {
        authenCPPage.clickLinkText(page);
    }

    public void forgotPass(String email) {
        authenCPPage.waitForGoToPage();
        enterEmail(email);
        authenCPPage.sendPassLink();
    }

    public void verifySendLinkSucc() {
        authenCPPage.verifyTextPresent("Resend instructions", true);
    }

    public void verifySignUpPage() {
        authenCPPage.verifyTextPresent("One more step!", true);
        authenCPPage.verifyTextPresent("Create a free account and we'll send you the quotation", true);
        authenCPPage.verifyTextPresent("Already have an account?", true);
    }

    public void goToSignInPage() {
        authenCPPage.clickLinkTextWithLabel("Sign in");
        authenCPPage.waitForPageLoad();
    }

    public void verifyDashboardCrosspanda() {
        authenCPPage.verifyDashboardCrosspanda();
    }

    public boolean isExistPageLogin() {
        return authenCPPage.isExistPageLogin();
    }

    public void closeChatBox() {
        if(authenCPPage.isOpenChatBox()){
            authenCPPage.closeChatBox();
        }
    }

    public void verifyTiltePageForgot() {
        String tilte = authenCPPage.getTitle();
        assertThat(tilte).isEqualTo("CrossPanda - Forgot your password");
    }
}
