package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.assertj.core.api.Java6Assertions;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


public class CartPage extends SBasePageObject {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    String xpathItemCart = "//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='%s']]";
    String xpathItemCartWithVariant = "//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='%s'] and descendant::p[normalize-space()='%s']]";

    public void verifyCartPageShow() {
        verifyElementPresent("//div[@id='cart']", true, 20);
    }

    public void clickCheckoutOnCart() {
        String xpathCheckout = "//div[@id='cart']//button[@name='checkout']";
        waitUntilElementVisible(xpathCheckout, 10);
        if(!isElementExist(xpathCheckout)) {
            clickOnElement("//a/span[@class='cart-number']");
            waitElementToBeClickable("//button[@name='checkout']");
            clickOnElement("//button[@name='checkout']");
        }else{
            clickOnElement(xpathCheckout);
        }
    }

    public void clickGoToCartPage() {
        clickOnElement("//button[contains(@class,'go-to-cart')]");
    }

    public void verifySubtotalonCartPage(String subtotal) {
        String actualSubtotal = waitElementToBePresentThenScrollIntoView("//div[@id='cart']//*[contains(@class,'cart__subtotal-price')][not (contains(@class,'original'))]").getText();
        comparePrice(actualSubtotal, subtotal);
    }

    public void verifyCartItems(String product, String price) {
        String xpath = "";
        if (product.contains(":")) {
            String[] prod = product.split(":");
            xpath = String.format(xpathItemCartWithVariant, prod[0], prod[1]) + "//p[contains(@class,'product-cart__price')]//span";
        } else {
            xpath =  String.format(xpathItemCart, product) + "//p[contains(@class,'product-cart__price')]//span";
        }

        verifyElementPresent(xpath, true, 3);
        String actualPrice = waitElementToBePresentThenScrollIntoView(xpath).getText();
        comparePrice(actualPrice, price);
    }

    public void verifyCartItemsWithVariant(String product, String variant, String quantity) {
        waitABit(2000);
        verifyElementPresent(String.format(xpathItemCartWithVariant, product, variant), true);
        String xpathQuantity = String.format(xpathItemCartWithVariant, product, variant) + "//input[@class='quantity__num']";
        String actQuan = getTextValue(xpathQuantity);
        assertThat(actQuan).isEqualTo(quantity);
    }

    String xpathCartLineItemBassy = "(//div[ contains(@class,'cart__template')]//div[contains(@class,'product-cart')])[%d]";
    String xpathCartLineItemRollerAndInside = "(//div[contains(@class,'product-cart-wrapper')]/div[contains(@class,'product-cart')])[%d]";
    String theme = LoadObject.getProperty("theme");

    public int countProduct() {
        String xPath = "";
        switch (theme) {
            case "bassy":
                xPath = "//div[ contains(@class,'cart__template')]//div[contains(@class,'product-cart')]";
                break;
            default:
                xPath = "//div[contains(@class,'product-cart-wrapper')]/div[contains(@class,'product-cart')]";
        }

        return countElementByXpath(xPath);
    }

    public String getProductName(int index) {
        String xPath = "";
        switch (theme) {
            case "bassy":
                xPath = String.format(xpathCartLineItemBassy, index) + "//*[contains(@class,'product__name ')]";
                break;
            default:
                xPath = String.format(xpathCartLineItemRollerAndInside, index) + "//*[contains(@class,'product-cart__name')]";
        }
        return getTextValue(xPath);
    }

    public String getProductPrice(int index) {
        String xPath = "";
        switch (theme) {

            case "bassy":
                xPath = String.format(xpathCartLineItemBassy, index) + "//*[contains(@class,'product__prices')]//span[1]";
                break;
            default:
                xPath = String.format(xpathCartLineItemRollerAndInside, index) + "//*[contains(@class,'product-cart__price')]/span[1]";
        }
        return getTextValue(xPath);

    }

    public String getProductVariant(int index) {
        String xPath = "";
        switch (theme) {
            case "bassy":
                xPath = String.format(xpathCartLineItemBassy, index) + "//*[contains(@class,'product__options')]";
                break;
            default:
                xPath = String.format(xpathCartLineItemRollerAndInside, index) + "//*[contains(@class,'product-cart__options')]";
        }

        if (isElementVisible(xPath, 5)) {
            return getTextValue(xPath);
        }
        return "";


    }

