package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.SpecificDescriptionPages;
import net.thucydides.core.annotations.Step;

public class SpecificDescriptionSteps {
    SpecificDescriptionPages specificDescriptionPages;

    @Step
    public void updateBaseDescription(String baseDescription) {
        specificDescriptionPages.updateBaseDescription(baseDescription);
    }

    @Step
    public void clickButtonUpdateDescription(String baseName) {
        specificDescriptionPages.clickButtonUpdateDescription(baseName); }

    @Step
    public void verifySEODescriptionIsDisplayedExactly() {
        specificDescriptionPages.verifySEODescriptionIsDisplayedExactly();
    }
}

