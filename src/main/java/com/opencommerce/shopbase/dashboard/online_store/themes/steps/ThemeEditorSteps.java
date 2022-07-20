package com.opencommerce.shopbase.dashboard.online_store.themes.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;

import java.util.Arrays;
import java.util.List;

public class ThemeEditorSteps extends ScenarioSteps {
    ThemeEditorPage themeEditorPage;

    @Step
    public void clickSection(String section) {
        themeEditorPage.clickSection(section);
    }

    @Step
    public void openThemeSettings() {
        if (!themeEditorPage.isOpenThemeSettings()) {
            themeEditorPage.openBlockThemeSetting();
        }
    }

    @Step
    public void enterLinkSocialWithLabel(String label, String value) {
        if (!value.isEmpty())
            themeEditorPage.enterLinkSocialWithLabel(label, value);
    }

    @Step
    public void choosePreview(String page) {
        themeEditorPage.choosePreview(page);
    }


    @Step
    public void clickSettingTab() {
        themeEditorPage.clickSettingtab(0);
    }

    @Step
    public void navigateToSettingTab() {
        themeEditorPage.navigateToSettingTab();
        themeEditorPage.waitUntilInvisibleLoading(10);
    }

    @Step
    public void navigateToSectionsTab() {
        themeEditorPage.navigateToSectionsTab();
    }

    @Step
    public void clickBtnChangeThemeStyle() {
        themeEditorPage.clickBtn("Change theme style");
    }

    @Step
    public void verifyPopupShown(String namePopup) {
        themeEditorPage.verifyPopupShown(namePopup);
    }

    @Step
    public void selectStyle(String themeStyle) {
        themeEditorPage.selectStyle(themeStyle);
    }

    @Step
    public void setImageOnSlideShowToDefault(boolean isSetImageOnSlideShowToDefault) {
        verifyPopupShown("Change theme styles");
        if (isSetImageOnSlideShowToDefault) {
            themeEditorPage.selectRadioButtonWithLabel("Set images on slideshow to default", true);
        } else {
            themeEditorPage.selectRadioButtonWithLabel("Still keep my custom images on slideshow", true);
        }
    }

    @Step
    public void clickBtnChange() {
        themeEditorPage.clickBtn("Change");
    }


    @Step
    public void openSettingBlock(String sessionName) {
        themeEditorPage.clickLinkTextWithLabel(sessionName);
    }


    @Step
    public void closeSessionSetting() {
        themeEditorPage.closeSessionSetting();
    }

    @Step
    public void openSessionBlock(String label) {
        themeEditorPage.clickLinkTextWithLabel(label);
    }

    @Step
    public void saveSetting() {
        int count = 0;
        if (themeEditorPage.isElementExist("//button[normalize-space()='Save' ][not(@disabled='disabled')]")) {
            themeEditorPage.clickBtn("Save");
            themeEditorPage.verifyMessageSaveSuccess(0);
        }
    }

    @Step
    public void turnOnSections(String sections) {
        List<String> sectionList = Arrays.asList(sections.split(","));
        for (String sectionName : sectionList) {
            if (iSectionExisted(sectionName)) {
                turnOnSection(sectionName, true);
            } else {
                addSection(sectionName);
                saveSetting();
                closeSessionSetting();
            }
        }
    }


    public void addSection(String sectionName) {
        themeEditorPage.hoverOnFirstSection(1);
        themeEditorPage.addSection(sectionName);
    }

    public void turnOnSection(String sectionName, boolean isOn) {
        themeEditorPage.turnOnSectionByIndex(sectionName, isOn);
    }

    public void chooseCollectionsOrProducts(String collections) {
        if (!collections.isEmpty()) {
            String[] cols = collections.split(",");
            for (String collection : cols) {
                themeEditorPage.selectCollectionOrProduct(collection);
            }

        }
    }

    @Step
    public void removeAllContent() {
        int n = themeEditorPage.countContent();
        for (int i = n; i >= 1; i--) {
            openBlockContentByIndex(i);
            themeEditorPage.removeContent(i);
        }
    }

    public void openBlockContentByIndex(int index) {
        themeEditorPage.openBlockContentByIndex(index);
    }

    public void clickAddContent() {
        themeEditorPage.clickAddContent();
    }


    public void clickSectionTab() {
    }

    public void uploadImageWithFile(String file) {
        if (!file.isEmpty()) {
            themeEditorPage.uploadImageWithFile(file);
        }
    }

    public void openBlockContent() {
        themeEditorPage.openLastBlockContent();
    }

    public void openPageOnNewWindow(String url) {
        themeEditorPage.openUrlInNewTab(url);
        themeEditorPage.switchToLatestTab();

    }

    @Step
    public boolean iSectionExisted(String sections) {
        return themeEditorPage.iSectionExisted(sections);
    }

    @Step
    public String getHeading() {
        return themeEditorPage.getHeading();
    }

    @Step
    public void removeSection() {
        themeEditorPage.clickBtnRemoveSection();
        themeEditorPage.alertAccept();
        themeEditorPage.waitForPageLoad();
    }

