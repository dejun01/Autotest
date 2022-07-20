package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class GeneralPages extends SBasePageObject {
    public GeneralPages(WebDriver driver){
        super(driver);
    }

    public void selectDdlWithLabelAtSettingsScreen(String _labelName, String _value) {
        String xPath = "((//*[descendant-or-self::*[text()[normalize-space()=\"" + _labelName + "\"]]]/following-sibling::div//select)[1]|(//label[text()[normalize-space()=\"" + _labelName + "\"]]/following-sibling::select)[1])";
        if (XH(xPath).isEnabled()) {
            waitElementToBePresentThenScrollIntoView(xPath).waitUntilEnabled().waitUntilVisible();
            clickOnElement(xPath + "/option[text()[normalize-space() =\"" + _value + "\"]]");
            if (isClickableBtn("Save")) {
                clickBtn("Save");
                waitForEverythingComplete();
                verifySaveSuccess();
            }
        }
    }

    public void verifySaveSuccess() {
        String xPath = "//div[@class='s-notices is-bottom']//div";
        waitElementToBeVisible(xPath);
    }

    public void verifyStoreName(String storeName) {
        String xPath = "//div[child::label[normalize-space()='Store name']]//following-sibling::div//input";
        waitElementToBeVisible(xPath);
        verifyElementText(xPath,storeName);
    }
}
