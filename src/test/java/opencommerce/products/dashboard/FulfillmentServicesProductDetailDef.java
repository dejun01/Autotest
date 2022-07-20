package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.FulfillmentServicesProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FulfillmentServicesProductDetailDef {
    @Steps
    FulfillmentServicesProductDetailSteps fulfillmentServicesProductDetailSteps;

    @Then("verify display block {string} in product detail")
    public void verifyDisplayBlockInProductDetail(String text) {
        fulfillmentServicesProductDetailSteps.clickBtnMoreOptions();
    }

    @And("Mapping product in product detail")
    public void mappingProductInProductDetail() {
        fulfillmentServicesProductDetailSteps.clickBTMapping();
    }

    @Then("verify display status {string} in product detail")
    public void verifyDisplayStatusInProductDetail(String text) {
        fulfillmentServicesProductDetailSteps.clickBack();
        assertThat(fulfillmentServicesProductDetailSteps.getButton(text)).isEqualTo(text);
    }

    @And("user get availablestock in warehouse of {string}")
    public void userGetAvailablestockInWarehouseOf(String product) {
        fulfillmentServicesProductDetailSteps.searchProduct(product);
        availableStock = fulfillmentServicesProductDetailSteps.getValueAvailableStock("AVAILABLE");
    }

    String availableStock = "";
    @Then("Verify display info product warehouse in product detail")
    public void verifyDisplayInfoProductWarehouseInProductDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product warehouse");
            String stock = SessionData.getDataTbVal(dataTable, row, "Stock");
            fulfillmentServicesProductDetailSteps.verifyProductWarehouse(product);
            fulfillmentServicesProductDetailSteps.verifyStock(stock);
        }
    }

    @And("click to {string} from Actions")
    public void clickToFromActions(String page) {
        fulfillmentServicesProductDetailSteps.clickOnActionBtn();
        fulfillmentServicesProductDetailSteps.clickOnBtnInMoreAction(page);
    }

    @Then("Verify redirect page {string} and display button {string} and button {string}")
    public void verifyRedirectPageAndDisplayButtonAndButton(String page) {
        fulfillmentServicesProductDetailSteps.verifyRedirectMapPage(page);
    }

    @Then("Verify redirect to {string} page")
    public void verifyRedirectToPage(String text) {
        assertThat(fulfillmentServicesProductDetailSteps.getRequest()).isEqualTo(text);
    }

    @Then("Verify redirect to {string} detail")
    public void verifyRedirectToDetail(String text) {
        assertThat(fulfillmentServicesProductDetailSteps.getText()).isEqualTo(text);
    }

    @Then("user redirect to {string} page")
    public void userRedirectToPage(String page) {
        fulfillmentServicesProductDetailSteps.userRedirectToPage(page);
    }

    @Then("Verify display button {string} and {string}")
    public void verifyDisplayButtonAnd(String btnremove, String btnedit) {
        fulfillmentServicesProductDetailSteps.verifyDisplayButtonAnd(btnremove, btnedit);
    }
}

