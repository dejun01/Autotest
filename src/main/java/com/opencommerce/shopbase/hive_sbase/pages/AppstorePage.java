package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class AppstorePage extends SBasePageObject {
    public AppstorePage(WebDriver driver) {
        super(driver);
    }


    public void enterCtaAfterInstallApp(String value) {
        waitClearAndType("//input[contains(@id,'ctaAfterInstall')]", value);
    }

    public void enterCtaBeforeInstallApp(String value) {
        waitClearAndType("//input[contains(@id,'ctaBeforeInstall')]", value);
    }

    public void enterContent(String content) {
        waitClearAndType("//textarea[contains(@id,'description')]", content);
    }
}
