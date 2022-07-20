package opencommerce.fulfillment_service.product_sourcing;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestListStep;
import com.vladsch.flexmark.ast.Link;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

public class RequestListDef {


    @Steps
    RequestListStep requestListStep;

    @And("click button {string}")
    public void clickButton(String quotationRequestName) {
        requestListStep.clickButton(quotationRequestName);
    }

    @Given("get count sale order in tab {string}")
    public void get_count_sale_order_in_tab(String tab) {
        requestListStep.switchToTabInRequestList(tab);
        OrderVariable.numberInTabRequestList = requestListStep.getNumberInTab(tab);
        int i = 0;
        while (OrderVariable.numberInTabRequestList == 0) {
            requestListStep.reloadPage();
            requestListStep.waitABit(3000);
            i++;
            if (i == 5) {
                break;
            }
        }
    }

    @Then("user switch to tab {string} in Request list")
    public void user_switch_to_tab_in_Request_list(String tab) {
        requestListStep.switchToTabInRequestList(tab);
    }

    @Then("check product just create into {string} tab")
    public void checkProductJustCreateIntoTab(String tabName) {
//        requestListStep.reloadPage();
        requestListStep.checkNumberAfterRequesProduct(OrderVariable.numberInTabRequestList, tabName);
        requestListStep.searchQuotationWithURL();
//        requestListStep.verifyResult();
    }

    @And("click {string} tab")
    public void clickTab(String quotationTab) {
        requestListStep.clickTab(quotationTab);
    }
    @And("check click icon detail with {string}")
    public void checkClickIconDetailWith(String quotationId) {
        requestListStep.checkClickIconDetail(quotationId);
    }

    @And("verify quotation")
    public void verifyQuotation(List<List<String>> data) {
//        requestListStep.searchQuotationId(OrderVariable.quotationID);
        for(int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String link = SessionData.getDataTbVal(data, row, "Source url");
            String status = SessionData.getDataTbVal(data, row, "Status quotation");
            String tab = SessionData.getDataTbVal(data, row, "Tab");
//            requestListStep.verifySourceUrl(link);
//            requestListStep.verifyStatus(status);
            switch (tab) {
                case "Submitted request":
                    requestListStep.switchToTabInRequestList("Submitted request");
                    requestListStep.verifySourceUrl(link);
                    requestListStep.verifyStatus(status);
                    break;
                case "Quotation created":
                    requestListStep.switchToTabInRequestList("Quotation created");
                    requestListStep.verifySourceUrl(link);
                    requestListStep.verifyStatus(status);
                    break;
                case "Needs update":
                    requestListStep.switchToTabInRequestList("Needs update");
                    requestListStep.verifySourceUrl(link);
                    requestListStep.verifyStatus(status);
                    break;
                case "Expired":
                    requestListStep.switchToTabInRequestList("Expired");
                    requestListStep.verifySourceUrl(link);
                    requestListStep.verifyStatus(status);
                    break;
                default:
                    requestListStep.switchToTabInRequestList("No result");
                    requestListStep.verifySourceUrl(link);
                    requestListStep.verifyStatus(status);
                    break;

            }
        }
    }

    @And("search quotation after created")
    public void searchQuotationAfterCreated() {
        requestListStep.searchQuotationId(OrderVariable.quotationID);}
}
