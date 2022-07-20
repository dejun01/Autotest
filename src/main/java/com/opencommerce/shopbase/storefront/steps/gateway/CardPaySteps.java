package com.opencommerce.shopbase.storefront.steps.gateway;

import com.opencommerce.shopbase.storefront.pages.gateway.CardPayPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CardPaySteps extends ScenarioSteps {
    CardPayPage cardPayPage;


    @Step
    public void inputCardInfo(String cardNo, String cardHolder, String expiredDate, String cvv){
        cardPayPage.inputCardNumber(cardNo);
        cardPayPage.inputCardHolder(cardHolder);
        cardPayPage.selectExpires(expiredDate);
        cardPayPage.inputCVV(cvv);
    }

    @Step
    public void clickOnPayBtn(){
        cardPayPage.clickOnElementByID("action-submit");
        cardPayPage.waitForEverythingComplete(60);
    }

    @Step
    public void clickOnCancelBtn(){
        cardPayPage.clickOnElementByID("action-cancel");
        waitABit(5000);
    }

    @Step
    public void verifyTotalAmount(String expectedAmt){
        cardPayPage.verifyTotalAmount(expectedAmt);
    }

    @Step
    public void submit3DS(String status){
        cardPayPage.submit3DS(status);
    }

    @Step
    public void inputCVV(String cvv){
        cardPayPage.inputCVV(cvv);
    }
}
