package com.opencommerce.shopbase.dashboard.authen.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

@DefaultUrl("https://accounts.dev.shopbase.net/logout")
public class LoginDashboardPage extends SBasePageObject {
    public LoginDashboardPage(WebDriver driver) {

        super(driver);
    }

    public int MAX_RETRY_TIME = 5;
    public String email;
    public String password;
    public String shopname;
    String packageGroupIDTest = "", packageGroupIDDefault = "";

    public void enterEmail(String _email) {
//        String url = getCurrentUrl();
        String xPath = "//input[@id='email']";
        waitUntilElementVisible(xPath, 20);
        waitElementToBeVisible(xPath,30);
        waitForElementFinishRendering(xPath);
        XH(xPath).waitUntilPresent()
                .waitUntilVisible()
                .waitUntilEnabled()
                .waitUntilClickable();
        waitClearAndType(xPath, _email);
        email = _email;

//        if (url.contains(".net.cn")) {
//            enterInputFieldWithLabel("邮箱", _email);
//
//        } else {
//            enterInputFieldWithLabel("email", _email);
//        }
    }

    public void enterPassword(String _password) {
        String xPath = "//input[@id='password']";
        waitUntilElementVisible(xPath, 20);
        waitElementToBeVisible(xPath,30);
        waitForElementFinishRendering(xPath);
        XH(xPath).waitUntilPresent()
                .waitUntilVisible()
                .waitUntilEnabled()
                .waitUntilClickable();
        waitClearAndType(xPath, _password);
        password = _password;

//        String xPath = "//div[child::*[normalize-space()='Password']]//input";
//        waitUntilElementVisible(xPath, 20);
//        waitClearAndType(xPath, _password);
//        password = _password;

//        String url = getCurrentUrl();
//        if (url.contains(".net.cn")) {
//            enterInputFieldWithLabel("密码", _password);
//        } else {
//            enterInputFieldWithLabel("Password", _password);
//        }
    }

    public void enterShopname(String sShopname) {
        String url = getCurrentUrl();
        if (url.contains(".net.cn")) {
            enterInputFieldWithLabel("商店名称", sShopname);
        } else {
            enterInputFieldWithLabel("Shop name", sShopname);
        }
    }

    public void verifyErrorMsg(String status) {
        System.out.println("Verify Error Msg :" + status);
        verifyTextPresent(status, true);
    }


    public boolean moreThanOneShop() {
        return isElementExist("//*[starts-with(@class,'text-select-shop') or contains(text(),'Select a shop')]");
    }

