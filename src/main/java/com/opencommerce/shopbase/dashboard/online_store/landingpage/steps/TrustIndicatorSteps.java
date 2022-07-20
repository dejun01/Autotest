package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.TrustIndicatorPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class TrustIndicatorSteps extends ScenarioSteps {
    TrustIndicatorPages trustIndicatorPages;

    @Step
    public void verifyShowIndicatorBackground(boolean showIndicatorBackground) {
        trustIndicatorPages.verifyShowIndicatorBackground (showIndicatorBackground);
    }

    @Step
    public void verifyHeading(String heading) {
        trustIndicatorPages.verifyHeading (heading);
    }

    @Step
    public void verifyType(String type) {
        trustIndicatorPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        trustIndicatorPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        trustIndicatorPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        trustIndicatorPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        trustIndicatorPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        trustIndicatorPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        trustIndicatorPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyHeadingColor(String headingColor) {
        trustIndicatorPages.verifyHeadingColor(headingColor);
    }

    @Step
    public void verifyHeadingFont(String headingFont) {
        trustIndicatorPages.verifyHeadingFont(headingFont);
    }

    @Step
    public void verifyHeadingSize(String headingSize) {
        trustIndicatorPages.verifyHeadingSize(headingSize);
    }

    @Step
    public void verifyAlignmentHeading(String alignmentHeading) {
        trustIndicatorPages.verifyAlignmentHeading(alignmentHeading);
    }

    @Step
    public void verifyBackgroundColor(String backgroundColor) {
        trustIndicatorPages.verifyBackgroundColor(backgroundColor);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        trustIndicatorPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        trustIndicatorPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        trustIndicatorPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyTitleAlignment(String titleAlignment) {
        trustIndicatorPages.verifyTitleAlignment(titleAlignment);
    }

    @Step
    public void verifyBodyColor(String bodyColor) {
        trustIndicatorPages.verifyBodyColor(bodyColor);
    }

    @Step
    public void verifyBodyFont(String bodyFont) {
        trustIndicatorPages.verifyBodyFont(bodyFont);
    }

    @Step
    public void verifyBodySize(String bodySize) {
        trustIndicatorPages.verifyBodySize(bodySize);
    }

    @Step
    public void verifyBodyAlignment(String bodyAlignment) {
        trustIndicatorPages.verifyBodyAlignment(bodyAlignment);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        trustIndicatorPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize) {
        trustIndicatorPages.verifyBorderTopSize(borderTopSize);
    }

    @Step
    public void verifyRatingIconColor(String ratingIconColor) {
        trustIndicatorPages.verifyRatingIconColor(ratingIconColor);
    }

    @Step
    public void verifyRatingIconSize(String ratingIconSize) {
        trustIndicatorPages.verifyRatingIconSize(ratingIconSize);
    }

    @Step
    public void verifyRattingAlignment(String rattingAlignment) {
        trustIndicatorPages.verifyRattingAlignment(rattingAlignment);
    }

    @Step
    public void verifycustomerNameColor(String customerNameColor) {
        trustIndicatorPages.verifycustomerNameColor(customerNameColor);
    }

    @Step
    public void verifyCustomerNameFont(String customerNameFont) {
        trustIndicatorPages.verifyCustomerNameFont(customerNameFont);
    }

    @Step
    public void verifyCustomerNameSize(String customerNameSize) {
        trustIndicatorPages.verifyCustomerNameSize(customerNameSize);
    }

    @Step
    public void verifyDateColor(String dateColor) {
        trustIndicatorPages.verifyDateColor(dateColor);
    }

    @Step
    public void verifyDateFont(String dateFont) {
        trustIndicatorPages.verifyDateFont(dateFont);
    }

    @Step
    public void verifydDateSize(String dateSize) {
        trustIndicatorPages.verifydDateSize(dateSize);
    }

    @Step
    public void verifyDateAlignment(String dateAlignment) {
        trustIndicatorPages.verifyDateAlignment(dateAlignment);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        trustIndicatorPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        trustIndicatorPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyPagingColor(String pagingColor) {
        trustIndicatorPages.verifyPagingColor(pagingColor);
    }

    @Step
    public void verifyMinHeight(String minHeight) {
        trustIndicatorPages.verifyMinHeight(minHeight);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        trustIndicatorPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        trustIndicatorPages.verifyCustomWidth(customWidth);
    }

    @Step
    public void verifyTitle(String title, int index) {
        trustIndicatorPages.verifyTitle(title,index);
    }

    @Step
    public void verifyBody(String body, int index) {
        trustIndicatorPages.verifyBody(body,index);
    }

    @Step
    public void verifyShowRatings(boolean showRatings, int index) {
        trustIndicatorPages.verifyShowRatings(showRatings,index);
    }

    @Step
    public void verifyCustomerName(String customerName, int index) {
        trustIndicatorPages.verifyCustomerName(customerName,index);
    }

    @Step
    public void verifyShowAvatar(boolean showAvatar, int index) {
        trustIndicatorPages.verifyShowAvatar(showAvatar,index);
    }

    @Step
    public void verifyAvatarImage(String avatarImage, int index) {
        trustIndicatorPages.verifyAvatarImage(avatarImage,index);
    }

    @Step
    public void verifyDate(String date, int index) {
        trustIndicatorPages.verifyDate(date,index);
    }
}
