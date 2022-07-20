package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CustomerAccountsPage extends SBasePageObject {
    public CustomerAccountsPage(WebDriver driver) {
        super(driver);
    }


    public boolean headerHaveLoginField() {
        return isElementExist("//div[contains(@class,'main-nav-wrapper')]//a[contains(@class,'login-icon')]");
    }

    public void verifyHeaderHaveLoginField(boolean isEnable) {
        verifyElementVisible("//div[contains(@class,'main-nav-wrapper')]//a[contains(@class,'login-icon')]", isEnable);
    }

    public void verifyHaveLoginField() {
        verifyElementPresent("//p[@class='have_an_account_login_link layout-flex__item']", true);
    }

    public void verifyHeaderHaventLoginField() {
        verifyElementPresent("//div[@data-testid='accountButton']", false);
    }


    public void verifyHaventLoginField() {
        verifyElementPresent("//p[@class='have_an_account_login_link layout-flex__item']", false);
    }

    public boolean isSaveSuccessfully() {
        return isElementExist("//div[@class='s-notices is-bottom']/div[normalize-space()='All changes were successfully saved']");
    }

    public void verifySuccessMgs() {
        waitForTextToAppear("All changes were successfully saved");
    }
}
