package com.opencommerce.shopbase.dashboard.apps.printbase.steps;

import com.opencommerce.shopbase.dashboard.apps.printbase.pages.CatalogPage;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.campaign.CampaignPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CatalogSteps extends ScenarioSteps {
    CatalogPage catalogPage;
    CampaignPage campaignPage;

    public float getProductPrice() {
        return catalogPage.getProductPrice();
    }
    public float getBaseProductPrice(String sproduct) {
        return catalogPage.getBaseProductPrice(sproduct);
    }

    public float getForProductBase(String product) {
        return catalogPage.getProductPriceForProduct(product);
    }

    public void selectShowPriceWithShipping(boolean isshowPriceWithShipping) {
        catalogPage.selectShowPriceWithShipping(isshowPriceWithShipping);
    }

    public void selectShippingAddress(String shippingAddress) {
        catalogPage.selectShippingAddress(shippingAddress);
    }
    public void selectAddress(String address) {
        catalogPage.selectAddress(address);
    }

    public void verifyPriceProductWithShipping(float priceAct, float priceExpected) {
        assertThat(priceExpected).isEqualTo(priceAct);
    }

    @Step
    public void verifyProcessingTime() {
        catalogPage.verifyProcessingTime();
    }

    @Step
    public void verifyProcessingTimeForProduct(String productBase, String processingTime) {
        catalogPage.verifyProcessingTimeForProductBase(productBase, processingTime);
    }

    public void verifyShippingNote() {
        catalogPage.verifyShippingNote();
    }

    @Step
    public void verifyShippingNoteForProduct(String productBase) {
        catalogPage.verifyShippingNoteForProduct(productBase);
    }

    public void clickTabName(String tabName) {
        campaignPage.switchToTabOnCatalog(tabName);
        waitABit(2000);
        campaignPage.waitUntilInVisibleLoadingTable();
//        catalogPage.clickTabName(tabName);
    }

    @Step
    public void verifyProductInCatalog(String productBase) {
        catalogPage.verifyProductInCatalog(productBase);
    }
}
