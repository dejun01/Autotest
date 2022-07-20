package com.opencommerce.shopbase.dashboard.dashboard.pages;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AppStorePage extends SBasePageObject {
    public int MAX_RETRY_TIME = 10;

    public AppStorePage(WebDriver driver) {
        super(driver);
    }

    public void openAppStore(String url) {
        openUrl(url);
    }

    public void searchApp(String value_search) {
        String xpath = "//input[@placeholder='Search for app']";
        waitClearAndType(xpath, value_search);
        XH(xpath).sendKeys(Keys.ENTER);
    }

    public void verifySearchAppResult(String value_search) {
        int result = countElementByXpath("//a[descendant::div[contains(.,'"+ value_search +"')]]");
        if(result == 1){
            verifyElementVisible("//h1[@class='search-title' and contains(.,'1 result for \""+ value_search +"\"')]", true);
        }else {
            verifyElementVisible("//h1[@class='search-title' and contains(.,'"+ result +" results for \""+ value_search +"\"')]", true);
        }
        verifyElementVisible("//*[@class='app-title' and contains(.,'"+ value_search +"')]", true);
    }

    public void verifySeachNoData(String value_search) {
        verifyElementVisible("//h1[@class='search-title' and contains(.,'No result for \""+ value_search +"\"')]", true);
        verifyElementVisible("//*[contains(text(),'Try searching again with different keyword.')]", true);
    }

    public void selectAppname(String appName) {
        String xpath = "//h5[@class='app-title'='" + appName + "']";
    }

    public void enterPassword(String value_pwd) {
        enterInputFieldWithLabel("Password", value_pwd);
    }

    public void enterShopname(String value_shopname) {
        enterInputFieldWithLabel("Your shop name", value_shopname);
    }

    public boolean isAppInstalled(String appName) {
        return isElementExist("//li[descendant::p[text()='" + appName + "']]");
    }

    public void clickBtnDeleteApp(String appName) {
        clickOnElement("//li[descendant::p[text()='" + appName + "']]//span[@data-label='Uninstall']");
        waitUntilElementVisible("//div[@class='s-modal-body']", 3);
    }

    public void enterAppNameIntoSearchField(String appName) {
        String xpath = "//input[@placeholder='Search for app']";
        waitTypeAndEnter(xpath, appName);
        waitForEverythingComplete();
    }

    public void clickSelectedApp(String appName) {
        String xpath = "//h5[@class='app-title'='" + appName + "']";
        waitElementToBeVisible(xpath);
        waitABit(1000);
        clickOnElement(xpath);
    }

    public void confirmDelete(String uninstall) {
        String xpath = "//button//span[normalize-space()='Uninstall']";
        waitElementToBeVisible(xpath, 3);
        clickOnElement(xpath);
        waitForElementNotAppear(xpath);
    }

    public void clickBtnVisitShopBaseAppStore() {
        String xpath = "(//button[descendant::*[text()='Visit ShopBase App Store']])[1]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public String getAppAccessToken() {
        waitForEverythingComplete();
        System.out.println("url: " + getCurrentUrl());
        String xPath = "//body//pre";
        String xPath1 = "//span[@class='objectBox objectBox-string']";

        if (isElementVisible(xPath, 10)) {
            verifyElementVisible(xPath, true);
            String appAccessToken = XH(xPath).getText().split("\"")[3];
            return appAccessToken;
        } else if (isElementVisible(xPath1, 10)) {
            verifyElementVisible(xPath1, true);
            String appAccessToken = XH(xPath1).getText().split("\"")[1];
            return appAccessToken;
        } else return "";
    }

    public void verifyButtonDeleteApp(String appName, boolean isInstall) {
        String xpathButtonDeleteApp = "//*[normalize-space() ='" + appName + "']/following::span[@data-label='Uninstall'][1]";
        verifyElementVisible(xpathButtonDeleteApp, isInstall);
    }

    public void registerWebhookWithAppSAccessToken(String shop, String topic, String appAccessToken, String address) {
        String url = "https://" + shop + "/admin/webhooks.json";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("topic", topic);
        requestParams.addProperty("address", address);
        requestParams.addProperty("format", "json");
        JsonObject requestParamsWebhook = new JsonObject();
        requestParamsWebhook.add("webhook", requestParams);
        System.out.println("url: " + url + "?access_token=" + appAccessToken);
        waitABit(10000);
        postAPI(url, appAccessToken, requestParamsWebhook, 0);
    }

    public void verifyWebhookOnAddress(String address, int numberBefore, String shop) throws Exception {
        waitABit(30000);
        JsonPath jp = getAPI(address);
        int perPage = Integer.parseInt(getData(jp, "per_page").toString());
        int total = Integer.parseInt(getData(jp, "total").toString());
        String page = (int) Math.ceil((double) total / perPage) + "";
        JsonPath jp1 = getAPI(address, page);
        String index = (int) Math.ceil((double) total % perPage - 1) + "";
        assertThat(total).isEqualTo(numberBefore + 1);
        String contentWebhook = getData(jp1, "data.content[" + index + "]").toString();
        String hmacActual = getData(jp1, "data[" + index + "].headers.x-shopbase-hmac-sha256[0]").toString();
        String hmacExpect = getHmac(contentWebhook);
        System.out.println("hmacActual: " + hmacActual);
        System.out.println("hmacExpect: " + hmacExpect);
        assertThat(hmacActual).isEqualTo(hmacExpect);
        assertThat(contentWebhook).contains(shop);
    }

    public static String getHmac(String message) throws Exception {
        String HMAC_ALGORITHM = "HmacSHA256";
        String secret = "c05445b90788d31146f5ae61e713042206ef93a86e909d08e4c83f37d4115656";
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), HMAC_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(rawHmac);
    }

    public Response postAPI(String url, String appAccessToken, JsonObject requestParamsWebhook, int currentRetryTime) {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.queryParam("access_token", appAccessToken);
        request.header("Content-Type", "application/json");
        request.body(requestParamsWebhook.toString());
        try {
            Response response = request.post();
            Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
            if (response.getStatusCode() == 200) {
                System.out.println("Post API successfully");
            }
            return response;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(10000);
                System.out.println("currentRetryTime: " + (currentRetryTime += 1));
                return postAPI(url, appAccessToken, requestParamsWebhook, currentRetryTime);
            } else {
                System.out.println("Reach to max retry, but it is still fail");
                Assert.fail();
                return null;
            }
        }
    }

    public JsonPath getAPI(String url, String page) {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.queryParam("page", page);
        Response resp = given().get(url);
        JsonPath jp = resp.then().extract().jsonPath();
        return jp;
    }

    public int getNumberOfWebhookBeforeOnAddress(String address) {
        JsonPath jp = getAPI(address);
        int total = Integer.parseInt(getData(jp, "total").toString());
        return total;
    }


    public void verifyButtonVisitShopBaseAppStore() {
        verifyElementPresent("(//span[contains(text(),'Visit ShopBase App Store')])[1]", true);
        verifyElementPresent("(//span[contains(text(),'Visit ShopBase App Store')])[2]", true);
    }

    public void clickIntoAreaAppName() {
        String xpath = "(//div[contains(@class,'app-recommend__info')])[1]/p[1]";
        String appName = $(xpath).getTextValue();
        clickOnElement(xpath);
        waitABit(3000);
        switchToWindowWithIndex(1);
        verifyTextPresent(appName, true);
    }

    public boolean verifyTextPresent(String text, boolean isPresent) {
        waitABit(10000);
        waitElementToBeVisible("//*[normalize-space()=\"" + text + "\"]");
        verifyElementPresent("//*[normalize-space()=\"" + text + "\"]", isPresent);
        return isPresent;
    }

    public void uninstallApp(String appName) {
        String xPathUninstall = "//*[normalize-space() ='" + appName + "']/following::span[@data-label='Uninstall'][1]";
        clickOnElement(xPathUninstall);
        clickBtn("Uninstall");
        waitForElementNotVisible(xPathUninstall);
    }

    public void verifyFulfillmentServiceOnOrderDetail(String productName) {
        String xPathServiceName = "//a[contains(text(),'" + productName + "')]/preceding::div[contains(text(),'dropship-connector')]";
        verifyElementVisible(xPathServiceName, false);
    }

    String xpathIframeSitekitPopup = "//iframe[@name='sitekitPopupIframe']";

    public boolean isSitekitPopupExist() {
        return isElementExist(xpathIframeSitekitPopup);
    }

    public void closePopupSitekit() {
        switchToIFrame(xpathIframeSitekitPopup);
        clickOnElement("//div[@class='sitekit-close']");
        switchToIFrameDefault();
    }

    public void selectPrintHubOnAppsList() {
        String xpathClickOnPrintHub = "//div[@class='app_list__items-info']//p[contains(text(),'Print Hub')]";
        waitElementToBeVisible(xpathClickOnPrintHub);
        clickOnElement(xpathClickOnPrintHub);

    }

    public void waitUntilAppNameDisplayed(String appName) {
        String xpath = "(//*[ text()[normalize-space()=" + appName + "] or child::span[normalize-space()=" + appName + "]])[1]";
        waitUntilInvisibleLoading(8);
        waitUntilElementVisible(xpathLinkText("", appName, 1), 15);
    }

    public void clickToInstallBtn() {
        clickOnElement("//p[contains(@class,'cta-button')]");
    }

    public void clickSelectAppPlusbase(String appName){
        String xpath = "//div[contains(@class,'is-guttered')]//*[@class='app-title' and normalize-space()='"+appName+"']";
        waitElementToBeVisible(xpath);
        waitABit(1000);
        clickOnElement(xpath);
    }

    public void verifyBuildInScreen(String shopName, String appName)
    {
        String xpathShopName = "(//div[contains(@class,'authorize-third-party-app')]//p)[1]";
        String shopNameActual = getText(xpathShopName);
        assertThat(shopNameActual).isEqualTo(shopName);
        String xpathAppName = "(//div[contains(@class,'authorize-third-party-app')]//p)[2]";
        String appNameActual = getText(xpathAppName);
        assertThat(appNameActual).isEqualTo(appName);
        clickBtn("Go to this feature");
        waitForEverythingComplete();
    }
    public void verifyNotifyDisableApp(String noti){
        verifyTextPresent(noti,true);
    }

    public void selectApp (String appName){
        String xpath = "//p[normalize-space()='"+ appName +"']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

}