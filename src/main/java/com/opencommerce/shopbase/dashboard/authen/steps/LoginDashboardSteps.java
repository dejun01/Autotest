package com.opencommerce.shopbase.dashboard.authen.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.pages.LoginDashboardPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.JavascriptException;

import static com.opencommerce.shopbase.OrderVariable.accesstoken;

public class LoginDashboardSteps extends CommonSteps {
    LoginDashboardPage loginPage;
    String partnerLink = LoadObject.getProperty("partnerLink");

    @Step
    public void openSignInPage() {
        try {
            loginPage.goToLoginPage();
        } catch (JavascriptException ex) {
            loginPage.goToLoginPage();
        }
        getDriver().manage().deleteAllCookies();
        loginPage.maximizeWindow();
//        loginPage.waitForTextToAppear("Sign in", 10000);
    }

    @Step
    public void openShop(String shop) {
        loginPage.openShop(shop);
        loginPage.maximizeWindow();
    }

    public void enterEmail(String _email) {

        loginPage.enterEmail(_email);
    }

    public void enterPassword(String _password) {

        loginPage.enterPassword(_password);
    }


    @Step
    public void verifyErrorMsg(String msg) {
        if (msg.contains(";")) {
            String[] msgs = msg.split("; ");
            for (String message : msgs) {
                loginPage.verifyErrorMsg(message, 0);
            }
        } else {
            loginPage.verifyErrorMsg(msg, 0);
        }
    }

    @Step
    public boolean moreThanOneShop() {
        return loginPage.moreThanOneShop();
    }

    @Step
    public void chooseShop(String shopname) {
        loginPage.chooseShop(shopname);
        loginPage.waitForEverythingComplete();
        loginPage.waitForPageLoad();
        loginPage.waitUntilInvisibleLoading(5);
        loginPage.waitForPageLoad();
        loginPage.waitUntilInvisibleLoading(5);
    }

    @Step
    public void clickSignUp() {
        String url = loginPage.getCurrentUrl();
        if (url.contains(".net.cn")) {
            loginPage.clickLinkTextWithLabel("注册.");
        } else {
            loginPage.clickLinkTextWithLabel("Sign up.");
        }
    }

    @Step
    public void enterShopname(String sShopname) {
        loginPage.enterShopname(sShopname);
    }

    @Step
    public void refreshPage() {
        loginPage.refreshPage();
        loginPage.waitForEverythingComplete();
    }

    @Step
    public void clickBtnSignUp() {
        String url = loginPage.getCurrentUrl();
        if (url.contains(".net.cn")) {
            loginPage.clickBtn("注册");
        } else {
            loginPage.clickBtn("Sign up");
        }
    }

    @Step
    public void closeBrowser() {
        loginPage.closeBrowser();
    }

    @Step
    public void verifySurveyScreen() {
        loginPage.verifySurveyScreen();
    }

    @Step
    public void logInfor(String value) {
    }

    @Step
    public LoginFromCommand loginAs(String username) {
        return new LoginFromCommand(username);
    }

    @Step
    public void verifyErrorLoginMsg(String msg) {

        loginPage.verifyErrorLoginMsg(msg, 3);
    }

    public void closeLiveChat() {
        loginPage.closeLiveChat();
    }

    @Step
    public void openURL(String url) {
        loginPage.openUrl(url);
        loginPage.waitForPageLoad();
        loginPage.maximizeWindow();
    }

    public String getAccessTokenShopBase() {
        return loginPage.getAccessTokenShopBase();
    }

    public String getAccessTokenShopBase(String shopDomain) {
        return loginPage.getAccessTokenShopBase(shopDomain);
    }

    public void loginShopbase(String shop, String email, String password) {
        loginPage.openUrl("https://" + shop + "/admin");
        loginPage.maximizeWindow();
        waitToLoadPage();
        enterEmail(email);
        enterPassword(password);
        clickBtnSignIn();
        closeLiveChat();
    }

    public void loginShopbase(String email, String password) {
        loginPage.maximizeWindow();
        enterEmail(email);
        enterPassword(password);
        clickBtnSignIn();
        closeLiveChat();
    }

    public void waitToLoadPage() {
        loginPage.waitForEverythingComplete();
        loginPage.waitForPageLoad();
//        loginPage.waitUntilInvisibleLoading(7);
    }

    public void moveToPage(String shop) {
        loginPage.openUrl("https://" + shop + "/admin");
        loginPage.waitForEverythingComplete();
        loginPage.waitUntilInvisibleLoading(5);
    }

    public void openSignInPartnerPage() {
        loginPage.openUrl(partnerLink);
    }

    @Step
    public void verifyTranslateText(String language, String element, String en, String zh) {
        if (language.equalsIgnoreCase("Chinese")) {
            loginPage.verifyTranslateText(element, zh);
        } else loginPage.verifyTranslateText(element, en);
    }

    @Step
    public void changeLanguage(String language) {
        loginPage.changeLanguage(language);
    }

    @Step
    public void verifySelectShopScreen() {
        loginPage.verifySelectShopScreen();
    }

    @Step
    public void clickbtnAddANewShop() {
        loginPage.clickbtnAddANewShop();
    }

    @Step
    public void enterYourShopname(String shopName) {
        loginPage.enterYourShopname(shopName);
    }

    @Step
    public void clickbtnCreate() {
        loginPage.clickbtnCreate();
    }

