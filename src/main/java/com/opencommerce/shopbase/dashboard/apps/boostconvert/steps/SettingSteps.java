package com.opencommerce.shopbase.dashboard.apps.boostconvert.steps;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.pages.SettingPages;
import net.thucydides.core.steps.ScenarioSteps;

public class SettingSteps extends ScenarioSteps {
    SettingPages settingPages;

    public void selectLayOut(String sLayout) {
        settingPages.selectLayout(sLayout);

    }

    public void settingTheme(String sTheme) {
        if (!sTheme.isEmpty()) {
            String[] data=sTheme.split(">");
            String nameTheme=data[0];
            String customize=data[1];

            chooseTheme(nameTheme);
            customizeTheme(nameTheme,customize);

        }
    }

    public void customizeTheme(String nameTheme, String customize) {
        if(nameTheme.equals("Basic theme")){
            choostStyleThemeBasic(customize);
        }
        else {
            String[] data=customize.split(",");
            String body=data[0];
            settingPages.enterInputFieldWithLabel("Body background color",body);
        }
    }

    public void choostStyleThemeBasic(String style) {
        settingPages.chooseStyleThemeBasic(style);
    }

    public void chooseTheme(String nameTheme) {
        settingPages.chooseTheme(nameTheme);
    }

    public void selectDesktopPosition(String sDesktopPosition) {
        if (!sDesktopPosition.isEmpty()) {
            settingPages.selectDdlWithLabel("Desktop position", sDesktopPosition);
        }
    }

    public void selectMobilePosition(String sMobilePosition) {
        if (!sMobilePosition.isEmpty()) {
            settingPages.selectDdlWithLabel("Mobile position", sMobilePosition);
        }
    }

    public void checkShowOnMobile(boolean sShowOnMobile) {
        settingPages.checkCheckBoxShowOnMobile(sShowOnMobile);

    }

    public void inputDislayTime(String sDisplayTime) {
        if (!sDisplayTime.isEmpty()) {
            settingPages.enterInputFieldWithLabel("Display time", sDisplayTime);
        }

    }

    public void inputMaximumPerPage(String sMaximumPerPage) {
        if (!sMaximumPerPage.isEmpty()) {
            settingPages.enterInputFieldWithLabel("Maximum per page", sMaximumPerPage);
        }
    }

    public void checkDisplayNotiofication(boolean sDisplayNotification) {
        settingPages.CheckCheckBoxRandomize(sDisplayNotification);

    }

    public void inputDelayTime(String sDelayTimeBetweenNotification) {
        if (!sDelayTimeBetweenNotification.isEmpty()) {
            settingPages.enterInputFieldWithLabel("Delay time between notifications", sDelayTimeBetweenNotification);
        }
    }

    public void inputDelayTimeShowPopUp(String sDelayTimeShowPopUp) {
        if (!sDelayTimeShowPopUp.isEmpty()) {
            settingPages.enterInputFieldWithLabel("Delay time to show popup after loading page", sDelayTimeShowPopUp);
        }
    }

    public void checkRanDomizeDelayTime(Boolean sRandomizeDelayTime) {
        settingPages.checkCheckBoxRanDomize(sRandomizeDelayTime);
    }

    public void checkRepeatSale(Boolean sRepeatSaleNotification) {
        settingPages.scrollIntoElementRanDom();
        settingPages.checkCheckBoxRepeat(sRepeatSaleNotification);
    }

    public void inputOnlyDisplaySynced(String sOnlyDispalySynced) {
        if (!sOnlyDispalySynced.isEmpty()) {
            settingPages.enterInputFieldWithLabel("Only display synced orders that create within the last", sOnlyDispalySynced);
        }
    }

    public void clickButtonSave() {
        waitABit(300);
        if (settingPages.isClickableBtn("Save")) {
            settingPages.clickBtn("Save");
            settingPages.verifyTextPresent("Saved all changes", true);
        }
    }


    public void verifyLayout(String sLayout) {
        settingPages.verifyLayout(sLayout);
    }

    public void verifyShowNoti() {
        settingPages.verifyShowNoti();
    }

    public void verifyBasicTheme(String sBasictheme) {
        settingPages.verifyBasicThem(sBasictheme);

    }

    public void verifydestopPosition(String sdesktopPosition) {
        settingPages.verifyDesktopposition(sdesktopPosition);
    }
}
