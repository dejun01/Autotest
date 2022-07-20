package com.opencommerce.shopbase.storefront.pages.searchpage;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;


public class SearchPage extends SBasePageObject {
    public SearchPage(WebDriver driver) {
        super(driver);
    }


    public void inputToSearchBox(String _value) {
        waitClearAndType("//input[@type='text']", _value);
    }

    public void clickOnSearchIcon() {
        clickOnElement("//button[@class='icon-fallback-text']");
    }

    public void verifySearchResult() {
        verifyElementVisible("//div[contains(@class,'product-grid')]", true);
    }

    public void verifyNoResultNoti(String _prod) {
        verifyElementVisible("//div[@class = 'subtle' and text()='Your search for \""+_prod+"\" did not yield any results.']", true);
    }

    public void verifyMatchedResult(String _value) {
        verifyElementVisible("(//div[contains(@class,'product-col')]//span[text()='"+_value+"'])[1]", true);
    }

    public void verifyKeywordMatchedProduct(String _value) {
        verifyElementVisible("//div[contains(@class,'product-col')]//span[text()='"+_value+"']", true);
    }
}
