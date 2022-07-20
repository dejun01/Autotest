package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing;

import common.BFlowPageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

public class HomePages extends BFlowPageObject {

    public HomePages(WebDriver driver) {
        super(driver);
    }

    public void clickLinkToMoreStores() {
        clickOnElement("//span[text()='Link to more stores']//parent::button");
    }

    public void clickDropDownList() {
        clickOnElement("//div[@class='ant-select ant-select-enabled']");
    }

    public void selectPlatform(String platform) {
        clickOnElement("//li[text()[normalize-space()='" + platform + "']]");
    }

    public void enterStoreURL(String store) {
        waitTypeAndEnter("//input[@class='ant-input']", store);
    }

    public void verifyConnectSusscess() {
        waitForEverythingComplete();
        String shop = LoadObject.getProperty("shop");
        verifyTextPresent(shop, true);
    }
}
