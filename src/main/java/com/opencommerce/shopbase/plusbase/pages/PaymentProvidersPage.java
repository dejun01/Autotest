package com.opencommerce.shopbase.plusbase.pages;

import com.github.javafaker.PhoneNumber;
import common.SBasePageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class PaymentProvidersPage extends SBasePageObject {
    public PaymentProvidersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getInfoAfterEdit(String info) {
        String xpath = "//input[@placeholder='"+info+"']";
        return getAttributeValue(xpath,"value");
    }
    public void editInfo(String notes, String name) {
        String xpath = "//input[@placeholder='"+name+"']";
        waitClearAndType(xpath,notes);

    }
    public void clickBTSave() {
        String xpath = "//button[normalize-space()='Save changes']";
        clickOnElement(xpath);
        waitABit(5000);

    }

    public String getCreditCard() {
        String xpath = "//p[contains(text(),'Accept credit card payments during checkout by choosing a payment provider')]//parent::div//h2[@class='stack-item__title']";
        return getText(xpath);
    }

    public String getPaypal() {
        String xpath = "//div[@class='paypal-text']";
        return getText(xpath);
    }

    public String getIntegratedProvider() {
        String xpath = "//p[contains(text(),'Providers that enable you to accept payment methods integrated by the third-party.')]//parent::div//h3";
        return getText(xpath);
    }

    public List<String> getListInfo() {
        String xpath = "//div[@class='group-title']";
        return getListText(xpath);
    }

    public String getShippingFee(String sBaseRules) {
        String xpath = "//tr[child::td[text()='Shipping']]//span";
        return getText(xpath);
    }

    public String getRiskSMP() {
        String xpath = "//*[contains(@class,'tag') and preceding-sibling::span[contains(text(),'Risk')]]";
        return getText(xpath);
    }
}
