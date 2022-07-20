package opencommerce.analytics;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.AnalyticSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SalesChannelSteps;
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

import static com.opencommerce.shopbase.OrderVariable.totalAmt;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyticsDef {
    @Steps
    AnalyticSteps aStep;
    @Steps
    CommonSteps commonSteps;
    @Steps
    SalesChannelSteps salesChannelSteps;

    DecimalFormat fm = new DecimalFormat("#0.00");
    public double totalSaleInit;
    public double totalProfitInit;
    public double viewProductInit;
    public double addToCartInit;
    public double totalItemInit;
    public double reachedCheckoutInit;
    public double sessionConvertedInit;
    public double totalOrderInit;
    public double topProductInit;
    private double emailSentBefore;
    private double clickedBefore;
    private double orderCompletedBefore;
    public double revenueBefore;
    public double recoveryRateBefore;
    public double firstTimeCusValue;
    public double returningCusValue;
    public double totalValue;
    public String liveSessions;
    public String liveSales;
    public String liveOrders;
    public String liveVisitors;
    public String liveViewProduct;
    public String liveAddCart;
    public String liveReachedCheckout;
    public String liveCheckedout;
    public String visitorsBefore;
    int n;

    JsonPath json;

    HashMap<String, List> before = new HashMap<>();
    HashMap<String, List> after = new HashMap<>();


    @Given("^Get data analytics before of product \"([^\"]*)\"$")
    public void getDataBeforeOfAnalytics(String productName) {
        totalSaleInit = Double.parseDouble(aStep.getMainChartValue("Total sales"));
        viewProductInit = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("View Product"));
        addToCartInit = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Add To Card"));
        reachedCheckoutInit = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Reached Checkout"));
        sessionConvertedInit = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Session Converted"));
        totalOrderInit = Double.parseDouble(aStep.getMainChartValue("Total orders"));
        topProductInit = Double.parseDouble(aStep.getValueTopProduct(productName));
        System.out.println(
                " totalSaleInit       " + totalSaleInit + "\n" +
                        " viewProductInit     " + viewProductInit + "\n" +
                        " addToCartInit       " + addToCartInit + "\n" +
                        " reachedCheckoutInit " + reachedCheckoutInit + "\n" +
                        " sessionConvertedInit" + sessionConvertedInit + "\n" +
                        " totalOrderInit      " + totalOrderInit + "\n" +
                        " topProductInit      " + topProductInit);

    }

    @Then("^Verify data analytics of product \"([^\"]*)\"$")
    public void verifyDataAnalyticsOfProduct(String productName, List<List<String>> dataTable) {
        aStep.switchToTheFirstTab();
        aStep.refreshPage();
        double totalSaleActual = Double.parseDouble(aStep.getMainChartValue("Total sales"));
        double onlineStoreConversionRateActual = Double.parseDouble(aStep.getMainChartValue("Online store conversion rate"));
        double viewProductActual = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("View Product"));
        double addToCartActual = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Add To Card"));
        double reachedCheckoutActual = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Reached Checkout"));
        double sessionConvertedActual = Double.parseDouble(aStep.getValueOnlineStoreConversionRate("Session Converted"));
        double totalOrderActual = Double.parseDouble(aStep.getMainChartValue("Total orders"));
        double averageOrderValueActual = Double.parseDouble(aStep.getMainChartValue("Average order value"));
        double averageOrderItemActual = Double.parseDouble(aStep.getMainChartValue("Average order items"));
        double topProductActual = Double.parseDouble(aStep.getValueTopProduct(productName));

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            double totalSaleIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "totalSale"));
            double viewProductIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "viewProduct"));
            double addToCartIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "addToCart"));
            double reachedCheckoutIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "reachedCheckout"));
            double sessionConvertedIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "sessionConverted"));
            double totalOrderIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "totalOrder"));
            double topProductIncreaseExpect = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "topProduct"));

            aStep.verifyDataAnalyticsChanged(totalSaleInit, totalSaleIncreaseExpect, totalSaleActual);
            aStep.verifyDataAnalyticsChanged(viewProductInit, viewProductIncreaseExpect, viewProductActual);
            aStep.verifyDataAnalyticsChanged(addToCartInit, addToCartIncreaseExpect, addToCartActual);
            aStep.verifyDataAnalyticsChanged(reachedCheckoutInit, reachedCheckoutIncreaseExpect, reachedCheckoutActual);
            aStep.verifyDataAnalyticsChanged(sessionConvertedInit, sessionConvertedIncreaseExpect, sessionConvertedActual);
            aStep.verifyDataAnalyticsChanged(totalOrderInit, totalOrderIncreaseExpect, totalOrderActual);
            aStep.verifyDataAnalyticsChanged(topProductInit, topProductIncreaseExpect, topProductActual);

            aStep.verifyOnlineStoreConversionRate(onlineStoreConversionRateActual, sessionConvertedActual, viewProductActual);
            aStep.verifyAverageOrderValue(averageOrderValueActual, totalSaleActual, totalOrderActual);
            aStep.verifyAverageOrderItem(averageOrderItemActual, topProductActual, totalOrderActual);
        }
        aStep.switchToLastestTab();
        totalSaleInit = totalSaleActual;
        viewProductInit = viewProductActual;
        addToCartInit = addToCartActual;
        reachedCheckoutInit = reachedCheckoutActual;
        sessionConvertedInit = sessionConvertedActual;
        totalOrderInit = totalOrderActual;
        topProductInit = topProductActual;
    }


    @And("^get data including: EMAIL SENT, EMAIL CLICKED, ORDER COMPLETED and revenue before$")
    public void getDataIncludingAndBefore() {
        emailSentBefore = Double.parseDouble(aStep.getValueFromAbandonedCheckoutRecoveryChart("Email sent"));
        clickedBefore = Double.parseDouble(aStep.getValueFromAbandonedCheckoutRecoveryChart("Email clicked"));
        orderCompletedBefore = Double.parseDouble(aStep.getValueFromAbandonedCheckoutRecoveryChart("Order completed"));
        revenueBefore = Double.parseDouble(aStep.getIndicatorFromAbandonedCheckoutsRecoveryChart("revenue"));
        recoveryRateBefore = Double.parseDouble(aStep.getIndicatorFromAbandonedCheckoutsRecoveryChart("recovery rate"));
        aStep.logInfor("Email sent: " + emailSentBefore + '\n' +
                "Email clicked: " + clickedBefore + '\n' +
                "Order completed: " + orderCompletedBefore + '\n' +
                "revenue: " + revenueBefore + '\n' +
                "recovery rate: " + recoveryRateBefore);
    }


    @And("^verify chart Abandoned checkouts recovery by \"([^\"]*)\"$")
    public void verifyChartAbandonedCheckoutsRecoveryBy(String typeChart, List<List<String>> dataTable) {
        aStep.refreshPage();
        String revenueAfter = aStep.getIndicatorFromAbandonedCheckoutsRecoveryChart("revenue");
        String recoveryRateAfter = aStep.getIndicatorFromAbandonedCheckoutsRecoveryChart("recovery rate");
        String emailSentAfter = aStep.getValueFromAbandonedCheckoutRecoveryChart("Email sent");
        String emailClickedAfter = aStep.getValueFromAbandonedCheckoutRecoveryChart("Email clicked");
        String orderCompletedAfter = aStep.getValueFromAbandonedCheckoutRecoveryChart("Order completed");

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String revenueIncreaseExpected = SessionData.getDataTbVal(dataTable, row, "Revenue").replace("+total order", totalAmt);
            String emailSentIncreaseExpected = SessionData.getDataTbVal(dataTable, row, "Email sent");
            String emailClickedIncreaseExpected = SessionData.getDataTbVal(dataTable, row, "Email clicked");
            String orderCompletedIncreaseExpected = SessionData.getDataTbVal(dataTable, row, "Order completed");

//            aStep.verifyDataAnalyticsChanged(emailSentBefore, emailSentIncreaseExpected, emailSentAfter);
//            aStep.verifyDataAnalyticsChanged(orderCompletedBefore, orderCompletedIncreaseExpected, orderCompletedAfter);
//            aStep.verifyDataAnalyticsChanged(clickedBefore, emailClickedIncreaseExpected, emailClickedAfter);
//            aStep.verifyDataAnalyticsChanged(revenueBefore, revenueIncreaseExpected, revenueAfter);
            aStep.verifyRecoveryRate(recoveryRateAfter, emailSentAfter, orderCompletedAfter);
        }

    }

    @Then("verify refer block displaying on order detail page")
    public void verifyReferBlockDisplayingOnOrderDetailPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String utmSourceIdentifier = SessionData.getDataTbVal(dataTable, row, "Source identifier");
            String utmReferringSite = SessionData.getDataTbVal(dataTable, row, "Referring site");
            String utmFirstPageVisited = SessionData.getDataTbVal(dataTable, row, "First page visited");
            String utmSource = SessionData.getDataTbVal(dataTable, row, "Source");
            String utmMedium = SessionData.getDataTbVal(dataTable, row, "Medium");
            String utmCampaign = SessionData.getDataTbVal(dataTable, row, "Campaign");
            aStep.verifyUtmSource(utmSource);
            aStep.verifyUtmMedium(utmMedium);
            aStep.verifyUtmCampaign(utmCampaign);
            aStep.verifyUtmSourceIdentifier(utmSourceIdentifier);
            aStep.verifyUtmReferringSite(utmReferringSite);
            aStep.verifyUtmFirstPageVisited(utmFirstPageVisited);
        }
    }

    @When("get data visitors, sessions, sales, orders")
    public void getDataVisitorsSessionsSalesOrders() {
        liveVisitors = aStep.getLiveVisitor();
        liveSessions = aStep.getLiveData("Total sessions");
        liveSales = aStep.getLiveData("Total sales");
        liveOrders = aStep.getLiveData("Total orders");
        System.out.println("visit: " + liveVisitors
                + "\nsession: " + liveSessions
                + "\nsale: " + liveSales
                + "\norders: " + liveOrders);
    }

    @And("verify data Live View increase")
    public void verifyDataLiveViewIncrease(List<List<String>> dataTable) {
        commonSteps.waitABit(40000);

        String liveSalesActual = aStep.getLiveData("Total sales");
        String liveOrdersActual = aStep.getLiveData("Total orders");
        String liveViewProductActual = aStep.getLiveViewConversionRateByAPI("view_product");
        String liveAddCartActual = aStep.getLiveViewConversionRateByAPI("add_to_cart");
        String liveReachedCheckoutActual = aStep.getLiveViewConversionRateByAPI("reached_checkout");
        String liveCheckedoutActual = aStep.getLiveViewConversionRateByAPI("order_distinct_count");
        String liveVisitorsActual = aStep.getLiveVisitor();
        String liveSessionsActual = aStep.getLiveData("Total sessions");

        System.out.println("visit: " + liveVisitorsActual
                + "\nsession: " + liveSessionsActual
                + "\nsale: " + liveSalesActual
                + "\norders: " + liveOrdersActual
                + "\nview: " + liveViewProductActual
                + "\nadd cart: " + liveAddCartActual
                + "\nreach checkout: " + liveReachedCheckoutActual
                + "\norders: " + liveCheckedoutActual);

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String visitorsRightNowInc = SessionData.getDataTbVal(dataTable, row, "Visitors right now");
            String totalSessionsInc = SessionData.getDataTbVal(dataTable, row, "Total sessions");
            String totalSalesInc = SessionData.getDataTbVal(dataTable, row, "Total sales");
            String totalOrdersInc = SessionData.getDataTbVal(dataTable, row, "Total orders");
            String viewProductInc = SessionData.getDataTbVal(dataTable, row, "View Product");
            String addToCartInc = SessionData.getDataTbVal(dataTable, row, "Add to Cart");
            String reachCheckoutInc = SessionData.getDataTbVal(dataTable, row, "Reach Checkout");
            String ordersInc = SessionData.getDataTbVal(dataTable, row, "Order Count");

            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveVisitors), Double.parseDouble(visitorsRightNowInc), Double.parseDouble(liveVisitorsActual));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveSessions), Double.parseDouble(totalSessionsInc), Double.parseDouble(liveSessionsActual));
            if (totalSalesInc.matches("@totalSales@")) {
                aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveSales), Double.parseDouble(totalAmt.replace("$", "")), Double.parseDouble(liveSalesActual));
            } else {
                aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveSales), Double.parseDouble(totalSalesInc), Double.parseDouble(liveSalesActual));
            }
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveOrders), Double.parseDouble(totalOrdersInc), Double.parseDouble(liveOrdersActual));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveViewProduct), Double.parseDouble(viewProductInc), Double.parseDouble(liveViewProductActual));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveAddCart), Double.parseDouble(addToCartInc), Double.parseDouble(liveAddCartActual));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveReachedCheckout), Double.parseDouble(reachCheckoutInc), Double.parseDouble(liveReachedCheckoutActual));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(liveCheckedout), Double.parseDouble(ordersInc), Double.parseDouble(liveCheckedoutActual));

        }
        liveVisitors = liveVisitorsActual;
        liveSessions = liveSessionsActual;
        liveSales = liveSalesActual;
        liveOrders = liveOrdersActual;
        liveViewProduct = liveViewProductActual;
        liveAddCart = liveAddCartActual;
        liveReachedCheckout = liveReachedCheckoutActual;
        liveCheckedout = liveCheckedoutActual;
        salesChannelSteps.switchToNextTab();
    }

    @And("get Live view conversion rate by API")
    public void getLiveViewConversionRateByAPI() {
        liveViewProduct = aStep.getLiveViewConversionRateByAPI("view_product");
        liveAddCart = aStep.getLiveViewConversionRateByAPI("add_to_cart");
        liveReachedCheckout = aStep.getLiveViewConversionRateByAPI("reached_checkout");
        liveCheckedout = aStep.getLiveViewConversionRateByAPI("order_distinct_count");

        System.out.println("view: " + liveViewProduct
                + "\nadd cart: " + liveAddCart
                + "\nreach checkout: " + liveReachedCheckout
                + "\norders: " + liveCheckedout);

    }

    @And("verify no data Live view after 10 minutes")
    public void verifyDataLiveViewConversionRateAfterMinutes() {
        commonSteps.waitABit(1000 * 600);
        liveVisitors = aStep.getLiveVisitor();
        getLiveViewConversionRateByAPI();

        String liveSessionsActual = aStep.getLiveData("Total sesions");
        String liveSalesActual = aStep.getLiveData("Total sales");
        String liveOrdersActual = aStep.getLiveData("Total orders");

        assertThat(liveSessionsActual).isEqualTo(liveSessions);
        assertThat(liveSalesActual).isEqualTo(liveSales);
        assertThat(liveOrdersActual).isEqualTo(liveOrders);

        assertThat(liveVisitors).isEqualTo("0");
        assertThat(liveViewProduct).isEqualTo("0");
        assertThat(liveAddCart).isEqualTo("0");
        assertThat(liveReachedCheckout).isEqualTo("0");
        assertThat(liveCheckedout).isEqualTo("0");
    }

    @Given("^get chart First-time vs returning customers data first time by API init$")
    public void getChartFirstTimeVsReturningCustomersDataFirstTimeByAPIInit(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filterBy = SessionData.getDataTbVal(dataTable, row, "Filter by");
            String firstTime = SessionData.getDataTbVal(dataTable, row, "Customer type 1");
            String returning = SessionData.getDataTbVal(dataTable, row, "Customer type 2");

            firstTimeCusValue = Double.parseDouble(aStep.getDataFirstAndReturningCusInit(filterBy, firstTime));
            returningCusValue = Double.parseDouble(aStep.getDataFirstAndReturningCusInit(filterBy, returning));
            totalValue = firstTimeCusValue + returningCusValue;
            List<Object> cusFirstTimeVsReturningInit = asList(firstTimeCusValue, returningCusValue, totalValue);
            System.out.println("Filter by: " + filterBy + "\n======================" + "\n" +
                    "Data init:\n" +
                    "First-time Customer total " + filterBy + ": " + firstTimeCusValue + "\n" +
                    "Returning Customer total " + filterBy + ": " + returningCusValue + "\n" +
                    "Total values: " + totalValue + "\n" +
                    "==============");
            before.put(filterBy + " - " + firstTime, cusFirstTimeVsReturningInit);
            System.out.println(filterBy + " " + firstTime + cusFirstTimeVsReturningInit);
            before.put(filterBy + " - " + returning, cusFirstTimeVsReturningInit);
            System.out.println(filterBy + " " + returning + cusFirstTimeVsReturningInit);
        }
    }

    @Then("^get chart Returning customers rate by API init$")
    public void getChartReturningCustomersRateByAPIBefore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filterBy = SessionData.getDataTbVal(dataTable, row, "Filter by");
            String firstTime = SessionData.getDataTbVal(dataTable, row, "Customer type 1");
            String returning = SessionData.getDataTbVal(dataTable, row, "Customer type 2");

            firstTimeCusValue = Double.parseDouble(aStep.getDataReturningRateInit(filterBy, firstTime));
            returningCusValue = Double.parseDouble(aStep.getDataReturningRateInit(filterBy, returning));
            if (firstTimeCusValue + returningCusValue > 0) {
                totalValue = returningCusValue / (firstTimeCusValue + returningCusValue) * 100;
            } else {
                totalValue = 0;
            }
            List<Object> cusReturningRateInit = asList(firstTimeCusValue, returningCusValue, totalValue);
            System.out.println("Filter by: " + filterBy + "\n======================" + "\n" +
                    "Data init:\n" +
                    "First-time Customer: " + firstTimeCusValue + "\n" +
                    "Returning Customer: " + returningCusValue + "\n" +
                    "Rate values: " + totalValue + "\n" +
                    "==============");
            before.put(filterBy + " - " + firstTime, cusReturningRateInit);
            System.out.println(filterBy + " " + firstTime + cusReturningRateInit);
            before.put(filterBy + " - " + returning, cusReturningRateInit);
            System.out.println(filterBy + " " + returning + cusReturningRateInit);
        }
    }

    @Then("^verify data chart First-time vs returning customers$")
    public void verifyDataChartFirstTimeVsReturningCustomers(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filterBy = SessionData.getDataTbVal(dataTable, row, "Filter by");
            String firstTime = SessionData.getDataTbVal(dataTable, row, "Customer type 1");
            String returning = SessionData.getDataTbVal(dataTable, row, "Customer type 2");
            double firstTimeValueIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "First-time customers"));
            double returningValueIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Returning customers"));
            double totalSalesValueIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total sales"));
            double totalOrdersValueIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total orders"));

            firstTimeCusValue = Double.parseDouble(aStep.getDataFirstAndReturningCusAfter(filterBy, firstTime));
            returningCusValue = Double.parseDouble(aStep.getDataFirstAndReturningCusAfter(filterBy, returning));
            totalValue = firstTimeCusValue + returningCusValue;
            List<Object> cusFirstTimeVsReturningActual = asList(firstTimeCusValue, returningCusValue, totalValue);

            System.out.println("Filter by: " + filterBy + "\n======================" +
                    "Data after:\n" +
                    "First-time Customer total " + filterBy + ": " + firstTimeCusValue + "\n" +
                    "Returning Customer total " + filterBy + ": " + returningCusValue + "\n" +
                    "Total values: " + totalValue + "\n" +
                    "==============");
            after.put(filterBy + " - " + firstTime, cusFirstTimeVsReturningActual);
            System.out.println(filterBy + " " + firstTime + cusFirstTimeVsReturningActual);
            after.put(filterBy + " - " + returning, cusFirstTimeVsReturningActual);
            System.out.println(filterBy + " " + returning + cusFirstTimeVsReturningActual);

            aStep.verifyDataAnalyticsChanged((double) (before.get(filterBy + " - " + firstTime).get(0)), firstTimeValueIncrease, (double) (after.get(filterBy + " - " + firstTime).get(0)));
            aStep.verifyDataAnalyticsChanged((double) (before.get(filterBy + " - " + returning).get(1)), returningValueIncrease, (double) (after.get(filterBy + " - " + firstTime).get(1)));
            if ("sales".equals(filterBy)) {
                aStep.verifyDataAnalyticsChanged((double) (before.get(filterBy + " - " + returning).get(2)), totalSalesValueIncrease, (double) (after.get(filterBy + " - " + firstTime).get(2)));
            }
            if ("order".equals(filterBy)) {
                aStep.verifyDataAnalyticsChanged((double) (before.get(filterBy + " - " + returning).get(2)), totalOrdersValueIncrease, (double) (after.get(filterBy + " - " + firstTime).get(2)));
            }
        }
    }

    @Then("^verify data chart Returning customers rate$")
    public void verifyDataChartReturningCustomersRate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String filterBy = SessionData.getDataTbVal(dataTable, row, "Filter by");
            String firstTime = SessionData.getDataTbVal(dataTable, row, "Customer type 1");
            String returning = SessionData.getDataTbVal(dataTable, row, "Customer type 2");

            firstTimeCusValue = Double.parseDouble(aStep.getDataReturningRateAfter(filterBy, firstTime));
            returningCusValue = Double.parseDouble(aStep.getDataReturningRateAfter(filterBy, returning));
            double returningRateActual = Double.parseDouble(aStep.getDashboardReturningRate());
            totalValue = Math.round((returningCusValue / (firstTimeCusValue + returningCusValue) * 100) * 100) / 100.0;

            System.out.println("Filter by: " + filterBy + "\n======================" +
                    "Data after:\n" +
                    "First-time Customer total " + filterBy + ": " + firstTimeCusValue + "\n" +
                    "Returning Customer total " + filterBy + ": " + returningCusValue + "\n" +
                    "Rate values: " + totalValue + "\n" +
                    "==============");

            assertThat(totalValue).isEqualTo(returningRateActual);
        }
    }

    @Then("^select field Report By \"([^\"]*)\"$")
    public void selectDdlWithLabel(String option) {
        aStep.selectDdlWithLabel(option);
    }

    @Then("^verify UTM value by \"([^\"]*)\"$")
    public void verifyUtmValueBy(String option, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String campaignOption = SessionData.getDataTbVal(dataTable, row, "Campaign Option");
            String addColumn = SessionData.getDataTbVal(dataTable, row, "Add Column");
            double viewProductIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "View product"));
            double addToCardIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Add to card"));
            double reachCheckOutIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Reach checkOut"));
            double ordersIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Orders"));
            double netQuantityIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Net quantity"));
            double totalSalesIncrease = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total sales"));


            if (addColumn.equals("")) {
                n = 0;
            } else {
                n = 1;
            }
            String addColumnActual = aStep.getValueAfter(n, addColumn, campaignOption);
            String viewProductActual = aStep.getValueAfter(n + 1, addColumn, campaignOption);
            String addToCardActual = aStep.getValueAfter(n + 2, addColumn, campaignOption);
            String reachCheckOutActual = aStep.getValueAfter(n + 3, addColumn, campaignOption);
            String ordersActual = aStep.getValueAfter(n + 4, addColumn, campaignOption);
            double conversionRateActual = Double.parseDouble(aStep.getValueAfter(n + 5, addColumn, campaignOption));
            String netQuantityActual = aStep.getValueAfter(n + 6, addColumn, campaignOption);
            double AOVActual = Double.parseDouble(aStep.getValueAfter(n + 7, addColumn, campaignOption));
            double AOIActual = Double.parseDouble(aStep.getValueAfter(n + 8, addColumn, campaignOption));
            String totalSalesActual = aStep.getValueAfter(n + 9, addColumn, campaignOption);
            double conversionRate = Math.round((Double.parseDouble(ordersActual) / Double.parseDouble(viewProductActual) * 100) * 100) / 100.0;
            double AOI = Math.round((Double.parseDouble(netQuantityActual) / Double.parseDouble(ordersActual) * 100)) / 100.0;
            double AOV = Math.round((Double.parseDouble(totalSalesActual) / Double.parseDouble(ordersActual) * 100)) / 100.0;

            List<String> dataAfter = asList(addColumnActual, viewProductActual, addToCardActual, reachCheckOutActual, ordersActual, netQuantityActual, totalSalesActual);
            after.put(campaignOption + " " + addColumn, dataAfter);

            switch (addColumn) {
                case "":
                    break;
                default:
                    assertThat(addColumn).isEqualTo(addColumnActual);
            }
            assertThat(conversionRateActual).isEqualTo(conversionRate);
            assertThat(AOIActual).isEqualTo(AOI);
            assertThat(AOVActual).isEqualTo(AOV);
            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(1)), viewProductIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(1)));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(2)), addToCardIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(2)));
