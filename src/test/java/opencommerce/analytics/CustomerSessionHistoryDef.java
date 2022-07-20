package opencommerce.analytics;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.AnalyticSteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.CustomerSessionHistorySteps;
import com.opencommerce.shopbase.dashboard.apps.analytics.steps.UTMAnalyticSteps;
import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.customer.steps.CustomerSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

public class CustomerSessionHistoryDef {

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
    @Steps
    CustomerSessionHistorySteps customerSessionStep;
    @Steps
    CustomerSteps customerSteps;


    public double totalSaleInit;
    public double profitInit;
    public double viewProductInit;
    public double addToCartInit;
    public double reachedCheckoutInit;
    public double totalOrderInit;
    public double quantityInit;

    public int totalSessionInit;


    HashMap<String, List> before = new HashMap<>();
    HashMap<String, List> after = new HashMap<>();
    JsonPath jsonPath;


    @And("verify last session block")
    public void verifyLastSessionBlock(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sourceidentifier = SessionData.getDataTbVal(dataTable, row, "Source identifier");
            String referringsite = SessionData.getDataTbVal(dataTable, row, "Referring site");
            String firstpagevisited = SessionData.getDataTbVal(dataTable, row, "First page visited");
            customerSessionStep.verifySourceIdentifier(sourceidentifier);
            customerSessionStep.verifyReferingsite(referringsite);
            customerSessionStep.verifyFirstPageVisited(firstpagevisited);

        }
    }

//    @Then("verify popup Session history")
//    public void verifyPopupSessionHistory(List<List<String>> dataTable) {
//        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
//            String totalsessions= SessionData.getDataTbVal(dataTable,row,"TOTAL SESSIONS");
//            String activity= SessionData.getDataTbVal(dataTable,row,"Activity");
//            customerSessionStep.veriryTotalSession(totalsessions);
//            customerSessionStep.verifyActivity(activity);
//        }
//    }

    @And("click button View all sessions")
    public void clickButtonViewAllSessions() {
        customerSessionStep.clickButtonViewAllSessions();

    }

    @And("search customer and click to customer detail")
    public void searchCustomerAndClickToCustomerDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String customerEmail = SessionData.getDataTbVal(dataTable, row, "Customer email");
            String customerName = SessionData.getDataTbVal(dataTable, row, "Customer name");
            customerSteps.searchCustomerByEmail(customerEmail);
            customerSteps.selectCustomer(customerName);
        }

    }

    @And("get total sessions of customer")
    public void getTotalSessionsOfCustomer() {
        totalSessionInit = customerSessionStep.getTotalSessionsOfCustomer();
    }

    @And("verify total sessions change")
    public void verifyTotalSessionsChange(List<List<String>> dataTable) {
        int totalSessionsActual = customerSessionStep.getTotalSessionsOfCustomer();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int totalSessionsInc = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Total sessions"));
            String activity = SessionData.getDataTbVal(dataTable, row, "Activity");
            String convertedOrder = SessionData.getDataTbVal(dataTable, row, "Converted to order");
            customerSessionStep.verifyTotalSessionsChange(totalSessionInit, totalSessionsInc, totalSessionsActual);
            customerSessionStep.verifyLastSessionDescription(totalSessionsActual, activity);
            if (convertedOrder.matches("@Not converted@")) {
                customerSessionStep.verifySessionCovertToOrder(totalSessionsActual, false);
            } else {
                customerSessionStep.verifySessionCovertToOrder(totalSessionsActual, true);
            }
        }
    }

    @Then("verify popup Session history after having new sessions")
    public void verifyPopupSessionHistoryAfterHavingNewSessions() {
    }

    @And("verify UTM parameters block")
    public void verifyUTMParametersBlock(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String utmSource = SessionData.getDataTbVal(dataTable, row, "Source");
            String utmMedium = SessionData.getDataTbVal(dataTable, row, "Medium");
            String utmCampaign = SessionData.getDataTbVal(dataTable, row, "Campaign");
            String utmTerm = SessionData.getDataTbVal(dataTable, row, "Term");
            String utmContent = SessionData.getDataTbVal(dataTable, row, "Content");
            customerSessionStep.verifyUTM("Source", utmSource);
            customerSessionStep.verifyUTM("Medium", utmMedium);
            customerSessionStep.verifyUTM("Campaign", utmCampaign);
            customerSessionStep.verifyUTM("Term", utmTerm);
            customerSessionStep.verifyUTM("Content", utmContent);
        }
    }

    @And("click button View all sessions and View full sessions")
    public void clickButtonViewAllSessionsAndViewFullSessions() {
        customerSessionStep.clickLinkViewAllSessions();
        customerSessionStep.clickButtonViewFullSession();
    }

//    @Then("verify popup Session history after having new sessions")
//    public void verifyPopupSessionHistoryAfterHavingNewSessions(List<List<String>> dataTable) {
//        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
//            String totalsession
//
//        }
//    }
}
