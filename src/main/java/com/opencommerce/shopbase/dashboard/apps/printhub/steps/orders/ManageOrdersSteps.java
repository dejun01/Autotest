package com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders;

import com.github.javafaker.Faker;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.fulfillment.MappingProductPages;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.ManageOrdersPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.IOException;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class ManageOrdersSteps extends ScenarioSteps {
    ManageOrdersPage manageOrdersPage;
    MappingProductPages mappingProductPages;

    public void searchOrderPhub(String keyword) {
        manageOrdersPage.searchOrderPhub(keyword);
    }

    public void verifyOrderStatus(String numberOrders, String status) {
        manageOrdersPage.verifyOrderStatus(numberOrders, status);
    }

    public void verifyLineitems(String productName, String lineitems, boolean isSync) {
        manageOrdersPage.verifyLineItems(productName, lineitems, isSync);
    }

    public void verifyTotalCost(float totalCost, String orderName) {
        Float actualTotalCost = manageOrdersPage.getTotalCost(orderName);
        assertThat(actualTotalCost).isEqualTo(totalCost);
    }

    public void verifyProductCost(float productCost, String orderName) {
        Float actualProductCost = manageOrdersPage.getProductCost(orderName);
        assertThat(actualProductCost).isEqualTo(productCost);
    }

    public void verifyTotalCost() {
        manageOrdersPage.verifyTotalCost();
    }

    public void verifyTotalProfit() {
        manageOrdersPage.verifyTotalProfit();
    }

    @Step
    public void open_Order_Detail_Phub(String numberOrders) {
        manageOrdersPage.openOrderDetail(numberOrders);
    }

    @Step
    public void open_Order_Detail_Pbase(String numberOrders) {
        manageOrdersPage.openOrderDetail_pBase(numberOrders);
    }

    public void verifySKUOrderPhub(String productName, String lineitems, String sku) {
        manageOrdersPage.verifySKU(productName, lineitems, sku);
    }

    public void switchToNameTab(String tabName) {
        manageOrdersPage.switchToNameTab(tabName);
    }

    @Step
    public float getTotalCost(String orderName) {
        return manageOrdersPage.getTotalCost(orderName);
    }

    @Step
    public float getInfOrder(String orderName, String field) {
        return manageOrdersPage.getInfOrder(orderName, field);
    }

    @Step
    public int getSupplier(String orderNumber) {
        return manageOrdersPage.getOrderAmount(orderNumber);
    }

    @Step
    public Float getProductCost(String orderNumber) {
        return manageOrdersPage.getProductCost(orderNumber);
    }

    @Step
    public void veifyPriceShip(float ex_priceShip, String orderName) {
        assertThat(ex_priceShip).isEqualTo(manageOrdersPage.getPriceShip(orderName));
    }

    @Step
    public void clickOrderNumber(String numberOrder) {
        manageOrdersPage.clickOrderNumber(numberOrder);
    }

    public void clickToOrderDetailButton(String product, String btn) {
        manageOrdersPage.clickToOrderDetailButton(product, btn);
        if (btn.equalsIgnoreCase("Hold"))
            manageOrdersPage.clickConfirm("Hold");
        if (btn.equalsIgnoreCase("Cancel"))
            manageOrdersPage.clickConfirm("Confirm");
        manageOrdersPage.waitForEverythingComplete();
        manageOrdersPage.waitForPageLoad();
    }

    public String getStatusOrder(String orderNumber) {
        String status = manageOrdersPage.getStatusOrder(orderNumber);
        return status;
    }

    @Step
    public float getValueOrder(String orderName) {
        return manageOrdersPage.getValueOrder(orderName);
    }

    public float getShipping(String orderName) {
        return manageOrdersPage.getPriceShip(orderName);
    }

    public float getTax(String orderName) {
        return manageOrdersPage.getTax(orderName);
    }

    public float getTotalProfit(String orderName) {
        return manageOrdersPage.getTotalProfit(orderName);
    }

    @Step
    public void clickMappingProduct() {
        manageOrdersPage.clickMappingProduct();
        mappingProductPages.clickCheckboxSelectAProduct();
    }

    @Step
    public void clickEditMappingProduct() {
        manageOrdersPage.clickEditMappingProduct();
    }

    @Step
    public void verifyVariantOrderPhub(String variants) {
        manageOrdersPage.verifyVariantOrderPhub(variants);
    }

    @Step
    public void verifySKUOrderPhub(String sku) {
        manageOrdersPage.verifySKU(sku);
    }

    @Step
    public void verifyCostOrderPhub(String cost) {
        manageOrdersPage.verifyCost(cost);
    }

    @Step
    public void verifyCostByItem(String baseProduct, String cost) {
        manageOrdersPage.verifyCostByItem(baseProduct, cost);
    }

    @Step
    public void verifyStatusOrderPhub(String status) {
        manageOrdersPage.verifyStatus(status);
    }

    @Step
    public void verifyFieldOrder(String field, String value) {
        manageOrdersPage.verifyFieldOrder(field, value);
    }

    @Step
    public void verifyProfit(String profit) {
        manageOrdersPage.verifyProfit(profit);
    }

    @Step
    public void verifyTotalCost(Float totalCost) {
        manageOrdersPage.verifyTotalCost(totalCost);
    }

    public void verifyStatusLineItem(String status, String productName, String Lineitems) {
        if (!status.isEmpty()) {
            String ac_Status = manageOrdersPage.getStatusLineItem(productName, Lineitems);
            assertThat(status).isEqualToIgnoringCase(ac_Status);
        }
    }

    @Step
    public void searchAndSelectOrder(String order) {
        manageOrdersPage.searchAndSelectOrder(order);
    }

    public String getAccessTokenShopBase() {
        return manageOrdersPage.getAccessTokenShopBase();
    }

    public void changeActionInOrderPhubWithAPI(List<Integer> orderIDs, String action, String supId, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/phub-order/action.json?access_token=" + accessToken;
        JsonObject requestParam = requestBodyActionInOrderPhub(orderIDs, action, supId);
        manageOrdersPage.postAPI(url, requestParam);
    }

    private JsonObject requestBodyActionInOrderPhub(List<Integer> orderIDs, String action, String supId) {
        JsonObject requestParam = new JsonObject();
        JsonArray products = new JsonArray();
        requestParam.addProperty("action", action);
        requestParam.addProperty("supplier_id", Integer.parseInt(supId));
        requestParam.add("order_ids", products);
        for (int a : orderIDs) {
            products.add(a);
        }
        return requestParam;
    }

    public void clickBtnActionOrderImportPhub() {
        manageOrdersPage.clickBtnActionOrderImportPhub();
    }

    public void selectPayForSelectedOrderImportPhub() {
        manageOrdersPage.selectPayForSelectedOrderImportPhub();
    }

    public void clickConfirmPaymentOrderImportPhub() {
        manageOrdersPage.clickConfirmPaymentOrderImportPhub();
    }

    public void navigateOrderDetailByID(int orderId) {
        String shop = LoadObject.getProperty("shop");
        manageOrdersPage.navigateToUrl("https://" + shop + "/admin/orders/" + orderId);
    }

    @Step
    public String randomOrderName() {
        Faker faker = new Faker();
        return faker.name().name();
    }

    @Step
    public void importOrder(String data, String orderName) throws IOException {
        manageOrdersPage.clickBTAction("Import");
        manageOrdersPage.importOrder(data, orderName);
        manageOrdersPage.clickBTAction("Upload File");
        manageOrdersPage.clickBTAction("Upload File");
        manageOrdersPage.clickBTAction("OK");
    }

    public void verifyLineitemsOrder(String productName, String lineitems, String sku, String totalCost) {
        assertThat(manageOrdersPage.getProduct()).isEqualTo(productName);
        assertThat(manageOrdersPage.getVariantTitle().trim()).isEqualTo(lineitems);
        assertThat(manageOrdersPage.getLineitemsSku().replace("SKU:", "").trim()).isEqualTo(sku);
        assertThat(manageOrdersPage.verifyTotalCostLineItem()).isEqualTo(totalCost);
    }

    public void displayError(String error) {
        assertThat(manageOrdersPage.getTextError()).isEqualTo(error);
    }

    public void importOrderFail(String data, String orderName) throws IOException {
        manageOrdersPage.clickBTAction("Import");
        manageOrdersPage.importOrder(data, orderName);
        manageOrdersPage.clickBTAction("Upload File");
    }

    public void chooseAction(String orderName, String action, String confirm) {
        manageOrdersPage.chooseOrder(orderName);
        manageOrdersPage.clickBtn("Actions");
        manageOrdersPage.clickAction(action);
        manageOrdersPage.clickBtn(confirm);
    }

    public void verifyOrderTab(String tabName, String err, String mess, String orderNumber, boolean flag) {
        manageOrdersPage.verifyTab(tabName);
        manageOrdersPage.openOrderDetail(orderNumber);
        manageOrdersPage.verifyMessageShippingProfile(orderNumber, err, mess, flag);

    }

    public void moveToPrintHubScreen(String name) {
        manageOrdersPage.moveToPrintHubScreen(name);
    }

    public void accessScreen(String tabName) {
        manageOrdersPage.accessScreen(tabName);
    }

    public void verifyButton(String buttonView, boolean flag) {
        manageOrdersPage.verifyButton(buttonView, flag);
    }

    public void verifyPriceCharge(String label, String val) {
        manageOrdersPage.verifyPriceCharge(label, val);
    }

    public void clickButton(String btnName) {
        manageOrdersPage.clickButton(btnName);
    }

    public void verifyInfoCharge(String feeType, String details, String amount, String orderName) {
        manageOrdersPage.verifyInfoCharge("Fee type", feeType);
        manageOrdersPage.verifyInfoCharge("Details", details);
        manageOrdersPage.verifyInfoCharge("Amount (USD)", amount);
        manageOrdersPage.showListOrder();
        manageOrdersPage.verifyTextPresent(orderName, true);
    }

    public void verifyStatusOrderCharge(String details, String status, String amount) {
        manageOrdersPage.verifyStatusOrderCharge(details, status, amount);
    }

    public void moveToDetailPayment(String details) {
        manageOrdersPage.moveToDetailPayment(details);
    }

    public Float getOutstandingBalanceDefault(String label) {
        return manageOrdersPage.getOutstandingBalanceDefault(label);
    }

    public float roundTwoDecimalPlaces(float exp) {
        return manageOrdersPage.roundTwoDecimalPlaces(exp);
    }

    public void openOrderDetail(String orderNumber) {
        manageOrdersPage.openOrderDetail(orderNumber);
    }

    public void verifyTotalOrder(String orderNumber, String total, boolean isShow) {
        manageOrdersPage.verifyTotalOrder(orderNumber, total, isShow);
    }

    public void verifyLineInOrder(String product, String status, boolean isShow) {
        manageOrdersPage.verifyLineInOrder(product, status, isShow);
    }
    @Step
    public void searchOrderPhubInTabAll(String status) {
        manageOrdersPage.clickhInTabAll(status);
        manageOrdersPage.searchOrderPhub(orderNumber);
    }
    @Step
    public void verifyOrderHasBeenEdited() {
        manageOrdersPage.verifyOrderHasBeenEdited();
    }

    public void holdOrderPhub(String act) {
        manageOrdersPage.holdOrderPhub(act);
    }

    public void veriryStatusOrder(String status) {
        manageOrdersPage.veriryStatusOrder(status);
    }
}
