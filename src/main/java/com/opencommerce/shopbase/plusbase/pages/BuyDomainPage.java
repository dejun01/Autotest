package com.opencommerce.shopbase.plusbase.pages;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class BuyDomainPage extends SBasePageObject {
    public BuyDomainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputDomain(String domain) {
        String xpath = "//input[@placeholder='e.g. example.com']";
        waitClearAndTypeThenEnter(xpath, domain);
    }

    public String getMessage() {
        String xpath = "//div[@class='s-alert is-green']/div//span";
        return getText(xpath);
    }

    public void verifyInfodomainWithTitle(String label, String val, String title) {
        String xpath = "//section[contains(@class, 'domain-type') and contains(., '" + title + "') and contains(., '" + label + "') and contains(., '" + val + "')]";
        waitElementToBeVisible(xpath, 30);
        verifyElementVisible(xpath, true);
    }

    public void removeDomain(String title, String domain, String btnName) {
        String xpath = "//section[contains(@class, 'domain-type') and contains(., '" + title + "')]//tr[child::td[text()[normalize-space()='" + domain + "']]]//a[text()[normalize-space()='" + btnName + "']]";
        clickOnElement(xpath);
    }

    public void verifydomaindashboard() {
        String xpath = "//a[text()[normalize-space()='Domains']]";
        clickOnElement(xpath);
    }

    public String getDomainName() {
        String xpath = "//a[contains(text(),'Domains')]//ancestor::div[contains(@class,'finish-buy-domain')]//h1";
        return getTextByJS(xpath).trim();
    }

    public void clickBtnAction(String buttonName) {
        String xpath = "//span[contains(text(),'" + buttonName + "')]//parent::button";
        focusClickOnElement(xpath);
        waitABit(5000);
    }

    public String getAlerts() {
        String xpath = "//div[@class='s-alert__content']";
        return getText(xpath);
    }

    public void clickConnectExistingDomain(String lable) {

    }
}
