package com.opencommerce.shopbase.dashboard.apps.boostconvert.steps;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.pages.TimerCountdownPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static common.utilities.DateTimeUtil.getNextByFormat;

public class TimerCountdownSteps extends ScenarioSteps {
    TimerCountdownPage timerCountdownPage;

    public void clickAddNewCountdownTimer() {
        timerCountdownPage.clickAddNewCountdownTimer();
    }

    @Step
    public void enterDuration(String sDuration) {
        String dur[] = sDuration.split(">");
        timerCountdownPage.enterDurationTime(dur[0]);
        timerCountdownPage.selectDurationType(dur[1]);
    }


    @Step
    public void selectPage(String page) {
        String tg[] = page.split(">");
        timerCountdownPage.selectSpecificOption(tg[0]);
        timerCountdownPage.openPopupSelector();
        timerCountdownPage.removeAllProduct();
        timerCountdownPage.addItemOnSelectorPopup(tg[1]);


    }

    @Step
    public void selectShowAtPage(String trigger, String page) {
        timerCountdownPage.selectRadioButtonWithLabel(trigger, true);
        if (trigger.equalsIgnoreCase("Show for some products I specify")) {
            selectPage(page);
        }
    }

    @Step
    public void setDisplayTimer(String sDisplayTimer) {
        if (!sDisplayTimer.isEmpty()) {
            for (int i = 1; i <= 4; i++) {
                String time = timerCountdownPage.getDisplay(i);
                if (sDisplayTimer.contains(time.substring(0, 1))) {
                    timerCountdownPage.selectDisPlayTimer(i, true);
                } else {
                    timerCountdownPage.selectDisPlayTimer(i, false);

                }
            }
        }
    }

    @Step
    public void clickSaveTimerCountdown() {
        timerCountdownPage.waitForEverythingComplete();
        timerCountdownPage.clickSaveTimerCountdown();
        timerCountdownPage.waitForEverythingComplete();

    }

    @Step
    public boolean isExistTimerList() {
        return timerCountdownPage.isExistTimerList();
    }

    @Step
    public boolean isExistTimerCountdownItem(){
        return timerCountdownPage.isExistTimerCountdownItem();
    }

    @Step
    public void editTheFirstTimer() {
        timerCountdownPage.clickBtnEditTimer(1);
    }


    //-------------------
    public void turnOnTimerCountdown(boolean isTurnOn) {
        timerCountdownPage.turnOnToggleWithLabel(isTurnOn);
    }

    @Step
    public void checkAllCountdownTimers() {
        timerCountdownPage.checkAllCountdownTimers();

    }

    @Step
    public void deleteTimerCountdown() {
        timerCountdownPage.clickBtnDelete();
        timerCountdownPage.confirmDelete();
        timerCountdownPage.waitUntilInvisibleLoading(5);
    }

    @Step
    public void repeatCountdownTimer(String sRepeat) {
        timerCountdownPage.checkCheckboxWithLabel("Repeat countdown timer after it ends", 1, Boolean.parseBoolean(sRepeat.toLowerCase()));
    }

    @Step
    public void scheduleTimeToShowTimerCountdown(String sSchedule, String sScheduleTime) {
        boolean isSchedule = Boolean.parseBoolean(sSchedule.toLowerCase());
        timerCountdownPage.checkCheckboxWithLabel("Schedule time to start Timer countdown", 1, isSchedule);
        if (isSchedule) {
            setScheduleTime(sScheduleTime);
        }
    }

    public void setScheduleTime(String sScheduleTime) {

        if (!sScheduleTime.equalsIgnoreCase("@TODAY@")) {

            if (sScheduleTime.equalsIgnoreCase("@NEXTDAY@")) {
                sScheduleTime = getNextByFormat("yyyy-MM-dd HH:mm:ss");
            }
            timerCountdownPage.setScheduleTime(sScheduleTime);
        }
    }


    public int countTimerCountdownOffer() {
        return timerCountdownPage.countTimerCountdownOffer();
    }

    public void clickBtnEditTimer(int i) {
        timerCountdownPage.clickBtnEditTimer(i);
    }

    public void turnOnCountdown(boolean isOn) {
        timerCountdownPage.turnOnToggleWithLabel(isOn);
    }

    public void clickBackTimerList() {
        timerCountdownPage.clickBackTimerList();
    }

    public void resetDuration() {
        timerCountdownPage.clickBtn("Reset");
    }
}
