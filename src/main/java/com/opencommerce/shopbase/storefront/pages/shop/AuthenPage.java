package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AuthenPage extends SBasePageObject {
    public AuthenPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");

    public void clickIconProfile() {
        switch (theme) {
            case "roller":
                clickOnElement("//a[@class='nav-customer__icon']");
                break;
            default:
                clickOnElement("//*[@data-testid='accountButton']");
        }
    }


    public  void clickSignIn() {
        switch (theme) {
            case "roller":
                clickOnElement("//div[@id='customers-login']//button[normalize-space(text())='Sign in']");
                break;
            default:
                clickBtn("Sign in to your account");
        }
    }

    public void openSignUpPage() {
        switch (theme) {
            case "roller":
                clickOnElement("//div[@id ='customers-login']//a[normalize-space(text())='Sign up']");
                break;
            default:
                clickBtn("Register an account");
        }
        verifyRegisterFormPresent();
    }


    public void verifyMsg(String result, String status) {
        if(status.equalsIgnoreCase("True") || status.equalsIgnoreCase("False") && !result.isEmpty()){
            verifyElementText("//*[contains(@class, 'notification__message')]", result);
        }
        else
            verifyElementPresent("//*[contains(@class, 'notification__message')]", false);
    }

    public void verifyRegisterFormPresent() {
        verifyElementPresent("//input[@name='password-confirm']", true);
    }


    @Step
    public void clickBtnSignUp() {
        switch (theme) {
            case "roller":
                clickOnElement("//div[@id ='customers-register']//button[normalize-space(text())='Sign up']");
                break;
            default:
                clickBtn("Register an account");
        }
    }

    public void verifyLoginSuccess() {
        verifyElementPresent("//span[normalize-space()='You are logged in!']", true);
    }

    public void enterEmai(String email) {
        String xpath = "//input[@name='email']";
        waitForElementFinishRendering(xpath);
        XH(xpath).sendKeys(email);
    }

    public void hoverAccountIcon() {
        hoverOnElement(iD("accountButton"));
    }

    public void verifyLogoutSuccess() {
        verifyElementPresent("//span[normalize-space()='You're logged out']", true);
    }


    public void clickLogout() {
        if(isElementExist("//a[normalize-space()='Logout']")){
            clickOnElement("//a[normalize-space()='Logout']");
        }
    }
}
