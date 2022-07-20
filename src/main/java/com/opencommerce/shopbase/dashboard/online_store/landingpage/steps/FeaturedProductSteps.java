package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.FeaturedProductPages;
import net.thucydides.core.steps.ScenarioSteps;

public class FeaturedProductSteps extends ScenarioSteps {
    FeaturedProductPages featuredProductPages;

    public void verifyHeading(String section,String heading) {
        featuredProductPages.verifyHeading(section,heading);
    }

    public void verifyShowTitle(String section,boolean showTitle) {        featuredProductPages.verifyShowTitle(section,showTitle);
    }

    public void verifyTitle(String section,String title,boolean showTitle, String product) {        featuredProductPages.verifyTitle(section,title,showTitle,product);
    }

    public void verifyShowPrice(String section,boolean showPrice) {        featuredProductPages.verifyShowPrice(section,showPrice);
    }

    public void verifyShowCompareAtPrice(String section,boolean showCompareAtPrice) {        featuredProductPages.verifyShowCompareAtPrice(section,showCompareAtPrice);
    }

    public void verifyShowSaleTag(String section,boolean showSaleTag) {        featuredProductPages.verifyShowSaleTag(section,showSaleTag);
    } 

    public void verifySaleType(String section,String saleType) {        featuredProductPages.verifySaleType(section,saleType);
    }

    public void verifyShowRatings(String section,boolean showRatings) {        featuredProductPages.verifyShowRatings(section,showRatings);
    }

    public void verifyShowVariantPicker(String section,boolean showVariantPicker) {        featuredProductPages.verifyShowVariantPicker(section,showVariantPicker);
    }

    public void verifyShowQuantity(String section,boolean showQuantity) {        featuredProductPages.verifyShowQuantity(section,showQuantity);
    }

    public void verifyDefaultQuantity(String section,String defaultQuantity) {        featuredProductPages.verifyDefaultQuantity(section,defaultQuantity);
    }

    public void verifyButtonLabel(String section,String buttonLabel) {        featuredProductPages.verifyButtonLabel(section,buttonLabel);
    }

    public void verifyDestination(String section,String destination) {
        featuredProductPages.clickOnButton(section);
        featuredProductPages.verifyDestination(section,destination);
    }

    public void verifyShowDescription(String section,boolean showDescription) {        featuredProductPages.verifyShowDescription(section,showDescription);
    }

    public void verifyShowTrustBadge(String section,boolean showTrustBadge) {        featuredProductPages.verifyShowTrustBadge(section,showTrustBadge);
    }

    public void verifyType(String section,String type) {        featuredProductPages.verifyType(section,type);
    }

    public void verifyCustomColor(String section,String customColor) {        featuredProductPages.verifyCustomColor(section,customColor);
    }

    public void verifyStartColor(String section,String startColor) {        featuredProductPages.verifyStartColor(section,startColor);
    }

    public void verifyEndColor(String section,String endColor) {        featuredProductPages.verifyEndColor(section,endColor);
    }

    public void verifyDirection(String section,String direction) {        featuredProductPages.verifyDirection(section,direction);
    }

    public void verifyColorOverlayBackground(String section,String colorOverlayBackground) {        featuredProductPages.verifyColorOverlayBackground(section,colorOverlayBackground);
    }

    public void verifyHeadingColor(String section,String headingColor,String heading) {        featuredProductPages.verifyHeadingColor(section,headingColor,heading);
    }

    public void verifyHeadingFont(String section,String headingFont,String heading) {        featuredProductPages.verifyHeadingFont(section,headingFont,heading);
    }

    public void verifyHeadingSize(String section,String headingSize, String heading) {        featuredProductPages.verifyHeadingSize(section,headingSize,heading);
    }

    public void verifyAlignmentHeading(String section,String alignmentHeading, String heading) {        featuredProductPages.verifyAlignmentHeading(section,alignmentHeading,heading);
    }

    public void verifyTitleColor(String section,String titleColor, boolean showTitle) {        featuredProductPages.verifyTitleColor(section,titleColor,showTitle);
    }

    public void verifyTitleFont(String section,String titleFont, boolean showTitle) {        featuredProductPages.verifyTitleFont(section,titleFont,showTitle);
    }

    public void verifyTitleSize(String section,String titleSize, boolean showTitle) {        featuredProductPages.verifyTitleSize(section,titleSize,showTitle);
    }

    public void verifyTitleAlignment(String section,String titleAlignment, boolean showTitle) {        featuredProductPages.verifyTitleAlignment(section,titleAlignment,showTitle);
    }

    public void verifyPriceColor(String section,String priceColor, boolean showPrice) {        featuredProductPages.verifyPriceColor(section,priceColor,showPrice);
    }

    public void verifyPriceSize(String section,String priceSize, boolean showPrice) {        featuredProductPages.verifyPriceSize(section,priceSize,showPrice);
    }

    public void verifyCompareAtPriceColor(String section,String compareAtPriceColor, boolean showCompareAtPrice) {        featuredProductPages.verifyCompareAtPriceColor(section,compareAtPriceColor,showCompareAtPrice);
    }

    public void verifyCompareAtPriceSize(String section,String compareAtPriceSize, boolean showCompareAtPrice) {        featuredProductPages.verifyCompareAtPriceSize(section,compareAtPriceSize,showCompareAtPrice);
    }

