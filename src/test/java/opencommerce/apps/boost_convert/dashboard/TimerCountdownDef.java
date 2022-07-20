
package opencommerce.apps.boost_convert.dashboard;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.TimerCountdownSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class TimerCountdownDef {
    @Steps
    TimerCountdownSteps timerCountdownSteps;

    @When("^create new timer countdown of BoostConvert$")
    public void create_new_Timer_countdown() {
        timerCountdownSteps.clickAddNewCountdownTimer();
    }

    @When("^turn off all timer countdown of BoostConvert exited$")
    public void turn_off_Timer_countdown() {
        int numberTimer = 0;
        if (timerCountdownSteps.isExistTimerList()) {
            numberTimer = timerCountdownSteps.countTimerCountdownOffer();
        }
        if (numberTimer > 1) {
            for (int i = 1; i <= numberTimer; i++) {
                timerCountdownSteps.clickBtnEditTimer(i);
                timerCountdownSteps.turnOnCountdown(false);
                timerCountdownSteps.clickBackTimerList();
            }
        }
    }

    @When("^input timer settings of BoostConvert's timer countdown as \"([^\"]*)\"$")
    public void user_input_Timer_settings_as(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isCreate = true;
            boolean sStatus = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Status"));
            String sDuration = SessionData.getDataTbVal(dataTable, row, "Duration");
            String sRepeat = SessionData.getDataTbVal(dataTable, row, "Repeat countdown timer after it ends");
            String sSchedule = SessionData.getDataTbVal(dataTable, row, "Schedule time to show Timer countdown");
            String sScheduleTime = SessionData.getDataTbVal(dataTable, row, "Schedule time");
            String sDisplayTimer = SessionData.getDataTbVal(dataTable, row, "Display timer");
            String sIsCreated = SessionData.getDataTbVal(dataTable, row, "is created");
            String trigger = SessionData.getDataTbVal(dataTable, row, "Trigger");
            String page = SessionData.getDataTbVal(dataTable, row, "Trigger pages");

            if (!sIsCreated.isEmpty()) {
                isCreate = Boolean.parseBoolean(sIsCreated);
            }
            if (isCreate) {
                timerCountdownSteps.clickAddNewCountdownTimer();
            } else {
                timerCountdownSteps.editTheFirstTimer();
                timerCountdownSteps.resetDuration();
            }
            timerCountdownSteps.turnOnTimerCountdown(sStatus);
            timerCountdownSteps.enterDuration(sDuration);
            timerCountdownSteps.repeatCountdownTimer(sRepeat);
            timerCountdownSteps.scheduleTimeToShowTimerCountdown(sSchedule, sScheduleTime);
            timerCountdownSteps.selectShowAtPage(trigger, page);
            timerCountdownSteps.setDisplayTimer(sDisplayTimer);
        }

    }

    @Then("^delete all timer countdown$")
    public void delete_all_timer_countdown() {
        while (timerCountdownSteps.isExistTimerCountdownItem()) {
            timerCountdownSteps.checkAllCountdownTimers();
            timerCountdownSteps.deleteTimerCountdown();
        }
    }

    @Then("^save timer countdown of BoostConvert$")
    public void save_Timer_countdown_and_verify_on_Storefront() {
        timerCountdownSteps.waitABit(3000);
        timerCountdownSteps.clickSaveTimerCountdown();
    }
}
