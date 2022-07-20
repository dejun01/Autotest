package com.opencommerce.shopbase.dashboard.apps.printhub.steps.fulfillment;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.fulfillment.MappingProductPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class MappingProductSteps extends ScenarioSteps {

    MappingProductPages mappingProductPages;
    @Step
    public void verifyDefineOptionDisable() {
        mappingProductPages.verifyDefineOptionDisable();
    }

    @Step
    public void verifyDefaultOption() {
        mappingProductPages.verifyDefaultOption();
    }

    @Step
    public void verifySaveButtonDisable() {
        mappingProductPages.verifySaveButtonDisable();
    }

    @Step
    public void verifyDefineOptionEnable() {
        mappingProductPages.verifyDefineOptionEnable();
    }

    @Step
    public void clickSave() {
        mappingProductPages.clickBtn("Save");
    }

    @Step
    public void selectProductMaping(String nameProduct) {
        mappingProductPages.selectProductMaping(nameProduct);
    }

    @Step
    public void verifyOptionValue(String offer,String mapWith) {
        mappingProductPages.verifyOptionValue(offer,mapWith);
    }

    @Step
    public void selectOption(String offer, String mapWith) {
        mappingProductPages.selectOption(offer,mapWith);
    }

    @Step
    public void clickBreadcrum() {
        mappingProductPages.clickBreadcrum();
    }

    @Step
    public void verifyAutoMapoption(String option, String optionmap) {
        mappingProductPages.verifyAutoMapoption(option,optionmap);
    }
    @Step
    public void verifyVariantAfterMap(String option, String optionmap) {
        mappingProductPages.verifyVariantAfterMap(option,optionmap);
    }
    @Step
    public void changeValueAutoMapoption(String optionValue, String changeOptionMapped) {
        mappingProductPages.changeValueAutoMapoption(optionValue,changeOptionMapped);
    }

    @Step
    public void clickUploadArtwork(String frontOrBack, String nameImage) {
        mappingProductPages.clickUploadArtwork(frontOrBack,nameImage);
    }

    @Step
    public void verifyMessageUploadArtwork(String message) {
        mappingProductPages.verifyMessageUploadArtwork(message);
    }

    @Step
    public void verifyButtonChangeAndDeleteArtwork(boolean elementVisible) {
        mappingProductPages.verifyButtonChangeAndDeleteArtwork(elementVisible);
    }

    @Step
    public void clickBtnSave() {
        mappingProductPages.clickBtnSave();
    }

    @Step
    public void refreshPage() {
        mappingProductPages.refreshPage();
    }

    @Step
    public void clickDeleteArtwork() {
        mappingProductPages.clickDeleteArtwork();
    }

    @Step
    public void verifyVariants(String nameProduct, String countVariants) {
        mappingProductPages.verifyVariants(nameProduct,countVariants);
    }

    @Step
    public void verifyTotalProduct(String totalProduct) {
        mappingProductPages.verifyTotalProduct(totalProduct);
    }

    @Step
    public void verifyTotalVariantsMapped(String optionValue, String totalVariantsMapped) {
        mappingProductPages.verifyTotalVariantsMapped(optionValue, totalVariantsMapped);
    }

    @Step
    public void selectAnotherOptionValue() {
        mappingProductPages.selectAnotherOptionValue();
    }

    @Step
    public void verifyTotal(String value) {
        mappingProductPages.verifyTotal(value);
    }

    @Step
    public void verifyMessage(String message) {
        mappingProductPages.verifyMessage(message);
    }

    @Step
    public void clickDiscard() {
        mappingProductPages.clickDiscard();
    }

    @Step
    public void selectTap(String tap) {
        mappingProductPages.selectTap(tap);
    }

    @Step
    public void selectCheckbox(String optionValue) {
        mappingProductPages.selectCheckbox(optionValue);
    }

    @Step
    public void verifyCountTotal(String nameProduct,String optionValue_1,String optionValue_2,String totalVariants_1,String totalVariants_2) {
        mappingProductPages.verifyCountTotal(nameProduct,optionValue_1,optionValue_2,totalVariants_1,totalVariants_2);
    }
}
