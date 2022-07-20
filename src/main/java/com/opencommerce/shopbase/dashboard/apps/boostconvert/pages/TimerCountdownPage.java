package com.opencommerce.shopbase.dashboard.apps.boostconvert.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class TimerCountdownPage extends SBasePageObject {
    public TimerCountdownPage(WebDriver driver) {
        super(driver);
    }

    String tableTimer = "//table[contains(@class,'table-timer-countdown')]";

    public boolean isExistTimerList() {
        return isElementExist(tableTimer);
    }

    public boolean isExistTimerCountdownItem(){
        return isElementExist(tableTimer + "//tbody//tr");
    }

    public void clickBtnEditTimer(int i) {
        clickOnElement("(" + tableTimer + "//tbody//tr)[" + i + "][1]//td[@class='text-center']//span[child::i[contains(@class,'mdi-pencil')]]");
    }

    public int countTimerCountdownOffer() {
        return countElementByXpath(tableTimer + "//tbody//tr");
    }

    public void checkAllCountdownTimers() {
        checkCheckbox(tableTimer + "//thead//th//span[@data-label='Select all timers']", true);
        clickOnElement("//div[text()='Timer list']");
    }

    public void clickBtnDelete() {
        clickOnElement("//div[@class='action-table']//button");
    }

    public void confirmDelete() {
        confirmDeleteRecord();
    }

    public void enterDurationTime(String durationTime) {
        enterInputFieldWithLabel("Duration", durationTime);
    }

    public void selectDurationType(String durationType) {
        selectDdlByXpath("//div[@class='card__section'][descendant::*[text()='Duration']]//select", durationType);
    }


    public void setScheduleTime(String sScheduleTime) {
        clickThenTypeCharByChar("Pick a day", sScheduleTime);
    }

    public void selectSpecificOption(String option) {
        selectDdlByXpath("//div[@class='card__section'][descendant::*[normalize-space()='Trigger']]//select", option);
    }

    String xpathDisplay = "(//div[contains(@class,'s-form-item s')][descendant::*[normalize-space()='Display timer countdown in']]//div[@class='s-form-item__content']//label)[%d]";

    public String getDisplay(int i) {
        return getText(String.format(xpathDisplay, i));
    }

    public void selectDisPlayTimer(int i, boolean isCheck) {
        checkCheckbox(String.format(xpathDisplay, i), isCheck);
    }

    public void clickAddNewCountdownTimer() {
        if (isExistTimerList()) {
            clickOnElement("//button[descendant::span[contains(.,'Add new timer countdown')]]");
        }
    }

    public void openPopupSelector() {
        openSelector();
    }

    public void clickSaveTimerCountdown() {
        clickOnElement("//div[@class='save-setting-fixed']//button[descendant-or-self::*[normalize-space()='Create timer' or normalize-space()='Update timer']]");
    }

    public void clickBackTimerList() {
        clickOnElement("//ol[@class='breadcrumb']//a");
    }


    public void removeAllProduct() {
        String xpath = "(//div[@class='product-selected-inner']//span[@class='product-action product-action--all'])";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }
}
