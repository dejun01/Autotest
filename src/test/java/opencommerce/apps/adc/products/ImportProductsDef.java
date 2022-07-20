package opencommerce.apps.adc.products;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.adc.manageorder.steps.MappingProductsSteps;
import com.opencommerce.shopbase.dashboard.apps.adc.products.steps.ImportProductsSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.*;
import static com.opencommerce.shopbase.dashboard.apps.adc.ADC.setImportedProdListToAdc;
import static org.assertj.core.api.Assertions.assertThat;

public class ImportProductsDef {
    @Steps
    ImportProductsSteps importProductsSteps;

    @Steps
    MappingProductsSteps mappingProductsSteps;

    @Steps
    CommonSteps commonSteps;

    @Steps
    ProductDetailSteps productDetailSteps;

    HashMap<String, String> productAfterEditing = new HashMap<>();
    public String newProductTitle = "", newProductDes = "", newProductVar, newPrice, newComparePrice;
    public int importedProductNum = 0;

    @Given("^delete all products from import list of ADC$")
    public void delete_all_products_from_import_list_of_ADC() {
        int row = importProductsSteps.getQuantityProductsInList();
        if (row > 0) {
            importProductsSteps.selectAllProduct();
            importProductsSteps.removeProductSelected();
        }
    }

    @Then("^verify import list of ADC is empty$")
    public void verify_import_list_of_ADC_is_empty() {
        int row = importProductsSteps.getQuantityProductsInList();
        assertThat(row).isEqualTo(0);
        importProductsSteps.verifyMsgDisplayed("Your import list is empty");
        importProductsSteps.verifyMsgToGoAliExpress("Go to AliExpress to find winning products");
        importProductsSteps.verifyLinkTextAliExpress();
    }

    @And("^import product from (AliExpress|ADC app) to (ADC app|Store)$")
    public void importProductFromAliExpressToStoreWithADCAs(String from, String to, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tsc = SessionData.getDataTbVal(dataTable, row, "Testcase");
            String productKey = SessionData.getDataTbVal(dataTable, row, "Product KEY");
            String urlAli = SessionData.getDataTbVal(dataTable, row, "AliExpress Product");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            if (from.equals("AliExpress") && to.equals("ADC app")) {
                importProductsSteps.logInfor("Testcase name: " + tsc);
                importProductsSteps.clickBtnImport();
                importProductsSteps.enterURLProduct(urlAli);
                importProductsSteps.clickBtnImportOnPopUp();
                importProductsSteps.verifyMsgDisplayed(msg);
                if (msg.isEmpty()) {  // import successfully
                    setImportedProdListToAdc(productKey, getProductName());
                    originalTitle = getImportedProdListToAdc(productKey);
                }
            } else if (from.equals("ADC app") && to.equals("Store")) {
                nameProductImportToStore = getImportedProdListToAdc(productKey);
                importProductsSteps.selectTabInImportList("Product");
                importProductsSteps.clickBtnImportToStore(originalTitle);
                importProductsSteps.verifyStatusImportToStore(nameProductImportToStore);
                importProductsSteps.clickBtnEditProductOnShopbase(nameProductImportToStore);
                importProductsSteps.verifyProductExistInImportList(nameProductImportToStore, false);
            }
        }
//        importedProdListToStore = importedProdListToAdc;
        System.out.println("setImportedProdListToAdc = " + importedProdListToAdc);
    }

    private String getProductName() {
        return importProductsSteps.getProductName(1);
    }

    @And("^verify products exist on ShopBase$")
    public void verifyProductsExistOnShopBaseAs(String key) {
        String nameProductImportToStore = importedProdListToAdc.get(key);
        importProductsSteps.verifyProductByTitle(nameProductImportToStore);
    }

    @And("^import product from AliExpress to ShopBase by ADC$")
    public void importProductFromAliExpressToShopBaseByADC(List<List<String>> dataTable) {

    }

    @Then("^select tab \"([^\"]*)\" then edit product information in import list$")
    public void selectTabThenEditProductInformationInImportList(String tabName, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            newProductTitle = SessionData.getDataTbVal(dataTable, row, "New title");
            newProductDes = SessionData.getDataTbVal(dataTable, row, "New description");
            newProductVar = SessionData.getDataTbVal(dataTable, row, "New value");
            String productKey = SessionData.getDataTbVal(dataTable, row, "Product KEY");
            String editedProd = getImportedProdListToAdc(productKey);

            importProductsSteps.selectTabInImportList(tabName);
            switch (tabName) {
                case "Product":
                    importProductsSteps.enterNewTitle(editedProd, newProductTitle);
                    importProductsSteps.verifyChangesTitleSuccessfully(productKey, newProductTitle);
                    importProductsSteps.updateProdVarAliInfo(editedProd, newProductTitle);
                    break;
                case "Description":
                    importProductsSteps.enterNewDescription(newProductDes);
                    importProductsSteps.verifyChangesDescriptionSuccessfully(newProductDes);
                    break;
                case "Variants":
//                    importProductsSteps.enterNewVariantName(originalVariant, newProductVar);
                    actualVarInfoBeforeEditing = importProductsSteps.getActualVariantInfoDisplayed(editedProd);
                    importProductsSteps.bulkUpdatePrice(newProductVar);
                    importProductsSteps.verifyVariantInfoAfterEditing(actualVarInfoBeforeEditing, editedProd, newProductVar);
                    break;
            }
        }
    }

    @When("^click on checkbox of product with KEY then (Import to Store|Remove from list)$")
    public void clickOnCheckbox(String action, List<List<String>> dataTable) {
        int num = 1;
        String prodTitle = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "Product KEY");

            // get product name matching by key
