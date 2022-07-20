package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.ProductRichTextPages;
import net.thucydides.core.steps.ScenarioSteps;

public class ProductRichTextSteps extends ScenarioSteps {
    ProductRichTextPages productRichTextPages;

    public void verifyTitleRichText(String richTextTitle) {
        productRichTextPages.verifyTitleRichText(richTextTitle);
    }

    public void verifyBodyRichText(String richTextBody) {
        productRichTextPages.verifyBodyRichText(richTextBody);
    }

    public void verifyTypeButtonRichText(String typeButton) {
        productRichTextPages.verifyTypeButtonRichText(typeButton);
    }

    public void verifyButtonLabelRichText(String buttonLabelRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelRichText(buttonLabelRichText,typeButton);
    }

    public void verifyButtonLinkRichText(String buttonLinkRichText,String typeButton) {
        productRichTextPages.verifyButtonLinkRichText(buttonLinkRichText,typeButton);
    }

    public void verifyLinkLabelRichText(String linkLabelRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelRichText(linkLabelRichText,typeButton);
    }

    public void verifyLinkRichText(String linkRichText,String typeButton) {
        productRichTextPages.verifyButtonLinkRichText(linkRichText,typeButton);
    }

    public void verifyTitleColorRichText(String titleColorRichText, String richTextTitle) {
        productRichTextPages.verifyTitleColorRichText(titleColorRichText,richTextTitle);
    }

    public void verifyTitleFontRichText(String titleFontRichText, String richTextTitle) {
        productRichTextPages.verifyTitleFontRichText(titleFontRichText,richTextTitle);
    }

    public void verifyTitleSizeRichText(String titleSizeRichText, String richTextTitle) {
        productRichTextPages.verifyTitleSizeRichText(titleSizeRichText,richTextTitle);
    }

    public void verifyAlignmentRichTextTitle(String alignmentRichTextTitle, String richTextTitle) {
        productRichTextPages.verifyAlignmentRichTextTitle(alignmentRichTextTitle,richTextTitle);
    }

    public void verifyBodyColorRichText(String bodyColorRichText, String richTextBody) {
        productRichTextPages.verifyBodyColorRichText(bodyColorRichText,richTextBody);
    }

    public void verifyBodyFontRichText(String bodyFontRichText, String richTextBody) {
        productRichTextPages.verifyBodyFontRichText(bodyFontRichText,richTextBody);
    }

    public void verifyBodySizeRichText(String bodySizeRichText, String richTextBody) {
        productRichTextPages.verifyBodySizeRichText(bodySizeRichText,richTextBody);
    }

    public void verifyAlignmentRichTextBody(String alignmentRichTextBody, String richTextBody) {
        productRichTextPages.verifyAlignmentRichTextBody(alignmentRichTextBody,richTextBody);
    }

    public void verifyButtonColorRichText(String buttonColorRichText, String buttonLabelRichText) {
        productRichTextPages.verifyButtonColorRichText(buttonColorRichText,buttonLabelRichText);
    }

    public void verifyButtonBorderColorRichText(String buttonBorderColorRichText, String buttonLabelRichText) {
        productRichTextPages.verifyButtonBorderColorRichText(buttonBorderColorRichText,buttonLabelRichText);
    }

    public void verifyButtonLabelColorRichText(String buttonLabelColorRichText, String buttonLabelRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelColorRichText(buttonLabelColorRichText,buttonLabelRichText,typeButton);
    }

    public void verifyButtonLabelFontRichText(String buttonLabelFontRichText, String buttonLabelRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelFontRichText(buttonLabelFontRichText,buttonLabelRichText,typeButton);
    }

    public void verifyButtonLabelSizeRichText(String buttonLabelSizeRichText, String buttonLabelRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelSizeRichText(buttonLabelRichText,buttonLabelSizeRichText,typeButton);
    }

    public void verifyAlignmentRichTextButton(String alignmentRichTextButton, String buttonLabelRichText,String typeButton) {
        productRichTextPages.verifyAlignmentRichTextButton(alignmentRichTextButton,buttonLabelRichText,typeButton);
    }

    public void verifyLinkColorRichText(String linkColorRichText, String linkRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelColorRichText(linkColorRichText,linkRichText,typeButton);
    }

    public void verifyLinkFontRichText(String linkFontRichText, String linkRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelFontRichText(linkFontRichText,linkRichText,typeButton);
    }

    public void verifyLinkSizeRichText(String linkSizeRichText, String linkRichText,String typeButton) {
        productRichTextPages.verifyButtonLabelSizeRichText(linkSizeRichText,linkRichText,typeButton);
    }

    public void verifyAlignmentRichTextLink(String alignmentRichTextLink, String linkRichText,String typeButton) {
        productRichTextPages.verifyAlignmentRichTextButton(alignmentRichTextLink,linkRichText,typeButton);
    }
}
