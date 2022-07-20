package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import opencommerce.sizechart.SizechartDef;
import java.util.List;
import static opencommerce.products.dashboard.ProductDetailDef.nameProductSbase;

public class ProductListDef {
    @Steps
    ProductListSteps productListSteps;

    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CampaignSteps campaignSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;

    boolean isPass = true;
    String accToken = "";


    String pass = LoadObject.getProperty("user.pwd");
    private int MAX_RETRY_TIME = 15;

    String templateName = "";

    @Then("^Click to button Add product$")
    public void clickToBtnAddProduct() {
        productListSteps.clickAddProduct();
    }

    @Then("^Search product \"([^\"]*)\" on All product screen$")
    public void searchProductOnAllProductScreen(String productName) {
        if (productName.matches("@(.*)@")) {
            switch (productName) {
                case "@first@":
                    productListSteps.searchProduct(ProductDetailDef.productTitle1);
                    break;
                case "@second@":
                    productListSteps.searchProduct(ProductDetailDef.productTitle2);
                    break;
                case "@Copy of NameProduct@":
                    productListSteps.searchProduct("Copy of " + ProductDetailDef.nameProductSbase);
                    break;
                default:
                    productListSteps.searchProduct(ProductDetailDef.nameProductSbase);
            }
        } else {
            productListSteps.searchProduct(productName);
            ProductDetailDef.nameProductSbase = productName;
        }
    }

    @And("^Open detail product of product \"([^\"]*)\"$")
    public void chooseProductOnDashboard(String productTitle) {
        if (productTitle.matches("@(.*)@")) {
            switch (productTitle) {
                case "@first@":
                    productListSteps.chooseProduct(ProductDetailDef.productTitle1);
                    break;
                case "@second@":
                    productListSteps.chooseProduct(ProductDetailDef.productTitle2);
                    break;
                case "@Copy of NameProduct@":
                    productListSteps.chooseProduct("Copy of " + ProductDetailDef.nameProductSbase);
                    break;
                default:
                    productListSteps.chooseProduct(ProductDetailDef.nameProductSbase);
            }
        } else {
            productListSteps.chooseProduct(productTitle);
        }
    }

