package opencommerce.analytics;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.AnalyticSteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.UTMAnalyticSteps;
import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SalesChannelSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.checkoutToken;
import static com.opencommerce.shopbase.OrderVariable.totalAmt;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class UTMAnalyticsDef {

    @Steps
    UTMAnalyticSteps utmAnalyticSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    AnalyticSteps aStep;
    @Steps
    BalanceSteps balanceSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    ProductSteps productSteps;


    public double totalSaleInit;
    public double profitInit;
    public double viewProductInit;
    public double addToCartInit;
    public double reachedCheckoutInit;
    public double totalOrderInit;
    public double quantityInit;
    public String productTitle = "";
    public double sessionConvertedInit;
    public double aovInit;
    public double aoiInit;


    HashMap<String, List> before = new HashMap<>();
    HashMap<String, List> after = new HashMap<>();
    JsonPath jsonPath;

    @Given("^open product with UTM and checkout$")
    public void openProductWithUTMAs(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String referrer = SessionData.getDataTbVal(dataTable, row, "referrer");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String utmSource = SessionData.getDataTbVal(dataTable, row, "utm_source");
            String utmMedium = SessionData.getDataTbVal(dataTable, row, "utm_medium");
            String utmCampaign = SessionData.getDataTbVal(dataTable, row, "utm_campaign");
            String utmContent = SessionData.getDataTbVal(dataTable, row, "utm_content");
            String utmTerm = SessionData.getDataTbVal(dataTable, row, "utm_term");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            utmAnalyticSteps.openLinkWithUTM(productName, utmSource, utmMedium, utmCampaign, utmContent, utmTerm, referrer);
            productSteps.addToCartWithQuantity(quantity);
            balanceSteps.clickToCheckoutWithoutVerify();
            checkoutToken = thankyouSteps.getCheckoutToken();
        }
    }


    @Then("Get data new version UTM analytics by API before")
    public void getDataNewVersionUTMAnalyticsByAPIBefore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String reportBy = SessionData.getDataTbVal(dataTable, row, "Report by");
            String utmValue = SessionData.getDataTbVal(dataTable, row, "UTM value");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop type");
            jsonPath = utmAnalyticSteps.getUTMbyAPI(shopType, reportBy);

            viewProductInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "view_content"));
            addToCartInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "add_to_cart"));
            reachedCheckoutInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "reached_checkout"));
            totalOrderInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_orders"));
            quantityInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_items"));
            totalSaleInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_sales"));
            profitInit = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_profit"));

            List<Object> utmAnaInit = asList(viewProductInit, addToCartInit, reachedCheckoutInit, totalOrderInit, quantityInit, totalSaleInit, profitInit);

            System.out.println("Filter by: " + reportBy + "\nUTM value: " + utmValue + "\n======================");

            System.out.println("Before data:\n" +
                    "view_content: " + viewProductInit + "\n" +
                    "add_to_cart: " + addToCartInit + "\n" +
                    "reached_checkout: " + reachedCheckoutInit + "\n" +
                    "order_count: " + totalOrderInit + "\n" +
                    "order_items_count: " + quantityInit + "\n" +
                    "total_sales: " + totalSaleInit + "\n" +
                    "profit: " + profitInit + "\n" +
                    "==============");
            before.put(reportBy + " - " + utmValue, utmAnaInit);
        }
    }

    @Then("Verify new data UTM analytics by API")
    public void verifyNewDataUTMAnalyticsByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String reportBy = SessionData.getDataTbVal(dataTable, row, "Report by");
            String utmValue = SessionData.getDataTbVal(dataTable, row, "UTM value");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop type");

            double viewProductIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "View product"));
            double addToCartIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Add to cart"));
            double reachedCheckoutIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Reached checkout"));
            double ordersIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Orders"));
            double netQuantityIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Net Quantity"));
            double totalSalesIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total Sales"));
            double profitIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Profit"));
            jsonPath = utmAnalyticSteps.getUTMbyAPI(shopType, reportBy);

            System.out.println("Filter by: " + reportBy + "\nUTM value: " + utmValue + "\n======================");

            double viewProductActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "view_content"));
            double addToCartActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "add_to_cart"));
            double reachedCheckoutActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "reached_checkout"));
            double totalOrdersActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_orders"));
            double netQuantityActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_items"));
            double totalSalesActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_sales"));
            double profitActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "total_profit"));
            double conversionRateActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "cr_rate"));
            double aovActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "aov_rate"));
            double aoiActual = Double.parseDouble(utmAnalyticSteps.getDataUTM(jsonPath, reportBy, utmValue, "aoi_rate"));
            List<Object> utmAnaActual = asList(viewProductActual, addToCartActual, reachedCheckoutActual, totalOrdersActual, netQuantityActual, totalSalesActual, profitActual);

            System.out.println("After data:\n" +
                    "UTM value:" + utmValue + "\n" +
                    "view_content: " + viewProductActual + "\n" +
                    "add_to_cart: " + addToCartActual + "\n" +
                    "reached_checkout: " + reachedCheckoutActual + "\n" +
                    "order_count: " + totalOrdersActual + "\n" +
                    "order_items_count: " + netQuantityActual + "\n" +
                    "total_sales: " + totalSalesActual + "\n" +
                    "profit: " + profitActual + "\n" +
                    "CR: " + conversionRateActual + "\n" +
                    "AOV: " + aovActual + "\n" +
                    "AOI: " + aoiActual + "\n");
            after.put(reportBy + " - " + utmValue, utmAnaActual);

            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(0)), viewProductIncrease, (double) (after.get(reportBy + " - " + utmValue).get(0)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(1)), addToCartIncrease, (double) (after.get(reportBy + " - " + utmValue).get(1)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(2)), reachedCheckoutIncrease, (double) (after.get(reportBy + " - " + utmValue).get(2)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(3)), ordersIncrease, (double) (after.get(reportBy + " - " + utmValue).get(3)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(4)), netQuantityIncrease, (double) (after.get(reportBy + " - " + utmValue).get(4)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(5)), totalSalesIncrease, (double) (after.get(reportBy + " - " + utmValue).get(5)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(reportBy + " - " + utmValue).get(6)), profitIncrease, (double) (after.get(reportBy + " - " + utmValue).get(6)));

            double crRateCalculate = (double) Math.round((totalOrdersActual / viewProductActual * 100) * 100) / 100;
            double aoiCalculate = (double) Math.round((netQuantityActual / totalOrdersActual) * 100) / 100;
            double aovCalculate = 0.00d;
            if (shopType.matches("sbase")) {
                aovCalculate = (double) Math.round((totalSalesActual / totalOrdersActual) * 100) / 100;
            } else {
                aovCalculate = (double) Math.round((profitActual / totalOrdersActual) * 100) / 100;
            }

            assertThat(crRateCalculate).isEqualTo(conversionRateActual);
            assertThat(aoiCalculate).isEqualTo(aoiActual);
            assertThat(aovCalculate).isEqualTo(aovActual);
        }
    }

    @Then("^get data UTM sales report init by API$")
    public void getDataUTMSalesReportInitByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filterValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String reportBy = SessionData.getDataTbVal(dataTable, row, "Report by");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop Type");
            String reportType = SessionData.getDataTbVal(dataTable, row, "Report type");
            String productFilter = SessionData.getDataTbVal(dataTable, row, "Product filter");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            JsonPath js = utmAnalyticSteps.getDataUTMSaleReport(shopType, reportBy, reportType);

            productTitle = utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "product_title");
            viewProductInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "view_content"));
            addToCartInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "add_to_cart"));
            reachedCheckoutInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "reached_checkout"));
            totalOrderInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "total_orders"));
            quantityInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "total_items"));
            totalSaleInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "net_sales"));
            profitInit = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportInit(js, reportBy, filterValue, productFilter, title, "total_profit"));
            List<Object> utmSaleReportInit = asList(viewProductInit, addToCartInit, reachedCheckoutInit, totalOrderInit, quantityInit, totalSaleInit, profitInit);
            before.put("List data init: ", utmSaleReportInit);
        }
    }

    @Then("^verify data UTM sales report by API$")
    public void verifyDataUTMSalesReportByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String reportBy = SessionData.getDataTbVal(dataTable, row, "Report by");
            String filterValue = SessionData.getDataTbVal(dataTable, row, "Value");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop Type");
            String reportType = SessionData.getDataTbVal(dataTable, row, "Report type");
            String productFilter = SessionData.getDataTbVal(dataTable, row, "Product filter");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            double viewProductIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "View product"));
            double addToCartIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Add to cart"));
            double reachedCheckoutIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Reached checkout"));
            double orderIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Orders"));
            double netQuantityIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Net quantity"));
            double netSalesIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Net sales"));
            double netProfitIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Net profit"));
            JsonPath js = utmAnalyticSteps.getDataUTMSaleReport(shopType, reportBy, reportType);

            double viewProductActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "view_content"));
            double addToCartActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "add_to_cart"));
            double reachedCheckoutActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "reached_checkout"));
            double totalOrdersActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "total_orders"));
            double sessionConvertedActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "cr_rate"));
            double netQuantityActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "total_items"));
            double aovActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "aov_rate"));
            double aoiActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "aoi_rate"));
            double netSalesActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "net_sales"));
            double profitActual = Double.parseDouble(utmAnalyticSteps.getDataUTMSaleReportAfter(js, reportBy, filterValue, productFilter, title, "total_profit"));
            List<Object> utmSaleReport = asList(viewProductActual, addToCartActual, reachedCheckoutActual, totalOrdersActual, netQuantityActual, netSalesActual, profitActual);
            after.put("List data after: ", utmSaleReport);

            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(0)), viewProductIncrease, (Double) (after.get("List data after: ").get(0)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(1)), addToCartIncrease, (Double) (after.get("List data after: ").get(1)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(2)), reachedCheckoutIncrease, (Double) (after.get("List data after: ").get(2)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(3)), orderIncrease, (Double) (after.get("List data after: ").get(3)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(4)), netQuantityIncrease, (Double) (after.get("List data after: ").get(4)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(5)), netSalesIncrease, (Double) (after.get("List data after: ").get(5)));
            aStep.verifyDataAnalyticsChanged((Double) (before.get("List data init: ").get(6)), netProfitIncrease, (Double) (after.get("List data after: ").get(6)));

            sessionConvertedInit = (double) Math.round(((totalOrdersActual / viewProductActual) * 100) * 100) / 100.0;
            aovInit = (double) Math.round((netSalesActual / totalOrdersActual) * 100) / 100.0;
            aoiInit = (double) Math.round((netQuantityActual / totalOrdersActual) * 100) / 100.0;
            double aovProfitActual = (double) Math.round((profitActual / totalOrdersActual) * 100) / 100;

            assertThat(sessionConvertedInit).isEqualTo(sessionConvertedActual);
            assertThat(aovInit).isEqualTo(aovActual);
            assertThat(aoiInit).isEqualTo(aoiActual);
            if (shopType.matches("net_sales")) {
                assertThat(aovInit).isEqualTo(aovActual);
            } else if (shopType.matches("profit")) {
                assertThat(aovProfitActual).isEqualTo(aovActual);
            }
        }
    }

    @And("^choose report by \"([^\"]*)\" on dashboard sales report$")
    public void chooseReportByOnDashboardSalesReport(String reportBy) {
        utmAnalyticSteps.chooseReportBy(reportBy);
    }

    @Then("^verify when add a \"([^\"]*)\" column on dashboard sales report$")
    public void verifyWhenAddAColumnOnDashboardSalesReport(String additionalColumn) {
        utmAnalyticSteps.verifyAddColumn(additionalColumn);
    }
}
