package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class FeatureProductPage extends SBasePageObject {
    public FeatureProductPage(WebDriver driver) {
        super(driver);
    }

    public void enterProduct(String productName) {
        clearProduct();
        if (!productName.isEmpty()) {
            enterInputFieldWithLabel("Product", productName);
            String xpath = "//div[@class='s-dropdown-item__content' and normalize-space()='" + productName + "']";
            waitElementToBeVisible(xpath, 10);
            clickOnElement(xpath);
            verifyElementPresent("//a[@class='s-delete is-small']", true);
        }
    }

    private void clearProduct() {
        String xpath = "//a[@class='s-delete is-small']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
        verifyElementPresent("//input[@placeholder='Type a keyword to search products']", true);
    }
}
