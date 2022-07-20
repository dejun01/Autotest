package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SignInPlusBasePage extends SBasePageObject {
    public SignInPlusBasePage(WebDriver driver) {
        super(driver);
    }

    public int MAX_RETRY_TIME = 5;
    public String email;
    public String password;
    public String shopname;

    public void openShop(String shop) {
        waitABit(5000);
        openUrl("https://" + shop);
        waitForPageLoad();
    }

    public void goToLoginPage() {
        open();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitUntiljQueryRequestCompletes(10);
        waitForPageLoad();
    }

    public void enterShopName(String shopName) {
        waitTypeAndEnter("(//input[contains(@name,'Enter your store name')])[1]", shopName);
    }

    public void clickCapchar() {
        if (isElementVisible("//div[@id='grecaptcha']//iframe", 10)) {
            switchToIFrame("//div[@id='grecaptcha']//iframe");
            clickOnElement("//div[@id='rc-anchor-alert']");
            switchToIFrameDefault();
        }
    }

    public void clickBtnGetStarted() {
        clickOnElement( "//p[@class='sub-headline']//following-sibling::a");
    }

    public String getHeader() {
        String xpath = "//div[@class='unite-ui-login__main']//h1";
        waitUntilElementVisible(xpath,10);
        return getText(xpath);

    }
}
