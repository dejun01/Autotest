package opencommerce.apps.printhub.orders;

import static com.opencommerce.shopbase.OrderVariable.*;
//import static opencommerce.apps.printhub.orders.campaign.campaignDef.campaignName;
import static org.assertj.core.api.Assertions.assertThat;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment.MyOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.ImportOrderByAPISteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.ManageOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.payments.PaymentsSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.PurchaseOrderDetailSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import common.utilities.DateTimeUtil;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.apps.crosspanda.fulfilment.MyOrdersDef;
import org.junit.Assert;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageOrdersDef {
    // Variable Manage Order detail
    public static float float_product_cost = 0.00f;
    public static float float_shipping = 0.00f;
    public static float float_tax = 0.00f;

    public static float float_totalCost_charge = 0.00f;
    public static float float_totalCost = 0.00f;
    public static int suplier_Total = 0;
    public static String order_Status = "";
    //***************//
    @Steps
    PurchaseOrderDetailSteps purchaseOrderDetailSteps;
    @Steps
    ImportOrderByAPISteps importOrderByAPISteps;
    @Steps
    ManageOrdersSteps manageOrdersSteps;
    @Steps
    CampaignSteps campaignSteps;

    @Steps
    MyOrdersSteps myOrdersSteps;

    @Steps
    PaymentsSteps paymentsSteps;

    @Steps
    OrderSteps orderSteps;

    String orderName = "";
    @Steps
    FulfillmentSteps fulfillmentSteps;

    public static DecimalFormat df = new DecimalFormat("0.00");

    @Given("^verify information order in Print Hub as1 \"([^\"]*)\"$")
    public void verify_detail_orders_as1(String dataKey, List<List<String>> dataTable) {
        String Tabs[] = null;
        int i = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String s_quantitySup = SessionData.getDataTbVal(dataTable, row, "Quantity supplier");
            String s_productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String s_priceShip = SessionData.getDataTbVal(dataTable, row, "Shipping price");
            String s_tax = SessionData.getDataTbVal(dataTable, row, "Tax");
            float ex_productCost = 0.00f, ex_priceShip = 0.00f, ex_tax = 0.00f, ex_totalCost = 0.00f;
            float ac_productCost = 0.00f, ac_priceShip = 0.00f, ac_tax = 0.00f, ac_totalCost = 0.00f;
            campaignSteps.waitUntilInVisibleLoadingTable();

            order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
            ac_productCost = Float.parseFloat(df.format(manageOrdersSteps.getProductCost(orderNumber)));
            System.out.println("ac_product cost " + ac_productCost);
            ac_priceShip = manageOrdersSteps.getShipping(orderNumber);
            System.out.println("ac_priceShip " + ac_priceShip);
            ac_tax = manageOrdersSteps.getTax(orderNumber);
            System.out.println("ac_tax " + ac_tax);
            if (!s_productCost.isEmpty()) {
                ex_productCost = Float.parseFloat(s_productCost);
                assertThat(ac_productCost).isEqualTo(ex_productCost);
            }
            if (!s_priceShip.isEmpty()) {
                ex_priceShip = Float.parseFloat(s_priceShip);
                manageOrdersSteps.veifyPriceShip(ex_priceShip, orderNumber);
            }
            if (!s_tax.isEmpty()) {
                ex_tax = Float.parseFloat(s_tax);
                assertThat(ac_tax).isEqualTo(ex_tax);
            }
            ex_totalCost = ac_productCost + ac_priceShip + ac_tax;
            ac_totalCost = manageOrdersSteps.getTotalCost(orderNumber);
            if (!status.isEmpty())
                Assert.assertEquals(order_Status, status);
            assertThat(ac_totalCost).isEqualTo(ex_totalCost);
        }
    }

    @Given("^verify information order in Print Hub as \"([^\"]*)\"$")
    public void verify_detail_orders_as(String dataKey, List<List<String>> dataTable) {
        String Tabs[] = null;
        int i = 0;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "Campaign");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String s_productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String s_shipping = SessionData.getDataTbVal(dataTable, row, "Shipping price");
            Float ac_productCost, ac_priceShip, ac_tax = 0.00f, ac_totalCost = 0.00f;
            Float ex_totalCost = 0.00f, ex_productCost = 0.00f, ex_priceShip = 0.00f;
            campaignSteps.waitUntilInVisibleLoadingTable();
            ac_productCost = Float.parseFloat(df.format(manageOrdersSteps.getProductCost(orderNumber)));
            ac_priceShip = Float.parseFloat(df.format(manageOrdersSteps.getShipping(orderNumber)));
            order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
            ac_tax = Float.parseFloat(df.format(manageOrdersSteps.getTax(orderNumber)));
            ex_totalCost = Float.parseFloat(df.format(ac_productCost + ac_priceShip + ac_tax));
            ac_totalCost = Float.parseFloat(df.format(manageOrdersSteps.getTotalCost(orderNumber)));
            if (!status.isEmpty())
                Assert.assertEquals(order_Status, status);
            if (!s_productCost.isEmpty()) {
                ex_productCost = Float.parseFloat(s_productCost);
//                Assert.assertEquals(ac_productCost, ex_productCost);
                assertThat(ac_productCost).isGreaterThan(0);
            }
            if (!s_shipping.isEmpty()) {
                ex_priceShip = Float.parseFloat(s_shipping);
//                Assert.assertEquals(ex_priceShip, ac_priceShip);
                assertThat(ac_productCost).isGreaterThan(0);
            }
            Assert.assertEquals(ex_totalCost, ac_totalCost);

        }
    }

    @Given("^verify information lineitems of orders with status \"([^\"]*)\" Print Hub as \"([^\"]*)\"$")
    public void verify_information_of_orders_as(String statusOrder, String dataKey, List<List<String>> dataTable) {
        manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String statusLine = SessionData.getDataTbVal(dataTable, row, "Status line");
            String lineitems = SessionData.getDataTbVal(dataTable, row, "Lineitems");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            boolean isSync = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is sync"));
            if (productName.matches("@(.*)@")) {
                String campaignName = null;
                productName = campaignName;
            }
            order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
            if (!statusOrder.isEmpty())
                Assert.assertEquals(order_Status, statusOrder);
            if (!lineitems.isEmpty()) {
                manageOrdersSteps.verifyLineitems(productName, lineitems, isSync);
                if (isSync == true) {
                    manageOrdersSteps.verifySKUOrderPhub(productName, lineitems, sku);
                    manageOrdersSteps.verifyStatusLineItem(statusLine, productName, lineitems);
                }
            }
        }
    }

    @And("^open order detail on admin shop$")
    public void openOrderDetailOnAdminShop() {
        manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
        manageOrdersSteps.waitABit(3000);
    }

    @And("^open order detail on admin Pbase$")
    public void openOrderDetailOnAdminPbase() {
        manageOrdersSteps.open_Order_Detail_Pbase(orderNumber);
        manageOrdersSteps.waitABit(3000);
    }

    @And("^search order Print Hub$")
    public void searchOrderPrintHub() {
        manageOrdersSteps.searchOrderPhub(orderNumber);
        campaignSteps.waitUntilInVisibleLoadingTable();
    }

    @And("^switch to \"([^\"]*)\" tab on Manage Orders of Print Hub$")
    public void switchToTabOnManageOrdersOfPrintHub(String tabName) {
        manageOrdersSteps.switchToNameTab(tabName);

    }

    @And("^switch to tab on Manage Orders of Print Hub as \"([^\"]*)\"$")
    public void switchToTabOnManageOrdersOfPrintHub(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            if (!tab.isEmpty()) {
                switchToTabOnManageOrdersOfPrintHub(tab);
                campaignSteps.waitUntilInVisibleLoadingTable();
            }
        }
    }

    @And("^click to button in order detail as \"([^\"]*)\"$")
    public void clickToButton(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String btn = SessionData.getDataTbVal(dataTable, row, "Button");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            openOrderDetailOnAdminShop();
            manageOrdersSteps.waitABit(3000);
            campaignSteps.waitUntilLoadingSuccess();
            manageOrdersSteps.clickToOrderDetailButton(product, btn);
            manageOrdersSteps.waitABit(5000);
            campaignSteps.waitUntilInVisibleLoadingTable();
        }
    }

    @And("^get order detail in Manage order Phub$")
    public void getOrderDetailInManageOrder() {
        manageOrdersSteps.waitABit(3000);
        order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
        float_product_cost = manageOrdersSteps.getProductCost(orderNumber);
        float_shipping = manageOrdersSteps.getShipping(orderNumber);
        float_totalCost_charge = float_product_cost + float_shipping;
        float_totalCost = manageOrdersSteps.getTotalCost(orderNumber);
        suplier_Total = manageOrdersSteps.getSupplier(orderNumber);
    }

    @And("^get order detail in Manage order Phub as \"([^\"]*)\"$")
    public void getTotalCostAndSuplierOfOrderPrintHubInDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String s_product_cost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            manageOrdersSteps.waitABit(3000);
            order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
            float_product_cost = manageOrdersSteps.getProductCost(orderNumber);
            if (!s_product_cost.isEmpty()) {
                float productCost = Float.parseFloat(s_product_cost);
                assertThat(float_product_cost).isEqualTo(productCost);
            }

            float_shipping = manageOrdersSteps.getShipping(orderNumber);
            float_totalCost_charge = float_product_cost + float_shipping;
            float_totalCost = manageOrdersSteps.getTotalCost(orderNumber);
            suplier_Total = manageOrdersSteps.getSupplier(orderNumber);
        }
    }

    @And("^get order detail in Phub's order")
    public void getTotalCostAndSuplierOfOrderPrintHub() {
        manageOrdersSteps.waitABit(3000);
        order_Status = manageOrdersSteps.getStatusOrder(orderNumber);
        float_product_cost = manageOrdersSteps.getProductCost(orderNumber);
        float_shipping = manageOrdersSteps.getShipping(orderNumber);
        float_totalCost_charge = float_product_cost + float_shipping;
        float_totalCost = manageOrdersSteps.getTotalCost(orderNumber);
        suplier_Total = manageOrdersSteps.getSupplier(orderNumber);
    }

    @And("^get order detail in Manage order PBase as \"([^\"]*)\"$")
    public void getTotalCostAndSuplierOfOrderPBaseInDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String s_campaign = SessionData.getDataTbVal(dataTable, row, "Campaign");
            String s_product_cost = SessionData.getDataTbVal(dataTable, row, "Product base");
            String s_shipping = SessionData.getDataTbVal(dataTable, row, "Shipping price");
            String s_qty = SessionData.getDataTbVal(dataTable, row, "Shipping price");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            Float shippingPrcie = Float.parseFloat(s_shipping);
        }
    }

    @And("^Mapping product in order$")
    public void mappingProductInOrder() {
        manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
        manageOrdersSteps.clickMappingProduct();
    }

    @Then("^verify order detail after mapped or charge$")
    public void verifyOrderDetailAfterMappedOrCharge(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            String variants = SessionData.getDataTbVal(dataTable, row, "Variants");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String cost = SessionData.getDataTbVal(dataTable, row, "Cost");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String shipping = SessionData.getDataTbVal(dataTable, row, "Shipping");
            String designFee = SessionData.getDataTbVal(dataTable, row, "Design fee");
            String totalCost = SessionData.getDataTbVal(dataTable, row, "Total Cost");
            float actTotalCost = 0;

            myOrdersSteps.refreshPage();
            if (!tab.isEmpty()) {
                manageOrdersSteps.switchToNameTab(tab);
                manageOrdersSteps.searchOrderPhub(orderNumber);
            }
            manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
            if (!variants.isEmpty()) {
                manageOrdersSteps.verifyVariantOrderPhub(variants);
            }
            manageOrdersSteps.verifySKUOrderPhub(sku);
            manageOrdersSteps.verifyStatusOrderPhub(status);
            if (row == 1) {
                manageOrdersSteps.verifyCostOrderPhub(cost);
            } else {
                manageOrdersSteps.verifyCostByItem(baseProduct, cost);
            }
            if (!productCost.isEmpty())
                manageOrdersSteps.verifyFieldOrder("Product cost:", productCost);
            if (!shipping.isEmpty())
                manageOrdersSteps.verifyFieldOrder("Shipping:", shipping);
            if (!designFee.isEmpty())
                manageOrdersSteps.verifyFieldOrder("Design fee:", designFee);
            Float actProductCost = manageOrdersSteps.getInfOrder(orderNumber, "Product cost");
            Float actShippingCost = manageOrdersSteps.getInfOrder(orderNumber, "Shipping");
            Float design_fee = manageOrdersSteps.getInfOrder(orderNumber, "Tax");
            if (!designFee.isEmpty()) {
                actTotalCost = actProductCost + actShippingCost + design_fee;
            } else {
                actTotalCost = actProductCost + actShippingCost;
            }
            manageOrdersSteps.verifyTotalCost(orderSteps.roundTwoDecimalPlaces(actTotalCost));
        }
    }

    @And("^Click edit mapping in order$")
    public void clickEditMappingInOrder() {
        manageOrdersSteps.clickEditMappingProduct();
    }

    @Then("verify order detail")
    public void verifyOrderDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String profit = SessionData.getDataTbVal(dataTable, row, "Profit");
            String variants = SessionData.getDataTbVal(dataTable, row, "Variants");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String cost = SessionData.getDataTbVal(dataTable, row, "Cost");
            String shipping = SessionData.getDataTbVal(dataTable, row, "Shipping");

            manageOrdersSteps.searchAndSelectOrder(orderNumber);
            if (!variants.isEmpty())
                manageOrdersSteps.verifyVariantOrderPhub(variants);
            manageOrdersSteps.verifySKUOrderPhub(sku);
            manageOrdersSteps.verifyCostOrderPhub(cost);
            manageOrdersSteps.verifyFieldOrder("Shipping:", shipping);
            manageOrdersSteps.verifyProfit(profit);
        }
    }

    @And("call api with action in order phub as {string}")
    public void callApiWithActionInOrderPhubAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String supId = SessionData.getDataTbVal(dataTable, row, "SupId");
            String accessToken = manageOrdersSteps.getAccessTokenShopBase();
            Integer orderId = paymentsSteps.getOrderIdAPI(orderNumber, accessToken);
            List<Integer> orderIds = new ArrayList<>();
            orderIds.add(orderId);
            manageOrdersSteps.changeActionInOrderPhubWithAPI(orderIds, action, supId, accessToken);
        }
    }

    @Given("import order to PrintHub")
    public void import_order_to_PrintHub(io.cucumber.datatable.DataTable dataTable) {
    }

    @Given("pay for order import phub")
    public void pay_for_order_import_phub(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String xOrderName = SessionData.getDataTbVal(dataTable, row, "Order number");
            String orderName = "";
            orderName = MyOrdersDef.getOrderName(xOrderName);
            manageOrdersSteps.searchAndSelectOrder(orderName);
            manageOrdersSteps.clickBtnActionOrderImportPhub();
            manageOrdersSteps.selectPayForSelectedOrderImportPhub();
            manageOrdersSteps.clickConfirmPaymentOrderImportPhub();
        }
    }

    @Given("get total cost of order as {string}")
    public void get_total_cost_of_order_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            manageOrdersSteps.switchToNameTab(tab);
            manageOrdersSteps.searchOrderPhub(orderNumber);
            manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
            Float actProductCost = manageOrdersSteps.getProductCost(orderNumber);
            Float actShippingCost = manageOrdersSteps.getShipping(orderNumber);
            totalCostOrderPhub = actProductCost + actShippingCost;
        }
    }

    @Then("Import order printHub by file CSV")
    public void importOrderPrintHubByFileCSV(List<List<String>> dataTable) throws IOException {
        StringBuilder data = new StringBuilder();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
        }
        orderName = manageOrdersSteps.randomOrderName();
        manageOrdersSteps.importOrder(data.toString(), orderName);
    }

    @And("Verify order in order list and verify lineItems in order")
    public void verifyOrderInOrderListAndVerifyLineItemsInOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            String date = SessionData.getDataTbVal(dataTable, row, "date");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String lineitems = SessionData.getDataTbVal(dataTable, row, "Lineitems");
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String totalCost = SessionData.getDataTbVal(dataTable, row, "Total Cost");
            purchaseOrderDetailSteps.accToTab(tab);
            importOrderByAPISteps.searchOrder(orderName);
            if (!date.isEmpty()) {
                date = DateTimeUtil.getDateFormat();
            }
            importOrderByAPISteps.verifyInforOrderInOrderList(orderName, date, status);
            importOrderByAPISteps.clickOrderName(orderName);
            if (!lineitems.isEmpty()) {
                manageOrdersSteps.verifyLineitemsOrder(productName, lineitems, sku, totalCost);
            }
        }
    }

    @Then("Verify display error {string}")
    public void verifyDisplayError(String error) {
        manageOrdersSteps.displayError(error);
    }

    @And("Import order printHub by file CSV fail")
    public void importOrderPrintHubByFileCSVFail(List<List<String>> dataTable) throws IOException {
        StringBuilder data = new StringBuilder();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
        }
        orderName = manageOrdersSteps.randomOrderName();
        manageOrdersSteps.importOrderFail(data.toString(), orderName);
    }

    @Then("Verify not display order printHub {string}")
    public void verifyNotDisplayOrderPrintHub(String noti, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tab = SessionData.getDataTbVal(dataTable, row, "tab");
            purchaseOrderDetailSteps.accToTab(tab);
            importOrderByAPISteps.searchOrder(orderName);
            importOrderByAPISteps.verifyNotData(noti);
        }
    }

    @And("Search order in tab {string}")
    public void searchOrderInTab(String tab) {
        purchaseOrderDetailSteps.accToTab(tab);
        importOrderByAPISteps.searchOrder(orderNumber);
    }

    @And("Payment order printHub in tab {string}")
    public void paymentOrderPrintHubInTab(String tab) {
        manageOrdersSteps.chooseAction(orderName, "Pay for selected orders", "Confirm payment");
    }

    @And("Delete order")
    public void deleteOrder() {
        manageOrdersSteps.chooseAction(orderName, "Delete selected orders", "Delete");
    }

    @And("verify order shipping profile")
    public void verifyOrderShippingProfile(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String orderName = "Order list".equals(SessionData.getDataTbVal(data, row, "Screen")) ? orderNumber : orderNameImport;
            boolean flag = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Dont Support address"));
            String error = SessionData.getDataTbVal(data, row, "Error");
            String message = SessionData.getDataTbVal(data, row, "Message");
            manageOrdersSteps.verifyOrderTab("All", error, message, orderName, flag);
            String tabName = flag ? "In Review" : "Awaiting Payment";
            manageOrdersSteps.verifyOrderTab(tabName, error, message, orderName, flag);
        }
    }

    String orderNameImport = "";

    @And("Import order printHub has shipping profile")
    public void importOrderPrintHubHasShippingProfile(List<List<String>> dataTable) throws IOException {
        StringBuilder data = new StringBuilder();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            data.append("\n").append(SessionData.getDataTbVal(dataTable, row, "Data"));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String time = formatter.format(date);
        orderNameImport = "HN_" + time;
        manageOrdersSteps.importOrder(data.toString(), orderNameImport);
    }

    @And("^move to (Print Hub) screen$")
    public void moveToPrintHubScreen(String name) {
        manageOrdersSteps.moveToPrintHubScreen(name);
    }

    @And("access {string} screen")
    public void accessScreen(String tabName) {
        manageOrdersSteps.accessScreen(tabName);
    }

    @And("^verify info payment (first|second) charge$")
    public void verifyInfoPaymentFirstCharge(String type, List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String buttonView = SessionData.getDataTbVal(data, row, "Button view");
            String outstandingBalance = SessionData.getDataTbVal(data, row, "Outstanding balance");
            String nextPayment = SessionData.getDataTbVal(data, row, "Next Payment");
            String feeType = SessionData.getDataTbVal(data, row, "Fee type");
            String details = SessionData.getDataTbVal(data, row, "Details");
            String amount = SessionData.getDataTbVal(data, row, "Amount (USD)");
            String status = SessionData.getDataTbVal(data, row, "Status");
            boolean flagActivateBalance = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Flag activate balance"));
            boolean flag = "first".equals(type) ? false : true;
            manageOrdersSteps.verifyButton(buttonView, flag);
            String expoutstandingBalance = flagActivateBalance ? outstandingBalance : convertToString(Float.parseFloat(outstandingBalance.replace("$", "")), outstandingDefault);
            manageOrdersSteps.verifyPriceCharge("Outstanding balance", expoutstandingBalance);
            manageOrdersSteps.verifyPriceCharge("Next Payment", nextPayment);
            if (!flag) {
                manageOrdersSteps.clickButton(buttonView);
            } else {
                manageOrdersSteps.verifyStatusOrderCharge(details, status, amount);
                manageOrdersSteps.moveToDetailPayment(details);
            }
            manageOrdersSteps.verifyInfoCharge(feeType, details, amount, orderNumber);
        }
    }

    @And("verify order import in phub")
    public void verifyOrderImportInPhub() {
        importOrderByAPISteps.verifyOrder(orderNumber);
    }

    @And("vefiry status order after pay now")
    public void vefiryStatusOrderAfterPayNow(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String details = SessionData.getDataTbVal(data, row, "Details");
            String amount = SessionData.getDataTbVal(data, row, "Amount (USD)");
            String status = SessionData.getDataTbVal(data, row, "Status");
            manageOrdersSteps.clickButton("Pay now");
            manageOrdersSteps.verifyStatusOrderCharge(details, status, amount);
        }
    }

    float outstandingDefault = 0;

    @And("get outstanding balance default")
    public void getOutstandingBalanceDefault() {
        outstandingDefault = manageOrdersSteps.getOutstandingBalanceDefault("Outstanding balance");
    }

    public String convertToString(Float val1, Float val2) {
        float exp = val1 + val2;
        return Float.toString(manageOrdersSteps.roundTwoDecimalPlaces(exp));
    }

    @And("verify info order with")
    public void verifyInfoOrderInTab(List<List<String>> data) {
        String total = "";
        boolean isShow = true;
        manageOrdersSteps.openOrderDetail(orderNumber);
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String status = SessionData.getDataTbVal(data, row, "Status");
            total = SessionData.getDataTbVal(data, row, "Total");
            isShow = Boolean.parseBoolean(SessionData.getDataTbVal(data, row, "Is show"));
            manageOrdersSteps.verifyLineInOrder(product, status, isShow);
        }
        manageOrdersSteps.verifyTotalOrder(orderNumber, total, isShow);
    }

    @And("^search order Print Hub in tab \"([^\"]*)\"$")
    public void searchOrderPrintHubInTab(String status) {
        manageOrdersSteps.searchOrderPhubInTabAll(status);
    }

    @And("Search and select order in order list")
    public void searchAndSelectOrderInOrderList() {
        orderSteps.searchOrderInOrderList();
    }

    @And("Verify order has been edited")
    public void verifyOrderHasBeenEdited() {
        manageOrdersSteps.verifyOrderHasBeenEdited();
    }

    @Then("Buy more products after updating order")
    public void buyMoreProductsAfterUpdatingOrder() {
        orderSteps.byMoreItem();
    }

    @And("^user \"([^\"]*)\" order in tab \"([^\"]*)\"$")
    public void userOrderInTab(String act, String status) {
        switchToTabOnManageOrdersOfPrintHub(status);
        manageOrdersSteps.searchOrderPhub(orderNumber);
        manageOrdersSteps.open_Order_Detail_Phub(orderNumber);
        manageOrdersSteps.holdOrderPhub(act);
        manageOrdersSteps.veriryStatusOrder(status);

    }
}
