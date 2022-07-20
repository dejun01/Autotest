package com.opencommerce.shopbase.dashboard.apps.printhub.steps.campaigns;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.campaigns.CampaignPages;
import net.thucydides.core.annotations.Step;

public class CampaignSteps {
    CampaignPages campaignPages;

    @Step
    public void switchToTabOnCatalog(String catalog) {
        campaignPages.switchToTabOnCatalog(catalog);
    }

    @Step
    public void clickBtnAddProductToCampaign(String productlist) {
        if (productlist.contains(",")) {
            String[] products = productlist.split(",");
            for (int i = 0; i < products.length - 1; i++) {
                campaignPages.clickBtnAddProductToCampaign(products[i]);
            }
        } else {
            campaignPages.clickBtnAddProductToCampaign(productlist);
        }
    }

    @Step
    public void clickBtnNewCampaign() {
        campaignPages.clickBtnNewCampaign();
        campaignPages.waitForPageLoad();
        campaignPages.waitUntilInvisibleLoading(10);
    }

    public void verifyShowMessage(String message) {
        campaignPages.verifyShowMessage(message);
    }

    @Step
    public void addArtworkToCampaign(String awFront, String awBack, String baseProduct) {
        campaignPages.chooseProductToAddDesign(baseProduct);
        //add front artwork
        if (!awFront.isEmpty()) {
            campaignPages.clickBtnAddArtwork("front");
            campaignPages.addArtworkToCampaign(awFront);
            campaignPages.waitUntilImageLoadingSuccess();
        }
        //add back artwork
        if (!awBack.isEmpty()) {
            campaignPages.clickBtnAddArtwork("back");
            campaignPages.addArtworkToCampaign(awBack);
            campaignPages.waitUntilImageLoadingSuccess();
        }
    }

    @Step
    public void chooseColorsForProduct(String baseProduct, String color) {
        campaignPages.chooseProductToAddDesign(baseProduct);
        campaignPages.openAndCloseColorTable(baseProduct);
        for (int i = 1; i <= campaignPages.countNumberOfProductColor(baseProduct); i++) {
            campaignPages.hoverColorButton(baseProduct, i);
            String currentColor = campaignPages.getColorName(baseProduct);
            if (color.equalsIgnoreCase(currentColor)) {
                campaignPages.selectProductColors(baseProduct, i);
            } else {
                campaignPages.unselectProductColors(baseProduct, i);
            }
        }
        campaignPages.clickLinkTextWithLabel("Colors");
    }

    @Step
    public void chooseProductToAddDesign(String baseproduct){
        campaignPages.chooseProductToAddDesign(baseproduct);
    }

    @Step
    public void openAndCloseColorTable(String baseproduct){
        campaignPages.openAndCloseColorTable(baseproduct);
    }

    @Step
    public int countNumberOfProductColor(String baseProduct) {
        return campaignPages.countNumberOfProductColor(baseProduct);
    }

    @Step
    public void hoverColorButton(String baseProduct, int number) {
        campaignPages.hoverColorButton(baseProduct, number);
    }

    @Step
    public String getColorName(String baseProduct) {
        return campaignPages.getColorName(baseProduct);
    }

    @Step
    public void selectProductColors(String baseProduct, int number) {
        campaignPages.selectProductColors(baseProduct, number);
    }

    @Step
    public void unselectProductColors(String baseProduct, int number) {
        campaignPages.unselectProductColors(baseProduct, number);
    }

    @Step
    public void selectSizeForProduct(String baseProduct, String size) {
        campaignPages.chooseProductToAddDesign(baseProduct);
        for (int i = 1; i <= campaignPages.countProductSize(baseProduct); i++) {
            String sizeName = campaignPages.getProductSizeText(baseProduct, i);
            if (size.equalsIgnoreCase(sizeName)) {
                campaignPages.selectSizeOfProduct(baseProduct, sizeName, true);
            } else {
                campaignPages.selectSizeOfProduct(baseProduct, sizeName, false);
            }
        }
    }

    @Step
    public void clickBtnContinue() {
        campaignPages.clickBtnContinue();
        campaignPages.waitForEverythingComplete();
    }

    @Step
    public void enterTitle(String sTitle) {
        if (!sTitle.isEmpty())
            campaignPages.enterInputFieldWithLabel("Title", sTitle);
    }

    @Step
    public void enterDescription(String sDescription) {
        if (!sDescription.isEmpty())
            campaignPages.enterDescription(sDescription);
    }

    @Step
    public void showProductDetail(boolean isShowProductDetail) {
        campaignPages.checkCheckboxWithLabel("Include product details in campaign description", 1, isShowProductDetail);
    }

    @Step
    public void showSizeChart(boolean isEnableSizeChart) {
        if (isEnableSizeChart) {
            campaignPages.clickEnableSizeChart();
        }
    }

    @Step
    public void enterTags(String tags) {
        if (!tags.isEmpty()) {
            campaignPages.enterTags(tags);
        }
    }

    @Step
    public void verifyPricingTabActive() {
        campaignPages.verifyTabActive("Pricing");
    }

    @Step
    public void enterSalePrice(String baseProduct, String salePrice) {
        if (!salePrice.isEmpty())
            campaignPages.enterSalePrice(baseProduct, salePrice);
    }

    @Step
    public void enterCompareAtPrice(String baseProduct, String comparePrice) {
        if (!comparePrice.isEmpty())
            campaignPages.enterCompareAtPrice(baseProduct, comparePrice);
    }

    @Step
    public void verifyCampaignDisplayed(String campaign) {
        campaignPages.verifyCampaignDisplayed(campaign);
    }
}
