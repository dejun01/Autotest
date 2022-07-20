package com.opencommerce.shopbase.dashboard.online_store.preferences.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class SitemapRobotTXTPage extends SBasePageObject {
    public SitemapRobotTXTPage(WebDriver driver) {
        super(driver);
    }
    public void verifyRobotStructure(String url) {
        verifyElementPresent("//*[contains(text(), 'Disallow: "+ url +"')]", true);
    }
    public void verifySitemapInRobotTXT(String shop) {
        verifyElementPresent("//*[contains(text(), 'Sitemap: https://"+ shop +"/sitemap.xml')]", true);
    }
}
