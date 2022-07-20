package com.opencommerce.shopbase.dashboard.apps.boostconvert.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ProductCountdownPage extends SBasePageObject {
    public ProductCountdownPage(WebDriver driver) {
        super(driver);
    }


    public void clickResetToDefault() {
        clickLinkTextWithLabel("//div[contains(@class,'product-countdown-setting-panel') and not (contains(@class,'collapsed'))]", "Reset to default", 1);
    }

    public boolean isMessageDefault() {
        return isElementExist("//div[text()[normalize-space()='Only {{stock_number}} items left in stock!']]");

    }

    public void clickBlock(String _blockName) {
        scrollIntoElementView("//div[@class='settings']//label[text()[normalize-space()='" + _blockName + "']]");
        clickOnElement("//div[@class='settings']//label[text()[normalize-space()='" + _blockName + "']]");
    }

    public void selectTrigger(String optionName) {
        clickOnElement("//label[text()[normalize-space()='" + optionName + "']]//preceding-sibling::div//span");
    }


    public void enterMessage(String sMassage) {
        clickAndClearThenType("//div[@class='bke_content m-t']//div[@class='content']", sMassage);
    }

    public void enterProcessColor(String processColor) {
        if (!processColor.isEmpty())
            enterInputFieldWithLabel("Process color", processColor);
    }

    public void openBlock(String blockName) {
        clickOnElement("//div[contains(@class,'product-countdown-setting-panel')]//label[contains(text(),'" + blockName + "')]");
    }

    public void choseWidgetEligment(String sWidgetEligment) {
        selectRadioButtonWithLabel(sWidgetEligment, true);
    }

    public void selectSpecificOption(String value) {
//        selectDdl("//div[contains(@class,'choose-specific-option')]", value, 1);
    }


    public void selectItemsLeftInStock(String option) {
        selectRadioButtonWithLabel(option, true);
    }

    String xpathFormRandum = "//div[contains(@class,'card--content')]//form";

    public void enterNumberRandomWith(String label, String value) {
        String xpath = xpathFormRandum + "//div[@class='s-form-item'][descendant::*[normalize-space()='" + label + "']]//input";
        waitTypeAndEnter(xpath, value);

    }

    public void inputRangeOfItems(String number) {
        String t[] = number.split(",");
        enterNumberRandomWith("Random from", t[0]);
        enterNumberRandomWith("to", t[1]);
    }

    public void clickBtnChange() {
        clickBtn(xpathFormRandum, "Change", 1);
    }
}

