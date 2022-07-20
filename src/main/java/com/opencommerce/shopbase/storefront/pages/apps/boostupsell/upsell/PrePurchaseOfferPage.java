package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.upsell;

import common.SBasePageObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrePurchaseOfferPage extends SBasePageObject {
    public PrePurchaseOfferPage(WebDriver driver) {
        super(driver);
    }

    public void verifyOfferMessageShown(String sMessage, boolean show) {
        verifyElementPresent("//div[contains(@class,'pre-purchase__offer-msg') and text()='" + sMessage + "']", show);
    }

    public void waitUntilBtnAddToCartShown() {
        waitUntilElementVisible("//div[contains(@class,'pre-purchase__container')]//button[child::*[text()='Add to cart']]", 15);
    }

    String xpathProduct = "//div[contains(@class,'app-flow-wrap upsell-product') and descendant::*[text()[normalize-space()=\"%s\"]]]";

    public void selectVariant(String productName, String variantProduct) {
        String xpath = String.format(xpathProduct, productName.trim()) + "//select";
        verifyElementPresent(xpath, true);
        clickOnElement(xpath);
        clickOnElement(xpath + "//option[text()[normalize-space()=\"" + variantProduct + "\"]]");
        waitABit(2000);
    }

    public void verifyPrice(String productName, String price) {
        String xpath = String.format(xpathProduct, productName.trim()) + "//div[contains(@class,'upsell-only-blocks__product-price upsell-color-price')]";
        String _price = waitElementToBePresentThenScrollIntoView(xpath).getText();
        String _newSPrice = price(_price);
        float actPrice = Float.parseFloat(_newSPrice);
        float expPrice = Float.parseFloat(price);
        assertThat(actPrice).isEqualTo(expPrice);
    }


    public void addProductInOffer(String productName) {
        String xpath = String.format(xpathProduct, productName.trim()) + "//button[contains(@class,'add-cart')]";
        waitForElementToPresent(xpath);
        try {
            clickOnElement(xpath);
        } catch (StaleElementReferenceException ex) {
            clickOnElement(xpath);
        }
        waitForEverythingComplete();
    }

    public void clickBtnCheckoutOnPopup() {
        waitElementToBeClickable("//button[contains(@class,'pre-purchase__action-checkout')]");
        clickOnElement("//button[contains(@class,'pre-purchase__action-checkout')]");
    }

    public void clickClosePopupPrePurchase() {
        clickOnElement("//button[@class='app-modal__close']");
    }

    public void verifyNumberOfProductOnUpsell(int number) {
        waitForEverythingComplete();
        waitABit(2000);
        String xpathRows = "//div[contains(@class,'app-flow-wrap upsell-product')]";
        int actualNumber = countElementByXpath(xpathRows);
        assertThat(actualNumber).isLessThanOrEqualTo(number);
    }

    public void verifyProduct(String product) {
        String xpath = String.format(xpathProduct, product.trim());
        verifyElementPresent(xpath, true);

    }

    public void addToCartProduct1st() {
        String xpathAddtoCart1st = "(//div[contains(@class,'pre-purchase__container')]//button)[1]";
        clickOnElement(xpathAddtoCart1st);
        waitForEverythingComplete();
        waitABit(3000);
    }

    public void verifyPopupPrePurchase(boolean isShow) {
        waitUntilElementVisible("//div[@class='app-modal pre-purchase']", 5);
        verifyElementPresent("//div[@class='app-modal pre-purchase']", isShow);
    }

    public void inputCustomOptionProduct(String product, String labelOption, String valueOption) {
        String xpathOption = String.format(xpathProduct, product.trim()) + "//div[child::*[text()='" + labelOption + "']]//following::input[contains(@class,'app-custom-option__input-option')]";
        waitTypeAndEnter(xpathOption, valueOption);
    }

    public List<String> getListProductOnPopupPrePurchase() {
        String xpath = "//div[contains(@class,'app-flow-wrap upsell-product')]//div[contains(@class,'upsell-product__product-name')]";
        return getListText(xpath);
    }

    public float getComparePrice(String product) {
        String xpath = String.format(xpathProduct, product.trim()) + "//div[contains(@class,'upsell-product__product-original-price upsell-color-compare-price')]";
        return getPrice(xpath);
    }

    public float getSalePrice(String pro) {
        String xpath = String.format(xpathProduct, pro.trim()) + "//div[contains(@class,'upsell-only-blocks__product-price upsell-color-price')]";
        return getPrice(xpath);
    }

    public void clickAddToCart(){
        String xPath = "//div[@data-form='product']//button[child::span[normalize-space()='Add to cart']]";
        clickOnElementByJs(xPath);
    }

    public void clickEditOffer(String nameOffer){
        String xPath = "//*[contains(@class, 'offer-name')]//*[normalize-space() = '"+ nameOffer +"']";
        clickOnElementByJs(xPath);
    }
}
