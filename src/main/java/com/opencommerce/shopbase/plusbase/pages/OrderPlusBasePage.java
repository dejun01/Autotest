package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPlusBasePage extends SBasePageObject {
    public OrderPlusBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyLineItem(String title) {
        verifyElementVisible("//div[@class='card__section' and contains(. , '" + title + "')]", true);
    }

    public void verifyPaymentGroup(String title, String val) {
        verifyElementVisible("//tr[contains(. , '" + title + "') and contains(., '" + val + "')]", true);
    }

    public float getValueInPayment(String label) {
        return getPrice("//tr[child::td[normalize-space()='" + label + "']]/td[last()]//span");
    }

    public float getDiscountPPC(String productPPC) {
        String xpath = "//div[contains(@class, 'item-details') and descendant::a[normalize-space()='" + productPPC + "']]//*[contains(@class, 'quantity--desktop')]";
        float price = getPrice(xpath + "/span[1]/del");
        float priceDiscount = getPrice(xpath + "/span[2]");
        int quantity = Integer.parseInt(getText(xpath + "/span[last()]"));
        float exp = (price - priceDiscount) * quantity;
        return exp;
    }

    public void clickOpenProfitGroup(String label) {
        clickOnElement("//span[normalize-space()='" + label + "']");
    }

    public void clickOpenHanldingFee() {
        clickOnElement("//*[contains(@class, 'down-drop-circle')]");
    }

    String xpathFiled = "//section//div[contains(. , '%s') and contains(@class, 'justify-content-space-between')]";

    public void verifyProfitGroup(String title) {
        verifyElementVisible(String.format(xpathFiled, title), true);
    }

    public float getValueProfitGroup(String label) {
        return getPrice(String.format(xpathFiled, label) + "//span[last()]");
    }

    public float getShippingFee(String label) {
        float ship = 0;
        if (isElementExist(String.format(xpathFiled, label))) {
            ship = getValueProfitGroup(label);
        }
        return ship;
    }

    public void searchOrder(String orderNumber) {
        waitClearAndTypeThenEnter("//div[@class='order-search-form']//input", orderNumber);
    }

    public float getProfit(String label) {
        return getPrice("//div[child::div[child::span[text()='" + label + "']]]/span[last()]");
    }

    public void clickToAvatar() {
        waitForEverythingComplete();
        clickOnElement("//span[contains(@class, 'avatar')]/img");
    }

    public void clickToBalance() {
        clickOnElement("//div[contains(text(), 'Balance ($')]");
    }

    public float getValueBalance(String label) {
        return getPrice("//div[descendant::h3[normalize-space()='" + label + "'] and @class='flex-space-between']/h3");
    }

    public float getProfitInvoiceDetail() {
        int count = getIndexElemInSBase("Amount");
        String xpath = "//tbody//span[contains(text(), 'Charged from the order')]//ancestor::tr/td[" + count + "]/span";
        return getPrice(xpath);
    }

    public int getIndexElemInSBase(String colname) {
        return countElementByXpath("//th[child::span[contains(text(), '" + colname + "')]]/preceding-sibling::th") + 1;
    }

    public float getTotal() {
        return getPrice("//tr[child::td[contains(text(), 'Total')]]/td[last()]/div");
    }

    public String verifyStatus(String orderNumber, String s) {
        int index = getIndex(s);
        String xpathFulfillmentStatus = "//a[text()[normalize-space()='" + orderNumber + "']]/ancestor::tr//td[" + index + "]";
        return getText(xpathFulfillmentStatus);
    }

    private int getIndex(String s) {
        return countElementByXpath("//th[child::span//div[contains(text(), '" + s + "')]]/preceding-sibling::th") + 1;
    }

    public void chooseOrder(String orderNumber) {
        String xpath = "//td[descendant::a[contains(text(),'" + orderNumber + "')]]//preceding-sibling::td//input";
        focusClickOnElement(xpath);

    }

    public void clickBTAction(String action) {
        String xpath = "//span[normalize-space()='" + action + "']";
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public String getStatus() {
        String xpath = "//span[@class='s-tag is-success']";
        return getText(xpath).toLowerCase();
    }

    public void verifyBTAction(String payment_status) {
        String xpath = "//button//span[contains(text(),'" + payment_status + "')]";
        Assert.assertTrue(isElementExist(xpath));
    }

    public void verifyTablename(String order) {
        String xpath = "//th[child::span[contains(text(), '" + order + "')]]";
        Assert.assertTrue(isElementExist(xpath));

    }

    public void verifyNameTab(String all) {
        String xpath = "//li//span[contains(text(),'" + all + "')]";
        Assert.assertTrue(isElementExist(xpath));
    }

    public void verifyNotDisplayBTEdit(String contact_information) {
        String xpath = "//div[child::h3[contains(text(),'" + contact_information + "')]]//following-sibling::div//button/span[contains(text(),'Edit')]";
        Assert.assertFalse(isElementExist(xpath));
    }

    public String verifyStatusStoreMerchant(String orderNumber, String s) {
        int index = getIndexElemInSBase(s);
        String xpathFulfillmentStatus = "//a[text()[normalize-space()='" + orderNumber + "']]/ancestor::tr//td[" + index + "]";
        return getText(xpathFulfillmentStatus);
    }

    public String getTrackingNumberLineItem() {
        String xpath = "//div[text()[normalize-space()='Tracking number:']]//a";
        return getText(xpath);
    }

    public float getSubTotal(String label) {
        return getPrice("//tr[child::td[normalize-space()='" + label + "']]/td[last()]//div");
    }

    public void verifyShippingInPaymentGroup(String title, String label) {
        verifyElementVisible("//tr[child::td[normalize-space()='" + title + "']]/td[last() and contains(., '" + label + "')]", true);
    }

    public void verifyProduct(String product, String quantity) {
        String xpath = "//section[contains(., '" + product + "') and contains(., '" + quantity + "')]";
        try {
            waitElementToBeVisible(xpath, 20);
            verifyElementVisible(xpath, true);
        } catch (Throwable t) {
            refreshPage();
            waitElementToBeVisible(xpath, 50);
            verifyElementVisible(xpath, true);
        }
    }

    public void clickMoreAction(String button) {
        waitForEverythingComplete();
        String xpath = "//span[normalize-space()='" + button + "']";
        try {
            clickOnElement(xpath);
        } catch (Throwable t) {
            for (int i = 1; i < 3; i++) {
                if (!isElementExist(xpath)) {
                    refreshPage();
                    waitForEverythingComplete(30);
                    continue;
                }
                break;
            }
            clickOnElement(xpath);
        }
    }

    public void choiceQuantity(String quantity, String product) {
        String xpath = "//tr[descendant::a[normalize-space()='" + product + "']]//input";
        if (isElementExist(xpath)) {
            waitClearAndType(xpath, quantity);
        }
    }

    public int countFieldByLabelBlock(String blockName) {
        return countElementByXpath("//div[descendant::span[normalize-space()='" + blockName + "'] and contains(@class, 'flex-column')]//input[@type='number']");
    }

    String xpathRefund = "//div[descendant::span[normalize-space()='%s'] and contains(@class, 'flex-column')]//div[@style][%d]";

    public String getLabelInBlockByIndex(String blockName, int index) {
        String xpath = String.format(xpathRefund + "//div[contains(@class, 'font-bold')]", blockName, index);
        return getText(xpath);
    }

    public float getValueRefundedByIndex(String blockName, int index) {
        String xpath = String.format(xpathRefund + "//input", blockName, index);
        return removeCurrency(getValue(xpath));
    }

    public float getValueAvailableByIndex(String blockName, int index) {
        String xpath = String.format(xpathRefund + "//div[contains(@class, 'text-italic')]", blockName, index);
        return removeCurrency(getText(xpath));
    }

    public void EnterShippingFee(String label, float val) {
        String xpath = "//div[descendant::div[normalize-space()='" + label + "'] and contains(@class, 'no-gutters')]//input[not(@disabled)]";
        waitClearAndType(xpath, Float.toString(val));
    }

    public float getShippingFeeRefund(String label) {
        return getPrice("//div[descendant::div[normalize-space()='" + label + "'] and contains(@class, 'no-gutters') and descendant::input[not(@disabled)]]//div[contains(@class, 'text-italic')]");
    }

    public void verifyFulfillmentGroup(String product, String quantity) {
        waitForEverythingComplete();
        String xpath = "//section[contains(.,  'Removed items(" + quantity + ")') and contains(., '" + product + "')]";
        verifyElementVisible(xpath, true);
    }

    public float getValuePaymentGroup(String label) {
        return getPrice("//tr[child::td[normalize-space()='" + label + "']]/td[last()]");
    }

    public void verifyInvoiceHistory(float in, float out, boolean isBoolean) {
        int count = getIndexElemInSBase("Amount");
        String xpathOUT = "//tbody//span[contains(text(), 'refund for buyer')]//ancestor::tr/td[" + count + "]/span";
        String xpathIN = "//tbody//span[contains(text(), 'Refund for seller')]//ancestor::tr/td[" + count + "]/span";
        if (isBoolean) {
            verifyElementVisible(xpathOUT, false);
        } else {
            assertThat(getPrice(xpathOUT)).isEqualTo(roundTwoDecimalPlaces(out));
        }
        assertThat(getPrice(xpathIN)).isEqualTo(roundTwoDecimalPlaces(in));
    }

    public void focusOut() {
        clickOnElement("//tr/th[normalize-space()='FULFILLMENT STATUS']");
    }

    String xpathEnter = "//div[descendant::span[normalize-space()='%s'] and contains(@class, 'flex-column')]/div[descendant::div[normalize-space()='%s']]";

    public float getValueAvailable(String blockName, String label) {
        String xpath = String.format(xpathEnter + "//div[contains(@class, 'font-bold')]", blockName, label);
        return getPrice(xpath);
    }

    public void EnterValueByLabel(String blockName, String label, float val) {
        String xpath = String.format(xpathRefund + "//input", blockName, label);
        waitClearAndType(xpath, Float.toString(val));
    }

    public void choosePaymentMethod(String paymentMethod) {
        String xpath = "//input[contains(@value,'sepa_debit')]";
        focusClickOnElement(xpath);
    }

    public void enterCode(String code) {
        switchToIFrame("//div[@id='iban-element']//iframe");
        String xpath = "//input[@name='iban']";
        waitClearAndType(xpath, code);
        switchToIFrameDefault();
    }

    public void searchOrderName(String orderName) {
        String xpath = "//div[@class='order-search-form']//input[@placeholder ='Search orders']";
        waitClearAndTypeThenEnter(xpath, orderName);
        waitUntilElementVisible("//a[@class='order-link']", 15);
    }

    public void selectOrder() {
        waitForEverythingComplete();
        checkCheckbox("//span[@data-label='Select all orders']//label", true);
    }

    public float getAdjustedValue() {
        return Float.parseFloat(getText("//*[child::*[contains(text(), 'Adjusted shipping')]]/span").replace("$", ""));
    }

    public void removeFilterFulfill() {
        clickOnElement("//div[child::div[text()='Time since created (hours) >= 6']]//i");
    }

    public float getTax() {
        return getPrice("//tr[child::td[text()='Tax']]/td[last()]/div");
    }
}
