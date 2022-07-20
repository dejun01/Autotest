package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

public class CommonXPandaPages extends BFlowPageObject {
    public CommonXPandaPages(WebDriver driver) {
        super(driver);
    }

    public void switchToMenu(String nameMenu) {
        clickOnElement("//ul[contains(@class,'ant-menu') or contains(@class,'nav-sidebar')]//span[text()='" + nameMenu + "']/parent::a");
    }

    public void switchToMenuLevel2(String tab) {
        clickOnElement("//ul[@class='ant-menu']//li//*[text()[normalize-space()='" + tab + "']]");
    }

    public boolean isSmartBar() {
        return isElementExist("//div[@class='smartbar-toggle-button']");
    }

    public void closeSmartBarCP() {
        clickOnElement("//div[@class='smartbar-toggle-button']");
    }

    public void switchToTab(String _labelName) {
        clickOnElement("//ul[contains(@class,'tree-menu-ul') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + _labelName + "']]|//div[@class='container']//*[contains(text(),'" + _labelName + "')]|//div[@role='tab' ][descendant-or-self ::*[contains(text(),'" + _labelName + "')]]");
        String xpathLoading = "//div[text()='Loading...']";
        waitForElementNotAppear(xpathLoading);
    }
}
