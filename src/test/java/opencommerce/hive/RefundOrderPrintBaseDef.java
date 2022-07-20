package opencommerce.hive;

import com.opencommerce.shopbase.Pbase.steps.RefundOrderPrintBaseStep;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RefundOrderPrintBaseDef {
    @Steps
    RefundOrderPrintBaseStep refundOrderPrintBaseStep;
    @Steps
    OrderSteps orderSteps;

    public float currentProfit = 0;
    int quantityOrder = 0;
    HashMap<String, Float> dataAfterRefundBuyer = new HashMap<>();
    HashMap<String, Float> dataAfterRefundSeller = new HashMap<>();
    HashMap<String, Float> dataAvailableBuyer = new HashMap<>();
    HashMap<String, Float> dataAvailableSeller = new HashMap<>();
    String blockNameBuyer = "Request to refund for buyer";
    String blockNameSeller = "Request to refund for seller";
    boolean withdrawSellerBalance = false;
    boolean sendMail = false;
    boolean choiceQuantity = true;

    @And("refund order on hive pbase")
    public void refundOrderOnHivePbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order name").replace("@order name@", orderNumber);
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            withdrawSellerBalance = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Withdraw seller balance"));
            sendMail = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Send mail"));
            choiceQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Choice quantity"));
            quantityOrder = Integer.parseInt(quantity);
            refundOrderPrintBaseStep.verifyOrderDetailRefund(orderName, quantity, true);

            if (choiceQuantity) {
                refundOrderPrintBaseStep.fillInSellingPrice();
            }else {
                refundOrderPrintBaseStep.choiceQuantity(quantity);
            }
            refundOrderPrintBaseStep.fillDataRefundOrder(withdrawSellerBalance, sendMail);

            dataAfterRefundSeller = refundOrderPrintBaseStep.calculateAmountAfterRefund(blockNameSeller);
            dataAfterRefundBuyer = refundOrderPrintBaseStep.calculateAmountAfterRefund(blockNameBuyer);

            refundOrderPrintBaseStep.clickButonRefund();
            dataAvailableSeller = refundOrderPrintBaseStep.calculateAmountAvailable(blockNameSeller);
            dataAvailableBuyer = refundOrderPrintBaseStep.calculateAmountAvailable(blockNameBuyer);

            verifyDataAfterRefund(dataAvailableBuyer, dataAfterRefundBuyer);
            verifyDataAfterRefund(dataAfterRefundSeller, dataAvailableSeller);
        }
    }

    public void verifyDataAfterRefund(HashMap<String, Float> act, HashMap<String, Float> exp) {
        for (String label : act.keySet()) {
            if(!"Refund taxes".equals(label)) {
                assertThat(act.get(label)).isEqualTo(exp.get(label));
            }
        }
    }

    @And("verify order detail after refund with status is {string}")
    public void verifyRefundOrderOnSbase(String status) {
        refundOrderPrintBaseStep.clickOrder(orderNumber);

        float refundSellingPriceBuyer = dataCancel.get(blockNameBuyer + " - Refund selling price");
        float refundShippingFeeBuyer = dataCancel.get(blockNameBuyer + " - Refund shipping fee");
        float recoverPaymentFeeBuyer = dataCancel.get(blockNameBuyer + " - Recover payment fee");
        float taxBuyer = dataCancel.get(blockNameBuyer + " - Refund taxes");

        float refundBuyer = refundSellingPriceBuyer + refundShippingFeeBuyer - recoverPaymentFeeBuyer + taxBuyer;

        float refundBaseCostSeller = dataCancel.get(blockNameSeller + " - Refund base cost");
        float refundShippingFeeSeller = dataCancel.get(blockNameSeller + " - Refund shipping fee");
        float refundProcessingFeeSeller = dataCancel.get(blockNameSeller + " - Refund processing fee");
        float refundPaymentFeeSeller = dataCancel.get(blockNameSeller + " - Refund payment fee");

        float in = refundBaseCostSeller + refundShippingFeeSeller + refundProcessingFeeSeller + refundPaymentFeeSeller;
        float out = refundSellingPriceBuyer;
        float profit = currentProfit + in;
        if(withdrawSellerBalance) {
            out = dataCancel.get(blockNameBuyer + " - Refund taxes");
            profit += out;
        } else {
            profit -= out;
        }

        refundOrderPrintBaseStep.verifyStatusOrderDetail(status);
        refundOrderPrintBaseStep.verifyOrderName(orderNumber);
        if(!choiceQuantity) {
            refundOrderPrintBaseStep.verifyRefundQuantity(quantityOrder);
        }
        refundOrderPrintBaseStep.verifyNetPayment();

        float baseCostAfterRefund = dataAfterRefundSeller.get("Refund base cost");
        float processingFeeAfterRefund = dataAfterRefundSeller.get("Refund processing fee");
        float paymentFeeAfterRefund = dataAfterRefundSeller.get("Refund payment fee");
        float handlingFee = processingFeeAfterRefund + paymentFeeAfterRefund;
        refundOrderPrintBaseStep.showCalculation();
        refundOrderPrintBaseStep.verifyValueForLabel("Basecost", baseCostAfterRefund);
        refundOrderPrintBaseStep.verifyValueForLabel("Handling fee", round(handlingFee));
        refundOrderPrintBaseStep.clickDropDown();
        refundOrderPrintBaseStep.verifyValueForLabel("Payment fee", paymentFeeAfterRefund);
        refundOrderPrintBaseStep.verifyValueForLabel("Processing fee", processingFeeAfterRefund);
        refundOrderPrintBaseStep.verifyValueForLabel("Refunded", out);

        refundOrderPrintBaseStep.verifyRefundTimeLine(round(refundBuyer));
        refundOrderPrintBaseStep.verifyRefundBuyer(round(round(refundBuyer)));
        refundOrderPrintBaseStep.verifyProfit("Profit", round(profit));
        refundOrderPrintBaseStep.moveToInvoiceDetail(orderNumber, status);
        refundOrderPrintBaseStep.verifyInvoiceHistory(round(in), out, withdrawSellerBalance);
    }


    @Then("Search and verify payment status is {string} on order list")
    public void searchAndVerifyPaymentStatusIsOnOrderList(String status) {
        refundOrderPrintBaseStep.searchOrder(orderNumber);
        refundOrderPrintBaseStep.verifyStatusOrderList(orderNumber, status);
        currentProfit = refundOrderPrintBaseStep.getProfitCurrent("PROFIT", orderNumber);
    }

    @And("move to {string} screen")
    public void moveToScreen(String nameScreen) {
        refundOrderPrintBaseStep.clickToSection(nameScreen);
    }

    @When("user navigate {string} screen")
    public void userNavigateScreen(String tabName) {
        refundOrderPrintBaseStep.userNavigateScreen(tabName);
    }

    private float round(float value) {
        return (float) Math.round(value * 100) / 100;
    }

    @Then("verify content buyer's email with")
    public void verifyContentBuyerSEmailWith(List<List<String>> dataTable) {
        for (int row: SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "email");
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            refundOrderPrintBaseStep.verifyReceivedEmail(email, subject);
        }
    }


}
