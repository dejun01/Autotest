package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.GeneralPages;
import net.thucydides.core.annotations.Step;

public class GeneralSteps {
    GeneralPages generalPages;

    @Step
    public void selectCurrency(String currency) {
        generalPages.selectDdlWithLabelAtSettingsScreen("Store currency", currency);
    }

    @Step
    public void verifyStoreName(String storeName) {
        generalPages.verifyStoreName(storeName);
    }
}
