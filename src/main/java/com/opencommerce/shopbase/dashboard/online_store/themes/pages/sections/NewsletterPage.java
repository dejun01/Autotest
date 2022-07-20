package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class NewsletterPage extends SBasePageObject {
    public NewsletterPage(WebDriver driver) {
        super(driver);
    }
    String xpathNewLetter = "//section[contains(@class,'section subscribe') or contains(@class,'section newsletter')]";

    public void verifyHeadingNewsletter(String heading){
        waitForElement();
        verifyElementText(xpathNewLetter + "//h2", heading.toUpperCase());
    }
    public void enterHeadingNewsletter(String heading) {
        waitClearAndType("//div[@currentlayout and descendant::p[normalize-space()='Heading' or normalize-space()='Section Headline']]//input", heading);
    }

    public void enterSubheadingNewsletter(String subheading) {
      //  waitClearAndType("//div[contains(@class,'redactor-styles redactor-in redactor')]", subheading);
        waitABit(10000);
        String xpathIframe = "//iframe[contains(@id,'tiny-vue')]";
        waitForElementToPresent(xpathIframe);
        switchToIFrame(xpathIframe);
        $("//body[@id='tinymce']").clear();
        $("//body[@id='tinymce']").sendKeys(subheading);
        switchToIFrameDefault();
    }

    public void uploadImageBachgroundBanner(String imgBg) {
        String xpath = "(//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='Background image']]//input[@type='file'])[1]";
        changeStyle(xpath);
        System.out.printf(xpath);
        chooseAttachmentFile(xpath, imgBg);

    }

    public void removeImage() {
        String xpath = "(//div[@currentlayout and descendant::p[normalize-space()='Background image']])//img[@class='iZocYm']";
        if(isElementExist(xpath)){
            clickOnElement(xpath);
            verifyElementPresent("(//div[@currentlayout and descendant::p[normalize-space()='Background image']])//button[normalize-space()='Upload image']", true);
        }
    }
}
