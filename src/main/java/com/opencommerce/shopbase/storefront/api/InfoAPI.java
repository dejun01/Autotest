package com.opencommerce.shopbase.storefront.api;

import com.opencommerce.shopbase.APICommon;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

public class InfoAPI {
    APICommon apiCommon;

    private String getInfoApiUrl(String checkoutToken) {
        String shopDomain = LoadObject.getProperty("shop");
        return "https://" + shopDomain + "/api/checkout/" + checkoutToken + "/info.json";
    }

    @Step
    public Integer getPaymentMethodIDAfterCheckout(String checkoutToken) {
        String url = getInfoApiUrl(checkoutToken);
        JsonPath response = apiCommon.getAPI(url);
        return response.get("result.info.payment_method_id");
    }

    public Integer getOrderID(String checkoutToken) {
        String url = getInfoApiUrl(checkoutToken);
        apiCommon.waiting(10000);
        JsonPath response = apiCommon.getAPI(url);
        return response.get("result.order.id");
    }

    public Long getOrderBYID(String checkoutToken) {
        String url = getInfoApiUrl(checkoutToken);
        apiCommon.waiting(10000);
        JsonPath response = apiCommon.getAPI(url);
        return response.get("result.order.id");
    }
}
