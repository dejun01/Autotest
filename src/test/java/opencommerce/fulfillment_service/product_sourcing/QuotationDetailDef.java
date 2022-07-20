package opencommerce.fulfillment_service.product_sourcing;

import com.opencommerce.odoo.steps.OdooSteps;
import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.ProductVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.FulfillmentSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.QuotationDetailSteps;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestListStep;
import com.opencommerce.shopbase.dashboard.fulfillment_service.steps.RequestSourceProductStep;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import opencommerce.balance.BalanceDef;

import static org.assertj.core.api.Assertions.assertThat;
import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.*;

import java.util.List;

public class QuotationDetailDef {
    String SO1 = LoadObject.getProperty("SO1");
    String SO2 = LoadObject.getProperty("SO2");

    @Steps
    OdooSteps odooSteps;
    @Steps
    QuotationDetailSteps quotationDetailSteps;
    @Steps
    RequestListStep requestListStep;
    @Steps
    RequestSourceProductStep requestSourceProductStep;
    @Steps
    FulfillmentSteps fulfillmentSteps;


    String totalCurrentAvailable = "";

    @Given("^select quotation (the first id|the second id)$")
    public void select_quotation_id(String quotation) {
        switch (quotation) {
            case "the first id":
                quotationDetailSteps.selectQuotation(SO1);
                quotationDetailSteps.clickBTAction(SO1);
                break;
            case "the second id":
                quotationDetailSteps.selectQuotation(SO2);
                quotationDetailSteps.clickBTAction(SO2);
                break;
        }

    }

