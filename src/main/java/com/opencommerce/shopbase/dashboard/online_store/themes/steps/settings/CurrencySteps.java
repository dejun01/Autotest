package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.CurrencyPage;
import net.thucydides.core.annotations.Step;

public class CurrencySteps {
    CurrencyPage currencyPage;

    @Step
    public void checkUncheckEnableCurrencyConverion(boolean isEnable) {
        currencyPage.checkCheckboxWithLabel("Enable currency conversion", isEnable);
    }

    @Step
    public void chooseFormat(String format) {
        currencyPage.selectDdlWithLabel("Format", format);
    }

    @Step
    public void chooseSupportCurrencies(String supportedCurrencies, String currencies) {
        currencyPage.selectRadioButtonWithLabel(supportedCurrencies, true);
        if(supportedCurrencies.contains("Some currencies")){
            currencyPage.enterSomeCurrencies(currencies);
        }
    }
}
