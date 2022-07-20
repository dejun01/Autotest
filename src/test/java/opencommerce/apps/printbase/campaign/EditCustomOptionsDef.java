package opencommerce.apps.printbase.campaign;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.campaigns.EditCustomOptionsSteps;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

public class EditCustomOptionsDef {
    @Steps
    EditCustomOptionsSteps editCustomOptionsSteps;

    @And("wait create camp success")
    public void waitCreateCampSuccess() {
        editCustomOptionsSteps.waitCreateCampSuccess();
    }
}
