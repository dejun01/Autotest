package com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.payments;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsPage extends SBasePageObject {

    public PaymentsPage(WebDriver driver) {
        super(driver);
    }


    String xpathPaymenMethod = "//*[text()[normalize-space()='Default payment method']]";
    String xpathActivateBtn = xpathPaymenMethod + "//following::span[text()='Activate']";
    String xpathDeactivateBtn = xpathPaymenMethod + "//following::span[text()='Deactivate']";
    String xpathParentOnPopup = "//div[@class='s-modal is-active']//div[@class='s-modal-footer']";
    String xpathDeactivateBtnOnPopup = "(" + xpathParentOnPopup + "//span[text()[normalize-space()='Deactivate']]" + ")" + "[2]";
    String xpathMessageParent = "//div[@class='s-notices is-bottom']";
    int balance = 1;


    public void verifyNextPayment(float expNextPayment) {
        float actualNextPayment = getNextPayment();

        if (expNextPayment == 0.00) {
            assertThat(actualNextPayment).isEqualTo(expNextPayment);
        } else {
            float balance = ((actualNextPayment % expNextPayment) * 100.0f) / 100.0f;
            if (balance != 0) {
                actualNextPayment += 0.01;
                balance = ((actualNextPayment % expNextPayment) * 100.0f) / 100.0f;
            }
            Assert.assertEquals((int) balance, 0);

        }

    }

    public Float getNextPayment() {
        String xpathNextPayment = "(//div[contains(text(),'Next Payment')]//ancestor::div[@class='row no-gutters']//following-sibling::div[@class='row no-gutters']//p)[2]";
        return getPrice(xpathNextPayment);
    }

    public void verifyEnablePayNowbtn(boolean isEnable) {
        String xpathPayNowbtn = "//div[@class='ui-card__section']//button[contains(@class,'s-button is-primary')]";
        String getAttribute = getAttributeValue(xpathPayNowbtn, "class");
        boolean status = true;
        if (getAttribute.contains("is-disabled") == true) {
            status = false;
        }
        Assert.assertEquals(status, isEnable);

    }

    public void verifyOutStandingBalance(Float expOutStandingBalance) {
        waitABit(3000);
        Float actOutStandingBalance = Float.parseFloat(getOutStandingBalance());
        assertThat(actOutStandingBalance).isEqualTo(expOutStandingBalance);
    }

    public String getOutStandingBalance() {
        String xpathStandingBalance = "(//div[contains(text(),'Outstanding balance')]//ancestor::div[@class='row no-gutters']//following-sibling::div[@class='row no-gutters']//p)[1]";
        String actOutStandingBalance = getText(xpathStandingBalance).replace("$","");
        return price(actOutStandingBalance);
    }

    public void createPayment(String shopId, String shop_name, String accessToken) {
        String requestParams = "{\"time_charge_failed\"" + ":" + " 1}";
        String url = "https://" + shop_name + "/admin/phub-payment/tool/auto-charge-payment.json";
        Response response = given().header("Content-Type", "application/javascript").header("X-Shop-ID", shopId).header("X-ShopBase-Access-Token", accessToken).body(requestParams).post(url);
        assertThat(response.getStatusCode()).isEqualTo(200);

    }

    public int countPaymentLog() {
        String xpathPaymentLog = "//table[@id='all-payments']//tr[child::td[@class='text-right']]";
        String xpathCountPaymentLog = "//div[contains(@class,'s-pagination')]//*[@class='s-info']";
        waitForEverythingComplete(10);
        int count;
        int countLogOnPage = countElementByXpath(xpathPaymentLog);
        //verify phan trang, 1 trang co 10 payments log
        Assert.assertEquals(countLogOnPage <= 10, true);
        try {
            count = Integer.parseInt(getTextValue(xpathCountPaymentLog).replace("1-10 of", "").trim());
        } catch (Exception e) {
            count = Integer.parseInt(getTextValue(xpathCountPaymentLog).replace("1-10 of", "").trim());
        }
        return count;

    }

    public void switchToFirstPaymentLogPage() {
        String xpathPreviousBtn = "//div[contains(@class,'s-pagination')]//a[@class='s-pagination-previous']";
        String xpathDisabledPreviousBtn = "//div[contains(@class,'s-pagination')]//a[@class='s-pagination-previous' and @disabled]";
        if (isElementExist(xpathPreviousBtn)) {
            do {
                clickOnElement(xpathPreviousBtn);
                waitForEverythingComplete();
            } while (!isElementExist(xpathDisabledPreviousBtn));
        }
    }

    public String getDetailColumn() {
        String xpathDetailColumn = "(//table[@id='all-payments']//a)[1]";
        String actualDetails = getText(xpathDetailColumn);
        return  actualDetails;
    }

    public void verifyAmountColumn(Float amount, int index) {
        String xpathAmountColumn = "(//table[@id='all-payments']//*[@class='s-paragraph'])[" + index + "]";
        Float actualAmount = getPrice(xpathAmountColumn);
        Assert.assertEquals(actualAmount, amount);
    }

    public void verifyStatusColumn(String status, int index) {
        String xpathStatusColumn = "(//table[@id='all-payments']//*[@class='text-right']//b)[" + index + "]";
        String actualStatus = getText(xpathStatusColumn);
        Assert.assertEquals(actualStatus, status);
    }

    public void openDetailPayment(int index) {
        String xpathDetailColumn = "(//table[@id='all-payments']//a)[" + index + "]";
        String xpathTitlePaymentDetails = "//div[@class='phub-payment-detail']//*[contains(text(),'Payment for')]";
        clickOnElement(xpathDetailColumn);
        waitForEverythingComplete();
        waitForElementToPresent(xpathTitlePaymentDetails);
    }

    public void verifyFreeType(String freeType) {
        String xpathFreeType = "//table[@class='s-table']//td[1]";
        String actualFreeType = getText(xpathFreeType);
        Assert.assertEquals(actualFreeType, freeType);
    }

    public void verifyDetails(String details) {
        String xpathDetails = "//table[@class='s-table']//td[2]";
        String actualDetails = getText(xpathDetails);
        Assert.assertEquals(actualDetails, details);
    }

    public void verifyAmount(Float amount) {
        String xpathAmount = "//table[@class='s-table']//td[3]";
        Float actualAmount = getPrice(xpathAmount);
        Assert.assertEquals(actualAmount, amount);
    }

    public void verifyStatus(String status) {
        String xpathStatus = "//div[@class='phub-payment-detail']//div[contains(@class,'invoice-badge')]";
        String actualStatus = getText(xpathStatus);
        Assert.assertEquals(actualStatus, status);
    }

    public void verifyAmountAtTimeline(Float amountTimeline) {
        String xpathAmount = "//div[@class='phub-payment-detail']//*[contains(@class,'text-center')]";
        Float actualAmount = getPrice(xpathAmount);
        Assert.assertEquals(actualAmount, amountTimeline);
    }

    public void verifyPaymentMethod(String paymentMethod) {
        String xpathPaymentMethod = "//div[@class='phub-payment-detail']//*[text()='Payment method']";
        verifyElementText(xpathPaymentMethod, "Payment method");
        String xpathNamePaymentMethod = "//div[@class='phub-payment-detail']//*[text()='ShopBase Balance']";
        verifyElementText(xpathNamePaymentMethod, paymentMethod);

    }

    public void viewNextPayment() {
        String xpathNextPayment = "//div[text()='Payment history']//following::a[text()='View next payment']";
        clickOnElement(xpathNextPayment);
        waitForEverythingComplete();
    }

    public void clickBreadcrum() {
        String xpathBreadcrum = "//div[@class='phub-payment-detail']//a[contains(text(),'Payments')]";
        clickOnElement(xpathBreadcrum);
        waitForEverythingComplete();
        waitABit(100);
    }

    public void activateShopBaseBalance(String mess) {
        String xpathMess = xpathMessageParent + "//div[text()[normalize-space()='" + mess + "']]";
        if (isElementExist(xpathActivateBtn)) {
            clickOnElement(xpathActivateBtn);
            waitUntilElementVisible(xpathMess, 90);
        }
        verifyElementPresent(xpathDeactivateBtn, true);

    }

    public void deactivateShopBaseBalance(String mess) {
        String xpathMess = xpathMessageParent + "//div[text()[normalize-space()='" + mess + "']]";
        if (isElementExist(xpathDeactivateBtn)) {
            clickOnElement(xpathDeactivateBtn);
            waitForEverythingComplete();
            verifyDeactivatePopup();
            clickOnElement(xpathDeactivateBtnOnPopup);
            waitUntilElementVisible(xpathMess, 90);
        }
        verifyElementPresent(xpathActivateBtn, true);
    }

    private void verifyDeactivatePopup() {
        String xpathTitle = "//div[@class='s-modal-header']//*[text()='Deactivate ShopBase Balance?']";
        String xpathCancelBtn = "(" + xpathParentOnPopup + "//span[text()[normalize-space()='Cancel']]" + ")" + "[2]";
        verifyElementPresent(xpathCancelBtn, true);
        verifyElementPresent(xpathDeactivateBtnOnPopup, true);
        verifyElementPresent(xpathTitle, true);
    }

    public void verifyProcessSuccess() {
        String xpathProcessingPayment = xpathMessageParent + "//div[text()[normalize-space()='Your payment is processing']]";
        String xpathMess = xpathMessageParent + "//div[text()[normalize-space()='Success']]";
        waitUntilElementVisible(xpathMess, 90);
    }

    public void verifyEnablePayNowBtnInDetail(boolean statusNextpayment) {
        String xpathPayNow = "//div[@class='phub-payment-detail']//button[@class='s-button is-primary']//span[text()[normalize-space()='Pay now']]";
        verifyElementPresent(xpathPayNow, statusNextpayment);
    }

    public void clickOpenListOrder() {
        String xpathOpenListOrder = "//span[@class = 's-icon is-big']";
        clickOnElement(xpathOpenListOrder);
        waitForEverythingComplete();
    }

    public List<String> getListOrderAndVerifyOrder(String orderID) {
        List<String> orderList = getListText("//tr[@class='sub-row']//a");
        assertThat(orderList.contains(orderID)).isEqualTo(true);
        return orderList;
    }

    public Float getCurrentAmount() {
        String xpath="//p[contains(.,'ShopBase Balance - Current amount:')]/b";
        return getPrice(xpath);
    }
    public void createPaymentOrderImport(String shopId, String shop_name, String accessToken){
        String requestParams = "{\"time_charge_failed\"" + ":" + " 1}";
        String url = "https://" + shop_name + "/admin/phub-payment/tool/auto-charge-payment.json";
        Response response = given().header("Content-Type", "application/javascript").header("X-Shop-ID", shopId).header("X-ShopBase-Access-Token", accessToken).body(requestParams).body("{\"pay_for_imported_order\": true}").post(url);
        assertThat(response.getStatusCode()).isEqualTo(200);

    }
}
