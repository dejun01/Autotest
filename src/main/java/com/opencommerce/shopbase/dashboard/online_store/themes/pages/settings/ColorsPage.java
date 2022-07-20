package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ColorsPage extends SBasePageObject {
    public ColorsPage(WebDriver driver) {
        super(driver);
    }

    public String getColorSettings(String label) {
        return getTextValue("//div[@class='card__section' ]/div/*[descendant::*[text()='" + label + "']]//input");
    }

    public void inputColor(String blockName, String label, String value) {
        String xpath;
        if(blockName.isEmpty()){
            xpath = "//div[@currentlayout and descendant::p[normalize-space()='" + label + "']]";
        }else {
            xpath = "//div[@class='card' and descendant::span[normalize-space()='"+blockName+"']]//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]";
        }
        waitClearAndType(xpath + "//input", value);
        clickOnElement(xpath + "//i");
    }
}