    @Step
    public void waitForTextToDisappear(String text) {
        themeEditorPage.waitForTextToDisappear(text);
    }

    @Step
    public void clickBtnCustomize() {
        themeEditorPage.clickBtnCustomizeCurrentTheme();
    }

    @Step
    public String getCurrentTheme() {
        return themeEditorPage.getCurrentTheme();
    }

    @Step
    public void clickBtnCustomizeCurrentTheme() {
        themeEditorPage.clickBtnCustomizeCurrentTheme();
    }

    @Step
    public void closeThemeEditor() {
        themeEditorPage.clickBtn("Close");
        themeEditorPage.confirmPopup();
    }

    @Step
    public void verifyThemesManagementPageDisplayed() {
        themeEditorPage.verifyThemesManagementPageDisplayed();
    }
    @Step
    public void removeFeature() {
        int n = themeEditorPage.countContent();
        for (int i = n; i >= 1; i--) {
            openBlockContentByIndex(i);
            themeEditorPage.removeFeature(i);
        }
    }

    @Step
    public void verifySectionBestSellers(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void verifySectionRecentlyViewedFeaturedProducts(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void verifySectionCartRecommendations(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void verifySectionProductsFromTheSameCollections(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void verifySectionReview(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    public void verifyWhoBoughtThisAlsoBought(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void verifyHandpickedProducts(String label) {
        themeEditorPage.verifySectionWidget(label);
    }

    @Step
    public void navigationToSectionTab() {
        themeEditorPage.clickBtnBack();
        themeEditorPage.clickSectionTab();
    }

    public void clickOptionImageList() {
        themeEditorPage.clickSelectBox();
    }

    public void selectOption(String settingImageList) {
        themeEditorPage.selectOption(settingImageList);
    }

    @Step
    public void selectDropDown(String label, String value){
        themeEditorPage.selectDropDown(label, value);
    }

    @Step
    public void selectDropDownInActiveBlock(String lable, String value){
        themeEditorPage.selectDropDownInActiveBlock(lable, value);
    }

    @Step
    public void inputTextBox(String label, String text){
        themeEditorPage.inputTextBox(label, text);
    }

    public void clickAddColumn() {
        themeEditorPage.clickAddColumn();
    }

    public void checkFullWidthSection(String label) {
        themeEditorPage.checkFullWidthSection(label);
    }

    public void clickAddSection() {
        themeEditorPage.clickAddSection();
    }

    public void sellectTextAlignment(String textAlignment) {
        themeEditorPage.sellectTextAlignment("Text alignment",textAlignment);
    }

    public void inputButtonLabelColor(String buttonLabelColor) {
        themeEditorPage.inputButtonLabelColor(buttonLabelColor);
    }

    public void inputTextColor(String label, String textColor) {
        themeEditorPage.inputTextColor(label,textColor);
    }

    public void uploadLogo(String logo) {
        themeEditorPage.uploadLogo(logo);
    }

    public void selectFooterMenu(String footerMenu) {
        themeEditorPage.selectFooterMenu("Footer menu",footerMenu);
    }

    public void inputCustomText(String customText) {
        themeEditorPage.inputCustomText(customText);
    }

    public void removeAllPaymentIcon() {
            int countElement = themeEditorPage.countPaymentIcon();

            for(int i=0; i<countElement; i++){
                removeContent(1);
            }
        }

        public void removeContent(int index){
            themeEditorPage.openContent(index);
            themeEditorPage.clickRemoveButton();
        }

    public void clickAddMorePaymentIcon() {
        themeEditorPage.clickAddMorePaymentIcon();
    }

    public void uploadImage(String image) {
        if(!image.isEmpty()){
            themeEditorPage.uploadImagePaymentIcon(image);
        }
    }

    public void verifyColor(String title, String label, String backgroundHeader) {
        themeEditorPage.verifyColorSetting(title,label,backgroundHeader);
    }

    public void clickCustomize() {
        themeEditorPage.clickCustomize();
    }

    public void selectDroplistByLabel(String label, String chooseLayout) {
        themeEditorPage.selectDroplistByLabel(label,chooseLayout);
    }

    public void inputText(String text, int index) {
        if(!text.isEmpty()) {
            themeEditorPage.inputText(text,index);
        }
    }

    public void inputTextArea(String title, String textAreaTopContent) {
        if(!textAreaTopContent.isEmpty())
        themeEditorPage.inputTextArea(title,textAreaTopContent);
    }

    public void uploadImageWithLabel(String title, String imageTopContent) {
        if(!imageTopContent.isEmpty()){
            themeEditorPage.uploadImageWithLabel(title,imageTopContent);

        }
    }

    public void inputTextBoxWithLabel(String title,String lable, String imageCaptionTopContent) {
        if(!imageCaptionTopContent.isEmpty()){
            themeEditorPage.inputTextBoxWithLabel(title,lable,imageCaptionTopContent);
        }

    }

    public void clickBtnBack() {
        themeEditorPage.clickBtnBack();
    }
}