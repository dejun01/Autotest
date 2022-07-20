package opencommerce.orders.checkout;

import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.PaymentMethodSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ShippingMethodSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.AbstractBigDecimalAssert;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.SettingsVariables.*;
import static com.opencommerce.shopbase.SettingsVariables.profileName;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


public class ShippingMethodDef {
    @Steps
    ShippingMethodSteps shippingMethodSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;


    @Given("^checkout successfully at shipping method page$")
    public void checkout_successfully_on_shopbase_at_shipping_method_page() {
        shippingMethodSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.enterPaymentMethodByStripe();
        paymentMethodSteps.clickCompleteOrder();
    }

    @Then("^click change contact$")
    public void user_click_Change_button() {
        shippingMethodSteps.clickBtnChange();

    }


    @Given("^choose shipping method \"([^\"]*)\"$")
    public void choose_shipping_method(String shippingMethod) {
        float_totalAmt = orderSummarySteps.getTotalPrice();
        shippingFeeBeforeDiscount = shippingMethodSteps.getShippingFeeBeforeDiscount();
        if (!onePageCheckoutSteps.isOnePage()) {
            if (float_totalAmt > 0) {
//                billingAddress = "Same as shipping address";
                if (!shippingMethod.equalsIgnoreCase("none")) {
                    shippingMethodSteps.chooseShippingMethod(shippingMethod);
                    shippingMethodSteps.clickContinueToPaymentMethod();
                    paymentMethodSteps.verifyPaymentMethodDisplayed();
                } else {
                    shippingMethodSteps.verifyNoShippingDisplayed();
                }
            } else {
                shippingMethodSteps.clickOnCompleteOrderBtn();
            }
//        } else {
//            billingAddress = "Same as shipping address";
        } else {
            if (!shippingMethod.equalsIgnoreCase("none")) {
                shippingMethodSteps.clickBthChangeToShowAllRate();
                shippingMethodSteps.chooseShippingMethod(shippingMethod);
            }
        }
    }


    @Given("^verify shipping base on Price rules$")
    public void verify_shipping_base_on_price_rules(List<List<String>> dataTable) {
        int expQuantity = 0;
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sBaseRules = SessionData.getDataTbVal(dataTable, row, "Base rules");
            String sPrice = SessionData.getDataTbVal(dataTable, row, "Price");
            shippingMethodSteps.chooseShippingMethod(sBaseRules);
            shippingMethodSteps.verifyShippingPrice(sPrice);
            expQuantity += 1;
        }
        shippingMethodSteps.verifyQuantityOfShippingMethod(expQuantity);
        shippingMethodSteps.clickContinueToPaymentMethod();
    }

    @Given("^verify shipping base on Profile rules$")
    public void verifyShippingBaseOnProfileRules(List<List<String>> dataTable) {
        shippingMethodSteps.clickBthChangeToShowAllRate();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sRateName = SessionData.getDataTbVal(dataTable, row, "Base rules");
            String sPrice = SessionData.getDataTbVal(dataTable, row, "Price");
            System.out.println(sRateName);
            System.out.println(sPrice);

            shippingMethodSteps.verifyShippingMethod(sRateName,sPrice);
        }
    }

    @Given("^verify shipping base on Price rules as \"([^\"]*)\"$")
    public void verify_shipping_base_on_price_rules_by_key(String dataKey, List<List<String>> dataTable) {
        int expQuantity = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sBaseRules = SessionData.getDataTbVal(dataTable, row, "Base rules");
            String sPrice = SessionData.getDataTbVal(dataTable, row, "Price");

            shippingMethodSteps.chooseShippingMethod(sBaseRules);
            shippingMethodSteps.verifyShippingPrice(sPrice);
            expQuantity += 1;
        }
        shippingMethodSteps.verifyQuantityOfShippingMethod(expQuantity);
        shippingMethodSteps.clickContinueToPaymentMethod();
    }

    @Then("^verify the message along with non shippable products is displayed$")
    public void verifyTheMessageAlongWithNonShippableProductsIsDisplayed(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String prodName = SessionData.getDataTbVal(dataTable, row, "Product not shippable");

            shippingMethodSteps.verifyTheMessageDisplayed(msg);
            shippingMethodSteps.verifyNonShippableProdDisplayed(prodName);
        }
    }

    @And("verify the message {string} is displayed")
    public void verifyTheMessageIsDisplayed(String msg) {
        shippingMethodSteps.verifyTheMessageDisplayed(msg);
    }


    @Given("verify shipping fee PlusBase in Shipping method page when checkout")
    public void verify_shipping_fee_in_Shipping_method_page_when_checkout() {
        Float expect = expectShippingFee;
        Float act = shippingMethodSteps.getShippingPrice();
        assertThat(expect).isEqualTo(act);
    }

    @And("verify shipping method was chosen is {string}")
    public void verifyShippingMethodWasChosen(String exShippingMethod) {
        paymentMethodSteps.verifyPaymentMethodDisplayed();
        shippingMethodSteps.verifyShippingMethodWasChosen(exShippingMethod);
    }
}
