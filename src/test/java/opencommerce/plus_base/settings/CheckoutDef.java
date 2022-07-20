package opencommerce.plus_base.settings;

import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.CustomerAccountsSteps;
import com.opencommerce.shopbase.plusbase.steps.CheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.orders.order.OrderDef;

import static com.opencommerce.shopbase.OrderVariable.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.nameProductImportToStore;
import static com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps.orderState;
import static opencommerce.products.dashboard.ProductDetailDef.nameProductSbase;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CheckoutDef {
    @Steps
    CustomerAccountsSteps customerAccountsSteps;
    @Steps
    ThankyouSteps thankyouSteps;

    @Steps
    OrderSteps orderSteps;
    @Steps
    CheckoutSteps checkoutSteps;


    @Then("click on button {string} and verify")
    public void clickOnButtonAndVerify(String name) {
        checkoutSteps.verifyClickBTCustomizeCheckout(name);

    }

    @And("Setting {string}")
    public void setting(String name, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Setting");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            checkoutSteps.settingCheckout(tab,status);
        }
    }

    @And("Verify display footer")
    public void verifyDisplayFooter() {
        checkoutSteps.displayFooter();

    }

    @And("Verify not display footer")
    public void verifyNotDisplayFooter() {
        checkoutSteps.notDisplayfooter();
    }

    @Then("verify header when login field")
    public void verifyHeaderWhenLoginField() {
        checkoutSteps.verifyHeaderHaveLogin();

    }

    @Then("verify header when not login field")
    public void verifyHeaderWhenNotLoginField() {
        checkoutSteps.verifyHeaderNotHaveLogin();
    }

    @Then("verify order detail after created")
    public void verifyOrderDetailAfterCreated() {
        String customerName = orderSteps.getCustomerName();
        int i = 1;
        for (String products : listProduct) {

            String[] _number = products.split("\\|");
            String productName = _number[0];
            String _price = _number[1];
            String _quantity = _number[2];

            thankyouSteps.logInfor("Product name : " + productName + " has price: " + _price + " Quantity :" + _quantity + " Total :" + totalAmt);
            if (!discountAmount.isEmpty()) {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price, discountAmount);
            } else {
                orderSteps.verifyProductInformation(i, productName, _quantity, _price);
            }
            i++;
        }


        if (!paymentGateway.equalsIgnoreCase("COD")) {
            orderState = "Authorized";
        }
        else {
            orderState = "Pending";
        }
        orderSteps.verifyOrderStatus(orderState);
        if (OrderDef.discountSF != null) {
            orderSteps.verifyDiscountOnDB(discountCode);
        }
        if (float_totalAmt > 0) {
            checkoutSteps.verifyOrderTimelineOrderPlusBase(customerName, customerEmail);
        }


    }

    @And("Setting checkout footer")
    public void settingCheckout(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Setting");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            checkoutSteps.settingDisplayFooterWhenCheckout(tab,status);
        }
    }

    @Then("verify header by \"([^\"]*)\"$")
    public void verifyHeader(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Setting");
            if (condition.equals("Accounts are optional")) {
                checkoutSteps.verifyHeaderHaveLogin();
            } else {
                checkoutSteps.verifyHeaderNotHaveLogin();
            }
        }
    }
}
