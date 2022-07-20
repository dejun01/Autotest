package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.HeroSectionPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class HeroSectionSteps extends ScenarioSteps {
    HeroSectionPages heroSectionPages;

    @Step
    public void verifyTile(String title) {
        if (!title.isEmpty()) {
            heroSectionPages.verifyTile(title);
        }
    }

    @Step
    public void verifyBodyText(String body) {
        if (!body.isEmpty()) {
            heroSectionPages.verifyBodyText(body);
        }
    }

    @Step
    public void verifyButtonLabel(String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            heroSectionPages.verifyButtonLabel(buttonLabel);
        }
    }

    @Step
    public void verifyButtonLink(String buttonLink, String buttonLabel) {
        if (!buttonLabel.isEmpty() && !buttonLink.isEmpty()) {
            heroSectionPages.verifyButtonLink(buttonLink);
        }
    }

    @Step
    public void verifyTypeBackground(String typeBackground) {
        heroSectionPages.verifyTypeBackground(typeBackground);
    }

    @Step
    public void verifyCustomColorBackground(String customColorBackground) {
        heroSectionPages.verifyCustomColorBackground(customColorBackground);
    }

    @Step
    public void verifyStartColorBackground(String startColorBackground) {
        heroSectionPages.verifyStartColorBackground(startColorBackground);
    }

    @Step
    public void verifyEndColorBackground(String endColorBackground) {
        heroSectionPages.verifyEndColorBackground(endColorBackground);
    }

    @Step
    public void verifyDirectionBackground(String directionBackground) {
        heroSectionPages.verifyDirectionBackground(directionBackground);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        heroSectionPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        heroSectionPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyTitleColorTypography(String titleColorTypography) {
        heroSectionPages.verifyTitleColorTypography(titleColorTypography);
    }

    @Step
    public void verifyTitleFontTypography(String titleFontTypography) {
        heroSectionPages.verifyTitleFontTypography(titleFontTypography);
    }

    @Step
    public void verifyTitleSizeTypography(String titleSizeTypography) {
        heroSectionPages.verifyTitleSizeTypography(titleSizeTypography);
    }

    @Step
    public void verifyBodyColorTypography(String bodyColorTypography) {
        heroSectionPages.verifyBodyColorTypography(bodyColorTypography);
    }

    @Step
    public void verifyBodyFontTypography(String bodyFontTypography) {
        heroSectionPages.verifyBodyFontTypography(bodyFontTypography);
    }

    @Step
    public void verifyBodySizeTypography(String bodySizeTypography) {
        heroSectionPages.verifyBodySizeTypography(bodySizeTypography);
    }

    @Step
    public void verifyButtonColorTypography(String buttonColorTypography) {
        heroSectionPages.verifyButtonColorTypography(buttonColorTypography);
    }

    @Step
    public void verifyButtonLabelColorTypography(String buttonLabelColorTypography) {
        heroSectionPages.verifyButtonLabelColorTypography(buttonLabelColorTypography);
    }

    @Step
    public void verifyButtonLabelFontTypography(String buttonLabelFontTypography) {
        heroSectionPages.verifyButtonLabelFontTypography(buttonLabelFontTypography);
    }

    @Step
    public void verifyButtonLabelSizeTypography(String buttonLabelSizeTypography) {
        heroSectionPages.verifyButtonLabelSizeTypography(buttonLabelSizeTypography);
    }

    @Step
    public void verifyBorderTopColorSeparator(String borderTopColorSeparator) {
        heroSectionPages.verifyBorderTopColorSeparator(borderTopColorSeparator);
    }

    @Step
    public void verifyBorderBottomColorSeparator(String borderBottomColorSeparator) {
        heroSectionPages.verifyBorderBottomColorSeparator(borderBottomColorSeparator);
    }

    @Step
    public void verifyPositionLayout(String positionLayout) {
        heroSectionPages.verifyPositionLayout(positionLayout);
    }

    @Step
    public void verifyAlignmentLayout(String alignmentLayout) {
        heroSectionPages.verifyAlignmentLayout(alignmentLayout);
    }

    @Step
    public void verifyMinHeightLayout(String minHeightLayout, String typeBackground) {
        heroSectionPages.verifyMinHeightLayout(minHeightLayout,typeBackground);
    }

    @Step
    public void verifyMaxWidthLayout(String maxWidthLayout) {
        heroSectionPages.verifyMaxWidthLayout(maxWidthLayout);
    }

    public void verifyAlignmenTitle(String alignmentLayout) {
        heroSectionPages.verifyAlignmenTitle(alignmentLayout);
    }

    public void verifyAlignmenBody(String alignmentLayout) {
        heroSectionPages.verifyAlignmenBody(alignmentLayout);
    }

    public void verifyAlignmenButton(String alignmentLayout) {
        heroSectionPages.verifyAlignmenButton(alignmentLayout);
    }
}
