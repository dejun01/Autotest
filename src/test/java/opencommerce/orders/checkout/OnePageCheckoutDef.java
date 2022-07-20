package opencommerce.orders.checkout;

import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.storefront.steps.gateway.PaypalSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CustomerInformationSteps;
import com.opencommerce.shopbase.storefront.steps.shop.PaymentMethodSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;

import java.util.List;

public class OnePageCheckoutDef {
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    ThankyouSteps thankYouSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    PaypalSteps paypalSteps;


    @Then("^checkout by Stripe successfully on one page with \"([^\"]*)\" user$")
    public void checkout_by_Stripe_successfully_on_one_page(String userType) {
        onePageCheckoutSteps.verifyOnePageCheckout();
        onePageCheckoutSteps.chooseEmail(userType);
        onePageCheckoutSteps.enterShippingAddress();
        onePageCheckoutSteps.enterCardInformation();
        onePageCheckoutSteps.clickPlaceYourOrder();
        thankYouSteps.verifyThankYouPage();
    }

    @Then("^checkout by Paypal express successfully on one page$")
    public void checkout_by_Paypal_express_successfully_on_one_page() {
        onePageCheckoutSteps.verifyOnePageCheckout();
        paymentMethodSteps.selectExpressCheckout();
        paymentMethodSteps.waitABit(30000);
        paypalSteps.loginPaypal("buyer@shopbase.com", "123456@a");
        paymentMethodSteps.waitABit(2000);
        paymentMethodSteps.verifyPaypalSelected();
        customerInformationSteps.enterPhone("4047957606", true);
        onePageCheckoutSteps.clickPlaceYourOrder();
        thankYouSteps.verifyThankYouPage();
    }

    @Then("^checkout by Paypal successfully on one page$")
    public void checkout_by_Paypal_successfully_on_one_page() {
        onePageCheckoutSteps.choosePaypal();
        onePageCheckoutSteps.clickContinueWithPaypal();
        onePageCheckoutSteps.waitForPayPalScreen();
        paymentMethodSteps.waitABit(2000);
        if (paypalSteps.isShowPayPalLogin() == true) {
            paypalSteps.enterUsername("buyer@shopbase.com");
            paypalSteps.enterPwd("123456@a");
        }
        paypalSteps.clickBtnContinuePayPal();
        thankYouSteps.verifyThankYouPage();
    }


}
