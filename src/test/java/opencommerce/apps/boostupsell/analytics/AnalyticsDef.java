package opencommerce.apps.boostupsell.analytics;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.InCartOfferSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.analytics.AnalyticsAppSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.QuickViewSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import common.utilities.SessionData;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyticsDef {

    @Steps
    AnalyticsAppSteps analyticsAppSteps;
    @Steps
    InCartOfferSteps inCartOfferSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;
    @Steps
    QuickViewSteps quickViewSteps;
    @Steps
    CommonSteps commonSteps;

    @Given("^Description: <\"([^\"]*)\">$")
    public void description() throws Throwable {

    }

    HashMap<String, List<Object>> before = new HashMap<>();

    @Given("^get data analytic by API as \"([^\"]*)\"$")
    public void getDataAnalyticVivaBoostUpsell(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String chartType = SessionData.getDataTbVal(dataTable, row, "Chart type");
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            List<Object> a = analyticsAppSteps.getAnalyticsByAPI(chartType, time);
            analyticsAppSteps.logInfor("Before: " + chartType + "-" + time + ":" + a);
            before.put(chartType + time, a);
        }
    }

    @Given("^verify data analytic by API as \"([^\"]*)\"$")
    public void verifyAnalyticByAPI(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            commonSteps.waitABit(8000);
            String chartType = SessionData.getDataTbVal(dataTable, row, "Chart type");
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            String usell = "0" + SessionData.getDataTbVal(dataTable, row, "Usell");
            String shopbase = "0" + SessionData.getDataTbVal(dataTable, row, "Shopbase");
            String total = "0" + SessionData.getDataTbVal(dataTable, row, "Total");

            String viewProduct = "0" + SessionData.getDataTbVal(dataTable, row, "View product");
            String addToCart = "0" + SessionData.getDataTbVal(dataTable, row, "Add to cart");
            String reachedCheckout = "0" + SessionData.getDataTbVal(dataTable, row, "Reached checkout");
            String sessionConverted = "0" + SessionData.getDataTbVal(dataTable, row, "Session converted");

            List<Object> anaBefore;
            List<Object> expectResult;
            anaBefore = before.get(chartType + time);

            if ("actions".equals(chartType)) {
                Object expectedViewProduct = (Integer) anaBefore.get(0) + Integer.parseInt(viewProduct);
                Object expectedAddToCart = (Integer) anaBefore.get(1) + Integer.parseInt(addToCart);
                Object expectedReachedCheckout = (Integer) anaBefore.get(2) + Integer.parseInt(reachedCheckout);
                Object expectedSessionConverted = (Integer) anaBefore.get(3) + Integer.parseInt(sessionConverted);
                expectResult = asList(expectedViewProduct, expectedAddToCart, expectedReachedCheckout, expectedSessionConverted);
            } else {
                double expectedUpsell = calculateAnalytic((double) anaBefore.get(0), Double.parseDouble(usell));
                double expectedShopbase = calculateAnalytic((double) anaBefore.get(1), Double.parseDouble(shopbase));
                double expectedTotal = calculateAnalytic((double) anaBefore.get(2), Double.parseDouble(total));

                expectResult = asList(expectedUpsell, expectedShopbase, expectedTotal);
            }
            int index = 0;
            List<Object> actualResult;
            do {
                index++;
                analyticsAppSteps.waitABit(6000);
                actualResult = analyticsAppSteps.getAnalyticsByAPI(chartType, time);

                analyticsAppSteps.logInfor("Expect  : " + chartType + "-" + time + ":" + expectResult);
                analyticsAppSteps.logInfor("After : " + chartType + "-" + time + ":" + actualResult);
            }
            while ((!expectResult.equals(actualResult)) & index <= 10);
            assertThat(actualResult).isEqualTo(expectResult);
        }
    }

    private double calculateAnalytic(double a, double b) {
        String total = String.format("%.2f", a + b);
        return Double.parseDouble(total);

    }

    @When("^add recommend product of target product \"([^\"]*)\" to cart$")
    public void addRecommendToCart(String targetProduct) {
        reviewOnSFSteps.waitForLoadApps("Upsell");
        inCartOfferSteps.clickBtnAddOnProductIncart(targetProduct, "");
        quickViewSteps.clickAddToCart();
        quickViewSteps.verifyCloseQickView();
    }
}
