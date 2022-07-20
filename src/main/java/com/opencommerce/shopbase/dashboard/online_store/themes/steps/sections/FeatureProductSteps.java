package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.FeatureProductPage;
import net.thucydides.core.annotations.Step;

public class FeatureProductSteps {
    FeatureProductPage featureProductPage;

    @Step
    public void enterProduct(String productName) {
        featureProductPage.enterProduct(productName);
    }

    @Step
    public void selectShowarrowsonproductgallery(Boolean isShowarrowsonproductgallery) {
        featureProductPage.checkCheckboxWithLabel("Show arrows on product gallery", isShowarrowsonproductgallery);
    }

    @Step
    public void selectEnableproductgalleryslideshow(Boolean isEnableproductgalleryslideshow) {
        featureProductPage.checkCheckboxWithLabel("Enable product gallery slideshow", isEnableproductgalleryslideshow);
    }

    @Step
    public void chooseGalleryTransition(String galleryTransition) {
        featureProductPage.selectDdlWithLabel("Gallery transition", galleryTransition);
    }

    @Step
    public void chooseImagePosition(String imagePosition) {
        featureProductPage.selectRadioButtonWithLabel(imagePosition, true);
    }

    @Step
    public void selectShowVendor(Boolean isShowvendor) {
        featureProductPage.checkCheckboxWithLabel("Show vendor", isShowvendor);
    }

    @Step
    public void selectShowpricesavings(Boolean isShowpricesavings) {
        featureProductPage.checkCheckboxWithLabel("Show price savings", isShowpricesavings);
    }

    @Step
    public void selectShowProductContent(Boolean isShowProductContent) {
        featureProductPage.checkCheckboxWithLabel("Show product content", isShowProductContent);
    }

    @Step
    public void chooseProductImgAlignment(String imagePosition) {
        featureProductPage.clickLinkTextWithLabel(imagePosition,1);
    }

    @Step
    public void selectShowFullProductDescription(Boolean isshowProductContent) {
        featureProductPage.checkCheckboxWithLabel("Show full product description", isshowProductContent);
    }

    @Step
    public void checkCheckboxShowFullProductDescription(Boolean isShow) {
        featureProductPage.checkCheckboxWithLabel("Show full product description", isShow);
    }
}
