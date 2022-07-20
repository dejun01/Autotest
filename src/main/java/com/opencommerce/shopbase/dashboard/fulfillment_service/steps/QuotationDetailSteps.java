package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.PurchaseOrderDetailPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.QuotationDetailPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;
import static com.opencommerce.shopbase.ProductVariable.*;


public class QuotationDetailSteps extends ScenarioSteps {

    QuotationDetailPage quotationDetailPage;
    PurchaseOrderDetailPage purchaseOrderDetailPage;

    @Step
    public void selectQuotation(String quotation) {
        quotationDetailPage.selectQuotation(quotation);
    }


    @Step
    public void clickBTAction(String qID) {
        quotationDetailPage.clickBTAction(qID);

    }
    public void cancelQuotation() {
        quotationDetailPage.cancelQuotation();
    }
    @Step
    public void verifyInfoQuotation(String variant, String totalItems, String totalBaseCost, String subtotal) {
        String[] listVariant = variant.split(";");
        for (String variantInfo : listVariant) {
            verifyVariantInfo(variantInfo);
        }
        assertThat(quotationDetailPage.getTotalOrderedQuantity()).isEqualTo(totalItems);
        assertThat(quotationDetailPage.getTotalBaseCost()).isEqualTo(totalBaseCost);
        assertThat(quotationDetailPage.getTotalSubTotal()).isEqualTo(subtotal);
    }

    private void verifyVariantInfo(String variantInfo) {
        String[] listSub = variantInfo.split(">");
        variantList.add(listSub[0]);
        int index = quotationDetailPage.getRowOfVariant(listSub[0]);
        assertThat(quotationDetailPage.verifyInfoUnitPriceRow(index)).isEqualTo(listSub[1]);
        assertThat(quotationDetailPage.verifyInfoSubTotalRow(index)).isEqualTo(listSub[2]);
    }

    @Step
    public void inputVariant(String variant) {
        String[] listVariant = variant.split(";");
        for (String variantQuantity : listVariant)
            EnterInputVariant(variantQuantity);

    }

    private void EnterInputVariant(String variantQuantity) {
        String[] listQuantity = variantQuantity.split(">");
        int indexVariant = quotationDetailPage.getRowOfVariant(listQuantity[0]);
        quotationDetailPage.inputVariant(listQuantity[1], indexVariant);
    }

    @Step
    public void clickBTPay(String pay) {
        quotationDetailPage.clickBTPay(pay);
    }

    @Step
    public void clickBTViewHistory() {
        quotationDetailPage.clickBTViewHistory();

    }

    @Step
    public void verifyInvoice(String amount, String status) {
        assertThat(purchaseOrderDetailPage.verifyInfoInvoiceDetail(4)).isEqualTo(amount);
        assertThat(purchaseOrderDetailPage.verifyInfoInvoiceDetail(5)).isEqualTo(status);

    }

    @Step
    public void verifyNameProductInQuotation(String nameProduct) {
        String actName = quotationDetailPage.getNameProductQuotation();
        assertThat(actName).isEqualTo(nameProduct);
    }

    @Step
    public void selectVariant(String variant) {
        String[] option = variant.split("/");
        for (int i = 0; i < option.length; i++) {
            quotationDetailPage.clickOptionValue(option[i]);
        }
    }

    @Step
    public void verifyPriceRange(String variant, String priceRange) {
        if (variant.contains(",")) {
            String[] variants = variant.split(",");
            for (String itemVariant : variants) {
                selectVariant(itemVariant);
            }
        } else {
            selectVariant(variant);
        }
        if (priceRange.contains(";")) {
            String[] listPriceRange = priceRange.split(";");
            for (String itemPrice : listPriceRange) {
                String[] property = itemPrice.split("-");
                String act = quotationDetailPage.getPriceInQuantity(property[0]);
                assertThat(act).isEqualTo(property[1]);
            }
        } else {
            String[] property = priceRange.split("-");
            String act = quotationDetailPage.getPriceInQuantity(property[0]);
            assertThat(act).isEqualTo(property[1]);
        }
    }

    @Step
    public void verifyPurchaseOrderLeadTime(String purchaseOrderLeadTime) {
        String actPurchaseOrderLeadTime = quotationDetailPage.getPurchaseOrderLeadTime();
        assertThat(actPurchaseOrderLeadTime).isEqualTo(purchaseOrderLeadTime);
    }

    @Step
    public void verifyExpirationDate(String expirationDate) {
        String act = quotationDetailPage.getExpirationDate();
        assertThat(act).isEqualTo(expirationDate);
    }

    @Step
    public void verifyNotiQuotationDetailInExpired() {
        quotationDetailPage.verifyNotiQuotationDetailInExpired();
    }

    @Step
    public void verifyNotShowPurchaseOrder() {
        quotationDetailPage.verifyNotShowPurchaseOrder();
    }

    @Step
    public void verifyShippingMethodDefault(String shippingMethod) {
        String act = quotationDetailPage.getShippingMethodDefault();
        assertThat(act).contains(shippingMethod);
    }

    @Step
    public void openPopupSelectShippingMethod() {
        quotationDetailPage.openPopupSelectShippingMethod();
    }

