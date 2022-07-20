package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.templates;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.templates.TemplatesPage;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.upsell.InCartOfferPage;
import net.thucydides.core.annotations.Step;

public class TemplatesSteps {
    TemplatesPage templatesPage;

    @Step
    public void clickOnCreateButton(){
        templatesPage.clickOnCreateButton();
    }

    @Step
    public void inputTitle(String title){
        templatesPage.inputTitle(title);
    }

    @Step
    public void inputDescription(String description){
        templatesPage.inputDescription(description);
    }

    @Step
    public void selectOffers(String offers){
        templatesPage.deleteOffersOnTemplateDetail();
        templatesPage.selectOffers(offers);
    }

    @Step
    public void uploadImage(String image){
        templatesPage.deleteUploadedImage();
        templatesPage.uploadImage(image);
    }

    @Step
    public void selectOrganization(String placeholder, String types){
        templatesPage.deleteSelectedOrganization(placeholder);
        templatesPage.selectOrganization(placeholder, types);
    }

    @Step
    public void selectPublishAndShare(boolean templatesGallery, boolean featuredTemplate){
        templatesPage.selectPublishAndShare( templatesGallery, featuredTemplate);
    }

    @Step
    public void clickOnSaveButtonOnPopup(){
        templatesPage.clickOnSaveButtonOnPopup();
    }

    @Step
    public void clickOnSaveButton(){
        templatesPage.clickOnSaveButton();
    }

    @Step
    public void verifyMessage(String message){
        templatesPage.verifyMessage(message);
    }

    @Step
    public void verifyTemplateOnList(String title, boolean isExist){
        templatesPage.searchElement(title);
        templatesPage.verifyTemplateOnList(title, isExist);
    }

    @Step
    public void deleteTemplate(String title){
        templatesPage.searchElement(title);
        templatesPage.clickOnTemplateOnTemplatePage(title);
        templatesPage.deleteTemplate();
    }

    @Step
    public void verifyTemplateOnTemplateGallery(String title, String description){
        templatesPage.searchElement(title);
        templatesPage.verifyElementOnTemplateGallery("title", title);
        if(!description.isEmpty()){
            templatesPage.verifyElementOnTemplateGallery("description", description);
        }
    }

    @Step
    public void checkShowTemplateOnTemplateGallery(String title, boolean isShow){
        templatesPage.searchTemplateOnTemplateGallery(title);
        templatesPage.verifyShowTemplateOnTemplateGallery(title, isShow);
    }

    @Step
    public void searchTemplateOnTemplateGallery(String title){
        templatesPage.searchTemplateOnTemplateGallery(title);
    }

    @Step
    public void clickOnTemplate(){
        templatesPage.clickOnTemplate();
    }

    @Step
    public void verifyOrganizationOnTemplateGalleryDetail(String blockName, String values){
        templatesPage.verifyOrganizationOnTemplateGalleryDetail(blockName, values);
    }

    @Step
    public void verifyTitleOnTemplateGalleryDetail(String title){
        templatesPage.verifyTitleOnTemplateGalleryDetail(title);
    }

    @Step
    public void verifyDescriptionOnTemplateGalleryDetail(String description){
        templatesPage.verifyDescriptionOnTemplateGalleryDetail(description);
    }

    @Step
    public void verifyTemplateOffers(String offersName, String strategy, String type){
        templatesPage.verifyTemplateOffers( offersName,  strategy,  type);
    }

    @Step
    public void verifyUseTemplate(String type, String offer, String targetProduct, String status, String priority){
        templatesPage.navigateToPage(type);
        templatesPage.verifyUseTemplate(type, offer, targetProduct, status, priority);
    }

    @Step
    public void deleteOffers(String offersName, String type){
        templatesPage.navigateToPage(type);
        templatesPage.deleteOffers(offersName, type);
    }

    @Step
    public void clickOnUseTemplateButton(){
        templatesPage.clickOnUseTemplateButton();
    }

    @Step
    public void openTemplateOnTemplatePage(String title){
        templatesPage.searchElement(title);
        templatesPage.openTemplateOnTemplatePage(title);
    }
}
