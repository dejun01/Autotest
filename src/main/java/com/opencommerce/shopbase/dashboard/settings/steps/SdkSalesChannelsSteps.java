package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.SalesChannelPage;
import com.opencommerce.shopbase.dashboard.settings.pages.SdkSalesChannelsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class SdkSalesChannelsSteps extends ScenarioSteps {
    SdkSalesChannelsPage sdkPage;

    public void openProductOnShopifySFonNewTab(String prodName) {
        sdkPage.openProductOnShopifySFonNewTab(prodName);
    }

    public void clickToCheckoutShopify() {
        sdkPage.clickToCheckoutShopify();
    }

    public void InputShippingInfoShopify() {
        sdkPage.InputShippingInfoShopify();
    }

    public void clickContinueCheckoutShopify() {
        sdkPage.clickContinueCheckoutShopify();
    }

    public void inputCardInfoShopify() {
        sdkPage.inputCardInfoShopify();
    }

    public void clickPayNowShopify() {
        sdkPage.clickPayNowShopify();
    }

    public String getShopifyOrderName() {
        return sdkPage.getShopifyOrderName();
    }

    public String getShopifyCustomerEmail() {
        return sdkPage.getShopifyCustomerEmail();
    }

    public String getShopifyCustomerShippingAddress() {
        return sdkPage.getShopifyCustomerShippingAddress();
    }

    public String getShopifyProductName() {
        return sdkPage.getShopifyProductName();
    }

    public String getShopifyProductSalePrice() {
        return sdkPage.getShopifyProductSalePrice();
    }

    public String getShopifySubtotal() {
        return sdkPage.getShopifySubtotal();
    }

    public String getShopifyShippingFee() {
        return sdkPage.getShopifyShippingFee();
    }

    public String getShopifyTotalOrder() {
        return sdkPage.getShopifyTotalOrder();
    }

    public void openShopifyDashboardOnNewTab() {
        sdkPage.openShopifyDashboardOnNewTab();
    }

    public void loginToShopify() {
        sdkPage.loginToShopify();
    }

    public void navigateToStoreShopifyDashboard() {
        sdkPage.navigateToStoreShopifyDashboard();
    }

    public void navigateToOnShopifySideBarMenu(String menu) {
        sdkPage.navigateToOnShopifySideBarMenu(menu);
    }

    public void clickOnShopifyOrderDetails(String orderNumber) {
        sdkPage.clickOnShopifyOrderDetails(orderNumber);
    }

    public void clickToSaleChannel(String channel) {
        sdkPage.clickToSaleChannel(channel);
    }

    public void clickBtnConnect() {
        sdkPage.clickBtnConnect();
    }

    public void inputShopifyShopUrl(String domain) {
        sdkPage.inputShopifyShopUrl(domain);
    }

    public void inputShopifyAPIkey(String apiKey) {
        sdkPage.inputShopifyAPIkey(apiKey);
    }

    public void inputShopifyAPIpassword(String apiPwd) {
        sdkPage.inputShopifyAPIpassword(apiPwd);
    }

    public void clickBtnContinueConnect() {
        sdkPage.clickBtnContinueConnect();
    }

    public void clickBtnConnectAnotherShop() {
        sdkPage.clickBtnConnectAnotherShop();
    }

    public String getSaleChannelDomain(String index) {
        return sdkPage.getSaleChannelDomain(index);
    }

    public String getSaleChannelStatus(String index) {
        return sdkPage.getSaleChannelStatus(index);
    }

    public void verifyShowBtnOnListAccount(String index, String btnLabel) {
        sdkPage.verifyShowBtnOnListAccount(index, btnLabel);
    }

    public void clickOnBtnOnListAccount(String index, String btnLabel) {
        sdkPage.clickOnBtnOnListAccount(index, btnLabel);
    }

    public void clickOrderDetailSyncFromShopify(String shopifyOrderName) {
        sdkPage.clickOrderDetailSyncFromShopify(shopifyOrderName);
    }

    public String getShopbaseCustomerEmail() {
        return sdkPage.getShopbaseCustomerEmail();
    }

    public String getShopbaseProductName() {
        return sdkPage.getShopbaseProductName();
    }

    public String getShopbaseProductSalePrice() {
        return sdkPage.getShopbaseProductSalePrice();
    }

    public String getShopbaseSubtotal() {
        return sdkPage.getShopbaseSubtotal();
    }

    public String getShopbaseShippingFee() {
        return sdkPage.getShopbaseShippingFee();
    }

    public String getShopbaseTotalOrder() {
        return sdkPage.getShopbaseTotalOrder();
    }


    public void verifyDataOrderSyncFromShop(String saleChanelProductName, String shopbaseProductName) {
        sdkPage.verifyDataOrderSyncFromShop(saleChanelProductName, shopbaseProductName);
    }

    public void verifySettingSaleChannel() {
        sdkPage.verifySettingSaleChannel();
    }

    public void getShopbaseCustomerShippingAddress() {
        sdkPage.getShopbaseCustomerShippingAddress();
    }

    public void verifyShippingInforSyncFromSaleChannel(String shippingInfo) {
        sdkPage.verifyShippingInforSyncFromSaleChannel(shippingInfo);
    }

    public void verifyShowButtonConnectSaleChannel() {
        sdkPage.verifyShowButtonConnectSaleChannel();
    }

    public void searchProductOnShopifyDashboard(String productName) {
        sdkPage.searchProductOnShopifyDashboard(productName);
    }

    public void clickToProductDetailsShopifyDashboard(String productName) {
        sdkPage.clickToProductDetailsShopifyDashboard(productName);
    }

    public String getShopifyVariant() {
        return sdkPage.getShopifyVariant();
    }

    public String getShopbaseVariant() {
        return sdkPage.getShopbaseVariant();
    }


    public void choiceCustomer(String label, String value) {
        sdkPage.enterInputFieldWithLabel(label, value);
        sdkPage.choiceCustomer(value);
    }

    public void choiceProduct(String label, String product, String variant) {
        sdkPage.enterInputFieldWithLabel(label, product);
        sdkPage.chooseVariant(variant);
        sdkPage.clickBtnName("Add");
        sdkPage.verifyProductAndVariant(product, variant);
    }

    public void scrollElement(String label) {
        sdkPage.scrollElement(label);
    }

    public void clickBtn(String btnName) {
        sdkPage.clickBtnName(btnName);
    }

    public void verifyCreateSuccess() {
        sdkPage.verifyCreateSuccess();
    }

    public String getOrderName() {
        return sdkPage.getOrderName();
    }

    public void switchMenuTab(String menu_name) {
        sdkPage.switchMenuTab(menu_name);
        sdkPage.waitForPageLoad();
        sdkPage.waitForEverythingComplete();
    }

    public void clickLinkTextWithLabel(String label) {
        sdkPage.scrollElement(label);
        sdkPage.clickLinkTextWithLabel(label);
    }
}
