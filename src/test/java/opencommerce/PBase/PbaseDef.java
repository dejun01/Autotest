package opencommerce.PBase;

import com.opencommerce.shopbase.Pbase.steps.PbaseSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.*;

import java.util.List;

public class PbaseDef {
    @Steps
    OrderSteps orderSteps;

    @Steps
    PBaseOrderStep pBaseOrderStep;

    @Steps
    PbaseSteps pbaseSteps;

    public String domain = LoadObject.getProperty("shop");


    @And("Approved order in hive and verify info order in order detail")
    public void approvedOrderInHiveAndVerifyInfoOrderInOrderDetail() {
        pBaseOrderStep.clickOrderDetail(orderNumber);
        pBaseOrderStep.clickBTCaculate();
        pBaseOrderStep.clickBTApproved();
        assertThat(pBaseOrderStep.getTextOrderNameDetail()).isEqualTo(orderNumber);
        pBaseOrderStep.verifyDisplayBTManualFulfill();
        pBaseOrderStep.veryDisableBTApproved();


    }

    @And("Search order created and verify info order")
    public void searchOrderCreatedAndVerifyInfoMultiOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String conditions = SessionData.getDataTbVal(dataTable, row, "condition");
            String fulfillmentStatus = SessionData.getDataTbVal(dataTable, row, "Fulfillment Status");
            String paymentStatus = SessionData.getDataTbVal(dataTable, row, "Payment Status");
            String approvedDate = SessionData.getDataTbVal(dataTable, row, "Approved At");
            if (!approvedDate.isEmpty()) {
                approvedDate = DateTimeUtil.getTodayFormatRequest();
            }
            String condition = "";
            switch (conditions) {
                case "Order Name":
                    condition = orderNumber;
                    break;
                case "Domain":
                    condition = domain;
                    break;
            }
            pBaseOrderStep.clickFilter(conditions);
            pBaseOrderStep.enterOrderCondition(conditions, condition);
            pBaseOrderStep.verifyInfOrder(orderNameList, fulfillmentStatus, paymentStatus, approvedDate);

        }

    }

    @And("Choose order approved")
    public void chooseOrderApproved() {
        pBaseOrderStep.clickOrderApproved(orderNameList);

    }

    @And("Approved multi order")
    public void approvedMultiOrder() {
        pBaseOrderStep.approvedMultiOrder();
    }

    @Then("Display error {string}")
    public void displayError(String error) {
        assertThat(pBaseOrderStep.getTextError()).isEqualTo(error);

    }

    @Then("Search and verify fulfillment status = {string} on order pbase list")
    public void searchAndVerifyFulfillmentStatusOnOrderPbaseList(String status) {
        orderSteps.searchOrderNameOnOrderList(orderNumber);
        pbaseSteps.verifyStatus(orderNumber, status);
    }

    @And("^Click to \"([^\"]*)\"$")
    public void clickTo(String page) {
        pBaseOrderStep.accPage(page);
    }

    @Then("Search and verify fulfillment status = {string} on order multi pbase list")
    public void searchAndVerifyFulfillmentStatusOnOrderMultiPbaseList(String status) {
        for (String orderName : orderNameList) {
            orderSteps.searchOrderNameOnOrderList(orderName);
            pbaseSteps.verifyStatus(orderName, status);
        }
    }

    @And("Confirm approved")
    public void confirmApproved() {
        pBaseOrderStep.confirmApproved();
    }


    @And("Turn off postPurchase")
    public void turnOffPostPurchase() {
        pbaseSteps.clickPostPurchase();

    }


}
