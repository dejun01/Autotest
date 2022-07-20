package opencommerce.fulfillment_service.claim;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ClaimDetailSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ClaimListSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;

public class ClaimDetailDef {

    @Steps
    ClaimDetailSteps claimDetailSteps;
    @Steps
    ClaimListSteps claimListSteps;

    @Given("verify data of order in new claim")
    public void verify_data_of_order_in_new_claim(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            String preferredSolution = SessionData.getDataTbVal(dataTable, row, "Preferred solution");
            String shippingInfor = SessionData.getDataTbVal(dataTable, row, "Shipping infor");
            String productVariant = SessionData.getDataTbVal(dataTable, row, "Product name>Variant");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String tkn = SessionData.getDataTbVal(dataTable, row, "Tracking number");
            String reason = SessionData.getDataTbVal(dataTable, row, "Reason for claim");
            String note = SessionData.getDataTbVal(dataTable, row, "Note");
            if (!preferredSolution.isEmpty()) {
                claimDetailSteps.verifyDefaultPreferredSolution(preferredSolution);
            }
            String[] listItem = productVariant.split(">");
            int index = claimDetailSteps.getIndexLineItemClaim(listItem[0], listItem[1]);
            if (isShow) {
                claimDetailSteps.verifyProductNameClaimDetail(listItem[0], index);
                claimDetailSteps.verifyVariantNameClaimDetail(listItem[1], index);
            } else {
                claimDetailSteps.verifyProductVariantNotShowClaimDetail(listItem[0], listItem[1]);
            }
            if (!shippingInfor.isEmpty()) {
                claimDetailSteps.verifyShippingInforClaimDetail(shippingInfor);
            }
            if (!quantity.isEmpty()) {
                claimDetailSteps.verifyDefaultQuantityLineItemClaimDetail(quantity, index);
            }
            if (!status.isEmpty()) {
                claimDetailSteps.verifyStatusLineitemInClaim(status, index);
            }
            if (!tkn.isEmpty()) {
                tkn = trackingNumber;
                claimDetailSteps.verifyTrackingNumberClaimDetail(tkn, index);
            }
            if (!note.isEmpty()) {
                claimDetailSteps.verifyNoteValue(note, index);
            }
            if (!reason.isEmpty()) {
                claimDetailSteps.verifySelectAReason(reason, index);
            }
        }
    }

    @Given("create new a claim then submit your claim")
    public void create_new_a_claim_then_submit_your_claim(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String preferredSolution = SessionData.getDataTbVal(dataTable, row, "Preferred solution");
            boolean checkLineitem = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Check lineitem"));
            String productVariant = SessionData.getDataTbVal(dataTable, row, "Product name>Variant");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String reason = SessionData.getDataTbVal(dataTable, row, "Reason for claim");
            String evidence = SessionData.getDataTbVal(dataTable, row, "Claim evidence");
            boolean isDisable = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Disable Submit your claim"));
            String result = SessionData.getDataTbVal(dataTable, row, "Result");

            String[] listItem = productVariant.split(">");
            int index = claimDetailSteps.getIndexLineItemClaim(listItem[0], listItem[1]);
            if (!checkLineitem) {
                claimDetailSteps.unCheckLineItemClaim(index);
//                claimDetailSteps.unCheckLineItemClaims();
            }
            if (!preferredSolution.isEmpty()) {
                claimDetailSteps.selectPreferredSolution(preferredSolution);
            }
            if (!quantity.isEmpty()) {
                claimDetailSteps.inputQuantityLineItemClaim(quantity, index);
            }

            if (!reason.isEmpty()) {
                claimDetailSteps.selectReasonLineItemClaim(reason, index);
            }
            if (!evidence.isEmpty()) {
                claimDetailSteps.uploadEvidenceLineItemClaim(evidence, index);
            }
            claimDetailSteps.verifyDisableSubmitClaim(isDisable);
            if (!isDisable) {
                claimDetailSteps.submitYourClaim();
            }
            if (result.contains("Success")) {
                ClaimListDef.claimNew = claimListSteps.getClaimInFirst();
                ClaimListDef.claimID = claimListSteps.getClaimID(ClaimListDef.claimNew);
            }
        }
    }

    @Given("verify message after submit your claim {string}")
    public void verify_message_after_submit_your_claim(String noti) {
        claimDetailSteps.verifyNotiAfterSubmitClaim(noti);
    }

    @Then("verify Preferred solution {string} claim")
    public void verifyPreferredSolutionClaim(String preferredSolution) {
        claimDetailSteps.verifyDefaultPreferredSolution(preferredSolution);
    }
}
