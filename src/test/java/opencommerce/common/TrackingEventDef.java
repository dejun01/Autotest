package opencommerce.common;

import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


import java.io.IOException;
import java.util.List;

public class TrackingEventDef {
    @Steps
    TrackingEventSteps teStep;
    @Steps
    ThankyouSteps tyStep;

    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    ProductSteps productSteps;

    String shopName = LoadObject.getProperty("shopname");

    @Given("^Verify tracking event with type is \"([^\"]*)\" and event is \"([^\"]*)\"")
    public void verifyTrackingEventWithKey(String type, String event, List<List<String>> dataTable) {
        for (int i = 0; i < dataTable.get(0).size(); i++) {
            String key = dataTable.get(0).get(i);
            if (!key.isEmpty()) {
                String valueExpect = dataTable.get(1).get(i);
                if (valueExpect.isEmpty()) {
                    valueExpect = LoadObject.getProperty(key);
                }
                if(key.equals("shop_name")){
                    valueExpect = shopName;
                }
                if (valueExpect.equals("@transaction_id@")) {
                    String transaction_id = teStep.getOrderIDAtCheckout();
                    teStep.verifyTrackingEvent(type, event, key, transaction_id);
                } else {
                    teStep.verifyTrackingEvent(type, event, key, valueExpect);
                }
            }
        }
    }

    @Given("^Verify tracking event of item with type is \"([^\"]*)\" and event is \"([^\"]*)\"")
    public void verifyTrackingEventOfItemWithKey(String type, String event, List<List<String>> dataTable) {
        for (int i = 0; i < dataTable.get(0).size(); i++) {
            String key = dataTable.get(0).get(i);
            String valueExpect = dataTable.get(1).get(i);
            if (valueExpect.isEmpty()) {
                valueExpect = LoadObject.getProperty(key);
            }
            teStep.verifyTrackingEventOfItem(type, event, key, valueExpect);
        }
    }


    @And("^Search product \"([^\"]*)\"$")
    public void searchProduct(String productName) {
        teStep.clearTrackingEvent();
        productSteps.searchProduct(productName);
    }

    @And("^clear tracking event$")
    public void clearTrackingEvent() {
        teStep.clearTrackingEvent();
    }

    @When("^Complete fill customer information")
    public void completeFillCustomerInfor() {
        long currentTime = System.currentTimeMillis();
        String currentCheckoutTime = Long.toString(currentTime);
        customerInformationSteps.enterCustomerInformationOnSF(true, currentCheckoutTime);
        teStep.clearTrackingEvent();
        customerInformationSteps.clickBtnContinueToShippingMethod();
    }

    @When("^Complete select payment method$")
    public void completeSelectPaymentMethod() {
        paymentMethodSteps.enterPaymentMethodByStripe();
        paymentMethodSteps.selectBillingAddress("Same as shipping address");
        teStep.clearTrackingEvent();
        paymentMethodSteps.clickBtnCompleteOrder();
        tyStep.verifyThankYouPage();
    }

    @When("^Add custom script form file \"([^\"]*)\"$")
    public void addCustomScriptFromFile(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String head = SessionData.getDataTbVal(dataTable, row, "Head");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");

            String scriptHead = "";
            String scriptBody = "";

            if (!head.isEmpty()) {
                scriptHead = teStep.getDataFromFile(head);
            }
            if (!body.isEmpty()) {
                scriptBody = teStep.getDataFromFile(body);
            }
            teStep.addCustomScriptByAPI(scriptHead, scriptBody);
        }
    }


}