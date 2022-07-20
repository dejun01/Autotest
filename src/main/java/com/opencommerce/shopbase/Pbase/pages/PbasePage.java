package com.opencommerce.shopbase.Pbase.pages;

import common.CommonPageObject;
import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PbasePage extends SBasePageObject {
    public PbasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyStatus(String orderNumber, String status) {
        int index = getIndexOfColumn("PAYMENT STATUS");
        String xpathFulfillmentStatus = "//a[text()[normalize-space()='" + orderNumber + "']]/ancestor::tr//td[" + index + "]";
        assertThat(status).isEqualTo(getText(xpathFulfillmentStatus));

    }

    public void clickPostPurchase() {
        String xPath = "//div[@class='upsell-only-blocks__detail']//following::div[contains(@class,'upsell-only-blocks__action')]/a";
        waitUntilElementVisible(xPath, 20);
        clickOnElementByJs(xPath);
    }
}