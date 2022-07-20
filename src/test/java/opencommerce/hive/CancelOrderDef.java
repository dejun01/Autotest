package opencommerce.hive;

import com.opencommerce.shopbase.Pbase.steps.CancelOrderStep;
import com.opencommerce.shopbase.Pbase.steps.RefundOrderPrintBaseStep;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.dataCancel;
import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class CancelOrderDef {
    @Steps
    CancelOrderStep CancelOrderStep;

    @Steps
    RefundOrderPrintBaseStep refundOrderStep;
    float currentProfit = 0;
    int quantityOrder = 0;
    HashMap<String, Float> dataAfterCancelBuyer = new HashMap<>();
    HashMap<String, Float> dataAfterCancelSeller = new HashMap<>();
    HashMap<String, Float> dataAvailableBuyer = new HashMap<>();
    HashMap<String, Float> dataAvailableSeller = new HashMap<>();
    String blockNameBuyer = "Request to cancel for buyer";
    String blockNameSeller = "Request to cancel for seller";
    boolean withdrawSellerBalance = false;
    boolean sendMail = false;
    boolean discountFreeShip = false;
    float discountAmount = 0;
    boolean flagQuantity = true;

    @Then("verify payment status is {string} on order list")
    public void verifyPaymentStatusIsOnOrderList(String status) {
        CancelOrderStep.verifyStatusOrderList(orderNumber, status);
        currentProfit = CancelOrderStep.getProfitCurrent("PROFIT", orderNumber);
    }

    @Given("view order detail on hive")
    public void viewOrderDetailOnHive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String status = SessionData.getDataTbVal(dataTable, row, "Payment status");
            String buttonName = SessionData.getDataTbVal(dataTable, row, "Button name");
            if("Paid".equals(status)) {
                refundOrderStep.clickButtonApprove("Approve");
                if("Cancel".equals(buttonName)) {
                    flagQuantity = false;
                }
            }
            refundOrderStep.clickButton(orderNumber, buttonName);
        }
    }

    @And("cancel order on hive pbase")
    public void qcancelOrderOnHivePbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            withdrawSellerBalance = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Withdraw seller balance"));
            sendMail = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Send mail"));

            quantityOrder = Integer.parseInt(quantity);
            refundOrderStep.verifyOrderDetailRefund(orderNumber, quantity, flagQuantity);
            if(flagQuantity) {
                refundOrderStep.choiceQuantity(quantity);
            }
            refundOrderStep.fillDataRefundOrder(withdrawSellerBalance, sendMail);
//            value expect rest after refund
            dataAfterCancelSeller = refundOrderStep.calculateAmountAfterRefund(blockNameSeller);
            dataAfterCancelBuyer = refundOrderStep.calculateAmountAfterRefund(blockNameBuyer);

            CancelOrderStep.clickButonCancel("cancel");
//            value Actual rest after refund
            dataAvailableSeller = refundOrderStep.calculateAmountAvailable(blockNameSeller);
            dataAvailableBuyer = refundOrderStep.calculateAmountAvailable(blockNameBuyer);

            verifyDataAfterRefund(dataAvailableBuyer, dataAfterCancelBuyer);
            verifyDataAfterRefund(dataAfterCancelSeller, dataAvailableSeller);
        }
    }

    public void verifyDataAfterRefund(HashMap<String, Float> act, HashMap<String, Float> exp) {
        for (String label : act.keySet()) {
            if(!"Refund taxes".equals(label)) {
                assertThat(act.get(label)).isEqualTo(exp.get(label));
            }
        }
    }

    @And("verify order detail after cancel with status is {string}")
    public void verifyOrderDetailAfterCancelWithStatusIs(String status) {
        CancelOrderStep.clickOrder(orderNumber);
        float sellingPrice = dataCancel.get(blockNameBuyer + " - Refund selling price");
        float shippingFeeBuyer = dataCancel.get(blockNameBuyer + " - Refund shipping fee");
        float recoverPaymentFee = dataCancel.get(blockNameBuyer + " - Recover payment fee");
        float taxBuyer = dataCancel.get(blockNameBuyer + " - Refund taxes");
        float refundBuyer = 0;
        if(!"Voided".equals(status)) {
            refundBuyer = sellingPrice + shippingFeeBuyer - recoverPaymentFee +taxBuyer;
        }

        float baseCost = dataCancel.get(blockNameSeller + " - Refund base cost");
        float shippingFeeSeller = dataCancel.get(blockNameSeller + " - Refund shipping fee");
        float processingFee = dataCancel.get(blockNameSeller + " - Refund processing fee");
        float paymentFee = dataCancel.get(blockNameSeller + " - Refund payment fee");
        float in = baseCost + shippingFeeSeller + processingFee + paymentFee;

        float out = sellingPrice;
        float profit = currentProfit + in;
        if(withdrawSellerBalance) {
            out = dataCancel.get(blockNameBuyer + " - Refund taxes");
            profit += out;
        } else {
            profit -= out;
        }


        refundOrderStep.verifyStatusOrderDetail(status);
        refundOrderStep.verifyOrderName(orderNumber);
        refundOrderStep.verifyRefundQuantity(quantityOrder);
        refundOrderStep.verifyNetPayment();
        refundOrderStep.verifyRefundBuyer(round(refundBuyer));

        float baseCostAfterRefund = dataAfterCancelSeller.get("Refund base cost");
        float processingFeeAfterRefund = dataAfterCancelSeller.get("Refund processing fee");
        float paymentFeeAfterRefund = dataAfterCancelSeller.get("Refund payment fee");
        float handlingFee = processingFeeAfterRefund + paymentFeeAfterRefund;
        refundOrderStep.showCalculation();
        if(discountFreeShip) {
            float expDiscount = discountAmount - shippingFeeSeller;
            CancelOrderStep.verifyDiscount("Discount", expDiscount);
        }
        refundOrderStep.verifyValueForLabel("Basecost", baseCostAfterRefund);
        refundOrderStep.verifyValueForLabel("Handling fee", round(handlingFee));
        refundOrderStep.clickDropDown();
        refundOrderStep.verifyValueForLabel("Payment fee", paymentFeeAfterRefund);
        refundOrderStep.verifyValueForLabel("Processing fee", processingFeeAfterRefund);
        refundOrderStep.verifyValueForLabel("Refunded", out);

        CancelOrderStep.verifyCancelTimeLine(round(refundBuyer));
        refundOrderStep.verifyProfit("Profit", round(profit));
        refundOrderStep.moveToInvoiceDetail(orderNumber, status);
        refundOrderStep.verifyInvoiceHistory(round(in), out, withdrawSellerBalance);
    }

    private float round(float value) {
        return (float) Math.round(value * 100) / 100;
    }

    @And("verify info order detail with discount = {string}")
    public void verifyInfoOrderDetailWithDiscount(String discountCode) {
        if("free ship".equals(discountCode)) {
            discountFreeShip = true;
            discountAmount = CancelOrderStep.getDiscountValue("Discount");
        }
    }

    @Given("verify email send to buyer with")
    public void verifyEmailSendToBuyerWithSubject(List<List<String>> dataTable) {
        for(int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            subject = subject.contains("@order name@") ? subject.replace("@order name@", orderNumber) : subject;
            CancelOrderStep.verifySendMail(subject, email);
        }
    }

    @And("^Navigate to order detail on hive-pbase$")
    public void navigateToOrderDetailOnHivePbase() {
        refundOrderStep.clickOrder(orderNumber);
    }
}
