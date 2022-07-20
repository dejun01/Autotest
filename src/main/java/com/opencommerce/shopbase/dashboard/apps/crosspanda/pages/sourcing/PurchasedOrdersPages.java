package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing;

import common.BFlowPageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PurchasedOrdersPages extends BFlowPageObject {
    public PurchasedOrdersPages(WebDriver driver) {
        super(driver);
    }

    public void verifyShowPO(String po) {
        verifyElementPresent("//a[contains(text(),'" + po + "')]//ancestor::tr", true);
        if (!po.isEmpty()) {
            assertThat(countElementByXpath("//a[contains(text(),'" + po + "')]//ancestor::tr")).isEqualTo(1);
        }
    }

    public void enterQuantityOfVariantByIndex(String varQuantity, int row) {
        scrollIntoElementView("//div[@class='ant-table-body']//tr[contains(@class,'ant-table-row')][" + row + "]");
        String xpath = "(//div[@class='table-detail']//div[@class='ant-table-fixed-right']//tbody//tr)[" + row + "]//input[@class='ant-input-number-input']";
        waitClearAndType(xpath, varQuantity);
    }

    public int getRowOfVariant(String variantName) {
        String xpath = "//div[@class='table-detail']//div[@class='ant-table-fixed-left']//tbody//tr[descendant::*[text()='" + variantName + "']]";
        int row = 0;
        if (isElementExist(xpath)) {
            row = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return row;
    }

    public void selectPaymentMethod(String paymentMethod) {
        hoverOnElement("//p[text()='" + paymentMethod + "']//ancestor::label[contains(@class,'radio-payment-methods')]//input");
        clickOnElement("//p[text()='" + paymentMethod + "']//ancestor::label[contains(@class,'radio-payment-methods')]//span[@class='ant-radio']");
    }

    public String getInvoiceName() {
        return getText("//div[child::p[text()='INVOICE NUMBER']]//p[2]");
    }

    public String getPO() {
        return getDetailInPaymentHis().replace("Payment for ", "");
    }

    public String getCreateDate() {
        return getText("//li[text()='Invoice created']//ancestor::div[contains(@class,'payment-time-line')]//li[2]");
    }

    public void goPaymentHistoryPage() {
        clickOnElement("//div[text()[normalize-space()='Payment History']]");
    }

    public void verifyPONotCreate(String invoice) {
        verifyTextPresent(invoice, false);
    }

    public void selectTabPOPage(String tab) {
        clickOnElement("//div[contains(text(),'" + tab + "')]");
    }

    public String getPurchaseDate() {
        int index = getIndexTextInPaymentHis("purchasedDate");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]");
    }

    public String getPurchaseNumber() {
        int index = getIndexTextInPaymentHis("purchaseNumber");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]//a");
    }

    public String getTotalAmount() {
        int index = getIndexTextInPaymentHis("totalAmount");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]//div");
    }

    public String getNameInPaymentHis() {
        return getText("//tbody[@class='ant-table-tbody']//a");
    }

    public int getIndexTextInPaymentHis(String title) {
        return countElementByXpath("//th[@key='" + title + "']//preceding-sibling::th") + 1;
    }

    public String getNamePO() {
        int index = 0;
        if (isElementExist("//th[@key='detail']")) {
            index = getIndexTextInPaymentHis("detail");
        } else {
            index = getIndexTextInPaymentHis("1");
        }
        return getText("//div[@class='ant-table-wrapper'][1]//tr[contains(@class,'ant-table-row')]//td[" + index + "]");
    }

    public String getDetailInPaymentHis() {
        int index = getIndexTextInPaymentHis("detail");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]");
    }

    public String getTextInPaymentHis(String title) {
        int index = getIndexTextInPaymentHis(title);
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]//div");
    }

    public String getCreatedInPaymentHis() {
        return getTextInPaymentHis("created");
    }

    public String getStatusInPaymentHis() {
        int index = getIndexTextInPaymentHis("status");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]//span[contains(@class,'ant-tag')]");
    }

    public String getAmountInPaymentHis() {
        return getTextInPaymentHis("amount");
    }

    public void verifyEnablePay(String invoice, boolean isPay) {
        verifyElementPresent("//a[text()='" + invoice + "']//ancestor::tr[contains(@class,'ant-table-row')]//button[child::span[text()='Pay']]", isPay);
    }

    public String getNonInvoiceName() {
        int index = getIndexTextInPaymentHis("name");
        return getText("//tr[contains(@class,'ant-table-row')]//td[" + index + "]//div");
    }


    public String getSubTotal() {
        String subTotal = getText("//div[@class='total-price']//p[@class='p-total']");
        return subTotal.replace("US", "").trim();
    }

    public void clickOutFieldInput() {
        clickOnElement("(//div[@class='table-detail']//div[@class='ant-table-fixed-right']//tbody//tr)[1]//input[@class='ant-input-number-input']");
    }

    public void clickToShowTypeBank() {
        hoverThenClickElement("//div[@class='ant-select ant-select-enabled']", "//div[@class='ant-select ant-select-enabled']");
    }

    public void selectTypeBank(String payment) {
        clickOnElement("//div[@class='ant-select-dropdown-content']//li[text()='" + payment + "']");
    }

    public void clickPayPal() {
        switchToIFrame("//iframe[@title='paypal_buttons']");
        clickOnElement("//div[@data-funding-source='paypal']");
        waitUntilElementVisible("//div[@id='main']", 10);
    }

    String emailPayPal = LoadObject.getProperty("email.paypal");
    String passPayPal = LoadObject.getProperty("pass.paypal");

    public void enterEmailPayPal() {
        waitABit(2000);
        enterInputFieldWithLabel("Password", emailPayPal);
    }

    public void clickBtnNext() {
        clickBtn("Next");
    }

    public void enterPassPayPal() {
        enterInputFieldWithLabel("Password", passPayPal);
    }

    public void waitPurchaseDone() {
        waitUntilElementVisible("//div[contains(@class,'content-invoice')]", 12);
    }

    public void waitLoadInvoice() {
        waitUntilElementVisible("//div[contains(@class,'content-invoice')]", 15);
    }

    public void clickCreateOrder(String typeOrder) {
        clickOnElement("//p[text()='" + typeOrder + "']//ancestor::div[contains(@class,'card-customer')]//span[text()='Create order']//ancestor::button");
    }
}
