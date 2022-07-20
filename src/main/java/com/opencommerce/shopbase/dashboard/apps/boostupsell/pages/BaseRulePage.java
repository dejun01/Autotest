package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class BaseRulePage extends SBasePageObject {
    public BaseRulePage(WebDriver driver) {
        super(driver);
    }

    public void selectProductMustMatch(String blockName, String productMustMatch){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//span[normalize-space()='"+productMustMatch+"']");
    }

    public void selectDropDown(String blockName, String option){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//option[normalize-space()='"+option+"']");
    }

    public void inputFieldCompare(String blockName, String value, int index){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//div[@class='s-flex']["+index+"]//option[normalize-space()='"+value+"']");
    }

    public void clickAddMore(String blockName){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//span[normalize-space()='Add condition']");
    }

    public void inputConditionCompare(String blockName, String value, int index){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//div[@class='s-flex']["+index+"]//option[normalize-space()='"+value+"']");
    }

    public void inputValueCompare(String blockName, String value, int index){
        clickAndClearThenType("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//div[@class='s-flex']["+index+"]//input", value);
    }

    public void selectType(String blockName, String type){
        clickOnElement("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='"+blockName+"']]]//label[contains(@class,'s-radio')]//span[normalize-space()='"+type+"']");
    }

    public void verifyShowAccessory(boolean isShow){
        verifyElementPresent("//div[@class='upsell-accessory-products']", isShow);
    }

    public void verifyOfferAcessoryMessage(String message){
        verifyElementContainsText("//div[contains(@class,'upsell-accessory-products__heading')]", message);
    }

    public void verifyShowItem(String product, boolean isShow){
        verifyElementPresent("//a[contains(@class,'upsell-accessory') and normalize-space()='"+product+"']", isShow);
    }
}
