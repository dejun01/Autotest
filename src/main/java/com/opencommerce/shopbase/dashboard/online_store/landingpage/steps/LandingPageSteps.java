package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.HeroSectionPages;
import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.LandingPages;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static common.utilities.DateTimeUtil.getNextByFormat;

public class LandingPageSteps extends ScenarioSteps {
    LandingPages landingPagePages;
    ThemeEditorPage themeEditorPage;
    HeroSectionPages heroSectionPages;

    @Step
    public void clickCheckbox() {
        landingPagePages.clickCheckbox();
    }

    @Step
    public void clickBtnAction() {
        landingPagePages.clickBtnAction();
    }

    @Step
    public void clickDeletePages() {
        landingPagePages.clickDeletePages();
    }

    @Step
    public void verifyDeleteAllPage() {
        landingPagePages.verifyDeleteAllPage();
    }

    @Step
    public void clickBtnAddPage() {
        landingPagePages.clickBtnAddPage();
    }

    @Step
    public void selectTemplate(String selectTemplate) {
        landingPagePages.selectTemplate(selectTemplate);
    }

    @Step
    public void inputWithTitle(String label, String pageTitle) {
        if (!pageTitle.isEmpty()) {
            landingPagePages.inputWithTitle(label, pageTitle);
        }
    }

    @Step
    public void inputSEODescription(String seoDescription) {
        if (!seoDescription.isEmpty()) {
            landingPagePages.inputSEODescription(seoDescription);
        }
    }

    @Step
    public void uploadOpenGraphImage(String openGraphImage) {
        if (!openGraphImage.isEmpty()) {
            landingPagePages.uploadOpenGraphImage(openGraphImage);
        }
    }

    @Step
    public void clickAddPage() {
        landingPagePages.clickAddPage();
    }

    @Step
    public void settingPublishThisPage(boolean publishThisPage) {
        landingPagePages.settingPublishThisPage(publishThisPage);
    }

    @Step
    public void verrifyCreateSuccess(String totalSection, String selectTemplate) {
        landingPagePages.verrifyCreateSuccess(totalSection, selectTemplate);
    }

    @Step
    public void clickBtnClose() {
        landingPagePages.clickBtnClose();
    }

    @Step
    public void clickIconDetail(String pageTitle) {
        landingPagePages.clickIconDetail(pageTitle);
    }

    @Step
    public void verifyPageTitle(String label, String pageTitle) {
        landingPagePages.verifyPageTitle(label, pageTitle);
    }

    @Step
    public void verifySeoTitle(String label, String seoTitle) {
        landingPagePages.verifySeoTitle(label, seoTitle);
    }

    @Step
    public void verifySeoDescription(String label, String seoDescription) {
        landingPagePages.verifySeoDescription(label, seoDescription);
    }

    @Step
    public void verifyopenGraphImage(String openGraphImage) {
        if (!openGraphImage.isEmpty()) {
            landingPagePages.verifyopenGraphImage(openGraphImage);
        }
    }

    @Step
    public void verifyOpenLinkSF(String pageTitle, boolean publishThisPage) {
        landingPagePages.verifyOpenLinkSF(pageTitle, publishThisPage);
    }

    @Step
    public void openLandingEditor(String titleLanding) {
        landingPagePages.openLandingEditor(titleLanding);
    }

    @Step
    public boolean checkExistPage() {
        return landingPagePages.checkExistPage();
    }

    @Step
    public void openSection(String label) {
        themeEditorPage.clickSection(label);
    }

    @Step
    public void inputTextBox(String label, String value) {
        landingPagePages.inputTextBox(label, value);

    }

    @Step
    public void inputTextArea(String label, String body) {
        landingPagePages.inputTextArea(label, body);
    }

    @Step
    public void openVisualSetting(String label) {
        landingPagePages.openVisualSetting(label);
    }

    @Step
    public void selectDdlWithLabel(String label, String value) {
        landingPagePages.selectLabel(label, value);
    }

