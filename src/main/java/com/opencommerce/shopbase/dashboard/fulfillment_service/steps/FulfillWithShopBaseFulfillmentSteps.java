package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.FulfillmentPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.FulfilWithShopBaseFulfillmentPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class FulfillWithShopBaseFulfillmentSteps extends ScenarioSteps {

    FulfilWithShopBaseFulfillmentPage fulfillmentPage;
    FulfillmentPage fulfillmentPages;

    @Step
    public void verifyStatusFulfill(String statusFulfill, String lineitem) {
        String[] property = lineitem.split(">");
        String act = fulfillmentPage.getStatusFulfill(property[0], property[1]);
        assertThat(act).contains(statusFulfill);
    }

    @Step
    public void verifyBaseCostLineItem(String baseCost, String lineitem) {
        String act = fulfillmentPage.getBaseCostLineItem(lineitem);
        assertThat(act).contains(baseCost);
    }

    @Step
    public void verifyShippingMethod(String shippingMethod, String lineitem) {
        String act = fulfillmentPage.getShippingMethod(lineitem);
        assertThat(act).isEqualTo(shippingMethod);
    }

    @Step
    public void verifyShippingFee(String shippingFee, String lineitem) {
        String act = fulfillmentPage.getShippingFee(lineitem);
        assertThat(act).isEqualTo(shippingFee);
    }

    @Step
    public void verifyEst(String est, String lineitem) {
        String act = fulfillmentPage.getEst(lineitem);
        assertThat(act).isEqualTo(est);
    }

    @Step
    public void verifyInventoryStatus(String inventoryStatus, String lineitem) {
        String act = fulfillmentPage.getInventoryStatus(lineitem);
        assertThat(act).isEqualTo(inventoryStatus);
    }

    @Step
    public void fulfillOrder() {
        int i = 0;
        waitABit(3000);
        while (fulfillmentPage.isNoOrder() && i < 5){
            refreshPage();
            waitABit(3000);
            i++;
        }

        fulfillmentPage.selectOrderToFulfill();
        fulfillmentPage.fulfillOrder();
    }

    public void refreshPage() {
        fulfillmentPage.refreshPage();
        fulfillmentPage.waitForPageLoad();
    }

    @Step
    public void payOrder() {
        if(fulfillmentPages.isBalanceNotEnough()){
            fulfillmentPages.clickBtn("Top up");
            fulfillmentPages.clickBtn("Confirm top up");
        }
        fulfillmentPage.payOrder();
        fulfillmentPage.verifyTextPresent("Service is paid successfully", true);
    }

    @Step
    public void wailToFulfillDone() {
        fulfillmentPage.wailToFulfillDone();
    }
}
