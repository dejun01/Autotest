package opencommerce.plus_base.catalog;

import com.opencommerce.shopbase.plusbase.steps.CatalogListStep;
import com.opencommerce.shopbase.plusbase.steps.PrivateRequestSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;


public class OrderCapDef {
    @Steps
    PrivateRequestSteps privateRequestSteps;
    @Steps
    CatalogListStep catalogListStep;

    @Then("^Verify info product out of stock in catalog list$")
    public void verifyInfoProductOutOfStock(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String productName = SessionData.getDataTbVal(data, row, "Product name");;
            String status = SessionData.getDataTbVal(data,row, "Status");
            privateRequestSteps.verifyProductInCatalogList(productName, status);
        }
    }

    @Then("CLick on product and verify information in catalog detail")
    public void clickOnProductAndVerifyInformationInCatalogDetail(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String productName = SessionData.getDataTbVal(data,row,"Product name");
            String status = SessionData.getDataTbVal(data,row,"BTImportToYourStore");
            catalogListStep.clickToProduct(productName);
            catalogListStep.verifyOutOfStockInCatalogDetail(productName, status);
        }
    }

    @Then("^verify product in private detail$")
    public void verifyProductInPrivateDetail(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String productName = SessionData.getDataTbVal(data, row, "Product name");
            String status = SessionData.getDataTbVal(data,row,"Status");
            String link = SessionData.getDataTbVal(data,row,"Link");
            String btImportToStore = SessionData.getDataTbVal(data,row, "BTImportToYourStore");
            privateRequestSteps.clickNameProduct();
            privateRequestSteps.verifyProductInPrivateRequestDetail(productName, status,link ,btImportToStore);
        }
    }

    @And("^Search product in private request list$")
    public void searchProductInPrivateRequestList(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String productName = SessionData.getDataTbVal(data, row, "Product name");
            privateRequestSteps.searchProductName(productName);
        }
    }
}