    @Step
    public int getColInPopupShippingMethod(String nameCol) {
        return quotationDetailPage.getColInPopupShippingMethod(nameCol);
    }

    @Step
    public void selectCountryInPopupShippingMethod(String country) {
        quotationDetailPage.clickSelectCountryInPopupShippingMethod();
        quotationDetailPage.selectOptionCountryInPopupShippingMethod(country);
        quotationDetailPage.clickLinkTextWithLabel("Estimated shipping fee");
    }

    @Step
    public void verifyEstimatedDeliveryTime(String estimatedDeliveryTime, String shippingCompany) {
        int col = getColInPopupShippingMethod("Estimated delivery time");
        String act = quotationDetailPage.getEstimatedDeliveryTime(shippingCompany, col);
        assertThat(act).isEqualTo(estimatedDeliveryTime);
    }

    @Step
    public void verifyCostShippingMethodInPopup(String cost, String shippingCompany) {
        int col = getColInPopupShippingMethod("Cost");
        String act = quotationDetailPage.getCostShippingMethodInPopup(shippingCompany, col);
        assertThat(act).isEqualTo(cost);
    }

    @Step
    public void clickStockproduct() {
        quotationDetailPage.clickStockproduct();
    }

    @Step
    public void verifyShippingMethodNotShow(String shippingCompany) {
        quotationDetailPage.verifyShippingMethodNotShow(shippingCompany);

    }

    @Step
    public void clickStockProducts(String name) {
        quotationDetailPage.clickOnTab(name);
    }

    public void verifyDisplayTextErr(String itemWilBeFulfilled, String itemCannotBeFulfilled) {
        assertThat(quotationDetailPage.getErrWillBeFulfilled()).isEqualTo("" + itemWilBeFulfilled + "/" + itemCannotBeFulfilled + " line items will be fulfilled");
        assertThat(quotationDetailPage.getErrLineItemsCanNotFulfill()).isEqualTo("" + itemCannotBeFulfilled + "/" + itemCannotBeFulfilled + " line items can not be fulfilled due to insufficient inventory");
    }

    public void verifyInfoWarehouseItems(String variant, String total) {
        String[] listVariant = variant.split(";");
        for (String variantInfo : listVariant) {
            verifyVariantInfoInReviewTab(variantInfo);
        }
        assertThat(quotationDetailPage.getTotal()).isEqualTo(total);
    }

    private void verifyVariantInfoInReviewTab(String variantInfo) {
        String[] subVariant = variantInfo.split(">");
        variantList.add(subVariant[0]);
        assertThat(quotationDetailPage.getInfoQuantitiRow(subVariant[0])).isEqualTo(subVariant[1]);
        assertThat(quotationDetailPage.verifyInfoSubCostRow(subVariant[0])).isEqualTo(subVariant[2]);

    }

    public void clickAutoPurchase() {
        quotationDetailPage.hoverToTextOther();
        quotationDetailPage.clickInput("Auto purchase enough item to fulfill");
    }

    public void clickCheckout() {
        quotationDetailPage.clickBtn("Checkout");
    }

    public void verifyInfoInTabMakePayment(String text, String shippingFee, String productCost, String total) {
        assertThat(quotationDetailPage.getTotalInfo("Charge shipping & purchasing order PlusHub")).isEqualTo(text);
        assertThat(quotationDetailPage.getTotalInfo("Shipping fee")).isEqualTo(shippingFee);
        assertThat(quotationDetailPage.getTotalInfo("Product cost")).isEqualTo(productCost);
        assertThat(quotationDetailPage.getTotalInfo("Total")).isEqualTo(total);
    }

    public void clickBT(String pay_now) {
        quotationDetailPage.clickBtn(pay_now);
    }

    public void verifyQuantityUnfulfilled(String quantity) {
        assertThat(quotationDetailPage.getQuantityOrderUnfulfilled()).isEqualTo("You have" + " " + quantity + " " + "unfulfilled orders with this products.");
    }


    public int getOrderedQuantity(String variant) {
        return quotationDetailPage.getOrderedQuantity(variant);
    }

    public void verifyUndisplayTabFulfillCurrentOrder() {
        quotationDetailPage.waitForPageLoad();
        quotationDetailPage.verifyUndisplayTabFulfillCurrentOrder();
    }
    public void clickBtnRequestUpdate() {
        quotationDetailPage.clickBtn("Request update");
    }

    public void selectRequest() {
        quotationDetailPage.selectRequest();
    }

    public void enterReason(String reason) {
        quotationDetailPage.enterInputFieldWithLabel("Please specify your reason",reason);
    }

    public void clickBtnConfirm() {
        quotationDetailPage.clickBtn("Confirm");
    }
    public void clickRenewRequest() {
        quotationDetailPage.clickBtn("Renew request");
    }

    public String getStatus(String text) {
        return quotationDetailPage.getStatus(text);
    }

    public boolean showTabFulfullCurrent() {
        return quotationDetailPage.showTabFulfullCurrent();
    }

    public boolean isNotEnoughMoney() {
        return quotationDetailPage.isNotEnoughMoney();
    }

    public void refresh() {
        quotationDetailPage.refreshPage();
    }

    public String getPriceVariant(){
        return quotationDetailPage.getPriceVariantQuotation();
    }
}

