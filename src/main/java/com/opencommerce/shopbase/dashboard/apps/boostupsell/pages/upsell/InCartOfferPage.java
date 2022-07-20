package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.upsell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class InCartOfferPage extends SBasePageObject {
    public InCartOfferPage(WebDriver driver) {
        super(driver);
    }

    String xpathIncart = "//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='%s']]";
    String xpathInCartWithVariant = "//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='%s'] and descendant::p[normalize-space()='%s']]";

    public void verifyTitleOfRecommendVariant(String targetProduct, String targetVariant, String recommendVariant) {
        String xpathTitle;

        if (targetVariant.isEmpty()) {
            xpathTitle = String.format(xpathIncart, targetProduct.trim()) + "//div[contains(@class,'information-title')]";
        } else {
            xpathTitle = String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//div[contains(@class,'information-title')]";
        }

        int i = 0;
        waitABit(3000);
        while (!isElementExist(xpathTitle) && i < 5){
            refreshPage();
            waitABit(3000);
            i++;
        }

        scrollIntoElementView(xpathTitle);
        verifyElementText(xpathTitle, recommendVariant);
    }

    public void verifyPriceOfRecommendVariant(String targetProduct, String targetVariant, String price) {
        String _xpathPrice;

        if (targetVariant.isEmpty()) {
            _xpathPrice = String.format(xpathIncart, targetProduct.trim()) + "//div[contains(@class,'offer__price')]";
        } else {
            _xpathPrice = String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//div[contains(@class,'offer__price')]";
        }

        waitABit(3000);
        scrollIntoElementView(_xpathPrice);
        verifyElementText(_xpathPrice, price);
    }

    public void clickBtnAddOnIncart(String targetProduct, String targetVariant) {
        if (targetVariant.isEmpty()) {
            clickOnElement(String.format(xpathIncart, targetProduct.trim()) + "//button[@data-button='add-cart-outline']");
        } else {
            clickOnElement(String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//button[@data-button='add-cart-outline']");
        }
    }

    public void verifyProductName(String productName) {
        verifyElementText("//div[contains(@class,'product-name')]", productName);
    }

    public void verifySelectedRecommendVariant(String selectedVariant) {
        String[] variants = selectedVariant.split("/");
        for (String i : variants) {
            String variant = i.trim();
            if (!variant.equalsIgnoreCase("All over print")) {
                String getValue = getText("//a[contains(@class,'qv-product-option--active') and normalize-space()='" + variant + "']");
                assertThat(variant).isEqualToIgnoringCase(getValue);
            }
        }
    }

    public void checkShowRecommendVariant(String targetProduct, String targetVariant, boolean isShow) {
        String _xpath;
        if (targetVariant.isEmpty()) {
            waitElementToBePresentThenScrollIntoView(String.format(xpathIncart, targetProduct.trim()));
            _xpath = String.format(xpathIncart, targetProduct.trim()) + "//descendant::div[contains(@class,'upsell-cart-offer')]";
        } else {
            waitElementToBePresentThenScrollIntoView(String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()));
            _xpath = String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//descendant::div[contains(@class,'upsell-cart-offer')]";
        }
        verifyElementPresent(_xpath, isShow);
    }

    public void openOffer(String offerName) {
        clickOnElement("//span[normalize-space()='" + offerName + "']");
    }

    public void verifyNotiMessage(String message) {
        try {
            waitForTextToAppear(message, 5000);
        } catch (Exception e) {
            scrollIntoElementView("//div[normalize-space()='" + message + "']");
            waitForTextToAppear(message, 5000);
        }
    }

    public void verifyOfferType(String offerType) {
        verifyElementPresent("//div[contains(@class, 'active offer-type') and descendant::div[normalize-space()='" + offerType + "']]", true);
    }

    public void verifyOfferName(String offerName) {
        verifyElementText("//input[@id='input-offer-name']", offerName);
    }

    public void verifyTypeOfCondition(String blockName, String type) {
        String _xpath = "//div[contains(@class,'usell-block-setup') and descendant::div[text()[normalize-space()='" + blockName + "']]]//descendant::label[descendant::span[normalize-space()='" + type + "']]//input";
        scrollIntoElementView(_xpath);

        boolean isStatus = XH(_xpath).isSelected();
        assertThat(isStatus).isEqualTo(true);
    }

    public void verifyValueSelected(String blockName, String numberProductsSelected) {
        verifyElementText("//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='" + blockName + "']]]//descendant::p", numberProductsSelected);
    }

    public void clickOnAddToCart() {
        clickOnElement("//button[@data-button='add-to-cart' and contains(@class,'upsell-cart-offer')]");
    }

    public void clickOnRecommendProductImage(String targetProduct, String targetVariant) {
        String xpath;
        if (targetVariant.isEmpty()) {
            xpath = String.format(xpathIncart, targetProduct.trim()) + "//div[contains(@class,'upsell-cart-offer__quickview')]";
        } else {
            xpath = String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//div[contains(@class,'upsell-cart-offer__quickview')]";
        }
        hoverThenClickElement(xpath, xpath);
    }

    public void clickOnRecommendProductTitle(String targetProduct, String targetVariant) {
        if (targetVariant.isEmpty()) {
            clickOnElement(String.format(xpathIncart, targetProduct.trim()) + "//descendant::div[contains(@class,'upsell-cart-offer__information-title')]");
        } else {
            clickOnElement(String.format(xpathInCartWithVariant, targetProduct.trim(), targetVariant.trim()) + "//descendant::div[contains(@class,'upsell-cart-offer__information-title')]");
        }
    }

    public void productRecommendAdded(String recommendProduct, String recommendVariant) {
        verifyRecommendProduct(recommendProduct);
        if (!recommendVariant.isEmpty()) {
            verifyRecommendVariant(recommendVariant);
        }
    }

    public void verifyRecommendProduct(String recommendProduct) {
        verifyElementPresent(String.format(xpathIncart, recommendProduct.trim()), true);
    }

    public void verifyRecommendVariant(String recommendVariant) {
        verifyElementPresent("//div[contains(@class,'product-cart__details') and descendant::p[normalize-space()='" + recommendVariant + "']]", true);
    }

    public void verifyIncartShown() {
        String xpath = "//div[contains(@class,'upsell-cart-offer__container')]";
        waitForElementToPresent(xpath);
    }


}