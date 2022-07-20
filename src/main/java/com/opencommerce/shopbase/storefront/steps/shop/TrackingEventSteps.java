package com.opencommerce.shopbase.storefront.steps.shop;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.storefront.pages.shop.TrackingEventPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.IOUtils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import static net.serenitybdd.rest.SerenityRest.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class TrackingEventSteps {
    TrackingEventPage tePage;

    @Step
    public void verifyTrackingEvent(String type, String event, String key, String expect) {
        if (!event.isEmpty()) {
            tePage.verifyTrackingEvent(type, event, key, expect);
        } else {
            tePage.verifyTrackingEventExistedByValue(type, key, expect);
        }
    }

    @Step
    public void verifyTrackingEventOfItem(String type, String event, String key, String expect) {
        tePage.verifyTrackingEventOfItem(type, event, key, expect);
    }

    @Step
    public String getOrderIDAtCheckout() {
        return tePage.getOrderIDAtCheckout();
    }

    @Step
    public void clearTrackingEvent() {
        tePage.clearTrackingEvent(1);
    }

    @Step
    public String getDataFromFile (String filePath) throws IOException {
        FileInputStream file = new FileInputStream("src/test/resources/dataset"+ filePath);
        return IOUtils.toString(file, StandardCharsets.UTF_8);
    }

    @Step
    public void addCustomScriptByAPI(String scriptHead, String scriptBody) {
        String shopDomain = LoadObject.getProperty("shop");
        String shopID = LoadObject.getProperty("shop_id");
        String token = tePage.getAccessTokenShopBase(shopDomain);

        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("scripts_head", scriptHead);
        requestParams.addProperty("scripts_body", scriptBody);
        requestParams.addProperty("shop_id", Integer.parseInt(shopID));

        Response response = given().header("Content-Type", "application/json").header("x-shopbase-access-token", token).body(requestParams.toString()).put("https://" + shopDomain+ "/admin/setting/online-store-preferences.json");
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);

    }
}