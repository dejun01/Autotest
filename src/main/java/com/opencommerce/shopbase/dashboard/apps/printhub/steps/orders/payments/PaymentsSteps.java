package com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.payments;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.payments.PaymentsPage;
import common.utilities.LoadObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsSteps extends ScenarioSteps {
    PaymentsPage paymentsPage;

    public void verifyOutStandingBalance(Float outstandingBalance) {
        paymentsPage.verifyOutStandingBalance(outstandingBalance);
    }

    public void verifyNextPayment(float nextPayment) {
        paymentsPage.verifyNextPayment(nextPayment);
    }

    public void verifyEnablePayNowbtn(boolean isEnable) {
        paymentsPage.verifyEnablePayNowbtn(isEnable);
    }

    public String getAccessTokenShopBase() {
        return paymentsPage.getAccessTokenShopBase();
    }

    public String getAccessTokenShopBaseByShop(String shopName, String username, String password) {
        return paymentsPage.getAccessTokenShopBaseByShop(shopName, username, password);
    }

    public void createPayment(String shopId, String shop_name, String accessToken) {
        paymentsPage.createPayment(shopId, shop_name, accessToken);
    }

    public int countPaymentLog() {
        paymentsPage.waitForEverythingComplete(20);
        return paymentsPage.countPaymentLog();
    }

    public void switchToFirstPaymentLogPage() {
        paymentsPage.switchToFirstPaymentLogPage();
    }

    public void verifyDetailsColumn(String details) {
        Assert.assertEquals(details, paymentsPage.getDetailColumn());
    }

    public void verifyAmountColumn(Float amount, int index) {
        paymentsPage.verifyAmountColumn(amount, index);
    }

    public void verifyStatusColumn(String status, int index) {
        paymentsPage.verifyStatusColumn(status, index);
    }

    public void openDetailPayment(int index) {
        paymentsPage.openDetailPayment(index);
    }

    public void verifyFreeType(String freeType) {
        paymentsPage.verifyFreeType(freeType);
    }

    public void verifyDetails(String details) {
        paymentsPage.verifyDetails(details);
    }

    public void verifyAmount(Float amount) {
        paymentsPage.verifyAmount(amount);
    }

    public void verifyStatus(String status) {
        paymentsPage.verifyStatus(status);
    }

    public void verifyAmountAtTimeline(Float amountTimeline) {
        paymentsPage.verifyAmountAtTimeline(amountTimeline);
    }

    public void verifyPaymentMethod(String paymentMethod) {
        paymentsPage.verifyPaymentMethod(paymentMethod);
    }

    public void viewNextPayment() {
        paymentsPage.viewNextPayment();
    }

    public void clickPayNowbtn() {
        paymentsPage.clickBtn("Pay now");
    }

    public void clickCanCelBtn() {
        paymentsPage.clickBtn("Cancel");
    }

    public void clickConfirmPaymentBtn() {
        paymentsPage.clickBtn("Confirm payment");
    }

    public void clickBreadcrum() {
        paymentsPage.clickBreadcrum();
    }

    public void activateShopBaseBalance(String mess) {
        paymentsPage.activateShopBaseBalance(mess);
    }

    public void disableShopBaseBalance(String mess) {
        paymentsPage.deactivateShopBaseBalance(mess);
    }

    public void verifyProcessSuccess() {
        paymentsPage.verifyProcessSuccess();

    }

    public Float getNextPayment() {
        return paymentsPage.getNextPayment();
    }

    public String getOutStandingBalance() {
        return paymentsPage.getOutStandingBalance();
    }

    public void verifyEnableNextPayment(boolean statusNextpayment) {
        viewNextPayment();
        paymentsPage.verifyEnablePayNowBtnInDetail(statusNextpayment);
    }

    public void clickOpenListOrder() {
        paymentsPage.clickOpenListOrder();
    }

    public int getListOrderAndVerifyOrder(String orderId) {
        Integer countOrder = paymentsPage.getListOrderAndVerifyOrder(orderId).size();
        System.out.println("orrder number" + countOrder);
        return countOrder;

    }

    public void fulfilledOrderAPI(String orderID, String accessToken) {
        String gapi = LoadObject.getProperty("gapi.url");
        String url = gapi + "/v1/pbase-supplier-webhook/scalablepress";
        JsonObject requestParams = requestBodyFulfilledOrder(orderID);
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.queryParam("access_token_by_shop_api_key", accessToken);
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    private JsonObject requestBodyFulfilledOrder(String orderID) {
        JsonObject requestParam = new JsonObject();
        JsonObject event = new JsonObject();
        JsonObject meta = new JsonObject();
        requestParam.addProperty("orderId", orderID);
        requestParam.add("event", event);
        event.addProperty("name", "name");
        event.addProperty("description", "description");
        event.addProperty("createdAt", "2014-09-04T21:08:25.401Z");
        event.add("meta", meta);
        meta.addProperty("item", "0");
        meta.addProperty("tracking", "trackingNumber");
        meta.addProperty("service", "US");
        return requestParam;
    }

    private JsonObject event(String productIds) {
        JsonObject event = new JsonObject();
        JsonObject meta = new JsonObject();
        event.addProperty("name", "");
        event.addProperty("description", "");
        event.addProperty("createdAt", "");
        meta.addProperty("item", "");
        meta.addProperty("tracking", "");
        meta.addProperty("service", "");
        event.add("meta", meta);
        return event;
    }


    public String getOrderIdOfScalableAPI(Integer orderId, String access_token) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/phub-order/supplier-order.json?order_id=" + orderId + "&access_token=" + access_token + "&is_phub=true";
        JsonPath js = getAPI(url);
        String id = (String) paymentsPage.getData(js, "supplier_order_ids[0]");
        List ids = (List) paymentsPage.getDataByKey(js, "supplier_order_ids");
        System.out.println(id);
        System.out.println(ids);
        return id;
    }

    @Step
    public Integer getOrderIdAPI(String orderName, String access_token) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/phub-order.json?page=1&limit=50&keyword=&access_token=" + access_token;
        JsonPath js = getAPI(url);
        Integer id = (Integer) paymentsPage.getData(js, "results.findAll{it.name=='" + orderName + "'}.id[0]");
        return id;
    }

    @Step
    private JsonPath getAPI(String api) {
        return paymentsPage.getAPI(api);
    }


    public void createPaymentMethod(String shopId, String method, String accessToken) {
        String gapi = LoadObject.getProperty("gapi.url");
        String url = gapi + "/admin/setting/app?access_token=" + accessToken;
        JsonObject requestParam = requestBodyPaymentMethod(shopId, method);
        paymentsPage.postAPI(url, requestParam);
    }

    private JsonObject requestBodyPaymentMethod(String shopId, String method) {
        JsonObject requestParam = new JsonObject();
        JsonObject settings = new JsonObject();
        requestParam.addProperty("shop_id", Integer.parseInt(shopId));
        requestParam.addProperty("app_code", "phub");
        boolean isActive = false;
        if (method.trim().equalsIgnoreCase("Activate"))
            isActive = true;
        requestParam.add("settings", settings);
        settings.addProperty("enable_charge", isActive);
        return requestParam;
    }

    public Float getCurrentAmount() {
        return paymentsPage.getCurrentAmount();
    }

    public void createPaymentOrderImprort(String shopId, String shop_name, String accessToken) {
        paymentsPage.createPaymentOrderImport(shopId, shop_name, accessToken);
    }
}


