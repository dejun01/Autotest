package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.templates;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class TemplatesPage extends SBasePageObject{
    public TemplatesPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreateButton(){
        clickOnElement("//a[normalize-space()='Create Template']");
    }

    public void inputTitle(String title){
        waitClearAndType("//input[@placeholder='Template Name']", title);
    }

    public void inputDescription(String description){
        switchToIFrame("//iframe[contains(@id,'tiny-vue')]");
        waitClearAndType("//body[@id='tinymce']", description);
        switchToIFrameDefault();
    }

    public void deleteOffersOnTemplateDetail(){
        String _xpath = "//i[contains(@class,'mdi-close')]";
        while (isElementExist(_xpath)){
            clickOnElement(_xpath);
        }
    }

    public void selectOffers(String offers){
        String[] offerList = offers.split(",");
        clickOnElement("//span[normalize-space()='Browse']");

        for(String i: offerList){
            String offer = i.trim();
            String _xpath = "//label[@class='s-checkbox' and following-sibling::a[normalize-space()='"+offer+"']]";
            clickOnElement("//input[@placeholder='Search offers']");
            clearTextByJS("//input[@placeholder='Search offers']");
            inputTextByJS("//input[@placeholder='Search offers']",offer);
            waitABit(1000);
            clickOnElement(_xpath);
        }
    }

    public void deleteUploadedImage(){
        String _xpath = "//i[contains(@class,'mdi-delete')]";
        if(isElementExist(_xpath)){
            clickOnElement(_xpath);
        }
    }

    public void uploadImage(String linkImage){
        String xpath = "//input[@type='file']";
        chooseAttachmentFile(xpath, linkImage);
    }

    public void deleteSelectedOrganization(String placeholder){
        while (isElementExist("//div[@class='tag-manager' and descendant::input[@placeholder='"+placeholder+"']]//span[contains(@class,'tag-list-item')]")){
            clickOnElement("//div[@class='tag-manager' and descendant::input[@placeholder='"+placeholder+"']]//span[contains(@class,'tag-list-item')]//a");
        }
    }

    public void selectOrganization(String placeholder, String types){
        String[] typeList = types.split(",");

        clickOnElement("//input[@placeholder='"+placeholder+"']");
        for(String i : typeList){
            String type = i.trim();

            waitClearAndType("//input[@placeholder='"+placeholder+"']", type);
            waitABit(1000);
            clickOnElement("//span[normalize-space()='" +type+ "']");
        }
    }

    public void selectPublishAndShare(boolean templatesGallery, boolean featuredTemplate){
        checkCheckbox("//label[@class='s-checkbox' and descendant::span[normalize-space()='Templates Gallery']]", templatesGallery);
        checkCheckbox("//label[@class='s-checkbox' and descendant::span[normalize-space()='Featured Template']]", featuredTemplate);

//        if(templatesGallery){
//            clickOnElement("//label[@class='s-checkbox' and descendant::span[normalize-space()='Templates Gallery']]");
//        }
//        if(featuredTemplate){
//            clickOnElement("//label[@class='s-checkbox' and descendant::span[normalize-space()='Featured Template']]");
//        }
    }

    public void clickOnSaveButtonOnPopup(){
        clickOnElement("//div[@class='s-modal-footer']//descendant::button//descendant::span[normalize-space()='Save']");
    }

    public void clickOnSaveButton(){
        clickOnElement("//span[normalize-space()='Save']//parent::button");
    }

    public void verifyMessage(String message){
            waitForTextToAppear(message, 10000);
    }

    public void verifyTemplateOnList(String title, boolean isExist){
        verifyElementPresent("//a[contains(@href,'/admin/apps/boost-upsell/template') and normalize-space()='"+title+"']", isExist);
    }

    public void searchElement(String title){
        waitClearAndTypeThenEnter("//div[contains(@class,'s-input') and descendant::i[contains(@class,'mdi-magnify')]]//input",title);
    }

    public void clickOnTemplateOnTemplatePage(String title){
        clickOnElement("//a[contains(@href,'/admin/apps/boost-upsell/template') and normalize-space()='"+title+"']");
    }

    public void verifyShowTemplateOnTemplateGallery(String title, boolean isShow){
        verifyElementPresent("//a[contains(@href,'/admin/apps/boost-upsell/template') and normalize-space()='"+title+"']", isShow);
    }

    public void clickOnButton(String buttonName){
        scrollToElement("//button[descendant::span[normalize-space()='"+buttonName+"']]");
        clickOnElement("//button[descendant::span[normalize-space()='"+buttonName+"']]");
    }

    public void deleteTemplate(){
        scrollToElement("//button[descendant::span[normalize-space()='Delete']]");
        clickOnElement("//button[descendant::span[normalize-space()='Delete']]");
        clickOnElement("//button[contains(@class,'btn-confirm-delete') and normalize-space()='OK']");
    }

    public void verifyElementOnTemplateGallery(String field, String value){
        verifyElementContainsText("//div[@class='upsell-card__content']//descendant::p[contains(@class,'"+field+"')]", value);
    }

    public void searchTemplateOnTemplateGallery(String title){
        waitClearAndTypeThenEnter("//input[@placeholder='Enter keyword to search']", title);
    }

    public void clickOnTemplate(){
        clickOnElement("//div[contains(@class,'upsell-card')]");
    }

    public void verifyOrganizationOnTemplateGalleryDetail(String blockName, String values){
        String[] valueList = values.split(",");
        for(String i: valueList){
            String value = i.trim();
            verifyElementPresent("//div[@class='s-flex--verticle s-w100' and descendant::p[normalize-space()='"+blockName+"']]//span//span[normalize-space()='"+value+"']", true);
        }
    }

    public void verifyTitleOnTemplateGalleryDetail(String title){
        verifyElementContainsText("//p[@class='type--bold font-16']", title);
    }

    public void verifyDescriptionOnTemplateGalleryDetail(String description){
        verifyElementContainsText("//div[@class='border-bottom s-py16']//p//p", description);
    }

    public void verifyTemplateOffers(String offersName, String strategy, String type){
        verifyElementPresent("//tr[descendant::td[normalize-space()='"+offersName+"']]//td[normalize-space()='"+strategy+"']", true);
        verifyElementPresent("//tr[descendant::td[normalize-space()='"+offersName+"']]//td[normalize-space()='"+type+"']", true);
    }

    public void verifyUseTemplate(String type, String offer, String targetProduct, String status, String priority){

        waitClearAndTypeThenEnter("//input[@placeholder='Enter keyword to search']", offer);
        if(type.equalsIgnoreCase("Pre-purchase") || type.equalsIgnoreCase("Post-purchase")){
            verifyElementContainsText("//tr[descendant::td[@class='offer-name' and descendant::span[normalize-space()='" +offer+ "']]]//td[@class='offer-type']", type);
        }
        verifyElementContainsText("//tr[descendant::td[@class='offer-name' and descendant::span[normalize-space()='" +offer+ "']]]//td[@class='offer-status']//div", status);
        verifyElementContainsText("//tr[descendant::td[@class='offer-name' and descendant::span[normalize-space()='" +offer+ "']]]//td[@class='target-items']//a", targetProduct);
    }

    public void navigateToPage(String type){
        if(type.equalsIgnoreCase("Upsell") || type.equalsIgnoreCase("Pre-purchase") || type.equalsIgnoreCase("Post-purchase")){
            clickOnElement("//a[@href='/admin/apps/boost-upsell/up-sell/list']");
        }else if(type.equalsIgnoreCase("Quantity")){
            clickOnElement("//a[@href='/admin/apps/boost-upsell/cross-sell/quantity-offer/list']");
        }else {
            clickOnElement("//a[@href='/admin/apps/boost-upsell/cross-sell/accessories/list']");
        }
    }

    public void deleteOffers(String offersName, String type){
        String[] offerList = offersName.split(",");

        for(String i : offerList){
            String offer = i.trim();
            waitClearAndTypeThenEnter("//input[@placeholder='Enter keyword to search']", offer);
            clickOnElement("//tr[descendant::td[@class='offer-name' and descendant::span[normalize-space()='"+offer+"']]]//td[@class='checkbox']//span");
            clickOnElement("//span[normalize-space()='Action']");
            clickOnElement("//span[normalize-space()='Delete']");
            clickOnElement("//button[normalize-space()='Delete']");

            waitForTextToAppear("Successfully removed offer", 3000);
        }
    }

    public void clickOnUseTemplateButton(){
        clickOnElement("//span[normalize-space()='Use Template']");
        waitABit(2000);
    }

    public void openTemplateOnTemplatePage(String title){
        clickOnElement("//a[normalize-space()='" +title+ "']");
    }
}
