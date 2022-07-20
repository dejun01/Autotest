package com.opencommerce.shopbase.dashboard.balance.steps;

import com.opencommerce.shopbase.dashboard.balance.pages.AuthorizeFirstChargePage;

public class AuthorizeFirstChargeSteps {
    AuthorizeFirstChargePage authorizePage;

    public void clickOnButtonBalance() {
        authorizePage.clickOnButtonBalance();
    }

    public void clickOnButtonFirstChargeReview() {
        authorizePage.clickOnButtonFirstChargeReview();

    }

    public void clickOnButonFilter() {
        authorizePage.clickOnButtonFilter();
    }

    public void selectStatus() {
        authorizePage.selectStatus();
    }

    public void clickSelectBox() {
        authorizePage.clickSelectBox();
    }

    public void selectWaitingReview() {
        authorizePage.selectWatingReview();
    }

    public void clickButtonFilter() {
        authorizePage.clickButtonFilter();
    }

    public void clickOnId32() {
        authorizePage.clickOnId32();
    }

    public void clickButtonApprove() {
        authorizePage.clickButtonApprove();
    }

    public void clickButtonYes() {
        authorizePage.clickButtonYes();
    }

    public void clickOnId30() {
        authorizePage.clickOnId30();
    }


    public void enterBin(String bin) {
        authorizePage.enterBin(bin);
    }

    public void selectBin() {
        authorizePage.selectBin();
    }

    public void clickSelectBoxBin() {
        authorizePage.clickSeclectBoxBin();
    }

    public void clickOnId() {
        authorizePage.clickOnId();
    }

    public void clearStatusCard() {
        authorizePage.clearStautusCard();
    }

    public void rejectCard() {
        authorizePage.rejectCard();
    }

    public String getStatus() {
        return authorizePage.getStatus();
    }

    public void verifyCardStatusIs(String status) {
        authorizePage.verifyCardStatusIs(status);
    }

    public void selectUserEmail() {
        authorizePage.selectUserEmail();
    }

    public void enterUserName(String userName) {
        authorizePage.enterUserName(userName);
    }
}