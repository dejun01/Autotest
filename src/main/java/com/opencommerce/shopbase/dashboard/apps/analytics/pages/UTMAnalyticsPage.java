package com.opencommerce.shopbase.dashboard.apps.analytics.pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;

import static common.utilities.DateTimeUtil.getNextByFormat;
import static common.utilities.DateTimeUtil.getTodayByFormat;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class UTMAnalyticsPage extends SBasePageObject {
    public UTMAnalyticsPage(WebDriver driver) {
        super(driver);
    }

    public String shopname = LoadObject.getProperty("shopname");

    String shop = LoadObject.getProperty("shop");
    String shopId = LoadObject.getProperty("shopid");
    String accessToken = getAccessTokenShopBase();


    public JsonPath getUTMbyAPI(String shopType, String reportBy) {
        switch (shopType) {
            case "sbase":
                shopType = "total_sales";
                break;
            case "pbase":
                shopType = "total_profit";
                break;
        }
        accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/analytics.json" + "?access_token=" + accessToken + "&shop_ids=" + shopId;
        JsonObject requestParams = new JsonObject();

        String today = getTodayByFormat("yyyy-MM-dd");
        String nextday = getNextByFormat("yyyy-MM-dd");

        requestParams.addProperty("report_type", "utm_campaign");
        requestParams.addProperty("from_time", today + "T00:00:00");
        requestParams.addProperty("to_time", nextday + "T00:00:00");
        requestParams.addProperty("limit", 50);
        requestParams.addProperty("aov_field", shopType);

        JsonArray group_by = new JsonArray();
        group_by.add(reportBy);
        requestParams.add("group_by", group_by);

        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        return jp;
    }

    public String getDataUTM(JsonPath jp, String reportBy, String utmValue, String keyValue) {
        String value = "";
        try {
            value = jp.get("data.findAll{it." + reportBy + "=='" + utmValue + "'}." + keyValue).toString().replaceAll("\\[", "").replaceAll("\\]", "");

        } catch (NullPointerException e) {
            value = "0";
        }
        if ("".equals(value) || "null".equals(value)) {
            value = "0";
        }
        return value;
    }

    public void openLinkWithUTM(String productName, String utmSource, String utmMedium, String utmCampaign, String utmContent, String utmTerm, String referrer) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/products/" + productName + "?utm_source=" + utmSource + "&utm_medium=" + utmMedium + "&utm_campaign=" + utmCampaign + "&utm_content=" + utmContent + "&utm_term=" + utmTerm + referrer;
        openUrl(url);
    }

    public JsonPath getDataUTMSaleReport(String shopType, String reportBy, String reportType) {
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        String today = getTodayByFormat("yyyy-MM-dd");
        String nextday = getNextByFormat("yyyy-MM-dd");
        String url = "https://" + shop + "/admin/analytics.json?shop_ids=" + shopId + "&access_token=" + accessToken;
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("aov_field", shopType);
        JsonArray ar_1 = new JsonArray();
        requestParams.add("filters", ar_1);
        requestParams.addProperty("from_time", today + "T00:00:00");
        requestParams.addProperty("to_time", nextday + "T00:00:00");
        JsonArray ar_2 = new JsonArray();
        ar_2.add("product_title");
        ar_2.add(reportBy);
        requestParams.add("group_by", ar_2);
        JsonArray ar_3 = new JsonArray();
        ar_3.add("product_title asc");
        requestParams.add("order_by", ar_3);
        requestParams.addProperty("limit", 50);
        requestParams.addProperty("page", 1);
        requestParams.addProperty("report_type", reportType);
        requestParams.addProperty("shopIds", shopId);
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        return jp;
    }

    public String getDataAnaUTMSalesReport(JsonPath js, String reportBy, String filterValue, String productFilter, String title, String keyValue) {
        String getData = String.format("data.findAll{data -> data.%s == '%s' && data.%s == '%s'}.%s", reportBy, filterValue, productFilter, title, keyValue);
        String value = js.get(getData).toString().replaceAll("\\[", "").replaceAll("\\]", "");
        if ("".equals(value) || "null".equals(value)) {
            value = "0";
        }
        return value;
    }

    public int countNumberOfUtmChartSalesReport() {
        String xpath = "//div[@class='sales-data']//thead//tr[contains(@class,'traffic-source')]//th";
        return countElementByXpath(xpath);
    }

    public void verifyAdditionalColumn(int valueBefore, int valueAfter) {
        assertThat(valueAfter).isEqualTo(valueBefore + 1);
    }

    public void verifyValueInAdditionalColumn(String custom_camp) {
        String xpathSize = "//div[@class='table-responsive']//thead//tr[contains(@class,'traffic-source')]//th";
        int count = countElementByXpath(xpathSize);
        for (int i = 1; i <= count; i++) {
            String xpath = "(//div[@class='table-responsive']//thead//tr[contains(@class,'traffic-source')]//th)[" + i + "]";
            String value = getTextValue(xpath);
            if (value.equals("UTM Campaign")) {
                String xpath2 = "(//div[@class='table-responsive']//tbody//td[normalize-space()='custom_source / custom_medium']//parent::tr//td)[" + i + "]";
                String campaignActual = getTextValue(xpath2);
                assertThat(custom_camp).isEqualTo(campaignActual);
                break;
            }
        }
    }
}

