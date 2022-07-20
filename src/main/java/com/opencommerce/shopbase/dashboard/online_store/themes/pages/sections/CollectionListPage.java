package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CollectionListPage extends SBasePageObject {
    public CollectionListPage(WebDriver driver) {
        super(driver);
    }

    String sessionActive = "//div[@class='s-collapse-item draggable-item is-active']";

    public void selectCollectionList(String collection) {
        if (!collection.isEmpty()) {
            if (isElementExist(sessionActive + "//a[@class='s-delete is-small']")) {
                clickOnElement(sessionActive + "//a[@class='s-delete is-small']");
            }

            String xpathInput = sessionActive + "//input[@placeholder='Type a keyword to search collections' or @placeholder='Type a keyword to search']";
            String xpathDropItem = sessionActive + "//div[@class='s-dropdown-item__content' and normalize-space()='" + collection + "']";
            clearAndInputTextByJS(xpathInput, collection);
            waitABit(1000);
            waitUntilElementVisible(xpathDropItem,10);
            clickOnElement(xpathDropItem);
        }
    }
}
