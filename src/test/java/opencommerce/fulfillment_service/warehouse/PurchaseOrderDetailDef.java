package opencommerce.fulfillment_service.warehouse;

import com.opencommerce.shopbase.ProductVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.InventoryListStep;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.PurchaseOrderDetailSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestListStep;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static com.opencommerce.shopbase.ProductVariable.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PurchaseOrderDetailDef {
    @Steps
    PurchaseOrderDetailSteps purchaseOrderDetailSteps;
    @Steps
    InventoryListStep inventoryListStep;
    RequestListStep requestListStep;

    String PO = LoadObject.getProperty("PO");


    @And("Acc page {string}")
    public void accPage(String page) {
        purchaseOrderDetailSteps.accToPage(page);

    }

    @And("Click on tab {string}")
    public void clickOnTab(String tab) {
        status = tab;
        purchaseOrderDetailSteps.accToTab(tab);


    }

    @Then("Search purchase order and Verify info purchase order")
    public void searchPurchaseOrderAndVerifyInfoPurchaseOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            String purchaseOrder = purchaseOrderCurrent.substring(2);
            int number = Integer.parseInt(purchaseOrder) + 1;
            if (number < 10) {
                purchaseNumber = "PO000" + number;

            }
            else if (number >= 10 && number < 100) {
                purchaseNumber = "PO00" + number;

            }
            else if (number >= 100 && number < 1000) {
                purchaseNumber = "PO0" + number;

            } else {
                purchaseNumber = "PO" + number;
            }
            switch (tab) {
                case "Incoming":
                    status = tab;
                    purchaseOrderDetailSteps.inputPurchaseOrder(purchaseNumber);
                    break;
                case "Canceled":
                    status =tab ;
                    purchaseOrderDetailSteps.inputPurchaseOrder(purchaseOrderCurrent);
                    break;
                default:
                    purchaseNumber = purchaseOrderDetailSteps.getTextPurchaseNumber();
            }

            purchaseNumber = purchaseOrderDetailSteps.verifyListInfoPO("name");
            productName = purchaseOrderDetailSteps.verifyListInfoPO("product-name");
            quantity = purchaseOrderDetailSteps.verifyListInfoPO("total-quantity");
            expectedDateOfArrival = DateTimeUtil.addDays(createdDate, estimateDelivery, "MMM dd, yyyy");
            expectedDateOfArrival = purchaseOrderDetailSteps.verifyListInfoPO("expected-date-of-arrival");
            totalAmount = purchaseOrderDetailSteps.verifyListInfoPO("total-price");
        }
    }

    @And("Click on Purchase Order Number")
    public void clickOnPurchaseOrderNumber() {
        purchaseOrderDetailSteps.clickPurchaseOrderNumber("name");
    }

    @Then("Verify info page PO detail")
    public void verifyLinkPageInvoiceDetail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant>unitPrice>subTotalariant");
            linkUrl = SessionData.getDataTbVal(dataTable, row, "Link");
            purchaseNumber = purchaseOrderDetailSteps.getTextPurchaseOrder();
            status = purchaseOrderDetailSteps.getStatus();
            purchaseOrderDetailSteps.verifyInfoVariant(variant);
            quantity = purchaseOrderDetailSteps.getQuantityOrderTotal();
            totalAmount = purchaseOrderDetailSteps.getTotalOrder();
