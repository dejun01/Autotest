package com.opencommerce.shopbase.partners.steps;

import com.opencommerce.shopbase.partners.pages.HomePage;
import net.thucydides.core.annotations.Step;

public class HomeSteps {

    HomePage homePage;

    @Step
    public void verifyHomePartner() {
        homePage.verifyHomePartner();
    }
}
