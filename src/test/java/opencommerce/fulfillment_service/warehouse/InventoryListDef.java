package opencommerce.fulfillment_service.warehouse;

import static com.opencommerce.shopbase.OrderVariable.*;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.InventoryListStep;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryListDef {

    public static String conditionValue = "";
    @Steps
    InventoryListStep inventoryListStep;
    @Steps
    FulfillmentSteps orderStep;

    @Then("Verify data search with condition of {string}")
    public void verifyDataSearchWithConditionOf(String title) {
        inventoryListStep.verifyDataSearchWithCondition(conditionValue, title);
    }

    @Then("show message error {string}")
    public void showMessageError(String message) {
        inventoryListStep.verifyMessage(message);
    }

    @Then("verify that the content of file downloaded is matched with product information from dashboard")
    public void verifyThatTheContentOfFileDownloadedIsMatchedWithProductInformationFromDashboard(List<String> dataTable) {
        FILE_PATH_ORDER_DOWNLOADED = inventoryListStep.openFileDownloaded();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_ORDER_DOWNLOADED));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                Map<String, String> expExport = new HashMap<>();
                for (String column : dataTable) {
                    // lib doesn't support getting the first element (Product Name)
                    if ("Product Name".equals(column)) {
                        expExport.put(column, csvRecord.get(0));
                    } else {
                        expExport.put(column, csvRecord.get(column));
                    }
                }
                expExportProductInventoryList.add(expExport);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        inventoryListStep.verifyDownloadFileMatchingTemplate(expExportProductInventoryList);
    }

    @Given("get product inventory default")
    public void getProductInventoryDefault() {
        inventoryListStep.clickElementProductInInventory();
        inventoryListStep.getProductInventoryDefault();
    }

    @Given("search product with {string}")
    public void searchProductWith(String search) {
        inventoryListStep.searchProductImport(search);
    }

    @When("select product export")
    public void selectProductExport() {
        inventoryListStep.selectProductExport();
    }

    @Then("verify noti export equal {string} with email {string}")
    public void verifyNotiExportEqualWithEmail(String noti, String email) {
        inventoryListStep.verifyNotiExportEqual(noti, email);
    }

    @Given("import to store with product {string}")
    public void importToStoreWithProduct(String product) {
        inventoryListStep.searchProductImport(product);
        inventoryListStep.chooseProductImport();
        inventoryListStep.clickButtonImportStore();
        inventoryListStep.verifyImportProductSuccess();
    }

    @Then("verify multiple product import")
    public void verifyMultipleProductImport() {
        for (Object prod : productMultiple) {
            verifyProductImported(prod.toString());
        }
    }

    @And("filter {string} with condition {string} and value {string}")
    public void filterWithConditionAndValue(String status, String condition, String value) {
        inventoryListStep.enterCondition(status, condition, value);
        conditionValue = value;
    }

    @And("clear info filter")
    public void clearInfoFilter() {
        inventoryListStep.clickButton("Clear all filters");
        inventoryListStep.verifyShowClearData();
    }

    @And("save info filter {string} with condition {string} and value {string}")
    public void saveInfoFilterWithConditionAndValue(String status, String contition, String value) {
        inventoryListStep.enterCondition(status, contition, value);
        inventoryListStep.clickButton("Save filter");
        inventoryListStep.saveConditionInFilter();
    }

    @Then("verify product imported {string}")
    public void verifyProductImported(String product) {
        inventoryListStep.searchProductWith(product);
        inventoryListStep.verifyProductImport(product);
        inventoryListStep.moteToProductDetail(product);
        inventoryListStep.deleteProductImport();
    }

    @Given("import multiple product in store")
    public void importMultipleProductInStore() {
        inventoryListStep.chooseMultipleProductImport();
        inventoryListStep.clickButtonImportMultipleStore();
        inventoryListStep.verifyImportProductSuccess();
    }

    @And("export product {string} in inventory")
    public void exportProductInInventory(String option) {
        inventoryListStep.clickButton("Export inventory");
        inventoryListStep.clickRadioExportWith(option);
        inventoryListStep.clickButtonOnPopup("Export");
    }

    HashMap<String, Integer> listitemProduct = new HashMap<>();
    HashMap<String, Integer> listitemVariant = new HashMap<>();

    @Given("get item inventory of product")
    public void getItemInventoryOfProduct(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            inventoryListStep.searchProductImport(product);
            listitemProduct = orderStep.getValueInventory();
            inventoryListStep.clickProduct(product);
            listitemVariant = inventoryListStep.getValueItemVarian(variant);
        }
    }

    @And("purchase product with")
    public void purchaseProductWith(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            inventoryListStep.searchProductInQuotation(product);
            inventoryListStep.switchTab("Quotation created");
            inventoryListStep.moveToViewQuotation(product);
            inventoryListStep.purchaseProductWithVariant(variant, quantity);
        }
    }

    @And("verify item inventory of product after purchase")
    public void verifyItemInventoryOfProductAfterPurchase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            inventoryListStep.searchProductImport(product);
            HashMap<String, Integer> infoProduct = orderStep.getValueInventory();
            verifyValue(infoProduct, listitemProduct, quantity);
            inventoryListStep.clickProduct(product);
            HashMap<String, Integer> infoVariant = inventoryListStep.getValueItemVarian(variant);
            verifyValue(infoVariant, listitemVariant, quantity);
        }
    }

    private void verifyValue(HashMap<String, Integer> act, HashMap<String, Integer> exp, int quantity) {
        for (String label : act.keySet()) {
            if ("PURCHASED".equals(label) || "INCOMING".equals(label)) {
                assertThat(act.get(label)).isEqualTo(exp.get(label) + quantity);
            } else {
                assertThat(act.get(label)).isEqualTo(exp.get(label));
            }
        }
    }

    @And("move to home page")
    public void moveToHomePage() {
        inventoryListStep.moveToHomePage();
    }

    @And("move to {string} page")
    public void moveToPage(String tabName) {
        inventoryListStep.moveToPage(tabName);
    }

    @Given("get count inventory of product warehouse")
    public void get_count_inventory_of_product_warehouse(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String purchase = SessionData.getDataTbVal(dataTable, row, "Purchased");
            String incoming = SessionData.getDataTbVal(dataTable, row, "Incoming");
            String availableStock = SessionData.getDataTbVal(dataTable, row, "Available stock");
            String unfulfilled = SessionData.getDataTbVal(dataTable, row, "Unfulfilled");
            String awaitingStock = SessionData.getDataTbVal(dataTable, row, "Awaiting stock");
            String processing = SessionData.getDataTbVal(dataTable, row, "Processing");
            String fulfilled = SessionData.getDataTbVal(dataTable, row, "Fulfilled");
            String needPurchase = SessionData.getDataTbVal(dataTable, row, "Need to purchase");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");

            JsonPath inventoryInformation = inventoryListStep.getInventoryByAPI(product);
            int countPurchase = 0;
            int countIncoming = 0;
            int countAvailableStock = 0;
            int countUnfulfilled = 0;
            int countAwaitingStock = 0;
            int countProcessing = 0;
            int countFulfilled = 0;
            int countNeedPurchase = 0;
            int count = 0;
            if (quantity.contains("-")) {
                count = -1 * Integer.parseInt(quantity.replace("-", ""));
            }
            if (quantity.contains("+")) {
                count = Integer.parseInt(quantity.replace("+", ""));
            }

            if (!purchase.isEmpty()) {
                countPurchase = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "purchased") + count;
            }
            if (!incoming.isEmpty()) {
                countIncoming = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "purchased") + count;
            }
            if (!availableStock.isEmpty()) {
                countAvailableStock = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "available_stock") + count;
            }
            if (!unfulfilled.isEmpty()) {
                countUnfulfilled = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "wait_to_fulfill") + count;
            }
            if (!awaitingStock.isEmpty()) {
                countAwaitingStock = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "awaiting_stock") + count;
            }
            if (!processing.isEmpty()) {
                countProcessing = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "processing") + count;
            }
            if (!fulfilled.isEmpty()) {
                countFulfilled = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "fulfilled") + count;
            }
            if (!needPurchase.isEmpty()) {
                countNeedPurchase = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "need_to_purchase") + count;
            }
            if (result.contains("1")) {
                countInventoryBefor.add(countPurchase);
                countInventoryBefor.add(countIncoming);
                countInventoryBefor.add(countAvailableStock);
                countInventoryBefor.add(countUnfulfilled);
                countInventoryBefor.add(countAvailableStock);
                countInventoryBefor.add(countProcessing);
                countInventoryBefor.add(countAwaitingStock);
                countInventoryBefor.add(countFulfilled);
                countInventoryBefor.add(countNeedPurchase);
            }
            if (result.contains("2")) {
                countInventoryAfter.add(countPurchase);
                countInventoryAfter.add(countIncoming);
                countInventoryAfter.add(countAvailableStock);
                countInventoryAfter.add(countUnfulfilled);
                countInventoryAfter.add(countAvailableStock);
                countInventoryAfter.add(countProcessing);
                countInventoryAfter.add(countAwaitingStock);
                countInventoryAfter.add(countFulfilled);
                countInventoryAfter.add(countNeedPurchase);
            }
        }
    }

    @Given("get count {string} inventory of product warehouse")
    public void get_count_inventory_of_product_warehouse(String countStatus, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            JsonPath inventoryInformation = inventoryListStep.getInventoryByAPI(product);
            switch (countStatus) {
                case "Purchased":
                    countStatus = "purchased";
                    break;
                case "Incoming":
                    countStatus = "incoming";
                    break;
                case "Available stock":
                    countStatus = "available_stock";
                    break;
                case "Unfulfilled":
                    countStatus = "wait_to_fulfill";
                    break;
                case "Awaiting stock":
                    countStatus = "awaiting_stock";
                    break;
                case "Processing":
                    countStatus = "processing";
                    break;
                case "Fulfilled":
                    countStatus = "fulfilled";
                    break;
                case "Need to purchased":
                    countStatus = "need_to_purchase";
                    break;
            }

            if (result.contains("1")) {
                int resultBefor = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "" + countStatus + "");
                countStatusInventoryBefor.add(resultBefor);
            }
            if (result.contains("2")) {
                int resultAfter = inventoryListStep.getCountInventory(inventoryInformation, product, variant, "" + countStatus + "");
                countStatusInventoryAfter.add(resultAfter);
            }
        }

    }

    @Given("verify data inventory of product warehouse")
    public void verify_data_inventory_of_product_warehouse() {
        assertThat(countInventoryBefor).isEqualTo(countInventoryAfter);
    }

    @Given("verify data {string} inventory of product warehouse after update")
    public void verify_data_inventory_of_product_warehouse_after_update(String string) {
        assertThat(countStatusInventoryBefor).isEqualTo(countStatusInventoryAfter);
    }


}
