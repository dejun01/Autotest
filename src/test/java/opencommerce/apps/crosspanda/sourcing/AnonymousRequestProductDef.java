package opencommerce.apps.crosspanda.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.AuthenCPSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.AnonymousRequestProductSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class AnonymousRequestProductDef {
    @Steps
    AnonymousRequestProductSteps anonymousRequestProductSteps;
    @Steps
    RequestProductSteps requestProductSteps;
    @Steps
    AuthenCPSteps authenCPSteps;

    @Given("^open anonymous request product page$")
    public void open_anonymous_request_product_page() {
        anonymousRequestProductSteps.openAnonymousRequestProductPage();
    }

    @When("^verify UI anonymous request product page$")
    public void verify_UI_anonymous_request_product_page(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String supplier = SessionData.getDataTbVal(dataTable, row, "Choose your supplier");
            String revenue = SessionData.getDataTbVal(dataTable, row, "Choose your monthly revenue");
            String links = SessionData.getDataTbVal(dataTable, row, "Links request");
            String contact = SessionData.getDataTbVal(dataTable, row, "Contact");
            anonymousRequestProductSteps.verifyDataSupplier(supplier);
            anonymousRequestProductSteps.verifyDataRevenue(revenue);
            anonymousRequestProductSteps.verifyLinkRequest(links);
            anonymousRequestProductSteps.verifyContact(contact);
            authenCPSteps.refreshPage();
        }
    }

    @Then("^anonymous request product as \"([^\"]*)\"$")
    public void anonymous_request_product_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String supplier = SessionData.getDataTbVal(dataTable, row, "Supplier");
            String revenue = SessionData.getDataTbVal(dataTable, row, "Revenue");
            String links = SessionData.getDataTbVal(dataTable, row, "Links");
            String note = SessionData.getDataTbVal(dataTable, row, "Note");
            String facebook = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String skype = SessionData.getDataTbVal(dataTable, row, "Skype");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String other = SessionData.getDataTbVal(dataTable, row, "Other");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            if (!supplier.isEmpty()) {
                anonymousRequestProductSteps.selectSupplier(supplier);
            }
            if (!revenue.isEmpty()) {
                anonymousRequestProductSteps.selectRevenue(revenue);
            }
            requestProductSteps.enterProductURL(links);
            anonymousRequestProductSteps.inputNote(note);
            anonymousRequestProductSteps.inputFacebook(facebook);
            anonymousRequestProductSteps.inputSkype(skype);
            anonymousRequestProductSteps.inputPhone(phone);
            anonymousRequestProductSteps.inputOther(other);
            requestProductSteps.confirmRequest();
            if (!msg.isEmpty()) {
                anonymousRequestProductSteps.verifyMsg(msg);
            }
        }
    }

}
