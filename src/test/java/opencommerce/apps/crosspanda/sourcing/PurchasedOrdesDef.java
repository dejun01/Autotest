package opencommerce.apps.crosspanda.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.CommonXPandaSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.PurchasedOrdersSteps;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing.RequestListSteps;
import com.opencommerce.odoo.steps.OdooSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.balance.BalanceDef;

import java.util.List;

public class PurchasedOrdesDef {
    @Steps
    PurchasedOrdersSteps purchasedOrdersSteps;
    @Steps
    CommonXPandaSteps commonXPandaSteps;
    @Steps
    private OdooSteps odooSteps;
    @Steps
    RequestListSteps requestListSteps;

    @Then("^search purchased number then verify result$")
    public void search_purchased_number_then_verify_result(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String po = SessionData.getDataTbVal(dataTable, row, "Purchase number");
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            Boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            purchasedOrdersSteps.selectTabPOPage(tab);
            purchasedOrdersSteps.searchPO(po);
            if (isShow) {
                purchasedOrdersSteps.verifySearchPO(po);
            } else {
                purchasedOrdersSteps.verifyNoData();
            }
        }
    }

    @Given("^config user is Deferred Payment as \"([^\"]*)\"$")
    public void config_user_is_Deferred_Payment_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean deferredPayment = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Deferred Payment"));
            String tokenApp = commonXPandaSteps.getAccessTokenXpanda();
            purchasedOrdersSteps.configUserDeferredPayment(tokenApp, deferredPayment);
        }
    }

    @When("^select quotation and purchased quotation as \"([^\"]*)\"$")
    public void select_quotation_and_purchased_quotation_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String quotation = SessionData.getDataTbVal(dataTable, row, "Quotation");
            String vaQuan = SessionData.getDataTbVal(dataTable, row, "Variant>Quantity");
            boolean isPO = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is PO first"));
            boolean isPay = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is pay"));
            String paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            String keyInvoice = SessionData.getDataTbVal(dataTable, row, "Invoice name");
            String keyPO = SessionData.getDataTbVal(dataTable, row, "PO");
            String keyDate = SessionData.getDataTbVal(dataTable, row, "Created date");
            String totalAmount = SessionData.getDataTbVal(dataTable, row, "Total amount");
            String typeOrder = SessionData.getDataTbVal(dataTable, row, "Type order");
            requestListSteps.searchQuotation(quotation);
            requestListSteps.clickView(quotation);

            purchasedOrdersSteps.enterQuantityOfProduct(vaQuan);
            String total = purchasedOrdersSteps.getSubTotal();
            SessionData.saveDataByKey(totalAmount, total);
            purchasedOrdersSteps.purchase();
            purchasedOrdersSteps.selectTypeOrder(typeOrder);

            if (isPO) {
                String invoice = purchasedOrdersSteps.getInvoiceName();
                String createDate = purchasedOrdersSteps.getCreateDate();
                SessionData.addToHashMap(key, keyInvoice, invoice);
                SessionData.addToHashMap(key, keyDate, createDate);
            } else {
                String nonInvoice = purchasedOrdersSteps.getNonInvoiceName();
                SessionData.addToHashMap(key, keyInvoice, nonInvoice);
            }

            String po = purchasedOrdersSteps.getPO();
            SessionData.addToHashMap(key, keyPO, po);

            if (isPay) {
                String[] payments = paymentMethod.split(">");
                switch (payments[0]) {
                    case "MONEY TRANSFER":
                        purchasedOrdersSteps.selectTypeBank(payments[1]);
                        purchasedOrdersSteps.selectPaymentMethod(payments[2]);
                        purchasedOrdersSteps.clickBtnTransfer();
                        break;
                    case "PAYPAL":
                        purchasedOrdersSteps.payWithPaypal();
                        break;
                    case "CREDIT CARD":
                        purchasedOrdersSteps.clickBtnPay();
                        break;
                }
            }
        }
    }

    @Then("^verify infor PO as \"([^\"]*)\"$")
    public void verify_infor_PO_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String tab = SessionData.getDataTbVal(dataTable, row, "Tab");
            boolean isPO = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is PO"));
            String keyPurchasedDate = SessionData.getDataTbVal(dataTable, row, "Purchased date");
            String keyPurchaseNumber = SessionData.getDataTbVal(dataTable, row, "Purchase number");
            String totalAmount = SessionData.getDataTbVal(dataTable, row, "Total amount");

            String purchaseNumber = (String) SessionData.getHashMapValueByKey(key, keyPurchaseNumber);

            purchasedOrdersSteps.selectTabPOPage(tab);
            purchasedOrdersSteps.searchPO(purchaseNumber);
            if (isPO) {
                String purchasedDate = (String) SessionData.getHashMapValueByKey(key, keyPurchasedDate);
                if (keyPurchasedDate == null) {
                    purchasedOrdersSteps.verifyPurchasedDate(purchasedDate);
                }
                purchasedOrdersSteps.verifyPurchaseNumber(purchaseNumber);
                if (!totalAmount.isEmpty()) {
                    String total = (String) SessionData.getDataByKey(totalAmount);
                    purchasedOrdersSteps.verifyTotalAmount(total);
                }
            } else {
                purchasedOrdersSteps.verifyNotPO(purchaseNumber);
            }
        }
    }

    @Then("^verify status invoice PO in Payment history as \"([^\"]*)\"$")
    public void verify_status_invoice_PO_in_Payment_history_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String keyInvoice = SessionData.getDataTbVal(dataTable, row, "Invoice name");
            String keyDetail = SessionData.getDataTbVal(dataTable, row, "Detail");
            String keyCreated = SessionData.getDataTbVal(dataTable, row, "Created");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String amount = SessionData.getDataTbVal(dataTable, row, "Amount");
            boolean isPay = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is pay"));

            String invoice = (String) SessionData.getHashMapValueByKey(key, keyInvoice);
            String po = (String) SessionData.getHashMapValueByKey(key, keyDetail);
            String created = (String) SessionData.getHashMapValueByKey(key, keyCreated);

            purchasedOrdersSteps.searchInvoice(invoice);
            purchasedOrdersSteps.verifyName(invoice);
            purchasedOrdersSteps.verifyDetail(po);
            if (created != null) {
                purchasedOrdersSteps.verifyCreate(created);
            }
            purchasedOrdersSteps.verifyStatus(status);
            if (!amount.isEmpty()) {
                String total = (String) SessionData.getDataByKey(amount);
                purchasedOrdersSteps.verifyAmount(total);
            }
            purchasedOrdersSteps.verifyShowPay(invoice, isPay);
        }
    }


    @Then("^verify PO with name \"([^\"]*)\" on Odoo$")
    public void verify_PO_with_name_on_Odoo(String dataKey, List<List<String>> tableQuotationEmail) throws Throwable {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(tableQuotationEmail, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(tableQuotationEmail, row, "KEY");
            String email = SessionData.getDataTbVal(tableQuotationEmail, row, "Email");
            String PO = SessionData.getDataTbVal(tableQuotationEmail, row, "PO");
            String keyInvoice = SessionData.getDataTbVal(tableQuotationEmail, row, "Invoice name");
            String nameQuotation = LoadObject.getProperty("partPO");
            boolean isSync = Boolean.parseBoolean(SessionData.getDataTbVal(tableQuotationEmail, row, "Is sync"));

            PO = (String) SessionData.getHashMapValueByKey(key, PO);
            String invoice = (String) SessionData.getHashMapValueByKey(key, keyInvoice);
            odooSteps.verifyPurchaseOrder(nameQuotation + invoice + "-" + PO, email, isSync);
        }
    }

    @Then("^approve order and assign onwer for DO on Odoo as \"([^\"]*)\"$")
    public void approve_order_and_assign_onwer_for_DO_on_Odoo_as(String dataKey, List<List<String>> dataTable) throws Throwable {

        for(int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()){
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String PO = SessionData.getDataTbVal(dataTable, row, "PO");
            String keyInvoice = SessionData.getDataTbVal(dataTable, row, "Invoice name");
            String nameQuotation = LoadObject.getProperty("partPO");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            PO = (String) SessionData.getHashMapValueByKey(key, PO);
            String invoice = (String) SessionData.getHashMapValueByKey(key, keyInvoice);
            odooSteps.approveOrder(PO, email);
            odooSteps.assignOwner(PO, email);
        }
    }

    @Given("^verify PO detail on Odoo as \"([^\"]*)\"$")
    public void verify_PO_detail_on_Odoo_as(String dataKey, List<List<String>> dataTable) throws Throwable{

        for(int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            String PO = SessionData.getDataTbVal(dataTable, row, "PO");
            String keyInvoice = SessionData.getDataTbVal(dataTable, row, "Invoice name");
            String nameQuotation = LoadObject.getProperty("partPO");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String productItem = SessionData.getDataTbVal(dataTable, row, "Product");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
//            PO = (String) SessionData.getHashMapValueByKey(key, PO);
//            String invoice = (String) SessionData.getHashMapValueByKey(key, keyInvoice);

            odooSteps.readPODetail(PO, email, productItem, quantity);
        }
    }



}
