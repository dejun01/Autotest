package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class FeaturedPromotionPage extends SBasePageObject {
    public FeaturedPromotionPage(WebDriver driver) {
        super(driver);
    }


    public void inputText(String label, String text) {
        switchToIFrame("//div[contains(@class,'is-active')]//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//iframe");
        waitClearAndType("//body[@id='tinymce']",text);
        switchToIFrameDefault();

    }

    public void verifyFullWidthSection(boolean fullWidthSection) {
        verifyElementPresent("//div[contains(@class,'featured-promotions')]//div[@class='container-fluid']",fullWidthSection);
    }

    public void verifyImage(int index) {
        verifyElementPresent("(//div[contains(@class,'featured-promotions')]//div[@class='featured-promotions-section__image'])["+index+"]//img",true);
    }

    public void verifyText(String text, int index) {
        verifyElementText("(//div[contains(@class,'featured-promotions')]//div[@class='featured-promotions-section__content'])["+index+"]",text);
    }
}
