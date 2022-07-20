package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import common.utilities.DateTimeUtil;
import org.openqa.selenium.WebDriver;


public class FreeSubscriptionPage extends SBasePageObject {

    public FreeSubscriptionPage(WebDriver driver){super(driver);}

    public void verifyButtonBilling(){
        waitForEverythingComplete();
        verifyElementVisible("//span[contains(text(),'View Billing')]",true);
    }

    public void verifyCongratulations() {
        verifyElementText("//div[@class='sub-banner-right']//h4","Congratulations! You've completed 10 orders on your free trial. Claim a month's free subscription now");
    }

    public void verifyTitle(){
        verifyElementText("//h4[@class='sub-banner-right-title']","Free subscription is on the way!");
    }

    public void verifyGetTips(){
        verifyElementText("//div[@class='sub-banner-right-tips']","Get tips on how to kick start and win 10 orders. Learn more");
    }
    public String getDateTrialOnBanner() {
        return getText("//*[contains(@class, 'sub-banner-right-enddate')]//b");

    }

    public int getNumberOrder(){
        return countElementByXpath("//div[@class='progress-sub' and child::div[contains(@class, 'active')]]");
    }


    public void searchOrder(String orderName) {
        waitTypeOnElement("//div[@class='order__filter__desktop d-flex']//input[@placeholder]", orderName);
    }


    public void verifyPaymentStatus(String orderNumber, String status){
        verifyElementVisible("//tr[contains(.,'"+orderNumber+"') and contains(., '"+status+"')]", true);
    }

    public void tickSelectAll(){
        checkCheckbox("//span[@data-label='Select all orders']//label", true);
    }

    public void clickOrder(String orderNumber){
        waitForEverythingComplete();
        clickOnElement("//tr[contains(.,'"+orderNumber+"') and contains(., 'Paid')]");
    }

    public void verifyTextGetTips() {
        verifyElementVisible("//span[contains(text(),'Get tips on how to get kick started and gain 10 or')]", true);
    }

    public void clickOnLinkTextSeeMoreDetail() {
        waitForEverythingComplete();
        clickOnElement("//div[@class='sub-banner-right-notice']//a");
    }

    public void verifyClaimSubs() {
        getAttributeValue("//a[contains(text(),'Claim your free subscription now')]","/admin/settings/account");
    }

    public void verifyAccountActive() {
        verifyElementVisible("//span[contains(text(),'View Billing')]",true);
    }

    public void clickOnOrders() {
        waitForEverythingComplete();
        clickOnElement("//span[contains(text(),'Orders')]");
    }

    public void verifyTextCompletedOrder() {
        isElementExist("//*[contains(@class,'sub-banner-right-notice')]");
    }
}


