package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.RequestListPage;
import common.utilities.DateTimeUtil;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestListStep extends ScenarioSteps {

    RequestListPage requestListPage;

    @Step
    public int getNumberTabInProcess(String tabName) {
        int number = requestListPage.getNumberInTab(tabName);
//        return requestListPage.getNumberTabInProcess(tabName);
        return number;
    }

    @Step
    public void clickButton(String name) {
        requestListPage.clickButton(name);
    }

    @Step
    public void reloadPage() {
        requestListPage.reloadPage();
    }

    @Step
    public void checkNumberAfterRequesProduct(int numberTabInProcess, String tabName) {
        requestListPage.checkNumberAfterRequesProduct(numberTabInProcess, tabName);
    }

    @Step
    public void searchQuotationWithURL() {
        requestListPage.searchQuotationWithURL();
    }

    @Step
    public String getQuotationId() {
        return requestListPage.getQuotationId();
    }

    @Step
    public void clickTab(String quotationTab) {
        requestListPage.clickTab(quotationTab);
    }

    @Step
    public void searchQuotationId(String quotationId) {
        requestListPage.enterInputFieldWithLabel("Search quotation by Quotation number, Source URL, Product name", quotationId);
    }

    @Step
    public void waitSearchResult(String quotationId) {
        requestListPage.waitSearchResult(quotationId);
    }

    @Step
    public void checkClickIconDetail(String quotationId) {
        requestListPage.checkClickIconDetail(quotationId);
    }

    @Step
    public void switchToTabInRequestList(String tab) {
        requestListPage.switchToTabInRequestList(tab);
    }

    @Step
    public int getNumberInTab(String tab) {
        return requestListPage.getNumberInTab(tab);
    }

    public void verifyQuotationIsCreatedOnSbase(int countLink, String tab) {
        int firstCount = OrderVariable.numberInTabRequestList;
        int lastCount = firstCount + countLink;
        int actCount = requestListPage.getNumberInTab(tab);
        assertThat(actCount).isEqualTo(lastCount);
    }

    public void verifyLinkProduct(String url) {
        String column = "REQUEST LINK";
        int col = requestListPage.getColRequestList(column);
        String act = requestListPage.getFirstDataInRequestList(col);
        assertThat(act).isEqualTo(url);
    }

    public void goToQuotationDetail(String quotationId) {
        waitSearchResult(quotationId);
        requestListPage.goToQuotationDetail(quotationId);
    }

    public void closeCripsChat() {
        requestListPage.closeCripsChat();
    }

    public void verifyWarehouseProduct(String warehouseProduct) {
        String colum = "PRODUCT";
        int col = requestListPage.getColRequestList(colum);
        String act = requestListPage.getWarehouseProduct(col);
        assertThat(act).isEqualTo(warehouseProduct);
    }

    public void verifyMinimumPrice(String miniQuantity) {
        String act = requestListPage.getMinimumQuantity();
        assertThat(act).isEqualTo(miniQuantity);
    }

    public void verifyExpiraton(String expiraton) {
        String act = requestListPage.getExpiraton();
        assertThat(act).isEqualTo(expiraton);
    }

    public void verifyRequestAt(String requestAt) {
        String act = requestListPage.getRequestAt();
        String exp = DateTimeUtil.getDateFormat();
        assertThat(act).isEqualTo(exp);
    }

    public void verifyQuotationNotShow() {
        requestListPage.verifyQuotationNotShow();
    }

    public void verifyQuotationShow() {
        requestListPage.verifyQuotationShow();
    }


    public void verifySourceUrl(String link) {
        requestListPage.verifySourceUrl(link);
    }
    public void verifyStatus(String status) {
        requestListPage.verifyStatus(status);
    }

}
