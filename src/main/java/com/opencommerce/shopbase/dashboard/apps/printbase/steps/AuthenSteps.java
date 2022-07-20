package com.opencommerce.shopbase.dashboard.apps.printbase.steps;

import com.opencommerce.shopbase.dashboard.apps.printbase.pages.AuthenPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.JavascriptException;

public class AuthenSteps extends ScenarioSteps {
    AuthenPage authenPage;

    @Step
    public void navigateToLandingPagePrintBase(String url) {
        authenPage.openBrowser(url);
        closePrintBasePopup();
    }

    @Step
    public void closePrintBasePopup() {
        authenPage.closePrintBasePopup();
    }

    @Step
    public void inputShopSignup(String shopName) {
        waitABit(5000);
        authenPage.enterShopName(shopName);
        authenPage.waitForEverythingComplete();
        authenPage.waitForPageLoad();
    }

    @Step
    public void inputEmail(String email) {
        authenPage.enterInputFieldWithLabel("Email", email);
    }

    @Step
    public void inputPassword(String password) {
        authenPage.enterInputFieldWithLabel("Password", password);
    }

    @Step
    public void clickSignUp() {
        authenPage.clickBtn("Sign up");
    }

    @Step
    public void inputFirstName(String firstName) {
        authenPage.enterInputFieldWithLabel("First name", firstName);
    }

    @Step
    public void inputLastName(String lastName) {
        authenPage.enterInputFieldWithLabel("Last name", lastName);
    }

    @Step
    public void inputStoreCountry(String storeCountry) {
        authenPage.inputStoreCountry(storeCountry);
    }

    @Step
    public void inputPersonalLocation(String persLocation) {
        authenPage.inputPersonalLocation(persLocation);
    }

    @Step
    public void enterPhoneNumber(String countryPhoneCode, String phoneNumber) {
        authenPage.inputCountryPhoneCode(countryPhoneCode);
        authenPage.enterInputFieldWithLabel("phone-number", phoneNumber);
    }

    @Step
    public void clickBtn() {
        authenPage.clickBtn("Next");
    }

    @Step
    public void clickSkip() {
        authenPage.clickBtn("Skip");
    }

    @Step
    public void verifyPrintBaseShopCreated(String shopName) {
        authenPage.waitUntilDashboardPresent(shopName);
        authenPage.verifyPrintBaseDashboardDisplayed();
    }

    @Step
    public void clickCapchar() {
        authenPage.clickCapchar();
    }

    @Step
    public void clickBtnAuthen(String text) {
        waitABit(10000);
        authenPage.clickBtnAuthen(text);
    }

    @Step
    public void openSignInPage(String storeName) {
        try {
            authenPage.goToLoginPage(storeName);
        } catch (JavascriptException ex) {
            authenPage.goToLoginPage(storeName);
        }
        getDriver().manage().deleteAllCookies();
        authenPage.maximizeWindow();
        authenPage.waitForTextToAppear("Sign in", 5000);
    }

    @Step
    public void clickBtnSignIn() {
        authenPage.clickBtn("Sign in");
        authenPage.waitForEverythingComplete();
        authenPage.waitForPageLoad();
    }

    @Step
    public void enterEmail(String _email) {
        authenPage.enterEmail(_email);
    }

    @Step
    public void enterPassword(String _password) {
        authenPage.enterPassword(_password);
    }
}
