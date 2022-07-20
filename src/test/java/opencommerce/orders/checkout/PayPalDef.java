package opencommerce.orders.checkout;

import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.steps.gateway.PaypalSteps;

import static com.opencommerce.shopbase.OrderVariable.capturedTransactionID;

public class PayPalDef {
    @Steps
    PaypalSteps paypalSteps;

    @And("^verify order information in Sandbox dashboard$")
    public void verifyOrderInformationInPaypalDashboard() {
        String transactionID = capturedTransactionID;
        System.out.println("Send to Sandbox "+transactionID+"========================");
        paypalSteps.clickActivityTabSandbox();
//        paypalSteps.searchTransactionID(transactionID);
//        paypalSteps.chooseTransactionIDFromListTrans(transactionID);
//        System.out.println(paypalSteps.getGrossAmount()+"========================");
    }

    @And("^login to Sandbox dashboard by \"([^\"]*)\"$")
    public void loginSandboxDashboard(String email) {
        paypalSteps.loginSandboxDashboard(email);
    }
}

