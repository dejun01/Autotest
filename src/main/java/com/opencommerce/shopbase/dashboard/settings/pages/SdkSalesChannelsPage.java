package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SdkSalesChannelsPage extends SBasePageObject {
    public SdkSalesChannelsPage(WebDriver driver) {
        super(driver);
    }

    String shopbaseCustomerName = "";
    String shopbaseCustomerAddress = "";
    String shopbaseCustomerCity = "";
    String shopbaseCustomerCountry = "";
    String shopbaseCustomerZipcode = "";


    public void openProductOnShopifySFonNewTab(String prodName) {
        String shopifyDomain = LoadObject.getProperty("shop1.domain.shopify");
        String url = "https://" + shopifyDomain + "/products/" + prodName;
        openUrlInNewTab(url);
    }

    public void clickToCheckoutShopify() {
        String xpath = "//button[@data-testid='Checkout-button']";
        switchToTheLastestTab();
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void InputShippingInfoShopify() {
        String xEmail = "//input[@id='checkout_email_or_phone']";
        String xFirstName = "//input[@id='checkout_shipping_address_first_name']";
        String xLastName = "//input[@id='checkout_shipping_address_last_name']";
        String xAddress = "//input[@id='checkout_shipping_address_address1']";
        String xCity = "//input[@id='checkout_shipping_address_city']";
        String xCountry = "//select[@id='checkout_shipping_address_country']";
        String xZipcode = "//input[@id='checkout_shipping_address_zip']";

        waitClearAndType(xEmail, "thotran@yopmail.com");
        waitClearAndType(xFirstName, "Tho");
        waitClearAndType(xLastName, "Tran");
        waitClearAndType(xAddress, "130 trung phung");
        selectDdlByXpath(xCountry, "Vietnam");
        waitClearAndType(xCity, "Hanoi");
        waitClearAndType(xZipcode, "10000");

    }

    public void clickContinueCheckoutShopify() {
        String xpath = "//button[@id='continue_button']";
        waitABit(5000);
        clickOnElement(xpath);
    }

    public String getIframeShopify(String name) {
        String xpath = "//div[@data-card-field-placeholder='" + name + "']//iframe";
        return xpath;
    }

    public void inputCardInfoShopify() {
        String xCardNumber = getIframeShopify("Card number");
        String xCardName = getIframeShopify("Name on card");
        String xCardDate = getIframeShopify("Expiration date (MM / YY)");
        String xCvv = getIframeShopify("Security code");



        switchToIFrame(xCardNumber);
        clickAndClearThenType("//input[@placeholder='Card number']", "1");
        switchToIFrameDefault();

        switchToIFrame(xCardName);
        clickAndClearThenType("//input[@placeholder='Name on card']", "tho");
        switchToIFrameDefault();


        switchToIFrame(xCardDate);
        inputSlowly("//input[@id='expiry']", "12/25");
        switchToIFrameDefault();

        switchToIFrame(xCvv);
        clickAndClearThenType("//input[@placeholder='Security code']", "123");
        switchToIFrameDefault();

    }

    public void clickPayNowShopify() {
        String xpath = "//div[@class='shown-if-js']//button";
        clickOnElement(xpath);
    }

    public String getShopifyOrderName() {
        String xpath = "//span[@class='os-order-number']";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyCustomerEmail() {
        String xpath = "//h3[text()[normalize-space()='Contact information']]//parent::div//p//bdo";
        //xpath spf dashboard
//        String xpath = "//button[@class='Polaris-Button_r99lw Polaris-Button--plain_2z97r Polaris-Button--textAlignLeft_1yjwh']//span//span";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyCustomerShippingAddress() {
        String xpath = "//h3[text()[normalize-space()='Shipping address']]//following-sibling::address";
        //xpath spf dashboard
        //String xpath = "//div[@class='_1daZ2']//p[contains(text(),'" + customerName + "')]";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyProductName() {
        String xpath = "//th[@class='product__description']//span[@class='product__description__name order-summary__emphasis']";
        //xpath spf dashboard
        //String xpath = "//div[@class='_19MS6']//a";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyProductSalePrice() {
        String xpath = "//td[@class='product__price']//span";
        //xpath spf dashboard
//        String xpath = "//div[@class='_3RD5s']";
        String value = getText(xpath);
        return value;
    }

    public String getShopifySubtotal() {
        String xpath = "//th[text()[normalize-space()='Subtotal']]//parent::tr//td//span";
        //xpath spf dashboard
//        String xpath = "//span[text()[normalize-space()='Subtotal']]//parent::span//parent::div//div[@class='_1H9_W']//span";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyShippingFee() {
        String xpath = "//span[text()[normalize-space()='Shipping']]//parent::th//parent::tr//td//span";
        String value = getText(xpath);
        return value;
    }

    public String getShopifyTotalOrder() {
        String xpath = "//span[text()[normalize-space()='Total']]//parent::th//parent::tr//td//span[2]";
        //xpath spf dashboard
//        String xpath = "//span[text()[normalize-space()='Total']]//parent::span//parent::div//div[@class='_1H9_W']//span";
        String value = getText(xpath);
        return value;
    }

    public void openShopifyDashboardOnNewTab() {
        String shopifyDashboard = LoadObject.getProperty("shopify.url");
        openUrlInNewTab(shopifyDashboard);
    }

    public void loginToShopify() {
        String shopifyUsername = LoadObject.getProperty("user.shopify");
        String shopifyPwd = LoadObject.getProperty("user.pass.shopify");
        String xUsername = "//input[@id='account_email']";
        String xNext = "//button[text()[normalize-space()='Next']]";
        String xPwd = "//input[@id='account_password']";
        String xLogin = "//button[text()[normalize-space()='Log in']]";

        waitClearAndType(xUsername, shopifyUsername);
        clickOnElement(xNext);
        waitClearAndType(xPwd, shopifyPwd);
        clickOnElement(xLogin);

    }

    public void navigateToStoreShopifyDashboard() {
        String xpath = "//span[text()[normalize-space()='jrhuyen']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xHome = "//span[text()[normalize-space()='Home']]";
        waitElementToBeVisible(xHome);
    }

    public void navigateToOnShopifySideBarMenu(String menu) {
        String xpath = "//span[text()[normalize-space()='" + menu + "']]";
        clickOnElement(xpath);
    }

    public void clickOnShopifyOrderDetails(String orderNumber) {
        String xpath = "//span[text()[normalize-space()='" + orderNumber + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickToSaleChannel(String channel) {
        String xpath = "//p[text()[normalize-space()='" + channel + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnConnect() {
        String xpath = "//span[text()[normalize-space()='Connect']]";
        clickOnElement(xpath);
    }

    public void inputShopifyShopUrl(String domain) {
        String xpath = "//div[text()[normalize-space()='https://']]//following-sibling::div//input";
        waitClearAndType(xpath, domain);
    }

    public void inputShopifyAPIkey(String apiKey) {
        String xpath = "//div[text()[normalize-space()='API key']]//following-sibling::div[1]//input";
        waitClearAndType(xpath, apiKey);
    }

    public void inputShopifyAPIpassword(String apiPwd) {
        String xpath = "//div[text()[normalize-space()='API password']]//following-sibling::div[1]//input";
        waitClearAndType(xpath, apiPwd);
    }

    public void clickBtnContinueConnect() {
        String xpath = "//span[text()[normalize-space()='Continue']]";
        clickOnElement(xpath);
    }

    public void clickBtnConnectAnotherShop() {
        String xpath = "//p[text()[normalize-space()='Connect another account']]";
        clickOnElement(xpath);
    }

    public String getSaleChannelDomain(String index) {
        int indexCol = getIndexOfColumn("Shop name");
        String xpath = "//tbody//tr[" + index + "]//td[" + indexCol + "]";
        String value = getText(xpath);
        return value;
    }

    public int getIndexOfColumn(String columnName) {
        String xpath = "//thead//tr//th[text()[normalize-space()='" + columnName + "']]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public String getSaleChannelStatus(String index) {
        int indexCol = getIndexOfColumn("Status");
        String xpath = "//tbody//tr[" + index + "]//td[" + indexCol + "]";
        String value = getText(xpath);
        return value;
    }

    public void verifyShowBtnOnListAccount(String index, String btnLabel) {
        int indexCol = getIndexOfColumn("Status") + 1;
        String xpath = "//tbody//tr[" + index + "]//td[" + indexCol + "]//button//span[text()[normalize-space()='" + btnLabel + "']]";
        isElementVisible(xpath, 5);
    }

    public void clickOnBtnOnListAccount(String index, String btnLabel) {
        int indexCol = getIndexOfColumn("Status") + 1;
        String xpath = "//tbody//tr[" + index + "]//td[" + indexCol + "]//button//span[text()[normalize-space()='" + btnLabel + "']]";
        clickOnElement(xpath);
        refreshPage();
    }

    public void clickOrderDetailSyncFromShopify(String shopifyOrderName) {
        String xpath = "//span[@data-label='This order is from Shopify sales channel']//preceding-sibling::div[contains(text(),'#spf') and contains(text(),'" + shopifyOrderName + "')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }


    public String getShopbaseCustomerEmail() {
        String xpath = "//h3[text()[normalize-space()='CONTACT INFORMATION']]//ancestor::div[@class='s-flex']//following-sibling::div//p";
        String value = getText(xpath).trim();
        return value;
    }

    public String getShopbaseProductName() {
        String xpath = "//p[@class='unfulfilled-card__name-product']//a";
        waitElementToBeVisible(xpath);
        String value = getText(xpath).trim();
        return value;
    }

    public String getShopbaseProductSalePrice() {
        String xpath = "//span[@class='unfulfilled-card__total-price']//span";
        String value = getText(xpath).trim();
        return value;
    }

    public String getShopbaseSubtotal() {
        String xpath = "//td[text()[normalize-space()='Subtotal']]//parent::tr//td/div";
        String value = getText(xpath).trim();
        return value;
    }

    public String getShopbaseShippingFee() {
        String xpath = "//td[text()[normalize-space()='Shipping']]//following-sibling::td//span";
        String value = getText(xpath).trim();
        return value;
    }

    public String getShopbaseTotalOrder() {
        String xpath = "//td[text()[normalize-space()='Total']]//parent::tr//td/div";
        String value = getText(xpath).trim();
        return value;
    }

    public void getShopbaseCustomerShippingAddress() {
        String xCustomerName = "//h3[text()[normalize-space()='Shipping address']]//ancestor::div[@class='type-container']//descendant::p[1]";
        String xCustomerAddress = "//h3[text()[normalize-space()='Shipping address']]//ancestor::div[@class='type-container']//descendant::p[2]";
        String xCustomerCity = "//h3[text()[normalize-space()='Shipping address']]//ancestor::div[@class='type-container']//descendant::p[3]";
        String xCustomerZipcode = "//h3[text()[normalize-space()='Shipping address']]//ancestor::div[@class='type-container']//descendant::p[4]";
        String xCustomerCountry = "//h3[text()[normalize-space()='Shipping address']]//ancestor::div[@class='type-container']//descendant::p[5]";

        shopbaseCustomerName = getText(xCustomerName).trim();
        shopbaseCustomerAddress = getText(xCustomerAddress).trim();
        shopbaseCustomerCity = getText(xCustomerCity).trim();
        shopbaseCustomerZipcode = getText(xCustomerZipcode).trim();
        shopbaseCustomerCountry = getText(xCustomerCountry).trim();
    }

    public void verifyDataOrderSyncFromShop(String saleChanelProductName, String shopbaseProductName) {
        assertThat(saleChanelProductName).isEqualTo(shopbaseProductName);
    }

    public void verifySettingSaleChannel() {
        String xSetting1 = "//h4[text()[normalize-space()='Automatic updates order from Sale channel']]";
        String xSetting2 = "//h4[text()[normalize-space()='Auto update order to SC']]";
        isElementVisible(xSetting1, 5);
        isElementVisible(xSetting2, 5);
    }


    public void verifyShippingInforSyncFromSaleChannel(String shippingInfo) {
        assertThat(shippingInfo).contains(shopbaseCustomerName);
        assertThat(shippingInfo).contains(shopbaseCustomerAddress);
        assertThat(shippingInfo).contains(shopbaseCustomerCity);
        assertThat(shippingInfo).contains(shopbaseCustomerCountry);
        assertThat(shippingInfo).contains(shopbaseCustomerZipcode);
    }

    public void verifyShowButtonConnectSaleChannel() {
        String xpath = "//span[text()[normalize-space()='Connect']]";
        isElementVisible(xpath, 5);
    }

    public void searchProductOnShopifyDashboard(String productName) {
        String xpath = "//input[@placeholder='Filter products']";
        waitTypeAndEnter(xpath, productName);
    }

    public void clickToProductDetailsShopifyDashboard(String productName) {
        String xpath = "//span[text()[normalize-space()='" + productName + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public String getShopifyVariant() {
        String xpath = "//th[@class='product__description']//span[@class='product__description__variant order-summary__small-text']";
        //xpath spf dashboard
//        String xpath = "//div[@class='_19MS6']//div//span";
        String value = getText(xpath);
        return value;
    }

    public String getShopbaseVariant() {
        String xpath = "//div[@class='unfulfilled-card__line_item__secondary-details']//span[1]//p";
        String value = getText(xpath);
        return value;
    }


    public void chooseVariant(String variant) {
        String xpath = "//div[child::div[normalize-space()='" + variant + "']]";
        clickOnElement(xpath);
    }

    public void clickBtnName(String btnName) {
        clickOnElement("//button[child::span[normalize-space()='" + btnName + "']]");
    }

    public void verifyProductAndVariant(String product, String variant) {
        String xProduct = "//a[normalize-space()= '" + product + "']";
        String xVariant = "//span[normalize-space()= '" + variant + "']";
        isElementVisible(xProduct, 5);
        isElementVisible(xVariant, 5);
    }

    public void choiceCustomer(String value) {
        clickOnElement("//*[contains(text(), 'barnes@mailtothis.com')]");
    }

    public void scrollElement(String label) {
        scrollIntoElementView("//*[normalize-space()='" + label + "']");
    }

    public void verifyCreateSuccess() {
        String xpath = "//h2[contains(text(),'Unfulfilled')]";
        waitElementToBeVisible(xpath, 50);
        verifyElementVisible(xpath, true);
    }

    public String getOrderName() {
        return getText("//div/h1");
    }

    public void switchMenuTab(String menu_name) {
        clickOnElement("//ul[contains(@class,'Navigation') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + menu_name + "']]|(//span[following-sibling::*[normalize-space()='" + menu_name + "']])[1]");
    }
}
