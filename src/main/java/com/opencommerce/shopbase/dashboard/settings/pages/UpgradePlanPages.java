package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class UpgradePlanPages extends SBasePageObject {
    public UpgradePlanPages(WebDriver driver) {
        super(driver);
    }

    public void verifyPackages() {
        verifyTextPresent("Basic", true);
        verifyTextPresent("Standard", true);
        verifyTextPresent("Pro", true);
    }

    public void clickBtnOnlyUseSBFullfillmentService() {
        clickOnElementByJs(xPathBtn("", "Only use PlusHub Service", 1));
    }

    public void clickBtnEnablePaymentGateway() {
        clickOnElementByJs("//span[contains(text(),'Enable')]");
    }
}

