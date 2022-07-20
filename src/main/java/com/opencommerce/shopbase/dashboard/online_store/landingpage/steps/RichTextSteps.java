package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.RichTextPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class RichTextSteps extends ScenarioSteps {
    RichTextPages richTextPages;

    @Step
    public void verifyTitle(String title, String positionLayout) {
        richTextPages.verifyTitle(title, positionLayout);
    }

    @Step
    public void verifyBody(String body, String positionLayout) {
        richTextPages.verifyBody(body, positionLayout);
    }

    @Step
    public void verifyButtonLabel(String buttonLabel) {
        richTextPages.verifyButtonLabel(buttonLabel);
    }

    @Step
    public void verifyButtonLink(String buttonLink) {
        richTextPages.verifyButtonLink(buttonLink);
    }

    @Step
    public void verifyTypeBackground(String typeBackground) {
        richTextPages.verifyTypeBackground(typeBackground);
    }

    @Step
    public void verifyCustomColorBackground(String customColorBackground) {
        richTextPages.verifyCustomColorBackground(customColorBackground);
    }

    @Step
    public void verifyStartColorBackground(String startColorBackground) {
        richTextPages.verifyStartColorBackground(startColorBackground);
    }

    @Step
    public void verifyEndColorBackground(String endColorBackground) {
        richTextPages.verifyEndColorBackground(endColorBackground);
    }

    @Step
    public void verifyDirectionBackground(String directionBackground) {
        richTextPages.verifyDirectionBackground(directionBackground);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        richTextPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        richTextPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyTitleColorTypography(String titleColorTypography, String positionLayout, String title) {
        richTextPages.verifyTitleColorTypography(titleColorTypography, positionLayout, title);
    }

    @Step
    public void verifyTitleFontTypography(String titleFontTypography, String positionLayout, String title) {
        richTextPages.verifyTitleFontTypography(titleFontTypography, positionLayout, title);
    }

    @Step
    public void verifyTitleSizeTypography(String titleSizeTypography, String positionLayout, String title) {
        richTextPages.verifyTitleSizeTypography(titleSizeTypography, positionLayout, title);
    }

    @Step
    public void verifyBodyColorTypography(String bodyColorTypography, String positionLayout, String body) {
        richTextPages.verifyBodyColorTypography(bodyColorTypography, positionLayout, body);
    }

    @Step
    public void verifyBodyFontTypography(String bodyFontTypography, String positionLayout, String body) {
        richTextPages.verifyBodyFontTypography(bodyFontTypography, positionLayout, body);
    }

    @Step
    public void verifyBodySizeTypography(String bodySizeTypography, String positionLayout, String body) {
        richTextPages.verifyBodySizeTypography(bodySizeTypography, positionLayout, body);
    }

    @Step
    public void verifyTypeButton(String typeButton) {
        richTextPages.verifyTypeButton(typeButton);
    }

    @Step
    public void verifyButtonColorTypography(String buttonColor) {
        richTextPages.verifyButtonColorTypography(buttonColor);
    }

    @Step
    public void verifyButtonLabelColor(String buttonLabelColor) {
        richTextPages.verifyButtonLabelColor(buttonLabelColor);
    }

    @Step
    public void verifyButtonLabelFont(String buttonLabelFont) {
        richTextPages.verifyButtonLabelFont(buttonLabelFont);
    }

    @Step
    public void verifyButtonLabelSize(String buttonLabelSize) {
        richTextPages.verifyButtonLabelSize(buttonLabelSize);
    }

    @Step
    public void verifyBorderTopColorSeparator(String borderTopColorSeparator, String typeBackground) {
        richTextPages.verifyBorderTopColorSeparator(borderTopColorSeparator, typeBackground);
    }

    @Step
    public void verifyBorderBottomColorSeparator(String borderBottomColorSeparator, String typeBackground) {
        richTextPages.verifyBorderBottomColorSeparator(borderBottomColorSeparator, typeBackground);
    }

    @Step
    public void verifyPositionLayout(String positionLayout) {
        richTextPages.verifyPositionLayout(positionLayout);
    }

    @Step
    public void verifyAlignmentLayout(String alignmentLayout, String positionLayout) {
        richTextPages.verifyAlignmentLayout(alignmentLayout, positionLayout);
    }

    @Step
    public void verifyMinHeightLayout(String minHeightLayout, String typeBackground) {
        richTextPages.verifyMinHeightLayout(minHeightLayout, typeBackground);
    }

    @Step
    public void verifyMaxWidthLayout(String maxWidthLayout) {
        richTextPages.verifyMaxWidthLayout(maxWidthLayout);
    }

    @Step
    public void verifyLinkColor(String linkColor) {
        richTextPages.verifyLinkColor(linkColor);
    }

    @Step
    public void verifyLinkFont(String linkFont) {
        richTextPages.verifyLinkFont(linkFont);
    }

    @Step
    public void verifyLinkSize(String linkSize) {
        richTextPages.verifyLinkSize(linkSize);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        richTextPages.verifyCustomWidth(customWidth);
    }
}
