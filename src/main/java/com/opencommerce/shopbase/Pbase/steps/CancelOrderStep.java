package com.opencommerce.shopbase.Pbase.steps;

import com.opencommerce.shopbase.Pbase.pages.CancelOrderPage;
import com.opencommerce.shopbase.Pbase.pages.RefundOrderPrintBasePage;
import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import com.opencommerce.shopbase.dashboard.products.pages.ProductListPages;

import static org.assertj.core.api.Assertions.assertThat;

public class CancelOrderStep {
    CancelOrderPage cancelOrderPage;
    OrderPage orderPage;
    RefundOrderPrintBasePage refundOrderPage;
    ProductListPages productListPages;

    public void searchOrderNameOnOrderList(String orderNumber) {
        orderPage.searchOrderNameOnOrderList(orderNumber);
    }

    public void verifyStatusOrderList(String orderName, String status) {
        refundOrderPage.verifyStatusOrder("PAYMENT STATUS", orderName, status);
    }

    public float getProfitCurrent(String label, String orderName) {
        return refundOrderPage.getProfit(label, orderName);
    }

    public void clickOrder(String orderNumber) {
        refundOrderPage.clickLinkTextWithLabel(orderNumber);
    }

    public void clickButonCancel(String label) {
        refundOrderPage.clickBtn(label);
    }

    public void verifyCancelTimeLine(float value) {
        cancelOrderPage.verifyReasonCancel("ShopBase canceled this order. Reason");
        if(value > 0) {
            cancelOrderPage.verifyRefuned(value);
        }
    }

    public float getDiscountValue(String label) {
        refundOrderPage.showCalculation();
        refundOrderPage.clickDropDown();
        return cancelOrderPage.getValueForLabel(label);
    }

    public void verifyDiscount(String label, float value) {
        float act = cancelOrderPage.getValueForLabel(label);
        assertThat(act).isEqualTo(value);
    }

    public void verifySendMail(String subject, String email) {
        productListPages.verifyReceivedMail(email, subject);
    }
}
