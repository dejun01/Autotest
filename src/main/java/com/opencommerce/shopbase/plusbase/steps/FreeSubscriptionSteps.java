package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.FreeSubscriptionPage;
import com.opencommerce.shopbase.plusbase.pages.OnboardingPage;
import com.opencommerce.shopbase.plusbase.pages.OrderPlusBasePage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSubscriptionSteps {
    OnboardingPage onboardingPage;
    FreeSubscriptionPage freeSubPage;
    OrderPlusBasePage orderPlusBase;


    @Step
    public void verifySteps(String steps) {
        String act = onboardingPage.getSteps();
        assertThat(act).isEqualTo(steps);
    }


    @Step
    public void verifyTitleBanner(String title) {
        freeSubPage.verifyTitle();
    }

    public void verifyTips(String tips) {
        freeSubPage.verifyGetTips();
    }

    @Step
    public int getNumberOrder() {
        return freeSubPage.getNumberOrder();
    }

    @Step
    public void searchOrder(String orderName) {
        freeSubPage.searchOrder(orderName);
    }

    @Step
    public void verifyStatusOrder(String orderNumber, String status) {
        freeSubPage.verifyPaymentStatus(orderNumber, status);
    }
    @Step
    public void tickSelect(){
        freeSubPage.tickSelectAll();
    }
    @Step
    public void selectOrder(String orderName){
        freeSubPage.clickOrder(orderName);
    }
    @Step
    public void clickDropAction(String button) {
        orderPlusBase.clickMoreAction(button);
    }

    @Step
    public String getDateTrialOnBanner() {
        return freeSubPage.getDateTrialOnBanner();
    }

    @Step
    public void verifyLinkTextGetTip() {
        freeSubPage.verifyTextGetTips();
    }
    @Step
    public void verifyLinkTextSeeMoreDetail() {
        freeSubPage.clickOnLinkTextSeeMoreDetail();
        freeSubPage.verifyButtonBilling();
    }
    @Step
    public void verifyClaimSubs() {
        freeSubPage.verifyClaimSubs();
    }
    @Step
    public void verifyCongratulation() {
        freeSubPage.verifyCongratulations();
    }

    @Step
    public void verifyDateTrialShop(String mess, boolean flag) {
        freeSubPage.verifyTextPresent(mess, flag);
    }

    @Step
    public void verifyButtonBilling() {
        freeSubPage.verifyAccountActive();
    }
    @Step
    public void clickOrder() {
        freeSubPage.clickOnOrders();
    }

    @Step
    public void verifyTextCompletedOrder() {
        freeSubPage.verifyTextCompletedOrder();
    }
}


