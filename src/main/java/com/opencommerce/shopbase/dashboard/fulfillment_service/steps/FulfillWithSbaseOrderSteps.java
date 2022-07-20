package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.MappingProductPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.FulfillWithSbaseOrderPage;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FulfillWithSbaseOrderSteps {
    FulfillWithSbaseOrderPage fulfillWithSbaseOrderPage;

    @Step
    public void fulfillOrder() {
        fulfillWithSbaseOrderPage.clickBTFulfillWith();
        fulfillWithSbaseOrderPage.selectSbFulfillment();
        fulfillWithSbaseOrderPage.clickBTFulfill();

    }

    @Step
    public void searchByOrder(String orderID) {
        fulfillWithSbaseOrderPage.searchByOrder(orderID);
    }

    @Step
    public void verifyInfoNeedTabToReview(String orderNumber, String productVariant, String productName, String country, String error) {
        assertThat(fulfillWithSbaseOrderPage.getInfoOrderFulfilment(orderNumber, "PRODUCT")).isEqualTo(productName.concat(" " + "-" + " " + productVariant).replace(",", " / "));
        assertThat(fulfillWithSbaseOrderPage.getInfoOrderFulfilment(orderNumber, "COUNTRY")).isEqualTo(country);
        assertThat(fulfillWithSbaseOrderPage.getInfoOrderFulfilment(orderNumber, "ERROR")).isEqualTo(error);
    }

    @Step
    public void verifyAndClickAction(String action) {
        fulfillWithSbaseOrderPage.clickBTAction();
        List<String> listAction = fulfillWithSbaseOrderPage.getListAction();
        for (String expAction : listAction) {
            assertThat(expAction).contains(action);
        }
        fulfillWithSbaseOrderPage.clickActions(action);
    }


    @Step
    public void editAddress(String address, String country, String city, String region, String code) {
        fulfillWithSbaseOrderPage.editAddress(address, country, city, region, code);
    }

    @Step
    public void searchByProductName(String productName) {
        fulfillWithSbaseOrderPage.enterInputFieldWithLabel("Search product by name", productName);
        fulfillWithSbaseOrderPage.clickBtn("Set");
    }

    @Step
    public void seclectOrder(String orderNumber) {
        fulfillWithSbaseOrderPage.selectOrder(orderNumber);
    }

    @Step
    public void clickBTMapping() {
        fulfillWithSbaseOrderPage.clickBtn("Map product");
    }

    public void clickTab(String tabName) {
        fulfillWithSbaseOrderPage.clickTab(tabName);
    }


}
