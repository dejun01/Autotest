package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing.RequestListPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class RequestListSteps extends ScenarioSteps {

    RequestListPage requestListPage;

    @Step
    public void searchQuotation(String quotationId) {
        requestListPage.enterQuotation(quotationId);
        requestListPage.waitSearchDone();
    }

    @Step
    public void verifySearchQuotation(String quotation) {
        if (quotation.isEmpty()) {
            requestListPage.verifyShowAll();
        } else {
            int count = requestListPage.countResult(quotation);
            for (int i = 1; i <= count; i++) {
                requestListPage.verifyQuotation(quotation, i);
            }
        }
    }

    @Step
    public void verifyNoData() {
        requestListPage.verifyTextPresent("No products matched your search criteria", true);
    }

    @Step
    public void verifyMsg(String msg) {
        requestListPage.verifyTextPresent(msg, true);
    }

    @Step
    public void verifyRequestSuccess(String email) {
        String mesRequestSucc = requestListPage.getMessRequestSucc();
        System.out.println(mesRequestSucc);
        assertThat(mesRequestSucc).isEqualTo("Request products successfully\nWe will send you the details via email " + email + " within 3 working days.");
    }

    @Step
    public String getQuotationID(int i) {
        return requestListPage.getQuotationID(i);
    }

    @Step
    public int countItem(String quotationID) {
        return requestListPage.countItem(quotationID);
    }

    public void verifyLinkProductInQuotation(String quotationId, String link) {
        requestListPage.verifyLinkProductInQuotation(quotationId, link);
    }

    public String getQuotationIDByLinkProduct(String link) {
        return requestListPage.getQuotationIDByLinkProduct(link);
    }

    public void verifyListPriceByQuantity(String priceByQuantitys, int index) {
        List<String> listpriceByQuantity = Arrays.asList(priceByQuantitys.split(";"));
        for (String quantityAndPrice : listpriceByQuantity) {
            requestListPage.verifyPriceByQuantity(quantityAndPrice, index);
        }
    }

    public void enterQuantity(String quantity, int index) {
        requestListPage.enterQuantity(quantity, index);
        requestListPage.clickOnRowToUpdatePrice(index);
    }

    public void verifyUnitPrice(String unitPrice, int index) {
        Double actUnitPrice = requestListPage.getPriceAfterInputQuantity("Unit price", index);
        assertThat(actUnitPrice).isEqualTo(requestListPage.convertToPrice(unitPrice));
    }

    public void verifySubtotal(String unitPrice, String quantity, int index) {

        Double actUnitPrice = requestListPage.getPriceAfterInputQuantity("Subtotal", index);
        Double expPrice = requestListPage.convertToPrice(unitPrice) * Integer.parseInt(quantity);
        assertThat(actUnitPrice).isEqualTo(expPrice);
    }

    public void verifyMinimumPrice(String minPrice) {
        verifyInformationQuotationByColName("Min Price", 1, minPrice);
    }

    public void verifyInformationQuotationByColName(String colName, int row, String value) {
        requestListPage.verifyInformationQuotationByColName(colName, row, value);
    }

    public int getRowVariant(String variant) {
        return requestListPage.getRowVariant(variant);
    }

    public void verifySubtotalOfOder() {
    }

    public void verifyQuotationExisted(String quotationId) {
        int i = 1;
        while (!requestListPage.isQuotationExisted(quotationId)) {
            waitABit(5000);
            requestListPage.refreshPage();
            i++;
            if (i == 10) {
                break;
            }
            assertThat(requestListPage.isQuotationExisted(quotationId)).isEqualTo(true);
        }

    }

    public void clickView(String quotationId) {
        requestListPage.clickView(quotationId);
    }

    public void verifyEstimatedTime(String expEstimatedTime) {
        String actEstimatedTime = requestListPage.getInforQuotation("Estimated delivery time");
        assertEquals(actEstimatedTime, expEstimatedTime);
    }

    public void verifyMinimumOrderQuantity(String expMinimumOrderQuantity) {
        String actMinimumOrderQuantity = requestListPage.getInforQuotation("Minimum order quantity");
        assertEquals(actMinimumOrderQuantity, expMinimumOrderQuantity);
    }

    public void verifyMinimumOrderValue(String expMinimumOrderValue) {
        String actMinimumOrderValue = requestListPage.getInforQuotation("Minimum order value");
        assertEquals(actMinimumOrderValue, expMinimumOrderValue);
    }
}