    @Step
    public void inputColor(String label, String value) {
        if (!value.isEmpty()) {
            landingPagePages.inputColor(label, value);
        }
    }

    public void scrollToLabel(String label) {
        landingPagePages.scrollToLabel(label);
    }

    @Step
    public void changeFont(String label, String value) {
        landingPagePages.clickBtnChange(label, value);
        landingPagePages.searchFont(label, value);
        landingPagePages.chooseFont(label, value);
        landingPagePages.closeSelectFontPopup();
        landingPagePages.verifyChangeFontSuccessfully(label, value);
    }
    @Step
    public void changeFont(String label, String value,int index) {
        landingPagePages.clickBtnChange(label,index);
        landingPagePages.searchFont(label, value);
        landingPagePages.chooseFont(label, value);
        landingPagePages.closeSelectFontPopup();
        landingPagePages.verifyChangeFontSuccessfully(label, value);
    }

    @Step
    public void openOnStorefront(String title) {
        landingPagePages.openOnStorefront(title);
    }

    @Step
    public void checkCheckBox(String label, boolean isCheck) {
        landingPagePages.checkCheckBox(label, isCheck);
    }

    @Step
    public void verifyShowHTML(String html) {
        landingPagePages.verifyShowHTML(html);
    }

    @Step
    public void verifyCustomHTML(String html) {
        if (!html.isEmpty()) {
            landingPagePages.verifyCustomHTML(html);
        }
    }

    @Step
    public void verifyFullWidthSection(boolean fullWidthSection) {
        landingPagePages.verifyFullWidthSection(fullWidthSection);
    }

    @Step
    public void uploadImageWithLabel(String label, String image) {
        if (!image.isEmpty()) {
            landingPagePages.uploadImageWithLabel(label, image);
        }
    }

    @Step
    public void opendMenuItem(String label, int index) {
        landingPagePages.opendMenuItem(label, index);
    }

    public void selectDropListWithLable(String label, String value) {
        landingPagePages.selectDropListWithLable(label, value);
    }

    @Step
    public void removeAllContent(String label) {
        int n = landingPagePages.countContent(label);
        for (int i = n; i >= 1; i--) {
            landingPagePages.openBlockContentByIndex(label, i);
            landingPagePages.removeContent(label, i);
        }
    }

    @Step
    public void clickAddContent() {
        landingPagePages.clickAddContent();
    }

    @Step
    public void inputEndDate(String endDate) {
        if (!endDate.equalsIgnoreCase("@TODAY@")) {

            if (endDate.equalsIgnoreCase("@NEXTDAY@")) {
                endDate = getNextByFormat("yyyy-MM-dd HH:mm:ss");
            }
            landingPagePages.setScheduleTime(endDate);
        }
    }

    @Step
    public void uploadImageWithLabel(String label, String image, int index) {
        if (!image.isEmpty()) {
            landingPagePages.uploadImageWithLabel(label, image, index);
        }

    }

    @Step
    public void inputTextBox(String label, String value, int index) {
        if (!value.isEmpty()) {
            landingPagePages.inputTextBox(label, value, index);
        }
    }

    @Step
    public void inputTextContentWithIframe(String value, int index) {
        if (!value.isEmpty()) {
            landingPagePages.inputTextContentWithIframe(value, index);
        }
    }

    @Step
    public void closeMenuItem(String label, int index) {
        landingPagePages.opendMenuItem(label, index);
    }

    @Step
    public void checkCheckBox(String label, boolean isCheck, int index) {
        landingPagePages.checkCheckBox(label, isCheck, index);
    }

    public void selectDdlWithLabel(String label, String value, int index) {
        landingPagePages.selectLabel(label, value,index);
    }

    public void enterProduct(String label, String value) {
        landingPagePages.enterProduct(label,value);
    }

    public void inputColor(String label, String value, int index) {
        landingPagePages.inputColor(label,value,index);
    }
}
