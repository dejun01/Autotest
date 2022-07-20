package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.billing;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

public class PaymentMethodsPage extends BFlowPageObject {
    public PaymentMethodsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyButton() {
        verifyElementPresent("//button[child::span[text()='Add new card']]", true);
    }

    public void verifyNotCard() {
        verifyElementPresent("//div[@class='card-list']", false);
    }

    public void switchToIFrameCardNumber() {
        switchToIFrame("//div[@id='stripe-card-number']//iframe");
    }

    public void switchToIFrameExpDate() {
        switchToIFrame("//div[@id='stripe-card-expiry']//iframe");
    }

    public void switchToIFrameCVV() {
        switchToIFrame("//div[@id='stripe-card-cvc']//iframe");
    }

    public void enterFieldLabel(String lable, String value) {
        clickOnElement("//div[text()='" + lable + "']");
        enterInputFieldWithLabel(lable, value);
    }

    public void selectFieldLabel(String label, String country) {
        clickOnElement("//div[child::div[text()='" + label + "']]");
        clickAndClearThenType("//div[text()='" + label + "']//ancestor::div[contains(@class,'ant-select')]//input", country);
        clickOnElement("//li[text()='" + country + "']");

    }

    public void waitForSaveSucc() {
        waitUntilElementInvisible("//button[text()='Save']", 10);
        waitUntilElementVisible("//div[@class='card-list']", 5);
    }

    public int getCountCard() {
        int countCard = countElementByXpath("//div[@class='card-item clearfix']");
        return countCard;
    }

    public void deletCard(int i) {
        clickOnElement("(//a[text()='Delete'])[" + i + "]");
    }

    public void deleteConfirm() {
        clickOnElement("//div[@class='ant-popover-buttons']//button[child::span[text()='OK']]");
    }

    public void verifyCardDefault(String cardType, boolean cardDefault) {
        verifyElementPresent("//p[text()='" + cardType + "']/ancestor::div[@class='card-item clearfix']//div[text()[normalize-space()='DEFAULT']]", cardDefault);
    }

    public void changeCardDefault(String cardType) {
        clickOnElement("//p[text()='" + cardType + "']/ancestor::div[@class='card-item clearfix']//p[text()[normalize-space()='Set as default']]");
    }

    public boolean BtnAddANewCard() {
        return isElementExist("//button[child::span[text()='Add new card']]");
    }

    public void clickAddNewCard() {
        clickOnElement("//button[child::span[text()='Add new card']]");
    }

    public boolean isCard() {
        return isElementExist("//div[@class='card-list']");
    }
}
