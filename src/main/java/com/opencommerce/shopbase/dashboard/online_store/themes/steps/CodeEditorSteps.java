package com.opencommerce.shopbase.dashboard.online_store.themes.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.CodeEditorPage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class CodeEditorSteps extends CommonSteps {
    CodeEditorPage codeEditorPage;

    @Step
    public void deleteAllThemes() {
        for (int i = 0; i < countThemesOnBlockMoreThemes(); i++) {
            codeEditorPage.openDropListAction();
            codeEditorPage.deleteTheme();
            codeEditorPage.verifyMsg("Your theme has been removed");
        }
        assertThat(countThemesOnBlockMoreThemes()).isEqualTo(0);
    }

    public int countThemesOnBlockMoreThemes() {
        return codeEditorPage.countThemes();
    }

    @Step
    public void verifyShowFile(String filePath, boolean isShow) {
        codeEditorPage.verifyShowFile(filePath, isShow);
    }

    @Step
    public void openFile(String filePath) {
        codeEditorPage.openFile(filePath);
    }

    public int countFileRevision() {
        return codeEditorPage.countFileRevision();
    }

    @Step
    public void editCode(String code) {
        codeEditorPage.editCode(code);
    }

    @Step
    public void clickOnTextLink(String textLink){
        codeEditorPage.clickOnTextLink(textLink);
    }

    @Step
    public void verifyNumberOfThemeInList(int number){
        codeEditorPage.verifyNumberOfThemeInList(number);
    }

    @Step
    public void verifyThemesName(String themesName){
        String[] listTheme = themesName.split(",");
        for(String s : listTheme){
            codeEditorPage.verifyThemesName(s);
        }
    }

    @Step
    public void verifyCurrentTheme(String themeName){
        codeEditorPage.verifyCurrentTheme(themeName);
    }

    @Step
    public void selectVersion(String version){
        codeEditorPage.selectVersion(version);
    }

    @Step
    public void verifyShowActionWithTheme(String themeName, String action, boolean isShowAction){
        codeEditorPage.verifyShowActionWithTheme(themeName, action, isShowAction);
    }

    @Step
    public void verifyCurrentVersion(String version) {
        codeEditorPage.verifyCurrentVersion(version);
    }

    @Step
    public void selectActionWithFile(String file, String action) {

        String xpathFile = codeEditorPage.getXpathByFile(file);
        codeEditorPage.hoverOnFile(xpathFile);
        codeEditorPage.clickOnDropList(xpathFile);
        codeEditorPage.selectAction(xpathFile, action);
    }

    @Step
    public void inputTextBoxOnPopup(String s) {
        codeEditorPage.inputTextBoxOnPopup(s);
    }

    @Step
    public void clickOnButton(String buttonName) {
        codeEditorPage.clickOnButton(buttonName);
        if(buttonName.equals("Save")){
            codeEditorPage.verifyTextPresent("File saved successfully", true);
        }
    }

    @Step
    public void verifyTextOnDashBoard(String text, boolean isShow) {
        codeEditorPage.verifyTextOnDashBoard(text, isShow);
    }

    @Step
    public void verifyTextOnStorefront(String text, boolean isShow) {
        codeEditorPage.verifyTextOnStorefront(text, isShow);
    }

    @Step
    public void clickOnBackIcon(String page) {
        codeEditorPage.clickOnBackIcon(page);
    }

    @Step
    public void verifyEditorCodePage(String themeName) {
        codeEditorPage.verifyEditorCodePage(themeName);
    }

    @Step
    public void clickOnIcon(String search) {
        codeEditorPage.clickOnIcon(search);
    }

    @Step
    public void searchAndSelectFile(String file) {
        String[] filePath = file.split("/");
        codeEditorPage.searchFile(filePath[filePath.length - 1]);
        codeEditorPage.selectFile(file);
    }
}
