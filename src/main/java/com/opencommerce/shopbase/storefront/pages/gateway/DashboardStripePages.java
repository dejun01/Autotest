package com.opencommerce.shopbase.storefront.pages.gateway;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class DashboardStripePages extends SBasePageObject {

    public DashboardStripePages(WebDriver driver) {
        super(driver);
    }

    public void openDashboardStripeLoginPage(String url) {
        openUrl(url);
    }

    public void openDashboardStripeLoginPageInNewTab(String url) {
        openUrlInNewTab(url);
    }

    public void inputEmail(String email) {
        String xPath = "//input[@id='email']";
        if (isElementVisible(xPath, 10)) {
            waitClearAndType(xPath, email);
        }
    }

    public boolean verifyInputtedEmail(){
        String xPath = "//input[@id='email']";
        String inputtedEmail = getTextValue(xPath);
        return inputtedEmail.isEmpty();
    }

    public void inputPassword(String pw) {
        String xPath = "//input[@name='password']";
        if (isElementVisible(xPath, 10)) {
            waitClearAndType(xPath, pw);
        }
    }

    public void clickOnSignInBtn() {
        String xPath = " //button[@type='submit']";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
            waitABit(5000);
        }
    }

    public void enableViewTestData() {
        String xPath = "//input[@type='checkbox']/..";
        String xPath_checked = "//input[@type='checkbox' and @checked]";
        clickOnElement(xPath);
//        if(isElementVisible(xPath,10)){
//            clickOnElement(xPath);
//            //verifyElementPresent(xPath_checked,true);
//        }

    }

    public void searchShopByConnectedId(String connectedId) {
        String xPath = "//input[@aria-placeholder='Search…']";
        String xPath_shop = "//table/tbody/tr";
        if (isElementVisible(xPath, 10)) {
            waitClearAndTypeThenEnter(xPath, connectedId);
            waitABit(5000);
        }
    }

    public void selectShopByConnectedIdAfterSearching() {
        String xPath_shop = "//table/tbody/tr";
        if (isElementVisible(xPath_shop, 10)) {
            clickOnElement(xPath_shop);
        }
        waitABit(5000);
        refreshPage();
    }

    public void clickOnSendFundsBtn() {
        String xPath = "//span[text()[normalize-space()='Send funds']]";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
        }
    }

    public void sendFunds(String amt) {
        clickOnSendFundsBtn();
        String xPath_amt = "//input[@id='amount']";
        String xPath_confirmCheckbox = "//input[@id='confirm' and @name='confirm' and @type='checkbox']";
        String xPath_sendBtn = "//span[text()='Send']";
        if (isElementVisible(xPath_amt, 10)) {
            waitClearAndType(xPath_amt, amt);
        }
        if (isElementVisible(xPath_amt, 10)) {
            clickOnElement(xPath_confirmCheckbox);
        }
        if (isElementVisible(xPath_sendBtn, 10)) {
            clickOnElement(xPath_sendBtn);
        }
    }

    public void openPayoutToBankPopup() {
        //need reload the page, the button id will be menu8-button
        String xPath_navigateBtn = "//button[@id='menu8-button']";
        String XPath_payoutToBank = "//span[contains(text(),'Pay out to bank')]";
        if (isElementVisible(xPath_navigateBtn, 10)) {
            clickOnElement(xPath_navigateBtn);
        }
        if (isElementVisible(XPath_payoutToBank, 10)) {
            clickOnElement(XPath_payoutToBank);
        }
    }

    public void payoutToBank(String amt) {
        String xPath_amt = "//input[@id='amount']";
        String xPath_confirmCheckbox = "//input[@id='confirm' and @name='confirm' and @type='checkbox']/..";
        String xPath_payoutBtn = "//span[text()='Pay out']";
        //waitClearAndType(xPath_amt,amt);
        clickThenTypeCharByChar(xPath_amt, amt);
        waitABit(5000);
        clickOnElement(xPath_confirmCheckbox);
        waitABit(5000);
        clickOnElement(xPath_payoutBtn);
    }

    public void clickOnCancelBtn() {
        String xPath = "//span[text()='Cancel']";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
        }
    }

    public double availableAmt() {
        String xPath_amt = "//input[@id='amount']";
        String amt = getAttributeValue(xPath_amt, "value");
        if(amt.contains(",")) {
            amt = amt.replace(",", "");
        }
        double _amt = Double.parseDouble(amt);
        return _amt;
    }

    public void navigateToTrxDetailPage(String trxID){
        String url = "https://dashboard.stripe.com/test/payments/"+trxID;
        openUrl(url);
    }

    public String getTrxTotalAmount(){
        String xPath = "(//span[contains(text(),'usd')][1]/preceding-sibling::span)[1]";
        return getTextValue(xPath).trim();
    }

    public String getTrxStatus(){
        String xPath = "//div[contains(@class,'db-PaymentBadgeStatus')]";
        return getTextValue(xPath).trim();
    }
}
