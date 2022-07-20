package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.PreferencesSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.CustomerAccountsSteps;

import java.util.List;

public class CustomerAccountsDef {
    @Steps
    CustomerAccountsSteps customerAccountsSteps;
    @Steps
    PreferencesSteps preferencesStep;

    @When("^click \"([^\"]*)\" radio button and Save$")
    public void click_radio_button_and_Save(String buttonName) {
        customerAccountsSteps.clickRadioButton(buttonName);
        if (preferencesStep.haveChange()){
            preferencesStep.clickBtnSave();
            preferencesStep.verifyMsgWithLabel("All changes were successfully saved");
        }
    }

    @Then("^verify customer infomation in checkout when have login field$")
    public void verify_customer_infomation_in_checkout_when_have_login_field() {
        customerAccountsSteps.verifyHaveLoginField();
    }

    @Then("^verify header when \"([^\"]*)\" login field$")
    public void verify_header_login_field(String _isEnable) {

        if (_isEnable.contains("have")) {
            String isEnable = _isEnable.replace("have", "true");
            customerAccountsSteps.verifyHeaderLoginField(isEnable);
        } else if (_isEnable.contains("have not")) {
            String isEnable = _isEnable.replace("have not", "false");
            customerAccountsSteps.verifyHeaderLoginField(isEnable);
        }
    }

    @Then("^verify customer infomation in checkout when haven't login field$")
    public void verify_customer_infomation_in_checkout_when_havent_login_field() {
        customerAccountsSteps.verifyHaventLoginField();
    }

    @When("click radio button by \"([^\"]*)\"$")
         public void clickRadioBtn(String key, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable,"KEY", key).keySet()){
            String condition = SessionData.getDataTbVal(dataTable, row, "Setting");
            customerAccountsSteps.clickRadioButton(condition);
            if (preferencesStep.haveChange()){
                preferencesStep.clickBtnSave();
                preferencesStep.verifyMsgWithLabel("All changes were successfully saved");
            }
        }
    }
}
