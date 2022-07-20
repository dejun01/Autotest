package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.NewsletterPage;
import net.thucydides.core.annotations.Step;

public class NewsletterSteps {

    NewsletterPage newsletterPage;

    @Step
    public void enterHeadingNewsletter(String heading) {
        newsletterPage.enterHeadingNewsletter(heading);
    }

    @Step
    public void enterSubheadingNewsletter(String subheading) {
        newsletterPage.enterSubheadingNewsletter(subheading);
    }

    public void chooseTextAlignment(String textAlignment) {
        newsletterPage.clickLinkTextWithLabel(textAlignment);
    }

    public void checkUncheckShowFullWidthSection(Boolean isFull) {
        newsletterPage.checkCheckboxWithLabel("Full width section", isFull);
    }

    public void uploadImage(String imgBg) {
        if (!imgBg.isEmpty()) {
            newsletterPage.uploadImageBachgroundBanner(imgBg);
        } else newsletterPage.removeImage();
    }
}
