package com.opencommerce.shopbase.dashboard.products.steps;

import com.opencommerce.shopbase.dashboard.products.pages.UploadImagesProductPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class UploadImagesProductSteps extends ScenarioSteps {
    UploadImagesProductPages uploadImagesProductPages;


    @Step
    public void updateManyImagesForManyVariantByGroup(String groupName) {
        uploadImagesProductPages.clickCheckBoxByGroupName(groupName);
        uploadImagesProductPages.selectUpdateImages();
    }
    @Step
    public void updateManyImageForAVariant(String optionValue, String optionValueNext){
        uploadImagesProductPages.clickOnElement("//tbody//tr[child::td[normalize-space()='"+ optionValue +"'] and child::td[normalize-space()='"+ optionValueNext +"']]//img");
    }

//    @Step
//    public void clickButtonUpdateImages() {
//        uploadImagesProductPages.clickButtonUpdateImages();
//    }

    public void verifyMockupAssignForBaseProduct(String sTotalMockup) {
        uploadImagesProductPages.verifyMockupAssignForBaseProduct(sTotalMockup);
    }

    public void viewVariant() {
        uploadImagesProductPages.viewVariant();
    }
}
