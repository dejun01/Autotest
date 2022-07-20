package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.CustomerInformationPages;
import com.opencommerce.shopbase.storefront.pages.shop.PaymentMethodPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.pages.shop.OnepageCheckoutPage;

import static com.opencommerce.shopbase.storefront.pages.shop.CustomerInformationPages.SHIPPING_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

public class OnePageCheckoutSteps {

    OnepageCheckoutPage onePageCheckoutPage;
    PaymentMethodPage paymentMethodPage;
    CustomerInformationPages customerInformationPages;

    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    String sEmail = "thotran@yopmail.com";


    @Step
    public void verifyOnePageCheckout() {
        onePageCheckoutPage.waitForEverythingComplete();
        onePageCheckoutPage.verifyShippingAdressDisplayed();
//        onePageCheckoutPage.verifyShippingMethodDisplayed();
        onePageCheckoutPage.verifyPaymentMethodDisplayed();
    }

    public String chooseEmail(String userType) {
        long currentTime = System.currentTimeMillis();
        if (userType.matches("first-time")) {
            sEmail = "email." + currentTime + "@beeketing.net";
        }
        return sEmail;
    }

    public void scrollToShippingAddressForm() {
        customerInformationPages.scrollIntoElementView(SHIPPING_ADDRESS);
    }

    @Step
    public void enterShippingAddress() {
        scrollToShippingAddressForm();
        customerInformationSteps.enterEmail(sEmail);
        customerInformationSteps.enterFirstName("Tho");
        customerInformationSteps.enterLastName("Tran");
        customerInformationSteps.selectCountry("United States", "California");
        customerInformationSteps.enterZipCode("90001");
        customerInformationSteps.enterAddress("1st Main Street");
        customerInformationSteps.enterApartment("");
        customerInformationSteps.enterCity("Los Angeles");
        customerInformationSteps.enterPhone("4047957606", true);
    }

    @Step
    public void enterCardInformation() {
        paymentMethodSteps.selectTypePaymentMethod("Card");
        paymentMethodSteps.enterCardNumber("4242424242424242", "Stripe");
        paymentMethodSteps.enterCardholderName("John Doe");
        paymentMethodSteps.enterDate("12/22", "Stripe");
        paymentMethodSteps.enterCVV("111", "Stripe");
    }

    @Step
    public void clickPlaceYourOrder() {
        paymentMethodPage.switchToIFrameDefault();
        onePageCheckoutPage.clickPlaceYourOrder();
    }

    public float getSubtotalOfOrder() {
        float subtotal = onePageCheckoutPage.getSubtotal();
        return subtotal;
    }

    public String getOrderNumber() {
        String getOrderNumber = onePageCheckoutPage.getOrderNumber();
        return getOrderNumber;
    }

    public void choosePaypal() {
        onePageCheckoutPage.choosePaypal();
    }

    public void clickContinueWithPaypal() {
        onePageCheckoutPage.clickContinueWithPaypal();
    }


    public void waitForPayPalScreen() {
        onePageCheckoutPage.waitForPayPalScreen();
    }

    public boolean isOnePage() {
        return onePageCheckoutPage.isOnePage();
    }

    public void verifyBtnPlaceYourOrder() {
        onePageCheckoutPage.verifyBtnPlaceYourOrder();
    }

    public void verifyLogo(String expLogo, Boolean isCheck) {
        customerInformationSteps.verifyLogo(expLogo, isCheck);
    }

    public String getLogo() {
        return onePageCheckoutPage.getLogo();
    }
}