    @Given("input ordered quantity for variants")
    public void input_ordered_quantity_for_variants(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variant = SessionData.getDataTbVal(dataTable, row, "Variant>quantity");
            ProductVariable.productName = SessionData.getDataTbVal(dataTable, row, "Product Name");
            quotationDetailSteps.waitABit(5);
            quotationDetailSteps.inputVariant(variant);

        }
    }
    @And("cancel quotation after request")
    public void cancelQuotation() {
        quotationDetailSteps.cancelQuotation();
    }

    @Given("purchase stock")
    public void purchase_stock() {

    }

    @Given("verify invoice is created after purchase stock")
    public void verify_invoice_is_created_after_purchase_stock(io.cucumber.datatable.DataTable dataTable) {

    }

    @Then("verify PO is created on Sbase after purchase stock")
    public void verify_PO_is_created_on_Sbase_after_purchase_stock(io.cucumber.datatable.DataTable dataTable) {

    }


    @And("verify information of Purchase order after input ordered quantity")
    public void verifyInformationOfPurchaseOrderAfterInputOrderedQuantity(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variant = SessionData.getDataTbVal(dataTable, row, "Variant>unitPrice>subTotal");
            quantity = SessionData.getDataTbVal(dataTable, row, "Total items");
            String totalBaseCost = SessionData.getDataTbVal(dataTable, row, "Total baseCost");
            totalAmount = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            quotationDetailSteps.verifyInfoQuotation(variant, quantity, totalBaseCost, totalAmount);

        }
    }


    @And("Created PO")
    public void createdPO() {
        quotationDetailSteps.clickBTPay("Purchase");
        quotationDetailSteps.clickBTPay("Pay now");
    }

    @And("Click view history")
    public void clickViewHistory() {
        quotationDetailSteps.clickBTViewHistory();
    }

    @Then("Verify Invoice status")
    public void verifyInvoiceStatus(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            quotationDetailSteps.verifyInvoice(amount, status);
        }
    }

    @When("^search quotation in Request list quotation$")
    public void search_quotation_in_Request_list_quotation$(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String quotationId = SessionData.getDataTbVal(dataTable, row, "Quotation ID");
            Boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            String productUrl = SessionData.getDataTbVal(dataTable, row, "Product URL");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");

            if (quotationId.contains("@")) {
                OrderVariable.quotationID = requestSourceProductStep.getTheFirstQuotation();
                quotationId = OrderVariable.quotationID;
            }
            switch (quotationId) {
                case "many line price":
                    quotationId = LoadObject.getProperty("quotation1");
                    break;
                case "shipping standard":
                    quotationId = LoadObject.getProperty("quotation2");
                    break;
                case "shipping electronic":
                    quotationId = LoadObject.getProperty("quotation3");
            }
            if (!productUrl.isEmpty()) {
                requestListStep.searchQuotationId(productUrl);
                quotationId = requestListStep.getQuotationId();
                OrderVariable.quotationID = quotationId;
            }
            if (!productName.isEmpty()) {
                requestListStep.searchQuotationId(productName);
            }
            if (!quotationId.isEmpty()) {
                requestListStep.searchQuotationId(quotationId);
            }
            requestListStep.closeCripsChat();
            if (isShow) {
                requestListStep.verifyQuotationShow();
                if (!quotationId.isEmpty()) {
                    requestListStep.goToQuotationDetail(quotationId);
                }
            } else {
                requestListStep.verifyQuotationNotShow();
            }
        }
    }

    @Then("verify quotation detail")
    public void verify_quotation_detail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameProduct = SessionData.getDataTbVal(dataTable, row, "Name product");
            String priceRange = SessionData.getDataTbVal(dataTable, row, "Quantity-Price");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String purchaseOrderLeadTime = SessionData.getDataTbVal(dataTable, row, "Purchase order lead time");
            String expirationDate = SessionData.getDataTbVal(dataTable, row, "Expiration date");
            boolean expired = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Expired"));

            if (!nameProduct.isEmpty()) {
                quotationDetailSteps.verifyNameProductInQuotation(nameProduct);
            }
            quotationDetailSteps.verifyPriceRange(variant, priceRange);
            if (!purchaseOrderLeadTime.isEmpty()) {
                quotationDetailSteps.verifyPurchaseOrderLeadTime(purchaseOrderLeadTime);
            }
            if (!expirationDate.isEmpty()) {
                if (expirationDate.contains("@")) {
                    expirationDate = DateTimeUtil.getTodayByFormat("MMM dd, yyyy");
                }
                quotationDetailSteps.verifyExpirationDate(expirationDate);
            }
            if (expired) {
                quotationDetailSteps.verifyNotiQuotationDetailInExpired();
                quotationDetailSteps.verifyNotShowPurchaseOrder();
            }
        }
    }

    @Given("verify variant are disabled in quotation detail")
    public void verify_variant_are_disabled_in_quotation_detail(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            boolean isDisabled = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is disabled"));

        }

    }

    @Then("verify shipping method default")
    public void verify_shipping_method_default(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shippingMethod = SessionData.getDataTbVal(dataTable, row, "Shipping method");
            quotationDetailSteps.verifyShippingMethodDefault(shippingMethod);
        }
    }

    @Then("open popup select shipping method in quotation detail")
    public void open_popup_select_shipping_method_in_quotation_detail() {
        quotationDetailSteps.openPopupSelectShippingMethod();
    }

    @Then("verify shipping method on popup Select shipping method")
    public void verify_shipping_method_on_popup_Select_shipping_method(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String estimatedDeliveryTime = SessionData.getDataTbVal(dataTable, row, "Estimated delivery time");
            String shippingCompany = SessionData.getDataTbVal(dataTable, row, "Shipping company");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            if (!variant.isEmpty()) {
                quotationDetailSteps.selectVariant(variant);
            }
            if (!country.isEmpty()) {
                quotationDetailSteps.selectCountryInPopupShippingMethod(country);
                quotationDetailSteps.waitABit(2000);
            }
            if (isShow) {
                quotationDetailSteps.verifyEstimatedDeliveryTime(estimatedDeliveryTime, shippingCompany);
            } else {
                quotationDetailSteps.verifyShippingMethodNotShow(shippingCompany);
            }
        }
    }

    @And("Verify balance total current available")
    public void verifyBalanceTotalCurrentAvailable() {
        totalCurrentAvailable = roundOff(BalanceDef.currentBalanceAmt - Double.parseDouble(totalAmount.replace("US $", "").trim()));
    }

    private String roundOff(double d) {
        return String.format("%1.2f", d);
    }

    @And("select tab {string} in quotation detail")
    public void selectTabInQuotationDetail(String name) {
        quotationDetailSteps.clickStockProducts(name);
    }

    @And("verify information variant in Fulfill order current")
    public void verifyInformationVariantInFulfillOrderCurrent(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            variant = SessionData.getDataTbVal(dataTable, row, "Variant>unitPrice>subTotal");
            String totalItems = SessionData.getDataTbVal(dataTable, row, "Total items");
            String totalBaseCost = SessionData.getDataTbVal(dataTable, row, "Total baseCost");
            totalAmount = SessionData.getDataTbVal(dataTable, row, "Subtotal");
            String quantity = SessionData.getDataTbVal(dataTable, row, "QuantityOrderUnfulfilled");
            quotationDetailSteps.verifyQuantityUnfulfilled(quantity);
            quotationDetailSteps.verifyInfoQuotation(variant, totalItems, totalBaseCost, totalAmount);
        }
    }

    @Given("get ordered quantity in Fulfill currder orders tab")
    public void get_ordered_quantity_in_Fulfill_currder_orders_tab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            int orderedQuantity = quotationDetailSteps.getOrderedQuantity(variant);
            countStatusInventoryAfter.add(orderedQuantity);
        }
    }

    @And("Verify info in review tab")
    public void verifyInfoInReviewTab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String variant = SessionData.getDataTbVal(dataTable, row, "WAREHOUSE ITEM>QUANTITY>COST");
            String total = SessionData.getDataTbVal(dataTable, row, "TOTAL");
            String itemWilBeFulfilled = SessionData.getDataTbVal(dataTable, row, "items wil be fulfilled");
            String itemCannotBeFulfilled = SessionData.getDataTbVal(dataTable, row, "item can't be fulfilled");
            boolean autoPurchase = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Auto purchase"));
            String fulfill = SessionData.getDataTbVal(dataTable, row, "Fulfill");
            quotationDetailSteps.verifyDisplayTextErr(itemWilBeFulfilled, itemCannotBeFulfilled);
            quotationDetailSteps.verifyInfoWarehouseItems(variant, total);
            if (autoPurchase) {
                quotationDetailSteps.clickAutoPurchase();
            }
            quotationDetailSteps.clickCheckout();
            if(!fulfill.isEmpty()){
                quotationDetailSteps.clickBT("Pay now");
                quotationDetailSteps.waitABit(5000);
            }
        }
    }

    @And("Verify info in Make a payment tab")
    public void verifyInfoInMakeAPaymentTab(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String chargeTotal = SessionData.getDataTbVal(dataTable, row, "Charge shipping & purchasing order ShopBase Fulfillment");
            String text = SessionData.getDataTbVal(dataTable, row, "Charge shipping & purchasing order PlusHub");
            String shippingFee = SessionData.getDataTbVal(dataTable, row, "Shipping fee");
            String productCost = SessionData.getDataTbVal(dataTable, row, "Product cost");
            String total = SessionData.getDataTbVal(dataTable, row, "Total");
            float actProductCost = Float.parseFloat(productCost.replace("$", ""));
            float expChagreTotal = shippingFeeSBFF + actProductCost;
            shippingFee = "$" + shippingFeeSBFF;
            total = text;
            quotationDetailSteps.verifyInfoInTabMakePayment(text, shippingFee, productCost, total);
        }
        quotationDetailSteps.clickBT("Pay now");
        quotationDetailSteps.waitABit(5000);
    }
    @Then("Verify SO display status {string}")
    public void verifySODisplayStatus(String text) {
        assertThat(quotationDetailSteps.getStatus(text)).isEqualTo(text);
    }

    @And("user renew quotation")
    public void userRenewQuotation() {
        quotationDetailSteps.clickRenewRequest();
        quotationDetailSteps.clickBtnConfirm();
    }

    @Then("verify undisplay tab Fulfill current orders in quotation detail")
    public void verify_undisplay_tab_Fulfill_current_orders_in_quotation_detail() {
        quotationDetailSteps.refresh();
        quotationDetailSteps.verifyUndisplayTabFulfillCurrentOrder();
    }
    @And("user request update quotation")
    public void userRequestUpdateQuotation(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String reason = SessionData.getDataTbVal(dataTable, row, "reason");
            quotationDetailSteps.clickBtnRequestUpdate();
            quotationDetailSteps.selectRequest();
            quotationDetailSteps.enterReason(reason);
            quotationDetailSteps.clickBtnConfirm();
        }
    }

    @Then("clear data fulfill current order")
    public void clear_data_fulfill_current_order() {
        if(quotationDetailSteps.showTabFulfullCurrent()){
            quotationDetailSteps.clickBT("Fulfill now");
            quotationDetailSteps.clickAutoPurchase();
            quotationDetailSteps.clickCheckout();
            if(quotationDetailSteps.isNotEnoughMoney()){
                quotationDetailSteps.clickBT("Top up");
                quotationDetailSteps.clickBT("Confirm top up");
            }
            quotationDetailSteps.clickBT("Pay now");
            quotationDetailSteps.waitABit(6000);
        }
    }

    @Then("Verify SO display Price USD {string}")
    public void verifySODisplayPrice( String text ) {
        assertThat(quotationDetailSteps.getPriceVariant()).isEqualTo(text);
    }

}



