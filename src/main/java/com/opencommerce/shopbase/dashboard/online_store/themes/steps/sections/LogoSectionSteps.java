package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.LogoSectionPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

public class LogoSectionSteps extends ScenarioSteps {
    LogoSectionPage logoSectionPage;
    String shopDomain = LoadObject.getProperty("shop");
    @Step
    public void enterHeading(String heading) {
        logoSectionPage.enterInputFieldWithLabel("Heading", heading);
    }

    @Step
    public void chooseTextAlignment(String textAlignment) {
        logoSectionPage.clickLinkTextWithLabel(textAlignment);
    }

    @Step
    public void inputAltText(String label,String altText) {
        if (!altText.isEmpty()) {
            logoSectionPage.inputAltText(label,altText);
        }
    }

    @Step
    public void inputImageLink(String label, String imageLink) {
        if (logoSectionPage.isExistLink(label)) {
            logoSectionPage.removeLink(label);
        }

        if (!imageLink.isEmpty()) {
            String[] data = imageLink.split(">");

            if (data.length == 1) {
                logoSectionPage.addLink(data[0], label);
            }
            if (data.length == 2) {
                logoSectionPage.chooseTypeLinkPageByLabel(data[0], data[1], label);
            }
        }
    }
    @Step
    public void verifyHeading(String heading) {
        if(!heading.isEmpty())
            logoSectionPage.verifyHeading(heading);
    }
    @Step
    public void verifyTextAlignment(String textAlignment) {
        if(!textAlignment.isEmpty())
            logoSectionPage.verifyTextAlignment(textAlignment);
    }
    @Step
    public void verifyAlytText(String altText, int index) {
        if(!altText.isEmpty())
            logoSectionPage.verifyAlytText(altText,index);
    }
    @Step
    public void verifyLinkImage(String link, int index) {
        logoSectionPage.verifyLinkImage(link,index);

    }
    //theme V2
    @Step
    public void isExistBlockSF(boolean status) {
        logoSectionPage.isExistBlockSF(status);
    }
    @Step
    public void verifyHeading(String heading, int indexBlock) {
        logoSectionPage.verifyHeading(heading, indexBlock);
    }
    @Step
    public void verifyImage(String img, int indexBlock) {
        if (!img.isEmpty()) {
            logoSectionPage.verifyImageExist(true, indexBlock);
        } else logoSectionPage.verifyImageExist(false, indexBlock);
    }
    @Step
    public void verifyAltText(String altText, int index) {
        logoSectionPage.verifyAltText(altText, index);
    }
    @Step
    public void verifyimageLink (String imgLink, int index) {
        if (!imgLink.isEmpty()) {
            logoSectionPage.verifyimageLink(imgLink, shopDomain, index);
        }
    }

}










