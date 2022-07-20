package com.opencommerce.shopbase.Pbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CancelOrderPage extends SBasePageObject {
    public CancelOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    String xpath = "//div[contains(text(), '%s')]";
    public void verifyReasonCancel(String text) {
        String xpathReason = String.format(xpath , text);
        try {
            verifyElementVisible(xpathReason, true);
        }catch (Throwable t) {
            waitForEverythingComplete(10);
            refreshPage();
            verifyElementVisible(xpathReason, true);
        }
    }

    public void verifyRefuned(float value) {
        String xpathRefund = String.format(xpath , value);
        verifyElementVisible(xpathRefund, true);
    }

    public float getValueForLabel(String label) {
        String xpath = "//div[contains(@class, 'justify-content-space-between')][child::span[contains(text(),'"+label+"')]]/span[last()]";
        return getPrice(xpath);
    }
}
