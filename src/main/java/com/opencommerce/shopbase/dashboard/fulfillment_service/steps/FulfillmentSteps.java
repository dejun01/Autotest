package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.FulfillmentPage;
import com.opencommerce.shopbase.dashboard.orders.pages.OrderPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class FulfillmentSteps extends ScenarioSteps {
    FulfillmentPage fulfillmentPage;
    OrderPage orderPage;


    @Step
    public void clickButton(String buttonName) {
        fulfillmentPage.clickButton(buttonName);
        fulfillmentPage.waitForEverythingComplete(10);
    }

    @Step
    public void searchOrderName(String orderNumber) {
        fulfillmentPage.searchOrderName(orderNumber);
    }

    public void verifyFulfillmentSuccess() {
        fulfillmentPage.verifyFulfillmentSuccess("We received your fulfillment request");
    }

    public void verifyStatusOrderAtList(String status) {
        fulfillmentPage.verifyStatusOrderAtList(status);
    }

    public void clickOrderName(String orderNumber) {
        fulfillmentPage.clickLinkTextWithLabel(orderNumber);
    }

    public void verifyOrderDetail(String orderName, int number, String status) {
        fulfillmentPage.verifyOrderName(orderName);
        fulfillmentPage.verifyFulfillmentStatus(status);
        fulfillmentPage.verifyNumberPackageFulfill(number);
    }

    public void clickOut() {
        fulfillmentPage.clickOut();
    }

    public void verifyTurnOffAutoPurchase(String notification, String buttonName) {
        fulfillmentPage.clickAutoPurchase(false);
        fulfillmentPage.choiceOrderFulfill();
        fulfillmentPage.clickBtn(buttonName);
        fulfillmentPage.verifyNotification(notification);
    }

    public void searchProduct(String product) {
        fulfillmentPage.searchProduct(product);
    }

    public String getValue(String label) {
        return fulfillmentPage.getValue(label);
    }

    public HashMap<String, Integer> getValueInventory() {
        HashMap<String, Integer> data = new HashMap<>();
        int count = fulfillmentPage.countcol();
        for (int i = 3; i <= 11; i++) {
            data.put(fulfillmentPage.getLabelInCol(i), fulfillmentPage.getValueInCol(i));
        }
        return data;
    }

    public void verifyLabelPopup(String label) {
        fulfillmentPage.verifyTextPresent(label, true);
    }

    public void redirectToHomeShopbase() {
        fulfillmentPage.redirectToHomeShopbase();
    }

    public float getBalance() {
        fulfillmentPage.refreshPage();
        fulfillmentPage.clickAvatar();
        return fulfillmentPage.getBalance();
    }

    public void choiceShopFulfill(String label) {
        fulfillmentPage.clickChoiceStore();
        fulfillmentPage.checkCheckboxWithLabel(label, true);
    }

    public void verifyStatusOrderList(String status, String orderName) {
        fulfillmentPage.verifyStatusOrderList(status, orderName);
        fulfillmentPage.focusOut("FULFILLMENT STATUS");
        fulfillmentPage.choiceOrder(orderName);
    }

    public void switchToTheLastestTabOfFulfillmentService() {
        fulfillmentPage.switchToLatestTab();
    }

    public void clickShopbaseFulfillment(String label) {
        fulfillmentPage.clickShopbaseFulfillment(label);
    }

    public void logoutToWithUrl() {
        fulfillmentPage.clickAvatar();
        fulfillmentPage.logout();
        fulfillmentPage.waitForEverythingComplete();
    }

    public void verifyProduct(String product) {
        fulfillmentPage.verifyProduct(product);
    }

    public void filterWithCondition(String title, String condition) {
        fulfillmentPage.clickTitleFilter(title);
        fulfillmentPage.checkCheckboxWithLabelInSBDS(condition);
    }

    public void verifyResultTag(String title, String condition) {
        fulfillmentPage.verifyResultTag(title, condition);
    }

    public void verifyButtonFilter(String name, boolean isBoolean) {
        fulfillmentPage.verifyButtonFilter(name, isBoolean);
    }

    public void saveFilterNormal(String filterName) {
        fulfillmentPage.enterInputFieldWithLabel("Filter name", "");
        fulfillmentPage.clickButton("Save");
        fulfillmentPage.verifyTextPresent("Please input filter name", true);
        fulfillmentPage.enterInputFieldWithLabel("Filter name", filterName);
        fulfillmentPage.clickButton("Save");
        fulfillmentPage.verifyTextPresent("Please input filter name", false);
        fulfillmentPage.verifyFilterName(filterName, true);
        fulfillmentPage.verifyButtonFilter("Edit current filter", true);
        fulfillmentPage.verifyButtonFilter("Add current filter", false);
    }

    public HashMap<String, String> getFilterDefault() {
        return fulfillmentPage.getFilterDefault();
    }

    public HashSet<String> getFilterTemplate() {
        return fulfillmentPage.getFilterTemplate();
    }

    public void clickTab(String filterName) {
        fulfillmentPage.clickTabFulfillOrder(filterName);
    }

    public void clickTabFulfillOrder(String tabName) {
        fulfillmentPage.clickTabFulfillOrder(tabName);
    }

    public void verifyValueInput(String filterName) {
        String name = fulfillmentPage.getValueInput("Filter name");
        assertThat(filterName).isEqualTo(name);
    }

    public void updateFilterTemplate(String oldFilterName) {
        fulfillmentPage.enterInputFieldWithLabel("Filter name", "");
        fulfillmentPage.actionFilter(1);
        fulfillmentPage.verifyTextPresent("Please input filter name", true);
        fulfillmentPage.enterInputFieldWithLabel("Filter name", oldFilterName);
        fulfillmentPage.actionFilter(1);
        fulfillmentPage.verifyTextPresent("Please input filter name", false);
        fulfillmentPage.verifyFilterName(oldFilterName, true);
    }

    public void DeleteFilter(String filterName) {
        fulfillmentPage.actionFilter(2);
        fulfillmentPage.verifyFilterName(filterName, false);
    }

    public void verifyResultFilter(String val, String title) {
        fulfillmentPage.verifyResultFilter(val, title);
    }

    public void showVariantOfProduct(String product) {
        fulfillmentPage.showVariantOfProduct(product);
    }

    public void showPopupReplace() {
        fulfillmentPage.showPopupReplace();
    }

    public void verifyVariantActive(String variant) {
        String[] variantList = variant.split(",");
        for (String row : variantList) {
            String[] item = row.split(":");
            fulfillmentPage.verifyVariantActive(item[0], item[1]);
        }
    }

    public void choiceVariant(String variant) {
        String[] variantList = variant.split(",");
        for (String row : variantList) {
            String[] item = row.split(":");
            fulfillmentPage.choiceVariant(item[0], item[1]);
        }
    }

    public void changeProduct(String product) {
        fulfillmentPage.searchProductReplace(product);
        fulfillmentPage.choiceProduct(product);
    }

    public void verifyShowButton(String label, boolean _isBoolean) {
        fulfillmentPage.verifyShowButton(label, _isBoolean);
    }

    public int getItemOfInventory(String label) {
        return fulfillmentPage.getItemOfInventory(label);
    }

    public int getItemOfVariantInventory(String label, String variant) {
        return fulfillmentPage.getItemOfVariantInventory(label, variant);
    }

    public void moveFulfillmentPage(String label) {
        fulfillmentPage.moveFulfillmentPage(label);
    }

    public void moveTab(String tabName) {
        fulfillmentPage.moveTab(tabName);
    }

    public void searchOrder(String orderNumber) {
        fulfillmentPage.enterInputFieldWithLabel("Search orders", orderNumber);

    }

    @Step
    public void clickBTMapping(String variant) {
        fulfillmentPage.clickBTMapp(variant);
    }

    @Step
    public void verifyNoOrder(String orderNumber) {
        assertThat(fulfillmentPage.getTextNoti()).isEqualTo("Could not find any orders matching" + " " + orderNumber);
    }

    @Step
    public void verifyInfoOrderInTab(String nameTab, String order, String product, String warehouseProduct, String
            quantity, String baseCostPerItem, String error) {
        int i = fulfillmentPage.getListLineItems();
        for (int x = 1; x <= i; x++) {
            if (product.contains(";") && !nameTab.equals("To fulfill")) {
                String[] prodSub = product.split(";");
                assertThat(fulfillmentPage.getProduct("LINE ITEM", x)).isEqualTo(prodSub[x - 1]);
            } else if (!nameTab.equals("To fulfill")) {
                assertThat(fulfillmentPage.getProduct("LINE ITEM", x)).isEqualTo(product);
            }
            switch (nameTab) {
                case "Need mapping":
                    assertThat(fulfillmentPage.getOrder("ORDER", x)).isEqualTo(order);
                    assertThat(fulfillmentPage.getQuantity("QUANTITY", x)).isEqualTo(quantity);
                    break;
                case "To fulfill":
                    assertThat(fulfillmentPage.getProduct("PRODUCT", x)).isEqualTo(product);
                    assertThat(fulfillmentPage.getOrders("ORDER", x)).isEqualTo(order);
                    assertThat(fulfillmentPage.getWarehouseProduct("WAREHOUSE PRODUCT", x)).isEqualTo(warehouseProduct);
                    assertThat(fulfillmentPage.getQuantitys("QUANTITY", x)).isEqualTo(quantity);
                    assertThat(fulfillmentPage.getBaseCostPerItem("BASE COST PER ITEM", x)).isEqualTo(baseCostPerItem);
                    break;
                case "Cannot Fulfill":
                    assertThat(fulfillmentPage.getOrder("ORDER", x)).isEqualTo(order);
                    assertThat(fulfillmentPage.getWarehouseProduct("WAREHOUSE ITEM", x)).isEqualTo(warehouseProduct);
                    assertThat(fulfillmentPage.getQuantity("QUANTITY", x)).isEqualTo(quantity);
                    assertThat(fulfillmentPage.getQuantity("ERROR", x)).isEqualTo(error);
                    break;
                default:
                    assertThat(fulfillmentPage.getOrderA("ORDER", x)).isEqualTo(order);
                    assertThat(fulfillmentPage.getWarehouseProduct("WAREHOUSE ITEM", x)).isEqualTo(warehouseProduct);
                    assertThat(fulfillmentPage.getQuantity("QUANTITY", x)).isEqualTo(quantity);

            }

        }
    }

    public String getcountOrder() {
        String text = fulfillmentPage.getcountOrder().substring(fulfillmentPage.getcountOrder().indexOf("(", 1)).replace(")", "").replace("(", "").trim();
        return text;
    }

    public void verifyItemOfInventory(String label, int number) {
        int actNumber = fulfillmentPage.getItemOfInventory(label);
        assertThat(actNumber).isEqualTo(number);
    }

    public void verifyItemOfVariantInventory(String label, String variant, int number) {
        int actNumber = fulfillmentPage.getItemOfVariantInventory(label, variant);
        assertThat(actNumber).isEqualTo(number);
    }

    public void userMoveToScreen(String tab) {
        fulfillmentPage.userMoveToScreen(tab);
    }

    public void ReplaceAll(boolean isReplaceAll) {
        fulfillmentPage.checkCheckboxWithLabel("Replace for all current orders", isReplaceAll);
    }

    public void refreshPage() {
        fulfillmentPage.refreshPage();
        fulfillmentPage.waitForPageLoad();
    }

    public void selectOrderFulfill() {
        int i = 0;
        waitABit(3000);
        while (fulfillmentPage.isNoOrder() && i < 5){
            refreshPage();
            waitABit(3000);
            i++;
        }
        fulfillmentPage.selectOrderFulfill();
        fulfillmentPage.clickButton("Fulfill selected orders");
    }

    public String getShippingOfOrder(String label) {
        return fulfillmentPage.getShippingOfOrder(label);
    }

    public void checkAutoPurchase(String label) {
        fulfillmentPage.checkCheckboxWithLabel(label, true);
    }

    public void verifyTotalOrder(String shipping) {
        String actShipping = fulfillmentPage.getTotalOnMakePayment();
        assertThat(actShipping).isEqualTo(shipping);
    }

    public void verifyInvoiceDetail(String label, String val) {
        fulfillmentPage.verifyInvoiceDetail(label, val);
    }

    public void verifyVisibleElement(String label) {
        fulfillmentPage.verifyShowButton(label, true);
    }

    public void fulfillOrder() {
        if (fulfillmentPage.isButtonExit("Checkout")) {
            fulfillmentPage.checkCheckboxWithLabel("Auto purchase enough item to fulfill", true);
            fulfillmentPage.clickButton("Checkout");
        }
        fulfillmentPage.clickButton("Pay now");
        fulfillmentPage.waitForTextToAppear("No order");
        fulfillmentPage.refreshPage();
        fulfillmentPage.waitForPageLoad();
        fulfillmentPage.verifySearchOrderDontExist("");
    }

    public void verifyValueWithStatusProcessing(HashMap<String, Integer> act, HashMap<String, Integer> exp, int quantity) {
        for (String header : act.keySet()) {
            switch (header) {
                case "PROCESSING":
                    assertThat(act.get(header)).isEqualTo(exp.get(header) + quantity);
                    break;
                case "AVAILABLE STOCK":
                case "UNFULFILLED":
                    assertThat(act.get(header)).isEqualTo(exp.get(header) - quantity);
                    break;
//                case "NEED TO PURCHASE":
//                    int needToPurchase = act.get("UNFULFILLED") + act.get("AWAITING STOCK") + act.get("INCOMING") - act.get("AVAILABLE STOCK");
//                    needToPurchase = needToPurchase < 0 ? 0 : needToPurchase;
//                    assertThat(act.get(header)).isEqualTo(needToPurchase);
//                    break;
//                case "FULFILLED":
//                    assertThat(act.get(header)).isEqualTo(exp.get(header) + quantity);
//                    break;
                default:
                    assertThat(act.get(header)).isEqualTo(exp.get(header));
                    break;
            }
        }
    }

    public void verifyValueWithStatusWaittingStock(HashMap<String, Integer> act, HashMap<String, Integer> exp, int quantity) {
        for (String header : act.keySet()) {
            switch (header) {
                case "AWAITING STOCK":
                    assertThat(act.get(header)).isEqualTo(exp.get(header) + quantity);
                    break;
                case "PURCHASED":
                case "INCOMING":
                    assertThat(act.get(header)).isEqualTo(exp.get(header));
                    break;
//                case "NEED TO PURCHASE":
//                    int needToPurchase = act.get("UNFULFILLED") + act.get("AWAITING STOCK") - act.get("INCOMING") - act.get("AVAILABLE STOCK");
//                    needToPurchase = needToPurchase < 0 ? 0 : needToPurchase;
//                    assertThat(act.get(header)).isEqualTo(needToPurchase);
//                    break;
                case "UNFULFILLED":
                    assertThat(act.get(header)).isEqualTo(exp.get(header) - quantity);
                    break;
//                case "FULFILLED":
//                    assertThat(act.get(header)).isEqualTo(exp.get(header) + quantity);
//                    break;
            }
        }
    }

    public void verifyValueWithStatusFulfilled(HashMap<String, Integer> act, HashMap<String, Integer> exp, int quantity) {
        for (String header : act.keySet()) {
            switch (header) {
                case "FULFILLED":
                    assertThat(act.get(header)).isEqualTo(exp.get(header) + quantity);
                    break;
            }
        }
    }

    public String getValueInWarehouse(String label) {
        return fulfillmentPage.getValueInWarehouse(label);
    }

    public void clickBTConfirm() {
        fulfillmentPage.clickBtn("Confirm");
    }

    public void verifyAutoDisplayFilterOrderCreatedOver6h() {
        assertThat(fulfillmentPage.getAutoFilterOrder()).isEqualTo("Time since created (hours) >= 6");
    }

    public void turnOffFilterAuto() {
        fulfillmentPage.turnOffFilterAuto();

    }


    public JsonPath getShippingFeeByAPI(String orderNumber) {
        String accessToken = fulfillmentPage.getAccessTokenShopBase();
        String shop = LoadObject.getProperty("shop");
        String api = "https://" + shop + "/admin/panda-fulfillment/fulfillment-info/orders.json?limit=50&page=1&search_keyword=" + orderNumber + "&search_option=order_name&tab=ready_to_fulfill&shop_ids=&tab_name=ready_to_fulfill";
        JsonObject requestParam = new JsonObject();
        requestParam.addProperty("", "");
        return fulfillmentPage.postAPISbase(api, requestParam, accessToken);
    }

    public float getActShippingFee(JsonPath getShippingFeeByAPI) {
        String result = "orders.shipping_fee";
        ArrayList<Float> list =  (ArrayList<Float>) fulfillmentPage.getData(getShippingFeeByAPI, result);
        return list.get(0);
    }

    public void fulfillOrderInWarehouse(String orderNumber) {
        fulfillmentPage.clickCancelFilter();
        fulfillmentPage.tickSelectOrder(orderNumber);
        fulfillmentPage.clickButton("Fulfill selected orders");
        fulfillmentPage.clickButton("Confirm");
    }

    public void searchOrderPlusBase(String orderNumber) {
        fulfillmentPage.searchOrderPlusBase(orderNumber);

    }

    public void verifyStatusOfBtnTrackingNumber(boolean is_disable) {
        fulfillmentPage.verifyStatusOfBtnGetTrackingNumber(is_disable);
    }

    public void verifyFulfillmentTab(String btnName) {
        fulfillmentPage.verifyHeaderTab("ORDER");
        fulfillmentPage.verifyHeaderTab("LINE ITEM");
        fulfillmentPage.verifyHeaderTab("WAREHOUSE ITEM");
        fulfillmentPage.verifyHeaderTab("QUANTITY");
        fulfillmentPage.verifyHeaderTab("SHIPPING");
        fulfillmentPage.verifyHeaderTab("TRACKING NUMBER");
        fulfillmentPage.verifyShowButton(btnName);
    }

    public void verifyProcessingAndAwaitingStockTab(String btnName) {
        fulfillmentPage.verifyHeaderTab("ORDER");
        fulfillmentPage.verifyHeaderTab("LINE ITEM");
        fulfillmentPage.verifyHeaderTab("WAREHOUSE ITEM");
        fulfillmentPage.verifyHeaderTab("QUANTITY");
        fulfillmentPage.verifyHeaderTab("SHIPPING");
        fulfillmentPage.verifyShowButton(btnName);
    }

    public void verifyNeedMappingTab() {
        fulfillmentPage.verifyHeaderTab("ORDER");
        fulfillmentPage.verifyHeaderTab("LINE ITEM");
        fulfillmentPage.verifyHeaderTab("QUANTITY");
        fulfillmentPage.verifyIcon("change-mapping");
        fulfillmentPage.verifyIcon("replace-mapping");
    }

    public void verifyToFulfillTab() {
        fulfillmentPage.verifyHeaderTab("ORDER");
        fulfillmentPage.verifyHeaderTab("PRODUCT");
        fulfillmentPage.verifyHeaderTab("WAREHOUSE PRODUCT");
        fulfillmentPage.verifyHeaderTab("QUANTITY");
        fulfillmentPage.verifyHeaderTab("SHIPPING");
        fulfillmentPage.verifyHeaderTab("BASE COST PER ITEM");
        fulfillmentPage.verifyIcon("change-mapping");
        fulfillmentPage.verifyIcon("replace-mapping");
    }

    public void cancelFulfillmentOrder() {
        fulfillmentPage.cancelFulfillmentOrder();
    }

    public void verifyStatusOrderInWarehouse(String status, String orderNumber, String label) {
        fulfillmentPage.verifyStatusOrderInWarehouse(status, orderNumber, label);
    }

    public void filterLineItemTag(String textTagLineItem, String status) {
        fulfillmentPage.clickButton("More filters");
        fulfillmentPage.inputTextLineItemSearch(textTagLineItem, status);
        fulfillmentPage.clickButton("Done");
    }

    public void verifyProductIsExit() {
        fulfillmentPage.verifyProductIsExit();
    }

    public void filterLineItemTagDoesntContain(String status) {
        fulfillmentPage.clickButton("More filters");
        fulfillmentPage.clickRadioTagLineItem(status);
        fulfillmentPage.clickButton("Done");
    }

    public void verifyProductUnavailable(String msg) {
        fulfillmentPage.verifyProductUnavailable(msg);
    }

    public void selectOrLineItemInWarehouse(String textInput) {
        fulfillmentPage.selectOrderInWarehouse();
        fulfillmentPage.clickButton("Action");
        orderPage.clickAddTagToLineItem();
        orderPage.InputTextTagItem(textInput);
        fulfillmentPage.clickButton("Confirm");
    }

    public void verifyWarehouseProduct(String product, String variant, int index) {
        fulfillmentPage.verifyWarehouseProductName(product);
        fulfillmentPage.verifyWarehouseVariantName(variant);
    }


    public void verifyProductSB(String product, String variant) {
        ArrayList actProduct = fulfillmentPage.getProductSB();
        ArrayList actVariant = fulfillmentPage.getVariantSB();
        assertThat(actProduct).contains(product);
        assertThat(actVariant).contains(variant);
    }

    public int getIndexLineItem(String product, String variant) {
        int index = fulfillmentPage.getIndexLineItem(product, variant);
        return index;
    }

    public void verifyQuantityLineItem(String quantity, int index) {
        String act = fulfillmentPage.getQuantityLineItem(index);
        assertThat(act).isEqualTo(quantity);
    }

    public void verifyShippingLineItem(String shipping, int index) {
        String act = fulfillmentPage.getShippingLineItem(index);
        assertThat(act).isEqualTo(shipping);
    }

    public void verifyBaseCostLineItem(String baseCostPerItem, int index) {
        String act = fulfillmentPage.getBaseCostLineItem(index);
        assertThat(act).isEqualTo(baseCostPerItem);
    }

    public void verifyErrorLineItem(String error, int index) {
    }

    public void verifyTrackingNumberLineItem(String trackingNumber, int index) {
    }

    public void verifyShowOrder(String order, boolean isShow) {
        fulfillmentPage.verifyShowOrder(order, isShow);
    }

    public void clearFilterData() {
        fulfillmentPage.clearFilterDate();
    }
}
