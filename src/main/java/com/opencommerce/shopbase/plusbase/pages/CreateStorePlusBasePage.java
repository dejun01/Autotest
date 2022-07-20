package com.opencommerce.shopbase.plusbase.pages;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
public class CreateStorePlusBasePage extends SBasePageObject {
    public CreateStorePlusBasePage(WebDriver driver) {
        super(driver);
    }
    public int MAX_RETRY_TIME = 10;
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String storeCountry;
    public String yourPersonalLocation;
    public String stateProvince;
    public String zipCode;
    public String phoneNumber;

    public boolean isDashboardHomepage() {
        String xPath = "//*[@class='nav nav-sidebar']";
        return isElementVisible(xPath, 10);
    }

    public void clickCapchar() {
        if (isElementVisible("//div[@id='grecaptcha']//iframe", 10)) {
            switchToIFrame("//div[@id='grecaptcha']//iframe");
            clickOnElement("//span[contains(@class,'recaptcha-checkbox')]//div[(@class='recaptcha-checkbox-border')]");
            switchToIFrameDefault();
        }
    }

    public void clickSelectAnotherShop() {
        String xPath = "//*[contains(@class,'avatar')]";
        clickOnElement(xPath);
        String xPathOther = "//div[normalize-space()='Select another shop']";
        clickOnElement(xPathOther);
    }

    public void clickNoThank() {
        clickOnElement("//button[@class='s-button is-outline']//span[contains(text(),'No thanks, I will decide later')]");
    }

    public void clickBtnWantDropshipStore() {
        clickOnElement("//div[@class='survey__btn--active survey__btn']");
    }

    public String getLogo() {
        String xpath = "//h2[contains(@class,'word-break')]";
        waitUntilElementVisible(xpath,25);
        return getText(xpath);

    }
}
