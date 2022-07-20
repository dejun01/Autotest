package com.opencommerce.shopbase.storefront.steps.gateway;

import com.opencommerce.shopbase.storefront.pages.gateway.OceanPaymentPage;
import net.thucydides.core.annotations.Step;

public class OceanPaymentSteps {
    OceanPaymentPage oceanPaymentPage;

    public void inputCardNumber(String cardNumber) {
        oceanPaymentPage.inputCardNumber(cardNumber);
    }

    public void inputCVV(String cvv) {
        oceanPaymentPage.inputCVV(cvv);
    }

    public void inputExpiredDate(String date) {
        oceanPaymentPage.inputExpiredDate(date);
    }

    @Step
    public void inputOceanPaymentCardInfo(String sCardNumber, String expiredDate, String sCVV) {
        inputCardNumber(sCardNumber);
        inputExpiredDate(expiredDate);
        inputCVV(sCVV);
    }

    @Step
    public void clickOnPayNowBtn() {
        oceanPaymentPage.clickBtn("PAY NOW");
    }

    @Step
    public void clickOnCancelBtn() {
        oceanPaymentPage.clickOnCancelBtn();
    }

    @Step
    public void clickOnAcceptButtonOfTheAlert() {
        oceanPaymentPage.clickOnAcceptButtonOfTheAlert();
    }
}
