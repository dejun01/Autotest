package com.opencommerce.shopbase.dashboard.tier_program.steps;

import com.opencommerce.shopbase.dashboard.tier_program.pages.TierDashboardPage;

public class TierDashboardSteps {
    TierDashboardPage tierDashboardPage;
    public void verifyAvailableBcoin(String avaiBcoin) {
        tierDashboardPage.verifyAvailableBcoin(avaiBcoin);
    }
    public void verifyBcoin(String bCoin) { tierDashboardPage.verifyBcoin(bCoin); }

    public void verifyNameTier(String tierName) {
        tierDashboardPage.verifyLevelTier(tierName);
    }

    public void verifyStatusofBenefits(String tier,String benefits) {
        tierDashboardPage.verifyStatusOfBenefis(tier,benefits);
    }
    public void verifyDisplayRedeemRewardsPopup() {
        tierDashboardPage.verifyDisplayRedeemRewardsPopup();
    }

    public void verifyStatusRedeemButton() {
        tierDashboardPage.verifyStatusRedeemButton();
    }

    public void clickOnRedeemButtonOnTheRedeemPopup() {
        tierDashboardPage.clickOnRedeemButtonOnTheRedeemPopup();
    }

    public void clickOnRedeemButtonOnTheConfirmScreen() {
        tierDashboardPage.clickOnRedeemButtonOnTheConfirmScreen();
    }

    public void clickOnSeeYourBalanceButton() {
        tierDashboardPage.clickOnSeeYourBalanceButton();
    }

    public void verifyInvoiceWhenRedeemSuccess(int amountReward) {
        tierDashboardPage.verifyInvoiceWhenRedeemSuccess(amountReward);
    }

    public void verifyAvailableBcoinAfterRedeem(double availableBcoin, int coinRedeem) {
        tierDashboardPage.verifyAvailableBcoinAfterRedeem(availableBcoin,coinRedeem);
    }

    public Boolean getStatusBtnRedeem(double availableBcoin) {
         return tierDashboardPage.getStatusBtnRedeem(availableBcoin);
    }

    public int getCoinRedeem() {
        return tierDashboardPage.getCoinRedeem();
    }

    public double getAvailableBcoin() {
        return tierDashboardPage.getAvailableBcoin();
    }

    public int getAmountReward() {
        return tierDashboardPage.getAmountReward();
    }

    public void refreshPage() {
        tierDashboardPage.refreshPage();
    }

    public void verifyDisplayAvailableBcoin() {
        tierDashboardPage.verifyDisplayAvailableBcoin();
    }
}
