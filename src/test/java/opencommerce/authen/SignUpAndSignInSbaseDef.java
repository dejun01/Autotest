package opencommerce.authen;

import com.google.gson.Gson;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.dashboard.pages.SignUpPage;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.home.steps.HomeSteps;
import com.opencommerce.shopbase.dashboard.online_store.navigation.steps.NavigationSteps;
import com.opencommerce.shopbase.dashboard.online_store.pages.steps.PagesSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ManageThemeSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.ShippingSteps;
import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.fluentlenium.core.annotation.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SignUpAndSignInSbaseDef {

    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    HiveStep hiveStep;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    ManageThemeSteps manageThemeSteps;
    @Steps
    PagesSteps pagesSteps;
    @Steps
    NavigationSteps navigationSteps;
    @Steps
    ShippingSteps shippingSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    AccountSteps accountSteps;
    @Steps
    HomeSteps homeSteps;

    @Page
    SignUpPage signUpPage;


    String email = LoadObject.getProperty("email");
    String passMail = LoadObject.getProperty("passmail");
    String userName = LoadObject.getProperty("user.name");
    String pass = LoadObject.getProperty("user.pwd");
    String webdriverBaseUrl = LoadObject.getProperty("webdriver.base.url");
    String domain = "";
    String shopname = "";
    Gson gson = new Gson();

    @Given("^User sign in to ShopBase dashboard with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_sign_in_to_ShopBase_dashboard_with_email_and_password_on(String _email, String _password) {
        loginSteps.openSignInPage();
        loginSteps.enterEmail(_email);

        if (_password.matches("@(.*)@")) {
            loginSteps.enterPassword(pass);
        } else {
            loginSteps.enterPassword(_password);
        }

        loginSteps.clickBtnSignIn();
    }

    @Then("^verify sign in status \"([^\"]*)\"$")
    public void verify_sign_in_status(String _status) {
        if (!_status.isEmpty()) {
            loginSteps.verifyErrorLoginMsg(_status);
        }
    }

    @Given("^verify sign up shopbase$")
    public void verify_sign_up_shopbase(List<List<String>> dataTable) throws IOException {
        loginSteps.openSignInPage();
        loginSteps.clickSignUp();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String sPassword = SessionData.getDataTbVal(dataTable, row, "Password");
            String sShopname = SessionData.getDataTbVal(dataTable, row, "Shopname");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");
            long currentTime = System.currentTimeMillis();
            if (sEmail.equalsIgnoreCase("@EMAIL@")) {
                sEmail = "Email" + currentTime + "@beeketing.com";
            }
            if (sShopname.equalsIgnoreCase("@SHOPNAME@"))
                sShopname = "ShopBase" + currentTime;
            loginSteps.enterEmail(sEmail);
            loginSteps.enterPassword(sPassword);
            loginSteps.enterShopname(sShopname);
            loginSteps.clickBtnSignUp();
            if (!sExpected.equalsIgnoreCase("success")) {
                loginSteps.verifyErrorMsg(sExpected);
                loginSteps.refreshPage();
            } else {
                loginSteps.closeBrowser();
                loginSteps.openSignInPage();
                loginSteps.clickSignUp();

            }

        }
    }

    @Given("^create a shop \"([^\"]*)\" with new account is \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void create_a_new_shop(String sShopname, String sEmail, String sPassword) throws IOException {

        long currentTime = System.currentTimeMillis();
        if (sEmail.matches("@(.*)@")) {
            sEmail = sEmail.replaceAll("@", "") + currentTime + "@yopmail.com";
        }
        if (sShopname.matches("@(.*)@"))
            sShopname = sShopname.replaceAll("@", "") + currentTime;
        loginSteps.openSignInPage();
        loginSteps.clickSignUp();
        loginSteps.enterEmail(sEmail);
        loginSteps.enterPassword(sPassword);
        loginSteps.enterShopname(sShopname);
        loginSteps.clickBtnSignUp();
        loginSteps.verifySurveyScreen();
    }

    @Given("^verify to add merchant address$")
    public void verify_to_add_merchant_address(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String sStoreCountry = SessionData.getDataTbVal(dataTable, row, "Store country");
            String sYourPersonalLocation = SessionData.getDataTbVal(dataTable, row, "Your personal location");
            String sPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String sBusiness = SessionData.getDataTbVal(dataTable, row, "Business");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");
            signUpSteps.getAPICountry();

            if (!sFirstName.equals("")) {
                signUpSteps.enterFirstName(sFirstName);
            }

            if (!sLastName.equals("")) {
                signUpSteps.enterLastName(sLastName);
            }

            signUpSteps.selectStoreCountry(sStoreCountry);
            signUpSteps.selectYourPersonalLocation(sYourPersonalLocation);
            signUpSteps.enterPhoneNumber(sPhoneNumber);
            if (!sBusiness.equals("")) {
                signUpSteps.enterEmailCustomer(sBusiness);
            }
            signUpSteps.clickBtnNext();
            signUpSteps.clickBtnStartNow();
            signUpSteps.clickSkip();
            signUpSteps.clickSkip();
            signUpSteps.clickNoIDontWantToImport();
            if (!sExpected.equalsIgnoreCase("success")) {
                loginSteps.verifyErrorMsg(sExpected);
                loginSteps.refreshPage();
            }
        }
    }

    @Given("^create a shop \"([^\"]*)\"$")
    public void create_a_shop(String shop) {
        if (shop.matches("@(.*)@"))
            shop = shop.replaceAll("@", "") + System.currentTimeMillis();
        signUpSteps.logInfor("Shop: " + shop);
        if (signUpSteps.isDashboardHomepage()) {
            signUpSteps.clickSelectAnotherShop();
            signUpSteps.clickBtnAddANewShop();
            signUpSteps.enterShopName(shop);
            signUpSteps.clickBtnCreate();
        } else {
            signUpSteps.clickBtnAddANewShop();
            signUpSteps.enterShopName(shop);
            signUpSteps.clickBtnCreate();
        }
        signUpSteps.waitUntilInvisibleLoading();
    }

    @And("^create a new shop$")
    public void createANewShopAndVerifyCaptchaIsEnable(List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shop = SessionData.getDataTbVal(dataTable, row, "shop");
            String status = SessionData.getDataTbVal(dataTable, row, "status");
            if (shop.matches("@(.*)@")){
                shop = shop.replaceAll("@", "") + System.currentTimeMillis();
            }
            signUpPage.openShopList();
            signUpSteps.clickBtnAddANewShop();
            signUpSteps.enterShopName(shop);
            signUpSteps.clickBtnCreate();
            switch (status){
                case "true":
                    signUpPage.verifyCaptchaIsEnable(true);
                    break;
                case "false":
                    signUpPage.verifyCaptchaIsEnable(false);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + status);
            }
        }
    }

    @Given("^user signup ShopBase with shop domain \"([^\"]*)\", password \"([^\"]*)\" and email$")
    public void user_signup_ShopBase_with_shop_domain_password_and_email(String sDomain, String sPassword, String sEmail) {
        loginSteps.openSignInPage();
        loginSteps.clickSignUp();
        loginSteps.enterPassword(sPassword);
        loginSteps.enterShopname(sDomain);
        List<String> emails = new ArrayList<String>(Arrays.asList(sEmail.split(", ")));
        for (int i = 0; i < emails.size(); i++) {
            System.out.println(i);
            String email = "disposable@" + emails.get(i);
            loginSteps.enterEmail(email);
            loginSteps.clickBtnSignUp();
            loginSteps.verifyErrorMsg("Please use the regular email address.");
        }
    }

    @Given("^user signup ShopBase with shop domain \"([^\"]*)\", password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void signUpShopbaseWithExistingAccount(String sDomain, String sPassword, String sEmail) throws IOException {
        loginSteps.openSignInPage();
        loginSteps.clickSignUp();
        loginSteps.enterPassword(sPassword);
        loginSteps.enterShopname(sDomain);
        loginSteps.enterEmail(sEmail);
        loginSteps.clickBtnSignUp();
        signUpPage.verifyCaptchaIsEnable(true);
    }

    @And("input store information as {string}")
    public void inputStoreInformationAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Store country");
            String location = SessionData.getDataTbVal(dataTable, row, "Your personal location");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String social = SessionData.getDataTbVal(dataTable, row, "Social profile");
            signUpSteps.clickBtnStartNow();
            signUpSteps.selectStoreCountry(country);
            signUpSteps.selectYourPersonalLocation(location);
            signUpSteps.enterPhoneNumber(phone);
            if (!social.equals("")) {
                signUpSteps.enterEmailCustomer(social);
            }
            signUpSteps.clickBtnNext();
            signUpSteps.clickBtnNext();
        }

    }

    @And("select store type as {string}")
    public void selectStoreTypeAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            String type = SessionData.getDataTbVal(dataTable, row, "Store type");
            String categoryNiche = SessionData.getDataTbVal(dataTable, row, "Category/Niche");

            //choose business type
            String bsnType[] = businessType.split(">");
            signUpSteps.chooseBusinessType(bsnType[0]);

            if (bsnType[0].equals("Print On Demand") || bsnType[0].equals("Dropshipping")) {
                if (!bsnType[1].isEmpty())
                    signUpSteps.choosePlatform(bsnType[1]);
                if (!type.isEmpty())
                    signUpSteps.chooseType(type);
            }

            //choose category or niche
            if (!categoryNiche.equals("")) {
                signUpSteps.selectCategoryNiche(categoryNiche);
                signUpSteps.clickBtnNext();
            } else {
                signUpSteps.clickBtnNoThanks();
            }
        }
    }


    @And("customize store as {string}")
    public void customizeStoreAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String importContent = SessionData.getDataTbVal(dataTable, row, "Import content");
            String storeImport = SessionData.getDataTbVal(dataTable, row, "Store import");
            String answer = SessionData.getDataTbVal(dataTable, row, "Answer question");
            String customStyle = SessionData.getDataTbVal(dataTable, row, "Customize style");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String typeProduct = SessionData.getDataTbVal(dataTable, row, "Type product");
            if (type.equals("ShopBase")) {

                // survey import content
                if (importContent.equals("Import")) {
                    signUpSteps.chooseStoreImport(storeImport);
                    signUpSteps.clickBtnImport();
                } else {
                    signUpSteps.clickBtnNoThanks();
                }
            }

            if (signUpSteps.isExistQuestionPage()) {
                // survey questions
                    HashMap<String,String> items = signUpSteps.getContentAnswerOnBoarding();
                    signUpSteps.selectAnswers(items);
                    signUpSteps.clickTakeMeToMyStore();
            }

            //survey customize style
            if (customStyle.equals("Yes")) {
                if (!color.equals("")) {
                    signUpSteps.inputColor(color);
                }
                if (!font.equals("")) {
                    signUpSteps.chooseFont(font);
                }
                signUpSteps.clickTakeMeToMyStore();
            }

            //verify create shop success
            signUpSteps.verifyCreatedShopSuccess();
