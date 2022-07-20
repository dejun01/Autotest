package opencommerce.fulfillment_service.fulfillment;

import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class FulfillmentDef {

    @Steps
    FulfillmentSteps fulfillmentSteps;
    @Steps
    OrderSteps orderSteps;


    String nameTab = "";
    public static String count = "";

    @And("fulfill order with")
    public void fulfillOrder(List<List<String>> dataTable) {
        fulfillmentSteps.searchOrderName(orderNumber);
        fulfillmentSteps.selectOrderFulfill();
        shipping = fulfillmentSteps.getShippingOfOrder("SHIPPING");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String titlePopup = SessionData.getDataTbVal(dataTable, row, "Title popup");
            String contentDetail = SessionData.getDataTbVal(dataTable, row, "Detail");
            boolean flagAvialableStock = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Flag avialable stock"));
            boolean flagAutoPurchase = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Flag auto purchase"));
            if (flagAvialableStock) {
                String headerContent = SessionData.getDataTbVal(dataTable, row, "Header content");
                String price = SessionData.getDataTbVal(dataTable, row, "Price").replace("@price shipping@", shipping);
                fulfillmentSteps.verifyLabelPopup(price);
                fulfillmentSteps.verifyLabelPopup(headerContent);
                fulfillmentSteps.verifyTotalOrder(shipping);

            }
            fulfillmentSteps.verifyLabelPopup(titlePopup);
            String[] details = contentDetail.split(">");
            for (String val : details) {
                fulfillmentSteps.verifyLabelPopup(val);

            }
            if (flagAvialableStock) {
                fulfillmentSteps.clickButton("Pay now");
            } else if (flagAutoPurchase) {
                fulfillmentSteps.checkAutoPurchase("Auto purchase enough item to fulfill");
                fulfillmentSteps.clickButton("Checkout");
                fulfillmentSteps.clickButton("Pay now");
            } else {
                fulfillmentSteps.verifyVisibleElement("Checkout");
            }
        }
    }

    @And("verify order detail after fulfillment with")
    public void verifyOrderDetailAfterFulfillmentWith(List<List<String>> dataTable) {
        fulfillmentSteps.searchOrderName(orderNumber);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int number = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Number Line Fulfill"));
            String status = SessionData.getDataTbVal(dataTable, row, "Fulfillment Status");
            fulfillmentSteps.verifyStatusOrderAtList(status);
            fulfillmentSteps.clickOrderName(orderNumber);
            fulfillmentSteps.verifyOrderDetail(orderNumber, number, status);
        }
    }

    int quantity = 0;
    String statusFulfilled = "";
    float balance = 0;
    HashMap<String, Integer> listBeforecheckout = new HashMap<>();
    HashMap<String, Integer> listBeforeFulfill = new HashMap<>();

    @And("^get value inventory of product before (fulfill|checkout)$")
    public void getValueInInventoryBeforeCheckout(String type, List<List<String>> dataTable) {
        searchProductInventory(dataTable);
        listBeforeFulfill = fulfillmentSteps.getValueInventory();
    }

    //    xem lại đoạn này ko cần nữa thì xóa
    @And("^get and verify value in inventory before fulfill$")
    public void getValueInInventory(List<List<String>> dataTable) {
        searchProductInventory(dataTable);
        listBeforeFulfill = fulfillmentSteps.getValueInventory();
        assertThat(listBeforeFulfill.get("SOLD")).isEqualTo(listBeforecheckout.get("SOLD"));
        assertThat(listBeforeFulfill.get("AVAILABLE STOCK")).isEqualTo(listBeforecheckout.get("AVAILABLE STOCK"));
        assertThat(listBeforeFulfill.get("UNFULFILLED")).isEqualTo(listBeforecheckout.get("UNFULFILLED") + quantity);
        assertThat(listBeforeFulfill.get("PURCHASED")).isEqualTo(listBeforecheckout.get("PURCHASED"));
    }

    @And("^get and verify value in inventory after fulfill$")
    public void getValueInInventoryAfterFulfill(List<List<String>> dataTable) {
        searchProductInventory(dataTable);
        HashMap<String, Integer> list = fulfillmentSteps.getValueInventory();
        if ("Processing".equals(statusFulfilled)) {
            fulfillmentSteps.verifyValueWithStatusProcessing(list, listBeforeFulfill, quantity);
        }
        if ("Awaiting Stock".equals(statusFulfilled)) {
            fulfillmentSteps.verifyValueWithStatusWaittingStock(list, listBeforeFulfill, quantity);
        }
        if ("Unfulfilled".equals(statusFulfilled)) {
            assertThat(list).isEqualTo(listBeforeFulfill);
        }
        if ("Fulfilled".equals(statusFulfilled)) {
            fulfillmentSteps.verifyValueWithStatusFulfilled(list, listBeforeFulfill, quantity);
        }
    }

    @And("get and verify value in inventory after cancel fulfill")
    public void getValueInInventoryAfterCancelFulfill(List<List<String>> dataTable) {
        searchProductInventory(dataTable);
        HashMap<String, Integer> afterCancellist = fulfillmentSteps.getValueInventory();
        assertThat(afterCancellist).isEqualTo(listBeforeFulfill);
    }

    private void searchProductInventory(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String actQuantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            if (actQuantity.isEmpty()) {
                actQuantity = "0";
            } else if (actQuantity.contains("@quantity")) {
                actQuantity = actQuantity.replace("@quantity", itemWarehouse);
            }
            quantity = Integer.parseInt(actQuantity);
            statusFulfilled = SessionData.getDataTbVal(dataTable, row, "Status");
            fulfillmentSteps.searchProduct(product);
            fulfillmentSteps.verifyProduct(product);
        }
    }

    @And("redirect to home shopbase")
    public void redirectToHomeShopbase() {
        fulfillmentSteps.redirectToHomeShopbase();
    }

    @And("get balance before fulfillment")
    public void getBalanceBeforeFulfillment() {
        balance = fulfillmentSteps.getBalance();
    }

    @And("verify balance after fulfillment")
    public void verifyBalanceAfterFulfillment() {
        float newBalance = fulfillmentSteps.getBalance();
        assertThat(newBalance).isNotEqualTo(balance);
    }

    private float round(float value) {
        return (float) Math.round(value * 100) / 100;
    }

    @Then("Search and verify fulfillment status = {string} on order of")
    public void searchAndVerifyFulfillmentStatusOnOrderOf(String status, List<List<String>> data) {
        fulfillmentSteps.choiceShopFulfill("All stores");
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String orderName = "the first shop".equals(SessionData.getDataTbVal(data, row, "Shop")) ? orderNameList.get(0) : orderNameList.get(1);
            fulfillmentSteps.verifyStatusOrderList(status, orderName);
        }
    }

    @When("search order of the second shop with fulfillment status = {string}")
    public void searchOrderOfTheSecondShopWithFulfillmentStatus(String status) {
        fulfillmentSteps.choiceShopFulfill("All stores");
        fulfillmentSteps.searchOrderName(orderNameList.get(1));
        fulfillmentSteps.verifyStatusOrderAtList(status);
        fulfillmentSteps.clickOut();
        fulfillmentSteps.clickOrderName(orderNameList.get(1));
    }

    @And("switch to the lastest tab of fulfillment service")
    public void switchToTheLastestTabOfFulfillmentService() {
        fulfillmentSteps.switchToTheLastestTabOfFulfillmentService();
    }

    @And("fulfill order with multi shop")
    public void fulfillOrderWithMultiShop(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String buttonName = SessionData.getDataTbVal(dataTable, row, "Action");
            String buttonFulfill = SessionData.getDataTbVal(dataTable, row, "Button fulfill");
            String buttonSelectedOrder = SessionData.getDataTbVal(dataTable, row, "Button selected order");
            fulfillmentSteps.clickButton(buttonName);
            fulfillmentSteps.clickShopbaseFulfillment("PlusHub");
            fulfillmentSteps.clickButton(buttonFulfill);
            fulfillmentSteps.clickButton(buttonSelectedOrder);
            fulfillmentSteps.clickButton("Pay now");
            fulfillmentSteps.verifyFulfillmentSuccess();
        }
    }

    @Given("logout to with url")
    public void logoutToWithUrl() {
        fulfillmentSteps.logoutToWithUrl();
    }

    @And("filter {string} with condition {string}")
    public void filterWithCondition(String title, String condition) {
        fulfillmentSteps.clickButton("More Filters");
        fulfillmentSteps.filterWithCondition(title, condition);
        fulfillmentSteps.clickButton("Apply");
    }

    @And("verify result filter normal with")
    public void verifyResultFilterNormal(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String title = SessionData.getDataTbVal(data, row, "Title");
            String condition = SessionData.getDataTbVal(data, row, "Condition");
            fulfillmentSteps.verifyResultTag(title, condition);
            fulfillmentSteps.verifyButtonFilter("Edit current filter", false);
            fulfillmentSteps.verifyButtonFilter("Add current filter", true);
        }
    }

    @And("save filter normal with {string}")
    public void saveFilterNormalWith(String button) {
        fulfillmentSteps.clickButton(button);
        fulfillmentSteps.saveFilterNormal("Filter name");
    }

    HashMap<String, String> filterDefault = new HashMap<>();

    @Given("verify tab filter default in order list")
    public void getTabFilterDefaultInOrderList() {
        String[] tabfilterDefault = {"All", "Open", "On Hold", "Closed"};
        filterDefault = fulfillmentSteps.getFilterDefault();
        for (String tab : tabfilterDefault) {
            assertThat(tab.toLowerCase()).isEqualTo(filterDefault.get(tab).toLowerCase());
            fulfillmentSteps.clickTab(tab);
//            fulfillmentSteps.verifyButtonFilter("Save current filter", true);
//            fulfillmentSteps.verifyButtonFilter("Add current filter", false);
//            fulfillmentSteps.verifyShowButton("Save current filter", true);
        }
    }

    @And("verify filter template in popup filter")
    public void verifyFilterTemplateInPopupFilter() {
        fulfillmentSteps.clickButton("Filter templates");
        HashSet<String> filterTemplate = fulfillmentSteps.getFilterTemplate();
        assertThat(filterTemplate).isEqualTo(filterDefault);
    }


    @And("add filter template with name = {string}")
    public void addFilterTemplateWithName(String filterName, List<List<String>> data) {
        fulfillmentSteps.clickButton("More Filters");
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String title = SessionData.getDataTbVal(data, row, "Title");
            String condition = SessionData.getDataTbVal(data, row, "Condition");
            fulfillmentSteps.filterWithCondition(title, condition);
        }
        fulfillmentSteps.clickButton("Apply");
        fulfillmentSteps.clickButton("Save current filter");
        fulfillmentSteps.saveFilterNormal(filterName);
    }

    @And("edit filter template with name = {string} to {string}")
    public void editFilterTemplateWithNameTo(String filterName, String oldFilterName) {
        fulfillmentSteps.clickButton("Edit current filter");
        fulfillmentSteps.verifyValueInput(filterName);
        fulfillmentSteps.updateFilterTemplate(oldFilterName);
    }

    @And("delete filter template with name = {string}")
    public void deleteFilterTemplateWithName(String filterName) {
        fulfillmentSteps.clickTab(filterName);
        fulfillmentSteps.clickButton("Edit current filter");
        fulfillmentSteps.verifyValueInput(filterName);
        fulfillmentSteps.DeleteFilter(filterName);
    }

    @Then("verify filter {string} with condition {string} and title {string}")
    public void verifyFilterWithConditionAndTitle(String label, String val, String title) {
        fulfillmentSteps.verifyResultTag(label, val);
        fulfillmentSteps.verifyResultFilter(val, title);
    }

    HashMap<String, Integer> dataReplace = new HashMap<>();

    @And("get value inventory before replace with")
    public void getValueInventoryBeforeReplaceWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String productReplace = SessionData.getDataTbVal(data, row, "Product replace");
            String variantReplace = SessionData.getDataTbVal(data, row, "Variant replace");
            fulfillmentSteps.searchProduct(product);
            dataReplace.put("product", fulfillmentSteps.getItemOfInventory("UNFULFILLED"));
            if (productReplace.isEmpty()) {
                fulfillmentSteps.showVariantOfProduct(product);
                dataReplace.put("variant", fulfillmentSteps.getItemOfVariantInventory("UNFULFILLED", variant));
                dataReplace.put("variant replace", fulfillmentSteps.getItemOfVariantInventory("UNFULFILLED", variantReplace));
            } else {
                fulfillmentSteps.searchProduct(productReplace);
                dataReplace.put("product replace", fulfillmentSteps.getItemOfInventory("UNFULFILLED"));
            }
        }
    }


    @And("^search (.*) order name$")
    public void searchFirstOrderName(String option) {
        String orderName = "second".equals(option) ? orderNameList.get(1) : orderNumber;
        fulfillmentSteps.searchOrderName(orderName);
    }

    @And("replace the order in list order with")
    public void replaceOrderInListOrder(List<List<String>> data) {
        fulfillmentSteps.showPopupReplace();
        fulfillmentSteps.verifyLabelPopup("Replace warehouse item");
        fulfillmentSteps.verifyLabelPopup("Replace with");
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String variantPlace = SessionData.getDataTbVal(data, row, "Variant replace");
            String productPlace = SessionData.getDataTbVal(data, row, "Product replace");
            boolean isReplaceAll = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Replace all"));
            fulfillmentSteps.verifyLabelPopup(product);
            fulfillmentSteps.verifyVariantActive(variant);
            if (productPlace.isEmpty()) {
                fulfillmentSteps.choiceVariant(variantPlace);
            } else {
                fulfillmentSteps.changeProduct(productPlace);
                fulfillmentSteps.choiceVariant(variantPlace);
            }
            fulfillmentSteps.ReplaceAll(isReplaceAll);
            fulfillmentSteps.clickButton("Replace");
        }
    }

    @And("verify replace the order in list order with")
    public void verifyReplaceTheOrderInListOrderWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            fulfillmentSteps.verifyWarehouseProduct(product, variant, 0);
        }
    }

    @And("get and verify value inventory after replace with")
    public void getAndVerifyValueInventoryAfterReplaceWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String productReplace = SessionData.getDataTbVal(data, row, "Product replace");
            String variantReplace = SessionData.getDataTbVal(data, row, "Variant replace");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(data, row, "Quantity"));
            fulfillmentSteps.searchProduct(product);
            if (productReplace.isEmpty()) {
                fulfillmentSteps.verifyItemOfInventory("UNFULFILLED", dataReplace.get("product"));
                fulfillmentSteps.showVariantOfProduct(product);
                fulfillmentSteps.verifyItemOfVariantInventory("UNFULFILLED", variant, dataReplace.get("variant") - quantity);
                fulfillmentSteps.verifyItemOfVariantInventory("UNFULFILLED", variantReplace, dataReplace.get("variant replace") + quantity);
            } else {
                fulfillmentSteps.verifyItemOfInventory("UNFULFILLED", dataReplace.get("product") - quantity);
                fulfillmentSteps.searchProduct(productReplace);
                fulfillmentSteps.verifyItemOfInventory("UNFULFILLED", dataReplace.get("product replace") + quantity);
            }
        }
    }

    @And("Acc to page {string}")
    public void accToPage(String name) {
        fulfillmentSteps.clickButton("Fulfill with");
        fulfillmentSteps.moveFulfillmentPage("PlusHub");
    }

    @And("move to {string} tab in fulfillment page and verify count")
    public void moveToTabInFulfillmentPage(String tabName) {
        nameTab = tabName;
        fulfillmentSteps.moveTab(tabName);
        int countAfter = Integer.parseInt(count) + 1;
        assertThat(fulfillmentSteps.getcountOrder()).isEqualTo(String.valueOf(countAfter));
    }

    @And("Search by order and verify info order")
    public void searchByOrderAndVerifyInfoOrder(List<List<String>> dataTable) {
        fulfillmentSteps.turnOffFilterAuto();
        fulfillmentSteps.searchOrderPlusBase(orderNumber);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "TAB");
            String order = SessionData.getDataTbVal(dataTable, row, "ORDER");
            String product = SessionData.getDataTbVal(dataTable, row, "PRODUCT");
            String variant = SessionData.getDataTbVal(dataTable, row, "VARIANT");
            String warehouseProduct = SessionData.getDataTbVal(dataTable, row, "WAREHOUSE PRODUCT");
            String warehouseVariant = SessionData.getDataTbVal(dataTable, row, "WAREHOUSE VARIANT");
            String quantity = SessionData.getDataTbVal(dataTable, row, "QUANTITY");
            String shipping = SessionData.getDataTbVal(dataTable, row, "SHIPPING");
            String baseCostPerItem = SessionData.getDataTbVal(dataTable, row, "BASE COST PER ITEM");
            String error = SessionData.getDataTbVal(dataTable, row, "ERROR");
            String trackingNumber = SessionData.getDataTbVal(dataTable, row, "TRACKING NUMBER");
            if (order.contains("@")) {
                order = orderNumber;
            }
            fulfillmentSteps.verifyProductSB(product, variant);
            int index = fulfillmentSteps.getIndexLineItem(product, variant);
            if (!warehouseProduct.isEmpty()) {
                fulfillmentSteps.verifyWarehouseProduct(warehouseProduct, warehouseVariant, index);
            }
            fulfillmentSteps.verifyQuantityLineItem(quantity, index);
            if (!shipping.isEmpty()) {
                fulfillmentSteps.verifyShippingLineItem(shipping, index);
            }
            if (!baseCostPerItem.isEmpty()) {
                fulfillmentSteps.verifyBaseCostLineItem(baseCostPerItem, index);
            }
            if (!error.isEmpty()) {
                fulfillmentSteps.verifyErrorLineItem(error, index);
            }
            if (!trackingNumber.isEmpty()) {
                fulfillmentSteps.verifyTrackingNumberLineItem(trackingNumber, index);
            }

        }

    }

    @And("Verify not display order in tab")
    public void verifyNotDisplayOrderInTab() {
        fulfillmentSteps.searchOrder(orderNumber);
        fulfillmentSteps.verifyNoOrder(orderNumber);
    }

    @And("Mapping product in tab need mapping")
    public void mappingProductInOrderDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String lineItem = SessionData.getDataTbVal(dataTable, row, "Line Item");
            fulfillmentSteps.clickBTMapping(lineItem);
        }
    }

    @And("move to {string} tab in fulfillment page and get count")
    public void moveToTabInFulfillmentPageAndGetCount(String tabName) {
        nameTab = tabName;
        fulfillmentSteps.moveTab(tabName);
        count = fulfillmentSteps.getcountOrder();
    }

    @When("user move to {string} screen")
    public void userMoveToScreen(String tab) {
        fulfillmentSteps.userMoveToScreen(tab);
    }

    @And("verify invoice detail with")
    public void verifyInvoiceDetailWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String content = SessionData.getDataTbVal(data, row, "Content").replace("@order name@", orderNumber);
            String amount = SessionData.getDataTbVal(data, row, "Amount").replace("@price shipping@", shipping);
            fulfillmentSteps.verifyInvoiceDetail("Content", content);
            fulfillmentSteps.verifyInvoiceDetail("Amount", amount);
        }
    }

    @And("fulfill order replace")
    public void fulfillOrderReplace() {
        fulfillmentSteps.selectOrderFulfill();
        fulfillmentSteps.fulfillOrder();
    }

    @And("get and verify value inventory after fulfill with")
    public void getAndVerifyValueInventoryAfterFulfillWith(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String productReplace = SessionData.getDataTbVal(data, row, "Product replace");
            String variantReplace = SessionData.getDataTbVal(data, row, "Variant replace");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(data, row, "Quantity"));
            fulfillmentSteps.searchProduct(product);
            fulfillmentSteps.verifyItemOfInventory("UNFULFILLED", dataReplace.get("product") - quantity);
            if (productReplace.isEmpty()) {
                fulfillmentSteps.showVariantOfProduct(product);
                fulfillmentSteps.verifyItemOfVariantInventory("UNFULFILLED", variant, dataReplace.get("variant") - quantity);
                fulfillmentSteps.verifyItemOfVariantInventory("UNFULFILLED", variantReplace, dataReplace.get("variant replace"));
            } else {
                fulfillmentSteps.searchProduct(productReplace);
                fulfillmentSteps.verifyItemOfInventory("UNFULFILLED", dataReplace.get("product replace"));
            }
        }
    }


    @And("get value {string} in warehouse")
    public void getValueInWarehouse(String label, List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            fulfillmentSteps.searchProduct(product);
            itemWarehouse = fulfillmentSteps.getValueInWarehouse(label);
        }
    }

    @And("Select ordedr fulfill")
    public void selectOrdedrFulfill() {
        fulfillmentSteps.selectOrderFulfill();
        fulfillmentSteps.clickBTConfirm();
    }

    @And("Verify auto filter order created over {int}h and turn off")
    public void verifyAutoFilterOrderCreatedOverHAndTurnOff(int arg0) {
        fulfillmentSteps.verifyAutoDisplayFilterOrderCreatedOver6h();
    }

    @Given("get shipping fee of order by API")
    public void get_shipping_fee_of_order_by_API() {
        JsonPath jsonShippingFee = fulfillmentSteps.getShippingFeeByAPI(orderNumber);
        shippingFeeSBFF = fulfillmentSteps.getActShippingFee(jsonShippingFee);
    }


    @Then("verify {string} btn Get tracking number")
    public void verifyBtnGetTrackingNumber(String is_disable) {
        boolean status = is_disable.equals("disable") ? true : false;
        fulfillmentSteps.verifyStatusOfBtnTrackingNumber(status);
    }

    @And("fulfill order with for {string}")
    public void fulfillOrderWithFor(String pay, List<List<String>> dataTable) {
        fulfillmentSteps.searchOrderName(orderNumber);
        fulfillmentSteps.selectOrderFulfill();
        shipping = fulfillmentSteps.getShippingOfOrder("SHIPPING");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String titlePopup = SessionData.getDataTbVal(dataTable, row, "Title popup");
            String contentDetail = SessionData.getDataTbVal(dataTable, row, "Detail");
            boolean flagAvialableStock = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Flag avialable stock"));
            boolean flagAutoPurchase = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Flag auto purchase"));
            if (flagAvialableStock) {
                String headerContent = SessionData.getDataTbVal(dataTable, row, "Header content");
                String price = SessionData.getDataTbVal(dataTable, row, "Price").replace("@price shipping@", shipping);
                fulfillmentSteps.verifyLabelPopup(price);
                fulfillmentSteps.verifyLabelPopup(headerContent);
                fulfillmentSteps.verifyTotalOrder(shipping);
            }
            fulfillmentSteps.verifyLabelPopup(titlePopup);
            String[] details = contentDetail.split(">");
            for (String val : details) {
                fulfillmentSteps.verifyLabelPopup(val);

            }
            String labelPay = "Pay now".equals(pay) ? "Pay now" : "Complete order";
            if (flagAvialableStock) {
                fulfillmentSteps.clickButton(labelPay);
            } else if (flagAutoPurchase) {
                fulfillmentSteps.checkAutoPurchase("Auto purchase enough item to fulfill");
                fulfillmentSteps.clickButton("Checkout");
                fulfillmentSteps.clickButton(labelPay);
            } else {
                fulfillmentSteps.verifyVisibleElement("Checkout");
            }
        }
    }

    @And("verify Balance after cancel order fulfill for user paylater")
    public void verifyBalanceAfterCancelOrderFulfillForUserPaylater() {
        float afterBalance = fulfillmentSteps.getBalance();
        assertThat(afterBalance).isEqualTo(balance);
    }

    @And("verify info of {string} tab with order")
    public void verifyInfoOfTabWithOrder(String tabName) {
        fulfillmentSteps.clickTab(tabName);
        switch (tabName) {
            case "Fulfilled":
                fulfillmentSteps.verifyFulfillmentTab("File a claim");
                break;
            case "Processing":
            case "Awaiting stock":
                fulfillmentSteps.verifyProcessingAndAwaitingStockTab("Cancel fulfillment");
                break;
            case "Need mapping":
                fulfillmentSteps.verifyNeedMappingTab();
                break;
            case "To fulfill":
                fulfillmentSteps.verifyToFulfillTab();
                break;
        }
    }

    @And("cancel fulfillment order in Fulfill order page")
    public void cancelFulfillmentOrderInFulfillOrderPage() {
        fulfillmentSteps.cancelFulfillmentOrder();
    }

    @Then("Click button fulfill and fulfill order in fulfillment")
    public void clickButtonFulfillAndFulfillOrderInFulfillment() {
        orderSteps.fulfillOrderPlusBase();
        fulfillmentSteps.fulfillOrderInWarehouse(orderNumber);
    }

    @And("Verify status order")
    public void verifyStatusOrder(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String status = SessionData.getDataTbVal(data, row, "Status");
            String label = SessionData.getDataTbVal(data, row, "Lable filter");
            fulfillmentSteps.verifyStatusOrderInWarehouse(status, orderNumber, label);
        }

    }

    @And("Search and verify tag line item product in fulfillment")
    public void searchAndVerifyTagLineItemProductInFulfillment(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String textTagLineItem = SessionData.getDataTbVal(data, row, "Text input tag line item");
            String status = SessionData.getDataTbVal(data, row, "Status filter");
            fulfillmentSteps.searchOrder(orderNumber);
            fulfillmentSteps.filterLineItemTag(textTagLineItem,status);
            fulfillmentSteps.verifyProductIsExit();
        }
    }


    @And("Verify line item tags doesn't contain in fulfillment")
    public void verifyLineItemTagsDoesnTContainInFulfillment(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String status = SessionData.getDataTbVal(data, row, "Status filter");
            String msg= SessionData.getDataTbVal(data, row, "Messenger");
            fulfillmentSteps.searchOrder(orderNumber);
            fulfillmentSteps.filterLineItemTagDoesntContain(status);
            fulfillmentSteps.verifyProductUnavailable(msg);
        }
    }

    @And("Search order in fulfillment order")
    public void searchOrderInFulfillmentOrder() {
        fulfillmentSteps.searchOrder(orderNumber);
    }

    @And("Add tag to line item order have status need mapping")
    public void addTagToLineItemOrderHaveStatusNeedMapping(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String textInput = SessionData.getDataTbVal(data, row, "Text input tag line item");
            fulfillmentSteps.selectOrLineItemInWarehouse(textInput);
        }
    }

    @Given("verify order {string} in Fulfill order page after {string} order")
    public void verify_order_in_Fulfill_order_page_after_order(String order, String action) {
        fulfillmentSteps.searchOrder(order);
        fulfillmentSteps.clearFilterData();
        if (action.equals("hold")){
            fulfillmentSteps.verifyShowOrder(order, false);
        }
        if (action.equals("release")){
            fulfillmentSteps.verifyShowOrder(order, true);
        }
    }
}
