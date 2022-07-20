package com.opencommerce.shopbase.Pbase.steps;

import com.opencommerce.shopbase.Pbase.pages.PbasePage;
import com.opencommerce.shopbase.common.pages.CommonPage;
import common.SBasePageObject;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PbaseSteps {
    PbasePage pbasePage;


    @Step
    public void verifyStatus(String orderNumber, String status) {
        pbasePage.verifyStatus(orderNumber, status);
    }

    @Step
    public void clickPostPurchase() {
        pbasePage.clickPostPurchase();
    }


}
