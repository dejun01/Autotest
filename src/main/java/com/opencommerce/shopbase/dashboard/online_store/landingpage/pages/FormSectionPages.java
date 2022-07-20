package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class FormSectionPages extends CommonLandingPages  {
    public FormSectionPages(WebDriver driver) {
        super(driver);
    }
    String xpath = "//section[contains(@data-id,'contact_form')]";
    public void verifyTitle(String title) {
        if(!title.isEmpty()) {
            verifyElementText(xpath + "//h3", title);
        }
    }

    public void verifyBody(String body) {
        if(!body.isEmpty()) {
            verifyElementText(xpath + "//p", body);
        }
    }
    public void verifyPlaceholder(String placeholder, int index) {
        if(!placeholder.isEmpty()) {
            String nameAR = getAttributeValueRaw("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//input)["+index+"]","placeholder");
            assertThat(nameAR).contains(placeholder);
        }
    }
    public void verifyRequireField(boolean isRequire,int index) {
        verifyElementPresent("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')])["+index+"]//input[@required]",isRequire);
    }

    public void verifyMessagePlaceholder(String messagePlaceholder) {
        if(!messagePlaceholder.isEmpty()) {
            String messageAR = getAttributeValueRaw("//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//textarea","placeholder");
            assertThat(messageAR).contains(messagePlaceholder);
        }
    }

    public void verifyRequireMessageField(boolean requireMessageField) {
        verifyElementPresent("//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//textarea[@required]",requireMessageField);

    }

    public void verifySubmitButtonLabel(String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()) {
            verifyElementText(xpath + "//button//span", submitButtonLabel);
        }
    }

    public void verifySubmitTo(String submitTo) {
    }

    public void verifyType(String typeBackground) {
        verifyTypeBackground(xpath,typeBackground);
    }

    public void verifyCustomColor(String customColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(customColor), 4);
    }

    public void verifyStartColor(String startColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(startColor), 4);
    }

    public void verifyEndColor(String endColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(endColor), 4);
    }

    public void verifyDirection(String directionBackground) {
        verifyDirectionBackground(xpath,directionBackground);
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-overlay')]", convertColorHexToRGB(colorOverlayBackground), 0);
    }

    public void verifyTitleColor(String titleColor, String title ) {
        if(!title.isEmpty()){
        verifyStyleLanding(xpath+"//h3", convertColorHexToRGB(titleColor), 0);
        }
    }

    public void verifyTitleFont(String titleFont, String title ) {
        if(!title.isEmpty()){
            verifyStyleLanding(xpath+"//h3",titleFont, 1);
        }
    }

    public void verifyTitleSize(String titleSize, String title ) {
        if(!title.isEmpty()){
            verifyStyleLanding(xpath+"//h3", titleSize+"px", 2);
        }
    }

    public void verifyAlignmentTitle(String alignmentTitle, String title) {
        if(!title.isEmpty()){
            verifyStyleLanding(xpath+"//h3", alignmentTitle, 3);
        }
    }

    public void verifyBodyColor(String bodyColor, String body ) {
        if(!body.isEmpty()){
            verifyStyleLanding(xpath+"//div[contains(@class,'lp-contact-form__body')]", convertColorHexToRGB(bodyColor), 0);
        }
    }

    public void verifyBodyFont(String bodyFont, String body) {
        if(!body.isEmpty()){
            verifyStyleLanding(xpath+"//div[contains(@class,'lp-contact-form__body')]",bodyFont, 1);
        }
    }

    public void verifyBodySize(String bodySize, String body) {
        if(!body.isEmpty()){
            verifyStyleLanding(xpath+"//div[contains(@class,'lp-contact-form__body')]",bodySize+"px", 2);
        }
    }

    public void verifyAlignmentBody(String alignmentBody, String body) {
        if(!body.isEmpty()){
            verifyStyleLanding(xpath+"//div[contains(@class,'lp-contact-form__body')]",alignmentBody, 3);
        }
    }

    public void verifyFormFieldBackgroundColor(String formFieldBackgroundColor) {
        verifyStyleLanding("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//input)[1]",convertColorHexToRGB(formFieldBackgroundColor),6);
    }

    public void verifyPlaceholderColor(String placeholderColor) {
    }

    public void verifyInputColor(String inputColor) {
        verifyStyleLanding("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//input)[1]",convertColorHexToRGB(inputColor),0);
    }

    public void verifyInputSize(String inputSize) {
        verifyStyleLanding("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//input)[1]",inputSize+"px",2);
    }

    public void verifyInputFont(String inputFont) {
        verifyStyleLanding("(//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-contact-form__input')]//input)[1]",inputFont,1);
    }

    public void verifyButtonPosition(String buttonPosition, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()) {
            verifyElementPresent(xpath + "//div[contains(@class,'lp-justify-" + buttonPosition + "')]//button", true);
        }
    }

    public void verifyButtonBackgroundColor(String buttonBackgroundColor, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()){
            verifyStyleLanding(xpath+"//button",convertColorHexToRGB(buttonBackgroundColor), 6);
        }
    }

    public void verifyButtonBorderColor(String buttonBorderColor, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()){
            verifyStyleLanding(xpath+"//button",convertColorHexToRGB(buttonBorderColor), 7);
        }
    }

    public void verifyButtonLabelColor(String buttonLabelColor, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()){
            verifyStyleLanding(xpath+"//button",convertColorHexToRGB(buttonLabelColor), 0);
        }
    }

    public void verifyButtonLabelFont(String buttonLabelFont, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()){
            verifyStyleLanding(xpath+"//button",buttonLabelFont, 1);
        }
    }

    public void verifyButtonLabelSize(String buttonLabelSize, String submitButtonLabel) {
        if(!submitButtonLabel.isEmpty()){
            verifyStyleLanding(xpath+"//button",buttonLabelSize+"px", 2);
        }
    }

    public void verifyBorderTopColor(String borderTopColor) {
        verifyStyleLanding(xpath,convertColorHexToRGB(borderTopColor),0);
    }

    public void verifyBorderTopSize(String borderTopSize) {
        verifyStyleLanding(xpath,borderTopSize+"px",0);
    }

    public void verifyBorderBottomColor(String borderBottomColor) {
        verifyStyleLanding(xpath,convertColorHexToRGB(borderBottomColor),4);
    }

    public void verifyBorderBottomSize(String borderBottomSize) {
        verifyStyleLanding(xpath,borderBottomSize+"px",1);
    }

    public void verifyPosition(String position) {
        verifyElementPresent("//section[contains(@data-id,'contact_form')]//div[contains(@class,'lp-container lp-justify-"+position+"')]",true);
    }

    public void verifyMinHeight(String minHeight) {
        verifyStyleLanding(xpath,minHeight,5);
    }

    public void verifyMaxWidth(String maxWidth) {
        verifyMaxWidthBackground(xpath,maxWidth);
    }

    public void verifyCustomWidth(String customWidth) {
        verifyStyleLanding(xpath+"//div[contains(@style,'max-width')]",customWidth,0);
    }
}
