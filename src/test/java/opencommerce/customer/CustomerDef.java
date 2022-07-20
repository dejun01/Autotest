package opencommerce.customer;

import com.opencommerce.shopbase.dashboard.customer.steps.CustomerSteps;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import static com.opencommerce.shopbase.OrderVariable.*;

public class CustomerDef {
    @Steps
    CustomerSteps customerSteps;

    @And("^search customer by email then select$")
    public void searchCustomerByEmailThenSelect() {
        customerName = shippingAddressHashMap.get("Shipping Name");
//        customerEmail = "anhxinh@beeketing.net";

        customerSteps.searchCustomerByEmail(customerEmail);
        customerSteps.selectCustomer(customerName);
    }

    @And("verify cpf or cnpj is displayed correctly in customer detail")
    public void verifyShippingAddressIsDisplayedCorrectlyInCustomerDetail() {
        String cpfOrCnpj = shippingAddressHashMap.get("Shipping CPF/CNPJ Number");

        customerSteps.verifyShippingAddress(cpfOrCnpj);
    }
}
