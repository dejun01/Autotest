package opencommerce.plus_base.catalog;

import com.opencommerce.shopbase.plusbase.steps.CatalogListStep;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class CatalogListDef {

    @Steps
    CatalogListStep catalogListStep;

    @Given("search product = {string} in catalog")
    public void searchProductExitInCatalog(String product) {
        catalogListStep.searchProduct(product, "Search by product name");
    }

    @Then("verify info product with")
    public void verifyInfoProductWith(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String baseCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String sellingPrice = SessionData.getDataTbVal(dataTable, row, "Selling price");
            boolean status = "yes".equals(SessionData.getDataTbVal(dataTable, row, "Product in list")) ? true : false;
            catalogListStep.verifyInfoProductWith(product, status, baseCost, sellingPrice);
        }
    }

    @Given("filter {string} with conditions = {string} and value = {string}")
    public void filterWithConditionsAndValue(String label, String conditions, String value) {
        catalogListStep.clickButton("Filters");
        catalogListStep.clickLabelFilter(label);
        catalogListStep.choiceConditions(label, conditions);
        catalogListStep.fillValue(label, value);
        catalogListStep.clickButton("Apply");
    }

    @Then("verify {string} with conditions = {string} and value = {string}")
    public void verifyWithConditionsAndValue(String label, String conditions, String value) {
        catalogListStep.verifyResultFilter(label, conditions, value);
    }

    @And("clear all filter")
    public void clearAllFilter() {
        catalogListStep.clickButton("Filter");
        String btnClaer = "Clear all filters";
        catalogListStep.clickButton(btnClaer);
        catalogListStep.verifyResult(btnClaer);
    }

    @And("move to product detail with product name = {string}")
    public void moveToProductDetailWithProductName(String product) {
        catalogListStep.moveToProductDetail(product);
        catalogListStep.verifyProductDetail(product);
    }

    @Given("filter {string} with value = {string}")
    public void filterWithValue(String label, String val) {
        catalogListStep.clickButton("Filters");
        catalogListStep.clickLabelFilter(label);
        catalogListStep.searchProduct(val, "Search Tags");
        catalogListStep.clickButton("Apply");
    }

    @Given("^click to (category|sub-category) name with$")
    public void clickToCategoryName(String option, List<List<String>> data) {
        for (int row: SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String name = SessionData.getDataTbVal(data, row, "Name");
            catalogListStep.clickCategoryOrSubCategoryName(option, name);
        }
    }

    @And("Removed tag with value = {string}")
    public void removedTagWithValue(String tag) {
        catalogListStep.removedTagWithValue(tag);
    }

    @When("move to {string} screen in plusbase")
    public void moveToScreenInPlusbase(String label) {
        catalogListStep.moveToScreenInPlusbase(label);
    }

    @Given("search and move to product detail with product = {string}")
    public void searchAndMoveToProductDetailWithProduct(String product) {
        catalogListStep.searchProduct(product, "Search by product name");
        catalogListStep.moveToProductDetail(product);
        catalogListStep.verifyProductDetail(product);
    }

    @And("verify product in product tab on store")
    public void verifyProductInProductTabOnStore(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String[] variant = SessionData.getDataTbVal(data, row, "Variant").split(":");
            String[] sellingPrice = SessionData.getDataTbVal(data, row, "Selling price").split(":");
            catalogListStep.clickButton("Edit product");
            catalogListStep.switchTabLastest();
            catalogListStep.verifyProductName(product);
            catalogListStep.verifyVariant(variant);
            catalogListStep.verifySellingPrice(sellingPrice);
            catalogListStep.deleteProduct("Delete product");
        }
    }

    @And("verify import multi product with")
    public void verifyImportMultiProductWith(List<List<String>> data) {
        for(int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            catalogListStep.verifyProductName(product);
            catalogListStep.verifyProductGroup(product, "Edit product");
            catalogListStep.verifyProductGroup(product, "View on store");
            catalogListStep.verifyProductGroup(product, "variant(s) have been added to store");
        }
    }

    @And("delete product name = {string}")
    public void deleteProductName(String product) {
        catalogListStep.searchProduct(product, "Search products");
        catalogListStep.clickProduct(product);
        catalogListStep.deleteProduct("Delete product");
    }

    @And("Change shipping order with")
    public void changeShippingOrderWith(List<List<String>> data) {

        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String firstItem = SessionData.getDataTbVal(data, row, "First item");
            String sellingPrice = SessionData.getDataTbVal(data, row, "Selling price");
            catalogListStep.clickTab("Pricing", product);
            catalogListStep.fillDataMarkup(product, "SHIPPING", variant, firstItem);
            catalogListStep.fillDataMarkup(product, "SELLING PRICE", variant, sellingPrice);
            catalogListStep.focusOut();
            catalogListStep.verifyProfitMarginMarkup(product, variant);
        }
    }

    @And("get shipping and selling price current with")
    public void getShippingAndSellingPriceCurrent(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String color = SessionData.getDataTbVal(data, row, "Color");
            String size = SessionData.getDataTbVal(data, row, "Size");
            catalogListStep.clickVariant("Color", color);
            catalogListStep.clickVariant("Size", size);
        }
        catalogListStep.getShippingAndSellingPriceCurrent();
    }
}
