package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyToFulfillPage extends SBasePageObject {
    public ReadyToFulfillPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchOrderName(String name) {
        waitForEverythingComplete();
        waitClearAndTypeThenEnter("//div[@class='order-search-form']//input[@placeholder = 'Search by order name, transaction id']", name);
    }


    public void verifyResult(String label, String value) {
        String xpath = "//td[@class='"+label+"']//span";
        int countRes = countElementByXpath(xpath);
        for(int i = 1; i <= countRes; i++) {
            String xpathExp = "//tr["+i+"]" + xpath;
            assertThat(getText(xpathExp)).isEqualTo(value);
        }
    }

    public void verifyShippingMethod(String label, String value) {
        String xpath = "//td[@class='"+label+"']//select";
        String act = getSelectedValue(xpath);
        assertThat(act).isEqualTo(value);
    }

    public void verifyButton(String name) {
        try {
            verifyElementVisible("//button[child::span[contains(text(), '"+name+"')] and @disabled]", true);
        }catch (Throwable t) {
            refreshPage();
            waitABit(4000);
            verifyElementVisible("//button[child::span[contains(text(), '"+name+"')] and @disabled]", true);
        }
    }

    public void choiceOrder() {
        checkCheckbox("//th[@class='checkbox-all']//input", true);
    }

    public float getPriceTotal() {
        return getPrice("//div[child::p[contains(text(), 'Paid for shipping')]]//following-sibling::div/p");
    }

    public float getPriceItem(String label) {
        String xpath = "//td[@class='"+label+"']//span";
        int countRes = countElementByXpath(xpath);
        float expPrice = 0;
        for(int i = 1; i <= countRes; i++) {
            String xpathExp = "//tr["+i+"]" + xpath;
            expPrice += getPrice(xpathExp);
        }
        return expPrice;
    }

    public void verifyMessage() {
        verifyElementVisible("//p[contains(text(), 'We received your fulfillment request')]", true);
    }

    public void verifyStatusOrder(String status) {
        int count = countItem("FULFILLMENT STATUS");
        String xpath = "//tr/td["+count+"]";
        assertThat(getText(xpath)).isEqualTo(status);
    }

    private int countItem(String label) {
        return countElementByXpath("//th[child::span[contains(text(), '"+label+"')]]") + 1;
    }

    public void clickButton(String buttonName) {
        clickOnElement("//button//span[contains(text(), '"+buttonName+"')]");
    }

    public void focusOut(String label) {
        try {
            focusClickOnElement("//th/span[contains(text(), '"+label+"')]");
        }catch (Throwable b){
            waitABit(4000);
            focusClickOnElement("//th/span[contains(text(), '"+label+"')]");
        }
    }

    public void verifyProduct(String product) {
        String xpath = "//span[contains(text(), '"+product+"') and not(contains(@style, 'display: none;'))]";
        hoverOnElement(xpath);
        verifyElementVisible(xpath, true);
    }
}