//            purchaseOrderDetailSteps.clickSourceURL();
//            purchaseOrderDetailSteps.verifyLinkSourUrl(linkUrl);

        }
    }

    @Then("Verify page invoice detail")
    public void clickOnViewInvoiceAndVerifyLinkPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "page");
            String amount = SessionData.getDataTbVal(dataTable, row, "amount");
            String type = SessionData.getDataTbVal(dataTable, row, "type");
            String content = SessionData.getDataTbVal(dataTable, row, "content");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            page = purchaseOrderDetailSteps.verifyTextInvoiceDetail();
            purchaseOrderDetailSteps.verifyInfoInvoiceDetail(amount, type, content, status);


        }
    }

    @Then("Search purchase order and Verify info purchase order in stock to warehouse")
    public void searchPurchaseOrderAndVerifyInfoPurchaseOrderInStockToWarehouse(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            totalItemTabOnTheWay = SessionData.getDataTbVal(dataTable, row, "Total items");
            subTotalTabOnTheWay = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            String actualDateOfArrival = SessionData.getDataTbVal(dataTable, row, "Actual Date Of Arrival");
            String expectedDateOfArrival = SessionData.getDataTbVal(dataTable, row, "Actual Date Of Arrival");
            purchaseOrderDetailSteps.inputPurchaseOrder(condition);
            purchaseNumber = purchaseOrderDetailSteps.verifyListInfoPO("name");
            productName = purchaseOrderDetailSteps.verifyListInfoPO("product-name");
            totalItemTabOnTheWay = purchaseOrderDetailSteps.verifyListInfoPO("total-quantity");
            expectedDateOfArrival = purchaseOrderDetailSteps.verifyListInfoPO("expected-date-of-arrival");
            actualDateOfArrival = purchaseOrderDetailSteps.verifyListInfoPO("date-done");
            subTotalTabOnTheWay = purchaseOrderDetailSteps.verifyListInfoPO("total-price");
        }


    }

    @And("get purchase order")
    public void getPurchaseOrder() {
        purchaseOrderCurrent = purchaseOrderDetailSteps.getTextPurchaseNumber();
    }

    @And("Click view invoice detail")
    public void clickViewInvoiceDetail() {
        purchaseOrderDetailSteps.clickBTViewinvoice();
    }

    @Then("Search purchase order and verify info purchase orders")
    public void searchPurchaseOrderAndVerifyInfoPurchaseOrders(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            totalItemTabOnTheWay = SessionData.getDataTbVal(dataTable, row, "Total items");
            subTotalTabOnTheWay = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            expectedDateOfArrival = SessionData.getDataTbVal(dataTable, row, "Expected Date Of Arrival");
            if(!condition.isEmpty()) {
                purchaseOrderDetailSteps.inputPurchaseOrder(PO);
            }
            purchaseNumber = purchaseOrderDetailSteps.verifyListInfoPO("name");
            productName = purchaseOrderDetailSteps.verifyListInfoPO("product-name");
            totalItemTabOnTheWay = purchaseOrderDetailSteps.verifyListInfoPO("total-quantity");
            expectedDateOfArrival = purchaseOrderDetailSteps.verifyListInfoPO("expected-date-of-arrival");
            subTotalTabOnTheWay = purchaseOrderDetailSteps.verifyListInfoPO("total-price");


        }
    }

    @Then("Verify info page PO detail tab")
    public void verifyInfoPageInvoiceDetailTab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant>unitPrice>subTotalariant");
            linkUrl = SessionData.getDataTbVal(dataTable, row, "Link");
            purchaseNumber = purchaseOrderDetailSteps.getTextPurchaseOrder();
            status = purchaseOrderDetailSteps.getStatus();
            purchaseOrderDetailSteps.verifyInfoVariant(variant);
            totalItemTabOnTheWay = purchaseOrderDetailSteps.getQuantityOrderTotal();
            subTotalTabOnTheWay = purchaseOrderDetailSteps.getTotalOrder();
            purchaseOrderDetailSteps.clickSourceURL();
            purchaseOrderDetailSteps.verifyLinkSourUrl(linkUrl);
        }
    }

    @And("get name PO create of product {string}")
    public void getNamePOCreateOfProduct(String product) {
        inventoryListStep.switchTab("Purchase orders");
        purchaseOrderDetailSteps.searchProductInPurchase(product);
        numberPurchase = purchaseOrderDetailSteps.getPOINName();
    }

    HashMap<String, Integer> listitemProduct = new HashMap<>();
    @Given("get item inventory of product inventory")
    public void getItemInventoryOfProductInventory(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            inventoryListStep.searchProductImport(product);
            listitemProduct = purchaseOrderDetailSteps.getValueInventory();
        }
    }

    @And("verify item inventory of product inventory after purchase")
    public void  verifyItemInventoryOfProductInventoryAfterPurchase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            inventoryListStep.searchProductImport(product);
            HashMap<String, Integer> infoProduct = purchaseOrderDetailSteps.getValueInventory();
            int quatity = purchaseOrderDetailSteps.getQuatityVariant(variant);
            purchaseOrderDetailSteps.verifyValueProduct(infoProduct, listitemProduct, quatity);
        }
    }

    @And("navigate to Balance page")
    public void navigateToBalancePage() {
        purchaseOrderDetailSteps.accToBalance();
    }

    @And("Get balance infomation")
    public void getBalanceInfomation() {

    }

    @Then("get purchase order after get tracking number")
    public void getPurchaseOrderAfterGetTrackingNumber() {
        purchaseOrderCurrent = purchaseOrderDetailSteps.getTextPurchaseNumberAfterGetTN();
    }
}
