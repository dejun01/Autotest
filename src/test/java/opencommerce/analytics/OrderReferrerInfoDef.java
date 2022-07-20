//package opencommerce.analytics;
//
//import com.opencommerce.shopbase.common.pages.CommonPage;
//import com.opencommerce.shopbase.dashboard.apps.analytics.pages.OrderReferrerInfoPage;
//import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import net.thucydides.core.annotations.Steps;
//import org.fluentlenium.core.annotation.Page;
//
//import java.util.List;
//
//public class OrderReferrerInfoDef {
//    @Steps
//    OrderSteps orderSteps;
//
//    @Page
//    CommonPage commonPage;
//
//    OrderReferrerInfoPage orderReferrerInfoPage;
//
//    @Given("initialization data")
//    public void initializationData() {
//        orderReferrerInfoPage.init();
//    }
//
//    @Then("verify filter following a recent order created '(.*)'")
//    public void verifyFilterFollowingARecentOrderCreated(List<List<String>> dataTable) {
//        List<String> data_list;
//        data_list = orderReferrerInfoPage.getFilterOrderByAPI(dataTable);
//        orderSteps.accessOrderDetailById(data_list.get(0));
//        orderReferrerInfoPage.loadAndVerifyOrderTrackingData(dataTable, data_list, "1");
//    }
//
//    @When("user navigate to checkout screen")
//    public void copyCheckoutUrlAndOpenMoreTwoTimes() {
//        orderReferrerInfoPage.copyCheckoutUrl(commonPage);
//        orderReferrerInfoPage.openCheckoutUrlManyTimes(2);
//    }
//
//    @Then("access to a recent order created and verify data {string}")
//    public void accessToARecentOrderCreatedAndVerifyData(String number_of_session, List<List<String>> dataTable) {
//        List<String> data_list;
//        data_list = orderReferrerInfoPage.getFilterOrderByAPI(dataTable);
//        orderSteps.accessOrderDetailById(data_list.get(0));
//        orderReferrerInfoPage.loadAndVerifyOrderTrackingData(dataTable, data_list, number_of_session);
//    }
//
//    @Then("filter with UTM parameters and verify first order in list")
//    public void filterOrderWithUTM(List<List<String>> dataTable) {
//        orderReferrerInfoPage.openOrdersFilterWithUTM(dataTable);
//        List<String> data_list;
//        data_list = orderReferrerInfoPage.getFilterOrderByAPI(dataTable);
//        orderSteps.accessOrderDetailById(data_list.get(0));
//        orderReferrerInfoPage.loadAndVerifyOrderFilter(dataTable, data_list);
//    }
//}