package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.FormSectionPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class FormSectionSteps extends ScenarioSteps {
    FormSectionPages formSectionPages;

    @Step
    public void verifyTitle(String title) {
        formSectionPages.verifyTitle(title);
    }

    @Step
    public void verifyBody(String body) {
        formSectionPages.verifyBody(body);
    }

    @Step
    public void verifyNamePlaceholder(String namePlaceholder) {
        formSectionPages.verifyPlaceholder(namePlaceholder,1);
    }

    @Step
    public void verifyRequireNameField(boolean requireNameField) {
        formSectionPages.verifyRequireField(requireNameField,1);
    }

    @Step
    public void verifyEmailPlaceholder(String emailPlaceholder) {
        formSectionPages.verifyPlaceholder(emailPlaceholder,3);
    }

    @Step
    public void verifyRequireEmailField(boolean requireEmailField) {
        formSectionPages.verifyRequireField(requireEmailField,3);
    }

    @Step
    public void verifyPhonePlaceholder(String phonePlaceholder) {
        formSectionPages.verifyPlaceholder(phonePlaceholder,2);
    }

    @Step
    public void verifyRequirePhoneField(boolean requirePhoneField) {
        formSectionPages.verifyRequireField(requirePhoneField,2);
    }

    @Step
    public void verifyAddressPlaceholder(String addressPlaceholder) {
        formSectionPages.verifyPlaceholder(addressPlaceholder,4);
    }

    @Step
    public void verifyRequireAddressField(boolean requireAddressField) {
        formSectionPages.verifyRequireField(requireAddressField,4);
    }

    @Step
    public void verifyMessagePlaceholder(String messagePlaceholder) {
        formSectionPages.verifyMessagePlaceholder(messagePlaceholder);
    }

    @Step
    public void verifyRequireMessageField(boolean requireMessageField) {
        formSectionPages.verifyRequireMessageField(requireMessageField);
    }

    @Step
    public void verifySubmitButtonLabel(String submitButtonLabel) {
        formSectionPages.verifySubmitButtonLabel(submitButtonLabel);
    }

    @Step
    public void verifySubmitTo(String submitTo) {
        formSectionPages.verifySubmitTo(submitTo);
    }

    @Step
    public void verifyType(String type) {
        formSectionPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        formSectionPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        formSectionPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        formSectionPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        formSectionPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        formSectionPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        formSectionPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyTitleColor(String titleColor, String title) {
        formSectionPages.verifyTitleColor(titleColor, title);
    }

    @Step
    public void verifyTitleFont(String titleFont, String title) {
        formSectionPages.verifyTitleFont(titleFont, title);
    }

    @Step
    public void verifyTitleSize(String titleSize, String title) {
        formSectionPages.verifyTitleSize(titleSize, title);
    }

    @Step
    public void verifyAlignmentTitle(String alignmentTitle, String title) {
        formSectionPages.verifyAlignmentTitle(alignmentTitle,title);
    }

    @Step
    public void verifyBodyColor(String bodyColor, String body) {
        formSectionPages.verifyBodyColor(bodyColor, body);
    }

    @Step
    public void verifyBodyFont(String bodyFont, String body) {
        formSectionPages.verifyBodyFont(bodyFont, body);
    }

    @Step
    public void verifyBodySize(String bodySize, String body) {
        formSectionPages.verifyBodySize(bodySize,body);
    }

    @Step
    public void verifyAlignmentBody(String alignmentBody, String body) {
        formSectionPages.verifyAlignmentBody(alignmentBody,body);
    }

    @Step
    public void verifyFormFieldBackgroundColor(String formFieldBackgroundColor) {
        formSectionPages.verifyFormFieldBackgroundColor(formFieldBackgroundColor);
    }

    @Step
    public void verifyPlaceholderColor(String placeholderColor) {
        formSectionPages.verifyPlaceholderColor(placeholderColor);
    }

    @Step
    public void verifyInputColor(String inputColor) {
        formSectionPages.verifyInputColor(inputColor);
    }

    @Step
    public void verifyInputSize(String inputSize) {
        formSectionPages.verifyInputSize(inputSize);
    }

    @Step
    public void verifyInputFont(String inputFont) {
        formSectionPages.verifyInputFont(inputFont);
    }

    @Step
    public void verifyButtonPosition(String buttonPosition, String submitButtonLabel) {
        formSectionPages.verifyButtonPosition(buttonPosition, submitButtonLabel);
    }

    @Step
    public void verifyButtonBackgroundColor(String buttonBackgroundColor, String submitButtonLabel) {
        formSectionPages.verifyButtonBackgroundColor(buttonBackgroundColor,submitButtonLabel);
    }

    @Step
    public void verifyButtonBorderColor(String buttonBorderColor, String submitButtonLabel) {
        formSectionPages.verifyButtonBorderColor(buttonBorderColor,submitButtonLabel);
    }

    @Step
    public void verifyButtonLabelColor(String buttonLabelColor, String submitButtonLabel) {
        formSectionPages.verifyButtonLabelColor(buttonLabelColor,submitButtonLabel);
    }

    @Step
    public void verifyButtonLabelFont(String buttonLabelFont, String submitButtonLabel) {
        formSectionPages.verifyButtonLabelFont(buttonLabelFont, submitButtonLabel);
    }

    @Step
    public void verifyButtonLabelSize(String buttonLabelSize, String submitButtonLabel) {
        formSectionPages.verifyButtonLabelSize(buttonLabelSize, submitButtonLabel);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        formSectionPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize) {
        formSectionPages.verifyBorderTopSize(borderTopSize);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        formSectionPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        formSectionPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyPosition(String position) {
        formSectionPages.verifyPosition(position);
    }

    @Step
    public void verifyMinHeight(String minHeight) {
        formSectionPages.verifyMinHeight(minHeight);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        formSectionPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        formSectionPages.verifyCustomWidth(customWidth);
    }
}
