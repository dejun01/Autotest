package com.opencommerce.shopbase.storefront.steps.gateway;

import com.opencommerce.shopbase.storefront.pages.gateway.AsiaBillPage;
import com.opencommerce.shopbase.storefront.pages.gateway.PaypalPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.awt.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AsiaBillSteps extends ScenarioSteps {
    AsiaBillPage asiaBillPage;

    public void inputFirstName(String name) {
        asiaBillPage.inputFirstName(name);
    }

    public void inputLastName(String name) {
        asiaBillPage.inputLastName(name);
    }

    @Step
    public void inputCardNumber(String cardNumber) {
        asiaBillPage.inputCardNumber(cardNumber);
    }

    @Step
    public void inputCVV(String cvv) {
        asiaBillPage.inputCVV(cvv);
    }

    public void inputCardIssuingBank(String bank) {
        asiaBillPage.inputCardIssuingBank(bank);
    }

    public void selectExpiredMonth(String month) {
        asiaBillPage.selectExpiredMonth(month);
    }

    public void selectExpiredYear(String year) {
        asiaBillPage.selectExpiredYear(year);
    }

    @Step
    public void inputExpireDate(String expireDate) {
        asiaBillPage.clickThenTypeCharByChar(asiaBillPage.xpathExpirationDate,expireDate);
    }

    public String getAddress() {
        return asiaBillPage.getAddress();
    }

    public String getCity() {
        return asiaBillPage.getCity();
    }

    public String getState() {
        return asiaBillPage.getState();
    }

    public String getPostcode() {
        return asiaBillPage.getPostcode();
    }

    public String getCountry() {
        return asiaBillPage.getCountry();
    }

    public String getEmail() {
        return asiaBillPage.getEmail();
    }

    public String getPhone() {
        return asiaBillPage.getPhone();
    }

    @Step
    public void clickOnPayNowBtn() {
        asiaBillPage.clickOnPayNowBtn();
    }

    public void clickOnCancelBtn() {
        asiaBillPage.clickOnCancelBtn();
    }

    public String getFirstName() {
        return asiaBillPage.getFirstName();
    }

    public String inputLastName() {
        return asiaBillPage.inputLastName();
    }

    public void clickOnCancelButtonOfTheAlert() {
        asiaBillPage.clickOnCancelButtonOfTheAlert();
    }

    public void clickOnAcceptButtonOfTheAlert() {
        asiaBillPage.clickOnAcceptButtonOfTheAlert();
    }

    @Step
    public void inputAsiaBillCardInfo(String cardNumber, String expiredDate, String cvv) {
        inputCardNumber(cardNumber);
        inputExpireDate(expiredDate);
        inputCVV(cvv);
    }
}
