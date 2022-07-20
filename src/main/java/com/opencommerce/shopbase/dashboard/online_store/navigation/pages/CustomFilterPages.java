package com.opencommerce.shopbase.dashboard.online_store.navigation.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomFilterPages extends SBasePageObject {
    public CustomFilterPages(WebDriver driver) {
        super(driver);
    }
    String xpathFilter = "//*[contains(@class,'select-filter')]";
    String xpathTag = "//*[contains(@class,'filter-tags')]";
    String xpathCollectionList = "//*[@data-id='collection_page']";

    public void verifyShowDroplistFilter(Boolean status) {
        verifyElementPresent(xpathFilter, status);
    }

    public void clickDroplistFilter() {
        waitElementToBePresent(xpathFilter);
        clickOnElement(xpathFilter);
    }

    public void verifyFilterTitle(String filterTitle) {
        verifyElementContainsText("//*[contains(@class,'filter-drawer-header')]//h5", filterTitle);
    }

    public int countOption() {
        return countElementByXpath("//button[contains(@class,'is-icon-direction')]");
    }

    public void removeOption() {
        clickOnElement("//button[contains(@class,'is-icon-direction')]");
    }

    public void clickButton(String text) {
        clickOnElement("//button[normalize-space()='"+ text +"']");
    }

    public boolean existShowMore() {
        return isElementExist("//button[normalize-space()='Show more']");
    }

    public int countOptionPopup() {
        return countElementByXpath("//*[contains(@class,'filter__block')]//input");
    }

    public void verifyFilter(int countOption, int countOptionPopup) {
        assertThat(countOption).isEqualTo(countOptionPopup);
    }

    public void addFilter(int index) {
        checkCheckbox("//*[contains(@class,'filter__block')]", index, true);
    }

    public void existOptionFilter(Boolean status) {
        verifyElementPresent("//*[contains(@class,'filter-collapse')]", status);
    }

    public void inputPrice(String label, String text) {
        enterInputFieldWithLabel(label, text);
    }

    public void verifyNotShowTag(boolean status) {
        verifyElementPresent(xpathTag + "//span", status);
    }

    public void verifyShowTag(String tag) {
        String xpath = xpathTag+ "//span[contains(text(),'"+ tag +"')]";
        verifyElementPresent(xpath, true);
        highlightElement(xpath);
    }

    public void verifyText(String results) {
        String xpath = xpathCollectionList + "//*[normalize-space()='"+ results +"']";
        verifyElementPresent(xpath, true);
        highlightElement(xpath);
    }

    public void verifyResult(String nameProduct) {
        String xpath = xpathCollectionList + "//span[normalize-space()='"+ nameProduct +"']";
        verifyElementPresent(xpath, true);
        highlightElement(xpath);
    }

}
