package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.TypographyPage;
import net.thucydides.core.annotations.Step;

import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TypographySteps {
    TypographyPage typographyPage;

    @Step
    public void verifyFontBySessionName(String sessionName, String value) {
        String actual = typographyPage.getFontBySessionName(sessionName);
        assertThat(actual).isEqualTo(value);
    }

    @Step
    public void verifyFontStyleBySessionName(String sessionName, String value) {
        String actual = typographyPage.getFontStyleBySessionName(sessionName);
        assertThat(actual).isEqualTo(value);
    }
    @Step
    public void verifyBaseSize(String sessionName, String baseSize) {
        typographyPage.verifyBaseSize(sessionName, baseSize);
    }

    @Step
    public void verifyCapitalize(String sessionName, String label, String headingBaseSize) {
        boolean isCapitalize = convertStatus(headingBaseSize);
        typographyPage.verifyCapitalize(sessionName, label, isCapitalize);

    }

    @Step
    public void changeFont(String position, String value) {
        typographyPage.clickBtnChange(position);
        typographyPage.searchFont(value);
        typographyPage.chooseFont(value);
        typographyPage.closeSelectFontPopup();
        typographyPage.verifyChangeFontSuccessfully(position, value);
    }

    @Step
    public void checkCapitalizeHeadings(Boolean isCheck) {
        typographyPage.checkCheckboxWithLabel("Capitalize headings", isCheck);
    }

    @Step
    public void checkCapitalizeBtnText(Boolean isCheck) {
        typographyPage.checkCheckboxWithLabel("Capitalize buttons text", isCheck);
    }

    public void checkCapitalizeText(Boolean capitalizeText) {
        typographyPage.checkCheckboxWithLabel("Capitalize text", capitalizeText);
    }

    public void checkSpaceLetters(Boolean spaceLetters) {
        typographyPage.checkCheckboxWithLabel("Space letters", spaceLetters);
    }

    public void checkUseAccentTextForSubheadings(Boolean useAccentTextForSubheadings) {
        typographyPage.checkCheckboxWithLabel("Use accent text for subheadings", useAccentTextForSubheadings);
    }
}
