package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import com.opencommerce.shopbase.hive_sbase.pages.ClaimHivePage;
import common.utilities.LoadObject;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimHiveSteps {
    ClaimHivePage claimHivePage;

    OrderPage orderPage;
    @Step
    public void openHiveCrossPanda(String linkhive) {
        claimHivePage.openUrl(linkhive);
        claimHivePage.maximizeWindow();
    }

    @Step
    public void signInHiveCrossPanda(String email, String pwd) {
        claimHivePage.enterInputFieldWithLabel("Username", email);
        claimHivePage.enterInputFieldWithLabel("Password", pwd);
        claimHivePage.Login();
        claimHivePage.verifyLoginSS();
    }

    @Step
    public void searchByClaimID(String claimID) {
        claimHivePage.enterClaimID("Claim ID");
        claimHivePage.searchByClaimID(claimID);


    }

    @Step
    public void verifyClaim(String claimID, String ownerEmail, String shopDomain, String orderNumber, String referredSolution, String createdAt, String statusName) {
        assertThat(claimHivePage.getClaimDetail(claimID, "Owner Email")).isEqualTo(ownerEmail);
        assertThat(claimHivePage.getClaimDetail(claimID, "Shop Domain")).isEqualTo(shopDomain.replace("/admin", ""));
        assertThat(claimHivePage.getClaimDetail(claimID, "Order Name")).isEqualTo(orderNumber);
        assertThat(claimHivePage.getClaimDetail(claimID, "Referred solution")).isEqualTo(referredSolution);
        assertThat(claimHivePage.getClaimDetail(claimID, "Created At")).contains(createdAt);
        assertThat(claimHivePage.getClaimDetail(claimID, "Status Name")).isEqualTo(statusName);
    }

    @Step
    public void clickClaimID(String claimID) {
        claimHivePage.clickClaimID(claimID);
    }

    @Step
    public String getTextName() {
        return claimHivePage.getTextInfoOrder("Name");
    }

    @Step
    public String getOwnerEmai() {
        return claimHivePage.getTextInfoOrder("Owner Email");
    }

    @Step
    public String getDomain() {
        return claimHivePage.getTextInfoOrder("Domain");
    }

    @Step
    public String getOrderNumberDetail() {
        return claimHivePage.getTextInfoOrder("Order Name");
    }

    @Step
    public String getRerredSolution() {
        return claimHivePage.getTextInfoOrder("Referred solution");
    }

    @Step
    public void searchByClaimIDSBase(String claimID) {
        claimHivePage.searchByClaimIDSBase(claimID);
    }

    @Step
    public void verifyInfoOrderINSbase(String claimID, String createdAt, String orderNumber, String preferredSolution, String status) {
        assertThat(claimHivePage.getTextCreatedAt(claimID)).isEqualTo(createdAt);
        assertThat(claimHivePage.getTextOrderNumber(claimID)).isEqualTo(orderNumber);
        assertThat(claimHivePage.getTextPreferredSolution(claimID)).isEqualTo(preferredSolution);
        assertThat(claimHivePage.getTextStatus(claimID)).isEqualTo(status);
    }

    @Step
    public void chooseAction(String status) {
        claimHivePage.chooseAction(status);
    }

    @Step
    public void verifyPreferredSolutionApproved(boolean Present) {
        claimHivePage.verifyPreferredSolutionApproved(Present);
    }

    @Step
    public void chooseSolutionAction(String solution) {
        claimHivePage.chooseSolutionAction(solution);
    }

    @Step
    public void clickBTUpdate() {
        claimHivePage.clickBtnUpdate();
    }

    @Step
    public void inputRefundAmount(String amount) {
        claimHivePage.inputRefundAmount(amount);
    }

    @Step
    public void verifyClaimDetail(String product, String sku, String quantityOrder, String date, String trackingNumber, String quantityClaim, String claim_type, String note) {
        if (!sku.isEmpty()) {
            assertThat(claimHivePage.getInfoClaimInListClaim(product, "SBCN SKU")).isEqualTo(sku);
        }
        assertThat(claimHivePage.getInfoClaimInListClaim(product, "Order quantity")).isEqualTo(quantityOrder);
        if (!date.isEmpty()) {
            assertThat(claimHivePage.getInfoClaimInListClaim(product, "Shipped date")).isEqualTo(date);
        }
        assertThat(trackingNumber).contains(claimHivePage.getInfoClaimInListClaim(product, "Tracking number"));
        assertThat(claimHivePage.getInfoClaimInListClaim(product, "Claim quantity")).isEqualTo(quantityClaim);
        assertThat(claimHivePage.getInfoClaimInListClaim(product, "Claim type")).isEqualTo(claim_type);
        assertThat(claimHivePage.getInfoClaimInListClaim(product, "note")).isEqualTo(note);
    }

    @Step
    public void verifyLinkTrack(String link, String trackingNumber) {
        String currentURL = claimHivePage.getCurrentUrl();
        assertThat(link + trackingNumber).contains(currentURL);

    }

    @Step
    public void clickOnTrackingNumber(String product) {
        claimHivePage.clickOnTrackingNumber(product);
        orderPage.switchToLatestTab();
        orderPage.waitForEverythingComplete();

    }

    @Step
    public void accToPage(String urlLink) {
        claimHivePage.navigateToUrl(urlLink);
    }

    public void navigateClaimHiveDetail(String claimID) {
        String url = LoadObject.getProperty("url_claim_detail") + claimID + "/edit";
        claimHivePage.navigateToUrl(url);
        orderPage.logInfor(url);
    }

    public void verifyMesgUpdate(String msg) {
        String actMsg = claimHivePage.getMsgUpdateClaim();
        assertThat(actMsg).contains(msg);
    }

    public void acceptNotiBrower() {
        claimHivePage.alertAccept();
    }
}
