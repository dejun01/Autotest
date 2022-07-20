package opencommerce.apps.crosspanda.common;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestProductSteps;
import com.opencommerce.odoo.steps.OdooSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.Arrays;
import java.util.List;

public class SBCNOdooDef {
    @Steps
    private OdooSteps odooSteps;
    @Steps
    RequestProductSteps requestProductSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;

    private static String tableQuotationEmail = "quotation_email";
    private static String tableQuotationProduct = "quotation_product";

    @Given("^I has created quotation \"([^\"]*)\" from DSC$")
    public void iHasCreatedQuotationFromDSCWithEmail(String dataKey, List<List<String>> dataTable) {

    }

    @Then("^I should be able to see SO with name \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeSOWithName(String dataKey, List<List<String>> tableQuotationEmail) throws Throwable {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotationEmail, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(tableQuotationEmail, row, "KEY");
            String email = SessionData.getDataTbVal(tableQuotationEmail, row, "Email");
            String quotationId = SessionData.getDataTbVal(tableQuotationEmail, row, "Quotation");
            String productURLStr = SessionData.getDataTbVal(tableQuotationEmail, row, "ProductURL");
            String[] productURLs = productURLStr.split(",");
            if (!email.isEmpty()) {
                email = LoadObject.getProperty("xp.email");
            }
            for (int i = 1; i <= productURLs.length; i++) {
                if (quotationId.isEmpty()) {
                    quotationId = (String) SessionData.getDataByKey(key);
                }
                odooSteps.verifyHasSaleOrder(quotationId, email);
            }

        }
    }

    @And("^That sale order \"([^\"]*)\" contains product$")
    public void thatSaleOrderContainsProduct(String dataKey, List<List<String>> tableQuotationProduct) throws Throwable {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotationProduct, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(tableQuotationProduct, row, "KEY");
            String quotationId = SessionData.getDataTbVal(tableQuotationProduct, row, "Quotation");
            String email = SessionData.getDataTbVal(tableQuotationProduct, row, "Email");
            String productURLStr = SessionData.getDataTbVal(tableQuotationProduct, row, "ProductURL");
            List<String> productUrls = Arrays.asList(productURLStr.split(","));
            if (!email.isEmpty()) {
                email = LoadObject.getProperty("xp.email");
            }
            odooSteps.waitABit(6000);
            for (int i = 1; i <= productUrls.size(); i++) {
                if (quotationId.isEmpty()) {
                    quotationId = (String) SessionData.getDataByKey(key);
                }
                odooSteps.verifySOHasProduct(quotationId, productUrls, email);
            }
        }
    }

    @Given("^sync quotation from Odoo to crosspanda$")
    public void create_quotation_with_name() {
        String tokenApp = commonXPandaSteps.getAccessTokenXpanda();
        requestProductSteps.syncQuotation(tokenApp);
    }

    @And("^I should be able to see CrossPanda order with name \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeCrossPandaOrderWithName(String dataKey, List<List<String>> dataTable) throws Throwable {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String orderKey = SessionData.getDataTbVal(dataTable, row, "Order key");
            String orderNumber = SessionData.getDataTbVal(dataTable, row, "Order number");
            String email = LoadObject.getProperty("xp.email");
            if (orderNumber.isEmpty()) {
                orderNumber = (String) SessionData.getDataByKey(orderKey);
            }
            odooSteps.verifyHasSaleOrder(orderNumber, email);
        }
    }
}
