package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.settings.steps.GeneralSteps;
import common.utilities.LoadObject;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class GeneralDef {
    @Steps
    GeneralSteps generalSteps;

    String shopname = LoadObject.getProperty("shopname");

    @Then("^Set store currency to \"([^\"]*)\"$")
    public void selectCurrencyAtGeneralSetting(String currency) {
        generalSteps.selectCurrency(currency);
    }

    @Then("verify store name$")
    public void verifyStoreName() {
        generalSteps.verifyStoreName(shopname);
    }
}
