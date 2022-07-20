package com.opencommerce.shopbase.dashboard.settings.api;

import com.opencommerce.shopbase.APICommon;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentsAPI{
    APICommon apiCommon;

    private String shopDomain = LoadObject.getProperty("shop");
    private String url = "https://" + shopDomain + "/admin/payments.json";

    @Step
    public HashMap<String, List<Integer>> getListOfActivePaymentMethod() {
        String accessToken = apiCommon.getAccessTokenShopBase();
        JsonPath response = apiCommon.getAPISbase(url, accessToken);
        int size = response.getList("payment_methods").size();
        System.out.println(size);
        HashMap<String, List<Integer>> paymentList = new HashMap<>();
        List<Integer> listOfCreditCardPaymentMethodId = new ArrayList<>();
        List<Integer> listOfPayPalPaymentMethodId = new ArrayList<>();
        boolean status;
        boolean isDead;
        int id;
        String code = "";
        for (int i = 0; i < size; i++) {
            status = response.get("payment_methods[" + i + "].active");
            isDead = response.get("payment_methods[" + i + "].is_dead");
            id = response.get("payment_methods[" + i + "].id");
            code = response.get("payment_methods[" + i + "].code");
            if (status && !isDead) {
                if (!"paypal-express".equalsIgnoreCase(code)) {
                    listOfCreditCardPaymentMethodId.add(id);
                } else {
                    listOfPayPalPaymentMethodId.add(id);
                }
            }
        }
        paymentList.put("credit-card",listOfCreditCardPaymentMethodId);
        paymentList.put("paypal-express",listOfPayPalPaymentMethodId);
        return paymentList;
    }
}
