package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

public class ProductImagePages extends CommonLandingPages {
    public ProductImagePages(WebDriver driver) {
        super(driver);
    }

    public void verifyRatio(String ratio) {
        verifyElementPresent("//section[contains(@data-id,'product_image')]//div[contains(@class,'lp-aspect-ratio "+ratio+"')]",true);
    }

    public void verifyOverlayColorImage(String overlayColorImage) {
        verifyStyleLanding("//section[contains(@data-id,'product_image')]//div[contains(@class,'lp-absolute lp-overlay')]",overlayColorImage,0);
    }

    public void verifyProductImagePosition(String productImagePosition) {
        switch (productImagePosition){
            case "Left":
                verifyElementPresent("//section[contains(@data-id,'product_image')]//div[@class='lp-row lp-product-image__blocks']",true);
                break;
            case "Right":
                verifyElementPresent("//section[contains(@data-id,'product_image')]//div[contains(@class,'lp-row-reverse-lg lp-column-reverse')]",true);
                break;
            case "Above":
                verifyElementPresent("//section[contains(@data-id,'product_image')]//div[contains(@class,'lp-items-center lp-flex-column')]",true);
                break;
            case "Below":
                verifyElementPresent("//section[contains(@data-id,'product_image')]//div[contains(@class,'lp-items-center lp-column-reverse')]",true);
                break;
        }
    }
}
