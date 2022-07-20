package opencommerce.common;

import com.opencommerce.shopbase.common.steps.CommonSteps;

import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static junit.framework.TestCase.fail;

public class CommonDef {
    String url = LoadObject.getProperty("url");
    @Steps
    CommonSteps commonSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    HiveStep hiveStep;


    private String windowID;
    String shop = LoadObject.getProperty("shop");
    String username = LoadObject.getProperty("user.name");
    String userpwd = LoadObject.getProperty("user.pwd");
    String url_account = LoadObject.getProperty("webdriver.base.url");
    String staffUserName = LoadObject.getProperty("user.name.staff");

    @When("^user navigate to \"([^\"]*)\" screen$")
    public void user_navigate_to_screen(String _nameTab) {
        String _tabs[] = _nameTab.split(">");
        int level = _tabs.length;
        for (int i = 0; i < level; i++) {
            commonSteps.switchToTab(_tabs[i]);
        }
        commonSteps.waitUntilInvisibleLoading(5);
        commonSteps.closePopup();
    }

    @When("^user navigate to \"([^\"]*)\" screen on mobile$")
    public void user_navigate_to_tab_on_mobile(String _nameTab) {
        commonSteps.openMenuOnMobile();
        user_navigate_to_screen(_nameTab);
    }

    @Given("user navigate to {string} screen by url")
    public void user_navigate_to_screen_by_url(String screen) {
        String domain = LoadObject.getProperty("shop");
        String url = "https://" + domain + "/admin/" + screen;
        commonSteps.navigateByUrl(url);
    }

    @When("^Switch to the first tab$")
    public void switchToTheFirstTab() {
        commonSteps.switchToTheFirstTab();
    }


    @When("^close browser$")
    public void close_browser() {
        commonSteps.clearData();
        commonSteps.closeBrowser();
    }

    @When("^quit browser$")
    public void quite_browser() {
        commonSteps.clearData();
        commonSteps.quitBrowser();
    }

    @Given("^Description: \"([^\"]*)\"$")
    public void testcase_s_description_is(String text) {
        commonSteps.logInfor(text);
    }

    @Then("^open page \"([^\"]*)\"$")
    public void openPage(String sPage) {
        productSteps.openPage(sPage);
    }

