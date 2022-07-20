package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;

public class ProductListPages extends CommonLandingPages {
    public ProductListPages(WebDriver driver) {
        super(driver);
    }
    String xpath = "//section[contains(@data-id,'product_list')]";
    public void verifyHighlightProduct(boolean highlightThisProduct, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-product-list__highlight')])["+index+"]",highlightThisProduct);
    }

    public void verifyHighlightText(String highlightText, int index) {
        if(!highlightText.isEmpty()){
            verifyElementText("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-product-list__highlight')]//span)["+index+"]",highlightText);
        }
    }

    public void verifyShowTitle(boolean showTitle, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//h3)["+index+"]", showTitle);
    }

    public void verifyTitle(String title, boolean showTitle, String product, int index) {
        if (showTitle) {
            if (title.isEmpty()) {
                verifyElementText("(//section[contains(@data-id,'product_list')]//h3)["+index+"]", product);
            } else {
                verifyElementText("(//section[contains(@data-id,'product_list')]//h3)["+index+"]", title);
            }
        }
    }

    public void verifyShowPrice(boolean showPrice, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//span[contains(@class,'lp-has-text-weight-bold')])["+index+"]",showPrice);
    }

    public void verifyShowCompareAtPrice(boolean showCompareAtPrice, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//span[contains(@class,'lp-decoration-line-through')]//del)["+index+"]", showCompareAtPrice);
    }

    public void verifyShowSaleTag(boolean showSaleTag, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//span[contains(@class,'lp-featured-product__sale-msg')])["+index+"]", showSaleTag);
    }

    public void verifySaleType(String saleType, int index) {
        String saleText = "";
        switch (saleType) {
            case "Amount":
                saleText = "$";
                break;
            case "Percentage":
                saleText = "%";
                break;
        }
        String saleTextAR = getTextByJS("(//section[contains(@data-id,'product_list')]//span[contains(@class,'lp-featured-product__sale-msg')])["+index+"]");
        assertThat(saleTextAR).contains(saleText);
    }

    public void verifyShowRatings(boolean showRatings, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-mb16 lp-flex lp')])["+index+"]", showRatings);
    }

    public void verifyShowVariantPicker(boolean showVariantPicker, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//span[contains(@class,'featured-product__variant-label')])["+index+"]", showVariantPicker);
    }

    public void verifyShowQuantity(boolean showQuantity, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-featured-product__quantity')])["+index+"]", showQuantity);
    }

    public void verifyDefaultQuantity(String defaultQuantity, int index) {
        String quantityAR = getValue("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-featured-product__quantity')]//input)["+index+"]");
        assertThat(quantityAR).contains(defaultQuantity);

    }

    public void verifyButtonLabel(String buttonLabel, int index) {
        if (!buttonLabel.isEmpty()) {
            verifyElementText("(//section[contains(@data-id,'product_list')]//div[contains(@class,'lp-flex lp-w-100')]//button)["+index+"]", buttonLabel);
        }
    }

    public void verifyDestination(String destination, int index) {
        String shop = LoadObject.getProperty("shop");
        switchToTheFirstTab();
        waitABit(5000);
        String urlAR = getCurrentUrl();
        assertThat(urlAR).contains("https://" + shop + destination);

    }

    public void verifyShowTrustBadge(boolean showTrustBadge, int index) {
        verifyElementPresent("(//section[contains(@data-id,'product_list')]//div[contains(@class,'featured-product__trust-badge')])["+index+"]", showTrustBadge);
    }
}
