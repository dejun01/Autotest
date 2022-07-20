package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.ProductVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.InventoryListPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.PurchaseOrderDetailPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.RequestListPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.opencommerce.shopbase.OrderVariable.*;

public class PurchaseOrderDetailSteps {
    PurchaseOrderDetailPage purchaseOrderDetailPage;
    RequestListPage requestListPage;

    InventoryListPage inventoryListPage;
    @Steps
    FulfillmentSteps orderStep;

    @Step
    public void clickBTViewinvoice() {
        purchaseOrderDetailPage.clickBTViewinvoice();

    }

    @Step
    public String verifyTextInvoiceDetail() {
        return purchaseOrderDetailPage.getTextInvoiceDetail();
    }

    @Step
    public String getTextPurchaseOrder() {
        return purchaseOrderDetailPage.getTextPurchaseOrder();
    }

    @Step
    public String getStatus() {
        return purchaseOrderDetailPage.getStatus();
    }

    @Step
    public List<String> getListVariant() {
        return purchaseOrderDetailPage.getListVariant();

    }

    @Step
    public String getQuantityOrderTotal() {
        return purchaseOrderDetailPage.getQuantityOrderTotal();
    }

    @Step
    public String getTotalOrder() {
        return purchaseOrderDetailPage.getTotalOrder();
    }

    @Step
    public List<String> getListUnitPrice() {
        return purchaseOrderDetailPage.getListUnitPrice();
    }


    @Step
    public void clickSourceURL() {
        purchaseOrderDetailPage.clickSourceURL();
    }

    @Step
    public String accToPage(String page) {
        return purchaseOrderDetailPage.accToPage(page);
    }

    @Step
    public void accToTab(String tab) {
        purchaseOrderDetailPage.accToTab(tab);

    }

    @Step
    public void inputPurchaseOrder(String purchaseOrderNumber) {
        purchaseOrderDetailPage.inputPurchaseOrder(purchaseOrderNumber);
    }

    @Step
    public List<String> getListPurchaseOrder() {
        return purchaseOrderDetailPage.getListPurchaseOrder();
    }

    @Step
    public void clickPurchaseOrderNumber(String name) {
        purchaseOrderDetailPage.clickPurchaseOrderNumber(name);
    }

    @Step
    public String verifyListInfoPO(String name) {
        return purchaseOrderDetailPage.verifyListInfoPO(name);
    }

    @Step
    public void verifyLinkSourUrl(String url) {
        purchaseOrderDetailPage.verifyLink(url);


    }

    @Step
    public void verifyInfoInvoiceDetail(String amount, String type, String content, String status) {
        assertThat(purchaseOrderDetailPage.verifyInfoInvoiceDetail(1)).isEqualTo(type);
        assertThat(purchaseOrderDetailPage.verifyInfoInvoiceDetail(2)).isEqualTo(content);
        assertThat(purchaseOrderDetailPage.getAmount().trim()).isEqualTo(amount);
        assertThat(purchaseOrderDetailPage.verifyInfoInvoiceDetail(4)).isEqualTo(status);


    }

    @Step
    public String getTextPurchaseNumber() {
        return purchaseOrderDetailPage.getTextPurchaseNumber();
    }

    @Step
    public void verifyInfoVariant(String variant) {
        String[] listVariant = variant.split(";");
        for (String variantInfo : listVariant) {
            verifySubVariant(variantInfo);
        }

    }

    private void verifySubVariant(String variantInfo) {
        String[] listInfo = variantInfo.split(">");
        int index = purchaseOrderDetailPage.getVariant(listInfo[0]);
        assertThat(purchaseOrderDetailPage.verifySubQuantity(index)).isEqualTo(listInfo[1]);
        assertThat(purchaseOrderDetailPage.verifyUnitPrice(index)).isEqualTo(listInfo[2]);
        assertThat(purchaseOrderDetailPage.verifySubTotal(index)).isEqualTo(listInfo[3]);

    }

    @Step
    public void searchProduct(String content) {
        requestListPage.searchProduct(content);
    }

    @Step
    public void getInfoWithProduct(String variant) {
        purchaseOrderDetailPage.clickProductInventory();
        expInfoProduct.put("PURCHASED", purchaseOrderDetailPage.getInfoWithProd("PURCHASED", ""));
        expInfoProduct.put("INCOMING", purchaseOrderDetailPage.getInfoWithProd("INCOMING", "" ));
        expInfoProduct.put("PURCHASED ITEM", purchaseOrderDetailPage.getInfoWithProd("PURCHASED", variant));
        expInfoProduct.put("INCOMING ITEM", purchaseOrderDetailPage.getInfoWithProd("INCOMING", variant));
    }

    @Step
    public void verifyDataInventoryOfProduct(String variant, int quatity) {
        purchaseOrderDetailPage.clickProductInventory();
        actInfoProduct.put("PURCHASED", purchaseOrderDetailPage.getInfoWithProd("PURCHASED", ""));
        actInfoProduct.put("INCOMING", purchaseOrderDetailPage.getInfoWithProd("INCOMING", "" ));
        actInfoProduct.put("PURCHASED ITEM", purchaseOrderDetailPage.getInfoWithProd("PURCHASED", variant));
        actInfoProduct.put("INCOMING ITEM", purchaseOrderDetailPage.getInfoWithProd("INCOMING", variant));
        purchaseOrderDetailPage.verifyDataInventoryOfProduct(quatity);
    }

    public void searchProductInPurchase(String product) {
        purchaseOrderDetailPage.searchProductInPurchase(product);
    }

    public String getPOINName() {
        return purchaseOrderDetailPage.getPOINName();
    }

    public HashMap<String, Integer> getValueInventory() {
        HashMap<String, Integer> data = orderStep.getValueInventory();
        return data;
    }

    public HashMap<String, HashMap<String, Integer>> getValueItemVarian(String variant) {
        HashMap<String, HashMap<String, Integer>> data = new HashMap<>();
        String[] variantList = variant.split(";");
        for(String item : variantList) {
            HashMap<String, Integer> list = new HashMap<>();
            String variantItem = item.split(" ")[0];
            int quantityVariant = Integer.parseInt(item.split(" ")[1].split(">")[1]);
            ProductVariable.productQuatity += quantityVariant;
            int count = inventoryListPage.countcol(variantItem);
            for(int i = 3; i <= count -1; i++) {
                list.put(inventoryListPage.getLabelInCol(i, variantItem), inventoryListPage.getValueInCol(i, variantItem));
            }
            data.put(variantItem, list);
            ProductVariable.variantQuatity.put(variantItem, quantityVariant);
        }

        return data;
    }

    public void verifyValueProduct(HashMap<String, Integer> act, HashMap<String, Integer> exp, int quantity) {
        for(String label : act.keySet()) {
            if("PURCHASED".equals(label) || "INCOMING".equals(label)) {
                assertThat(act.get(label)).isEqualTo(exp.get(label) + quantity);
            } else {
                assertThat(act.get(label)).isEqualTo(exp.get(label));
            }
        }
    }

    public void accToBalance() {
        purchaseOrderDetailPage.accToBalance();
    }

    public int getQuatityVariant(String variant) {
        int quatity = 0;
        String[] variantList = variant.split(";");
        for(String item : variantList) {
            int quantityVariant = Integer.parseInt(item.split("/")[1].split(">")[1]);
            quatity += quantityVariant;
        }
        return quatity;
    }
    public String getTextPurchaseNumberAfterGetTN() {
        return purchaseOrderDetailPage.getTextPurchaseNumberAfterGetTN();
    }
}


