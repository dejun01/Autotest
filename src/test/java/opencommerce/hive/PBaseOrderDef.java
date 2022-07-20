package opencommerce.hive;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import com.opencommerce.shopbase.hive.pbaseorder.steps.TierSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;


import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep.currentVersion;
import static org.junit.Assert.assertThat;

public class PBaseOrderDef {

    @Steps
    PBaseOrderStep pBaseOrderStep;
    @Steps
    TierSteps tierSteps;
    @Steps
    HiveStep hiveSteps;
    @Steps
    CampaignSteps campaignSteps;

    String email = LoadObject.getProperty("emailHive");
    String pwd = LoadObject.getProperty("pwdHive");
    String linkhive = LoadObject.getProperty("hive");
    String userId = LoadObject.getProperty("userId");
    String userNameBeeketing = LoadObject.getProperty("emailbeeketing");
    String userPwdBeeketing = LoadObject.getProperty("pwdbeeketing");
    String hivePBase = LoadObject.getProperty("hivePBase");


    @Then("^login to hive-pbase$")
    public void login_to_hive_pbase() {
        pBaseOrderStep.openHivePbase(linkhive);
        pBaseOrderStep.signInHivePbase(email, pwd);
    }

    @Then("redirect to order detail on hive-pbase")
    public void redirect_to_order_detail_on_hive_pbase() {
        int orderId = OrderVariable.orderId;
        pBaseOrderStep.navigateToPBase(orderId);
    }

    @Then("^approved order pod in hive-pbase$")
    public void approved_order_pod_in_hive_pbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            int orderId = OrderVariable.orderId;
            if (type.equals("Pbase")) {
                pBaseOrderStep.navigateToPBase(orderId);
            }
            pBaseOrderStep.approvedOrder();
        }
    }

    @Given("refund order pod on hive-pbase")
    public void refund_order_pod_on_hive_pbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String refundLineitem = SessionData.getDataTbVal(dataTable, row, "Line item Name>Quantity");
            boolean refundBuyer = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Buyer"));
            boolean refundSeller = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Seller"));
            int orderId = OrderVariable.orderId;
            pBaseOrderStep.navigateToPBase(orderId);
            pBaseOrderStep.clickBtnRefundOrderPBase();
            pBaseOrderStep.inputRefundOrder(refundLineitem);
            if (refundSeller) {
                pBaseOrderStep.checkRefundforSeller();
            }
            if (refundBuyer) {
                pBaseOrderStep.checkRefundforBuyer();
            }
            pBaseOrderStep.refundOrderPBase();

        }
    }

    @Given("cancel order on hive-pbase")
    public void cancel_order_on_hive_pbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            Boolean refundBuyer = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Buyer"));
            Boolean refundSeller = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Seller"));
            int orderId = OrderVariable.orderId;
            pBaseOrderStep.navigateToPBase(orderId);
            pBaseOrderStep.clickBtnCancelOrderPBase();
            if (refundSeller) {
                pBaseOrderStep.checkRefundforSeller();
            }
            if (refundBuyer) {
                pBaseOrderStep.checkRefundforBuyer();
            }
            pBaseOrderStep.cancelOrderPBase();
        }
    }

    @Given("manual fulfill order pod in hive-pbase")
    public void manual_fulfill_order_pod_in_hive_pbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order name");
            String fulfillLineitem = SessionData.getDataTbVal(dataTable, row, "Line item Name>Quantity");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String trackingUrl = SessionData.getDataTbVal(dataTable, row, "Tracking url");
            int orderId = OrderVariable.orderId;
            pBaseOrderStep.navigateToPBase(orderId);
            pBaseOrderStep.clickBtnManualFulfillPBase();
            pBaseOrderStep.inputManualFulfillOrder(fulfillLineitem);
            pBaseOrderStep.inputTrackingNumberPod(trackingNumber);
            pBaseOrderStep.inputTrackingUrlPod(trackingUrl);
            pBaseOrderStep.markAsFulfillPod();
        }
    }


    @Then("change bcoin and available bcoin of user in hive")
    public void changeBcoinAndAvailableBcoinOfUseInHive(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");

            HashMap<String, String> pre = new HashMap<>();
            HashMap<String, String> cur = new HashMap<>();
            pre = tierSteps.getTierInformationOfUserByApi();
            System.out.println("Pre: " + pre);
            pBaseOrderStep.navigateToEditUserPage(linkhive, userId);
            pBaseOrderStep.selectAction(type, action, quantity);
            pBaseOrderStep.clickBtnUpdate();
            cur = tierSteps.getTierInformationOfUserByApi();
            System.out.println("Current: " + cur);
            pBaseOrderStep.verifyBcoinAndAvailableBcoin(pre, cur, type, action, quantity);
            HashMap<String, List<String>> tierInfo = tierSteps.getTierListTierApi();
            System.out.println("Tier: " + tierInfo);
            tierSteps.verifyTierLevel(tierInfo, pre, cur);
        }
    }

    @Given("login to hive pbase by google")
    public void loginToHiveByGoogle() {
        pBaseOrderStep.openHivePbase(hivePBase);
        hiveSteps.loginWithGG();
        hiveSteps.emailGG(userNameBeeketing);
        hiveSteps.PassEmailGG(userPwdBeeketing);
        hiveSteps.waitABit(5000);

    }

    @Given("login to hive sbase by google")
    public void loginToHiveSbaseByGoogle() {
        pBaseOrderStep.openHivePbase(linkhive);
        hiveSteps.loginWithGG();
        hiveSteps.emailGG(userNameBeeketing);
        hiveSteps.PassEmailGG(userPwdBeeketing);
        hiveSteps.waitABit(5000);
    }

    @Given("login to hive sbase")
    public void loginToHiveShopBase() {
        pBaseOrderStep.openHivePbase(linkhive);
        pBaseOrderStep.signInHivePbase(email, pwd);
    }

    @Then("login to hive pbase")
    public void loginToHivePbase() {
        pBaseOrderStep.openHivePbase(hivePBase);
        pBaseOrderStep.signInHivePbase(email, pwd);
    }

    @And("get orderID")
    public void getOrderID() {
        pBaseOrderStep.getOrderID();
    }

    @Then("get current version")
    public void getVersionOrder() {
        pBaseOrderStep.navigateToOrderDetail();
        pBaseOrderStep.getVersionOrder();
        pBaseOrderStep.verifyVersion();
    }

    @And("verify version camp")
    public void verifyCampVersion() {
        pBaseOrderStep.navigateToOrderDetail();
        pBaseOrderStep.verifyCampVersion();
    }

    @Then("^redirect to order (Phub|Pbase) detail on hive-pbase$")
    public void redirect_order_detail_on_hive_pbase(String typeOrder) {
        int orderId = OrderVariable.orderId;
        pBaseOrderStep.navigateToOrderDetailInHive(typeOrder, orderId);
    }

    @And("verify version campaign in order is {string}")
    public void verifyVersionCampaignInOrderIs(String ver) throws Exception {
        Integer verActual = pBaseOrderStep.getVersionInOrder();
        Integer verEx = Integer.parseInt(ver);
        Assertions.assertThat(verActual).isEqualTo(verEx);
    }
}