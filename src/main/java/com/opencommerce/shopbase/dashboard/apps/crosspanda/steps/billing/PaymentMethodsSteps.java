package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.billing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.billing.PaymentMethodsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class PaymentMethodsSteps extends ScenarioSteps {
    PaymentMethodsPage paymentMethodsPage;

    @Step
    public void verifyUINotCard() {
        paymentMethodsPage.verifyTextPresent("Add a credit/debit card", true);
        paymentMethodsPage.verifyTextPresent("Pay your orders by your credit or debit card", true);
        paymentMethodsPage.verifyButton();
        paymentMethodsPage.verifyNotCard();
    }

    @Step
    public boolean isCard() {
        return paymentMethodsPage.isCard();
    }

    @Step
    public boolean isBtnAddANewCard() {
        return paymentMethodsPage.BtnAddANewCard();
    }

    @Step
    public void addNewCard() {
        paymentMethodsPage.clickAddNewCard();
    }

    @Step
    public void enterCardholderName(String cardName) {
        paymentMethodsPage.enterInputFieldWithLabel("Cardholder Name", cardName);
    }

    @Step
    public void enterCardNumber(String cardNumber) {
        paymentMethodsPage.switchToIFrameCardNumber();
        waitABit(3000);
        paymentMethodsPage.enterInputFieldWithLabel("1234 1234 1234 1234", cardNumber);
        paymentMethodsPage.switchToIFrameDefault();
    }

    @Step
    public void enterExpDate(String expDate) {
        paymentMethodsPage.switchToIFrameExpDate();
        waitABit(3000);
        paymentMethodsPage.enterInputFieldWithLabel("MM / YY", expDate);
        paymentMethodsPage.switchToIFrameDefault();
    }

    @Step
    public void enterCVV(String cvc) {
        paymentMethodsPage.switchToIFrameCVV();
        waitABit(3000);
        paymentMethodsPage.enterInputFieldWithLabel("CVC", cvc);
        paymentMethodsPage.switchToIFrameDefault();
    }

    @Step
    public void enterFirstName(String firstName) {
        paymentMethodsPage.enterInputFieldWithLabel("First Name", firstName);
    }

    @Step
    public void enterLastName(String lastName) {
        paymentMethodsPage.enterInputFieldWithLabel("Last Name", lastName);
    }

    @Step
    public void enterAddress(String address) {
        paymentMethodsPage.enterInputFieldWithLabel("Address", address);
    }

    @Step
    public void enterCity(String city) {
        paymentMethodsPage.enterInputFieldWithLabel("City", city);
    }

    @Step
    public void selectCountry(String country) {
        paymentMethodsPage.selectFieldLabel("Select country", country);
    }

    @Step
    public void selectState(String state) {
        paymentMethodsPage.selectFieldLabel("Select state", state);
    }

    @Step
    public void enterZipCode(String zipcode) {
        paymentMethodsPage.enterInputFieldWithLabel("Zip/Postal code", zipcode);
    }

    @Step
    public void saveCard() {
        paymentMethodsPage.clickBtn("Save");
        paymentMethodsPage.waitForSaveSucc();
    }

    @Step
    public void verifyError(String message) {
        paymentMethodsPage.verifyTextPresent(message, true);
    }

    @Step
    public void verifyCardType(String cardType) {
        paymentMethodsPage.verifyTextPresent(cardType, true);
    }

    @Step
    public void verifyExpDateCard(String expDate) {
        paymentMethodsPage.verifyTextPresent(expDate, true);
    }

    @Step
    public void verifyCardDefault(String cardType, boolean cardDefault) {
        paymentMethodsPage.verifyCardDefault(cardType, cardDefault);
    }

    @Step
    public void deleteCard() {
        int countCard = paymentMethodsPage.getCountCard();
        paymentMethodsPage.waitForEverythingComplete();
        if (countCard == 1) {
            paymentMethodsPage.deletCard(1);
            paymentMethodsPage.deleteConfirm();
        } else {
            for (int i = 2; i <= countCard; i++) {
                paymentMethodsPage.deletCard(countCard - 1);
                paymentMethodsPage.deleteConfirm();
            }
        }
    }

    @Step
    public void changeCardDefault(String cardType) {
        paymentMethodsPage.changeCardDefault(cardType);
    }

    @Step
    public void enterApartment(String apartment) {
        paymentMethodsPage.enterInputFieldWithLabel("More address", apartment);
    }
}
