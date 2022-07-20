package com.opencommerce.shopbase.dashboard.apps.analytics.pages;

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
import java.util.*;

import static common.utilities.DateTimeUtil.getNextByFormat;
import static common.utilities.DateTimeUtil.getTodayByFormat;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyticsPage extends SBasePageObject {
    public AnalyticsPage(WebDriver driver) {
        super(driver);
    }

    public String shopname = LoadObject.getProperty("shopname");
    String shop = LoadObject.getProperty("shop");
    String shopid = LoadObject.getProperty("shopid");
    String accessToken = getAccessTokenShopBase();


    public String getMainChartValue(String chartName) {
        String xpath = "//div[child::h4[normalize-space()='" + chartName + "']]//following-sibling::div//h2";
        waitElementToBeVisible(xpath);
        String value = getText(xpath);
        return value;
    }

    public String getValueOnlineStoreConversionRate(String valueName) {
        String attribute = "";
        switch (valueName) {
            case "Session":
                attribute = "auto-sessions";
                break;
            case "View Product":
                attribute = "auto-view-content";
                break;
            case "Add To Card":
                attribute = "auto-add-to-cart";
                break;
            case "Reached Checkout":
                attribute = "auto-reached-checkout";
                break;
            case "Session Converted":
                attribute = "auto-session-converted";
                break;
            default:
                fail();
        }
        String xpath = "//h4[normalize-space()='Online store conversion rate']//preceding::div[@class='hidden-items hidden']//input[@id='" + attribute + "']";
        String value = getAttributeValue(xpath, "val");
        return value;
    }

    public String getValueTopProduct(String productName) {
        String xpath = "//div[child::p[text()='Top products by units sold']]//following-sibling::div[contains(@class,'no-product')]";
        String xpathUnitArray = "//div[@class='product-list new']//tbody//tr";
        int size = getDriver().findElements(By.xpath(xpathUnitArray)).size();
        if (isElementVisible(xpath, 5)) {
            System.out.println("Top Product of " + productName + ": 0");
            return "0";
        } else if (size == 1) {
            String xpathUnit = "//div[@class='product-list new']//tr[descendant::span[normalize-space()='" + productName + "']]//td[4]";
            waitElementToBeVisible(xpathUnit);
            String value = getText(xpathUnit);
            System.out.println("Top Product of " + productName + ": " + value);
            return value;
        } else {
            int value = 0;
            for (int i = 2; i <= size + 1; i++) {
                String xpathUnit = "(//div[@class='product-list new']//tr)[" + i + "]//td[4]";
                waitElementToBeVisible(xpathUnit);
                value = value + Integer.parseInt(getText(xpathUnit));
            }
            System.out.println(value);
            return Integer.toString(value);
        }
    }


    public void openShopOnNewTab(String shop) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open(\"https://" + shop + "\")");
        waitForEverythingComplete();
        switchToLatestTab();
        waitForPageLoad();
    }


    public void verifyOnlineStoreConversionRate(double onlineStoreConversionRateActual, double sessionConvertedActual, double viewProductActual) {
        if (viewProductActual == 0) {
//            assertThat(onlineStoreConversionRateActual.split("%")[0]).isEqualTo("0");
        } else {
//            double onlineStoreConversionRateExpect = Math.round(Double.parseDouble(sessionConvertedActual) / Double.parseDouble(viewProductActual) * 100 * 100.0) / 100.0;
//            assertThat(onlineStoreConversionRateExpect).isEqualTo(Double.parseDouble(onlineStoreConversionRateActual.split("%")[0]));
        }
    }

    public void verifyAverageOrderValue(double averageOrderValueActual, double totalSaleActual, double totalOrderActual) {
        String totalRefunded = getTotalRefund();
//        if (totalOrderActual.equals("0")) {
//            assertThat(averageOrderValueActual.split("\\$")[1]).isEqualTo("0.00");
//        } else {
//            double averageOrderValueExpect = Math.round((Double.parseDouble(totalSaleActual.split("\\$")[1]) + Double.parseDouble(totalRefunded)) / Double.parseDouble(totalOrderActual) * 100.0) / 100.0;
//            assertThat(averageOrderValueExpect).isEqualTo(Double.parseDouble(averageOrderValueActual.split("\\$")[1]));
//        }
    }

    public String getTotalRefund() {
        double totalRefund = 0;
        int totalRefundOdrder = 0;
        String date = LocalDateTime.now(ZoneOffset.UTC).toLocalDate().toString();
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/orders.json";
        RequestSpecification request = RestAssured.given();
        request.queryParam("access_token", accessToken);
        request.queryParam("created_at_min", date + "T00:00:00-00:00");
        request.queryParam("created_at_max", date + "T23:59:59-00:00");
        Response response = request.get(url);
        JsonPath jp = response.then().extract().jsonPath();
        String financialStatus = getData(jp, "orders.financial_status").toString();
        String totalPrice = getData(jp, "orders.total_price").toString();
        if (!financialStatus.equals("[]") && !totalPrice.equals("[]")) {
            String[] financialStatusArray = financialStatus.split("\\[")[1].split("]")[0].replace(" ", "").split(",");
            String[] totalPriceArray = totalPrice.split("\\[")[1].split("]")[0].replace(" ", "").split(",");
            for (int i = 0; i < financialStatusArray.length; i++) {
                if (financialStatusArray[i].equals("refunded")) {
                    totalRefund = totalRefund + Double.parseDouble(totalPriceArray[i]);
                    totalRefundOdrder++;
                }
            }
            System.out.println("totalRefund: " + totalRefund);
            System.out.println("totalRefundOdrder: " + totalRefundOdrder);
        }
        return Double.toString(totalRefund);
    }

    public void verifyAverageOrderItem(double averageOrderItemActual, double topProductActual, double totalOrderActual) {
//        if (totalOrderActual.equals("0")) {
//            assertThat(averageOrderItemActual).isEqualTo("0.00");
////        } else {
//            double averageOrderItemExpect = Math.round(Double.parseDouble(topProductActual) / Double.parseDouble(totalOrderActual) * 100.0) / 100.0;
//            assertThat(averageOrderItemExpect).isEqualTo(Double.parseDouble(averageOrderItemActual));
//        }
    }

    public void closeLastestTab() {
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        int size = getDriver().getWindowHandles().size();
        getDriver().switchTo().window(tabs.get(size - 1)).close();
        size = getDriver().getWindowHandles().size();
        getDriver().switchTo().window(tabs.get(size - 1));
    }

    public String getValueFromAbandonedCheckoutRecoveryChart(String eventName) {
        String attribute = "";
        switch (eventName) {
            case "Email sent":
                attribute = "auto-acr-sent";
                break;
            case "Email clicked":
                attribute = "auto-acr-clicked";
                break;
            case "Order completed":
                attribute = "auto-acr-order-complete";
                break;
            default:
                fail();
        }
        String xpath = "//h4[text()='Abandoned checkouts recovery']//preceding::div[@class='hidden-items hidden']//input[@id='" + attribute + "']";
        String value = getAttributeValue(xpath, "val");
        return value;
    }

    public String getIndicatorFromAbandonedCheckoutsRecoveryChart(String indicator) {
        return getText("(//div[child::h4[text()='Abandoned checkouts recovery']]//div[normalize-space()='" + indicator + "']/preceding-sibling::div)[1]");
    }


    public String getLiveData(String type) {
        String xpath = "//div[text()='" + type + "']//following-sibling::div";
        String value = getText(xpath).replace("$", "");
        return value;
    }

    public String getLiveVisitor() {
        String xpath = "//p[text()='Visitors right now']//parent::div//following-sibling::div";
        String value = getText(xpath);
        return value;
    }

    public String getLiveViewConversionRateByAPI(String action) {

        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/analytics/live-view.json" + "?access_token=" + accessToken + "&report_type=conversion_rate";
        JsonObject requestParams = new JsonObject();

        requestParams.addProperty("since", "-10");
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        String value = jp.get("summary." + action).toString();
        return value;
    }

    public String getDataCustomerAnalyticsByAPI(String filterBy, String customerType) {
        String accessToken = getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String shopId = LoadObject.getProperty("shop_id");
        String url = "https://" + shop + "/admin/analytics.json" + "?access_token=" + accessToken + "&shop_ids=" + shopId;
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00");
        Calendar c = Calendar.getInstance();
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("report_type", "customer");
        requestParams.addProperty("aov_field", "total_sales");
        requestParams.addProperty("from_time", date.format(c.getTime()));
        c.add(Calendar.DATE, 1);
        requestParams.addProperty("to_time", date.format(c.getTime()));
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        String value = jp.get("summary." + filterBy + "." + customerType).toString();
        return value;
    }

    public String getDashboardReturningRate() {
        String xpath = "//h4[normalize-space()='Returning customer rate']//parent::div[contains(@class, 'justify-content-between')]//following-sibling::div[@class='line-chart']//h2";
        String returningRateActual = getText(xpath).split("%")[0];
        return returningRateActual;
    }

    public void verifyUTM(String utmSource, String expectedVal) {
        String xpath = "(//p[normalize-space()='" + utmSource + "']/following::p)[1]";
        String value = getText(xpath);
        assertThat(value).isEqualTo(expectedVal);
    }

    public void verifyUtmFirstPageVisited(String utmFirstPageVisited) {
        String xpath = "(//p[normalize-space()='First page visited']//following::span)[1]";
        String value = getText(xpath);
        assertThat(value).isEqualTo(utmFirstPageVisited);
    }

    public void selectDdlWithLabel(String option) {
        selectDdlWithLabel("Report by", option);
    }

    public String getValueActualBy() {
        String data = "//div[@class='table-responsive']//tr[1]";
        isElementVisible(data, 5);
        String option = "//table[@class='table traffic-source table-traffic-source']//td[1]";
        String value = "";
        for (int i = 1; i <= 4; i++) {
            value = "//table[@class='table traffic-source table-traffic-source']//td[1]//following-sibling::td[" + i + "]";

        }
        return getValue(value);
    }

    public String getActualValue(int i) {
        String value = "";
        String xpath = "//td[normalize-space()='social']//following-sibling::td[" + i + "]";
        value = getText(xpath);
        return value;
    }

    public void clickToSectionAtAnalyticsScreen(String sub) {
        clickOnElement("(//span[normalize-space()='Analytics'])[1]");
        waitForEverythingComplete(3);
        clickOnElement("//div[normalize-space()='Traffic sources']");
    }

    public void choiceOptionReportBy(String option) {
        selectDdlWithLabel("Report by", option);
    }

    public void addColumn(String column) {
        selectDdlWithLabel("Add column", column);
    }

    public int getIndexColumn() {
        String xpath = "(//div[contains(@class,'table')]//tr)[1]//th";
        int index = countElementByXpath(xpath);
        return index;
    }

    public String getVisitorsBefore() {
        String value = "";

        if (isElementVisible("//td[normalize-space()='social']", 3)) {
            String xpath = "//td[normalize-space()='social']//following-sibling::td[2]";
            value = getText(xpath);

        } else {
            value = String.valueOf(0);

        }
        return value;
    }

    public String getValue(int i, String valueAdd, String value) {
        String valueCampain = "";
        String xpathfirst = "";
        String xpath = "";
        switch (valueAdd) {
            case "":
                xpathfirst = "//td[normalize-space()='" + value + "']";
                xpath = "//td[normalize-space()='" + value + "']//following-sibling::td[" + i + "]";
                if (isElementVisible(xpathfirst, 2)) {
                    if (i != 0) {

                        valueCampain = getText(xpath).replace("%", "").replace("$", "").replace(",", "");
                    } else {
                        valueCampain = value;
                    }
                } else {
                    valueCampain = String.valueOf(0);
                }
                break;
            default:
                xpathfirst = "//td[normalize-space()='" + valueAdd + "']//preceding-sibling::td";
                xpath = "//td[normalize-space()='" + valueAdd + "']//preceding-sibling::td//following-sibling::td[" + i + "]";

                if (isElementVisible(xpathfirst, 2)) {
                    if (i != 0) {

                        valueCampain = getText(xpath).replace("%", "").replace("$", "").replace(",", "");
                    } else {
                        valueCampain = valueAdd;
                    }
                } else {
                    valueCampain = String.valueOf(0);
                }
        }
        return valueCampain;
    }

    public Double getTotalSalesByAPI() {
        String accessToken = getAccessTokenShopBase();

        String today = getTodayByFormat("yyyy-MM-dd");

        String url = "https://" + shop + "/admin/statistics/v2/dashboard/total-sales.json"
                + "?access_token=" + accessToken
                + "&from_time=" + today
                + "&to_time=" + today
                + "&sources=total";

        JsonPath jp = getAPI(url);
        HashMap<String, Integer> total_sales = jp.get("total_sales");
        return Double.parseDouble(String.valueOf(total_sales.get("total")));
    }

    public String getTotalSalesByXpath() {
        String xpath = "//div[@class='overview-content']//div[normalize-space()='Total sales']//preceding-sibling::div";
        String priceValue = getTextValue(xpath).replaceAll("[,|$]", "");
        return priceValue;
    }

    public int getTotalOrdersByAPI() {
        String accessToken = getAccessTokenShopBase();

        String today = getTodayByFormat("yyyy-MM-dd");

        String url = "https://" + shop + "/admin/statistics/v2/dashboard/total-orders.json"
                + "?access_token=" + accessToken
                + "&from_time=" + today
                + "&to_time=" + today
                + "&sources=total";

        JsonPath jp = getAPI(url);
        HashMap<String, Integer> total_sales = jp.get("total_orders");
        return total_sales.get("total");
    }

    public String getTotalOrdersByXpath() {
        String xpath = "//div[@class='overview-content']//div[normalize-space()='Total orders']//preceding-sibling::div";
        return getTextValue(xpath);
    }

    public List<String> getTotalSalesAndTotalOrdersByHomeApi() {
        String accessToken = getAccessTokenShopBase();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00");
        Calendar c = Calendar.getInstance();
        String url = "https://" + shop + "/admin/analytics/home.json" + "?access_token=" + accessToken;
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("report_type", "home_page");
        requestParams.addProperty("from_time", date.format(c.getTime()));
        c.add(Calendar.DATE, 1);
        requestParams.addProperty("to_time", date.format(c.getTime()));
        requestParams.addProperty("aov_field", "total_sales");
        Response a = postAPI(url, requestParams);
        JsonPath jp = a.then().extract().jsonPath();
        String total_orders = jp.get("summary.total_orders").toString();
        String total_sales = jp.get("summary.total_sales").toString();
        return Arrays.asList(total_orders, total_sales);
    }

    public void verifyOverviewContent(){
        List<String> data = getTotalSalesAndTotalOrdersByHomeApi();
        if("0".equals(data.get(0))){
            assertThat("No sales yet").contains(getTotalSalesByXpath());
            assertThat("No orders yet").contains(getTotalOrdersByXpath());
        } else {
            assertThat(Float.parseFloat(data.get(1))).isEqualTo(Float.parseFloat(getTotalSalesByXpath()));
            assertThat(data.get(0)).isEqualTo(getTotalOrdersByXpath());
        }
    }

    public JsonPath getAnalyticsAPI(String shopType, String reportType) {
        String url = "https://" + shop + "/admin/analytics.json?access_token=" + accessToken + "&shop_ids=" + shopid;
        JsonObject requestParams = new JsonObject();

        String today = getTodayByFormat("yyyy-MM-dd");
        String nextday = getNextByFormat("yyyy-MM-dd");

        requestParams.addProperty("report_type", reportType);
        requestParams.addProperty("from_time", today + "T00:00:00");
        requestParams.addProperty("to_time", nextday + "T00:00:00");

        if (shopType.matches("shopbase")) {
            requestParams.addProperty("aov_field", "total_sales");

        } else {
            requestParams.addProperty("aov_field", "total_profit");
        }
        Response a = postAPI(url, requestParams);
        JsonPath json = a.then().extract().jsonPath();

        return json;
    }

    public String getDataAnalyticsByAPI(JsonPath json, String source, String key) {
        String value = "";
        if (source.matches("summary")) {
            try {
                value = json.get("summary." + key).toString();
            } catch (NullPointerException e) {
                value = "0";
            }
        } else {
            try {
                value = json.get("data." + source + "." + key).toString();
            } catch (NullPointerException e) {
                value = "0";
            }
        }
        return value;
    }
}

