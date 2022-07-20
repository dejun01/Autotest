package com.opencommerce.shopbase.dashboard.online_store.themes.steps;

import com.opencommerce.shopbase.common.steps.CommonThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorV2Page;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class ThemeEditorV2Steps extends CommonThemeEditorSteps {
    ThemeEditorV2Page themeEditorV2Page;
    String shop = LoadObject.getProperty("shop");

    @Step
    public int countSectionByXpath(String nameSection) {
        return themeEditorV2Page.countSectionByXpath(nameSection);
    }

    @Step
    public void clickButtonBack(){
        themeEditorV2Page.clickButtonBack();
    }

    @Step
    public boolean isExistImage(String label){
        return themeEditorV2Page.isExistImage(label);
    }

    @Step
    public int countBlockByXpath(String nameBlock, String nameSection) {
        return themeEditorV2Page.countBlockByXpath(nameBlock, nameSection);
    }

    @Step
    public void inputTextWithIframe(String label, String text){
        themeEditorV2Page.inputTextWithIframe(label, text);
    }
    @Step
    public void inputTextWithIframe(String label, String text, int index){
        themeEditorV2Page.inputTextWithIframe(label, text,index);
    }

    @Step
    public void selectSettingsSection(String section) {
        themeEditorV2Page.selectSettingsSection(section);
    }


    @Step
    public void selectRadioButton(String value){
        themeEditorV2Page.selectRadioButton(value);
    }
}
