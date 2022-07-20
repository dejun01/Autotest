package com.opencommerce.shopbase.dashboard.tier_program.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TierDashboardPage extends SBasePageObject {
    public TierDashboardPage(WebDriver driver) {
        super(driver);
    }

    int coinRedeem;
    double availableBcoin;
    int amountReward;

    public int getCoinRedeem(){
        String coinOnTheReedemBox = getText("//span[@class='discount-available-coin']").trim();
        coinRedeem = Integer.parseInt(coinOnTheReedemBox.replaceAll("[^0-9]", ""));
        return coinRedeem;
    }
    public double getAvailableBcoin(){
        String avaBcoin = getText("//div[@class='coin-amount s-mt8']").trim();
        availableBcoin = Double.parseDouble(avaBcoin.replaceAll(",", ""));
        return availableBcoin;
    }
    public int getAmountReward(){
        String amountOntheReedemBox = getText("//p[@class='reward-money']").trim();
        amountReward = Integer.parseInt(amountOntheReedemBox.replaceAll("[^0-9]", ""));
        return amountReward;
    }


    public void verifyAvailableBcoin(String avaiBcoin) {

        String availableBcoinonDB = getText("//div[@class='coin-amount s-mt8']").trim().replaceAll(",", "");
        Assertions.assertThat(availableBcoinonDB).isEqualToIgnoringCase(avaiBcoin);
    }

    public void verifyBcoin(String bCoin) {
        String bcoinonDB = getText("//p[@class='current-rank-point text-center']//child::b").trim().replaceAll(",", "");
        Assertions.assertThat(bcoinonDB).isEqualToIgnoringCase(bCoin);
    }

    public void verifyLevelTier(String tierName) {
        String tierNameonDB = getText("//span[contains(@class,'tier-name level')]").trim();
        Assertions.assertThat(tierNameonDB).isEqualToIgnoringCase(tierName);
    }

    public void verifyStatusOfBenefis(String tier, String benefits) {
        String level = getText("//span[contains(@class,'tier-name level')]").trim();
        if (level.equals(tier)) {
            verifyElementText("//div[@class='wrap-info-item flex align-items-center s-mb8 benefit-active']", benefits);
        }
    }

    public void verifyDisplayRedeemRewardsPopup() {
        clickOnElement("//a[normalize-space()='See all rewards']");
        verifyTextPresent("Redeem Rewards", true);
    }

    public void verifyStatusRedeemButton() {
        clickOnElement("//a[normalize-space()='See all rewards']");
        String avaBcoin = getText("//div[@class='coin-amount']").replaceAll(",", "").trim();
        double availableBcoin = Double.parseDouble(avaBcoin);
        int n = countElementByXpath("//div[contains(@class,'animation-content')]//span[@class='discount-available-coin']");
        System.out.println("number of reward: " + n);
        for (int i = 0; i < n; i++) {
            String coinOnTheReedemBox = getText("(//div[contains(@class,'animation-content')]//span[@class='discount-available-coin'])[" + (i + 1) + "]").trim();
            int coinRedeem = Integer.parseInt(coinOnTheReedemBox.replaceAll(",", ""));
            if (coinRedeem > availableBcoin) {
                verifyElementPresent("//div[contains(@class,'animation-content')]//div[@class='redeem-item flex' and descendant::span[normalize-space()='" + coinOnTheReedemBox + "']]//button[@class='btn btn-redeem' and @disabled='disabled']", true);
            } else {
                verifyElementPresent("//div[contains(@class,'animation-content')]//div[@class='redeem-item flex' and descendant::span[normalize-space()='" + coinOnTheReedemBox + "']]//button[@class='btn btn-redeem' and not (@disabled='disabled')]", true);
            }
        }
    }

    public void clickOnRedeemButtonOnTheRedeemPopup() {
        clickOnElement("//div[contains(@class,'animation-content')]//*[@class='btn btn-redeem' and not (@disabled='disabled')]");
    }

    public void clickOnRedeemButtonOnTheConfirmScreen() {
        clickOnElement("//span[normalize-space()='Redeem Now']");
    }

    public void clickOnSeeYourBalanceButton() {
        clickOnElement("//span[normalize-space()='See your balance']");
    }

    public void verifyInvoiceWhenRedeemSuccess(int amountReward) {
        String valueAmountOnInvoice = getText("//div[normalize-space()='IN']//following-sibling::div[1]").trim();
        int amountOnInvoice = Integer.parseInt(valueAmountOnInvoice.split("\\.")[0].replaceAll("[^0-9]", ""));
        Assertions.assertThat(amountReward).isEqualTo(amountOnInvoice);
        verifyTextPresent("invoice payout of tier program", true);
    }

    public void verifyAvailableBcoinAfterRedeem(double availableBcoin, int coinRedeem) {
        String xpath = "//div[@class='coin-amount s-mt8']";
        float avaiAfterRedeem = Float.parseFloat((getText(xpath)).replaceAll(",", ""));
        Assertions.assertThat(availableBcoin - coinRedeem).isEqualTo(avaiAfterRedeem);
        closeBrowser();
    }

    public Boolean getStatusBtnRedeem(double availableBcoin) {
        String xpath = "//div[contains(@class,'animation-content')]//span[@class='discount-available-coin']";
        int firstCoinOnTheRedeemBox = Integer.parseInt((getText(xpath)).replaceAll(",", ""));
        if (firstCoinOnTheRedeemBox <= availableBcoin)
            return true;
        else return false;
    }

    public void verifyDisplayAvailableBcoin() {
        verifyElementPresent("//div[@class='coin-amount s-mt8']", true);
    }

}