    public void chooseShop(String shopname) {
        String xpath = "//span[contains(@class,'ui-login_domain-label') and (text()='" + shopname + "' or normalize-space()='" + shopname + "')]";
        waitABit(10000);
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);

    }

    public void verifySurveyScreen() {
        waitForEverythingComplete();
        verifyElementPresent("//div[contains(@class,'section-survey')]", true);
    }

    public void openShop(String shop) {
        getDriver().manage().deleteAllCookies();
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

    public void verifyErrorMsg(String msg, int currentRetryTime) {
        System.out.println("Verify Error Msg :" + msg);
        String xPath = "//div[@class='s-form-item__error']";
        try {
            if (XH(xPath).containsElements("a")) {
                verifyElementText(xPath, msg);
            } else {
                verifyTextPresent(msg, true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(300);
                verifyErrorMsg(msg, currentRetryTime + 1);
            } else {
                refreshPage();
                enterPassword(password);
                enterShopname(shopname);
                enterEmail(email);
                clickBtn("Sign up");
                if (XH(xPath).containsElements("a")) {
                    verifyElementText(xPath, msg);
                } else {
                    verifyTextPresent(msg, true);
                }
            }
        }
    }

    public void verifyErrorLoginMsg(String msg) {
        String xPath = "//p[normalize-space()='" + msg + "']";
        waitElementToBeVisible(xPath);
        verifyElementVisible(xPath, true);
    }

    public void verifyErrorLoginMsg(String msg, int currentRetryTime) {
        String xPath = "//p[normalize-space()='" + msg + "']";
        try {
            waitABit(2000);
            waitElementToBeVisible(xPath);
            verifyElementPresent(xPath, true);
        } catch (Throwable e) {
            e.printStackTrace();
            if (currentRetryTime < MAX_RETRY_TIME) {
                refreshPage();
                waitABit(2000);
                waitElementToBeVisible(xPath);
                verifyErrorLoginMsg(xPath, currentRetryTime + 1);
            }
        }
    }

    public void waitNavSidebar() {
        waitUntilElementInvisible("//*[@class='nav nav-sidebar']", 5);
    }

    public void closeSurvey() {
        String xPath = "//div[contains(@class,'survey')]//div[contains(@class,'sm-close')]";
        if (isElementVisible(xPath, 5)) {
            clickOnElement(xPath);
            waitForElementNotAppear(xPath);
        }
    }

    public void closeLiveChat() {
        String xpath = "//div[@id='crisp-chatbox']";

        try {
            waitElementToBePresent(xpath);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].remove();", findBy(xpath));
            assertThat(isElementExist(xpath)).isEqualTo(false);
        } catch (Throwable t) {
            System.out.println("Crisp chatbox is still exist");
        }
    }


    public void selectTheNewstShop() {
        waitElementToBeVisible("(//span[@class='ui-login_domain-label'])[last()]");
        clickOnElement("(//span[@class='ui-login_domain-label'])[last()]");
    }

    public void changeLanguage(String language) {
        waitForElementToPresent("//div[@class='lang-select']");
        clickOnElement("//div[@class='lang-select']");
        waitForEverythingComplete();
        clickOnElement("//span[@class='s-dropdown-item']//div[normalize-space()='" + language + "']");
    }

    public void verifyTranslateText(String element, String language) {
        System.out.println("element: " + element);
        verifyElementText(element, language);
    }

    public void verifySelectShopScreen() {
        verifyElementPresent("//p[contains(@class,'text-select-shop')]", true);
    }

    public void clickbtnAddANewShop() {
        clickOnElement("//button[@type='button']");
    }

    public void enterYourShopname(String shopName) {
        waitClearAndType("//input[@type='text']", shopName);
    }

    public void clickbtnCreate() {
        clickOnElement("//button[@type='submit']");
    }

    public void waitToShowSideBar() {
        String xpath = "//aside[@class='unite-ui-dashboard__aside']";
        waitElementToBeVisible(xpath, 20);
    }

    public void verifyEnabledSwitchDomain(boolean isEnabled) {
        String xpath = "//a[contains(text(),'Switch to ShopBase')]";
        verifyElementVisible(xpath, isEnabled);

    }

    public String getDomainLocation() {
        String[] domain = getCurrentUrl().split("/");
        String domainLocation = domain[2];
        return domainLocation;
    }

    public void verifyBtnSwitch(String location) {
        String xpath = "//a[text()[normalize-space()='Switch to ShopBase " + location + "']]";
        verifyElementVisible(xpath, true);
    }

    public void clickToSwitchDomain() {
        String xpath = "//a[contains(text(),'Switch to ShopBase')]";
        waitElementToBeVisible(xpath);
        clickOnElementByJs(xpath);
    }

    public void openPagewithParam(String url, String page, String paramUrl) {
        String pageUrl = url + "/" + page + paramUrl;
        openUrl(pageUrl);
    }

    public void verifyShowElementsOnSignUpPage() {
        String xpathEmail = "//input[@id='email']";
        String xpathPassword = "//input[@id='password']";
        String xpathShopName = "//input[@id='shop_name']";
        String xpathSignup = "//span[text()[normalize-space()='Sign up']]";

        verifyElementVisible(xpathEmail, true);
        verifyElementVisible(xpathPassword, true);
        verifyElementVisible(xpathShopName, true);
        verifyElementVisible(xpathSignup, true);
    }

    public void clickToSignInPage() {
        String xpath = "//span[text()[normalize-space()='Sign in']]";
        clickOnElement(xpath);
    }

    public void verifyShowElementsOnSignInPage() {
        String xpathEmail = "//input[@id='email']";
        String xpathPassword = "//input[@id='password']";
        String xpathSignin = "//span[text()[normalize-space()='Sign in']]";

        verifyElementVisible(xpathEmail, true);
        verifyElementVisible(xpathPassword, true);
        verifyElementVisible(xpathSignin, true);
    }

    public void verifyDomainInListShopChangedToHongKongDomain() {
        String shop = LoadObject.getProperty("shopname");
        String xpath = "//span[contains(text(),'" + shop + "." + "')]";
        String domain = getText(xpath);
        assertThat(domain).contains(".cn");
    }

    public void clickToUserMenuBar() {
        String xPath = "//div[@class='d-flex flex-column']";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void verifyShowButtonSwitchDomainOnUserMenuBar(String location) {
        String xpath = "//a[contains(text(),'Switch to ShopBase " + location + "')]";
        verifyElementVisible(xpath, true);
    }

    public void clickToSwitchDomainOnUserMenu() {
        String xpath = "//a[contains(text(),'Switch to ShopBase')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifyElementsOnAffiliatePage() {
        String xpathDevelopmentStore = "//span[text()[normalize-space()='Development stores']]";
        String xpathAffiliate = "//span[text()[normalize-space()='Affiliate']]";
        verifyElementVisible(xpathDevelopmentStore, true);
        verifyElementVisible(xpathAffiliate, true);
    }

    public void verifyElementsOnOrderPage() {
        String xpathTitle = "//h2[text()='Orders']";
        String xpathSearchOrder = "//div[@class='order-search-form']//input[@placeholder='Search by order name, transaction id']";
        verifyElementVisible(xpathTitle, true);
        verifyElementVisible(xpathSearchOrder, true);
    }

    public void selectLanguage(String language) {
        clickOnElement("//div[@class='footer-switch-domain']//span[@class='border-bottom-dotted']");
        clickOnElement("//div[@class='switch-language__block']//div[normalize-space()='" + language + "']");
        clickBtn("Done");
    }

    public void clickBtnSignIn() {
        String xpath = "//button[child::span[normalize-space()='Sign in'] or child::span[normalize-space()='登录']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifyPackage(String domain) {
        JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
        String shopId = jsExec.executeScript("return sbShopId").toString();
        String api = LoadObject.getProperty("gapi.url");
        String url = api + "/v1/payment/package-group?shop_id=" + shopId;
        JsonPath rs = getAPI(url);
        String packageGroupIdActual = rs.get("id").toString();
        if (domain.equals("China")) {
            assertThat(packageGroupIdActual).isEqualTo(packageGroupIDTest);
        } else {
            assertThat(packageGroupIdActual).isEqualTo(packageGroupIDDefault);
        }
    }

    public void changeDomainDashboard() {
        String xpath = "//span[contains(@class, 'bottom') and normalize-space()='English - US Server']";
        String xpathServerLocation = "//span[normalize-space()='China Mainland Server']";
        String doneButton = "//span[normalize-space()='Done']//parent::button";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        waitElementToBeVisible(xpathServerLocation);
        clickOnElement(xpathServerLocation);
        clickOnElement(doneButton);
        waitForPageLoad();
    }

    public void getPackageIdInHive() {
        String xpathPackageGroupIDTest = "//a[normalize-space()='Package Test Staging']//parent::td";
        String xpathPackageGroupIDDefault = "//a[normalize-space()='ShopBase Package Test No FreeTrial']//parent::td";
        packageGroupIDTest = getAttributeValue(xpathPackageGroupIDTest, "objectid");
        packageGroupIDDefault = getAttributeValue(xpathPackageGroupIDDefault, "objectid");
    }

    public void chooseShopClose(String shopname) {
        String xpath = "//span[contains(@class,'ui-login_domain-label') and (text()='" + shopname + "' or normalize-space()='" + shopname + " (closed)')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public boolean surveyFormIsExist() {
        return isElementExist("//div[@class='survey-form']");
    }

    public void selectCountry(String label, String value) {
        clickOnElement("//input[@placeholder='" + label + "']");
        clickOnElement("//div[normalize-space()='" + value + "']");
    }

    public void enterPhoneNumber(String label, String value) {
        clickOnElement("//div[@class='s-form-item is-required' and descendant::label[normalize-space()='" + label + "']]//input[@autocomplete='just-turn-off-stupid-auto-complete']");
        clickOnElement("//div[normalize-space()='+84']");
        waitClearAndType("//input[@placeholder='650-978-688']", value);
    }
}
