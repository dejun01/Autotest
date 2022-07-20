package com.opencommerce.shopbase.dashboard.orders.pages;

import common.SBasePageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class AbandonedCheckoutsPage extends SBasePageObject {
    private String checkoutLink;
    private String customerName = "//div[@class='card__section']//a[contains(@href,'admin/customers')]";

    public AbandonedCheckoutsPage(WebDriver driver) {
        super(driver);
    }

    public String getCustomerName() {
        return getText(customerName);
    }

    public String getProductNameOnSF() {
        String productNameOnSF = getText("//tr[@class='checkout-product']//span[@class='checkout-product__name']");
        return productNameOnSF;
    }

    public void verifyProductNameOnDB(String productNameOnSF) {
        String productNameOnDB = getText("//div[@class='unfulfilled-card__item-details']//*[@class='unfulfilled-card__name-product']");
        assertThat(productNameOnDB).isEqualTo(productNameOnSF);
    }

    public void verifyShippingAddress(String expectedShippingAddress) {
        List<String> addressOnDB = getListText("//*[normalize-space()='Shipping address']/ancestor::div[@class='card__section']//p");
        String _addressOnDB = addressOnDB.toString().replaceAll("\\W", " ").replaceAll("\\s+", " ").trim();
        assertThat(_addressOnDB).isEqualTo(expectedShippingAddress);
    }

    public void verifyShippingAddressMatchedWith(String expectedAddress) {
        List<String> addressOnDB = getListText("//*[normalize-space()='Shipping address']/ancestor::div[@class='card__section']//p");
        String _addressOnDB = addressOnDB.toString().replaceAll("\\W", " ").replaceAll("\\s+", " ").trim();
        assertThat(_addressOnDB).contains(expectedAddress);
    }

    public String getContactOnSF() {
        String contactOnSF = getText("//div[normalize-space()='Contact']/following-sibling::*");
        return contactOnSF;
    }

    public String getContactOnDB() {
        String contactOnDB = getText("//*[normalize-space()='Customer']/following::div[contains(text(),'@')]");
        return contactOnDB;
    }

    public String getInputField(String fieldName) {
        String value = getAttributeValue("//input[@placeholder='" + fieldName + "']", "value");
        return value;
    }


    public void navigateToTheNewestAbandonedCheckoutDetails() {
        String xPathCheckoutID = "(//tr//a[@class='order-link'])[1]";
//        String xpath = "//span[@class='order-customer-name money--gray' and contains(text(),'" + customerName + "')]";
        String xPathCart = "//span[normalize-space()='Checkout details']";
        clickOnElement(xPathCheckoutID);
        waitElementToBeVisible(xPathCart);
    }

    public long getTimeOfAbandonedCheckout() {
        String xPathEmail = "//div[@class='card__section']//div[contains(text(),'@mailtothis.com') or contains(text(),'@mailinator.girl-viet.com')]";
        long time = Long.parseLong(getText(xPathEmail).split("@")[0]);
        return time;
    }


    public String getRecoverCartLink(boolean recoverKey) {
        String xpath = "//span[contains(text(),'checkouts') and contains(text(),'recover?key')]";
        if (recoverKey) {
            checkoutLink = getTextValue(xpath);
        } else {
            checkoutLink = getTextValue(xpath).split("/recover")[0];
        }
        return checkoutLink;

    }

    public void openRecoverCartLink(boolean recoverKey) {
        checkoutLink = getRecoverCartLink(recoverKey);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open('" + checkoutLink + "');");
        switchToLatestTab();
        waitForEverythingComplete();
    }

    public void navigateToTheScreen(String menu, String tab) {
        String xPathMenu = "//a[@href ='/admin/" + menu + "']/preceding::a[@href ='/admin/" + menu + "']";
        String xpathtab = "//span[normalize-space()='" + tab + "']";
        clickOnElement(xPathMenu);
        waitForEverythingComplete();
        clickOnElement(xpathtab);
    }

    public void clickOnEmailNotSent() {
        clickOnElement("//span[contains(text(),'Email not sent')]");
    }


    public void clickOnSendACartRecoveryEmail(String label) {
        waitElementToBeVisible("//span[contains(text(),'" + label + "')]");
        clickOnElement("//span[contains(text(),'" + label + "')]");
        waitElementToBeVisible("//span[contains(text(),'Send notification')]");
        clickOnElement("//span[contains(text(),'Send notification')]");
    }

    public void openMailBox(String emailAddress, String url) {
        openUrl(url);
        String xpathEmail = "(//div[@class='input-group']//input[contains(@placeholder,'View Any Public Inbox')])[1]";
        XH(xpathEmail).clear();
        waitTypeAndEnter(xpathEmail, emailAddress);
        waitElementToBeVisible("//div[@class='table-responsive']//tr[not (@class='headings')]");
    }

    public void checkMail(String subject) {
        String xpathSubject = "//div[@class='table-responsive']//*[contains(text(),'" + subject + "')]";
        clickOnElement(xpathSubject);
        waitElementToBeVisible("//td[normalize-space()='Received:']");
        switchToIFrame("//iframe[@id='msg_body']");
        verifyElementVisible("", true);
    }

    public void verifyTimeline(String expectedTimline) {
        String xPath = "//div[starts-with(@class, 'timeline')]//div[@class= 'content-body' and starts-with(text(),'" + expectedTimline + "')]";
        verifyElementVisible(xPath, true);
    }

    public String getEmailAddressOfBuyer() {
        return getText("(//div[@class='card__section']//p[a[contains(@href,'admin/customers')]]/following::div)[1]");
    }

    public void navigateToMessageLog() {
        String xPath = "//*[@class='nav nav-stacked']//*[@id='left-dock-item-sms-mms']//a";
//        waitElementToBeVisible("//table[@class='table table-hover sms-logs']");
        clickOnElement("//a[text()='Logs']");
        waitForEverythingComplete();
    }

    public void viewTheNewestMsgWithPhoneNumber(String phoneNumber) {
        String xPath = "(//td[contains(text(),'" + phoneNumber + "')]/parent::tr//td[1])[1]";
        clickOnElement(xPath);
        waitForEverythingComplete();
    }

    public void verifyMsg(String msg) {
        String xPath = "//*[@class='dl-horizontal']//*[contains(text(),\"" + msg + "\")]";
        verifyElementVisible(xPath, true);
    }

    public void cancelEmailWithSubject(String subject) {
        String xPath = "//div[contains(text(),'" + subject + "')]/following::button[normalize-space()='Cancel email']";
        clickOnElement(xPath);
        waitForEverythingComplete();
        refreshPage();
        waitForEverythingComplete(60);
    }

    public void verifyMessage(String msg) {
        String xPath = "//div[@class='s-notices is-bottom']//div[text()='" + msg + "']";
        verifyElementVisible(xPath, true);
    }

    public String getCheckoutID() {
        String checkoutID = "//div[@class='title-bar']//h2";
        waitUntilElementVisible(checkoutID, 8);
        return getText(checkoutID);
    }

    public void switchToIframeMailinatior() {
        switchToIFrame("//iframe[@id='msg_body']");
    }

    public void verifyEmailContent(String content, boolean isPresent) {
        String xpath = "//*[contains(.,\"" + content + "\")]";
        verifyElementPresent(xpath, isPresent);
    }

    public void selectTheAbandonedCheckoutByCustomerName(String customerName) {
        String _customerName = "(//a//span[contains(@class,'order-customer-name')][text()[normalize-space()='" + customerName + "']])[1]";
        clickOnElement(_customerName);
        waitForEverythingComplete();
        waitUntilElementVisible(this.customerName, 8);
    }

    public void verifyProductInfo(String expectedProdName, String expectedVarName, String expectedPrice, String expectedQuantity) {
        String prodVar = "//div[@class='unfulfilled-card__item-details']//a[contains(@href,'admin/products')][text()[normalize-space()='" + expectedProdName + "']]/following::*[text()[normalize-space()='" + expectedVarName + "']]";
        String actualQtyAndPrice = getText(prodVar + "/following::div[@class='unfulfilled-card__price-by-quantity']");
        String expectedQtyAndPrice = expectedPrice + "x" + expectedQuantity;

        verifyElementVisible(prodVar, true);
        assertThat(actualQtyAndPrice).isEqualTo(expectedQtyAndPrice);
    }

    public void verifySubTotal(String expectedSubTotal) {
        String actualSubtotal = getText("//div[contains(@class,'card__section')]//span[normalize-space()='Subtotal']/following-sibling::span");
        assertThat(actualSubtotal).isEqualTo(expectedSubTotal);
    }

    public String getActualRecoveryLink() {
        return getText("//span[@class='recovery-link text-break-all']");
    }

    public void searchAbandonedCheckout(String criteria) {
        String textboxSearch = "//div[@class='order-search-form']//input[@placeholder='Search checkouts']";
        retrySearch(textboxSearch, 3);
        waitClearAndTypeThenEnter(textboxSearch, criteria);
        String result = "//tr[@class='cursor-default']";
        retrySearch(result, 5);
    }

    public void retrySearch(String xpath, int retryTimes) {
        int i = 0;
        while (!isElementExist(xpath, 10) && i < retryTimes) {
            refreshPage();
            waitElementToBePresent(xpath);
            waitUntilElementVisible(xpath,5);
            retryTimes++;
        }
    }

    public String getRecoveryStatus() {
        return getText("(//span[contains(@class,'is-rounded is-success')])[last()]");
    }

    public void clickTheBreadCrumb(String val) {
        clickOnElement("//ol[@class='s-breadcrumb']//*[text()[normalize-space()='" + val + "']]");
        waitUntilInvisibleLoading(8);
    }

    public void verifyTimelineUnique(String expectedTimeline) {
        String xpath = "//div[contains(text(),'" + expectedTimeline + "')]";
        assertThat(countElementByXpath(xpath)).isEqualTo(1);
    }

    public void selectBlock(String blockName){
        String xPathBlockName = "//p[normalize-space()='"+ blockName +"']";
        clickOnElement(xPathBlockName);
    }
}
