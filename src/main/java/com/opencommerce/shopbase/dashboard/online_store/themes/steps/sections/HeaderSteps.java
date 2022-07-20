package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.HeaderPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import static common.utilities.LoadObject.convertStatus;

public class HeaderSteps {
    HeaderPage headerPage;

    @Step
    public void checkCheckboxSetting(String label, String status) {
        if (!status.isEmpty()) {
            boolean isCheck = convertStatus(status);
            headerPage.checkCheckboxWithLabel(label, 1, isCheck);
        }
    }

    @Step
    public void selectCartIcon(String cartIcon) {
        headerPage.selectDdlWithLabel("Cart icon", cartIcon);
    }

    @Step
    public void settingShowAnnouncementBar(String showAnnouncementBar, String announcementMessage) {
        boolean isShowAnnouncementBar = convertStatus(showAnnouncementBar);
        headerPage.checkCheckboxWithLabel("Show announcement bar", 1, isShowAnnouncementBar);
        if (isShowAnnouncementBar) {
            enterAnnouncementMessage(announcementMessage);
        }
    }


    @Step
    public void enterAnnouncementMessage(String announcementMessage) {
        if (!announcementMessage.isEmpty()) {
            headerPage.enterAnnouncementMessage(announcementMessage);
        }
    }

    @Step
    public void settingTopBar(String showTopBar, String showSocialMediaHeaderIcons, String phoneNumber, String topBarMenu) {
        boolean isShowTopBar = convertStatus(showTopBar);
        headerPage.checkCheckboxWithLabel("Show top bar", 1, isShowTopBar);
        if (isShowTopBar) {
            checkCheckboxSetting("Show social media header icons", showSocialMediaHeaderIcons);
            enterPhoneNumber(phoneNumber);
            selectTopBarMenu(topBarMenu);

        }
    }

    @Step
    public void selectTopBarMenu(String topBarMenu) {
        String theme = LoadObject.getProperty("theme");
        if(theme.equalsIgnoreCase("roller")){
            headerPage.selectDdlWithLabel("Top bar menu", topBarMenu);
        } else headerPage.selectDdlWithLabel("Topbar menu", topBarMenu);

    }

    @Step
    public void enterPhoneNumber(String phoneNumber) {
        headerPage.enterInputFieldWithLabel("Phone number", phoneNumber);
    }

    @Step
    public void settingDesktopLogo(String desktopLogo) {
        uploadImageByLabel("Desktop logo", desktopLogo);
    }

    @Step
    public void settingMobileLogo(String mobileLogo) {
        settingMobileLogo("Mobile logo", mobileLogo);
    }
    @Step
    public void settingMobileLogo(String label, String filename) {

        if (filename.isEmpty()) {
            headerPage.deleteImageByLabel(label);
        } else {
            headerPage.uploadMobileLog("", label, filename);
        }
    }
    @Step
    public void selectMainMenu(String mainMenu) {
        headerPage.selectDdlWithLabel("Main menu", mainMenu);
    }

    @Step
    public void uploadImageByLabel(String label, String filename) {

        if (filename.isEmpty()) {
            headerPage.deleteImageByLabel(label);
        } else {
            headerPage.uploadImageByLabel("", label, filename);
        }
    }


    public void chooseLayoutHeaderInside(String layout) {
        headerPage.chooseLayoutHeaderInside(layout);

    }

    public void enableFullWidthSection(boolean fullWidthSection) {
        headerPage.enableFullWithSection(fullWidthSection);
    }

    public void chooseContendAlignment(String contentAlignment) {
        headerPage.chooseLayoutWithLable("Content alignment",contentAlignment);
    }

    public void selectMenuPosition(String menuPosition) {
        headerPage.chooseLayoutWithLable("Menu position",menuPosition);
    }

    public void selectCartIconInside(String cartIcon) {
        headerPage.selectCartIconInside(cartIcon);
    }

    public void clickCheckAdvanceSettings() {
        headerPage.clickOnTextAdvanceSetting();
    }

    public void enterTextInLogo(String text) {
        headerPage.enterInputFieldWithLabel("Text in logo", text);
    }

    public void clickBtnRegenerate() {
        headerPage.clickBtn("Regenerate");
    }

    public void clickBtnAddTotheme() {
        headerPage.clickBtn("Add to my store");
    }

    public void checkCheckboxDesktop(Boolean isCheck) {
        headerPage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxMobile(Boolean isCheck) {
        headerPage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxCheckoutPage(Boolean isCheck) {
        headerPage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxFavicon(Boolean isCheck) {
        headerPage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxEmail(Boolean isCheck) {
        headerPage.checkCheckboxWithLabel("Desktop", isCheck);
    }

    public void checkCheckboxAllThemes(Boolean isAll) {
        headerPage.checkCheckboxWithLabel("All themes", isAll);
    }

    public void clickBtnSave() {
        headerPage.clickBtnSave();
        headerPage.verifySaveSuccessfully(0);
    }

    public String getLogo() {
        return headerPage.getLogo();
    }

    public void checkHeaderSettings(String label, boolean isCheck) {
        headerPage.checkHeaderSettings(label,isCheck);
    }

    public void settingSize(String size) {
        headerPage.settingSize("Size",size);
    }

    public void selectMenu(String menu) {
        headerPage.selectMenu("Menu",menu);
    }
}
