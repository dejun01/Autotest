package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.FooterPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class FooterSteps {
    FooterPage footerPage;
    String theme = LoadObject.getProperty("theme");

    @Step
    public void enterCopyrightText(String copyrightText) {
        footerPage.enterInputFieldWithLabel("Copyright text", copyrightText);
    }

    @Step
    public void isCheckboxShowPaymentMethod(boolean showPaymentMethodIcons) {
        footerPage.isCheckedShowPaymentMethod(showPaymentMethodIcons);
    }

    @Step
    public void isCheckboxShowSocialMedia(boolean showSocialMediaFooterIcons) {
        footerPage.isCheckedShowSocialIcon(showSocialMediaFooterIcons);
    }

    @Step
    public void addContent(String type, String content) {
        String[] data = content.split(">");
        switch (type) {
            case "Title":
                footerPage.enterHeadingTitleFooter(data[0]);
                footerPage.enterTextTitleFooter(data[1]);
                break;
            case "Menu":
                footerPage.chooseMenuFooter(content);
                break;
            case "Store information":
                if (theme.equals("inside")) {
                    footerPage.enterStoreInforInside("Heading", data[0]);
                    footerPage.enterStoreInforInside("Address", data[1]);
                    footerPage.enterStoreInforInside("Email", data[2]);
                    footerPage.enterStoreInforInside("Phone number", data[3]);
                    boolean showSocialIcon = Boolean.parseBoolean(data[4]);
                    footerPage.isCheckedShowSocialIcon(showSocialIcon);
                } else {
                    footerPage.enterContentOfStoreInformation("Address", data[0]);
                    footerPage.enterContentOfStoreInformation("Email", data[1]);
                    footerPage.enterContentOfStoreInformation("Phone", data[2]);
                }
                break;
            default:
                footerPage.choosePage(content);
        }
    }

    public void addContentpbase(String type, String content) {
        switch (type) {
            case "Title":
                String[] data = content.split(">");
                footerPage.enterHeadingTitleFooter(data[0]);
                footerPage.enterTextTitleFooter(data[1]);
                break;
            case "Menu":
                footerPage.chooseMenuFooter(content);
                break;
            case "Store information":
                footerPage.enterContentOfStoreInformation("Address", content);
                break;
            default:
                footerPage.choosePage(content);
        }
    }


    public void clickAddContent() {
        footerPage.clickAddContent();
    }

    @Step
    public void chooseTypeContent(String type) {
        footerPage.chooseTypeContent(type);
    }

    @Step
    public void openLastBlockContent() {
        footerPage.openLastBlockContent();
    }

    @Step
    public void closeBlockContent() {
        footerPage.closeBlockContent();
    }

    @Step
    public void chooseLayout(String layout) {
        if (!layout.isEmpty()) {
            footerPage.clickLinkTextWithLabel(layout);
        }
    }

    @Step
    public void isFullWidthSection(boolean isFull) {
        footerPage.checkCheckboxWithLabel("Full width section", isFull);
    }

    @Step
    public void uploadLogo(String logo) {
        if (!logo.isEmpty()) {
            footerPage.uploadLogo(logo);
        } else {
            footerPage.removeLogo();
        }
    }

    @Step
    public void chooseContentAlignment(String contentAlignment) {
        if (!contentAlignment.isEmpty()) {
            footerPage.clickLinkTextWithLabel(contentAlignment);
        }
    }

    @Step
    public void chooseMenu(String footerMenu) {
        if(!footerMenu.isEmpty()) {
            footerPage.selectDdlWithLabel("Footer menu", footerMenu);
        }
    }

    @Step
    public void openBlockWithLabel(String label) {
        footerPage.openBlockWithLabel(label);
    }

    @Step
    public void opentBlockContent(String type) {
        footerPage.opentBlockContent(type);
    }

    @Step
    public void openThemeSettings() {
        footerPage.openThemeSettings();
    }

    @Step
    public void enterLink(String link) {
        footerPage.enterLink(link);
    }

    @Step
    public void addHeading(String type, String heading) {
        if (type.equalsIgnoreCase("Store information"))
            footerPage.addHeading("Heading", heading);
    }

    public void isCheckHideContentHeading(String type, boolean hideContentHeading) {
        if (type.equalsIgnoreCase("Store information"))
            footerPage.isCheckHideContentHeading(hideContentHeading);
    }
}
