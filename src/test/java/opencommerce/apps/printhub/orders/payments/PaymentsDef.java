package opencommerce.apps.printhub.orders.payments;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.ManageOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.payments.PaymentsSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static opencommerce.apps.printhub.orders.ManageOrdersDef.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsDef {
    String accessToken = "";
    int countPaymentsLog;
    float currentAmount;
    int countOrder;
    @Steps
    PaymentsSteps paymentsSteps;
    @Steps
    OrderSteps orderSteps;

    @When("^verify data of payment history table as \"([^\"]*)\"$")
    public void verify_data_of_payment_history_table_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String outstandingBalance = SessionData.getDataTbVal(dataTable, row, "Outstanding balance");
            String nextPayment = SessionData.getDataTbVal(dataTable, row, "Next payment");
            String s_Enable = SessionData.getDataTbVal(dataTable, row, "Is enable Pay now");
            boolean isEnable = false;
            if (s_Enable.equalsIgnoreCase("true"))
                isEnable = true;
            Float actualNextPayment = paymentsSteps.getNextPayment();
            if (outstandingBalance.contains("0.00")) {
                paymentsSteps.verifyOutStandingBalance(Float.parseFloat(outstandingBalance));
            }else {
                paymentsSteps.verifyOutStandingBalance(orderSteps.roundTwoDecimalPlaces(totalCostOrderPhub));
            }
            if (nextPayment.contains("0.00")) {
                assertThat(Float.parseFloat(nextPayment)).isEqualTo(actualNextPayment);
            }else {
                assertThat(orderSteps.roundTwoDecimalPlaces(totalCostOrderPhub)).isEqualTo(actualNextPayment);
            }
            paymentsSteps.verifyEnablePayNowbtn(isEnable);
        }
    }

    @And("^call api to create next payment or charge payment Print Hub$")
    public void callApiToCreateNextPaymentOrChargePayment() {
        String shop_name = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        if (accessToken.isEmpty())
            accessToken = paymentsSteps.getAccessTokenShopBase();
        paymentsSteps.createPayment(shopId, shop_name, accessToken);
    }

    @And("^count payment log Print Hub$")
    public void countPaymentLogPrintHub() {
        countPaymentsLog = paymentsSteps.countPaymentLog();
    }

    @And("^verify data of payment log as \"([^\"]*)\"$")
    public void verifyDataOfPaymentLogAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String details = SessionData.getDataTbVal(dataTable, row, "Details");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            paymentsSteps.verifyAmountColumn(orderSteps.roundTwoDecimalPlaces(totalCostOrderPhub), 1);
            paymentsSteps.switchToFirstPaymentLogPage();
            if (!details.isEmpty())
                paymentsSteps.verifyDetailsColumn(details);
            if (!status.isEmpty())
                paymentsSteps.verifyStatusColumn(status, 1);
        }

    }

    @And("^verify list order next payment$")
    public void verifyListOrderPayment() {
        paymentsSteps.viewNextPayment();
        paymentsSteps.clickOpenListOrder();
        countOrder = paymentsSteps.getListOrderAndVerifyOrder(orderNumber);
        paymentsSteps.clickBreadcrum();

    }

    @And("^verify updated payments log Print Hub$")
    public void verifyUpdatedPaymentsLogPrintHub() {
        int actualPaymentLog = paymentsSteps.countPaymentLog();
        Assert.assertEquals(countPaymentsLog + 1, actualPaymentLog);
    }

    @And("^verify data of payment details as \"([^\"]*)\"$")
    public void verifyDataOfPaymentDetailsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String freeType = SessionData.getDataTbVal(dataTable, row, "Free type");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amounts");
            String status = SessionData.getDataTbVal(dataTable, row, "Status of Timeline");
            String amountTimeline = SessionData.getDataTbVal(dataTable, row, "Amount of Timeline");
            String details = SessionData.getDataTbVal(dataTable, row, "Details");
            String paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            paymentsSteps.verifyFreeType(freeType);
            paymentsSteps.verifyDetails(details);
            paymentsSteps.verifyAmount(orderSteps.roundTwoDecimalPlaces(totalCostOrderPhub));
            paymentsSteps.verifyStatus(status);
            paymentsSteps.verifyAmountAtTimeline(orderSteps.roundTwoDecimalPlaces(totalCostOrderPhub));
            paymentsSteps.verifyPaymentMethod(paymentMethod);
        }
    }

    @And("^view detail payment of next payment Print Hub$")
    public void viewNextPaymentPrintHub() {
        paymentsSteps.viewNextPayment();
    }

    @And("^redirect to payment details page Print Hub$")
    public void redirectToPaymentDetailsPagePrintHub() {
        paymentsSteps.openDetailPayment(1);
    }

    @And("^paid payment at detail payment Print Hub$")
    public void paidPaymentAtDetailPaymentPrintHub() {
        paymentsSteps.clickPayNowbtn();
        paymentsSteps.clickCanCelBtn();
        paymentsSteps.clickPayNowbtn();
        paymentsSteps.clickConfirmPaymentBtn();
        paymentsSteps.verifyProcessSuccess();
    }

    @And("^redirect from payment details to payments page Print Hub$")
    public void redirectFromPaymentDetailsToPaymentsPagePrintHub() {
        paymentsSteps.clickBreadcrum();
    }


    @And("^change status ShopBase Balance as \"([^\"]*)\"$")
    public void changeStatusShopBaseBalanceAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String mess = SessionData.getDataTbVal(dataTable, row, "Message");
            switch (status) {
                case "Activate":
                    paymentsSteps.activateShopBaseBalance(mess);
                    break;
                case "Deactivate":
                    paymentsSteps.disableShopBaseBalance(mess);
                    break;
            }

        }
    }

    @And("^verify UI after change status as \"([^\"]*)\"$")
    public void verifyUIAfterChangeStatusAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean statusNextpayment = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is enable Next paymenet"));
            boolean statusPaynow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is enable Pay now"));
            float nextPayment = paymentsSteps.getNextPayment();
            float outStandingBalance = Float.parseFloat(paymentsSteps.getOutStandingBalance());
            if (nextPayment != 0) {
                paymentsSteps.verifyEnableNextPayment(statusNextpayment);
                paymentsSteps.clickBreadcrum();
            }
            if (outStandingBalance != 0) {
                paymentsSteps.verifyEnablePayNowbtn(statusPaynow);
            }
        }
    }

    @And("call api to \"([^\"]*)\" payment method in Phub")
    public void callApiToPaymentMethod(String method) {
        String shopId = LoadObject.getProperty("shopId");
        if (accessToken.isEmpty())
            accessToken = paymentsSteps.getAccessTokenShopBase();
        paymentsSteps.createPaymentMethod(shopId, method, accessToken);

    }

    @And("get information detail in Payments page Phub")
    public void getInformationDetailInPaymentsPagePhub() {
        countPaymentsLog = paymentsSteps.countPaymentLog();
        currentAmount = paymentsSteps.getCurrentAmount();
    }


    @And("verify current amount after charge success as {string}")
    public void verifyCurrentAmountAfterChargeSuccessAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String s_amount = SessionData.getDataTbVal(dataTable, row, "Amounts");
            Float amount = 0.00f;
            Float currentAmountActual = paymentsSteps.getCurrentAmount();
            if (!s_amount.isEmpty()) {
                amount = Float.parseFloat(s_amount);
            }
            Float currentAmountEx = currentAmount - amount;
            Assert.assertEquals(currentAmountEx, currentAmountActual);
        }
    }


    @And("^call api to create next payment or charge payment Print Hub for (import order|import order for fistshop)$")
    public void callApiToCreateNextPaymentOrChargePaymentPrintHubForImportOrder(String type) {
        String shop_name = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        String username = LoadObject.getProperty("user.name");
        String password = LoadObject.getProperty("user.pwd");
        if(type.contains("fistshop")) {
            shop_name = LoadObject.getProperty("firstShop");
            shopId = LoadObject.getProperty("firstshop_id");
        }
        if (accessToken.isEmpty())
            accessToken = paymentsSteps.getAccessTokenShopBaseByShop(shop_name, username, password);
        paymentsSteps.createPaymentOrderImprort(shopId, shop_name, accessToken);
    }
}