//            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(3)), reachCheckOutIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(3)));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(4)), ordersIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(4)));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(6)), netQuantityIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(5)));
            aStep.verifyDataAnalyticsChanged(Double.parseDouble((String) before.get(campaignOption + " " + addColumn).get(9)), totalSalesIncrease, Double.parseDouble((String) after.get(campaignOption + " " + addColumn).get(6)));
        }
    }

    @And("^choice option report by \"([^\"]*)\"$")
    public void choiceOptionReportBy(String option) {
        aStep.choiceOptionReportBy(option);

    }

    @When("^add column \"([^\"]*)\"$")
    public void addColumn(String column) {
        aStep.addColumn(column);
    }


    @Given("^verify UTM Channel value$")
    public void verifyUTMChannelValueIncrease(List<List<String>> dataTable) {
        int index = aStep.getIndexColumn();
        String[] arr = new String[index];
        for (int i = 1; i < index; i++) {
            arr[i] = aStep.getActualValue(i);
        }
        String referrerActual = arr[1];
        String visitorsActual = arr[2];

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String referrer = SessionData.getDataTbVal(dataTable, row, "Referrer name");
            String visitorsIncrease = SessionData.getDataTbVal(dataTable, row, "Unit visitors");

            assertThat(referrerActual).isEqualTo(referrer);
            aStep.verifyDataAnalyticsChanged(Double.parseDouble(visitorsBefore), Double.parseDouble(visitorsIncrease), Double.parseDouble(visitorsActual));

        }
        visitorsBefore = visitorsActual;
    }

    @And("^get data visitors before$")
    public void getDataVisitorsBefore() {
        visitorsBefore = aStep.getVisitorsBefore();
    }

    @And("get data \"([^\"]*)\" before$")
    public void getDataBefore(String option, List<List<String>> dataTable) {

        commonSteps.waitABit(1000);

        int index = aStep.getIndexColumn();
        String[] arr = new String[index];
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String valueAdd = SessionData.getDataTbVal(dataTable, row, "Add Column");
            for (int i = 1; i < index; i++) {
                arr[i] = aStep.getValueBefore(i, valueAdd, value);
            }
            if (valueAdd.equals("")) {
                n = 0;
            } else {
                n = 1;
            }
            String addColumnBefore = arr[n];
            String viewProductBefore = arr[n + 1];
            String addToCartBefore = arr[n + 2];
            String reachCheckOutBefore = arr[n + 3];
            String ordersBefore = arr[n + 4];
            String conversionRateBefore = arr[n + 5];
            String netQuantityBefore = arr[n + 6];
            String AOVBefore = arr[n + 7];
            String AOIBefore = arr[n + 8];
            String totalSalesBefore = arr[n + 9];
            List<String> dataBefore = asList(addColumnBefore, viewProductBefore, addToCartBefore, reachCheckOutBefore, ordersBefore, conversionRateBefore, netQuantityBefore, AOVBefore, AOIBefore, totalSalesBefore);
            before.put(value + " " + valueAdd, dataBefore);

        }
    }

    @Then("verify total sales and total orders in homepage")
    public void verifyOverviewContentInHomepage() {
        aStep.verifyOverviewContent();
    }


    @Given("get \"([^\"]*)\" data analytics new version by API")
    public void getDataAnalyticsNewVersionByAPI(String shopType, List<List<String>> dataTable) {
        json = aStep.getAnalyticsAPI(shopType, "shop_cr");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String source = SessionData.getDataTbVal(dataTable, row, "Source");
            viewProductInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "view_content"));
            addToCartInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "add_to_cart"));
            reachedCheckoutInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "reached_checkout"));
            sessionConvertedInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "session_convert"));
            totalOrderInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_orders"));
            totalItemInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_items"));
            totalSaleInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_sales"));
            totalProfitInit = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_profit"));

            List<Object> AnaInit = asList(viewProductInit, addToCartInit, reachedCheckoutInit, sessionConvertedInit, totalOrderInit, totalItemInit, totalSaleInit, totalProfitInit);

            before.put(source, AnaInit);
        }
    }

    @Given("verify \"([^\"]*)\" data analytic new version by API")
    public void verifyDataAnalyticNewVersionByAPI(String shopType, List<List<String>> dataTable) {
        json = aStep.getAnalyticsAPI(shopType, "shop_cr");

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String source = SessionData.getDataTbVal(dataTable, row, "Source");
            double viewProductInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "View Product"));
            double addToCartInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Add to Cart"));
            double reachedCheckoutInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Reached Checkout"));
            double SessionConvertedInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Session Converted"));
            double totalOrdersInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total Orders"));
            double totalItemsInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total Items"));
            double totalSalesInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total Sales"));
            double totalProfitInc = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Profit"));


            double viewProductActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "view_content"));
            double addToCartActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "add_to_cart"));
            double reachedCheckoutActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "reached_checkout"));
            double sessionConvertedActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "session_convert"));
            double totalOrderActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_orders"));
            double totalItemActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_items"));
            double totalSaleActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_sales"));
            double totalProfitActual = Double.parseDouble(aStep.getDataAnalyticsByAPI(json, source, "total_profit"));

            List<Object> AnaActual = asList(viewProductActual, addToCartActual, reachedCheckoutActual, sessionConvertedActual, totalOrderActual, totalItemActual, totalSaleActual, totalProfitActual);

            after.put(source, AnaActual);

            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(0), viewProductInc, (Double) after.get(source).get(0));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(1), addToCartInc, (Double) after.get(source).get(1));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(2), reachedCheckoutInc, (Double) after.get(source).get(2));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(3), SessionConvertedInc, (Double) after.get(source).get(3));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(4), totalOrdersInc, (Double) after.get(source).get(4));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(5), totalItemsInc, (Double) after.get(source).get(5));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(6), totalSalesInc, (Double) after.get(source).get(6));
            aStep.verifyDataAnalyticsChanged((Double) before.get(source).get(7), totalProfitInc, (Double) after.get(source).get(7));

            before.put(source, AnaActual);
        }

    }
}
