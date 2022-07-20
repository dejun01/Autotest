package com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings.ProductGridPage;
import net.thucydides.core.annotations.Step;

public class ProductGridSteps {
    ProductGridPage productGridPage;

    @Step
    public void selectShowsalebanners(boolean isShowsalebanners) {
        productGridPage.checkCheckboxWithLabel("Show sale banners", isShowsalebanners);
    }

    @Step
    public void chooseOptionStyle(String optionsStyle) {
        productGridPage.chooseOptionStyle(optionsStyle);
    }

    @Step
    public void settingOptionsStyleAndSwatches(String optionsStyle, boolean isEnablevariantgroupswatches, boolean isEnablecolorswatches) {
//        chooseOptionStyle(optionsStyle);
        if (optionsStyle.equalsIgnoreCase("Buttons")) {
            productGridPage.checkCheckboxWithLabel("Enable variant group swatches", isEnablevariantgroupswatches);
            productGridPage.checkCheckboxWithLabel("Enable color swatches", isEnablecolorswatches);
        }
    }

    @Step
    public void selectHideSelectorWhenOptionHasOneValue(boolean isHideselectorwhenoptionhasonevalue) {
        productGridPage.checkCheckboxWithLabel("Hide selector when option has one value", isHideselectorwhenoptionhasonevalue);
    }

    @Step
    public void selectShowQuantityBox(boolean isShowquantitybox, boolean isShowquantityboxinthesameline) {
        productGridPage.checkCheckboxWithLabel("Show quantity box", isShowquantitybox);
        if (isShowquantitybox) {
            productGridPage.checkCheckboxWithLabel("Show quantity box in the same line with Add to cart / Buy now button", isShowquantityboxinthesameline);
        }
        productGridPage.verifyShowquantityboxinthesamelineExist(isShowquantitybox);
    }

    @Step
    public void showTrustBadgeImage(boolean showTrustBadgeImage) {
        productGridPage.checkCheckboxWithLabel("Show trust badge image", showTrustBadgeImage);
    }

    @Step
    public void chooseLinkProductOptions(String linkProductOptions) {
        productGridPage.selectRadioButtonWithLabel(linkProductOptions, true);
    }

    @Step
    public void selectSelectVariantsByClickingTheirImages(boolean selectVariantByClickingThierImage) {
        productGridPage.checkCheckboxWithLabel("Select variants by clicking their images", selectVariantByClickingThierImage);
    }


    @Step
    public void enterTextFor0Sproduct(String textFor0Sproduct) {
        productGridPage.enterTextFor0$product(textFor0Sproduct);
    }

    public void selectDescriptionPosition(String descriptionPosition) {
        if (!descriptionPosition.isEmpty()) {
            chooseOptionPosition(descriptionPosition);
        }
    }

    @Step
    public void chooseOptionPosition(String descriptionPosition) {
        productGridPage.chooseOptionPosition(descriptionPosition);
    }

    @Step
    public void selectShowPriceSavingType(String showPriceSavingsType) {
        if (!showPriceSavingsType.isEmpty()) {
            productGridPage.chooseOptionSavingType(showPriceSavingsType);
        }
    }

    @Step
    public void selectShowPriceSaving(Boolean isShowPriceSaving) {
        productGridPage.checkCheckboxWithLabel("Show price savings", isShowPriceSaving);
    }

    @Step
    public void selectShowQuantityBoxAndATC(Boolean isshowQuantityBoxAndATC) {
        productGridPage.checkCheckboxWithLabel("Show quantity box and Add to cart button", isshowQuantityBoxAndATC);

    }

    public void selectOptionStyleInside(String optionsStyle) {
        productGridPage.selectOptionStyles(optionsStyle);
    }

    public void selectTabPosition(String tabPosition) {
        productGridPage.selectDdlWithLabel("Select a default template for all product pages. You can go to the product editing page and change the template for each product manually.", tabPosition);
    }

    public void selectVariantStyle(String variantStyle) {
        productGridPage.selectDdlWithLabel("Variant style", variantStyle);
    }

    public void selectSaleType(String saleType) {
        productGridPage.selectDdlWithLabel("Sale type", saleType);
    }
}
