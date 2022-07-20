package com.opencommerce.shopbase.dashboard.orders.api;

import com.opencommerce.shopbase.APICommon;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class StripeAPI {
    APICommon apiCommon;
    @Steps
    OrderSteps orderSteps;

    String listOfCountryFile = "src/test/resources/dataset/country_name_code.json";

    //-----------------------API URL----------------------------------------------------------------------------------------
    private String retrieveAChargeUrl(String chargeId) {
        return "https://api.stripe.com/v1/charges/" + chargeId;
    }

    private String retrieveADisputeResponseUrl(String disputeId) {
        return "https://api.stripe.com/v1/disputes/" + disputeId;
    }

    //----------------------------------------------------------------------------------------------------------------------
    //get charge infor of Stripe transaction
    private JsonPath getAChargeResponseBody(String chargeId, String userName) {
        return apiCommon.getAPIStripeTrx(retrieveAChargeUrl(chargeId), userName, "");
    }

    //get charge info of Spay transaction
    private JsonPath getAChargeResponseBody(String chargeId, String userName, String connectedId) {
        return apiCommon.getDataFromStripe(retrieveAChargeUrl(chargeId), userName, "", connectedId);
    }

    private JsonPath getADisputeResponseBody(String disputeId, String userName, String connectedId) {
        return apiCommon.getDataFromStripe(retrieveADisputeResponseUrl(disputeId), userName, "", connectedId);
    }

    //shippingAddress = (sFirstName + " " + sLastName + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipCode + " " + sState + " " + sCountry + " " + sPhone.replaceAll("\\s", "")).replaceAll("\\s{2,}", " ");
    @Step
    public HashMap<String, String> getChargeInfoStripe(String chargeId, String userName) {
        HashMap<String, String> chargeInfo = new HashMap<>();
        JsonPath response = getAChargeResponseBody(chargeId, userName);

        String city = response.get("billing_details.address.city").toString().trim();
        String country = response.get("billing_details.address.country").toString().trim();
        country = orderSteps.getCountryNameByCodeFromFile(listOfCountryFile, country);
        String line1 = response.get("billing_details.address.line1").toString().trim();
        String line2 = "";
        if (response.get("billing_details.address.line2") != null) {
            line2 = response.get("billing_details.address.line2").toString().trim();
        }
        String postal_code = response.get("billing_details.address.postal_code").toString().trim();
        String state = response.get("billing_details.address.state").toString().trim();
        String name = response.get("billing_details.name").toString().trim();
        String phone = response.get("billing_details.phone").toString().trim();
        String billingAddress = (name + " " + line1 + " " + line2 + " " + city + " " + postal_code + " " + state + " " + country + " " + phone.replaceAll("\\s", "")).trim().replaceAll(" +", " ");

        String sCity = response.get("shipping.address.city").toString().trim();
        String sCountry = response.get("shipping.address.country").toString().trim();
        sCountry = orderSteps.getCountryNameByCodeFromFile(listOfCountryFile, sCountry);
        String sLine1 = response.get("shipping.address.line1").toString().trim();
        String sLine2 = "";
        if(response.get("shipping.address.line2") != null)
        {
            sLine2 = response.get("shipping.address.line2").toString().trim();
        }
        String sPostal_code = response.get("shipping.address.postal_code").toString().trim();
        String sState = response.get("shipping.address.state").toString().trim();
        String sName = response.get("shipping.name").toString().trim();
        String sPhone = response.get("shipping.phone").toString().trim();
        String shippingAddress = (sName + " " + sLine1 + " " + sLine2 + " " + sCity + " " + sPostal_code + " " + sState + " " + sCountry + " " + sPhone.replaceAll("\\s", "")).trim().replaceAll(" +", " ");

//        String trackingNumber = response.get("shipping.carrier").toString().trim();
//        String trackingCarrier = response.get("shipping.tracking_number").toString().trim();

        chargeInfo.put("Amount", response.get("amount").toString().trim());
        chargeInfo.put("Captured amount", response.get("amount_captured").toString().trim());
        chargeInfo.put("Email", response.get("billing_details.email").toString().trim());
//        chargeInfo.put("Tracking", response.get("shipping.carrier").toString().trim() + " - " + response.get("shipping.tracking_number").toString().trim());
//        chargeInfo.put("Description", response.get("description").toString().trim());
//        chargeInfo.put("Billing address", billingAddress);
        chargeInfo.put("Shipping address", shippingAddress);

        //Metadata
        chargeInfo.put("Owner email", response.get("metadata.owner_email").toString().trim());
        chargeInfo.put("Checkout token", response.get("metadata.checkout_token").toString().trim());
        chargeInfo.put("Shop ID", response.get("metadata.shop_id").toString().trim());
//        chargeInfo.put("Order ID", response.get("metadata.order_id").toString().trim());
        chargeInfo.put("Customer email", response.get("metadata.customer_email").toString().trim());
        chargeInfo.put("Checkout ID", response.get("metadata.checkout_id").toString().trim());
        chargeInfo.put("Billing name", response.get("metadata.customer_billing_name").toString().trim());

        return chargeInfo;
    }

    @Step
    public HashMap<String, HashMap<String, String>> getChargeInfoRefunds(String chargeId, String userName) {
        HashMap<String, HashMap<String, String>> chargeInfoRefunds = new HashMap<>();
        JsonPath response = getAChargeResponseBody(chargeId, userName);

        int refundSize = response.getList("refunds.data").size();
        if (refundSize > 0) {
            for (int i = 0; i < refundSize; i++) {
                HashMap<String, String> refundDetails = new HashMap<>();
                refundDetails.put("Amount", response.get("refunds.data[" + i + "].amount").toString().trim());
                refundDetails.put("Currency", response.get("refunds.data[" + i + "].currency").toString().trim());
                refundDetails.put("Charge ID", response.get("refunds.data[" + i + "].charge").toString().trim());
                refundDetails.put("Status", response.get("refunds.data[" + i + "].status").toString().trim());
                String refundTrxId = response.get("refunds.data[" + i + "].id").toString().trim();
                chargeInfoRefunds.put(refundTrxId, refundDetails);
            }
        }
        return chargeInfoRefunds;
    }

    @Step
    public HashMap<String, String> getChargeInfoSPay(String chargeId, String userName, String connectedId) {
        HashMap<String, String> chargeInfo = new HashMap<>();

        return chargeInfo;
    }

    public String getDisputeId(String chargeId, String userName, String connectedId) {
        return getAChargeResponseBody(chargeId, userName, connectedId).get("dispute");
    }

    @Step
    public HashMap<String, String> getDisputeResponse(String disputeId, String userName, String connectedId) {
        HashMap<String, String> disputeResponse = new HashMap<>();
        JsonPath response = getADisputeResponseBody(disputeId, userName, connectedId);

        disputeResponse.put("id", response.get("id"));
        disputeResponse.put("Product description", response.get("evidence.product_description"));
        disputeResponse.put("Customer name", response.get("evidence.customer_name"));
        disputeResponse.put("Customer email", response.get("evidence.customer_email_address"));
        disputeResponse.put("Customer signature", response.get("evidence.customer_signature"));
        disputeResponse.put("Billing address", response.get("evidence.billing_address"));
        disputeResponse.put("Receipt", response.get("evidence.receipt"));
        disputeResponse.put("Customer communication", response.get("evidence.customer_communication"));
        disputeResponse.put("Shipping address", response.get("evidence.shipping_address"));
        disputeResponse.put("Date of shipment", response.get("evidence.shipping_date"));
        disputeResponse.put("Package carrier", response.get("evidence.shipping_carrier"));
        disputeResponse.put("Tracking number", response.get("evidence.shipping_tracking_number"));
        disputeResponse.put("Proof of shipping", response.get("evidence.shipping_documentation"));
        disputeResponse.put("Proof of access or downloading product", response.get("evidence.access_activity_log"));
        disputeResponse.put("Additional evidence or statements text", response.get("evidence.uncategorized_text"));
        disputeResponse.put("Additional evidence or statements file", response.get("evidence.uncategorized_file"));

        return disputeResponse;
    }

}
