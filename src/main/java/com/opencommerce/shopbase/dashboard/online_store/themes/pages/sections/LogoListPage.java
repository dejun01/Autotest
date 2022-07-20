package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;

import org.openqa.selenium.WebDriver;

public class LogoListPage extends ThemeEditorPage {
    public LogoListPage(WebDriver driver) {
        super(driver);
    }

    public void enterHeading(String heading) {
        enterInputFieldWithLabel("Heading", heading);
    }

    String sessionActive = "//div[@class='card__section']//div[@class='s-collapse-item draggable-item is-active']";


    public void enterContentLogo(String label, String value) {
        String xpath = sessionActive + "//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//input";
        waitTypeOnElement(xpath, value);
    }


    public void selectLinkPage(String typeLink, String dataLink) {
        chooseTypeLinkPageByLabel(typeLink);
        selectDataLink(dataLink);
    }

    public void selectDataLink(String dataLink) {
        String xpathInput = sessionActive + "//div[@name='link']//div[@class='input-wrapper s-mt8']//input";
        String xpathDropItem = sessionActive + "//div[contains(@class,'is-focusable')]//span[@class='s-dropdown-item'  and normalize-space()='" + dataLink + "']//div[@class='s-dropdown-item__content']";
        clickThenTypeCharByChar(xpathInput, dataLink);
        waitUntilElementVisible(xpathDropItem, 10);
        try {
            clickOnElement(xpathDropItem);
        } catch (Exception ex) {
            clickOnElement(xpathDropItem);
        }
    }

    public void chooseTypeLinkPageByLabel(String typeLink) {
        String xpathInput = sessionActive + "//div[@name='link']//input";
        String xpathDropItem = sessionActive + "//div[contains(@class,'is-focusable')]//span[@class='s-dropdown-item'  and normalize-space()='" + typeLink + "']";
        clickThenTypeCharByChar(xpathInput, typeLink);
        waitUntilElementVisible(xpathDropItem, 10);
        if (isElementExist(xpathDropItem, 5)) {
            clickOnElementByJs(xpathDropItem);
        }
    }

    public void closeBlockContent() {
        clickOnElement("//div[@role='tab']//div[@role='button']//span[@class='s-icon s-collapse-item__arrow is-small is-active']");
    }
}
