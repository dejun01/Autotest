package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BannerPage extends SBasePageObject {
    public BannerPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");
    String shop = LoadObject.getProperty("shop");

    public void uploadImageBachgroundBanner(String imgBg) {
        String xpath = "(//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='Background image' or normalize-space()='Image']]//input[@type='file'])";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, imgBg);
    }

    public boolean isExistLink(String label) {
        return isElementExist("//div[@class='input-wrapper s-mt16' and child::label[normalize-space()='" + label + "']]//a[@class='s-delete is-small']", 5);
    }


    public void chooseTypeLinkPageByLabel(String typeLink, String dataLink, String label) {
        String xpathparent = "//div[@class='s-form-item__content' and descendant::label[normalize-space()='" + label + "']]";
        String xpathInputType = xpathparent + "//input";
        String xpathType = xpathparent + "//div[contains(@class,'is-opened')]//*[@class='s-dropdown-item' and normalize-space()='" + typeLink + "']";
        inputSlowly(xpathInputType, typeLink);
        scrollIntoElementView(xpathInputType);
        clickOnElementByJs(xpathType);
        String xpathInputLink = xpathparent + "//input[contains(@placeholder,'Search')]";
        String xpathLink = xpathparent + "//*[normalize-space()='" + dataLink + "']";
        scrollIntoElementView(xpathInputLink);
        inputSlowly(xpathInputLink, dataLink);
        clickOnElement(xpathLink);
    }

    public void removeLink(String label) {
        String xpath = "//div[@class='input-wrapper s-mt16' and child::label[normalize-space()='" + label + "']]//a[@class='s-delete is-small']";
        clickOnElement(xpath);
    }

    public void addLink(String link, String label) {
        String xpath = "//div[@class='input-wrapper s-mt16' and child::label[normalize-space()='" + label + "']]//input";
        waitClearAndTypeThenEnter(xpath, link);
        clickOnElement("//div[@class='input-wrapper s-mt16' and child::label[normalize-space()='" + label + "']]//strong[text()='Add']");
    }

    public void selectOption(String label, String option) {
        clickOnElement("//div[descendant::p[normalize-space()='" + label + "'] and @currentlayout]//select");
        clickOnElement("//div[descendant::p[normalize-space()='" + label + "'] and @currentlayout]//option[normalize-space()='" + option + "']");
    }

    public void enterAltTextBanner(String label, String altText) {
        String xpath = "//div[@class='s-form-item text' and preceding-sibling::div[@class='help-label' and descendant::p[text()='" + label + "']]]//input";
        waitClearAndTypeThenEnter(xpath, altText);
    }

    //Theme inside v2

    String xpathSectionBanner = "//section[contains(@type,'banner')]";

    public void verifyImageBannerExist(boolean isExisted) {
        String xpath = xpathSectionBanner+ "//img[contains(@srcset,'file')]";
        scrollIntoElementView(xpathSectionBanner);
        verifyElementPresent(xpath, isExisted);
    }

    public void verifyimageLinkBanner(String imgLink, String shop) {
        String href = getAttributeValue(xpathSectionBanner+ "//div[contains(@class,'banner-section__image') or contains(@class,'banner-container') ]//ancestor::a", "href").replace("https://" + shop, "").trim();
        assertThat(href).isEqualTo(imgLink);
    }

    public void verifyAltTextBanner(String altText) {
        String curAlt = getAttributeValue(xpathSectionBanner + "//img", "alt");
        assertThat(curAlt).isEqualTo(altText);
    }

    public void verifyOpacity(String opacity) {
        verifyElementPresent("//*[contains(@style,'opacity:" + opacity + "') or contains(@style,'opacity: " + opacity + "')]", true);
    }

    public void verifyTextPositionBanner(String textPosition) {
        if (theme.equalsIgnoreCase("RollerV3")) {
            verifyElementPresent(xpathSectionBanner + "//div[contains(@class,'text-wrap--" + textPosition + "')]", true);
        } else {
            verifyElementPresent(xpathSectionBanner + "//div[contains(@class,'banner-section--position-" + textPosition + "')]", true);
        }
    }

    public void verifyTextAlignmentBanner(String textAlignment) {
        verifyElementPresent(xpathSectionBanner + "//*[contains(@class,'text-align-" + textAlignment + "')]", true);
    }

    public void verifyShowWidthSection(Boolean isFull) {
        if (!isFull) {
            verifyElementPresent(xpathSectionBanner + "//div[contains(@class,'banner-section--no-full')]", true);
        } else
            verifyElementPresent(xpathSectionBanner + "//div[contains(@class,'container d-block')]", true);
    }

    public void verifyHeadingBanner(String heading) {
        if(!heading.isEmpty()) {
            if (theme.equalsIgnoreCase("RollerV3")) {
                verifyElementText(xpathSectionBanner + "//h2", heading);
            } else {
                verifyElementText(xpathSectionBanner + "//h3", heading.toUpperCase());
            }
        }
    }

    public void verifyDescriptionBanner(String text) {
        verifyElementText(xpathSectionBanner + "//div[contains(@class,'banner-section__subtitle')]", text);
    }

    public void verifyFirstBtnLabelBanner(String primaryButtonLabel) {
        verifyElementText(xpathSectionBanner + "//a[contains(@class,'btn-primary')]", primaryButtonLabel.toUpperCase());
    }

    public void verifyFirstBtnLinkBanner(String primaryButtonLink, String primaryButtonLabel, String shop) {
        String xPath = xpathSectionBanner + "//a[normalize-space()='" + primaryButtonLabel + "']";
        String attribute = getAttributeValue(xPath, "href").replace("https://" + shop, "").trim();
        assertThat(attribute).isEqualTo(primaryButtonLink);
    }

    public void verifySecondBtnLabelBanner(String secondaryButtonLabel) {
        if(theme.equalsIgnoreCase("RollerV3")){
            verifyElementText(xpathSectionBanner + "//a[contains(@class,'carousel__second-link')]", secondaryButtonLabel.toUpperCase());
        }else {
            verifyElementText(xpathSectionBanner + "//a[contains(@class,'btn-outline')]", secondaryButtonLabel.toUpperCase());
        }
    }

    public void verifySecondBtnLinkBanner(String secondaryButtonLink, String secondaryButtonLabel, String shop) {
        String xPath = xpathSectionBanner + "//a[normalize-space()='" + secondaryButtonLabel + "']";
        String attribute = getAttributeValue(xPath, "href").replace("https://" + shop, "").trim();
        assertThat(attribute).isEqualTo(secondaryButtonLink);
    }

    public void isExistBlockBannerSF(String nameSection, String nameBlockSF, boolean status) {
        String xpath = "//*[contains(@type,'" + nameSection.toLowerCase() + "')]//*[contains(@class, '" + nameSection.toLowerCase() + "-section__" + nameBlockSF + "')]";
        verifyElementPresent(xpath, status);
    }

    public void verifyPreHeadingBanner(String preHeading) {
        if(!preHeading.isEmpty()){
            verifyElementText(xpathSectionBanner + "//div[contains(@class,'text-wrap__preheading')]",preHeading.toUpperCase());
        }
    }

    public void verifySubHeadingBanner(String subHeading) {
        if(!subHeading.isEmpty()){
            verifyElementText(xpathSectionBanner + "//div[contains(@class,'text-wrap__subheading')]",subHeading.toUpperCase());
        }
    }

}
