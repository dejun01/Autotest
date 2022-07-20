package opencommerce.plus_base;
import com.opencommerce.shopbase.plusbase.steps.OnboardingSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.authen.SignUpAndSignInPrintbaseDef;
import java.util.List;
public class OnboardingDef {
    @Steps
    SignUpAndSignInPrintbaseDef signUpAndSignInPrintbaseDef;

    @Steps
    OnboardingSteps onboardingSteps;

    @Then("Click Skip this step")
    public void clickSkipThisStep(List<List<String>> dataTable) {
        onboardingSteps.returnOnboardingSteps();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String steps = SessionData.getDataTbVal(dataTable, row, "Steps");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String button = SessionData.getDataTbVal(dataTable, row, "Button");
            onboardingSteps.verifySteps(steps);
            onboardingSteps.verifyDescription(description, signUpAndSignInPrintbaseDef.shops);
            onboardingSteps.verifyClickBT(button);
            onboardingSteps.returnOnboardingSteps();
            onboardingSteps.clickSkipThisStep();
        }
    }

    @Then("Click button {string}")
    public void clickButton(String btnLabel) {
        onboardingSteps.clickBtnStartNow(btnLabel);
    }

}

