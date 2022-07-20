//package com.opencommerce.shopbase.dashboard.apps.analytics.pages;
//
//import com.opencommerce.shopbase.common.pages.CommonPage;
//import com.opencommerce.shopbase.dashboard.apps.analytics.steps.OrderReferrerInfoSteps;
//import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
//import common.SBasePageObject;
//import common.utilities.LoadObject;
//import net.thucydides.core.annotations.Step;
//import net.thucydides.core.annotations.Steps;
//import org.fluentlenium.core.annotation.Page;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.openqa.selenium.WebDriver;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class OrderReferrerInfoPage extends SBasePageObject {
//    String shopName, shop, shopId, accessToken;
//    @Page
//    UTMAnalyticsPage utmAnalyticsPage;
//    @Page
//    OrderPage orderPage;
//    @Steps
//    OrderReferrerInfoSteps referrerInfoSteps;
//    HashMap<String, List<Object>> first_session;
//    HashMap<String, List<Object>> last_session;
//    List<String> conversion_summary;
//    List<Integer> order_list;
//    String urlCheckout;
//    String totalSession;
//
//    public OrderReferrerInfoPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void init() {
//        referrerInfoSteps = new OrderReferrerInfoSteps();
//        this.first_session = new HashMap<>();
//        this.last_session = new HashMap<>();
//        this.conversion_summary = new ArrayList<>();
//        this.order_list = new ArrayList<>();
//
//        shopName = LoadObject.getProperty("shopname");
//        shop = LoadObject.getProperty("shop");
//        shopId = LoadObject.getProperty("shopid");
//        accessToken = getAccessTokenShopBase();
//    }
//
//    public void loadAndVerifyOrderFilter(List<List<String>> dataTable, List<String> data_list) {
//        JSONObject data_obj = new JSONObject(this.getOrderTrackingData(data_list));
//        JSONObject first_session_obj = (JSONObject) data_obj.get("first_session");
//        referrerInfoSteps.getUtmParametersByDataTable(dataTable, 1, first_session);
//        referrerInfoSteps.getExtraParametersByDataResponse(first_session_obj, first_session, "first_session");
//        verifyUtmDataInTrackingApi(first_session_obj, first_session);
//    }
//
//    public void loadAndVerifyOrderTrackingData(List<List<String>> dataTable, List<String> data_list, String number_of_session) {
//        JSONObject data_obj = new JSONObject(this.getOrderTrackingData(data_list));
//
//        JSONObject first_session_obj = (JSONObject) data_obj.get("first_session");
//        JSONObject last_session_obj = (JSONObject) data_obj.get("last_session");
//
//        totalSession = number_of_session;
//
//        referrerInfoSteps.getUtmParametersByDataTable(dataTable, 1, first_session);
//        referrerInfoSteps.getUtmParametersByDataTable(dataTable, 2, last_session);
//
//        referrerInfoSteps.getExtraParametersByDataTable(dataTable, 1, first_session);
//        referrerInfoSteps.getExtraParametersByDataTable(dataTable, 2, last_session);
//
//        referrerInfoSteps.getExtraParametersByDataResponse(first_session_obj, first_session, "first_session");
//        referrerInfoSteps.getExtraParametersByDataResponse(last_session_obj, last_session, "last_session");
//
//        conversion_summary = getConversionSummaryDataByXpath();
//
//        clickViewDetailsBtn();
//        waitABit(5000);
//
//        verifyConversionDetails(first_session_obj, last_session_obj, number_of_session);
//
//        int order_off_set = referrerInfoSteps.getOrderOffsetByDataResponse(data_obj);
//        int total_session = referrerInfoSteps.getTotalSessionByDataResponse(data_obj);
//        String first_session_from = referrerInfoSteps.getFirstSessionFrom(first_session);
//
//        verifyOrderOffset(conversion_summary, order_off_set);
//        verifyFirstSessionFrom(conversion_summary, first_session_from);
//
//        verifyUtmDataInTrackingApi(first_session_obj, first_session);
//
//        switch (number_of_session) {
//            case "1":
//                // No need to do here - refactor later
//                break;
//            case "3":
//                int session_over_day = referrerInfoSteps.getSessionOverDayByDataResponse(first_session_obj, last_session_obj, total_session);
//                verifySessionOver(conversion_summary, session_over_day);
//                verifyUtmDataInTrackingApi(last_session_obj, last_session);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + number_of_session);
//        }
//    }
//
//    public void openOrdersFilterWithUTM(List<List<String>> dataTable) {
//        List<String> filters = Arrays.asList("Source", "Medium", "Campaign", "Content", "Term");
//        referrerInfoSteps.getUtmParametersByDataTable(dataTable, 1, first_session);
//        clickBtn("More filters");
//        for (int i = 0; i < filters.size(); i++) {
//            clickFilterItemAndInputValue(first_session, filters.get(i), i);
//        }
//        orderPage.clickFilterLabel("Apply");
//    }
//
//    public JSONObject getOrderTrackingData(List<String> data_list) {
//        JSONObject data_obj = new JSONObject();
//        try {
//            URL url = new URL("https://" + shop + "/admin/analytics/tracking.json");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setDoOutput(true);
//            addMultipleRequestProperties(http);
//            http.addRequestProperty("Origin", "https://" + shop);
//            http.addRequestProperty("Referer", "https://" + shop + "/admin/orders/" + data_list.get(0));
//
//            String data = "{\"ref_type\":\"order\",\"order_id\":" + data_list.get(0) + ",\"customer_id\":" + data_list.get(1) + "}";
//            System.out.println(data);
//            byte[] out = data.getBytes(StandardCharsets.UTF_8);
//
//            OutputStream stream = http.getOutputStream();
//            stream.write(out);
//
//            int responseCode = http.getResponseCode();
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//                JSONObject obj = referrerInfoSteps.getDataObject(http);
//                data_obj = (JSONObject) obj.get("data");
//            }
//
//            System.out.println(responseCode + " " + http.getResponseMessage());
//            http.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data_obj;
//    }
//
//    public List<String> getFilterOrderByAPI(List<List<String>> dataTable) {
//        List<String> data_list = new ArrayList<>();
//        referrerInfoSteps.getUtmParametersByDataTable(dataTable, 1, first_session);
//        String url_temp = "https://" + shop + "/admin/orders/v2.json?fields=id,name,created_at,customer,financial_status,fulfillment_status,sales_channel,created_source,total_price,fulfillments,line_items,shop_id,closed_at,product_mappings,source_name,is_risk,note,print_file_status,shipment_status,payment_gateway&line_item_fields=id,fulfillment_status,image_src,title,product_id,variant_title,created_at,quantity,raw_price,holding_time&tab=all&shopType=&orderType=order&page=1&limit=1&sort_field=id&sort_mode=desc&view_product_mapping=true&search_option=order_name&has_tracking_numbers=false&no_tracking_numbers=false&"
//                + "utm_source=" + first_session.get("utm_params").get(0)
//                + "&utm_medium=" + first_session.get("utm_params").get(1)
//                + "&utm_campaign=" + first_session.get("utm_params").get(2)
//                + "&utm_content=" + first_session.get("utm_params").get(3)
//                + "&utm_term=" + first_session.get("utm_params").get(4)
//                + "&until_id=null";
//
//        try {
//            URL url = new URL(url_temp);
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            addMultipleRequestProperties(http);
//            http.addRequestProperty("Referer", "https://" + shop + "/admin/orders?tab=all");
//            int responseCode = http.getResponseCode();
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//                JSONObject obj = referrerInfoSteps.getDataObject(http);
//                JSONArray arr = (JSONArray) obj.get("orders");
//                for (Object o : arr) {
//                    JSONObject new_obj = (JSONObject) o;
//                    String order_id = new_obj.get("id").toString();
//                    data_list.add(order_id);
//                    JSONObject customer_obj = (JSONObject) new_obj.get("customer");
//                    String customer_id = customer_obj.get("id").toString();
//                    data_list.add(customer_id);
//                    System.out.println("Order id: " + order_id + " | Customer id: " + customer_id);
//                }
//            }
//            http.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data_list;
//    }
//
//    public void addMultipleRequestProperties(HttpURLConnection http) {
//        http.addRequestProperty("Connection", "keep-alive");
//        http.addRequestProperty("Pragma", "no-cache");
//        http.addRequestProperty("Cache-Control", "no-cache");
//        http.addRequestProperty("X-ShopBase-Access-Token", accessToken);
//        http.addRequestProperty("sec-ch-ua-mobile", "?0");
//        http.addRequestProperty("sec-ch-ua-platform", "\"Windows\"");
//        http.addRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"");
//        http.addRequestProperty("Sec-Fetch-Site", "same-origin");
//        http.addRequestProperty("Sec-Fetch-Mode", "cors");
//        http.addRequestProperty("Sec-Fetch-Dest", "empty");
//        http.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
//        http.addRequestProperty("Content-Type", "text/plain;charset=UTF-8");
//        http.addRequestProperty("Accept", "*/*");
//        http.addRequestProperty("Accept-Language", "en-US,en;q=0.9");
//        http.addRequestProperty("Cookie", getDriver().manage().getCookies().toString());
//    }
//
//    public void verifyConversionDetails(JSONObject first_session_obj, JSONObject last_session_obj, String number_of_session) {
//        verifyTotalSessions(getTotalSessionsByXpath(), totalSession);
//        verifyFirstSessionFrom(conversion_summary, getFirstSessionFromByXpath());
//
//        switch (number_of_session) {
//            case "1":
//                // No need to do here - refactor later
//                break;
//            case "3":
//                verifyDaysToConversion(getDaysToConversionByXpath(), referrerInfoSteps.getDaysToConversionByDataResponse(first_session_obj, last_session_obj));
//                verifyReturnedTimes(getReturnedTimesByXpath(), Integer.parseInt(totalSession), getDaysToConversionByXpath());
//                verifyConvertedAfter(last_session, getConvertedAfterByXpath());
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + number_of_session);
//        }
//    }
//
//    public void verifyOrderOffset(List<String> conversion_summary, int order_offset) {
//        String order_offset_display;
//        int n = 3;
//        boolean status = true;
//        while (status) {
//            order_offset_display = conversion_summary.get(0).substring(0, n).replaceAll("\\s+", "");
//            if (isNumeric(order_offset_display)) {
//                assertThat(order_offset).isEqualTo(Integer.parseInt(order_offset_display));
//                status = false;
//            } else {
//                n--;
//            }
//        }
//    }
//
//    public void verifyTotalSessions(int totalSessions, String numberOfSession) {
//        assertThat(Integer.valueOf(numberOfSession)).isEqualTo(totalSessions);
//    }
//
//    public void verifyDaysToConversion(int days_xpath, int days_response) {
//        assertThat(days_response).isEqualTo(days_xpath);
//    }
//
//    public void verifyReturnedTimes(String txtReturn, int totalSession, int dayToConversion) {
//        String returnedTimes;
//        int n = 12;
//        boolean status = true;
//        while (status) {
//            returnedTimes = txtReturn.substring(9, n).replaceAll("\\s+", "");
//            if (isNumeric(returnedTimes)) {
//                assertThat((totalSession - dayToConversion - 1)).isEqualTo(Integer.parseInt(returnedTimes));
//                status = false;
//            } else {
//                n--;
//            }
//        }
//    }
//
//    public void verifyConvertedAfter(HashMap<String, List<Object>> last_session, String txtConvertedAfter) {
//        String channel = last_session.get("utm_params").get(0).toString();
//        assertThat(txtConvertedAfter).contains(channel);
//    }
//
//    public void verifyFirstSessionFrom(List<String> conversion_summary, String first_session_from) {
//        String _first_session_from = conversion_summary.get(1).substring(17);
//        assertThat(first_session_from).isEqualTo(_first_session_from);
//    }
//
//    public void verifySessionOver(List<String> conversion_summary, int sessionOverDay) {
//        String _sessionOverDay;
//        int n = 3;
//        boolean status = true;
//        while (status) {
//            _sessionOverDay = conversion_summary.get(2).substring(0, n).replaceAll("\\s+", "");
//            if (isNumeric(_sessionOverDay)) {
//                assertThat(sessionOverDay).isEqualTo(Integer.parseInt(_sessionOverDay));
//                status = false;
//            } else {
//                n--;
//            }
//        }
//    }
//
//    public void verifyUtmDataInTrackingApi(JSONObject response, HashMap<String, List<Object>> session) {
//        assertThat(session.get("utm_params").get(0)).isEqualTo(response.get("utm_source").toString());
//        assertThat(session.get("utm_params").get(1)).isEqualTo(response.get("utm_medium").toString());
//        assertThat(session.get("utm_params").get(2)).isEqualTo(response.get("utm_campaign").toString());
//        assertThat(session.get("utm_params").get(3)).isEqualTo(response.get("utm_content").toString());
//        assertThat(session.get("utm_params").get(4)).isEqualTo(response.get("utm_term").toString());
//    }
//
//    public void copyCheckoutUrl(CommonPage commonPage) {
//        urlCheckout = commonPage.getCurrentUrl();
//    }
//
//    public void openCheckoutUrlManyTimes(int times) {
//        for(int i = 0; i < times; i++){
//            closeBrowser();
//            openUrl(urlCheckout);
//            waitABit(7000);
//        }
//        utmAnalyticsPage.maximizeWindow();
//        urlCheckout = "";
//    }
//
//    @Step
//    public String getFirstSessionFromByXpath() {
//        String xpath = "//*[normalize-space()='Conversion details']/following::*[contains(text(),'1st session')]";
//        return getTextValue(xpath).substring(17);
//    }
//
//    @Step
//    public String getReturnedTimesByXpath() {
//        String xpath = "//*[normalize-space()='Conversion details']/following::*[contains(text(),'Returned') and contains(text(),'time')]";
//        return getTextValue(xpath);
//    }
//
//    @Step
//    public String getConvertedAfterByXpath() {
//        String xpath = "//*[normalize-space()='Conversion details']/following::*[contains(text(),'Converted')]";
//        return getTextValue(xpath);
//    }
//
//    @Step
//    public int getDaysToConversionByXpath() {
//        String xpath = "(//div[@class='s-modal-body']//h2)[2]";
//        return Integer.parseInt(getTextValue(xpath));
//    }
//
//    @Step
//    public int getTotalSessionsByXpath() {
//        String xpath = "(//div[@class='s-modal-body']//h2)[1]";
//        return Integer.parseInt(getTextValue(xpath));
//    }
//
//    @Step
//    public void clickViewDetailsBtn() {
//        String xpath = "//button[contains(@class, 'button linkPrimary')]";
//        clickOnElement(xpath);
//    }
//
//    @Step
//    public List<String> getConversionSummaryDataByXpath() {
//        List<String> list = new ArrayList<>();
//        String xpath = "(//div[@class='card__header']//div[contains(@class,'align-items-start')]//p)";
//        for (int i = 1; i <= 3; i++) {
//            String value = getTextValue(xpath + "[" + i + "]");
//            list.add(value);
//        }
//        return list;
//    }
//
//    @Step
//    public void clickFilterItemAndInputValue(HashMap<String, List<Object>> first_session, String filter_item, int index) {
//        orderPage.clickFilterLabel(filter_item);
//        orderPage.inputValueByFieldLabel(("Search by " + filter_item.toLowerCase()), first_session.get("utm_params").get(index).toString());
//    }
//}