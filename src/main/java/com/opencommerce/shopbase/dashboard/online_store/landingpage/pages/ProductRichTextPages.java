package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductRichTextPages extends CommonLandingPages {
    public ProductRichTextPages(WebDriver driver) {
        super(driver);
    }

    public void verifyTitleRichText(String richTextTitle) {
        if(!richTextTitle.isEmpty()) {
            verifyElementText("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//h3", richTextTitle);
        }
    }

    public void verifyBodyRichText(String richTextBody) {
        if(!richTextBody.isEmpty()) {
            verifyElementText("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//div[contains(@class,'lp-mb24')]", richTextBody);
        }
    }

    public void verifyTypeButtonRichText(String typeButton) {
        if (typeButton.equalsIgnoreCase("Button")) {
            verifyElementPresent("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]", true);

        } else {
            verifyElementPresent("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]", true);
        }
    }

    public void verifyButtonLabelRichText(String buttonLabelRichText,String typeButton) {
        if(!buttonLabelRichText.isEmpty()) {
            switch (typeButton) {
                case "Button":
                    verifyElementText("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]", buttonLabelRichText);
                    break;
                case "Link":
                    verifyElementText("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]", buttonLabelRichText);
                    break;
            }
        }
    }

    public void verifyButtonLinkRichText(String buttonLinkRichText,String typeButton) {
        if(!buttonLinkRichText.isEmpty()){
            switch (typeButton) {
                case "Button":
                    String linkButton = getAttributeValueRaw("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]","href");
                    assertThat(linkButton).contains(buttonLinkRichText);
                    break;
                case "Link":
                    String link = getAttributeValueRaw("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]","href");
                    assertThat(link).contains(buttonLinkRichText);
                    break;
            }
        }
    }

    public void verifyTitleColorRichText(String titleColorRichText, String richTextTitle) {
        if(!richTextTitle.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//h3",titleColorRichText,0);
        }
    }

    public void verifyTitleFontRichText(String titleFontRichText, String richTextTitle) {
        if(!richTextTitle.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//h3",titleFontRichText,1);
        }
    }

    public void verifyTitleSizeRichText(String titleSizeRichText, String richTextTitle) {
        if(!richTextTitle.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//h3",titleSizeRichText+"px",2);
        }
    }

    public void verifyAlignmentRichTextTitle(String alignmentRichTextTitle, String richTextTitle) {
        if(!richTextTitle.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//h3",alignmentRichTextTitle,3);
        }
    }

    public void verifyBodyColorRichText(String bodyColorRichText, String richTextBody) {
        if(!richTextBody.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//div[contains(@class,'lp-mb24')]",bodyColorRichText,0);
        }
    }

    public void verifyBodyFontRichText(String bodyFontRichText, String richTextBody) {
        if(!richTextBody.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//div[contains(@class,'lp-mb24')]",bodyFontRichText,1);
        }
    }

    public void verifyBodySizeRichText(String bodySizeRichText, String richTextBody) {
        if(!richTextBody.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//div[contains(@class,'lp-mb24')]",bodySizeRichText+"px",2);
        }
    }

    public void verifyAlignmentRichTextBody(String alignmentRichTextBody, String richTextBody) {
        if(!richTextBody.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//div[contains(@class,'lp-mb24')]",alignmentRichTextBody,3);
        }
    }

    public void verifyButtonColorRichText(String buttonColorRichText, String buttonLabelRichText) {
        if(!buttonLabelRichText.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",buttonColorRichText,5);
        }
    }

    public void verifyButtonBorderColorRichText(String buttonBorderColorRichText, String buttonLabelRichText) {
        if(!buttonLabelRichText.isEmpty()){
            verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",buttonBorderColorRichText,7);
        }
    }

    public void verifyButtonLabelColorRichText(String buttonLabelColorRichText, String buttonLabelRichText,String typeButton) {
        if(!buttonLabelRichText.isEmpty()){
            switch (typeButton) {
                case "Button":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",buttonLabelColorRichText,0);
                    break;
                case "Link":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]",buttonLabelColorRichText,0);
                    break;
            }
        }
    }

    public void verifyButtonLabelFontRichText(String buttonLabelFontRichText, String buttonLabelRichText,String typeButton) {
        if(!buttonLabelRichText.isEmpty()){
            switch (typeButton) {
                case "Button":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",buttonLabelFontRichText,1);
                    break;
                case "Link":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]",buttonLabelFontRichText,1);
                    break;
            }
        }
    }

    public void verifyButtonLabelSizeRichText(String buttonLabelRichText, String buttonLabelSizeRichText,String typeButton) {
        if(!buttonLabelRichText.isEmpty()){
            switch (typeButton) {
                case "Button":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",buttonLabelSizeRichText+"px",0);
                    break;
                case "Link":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]",buttonLabelSizeRichText+"px",0);
                    break;
            }
        }
    }

    public void verifyAlignmentRichTextButton(String alignmentRichTextButton, String buttonLabelRichText,String typeButton) {
        if(!buttonLabelRichText.isEmpty()){
            switch (typeButton) {
                case "Button":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-btn')]",alignmentRichTextButton,3);
                    break;
                case "Link":
                    verifyStyleLanding("//section[contains(@data-id,'product_richtext')]//div[contains(@class,'lp-rich-text')]//a[contains(@class,'lp-link')]",alignmentRichTextButton,3);
                    break;
            }
        }
    }
}
