package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.ImageWithTextPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ImageWithTextSteps extends ScenarioSteps {
    ImageWithTextPage imageWithTextPage;

    String shopDomain = LoadObject.getProperty("shop");

    @Step
    public void selectLayout(String layout){
        imageWithTextPage.selectLayout(layout);
    }

    @Step
    public void checkCheckBox(String label, boolean isCheck){
        imageWithTextPage.checkCheckboxWithLabel(label, isCheck);
    }

    @Step
    public void checkCheckBoxContentActive(String label, boolean isCheck){
        imageWithTextPage.checkCheckBoxContentActive(label, isCheck);
    }

    @Step
    public void selectTextAlignment(String textAlignment){
        imageWithTextPage.selectTextAlignment(textAlignment);
    }

    @Step
    public void RemoveAllContent(String label){
        int countElement = imageWithTextPage.countElementContent(label);

        for(int i=0; i<countElement; i++){
            removeContent(1);
        }
    }

    public void removeContent(int index){
        imageWithTextPage.openContent(index);
        imageWithTextPage.clickRemoveButton();
    }

    @Step
    public void addContent(){
        imageWithTextPage.addContent();
    }

    @Step
    public void inputImage(String path){
        imageWithTextPage.inputImage(path);
    }

    @Step
    public void inputTextBox(String label, String text){
        imageWithTextPage.inputTextBox(label, text);
    }

    @Step
    public void inputLink(String label, String link){
        if(imageWithTextPage.isExistLink(label)){
            imageWithTextPage.removeLink(label);
        }

        if (!link.isEmpty()) {
            String[] data = link.split(">");

            if(data.length == 1){
                if(data[0].equalsIgnoreCase("Home Page")){
                    imageWithTextPage.chooseTypeLinkPageByLabel(data[0], "", label);
                }else {
                    imageWithTextPage.addLink(data[0], label);
                }
            }
            if (data.length == 2) {
                imageWithTextPage.chooseTypeLinkPageByLabel(data[0], data[1], label);
            }
        }
    }

    @Step
    public void inputTextContentWithIframe(String text){
        imageWithTextPage.inputTextContentWithIframe(text);
    }

    @Step
    public void verifyLayout(String layout){
        imageWithTextPage.verifyLayout(layout);
    }

    @Step
    public void verifyFullWidth(String label, boolean isFullWidth, String layout){
        if(!layout.equalsIgnoreCase("box"))
        imageWithTextPage.verifyFullWidth(label, isFullWidth);
    }

    @Step
    public void verifyTextAlignment(String label, String text, int index){
        imageWithTextPage.verifyTextAlignment(label, text, index);
    }

    @Step
    public void verifyAltText(String label, String text, int index){
        imageWithTextPage.verifyAltText(label, text, index);
    }


    @Step
    public void verifyImageLink(String label, String link, int index){
        if(!link.isEmpty()) {
            imageWithTextPage.verifyImageLink(label, link, index);
        }
    }

    @Step
    public void verifyNavigationPage(String link){
        String expectedURL = "https://" + shopDomain + link;
        imageWithTextPage.switchToWindowWithIndex(0);
        String actualURL = imageWithTextPage.getCurrentUrl();
        imageWithTextPage.verifyNavigationPage(expectedURL, actualURL);
    }

    @Step
    public void verifyHeadline(String label, String headline, int index){
        imageWithTextPage.verifyHeadline(label, headline, index);
    }

    @Step
    public void openUrl(String link){
        String URL = "https://" + shopDomain + link;
        imageWithTextPage.openUrl(URL);
    }

    @Step
    public void verifyText(String layout, String text, int index){
        if(!text.isEmpty()) {
            imageWithTextPage.verifyText(layout, text, index);
        }
    }

    @Step
    public void verifyButtonLink(String buttonLabel, String link, int index){
        if(!link.isEmpty()){
            imageWithTextPage.verifyButtonLink(buttonLabel,link,index);
        }
    }

    @Step
    public void verifyButtonLabel(String label, String headline, int index){

    }

    public void verifyPromotionsAnimation(String promotionsAnimation, int index) {
        if(!promotionsAnimation.isEmpty()) {
            imageWithTextPage.verifyPromotionsAnimation(promotionsAnimation, index);
        }
    }

    //Theme inside v2
    @Step
    public void verifyImage(String img, int indexBlock) {
        if (!img.isEmpty()) {
            imageWithTextPage.verifyImageExist(true, indexBlock);
        } else imageWithTextPage.verifyImageExist(false, indexBlock);
    }

    @Step
    public void verifyimageLink (String imgLink, int index) {
        if (!imgLink.isEmpty()) {
            imageWithTextPage.verifyimageLink(imgLink, shopDomain, index);
        }
    }

    @Step
    public void verifyAltText(String altText, int index) {
        imageWithTextPage.verifyAltText(altText, index);
    }

    @Step
    public void verifyShowWidthSection(Boolean isFull) {
        imageWithTextPage.verifyShowWidthSection(isFull);
    }

    @Step
    public void verifyTextAlignment(String textAlignment) {
         imageWithTextPage.verifyTextAlignment(textAlignment);
    }

    @Step
    public void verifyHeading(String heading, int indexBlock) {
        imageWithTextPage.verifyHeading(heading, indexBlock);
    }

    @Step
    public void verifyDescription(String text, int indexBlock) {
        imageWithTextPage.verifyDescription(text, indexBlock);
    }

    @Step
    public void verifyButtonType(boolean buttonType, int index) {
        imageWithTextPage.verifyButtonType(buttonType, index);
    }

    @Step
    public void verifyButton(String buttonLabel, String link, int index){
        if(!link.isEmpty()){
            imageWithTextPage.verifyButton(buttonLabel,link,index, shopDomain);
        }
    }

    @Step
    public void isExistBlockSF(boolean status) {
        imageWithTextPage.isExistBlockSF(status);
    }

}
