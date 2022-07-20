package com.opencommerce.shopbase.common.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.findAll;

public class CommonPage extends SBasePageObject {
    public CommonPage(WebDriver driver) {
        super(driver);
    }
    String toastMsg = "//div[contains(@class,'s-toast') and contains(@class,'is-bottom')]//div";
    String btnBackToPaymentProvidersPage = "//div[contains(@class,'setting-payment-page')]//*[@class = 'router-link-active']";

    public void openMenuOnMobile() {
        clickOnElement("//button[descendant::span[contains(@class,'mobile-menu-icon') or contains(@class,'icon-menu-mobile')]]");
    }

    public void verifyCssValue(String element, String css) {
        String[] cssVl = css.split(">");
        if (cssVl.length > 1) {
            verifyCssValueByElement(element, cssVl[0], cssVl[1]);
        } else {
            verifyCssValueByElement(element, cssVl[0], "");
        }
    }

    public void openUrlInANewTab(String url) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open('https://' + '" + url + "', '_blank');");
        switchToLatestTab();
    }

    public String getWindowID() {
        return getDriver().getWindowHandle();
    }

    public void selectAnotherShop() {
        clickOnElement("//div[@class='d-flex flex-column']");
        clickOnElement("//div[normalize-space()='Select another shop']");
    }

    public void switchToTheLatestWindowTab() {
        switchToLatestTab();
    }

    public void logout() {
        clickOnElement("//div[@class='d-flex flex-column']");
        clickOnElement("//div[normalize-space()='Logout']");
        waitForPageLoad();
    }

    public void closeOnboarding() {
        String popupOnboarding = "//div[contains(@class,'onboarding-popup')]";

        if (isElementVisible(popupOnboarding, 2)) {
            WebElement element = getDriver().findElement(By.xpath(popupOnboarding));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].parentNode.removeChild(arguments[0])", element);
            waitUntilElementInvisible(popupOnboarding, 7);
        }
    }

    public void waitUntilToastMsgInvisible() {
        waitUntilElementInvisible(toastMsg, 8);
    }

    public String getToastMsg(){
        waitUntilElementVisible(toastMsg, 8);
        return getText(toastMsg);
    }

    public void clickBackToPaymentProviders() {
        clickOnElement(btnBackToPaymentProvidersPage);
    }

    public void verifyErrorToastMessageWillBeShown(String message) {
        waitUntilElementVisible(toastMsg, 8);
        String msg = XH(toastMsg).getText();
        assertThat(msg).isEqualTo(message);
    }
}
