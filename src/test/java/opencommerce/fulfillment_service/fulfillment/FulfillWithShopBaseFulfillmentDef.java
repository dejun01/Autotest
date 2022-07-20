package opencommerce.fulfillment_service.fulfillment;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillWithShopBaseFulfillmentSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class FulfillWithShopBaseFulfillmentDef {

    @Steps
    FulfillWithShopBaseFulfillmentSteps fulfillmentSteps;

    @Steps
    OrderSteps orderSteps;

    @Then("verify data order in Fulfill with PlusHub page then Fulfill order")
    public void verify_data_order_in_Fulfill_with_ShopBase_fulfillment_page_then_Fulfill_order(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String statusFulfill = SessionData.getDataTbVal(dataTable, row, "Status Fulfill");
            String lineitem = SessionData.getDataTbVal(dataTable, row, "Product name>Variant");
            String baseCost = SessionData.getDataTbVal(dataTable, row, "Base cost>Quantity");
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            String shippingFee = SessionData.getDataTbVal(dataTable, row, "Shipping fee");
            String est = SessionData.getDataTbVal(dataTable, row, "Estimated shipping time");
            String inventoryStatus = SessionData.getDataTbVal(dataTable, row, "Inventory status");
            boolean fulfill = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Fulfill order"));
            fulfillmentSteps.verifyStatusFulfill(statusFulfill, lineitem);
            if (!baseCost.isEmpty()) {
                fulfillmentSteps.verifyBaseCostLineItem(baseCost, lineitem);
            }
            if (!shippingMethod.isEmpty()) {
                fulfillmentSteps.verifyShippingMethod(shippingMethod, lineitem);
            }
            if (!shippingFee.isEmpty()) {
                fulfillmentSteps.verifyShippingFee(shippingFee, lineitem);
            }
            if (!est.isEmpty()) {
                fulfillmentSteps.verifyEst(est, lineitem);
            }
            if (!inventoryStatus.isEmpty()) {
                fulfillmentSteps.verifyInventoryStatus(inventoryStatus, lineitem);
            }
            if(fulfill){
                fulfillmentSteps.fulfillOrder();
                fulfillmentSteps.payOrder();
                fulfillmentSteps.wailToFulfillDone();
                orderSteps.waitForShowDataOrder();
            }
        }

    }
}
