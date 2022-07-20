package com.opencommerce.shopbase.dashboard.apps.printbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class AuthenPage extends SBasePageObject {
    public AuthenPage(WebDriver driver) {
        super(driver);
    }

    public int MAX_RETRY_TIME = 5;
    public String email;
    public String password;
    public String shopname;

    public void enterShopName(String shopName) {
        waitTypeAndEnter("(//input[contains(@name,'Enter your store name')])[1]", shopName);
//        enterInputFieldWithLabel("Enter your store name (ex: startees)", shopName);
    }

    public void clickStartFreeTrial() {
        try {
            clickOnElement("(//button[contains(.,'Start free trial')])[1]");
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            clickOnElement("(//button[contains(.,'Start free trial')])[1]");
        }
    }

    public void waitUntilDashboardPresent(String shopName) {
        waitUntilElementVisible("//p[text()='" + shopName + "']", 60);
    }

    public void verifyPrintBaseDashboardDisplayed() {
        verifyElementPresent("//span[text()[normalize-space()='Campaigns']]", true);
    }

    public void inputStoreCountry(String storeCountry) {
        enterInputFieldWithLabel("Select Store country", storeCountry);
    }

    public void inputPersonalLocation(String storeCountry) {
        enterInputFieldWithLabel("Your personal location", storeCountry);
    }

    public void inputCountryPhoneCode(String countryPhoneCode) {
        String xPath = "//div[@class='s-form-item__content' and child::div//input[@id='phone-number']]";
        enterInputFieldWithLabel(xPath, "text", countryPhoneCode, 1);
    }

    public void clickCapchar() {
        if (isElementVisible("//div[@id='grecaptcha']//iframe", 10)) {
            switchToIFrame("//div[@id='grecaptcha']//iframe");
            clickOnElement("//div[@id='rc-anchor-alert']");
            switchToIFrameDefault();
        }
    }

    public void closePrintBasePopup() {
        String xpath = "//iframe[@class='HB-Modal smooth-impact']";
        try {
            waitUntilElementVisible(xpath, 50);
            switchToIFrame(xpath);
            waitABit(5000);
            clickOnElement("//div[contains(@class,'no-thanks-wrapper')]/ancestor::div[@id='hellobar-modal']//a[@class='icon-close']");
        } catch (Throwable t) {
            System.out.println(" ");
        }
    }

    String xpath_Button = "//button/span[contains(text(),'%s')]";

    public void clickBtnAuthen(String text) {
        String xpath_Start = String.format(xpath_Button, text);
        waitUntilElementVisible(xpath_Start, 50);
        clickOnElement(xpath_Start);
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void goToLoginPage(String storeName) {
        open();
        String url = getDriver().getCurrentUrl() + "&storename=" + storeName;
        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitUntiljQueryRequestCompletes(10);
        waitForPageLoad();
    }

    public void enterEmail(String _email) {
        String xPath = "//div[child::*[normalize-space()='Email']]//input";
        waitClearAndType(xPath, _email);
        email = _email;
    }

    public void enterPassword(String _password) {
        enterInputFieldWithLabel("Password", _password);
        password = _password;
    }
}