    @Then("^compare current element with image as \"([^\"]*)\"$")
    public void compareCurrentElementWithImage(String dataKey, List<List<String>> dataTable) throws IOException {
        commonSteps.waitABit(2000);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String imageName = SessionData.getDataTbVal(dataTable, row, "Expected UI");
            String isAllScreen = SessionData.getDataTbVal(dataTable, row, "Is all screen");
            float expectedPercent = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Percent"));
            boolean isFull = false;
            if (!isAllScreen.isEmpty()) {
                isFull = Boolean.parseBoolean(isAllScreen);
            }
            commonSteps.setViewPort(1920, 1080);
            BufferedImage fileOutPut = commonSteps.takesScreenshotByElement(element, isFull);
            commonSteps.compareImage(imageName, fileOutPut, expectedPercent);

        }
    }

    @And("^verify UI by css as \"([^\"]*)\"$")
    public void verifyUIByCssAs(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String cssValue = SessionData.getDataTbVal(dataTable, row, "CSS value");
            int countElement = commonSteps.countElementByXpath(element);

            for (int i = 1; i <= countElement; i++) {
                String xpath = "(" + element + ")[" + i + "]";
                commonSteps.verifyElementVisible(xpath);
                commonSteps.logInfor(description + "with xpath: " + xpath + " with element " + cssValue);
                commonSteps.verifyCssValues(xpath, cssValue);
            }
        }
    }

    @Given("^open the url \"([^\"]*)\" in a new tab$")
    public void openTheUrlInANewTab(String url) {
        windowID = commonSteps.getWindowID();
        commonSteps.openUrlInANewTab(url);
    }

    @Given("^open storefront (.*) on a new tab$")
    public void openShopInNewTab(String shopType) {
        String shopDomain = getShopDomain(shopType);
        openTheUrlInANewTab(shopDomain);
    }

    @And("^close all tabs without original tab$")
    public void closeAllTabsWithoutOriginalTab() {
        commonSteps.closeAllTabWithoutOriginalTab(windowID);
    }

    @Given("^open url \"([^\"]*)\"$")
    public void openUrl(String url) {
        commonSteps.openUrl(url);
    }


    @Given("^(.*) login to (.*) dashboard$")
    public void login_shopbase_dashboard(String actor, String shopType) {
        String shopDomain = getShopDomain(shopType);
        String username = LoadObject.getProperty(actor + ".name");
        String pwd = LoadObject.getProperty(actor + ".pwd");

        if (url_account.contains(".net.cn")) {
            shopDomain = shopDomain.replace(".myshopbase.net", ".shopbase.net.cn").replace(".onshopbase.com", ".shopbase.net.cn");
        }

        loginStep.logInfor(shopDomain + " with username = " + username + " and pwd = " + pwd);
        loginStep.loginShopbase(shopDomain, username, pwd);

        shop = shopDomain;
    }

    @Given("^(.*) open list of shops")
    public void open_list_of_shops(String actor) {
        String username = LoadObject.getProperty(actor + ".name");
        String pwd = LoadObject.getProperty(actor + ".pwd");
        loginStep.openSignInPage();
        loginStep.enterEmail(username);
        loginStep.enterPassword(pwd);
        loginStep.signInToListStore();
    }

    private String getShopDomain(String shopType) {
        String shopDomain = LoadObject.getProperty("shop");
        if (!shopType.equals("shopbase")) {
            shopDomain = LoadObject.getProperty(shopType);
        }
        return shopDomain;
    }

    @Given("^(.*) login to (.*) dashboard by API$")
    public void login_shopbase_dashboardByAPI(String actor, String shopType) {
        String shopDomain = getShopDomain(shopType);
        String scope = "JTVCJTIyaG9tZSUyMiwlMjJyZWFkX3Byb2R1Y3RzJTIyLCUyMndyaXRlX3Byb2R1Y3RzJTIyLCUyMnJlYWRfcHJvZHVjdF9saXN0aW5ncyUyMiwlMjJyZWFkX2ludmVudG9yeSUyMiwlMjJ3cml0ZV9pbnZlbnRvcnklMjIsJTIycmVhZF9mdWxmaWxsbWVudHMlMjIsJTIycmVhZF9jdXN0b21lcnMlMjIsJTIyd3JpdGVfY3VzdG9tZXJzJTIyLCUyMmRhc2hib2FyZHMlMjIsJTIycmVhZF9hbmFseXRpY3MlMjIsJTIycmVhZF9zYWxlcyUyMiwlMjJyZWFkX21hcmtldGluZ19ldmVudHMlMjIsJTIyd3JpdGVfbWFya2V0aW5nX2V2ZW50cyUyMiwlMjJhcHBzJTIyLCUyMnNldHRpbmdzJTIyLCUyMnJlYWRfdGhlbWVzJTIyLCUyMnJlYWRfcGF5bWVudF9wcm92aWRlcnMlMjIsJTIyd3JpdGVfcGF5bWVudF9wcm92aWRlcnMlMjIsJTIyd3JpdGVfdGhlbWVzJTIyLCUyMnJlYWRfY29udGVudCUyMiwlMjJyZWFkX3BhZ2VzJTIyLCUyMndyaXRlX3BhZ2VzJTIyLCUyMndyaXRlX2NvbnRlbnQlMjIsJTIycmVhZF9wcmVmZXJlbmNlcyUyMiwlMjJ3cml0ZV9wcmVmZXJlbmNlcyUyMiwlMjJkb21haW5zJTIyLCUyMnJlYWRfb3JkZXJzJTIyLCUyMnJlYWRfYWxsX29yZGVycyUyMiwlMjJleHBvcnRfb3JkZXJzJTIyLCUyMnZpZXdfYXNzaWduZWRfc3RhZmZzJTIyJTVE";
        String username = LoadObject.getProperty(actor + ".name");
        String pwd = LoadObject.getProperty(actor + ".pwd");
        if (pwd.isEmpty()) {
            pwd = LoadObject.getProperty("user.pwd");
        }
        System.out.println("token " + accesstoken);
        if ((shop != shopDomain && actor != userRole) || (accesstoken.isEmpty())) {
            accesstoken = loginStep.getAccessTokenShopBase(username, pwd, shopDomain);
        }
        System.out.println("token " + accesstoken);

        String url = "https://" + shopDomain + "/admin?access_token=" + accesstoken + "&scopes=" + scope;

        if (shopDomain.contains("onshopbase.com")) {
            isProductionEnvironment = true;
        }
        System.out.println("url: " + url);
        loginStep.openURL(url);
        loginStep.closeLiveChat();
        shop = shopDomain;
        userRole = actor;
    }

    @Then("redirect to (.*) dashboard")
    public void redirect_to_shop_dashboard(String shopType) {
        String shopDomain = getShopDomain(shopType);
        loginStep.openShop(shopDomain + "/admin");
        loginStep.waitToLoadPage();
    }

    @Given("^(.*) login to email and open email with subject contains (.*)$")
    public void login_email(String actor, String shop) {
        String emailStaff = LoadObject.getProperty(actor + ".name");
        String[] subject = getShopDomain(shop).split("\\.");
        productListSteps.verifyReceivedEmail(emailStaff, subject[0]);
    }

    @Given("close live chat")
    public void closeLiveChat() {
        loginStep.closeLiveChat();
    }

    @Given("^open (.*) on storefront$")
    public void open_shopbase(String shopType) {
        shopDomain = getShopDomain(shopType);
        loginStep.openShop(shopDomain);
        loginStep.waitABit(10000);
    }

    @And("^back to the previous screen$")
    public void backToThePreviousScreen() {
        commonSteps.backToThePreviousScreen();
    }

    @And("^clear all data$")
    public void clearAllDataWhenCheckout() {
        commonSteps.clearData();
    }


    @And("^Select another shop \"([^\"]*)\"$")
    public void selectAnotherShop(String nameShop) {
        commonSteps.selectAnotherShop();
        if (!nameShop.isEmpty()) {
            loginStep.chooseShop(nameShop);
        }
    }

    @And("logout to shopbase dashboard")
    public void logoutToShopbaseDashboard() {
        commonSteps.logout();
    }

    @Then("click on \"([^\"]*)\" button")
    public void clickOnButton(String btn) {
        commonSteps.clickOnBtn(btn);
    }

    @Then("user navigate to screen \"([^\"]*)\" by API")
    public void userNavigateToScreenByAPI(String screen) {
        String url = "";

        accesstoken = loginStep.getAccessTokenShopBase(shopDomain);
        url = "https://" + shopDomain + screen + "?access_token=" + accesstoken;

        commonSteps.openUrl(url);
        commonSteps.waitUntilInvisibleLoading(10);
        commonSteps.closeOnboarding();
    }

    @And("verify text {string} is displayed")
    public void waitForTextToAppear(String text) {
        commonSteps.waitForTextToAppear(text);
    }

    @Given("clear shop data of shopid {string}")
    public void clear_shop_data_of_shopid(String shopId) {
        if (shopId.contains("@")) {
            shopId = LoadObject.getProperty("shop_id");
        }
        commonSteps.switchToTabsInHive(url);
        hiveStep.enterShopId(shopId);
        hiveStep.clickBtnClear();
        hiveStep.verifyClearSuccces();
    }

    @Given("^login to shopbase dashboard$")
    public void login_shopbase_dashboard() {
        loginStep.openURL(url_account);
        loginStep.openSignInPage();
        loginStep.enterEmail(username);
        loginStep.enterPassword(userpwd);
        loginStep.clickBtnSignIn();
    }

    @And("^(.*) login to template store$")
    public void loginTemplate(String actor) {
        String username = LoadObject.getProperty(actor + ".name");
        String pwd = LoadObject.getProperty(actor + ".pwd");
        loginStep.logInfor(" username = " + username + " and pwd = " + pwd);
        loginStep.loginShopbase(username, pwd);
    }

    @And("^select shop (.*)$")
    public void loginToGetTemplate(String shopname) {
        String shop = LoadObject.getProperty(shopname);
        loginStep.chooseShop(shop);
    }
}
