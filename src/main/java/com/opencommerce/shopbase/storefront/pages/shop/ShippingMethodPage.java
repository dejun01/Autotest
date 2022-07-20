package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.sql.Struct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ShippingMethodPage extends SBasePageObject {
    public ShippingMethodPage(WebDriver driver) {
        super(driver);
    }


    public void verifyShippingMethodPageDisplayed() {
        verifyElementPresent("//h2[normalize-space()='Shipping method']", true);
    }

    public void verifyNoShippingZoneDisplayed() {
        verifyElementPresent("//p[text()='There are no shipping methods available for your cart or address']", true);
    }

    public String getShippingPrice() {
        String xpath = "//tr[child::td[text()='Shipping']]//span[@class='order-summary__emphasis']";
        String sShippingPrice = XH(xpath).getText();
        if (!sShippingPrice.equalsIgnoreCase("Free")) {
            if (Character.getType(sShippingPrice.charAt(0)) == Character.CURRENCY_SYMBOL) {
                sShippingPrice = sShippingPrice.replace(sShippingPrice.charAt(0), '0');
            } else if (Character.getType(sShippingPrice.charAt(sShippingPrice.length() - 1)) == Character.CURRENCY_SYMBOL) {
                sShippingPrice = sShippingPrice.replace(sShippingPrice.charAt(sShippingPrice.length() - 1), '0');
            }
        } else {
            sShippingPrice = "0";
        }
        return sShippingPrice;
    }

    public String getShippingPriceOfRateName(String sRateName) {
        String actualPrice = "";
        if (isElementExist("//div[contains(@class,'review-block')]/*[contains(@class,'emphasis')]")) {
            String xPathPrice = "//div[contains(@class,'review-block') and descendant::*[normalize-space()='" + sRateName + "']]//*[contains(@class,'emphasis')]";
            actualPrice = getText(xPathPrice).split("\\s",0)[0];
        } else {
            String xPathFree = "//div[@class='shipping-selector']//*[normalize-space()='Free']";
            if (isElementExist(xPathFree)) {
                scrollIntoElementView(xPathFree);
                actualPrice = "Free";
            } else {
                String xPathPrice = "//div[@class='shipping-selector']//*[contains(@class,'amount')]";
                actualPrice = getText(xPathPrice).split("\\s",0)[0];
            }
        }
        System.out.println(actualPrice);
        return actualPrice;
    }

    public void verifyDisplayOfCompleteOrderBtn() {
        String xPath = "//button[contains(text(),'Complete order')]";
        waitForEverythingComplete(5);
        verifyElementPresent(xPath, true);
    }

    public List<String> getActualNonShippableProd() {
        return getListText("//ul[@class='not-shippable-variants']//span");
    }

    public String getShippingFeeBeforeDiscount() {
        String xPath = "//*[@class='shipping-selector__amount' or @class='review-block__amount']//*[@class='content-box__small-text']";
        String shippingFee = "";
        if (isElementVisible(xPath, 5))
            shippingFee = getText(xPath);
        return shippingFee;
    }

    public void clickBtnChange() {
        String btn = "(//span[normalize-space()='Change'])[1]";
        if (isElementVisible(btn, 10))
            clickOnElement(btn);
    }

    public int getQuantityOfShippingMethod() {
        Integer actQuantity = countElementByXpath("//div[contains(@class,'shipping-method')]//label[@class='s-radio']");
        return actQuantity;
    }
}
