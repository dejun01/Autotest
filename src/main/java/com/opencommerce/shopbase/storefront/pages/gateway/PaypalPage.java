package com.opencommerce.shopbase.storefront.pages.gateway;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PaypalPage extends SBasePageObject {
    public PaypalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isExistBtnLoginPaypalExpress() {
        return $("//a[text()='Log In']").isDisplayed();
    }

    public boolean isExistBtnContinue() {
        boolean existBtnContinue = isElementExist("//button[text()='Continue']");
        return existBtnContinue;
    }

    public void clickBtnLoginPayPal() {
        getDriver().manage().deleteAllCookies();
        String xPath = "//*[text()='Log In']|//*[text()='Đăng nhập']";
        if (isElementVisible(xPath, 30)) {
            waitForElementFinishRendering(xPath);
            clickOnElement(xPath);
        }
    }

    public void waitBtnLoginPaypalPresent() {
        String xPath = "//*[text()='Log In']|//*[text()='Đăng nhập']";
        if (isElementVisible(xPath, 30))
            waitForElementToPresent(xPath);
    }

    public void clickBtnContinuePayPal() {
        String xPath = "//*[text()='Continue']";
        waitUntilElementVisible(xPath, 30);
        waitForElementFinishRendering(xPath);
        scrollIntoElementView(xPath);
//        clickOnElementByJs(xPath);
        $(xPath).click();
    }

    public void clickBtnPaynowPayPal() {
        String _xPath = "//button[@id='acceptAllButton']";
        if (isElementVisible(_xPath, 10)) {
            waitForElementFinishRendering(_xPath);
            clickOnElement(_xPath);
        }
        String xPath = "//button[@id='payment-submit-btn']";
        waitUntilElementVisible(xPath, 50);
        waitForElementFinishRendering(xPath);
        waitElementToBeClickable(xPath);
        scrollIntoElementView(xPath);
        try {
            clickOnElement(xPath);
        } catch (Exception e) {
            clickOnElementByJs(xPath);
        }
    }

    public void clickButtonLoginSandboxDashboard() {
        String xPath_LoginBtn = "//div[@id='header-buttons']//a[text()='Log In']";
        if (!isElementExist(xPath_LoginBtn)) {
            waitForElementToPresent("//a[@id='header-logout']");
            clickOnElement("//a[@id='header-logout']");
            waitForElementFinishRendering(xPath_LoginBtn);
            waitForElementToPresent(xPath_LoginBtn);
            clickOnElement(xPath_LoginBtn);
        }
        clickOnElement(xPath_LoginBtn);
    }

    public void enterEmailLoginSandboxer(String email) {
        String xPath = "//section[@id='login']//input[@id='email']";
        waitABit(2000);
        $(xPath).click();
        $(xPath).clear();
        $(xPath).sendKeys(email);
    }

    public void clickBtnNext() {
        String xPath = "//*[@id='btnNext']";
        String xPathSpinner = "//div[@class='transitioning spinner']";
        if (isElementVisible(xPath, 2)) {
            waitForElementFinishRendering(xPath);
            clickOnElementByJs(xPath);
            waitForElementNotAppear(xPathSpinner);
            waitUntilElementVisible("//input[@id='password']", 90);
        }
    }

    public boolean isBtnNextPresent() {
        return $(iD("btnNext")).isClickable();
    }

    public void enterPassLoginSandbox(String pass) {
        String xPath = "//section[@id='login']//input[@id='password']";
        $(xPath).click();
        $(xPath).clear();
        $(xPath).sendKeys(pass);
    }

    public void clickBtnLogin() {
        $(iD("btnLogin")).click();
        waitABit(2000);
    }

    public void verifySandboxDashboard() {
        verifyElementPresent("//*[text()='PayPal balance' or text()='PayPal Balance']", true);
    }

    public void clickActivityTabSandbox() {
        clickOnElement("//a[@data-testid='navigation_transactions']");
        verifyElementPresent("//input[@placeholder='Search for transactions']", true);
    }

    public void searchTransactionID(String transactionID) {
        waitTypeAndEnter("//input[@placeholder='Search for transactions']", transactionID);
    }

    public void chooseTransactionIDFromListTrans(String transID) {
        clickOnElement("//tr[@id='" + transID + "']//td[@class='type']");
    }

    public String getGrossAmount() {
        String xPath = "//span[@class='total-amount']";
        String grossAmount = getTextValue(xPath);
        return grossAmount;
    }

    public void enterPwd(String pwd) {
//        String xPath = "//section[@id='login']//input[@id='password']";
        String xPath = "//*[@id='login' or @id='passwordSection']//input[@id='password']";
        if (isElementVisible(xPath, 5))
            $(xPath).sendKeys(pwd);
//        waitClearAndType("//section[@id='login']//input[@id='password']", pwd);
    }

    //move - done
    public void enterUserName(String username) {
        String xPath = "//*[@id='login' or @id='passwordSection']//input[@id='email']";
        if (isElementVisible(xPath, 30)) {
            $(xPath).clear();
            $(xPath).sendKeys(username);
        }
    }

    String paypalLoginButtonXpath = "//*[text()[normalize-space()='Log In'] or @class='btn full ng-binding' or @id='btnLogin' or text()[normalize-space()='Đăng nhập']]";

    public void clickOnLogin() {
        if (isElementVisible(paypalLoginButtonXpath, 10))
            $(paypalLoginButtonXpath).click();

    }

    public void switchToPaypalLoginPage() {
        ArrayList<String> availableWindows = new ArrayList<String>(getDriver().getWindowHandles());
        if ((availableWindows.size()) > 1) {
            getDriver().switchTo().window(availableWindows.get(1));
        }
    }

    public boolean checkLoginBtnDisplay() {
        return isElementVisible(paypalLoginButtonXpath, 5);
    }

    public boolean isShowPayPalLogin() {
        String xpath = "//*[text()[normalize-space()='Pay with PayPal']]";
        return isElementExist(xpath);
    }

    public void clickOnAcceptCookieBtn() {
        String xPath = "//*[@id='acceptAllButton']";
        if (isElementVisible(xPath, 7)) {
            try {
                $(xPath).click();
            } catch (Exception e) {
                $(xPath).click();
            }
        }
    }

    public String getPaidTotalAmt() {
        String xPath = "//*[@data-testid='header-cart-total']";
        int i = 0;
        while (!isElementVisible(xPath, 30)) {
            refreshPage();
            i++;
            if (i == 5) {
                break;
            }
        }
        return getTextValue(xPath).split("\\s+", 2)[0];
    }

    private String getTextOfReturnHyperlink() {
        String xPath = "//a[@data-testid='cancel-link']";
        waitForElementFinishRendering(xPath);
        System.out.println(getText(xPath));
        return getText(xPath);
    }

    public void verifyBrandName(String brandName) {
        String returnHyperLink = getTextOfReturnHyperlink();
        System.out.println(brandName);
        assertThat(returnHyperLink, containsString(brandName));
    }

    public boolean switchToPayPalIframe() {
        String xPath = "//iframe[@name='injectedUl']";
        if (isElementExist(xPath, 3)) {
            switchToIFrame(xPath);
            return true;
        }
        return false;
    }

    public void clickBtnCheckoutBuyWithPaypal() {
        String xPath_iframe = "//iframe[contains(@name,'paypal_buttons')]";
        String xPath_paypalSmart = "//div[@data-funding-source='paypal']";
        if (isElementExist(xPath_iframe, 60)) {
            switchToIFrame(xPath_iframe);
            waitUntilElementVisible(xPath_paypalSmart, 10);
            clickOnElementByJs(xPath_paypalSmart);
            switchToIFrameDefault();
        }
    }

    String xPathChangeBtn = "//a[text()='Change']";

    public boolean changeBtnDisplay() {
        return isElementVisible(xPathChangeBtn, 5);
    }

    public void clickChangeBtn() {
        $(xPathChangeBtn).click();
    }
}
