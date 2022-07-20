package opencommerce.hive_sbase;

import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ClaimDetailSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.hive_sbase.steps.ClaimHiveSteps;
import common.CommonPageObject;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.balance.BalanceDef;
import opencommerce.fulfillment_service.claim.ClaimListDef;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class ClaimHiveDef {

    @Steps
    OrderSteps orderSteps;

    @Steps
    BalanceSteps balanceSteps;
    @Steps
    ClaimHiveSteps claimHiveSteps;
    @Steps
    ClaimDetailSteps claimDetailSteps;

    String linkhive = LoadObject.getProperty("hive");
    String email = LoadObject.getProperty("emailHive");
    String pwd = LoadObject.getProperty("pwdHive");
    String shopDomain = LoadObject.getProperty("shop");
    String ownerEmail = LoadObject.getProperty("user.name");
    String url = LoadObject.getProperty("url");


    String createdAt = DateTimeUtil.getDateFormat();
    String referredSolution = "";
    String statusName = "";
    String product = "";
    String refund_amount = "";

    @Given("login to hive-crossPanda")
    public void loginToHiveCrossPanda() {
        claimHiveSteps.openHiveCrossPanda(linkhive);
        claimHiveSteps.signInHiveCrossPanda(email, pwd);
    }

    @And("Search Claim and verify claim in claim list page in hive sbase")
    public void verifyClaimOnSbase(List<List<String>> dataTable) {
        claimHiveSteps.searchByClaimID(ClaimListDef.claimID);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            ClaimListDef.claimID = SessionData.getDataTbVal(dataTable, row, "Claim");
            referredSolution = SessionData.getDataTbVal(dataTable, row, "Referred solution");
            String created_at = SessionData.getDataTbVal(dataTable, row, "Created At");
            statusName = SessionData.getDataTbVal(dataTable, row, "Status Name");
            if (!created_at.isEmpty()) {
                claimHiveSteps.verifyClaim(ClaimListDef.claimID, ownerEmail, shopDomain, ClaimListDef.orderName, referredSolution, createdAt, statusName);

            }
        }
    }

    @And("Acc claim detail")
    public void accClaimDetail() {
        claimHiveSteps.clickClaimID(ClaimListDef.claimID);
    }

    @Then("Navigate claim hive detail")
    public void navigateClaimHiveDetail() {
        claimHiveSteps.navigateClaimHiveDetail(ClaimListDef.claimID);
    }

    @Then("Verify claim in claim detail in hive sbase")
    public void verifyClaimInClaimDetail(List<List<String>> dataTable) {
        claimHiveSteps.navigateClaimHiveDetail(ClaimListDef.claimID);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String orderName = SessionData.getDataTbVal(dataTable, row, "Order Name");
            String referredSolution = SessionData.getDataTbVal(dataTable, row, "Referred solution");
            product = SessionData.getDataTbVal(dataTable, row, "Product");
            String tracking_number = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String claim_type = SessionData.getDataTbVal(dataTable, row, "Claim type");
            String note = SessionData.getDataTbVal(dataTable, row, "Seller's note");
            String sku = SessionData.getDataTbVal(dataTable, row, "SBCN SKU");
            String quantityClaim = SessionData.getDataTbVal(dataTable, row, "Claim quantity");
            String quantityOrder = SessionData.getDataTbVal(dataTable, row, "Order quantity");
            String date = SessionData.getDataTbVal(dataTable, row, "Shipped date");

            String claimNameHive = claimHiveSteps.getTextName();
            assertThat(claimNameHive).isEqualTo(ClaimListDef.claimNew);

            String actOwnerEmail = claimHiveSteps.getOwnerEmai();
            assertThat(actOwnerEmail).isEqualTo(ownerEmail);

            String actDomain = claimHiveSteps.getDomain();
            assertThat(actDomain).isEqualTo(shopDomain);

            String actOrderName = claimHiveSteps.getOrderNumberDetail();
            if (orderName.contains("#order")) {
                orderName = orderNumber;
            }
            if (orderName.contains("@order")) {
                orderName = ClaimListDef.orderName;
            }
            assertThat(actOrderName).isEqualTo(orderName);

            String actReferredSolution = claimHiveSteps.getRerredSolution();
            assertThat(actReferredSolution).isEqualTo(referredSolution);

            if (!tracking_number.isEmpty()) {
                tracking_number = trackingNumber;
            }
            claimHiveSteps.verifyClaimDetail(product, sku, quantityOrder, date, tracking_number, quantityClaim, claim_type, note);
        }

    }

    @And("Change status claim in hive Sbase")
    public void changeStatusClaimInHiveSbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            refund_amount = SessionData.getDataTbVal(dataTable, row, "Refund Amount");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            claimHiveSteps.chooseAction(status);
            if (status.equals("Approved")) {
                if (!refund_amount.isEmpty()) {
                    claimHiveSteps.inputRefundAmount(refund_amount);
                }
                claimHiveSteps.clickBTUpdate();
                claimDetailSteps.waitABit(3000);
                claimHiveSteps.acceptNotiBrower();
            } else {
                claimHiveSteps.clickBTUpdate();
            }
            claimHiveSteps.verifyMesgUpdate(msg);
        }
    }

    @And("Change solution claim in hive Sbase")
    public void changeSolutionClaimInHiveSbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String solution = SessionData.getDataTbVal(dataTable, row, "Solution");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            claimHiveSteps.chooseSolutionAction(solution);
            claimHiveSteps.clickBTUpdate();
            claimDetailSteps.waitABit(3000);
            claimHiveSteps.verifyMesgUpdate(msg);
        }
    }

    @Then("Verify balance after approved")
    public void verifyBalanceAfterApproved() {
        Double balanceAfterApproved = Double.parseDouble(roundOff(BalanceDef.currentBalanceAmt + Double.parseDouble(refund_amount)));
        assertThat(balanceAfterApproved).isEqualTo(balanceSteps.getCurrentAvailableBalance());
    }

    private String roundOff(double v) {
        return String.format("%1.2f", v);
    }

    @When("click on tracking nummber")
    public void clickOnTrackingNummber() {
        claimHiveSteps.clickOnTrackingNumber(product);

    }

    @Then("Redirect to link track")
    public void redirectToLinkTrack(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link_track = SessionData.getDataTbVal(dataTable, row, "link track");
            String tracking_number = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            if (!tracking_number.isEmpty()) {
                claimHiveSteps.verifyLinkTrack(link_track, trackingNumber);
            }
        }
    }

    @Then("verify Preferred solution before approved")
    public void verifyPreferredSolutionBeforeApproved() {
        claimHiveSteps.verifyPreferredSolutionApproved(true);
    }

    @Then("verify Preferred solution after approved")
    public void verifyPreferredSolutionAfterApproved() {
        claimHiveSteps.verifyPreferredSolutionApproved(false);
    }

}


