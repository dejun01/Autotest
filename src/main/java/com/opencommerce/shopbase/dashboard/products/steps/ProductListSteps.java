package com.opencommerce.shopbase.dashboard.products.steps;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.products.pages.ProductListPages;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;
import static junit.framework.TestCase.fail;
import static net.serenitybdd.rest.SerenityRest.given;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductListSteps extends ScenarioSteps {
    ProductListPages productListPages;

    @Step
    public void clickAddProduct() {
        productListPages.clickBtnAddProduct();
    }

    public boolean searchProduct(String nameProduct) {
        return productListPages.searchProduct(nameProduct);
    }

    public void chooseProduct(String productName) {
        productListPages.clickProductName(productName);
    }

    public void selectAllProductCrossPage() {
        productListPages.selectAllProductCrossPage();
    }

    public boolean isNoDataFound(String title) {
        return productListPages.isNoDataFound();
    }

    public boolean importEnoughProduct(String totalExpect) {
        return productListPages.importEnoughProduct(totalExpect);
    }

    public void clickAction() {
        productListPages.waitForPageLoad();
        productListPages.clickBtnAction();
    }

    public void chooseActionDeleteSelectedProduct(String typeProduct) {
        switch (typeProduct) {
            case "products":
                productListPages.chooseActionDeleteProduct();
                break;
            case "campaigns":
                productListPages.chooseAction("Delete selected " + typeProduct + "");
                break;
            default:
                fail();
        }
    }

    public void clickBtnDelete() {
        productListPages.clickBtnDelete();
        waitABit(2000);
        productListPages.waitForPageLoad();
    }

    public void verifyNoDataFound() {
        productListPages.verifyNoDataFound();
    }

    public void verifyNoProductFound() {
        productListPages.verifyNoProductFound();
    }

    public boolean hasProduct() {
        return productListPages.hasProduct();
    }

    public void clickBtnCancel() {
        productListPages.clickBtnCancel();
    }

    @Step
    public void clickBtnClose() {
        productListPages.clickBtnClose();
    }

    @Step
    public void verifyProductNotFound() {
        productListPages.verifyProductNotFound();
    }

    @Step
    public void clickBtnImport() {
        productListPages.closePopup();
        productListPages.clickBtnImport();
    }

    @Step
    public void chooseOverwrite() {
        productListPages.checkCheckboxWithLabel("Overwrite any existing products that have the same product handle.");
    }

    @Step
    public void clickUploadFile() {
        productListPages.clickUploadFile();
    }

    @Step
    public void clickOkImport() {
        productListPages.clickBtn("OK");
    }

    @Step
    public void verifyContentOfMailImportProduct(String content) {
        productListPages.verifyContentOfMailImportProduct(content);
    }

    @Step
    public void verifyContentOfMailImportProductError(String error) {
        productListPages.verifyContentOfMailImportProductError(error);
    }

    public void verifyTitleProductList(String title) {
        productListPages.verifyTitleProductList(title);
    }

    public void verifyTypeProductList(String title, String type) {
        productListPages.verifyProductInforInProductList(title, "TYPE", type);
    }

    public void verifyVendorProductList(String title, String vendor) {
        productListPages.verifyProductInforInProductList(title, "VENDOR", vendor);
    }

    @Step
    public void chooseFileCSV(String fileName) {
        productListPages.chooseFileCSV(fileName);
    }

    @Step
    public void verifyMessage(String errorMessage) {
        productListPages.verifyMessage(errorMessage);
    }

    @Step
    public void selectAllProductNextPage() {
        productListPages.selectAllProductNextPage();
    }

    @Step
    public void selectAllProduct() {
        productListPages.selectAllProduct();
    }

    @Step
    public void nextPage() {
        productListPages.nextPage();
    }

    @Step
    public void clickExportProduct() {
        productListPages.clickBtn("Export");
    }

    @Step
    public void selectExport(String export) {
        productListPages.selectRadioButtonWithLabel(export, true);
    }

    @Step
    public void selectExportAs(String exportAs) {
        productListPages.selectRadioButtonWithLabel(exportAs, true);
    }

    @Step
    public void clickExport() {
        productListPages.clickExport();
    }

    @Step
    public void verifyMessageExportSuccess(String msg) {
        productListPages.verifyMessageExportSuccess(msg);
    }

    @Step
    public void verifyReceivedEmail(String emailAddress, String subject) {
        productListPages.verifyReceivedMail(emailAddress, subject);
    }

    @Step
    public void openSubjectMail(String subject) {
        productListPages.openSubjectMail(subject);
    }

    @Step
    public void loginMailBox(String emailAddress) {
        productListPages.loginMailBox(emailAddress);
    }

    @Step
    public void verifyContentOfMailExportProduct() {
        productListPages.verifyContentOfMailExportProduct();
    }

    @Step
    public void chooseAction(String action) {
        productListPages.chooseAction(action);
    }

    @Step
    public void clickDelete() {
        productListPages.clickBtn("Delete");
    }

    @Step
    public void verifyMessageDeleteProduct(String message) {
        productListPages.verifyMessageDeleteProduct(message);
    }

    @Step
    public void verifyContentOfMail(String content) {
        productListPages.verifyContentOfMail(content);
    }

    @Step
    public void confirmAction(String action, String pass) {
        if (action.equals("Delete selected products")) {
            clickBtnDelete();
            confirmPassDelete(pass);
        } else if (action.contains("tags")) {
            productListPages.clickBtn("Apply changes");
            productListPages.waitProductListingPageDisplayed();
        } else if (action.contains("collection")) {
            productListPages.clickBtn("Save");
            productListPages.waitProductListingPageDisplayed();
        } else {
            productListPages.clickBtn(action);
            productListPages.waitProductListingPageDisplayed();
        }
        productListPages.refreshPage();
        productListPages.waitUntilInvisibleLoading(10);
    }

    @Step
    public void selectTab(String tab) {
        if (!tab.isEmpty()) {
            productListPages.closePopup();
            productListPages.selectTab(tab);
        }
    }

    @Step
    public void verifyListProduct(String listProduct) {
        productListPages.verifyListProduct(listProduct);
    }

    @Step
    public void selectProduct(String title) {
        if (!title.isEmpty())
            productListPages.selectProduct(title);
    }

    @Step
    public void verifyStatus(String product, String status) {
        productListPages.verifyStatus(product, status);
    }

    @Step
    public void verifyTotalProduct(String total) {
        productListPages.verifyTotalProduct(total);
    }

    @Step
    public void unSelectProduct(int numberOfProduct) {
        productListPages.unSelectProduct(numberOfProduct);
    }

    @Step
    public void inputTags(String tags) {
        productListPages.inputTags(tags);
    }

    @Step
    public void applyChanges() {
        productListPages.clickBtn("Apply changes");
    }

    @Step
    public void addCollection(String collection) {
        productListPages.addCollection(collection);
    }

    @Step
    public void selectCollection(String collection) {
        productListPages.selectCollection(collection);
    }

    @Step
    public void clickMoreFilter() {
        productListPages.clickBtn("More filters");
    }

    @Step
    public void clickDone() {
        productListPages.clickBtn("Done");
    }

    @Step
    public void filterByValue(String filter, String value) {
        productListPages.filterByValue(filter, value);
    }

    @Step
    public void filterByConditionValue(String filter, String condition, String value) {
        productListPages.filterByConditionValue(filter, condition, value);
    }

    @Step
    public void filterByCondition(String filter, String condition) {
        productListPages.filterByCondition(filter, condition);
    }

    @Step
    public void confirmPassDelete(String pass) {
        productListPages.confirmPassDelete(pass);
    }

    @Step
    public void clickBtnStatusImport() {
        productListPages.clickBtnStatusImport();
    }

    @Step
    public void verifyStatusImport(String nameFile, String status) {
        productListPages.verifyStatusImport(nameFile, status);
    }

    @Step
    public void verifyImport(String nameFile, String information) {
        productListPages.verifyImport(nameFile, information);
    }

    public JsonPath getProduct(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/products.json?query=&status=all&access_token=" + accessToken;
        return productListPages.getAPI(url);
    }

    @Step
    public Response deleteProductsByAPI(Long id, String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/products.json?ids=" + id;
        JsonObject requestParam = requestBodyDeleteProduct();
        Response resp = given().header("X-ShopBase-Access-Token", accessToken).header("Content-Type", "text/plain;charset=UTF-8").body(requestParam.toString()).delete(url);
        return resp;
    }

    private JsonObject requestBodyDeleteProduct() {
        JsonObject requestParam = new JsonObject();
        JsonObject params = new JsonObject();
        params.addProperty("numbers", 1);
        requestParam.addProperty("params", String.valueOf(params));
        requestParam.addProperty("otp_service", "");
        requestParam.addProperty("calling_code", "");
        requestParam.addProperty("phone_user", "");
        requestParam.addProperty("otp_code", "");
        return requestParam;
    }

    @Step
    public void verifyProductStoreImport() {
        productListPages.verifyProductStoreImport();
    }

    public List<String> getListProductByAPI(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/products.json";
        JsonPath productList = productListPages.getAPISbase(api, accessToken);
        return productList.get("products.title");
    }

    public List<String> getListCampaignByAPI(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/pbase-campaigns.json";
        JsonPath campaigList = productListPages.getAPISbase(api, accessToken);
        return campaigList.get("campaigns.title");
    }

    //Clone product by Duc Dao
    public void selectProducts(String numberOfProduct) {
        productListPages.selectProducts(numberOfProduct);
    }

    public void importProdToAnotherShop(String desStore, String typeShop) {
        productListPages.clickActionButton();
        productListPages.clickImportProducts(typeShop);
        productListPages.chooseAStore(desStore);
    }

    @Step
    public void verifyMessageCloneProduct(String message) {
        productListPages.verifyMessageCloneProduct(message);
    }

    @Step
    public void clickImport() {
        productListPages.clickImport();
    }

    @Step
    public void verifyTotalProductClone(String totalProducts) {
        productListPages.verifyTotalProductClone(totalProducts);
    }

    @Step
    public void selectActionClone(String action) {
        productListPages.selectActionClone(action);
    }

    @Step
    public void selectKeepID(String check) {
        productListPages.selectKeepID(check);
    }

    public void selectShop(String DashboardShop) {
        productListPages.selectShop(DashboardShop);
    }

    public void verifyImportProduct(String status, String actualQuantity) {
        productListPages.verifyImportProcess(status, 0);
        productListPages.verifyProductQuantityOfDesShop(actualQuantity);
    }

    public void verifyImportProcess() {
        productListPages.verifyImportProcess();
    }

    public void actionWithMultipleProduct(String actions) {
        String action = actions;
        String[] value = null;

        if (actions.contains(">")) {
            String[] acts = actions.split(">");
            action = acts[0];
            value = acts[1].split(",");
        }
        clickAction();
        chooseAction(action);
        inputValue(action, value);
        waitABit(1000);
        productListPages.waitProductListingPageDisplayed();
        productListPages.refreshPage();
    }

    String pass = LoadObject.getProperty("user.pwd");

    private void inputValue(String action, String[] value) {
        if (action.contains("tags")) {
            for (String tag : value) {
                inputTags(tag);
                productListPages.clickBtn("Apply changes");
                productListPages.waitProductListingPageDisplayed();
            }
        } else if (action.contains("collection")) {
            for (String collection : value) {
                addCollection(collection);
                selectCollection(collection);
            }
            productListPages.clickSave();
            productListPages.waitProductListingPageDisplayed();
        } else if (action.equals("Delete selected products")) {
            clickBtnDelete();
            confirmPassDelete(pass);
        } else if (!action.equals("Edit products")) {
            productListPages.clickBtn(action);
            productListPages.waitProductListingPageDisplayed();
        }
    }

    public void verifyProductExist(String title, boolean isExist) {
        productListPages.refreshPage();
        int n = productListPages.countProductByProductName(title);
        if (isExist) {
            assertThat(n).isGreaterThanOrEqualTo(1);
        } else {
            assertThat(n).isEqualTo(0);
        }
    }

    public void cleanFilter() {
        productListPages.clearFilter();
    }

    public void clickOnTab(String tab) {
        if (tab.contains(",")) {
            String[] subTab = tab.split(",");
            for (String nameTab : subTab) {
                productListPages.clickOnTab(nameTab);
            }
        } else {
            productListPages.clickOnTab(tab);
        }
    }

    public void verifyNotDisplayBT(String buttonName) {
        boolean isImportDisable = productListPages.isClickableBtn(buttonName);
        Assertions.assertThat(isImportDisable).isEqualTo(false);
    }

    public void clickBTMoreFilter() {
        productListPages.clickBtn("More filters");
    }

    public void filterCondition(String condition, String subCondition) {
        productListPages.filterCondition(condition);
        productListPages.filterSubCondition(subCondition);
    }

    public void clickBTDone() {
        productListPages.clickBtn("Done");
    }

    public void clickBTCurrentFilter(String buttonName) {
        productListPages.clickBtn(buttonName);
    }

    public void enterNameTemplateFilter(String nameTemplateFilter) {
        productListPages.enterNameTemplateFilter(nameTemplateFilter);
    }

    public void verifyDisplayTemplateNew(String templateName) {
        productListPages.verifyDisplayTemplateNew(templateName);

    }

    public void verifyDateFilter(String productName, String status) {
        productListPages.verifyDateFilter(productName);
        productListPages.verifyDateFilter(status);
    }

    public void clickLink(String action) {
        productListPages.clickLink(action);
    }

    public void verifyLinkRedirectProductMappingPage() {
        assertThat(productListPages.getNamePage()).isEqualTo("Product Mapping");
    }

    public void clickActionTemplate(String action) {
        if (action.equals("Edit")) {
            productListPages.clickActionEdit();
        } else {
            productListPages.clickActionDelete();
        }
    }

    public void verifyNotDisplayTemplateFilter(String templateName) {
        productListPages.verifyNotDisplayTemplateFilter(templateName);
    }

    @Step
    public void importUrlProduct() {
        productListPages.importUrlProduct();
    }

    @Step
    public void selectPlatformAndType(String platform) {
        productListPages.selectPlatformAndType(platform);
    }

    @Step
    public void inputUrl(String url) {
        productListPages.inputUrl(url);
    }

    @Step
    public void importProduct() {
        productListPages.importProduct();
    }

    @Step
    public void clickImportList() {
        productListPages.clickImportList();
    }

    @Step
    public void verifyStatusImportApp(String product, String status) {
        productListPages.verifyStatusImportApp(product, status);
    }

    @Step
    public void verifyNoteImportApp(String product, String note) {
        productListPages.verifyNoteImportApp(product, note);
    }

    public int getNumberProductOfList() {
        productListPages.waitForEverythingComplete();
        return productListPages.getNumberProductOfList();
    }

    @Step
    public void clickBtnImportPB() {
        productListPages.clickBtnImportPB();
    }

    @Step
    public Response deleteAllProductsByAPI(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/products.json?ids=&title=&published_status=any&permanently_delete=0&type=all&exclude_ids=&ignore_watch=true";
        JsonObject requestParam = requestBodyDeleteAllProduct();
        Response resp = given().header("x-shopbase-access-token", accessToken).header("content-type", "text/plain;charset=UTF-8").body(requestParam.toString()).delete(url);
        return resp;
    }

    @Step
    private JsonObject requestBodyDeleteAllProduct() {
        JsonObject requestParam = new JsonObject();
        requestParam.addProperty("type", "all");
        return requestParam;
    }

    @Step
    public void closePopup() {
        productListPages.closePopup();
    }
    @Step
    public void clickPaymentStatusInHive() {
        productListPages.clickPaymentStatusInHive();
    }
    @Step
    public void verifyListPaymentStatus(String status) {
        productListPages.verifyListPaymentStatus(status);
    }
    @Step
    public void selectPlatformTypeInHive(String platform) {
        productListPages.selectPlatformTypeInHive(platform);
    }
    @Step
    public void verifyProductsInSearchProducts(String sNumber) {
        productListPages.verifyProductsInSearchProducts(sNumber);
    }

    public void openPageDeactiveShopbasePayments() {
        productListPages.openPageDeactiveShopbasePayments();
    }

    @Step
    public void verifyMessageDetail(String msg){
        productListPages.verifyMessageDetail(msg);
    }
    @Step
    public void verifyVariantColor(String sColor) {
        productListPages.verifyVariantColor(sColor);
    }
}

