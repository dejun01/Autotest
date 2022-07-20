package opencommerce.fulfillment_service.claim;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.ClaimListSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class ClaimListDef {

    String shopName = LoadObject.getProperty("shop");
    public static String orderName = LoadObject.getProperty("order_1");
    public static String orderName2 = LoadObject.getProperty("order_2");
    @Steps
    OrderSteps orderSteps;

    @Steps
    ClaimListSteps claimListSteps;
    int countClaimCurrentNew = 0;
    int countClaim = 0;
    String claimCurrentNew = "";
    public static String claimNew = "";
    public static String claimID = "";


    @Given("search claim id {string} then go to claim detail")
    public void search_claim_id_then_go_to_claim_detail(String claim) {
        if (claim.contains("@")) {
            claim = claimListSteps.getClaimInFirst();
        }
        claimListSteps.searchClaim(claim);
        claimListSteps.goToClaimDetail();
    }

    @And("Get count in tab {string}")
    public void getCountInTab(String tab) {
        countClaimCurrentNew = claimListSteps.getCount();
    }

    @And("Get claim ID")
    public void getClaimID() {
        claimCurrentNew = claimListSteps.getTextClaim();

    }

    @Then("Search and verify new claim in tab")
    public void searchAndVerifyNewClaimInTab(List<List<String>> dataTable) {
        countClaim = countClaimCurrentNew + 1;
        assertThat(claimListSteps.getCount()).isEqualTo(countClaim);
        claimNew = claimListSteps.getClaimInFirst();
        claimListSteps.searchClaim(claimNew);
        claimListSteps.waitToDataClaimShow();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String date = SessionData.getDataTbVal(dataTable, row, "Date");
            String order = SessionData.getDataTbVal(dataTable, row, "Order");
            String preferredResolution = SessionData.getDataTbVal(dataTable, row, "Preferred Resolution");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
//            if (!date.isEmpty()) {
//                date = DateTimeUtil.getTodayFormats();
//            }
            if (order.contains("@")) {
                order = orderNumber;
            } else {
                order = orderName;
            }
            claimListSteps.verifyInfoClaimInTab(claimNew, date, order, preferredResolution, status);
        }
    }

    @Then("go to order detail from claim list")
    public void go_to_order_detail_from_claim_list() {
        claimListSteps.selectOrderName();
        claimListSteps.swichToWindow();
    }

    @And("Cancel claim")
    public void cancelClaim() {
        claimNew = claimListSteps.getClaimInFirst();
        claimListSteps.cancelClaim(claimNew);
    }


    @And("{string} a claim in hive_sb")
    public void aClaimInHive_sb(String action) {
//        claimNew = claimListSteps.getClaimInFirst();
//        claimID = claimListSteps.getClaimID(claimNew);
        claimListSteps.approvedClaim(Integer.parseInt(claimID), shopName, action);
    }

    @And("^search order by text (order|the second order) then select$")
    public void searchOrderByTextOrderThenSelect(String orders) {
        switch (orders) {
            case "order":
                orderSteps.searchOrderNameOnOrderList(orderName);
                orderSteps.clickNameOrderOnList(orderName);
                orderSteps.verifyDisplayPopupLiveChat();
                break;
            case "the second order":
                orderSteps.clickNameOrderOnList(orderName2);
                orderSteps.verifyDisplayPopupLiveChat();
                break;
        }

    }

}
