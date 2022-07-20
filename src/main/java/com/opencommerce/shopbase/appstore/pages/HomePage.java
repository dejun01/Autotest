package com.opencommerce.shopbase.appstore.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends SBasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getCollectionOnAppStore() {
        return getListText("//div[@class='sidebar-block-icon']//div[@class='block-title']");
    }

    public void collectionInSidebarDisplay(boolean isDisplay) {
        verifyElementPresent("//div[@class='sidebar-block-icon']", isDisplay);
    }

    public void verifyListCategoryInSidebarDisplay(boolean isDisplay) {
        verifyElementPresent("//div[@class='sidebar-block']", isDisplay);
    }

    public List<String> getCategoryInSidebar() {
        return getListText("//div[@class='sidebar-block' and descendant::h3[normalize-space()='categories']]//ul/li//a");
    }

    public void verifyCategoryInHome(boolean isDisplay) {
        verifyElementPresent("//div[@id='categories-carousel']", isDisplay);
    }

    public HashMap<String, List<String>> getListCategoryInHome() {
        HashMap<String, List<String>> actCate = new HashMap<>();
        int count = countElementByXpath("//div[@id='categories-carousel']//div[@class='categories-item']");
        for (int i = 0; i < count; i++) {
            String name = getText("(//div[@id='categories-carousel']//div[@class='categories-item'])[" + (i + 1) + "]//h5");
            String des = getText("(//div[@id='categories-carousel']//div[@class='categories-item'])[" + (i + 1) + "]//p");
            actCate.put(name, asList(name, des));
        }
        return actCate;
    }

    public HashMap<String, List<String>> getListCollectionInHome() {
        HashMap<String, List<String>> actCol = new HashMap<>();
        int count = countElementByXpath("//div[contains(@id,'collection')]");
        for (int i = 0; i < count; i++) {
            String name = getText("(//header[@class='collections-title']//h4)[" + (i + 1) + "]");
            String des = getText("(//header[@class='collections-title']//span)[" + (i + 1) + "]");
            actCol.put(name, asList(name, des));
        }
        return actCol;
    }

    public List<String> getlistCollectionInHome() {
        return getListText("//header[@class='collections-title']//h4");
    }

    public int countAppOfCollectionInHome(String collectionName) {
        return countElementByXpath("//div[contains(@id,'collection') and descendant::h4[normalize-space()='" + collectionName + "']]//h5");
    }

    public String getAppName(String collectionName, int res) {
        return getText("(//div[contains(@id,'collection') and descendant::h4[normalize-space()='" + collectionName + "']]//h5)[" + res + "]");
    }

    public String getAppDesc(String collectionName, int res) {
        return getText("(//div[contains(@id,'collection') and descendant::h4[normalize-space()='" + collectionName + "']]//div[contains(@class,'app-item-info')]//*[not(@class='app-title')])[" + res + "]");
    }

    public void enterKeyword(String keyword) {
        waitClearAndTypeThenEnter("//input[@placeholder='Search for app']", keyword);
    }

    public void verifyMessageNoresult(String keyword) {
        waitForEverythingComplete();
        verifyElementText("//h1[@class='search-title']", "No result for \"" + keyword + "\"");
        verifyElementText("//p[@class='collection-subtext']", "Try searching again with different keyword.");
    }

    public void verifySuggestApp() {
        verifyElementText("//header[@class='collections-title']/h4", "You may have interests on these apps ...");
    }

    public void verifyMessageResult(String keyword) {
        verifyElementText("//h1[@class='search-title']", "1 result for \"" + keyword + "\"");
    }

    public void verifyAppsDisplayed(String keyword) {
        String app = getText("//div[@class='app-item-info']//h5");
        assertThat(app).contains(keyword);
    }

    public void clickApp(String appName) {
        clickOnElement("//div[@class='app-item-info']//h5[normalize-space()='" + appName + "']");
    }

    public Boolean isExistedSeeAll(String collectionName) {
        return isElementExist("//div[descendant::h4[normalize-space()='" + collectionName + "'] and @class='collection-with-list-app']//a[normalize-space()='See all']");
    }

    public void clickSeeAll(String collectionName) {
        clickOnElement("//div[descendant::h4[normalize-space()='" + collectionName + "'] and @class='collection-with-list-app']//a[normalize-space()='See all']");
    }

    public void clickCategoryName(String name) {
        clickOnElement("//div[contains(@class,'block-sidebar-item')]//a[normalize-space()='" + name + "']");
    }
}
