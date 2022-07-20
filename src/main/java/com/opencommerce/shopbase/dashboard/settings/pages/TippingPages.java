package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.*;
import java.io.*;
import java.util.*;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TippingPages extends SBasePageObject {
    public TippingPages(WebDriver driver) {
        super(driver);
    }

    public void verifyDisplayOfTextUnderTipping(String expectedText) {
        String xPath = "//*[text()[normalize-space()='Tipping']]/following-sibling::p";
        verifyElementText(xPath, expectedText);
    }

    public void clickToShowTippingOptions() {

    }

    public void saveChanges() {
        String xPath = "//*[text()[normalize-space()='Save changes']]";
        if (isElementVisible(xPath,3))
            clickBtn("Save changes");
            waitForEverythingComplete();
    }

    public void verifyTippingOptionDisplayed(boolean isDisplayed) {
        String xPath = "//*[text()[normalize-space()='Tipping options']]";
        if (isDisplayed) {
            assertThat(isElementVisible(xPath,3)).isTrue();
            System.out.println("Tipping option is shown");
        }
        else {
            assertThat(isElementVisible(xPath, 3)).isFalse();
        }
    }

    public void verifyMessageAfterEnterInput(int index, String expectedMessage) {
        String xPath = "//*[text()[normalize-space()='Option " + index + "']]/parent::div/following-sibling::*//*[@class='s-form-item__error']";
        if (expectedMessage.isEmpty()) {
            assertThat(isElementExist(xPath, 1)).isFalse();
        } else {
            String actualMessage = getTextValue(xPath);
            assertThat(actualMessage).isEqualTo(expectedMessage);
        }
    }

    public void verifyTippingOptionAfterSaved(int index, String expectedValue) {
        String xPath = xPathInputFieldWithLabel("","Option " + index, 1);
        String actualValue = getTextValue(xPath);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    public void verifyTippingBlockSelected(String labelName, boolean isSelectedBefore) {
        String xPathCheckbox = "//*[text()[normalize-space()=\"" + labelName + "\"]]//preceding-sibling::input";
        if (isSelectedBefore) {
            assertThat(XH(xPathCheckbox).isSelected()).isTrue();
        } else {
            assertThat(XH(xPathCheckbox).isSelected()).isFalse();
        }
    }

    public void verifyDisplayOfTipLine(boolean isTipLineDispayed) {
        String xPathTipLine = "//table[@class='total-line-table']//tbody//*[text()[normalize-space()='Tip']]";
        if (isTipLineDispayed) {
            assertThat(isElementVisible(xPathTipLine, 10)).isTrue();
        } else {
            assertThat(isElementVisible(xPathTipLine, 10)).isFalse();
        }
    }

    public double getTippingAmountOnCartSummary() {
        String xPathTipAmount = "//*[text()[normalize-space()='Tip']]//following-sibling::td";
        return Double.parseDouble(price(getText(xPathTipAmount)));
    }

    public void verifyLabelName(String expectedLabelName) {
        String xPathLabelName = "//*[@id='accept-marketing']//*[@class='s-control-label']";
        String actualLabelName = getTextValue(xPathLabelName);
        assertThat(actualLabelName).isEqualTo(expectedLabelName);
    }

    public void clickOnTippingBlock() {
        String xPathTippingBlock = "//*[@id='accept-marketing']";
        clickOnElement(xPathTippingBlock);
        waitForEverythingComplete(5);
    }

    public List<String> getTippingOptionSF() {
        String xPathTippingOption = "//*[contains(@class,'tip-line')]";
        return getListText(xPathTippingOption);
    }

    public int numberOfTippingOptionsOnSF() {
        String xPathTippingOption = "//*[contains(@class,'tip-line')]";
        if (isElementVisible(xPathTippingOption, 5)) {
            return getListText(xPathTippingOption).size();
        } else {
            return 0;
        }
    }

    public void verifyNumberOfTippingOptionsOnSF(int expectedNumberOfTippingOptionsOnSF) {
        int actualTippingOptionsOnSF = numberOfTippingOptionsOnSF();
        assertThat(actualTippingOptionsOnSF).isEqualTo(expectedNumberOfTippingOptionsOnSF);
    }

    public void verifyButtonAddCustomTipOnSF(String nameOfButton, String statusOfButton) {
        String xPathButton = "//*[text()[normalize-space()='Custom tip']]/following::button[1]";
        assertThat(getTextValue(xPathButton)).isEqualTo(nameOfButton);
        switch (statusOfButton) {
            case "Enabled":
                assertThat(XH(xPathButton).isDisabled()).isFalse();
                break;
            case "Disabled":
                assertThat(XH(xPathButton).isDisabled()).isTrue();
                break;
        }
    }

    public double getSubTotalPrice() {
        String xPathSubTotal = "//td[text()[normalize-space()='Subtotal']]//following-sibling::td";
        String xPathDiscount = "//*[text()[normalize-space()='Discount']]/ancestor::td//following-sibling::td";
        double discount = 0.00;
        double subTotal = Double.parseDouble(price(getText(xPathSubTotal)));
        if (isElementVisible(xPathDiscount, 5)) {
            discount = Double.parseDouble(price(getText(xPathDiscount)));           
        }
        System.out.println("Subtotal include discount: " + (subTotal - discount));
        return Math.round((subTotal - discount)*100.0)/100.0;
    }

    public double calculateTippingValue(double tippingOption) {
        return Math.round((getSubTotalPrice()*(tippingOption/100))*100.0)/100.0;
    }

    public void verifyTippingValueOnSF(int index, double tippingOption) {
        String xPathTippingActual = "//*[contains(@class,'tip-line')][" + index + "]//*[@class='tip-amount']";
        double expectedTippingValue = calculateTippingValue(tippingOption);
        double actualTippingValue = Double.parseDouble(price(getText(xPathTippingActual)));
        System.out.println("Actual Tipping Value for Option " + index + " " + actualTippingValue);
        System.out.println("Expect Tipping Value for Option " + index + " " + expectedTippingValue);
        assertThat(actualTippingValue).isEqualTo(expectedTippingValue);
    }

    public void clickToAddTippingOption(int index) {
        String xPathTippingOption = "//*[contains(@class,'tip-line')][" + index + "]";
        clickOnElement(xPathTippingOption);
    }

    public double getTippingAmount(int index) {
        String xPathTippingActual = "//*[contains(@class,'tip-line')][" + index + "]//*[@class='tip-amount']";
        return Double.parseDouble(price(getText(xPathTippingActual)));
    }

    public void verifyTippingAmountOnCartSummary(int index) {
        assertThat(getTippingAmountOnCartSummary()).isEqualTo(getTippingAmount(index));
    }

    public void waitUntilApplyDiscountSucessfully() {
        waitForElementNotPresent("//button[normalize-space()='Apply' and contains(@class,'is-loading')]");
    }
}
