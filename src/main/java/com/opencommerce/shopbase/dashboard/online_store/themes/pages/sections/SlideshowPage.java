package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SlideshowPage extends SBasePageObject {

    public  SlideshowPage(WebDriver driver){
        super(driver);
    }
    String xpathBlock = "//section[contains(@type,'slideshow')]";
    public void deleteImageByLabel(String label) {
        String xpath = "//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm']";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }

    public void uploadImageByLabel(String xpathparent, String label, String filename) {
        String xpath = "(" + xpathparent + "//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='" + label + "']]//input[@type='file'])[1]";
        changeStyle(xpath);
        System.out.printf(xpath);
        chooseAttachmentFile(xpath, filename);
    }

    String sildeShow = "(//div[contains(@class,'s-collapse-item draggable-item')])[%d]";

    public boolean isExistSlideshow(int slideshowIndex) {
        return isElementExist(String.format(sildeShow, slideshowIndex));
    }

    public void clickAddSlideshow() {
        clickLinkTextWithLabel("Add slideshow");
    }

    public void openBlockSlideshow(int slideshowIndex) {
        clickLinkTextWithLabel(String.format(sildeShow, slideshowIndex), "Slideshow", 1);
    }

    String xpathSlideActive = "//div[@class='s-collapse-item draggable-item is-active']";

    public void enterInputSettingSileshowWithLabel(String label, String value) {
        String xpath = xpathSlideActive + "//div[child::div[@class='help-label']//*[normalize-space()=\"" + label + "\"]]//input";
        clearAndInputTextByJS(xpath, value);
    }

    public void enterTextAreaSettingSileshowWithLabel(String label, String value) {
        enterTextAreaWithLabel(xpathSlideActive, label, value, 1);
    }

    public void selectDdlSettingSileshowWithLabel(String label, String value) {
        selectDdlWithLabel(xpathSlideActive, label, value, 1);
    }

    public void highlightButtonlabel(String label, boolean isCheck) {
        checkCheckboxWithLabel(xpathSlideActive, label, 1, isCheck);
    }

    public void uploadBackgroundImage(String backgroundImage) {
        uploadImageByLabel(xpathSlideActive, "Background image", backgroundImage);
    }

    public int countQuantityOfSlideshow() {
        return countElementByXpath("//div[contains(@class,'s-collapse-item draggable-item')]");
    }

    public void removeContent() {
        clickBtn(xpathSlideActive, "Remove content", 1);
    }


    public void enterText(String text) {
        waitClearAndType("//textarea[contains(@id,'tiny-vue')]", text);
    }

    public void verifyAltText(String altText){
        String altTextAR = getAttributeValue("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]","alt");
        assertThat(altText).contains(altTextAR);
    }

    public void verifyTextAlignment(String textAlignment) {
        verifyElementPresent("//section[contains(@class,'slideshow')]//div[contains(@class,'text-align-"+textAlignment.toLowerCase()+"')]",true);
    }

    public void verifyHeadling(String heading) {
        verifyElementContainsText("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//h1",heading.toUpperCase());
    }

    public void verifySubheading(String subheading) {
        verifyElementContainsText("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//p",subheading.toUpperCase());
    }

    public void verifyButtonLink(String buttonLink) {
        if(!buttonLink.isEmpty()){
            String linkAR = getAttributeValue("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//a","href");
            assertThat(linkAR).contains(buttonLink);
        }
    }

    public void verifyButtonLabel(String buttonLabel) {
        if(!buttonLabel.isEmpty()){
            verifyElementContainsText("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//a",buttonLabel.toUpperCase());
        }
    }

    public void verifyTextColor(String textColor) {
        String textColorAR = getAttributeValue("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//h1","style").split(";")[0].replace("color:","").trim();
        assertThat(textColor).contains(textColorAR);
    }

    public void verifyButtonLabelColor(String buttonLabelColor) {
        String buttonLabelColorAR = getAttributeValue("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//a","style").split(";")[0].replace("color:","").trim();
        assertThat(buttonLabelColorAR).contains(buttonLabelColor);
    }

    public void verifyButtonColor(String buttonColor) {
        String buttonColorAR = getAttributeValue("//section[contains(@class,'slideshow')]//div[contains(@class,'container')]//a","style").split(";")[1].replace("background-color:","").trim();
        assertThat(buttonColorAR).contains(buttonColor);
    }

    public void isExistBlockSlideSF(boolean isCheck) {
        verifyElementPresent("//*[contains(@type,'slideshow')]//div[@class='container-fluid']",false);
    }

    public void verifyTextAnimation(String textAnimation) {
        if(!textAnimation.isEmpty()) {
            if (!textAnimation.equalsIgnoreCase("None")) {
                verifyElementPresent("//section[contains(@type,'slideshow')]//div[contains(@class,'slide-active')]//div[contains(@class,'text-wrap--" + textAnimation + "')]", true);
            }
        }
    }

    public void verifyGalleryTransition(String galleryTransition) {
        if(!galleryTransition.isEmpty()) {
            if (galleryTransition.equalsIgnoreCase("fade")) {
                verifyElementPresent("//section[contains(@type,'slideshow')]//div[contains(@class,'fade')]", true);
            } else {
                verifyElementPresent("//section[contains(@type,'slideshow')]//div[contains(@class,'fade')]", false);
            }
        }

    }

    public void verifyOpacity(String opacity) {
        if(!opacity.isEmpty()) {
            verifyElementPresent("//section[contains(@type,'slideshow')]//div[contains(@class,'slide-active')]//div[contains(@style,'opacity:"+opacity+"')]",true);
        }
    }

    public void verifyEnableParallaxScrolling(Boolean enableParallaxScrolling) {
        verifyElementPresent("//section[contains(@type,'slideshow')]//div[contains(@class,'slide-active')]//img[contains(@class,'parallax')]",enableParallaxScrolling);
    }

    public void verifyImageExist(boolean isExisted, int indexBlock) {
        String xpath = "(" + xpathBlock+ "//img[contains(@srcset,'file')])[" + indexBlock + "]";
        scrollIntoElementView(xpathBlock);
        verifyElementPresent(xpath,isExisted);
    }
    public void verifyAltText(String altText,int indexBlock){
        String altTextAR = getAttributeValue("(" + xpathBlock+ "//img)[" + indexBlock + "]","alt");
        assertThat(altText).contains(altTextAR);
    }

    public void verifyPreHeading(String preHeading, int indexBlock) {
        if(!preHeading.isEmpty()){
            verifyElementText("("+xpathBlock+"//div[contains(@class,'text-wrap__preheading')])["+indexBlock+"]",preHeading.toUpperCase());
        }
    }

    public void verifyHeading(String heading, int indexBlock) {
        if(!heading.isEmpty()){
            verifyElementText("("+xpathBlock+"//h3)["+indexBlock+"]",heading.toUpperCase());
        }
    }

    public void verifySubHeading(String subHeading, int indexBlock) {
        if(!subHeading.isEmpty()){
            verifyElementText("("+xpathBlock+"//div[contains(@class,'text-wrap__subheading')])["+indexBlock+"]",subHeading.toUpperCase());
        }
    }

    public void verifyTextPosition(String textPosition, int indexBlock) {
        verifyElementPresent("("+xpathBlock+"//div[contains(@class,'text-wrap--"+textPosition+"')])["+indexBlock+"]",true);
    }

    public void verifyTextAlignment(String textAlignment, int indexBlock) {
        verifyElementPresent("("+xpathBlock+"//div[contains(@class,'text-align-"+textAlignment+"')])["+indexBlock+"]",true);
    }

    public void verifyFirstButtonLabel(String firstBtnLbl,boolean highlightBtn1, int indexBlock) {
        if(!firstBtnLbl.isEmpty())
        verifyElementText("("+xpathBlock+"//a[contains(@class,'carousel__first-link')])["+indexBlock+"]",firstBtnLbl);
        verifyElementPresent("("+xpathBlock+"//a[contains(@class,'carousel__first-link btn-primary banner-button')])["+indexBlock+"]",highlightBtn1);
    }

    public void verifySecondaryButtonLabel(String secondBtnLbl,boolean highlightBtn2, int indexBlock) {
        if(!secondBtnLbl.isEmpty())
        verifyElementText("("+xpathBlock+"//a[contains(@class,'carousel__second-link')])["+indexBlock+"]",secondBtnLbl);
        verifyElementPresent("("+xpathBlock+"//a[contains(@class,'carousel__second-link btn-primary banner-button')])["+indexBlock+"]",highlightBtn2);
    }
}
