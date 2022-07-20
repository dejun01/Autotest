package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.SpecificDescriptionSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class SpecificDescriptionDef {
    @Steps
    SpecificDescriptionSteps specificDescriptionSteps;

    @When("user update base description")
    public void updateBaseDescription(List<List<String>> dataTable) {
        for(int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
            String baseName = SessionData.getDataTbVal(dataTable, row, "base name");
            String descriptionBase = SessionData.getDataTbVal(dataTable, row, "base description");

            specificDescriptionSteps.clickButtonUpdateDescription(baseName);
            specificDescriptionSteps.updateBaseDescription(descriptionBase);

        }

    }

    @Then("user verify SEO description is displayed exactly")
    public void userVerifySEODescriptionIsDisplayedExactly() {
        specificDescriptionSteps.verifySEODescriptionIsDisplayedExactly();
    }
}
