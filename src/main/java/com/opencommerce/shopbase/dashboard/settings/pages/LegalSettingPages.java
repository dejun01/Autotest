package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;


public class LegalSettingPages extends SBasePageObject {

    public LegalSettingPages(WebDriver driver) {
        super(driver);
    }

    public void inputIntoPagePolicy(String page, String value) {
        String xPath = "(//label[normalize-space()='" + page + "']/parent::div)/descendant::textarea";
        waitTypeAndEnterThenUpdateValue(xPath, value);
    }

    public void verifyContentPage(String page, String content) {
        String xPathTitlePage = "//h3[normalize-space()='" + page + "']";
        verifyElementPresent(xPathTitlePage, true);
        String xPathContentpage = "//div[contains(@class,'page-content')]/pre[contains(normalize-space(),'" + content + "')]";
        verifyElementText(xPathContentpage, content);
    }

    public void clickButtonCreateFromTemplate(String page) {
        String xPath = "(//label[normalize-space()='" + page + "']/parent::div)/descendant::button";
        clickOnElement(xPath);
    }

    public void updateData(String data, String label) {
        String xPath = "//div[child::label[normalize-space()='" + label + "']]//following-sibling::div//input";
        waitElementToBeVisible(xPath);
        waitTypeAndEnterThenUpdateValue(xPath, data);
    }

    public void clickCountry(String label) {
        String xPath = "//div[child::label[normalize-space()='" + label + "']]//following-sibling::div//input";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void selectCountry(String data) {
        String xPathValue = "//div[normalize-space()='" + data + "']";
        waitElementToBeVisible(xPathValue);
        clickOnElement(xPathValue);
    }

    public void verifyContentNotEmpty(String page, String content) {
        verifyElementVisible("//h3[normalize-space()='" + page + "']", true);
        verifyElementPresent("//div[contains(@class,'page-content')]/pre[contains(normalize-space(),'" + content + "')]", true);

    }
}
