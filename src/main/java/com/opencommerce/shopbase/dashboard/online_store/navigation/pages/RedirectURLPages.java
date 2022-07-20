package com.opencommerce.shopbase.dashboard.online_store.navigation.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

public class RedirectURLPages extends SBasePageObject {
    public RedirectURLPages(WebDriver driver) {
        super(driver);
    }

    String shop = LoadObject.getProperty("shop");

    public void clickURLRedirects() {
        clickOnElement("//span[normalize-space()='URL Redirects']");
    }

    public void veriryRedirectURLProduct(String productName, String newURL, Boolean isCreate) {
        searchRedirectURL(newURL);
        verifyElementPresent("//a[normalize-space()='/products/" + productName + "']//ancestor::tr//a[normalize-space()='/products/" + newURL + "']", isCreate);
    }

    public void searchRedirectURL(String redirectURL) {
        String xPath = "//input[@placeholder='Search']";
        waitElementToBeVisible(xPath);
        waitTypeAndEnter(xPath, redirectURL);
        waitABit(1000);
    }

    public void veriryRedirectURLCollection(String collectionName, String newURL, boolean isCreate) {
        searchRedirectURL(newURL);
        verifyElementPresent("//a[normalize-space()='/collections/" + collectionName + "']//ancestor::tr//a[normalize-space()='/collections/" + newURL + "']", isCreate);
    }


    public void veriryRedirectURLPage(String currentURL, String newURL, boolean isCreate) {
        searchRedirectURL(newURL);
        verifyElementPresent("//a[normalize-space()='/pages/" + currentURL + "']//ancestor::tr//a[normalize-space()='/pages/" + newURL + "']", isCreate);
    }

    public void chooseURLRedirect(String url) {
        clickOnElement("//a[contains(text(),'" + url + "')]");
    }

    public void enterURLRedirectFrom(String newFrom) {
        enterInputFieldWithLabel("Redirect from", newFrom);
    }

    public void verifyURLRedirect(String newFrom, String url) {

    }

    public void verifySaveRedirectSuccessfully() {
        verifyElementPresent("//div[normalize-space()='Your redirect was updated']", true);
    }

    public void clickCreateURLredirect() {
        clickOnElement("//a[normalize-space()='Create URL redirect']");
    }

    public void enterURLRedirectTo(String redirectTo) {
        redirectTo = "http://" + shop + redirectTo;
        enterInputFieldWithLabel("Redirect to", redirectTo);
    }

    public void verifyCreateRedirectSucessfully() {
        verifyElementPresent("//*[normalize-space()='Your redirect created']", true);
    }

}
