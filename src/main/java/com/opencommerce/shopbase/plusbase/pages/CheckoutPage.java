package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends SBasePageObject {
    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }


    public String getPage() {
        String xpath = "//p[contains(text(),'checkout page')]//a";
        return getText(xpath);
    }

    public void settingChekout(String tab, String status) {
        String xpathStatus = "//span[contains(text(),'" + tab + "')]//preceding-sibling::input";
        String xpath = "//span[contains(text(),'" + tab + "')]//preceding-sibling::span[contains(@class,'check')]";
        if (status.equals("uncheck")) {
            verifyCheckedThenClick(xpathStatus, xpath, false);
        } else {
            verifyCheckedThenClick(xpathStatus, xpath, true);
        }
    }

    public void displayFooter() {
        String xpath = "//li[@class='policy-list__item']";
        verifyElementPresent(xpath, true, 10);
    }

    public void notDisplayfooter() {
        String xpath = "//li[@class='policy-list__item']";
        verifyElementPresent(xpath, false, 10);
    }

    public void verifyHeaderHaveLogin() {
        String xpath = "//div[contains(@class,'flex items-center')]//a[contains(@class,'login-icon relative')]";
        verifyElementPresent(xpath, true, 5);
    }

    public void verifyHeaderNotHaveLogin() {
        String xpath = "//div[contains(@class,'items-center')]//a[contains(@class,'login-icon')]";
        verifyElementPresent(xpath, false, 5);
    }
}
