package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.PreferencesSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.CustomerAccountsSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import opencommerce.orders.checkout.CustomerInformationDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.listOrder;
import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;

public class SettingDef {
    @Steps
    SettingSteps settingSteps;
    @Steps
    PreferencesSteps preferencesSteps;

    @And("^Click to \"([^\"]*)\" section at Settings screen$")
    public void clickToTabAtSettingsScreen(String tab) {
        //settingSteps.refreshPage();
        settingSteps.clickToSection(tab);
    }

    @Then("^add staff account with email$")
    public void add_staff_account_with_email(String staffEmail) {
        List<String> mails = new ArrayList<String>(Arrays.asList(staffEmail.split(", ")));
        settingSteps.clickAddStaffAccount();
        for (int i = 0; i < mails.size(); i++) {
            System.out.println(i);
//            sStep.refreshPage();
            String email = "disposable1@" + mails.get(i);
            settingSteps.enterStaffEmail(email);
            System.out.println("Email: " + email);
            settingSteps.clickSendInvite();
            settingSteps.verifyErrorMsg("Please use the regular email address");
        }
    }

    @And("^navigate to \"([^\"]*)\" section in Settings page$")
    public void navigate_to_the_section_in_settings_page(String section) {
        settingSteps.chooseTheSection(section);
    }

    @Given("set shipping delivery time")
    public void setShippingDeliveryTime(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sMin = SessionData.getDataTbVal(dataTable, row, "Min shipping time");
            String sMax = SessionData.getDataTbVal(dataTable, row, "Max shipping time");
            shipFrom = sMin;
            shipTo = sMax;
        }
    }

    @When("setting additional with {string}")
    public void settingAdditionalWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isShowLegal = SessionData.getDataTbVal(dataTable, row, "isShowLegal");
            String isManual = SessionData.getDataTbVal(dataTable, row, "Manual confirm");
            String isAuto = SessionData.getDataTbVal(dataTable, row, "Auto confirm");
            boolean isShow= Boolean.parseBoolean(isShowLegal);
            if(isShow==true){
                settingSteps.checkedCheckboxShowlegal("Show legal agreement on the checkout page",true);
                settingSteps.settingAdditional(isManual,isAuto);
            }
            else settingSteps.checkedCheckboxShowlegal("Show legal agreement on the checkout page",false);
            if (preferencesSteps.haveChange()){
                preferencesSteps.clickBtnSave();
                preferencesSteps.verifyMsgWithLabel("All changes were successfully saved");
            }
        }
        
    }

    @And("verify tos checkout with {string}")
    public void verifyTosCheckoutWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String showCheckboxTos = SessionData.getDataTbVal(dataTable, row, "ShowCheckboxTos");

            settingSteps.scrollToCompleOrder();
            if (showCheckboxTos.equals("Manual")) {
                settingSteps.verifyCheckoxTosCheckout();
            }
            if (showCheckboxTos.equals("Auto")) {
                settingSteps.verifyAutoTosCheckout();
                settingSteps.verifyModelTermOfService();
            } else if (showCheckboxTos.equals("No")) {
                settingSteps.verifyNotShowAdditionalCheckout();
            }

        }
    }
}
