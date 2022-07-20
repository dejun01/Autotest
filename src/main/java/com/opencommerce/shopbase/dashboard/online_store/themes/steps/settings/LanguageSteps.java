package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.LanguagePage;
import net.thucydides.core.annotations.Step;

public class LanguageSteps {
    LanguagePage languagePage;

    @Step
    public void checkUncheckEnableLanguageTranslation(boolean isEnable) {
        languagePage.checkUncheckEnableLanguageTranslation(isEnable);
    }

    @Step
    public void chooseSupportedLanguages(String supportedLanguages, String languages) {
        languagePage.selectSupportedLanguages(supportedLanguages);
        if (supportedLanguages.contains("Some languages")) {
            languagePage.enterLanguages(languages);
        }
    }

    @Step
    public void enterDefaultLanguage(String defaultLanguage) {
        languagePage.enterDefaultLanguage(defaultLanguage);
    }
}
