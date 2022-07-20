package com.opencommerce.shopbase.Pbase.steps;

import com.opencommerce.shopbase.Pbase.pages.RefundOrderPrintBasePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

public class RefundOrderPrintBaseStep {
    RefundOrderPrintBasePage refundOrderPrintBasePage;
    String nameShop = LoadObject.getProperty("nameShop");
    String firstShopName = LoadObject.getProperty("firstShopName");
    @Step
    public void clickOrder(String orderName) {
        refundOrderPrintBasePage.clickOrder(orderName);
    }

    @Step
    public void clickButton(String orderName, String name) {
        refundOrderPrintBasePage.verifyShowOrderName(orderName);
        refundOrderPrintBasePage.clickButton(name);
    }

    @Step
    public void verifyOrderDetailRefund(String orderName, String quantity, boolean _isboolean) {
        refundOrderPrintBasePage.verifyOrderNameRefund(orderName);
        if(_isboolean) {
            refundOrderPrintBasePage.verifyQuantityRefund(quantity);
        }
    }
    
    public void choiceQuantity(String quantity) {
        refundOrderPrintBasePage.choiceQuantity(quantity);
    }

    @Step
    public void fillDataRefundOrder(boolean withdrawSellerBalance, boolean sendMail) {
        refundOrderPrintBasePage.fillInValue("Refund shipping fee");
        refundOrderPrintBasePage.fillInValue("Refund taxes");
        refundOrderPrintBasePage.fillInValue("Refund payment fee");
        refundOrderPrintBasePage.fillInValue("Refund processing fee");
        refundOrderPrintBasePage.focuOut();
        refundOrderPrintBasePage.checkCheckboxWithLabel("Do not withdraw from Seller's balance", withdrawSellerBalance);
        refundOrderPrintBasePage.checkCheckboxWithLabel("Send notification to the buyer", sendMail);
    }

    @Step
    public void clickButonRefund() {
        refundOrderPrintBasePage.clickBtn("refund");
    }


    public HashMap<String, Float> calculateAmountAfterRefund(String blockName) {
        HashMap<String, Float> data = new HashMap<>();
        int countField = refundOrderPrintBasePage.countFieldByLabelBlock(blockName);
        for (int i = 1; i <= countField; i++) {
            String label = refundOrderPrintBasePage.getLabelInBlockByIndex(blockName, i);
            float valueRefund = refundOrderPrintBasePage.getValueRefundedByIndex(blockName, i);
            float availableAmount = refundOrderPrintBasePage.getValueAvailableByIndex(blockName, i);
            float expResult = availableAmount - valueRefund;
            data.put(label, expResult);
            dataCancel.put(blockName + " - " + label, valueRefund);
        }
        return data;
    }

    public HashMap<String, Float> calculateAmountAvailable(String blockName) {
        HashMap<String, Float> data = new HashMap<>();
        int countField = refundOrderPrintBasePage.countFieldByLabelBlock(blockName);
        for (int i = 1; i <= countField; i++) {
            String label = refundOrderPrintBasePage.getLabelInBlockByIndex(blockName, i);
            float availableAmount = refundOrderPrintBasePage.getValueAvailableByIndex(blockName, i);
            data.put(label, availableAmount);
        }
        return data;
    }

    @Step
    public void userNavigateScreen(String tabName) {
        refundOrderPrintBasePage.userNavigateScreen(tabName);
    }

    public void verifyStatusOrderList(String orderName, String status) {
        refundOrderPrintBasePage.verifyStatusOrder("PAYMENT STATUS", orderName,  status);
    }

    public void verifyRefundBuyer(float refundBuyer) {
        float act = refundOrderPrintBasePage.getPriceCustomer("Refunded to customer");
        assertThat(act).isEqualTo(refundBuyer);
    }

    public void verifyStatusOrderDetail(String status) {
        refundOrderPrintBasePage.verifyOrderStatusOrderDetail(status);
    }

    public void verifyOrderName(String orderNumber) {
        refundOrderPrintBasePage.verifyOrderName(orderNumber);
    }

    public void verifyRefundQuantity(int quantity) {
        refundOrderPrintBasePage.verifyRefundQuantity(quantity);
    }

    public void verifyNetPayment() {
        float paidCustomer = refundOrderPrintBasePage.getPriceCustomer("Paid by customer");
        float refunded = refundOrderPrintBasePage.getPriceCustomer("Refunded to customer");
        float netPaymnet = refundOrderPrintBasePage.getPriceCustomer("Net Payment");
        float exp = (float)Math.round((paidCustomer - refunded) * 100) / 100;
        assertThat(exp).isEqualTo(netPaymnet);
    }

    public void verifyValueForLabel(String label, float value) {
        float act = refundOrderPrintBasePage.getValueForLabel(label);
        assertThat(act).isEqualTo(value);
    }

    public void clickDropDown() {
        refundOrderPrintBasePage.clickDropDown();
    }

    public void verifyRefundTimeLine(float total) {
        refundOrderPrintBasePage.verifyRefundTimeLine(total);
    }

    public void verifyProfit(String label, float value) {
        refundOrderPrintBasePage.verifyProfit(label, value);
    }

    public float getProfitCurrent(String label, String orderName) {
        return refundOrderPrintBasePage.getProfit(label, orderName);
    }

    public void moveToInvoiceDetail(String orderName, String status) {
        refundOrderPrintBasePage.clickAvatar();
        refundOrderPrintBasePage.clickLinkWithLabel("Balance");
        refundOrderPrintBasePage.clickViewHistory("View history");
        refundOrderPrintBasePage.clickBtn("Filter");
        refundOrderPrintBasePage.clickTypeFilter();
        refundOrderPrintBasePage.clickOptionTypeFilter();
        refundOrderPrintBasePage.clickSelectType();
        refundOrderPrintBasePage.clickOptionType();
        String name = "Voided".equals(status) ? firstShopName : nameShop;
        refundOrderPrintBasePage.moveToInvoiceDetail(orderName, name);
    }

    public void verifyInvoiceHistory(float in, float out, boolean withdrawSellerBalance) {
        refundOrderPrintBasePage.verifyInvoiceDetail(in, out, withdrawSellerBalance);
    }

    public void verifyReceivedEmail(String email, String subject) {
        refundOrderPrintBasePage.openUrl("https://www.mailinator.com");
        refundOrderPrintBasePage.searchEmailInMailinator(email);
    }

    public void showCalculation() {
        refundOrderPrintBasePage.showCalculation();
    }

    public void clickButtonApprove(String buttonName) {
        refundOrderPrintBasePage.clickApporveButton(buttonName);
        refundOrderPrintBasePage.verifyApprovedButton("Approved");
    }

    public void fillInSellingPrice() {
        refundOrderPrintBasePage.fillInValue("Refund selling price");
    }

    public void clickToSection(String nameScreen) {
        refundOrderPrintBasePage.clickToSection(nameScreen);
    }

    public void searchOrder(String orderNumber) {
        refundOrderPrintBasePage.searchOrder(orderNumber);
    }
}
