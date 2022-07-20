package opencommerce.orders.order_detail;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentWithOrderDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class FulfillmentWithDef {
    @Steps
    FulfillmentWithOrderDetailSteps fulfillmentWithOrderDetailSteps;

    String product = "";



    @And("Verify product ready to fulfill")
    public void getProductReadyToFulfill(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            product = SessionData.getDataTbVal(dataTable, row, "product");
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "shipping method");
            String shippingFee = SessionData.getDataTbVal(dataTable, row, "shipping fee");
            String estimatedShippingTime = SessionData.getDataTbVal(dataTable, row, "estimated shipping time");
            fulfillmentWithOrderDetailSteps.verifyTextFulfillWithShopBaseFulfillment();
            fulfillmentWithOrderDetailSteps.verifyInfoOrder(tab, product, shippingMethod, shippingFee, estimatedShippingTime);
        }
    }

    @Then("Verify order detail after in fulfill")
    public void verifyOrderDetailAfterInFulfill(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String product = SessionData.getDataTbVal(dataTable, row, "product");
            fulfillmentWithOrderDetailSteps.verifyInfoOrderAfterFulfill(product, tab);
        }
    }
    @And("Fulfill order")
    public void fulfillOrder() {
        fulfillmentWithOrderDetailSteps.clickBTFulfillOrder();

    }

    @And("Cancel fulfill line items")
    public void cancelFulfillLineItems() {
        fulfillmentWithOrderDetailSteps.cancelFulfill();

    }

    @Then("Verify status line items")
    public void verifyStatusLineItems() {
    }

    @Then("verify shippment status on order detail")
    public void verifyShippmentStatusOnOrderDetail() {
        fulfillmentWithOrderDetailSteps.reLoadPage();
        fulfillmentWithOrderDetailSteps.verifyShipmentStatus();
    }
}
