package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.GalleryImagePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class GalleryImageSteps {
    GalleryImagePage galleryImagePage;
    String theme = LoadObject.getProperty("theme");
    @Step
    public void enterHeadline(String headline) {
        galleryImagePage.enterInputFieldWithLabel("Headline",headline);
    }

    public void chooseContentAlignment(String contentAlignment) {
        galleryImagePage.clickLinkTextWithLabel(contentAlignment);
    }

    public void checkUncheckShowFullWidthSection(Boolean isFull) {
        galleryImagePage.checkCheckboxWithLabel("Full width section",isFull);
    }

    public void checkUncheckShowNoSpacing(Boolean isSpac) {
        galleryImagePage.checkCheckboxWithLabel("No spacing",isSpac);
    }


    public void inputAltText(String altText) {
        if (!altText.isEmpty()) {
            galleryImagePage.enterInputFieldWithLabel("Alt text", altText, 1);
        }
    }
}