//            commonSteps.switchToTab(typeProduct);
//            signUpSteps.clickBtnUseSample();
        }
    }

    @Then("verify store information as {string}")
    public void verifyStoreInformationAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String currency = SessionData.getDataTbVal(dataTable, row, "Currency");
            settingSteps.chooseTheSection("General");
            settingSteps.verifyPhone(phone);
            settingSteps.verifyCountry(country);
            settingSteps.verifyCurrency(currency);
        }

    }

    @And("verify data clone from template as {string}")
    public void verifyDataCloneFromTemplate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String shopCr = SessionData.getDataTbVal(dataTable, row, "Shop current");
            String store = SessionData.getDataTbVal(dataTable, row, "Store");
            String shopTem = SessionData.getDataTbVal(dataTable, row, "Store template");
            String userNameTem = SessionData.getDataTbVal(dataTable, row, "User name template");
            String passTem = SessionData.getDataTbVal(dataTable, row, "Pass template");
            String accTokenShopCr = signUpSteps.getAccessToken(userName, pass, shopCr);
            String accTokenShopTem = signUpSteps.getAccessToken(userNameTem, passTem, shopTem);
            if (store.equals("Template")) {
                if (type.equals("ShopBase")) {
                    verifySampleProduct(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
                } else {
                    verifySampleCampaign(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
                }
            } else {
                commonSteps.switchToTab("Products");
                productListSteps.verifyProductStoreImport();
            }
            verifyCollections(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
            verifyThemes(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
            verifyPages(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
            verifyNavigation(accTokenShopCr, shopCr, accTokenShopTem, shopTem);
        }
    }

    public void verifySampleProduct(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        List<String> productCr = productListSteps.getListProductByAPI(accTokenShopCr, shopCr);
        List<String> productTem = productListSteps.getListProductByAPI(accTokenShopTem, shopTem);
        signUpSteps.verifySampleData(productCr, productTem);
    }

    public void verifySampleCampaign(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        List<String> campaignCr = productListSteps.getListCampaignByAPI(accTokenShopCr, shopCr);
        List<String> campaignTem = productListSteps.getListCampaignByAPI(accTokenShopTem, shopTem);
        signUpSteps.verifySampleData(campaignCr, campaignTem);
    }

    public void verifyPages(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        List<String> pagesCr = pagesSteps.getListPages(accTokenShopCr, shopCr);
        List<String> pagesTem = pagesSteps.getListPages(accTokenShopTem, shopTem);
        signUpSteps.verifySampleData(pagesCr, pagesTem);
    }

    public void verifyNavigation(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        List<String> navigationCr = navigationSteps.getNavigation(accTokenShopCr, shopCr);
        List<String> navigationTem = navigationSteps.getNavigation(accTokenShopTem, shopTem);
        signUpSteps.verifySampleData(navigationCr, navigationTem);
    }

    public void verifyCollections(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        String collectionCr = gson.toJson(collectionListSteps.getCollectionAndNormalize(accTokenShopCr, shopCr));
        String collectionTem = gson.toJson(collectionListSteps.getCollectionAndNormalize(accTokenShopTem, shopTem));
        signUpSteps.verifyShopData(collectionCr, collectionTem);
    }

    public void verifyThemes(String accTokenShopCr, String shopCr, String accTokenShopTem, String shopTem) {
        String themeCr = gson.toJson(manageThemeSteps.getThemesAndNormalize(accTokenShopCr, shopCr));
        String themeTem = gson.toJson(manageThemeSteps.getThemesAndNormalize(accTokenShopTem, shopTem));
        signUpSteps.verifyShopData(themeCr, themeTem);
    }

    @And("verify show theme default")
    public void verifyShowThemeDefault() {
        commonSteps.switchToTab("Online Store");
        manageThemeSteps.verifyShowTheme();
    }

    @Given("login hive admin")
    public void loginHiveAdmin() {
        hiveStep.goToLoginHivePage();
        hiveStep.loginWithGG();
        hiveStep.emailGG(email);
        hiveStep.PassEmailGG(passMail);
        hiveStep.waitABit(5000);
    }

    @And("verify default shipping")
    public void verifyDefaultShipping() {
        commonSteps.switchToTab("Settings");
        settingSteps.chooseTheSection("Shipping");
        shippingSteps.verifyShipping();
    }

    @And("choose shop as {string}")
    public void chooseShopAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String shopName = SessionData.getDataTbVal(dataTable, row, "Shop name");
            if (shopName.equalsIgnoreCase("shop"))
                shopName = LoadObject.getProperty("shop");
            loginSteps.chooseShop(shopName);
            shopname = shopName;
        }
    }

    @When("open page & verify show button switch to HongKong domain")
    public void openPageVerifyShowButtonSwitchToHongKongDomain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            String paramUrl = SessionData.getDataTbVal(dataTable, row, "Param url");
            boolean isEnabledSwitchDomain = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is enabled switch domain"));
            loginSteps.openPagewithParam(webdriverBaseUrl, page, paramUrl);
            loginStep.maximizeWindows();
            loginSteps.verifyEnabledSwitchDomain(isEnabledSwitchDomain);

        }
    }

    @And("get current domain url and verify domain")
    public void getCurrentDomainUrlAndVerifyDomain(List<List<String>> dataTable) {
        domain = loginSteps.getDomainLocation();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String domainLocation = SessionData.getDataTbVal(dataTable, row, "Domain location");
            switch (domainLocation) {
                case "US":
                    loginSteps.verifyBtnSwitch("HongKong");
                    break;
                case "HongKong":
//                    loginSteps.verifyBtnSwitch("US");
                    assertThat(domain).contains("shopbase.net");
            }
        }
    }

    @And("click to switch domain")
    public void clickToSwitchDomain() {
        loginSteps.clickToSwitchDomain();
    }

    @And("verify show elements on sign up page")
    public void verifyShowElementsOnSignUpPage() {
        loginSteps.verifyShowElementsOnSignUpPage();
    }

    @And("click to sign in from sign up page")
    public void clickToSignInPage() {
        loginSteps.clickToSignInPage();
    }

    @And("verify show elements on sign in page")
    public void verifyShowElementsOnSignInPage() {
        loginSteps.verifyShowElementsOnSignInPage();
    }

    @And("^login to dashboard")
    public void loginToDashboard() {
        loginSteps.openSignInPage();
        loginStep.enterEmail(userName);
        loginStep.enterPassword(pass);
        loginStep.clickBtnSignIn();
    }

    @And("verify domain in list shop changed to HongKong domain")
    public void verifyDomainInListShopChangedToHongKongDomain() {
        loginSteps.verifyDomainInListShopChangedToHongKongDomain();
    }

    @Then("^verify show button switch domain \"([^\"]*)\" on user menu bar$")
    public void verifyShowButtonSwitchDomainOnUserMenuBar(String location) {
        loginSteps.clickToUserMenuBar();
        loginSteps.verifyShowButtonSwitchDomainOnUserMenuBar(location);
    }

    @And("click to switch domain on user menu bar")
    public void clickToSwitchDomainOnUserMenuBar() {
        loginSteps.clickToSwitchDomainOnUserMenu();
    }

    @And("verify elements on affiliate page")
    public void verifyElementsOnAffiliatePage() {
        loginSteps.verifyElementsOnAffiliatePage();
    }

    @And("verify elements on order page")
    public void verifyElementsOnOrderPage() {
        loginSteps.verifyElementsOnOrderPage();
    }

    @Then("^create a shop with new account and password with domain$")
    public void createAShopWithNewAccountIsAndPasswordIsWithDomain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String shopName = SessionData.getDataTbVal(dataTable, row, "Shop");
            String getEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String getPassword = SessionData.getDataTbVal(dataTable, row, "Password");
            String getDomain = SessionData.getDataTbVal(dataTable, row, "Domain");
            long currentTime = System.currentTimeMillis();
            String url_account = LoadObject.getProperty("webdriver.base.url");
            loginSteps.openURL(url_account);
            if (getDomain.matches("China")) {
                loginSteps.changeDomainDashboard();
            }
            if (getEmail.matches("@(.*)@")) {
                getEmail = getEmail.replaceAll("@", "") + currentTime + "@beeketing.net";
            } else if (getEmail.matches("chinaemail")) {
                getEmail = getEmail + currentTime + "@qq.com";
            }
            if (shopName.matches("@(.*)@"))
                shopName = shopName.replaceAll("@", "") + currentTime;
            loginSteps.clickSignUp();
            loginSteps.enterEmail(getEmail);
            loginSteps.enterPassword(getPassword);
            loginSteps.enterShopname(shopName);
            loginSteps.clickBtnSignUp();
        }
    }

    @Then("^verify package \"([^\"]*)\"$")
    public void verifyPackage(String domain) {
        loginSteps.waitABit(20000);
        loginSteps.verifyPackage(domain);
    }

    @And("get package id in hive")
    public void getPackageIdInHive() {
        loginSteps.getPackageIdInHive();
    }

    @And("check not display offer when user is being free trial")
    public void checkNotDisplayOfferWhenUserIsBeingFreeTrial() {
        accountSteps.closeStore();
        accountSteps.clickSwitchToAnotherShopbutton();
        signUpSteps.verifyDisplayOffer(shopname, false);
        loginSteps.chooseShopClose(shopname);
        accountSteps.reopenStore();
    }

    @And("check display offer when close store")
    public void checkDisplayOfferWhenCloseStore() {
        homeSteps.selectPlanInTheHomePage();
        accountSteps.clickBTUpgradePlan();
        accountSteps.choosePlan("Basic Base");
        accountSteps.clickBTStartPlan();
        accountSteps.clickAccountInTheSelectPlan();
        accountSteps.closeStore();
        accountSteps.clickSwitchToAnotherShopbutton();
        signUpSteps.verifyDisplayOffer(shopname, true);
    }
}

