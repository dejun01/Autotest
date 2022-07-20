package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class RichTextPage extends ThemeEditorPage {

    public RichTextPage(WebDriver driver) {
        super(driver);
    }


    public void clearLinkExisted() {
        while (isElementExist("//a[@class='s-delete is-small']")) {
            clickOnElement("//a[@class='s-delete is-small']");
        }
        verifyElementPresent("//input[@placeholder='Paste a link or search']", true);
    }

    public void chooseLink(String links) {
        clickOnElement("//input[@placeholder='Search or paste a link']");
        String listlink[] = links.split(",");
        for (String link : listlink) {
            clickOnElement("//div[contains(@class,'s-dropdown-menu is-opened')]//span[normalize-space()='" + link + "']");
        }
    }

    public void enterText(String text) {
        waitABit(10000);
        String xpathIframe = "//iframe[contains(@id,'tiny-vue')]";
        waitForElementToPresent(xpathIframe);
        switchToIFrame(xpathIframe);
        $("//body[@id='tinymce']").clear();
        $("//body[@id='tinymce']").sendKeys(text);
        switchToIFrameDefault();

    }

}
