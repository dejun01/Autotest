package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductWidgetPage extends SBasePageObject {
    public ProductWidgetPage(WebDriver driver) {
        super(driver);
    }

    public void scrollToFooter() {
        waitElementToBePresentThenScrollIntoView("//*[@data-id='footer']");
    }

    String productWidget = "//div[contains(@class,'upsell-product-widget__carousel')][child::*[contains(text(),'%s')]]";

    public void verifyProductWidgetShown(String widgetName, boolean isShow) {
        verifyElementPresent(String.format(productWidget, widgetName), isShow);
    }

    public boolean productWidgetIsShown(String widgetName) {
        return isElementExist(String.format(productWidget, widgetName));
    }

    String xpathHeader = productWidget + "//h2";

    public void verifyFontHeader(String widgetName, String fontHeader) {
        verifyFrontStyleByXpath(String.format(xpathHeader, widgetName), fontHeader);
    }


    String xpathProductName = productWidget + "//div[contains(@class,'upsell-widget-product__name')]/a";

    public void verifyFontProductName(String widgetName, String fontProductName) {
        verifyFrontStyleByXpath(String.format(xpathProductName, widgetName), fontProductName);
    }

    String xpathProductPrice = productWidget + "//div[contains(@class,'upsell-widget-product__price upsell-color-price')]";

    public void verifyFontProductPrice(String widgetName, String fontProductPrice) {
        verifyFrontStyleByXpath(String.format(xpathProductPrice, widgetName), fontProductPrice);
    }

    String xpathButtonAddTocart = productWidget + "//div[@role='tabpanel'][1]";

    public void verifyFontButtonAddCart(String widgetName, String sCallToActionText) {
        if (!sCallToActionText.isEmpty())
            verifyFrontStyleByXpath(String.format(xpathButtonAddTocart, widgetName) + "//button[contains(@class,'upsell-widget-product__add-cart')]", sCallToActionText);

    }

    public void verifyBtnAddToCartShown(String widgetName, boolean isTurnOnAddCartButton) {
        hoverOnElement(String.format(xpathButtonAddTocart, widgetName) + "//img");
        verifyElementPresent(String.format(xpathButtonAddTocart, widgetName) + "//button[contains(@class,'upsell-widget-product__add-cart')]", isTurnOnAddCartButton);
    }

    public void verifyFrontStyleByXpath(String xpath, String value) {
        if (!value.isEmpty()) {
            if (value.contains(">")) {
                String[] t = value.split(">");
                verifyCssValueByElement(xpath, "font-size", t[0]);
                verifyStyleFont(xpath, t[1]);
            } else {
                verifyCssValueByElement(xpath, "font-size", value);
            }
        }
    }


    public void verifyStyleFont(String xpath, String value) {
        List<String> listStyleSelected = convertStyle(value);

        List<String> styles = asList("text-align", "font-weight", "font-style", "text-decoration");
        List<String> actual = new ArrayList<>();
        for (String st : styles) {
            String vl = XH(xpath).getCssValue(st);
            if (!vl.isEmpty()) {
                if (vl.contains("underline")) {
                    vl = "underline";
                }
                actual.add(vl);
            }
        }
        assertThat(actual).containsAll(listStyleSelected);

    }

    private List<String> convertStyle(String value) {
        List<String> listStyleSelected = new ArrayList<>();
        String[] styles = value.split(",");
        for (String style : styles) {
            style = changeCssValue(style);
            listStyleSelected.add(style);
        }
        return listStyleSelected;
    }

    public int getNumberOfProductsToBeShown(String widgetName) {
        return countElementByXpath(String.format(productWidget, widgetName) + "//div[@role='tabpanel']");
    }

    public int getNumberOfProductsPerSlide(String widgetName) {
        return countElementByXpath(String.format(productWidget, widgetName) + "//div[@role='tabpanel'][contains(@class,'active')]");
    }

    public void switchToNextSlide(String widgetName) {
        clickOnElementByJs(String.format(productWidget, widgetName) + "//button[@aria-label='Next page']");
    }

    public void switchToPreSlide(String widgetName) {
        waitForEverythingComplete();
        clickOnElementByJs(String.format(productWidget, widgetName) + "//button[@aria-label='Previous page']");

    }

    public List<String> getListProductOnWidget(String widgetName) {
        String xpath = String.format(xpathProductName, widgetName);
        return getListText(xpath);

    }

    public void addToCartProductIndex(String widgetName, String index) {
        String xpathHover = "(" + String.format(productWidget, widgetName) + "//div[@role='tabpanel'])[" + index + "]";
        String xpathAddToCart = xpathHover + "//button[contains(@class,'upsell-widget-product__add-cart')]";
        scrollIntoElementView(xpathHover);
        scrollIntoElementView(xpathAddToCart);
        hoverOnElement(xpathHover);
        clickOnElementByJs(xpathAddToCart);
    }


    public void addToCartProductName(String widgetName, String productName) {
        String xpathHover = String.format(productWidget, widgetName) + "//div[@role='tabpanel' and descendant::a[text()[normalize-space()='" + productName + "']]]";
        String xpathAddToCart = xpathHover + "//button[contains(@class,'upsell-widget-product__add-cart')]";
        scrollIntoElementView(xpathHover);
        scrollIntoElementView(xpathAddToCart);
        hoverOnElement(xpathHover);
        clickOnElementByJs(xpathAddToCart);
    }
}

