package com.opencommerce.shopbase.storefront.steps.gateway;

import com.opencommerce.shopbase.storefront.pages.gateway.DashboardStripePages;
import net.thucydides.core.annotations.Step;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardStripeSteps {
    DashboardStripePages dashboardStripePages;

    @Step
    public void openDashboardStripeLoginPage(String url){
        dashboardStripePages.openDashboardStripeLoginPage(url);
        getDriver().manage().deleteAllCookies();
        dashboardStripePages.maximizeWindow();
    }

    @Step
    public void openDashboardStripeLoginPageInNewTab(String url){
        dashboardStripePages.openDashboardStripeLoginPageInNewTab(url);
    }

    @Step
    public void inputEmail(String email){
        dashboardStripePages.inputEmail(email);
        dashboardStripePages.waitForEverythingComplete();
        if(dashboardStripePages.verifyInputtedEmail()){
            dashboardStripePages.inputEmail(email);
        }
    }

    @Step
    public void inputPassword(String password){
        dashboardStripePages.inputPassword(password);
    }

    @Step
    public void clickOnSignInBtn(){
        dashboardStripePages.clickOnSignInBtn();
    }

    @Step
    public void enableViewTestData(){
        dashboardStripePages.enableViewTestData();
    }

    @Step
    public void searchShopByConnectedId(String connectedId){
        dashboardStripePages.searchShopByConnectedId(connectedId);
    }

    @Step
    public void selectShopByConnectedIdAfterSearching(){
        dashboardStripePages.selectShopByConnectedIdAfterSearching();
    }

    @Step
    public void clickOnSendFundsBtn(){
        dashboardStripePages.clickOnSendFundsBtn();
    }

    @Step
    public void sendFunds(String amt){
        dashboardStripePages.sendFunds(amt);
    }

    @Step
    public void openPayoutToBankPopup(){
        dashboardStripePages.openPayoutToBankPopup();
    }

    @Step
    public void payoutToBank(String amt){
        dashboardStripePages.payoutToBank(amt);
    }

    @Step
    public double availableAmt(){
        double amt = dashboardStripePages.availableAmt();
        return amt;
    }

    @Step
    public void clickOnCancelBtn(){
        dashboardStripePages.clickOnCancelBtn();
    }

    @Step
    public void verifyTrxTotalAmt(String expectedAmt){
        String actualAmt = dashboardStripePages.getTrxTotalAmount();
         assertThat(expectedAmt).isEqualTo(actualAmt);
    }

    @Step
    public void verifyTrxStatus(String expectedStatus){
        String actualStatus = dashboardStripePages.getTrxStatus();
        assertThat(expectedStatus).isEqualTo(actualStatus);
    }

    @Step
    public void navigateToTrxDetailPage(String trxID){
        dashboardStripePages.navigateToTrxDetailPage(trxID);
    }
}
