package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.UpgradePlanPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.reports.adaptors.specflow.ScenarioStep;
import net.thucydides.core.steps.ScenarioSteps;

public class UpgradePlanSteps extends ScenarioSteps {
    UpgradePlanPages upgradePlanPages;

    @Step
    public void verifyPackages() {
        upgradePlanPages.verifyPackages();
    }

    @Step
    public void verifyBlockDisableFulfillmentService(){
        upgradePlanPages.verifyTextPresent("Only use PlusHub Service", true);
        upgradePlanPages.clickBtnOnlyUseSBFullfillmentService();
        upgradePlanPages.verifyTextPresent("Sell more with an online store", true);
    }

    @Step
    public void verifyBlockEnablePaymentGateway() {
        upgradePlanPages.verifyTextPresent("Sell more with an online store",true);
        upgradePlanPages.clickBtnEnablePaymentGateway();
    }

    @Step
    public void waitButtonUpgradePlanPresent() {
        upgradePlanPages.waitUntilInvisibleLoading(8);
    }
}
