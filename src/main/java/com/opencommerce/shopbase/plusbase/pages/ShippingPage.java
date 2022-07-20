package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ShippingPage extends SBasePageObject {
    public ShippingPage(WebDriver webDriver){super(webDriver);}
    public  void clickCheckboxFreeShipRate(String shipping) {
        String xpath  = "//div[contains(text(),'"+shipping+"')]//ancestor::div[contains(@class,'justify-content-space-between')]//following-sibling::label//input";
        String xpathInput  = "//div[contains(text(),'"+shipping+"')]//ancestor::div[contains(@class,'justify-content-space-between')]//following-sibling::label//span[@class='s-check']";
        verifyCheckedThenClick(xpath,xpathInput,true);

    }
    public void editRate(String shipping, String beforeRate) {
        String xpath = "//div[contains(text(),'"+shipping+"')]//ancestor::div[contains(@class,'justify-content-space-between')]//following-sibling::div//input";
        waitClearAndType(xpath,beforeRate);
    }

    public String getValueAfterEdit(String shipping) {
        String xpath = "//div[contains(text(),'"+shipping+"')]//ancestor::div[contains(@class,'justify-content-space-between')]//following-sibling::div//input";
        return getTextValue(xpath);
    }
    public void waitUntilUpdateProfileSuccessfully(String profileName) {
        String msgSuccess = "//div[contains(@class,'toast is-dark is-bottom')]//div[text()[normalize-space()='Successfully created %s'] or text()[normalize-space()='Successfully updated %s']]";
        verifyElementVisible(String.format(msgSuccess, profileName, profileName), true);
    }
    public void searchCountries(String sCountry) {
        waitElementToBeVisible("//div[contains(@class,'form-item')]//input[@placeholder='Search countries']");
        enterInputFieldWithLabel("Search countries", sCountry);
    }
    public void chooseCountries(String sCountry) {
        checkCheckboxWithLabel(sCountry);
    }
    public void clickBtnDone() {
        clickOnElement("//span[text()[normalize-space()='Done']]");
        if (isElementExist("//div[contains(@class, 'item__error')]")) {
            System.out.println("error message " + XH("//div[contains(@class, 'item__error')]").getText() + " ==============================");
        }
    }
    public void clickBtnAddRate() {
        String xpath = "//span[normalize-space()='Add rate']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
    public void enterProfileName(String profileName)
    {
        String xpath = "//div[normalize-space()='Profile Name']//following-sibling::div//input";
        clearAndInputTextByJS(xpath, profileName);
    }

}
