package com.opencommerce.shopbase.dashboard.dashboard.pages;

//import com.opencommerce.shopbase.dashboard.apps.analytics.steps.OrderReferrerInfoSteps;

import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.junit.ComparisonFailure;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class SignUpPage extends SBasePageObject {
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
    public String gapi_url;
    public String accessToken;
    public String captchaToken;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void init() {
        gapi_url = LoadObject.getProperty("gapi.url");
        accessToken = getAccessTokenShopBase();
        captchaToken = "eyJzZXJ2aWNlIjoiZ2VldGVzdCJ9Cgo=";
    }

    public void verifyExistPageDefault(String title) {
        String xpath = "//td[@class='page-description']//div[@class='s-flex-item']//a[normalize-space()='" + title + "']";
        waitElementToBeVisible(xpath);
        assertThat(getText(xpath)).isEqualTo(title);
    }

    public void clickStoreCountry() {
        String xpath = "//input[@placeholder='Select Store country']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathListStoreCountry = "(//div[@class='s-select-searchable__item-list'])[1]";
        waitABit(1000);
        String value = XH(xpathListStoreCountry).getTextValue();
    }

    public void clickYourPersonalLocation() {
        String xpath = "//input[@placeholder='Select personal location']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathListYourPersonalLocation = "(//div[@class='s-select-searchable__item-list'])[2]";
        waitABit(1000);
        String value = XH(xpathListYourPersonalLocation).getTextValue();
        System.out.println("List your personal location: " + value);
    }

    public void selectStoreCountry(String sStoreCountry) {
        storeCountry = sStoreCountry;
        String xPathBusiness = "//input[@placeholder='facebook.com/myusername']";
        String xPathStoreCountry = "//input[@placeholder='Select Store country']";
        inputSlowly(xPathStoreCountry, sStoreCountry);
        waitABit(1000);
        System.out.println("Value Store country: " + XH(xPathStoreCountry).getValue());
        waitABit(1000);
    }

    public void selectYourPersonalLocation(String sYourPersonalLocation) {
        yourPersonalLocation = sYourPersonalLocation;
        String xPathBusiness = "//input[@placeholder='facebook.com/myusername']";
        String xPathYourPersonalLocation = "//input[@placeholder='Select personal location']";
        inputSlowly(xPathYourPersonalLocation, sYourPersonalLocation);
        waitABit(1000);
//        clickOnElement(xPathBusiness);
//        waitABit(1000);
        System.out.println("Value Your personal location: " + XH(xPathYourPersonalLocation).getValue());
        waitABit(1000);
    }

    public void enterFirstName(String sFirstName) {
        String xPath = "//div[child::*[normalize-space()='First name']]/following-sibling::div//input[@placeholder='Enter your name']";
        firstName = sFirstName;
        if (isElementVisible(xPath, 2)) {
            waitTypeAndEnter(xPath, sFirstName);
        }
    }

    public void enterLastName(String sLastName) {
        String xPath = "//div[child::*[normalize-space()='Last name']]/following-sibling::div//input[@placeholder='Enter your last name']";
        lastName = sLastName;
        if (isElementVisible(xPath, 2)) {
            waitTypeAndEnter(xPath, sLastName);
        }
    }

    public boolean isDashboardHomepage() {
        String xPath = "//*[@class='nav nav-sidebar']";
        return isElementVisible(xPath, 10);
    }

    public void clickSelectAnotherShop() {
        String xPath = "//*[contains(@class,'avatar')]";
        clickOnElement(xPath);
        String xPathOther = "//div[normalize-space()='Select another shop']";
        clickOnElement(xPathOther);
    }

    public void enterAddress(String sAddress) {
        address = sAddress;
        enterInputFieldWithLabel("Address", sAddress);
    }

    public void enterCity(String sCity) {
        city = sCity;
        enterInputFieldWithLabel("City", sCity);
    }

    public void enterZipPostalCode(String sZipCode) {
        zipCode = sZipCode;
        enterInputFieldWithLabel("ZIP/Postal Code", sZipCode);
    }

    public void enterPhoneNumber(String sPhoneNumber) {
        phoneNumber = sPhoneNumber;
        String xpath = "//input[@id='phone-number']";
        XH(xpath).type(sPhoneNumber);
    }

    public void getAPICountry() {
        String url = "https://api.shopbase.com/admin/countries";
        JsonPath jp = getAPI(url);
        ArrayList results = getDataByKey(jp, "countries");
        int size = results.size();
        System.out.println("Size: " + size);
        assertThat(size).isEqualTo(242);
    }

    public void clickBtnEnterMyShop(int currentRetryTime) {
        clickBtn("Enter my shop");
        String xPath = "//div[@class='s-form-item__error']";
        try {
            verifyElementPresent(xPath, false);
            verifyElementVisible(xPath, false);
        } catch (ComparisonFailure e) {
            System.out.println("Fail: " + e.getClass().getSimpleName() + " " + e.getMessage());
            if (currentRetryTime < MAX_RETRY_TIME) {
                refreshPage();
                clickBtn("Skip");
                enterFirstName(firstName);
                enterLastName(lastName);
                if (!storeCountry.isEmpty()) {
                    clickStoreCountry();
                    selectStoreCountry(storeCountry);
                }

                if (!yourPersonalLocation.isEmpty()) {
                    clickYourPersonalLocation();
                    selectYourPersonalLocation(yourPersonalLocation);
                }

                enterPhoneNumber(phoneNumber);
                clickBtnEnterMyShop(currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }
    }

    public void clickStateProvince() {
        waitElementToBeVisible("//input[@placeholder='Select State']|//input[@placeholder='Select Province']");
        clickOnElement("//input[@placeholder='Select State']|//input[@placeholder='Select Province']");
        String xPath = "//div[@class='s-select-searchable__item-list']";
        waitABit(1000);
        String value = XH(xPath).getTextValue();
        System.out.println("List value state/province: " + value);
    }

    public void selectStateProvince(String sStateOrProvince) {
        stateProvince = sStateOrProvince;
        String xPathCity = "//input[@placeholder='Enter city']";
        String xPathStateProvince = "//input[@placeholder='Select State']|//input[@placeholder='Select Province']";
        waitTypeOnElement(xPathStateProvince, sStateOrProvince);
        waitABit(1000);
        clickOnElement(xPathCity);
        waitABit(1000);
        System.out.println("Value state/province: " + XH(xPathStateProvince).getValue());
        waitABit(1000);
    }

    //Access to Sign Up screen
    public void openSignInPage(String url) {
        openBrowser(url);
    }

    public void clickOnSignUpHyperLink() {
        String xPath = "//a/span[contains(text(),'Sign up')]/..";
        clickOnElement(xPath);
    }

    //Sign up screen - input Email
    public void inputEmail(String email) {
        String xPath = "//input[@id='email']";
        waitClearAndType(xPath, email);
    }

    //Sign up screen - input password
    public void inputPassword(String password) {
        String xPath = "//input[@id='password']";
        waitClearAndType(xPath, password);
    }

    //Sign up screen - input shop name
    public void inputShopName(String shopName) {
        String xPath = "//input[@id='shop_name']";
        waitClearAndType(xPath, shopName);
    }

    //Sign up screen - Click on Sign Up button
    public void clickOnSignUpbtn() {
        String xPath = "//button[@type='submit']//*[text()='Sign up']";
        clickOnElement(xPath);
    }

    //"Let us help you build your own shop" screen - Click on Skip button
    public void clickOnSkipBtn() {
        waitForEverythingComplete();
        String xPath = "//span[contains(text(),'Skip')]";
        if (isElementVisible(xPath, 5))
            clickOnElement(xPath);
    }

    //"Add you contact information ..." screen - click on "Enter My shop" button
    public void clickOnEnterMyShopBtn() {
        String xPath = "//button//span[text()='Enter my shop']/..";
        if (isElementVisible(xPath, 5))
            clickOnElement(xPath);
    }

    public void inputFirstName(String sFirstName) {
        String xPath = "//input[@id='first-name']";
        firstName = sFirstName;
        if (isElementVisible(xPath, 2)) {
            waitTypeAndEnter(xPath, sFirstName);
        }
    }

    public void inputLastName(String sLastName) {
        String xPath = "//input[@id='last-name']";
        lastName = sLastName;
        if (isElementVisible(xPath, 2)) {
            waitTypeAndEnter(xPath, sLastName);
        }
    }

    //Enter Store country in "Add you contact information ..." screen
    public void inputStoreCountry(String _country) {
        String xPath = "//input[@placeholder='Select Store country']";
        String xPathDropdown = "//input[@placeholder='Select Store country']/../following-sibling::*//div[contains(text(),'" + _country + "')]";
        waitClearAndType(xPath, _country);
        if (isElementVisible(xPathDropdown, 2)) {
            clickOnElement(xPathDropdown);
        }
    }

    //Enter personal location in "Add you contact information ..." screen
    public void inputPersonalLocation(String _country) {
        String xPath = "//input[@placeholder='Select personal location']";
        String xPathDropdown = "//input[@placeholder='Select personal location']/../following-sibling::*//div[contains(text(),'" + _country + "')]";
        waitClearAndType(xPath, _country);
        if (isElementVisible(xPathDropdown, 2)) {
            clickOnElement(xPathDropdown);
        }
    }

    //Enter phone number in in "Add you contact information ..." screen
    public void inputPhoneNumber(String phoneNumberP1, String phoneNumberP2) {
        String xPathNum1 = "(//div/label[text()='Phone number']/../following-sibling::*//input)[1]";
        String xPathNum1Dropdown = xPathNum1 + "/../following-sibling::*//div[contains(text(),'" + phoneNumberP1 + "')]";
        waitClearAndType(xPathNum1, phoneNumberP1);
        if (isElementVisible(xPathNum1Dropdown, 2)) {
            clickOnElement(xPathNum1Dropdown);
        }
        String xPathNum2 = "//input[@id='phone-number']";
        waitClearAndType(xPathNum2, phoneNumberP2);
    }

    public void clickOnNextBtn() {
        String xPath = "//span[contains(text(),'Next')]";
        if (isElementVisible(xPath, 5))
            clickOnElement(xPath);
    }

    public void clickOnStartNowBtn() {
        String xPath = "//span[contains(text(),'Start now')]";
        if (isElementVisible(xPath, 5))
            clickOnElement(xPath);
    }

    public void verifyCreatedShopPrintBase() {
        String xPath = "//h2[contains(.,\"Welcome to PrintBase\")]";
        waitUntilElementVisible(xPath, 30);
        refreshPage();
//        verifyElementPresent(xPath, true);
    }

    public void verifyStoreName(String storeName) {
        String xPath = "//input[@placeholder='Your shop name']";
        waitElementToBeVisible(xPath);
        verifyElementText(xPath, storeName);
    }

    public void clickTakeMeToMyStore() {
        String xpath = "//button[normalize-space()='Take me to my store!']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void selectQuestion(String value_prod, String value_stag) {
        String xpath_prod = "//div[@class='s-select full-width']//select";
        String xpath_stag = "//p[normalize-space()='Question1']//following-sibling::div//select";
        if (isElementExist(xpath_stag)) {
            selectDdlByXpath(xpath_stag, value_stag);
        } else {
            selectDdlByXpath(xpath_prod, value_prod);
        }
        String xpath_next = "//span[normalize-space()='Next']";
        waitABit(2000);
        clickOnElement(xpath_next);
    }

    public void chooseBusinessType(String businessType) {
        clickOnElement("//div[@class='survey__type-business-v3']//li[normalize-space()='" + businessType + "']");
    }

    public void chooseType(String type) {
        clickOnElement("//*[(@class='survey__btn' or @class='s-flex s-flex--align-center') and contains(text(),'" + type + "')]");
    }

    public void inputKeySearch(String categoryNiche) {
        inputSlowly("//input[@placeholder='Search for your category. Ex: Baby']", categoryNiche);
    }

    public void chooseCategoryNiche(String categoryNiche) {
        String xpath = "//div[contains(@class, 's-select-searchable')]//p[normalize-space()='" + categoryNiche + "']";
        waitElementToBePresent(xpath);
        clickOnElement(xpath);
    }

    public void inputColor(String color) {
        waitClearAndType("//div[@class='text-center s-color-picker']//input", color);
    }

    public void chooseFont(String font) {
        inputSlowly("//div[@class='s-select-searchable s-select-searchable--bottom-right']//input", font);
        clickOnElement("//div[@value='" + font + "']");
    }

    public void clickBtnNoThanks() {
        clickOnElementByJs("//button//span[contains(text(),'No thanks')]");
    }

    public void chooseStoreImport(String storeImport) {
        selectDdlByXpath("//div[@class='s-select full-width']//select", storeImport);
    }

    public void clickBtnImport() {
        clickOnElement("//button//span[contains(text(),'Import')]");
    }

    public void selectAnswer(String answer, int j) {
        String xpath = "(//div[@class='s-select full-width']//select)[" + j + "]";
        clickOnElement(xpath);
        String xpathValue = xpath + "//option[contains(text(),'" + answer + "')]";
        clickOnElement(xpathValue);
    }

    public void verifyCreatedShopSuccess() {
        String xPath = "//span[normalize-space()='Home']";
        waitUntilElementVisible(xPath, 100);
        refreshPage();
        verifyElementPresent(xPath, true);
    }

    public void verifyErrorMessage(String msg) {
        List<String> result = getListText("//div[@class='s-form-item__error']");
        System.out.println(result);
        assertThat(result).containsAll(asList(msg.split(",")));
    }

    public void clickBtnSample() {
        String xpath = "//button[@class='s-button is-default']";
        clickOnElement(xpath);
    }

    public void verifyShopData(String data1, String data2) {
        assertThat(data1).isEqualTo(data2);
    }

    public void verifySampleData(List<String> sampleData1, List<String> sampleData2) {
        for (int i = 0; i < sampleData1.size(); i++) {
            Assertions.assertThat(sampleData1.get(i)).isEqualTo(sampleData2.get(i));
        }
    }

    public void clickCapchar() {
        if (isElementVisible("//div[@id='grecaptcha']//iframe", 10)) {
            switchToIFrame("//div[@id='grecaptcha']//iframe");
            clickOnElement("//span[contains(@class,'recaptcha-checkbox')]//div[(@class='recaptcha-checkbox-border')]");
            switchToIFrameDefault();
        }
    }

    public void enterEmailCustomer(String sEmail) {
        String xpath = "//input[@type='email']";
        XH(xpath).type(sEmail);
    }

    public void verifyDisplayOffer(String shopname, Boolean isDisplay) {
        verifyElementPresent("//*[contains(text(),'" + shopname + "')]//following::div[@class='closed_offer']", isDisplay);
    }

    public boolean isExistQuestionPage() {
        return isElementExist("//div[@class='s-m40 survey__onboarding-question']");
    }

    public void clickBtnNext() {
        waitElementToBeClickable("//button[@class='s-button is-primary']");
        clickOnElement("//button[@class='s-button is-primary']");

    }

    public boolean isIntroPageExist() {
        return isElementExist("//button[normalize-space()='Start now']");
    }

    public void verifyCaptchaIsEnable(boolean status) throws IOException {
        init();
        accessToken = "default_token";
        boolean isSpam = isIpSpam();
        int responseCodeCaptchaApi = getCaptchaResponseCode();
        if (status) {
            assertThat(isSpam).isEqualTo(true);
            if (responseCodeCaptchaApi == 404) {
                throw new IllegalStateException("Unexpected value: " + responseCodeCaptchaApi);
            } else {
                assertThat(responseCodeCaptchaApi).isEqualTo(422);
            }
        } else {
            assertThat(isSpam).isEqualTo(false);
        }
    }

    public Integer getCaptchaResponseCode() throws IOException {
        int responseCode = 0;
        String url_temp = gapi_url + "/signup/shop";
        URL url = new URL(url_temp);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("X-ShopBase-Access-Token", accessToken);
        http.addRequestProperty("X-SB-Captcha", captchaToken);
        responseCode = http.getResponseCode();
        return responseCode;
    }

    public boolean isIpSpam() {
        String url = gapi_url + "/signup/check-ip-spam";
        JsonPath jp = getAPI(url);
        return Boolean.parseBoolean(String.valueOf(jp.get("is_spam")));
    }

    public void openShopList() {
        String env = LoadObject.getProperty("shop.env");
        String url;
        if (env.equals("")) {
            url = "https://accounts.shopbase.com/shop/select";
        } else {
            url = "https://accounts." + env + ".shopbase.net/shop/select";
        }
        openUrl(url);
    }

    public void selectQuestionAndAnswers(String item, String i) {
        String xpathAnswers = "//p[normalize-space()='" + i.trim() + "']//following-sibling::div//select";
        clickOnElement(xpathAnswers);
        selectDdlByXpath(xpathAnswers, item);
    }
}

