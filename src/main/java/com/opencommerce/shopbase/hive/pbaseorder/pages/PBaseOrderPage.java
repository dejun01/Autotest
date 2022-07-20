package com.opencommerce.shopbase.hive.pbaseorder.pages;

import common.SBasePageObject;
import io.cucumber.java.sl.In;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PBaseOrderPage extends SBasePageObject {
    public PBaseOrderPage(WebDriver driver) {
        super(driver);
    }

    public void Login() {
        clickBtn("Log in");
    }

    public void verifyLoginSS() {
        waitUntilElementVisible("//ul[@class='sidebar-menu']", 20);
    }


    public void verifyApprovedSuccess() {
        String xpath = "//div[contains(@class,'alert-success')]";
        waitElementToBePresent(xpath);
    }

    public void waitForEnableBtnApproved() {
        String xpath = "//label[text()='Approved']";
        waitForElementNotVisible(xpath);
        waitABit(4000);
    }


    public void inputRefundOrder(String refundLineitem) {
        String[] arr = refundLineitem.split(">");
        String lineitemName = arr[0];
        String quantity = arr[1];
        inputQuantityLineitemOrderPOD(lineitemName, quantity);
    }

    public void inputQuantityLineitemOrderPOD(String lineitemName, String quantity) {
        String xpath = "//tr[child::td[text()='" + lineitemName + "']]//div[contains(@class,'select-quantity')]";
        clickOnElement(xpath);
        String xpathQuantity = "//div[@id='select2-drop']//li[child::div[text()='" + quantity + "']]";
        clickOnElement(xpathQuantity);
    }

    public void clickBtnRefundOrderPBase() {
        clickOnElement("//a[text()='Refund']");
    }

    public void checkRefundPBase(String someOne) {
        String xpathHover = "//div[child::label[text()='" + someOne + "']]//div[@class='icheckbox_square-blue']";
        String xpathClick = "//div[child::label[text()='" + someOne + "']]//ins[@class='iCheck-helper']";
        hoverThenClickElement(xpathHover, xpathClick);
    }

    public void checkRefundforSeller() {
        checkRefundPBase("Request to refund for seller");
    }

    public void checkRefundforBuyer() {
        checkRefundPBase("Request to refund for buyer");
    }

    public void clickBtnCancelOrderPBase() {
        clickOnElement("//a[text()='Cancel']");
    }

    public void clickBtnManualFulfillPBase() {
        clickOnElement("//a[text()[normalize-space()='Manual fulfill']]");
    }

    public void inputFulfillOrder(String fulfillLineitem) {
        String[] arr = fulfillLineitem.split(">");
        String lineitemName = arr[0];
        String quantity = arr[1];
        inputFulfillQuantityLineitemOrderPOD(lineitemName, quantity);
    }

    private void inputFulfillQuantityLineitemOrderPOD(String lineitemName, String quantity) {
        String xpath = "//tr[child::td[text()='" + lineitemName + "']]//div[contains(@class,'select2-container')]";
        clickOnElement(xpath);
        String xpathQuantity = "//div[@id='select2-drop']//li[child::div[text()='" + quantity + "']]";
        clickOnElement(xpathQuantity);
    }

    public void inputTrackingNumberPod(String trackingNumber) {
        String xpath = "//input[@name='trackingNumber']";
        clickAndClearThenType(xpath, trackingNumber);
    }

    public void inputTrackingUrlPod(String trackingUrl) {
        String xpath = "//input[@name='trackingUrl']";
        clickAndClearThenType(xpath, trackingUrl);
    }

    public int getIndexColum(String col) {
        String xpath = "//th[text()='Tracking No']/preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public String verifyTKNOrder(int index) {
        String xpath = "//table[@class='table table-bordered  table-striped']//td[" + index + "]//a";
        return getText(xpath);
    }

    public void clickFilter(String name) {
        String xpath = "//a[@class='dropdown-toggle sonata-ba-action']//i";
        String xpath2 = "//i[@class='fa fa-check-square-o']";
        String xpath3 = "//a[normalize-space()='" + name + "']";
        clickOnElement(xpath);
        List<WebElement> checkTick = getDriver().findElements(By.xpath(xpath2));
        for (WebElement checkBox : checkTick) {
            clickOn(checkBox);
        }
        clickOnElement(xpath3);


    }

    public void clickBTApproved() {
        scrollIntoElementView("//button[@class='btn btn-primary' and text() ='Approve']");
        String xpath = "//button[@class='btn btn-primary' and text() ='Approve']";
        clickOnElement(xpath);
    }

    public void clickBTCaculate() {
        String xpath = "//button[@class='btn btn-primary' and contains(text(),'Calculate')]";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }

    public String getTextOrderNameDetail() {
        String xpath = "//h5[contains(text(),'Edit')]";
        return getText(xpath).replace("Edit", "").trim();
    }

    public void verifyDisplayBTManualFulfill() {
        String xpath = "//a[text()[normalize-space()='Manual fulfill']]";
        Assert.assertTrue(isElementExist(xpath));

    }

    public void veryDisableBTApproved() {
        String xpath = "//label[@disabled='true' and text()='Approved']";
        Assert.assertTrue(isElementExist(xpath));
    }

    public void verifyPaymentStatusOrder(String orderName, String paymentStatus) {
        int index = getIndexOfColumn("Payment Status");
        String xpathPaymentStatus = "//a[text()[normalize-space()='" + orderName + "']]/ancestor::tr//td[" + index + "]";
        waitUntilElementVisible(xpathPaymentStatus, 10);
        assertThat(paymentStatus).isEqualTo(getText(xpathPaymentStatus));
    }

    public void verifyFulfillmentStatusOrder(String orderName, String fulfillmentStatus) {
        int index = getIndexOfColumn("Fulfillment Status");
        String xpathFulfillmentStatus = "//a[text()[normalize-space()='" + orderName + "']]/ancestor::tr//td[" + index + "]";
        waitUntilElementVisible(xpathFulfillmentStatus, 10);
        assertThat(fulfillmentStatus).isEqualTo(getText(xpathFulfillmentStatus));
    }

    public void verifyApprovedDateOrder(String orderName, String approvedDate) {
        int index = getIndexOfColumn("Approved At");
        String xpathApprovedDate = "//a[text()[normalize-space()='" + orderName + "']]/ancestor::tr//td[" + index + "]";
        waitUntilElementVisible(xpathApprovedDate, 10);
        assertThat(approvedDate).isEqualTo(getText(xpathApprovedDate));
    }

    public String getTextError() {
        String xpath = "//div[contains(@class,'alert-dismissable')]";
        return getText(xpath);
    }

    public void clickOrderApproved(String orderName) {
        try {
            int index = getIndexOfColumn("Order Name") - 1;
            String xpath = "//a[text()[normalize-space()='" + orderName + "']]/ancestor::tr//td[" + index + "]";
            focusClickOnElement(xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void approvedMultiOrder() {
        String inputApproved = "//label[@class='checkbox']//following-sibling::div[@class='select2-container']//b";
        String approved = "//div[@role='option' and text() = 'Approve Order']//parent::li";
        String submit = "//input[contains(@class,'btn-primary')]";
        scrollToElement(inputApproved);
        focusClickOnElement(inputApproved);
        clickOnElement(approved);
        waitUntilElementInvisible("//div[contains(@class,'select2-drop-auto-width')]", 5);
        clickOnElement(submit);

    }

    public void confirmApproved() {
        String xpath = "//button[text()='Yes, execute']";
        waitUntilElementVisible(xpath, 10);
        focusClickOnElement(xpath);
    }

    public void accPage(String page) {
        String xpath = "//a[contains(.,'" + page + "')]//parent::li";
        clickOnElement(xpath);
    }

    public void clickOrderDetail(String name) {
        String xpath = "//a[normalize-space()='" + name + "']";
        clickOnElement(xpath);

    }

    public void enterOrderDomain(String enter) {
        String xpath = "//input[@id='filter_shop__domain_value']";
        waitUntilElementVisible(xpath, 20);
        waitClearAndTypeThenEnter(xpath, enter);
        waitForEverythingComplete();
    }

    public void enterOrderName(String enter) {
        String xpath = "//input[@id='filter_name_value']";
        waitUntilElementVisible(xpath, 20);
        waitClearAndTypeThenEnter(xpath, enter);
        waitForEverythingComplete();
    }

    public boolean checkLogin() {
        String xpath = "//div[@class='alert alert-danger alert-dismissable']//button[@class='close']";
        if (isElementVisible(xpath, 10)) {
            return true;
        }
        return false;
    }

    public void chooseAction(String type, String action) {
        if (type.equalsIgnoreCase("Available BCoin")) {
            scrollToElement("(//div[@class='row' and descendant::strong[text()='" + type + " : ']])[3]");
            clickOnElement("(//div[@class='row' and descendant::strong[text()='" + type + " : ']])[3]//a[@class='select2-choice']");
        } else {
            scrollToElement("//div[@class='row mt-20' and descendant::strong[text()='" + type + " : ']]");
            clickOnElement("//div[@class='row mt-20' and descendant::strong[text()='" + type + " : ']]//a[@class='select2-choice']");

        }
        clickOnElement("//div[@class='select2-result-label' and normalize-space()='" + action + "']");
    }

    public void inputQuantity(String type, String quantity) {
        if (type.equalsIgnoreCase("Available BCoin")) {
            waitClearAndType("(//div[@class='row' and descendant::strong[text()='" + type + " : ']])[3]//input[@type='number']", quantity);
        } else
            waitClearAndType("//div[@class='row mt-20' and descendant::strong[text()='" + type + " : ']]//input[@type='number']", quantity);
    }

    public void verifyCancleOrderSuccessfully() {
        verifyElementContainsText("//div[@class='read-more-wrap']", "has been canceled.");
    }

    public void chooseQuantity(int quantity) {
        String xpath = "//div[@class='select2-container select-quantity']//a[@class='select2-choice']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
            clickOnElement("//ul//li//div[@class='select2-result-label' and normalize-space()='" + quantity + "']");
        }
    }

    public String getOrderID() {
        String xpath = "//td[@class='cursor-default no-padding-important']//div//a";
        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
        String value = element.get(0).getAttribute("href");
        String[] arr = value.split("/");
        String orderID = arr[arr.length - 1];
        return orderID;
    }

    public void navigateToOrderDetail(String orderID) {
        if (orderID.contains("5983")) {
            String url = "https://hive-pbase.stag.shopbase.net/admin/pbase-order/" + orderID + "/edit";
            navigateToUrl(url);
        } else {
            String url = "https://hive-pbase.shopbase.com/admin/pbase-order/" + orderID + "/edit";
            navigateToUrl(url);
        }
    }

    public Integer getVersionOrder() throws Exception {
        try {
            JSONParser parser = new JSONParser();
            String xpath = "//div[contains(text(), '\"version\"')]";
            String value = getText(xpath);
            JSONArray arr = (JSONArray) parser.parse(value);

            Iterator<JSONObject> iterator = arr.iterator();
            int version=0;
            if (iterator.hasNext()) {
                version = Integer.parseInt((String) iterator.next().get("version"));
            }
            return version;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Can not get version");
        }
    }
}


