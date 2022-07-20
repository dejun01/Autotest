package com.opencommerce.shopbase.dashboard.settings.api;

import com.opencommerce.shopbase.APICommon;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayPalPaymentSettingsAPI{
    APICommon apiCommon;

    private String shopDomain = LoadObject.getProperty("shop");
    private String url = "https://" + shopDomain + "/admin/payments/paypal_payment_setting.json";

    @Step
    public String getBrandName() {
        String accessToken = apiCommon.getAccessTokenShopBase();
        JsonPath response = apiCommon.getAPISbase(url, accessToken);
        apiCommon.waiting(7000);
        return response.get("brand_name");
    }
}
