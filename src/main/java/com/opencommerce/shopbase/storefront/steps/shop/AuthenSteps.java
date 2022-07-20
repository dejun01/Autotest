package com.opencommerce.shopbase.storefront.steps.shop;

import net.thucydides.core.annotations.Step;
import com.opencommerce.shopbase.storefront.pages.shop.AuthenPage;

public class AuthenSteps {
    AuthenPage authenPage;

    @Step
    public void openPopupSignIn() {
        authenPage.clickIconProfile();
        authenPage.waitForEverythingComplete();
        authenPage.waitElementToBePresent("//div[normalize-space()='Sign in']");
    }

    public void openSignUpPage() {
        authenPage.openSignUpPage();
    }

    @Step
    public void enterEmail(String email) {
        authenPage.enterEmai(email);
    }

    @Step
    public void enterSignupFirstName(String firstName) {
        authenPage.enterInputFieldWithLabel("first-name", firstName);
    }

    @Step
    public void enterSignupLastName(String lastName) {
        authenPage.enterInputFieldWithLabel("last-name", lastName);
    }

    @Step
    public void enterPassword(String pass) {
        authenPage.enterInputFieldWithLabel("password", pass);
    }

    @Step
    public void enterSignupRepeatPassword(String repeatPassword) {
        authenPage.enterInputFieldWithLabel("password-confirm", repeatPassword);
    }

    @Step
    public void clickBtnSignUp() {
        authenPage.clickBtnSignUp();
    }

    @Step
    public void verifyMsg(String result, String status) {
        authenPage.verifyMsg(result, status);
    }

    @Step
    public void closeRegisterForm() {
        authenPage.clickOnElement("//i[@slot='close']");
    }

    @Step
    public void clickSignin() {
        authenPage.clickSignIn();
    }

    public void verifyLoginSuccess() {
        authenPage.verifyLoginSuccess();
    }

    @Step
    public void hoverAccountIcon() {
        authenPage.hoverAccountIcon();
    }

    public void clickLogout() {
        authenPage.clickLogout();
    }

    public void verifyLogoutSuccess() {
        authenPage.verifyLogoutSuccess();
    }

    public void refreshPage() {
        authenPage.refreshPage();

    }

}
