package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.Pbase.pages.RefundOrderPrintBasePage;
import com.opencommerce.shopbase.plusbase.pages.OrderPlusBasePage;
import common.utilities.LoadObject;

import java.util.HashMap;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPlusBaseStep {
    OrderPlusBasePage orderPlusBasePage;
    RefundOrderPrintBasePage refundOrderPrintBasePage;

    public void verifyStatus(String status) {
        orderPlusBasePage.verifyTextPresent(status, true);
    }

    public void verifyLineItem(String product, String sku, String quantity) {
        orderPlusBasePage.verifyLineItem(product);
        orderPlusBasePage.verifyLineItem(sku);
        orderPlusBasePage.verifyLineItem(quantity);
    }

    public void verifyPaymentGroup(String label, String quantity, String val) {
        orderPlusBasePage.verifyPaymentGroup(label, quantity);
        orderPlusBasePage.verifyPaymentGroup(label, val);
    }

    public void verifyTotal(float val) {
        float act = orderPlusBasePage.getTotal();
        assertThat(act).isBetween(val - 0.01f, val + 0.01f);
    }

    public void verifyShippingMethod(String title, boolean _boolean, String ship) {
        String label = _boolean ? "Free" : ship;
        orderPlusBasePage.verifyShippingInPaymentGroup(title, label);
    }

    public float getDiscount(String label, boolean flgDiscountFree, boolean flagPPC, String productPPC) {
        float discount = 0;
        if (!label.isEmpty()) {
            if (flgDiscountFree) {
                discount = orderPlusBasePage.getValueInPayment("Shipping");
            } else {
                discount = Float.parseFloat(discountAmount.replace("$", ""));
            }
            if (flagPPC) {
                discount += orderPlusBasePage.getDiscountPPC(productPPC);
            }
        }
        return orderPlusBasePage.roundTwoDecimalPlaces(discount);
    }

    public void clickOpenProfitGroup(String label) {
        orderPlusBasePage.clickOpenProfitGroup(label);
    }

    public void clickOpenHanldingFee() {
        orderPlusBasePage.clickOpenHanldingFee();
    }

    public void verifyProfitGroup(String label, String val) {
        orderPlusBasePage.verifyProfitGroup(label);
        orderPlusBasePage.verifyProfitGroup(val);
    }

    public float getValueProfitGroup(String label) {
        return orderPlusBasePage.getValueProfitGroup(label);
    }

    public void verifyHandlingFeeGroup(String label, float val) {
        float act = orderPlusBasePage.getValueProfitGroup(label);
        if (act == 0) {
            orderPlusBasePage.refreshPage();
            orderPlusBasePage.waitForEverythingComplete(10);
            orderPlusBasePage.clickOpenProfitGroup("Show calculation");
            orderPlusBasePage.clickOpenHanldingFee();
        }
        compareValue(act, val);
    }

    public float getShippingFee(String label) {
        return orderPlusBasePage.getShippingFee(label);
    }

    public float round(float value) {
        return orderPlusBasePage.roundTwoDecimalPlaces(value);
    }

    public void searchOrder(String orderNumber) {
        orderPlusBasePage.searchOrder(orderNumber);
    }

    public void clickOrder(String orderNumber) {
        orderPlusBasePage.clickLinkTextWithLabel(orderNumber);
    }

    public void verifyProfit(String label, float val) {
        float act = orderPlusBasePage.getProfit(label);
        compareValue(act, val);
    }

    public void moveToBalance() {
        orderPlusBasePage.clickToAvatar();
        orderPlusBasePage.clickToBalance();
    }

    public float getValueBalance(String label) {
        return orderPlusBasePage.getValueBalance(label);
    }

    public void verifyValueBalance(String label, float val) {
        float act = orderPlusBasePage.getValueBalance(label);
        assertThat(act).isNotEqualTo(val);
    }

    public void clickButton(String label) {
        orderPlusBasePage.clickBtn(label);
    }

    public void moveToViewHistory(String orderName, String status) {
        String shopname = LoadObject.getProperty("shop");
        refundOrderPrintBasePage.moveToInvoiceDetail(orderName, shopname);
        orderPlusBasePage.verifyTextPresent(status, true);
    }

    public void verifyInvoiceDetail(float profit) {
        float start = orderPlusBasePage.roundTwoDecimalPlaces(profit - 0.01f);
        float end = orderPlusBasePage.roundTwoDecimalPlaces(profit + 0.01f);
        float act = orderPlusBasePage.getProfitInvoiceDetail();
        assertThat(act).isBetween(start, end);
    }

    private void compareValue(float act, float val) {
        float start = orderPlusBasePage.roundTwoDecimalPlaces(val - 0.01f);
        float end = orderPlusBasePage.roundTwoDecimalPlaces(val + 0.01f);
        assertThat(orderPlusBasePage.roundTwoDecimalPlaces(act)).isBetween(start, end);
    }

    public float getTotal() {
        return orderPlusBasePage.getTotal();
    }

    public float getDiscountOrder() {
        return orderPlusBasePage.getValueInPayment("Discount");
    }

    public float getValueDiscountPPC(String productPPC) {
        return orderPlusBasePage.getDiscountPPC(productPPC);
    }

    public void verifyStatus(String orderNumber, String paymentStatus, String approveStatus) {
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "PAYMENT")).isEqualTo(paymentStatus);
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "APPROVE")).isEqualTo(approveStatus);

    }

    public void chooseOrder(String orderNumber) {
        orderPlusBasePage.chooseOrder(orderNumber);
    }

    public void clickBTAction(String action) {
        orderPlusBasePage.clickBtn("Actions");
        orderPlusBasePage.clickBTAction(action);
        orderPlusBasePage.clickBtn("Confirm");
        orderPlusBasePage.waitForTextToAppear("successfully");
    }

    public void verifyOrderStatusApproved(String status) {
        assertThat(orderPlusBasePage.getStatus()).isEqualTo(status.toLowerCase());
    }

    public void verifyBTAction(String infoAction) {
        String[] actions = infoAction.split(",");
        for (String action : actions) {
            orderPlusBasePage.verifyBTAction(action);
        }

    }

    public void verifyTableName(String tableName) {
        String[] nameTable = tableName.split(",");
        for (String name : nameTable) {
            orderPlusBasePage.verifyTablename(name);
        }
    }

    public void verifyNameTab(String nameTab) {
        String[] nameTabs = nameTab.split(",");
        for (String name : nameTabs) {
            orderPlusBasePage.verifyNameTab(name);
        }
    }

    public void verifyNotDisplayEdit(String notEditInfo) {
        String[] infoNotEdit = notEditInfo.split(",");
        for (String info : infoNotEdit) {
            orderPlusBasePage.verifyNotDisplayBTEdit(info);

        }
    }

    public void verifyStatusOnStoreMerchant(String orderNumber, String paymentStatus, String fulfillStatus) {
        assertThat(orderPlusBasePage.verifyStatusStoreMerchant(orderNumber, "PAYMENT STATUS")).isEqualTo(paymentStatus);
        assertThat(orderPlusBasePage.verifyStatusStoreMerchant(orderNumber, "FULFILLMENT STATUS")).isEqualTo(fulfillStatus);
    }


    public String getTrackingNumberLineItem() {
        return orderPlusBasePage.getTrackingNumberLineItem();
    }

    public void clickBTRefundOrder() {
        orderPlusBasePage.clickBtn("Refund order");
    }

    public void verifyStatusFulfilment(String orderNumber, String fulfillStatus, String approveStatus) {
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "FULFILLMENT")).isEqualTo(fulfillStatus);
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "APPROVE")).isEqualTo(approveStatus);

    }


    public String getShippingInPaymentGroup(boolean _isDiscount) {
        String shipping = shippingFee.replace("USD", "");
        if (_isDiscount) {
            shipping = Float.toString(orderPlusBasePage.getValueInPayment("Discount"));
        }
        return shipping;
    }

    public float getSubTotal(String label) {
        return orderPlusBasePage.getSubTotal(label);
    }

    public void verifyProduct(String product, String quantity) {
        orderPlusBasePage.verifyProduct(product, quantity);
    }

    public void clickMoreAction(String button) {
        orderPlusBasePage.clickMoreAction(button);
    }

    public void verifyInfo(String label) {
        orderPlusBasePage.verifyTextPresent(label, true);
    }

    public Float covertToFloat(String val) {
        return orderPlusBasePage.removeCurrency(val);
    }

    public void choiceQuantity(String quantity, String product) {
        orderPlusBasePage.choiceQuantity(quantity, product);
    }

    public void checkCheckbox(String label, boolean isBoolean) {
        orderPlusBasePage.checkCheckboxWithLabel(label, isBoolean);
    }

    public HashMap<String, Float> getAmountBeforeRefund(String blockName) {
        HashMap<String, Float> data = new HashMap<>();
        int countField = orderPlusBasePage.countFieldByLabelBlock(blockName);
        for (int i = 1; i <= countField; i++) {
            String label = orderPlusBasePage.getLabelInBlockByIndex(blockName, i);
            float valueRefund = orderPlusBasePage.getValueRefundedByIndex(blockName, i);
            float availableAmount = orderPlusBasePage.getValueAvailableByIndex(blockName, i);
            float expResult = availableAmount - valueRefund;
            data.put(label, expResult);
            dataRefundForSeller.put(label, valueRefund);
        }
        return data;
    }

    public void EnterShippingFee(String label) {
        float shippingFee = orderPlusBasePage.getShippingFeeRefund(label);
        orderPlusBasePage.EnterShippingFee(label, shippingFee);
    }

    public void verifyFulfillmentGroup(String product, String quantity) {
        orderPlusBasePage.verifyFulfillmentGroup(product, quantity);
    }

    public HashMap<String, Float> amountRefund(String blockName) {
        HashMap<String, Float> data = new HashMap<>();
        int countField = orderPlusBasePage.countFieldByLabelBlock(blockName);
        for (int i = 1; i <= countField; i++) {
            String label = orderPlusBasePage.getLabelInBlockByIndex(blockName, i);
            float valueRefund = orderPlusBasePage.getValueRefundedByIndex(blockName, i);
            data.put(label, valueRefund);
        }
        return data;
    }

    public float getProfit(String label) {
        return orderPlusBasePage.getProfit(label);
    }

    public void verifyRefundedCustomer(String label, float val) {
        float act = orderPlusBasePage.getValuePaymentGroup(label);
        assertThat(act).isEqualTo(orderPlusBasePage.roundTwoDecimalPlaces(val));
    }

    public void verifyNetPayment(String label) {
        float paidByCustomer = orderPlusBasePage.getValuePaymentGroup("Paid by customer");
        float refundedCustomer = orderPlusBasePage.getValuePaymentGroup("Refunded to customer");
        float exp = orderPlusBasePage.roundTwoDecimalPlaces(paidByCustomer - refundedCustomer);
        float act = orderPlusBasePage.getValuePaymentGroup("Net Payment");
        assertThat(act).isEqualTo(exp);
    }

    public void verifyRefundProfitGroup(String label, float val) {
        float act = orderPlusBasePage.getValueProfitGroup(label);
        assertThat(act).isBetween(orderPlusBasePage.roundTwoDecimalPlaces(val) - 0.01f, orderPlusBasePage.roundTwoDecimalPlaces(val) + 0.01f);
    }

    public void verifyProfitAfterRefund(String label, float val) {
        float act = orderPlusBasePage.getProfit(label);
        assertThat(act).isBetween(orderPlusBasePage.roundTwoDecimalPlaces(val) - 0.01f, orderPlusBasePage.roundTwoDecimalPlaces(val) + 0.01f);
    }

    public void verifyInvoiceHistory(float in, float out, boolean isBoolean) {
        orderPlusBasePage.verifyInvoiceHistory(in, out, isBoolean);
    }

    public void focusOut() {
        orderPlusBasePage.focusOut();
    }

    public void EnterValueByLabel(String blockName, String label) {
        float val = orderPlusBasePage.getValueAvailable(blockName, label);
        orderPlusBasePage.EnterValueByLabel(blockName, label, val);
    }

    public void choosePaymentMethod(String paymentMethod, String code) {
        orderPlusBasePage.choosePaymentMethod(paymentMethod);
        orderPlusBasePage.enterCode(code);
        orderPlusBasePage.clickBtn("Complete order");
    }

    public void searchOrderName(String orderName) {
        orderPlusBasePage.searchOrderName(orderName);
    }

    public void selectOrder() {
        orderPlusBasePage.selectOrder();
    }

    public void clickContinueToPaymentMethod() {
        orderPlusBasePage.waitForEverythingComplete();
        orderPlusBasePage.clickBtn("Continue to shipping method");
    }

    public float getAdjustedValue() {
        return orderPlusBasePage.getAdjustedValue();
    }

    public void verifyStatusOnStoreTemplate(String orderNumber, String paymentStatus, String fulfillStatus) {
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "PAYMENT")).isEqualTo(paymentStatus);
        assertThat(orderPlusBasePage.verifyStatus(orderNumber, "FULFILLMENT")).isEqualTo(fulfillStatus);
    }

    public void clickShopBaseFulfillment(int index) {
        orderPlusBasePage.clickBtn("ShopBase Fulfillment", index);
    }

    public void removeFilterFulfill() {
        orderPlusBasePage.removeFilterFulfill();
    }

    public void getTrackingNumber() {
        trackingNumber = orderPlusBasePage.getTrackingNumberLineItem();
    }

    public float getTax() {
        return orderPlusBasePage.getTax();
    }

    public void refreshPage() {
        orderPlusBasePage.refreshPage();
        orderPlusBasePage.waitForPageLoad();
    }

    public boolean isData() {
        return orderPlusBasePage.isLinkTextExist("Balance");
    }
}
