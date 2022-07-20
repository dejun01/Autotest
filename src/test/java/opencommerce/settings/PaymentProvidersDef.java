package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class PaymentProvidersDef {
    @Steps
    PaymentsSettingSteps paymentsSettingSteps;

    @Then("Verify UI of the Payment providers page")
    public void verifyUIOfThePaymentProvidersPage() {
        paymentsSettingSteps.verifyHeader();
        paymentsSettingSteps.verifyStatement();
        paymentsSettingSteps.verifyAcceptCreditCard();
        paymentsSettingSteps.verifyAcceptCreditCard();
        paymentsSettingSteps.verifyPaypal();
    }
}
