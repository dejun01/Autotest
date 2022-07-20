package com.opencommerce.shopbase.dashboard.online_store.themes.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ManageThemePage extends ThemeEditorV2Page {
    public ManageThemePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectTabThemes() {
        clickOnElement("//p[normalize-space()='Themes']");
    }

    public void selectTheme(String theme) {
        clickOnElement("//div[@class='explore-themes-templates__theme']//h4[normalize-space()='" + theme + "']");
    }

    public void clickButtonAddTheme() {
        clickOnElement("//div[contains(@class,'s-modal-content')]//button[@class='s-button is-primary']");
    }

    public void verifyThemeName(String theme) {
        scrollIntoElementView("//p[@class='themes-list__theme-title']");
        verifyElementContainsText("//p[@class='themes-list__theme-title']", theme);
    }

    public void verifyLastSaved(String lastSaved) {
        verifyElementContainsText("//div[@class='s-flex-item themes-list__date']//span", lastSaved);
    }

    public void openActionsDropDown(String theme) {
        clickOnElement("//div[@class='s-flex s-flex--spacing-tight' and descendant::*[normalize-space()='" + theme + "']] //button[normalize-space()='Actions']");
    }

    public void openActionsDropDown() {
        waitElementToBeVisible("//div[@class='s-flex s-flex--spacing-tight'] //button[normalize-space()='Actions' or normalize-space()='操作']");
        clickOnElement("//div[@class='s-flex s-flex--spacing-tight'] //button[normalize-space()='Actions' or normalize-space()='操作']");
    }

    public void selectAction(String theme, String action) {
        scrollIntoElementView("//div[contains(@class,'s-flex') and descendant::*[normalize-space()='" + theme + "']][1]//span[normalize-space()='" + action + "']");
        clickOnElement("//div[contains(@class,'s-flex') and descendant::*[normalize-space()='" + theme + "']][1]//span[normalize-space()='" + action + "']");
    }

    public void selectAction(String action) {
        scrollIntoElementView("//span[normalize-space()='" + action + "']");
        clickOnElement("//span[normalize-space()='" + action + "']");
    }

    public void verifyTitlePublishedTheme(String theme) {
        waitForEverythingComplete();
        verifyElementContainsText("//h3[contains(@class,'s-heading published-theme__title')]", theme);
    }

    public void clickButtonOnDashboard(String label) {
        clickOnElement("//button[normalize-space()='" + label + "']");
    }

    public void inputThemeName(String name) {
        waitClearAndType("//div[@class='s-modal-body']//input", name);
    }

    public void verifyThemeNameOnSF(String themeName) {
        verifyElementContainsText("//div[@class='footer_content']//span", themeName);
    }

    public void verifyIDPublishedTheme(String themeID) {
        verifyElementContainsText("//p[@class='published-theme__id']", themeID);
    }

    public String getThemeID(String theme) {
        return getText("(//div[@class='s-flex s-flex--spacing-tight' and descendant::*[normalize-space()='" + theme + "']])[1]//p[@class='themes-list__theme-id']");
    }

    public void verifyRemovedTheme(String strThemeID, boolean isShow) {
        waitForEverythingComplete();
        verifyElementPresent("//p[normalize-space()='" + strThemeID + "']", isShow);
    }

    public void verifyShowTheme() {
        verifyElementPresent("//div[@class='card__section published-theme']", true);
        verifyElementPresent("//div[@class='card__section theme-previews']", true);
    }

    public void verifyBtnDisablePasswordDisplay(boolean isDisplay) {
        verifyElementPresent("//button[normalize-space()='Disable password']", isDisplay);
    }

    public void verifyAlertDisable() {
        verifyElementPresent("//div[@class='s-alert__content']", false);
    }

    public void clickButtonCopyATheme() {
        clickOnElement("//button[normalize-space()='Copy a theme']");
    }

    public void clickButtonCopyTheme(String label) {
        clickOnElement("//button[normalize-space()='" + label + "']");
    }

    public void enterThemeId(String _value) {
        waitClearAndTypeThenTab("//input[@id='theme-id']", _value);
    }

    public void verifyShowMessage(String message) {
        verifyElementPresent("//div[normalize-space()='" + message + "']", true);
    }

    public void clickButtonCustomize() {
        clickOnElement("//div[contains(@class,'published-theme')]//button [normalize-space()='Customize']");
    }

    public void verifyText(String section, boolean isPresent) {
        highlightElement("//*[normalize-space() = '" + section + "']");
        verifyElementPresent("//*[normalize-space() = '" + section + "']", isPresent);
    }

    public void openAddSection(String labelAddSection) {
        String elementToClick = "//div[normalize-space() = '" + labelAddSection + "']";
        hoverThenClickElement("//p[normalize-space() = 'Cart recommendations']", elementToClick);
        verifyElementPresent("//p[normalize-space() = 'Add Section']", true);
    }

    public void clickButtonBack() {
        clickOnElement("//span[contains(@class, 'is-default')]");
    }

    public void clickCustomizeCopyTheme() {
        clickOnElement("//div[@class='s-flex-item themes-list__actions']//button[normalize-space() = 'Customize']");
    }

    public boolean verifyTypeThemePB() {
        return isElementExist("//*[contains(@src,'PrintBase')]", 10);
    }

    //verify header have layout: Inline, Rick, Mininal

    public void verifyHeaderThemeInside(boolean isLayoutMinimal) {
        verifyElementPresent("//h1[text()='Header']", true);
        verifyBtnGroup("Layout", "Inline", true);
        verifyBtnGroup("Layout", "Rich", true);
        verifyBtnGroup("Layout", "Minimal", isLayoutMinimal);
        clickOnElement("//button[contains(@class,'sb-button--only-icon--small') and contains(@style,'visible')]");

    }

    public int countBlock(String block) {
        return countElementByXpath("//*[contains(text(), 'content')]//ancestor::div[@class='card without-padding']//div[@role='button']//*[contains(text(), '" + block + "')]");
    }

    public void clickBlock(int i, String block) {
        clickOnElement("(//*[contains(text(), 'content')]//ancestor::div[@class='card without-padding']//div[@role='button']//*[contains(text(), '" + block + "')])[" + i + "]");
    }

    public boolean isDroplistBlockExist(String block) {
        return isElementExist("//*[contains(text(), '" + block + "')]//ancestor::div[contains(@class, 'is-active')]");
    }

    public void verifyBlockFooterPB(int countDroplistOfBlock) {
        Assertions.assertThat(countDroplistOfBlock).isLessThanOrEqualTo(1);
    }

    public void verifyBlockFooterSB(int countDroplistOfBlock, int _countBlock) {
        Assertions.assertThat(countDroplistOfBlock).isLessThanOrEqualTo(_countBlock);
    }

    public void closeCustomizeTheme() {
        clickOnElement("//*[normalize-space() = 'Close']");
    }

    public int countTheme() {
        return countElementByXpath("//p[@class ='themes-list__theme-title']");
    }

    public void verifyRemoveAllThemes() {
        verifyElementPresent("//p[@class ='themes-list__theme-title']", false);
    }

    public void selectActionOfCurrentTheme(String action) {
        clickOnElement("//div[@class=\"published-theme__actions\"]//span[normalize-space()='" + action + "']");
    }

    public void openActionsDropDownOfCurrentTheme() {
        clickOnElement("//div[@class=\"published-theme__actions\"]//button[normalize-space()='Actions']");
    }
}
