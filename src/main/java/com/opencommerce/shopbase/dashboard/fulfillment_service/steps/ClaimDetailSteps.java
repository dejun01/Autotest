package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.ClaimDetailPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimDetailSteps extends ScenarioSteps {

    ClaimDetailPage claimDetailPage;

    @Step
    public void verifyDefaultPreferredSolution(String preferredSolution) {
        claimDetailPage.verifyDefaultPreferredSolution(preferredSolution);
    }

    @Step
    public int getIndexLineItemClaim(String productName, String variantName) {
        int index = claimDetailPage.getIndexLineItemClaim(productName, variantName);
        return index;
    }

    @Step
    public void verifyProductNameClaimDetail(String productName, int index) {
        String act = claimDetailPage.getProductNameClaimDetail(index);
        assertThat(act).isEqualTo(productName);
    }

    @Step
    public void verifyVariantNameClaimDetail(String variantName, int index) {
        String act = claimDetailPage.getVariantNameClaimDetail(index);
        assertThat(act).isEqualTo(variantName);
    }

    @Step
    public void verifyProductVariantNotShowClaimDetail(String productName, String variantName) {
        claimDetailPage.verifyProductVariantNotShow(productName, variantName);
    }

    @Step
    public void verifyShippingInforClaimDetail(String shippingInfor) {
        String act = claimDetailPage.getShippingInforClaimDetail();
        assertThat(act).isEqualTo(shippingInfor);
    }

    @Step
    public void verifyDefaultQuantityLineItemClaimDetail(String quantity, int index) {
        String act = claimDetailPage.getDefaultQuantityClaimDetail(index);
        assertThat(act).isEqualTo(quantity);
    }

    @Step
    public void verifyStatusLineitemInClaim(String status, int index) {
        String act = claimDetailPage.getStatusLineItemClaimDetail(index);
        assertThat(act).isEqualTo(status);
    }

    @Step
    public void verifyTrackingNumberClaimDetail(String tkn, int index) {
        String act = claimDetailPage.getTrackingNumberClaimDetail(index);
        assertThat(tkn).contains(act);
    }

    @Step
    public void verifySelectAReason(String reason, int index) {
        String act = claimDetailPage.getReasonForClaim(index);
        assertThat(act).isEqualTo(reason);
    }

    @Step
    public void unCheckLineItemClaim(int index) {
        claimDetailPage.unCheckLineItemClaim(index);
    }

    @Step
    public void selectPreferredSolution(String preferredSolution) {
        claimDetailPage.selectPreferredSolution(preferredSolution);
    }

    @Step
    public void inputQuantityLineItemClaim(String quantity, int index) {
        claimDetailPage.inputQuantityLineItemClaim(quantity, index);
    }

    @Step
    public void selectReasonLineItemClaim(String reason, int index) {
        claimDetailPage.clickSelectReason(index);
        claimDetailPage.chooseReason(index, reason);
    }

    @Step
    public void uploadEvidenceLineItemClaim(String evidence, int index) {
        if (evidence.contains(">")) {
            String[] type = evidence.split(">");
            switch (type[0]) {
                case "Choose file":
                    claimDetailPage.uploadEvidenceLineItemClaim(type[1], index);
                    break;
                case "Input note":
                    claimDetailPage.inputNoteClaim(type[1], index);
            }
        }
    }

    @Step
    public void verifyDisableSubmitClaim(boolean isDisable) {
        claimDetailPage.verifyDisableSubmitClaim(isDisable);
    }

    @Step
    public void submitYourClaim() {
        claimDetailPage.clickBtn("Submit your claim");
    }

    public void verifyNotiAfterSubmitClaim(String noti) {
        claimDetailPage.verifyNotiAfterSubmitClaim(noti);
    }

    public void verifyNoteValue(String note, int index) {
        String act = claimDetailPage.getNoteValue(index);
        assertThat(act).isEqualTo(note);
    }

    @Step
    public void unCheckLineItemClaims() {
        claimDetailPage.unCheckLineItemClaims();
    }
}
