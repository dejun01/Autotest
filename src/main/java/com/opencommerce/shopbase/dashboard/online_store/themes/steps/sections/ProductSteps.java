package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.ProductPage;
import net.serenitybdd.screenplay.actions.ScrollToWebElement;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    ProductPage productPage;

    @Step
    public void selectEnableProductGalleryPopup(Boolean isEnableProductGalleryPopup) {
        productPage.checkCheckboxWithLabel("Enable product gallery popup", isEnableProductGalleryPopup);
    }

    @Step
    public void slectShowBreadcrumbLinks(Boolean isShowBreadcrumbLinks) {
        productPage.checkCheckboxWithLabel("Show breadcrumb links", isShowBreadcrumbLinks);
    }

    @Step
    public void selectShowVendor(Boolean isShowVendor) {
        productPage.checkCheckboxWithLabel("Show vendor", isShowVendor);
    }

    @Step
    public void selectShowSKU(Boolean isShowSKU) {
        productPage.checkCheckboxWithLabel("Show SKU", isShowSKU);
    }

    @Step
    public void selectShowPriceSavings(Boolean isShowPriceSavings) {
        productPage.checkCheckboxWithLabel("Show price savings", isShowPriceSavings);
    }

    @Step
    public void selectShowCollections(Boolean isShowCollections) {
        productPage.checkCheckboxWithLabel("Show collections", isShowCollections);
    }

    @Step
    public void selectShowTypes(Boolean isShowTypes) {
        productPage.checkCheckboxWithLabel("Show types", isShowTypes);
    }

    @Step
    public void selectShowTags(Boolean isShowTags) {
        productPage.checkCheckboxWithLabel("Show tags", isShowTags);
    }

    @Step
    public void selectShowSocialMediaShareIcons(Boolean isShowSocialMediaShareIcons) {
        productPage.checkCheckboxWithLabel("Show social media share icons", isShowSocialMediaShareIcons);
    }

    @Step
    public void selectShowStickyButton(Boolean isShowStickyButton) {
        productPage.checkCheckboxWithLabel("Show Sticky button", isShowStickyButton);
    }

    @Step
    public void selectShowTrustBadgeImage(Boolean isShowTrustbadgeImage) {
        productPage.checkCheckboxWithLabel("Show trust badge image", isShowTrustbadgeImage);
    }

    @Step
    public void uploadTrustBadgeImage(String trustBadgeImage) {

    }

    public void selectEnableProductBreakcrumb(Boolean isshowBreadcrumbLinks) {
        productPage.checkCheckboxWithLabel("Enable product breadcrumb", isshowBreadcrumbLinks);
    }

}