//            prodTitle = importedProdListToAdc.get(key);
            prodTitle = getImportedProdListToAdc(key);
            importProductsSteps.clickOnCheckboxByProductTitle(prodTitle);
            importProductsSteps.verifyNumberOfProductsSelected(num);
            prodList.add(prodTitle);
            num++;
        }
        importProductsSteps.clickOnBtnInActionsBar(action);
        if (action.equals("Import to Store")) {
            importProductsSteps.verifyStatusImportToStore(prodList);
        } else if (action.equals("Remove from list")) {
            importProductsSteps.verifyMsgDisplayedWhenDeletingProd(num - 1);
            importProductsSteps.verifyRemovedProductIsNotDisplayed(prodList);
        }
    }

    @And("^verify \"([^\"]*)\" button is displayed and working properly$")
    public void verifyButtonIsDisplayedAndWorkingProperly(String val) {
        importProductsSteps.verifyBtnExtension(val);
    }

    @And("^config runtime env$")
    public void configRuntimeEnv() {
        importProductsSteps.configRuntime();
    }

    @And("^verify that product information is displayed correctly on the (product|campaign) detail page$")
    public void verifyThatProductInformationIsDisplayedCorrectlyOnTheProductDetailPage(String typeShop) {
        //go to ShopBase dashboard
        commonSteps.switchToTheLatestWindowTab();

        String aliProdVarName = "";
        String aliVarName = "";
        String aliVarPrice = "";

        productDetailSteps.verifyTitle(nameProductImportToStore);
        productDetailSteps.verifyDescription(description);

        Set<Map.Entry<String, List<Float>>> setHashMap = prodVarAliInfo.entrySet();
        for (Map.Entry<String, List<Float>> variantsInfo : setHashMap) {
            aliProdVarName = variantsInfo.getKey();
            aliVarName = aliProdVarName.split(":")[1].trim();
            aliVarPrice = String.valueOf(prodVarAliInfo.get(aliProdVarName).get(1));
            productDetailSteps.verifyVariantPrice(aliVarName, aliVarPrice, typeShop);
        }
    }

    @And("import product from AliExpress to ADC app by API")
    public void importProductFromAliExpressToADCAppByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productKey = SessionData.getDataTbVal(dataTable, row, "Product KEY");
            String urlAli = SessionData.getDataTbVal(dataTable, row, "AliExpress Product");

            mappingProductsSteps.getProductCostByVariantAdded(urlAli);
        }
    }
}
