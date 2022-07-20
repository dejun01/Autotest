package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import com.fasterxml.jackson.databind.JsonNode;
import com.opencommerce.shopbase.OrderVariable;
import common.SBasePageObject;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.net.SocketTimeoutException;

import static com.opencommerce.shopbase.OrderVariable.*;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;


public class FulfilWithShopBaseFulfillmentPage extends SBasePageObject {
    public FulfilWithShopBaseFulfillmentPage(WebDriver driver) {
        super(driver);
    }

    public String getStatusFulfill(String productName, String variant) {
        String xpath = "//div[text()='" + productName + "']//parent::div[child::div[text()[normalize-space()='" + variant + "']]]/ancestor::div[@class='line-section']//div[@class='line-section__title']//span";
        return getText(xpath);
    }

    public String getBaseCostLineItem(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath = "//div[text()='" + property[0] + "']//parent::div[child::div[text()[normalize-space()='" + property[1] + "']]]//div[contains(@class,'description')]";
        return getText(xpath);
    }

    public String getShippingMethod(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath_1 = "//div[text()='" + property[0] + "']//parent::div[child::div[text()[normalize-space()='" + property[1] + "']]]/ancestor::tr//td[@class='shipping-method']//option[1]";
        return getText(xpath_1);
    }

    public String getShippingFee(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath_1 = "//div[text()='" + property[0] + "']//parent::div[child::div[text()[normalize-space()='" + property[1] + "']]]/ancestor::tr//td[contains(@class,'shipping-fee')]";
        return getText(xpath_1);
    }

    public String getEst(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath_1 = "//div[text()='" + property[0] + "']//parent::div[child::div[text()[normalize-space()='" + property[1] + "']]]/ancestor::tr//td[contains(@class,'estimated-shipping-time')]";
        return getText(xpath_1);
    }

    public String getInventoryStatus(String lineitem) {
        String[] property = lineitem.split(">");
        String xpath = "//div[text()='" + property[0] + "']//parent::div[child::div[text()[normalize-space()='" + property[1] + "']]]/ancestor::tr//td[contains(@class,'inventory-status')]";
        return getText(xpath);
    }

    public void selectOrderToFulfill() {
        checkCheckbox("//th[contains(@class, 'order-select-header')]//label", true);
    }

    public boolean isNoOrder() {
        return isElementExist("//p[text()[normalize-space()='No order']]");
    }

    public void fulfillOrder() {
        String xpath = "//button[child::span[text()[normalize-space()='Fulfill selected orders']]]";
        clickOnElement(xpath);
    }

    public void payOrder() {
        String xpath = "//button[child::span[text()[normalize-space()='Pay now']]]";
        clickOnElement(xpath);
    }

    public void wailToFulfillDone() {
        String order = OrderVariable.orderNumber;
        String xpathBreadcrumb = "//ol[@class='s-breadcrumb']//a[contains(text(),'Orders '" + order + ")]";
        String xpath = "//button[@class='s-button fulfill-selected-orders is-primary is-loading is-disabled']";
        waitUntilElementInvisible(xpath, 20);
        if (isElementExist(xpathBreadcrumb)) {
            clickOnElement(xpathBreadcrumb);
        }
    }

//    public Response getListPricingFromScalablepress(String handle, Integer idProductBase) {
//        String shopDomain = LoadObject.getProperty("shop");
//        String url = "https://api.scalablepress.com/v2/products/" + handle + "/items";
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.authentication = preemptive().basic("quan@beeketing.com ", "test_eDu4yh3iwMfkMm9K3uMtYw");
//        Response response = RestAssured.get(url);
//        assertThat(response.getStatusCode()).isBetween(200, 201);
//        return response;
//    }
}
