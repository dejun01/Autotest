package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CustomPagesPage extends SBasePageObject {
    public CustomPagesPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageDisplay(String page) {
//        verifyElementPresent("//h1[normalize-space()='" + page + "']", true);
        verifyElementText("//h1",page.toUpperCase());
    }

    public void verifyPageNotFound() {
        verifyElementPresent("//h2[normalize-space()='404 Page Not Found']", true);
    }
}
