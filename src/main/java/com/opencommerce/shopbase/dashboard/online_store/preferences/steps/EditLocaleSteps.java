package com.opencommerce.shopbase.dashboard.online_store.preferences.steps;

import com.opencommerce.shopbase.dashboard.online_store.preferences.pages.EditLocalePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditLocaleSteps {
    EditLocalePage editLocalePage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void goToEditPackFromThemeAction() {
        editLocalePage.clickToActionBtn();
        editLocalePage.selectEditLangAction();
        editLocalePage.refreshPage();
    }

    @Step
    public void selectThemeOrApp(String page, String value) {
        if(page.isEmpty()){
            editLocalePage.selectOptionOnPrePage("Select theme or app", value);
        }else{
            editLocalePage.selectOptionOnEditPack(value);
        }
    }

    @Step
    public void checkAllLang(String page) {
        if(page.isEmpty()){
            editLocalePage.checkAllLangOnPrePage();
        }else {
            editLocalePage.checkAllLangOneditPack();
        }
    }

    @Step
    public int countAllLang(String page) {
        if(page.isEmpty()){
            return editLocalePage.countAllLangOnPrePage();
        }else {
            return editLocalePage.countAllLangOnEditPack();
        }
    }

    @Step
    public void verifyAllLangInDdlList(int countLang, int allLang) {
        assertThat(countLang).isEqualTo(allLang);
    }

    @Step
    public void selectThemeAndLangOnPrePage(String theme, String lang) {
        editLocalePage.selectOptionOnPrePage("Select theme or app", theme);
        editLocalePage.selectOptionOnPrePage("Select language", lang);
    }

    @Step
    public void directToEditPack() {
        editLocalePage.clickBtn("Start editing");
        editLocalePage.waitForPageLoad();
    }

    @Step
    public void verifySelectedOptionOnEditPack(String theme, String lang) {
        editLocalePage.verifySelectedValueOnEditPack(theme);
        editLocalePage.verifySelectedValueOnEditPack(lang);
    }

    @Step
    public void selectThemeAndLangOnEditPack(String theme, String lang) {
        editLocalePage.selectOptionOnEditPack(theme);
        editLocalePage.selectOptionOnEditPack(lang);
    }

    @Step
    public void searchKeyOnTable(String keyValue) {
        editLocalePage.searchKeyOnTable(keyValue);
    }

    @Step
    public void verifyEmptyResult() {
        editLocalePage.countResultKeyOnTable(0);
    }

    @Step
    public void verifyEditIcon(String keyValue) {
        editLocalePage.hoverOnKey(keyValue);
        editLocalePage.verifyEditIcon();
    }

    @Step
    public void editLangPhrase(String key, String value) {
        editLocalePage.inputToEditPhrase(key, value);
    }

    @Step
    public void verifyEditedPhrase(String value) {
        editLocalePage.verifyEditedPhrase(value);
    }

//    @Step
//    public void cancelEditAction(String key) {
//        String phrase = editLocalePage.getPhrase(key);
//        editLocalePage.actionWithPhrase(key, "close");
//        editLocalePage.verifyCanceledPhrase(key, phrase);
//    }

    @Step
    public void saveEditAction(String key) {
        editLocalePage.actionWithPhrase(key, "check");
        editLocalePage.clickBtn("Save");
    }

    @Step
    public void waitToSavedMessInvisible() {
        editLocalePage.waitSavedMessInvisible();
    }

    @Step
    public void sortKeyOnTable(String column, String sort) {
        editLocalePage.selectSortAction(column, sort);
    }

    @Step
    public void verifySortDescendingAction() {
        editLocalePage.verifySortDescendingAction();
    }

    @Step
    public int selectRowPerPage(int value) {
        editLocalePage.selectRowPerPage(value);
        return value;
    }

    @Step
    public int countAllKey() {
        return editLocalePage.countAllKey();
    }

    @Step
    public void verifyAllKeyDisplayedOnPage(int count, int rowOnPage) {
        assertThat(count).isEqualTo(rowOnPage);
    }

    @Step
    public void verifySortAscendingAction() {
        editLocalePage.verifySortAscending();
    }

    @Step
    public void openShopOnSF(String prod) {
        editLocalePage.openUrl("https://" + shop + prod);
        editLocalePage.waitForPageLoad();
    }

    @Step
    public void clickOnLangBtn() {
        editLocalePage.clickOnLocaleBtn();
    }

    @Step
    public void selectLangOnSF(String language) {
        editLocalePage.selectLangOnSF(language);
        editLocalePage.saveLangBtn();
    }

    @Step
    public void verifyThemePhrase(String editedKey) {
        editLocalePage.verifyThemePhrase(editedKey);
    }

    @Step
    public void verifyAppPhrase(String editedKey) {
        editLocalePage.verifyAppPhrase(editedKey);
    }

    @Step
    public void closePopup() {
        editLocalePage.closeLangPopup();
    }
}
