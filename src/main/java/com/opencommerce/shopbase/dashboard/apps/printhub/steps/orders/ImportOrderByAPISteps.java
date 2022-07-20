package com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.ImportOrderByAPIPage;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ImportOrderByAPISteps {
    ImportOrderByAPIPage importOrderByAPIPage;

    @Step
    public String getAccessToken() {
        return importOrderByAPIPage.getAccessTokenShopBase();
    }

    @Step
    public void createOrderPhubByAPI(String url, String accessToken, String name, String shipping_name, String address1, String shipping_address_2, String city, String zip, String province, String country, String shipping_phone, String line_items, String line_name, String reference_id, int parseInt, String lineitems_sku, String products_name, String variant_title, String color, String size, String artwork_front_url, String artwork_back_url, String mockup_front_url, String mockup_back_url) {
        com.google.gson.JsonObject requestParam = new com.google.gson.JsonObject();
        com.google.gson.JsonArray orders = new JsonArray();
        com.google.gson.JsonObject order = new JsonObject();
        requestParam.addProperty("name", name);
        requestParam.addProperty("shipping_name", shipping_name);
        requestParam.addProperty("shipping_address_1",address1);
        requestParam.addProperty("shipping_address_2", shipping_address_2);
        requestParam.addProperty("shipping_city", city);
        requestParam.addProperty("shipping_zip", zip);
        requestParam.addProperty("shipping_province", province);
        requestParam.addProperty("shipping_country", country);
        requestParam.addProperty("shipping_phone", shipping_phone);
        requestParam.add("line_items", orders);
        order.addProperty("name", line_name);
        order.addProperty("reference_id", reference_id);
        order.addProperty("lineitem_quantity", parseInt);
        order.addProperty("lineitem_sku", lineitems_sku);
        order.addProperty("product_name", products_name);
        order.addProperty("product_id", variant_title);
        order.addProperty("color", color);
        order.addProperty("size", size);
        order.addProperty("artwork_front_url", artwork_front_url);
        order.addProperty("artwork_back_url", artwork_back_url);
        order.addProperty("mockup_front_url", mockup_front_url);
        order.addProperty("mockup_back_url", mockup_back_url);
        orders.add(order);
        importOrderByAPIPage.postAPISbase(url, requestParam, accessToken);
    }

    @Step
    public String getOrderName(String url, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(url, accessToken);
        return jsonPath.get("order_name");

    }

    @Step
    public int getOrderId(String url, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(url, accessToken);
        return ((HashMap<String, Integer>) jsonPath.getList("mpo").get(0)).get("id");
    }

    @Step
    public void searchOrder(String order_name) {
        importOrderByAPIPage.searchOrder(order_name);
    }

    @Step
    public void verifyInforOrderInOrderList(String order_name, String date, String status) {
        assertThat(importOrderByAPIPage.getDate(order_name, "Date")).isEqualTo(date);
        assertThat(importOrderByAPIPage.getDate(order_name, "status")).isEqualTo(status);
    }

    @Step
    public void clickOrderName(String order_name) {
        importOrderByAPIPage.clickOrderName(order_name);

    }

    @Step
    public void verifyOrderDetail(String products_name, String variant_title, String lineitems_sku, String address1, String city, String province, String country, String zip, String color, String size) {
        assertThat(importOrderByAPIPage.getProduct()).isEqualTo(products_name);
        assertThat(importOrderByAPIPage.getVariantTitle().trim()).isEqualTo(variant_title + " " + "/" + " " + color + " " + "/" + " " + size);
        assertThat(importOrderByAPIPage.getLineitemsSku().replace("SKU:", "").trim()).isEqualTo(lineitems_sku);
        assertThat(importOrderByAPIPage.getShippingAddress(2)).isEqualTo(address1 + " " + city + " " + province);
        if(country.equals("US")) {
            assertThat(importOrderByAPIPage.getShippingAddress(3)).isEqualTo(zip + " " + country.replace("US","United States"));
        }
    }

    @Step
    public String getCity(String urlGet, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(urlGet, accessToken);
        return ((HashMap<String, String>) jsonPath.get("shipping_address")).get("city");
    }

    @Step
    public String getProvince(String urlGet, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(urlGet, accessToken);
        return ((HashMap<String, String>) jsonPath.get("shipping_address")).get("province");
    }

    @Step
    public String getCountry(String urlGet, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(urlGet, accessToken);
        return ((HashMap<String, String>) jsonPath.get("shipping_address")).get("country");
    }

    @Step
    public String getZip(String urlGet, String accessToken) {
        JsonPath jsonPath = importOrderByAPIPage.getAPISbase(urlGet, accessToken);
        return ((HashMap<String, String>) jsonPath.get("shipping_address")).get("zip");
    }

    @Step
    public void deleteOrder(String url, String accessToken) {
        importOrderByAPIPage.deleteAPI(url, accessToken);
    }

    @Step
    public void verifyNotData(String noti) {
        assertThat(importOrderByAPIPage.getNotData()).contains(noti);
    }

    @Step
    public void paymentOrder(String order_name) {
        importOrderByAPIPage.clickCheckBox(order_name);
        importOrderByAPIPage.clickBtn("Actions");
        importOrderByAPIPage.clickBTAction();
        importOrderByAPIPage.clickBtn("Confirm payment");
    }

    @Step
    public void selectApp(String app) {
        importOrderByAPIPage.selectApp(app);
    }

    public void verifyOrder(String orderName) {
        importOrderByAPIPage.verifyTextPresent(orderName, true);
    }
}
