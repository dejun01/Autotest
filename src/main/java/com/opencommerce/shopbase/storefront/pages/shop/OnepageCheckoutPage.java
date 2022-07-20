package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class OnepageCheckoutPage extends SBasePageObject {

    public OnepageCheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void verifyShippingAdressDisplayed() {
        waitABit(3000);
        verifyElementPresent("//div[@class='section section-shipping-address']", true);

    }

    public void verifyShippingMethodDisplayed() {
        waitABit(3000);
        verifyElementPresent("//div[@class='section section--shipping-method']", true);
    }

    public void verifyPaymentMethodDisplayed() {
        waitABit(3000);
        verifyElementPresent("//div[@class='section' and descendant::h2[normalize-space()='Payment method']]", true);
    }

    public void clickPlaceYourOrder() {
        clickOnElement("//span[normalize-space()='Place Your Order']//ancestor::button");
    }

    public void choosePaypal() {
        clickOnElement("//input[@value='paypal-express']//parent::label");
    }

    public void clickContinueWithPaypal() {
        clickBtn("Continue with PayPal");
    }

    public Float getShippingPrice() {
        String xpath = "//table[@class='total-line-table']//td[normalize-space()='Shipping']/following::span[@class='order-summary__small-text']";
        String shipping = XH(xpath).getText();
        if (Character.getType(shipping.charAt(0)) == Character.CURRENCY_SYMBOL) {
            shipping = shipping.replace(shipping.charAt(0), '0');
        } else if (Character.getType(shipping.charAt(shipping.length() - 1)) == Character.CURRENCY_SYMBOL) {
            shipping = shipping.replace(shipping.charAt(shipping.length() - 1), '0');
        }
        return Float.parseFloat(shipping);
    }

    public void verifyBtnPlaceYourOrder() {
        verifyElementPresent("//span[normalize-space()='Place Your Order']", true);
    }

    public float getSubtotal() {
        String sub = getText("//*[contains(text(),'Subtotal')]//ancestor::*[@class='total-line']//span[@class='order-summary__emphasis']").replace("$", "");
        float subtotal = Float.parseFloat(sub);
        return subtotal;
    }

    public String getOrderNumber() {
        String numberOrder = getText("//div[@class='os-header__heading']//div[contains(@class,'order-number')]").replace("Order", "");
        return numberOrder;
    }

    public void waitForPayPalScreen() {
        String xpath = "//img[@alt='PayPal']";
        waitUntilElementVisible(xpath, 90);

    }

    public boolean isOnePage() {
        String xPath = "//ul[@class='breadcrumbs']";
        return !isElementVisible(xPath, 5);
    }

    public String getLogo() {
        String logo = getAttributeValue("//div[@class='content']//a[contains(@class,'logo--position')]//img", "src");
        waitForPageLoad();
        waitForEverythingComplete();
        if (logo.contains("@")) {
            String result[] = logo.split("@");
            return result[result.length - 1];
        } else return logo;
    }
}
