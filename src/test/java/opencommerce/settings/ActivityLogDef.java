package opencommerce.settings;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.balance.pages.BalancePage;
import com.opencommerce.shopbase.dashboard.settings.pages.AccountPage;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AddAccountStaffSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ActivityLogDef {
    String logDetail = "";
    int totalActivity = 0;
    BalancePage balancePage;

    @Steps
    AccountSteps accountSteps;
    AccountPage accountPage;


    @Then("Verify Activity log")
    public void verifyActivityLog(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String module = SessionData.getDataTbVal(dataTable, row, "Module");
            String activity = SessionData.getDataTbVal(dataTable, row, "Activity");
            String detail = SessionData.getDataTbVal(dataTable, row, "Detail");
            accountSteps.clickViewActivityLog();
            accountSteps.filterActivityLog(module, activity);
            accountSteps.verifyActivityLog(module, activity, detail);
            logDetail = detail;
            if (detail.matches("Close store")){
                totalActivity = totalActivity + 1;
            }
            if(detail.matches("Reopen store")){
                totalActivity = totalActivity - 1;
            }
        }
        int totalActivityActual = Integer.parseInt(accountSteps.getTotalActivityLogByAPI());
        assertThat(totalActivity + 1).isEqualTo(totalActivityActual);
        totalActivity = totalActivityActual;
    }


    @Then("verify log detail")
    public void verifyLogDetail(List<List<String>> dataTable) {
        accountSteps.clickOnLastestActivity(logDetail);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String attribute = SessionData.getDataTbVal(dataTable, row, "Attribute");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            if (logDetail.matches("Update balance settings")) {
                if (balancePage.autoTopup == true) {
                    accountSteps.verifyLogDetail(attribute, "on");
                } else {
                    accountSteps.verifyLogDetail(attribute, "off");
                }
            }
            if (logDetail.matches("Confirm plan")) {
                accountSteps.verifyLogDetail(attribute, value);
                accountSteps.verifyLogDetail("plan", accountPage.currentPlan);
            }
            if (logDetail.matches("Update settings")) {
                accountSteps.verifyLogDetail(attribute, value);
                accountSteps.verifyLogDetail("phone_user", accountPage.phoneNumber);
            }

        }
    }

    @And("close store and reopen")
    public void closeStoreAndReopen() {
        accountSteps.closeStore();
        accountSteps.reopenStore();
    }

    @When("Navigate to profile page")
    public void navigateToProfilePage() {
        accountSteps.navigateToProfilePage();
    }

    @And("Change first name and last name")
    public void changeFirstNameAndLastName() {
        accountSteps.changeFirstNameAndLastName();
    }

    @And("Change phone number")
    public void changePhoneNumber() {
        accountSteps.changePhoneNumber();
    }

    @When("get total activity log by API")
    public void getTotalActivityLogByAPI() {
        totalActivity = Integer.parseInt(accountSteps.getTotalActivityLogByAPI());
    }
}




