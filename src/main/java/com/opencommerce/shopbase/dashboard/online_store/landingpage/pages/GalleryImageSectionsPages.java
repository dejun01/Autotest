package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class GalleryImageSectionsPages extends SBasePageObject {
    public GalleryImageSectionsPages(WebDriver driver) {
        super(driver);
    }
    String xpath = "//section[contains(@data-id,'gallery_image')]";
    public void verifynoSpacing(boolean noSpacing) {
        verifyElementPresent(xpath+"//div[contains(@class,'lp-no-gutters')]",noSpacing);
    }

    public void verifytTitle(String title) {
    }

    public void verifyImage(String image) {
    }

    public void verifyType(String type) {
    }

    public void verifyCustomColor(String customColor) {
    }

    public void verifyStartColor(String startColor) {
    }

    public void verifyEndColor(String endColor) {
    }

    public void verifyDirection(String direction) {
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
    }

    public void verifyTitleColor(String titleColor) {
    }

    public void verifyTitleFont(String titleFont) {
    }

    public void verifyTitleSize(String titleSize) {
    }

    public void verifyBorderTopColor(String borderTopColor) {
    }

    public void verifyBorderTopSize(String borderTopSize) {
    }

    public void verifyBorderBottomColor(String borderBottomColor) {
    }

    public void verifyBorderBottomSize(String borderBottomSize) {
    }

    public void verifyImageRatio(String imageRatio) {
    }

    public void verifyAlignment(String alignment) {
    }

    public void verifyMinHeight(String minHeight) {
    }

    public void verifyMaxWidth(String maxWidth) {
    }

    public void verifyCustomWidth(String customWidth) {
    }
}
