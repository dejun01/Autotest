package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FeaturedProductPages extends CommonLandingPages {
    public FeaturedProductPages(WebDriver driver) {
        super(driver);
    }

    public String getXpath(String section) {
        String xPath = "";
        switch (section) {
            case "Featured Product":
                xPath = "//section[contains(@data-id,'featured_product')]";
                break;
            case "Product Image":
                xPath = "//section[contains(@data-id,'product_image')]";
                break;
            case "Product Rich Text":
                xPath ="//section[contains(@data-id,'product_richtext')]";
                break;
            case "Product list":
                xPath = "//section[contains(@data-id,'product_list')]";
                break;
        }
        return xPath;
    }

    public void verifyHeading(String section, String heading) {
        if (!heading.isEmpty()) {
            verifyElementText(getXpath(section) + "//h2", heading);
        }
    }

    public void verifyShowTitle(String section, boolean showTitle) {
        verifyElementPresent(getXpath(section) + "//h3", showTitle);
    }

    public void verifyTitle(String section, String title, boolean showTitle, String product) {
        if (showTitle) {
            if (title.isEmpty()) {
                verifyElementText(getXpath(section) + "]//h3", product);
            } else {
                verifyElementText(getXpath(section) + "//h3", title);
            }
        }
    }

    public void verifyShowPrice(String section, boolean showPrice) {
        verifyElementPresent(getXpath(section) + "//span[contains(@class,'lp-has-text-weight-bold')]", showPrice);
    }

    public void verifyShowCompareAtPrice(String section, boolean showCompareAtPrice) {
        verifyElementPresent(getXpath(section) + "//span[contains(@class,'lp-decoration-line-through')]//del", showCompareAtPrice);
    }

    public void verifyShowSaleTag(String section, boolean showSaleTag) {
        verifyElementPresent(getXpath(section) + "//span[contains(@class,'lp-featured-product__sale-msg')]", showSaleTag);
    }

    public void verifySaleType(String section, String saleType) {
        String saleText = "";
        switch (saleType) {
            case "Amount":
                saleText = "$";
                break;
            case "Percentage":
                saleText = "%";
                break;
        }
        String saleTextAR = getTextByJS(getXpath(section) + "//span[contains(@class,'lp-featured-product__sale-msg')]");
        assertThat(saleTextAR).contains(saleText);
    }

    public void verifyShowRatings(String section, boolean showRatings) {
        verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-mb16 lp-flex lp')]", showRatings);
    }

    public void verifyShowVariantPicker(String section, boolean showVariantPicker) {
        verifyElementPresent(getXpath(section) + "//span[contains(@class,'featured-product__variant-label')]", showVariantPicker);
    }

    public void verifyShowQuantity(String section, boolean showQuantity) {
        verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-featured-product__quantity')]", showQuantity);
    }

    public void verifyDefaultQuantity(String section, String defaultQuantity) {
        String quantityAR = getValue(getXpath(section) + "//div[contains(@class,'lp-featured-product__quantity')]//input");
        assertThat(quantityAR).contains(defaultQuantity);
    }

    public void verifyButtonLabel(String section, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyElementText(getXpath(section) + "//div[contains(@class,'lp-flex lp-w-100')]//button", buttonLabel);
        }
    }

    public void verifyDestination(String section, String destination) {
        String shop = LoadObject.getProperty("shop");
        switchToTheFirstTab();
        waitABit(5000);
        String urlAR = getCurrentUrl();
        assertThat(urlAR).contains("https://" + shop + destination);
    }

    public void verifyShowDescription(String section, boolean showDescription) {
        verifyElementPresent(getXpath(section) + "//div[contains(@class,'featured-product__description')]", showDescription);
    }

    public void verifyShowTrustBadge(String section, boolean showTrustBadge) {
        verifyElementPresent(getXpath(section) + "//div[contains(@class,'featured-product__trust-badge')]", showTrustBadge);
    }

    public void verifyType(String section, String typeBackground) {
        verifyTypeBackground(getXpath(section), typeBackground);
    }

    public void verifyCustomColor(String section, String customColor) {
        verifyStyleLanding(getXpath(section), convertColorHexToRGB(customColor), 4);
    }

    public void verifyStartColor(String section, String startColor) {
        verifyStyleLanding(getXpath(section), convertColorHexToRGB(startColor), 4);
    }

    public void verifyEndColor(String section, String endColor) {
        verifyStyleLanding(getXpath(section), convertColorHexToRGB(endColor), 4);
    }

    public void verifyDirection(String section, String directionBackground) {
        verifyDirectionBackground(getXpath(section), directionBackground);
    }

    public void verifyColorOverlayBackground(String section, String colorOverlayBackground) {
        verifyStyleLanding(getXpath(section) + "//div[contains(@class,'lp-overlay')]", convertColorHexToRGB(colorOverlayBackground), 0);
    }

    public void verifyHeadingColor(String section, String headingColor, String heading) {
        if (!heading.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//h2", convertColorHexToRGB(headingColor), 0);
        }
    }

    public void verifyHeadingFont(String section, String headingFont, String heading) {
        if (!heading.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//h2", headingFont, 1);
        }
    }

    public void verifyHeadingSize(String section, String headingSize, String heading) {
        if (!heading.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//h2", headingSize + "px", 2);
        }
    }

    public void verifyAlignmentHeading(String section, String alignmentHeading, String heading) {
        if (!heading.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//h2", alignmentHeading, 3);
        }
    }

    public void verifyTitleColor(String section, String titleColor, boolean showTitle) {
        verifyStyleLandingShow(getXpath(section) + "//h3", convertColorHexToRGB(titleColor), showTitle, 0);
    }

    public void verifyTitleFont(String section, String titleFont, boolean showTitle) {
        verifyStyleLandingShow(getXpath(section) + "//h3", titleFont, showTitle, 1);
    }

    public void verifyTitleSize(String section, String titleSize, boolean showTitle) {
        verifyStyleLandingShow(getXpath(section) + "//h3", titleSize + "px", showTitle, 2);
    }

    public void verifyTitleAlignment(String section, String titleAlignment, boolean showTitle) {
        verifyStyleLandingShow(getXpath(section) + "//h3", titleAlignment, showTitle, 3);
    }

    public void verifyPriceColor(String section, String priceColor, boolean showPrice) {
        verifyStyleLandingShow(getXpath(section) + "//p[child::span[contains(@class,'has-text-weight-bold')]]", convertColorHexToRGB(priceColor), showPrice, 0);

    }

    public void verifyPriceSize(String section, String priceSize, boolean showPrice) {
        verifyStyleLandingShow(getXpath(section) + "//p[child::span[contains(@class,'has-text-weight-bold')]]", priceSize + "px", showPrice, 2);
    }

    public void verifyCompareAtPriceColor(String section, String compareAtPriceColor, boolean showCompareAtPrice) {
        verifyStyleLandingShow(getXpath(section) + "//span[contains(@class,'decoration-line-through')]", convertColorHexToRGB(compareAtPriceColor), showCompareAtPrice, 0);
    }

    public void verifyCompareAtPriceSize(String section, String compareAtPriceSize, boolean showCompareAtPrice) {
        verifyStyleLandingShow(getXpath(section) + "//span[contains(@class,'decoration-line-through')]", compareAtPriceSize + "px", showCompareAtPrice, 1);
    }

    public void verifySaleTagBackground(String section, String saleTagBackground, boolean showSaleTag) {
        verifyStyleLandingShow(getXpath(section) + "//span[contains(@class,'featured-product__sale-msg')]", convertColorHexToRGB(saleTagBackground), showSaleTag, 4);
    }

    public void verifySaleTagLabelColor(String section, String saleTagLabelColor, boolean showSaleTag) {
        verifyStyleLandingShow(getXpath(section) + "//span[contains(@class,'featured-product__sale-msg')]", convertColorHexToRGB(saleTagLabelColor), showSaleTag, 0);
    }

    public void verifySaleTagSize(String section, String saleTagSize, boolean showSaleTag) {
        verifyStyleLandingShow(getXpath(section) + "//span[contains(@class,'featured-product__sale-msg')]", saleTagSize + "px", showSaleTag, 1);
    }

    public void verifyPriceFont(String section, String priceFont, boolean showPrice) {
        verifyStyleLandingShow(getXpath(section) + "//p[child::span[contains(@class,'has-text-weight-bold')]]", priceFont, showPrice, 1);
    }

    public void verifyPriceAlignment(String section, String priceAlignment, boolean showPrice) {
        verifyStyleLandingShow(getXpath(section) + "//p[child::span[contains(@class,'has-text-weight-bold')]]", priceAlignment, showPrice, 3);
    }

    public void verifyRatingIconColor(String section, String ratingIconColor, boolean showRatings) {
        verifyStyleLandingShow(getXpath(section) + "//div[contains(@class,'lp-mb16 lp-flex lp')]//*[@class='lp-mr2'][1]", convertColorHexToRGB(ratingIconColor), showRatings, 0);
    }

    public void verifyRatingIconSize(String section, String ratingIconSize, boolean showRatings) {
        if (!ratingIconSize.isEmpty() && showRatings) {
            verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-mb16 lp-flex lp')]//*[@height='" + ratingIconSize + "'][1]", true);
        }
    }

    public void verifyRatingAlignment(String section, String ratingAlignment, boolean showRatings) {
        if (showRatings) {
            verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-flex lp-justify-" + ratingAlignment + "')]", true);
        }
    }

    public void verifyVariantLabelColor(String section, String variantLabelColor, boolean showVariantPicker) {
        verifyStyleLandingShow(getXpath(section) + "//div[child::div[@class='lp-mb16']]", convertColorHexToRGB(variantLabelColor), showVariantPicker, 0);
    }

    public void verifyVariantLabelFont(String section, String variantLabelFont, boolean showVariantPicker) {
        verifyStyleLandingShow(getXpath(section) + "//div[child::div[@class='lp-mb16']]", variantLabelFont, showVariantPicker, 1);
    }

    public void verifyVariantLabelSize(String section, String variantLabelSize, boolean showVariantPicker) {
        verifyStyleLandingShow(getXpath(section) + "//div[child::div[@class='lp-mb16']]", variantLabelSize + "px", showVariantPicker, 2);
    }

    public void verifyVariantPickerAlignment(String section, String variantPickerAlignment, boolean showVariantPicker) {
        verifyStyleLandingShow(getXpath(section) + "//div[child::div[@class='lp-mb16']]", variantPickerAlignment, showVariantPicker, 3);
    }

    public void verifyButtonBackgroundColor(String section, String buttonBackgroundColor, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//button[contains(@class,'featured-product__button')]", convertColorHexToRGB(buttonBackgroundColor), 6);
        }
    }

    public void verifyButtonBorderColor(String section, String buttonBorderColor, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//button[contains(@class,'featured-product__button')]", convertColorHexToRGB(buttonBorderColor), 7);
        }
    }

    public void verifyButtonLabelColor(String section, String buttonLabelColor, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//button[contains(@class,'featured-product__button')]", convertColorHexToRGB(buttonLabelColor), 0);
        }
    }

    public void verifyButtonLabelFont(String section, String buttonLabelFont, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//button[contains(@class,'featured-product__button')]", buttonLabelFont, 1);
        }
    }

    public void verifyButtonLabelSize(String section, String buttonLabelSize, String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyStyleLanding(getXpath(section) + "//button[contains(@class,'featured-product__button')]", buttonLabelSize + "px", 2);
        }
    }

    public void verifyDescriptionColor(String section, String descriptionColor, boolean showDescription) {
        verifyStyleLandingShow(getXpath(section) + "//div[contains(@class,'featured-product__description')]//div[child::p]", convertColorHexToRGB(descriptionColor), showDescription, 0);
    }

    public void verifyDescriptionFont(String section, String descriptionFont, boolean showDescription) {
        verifyStyleLandingShow(getXpath(section) + "//div[contains(@class,'featured-product__description')]//div[child::p]", descriptionFont, showDescription, 1);
    }

    public void verifyDescriptionSize(String section, String descriptionSize, boolean showDescription) {
        verifyStyleLandingShow(getXpath(section) + "//div[contains(@class,'featured-product__description')]//div[child::p]", descriptionSize + "px", showDescription, 2);
    }

    public void verifyBorderTopColor(String section, String borderTopColor) {
        verifyStyleLanding(getXpath(section), convertColorHexToRGB(borderTopColor), 0);
    }

    public void verifyBorderTopSize(String section, String borderTopSize) {
        verifyStyleLanding(getXpath(section), borderTopSize + "px", 0);
    }

    public void verifyBorderBottomColor(String section, String borderBottomColor) {
        verifyStyleLanding(getXpath(section), convertColorHexToRGB(borderBottomColor), 1);
    }

    public void verifyBorderBottomSize(String section, String borderBottomSize) {
        verifyStyleLanding(getXpath(section), borderBottomSize + "px", 1);
    }

    public void verifyProductImagePosition(String section, String productImagePosition) {
        switch ((productImagePosition)) {
            case "Left":
                verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-flex-row-reverse')]", false);
                break;
            case "Above":
                verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-col-12 lp-col-lg-6')]//div[@class='lp-col-12']", true);
                break;
            case "Right":
                verifyElementPresent(getXpath(section) + "//div[contains(@class,'lp-flex-row-reverse')]", true);
                break;
        }
    }

    public void verifyMinHeight(String section, String minHeight) {
        verifyStyleLanding(getXpath(section), minHeight, 5);
    }

    public void verifyMaxWidth(String section, String maxWidth) {
        verifyMaxWidthBackground(getXpath(section), maxWidth);
    }

    public void verifyCustomWidth(String section, String customWidth) {
        verifyStyleLanding(getXpath(section) + "//div[contains(@style,'max-width')]", customWidth, 0);
    }

    public void verifyProductDefault(String section, String text) {
        String act = getAttributeValue(getXpath(section) + "//img", "src");
        assertThat(act).startsWith("data:image/png");
    }

    public void clickOnButton(String section) {
        clickOnElement(getXpath(section) + "//div[contains(@class,'lp-flex lp-w-100')]//button");
    }

    public void verifyTypeLayout(String section, String typeLayout) {
        switch (typeLayout){
            case "Column":
                verifyElementPresent(getXpath(section)+"//div[contains(@class,'lp-align-start lp-justify-space-between')]",true);
                break;
            case "Row":
                verifyElementPresent(getXpath(section)+"//div[contains(@class,'lp-z-10 lp-justify-space-between')]",true);
                break;
        }
    }
}
