package opencommerce.plus_base.catalog;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.plusbase.steps.CatalogDetailSteps;
import com.opencommerce.shopbase.plusbase.steps.PrivateRequestSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateRequestDef {
    @Steps
    PrivateRequestSteps privateRequestSteps;
    @Steps
    CatalogDetailSteps catalogDetailSteps;

    String productName = "";
    Integer quantity = 0;
    Integer quantityAfterImport = 0;

    @And("Input link request product and verify")
    public void inputLinkRequestProductAndVerify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "link");
            String requestLink = SessionData.getDataTbVal(dataTable, row, "Request link");
            privateRequestSteps.inputLink(link);
            privateRequestSteps.clickBT("Add request");
            privateRequestSteps.verifyDisplayLinkRequestInRequestListPage(requestLink);
        }
        privateRequestSteps.clickBT("Send request");
    }

    @And("^Verify info product after request success by \"([^\"]*)\"$")
    public void verifyInfoProductAfterRequestSuccess(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            productName = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String link = SessionData.getDataTbVal(dataTable, row, "link");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            privateRequestSteps.verifyInfoProduct(productName, link, status);
        }
    }

    @And("^Search product in private product list by \"([^\"]*)\"$")
    public void searchProductInPrivateProductListBy(String key, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", key).keySet()) {
            String prodName = SessionData.getDataTbVal(dataTable, row, "product");
            privateRequestSteps.searchProducName(prodName);
        }
    }

    @And("CLick on product name and verify button Import to your store")
    public void clickOnProductNameAndVerifyButtonIs(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String btImportToStore = SessionData.getDataTbVal(dataTable, row, "BTImportToStore");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String processingTime = SessionData.getDataTbVal(dataTable, row, "Processing time");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            privateRequestSteps.clickNameProduct();
            privateRequestSteps.verifyBTImportToStore(productName, link, btImportToStore, status, variant, processingTime, productCost);
        }
    }

    @And("click {string} tab in private request list")
    public void clickTabInPrivateRequestList(String tab) {
        privateRequestSteps.clickOnTab(tab);
    }

    @And("Import to store")
    public void importToStore() {
        privateRequestSteps.clickButton("Import to your store");
        privateRequestSteps.selectproduct();
    }

    @Then("Verify product after in import success")
    public void verifyProductAfterInImportSuccess(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            privateRequestSteps.verifyProductImport(product);
        }
    }

    @Then("Search product and verify display product imported")
    public void searchProductAndVerifyDisplayProductImported() {
        privateRequestSteps.searchProduct(productName);
        quantityAfterImport = privateRequestSteps.getQuantityProduct(productName);
        assertThat(quantityAfterImport).isEqualTo(quantity + 1);
    }

    @And("Search product {string} and get quantity product")
    public void searchProductAndGetQuantityProduct(String productName) {
        privateRequestSteps.searchProduct(productName);
        quantity = privateRequestSteps.getQuantityProduct(productName);
    }

    @Then("user input request product link")
    public void user_input_request_product_link(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            privateRequestSteps.inputLink(link);
            privateRequestSteps.clickBT("Add request");
        }
        privateRequestSteps.clickBT("Send request");
    }

    @Then("wait to product is crawl and verify data")
    public void wait_to_product_is_crawl_and_verify_data(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            boolean isAli = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Ali product"));
            if (isAli) {
                privateRequestSteps.waitCrawlSuccess(productName, status);
            }
            privateRequestSteps.searchProduct(link);
            privateRequestSteps.verifyProductName(productName);
            privateRequestSteps.verifyStatusProduct(status);
        }
    }

    @Then("get quotation name on Private product")
    public void get_quotation_name_on_Private_product_with_link() {
        OrderVariable.quotationID = privateRequestSteps.getQuotationNameOnPrivateProduct();
    }

    @Given("go to product private detail {string}")
    public void go_to_product_private_detail(String productName) {
        privateRequestSteps.searchProducName(productName);
        privateRequestSteps.clickNameProduct();
    }

    @Given("verify product cost variant and shipping information")
    public void verify_product_cost_variant_and_shipping_information(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String shipTo = SessionData.getDataTbVal(dataTable, row, "Ship to");
            boolean isCountry = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is country"));
            String firstItem = SessionData.getDataTbVal(dataTable, row, "Fist item");
            String additional = SessionData.getDataTbVal(dataTable, row, "Additional item");
            String countCountry = SessionData.getDataTbVal(dataTable, row, "Count Country");
            if (!variant.isEmpty()) {
                catalogDetailSteps.selectVariant(variant);
                privateRequestSteps.verifyProductCostVariant(productCost);
            }
            if (!shipTo.isEmpty()) {
                privateRequestSteps.verifyCountrySupportProduct(shipTo, isCountry);
            }
            if(!countCountry.isEmpty()){
                int count = Integer.parseInt(countCountry);
                privateRequestSteps.verifyCountCountrySupport(count);
            }
            if (!firstItem.isEmpty()) {
                privateRequestSteps.verifyFirstItem(firstItem);
            }
            if (!additional.isEmpty()) {
                privateRequestSteps.verifyAdditional(additional);
            }
        }
    }

}
