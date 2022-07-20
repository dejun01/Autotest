package com.opencommerce.shopbase.common.steps;

import com.opencommerce.shopbase.common.pages.CommonThemeEditorPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.awt.*;
import java.io.IOException;

public class CommonThemeEditorSteps extends ScenarioSteps {
    CommonThemeEditorPage commonThemeEditorPage;


    @Step
    public void openPreviewPage(String page) {
        commonThemeEditorPage.openPreviewPage(page);
    }

    @Step
    public void openSectionSettingWithName(String section) {
        commonThemeEditorPage.openSectionSettingWithName(section, 1);
    }

    @Step
    public void openSectionSettingWithName(String section, int index) {
        commonThemeEditorPage.openSectionSettingWithName(section, index);
    }

    @Step
    public void openBlockWithName(String section, String block) {
        commonThemeEditorPage.openBlockWithName(section, block, 1);
    }

    @Step
    public void openBlockWithName(String section, String block, int index) {
        commonThemeEditorPage.openBlockWithName(section, block, index);
    }

    @Step
    public void selectValueInAutocomplete(String label, String value) {
        if (commonThemeEditorPage.isExistValueInAutocomplete(label)) {
            commonThemeEditorPage.removeLValueFromAutocomplete(label);
        }

        if (value.isEmpty()) {
            commonThemeEditorPage.clickEmptyArea();
        } else if (value.contains("/")) {
            commonThemeEditorPage.addPathToAutocomplete(label, value);
        } else {
            String[] data = value.split(">");
            for (String s : data) {
                commonThemeEditorPage.selectValueInAutocomplete(label, s);
            }
        }
    }

    public void clickButtonGroup(String label, String button) {
        commonThemeEditorPage.clickButtonGroup(label, button);
    }

    public void scrollSliderBarByPercent(String label, String percent) {
        commonThemeEditorPage.scrollSliderBarByPercent(label, percent);
    }

    public void selectValueInDropDown(String label, String value) {
        commonThemeEditorPage.selectValueInDropDown(label, value);
    }

    public void uploadImageWithFile(String label, String path) {
        commonThemeEditorPage.uploadImageWithFile(label, path);
    }
    public void uploadImageWithFile(String label, String path, int index) {
        commonThemeEditorPage.uploadImageWithFile(label, path,index);
    }

    public void removeImageUploaded(String label) {
        commonThemeEditorPage.removeImageUploaded(label);
    }

    public void inputTextWithIframe(String label, String text) {
        commonThemeEditorPage.inputTextWithIframe(label, text);
    }

    public void settingColor(String label, String color) {
        try {
            commonThemeEditorPage.settingColor(label, color);
        } catch (Exception e) {
            commonThemeEditorPage.openAdvancedSetting();
            commonThemeEditorPage.settingColor(label, color);
        }
    }

    public void addSectionWithName(String section) {
        waitABit(500);
        commonThemeEditorPage.clickOnButton("", "Add section");
        commonThemeEditorPage.addSectionWithName(section);
    }

    public void addBlockWithName(String section, String block) {
        commonThemeEditorPage.clickOnButton(section, "Add block");
        commonThemeEditorPage.addSectionWithName(block);
    }

    public void showOrHideBlock(String section, int indexSection, String block, int indexBlock, boolean isShow) {
        commonThemeEditorPage.showOrHideBlock(section, indexSection, block, indexBlock, isShow);
    }

    public void showOrHideBlock(String section, String block, boolean isShow) {
        commonThemeEditorPage.showOrHideBlock(section, 1, block, 1, isShow);
    }

    public void showOrHideSection(String section, int index, boolean isShow){
        commonThemeEditorPage.showOrHideSection(section, index, isShow);
    }

    public void showOrHideSection(String section, boolean isShow){
        commonThemeEditorPage.showOrHideSection(section, 1, isShow);
    }

    public void clickOnButton(String button) {
        commonThemeEditorPage.clickOnButton("", button);
    }

    public void clickOnButton(String section, String button) {
        commonThemeEditorPage.clickOnButton(section, button);
    }

    public void openTab(String tab) {
        commonThemeEditorPage.openTab(tab);
    }

    public void inputTextBoxWithLabel(String label, String value) {
        commonThemeEditorPage.inputTextBoxWithLabel(label, value);
    }

    public void inputTextBoxWithLabelThenTab(String label, String value) {
        commonThemeEditorPage.inputTextBoxWithLabelThenTab(label, value);
    }

    public void getCurrentURL() {
        commonThemeEditorPage.getCurrentUrl();
    }

    public void switchToWindowWithIndex(int index) {
        commonThemeEditorPage.switchToWindowWithIndex(index);
    }

    public void switchToLatestTab() {
        commonThemeEditorPage.switchToLatestTab();
    }

    public void switchToTheFirstTab() {
        commonThemeEditorPage.switchToTheFirstTab();
    }

    public void checkCheckBoxWithLabel(String label, boolean isCheck) {
        commonThemeEditorPage.checkCheckboxWithLabel(label,isCheck);
    }
    public void checkCheckBoxWithLabel(String label, boolean isCheck, int _resOrder) {
        commonThemeEditorPage.setCheckboxWithLabel(label, isCheck, _resOrder);
    }

    public void clickButtonSaveChange(){
        if(!commonThemeEditorPage.isCheckButtonDisable("Save")) {
            commonThemeEditorPage.clickOnButton("", "Save");
            commonThemeEditorPage.verifyTextPresent("All changes were successfully saved", true);
        }
    }

    @Step
    public void addBlockWithName(String section, int indexSection, String block) {
        commonThemeEditorPage.clickOnButtonAddBlock(section, indexSection);
        commonThemeEditorPage.addSectionWithName(block);
    }

    public void clickCustomizeCurrentTheme(){
        commonThemeEditorPage.clickCustomizeCurrentTheme();
    }

    public void inputTextBoxWithLabel(String label, String value, int index) {
        commonThemeEditorPage.inputTextBoxWithLabel(label, value, index);
    }

    @Step
    public void selectValueInAutocomplete(String label, String value, int index) {
        if (commonThemeEditorPage.isExistValueInAutocomplete(label, index)) {
            commonThemeEditorPage.removeLValueFromAutocomplete(label, index);
        }
        if (value.isEmpty()) {
            commonThemeEditorPage.clickEmptyArea();
        } else if (value.contains("/")) {
            commonThemeEditorPage.addPathToAutocomplete(label, value, index);
        } else {
            String[] data = value.split(">");
            for (String s : data) {
                commonThemeEditorPage.selectValueInAutocomplete(label, s, index);
            }
        }
    }

    @Step
    public void inputTextAreaWithLabel(String label, String value) {
        commonThemeEditorPage.inputTextAreaWithLabel(label, value);
    }

    @Step
    public void removeValueFromAutocomplete(String label){
        if (commonThemeEditorPage.isExistValueInAutocomplete(label)) {
            commonThemeEditorPage.removeLValueFromAutocomplete(label);
        }
    }

}
