package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing.PurchasedOrdersPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PurchasedOrdersSteps extends ScenarioSteps {
    PurchasedOrdersPages purchasedOrdersPages;

    @Step
    public void searchPO(String po) {
        purchasedOrdersPages.waitForEverythingComplete();
        purchasedOrdersPages.typeAndEnterInputFieldWithLabel("Search by Product name, purchase number", po);
    }

    @Step
    public void verifySearchPO(String po) {
        purchasedOrdersPages.waitLoadInvoice();
        purchasedOrdersPages.verifyShowPO(po);
    }

    @Step
    public void verifyNoData() {
        purchasedOrdersPages.verifyTextPresent("No orders found", true);
        purchasedOrdersPages.verifyTextPresent("No orders matched your search criteria", true);
    }

    @Step
    public static JsonObject requestBodyDeferredPayment(int userId, boolean isDeferredPayment) {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("user_id", userId);
        requestParams.addProperty("status", isDeferredPayment);
        return requestParams;
    }

    String gapi = LoadObject.getProperty("gapi.url");
    String user_id = LoadObject.getProperty("user_id");

    @Step
    public void configUserDeferredPayment(String token, boolean deferredPayment) {
        String api = "" + gapi + "/v1/panda/qctool/update-user-setting?access_token=" + token;
        int userId = Integer.parseInt(user_id);
        JsonObject requestBody = requestBodyDeferredPayment(userId, deferredPayment);
        purchasedOrdersPages.putAPI(api, requestBody);
    }

    @Step
    public void enterQuantityOfProduct(String vaQuan) {
        String[] listVariantQuan = vaQuan.split(";");
        for (String varQuantity : listVariantQuan) {
            enterQuantityOfVariant(varQuantity);
        }
    }

    private void enterQuantityOfVariant(String varQuantity) {
        String value[] = varQuantity.split(">");
        int row = purchasedOrdersPages.getRowOfVariant(value[0]);
        purchasedOrdersPages.enterQuantityOfVariantByIndex(value[1], row);
        purchasedOrdersPages.clickOutFieldInput();
    }

    @Step
    public void purchase() {
        purchasedOrdersPages.clickBtn("Purchase");
        purchasedOrdersPages.waitPurchaseDone();
    }

    @Step
    public void clickBtnPay() {
        if (purchasedOrdersPages.isElementExist(purchasedOrdersPages.xPathBtn("", "Pay", 1))) {
            purchasedOrdersPages.clickBtn("Pay");
            purchasedOrdersPages.waitUntilInvisibleLoading(10);
        }
        purchasedOrdersPages.clickBtn("PAY NOW");
        purchasedOrdersPages.clickBtn("Confirm payment");
        purchasedOrdersPages.waitUntilInvisibleLoading(15);
        purchasedOrdersPages.waitForPageLoad();
    }

    @Step
    public void selectPaymentMethod(String paymentMethod) {
        purchasedOrdersPages.selectPaymentMethod(paymentMethod);
    }

    @Step
    public String getInvoiceName() {
        return purchasedOrdersPages.getInvoiceName();
    }

    @Step
    public String getPO() {
        return purchasedOrdersPages.getNamePO().replace("Payment for ", "");
    }

    @Step
    public String getCreateDate() {
        return purchasedOrdersPages.getCreateDate();
    }

    @Step
    public void verifyPONotCreate(String invoice) {
        purchasedOrdersPages.goPaymentHistoryPage();
        purchasedOrdersPages.verifyPONotCreate(invoice);
    }

    @Step
    public void selectTabPOPage(String tab) {
        purchasedOrdersPages.selectTabPOPage(tab);
    }

    @Step
    public void verifyPurchasedDate(String purchasedDate) {
        String actPurchaseDate = purchasedOrdersPages.getPurchaseDate();
        assertThat(actPurchaseDate).isEqualTo(purchasedDate);
    }

    @Step
    public void verifyNotPO(String purchaseNumber) {
        purchasedOrdersPages.verifyTextPresent("No orders found", true);
        purchasedOrdersPages.verifyTextPresent("No orders matched your search criteria", true);
    }

    @Step
    public void verifyPurchaseNumber(String purchaseNumber) {
        String actPurchaseNumber = purchasedOrdersPages.getPurchaseNumber();
        assertThat(actPurchaseNumber).isEqualTo(purchaseNumber);
    }

    @Step
    public void verifyTotalAmount(String totalAmount) {
        String actTotalAmount = purchasedOrdersPages.getTotalAmount();
        assertThat(actTotalAmount).isEqualTo(totalAmount);
    }

    @Step
    public void searchInvoice(String invoice) {
        purchasedOrdersPages.waitLoadInvoice();
        purchasedOrdersPages.typeAndEnterInputFieldWithLabel("Search by invoice name", invoice);
    }

    @Step
    public void verifyName(String invoice) {
        String actInvoice = purchasedOrdersPages.getNameInPaymentHis();
        assertThat(actInvoice).isEqualTo(invoice);
    }

    @Step
    public void verifyDetail(String po) {
        String actDetail = purchasedOrdersPages.getDetailInPaymentHis();
        assertThat(actDetail).isEqualTo("Payment for " + po);
    }

    @Step
    public void verifyCreate(String created) {
        String actCreated = purchasedOrdersPages.getCreatedInPaymentHis();
        assertThat(actCreated).isEqualTo(created);
    }

    @Step
    public void verifyStatus(String status) {
        String actStatus = purchasedOrdersPages.getStatusInPaymentHis();
        assertThat(actStatus).isEqualTo(status);
    }

    @Step
    public void verifyAmount(String amount) {
        String actAmount = purchasedOrdersPages.getAmountInPaymentHis();
        assertThat(actAmount).isEqualTo(amount);
    }

    @Step
    public void verifyShowPay(String invoice, boolean isPay) {
        purchasedOrdersPages.verifyEnablePay(invoice, isPay);
    }

    public String getNonInvoiceName() {
        waitABit(4000);
        return purchasedOrdersPages.getNonInvoiceName();
    }

    public String getSubTotal() {
        return purchasedOrdersPages.getSubTotal();
    }

    public void selectTypeBank(String payment) {
        purchasedOrdersPages.clickToShowTypeBank();
        purchasedOrdersPages.selectTypeBank(payment);
    }

    public void clickBtnTransfer() {
        purchasedOrdersPages.clickBtn("TRANSFER");
        purchasedOrdersPages.clickBtn("Confirm payment");
        purchasedOrdersPages.waitUntilInvisibleLoading(15);
    }



    public void payWithPaypal() {
        purchasedOrdersPages.clickPayPal();
        System.out.print("title: " + purchasedOrdersPages.getTitle());
//        purchasedOrdersPages.switch("Log in to your PayPal account");
        System.out.print("title1: " + purchasedOrdersPages.getTitle());
        purchasedOrdersPages.enterEmailPayPal();
        purchasedOrdersPages.clickBtnNext();
        purchasedOrdersPages.enterPassPayPal();
        purchasedOrdersPages.clickBtn("Log In");
    }

    public void selectTypeOrder(String typeOrder) {
        purchasedOrdersPages.clickCreateOrder(typeOrder);
    }
}
