package com.opencommerce.shopbase.dashboard.customer.steps;

import com.opencommerce.shopbase.dashboard.customer.pages.CustomerPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerSteps extends ScenarioSteps {
    CustomerPages customerPages;

    @Step
    public void verifyCustomerNamePresent(String text) {
        customerPages.verifyTextPresent(text, true);
    }

    public void backToPreviousScreen() {
       getDriver().navigate().back();
       waitABit(8000);
    }

    @Step
    public void searchCustomerByEmail(String customerEmail) {
        customerPages.searchCustomer(customerEmail);
    }

    @Step
    public void selectCustomer(String customerName) {
        customerPages.selectCustomer(customerName);
    }

    @Step
    public void verifyShippingAddress(String shippingAddress) {
        String actualShippingAddress = customerPages.getShippingAddress();
        assertThat(actualShippingAddress).contains(shippingAddress);
    }
}
