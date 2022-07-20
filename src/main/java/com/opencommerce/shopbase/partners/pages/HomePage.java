package com.opencommerce.shopbase.partners.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class HomePage extends SBasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePartner() {
        verifyElementPresent("//div[normalize-space()='Welcome to ShopBase Partner program']",true);
    }
}
