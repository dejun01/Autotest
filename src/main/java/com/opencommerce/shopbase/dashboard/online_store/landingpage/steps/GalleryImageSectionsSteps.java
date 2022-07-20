package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.GalleryImageSectionsPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class GalleryImageSectionsSteps extends ScenarioSteps {
    GalleryImageSectionsPages galleryImageSectionsPages;

    @Step
    public void verifynoSpacing(boolean noSpacing) {
        galleryImageSectionsPages.verifynoSpacing(noSpacing);
    }

    @Step
    public void verifytTitle(String title) {
        galleryImageSectionsPages.verifytTitle(title);
    }

    @Step
    public void verifyImage(String image) {
        galleryImageSectionsPages.verifyImage(image);
    }

    @Step
    public void verifyType(String type) {
        galleryImageSectionsPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        galleryImageSectionsPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        galleryImageSectionsPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        galleryImageSectionsPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        galleryImageSectionsPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        galleryImageSectionsPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        galleryImageSectionsPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        galleryImageSectionsPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        galleryImageSectionsPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        galleryImageSectionsPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        galleryImageSectionsPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize) {
        galleryImageSectionsPages.verifyBorderTopSize(borderTopSize);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        galleryImageSectionsPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        galleryImageSectionsPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyImageRatio(String imageRatio) {
        galleryImageSectionsPages.verifyImageRatio(imageRatio);
    }

    @Step
    public void verifyAlignment(String alignment) {
        galleryImageSectionsPages.verifyAlignment(alignment);
    }

    @Step
    public void verifyMinHeight(String minHeight) {
        galleryImageSectionsPages.verifyMinHeight(minHeight);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        galleryImageSectionsPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        galleryImageSectionsPages.verifyCustomWidth(customWidth);
    }
}
