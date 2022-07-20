package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CollectionDetailPage extends SBasePageObject {
    public CollectionDetailPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCollectionPageDisplay(String collectionName) {
        verifyElementPresent("//h1[normalize-space()='" + collectionName + "']", true);
    }

    public void verifyPageNotFound() {
        verifyElementPresent("//h2[normalize-space()='404 Page Not Found']", true);
    }

    public void verifyShowSort(boolean showSort) {
        verifyElementPresent("//div[@class='flex align-center justify-center']//div[text()='Sort by']",showSort);
    }

    public void verifyPagination(boolean showPagination) {
        verifyElementPresent("//div[@class='pagination']",showPagination);
    }
}