    public String getProductQty(int index) {
        String xPath = "";
        switch (theme) {
            case "bassy":
                xPath = String.format(xpathCartLineItemBassy, index) + "//*[contains(@class,'product__quantity')]//input";
                break;
            default:
                xPath = String.format(xpathCartLineItemRollerAndInside, index) + "//div[contains(@class,'product-cart__quantity')]//input";

        }
        return getTextValue(xPath);

    }

    public static int maxRetry = 5;
    public void verifyDiscountCode(String discountCode, String discountValue) {
        String xpath = "//div[@id='cart']//div[@class='scoped text-uppercase']";
        int currentRetry = 0;

        while (!isElementExist(xpath) && currentRetry < maxRetry){
            waitForPageLoad();
            waitABit(3000);
            currentRetry++;
            refreshPage();
        }

        String actDiscount = getText("//div[@id='cart']//div[contains(@class,'uppercase')]");
        String actDiscountValue = getText("//div[@id='cart']//*[contains(@class,'cart-total-discount__price')]");
        assertThat(actDiscount.toUpperCase()).contains(discountCode.toUpperCase());
        comparePrice(actDiscountValue, discountValue);
    }

    public void setQuantityProductOnCart(String sNameProduct, String productVariant, String quantity) {
        String xpath;
        if(productVariant.isEmpty()){
            xpath = String.format(xpathItemCart, sNameProduct) + "//input";
        }else {
            xpath = String.format(xpathItemCartWithVariant, sNameProduct, productVariant) + "//input";
        }

        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, quantity);
        clickOnElement("//div[@id='cart']//div[contains(text(),'Subtotal')]");
        waitElementToBeVisible("//button[@name='checkout']");
    }

//    public void setQuantityProduct(String xpathParent, String quantity) {
//        String quantityActual = getTextValue(xpathParent + "//input");
//        float t = Integer.parseInt(quantity) - Integer.parseInt(quantityActual);
//        if (t > 0) {
//            for (int i = 1; i <= t; i++) {
//                addProduct(xpathParent);
//            }
//        } else if (t < 0) {
//
//            for (int i = 1; i <= -t; i++) {
//                removeProduct(xpathParent);
//            }
//        }
//
//    }
//
//    private void addProduct(String xpathParent) {
//        clickOnElement(xpathParent + "//button[@class='quantity__adjust quantity__adjust--plus' or contains(@class,'qty-input__increase')]");
//    }
//
//    private void removeProduct(String xpathParent) {
//        clickOnElement(xpathParent + "//button[@class='quantity__adjust quantity__adjust--minus'  or contains(@class,'qty-input__decrease')]");
//    }

    public void verifyMessageDiscountCart(String messageDiscount) {
        String xpathMessageSuccess = "//div[@class='cart-alert']";
        if (isElementExist(xpathMessageSuccess)) {
            waitUntilElementVisible(xpathMessageSuccess, 20);
            String actMsg = getText(xpathMessageSuccess);
            Java6Assertions.assertThat(actMsg).contains(messageDiscount);
        } else
            verifyElementPresent(xpathMessageSuccess, false);
    }

    public void verifyDiscountCodeOnCart(String discount) {
        String[] discountData = discount.split(",");
        String discountCode = discountData[0];
        String discountPrice = discountData[1];

        verifyElementPresent("//div[normalize-space()='"+ discountCode +"']", true);
        String actDiscountPrice = getText("//*[contains(@class,'cart-total-discount__price')]");
        comparePrice(actDiscountPrice, discountPrice);
    }

    public void verifyCheckoutPageShow() {
        verifyElementPresent("//div[@id='checkout']", true);
    }

    public void verifyMiniCartShow(boolean isShow) {
        verifyElementPresent("//div[contains(@class,'cart-drawer-container') or contains(@class,'cart--drawer')]", isShow);
    }

    public void openMiniCart() {
        String xpath = "//div[contains(@class,'cart-drawer-container')]";
        if (!isElementExist(xpath)) {
            clickOnElement("//a[contains(@class,'cart')]");
            verifyElementPresent(xpath, true);
        }

    }

    public void verifyShowCheckoutBtnInMIniCart(Boolean isShow) {
        verifyElementPresent("//button[normalize-space()='Go to cart']", !isShow);
    }

    public void clickButtonCheckout() {
        String xpath = "//button[@name='checkout' or normalize-space()='CHECKOUT']";
        if(!isElementExist(xpath)) {
            clickOnElement("//a/span[@class='cart-number']");
            waitElementToBeVisible(xpath, 10);
        }
        clickOnElement(xpath);
    }

    public void verifyOpenPage(String page) {
        String actualPage = getCurrentUrl();

        assertThat(actualPage).contains(page);
    }
}
