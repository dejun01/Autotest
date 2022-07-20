package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.FooterPage;
import net.thucydides.core.annotations.Step;

public class FooterSteps {
    FooterPage footerPage;

    @Step
    public void verifyCopyrightText(String copyrightText) {
        if (!copyrightText.isEmpty()) {
            footerPage.verifyCopyrightText(copyrightText);
        }
    }

    @Step
    public void verifyShowPaymentMethod(boolean showPaymentMethodIcons) {
        footerPage.verifyShowPaymentMethod(showPaymentMethodIcons);
    }

    @Step
    public void verifyShowSocialMedia(boolean showSocialMediaFooterIcons) {
        footerPage.verifyShowSocialMedia(showSocialMediaFooterIcons);

    }

    @Step
    public void verifyContent(String type, String content, int index) {
        String[] data = content.split(">");
        footerPage.verifyHeadingOfTitle(data[0], index);
        switch (type) {
            case "Title":
                footerPage.verifyTextOfTitle(data[1], index);
                break;
            case "Store information":
                footerPage.verifyAddress(data[1]);
                footerPage.verifyEmail(data[2]);
                footerPage.verifyPhoneNumber(data[3]);
                break;
        }
    }

    @Step
    public void verifyFullWidth(boolean fullWidth) {
        footerPage.verifyFullWidth(fullWidth);
    }

    @Step
    public void verifyText(String text) {
        footerPage.verifyText(text);
    }

    @Step
    public void verifyContentAlignment(String contentAlignment) {
        if(!contentAlignment.isEmpty()) {
            footerPage.verifyContentAlignment(contentAlignment);
        }
    }

    @Step
    public void verifyFooterMenu(String footerMenu) {
        if(!footerMenu.isEmpty()) {
            footerPage.verifyFooterMenu(footerMenu);
        }
    }

    @Step
    public void verifyLogo(String logo) {
        footerPage.verifyLogo(logo);
    }
    public void verifyContentPbase(String type, String content, int index) {
        String[] data;
        switch (type) {
            case "Title":
                data = content.split(">");
                footerPage.verifyHeadingOfTitle(data[0], index);
                break;
            case "Store information":
                footerPage.verifyAddress(content);
                break;
            case "Menu":
            case "Contact info":
                data = content.split(">");
                footerPage.verifyHeadingOfTitle(data[0], index);
                if(data.length > 1){
                    String[] listItem = data[1].split(",");
                    for (String item : listItem){
                        footerPage.verifyItemContent(index, item);
                    }
                }
                break;
            default:
                footerPage.verifyHeadingOfTitle(content, index);
        }

    }

    public void verifySizeLogo(String size) {
        footerPage.verifySizeLogo(size);
    }

    public void verifyHeadingPbase(String type, String heading, boolean hideContentHeading ) {
        if(type.equalsIgnoreCase("Store information"))
            footerPage.verifyHeadingPbase(type,heading, hideContentHeading);

    }

    public void verifyLogoPbase(String desktopLogo) {
        footerPage.verifyLogoPbase(desktopLogo);
    }

    public void verifyLogoSizePbase(String size) {
        footerPage.verifySizeLogoPbase(size);
    }

    public void veifyHideContentHeading( boolean hideContentHeading) {
        footerPage.veifyHideContentHeading(hideContentHeading);
    }

    public void verifyMenuFooter(String footerMenu) {
        footerPage.verifyMenuFooter(footerMenu);
    }

    public void verifyShowPaymentMethodIcons(boolean showPaymentMethodIcons) {
        footerPage.verifyShowPaymentMethodIcons(showPaymentMethodIcons);
    }

    public void verifyEnableSocialProfile(boolean enableSocialProfile) {
        footerPage.verifyEnableSocialProfile(enableSocialProfile);
    }

    public void verifyCustomText(String customText) {
        footerPage.verifyCustomText(customText);
    }

    public void verifyMainMenu(String mainMenu) {
        footerPage.verifyMainMenu(mainMenu);
    }

    public void verifyHeading(String heading) {
        if (!heading.isEmpty()){
            footerPage.verifyHeading(heading);
        }
    }

    public void verifyTextContent(String text) {
        if (!text.isEmpty()){
            footerPage.verifyTextContent(text);
        }
    }

    public void verifyContentPage(String contentPage) {
        if (!contentPage.isEmpty()){
            footerPage.verifyContentPage(contentPage);
        }
    }
}
