package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.FeaturedCollectionPage;
import common.utilities.LoadObject;

import static common.utilities.LoadObject.convertStatus;

public class FeaturedCollectionSteps {
    FeaturedCollectionPage featuredCollectionPage;
    String theme = LoadObject.getProperty("theme");

    public void enterHeading(String heading) {
        featuredCollectionPage.enterInputFieldWithLabel("Heading", heading);
    }

    public void chooseCollectionLayout(String collectionLayout) {
        if (!collectionLayout.isEmpty()) {
            switch (theme) {
                case "inside":
                    featuredCollectionPage.clickLinkTextWithLabel(collectionLayout);
                    break;
                default:
                    featuredCollectionPage.selectRadioButtonWithLabel(collectionLayout, 1, true);
            }
        }
    }

    public void chooseProductRow(String productPerRow) {

    }

    public void chooseLimitProducts(String limitProducts) {
    }

    public void settingBtnViewMore(Boolean showViewMoreButton, String viewMoreLable) {
        featuredCollectionPage.checkCheckboxWithLabel("Show View more button", 1, showViewMoreButton);
        if (!viewMoreLable.isEmpty()) {
            featuredCollectionPage.enterInputFieldWithLabel("View more button label", viewMoreLable);
        }
    }

    public void chooseAlignment(String alignment) {
        featuredCollectionPage.clickLinkTextWithLabel(alignment);
    }

    public void enterButtonLabel(String btnLabel) {
        featuredCollectionPage.enterInputFieldWithLabel("Button label", btnLabel);
    }

    public void checkUncheckDisplayAsTextLink(Boolean isDisplay) {
        featuredCollectionPage.checkCheckboxWithLabel("Display as text link", isDisplay);
    }
}
