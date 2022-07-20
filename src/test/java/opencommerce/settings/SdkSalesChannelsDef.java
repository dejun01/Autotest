package opencommerce.settings;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SalesChannelSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SdkSalesChannelsSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static com.opencommerce.shopbase.OrderVariable.totalAmt;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class SdkSalesChannelsDef {
    @Steps
    SdkSalesChannelsSteps sdkSteps;
    @Steps
    CommonSteps commonSteps;

    String shopifyOrderName = "";
    String shopifyCustomerEmail = "";
    String shopifyCustomerName = "";
    String shopifyCustomerShippingAddress = "";
    String shopifyProductName = "";
    String shopifyVariant = "";
    String shopifyProductSalePrice = "";
    String shopifySubTotal = "";
    String shopifyShippingFee = "";
    String shopifyTotalOrder = "";

    String shopbaseCustomerEmail = "";
    String shopbaseProductName = "";
    String shopbaseVariant = "";
    String shopbaseProductSalePrice = "";
    String shopbaseSubTotal = "";
    String shopbaseShippingFee = "";
    String shopbaseTotalOrder = "";

    String shopifyDomain1 = LoadObject.getProperty("shop1.domain.shopify");
    String shopifyAPIKey1 = LoadObject.getProperty("shop1.apikey.shopify");
    String shopifyAPIPwd1 = LoadObject.getProperty("shop1.apipwd.shopify");

    String shopifyDomain2 = LoadObject.getProperty("shop2.domain.shopify");
    String shopifyAPIKey2 = LoadObject.getProperty("shop2.apikey.shopify");
    String shopifyAPIPwd2 = LoadObject.getProperty("shop2.apipwd.shopify");

    @And("checkout product Shopify without verify")
    public void checkoutProductShopifyWithoutVerify() {
        sdkSteps.clickToCheckoutShopify();
        sdkSteps.InputShippingInfoShopify();
        sdkSteps.clickContinueCheckoutShopify();
        sdkSteps.clickContinueCheckoutShopify();
        sdkSteps.inputCardInfoShopify();
        sdkSteps.clickPayNowShopify();
    }

    @And("get all information order Shopify")
    public void getAllInformationOrderShopify() {
        shopifyOrderName = sdkSteps.getShopifyOrderName().replace("Order #", "").trim();
        shopifyCustomerEmail = sdkSteps.getShopifyCustomerEmail();
        shopifyCustomerShippingAddress = sdkSteps.getShopifyCustomerShippingAddress();
        shopifyProductName = sdkSteps.getShopifyProductName();
        shopifyVariant = sdkSteps.getShopifyVariant();
        shopifyProductSalePrice = sdkSteps.getShopifyProductSalePrice();
        shopifySubTotal = sdkSteps.getShopifySubtotal();
        shopifyShippingFee = sdkSteps.getShopifyShippingFee();
        shopifyTotalOrder = sdkSteps.getShopifyTotalOrder();
    }

    @When("login to Shopify dashboard on new tab")
    public void loginToShopifyDashboardOnNewTab() {
        sdkSteps.openShopifyDashboardOnNewTab();
        sdkSteps.loginToShopify();
    }

    @And("navigate to store Shopify dashboard")
    public void navigateToStoreShopifyDashboard() {
        sdkSteps.navigateToStoreShopifyDashboard();
    }

    @And("navigate to {string} on Shopify side bar menu")
    public void navigateToOnShopifySideBarMenu(String menu) {
        sdkSteps.navigateToOnShopifySideBarMenu(menu);
    }

    @And("click on Shopify order details")
    public void clickOnShopifyOrderDetails() {
        sdkSteps.clickOnShopifyOrderDetails(orderNumber);
    }

    @And("Click to sale channel {string}")
    public void clickToSaleChannel(String channel) {
        sdkSteps.clickToSaleChannel(channel);
    }

    @And("connect to Shopify shop")
    public void connectToShopifyShop() {
        sdkSteps.clickBtnConnect();
        sdkSteps.inputShopifyShopUrl(shopifyDomain1);
        sdkSteps.inputShopifyAPIkey(shopifyAPIKey1);
        sdkSteps.inputShopifyAPIpassword(shopifyAPIPwd1);
        sdkSteps.clickBtnContinueConnect();
        sdkSteps.verifySettingSaleChannel();
    }

    @And("connect to another Shopify shop")
    public void connectToAnotherShopifyShop() {
        sdkSteps.clickBtnConnectAnotherShop();
        sdkSteps.inputShopifyShopUrl(shopifyDomain2);
        sdkSteps.inputShopifyAPIkey(shopifyAPIKey2);
        sdkSteps.inputShopifyAPIpassword(shopifyAPIPwd2);
        sdkSteps.clickBtnContinueConnect();
    }

    @Then("verify connect Sale channel information")
    public void verifyConnectSaleChannelInformation(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            String shopName = SessionData.getDataTbVal(dataTable, row, "Shop name");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            switch (shopName) {
                case "@shop1@":
                    shopName = shopifyDomain1;
                    break;
                case "@shop2@":
                    shopName = shopifyDomain2;
                    break;
            }
            String domainActual = sdkSteps.getSaleChannelDomain(index);
            String statusActual = sdkSteps.getSaleChannelStatus(index);
            assertThat(shopName).isEqualTo(domainActual);
            assertThat(status).isEqualTo(statusActual);
            sdkSteps.verifyShowBtnOnListAccount(index, "Disconnect");
        }
    }

    @And("disconnect account on list")
    public void disconnectAccountOnList(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String index = SessionData.getDataTbVal(dataTable, row, "Index");
            sdkSteps.clickOnBtnOnListAccount(index, "Disconnect");
        }


    }

    @And("click to order detail sync from Shopify")
    public void clickOrderDetailSyncFromShopify() {
        sdkSteps.clickOrderDetailSyncFromShopify(shopifyOrderName);
    }


    @And("verify order detail sync from Sales channel")
    public void verifyOrderDetailSyncFromSalesChannel() {
        sdkSteps.verifyDataOrderSyncFromShop(shopifyProductName, shopbaseProductName);
        sdkSteps.verifyDataOrderSyncFromShop(shopifyVariant, shopbaseVariant);
        sdkSteps.verifyDataOrderSyncFromShop(shopifyProductSalePrice, shopbaseProductSalePrice);
        sdkSteps.verifyDataOrderSyncFromShop(shopifyShippingFee, shopbaseShippingFee);
        sdkSteps.verifyDataOrderSyncFromShop(shopifySubTotal, shopbaseSubTotal);
        sdkSteps.verifyDataOrderSyncFromShop(shopifyTotalOrder, shopbaseTotalOrder);
        sdkSteps.verifyDataOrderSyncFromShop(shopifyCustomerEmail, shopbaseCustomerEmail);
        sdkSteps.verifyShippingInforSyncFromSaleChannel(shopifyCustomerShippingAddress);
    }

    @And("get order detail information on dashboard")
    public void getOrderDetailInformationOnDashboard() {
        shopbaseProductName = sdkSteps.getShopbaseProductName();
        shopbaseVariant = sdkSteps.getShopbaseVariant();
        shopbaseProductSalePrice = sdkSteps.getShopbaseProductSalePrice();
        shopbaseShippingFee = sdkSteps.getShopbaseShippingFee();
        shopbaseSubTotal = sdkSteps.getShopbaseSubtotal();
        shopbaseTotalOrder = sdkSteps.getShopbaseTotalOrder();
        shopbaseCustomerEmail = sdkSteps.getShopbaseCustomerEmail();
        sdkSteps.getShopbaseCustomerShippingAddress();
    }

    @And("verify show button connect sale channel")
    public void verifyShowButtonConnectSaleChannel() {
        sdkSteps.verifyShowButtonConnectSaleChannel();
    }

    @And("search product {string} on Shopify dashboard and click to product details")
    public void searchProductOnShopifyDashboardAndClickToProductDetails(String productName) {
        sdkSteps.searchProductOnShopifyDashboard(productName);
        sdkSteps.clickToProductDetailsShopifyDashboard(productName);
    }

    @And("create order in shopify with")
    public void createOrderInShopifyWith(List<List<String>> data) {
        sdkSteps.switchMenuTab("Orders");
        sdkSteps.clickLinkTextWithLabel("Create order");
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String product = SessionData.getDataTbVal(data, row, "Product");
            String variant = SessionData.getDataTbVal(data, row, "Variant");
            String customer = SessionData.getDataTbVal(data, row, "Customer");
            sdkSteps.choiceProduct("Search products", product, variant);
            sdkSteps.choiceCustomer("Search customers", customer);
            sdkSteps.scrollElement("Mark as paid");
            sdkSteps.clickBtn("Mark as paid");
            sdkSteps.clickBtn("Create order");
            sdkSteps.verifyCreateSuccess();
            shopifyOrderName = sdkSteps.getOrderName().replace("#", "");
            shopifyCustomerName = customer;
        }
    }

    @Given("open product {string} on Shopify SF on new tab")
    public void openProductOnShopifySFOnNewTab(String prodName) {
        sdkSteps.openProductOnShopifySFonNewTab(prodName);
    }
}
