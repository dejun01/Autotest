package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.PAQsSectionPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class PAQsSectionSteps extends ScenarioSteps {
    PAQsSectionPages paQsSectionPages;

    @Step
    public void verifyHeading(String heading) {
        paQsSectionPages.verifyHeading(heading);
    }

    @Step
    public void verifyOpenTheFirstContentByDefault(boolean openTheFirstContentByDefault) {
        paQsSectionPages.verifyOpenTheFirstContentByDefault(openTheFirstContentByDefault);
    }

    @Step
    public void verifyType(String type) {
        paQsSectionPages.verifyType(type);
    }

    @Step
    public void verifyTypeBackground(String typeBackground) {
        paQsSectionPages.verifyTypeBackground(typeBackground);
    }

    @Step
    public void verifyCustomColorBackground(String customColorBackground) {
        paQsSectionPages.verifyCustomColorBackground(customColorBackground);
    }

    @Step
    public void verifyStartColorBackground(String startColorBackground) {
        paQsSectionPages.verifyStartColorBackground(startColorBackground);
    }

    @Step
    public void verifyEndColorBackground(String endColorBackground) {
        paQsSectionPages.verifyEndColorBackground(endColorBackground);
    }

    @Step
    public void verifyDirectionBackground(String directionBackground) {
        paQsSectionPages.verifyDirectionBackground(directionBackground);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        paQsSectionPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        paQsSectionPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyHeadingColor(String headingColor) {
        paQsSectionPages.verifyHeadingColor(headingColor);
    }

    @Step
    public void verifyHeadingFont(String headingFront) {
        paQsSectionPages.verifyHeadingFont(headingFront);
    }

    @Step
    public void verifyHeadingSize(String headingSize) {
        paQsSectionPages.verifyHeadingSize(headingSize);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        paQsSectionPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        paQsSectionPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        paQsSectionPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyBodyColor(String bodyColor, boolean openTheFirstContentByDefault) {
        paQsSectionPages.verifyBodyColor(bodyColor, openTheFirstContentByDefault);
    }

    @Step
    public void verifyBodyFont(String bodyFont, boolean openTheFirstContentByDefault) {
        paQsSectionPages.verifyBodyFont(bodyFont, openTheFirstContentByDefault);
    }

    @Step
    public void verifyBodySize(String bodySize, boolean openTheFirstContentByDefault) {
        paQsSectionPages.verifyBodySize(bodySize, openTheFirstContentByDefault);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor, String typeBackground) {
        paQsSectionPages.verifyBorderTopColor(borderTopColor, typeBackground);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize, String typeBackground) {
        paQsSectionPages.verifyBorderTopSize(borderTopSize, typeBackground);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor, String typeBackground) {
        paQsSectionPages.verifyBorderBottomColor(borderBottomColor, typeBackground);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize, String typeBackground) {
        paQsSectionPages.verifyBorderBottomSize(borderBottomSize, typeBackground);
    }

    @Step
    public void verifyHeadingAlignment(String headingAlignment, String heading) {
        paQsSectionPages.verifyHeadingAlignment(headingAlignment, heading);
    }

    @Step
    public void verifyMinHeight(String minHeight, String typeBackground) {
        paQsSectionPages.verifyMinHeight(minHeight, typeBackground);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        paQsSectionPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        paQsSectionPages.verifyCustomWidth(customWidth);
    }

    @Step
    public void verifyTitle(String title) {
        paQsSectionPages.verifyTitle(title);
    }

    @Step
    public void verifybody(String body, boolean openTheFirstContentByDefault) {
        paQsSectionPages.verifybody(body, openTheFirstContentByDefault);
    }
}
