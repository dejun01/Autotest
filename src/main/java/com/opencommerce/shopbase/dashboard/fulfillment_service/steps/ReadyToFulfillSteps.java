package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.ReadyToFulfillPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyToFulfillSteps {
    ReadyToFulfillPage readyToFulfillPage;

    public void clickButton(String buttonName) {
        readyToFulfillPage.clickButton(buttonName);
    }

    public void choiceWithCondition(String condition) {
        readyToFulfillPage.selectRadioButtonWithLabel(condition, true);
    }

    public void searchOrderName(String name) {
        readyToFulfillPage.searchOrderName(name);
    }

    public void verifyResult(String label, String value) {
        readyToFulfillPage.verifyResult(label, value);
    }

    public void verifyShippingMethod(String label, String value) {
        readyToFulfillPage.verifyShippingMethod(label, value);
    }

    public void verifyButton(String name) {
        readyToFulfillPage.verifyButton(name);
    }

    public void choiceOrder() {
        readyToFulfillPage.choiceOrder();
    }

    public void verifyPriceFulfill() {
        float baseCost = readyToFulfillPage.getPriceItem("base-cost");
        float shippingFee = readyToFulfillPage.getPriceItem("shipping-fee");
        float actTotal = readyToFulfillPage.getPriceTotal();
        float expTotal = (float) Math.round((baseCost+shippingFee)*100)/100;
        assertThat(actTotal).isEqualTo(expTotal);
    }

    public void verifyMessage() {
        readyToFulfillPage.verifyMessage();
    }

    public void verifyStatusOrder(String status, String name) {
        readyToFulfillPage.searchOrderName(name);
        readyToFulfillPage.verifyStatusOrder(status);
    }

    public void focusOut(String label) {
        readyToFulfillPage.focusOut(label);
    }

    public void verifyProduct(String product) {
        readyToFulfillPage.verifyProduct(product);
    }
}
