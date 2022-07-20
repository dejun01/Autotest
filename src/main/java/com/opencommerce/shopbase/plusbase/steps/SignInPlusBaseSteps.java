package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.dashboard.authen.pages.LoginDashboardPage;
import com.opencommerce.shopbase.plusbase.pages.SignInPlusBasePage;
import common.utilities.LoadObject;
import net.thucydides.core.steps.ScenarioSteps;

public class SignInPlusBaseSteps extends ScenarioSteps {
    SignInPlusBasePage signInPage;
    LoginDashboardPage loginPage;
    String partnerLink = LoadObject.getProperty("partnerLink");


    public void enterEmail(String _email) {
        loginPage.enterEmail(_email);
    }

    public void enterPassword(String _password) {
        loginPage.enterPassword(_password);
    }

    public void clickBtnSignIn() {
        signInPage.clickBtn("Sign in");
        signInPage.waitForEverythingComplete();
        signInPage.waitForPageLoad();
    }

    public void clickSignUp() {
        signInPage.clickLinkTextWithLabel("Sign up.");
    }

    public void enterShopname(String sShopname) {
        loginPage.enterShopname(sShopname);
    }

    public void clickBtnSignUp() {
        signInPage.clickBtn("Sign up");

//        signInPage.clickLinkTextWithLabel("Sign up.");
    }

    public void verifyErrorMsg(String msg) {
        loginPage.verifyErrorMsg(msg);
    }

    public void refreshPage() {
        signInPage.refreshPage();
        signInPage.waitForEverythingComplete();
    }

    public void closeBrowser() {
        signInPage.closeBrowser();
    }

    public void inputShopSignup(String shopName) {
        waitABit(5000);
        signInPage.enterShopName(shopName);
        signInPage.waitForEverythingComplete();
        signInPage.waitForPageLoad();
    }

    public void clickCapchar() {
        signInPage.clickCapchar();
    }

    public void openShop(String shop) {
        loginPage.openShop(shop);
    }

    public void clickBtnGetStarted() {
        signInPage.clickBtnGetStarted();
    }

    public String getHeader() {
       return signInPage.getHeader();

    }
}


