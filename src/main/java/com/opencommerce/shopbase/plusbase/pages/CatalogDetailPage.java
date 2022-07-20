package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogDetailPage extends SBasePageObject {
    public CatalogDetailPage(WebDriver driver) {
        super(driver);
    }
//    public void VerifyUIShipping(){
//        verifyTextPresent("Shipping information",true);
//    }

    public void clickProduct(String productName) {
        String xpath = "(//div[normalize-space()='" + productName + "'])[1]/parent::div";
        waitElementToBePresent(xpath);
        clickOnElement(xpath);
    }

    public void chooseShippingTo(String country) {
        String xpath = "//li[normalize-space()='" + country + "']";
        clickOnElement("//div[normalize-space()='Ship to']/following-sibling::*//button");
        waitElementToBePresent(xpath);
        clickOnElement(xpath);
    }

    public void verifyShippingTo(String country) {
        clickOnElement("//div[normalize-space()='Ship to']/following-sibling::*//button");
        verifyTextPresent(country, true);
    }

    public String getShippingFee(String label) {
        String xpath = "//div[@class='zone'][1]//div[contains(text(), '" + label + "')]/span";
        return getText(xpath);
    }

    public void verifyShippingFee(String country, String price, String additional, String rateName) {
        if (isElementExist("//div[normalize-space()='Ship to']/following-sibling::*//button")) {
            chooseShippingTo(country);
            waitForTextToAppear("First item:");
            String firstItem = getShippingFee("First item");
            String additionalItem = getShippingFee("Additional item");
            assertThat(firstItem).isEqualTo(price);
            assertThat(additionalItem).isEqualTo(additional);
            String lineShip = getText("//div[@class='zone'][1]//div[contains(text(), 'First item')]/parent::*/preceding-sibling::div//div");
            assertThat(lineShip).isEqualTo(rateName);
        } else {
            verifyTextPresent("Sorry! Shipping to this destination is unavailable.", true);
        }
    }

    public float getFirstItem() {
        String xpath = "//div[text()[normalize-space()='First item:']]//span";
        return getPrice(xpath);
    }

    public void clickVariantValue(String value) {
        if (value.contains(":")) {
            String[] values = value.split(":");
            String xpath = "//div[text()='" + values[0] + "']/parent::div//span[text()[normalize-space()='" + values[1] + "']]";
            clickOnElement(xpath);
        }
    }

    public float getAdditionalItem() {
        String xpath = "//div[text()[normalize-space()='Additional item:']]//span";
        return getPrice(xpath);
    }
}

