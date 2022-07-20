package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.CustomerAccountsPage;
import net.thucydides.core.annotations.Step;

public class CustomerAccountsSteps {
    CustomerAccountsPage customerAccountsPage;

    @Step
    public void clickRadioButton(String buttonName) {
        customerAccountsPage.selectRadioButtonWithLabel(buttonName,true);

    }

    @Step
    public void clickSaveButton() {
        customerAccountsPage.clickBtn("Save changes");
        if(customerAccountsPage.isSaveSuccessfully()) {
            customerAccountsPage.waitForTextToDisappear("All changes were successfully saved");
        }
    }


    @Step
    public void verifyHaveLoginField() {
        customerAccountsPage.verifyHaveLoginField();
    }


    @Step
    public void verifyHeaderHaveLoginField(boolean isEnable) {
        customerAccountsPage.refreshPage();
        customerAccountsPage.waitForEverythingComplete();
        customerAccountsPage.verifyHeaderHaveLoginField(isEnable);
    }

    @Step
    public void verifyHeaderLoginField(String _isEnable) { //F
        boolean isEnable = Boolean.parseBoolean(_isEnable);

        if(isEnable) {
            boolean check = customerAccountsPage.headerHaveLoginField();
            if(!check) {
                verifyHeaderHaveLoginField(isEnable);
            }
        }
        else {
            boolean check = customerAccountsPage.headerHaveLoginField();
            if(check) {
                verifyHeaderHaveLoginField(isEnable);
            }
        }
    }

    @Step
    public void verifyHaventLoginField() {
        customerAccountsPage.verifyHaventLoginField();
    }

    @Step
    public void verifySuccessMgs() {
        customerAccountsPage.verifySuccessMgs();
    }
}