    @Given("^Search product on All \"([^\"]*)\" screen and Delete \"([^\"]*)\" with title$")
    public void deleteProductWithTitle(List<List<String>> dataTable, String typeProduct) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Product title");
            productListSteps.searchProduct(title);
            if (!productListSteps.isNoDataFound(title)) {
                productListSteps.selectAllProductCrossPage();
                productListSteps.clickAction();
                productListSteps.chooseActionDeleteSelectedProduct(typeProduct);
                productListSteps.clickBtnDelete();
                productListSteps.verifyNoDataFound();
            }
        }
    }

    @Given("^Delete all (products|campaigns)$")
    public void deleteAllProduct(String typeProduct) {
        if (productListSteps.hasProduct()) {
            productListSteps.selectAllProductCrossPage();
            productListSteps.clickAction();
            productListSteps.chooseActionDeleteSelectedProduct(typeProduct);
            productListSteps.confirmPassDelete(pass);
            productListSteps.clickBtnDelete();
        }
        productListSteps.verifyNoProductFound();
    }

    @And("^Verify has product with \"([^\"]*)\" title$")
    public void verifyHasProductWithTitle(String title) {
        productListSteps.hasProduct();
    }

    @Given("^verify content of mail import product$")
    public void verify_content_of_mail_import_product(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String error = SessionData.getDataTbVal(dataTable, row, "Error");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            if (!error.isEmpty()) {
                productListSteps.verifyContentOfMailImportProductError(error);
            }
            if (!content.isEmpty()) {
                productListSteps.verifyContentOfMailImportProduct(content);
            }
        }
    }

    @Given("^(.*) verify content of mail import product$")
    public void verify_mail_import_product(String actor, List<List<String>> dataTable) {
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
                productListSteps.verifyContentOfMailImportProduct(content);
            }
        }
    }

    @Then("^Verify data of product on product list screen$")
    public void verifyDataOfProductOnProductListScreen(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String vendor = SessionData.getDataTbVal(dataTable, row, "Vendor");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String exist = SessionData.getDataTbVal(dataTable, row, "Is exist");
            boolean isExist = true;
            if (!exist.isEmpty()) {
                isExist = Boolean.parseBoolean(exist);
            }
            if (title.matches("@(.*)@")) {
                title = nameProductSbase;
            }
            productListSteps.searchProduct(title);
            productListSteps.verifyProductExist(title, isExist);
            if (isExist) {
                productListSteps.verifyTitleProductList(title);
                productListSteps.verifyTypeProductList(title, type);
                productListSteps.verifyVendorProductList(title, vendor);
                productListSteps.verifyStatus(title, status);
            }
            productListSteps.cleanFilter();
        }
    }

    private void uploadFileImport(String fileName) {
        productListSteps.clickBtnImport();
        productListSteps.chooseFileCSV(fileName);
        productListSteps.chooseOverwrite();
        productListSteps.clickUploadFile();
    }

    @Then("^import product with csv file and verify error massage$")
    public void importProductWithCsvFileAndVerifyMassage(List<List<String>> dataTable) {
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

    @Then("^Import product by csv with file name$")
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

    @Given("^select all products in the first (\\d+) pages$")
    public void select_products(int page) {
        for (int i = 1; i <= page; i++) {
            productListSteps.selectAllProduct();
            if (i > 1) {
                productListSteps.nextPage();
                productListSteps.selectAllProductNextPage();
            }
        }
    }

    @Then("^(.*) export product with condition$")
    public void exportProductWithCondition(String actor, List<List<String>> dataTable) {
        String staffEmail = LoadObject.getProperty(actor + ".name");
        String msg = "";

        productListSteps.clickExportProduct();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String export = SessionData.getDataTbVal(dataTable, row, "Export");
            String exportAs = SessionData.getDataTbVal(dataTable, row, "Export as");
            msg = SessionData.getDataTbVal(dataTable, row, "Message");
            msg = msg.replace("@email@", staffEmail);

            productListSteps.selectExport(export);
            productListSteps.selectExportAs(exportAs);
            productListSteps.clickExport();
            if (!msg.isEmpty()) {
                productListSteps.verifyMessageExportSuccess(msg);
            }
        }
    }

    @And("^verify content of mail export product$")
    public void verifyContentOfMailExportProduct() {
        productListSteps.verifyContentOfMailExportProduct();
    }

    @And("^Select all product and delete$")
    public void selectAllProductAndDelete(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String namePro = SessionData.getDataTbVal(dataTable, row, "Product name");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            productListSteps.searchProduct(namePro);
            productListSteps.selectAllProductCrossPage();

            productListSteps.clickAction();
            productListSteps.chooseAction(action);
            productListSteps.clickBtnDelete();
            productListSteps.verifyMessageDeleteProduct(message);
        }
    }

    @And("^verify mail import product with content \"([^\"]*)\"$")
    public void verifyContentOfMailImportProduct(String content) {
        productListSteps.verifyContentOfMail(content);
    }


    @Then("verify filter product by tab")
    public void verifyFilterProductByTab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String listProduct = SessionData.getDataTbVal(dataTable, row, "List product");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total product");
            String unselectProduct = SessionData.getDataTbVal(dataTable, row, "Unselect product");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");

            productListSteps.selectAllProductCrossPage();
            if (!action.isEmpty()) {
                productListSteps.clickAction();
                productListSteps.chooseAction(action);
            }
            if (!tags.isEmpty()) {
                productListSteps.inputTags(tags);
            }
            if (!collection.isEmpty()) {
                productListSteps.addCollection(collection);
                productListSteps.selectCollection(collection);
            }
            if (!action.isEmpty() && !action.equals("Edit products")) {
                productListSteps.confirmAction(action, pass);
            }

            productListSteps.selectTab(tab);
            if (!listProduct.isEmpty()) {
                productListSteps.verifyListProduct(listProduct);
            }
            if (!totalProduct.isEmpty()) {
                productListSteps.selectAllProductCrossPage();
                productListSteps.verifyTotalProduct(totalProduct);
            }
            if (!status.isEmpty()) {
                String[] products = listProduct.split(",");
                for (String product : products) {
                    productListSteps.verifyStatus(product, status);
                }
            }
        }
    }

    @Then("verify list product by created time \"([^\"]*)\"$")
    public void verifyListProductByCreatedTime(String listProduct) {
        productListSteps.verifyListProduct(listProduct);
    }

    @And("Wait list product imported with total \"([^\"]*)\"")
    public void verifyListProductImportedWithTotal(String totalExpect) {
        int retryTimes = 0;
        boolean check = productListSteps.importEnoughProduct(totalExpect);
        while (!check && retryTimes <= MAX_RETRY_TIME) {
            productListSteps.waitABit(5000);
            productDetailSteps.refreshPage();
            retryTimes++;
            check = productListSteps.importEnoughProduct(totalExpect);
        }
    }

    @Then("select all product with action")
    public void selectMultipleProductWithAction(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String unselectProduct = SessionData.getDataTbVal(dataTable, row, "Unselect product");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");

            productListSteps.selectAllProductCrossPage();
            if (!unselectProduct.isEmpty()) {
                productListSteps.unSelectProduct(Integer.parseInt(unselectProduct));
            }
            if (!action.isEmpty()) {
                productListSteps.clickAction();
                productListSteps.chooseAction(action);
            }
            if (!tags.isEmpty()) {
                productListSteps.inputTags(tags);
            }
            if (!collection.isEmpty()) {
                productListSteps.addCollection(collection);
                productListSteps.selectCollection(collection);
            }
            if (!action.isEmpty() && !action.equals("Edit products")) {
                productListSteps.confirmAction(action, pass);
            }
        }
    }

    @Then("add or remove tags to product \"([^\"]*)\" with action \"([^\"]*)\"")
    public void addTagsToProduct(String product, String action) {
        productListSteps.selectProduct(product);
        productListSteps.clickAction();
        productListSteps.chooseAction(action);
        productListSteps.inputTags(SizechartDef.tagSizeChart);
        productListSteps.applyChanges();
    }

    @Then("add action to products")
    public void addActionToProducts(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String listProduct = SessionData.getDataTbVal(dataTable, row, "List product");

            String[] products = listProduct.split(",");
            for (String product : products) {
                productListSteps.selectProduct(product);
            }
            if (!action.isEmpty()) {
                productListSteps.actionWithMultipleProduct(action);
            }
        }
    }

    @And("verify total (.*) by filter condition")
    public void verifyProductByFilterCondition(String pro, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String totalProduct = SessionData.getDataTbVal(dataTable, row, "Total product");
            String filter = SessionData.getDataTbVal(dataTable, row, "Filter by");
            String conditionFilter = SessionData.getDataTbVal(dataTable, row, "Condition filter");

            productListSteps.clickMoreFilter();
            if (conditionFilter.isEmpty()) {
                productListSteps.filterByValue(filter, value);
            } else if (value.isEmpty()) {
                productListSteps.filterByCondition(filter, conditionFilter);
            } else {
                productListSteps.filterByConditionValue(filter, conditionFilter, value);
            }
            productListSteps.clickDone();
            productListSteps.selectAllProductCrossPage();
            productListSteps.verifyTotalProduct(totalProduct);
            productListSteps.cleanFilter();
        }
    }


    @And("verify status import product")
    public void verifyStatusImportProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameFile = SessionData.getDataTbVal(dataTable, row, "File name");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String information = SessionData.getDataTbVal(dataTable, row, "Information import");

            productListSteps.clickBtnStatusImport();
            productListSteps.verifyStatusImport(nameFile, status);
            if (!information.isEmpty()) {
                productListSteps.verifyImport(nameFile, information);
            }
            campaignSteps.refreshPage();
        }
    }

    @Given("Delete all products by API")
    public void deleteAllProductsByAPI() {
        if (isPass) {
            if (accToken.isEmpty())
                accToken = myCampaignSteps.getAccessTokenShopBase();
            JsonPath listProduct = productListSteps.getProduct(accToken);
            Object result = listProduct.get("products");
            if (result != null) {
                List<Long> products = listProduct.get("products.id");
                for (Long id : products)
                    productListSteps.deleteProductsByAPI(id, accToken);
            }
        }
    }

    //Clone Product by Duc Dao
    @And("^select \"([^\"]*)\" products$")
    public void selectProducts(String numberOfProduct) {
        productListSteps.selectProducts(numberOfProduct);
    }

    @And("^Import (.*) to another shop$")
    public void importProdToAnotherShop(String typeShop, List<List<String>> dataTable) {
        String desStore = LoadObject.getProperty("secondShop");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String totalProducts = SessionData.getDataTbVal(dataTable, row, "Total products");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String keepProdouctId = SessionData.getDataTbVal(dataTable, row, "Keep prodouct ID");

            productListSteps.importProdToAnotherShop(desStore, typeShop);
            if (!message.isEmpty()) {
                productListSteps.verifyMessageCloneProduct(message);
            }
            if (!totalProducts.isEmpty()) {
                productListSteps.verifyTotalProductClone(totalProducts);
            }
            if (!action.isEmpty()) {
                productListSteps.selectActionClone(action);
            }

            if (!keepProdouctId.isEmpty()) {
                productListSteps.selectKeepID(keepProdouctId);
            }
            productListSteps.clickBtnImportPB();
        }
    }

    @And("^Select \"([^\"]*)\" shop on select shop dashboard$")
    public void selectShop(String DashboardShop) {
        productListSteps.selectShop(DashboardShop);
    }

    @Then("^verify import products status is \"([^\"]*)\" with \"([^\"]*)\" products")
    public void verifyImportProd(String status, String actualQuantity) {
        productListSteps.verifyImportProduct(status,actualQuantity);
    }

    @And("^verify import process")
    public void verifyImportProcess() {
        productListSteps.verifyImportProcess();
    }

    @When("Acc to tab template filter")
    public void accToTabTemplateFilter(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            productListSteps.clickOnTab(tab);
        }
    }

    @Then("Verify button {string} is disable")
    public void verifyButtonIsDisable(String buttonName) {
        productListSteps.verifyNotDisplayBT(buttonName);
    }

    @When("Filter product by condition")
    public void filterProductByCondition(List<List<String>> dataTable) {
        productListSteps.clickBTMoreFilter();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String subCondition = SessionData.getDataTbVal(dataTable, row, "SubCondition");
            productListSteps.filterCondition(condition, subCondition);
        }
        productListSteps.clickBTDone();
    }

    @And("{string} for new template with name {string}")
    public void forNewTemplateWithName(String buttonName, String nameTemplateFilter) {
        templateName = nameTemplateFilter;
        productListSteps.clickBTCurrentFilter(buttonName);
        productListSteps.enterNameTemplateFilter(nameTemplateFilter);
    }

    @Then("Verify display tab new template")
    public void verifyDisplayTabNewTemplate() {
        productListSteps.verifyDisplayTemplateNew(templateName);
    }

    @And("Verify display product by condition filter")
    public void verifyDisplayProductByConditionFilter(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "ProductName");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            productListSteps.verifyDateFilter(productName, status);
        }
    }

    @And("Click on status {string}")
    public void clickOnStatus(String action) {
        productListSteps.clickLink(action);
    }

    @Then("Verify link redirect product mapping page")
    public void verifyLinkRedirectProductMappingPage() {
        productListSteps.verifyLinkRedirectProductMappingPage();
    }

    @And("{string} name template filter")
    public void nameTemplateFilter(String action) {
        productListSteps.clickActionTemplate(action);
    }

    @Then("Verify not display tab new template")
    public void verifyNotDisplayTabNewTemplate() {
        productListSteps.verifyNotDisplayTemplateFilter(templateName);
    }

    @Then("import product url from another platform")
    public void importProductUrlFromAnotherPlatform(List<List<String>> dataTable) {
        productListSteps.importUrlProduct();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String platform = SessionData.getDataTbVal(dataTable, row, "Platform");
            String inputType = SessionData.getDataTbVal(dataTable, row, "Input type");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");

            productListSteps.selectPlatformAndType(platform);
            productListSteps.selectPlatformAndType(inputType);
            productListSteps.inputUrl(product);
            productListSteps.importProduct();
        }
    }

    @Then("Verify list product imported")
    public void verifyListProductImported(List<List<String>> dataTable) {
        productListSteps.clickImportList();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String note = SessionData.getDataTbVal(dataTable, row, "Note");

            productListSteps.verifyStatusImportApp(product, status);
            if (!note.isEmpty()) {
                productListSteps.verifyNoteImportApp(product, note);
            }
        }
    }

    @And("delele all product in dashboard")
    public void deleleAllProductInDashboard() {
        int count = productListSteps.getNumberProductOfList();
        if (count > 0) {
            productListSteps.selectAllProduct();
            productListSteps.clickAction();
            productListSteps.chooseAction("Delete selected products");
            productListSteps.clickBtnDelete();
            String pw = LoadObject.getProperty("user.pwd");
            productListSteps.confirmPassDelete(pw);
        }
    }

    @And("delete product{string} by API")
    public void deleteProductByAPI(String productName) {
        String accessToken = myCampaignSteps.getAccessTokenShopBase();
        Long id = productDetailSteps.getProductIDByName(productName, accessToken);
        productListSteps.deleteProductsByAPI(id, accessToken);
    }

    @And("verify product not exist in dashboard")
    public void verifyProductNotExistInDashboard() {
        productListSteps.verifyNoProductFound();
    }

    @Given("Delete multi products by API")
    public void deleteMultiProductsByAPI() {
        if (isPass) {
            if (accToken.isEmpty())
                accToken = myCampaignSteps.getAccessTokenShopBase();
            productListSteps.deleteAllProductsByAPI(accToken);
        }
    }

    @Then("verify show Payment status as {string}")
    public void verifyShowPaymentStatusAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String showStatus = SessionData.getDataTbVal(dataTable, row, "Show");
            boolean isShow = true;
            if (showStatus.isEmpty()) {
                isShow = Boolean.parseBoolean(showStatus);
            }

        }

    }

    @And("verify list Payment status as {string}")
    public void verifyListPaymentStatusAs(String dataKey, List<List<String>> dataTable) {
        productListSteps.clickPaymentStatusInHive();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            if (status.isEmpty()){
                productListSteps.verifyListPaymentStatus(status);
            }

        }
    }

    @And("choose platform type of shop as {string}")
    public void choosePlatformTypeOfShopAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String platform= SessionData.getDataTbVal(dataTable, row, "Platform type");
            if (!platform.isEmpty()){
                productListSteps.selectPlatformTypeInHive(platform);

            }

        }
    }

    @And("verify {string} products in Search products")
    public void verifyProductsInSearchProducts(String sNumber) {
        productListSteps.verifyProductsInSearchProducts(sNumber);
    }

    @And("open page deactive shopbase payments")
    public void openPageDeactiveShopbasePayments() {
        productListSteps.openPageDeactiveShopbasePayments();
    }

    @And("^verify detail message in import process")
    public void verifyDetailMessageProcess(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sMsg = SessionData.getDataTbVal(dataTable, row, "Message");
            if (!_sMsg.isEmpty()) {
                productListSteps.verifyMessageDetail(_sMsg);
            }
        }
    }

}


