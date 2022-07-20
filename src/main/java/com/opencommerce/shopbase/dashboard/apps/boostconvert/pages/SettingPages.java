package com.opencommerce.shopbase.dashboard.apps.boostconvert.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class SettingPages extends SBasePageObject {
    public SettingPages(WebDriver driver) {
        super(driver);
    }

    public void selectLayout(String sLayout) {
        int layout = Integer.parseInt(sLayout);
        selectRadioButtonWithLayout(layout, true);
    }


    public void checkCheckBoxShowOnMobile(boolean sShowOnMobile) {
        checkCheckboxWithLabel("Show on Mobile", 1, sShowOnMobile);
    }

    public void CheckCheckBoxRandomize(boolean sDisplayNotification) {
        checkCheckboxWithLabel("Display notifications in random order", 1, sDisplayNotification);
    }


    public void scrollIntoElementRanDom() {
        scrollIntoElementView("//div[@placeholder='Only display synced orders that created within the last']//div[@class='input-container']");
    }

    public void checkCheckBoxRanDomize(Boolean sRandomizeDelayTime) {
        checkCheckbox("//div[contains(@class,'margin-checkbox random-delay-time kit-checkbox-wrapper')]", 1, sRandomizeDelayTime);
    }

    public void checkCheckBoxRepeat(Boolean sRepeatSaleNotification) {
        scrollIntoElementRanDom();
        checkCheckbox("//div[@class='repeat-noti kit-checkbox-wrapper ']", 1, sRepeatSaleNotification);
    }

    public void selectRadioButtonWithLayout(int _resOrder, boolean _ischeck) {
        String xpathParent = "//div[contains(@class,'noti-layout')]//div[@class='radio']";
//        selectRadioButton(xpathParent, _resOrder, _ischeck);
    }

    public void chooseTheme(String nameTheme) {
        String xpath = "//div[contains(@class,'noti-theme-design')]//label[contains(text(),'" + nameTheme + "')]//preceding-sibling::div[@class='kit-radio-container']//span";
        clickOnElement(xpath);
    }

    public void chooseStyleThemeBasic(String style) {
//        selectDdl("//div[contains(@class,'noti-theme-design')]", style, 1);
    }


    public void verifyLayout(String sLayout) {
        waitForElementToPresent("//div[contains(@class,'" + sLayout + "')]");
    }

    public void verifyBasicThem(String sBasictheme) {
        waitForElementToPresent("//div[contains(@class,'" + sBasictheme + "')]");

    }

    public void verifyDesktopposition(String sdesktopPosition) {
        waitForElementToPresent("//div[contains(@class,'" + sdesktopPosition + "')]");

    }

    public void verifyShowNoti() {
        waitForElementToPresent("//div[contains(@class,'wrapper-noti')]");
    }
}
