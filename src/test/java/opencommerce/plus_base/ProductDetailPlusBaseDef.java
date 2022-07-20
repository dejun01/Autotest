package opencommerce.plus_base;
import com.opencommerce.shopbase.plusbase.steps.ProductDetailPlusBaseSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import java.util.List;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;

public class ProductDetailPlusBaseDef {
    @Steps
    ProductDetailPlusBaseSteps ProductDetailSteps;

    @Steps
    ProductListSteps productListSteps;
    @When("user navigates to {string} screen")
    public void userNavigatesToScreen(String tabName) {
        ProductDetailSteps.userNavigatesToScreen(tabName);
    }


    @Then("search product PlBase {string} then select product")
    public void searchProductPlBaseThenSelectOrder(String product) {
        ProductDetailSteps.searchProductPlBase(product);
        ProductDetailSteps.clickProductPlBase(product);
    }

    @Then("Verify data of product on catalog detail screen")
    public void verifyDataOfProductOnCatalogDetailScreen(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String variants = SessionData.getDataTbVal(dataTable, row, "Variants");
            String processingTime = SessionData.getDataTbVal(dataTable, row, "Processing time");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String sellingPrice = SessionData.getDataTbVal(dataTable, row, "Selling price");
            Float profitMargin = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Profit margin"));
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String aboutThisProduct = SessionData.getDataTbVal(dataTable, row, "About this product");
            String shipTo = SessionData.getDataTbVal(dataTable, row, "Ship to");
            ProductDetailSteps.verifyProductName(productName);
            ProductDetailSteps.verifyTags(tags);
            ProductDetailSteps.verifyProcessingTime(processingTime);
            ProductDetailSteps.verifyProductCost(variants, productCost);
            ProductDetailSteps.verifySellingPrice(variants, sellingPrice);
//            ProductDetailSteps.verifyProfitMargin(variants, profitMargin);
            ProductDetailSteps.verifyDescription(description);
            ProductDetailSteps.verifyAboutThisProduct(aboutThisProduct);
//            ProductDetailSteps.verifyShipTo(shipTo);
        }
    }

    @And("import to import list page")
    public void importToImportListPage() {
        ProductDetailSteps.clickBTImportToYourStore();
        ProductDetailSteps.directToImportListPage();
    }

//    @And("Select ship to {string}")
//    public void selectShipTo(String Country) {
//        ProductDetailSteps.selectShipTo(Country);
//    }
//
//    @And("Verify Shipping method")
//    public void verifyShippingMethod(List<List<String>> dataTable) {
//        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
//            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
//            String shippingFee = SessionData.getDataTbVal(dataTable, row, "Shipping fee");
//            String EstimatedDeliveryTime = SessionData.getDataTbVal(dataTable, row, "Estimated delivery time");
//            ProductDetailSteps.verifyShippingMethod(shippingMethod);
//            ProductDetailSteps.verifyShippingFee(shippingFee);
//            ProductDetailSteps.verifyEstimatedDeliveryTime(EstimatedDeliveryTime);
//        }
//    }

    @Then("search product PlBase {string}")
    public void searchProductPlBase(String product) {
        ProductDetailSteps.searchProductPlBase(product);
    }

    @And("select checkbox all product inlist")
    public void selectCheckboxAllProductInlist() {
        ProductDetailSteps.selectCheckboxAllProduct();
    }

    @Then("^Import plusbase product by csv with file name$")
    public void importProductWithCsvFileWithFileName(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String fileName = SessionData.getDataTbVal(dataTable, row, "File name");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            uploadFileImport(fileName);
            productListSteps.clickUploadFile();
            productListSteps.waitABit(2000);
            productListSteps.verifyMessage(message);
            productListSteps.clickOkImport();
            productListSteps.waitABit(5000);
        }
    }

    private void uploadFileImport(String fileName) {
        productListSteps.clickBtnImport();
        productListSteps.chooseFileCSV(fileName);
        productListSteps.clickUploadFile();
    }

    @Given("^(.*) verify content of mail plbase import product$")
    public void verify_mail_plbase_import_product(String actor, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            String error = SessionData.getDataTbVal(dataTable, row, "Error");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");

            String emailStaff = LoadObject.getProperty(actor + ".name");
            productListSteps.loginMailBox(emailStaff);

            if (!subject.isEmpty()) {
                productListSteps.openSubjectMail(subject);
            }
            if (!error.isEmpty()) {
                productListSteps.verifyContentOfMailImportProductError(error);
            }
            if (!content.isEmpty()) {
                ProductDetailSteps.verifyContentOfMailPlBaseImportProduct(content);
            }
        }
    }

    @Then("^import plbase product with csv file and verify error massage$")
    public void importPlBaseProductWithCsvFileAndVerifyMassage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String fileName = SessionData.getDataTbVal(dataTable, row, "File name");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            uploadFileImport(fileName);
            productListSteps.verifyMessage(errorMessage);
            productListSteps.clickBtnCancel();
            productListSteps.clickBtnClose();
            productListSteps.waitABit(3000);
        }
    }

    @Then("^import sample csv template$")
    public void importSampleCSVTemplate() {
        productListSteps.clickBtnImport();
        ProductDetailSteps.clickDownLoadCSVTemplate();
    }


    @And("verify product information in product detail of PlusBase as {string}")
    public void verifyProductInformationInProductDetailOfPlusBaseAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sColor = SessionData.getDataTbVal(dataTable, row, "Color");
            if(!_sColor.isEmpty()){
                productListSteps.verifyVariantColor(_sColor);
            }

        }
    }
}
