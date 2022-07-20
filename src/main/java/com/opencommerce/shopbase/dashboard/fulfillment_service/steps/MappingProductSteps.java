package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.MappingProductPage;
import common.CommonPageObject;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingProductSteps {
    MappingProductPage mappingProductPage;

    @Step
    public void clickBTAction(String name) {
        mappingProductPage.clickBTAction(name);
    }

    @Step
    public void chooseStore(String name) {
        mappingProductPage.chooseStore(name);
    }

    @Step
    public void clickBTMapping() {
        mappingProductPage.clickBTMapping();
    }

    @Step
    public void enterNameOrder(String name) {
        mappingProductPage.enterNameOrder(name);
    }

    @Step
    public void clickBTContinue() {
        mappingProductPage.clickBTContinue();
    }

    @Step
    public void verifyNameProduct(String nameProductSbase, String nameProductOdoo) {
        assertThat(mappingProductPage.getListText().get(0)).isEqualTo(nameProductSbase);
        assertThat(mappingProductPage.getListText().get(1).replace("...", "").concat("wo Piece Skirts Sets")).isEqualTo(nameProductOdoo);


    }

    @Step
    public void mappingOrder(String variantValueSbase, String variantValueOdoo) {
        mappingProductPage.mappingOrderSbase(variantValueSbase);
        mappingProductPage.mappingOrderOdoo(variantValueOdoo);
    }

    @Step
    public void displayMess(String mess) {
        mappingProductPage.verifyDisplayMess(mess);
    }

    @Step
    public void clickBTMappingProductRedirect() {
        mappingProductPage.clickBTMappingProductRedirect();
    }

    @Step
    public void clickSave() {
        mappingProductPage.clickSave();
    }

    @Step
    public void verifyTextErr(String err) {
        assertThat(mappingProductPage.getTextErr()).isEqualTo(err);
    }

    @Step
    public void clickRemoveProduct(String name) {
        mappingProductPage.clickRemoveProduct(name);

    }

    @Step
    public void clickEdit(String name) {
        mappingProductPage.clickEdit(name);

    }

    @Step
    public int verifyBeforeRemove() {
        return mappingProductPage.verifyListPro();

    }

    @Step
    public void verifyAfterRemove(int listProductBefo) {
        assertThat(mappingProductPage.verifyListPro()).isEqualTo(listProductBefo - 1);

    }

    @Step
    public void clearData() {
        mappingProductPage.clearData();
    }

    @Step
    public void verifyInforMappingProd(String variantValueSbase, String variantValueOdoo) {
        mappingProductPage.verifyInforMappingProd(variantValueSbase, variantValueOdoo);
    }
    @Step
    public void mappingVariantOdoo(String variantValueOdoo) {
        mappingProductPage.mappingOrderVariantOdoo(variantValueOdoo);

    }
    public void searchProductImport(String nameProductOdoo) {
        mappingProductPage.searchProductImport(nameProductOdoo);
    }

    public void verifyDisableSave(String button) {
        mappingProductPage.verifyDisableSave(button);
    }
}
