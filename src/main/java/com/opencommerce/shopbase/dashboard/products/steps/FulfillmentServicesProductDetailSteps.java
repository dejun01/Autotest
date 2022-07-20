package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.FulfillmentServicesProductDetailPage;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class FulfillmentServicesProductDetailSteps extends ScenarioSteps {
    FulfillmentServicesProductDetailPage fulfillmentServicesProductDetailPage;
    public String getHeader() {
        return fulfillmentServicesProductDetailPage.getHeader();
    }

    public void clickBTMapping() {
        fulfillmentServicesProductDetailPage.clickBtn("Map product");
    }

    public String getButton(String text) {
        return fulfillmentServicesProductDetailPage.getButton(text);
    }

    public String getValueAvailableStock(String label) {
        return fulfillmentServicesProductDetailPage.getValueAvailableStock(label);
    }

    public void searchProduct(String product) {
        fulfillmentServicesProductDetailPage.searchProduct(product);
    }

    public String getName() {
        return fulfillmentServicesProductDetailPage.getName();
    }

    public void clickOnActionBtn() {
        fulfillmentServicesProductDetailPage.clickOnActionBtn();
    }

    public void clickOnBtnInMoreAction(String page) {
        fulfillmentServicesProductDetailPage.clickOnBtnInMoreAction(page);
    }

    public String getText() {
        String text= fulfillmentServicesProductDetailPage.getQuotation();
        String[] subtext = text.split(" ");
        return subtext[0];
    }

    public void verifyRedirectMapPage(String page) {
        assertThat(fulfillmentServicesProductDetailPage.getPage()).isEqualTo(page);
    }

    public void clickBtnMoreOptions() {
        fulfillmentServicesProductDetailPage.clickBtnMoreOptions();
    }

    public void clickBack() {
        fulfillmentServicesProductDetailPage.clickBack();
    }

    public String getRequest() {return fulfillmentServicesProductDetailPage.getRequest(); }

    public void userRedirectToPage(String page) {
        fulfillmentServicesProductDetailPage.userRedirectToPage();
    }

    public void verifyDisplayButtonAnd(String btnremove, String btnedit) {
        fulfillmentServicesProductDetailPage.getRemoveBtn(btnremove);
        fulfillmentServicesProductDetailPage.getRemoveBtn(btnedit);
    }
    String availableStock = "";

    public void verifyProductWarehouse(String text) {
        String product = fulfillmentServicesProductDetailPage.getName();
        assertThat(product).isEqualTo(text);
    }

    public void verifyStock(String text) {
        String stock = fulfillmentServicesProductDetailPage.getValueStock();
        String[] stocks = stock.split(":");
        assertThat(stocks[1]. trim()).isEqualTo(text);
    }
}