package opencommerce.settings;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.TippingSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static com.opencommerce.shopbase.OrderVariable.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TippingDef {
    @Steps
    TippingSteps tippingSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;

    @And("^user choose (show|not show) tipping options$")
    public void userChooseNotShowTippingOptions(String isShowing) {
        switch (isShowing) {
            case "not show":
                tippingSteps.showTippingOptions(false);
                tippingSteps.saveTippingOptions();
                break;
            case "show":
                tippingSteps.showTippingOptions(true);
                tippingSteps.saveTippingOptions();
                break;
        }
    }

    @Then("^verify UI of tipping module when (show|not show) tipping options$")
    public void verifyUIOfTippingModule(String isShowing) {
        switch (isShowing) {
            case "not show":
                tippingSteps.verifyTippingUI(false);
                break;
            case "show":
                tippingSteps.verifyTippingUI(true);
                break;
        }
    }

    @Then("^verify input field tipping options (without|with) saving$")
    public void verifyInputFieldTippingOptions(String savingAction, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            for (int i=1; i<4 ; i++) {
                String option = SessionData.getDataTbVal(dataTable, row, "Option " + i);
                String expected = SessionData.getDataTbVal(dataTable, row, "Expected " + i);
                tippingSteps.enterTippingOption(i, option);
                if (savingAction.equalsIgnoreCase("with")) {
                    tippingSteps.saveTippingOptions();
                    tippingSteps.verifyTippingOptionAfterSaved(i, expected);
                } else {
                    tippingSteps.verifyMessageAfterEnterInput(i, expected);
                }
            }
        }
    }

    @When("^user choose to (enable|disable) tipping$")
    public void userChooseToEnableTipping(String statusOfTipping) {
        String labelName = "Show your support for the team at " + LoadObject.getProperty("shopname");
        tippingSteps.verifyLabelName(labelName);
        switch (statusOfTipping) {
            case "enable":
                //verify that tipping block is not enabled before
                tippingSteps.verifyTippingIsNotSelected(labelName);
                tippingSteps.verifyDisplayOfTipLine(false);
                //then enable tipping block
                tippingSteps.clickOnTippingBlock();
                tippingSteps.verifyTippingOptionOnSFWhenEnable(numberOfTippingOption);
                break;
            case "disable":
                break;
        }
    }

    @Then("^input \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for each \"([^\"]*)\" then save$")
    public void inputThenSave(String option1, String option2, String option3, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "Case", dataKey).keySet()) {
            String expectedOption1 = SessionData.getDataTbVal(dataTable, row, "Expected 1");
            String expectedOption2 = SessionData.getDataTbVal(dataTable, row, "Expected 2");
            String expectedOption3 = SessionData.getDataTbVal(dataTable, row, "Expected 3");
            enterTippingOptionThenValidate(1, option1, expectedOption1);
            enterTippingOptionThenValidate(2, option2, expectedOption2);
            enterTippingOptionThenValidate(3, option3, expectedOption3);
            tippingOption.add(Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Expected on SF 1")));
            tippingOption.add(Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Expected on SF 2")));
            tippingOption.add(Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Expected on SF 3")));
            numberOfTippingOption = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Expected No Of Tipping on SF"));
            System.out.println(tippingOption.get(0) + " " + tippingOption.get(1) + " " + tippingOption.get(2) + " " + numberOfTippingOption);
        }
    }

    private void enterTippingOptionThenValidate(int index, String option, String expected) {
        tippingSteps.enterTippingOption(index, option);
        tippingSteps.saveTippingOptions();
        tippingSteps.verifyTippingOptionAfterSaved(index, expected);
    }

    @Then("verify tipping value on StoreFront")
    public void verifyTippingValueOnStoreFront() {
        for (int i=0; i<numberOfTippingOption ; i++) {
            tippingSteps.verifyTippingValueOnSF(i+1, tippingOption.get(i));
        }
    }

    @Then("^click to add (1st|2nd|3rd|custom) tipping option$")
    public void clickToAddTippingOption(String orderOfTippingOption) {
        switch (orderOfTippingOption) {
            case "1st":
                tippingSteps.clickToAddTippingOption(1);
                break;
            case "2nd":
                tippingSteps.clickToAddTippingOption(2);
                break;
            case "3rd":
                tippingSteps.clickToAddTippingOption(3);
                break;
            case "custom":

                break;
        }
        isTippingAdded = true;
        tippingAmountAdded = tippingSteps.getTippingAmountOnCartSummary();
    }

    @And("^apply discount code for for each \"([^\"]*)\"$")
    public void applyDiscountCodeForForEach(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "Case", dataKey).keySet()) {
            String discountCode = SessionData.getDataTbVal(dataTable, row, "Discount code");
            tippingSteps.enterDiscountCode(discountCode);
            float_totalAmt = orderSummarySteps.getTotalPrice();
        }
    }
}
