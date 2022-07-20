package com.opencommerce.shopbase.dashboard.orders.api;

import com.opencommerce.shopbase.APICommon;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionsAPI {
    APICommon apiCommon;

    private String shopDomain = LoadObject.getProperty("shop");

    public String getUrl(int orderId) {
        return "https://" + shopDomain + "/admin/orders/" + orderId + "/transactions.json";
    }

    private JsonPath getResponseBody(String apiUrl) {
        String accessToken = apiCommon.getAccessTokenShopBase();
        JsonPath response = apiCommon.getAPISbase(apiUrl, accessToken);
        return response;
    }


    @Step
    public String getStripeChargedID(String apiUrl) {
        return getTrxIDByTransactionKind(apiUrl, "capture");
    }

    @Step
    public String getStripePaymentIntentID(String apiUrl) {
        return getTrxIDByTransactionKind(apiUrl, "authorization");
    }

    @Step
    public String getStripeRefundID(String apiUrl) {
        return getTrxIDByTransactionKind(apiUrl, "refund");
    }

    @Step
    public String getShopID(String apiUrl) {
        JsonPath response = getResponseBody(apiUrl);
        return response.get("transactions[0].shop_id").toString();
    }

    private String getTrxIDByTransactionKind(String apiUrl, String kind) {
        String accessToken = apiCommon.getAccessTokenShopBase();
        JsonPath response = apiCommon.getAPISbase(apiUrl, accessToken);
        int size = response.getList("transactions").size();
        String trxID = "";
        List<String> listOfTrxID = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String _kind = response.get("transactions[" + i + "].kind");
            if (kind.equalsIgnoreCase(_kind)) {
                listOfTrxID.add(response.get("transactions[" + i + "].authorization"));
            }
        }
        if (!listOfTrxID.isEmpty()) {
            trxID = listOfTrxID.toString().replace("[", "").replace("]", "");
        }
        return trxID;
    }
}
