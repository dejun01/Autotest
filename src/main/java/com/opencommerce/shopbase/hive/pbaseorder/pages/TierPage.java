package com.opencommerce.shopbase.hive.pbaseorder.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TierPage extends SBasePageObject {
    public TierPage(WebDriver driver) {
        super(driver);
    }

    public void verifyNumberOfTier() {
        int number = (getListText("//tbody//tr")).size();
        assertThat(number).isEqualTo(5);
    }

    public void verifyTierName() {
    }

    public void verifyTierThreshold() {
    }

    public void verifyTierThresholdKeep() {
    }

    public void verifyTierCycle() {
    }

    public void clickBtnEditTier(String tier) {
        clickOnElement("//tbody//td[normalize-space()='" + tier + "' and not(child::a)]//parent::tr//a[@title='Edit']");
    }

    public void verifyErrorMessages(String msg) {
        String mes = getText("//div[@class='alert alert-danger alert-dismissable']");
        assertThat(mes).contains(msg);
    }

    public void scrollIntoCycleInput() {
        scrollIntoElementView("//div[@class='form-group' and descendant::label[normalize-space()='Cycle']]//input");
    }

    public void clearMessages() {
        clickOnElement("//div[@class='alert alert-danger alert-dismissable']//button");
    }

    public boolean isExistedBtnEdit() {
        return isElementExist("//a[@title='Edit']");
    }

    public void verifyResetCycle(String curStart, String curEnd, String nextCycle) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatNowMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");
        String currentDay = formatNowDay.format(currentTime);
        String currentMonth = formatNowMonth.format(currentTime);
        String currentYear = formatNowYear.format(currentTime);

        SimpleDateFormat formatTierDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatTierMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatTierYear = new SimpleDateFormat("YYYY");

        long cur = Long.parseLong(curStart + "000");
        String tierDay = formatTierDay.format(cur);
        String tierMonth = formatTierMonth.format(cur);
        String tierYear = formatTierYear.format(cur);

        assertThat(tierDay).isEqualTo(currentDay);
        assertThat(tierMonth).isEqualTo(currentMonth);
        assertThat(tierYear).isEqualTo(currentYear);

    }

    public void waitToAPIupdate(int time) {
        waitABit(time);
    }
}
