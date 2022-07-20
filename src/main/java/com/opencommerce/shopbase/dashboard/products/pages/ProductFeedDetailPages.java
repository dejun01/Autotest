package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductFeedDetailPages extends SBasePageObject {
    public ProductFeedDetailPages(WebDriver driver) {
        super(driver);
    }

    public void chooseProductFeed(String feedName) {
        String xPath = "//a[normalize-space()='"+feedName+"']";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void searchProductFeed(String nameFeed) {
        String xPath = "//input[@type='text' and @placeholder='Search product feed name']";
        waitClearAndType(xPath,nameFeed);
    }

    public String getName() {
        String nameFeed= getText("//a[contains(text(),'Product feed test')]");
        return nameFeed;
    }

    public void clickBreadProductFeeds() {
        String xpath = "//a[@class='router-link-active']//span[@class='s-icon is-small']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void searchCollection(String colName) {
        String xpath_a = "//input[@placeholder='Search for collections']";
        XH(xpath_a).sendKeys(Keys.ENTER);
        String xpath_b = "//h2//following-sibling::div//input[@placeholder='Search for collections']";
        waitClearAndType(xpath_b, colName);
        waitABit(2000);
    }

    public void chooseCollection() {
        String xPath = "//div[@class='collection-list']//label[1]";
        clickOnElement(xPath);
    }

    public void typeFeedName(String feedName) {
        String xPath = "//input[@type='text' and @placeholder='Collection name - Product Feed']";
        waitClearAndType(xPath,feedName);
    }
}