    public void verifySaleTagBackground(String section,String saleTagBackground, boolean showSaleTag) {        featuredProductPages.verifySaleTagBackground(section,saleTagBackground,showSaleTag);
    }

    public void verifySaleTagLabelColor(String section,String saleTagLabelColor, boolean showSaleTag) {        featuredProductPages.verifySaleTagLabelColor(section,saleTagLabelColor,showSaleTag);
    }

    public void verifySaleTagSize(String section,String saleTagSize, boolean showSaleTag) {        featuredProductPages.verifySaleTagSize(section,saleTagSize,showSaleTag);
    }

    public void verifyPriceFont(String section,String priceFont, boolean showPrice) {        featuredProductPages.verifyPriceFont(section,priceFont,showPrice);
    }

    public void verifyPriceAlignment(String section,String priceAlignment, boolean showPrice) {        featuredProductPages.verifyPriceAlignment(section,priceAlignment,showPrice);
    }

    public void verifyRatingIconColor(String section,String ratingIconColor,boolean showRatings) {        featuredProductPages.verifyRatingIconColor(section,ratingIconColor,showRatings);
    }

    public void verifyRatingIconSize(String section,String ratingIconSize,boolean showRatings) {        featuredProductPages.verifyRatingIconSize(section,ratingIconSize,showRatings);
    }

    public void verifyRatingAlignment(String section,String ratingAlignment,boolean showRatings) {        featuredProductPages.verifyRatingAlignment(section,ratingAlignment,showRatings);
    }

    public void verifyVariantLabelColor(String section,String variantLabelColor,boolean showVariantPicker) {        featuredProductPages.verifyVariantLabelColor(section,variantLabelColor,showVariantPicker);
    }

    public void verifyVariantLabelFont(String section,String variantLabelFont,boolean showVariantPicker) {        featuredProductPages.verifyVariantLabelFont(section,variantLabelFont,showVariantPicker);
    }

    public void verifyVariantLabelSize(String section,String variantLabelSize,boolean showVariantPicker) {        featuredProductPages.verifyVariantLabelSize(section,variantLabelSize,showVariantPicker);
    }

    public void verifyVariantPickerAlignment(String section,String variantPickerAlignment,boolean showVariantPicker) {        featuredProductPages.verifyVariantPickerAlignment(section,variantPickerAlignment,showVariantPicker);
    }

    public void verifyButtonBackgroundColor(String section,String buttonBackgroundColor,String buttonLabel) {        featuredProductPages.verifyButtonBackgroundColor(section,buttonBackgroundColor,buttonLabel);
    }

    public void verifyButtonBorderColor(String section,String buttonBorderColor,String buttonLabel) {        featuredProductPages.verifyButtonBorderColor(section,buttonBorderColor,buttonLabel);
    }

    public void verifyButtonLabelColor(String section,String buttonLabelColor,String buttonLabel) {        featuredProductPages.verifyButtonLabelColor(section,buttonLabelColor,buttonLabel);
    }

    public void verifyButtonLabelFont(String section,String buttonLabelFont,String buttonLabel) {        featuredProductPages.verifyButtonLabelFont(section,buttonLabelFont,buttonLabel);
    }

    public void verifyButtonLabelSize(String section,String buttonLabelSize,String buttonLabel) {        featuredProductPages.verifyButtonLabelSize(section,buttonLabelSize,buttonLabel);
    }

    public void verifyDescriptionColor(String section,String descriptionColor,boolean showDescription) {        featuredProductPages.verifyDescriptionColor(section,descriptionColor,showDescription);
    }

    public void verifyDescriptionFont(String section,String descriptionFont,boolean showDescription) {        featuredProductPages.verifyDescriptionFont(section,descriptionFont,showDescription);
    }

    public void verifyDescriptionSize(String section,String descriptionSize,boolean showDescription) {        featuredProductPages.verifyDescriptionSize(section,descriptionSize,showDescription);
    }

    public void verifyBorderTopColor(String section,String borderTopColor) {        featuredProductPages.verifyBorderTopColor(section,borderTopColor);
    }

    public void verifyBorderTopSize(String section,String borderTopSize) {        featuredProductPages.verifyBorderTopSize(section,borderTopSize);
    }

    public void verifyBorderBottomColor(String section,String borderBottomColor) {        featuredProductPages.verifyBorderBottomColor(section,borderBottomColor);
    }

    public void verifyBorderBottomSize(String section,String borderBottomSize) {        featuredProductPages.verifyBorderBottomSize(section,borderBottomSize);
    }

    public void verifyProductImagePosition(String section,String productImagePosition) {
        featuredProductPages.verifyProductImagePosition(section,productImagePosition);
    }

    public void verifyMinHeight(String section,String minHeight) {        featuredProductPages.verifyMinHeight(section,minHeight);
    }

    public void verifyMaxWidth(String section,String maxWidth) {        featuredProductPages.verifyMaxWidth(section,maxWidth);
    }

    public void verifyCustomWidth(String section,String customWidth) {        featuredProductPages.verifyCustomWidth(section,customWidth);
    }

    public void verifyProductDefault(String section,String text) { featuredProductPages.verifyProductDefault(section, text);
    }

    public void verifyTypeLayout(String section, String typeLayout) {
        featuredProductPages.verifyTypeLayout(section,typeLayout);
    }
}
