package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.FeaturedPromotionSectionPages;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.FeaturedPromotionPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class FeaturedPromotionSectionSteps extends ScenarioSteps {
    FeaturedPromotionSectionPages featuredPromotionSectionPages;

    @Step
    public void verifyHeading(String heading) {
        featuredPromotionSectionPages.verifyHeading(heading);
    }

    @Step
    public void verifyType(String type) {
        featuredPromotionSectionPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        featuredPromotionSectionPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        featuredPromotionSectionPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        featuredPromotionSectionPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        featuredPromotionSectionPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        featuredPromotionSectionPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        featuredPromotionSectionPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyHeadingColor(String headingColor) {
        featuredPromotionSectionPages.verifyHeadingColor(headingColor);
    }

    @Step
    public void verifyHeadingFont(String headingFont) {
        featuredPromotionSectionPages.verifyHeadingFont(headingFont);
    }

    @Step
    public void verifyHeadingSize(String headingSize) {
        featuredPromotionSectionPages.verifyHeadingSize(headingSize);
    }

    @Step
    public void verifyAlignmentHeading(String alignmentHeading) {
        featuredPromotionSectionPages.verifyAlignmentHeading(alignmentHeading);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        featuredPromotionSectionPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        featuredPromotionSectionPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        featuredPromotionSectionPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyAlignmentTitle(String alignmentTitle) {
        featuredPromotionSectionPages.verifyAlignmentTitle(alignmentTitle);
    }

    @Step
    public void verifyBodyColor(String bodyColor) {
        featuredPromotionSectionPages.verifyBodyColor(bodyColor);
    }

    @Step
    public void verifyBodyFont(String bodyFont) {
        featuredPromotionSectionPages.verifyBodyFont(bodyFont);
    }

    @Step
    public void verifyBodySize(String bodySize) {
        featuredPromotionSectionPages.verifyBodySize(bodySize);
    }

    @Step
    public void verifyAlignmentBody(String alignmentBody) {
        featuredPromotionSectionPages.verifyAlignmentBody(alignmentBody);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        featuredPromotionSectionPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize) {
        featuredPromotionSectionPages.verifyBorderTopSize(borderTopSize);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        featuredPromotionSectionPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        featuredPromotionSectionPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyPosition(String position) {
        featuredPromotionSectionPages.verifyPosition(position);
    }

    @Step
    public void verifyAlignment(String alignment) {
        featuredPromotionSectionPages.verifyAlignment(alignment);
    }

    @Step
    public void verifyMinHeight(String minHeight) {
        featuredPromotionSectionPages.verifyMinHeight(minHeight);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        featuredPromotionSectionPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        featuredPromotionSectionPages.verifyCustomWidth(customWidth);
    }

    @Step
    public void verifyIcon(String icon, int index) {
        featuredPromotionSectionPages.verifyIcon(icon, index);
    }

    @Step
    public void verifyNackgroundImage(String backgroundImage, int index) {
        featuredPromotionSectionPages.verifyNackgroundImage(backgroundImage, index);
    }

    @Step
    public void verifyTitle(String title, int index) {
        featuredPromotionSectionPages.verifyTitle(title, index);
    }

    @Step
    public void verifyBody(String body, int index) {
        featuredPromotionSectionPages.verifyBody(body, index);
    }
}
