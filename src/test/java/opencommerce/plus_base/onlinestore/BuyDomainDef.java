package opencommerce.plus_base.onlinestore;
import com.opencommerce.shopbase.plusbase.steps.BuyDomainSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class BuyDomainDef {
    @Steps
    BuyDomainSteps buyDomainSteps;
    String domain = "";
    String env = LoadObject.getProperty("env");

    @When("^connect domain not buy from ShopBase (domainsb)$")
    public void connectDomainNotBuyFromShopBase(String domainsb) {
        domain = LoadObject.getProperty(domainsb);
        buyDomainSteps.clickBtnConnectExistingDomain(domain);
        buyDomainSteps.verifyAlert(domain);
    }

    @When("^connect domain buy from PlusBase (domainplb)$")
    public void connectDomainBuyFromPlusBase(String domainplb) {
        domain = LoadObject.getProperty(domainplb);
        buyDomainSteps.clickBtnConnectExistingDomain(domain);
        buyDomainSteps.clickBtn("Verify connection");
    }

    @Then("verify domain connect success with")
    public void verifyDomainConnectSuccessWith(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String titlePrimary = SessionData.getDataTbVal(dataTable, row, "Title Primary");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String message = SessionData.getDataTbVal(dataTable, row, "Message").replace("@domain", domain);
            String titleDomain = SessionData.getDataTbVal(dataTable, row, "Title Domain");
            String env = LoadObject.getProperty("env");
            if ("prod".equals(env)) {
                buyDomainSteps.verifyInfodomainWithTitle("DOMAIN NAME", domain, titlePrimary);
            }
            buyDomainSteps.verifyInfodomainWithTitle("STATUS", status, titlePrimary);
            buyDomainSteps.verifyInfodomainWithTitle("DOMAIN NAME", domain, titleDomain);
            buyDomainSteps.verifyInfodomainWithTitle("STATUS",status, titleDomain);
            buyDomainSteps.verifyMessage(message);
        }
    }

    @And("remove domain just connected")
    public void removeDomainJustConnected(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String titleDomain = SessionData.getDataTbVal(dataTable, row, "Title Domain");
            String btnName = SessionData.getDataTbVal(dataTable, row, "Btn Name");
            buyDomainSteps.removeDomain(titleDomain, domain, btnName);
            buyDomainSteps.clickBtn(btnName);
        }
    }
}
