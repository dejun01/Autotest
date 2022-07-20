package com.opencommerce.shopbase.storefront.steps.shop;

import common.CommonPageObject;
import net.serenitybdd.screenplay.actions.SendKeystoElement;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.storefront.pages.shop.PaymentMethodPage;

import static com.opencommerce.shopbase.OrderVariable.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodSteps extends ScenarioSteps {
    PaymentMethodPage paymentMethodPage;
    CommonPageObject commonPageObject;

    @Step
    public void verifyPaymentMethodDisplayed() {
        paymentMethodPage.verifyPaymentMethodDisplayed();
        shippingMethod = paymentMethodPage.getShippingMethodSelected();
    }

    @Step
    public void selectTypePaymentMethod(String method) {
        if (!method.isEmpty()) {
            paymentMethodPage.selectTypePaymentMethod(method);
            paymentMethod = method;
        }
    }

    @Step
    public void enterPaymentInfo(String cardNumber, String cardHolder, String expDate, String cvv, String paymentGateway) {
        waitABit(5000);
        switch (paymentGateway) {
            case "Stripe":
                paymentMethodPage.scrollToCardForm();
                paymentMethodPage.switchToIframeStripe("frame-form");
                enterStripeCardNumber(cardNumber, paymentGateway);
                enterCardholderName(cardHolder);
                enterStripeCardDate(expDate, paymentGateway);
                enterStripeCardCVV(cvv, paymentGateway);
                paymentMethodPage.switchToIFrameDefault();
                break;
            case "ShopBase Payments":
                paymentMethodPage.switchToIframeStripe("frame-form");
                enterCardNumber(cardNumber, paymentGateway);
                enterCardholderName(cardHolder);
                enterDate(expDate, paymentGateway);
                enterCVV(cvv, paymentGateway);
                paymentMethodPage.switchToIFrameDefault();
                break;
            case "Braintree":
            case "Checkout.com":
                enterCardNumber(cardNumber, paymentGateway);
                enterCardholderName(cardHolder);
                enterDate(expDate, paymentGateway);
                enterCVV(cvv, paymentGateway);
                break;
            case "Paypal-Pro":
                enterCardNumberOfPaypalPro(cardNumber);
                enterDateOfPaypalPro(expDate);
                enterCVVOfPaypalPro(cvv);
                break;
            case "Paypal": //paypal smart button checkout via credit card
                paymentMethodPage.clickOnAcceptCookieBtn();
                inputCardNumberInPaypalSmartBtn(cardNumber);
                inputExpireDateInPaypalSmartBtn(expDate);
                inputCVVInPaypalSmartBtn(cvv);
                break;
        }
    }

    @Step
    public void enterCardNumber(String cardNumber, String paymentGateway) {
        if (!cardNumber.isEmpty()) {
            switch (paymentGateway) {
                case "Stripe":
                    paymentMethodPage.switchToIframeStripe("frame-form");
                    paymentMethodPage.switchToIframeStripe("card-number");
                    break;
                case "ShopBase Payments":
                    paymentMethodPage.switchToIframeStripe("card-number");
                    break;
                case "Braintree":
                    paymentMethodPage.switchToIframeBraintree("card-number");
                    break;
                case "Checkout.com":
                    paymentMethodPage.switchToIframeCheckoutCom("card-number");
                    break;
            }
            paymentMethodPage.enterCardNumber(cardNumber);
            paymentMethodPage.switchToIFrameDefault();
        }
    }


    @Step
    public void enterCardholderName(String cardName) {
        if (!cardName.isEmpty())
            paymentMethodPage.enterInputFieldWithLabel("Cardholder name", cardName);
    }

    @Step
    public void enterDate(String date, String paymentGateway) {
        if (!date.isEmpty()) {
            switch (paymentGateway) {
                case "Stripe":
                case "ShopBase Payments":
                    paymentMethodPage.switchToIframeStripe("expired-date");
                    break;
                case "Braintree":
                    paymentMethodPage.switchToIframeBraintree("expired-date");
                    break;
                case "Checkout.com":
                    paymentMethodPage.switchToIframeCheckoutCom("expired-date");
                    break;
            }
            paymentMethodPage.enterDate(date);
            paymentMethodPage.switchToIFrameDefault();

        }
    }

    @Step
    public void enterCVV(String sCVV, String paymentGateway) {
        if (!sCVV.isEmpty()) {
            switch (paymentGateway) {
                case "Stripe":
                case "ShopBase Payments":
                    paymentMethodPage.switchToIframeStripe("cvv");
                    break;
                case "Braintree":
                    paymentMethodPage.switchToIframeBraintree("cvv");
                    break;
                case "Checkout.com":
                    paymentMethodPage.switchToIframeCheckoutCom("cvv");
                    break;
            }
            paymentMethodPage.enterCVV(sCVV);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void enterCardNumberOfPaypalPro(String cardNumber) {
        if (!cardNumber.isEmpty()) {
            paymentMethodPage.switchToIFramePaypalPro();
            paymentMethodPage.enterCardNumber(cardNumber);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void enterDateOfPaypalPro(String date) {
        if (!date.isEmpty()) {
            paymentMethodPage.switchToIFramePaypalPro();
            paymentMethodPage.enterDate(date);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void enterCVVOfPaypalPro(String sCVV) {
        if (!sCVV.isEmpty()) {
            paymentMethodPage.switchToIFramePaypalPro();
            paymentMethodPage.enterCVV(sCVV);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void chooseBillingAddress(String billAddress) {
        paymentMethodPage.selectRadioButtonWithLabel(billAddress, true);
    }

    @Step
    public void inputBillFirstname(String firtname) {
        paymentMethodPage.enterInputFieldWithLabel("first-name", firtname);
    }

    @Step
    public void inputBillLastname(String lastname) {
        paymentMethodPage.enterInputFieldWithLabel("last-name", lastname);
    }

    @Step
    public void chooseBillCountry(String country) {
        paymentMethodPage.selectDdlWithLabel("Country", country);
    }

    @Step
    public void inputBillAddress(String address) {
        paymentMethodPage.enterInputFieldWithLabel("street-address", address);
    }

    @Step
    public void inputBillApartment(String apartment) {
        paymentMethodPage.enterInputFieldWithLabel("sapartment-number", apartment);
    }

    @Step
    public void inputBillZipCode(String zipCode) {
        paymentMethodPage.enterInputFieldWithLabel("zip-code", zipCode);
    }

    @Step
    public void inputBillCity(String city) {
        paymentMethodPage.enterInputFieldWithLabel("city", city);
    }

    @Step
    public void inputPhone(String phone) {
        paymentMethodPage.enterInputFieldWithLabel("phone-number", phone);
    }

    @Step
    public void clickCompleteOrder() {
        paymentMethodPage.clickBtn("Complete order");
        paymentMethodPage.waitForEverythingComplete();
    }

    @Step
    public void verifyPaypalSelected() {
        paymentMethodPage.verifyPaypalSelected();
    }

    @Step
    public void verifyPaymentMethodPageDisplayed() {
        paymentMethodPage.verifyPaymentMethodDisplayed();
    }

    @Step
    public void enterCardInformation() {
        enterCardNumber("4242424242424242", "Stripe");
        enterCardholderName("Tho Tran");
        enterDate("12/22", "Stripe");
        enterCVV("111", "Stripe");
    }

    @Step
    public void clickPlaceYourOrder() {
        paymentMethodPage.clickBtn("Place Your Order");
    }

    @Step
    public void callFunctionPostPurchaseActivate() {
        paymentMethodPage.callFunctionPostPurchaseActivate();
    }

    @Step
    public void callFunctionPostPurchaseAddProduct(String variantId, String quantity, String price) {
        paymentMethodPage.callFunctionPostPurchaseAddProduct(variantId, quantity, price);
    }

    @Step
    public void callFunctionPostPurchaseFinalize() {
        paymentMethodPage.callFunctionPostPurchaseFinalize();
    }


    @Step
    public void clickBtnCheckoutPaypalSmart() {
        //waitABit(10000);
        paymentMethodPage.clickBtnCheckoutPaypalSmart();
    }

    @Step
    public void clickOnCreditCartBtn() {
        paymentMethodPage.waitForPageLoad();
        paymentMethodPage.clickOnCreditCartBtn();
    }

    @Step
    public void inputCardNumberInPaypalSmartBtn(String cardNumber) {
        paymentMethodPage.inputCardNumberInPaypalSmartBtn(cardNumber);
    }

    @Step
    public void inputExpireDateInPaypalSmartBtn(String expiredDate) {
        paymentMethodPage.inputExpireDateInPaypalSmartBtn(expiredDate);
    }

    @Step
    public void inputCVVInPaypalSmartBtn(String cvv) {
        paymentMethodPage.inputCVVInPaypalSmartBtn(cvv);
    }

    @Step
    public void clickOnPayNowBtnInPaypalSmartBtn() {
        paymentMethodPage.clickOnPayNowBtnInPaypalSmartBtn();
        //waitABit(20000);
    }

    @Step
    public void enterPaymentMethodByStripe() {
        waitABit(5000);
        selectTypePaymentMethod("Credit Card");
        paymentMethodPage.switchToIframeParent();
        enterCardNumber("4242424242424242", "Stripe");
        enterCardholderName("John Doe");
        enterDate("12/22", "Stripe");
        enterCVV("111", "Stripe");
        paymentMethodPage.switchToIFrameDefault();
    }

    @Step
    public void clickBtnCompleteOrder() {
        paymentMethodPage.clickBtn("Complete order");
    }

    //mew
    @Step
    public boolean verifyPaymentMethod(String paymentMethod) {
        return paymentMethodPage.verifyPaymentMethod(paymentMethod);
    }

    @Step
    public void verifyErrorMessageForPaymentMethod(String paymentGateway, String msgs) {
        switch (paymentGateway) {
            case "Stripe":
            case "ShopBase Payments":
            case "Braintree":
            case "Checkout.com":
            case "Unlimint":
                paymentMethodPage.waitForEverythingComplete();
                paymentMethodPage.verifyAMsgErrorForStripe(msgs);
                break;
            case "Paypal-Pro":
                paymentMethodPage.waitForEverythingComplete();
                paymentMethodPage.verifyErrorMessageForPaypalPro(msgs);
                break;
            case "Paypal": //paypal smart button checkout via credit card
                break;
        }
    }

    @Step
    public void selectExpressCheckout() {
        paymentMethodPage.selectExpressCheckout();
        if (paymentMethodPage.countNumberOfWWindow() == 1) {
            paymentMethodPage.selectExpressCheckout();
        }
    }

    @Step
    public void loginFasterPay(String username, String password) {
        verifyFasterPayScreen();
        clickLinkLoginFasterPay();
        enterUsernameFasterPay(username);
        enterPwdFasterPay(password);
        clickBtnLoginFasterPay();
        enterCVCToPayFasterPay("111");
        clickBtnPay();
        waitABit(5000);
        clickBtnBackToMerchant();
    }

    @Step
    private void clickBtnPay() {
        paymentMethodPage.clickBtnPay();
    }

    @Step
    private void enterCVCToPayFasterPay(String cVC) {
        paymentMethodPage.switchToFasterPay();
        paymentMethodPage.enterCVC(cVC);
    }

    @Step
    private void enterPwdFasterPay(String password) {
        paymentMethodPage.enterInputFieldWithLabel("Password", password);
    }

    @Step
    private void enterUsernameFasterPay(String username) {
        paymentMethodPage.enterInputFieldWithLabel("Email", username);
    }

    @Step
    private void clickLinkLoginFasterPay() {
        paymentMethodPage.clickLinkTextWithLabel("Log in");
    }

    @Step
    private void clickBtnLoginFasterPay() {
        paymentMethodPage.clickBtnLoginFasterPay();
        paymentMethodPage.waitForPageLoad();
    }

    @Step
    public void verifyFasterPayScreen() {
        paymentMethodPage.waitForPageLoad();
        assertThat(paymentMethodPage.getTitle()).isEqualTo("FasterPay");
    }

    @Step
    public void clickBtnBackToMerchant() {
        waitABit(2000);
        paymentMethodPage.clickLinkTextWithLabel("Back to merchant");
    }

    @Step
    public void clickFasterPayCheckbox() {
        selectTypePaymentMethod("Credit Card powered by");
        paymentMethodPage.verifyFasterPaySelected();
    }

    @Step
    public void verifyNoPaymentMethods() {
        paymentMethodPage.verifyNoPaymentMethods();

    }

    @Step
    public void verifyOutOfStockMsg() {
        paymentMethodPage.verifyTextPresent("Out of stock", true);
        paymentMethodPage.verifyTextPresent("Some items are no longer available.", true);
        paymentMethodPage.verifyTextPresent("Your cart has been updated.", true);
    }

    @Step
    public void verifyQuantity(String quantity) {
        String textQuantity = paymentMethodPage.getQuantity();
        String qua[] = quantity.split(">");
        assertThat(textQuantity).contains(qua[0]);
        assertThat(textQuantity).contains(qua[1]);
    }

    @Step
    public void clickBtnContinue() {
        paymentMethodPage.clickBtn("Continue");
    }

    @Step
    public void selectBillingAddress(String option) {
        paymentMethodPage.selectRadioButtonWithLabel(option, true);
    }

    @Step
    public void clickBtnPlaceYourOrder() {
        paymentMethodPage.clickBtn("Place Your Order");
    }

    @Step
    public void verifyPaymentInfoIn3DsPopup(String expectedAmt, String paymentGateway) {
        switch (paymentGateway) {
            case "Stripe":
            case "ShopBase Payments":
                break;
            case "Paypal-Pro":
            case "Braintree":
                paymentMethodPage.switchToiFrame3Ds(paymentGateway);
                paymentMethodPage.verifyAmountIn3DsPopup(expectedAmt, paymentGateway);
                paymentMethodPage.switchToiFrameDefault3Ds(paymentGateway);
                break;
            case "Checkout.com":
                break;
            case "Paypal": //paypal smart button checkout via credit card
                break;
        }
    }

    @Step
    public void submit3DsPassword(String pwd, String paymentGateway) {
        switch (paymentGateway) {
            case "Stripe":
            case "ShopBase Payments":
                paymentMethodPage.switchToiFrame3Ds(paymentGateway);
                paymentMethodPage.confirmStripe3DSecureSuccessfully();
                paymentMethodPage.switchToIFrameDefault();
                break;
            case "Paypal-Pro":
                paymentMethodPage.switchToiFrame3Ds(paymentGateway);
                paymentMethodPage.inputPassword3Ds(pwd);
                logInfor("Before Click submit");
                paymentMethodPage.clickOnSubmit3DsBtn();
                logInfor("Click submit successfully");
                paymentMethodPage.switchToiFrameDefault3Ds(paymentGateway);
                logInfor("Switch to default iframe successfully");
                break;
            case "Braintree":
                paymentMethodPage.switchToiFrame3Ds(paymentGateway);
                paymentMethodPage.inputPassword3DsBraintree(pwd);
                logInfor("Before Click submit");
                paymentMethodPage.clickOnSubmit3DsBtn();
                logInfor("Click submit successfully");
                paymentMethodPage.switchToiFrameDefault3Ds(paymentGateway);
                logInfor("Switch to default iframe successfully");
                break;
            case "Checkout.com":
                paymentMethodPage.switchToiFrame3DsCheckoutCom();
                paymentMethodPage.inputPassword3Ds(pwd);
                logInfor("Before Click submit");
                paymentMethodPage.clickOnSubmit3DsBtn();
                logInfor("Click submit successfully");
                paymentMethodPage.switchToIFrameDefault();
                logInfor("Switch to default iframe successfully");
                break;
        }
    }

    @Step
    public void submit3DsFail(String paymentGateway) {
        switch (paymentGateway) {
            case "Stripe":
            case "ShopBase Payments":
                paymentMethodPage.switchToiFrame3Ds(paymentGateway);
                paymentMethodPage.confirmStripe3DSecureUnsuccessfully();
                paymentMethodPage.switchToIFrameDefault();
                break;
            case "Paypal-Pro":
            case "Braintree":
            case "Checkout.com":
        }
    }

    @Step
    public void logInfor(String s) {
    }

    @Step
    public void verifyStayingAtWhichCheckoutStep(String checkoutStep) {
        paymentMethodPage.verifyStayingAtWhichCheckoutStep(checkoutStep);
    }

    @Step
    public void compareProductList(List<String> productListBefore, List<String> productListAfter) {
        assertThat(productListBefore).isEqualTo(productListAfter);
    }

    @Step
    public void clickOnCompleteOrderBtn() {
        paymentMethodPage.clickBtn("Complete order");
    }

    @Step
    public String getThirdPartyGateway() {
        return paymentMethodPage.getThirdPartyGateway();
    }

    @Step
    public boolean checkShopBasePayPalSmartButton() {
        return paymentMethodPage.checkShopBasePayPalSmartButton();
    }

    public void clickButtonForLabel(String label) {
        paymentMethodPage.clickBtn(label);
    }

    public void verifyGatewayPaymentDisplayOnSF(String gateway, String status) {
        paymentMethodPage.verifyGatewayPaymentDisplayOnSF(gateway, status);
    }

    public void inputCode(String code) {
        paymentMethodPage.inputCode(code);
    }

    public void orderCamp(String sCartNumber, String sCartName, String sDate, String sCVV) throws Exception {
        paymentMethodPage.inputCardNumber(sCartNumber);
        paymentMethodPage.inputCardholderName(sCartName);
        paymentMethodPage.inputDate(sDate);
        paymentMethodPage.inputCVV(sCVV);
        paymentMethodPage.clickOnPlaceYourOrderBtn();
        paymentMethodPage.clickOnCompleteOrder();
        waitABit(5000);
    }


    @Step
    public void enterStripeCardNumber(String cardNumber, String paymentGateway) {
        if (!cardNumber.isEmpty()) {
            if ("Stripe".equalsIgnoreCase(paymentGateway)) {
                paymentMethodPage.switchToIframeStripe("card-number");
            }
            paymentMethodPage.enterCardNumber(cardNumber);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void enterStripeCardDate(String date, String paymentGateway) {
        if (!date.isEmpty()) {
            if ("Stripe".equalsIgnoreCase(paymentGateway)) {
                paymentMethodPage.switchToIframeStripe("expired-date");
            }
            paymentMethodPage.enterDate(date);
            paymentMethodPage.switchToIFrameDefault();

        }
    }

    @Step
    public void enterStripeCardCVV(String sCVV, String paymentGateway) {
        if (!sCVV.isEmpty()) {
            if ("Stripe".equalsIgnoreCase(paymentGateway)) {
                paymentMethodPage.switchToIframeStripe("cvv");
            }
            paymentMethodPage.enterCVV(sCVV);
            paymentMethodPage.switchToIFrameDefault();
        }
    }

    @Step
    public void verifyEUPaymentMethods(String methods, int index) {
        paymentMethodPage.verifyEUPaymentMethod(methods, index);
    }

    @Step
    public void choosePaymentMethods(String value) {
        paymentMethodPage.choosePaymentMethod(value);
    }

    @Step
    public void clickBtnAuthorize() {
        paymentMethodPage.clickAuthorized();
    }

    @Step
    public void selectBank(String sBank) {
        paymentMethodPage.selectBank(sBank);
    }

    @Step
    public void inputIBANNumber(String number) {
        paymentMethodPage.enterIBANNumber(number);
    }

    public float getValuePayemnt(String label) {
        return paymentMethodPage.getValuePayemnt(label);
    }

    public void verifyValuePaymentAfterAddPPC(float valAfter, float valBefore) {
        assertThat(valAfter).isGreaterThan(valBefore);
    }
}




