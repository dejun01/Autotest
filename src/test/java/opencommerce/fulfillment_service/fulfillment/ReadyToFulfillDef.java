package opencommerce.fulfillment_service.fulfillment;

import static com.opencommerce.shopbase.OrderVariable.*;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ReadyToFulfillSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class ReadyToFulfillDef {
    @Steps
    ReadyToFulfillSteps ReadyToFulfillStep;

    @And("move to fulfillment order with condition = {string}")
    public void moveToFulfillmentOrderWithCondition(String condition, List<List<String>> dataTable) {
        for(int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String label = SessionData.getDataTbVal(dataTable, row, "Label status");
            String sbaseButton = SessionData.getDataTbVal(dataTable, row, "Button shopBase fulfillment");
            String fulfillOrderButton = SessionData.getDataTbVal(dataTable, row, "Button Fulfill order");
            String selectOrderButton = SessionData.getDataTbVal(dataTable, row, "Button selected orders");
            ReadyToFulfillStep.focusOut(label);
            ReadyToFulfillStep.searchOrderName(orderNumber);
            ReadyToFulfillStep.clickButton(sbaseButton);
            ReadyToFulfillStep.choiceWithCondition(condition);
            ReadyToFulfillStep.clickButton(fulfillOrderButton);
            ReadyToFulfillStep.verifyButton(selectOrderButton);
        }
    }

    @And("verify result search with")
    public void verifyResultSearchWith(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            String baseCost = SessionData.getDataTbVal(dataTable, row, "Base cost");
            String shippingFee = SessionData.getDataTbVal(dataTable, row, "Shipping fee");
            String estimatedShippingTime = SessionData.getDataTbVal(dataTable, row, "Estimated shipping time");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            if(orderName.contains("@")) {
                orderName = orderNumber;
            }
            ReadyToFulfillStep.verifyResult("order-name", orderName);
            ReadyToFulfillStep.verifyShippingMethod("shipping-method", shippingMethod);
            ReadyToFulfillStep.verifyResult("base-cost", baseCost);
            ReadyToFulfillStep.verifyResult("shipping-fee", shippingFee);
            ReadyToFulfillStep.verifyResult("estimated-shipping-time", estimatedShippingTime);
            ReadyToFulfillStep.verifyProduct(product);
        }
    }

    @And("verify info before pay order with name = {string}")
    public void fulfillOrderWithName(String orderName) {
        if(orderName.contains("@")) {
            orderName = orderNumber;
        }
        ReadyToFulfillStep.choiceOrder();
        ReadyToFulfillStep.clickButton("Fulfill selected orders");
        ReadyToFulfillStep.verifyPriceFulfill();
        ReadyToFulfillStep.clickButton("Pay");
        ReadyToFulfillStep.verifyMessage();
        ReadyToFulfillStep.verifyStatusOrder("Processing", orderName);
    }
}
