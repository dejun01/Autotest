package opencommerce.orders.order;

import com.opencommerce.shopbase.dashboard.orders.steps.MoreFilterSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.DateTimeUtil;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNameList;
import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.junit.Assert.assertThat;

public class MoreFilterDef {

    @Steps
    OrderSteps orderSteps;
    @Steps
    MoreFilterSteps moreFilterSteps;


    String condition = "";
    String valueFulfillmentService = "";
    String valueTrackingNumber = "";
    String valueProductMapping = "";
    String trackingNumber = "";
    String filterTemplates = "" + DateTimeUtil.getTodayFormatDate();
    String value = "";
    ArrayList<String> listValue = new ArrayList<>();
    ArrayList<String> listCondition = new ArrayList<>();

    @And("More filter")
    public void moreFilter() {
        moreFilterSteps.clickBtnFilter("More filters");
    }
    @Then("Display text {string}")
    public void displayText(String textName) {
        moreFilterSteps.verifyErr(textName);
    }

    @Then("verify save info filter")
    public void verifySaveInfoFilter() {
        moreFilterSteps.verifyFilterAfterSave(listValue,listCondition);

    }

    @Then("Verify display filter header")
    public void verifyDisplayFilterHeader(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tag = SessionData.getDataTbVal(dataTable, row, "tag");
            String fulfillmentService = SessionData.getDataTbVal(dataTable, row, "fulfillment service");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "tracking number");
            String productMapping = SessionData.getDataTbVal(dataTable, row, "product mapping");
            moreFilterSteps.verifyTextFilter(tag, fulfillmentService, trackingNumber, productMapping);

        }


    }

    @And("Filter by condition as {string}")
    public void filterByConditionAs(String key, List<List<String>> dataTable) {
        moreFilterSteps.clickBtnFilter("More filters");
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            condition = SessionData.getDataTbVal(dataTable, row, "condition");
            value = SessionData.getDataTbVal(dataTable, row, "value");
            filterTemplates += SessionData.getDataTbVal(dataTable, row, "filter templates");
            listValue.add(value);
            listCondition.add(condition);
            if (key.equals("4") || key.equals("5")) {
                moreFilterSteps.clickBTCondition(condition);
                moreFilterSteps.enterFilterByCondition(condition, value);
            } else {
                moreFilterSteps.clickCondition(condition, value);

            }

        }
        moreFilterSteps.clickSaveFilter(filterTemplates);
        moreFilterSteps.clickBTDone();

    }

    @Then("verify order after filter by condition as {string}")
    public void verifyOrderAfterFilterByConditionAs(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            trackingNumber = SessionData.getDataTbVal(dataTable, row, "tracking number");
            String fulfillmentStatus = SessionData.getDataTbVal(dataTable, row, "fulfillment status");
            orderNumber = SessionData.getDataTbVal(dataTable, row, "order");
            String carrier = SessionData.getDataTbVal(dataTable, row, "Shipping carrier");
            String thirdPartyTracking = SessionData.getDataTbVal(dataTable, row, "Third party tracking");
            if (!fulfillmentStatus.isEmpty()) {
                moreFilterSteps.verifyInforSearch(orderNumber, fulfillmentStatus);
            }
            if (!trackingNumber.isEmpty()) {
                orderSteps.clickNameOrderOnList(orderNumber);
                orderSteps.clickBTTrackingNumber();
                orderSteps.verifyTrackingNumberLinkToThirdParty(carrier,thirdPartyTracking,trackingNumber);
            }
        }
    }

    @Then("Verify display text {string}")
    public void verifyDisplayText(String text) {
        Assertions.assertThat(moreFilterSteps.getTextByShopBaseFulfillment()).isEqualTo(text);
    }

    @And("Filter by templates")
    public void filterByTemplates() {
        moreFilterSteps.chooseFilterTemplates(filterTemplates);
    }

    @And("Filter order by condition")
    public void filterOrderByConditionAs(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String IsText = SessionData.getDataTbVal(dataTable, row, "IsText");
            String filterType = SessionData.getDataTbVal(dataTable, row, "Filter button");
            moreFilterSteps.clickBtnFilter(filterType);
            condition = SessionData.getDataTbVal(dataTable, row, "condition");
            value = SessionData.getDataTbVal(dataTable, row, "value");
            listValue.add(value);
            listCondition.add(condition);
            if (IsText.equals("1")) {
                moreFilterSteps.clickOnCondition(condition);
                moreFilterSteps.enterValueFilter(condition, value);
            } else {
                moreFilterSteps.clickOnCondition(condition);
                moreFilterSteps.chooseValueFilter(condition,value);
            }
            moreFilterSteps.clickApplyFilter();
        }


    }

    @And("search order by {string} as {string}")
    public void searchOrderBy( String searchType,String key,List<List<String>> dataTable ) {
        moreFilterSteps.clickBtnFilter("Order name");
        moreFilterSteps.chooseSearchType(searchType);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            String dataFilter = SessionData.getDataTbVal(dataTable, row, "Data filter");
            String expect = SessionData.getDataTbVal(dataTable, row, "Expect");
            orderSteps.enterTextThenEnter(dataFilter);
            if (expect.contains("@")) {
                expect = orderNumber;
                orderSteps.verifyOrderInformation(expect);
            }
            else
            {
                orderSteps.notResult(dataFilter);
            }

        }
}



}



