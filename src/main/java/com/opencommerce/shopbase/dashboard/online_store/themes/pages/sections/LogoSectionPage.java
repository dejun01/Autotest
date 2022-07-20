package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;
import common.SBasePageObject;
import common.utilities.LoadObject;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
public class LogoSectionPage extends SBasePageObject {
    public LogoSectionPage(WebDriver driver) {
        super(driver);
    }
    String shop = LoadObject.getProperty("shop");
    String xpathLogo = "//section[contains(@class,'section subscribe') or contains(@class,'section logo')]";
    public boolean isExistLink(String label) {
        return isElementExist("//div[contains(@class,'is-active')]//div[child::label[normalize-space()='\"+label+\"']]//a[@class='s-delete is-small']", 5);
    }

    public void removeLink(String label) {
        String xpath = "//div[contains(@class,'is-active')]//div[child::label[normalize-space()='" + label + "']]//a[@class='s-delete is-small']";
        clickOnElement(xpath);
    }

    public void chooseTypeLinkPageByLabel(String typeLink, String dataLink, String label) {
        clickOnElementByJs("//div[contains(@class,'is-active')]//div[child::label[normalize-space()='" + label + "']]//input");
        clickOnElementByJs("//div[contains(@class,'is-active')]//div[child::label[normalize-space()='" + label + "']]//span[normalize-space()='" + typeLink + "']");
        waitForEverythingComplete(5);
        if (!dataLink.isEmpty()) {
            clickOnElementByJs("//div[contains(@class,'is-active')]//span[normalize-space()=\"" + dataLink + "\"]");
        }

    }

    public void addLink(String link, String label) {
        String xpath = "//div[contains(@class,'is-active')]//div[child::label[normalize-space()='"+label+"']]//input";
        waitClearAndTypeThenEnter(xpath, link);
        clickOnElement("//div[contains(@class,'is-active')]//div[child::label[normalize-space()='"+label+"']]//strong[text()='Add']");

    }

    public void inputAltText(String label, String altText) {
        waitClearAndType("//div[contains(@class,'is-active')]//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//input", altText);
    }

    public void verifyHeading(String heading) {
        verifyElementText("//section[contains(@class,'logo-list')]//h4",heading.toUpperCase());
    }

    public void verifyTextAlignment(String textAlignment) {
        verifyElementPresent("//section[contains(@class,'logo-list')]//h4[contains(@class,'text-align-"+textAlignment.toLowerCase()+"')]",true);
    }

    public void verifyAlytText(String altText, int index) {
        String attAR = getAttributeValue("(//section[contains(@class,'logo-list')]//img)["+index+"]","alt");
        assertThat(attAR).isEqualTo(altText);

    }

    public void verifyLinkImage(String link, int index) {
        String attAR = getAttributeValue("(//section[contains(@class,'logo-list')]//a)["+index+"]","href").replace("https://"+shop,"");
        assertThat(attAR).isEqualTo(link);
    }

    //Inside v2
    public void isExistBlockSF(String nameSection, boolean status) {
        String xpath = "//*[contains(@type, " +nameSection+ ")]//*[contains(@class, 'feature-set-content-wrap')]";
        verifyElementPresent(xpath, status);
    }
    public void isExistBlockSF(boolean status) {
        String xpath = "//*[contains(@type,'logo')]//*[contains(@class, 'feature-set-content-wrap')]";
        verifyElementPresent(xpath, status);
    }
    public void verifyHeading(String heading, int indexBlock) {
        verifyElementText("(" + xpathLogo + ")[" + indexBlock + "]//h4", heading.toUpperCase());
    }
    public void verifyImageExist(boolean isExisted, int indexBlock) {
        String xpath = "(" + xpathLogo+ "//*[contains(@class,'VueCarousel-slide')])[" + indexBlock + "]//img[contains(@srcset,'file')]";
        scrollIntoElementView(xpathLogo);
        verifyElementPresent(xpath, isExisted);
    }
    public void verifyAltText(String altText, int index) {
        String curAlt = getAttributeValue("(" + xpathLogo+ "//img" + ")[" + index + "]", "alt");
        AssertionsForClassTypes.assertThat(curAlt).isEqualTo(altText);
    }
    public void verifyimageLink(String imgLink, String shop, int index) {
        String href = getAttributeValue("(//*[contains(@type,'logo-list')]//*[contains(@class,'VueCarousel-slide')])["+ index +"]//a", "href").replace("https://" + shop, "").trim();
        AssertionsForClassTypes.assertThat(href).isEqualTo(imgLink);
    }

}