    public String getAccessTokenShopBase(String username, String pwd, String shopDomain) {
        return loginPage.getAccessTokenShopBase(username, pwd, shopDomain);
    }

    public void signInToListStore() {
        loginPage.clickBtn("Sign in");
        loginPage.waitForEverythingComplete();
        loginPage.waitForPageLoad();
        loginPage.verifySelectShopScreen();
    }

    public void verifyEnabledSwitchDomain(boolean isEnabled) {
        loginPage.verifyEnabledSwitchDomain(isEnabled);
    }

    public String getDomainLocation() {
        return loginPage.getDomainLocation();
    }

    public void verifyBtnSwitch(String location) {
        loginPage.verifyBtnSwitch(location);
    }

    public void clickToSwitchDomain() {
        loginPage.clickToSwitchDomain();
    }

    public void openPagewithParam(String url, String page, String paramUrl) {
        loginPage.openPagewithParam(url, page, paramUrl);
    }

    public void verifyShowElementsOnSignUpPage() {
        loginPage.verifyShowElementsOnSignUpPage();
    }

    public void clickToSignInPage() {
        loginPage.clickToSignInPage();
    }

    public void verifyShowElementsOnSignInPage() {
        loginPage.verifyShowElementsOnSignInPage();
    }

    public void verifyDomainInListShopChangedToHongKongDomain() {
        loginPage.verifyDomainInListShopChangedToHongKongDomain();
    }

    public void clickToUserMenuBar() {
        loginPage.clickToUserMenuBar();
    }

    public void verifyShowButtonSwitchDomainOnUserMenuBar(String location) {
        loginPage.verifyShowButtonSwitchDomainOnUserMenuBar(location);
    }

    public void clickToSwitchDomainOnUserMenu() {
        loginPage.clickToSwitchDomainOnUserMenu();
    }

    public void verifyElementsOnAffiliatePage() {
        loginPage.verifyElementsOnAffiliatePage();
    }

    public void verifyElementsOnOrderPage() {
        loginPage.verifyElementsOnOrderPage();
    }

    public void maximizeWindows() {
        loginPage.maximizeWindow();
    }

    public void selectLanguage(String language) {
        loginPage.selectLanguage(language);
    }

    public void verifyPackage(String domain) {
        loginPage.verifyPackage(domain);
    }

    public void changeDomainDashboard() {
        loginPage.changeDomainDashboard();
    }

    public void getPackageIdInHive() {
        String urlHive = LoadObject.getProperty("urlPackageGroup");
        loginPage.navigateToUrl(urlHive);
        loginPage.getPackageIdInHive();
    }

    public void openSignInPageInNewTab() {
        String baseurl = LoadObject.getProperty("webdriver.base.url");
        loginPage.openUrlInNewTab(baseurl + "/sign-in");
        switchToTheLastestTab();
    }

    public void chooseShopClose(String shopname) {
        loginPage.chooseShopClose(shopname);
        loginPage.waitForEverythingComplete();
    }

    public void submitForm() {
        if (loginPage.surveyFormIsExist()){
            loginPage.enterInputFieldWithLabel("Business name", "Open Commerce Group");
            loginPage.enterInputFieldWithLabel("Primary business email address", "shopbase@beeketing.net");
            loginPage.enterInputFieldWithLabel("First name", "OCG");
            loginPage.enterInputFieldWithLabel("Last name", "Test");
            loginPage.enterInputFieldWithLabel("Your company website (Optional)", "opencommercegroup.com");
            loginPage.enterInputFieldWithLabel("City", "Hanoi");
            loginPage.selectCountry("Select country", "Vietnam");
            loginPage.enterInputFieldWithLabel("ZIP/Postal Code", "10000");
            loginPage.enterPhoneNumber("Phone number", "0914123456");
            loginPage.checkCheckboxWithLabel("eCommerce app development");
            loginPage.checkCheckboxWithLabel("BigCommerce");
            loginPage.clickBtn("View your dashboard");
        }
    }

    public class LoginFromCommand {
        private String _username;
        private String _pwd;

        public LoginFromCommand(String username) {
            this._username = username;
        }

        public LoginFromCommand withPassword(String pwd) {
            this._pwd = pwd;
            return this;
        }

        public void login() {
            try {
                openSignInPage();
            } catch (JavascriptException exception) {
                loginPage.closeBrowser();
                openSignInPage();
            }
            enterEmail(_username);
            enterPassword(_pwd);
            clickBtnSignIn();
        }

    }

    @Step
    public void waitNavSidebar() {
        loginPage.waitForEverythingComplete();
        loginPage.waitForPageLoad();
        loginPage.waitNavSidebar();
    }

    @Step
    public void clickBtnSignIn() {
        loginPage.clickBtnSignIn();
        loginPage.waitForEverythingComplete(20);
        loginPage.waitForPageLoad();
//        loginPage.waitUntilInvisibleLoading(5);
//        loginPage.waitToShowSideBar();
    }

    @Step
    public void loginIntoSBaseDashboard(String url, String userName, String password) {
        loginPage.openUrl(url);
        loginPage.maximizeWindow();
        enterEmail(userName);
        enterPassword(password);
        clickBtnSignIn();
        loginPage.waitUntilInvisibleLoading(20);
    }

    @Step
    public void selectTheNewstShop() {
        loginPage.selectTheNewstShop();
        loginPage.waitForEverythingComplete();
    }
}

