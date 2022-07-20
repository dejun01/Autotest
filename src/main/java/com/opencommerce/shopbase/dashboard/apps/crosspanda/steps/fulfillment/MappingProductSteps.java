package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.fulfillment.MappingProductPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingProductSteps {

    MappingProductPage mappingProductPage;

    public void verifyMessageMapping(String msg) {
        String actMsg = mappingProductPage.getMsgMapping();
        assertThat (actMsg).isEqualTo(msg);
    }

    @Step
    public void selectPurchasedProduct(String purchasedProduct) {
        mappingProductPage.searchPurchasedProduct(purchasedProduct);
        mappingProductPage.clickBtnSetPurchasedProduct();
    }

    public void selectOptionProduct(String option) {
    }

    public void selectValueOptionProduct(String value) {

    }
}
