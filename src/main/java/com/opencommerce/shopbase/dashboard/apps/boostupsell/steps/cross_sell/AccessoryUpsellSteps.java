package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.CreateOfferPage;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell.AccessoryUpsellPages;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AccessoryUpsellSteps extends ScenarioSteps {

    AccessoryUpsellPages accessoryUpsellPages;

    public void clickDelete() {
        accessoryUpsellPages.clickDelete();
    }

    public void clickBtnAdd(String label) {
        accessoryUpsellPages.clickAddAccessoryBtn(label);
    }

    public void clickBtnAddToCartOnPopup() {
        accessoryUpsellPages.clickBtnAddToCartOnPopup();
    }

    public void inputCustomOption(String customOption) {
        accessoryUpsellPages.inputCustomOption(customOption);
    }

    public void verifyDisableBtnAdd(String productTarget) {
        accessoryUpsellPages.verifyDisableBtnAdd(productTarget);
    }

    public void chooseProductVariant(String variant) {
        String[] prodVariants = variant.split(",");
        for (String prV : prodVariants) {
            accessoryUpsellPages.chooseProductVariants("Size", prV);
            accessoryUpsellPages.chooseProductVariants("Color", prV);
        }
    }

    public void verifyProductTarget(String productTarget) {
        accessoryUpsellPages.verifyProductTarget(productTarget);
    }

    public void verifyProductVariant(String variant) {
        accessoryUpsellPages.verifyVariant(variant);
    }

    public void verifyProductPrice(String price) {
        accessoryUpsellPages.verifyPrice(price);
    }

    public void verifyProductCustomOption(String customOption) {
        accessoryUpsellPages.verifyCustomOption(customOption);
    }

    public void clickOnViewMoreProd() {
        accessoryUpsellPages.clickOnViewMoreProd();
    }

    public void switchToAcessoryList() {
        accessoryUpsellPages.switchToTab("Accessories");
    }

    public boolean isSubmitBtnVisible() {
        return accessoryUpsellPages.isSubmitBtnVisible();
    }
}
