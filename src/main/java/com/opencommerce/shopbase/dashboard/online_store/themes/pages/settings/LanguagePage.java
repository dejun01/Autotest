package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class LanguagePage extends SBasePageObject {
    public LanguagePage(WebDriver driver) {
        super(driver);
    }

    public void enterLanguages(String languages) {
        waitClearAndType("(//input[@class='s-input__inner'])[1]", languages);
    }

    public void checkUncheckEnableLanguageTranslation(boolean isEnable) {
        String status = "//div[@class='s-form-item checkbox']//input";
        String click = "//div[@class='s-form-item checkbox']//label";
        verifyCheckedThenClick(status, click, isEnable);
    }

    public void selectSupportedLanguages(String supportedLanguages) {
        String status = "//label[@class='s-radio' and descendant::span[normalize-space()=\"" + supportedLanguages + "\"]]//input";
        String click = "//label[@class='s-radio' and descendant::span[normalize-space()=\"" + supportedLanguages + "\"]]";
        verifyCheckedThenClick(status, click, true);
    }

    public void enterDefaultLanguage(String defaultLanguage) {
        waitClearAndType("//div[@currentlayout and descendant::p[normalize-space()='Default language']]//input", defaultLanguage);
    }
}
