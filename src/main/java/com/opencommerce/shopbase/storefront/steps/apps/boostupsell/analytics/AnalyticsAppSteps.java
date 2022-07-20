package com.opencommerce.shopbase.storefront.steps.apps.boostupsell.analytics;

import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.storefront.pages.apps.boostupsell.AnalyticsAppPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static common.utilities.DateTimeUtil.addDays;
import static common.utilities.DateTimeUtil.getTodayByFormat;
import static java.util.Arrays.asList;

public class AnalyticsAppSteps extends ScenarioSteps {
    AnalyticsAppPage analyticsAppPage;


    public int getTotalOrder(String access) {
        int totalOrder = 0;
        String api = "https://" + shop + "/admin/" + "/statistics/v2/total-orders.json?sources=shopbase,total,usell,sdk";
        JsonPath js = analyticsAppPage.getAPISbase(api, access);
        totalOrder = js.get("products.findAll{it.title");
        return totalOrder;
    }

    String shop = LoadObject.getProperty("shop");
    String shop_id = LoadObject.getProperty("shop_id");
    String access_token = "";

    public List<Object> getAnalyticsByAPI(String chartType, String time) {
        String timeRange = getTimeRange(time, "yyyy-MM-dd");

        if (access_token.isEmpty()) {
            access_token = analyticsAppPage.getAccessTokenShopBase();
        }
        String api = "https://" + shop + "/admin/statistics/v2/" + chartType + ".json?" + timeRange + "&sources=shopbase,total,usell,sdk" + "&shop_ids=" + shop_id;
        System.out.println(api);
        JsonPath js = analyticsAppPage.getAPISbase(api, access_token);
        List<Object> ana = new ArrayList<>();

        Object total;
        Object usell;
        Object shopbase;


        switch (chartType) {

            case "total-sales":
                shopbase = Double.parseDouble(getData(js, "total_sales.shopbase"));
                usell = Double.parseDouble(getData(js, "total_sales.usell"));
                total = Double.parseDouble(getData(js, "total_sales.total"));
                ana = asList(usell, shopbase, total);
                break;
            case "shop-aov":
                shopbase = Double.parseDouble(getData(js, "aov.shopbase"));
                usell = Double.parseDouble(getData(js, "aov.usell"));
                total = Double.parseDouble(getData(js, "aov.total"));
                ana = asList(usell, shopbase, total);
                break;
            case "actions":
                int viewProduct = Integer.parseInt(getData(js, "ViewContent.is_tracked_previous_action"));
                int addToCart = Integer.parseInt(getData(js, "AddToCart.is_tracked_previous_action"));
                int reachedCheckout = Integer.parseInt(getData(js, "ReachedCheckout.is_tracked_previous_action"));
                int sessionConverted = Integer.parseInt(getData(js, "SessionConverted.is_tracked_previous_action"));
                ana = asList(viewProduct, addToCart, reachedCheckout, sessionConverted);
                break;
            case "total-orders":
                total = Double.parseDouble(getData(js, "total_orders.total"));
                shopbase = Double.parseDouble(getData(js, "total_orders.shopbase"));
                usell = Double.parseDouble(getData(js, "total_orders.usell"));
                ana = asList(usell, shopbase, total);
                break;
        }

        return ana;

    }

    public String getData(JsonPath jsonPath, String key) {
        Object data = analyticsAppPage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    private String getTimeRange(String time, String format) {
        String from = "";
        String to = "";
        String today = getTodayByFormat(format);
        switch (time) {
            case "TODAY":
                to = from = today;
                break;
            case "YESTERDAY":
                to = from = addDays(today, -1, format);
                break;
            case "LAST 7 DAYS":
                to = today;
                from = addDays(to, -7, format);
                break;
        }
        String date = "from_time=" + from + "&to_time=" + to;
        return date;
    }

    public Double convertNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String percent = decimalFormat.format(number).replace(".00", "");
        Double result = Double.parseDouble(percent);
        return result;
    }

    @Step
    public void logInfor(String s) {
        System.out.println(s);
    }
}
