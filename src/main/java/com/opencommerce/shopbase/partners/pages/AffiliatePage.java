package com.opencommerce.shopbase.partners.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AffiliatePage extends SBasePageObject {
    public AffiliatePage(WebDriver driver) {
        super(driver);
    }

    String fpr = LoadObject.getProperty("fpr");

    public void verfifySignUpScreen() {

        verifyElementText("//h1", "Sign up your partner account");
    }


    public void openAffiliateLink() {
        waitABit(5000);
        openUrl("https://www.shopbase.com/affiliate");
        waitForPageLoad();
    }

    public void clickBtnStartFreeTrial() {
        clickOnElement("//input[@value='Start Free Trial']");
    }

    public String get() {
        String gapiUrl = LoadObject.getProperty("gapi.url");
        String shopDomain = LoadObject.getProperty("shop");
        Response response = getResponseCredentials();
        int userId = response.then().extract().path("user_id");
        String userAccessToken = response.then().extract().path("access_token");
        return userAccessToken;
    }

    public void enterCode(String code) {
        waitClearAndType("//input[@placeholder='type your custom link']", code);
    }

    public void verifyErrorMsg(String msg) {
        verifyElementText("//*[@class='errorMessage s-mt8' or @class='text-color-danger']", msg);
    }

    public void verifySaveSuccess() {
        verifyElementText("//div[@class='s-toast is-dark is-bottom']", "Save successfully");
    }

    public void switchToTabInMenu(String tabName) {
        clickOnElement("//li[contains(@class,'menu li-dashboard')]//span[normalize-space()='" + tabName + "']");
    }

    public void waitForApiUpdate(int time) {
        waitABit(time);
    }

    public void closePopup() {
        if (isElementExist("//iframe[@id='popup-basic-container']")) {
            switchToIFrame("//iframe[@id='popup-basic-container']");
            clickOnElement("//div[@class='sitekit-close']");
            switchToIFrameDefault();
        }

    }

    public void verifyHeading(String heading) {
        verifyElementText("//h2", heading);
    }

    public void verifyTabs(String tabName, int res) {
        verifyElementText("(//div[@class='s-tabs clearfix affiliate-tier-tab-content']//nav//p)[" + res + "]", tabName);
    }

    public void verifyAffiliateShareAndInvitationBlockDisplay() {
        verifyElementPresent("//div[@id='affiliate-share-and-invitation']", true);
    }

    public void verifyBlockCashbackRuleAndRateDisplay() {
        verifyElementPresent("//div[@id='affiliate-sale-item-cash-back-info']", true);
    }

    public void verifyBlockAffiliatBenefitDisplay() {
        verifyElementPresent("//div[@id='affiliate-group-benefit']", true);
    }

    public String getInviCode() {
        return getValue("//div[@class='invitation-code s-input is-disabled' or @class='white-bg s-input is-disabled']//input");
    }

    public List<String> getListBenefit() {
        return getListText("//p[@class='benefit-item s-mt8 s-flex align-items-start' and not(@style=\"display: none;\")]");
    }

    public String getNewUsers() {
        String newUsers = getText("(//div[@class='rule-item s-mt8'])[1]//b");
        return newUsers.substring(newUsers.lastIndexOf("/") + 1);
    }

    public String getTotalItems() {
        String newUsers = getText("(//div[@class='rule-item s-mt8'])[2]//b");
        return newUsers.substring(newUsers.lastIndexOf("/") + 1);
    }

    public String getUserHaveOrder() {
        String newUsers = getText("(//div[@class='rule-item s-mt8'])[3]//b");
        return newUsers.substring(newUsers.lastIndexOf("/") + 1);
    }

    public void verifyGroupName(String groupName) {
        String act = getText("//div[contains(@class,'cashback-info-header')]//h4");
       assertThat(act).isEqualTo(groupName);

    }

    public void verifyBenefit(ArrayList<String> benefits) {
        List<String> act = getListText("//p[@class='benefit-item s-mt8 s-flex align-items-start' and not(@style=\"display: none;\")]");
        assertThat(benefits.size()).isEqualTo(act.size());

        for (String benefit : benefits) {
            assertThat(act).contains(benefit.trim());
        }
    }

    public void verifySilverBaseRate(String silverBaseRate) {
        String rate = getText("//div[child::p[normalize-space()='Silver Base']]//div//p");
        assertThat(rate.replaceAll("[^0-9.]", "")).isEqualTo(silverBaseRate);
    }

    public void verifyGoldBaseRate(String goldBaseRate) {
        String rate = getText("//div[child::p[normalize-space()='Gold Base']]//div//p");
        assertThat(rate.replaceAll("[^0-9.]", "")).isEqualTo(goldBaseRate);
    }

    public void verifyNewUser(String newUser) {
        String newUsers = getText("//div[@class='rule-item s-mt12 col-md-6'][1]//b");
        assertThat(newUsers.substring(newUsers.lastIndexOf("/") + 1)).isEqualTo(newUser);
    }

    public void verifyTotalItems(String totalItems) {
        String total = getText("//div[@class='rule-item s-mt12 col-md-6'][2]//b");
        assertThat(total.substring(total.lastIndexOf("/") + 1)).isEqualTo(totalItems);
    }

    public void verifyUserHasOrder(String usersHasOrder) {
        String user = getText("//div[@class='rule-item s-mt12 col-md-6'][3]//b");
        assertThat(user.substring(user.lastIndexOf("/") + 1)).isEqualTo(usersHasOrder);
    }

    public void verifyHowItWorkDisplay() {
        verifyElementPresent("//div[@id='how-it-works']", true);
    }

    public void verifyCashbackRateDisplay() {
        verifyElementPresent("//div[@id='affiliate-billing-cash-back-info']", true);
    }

    public void verifyProfitStatisticDesc() {
        verifyElementText("//p[contains(@class,'profit-statistic-desc')]//span", "Your Cashback will be counted and added to your Balance at the end of every week.");
    }

    public void verifyFilterDisplay() {
        verifyElementPresent("//div[contains(@class,'profit-statistic-actions-filter')]", true);
    }

    public void verifyProfitStatisticTableDisplay() {
        verifyElementPresent("//div[@class='s-mb64 profit-statistic-table']", true);
    }

    public void verifySignUp(String totalUser) {
        String signup = getText("//div[child::p[normalize-space()='Sign-ups'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(signup).isEqualTo(totalUser);
    }

    public void verifyCashBack(String totalCashback) {
        String cashback = getText("//div[child::p[normalize-space()='Cashback'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(cashback).isEqualTo(totalCashback);
    }

    public void verifyClickShopBase(String clickSBase) {
        String click = getText("//div[child::p[normalize-space()='Clicks'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(click).isEqualTo(clickSBase);
    }

    public void runExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var searchParams = new URLSearchParams(window.location.search);\n" +
                "if(searchParams.has('fpr')){\n" +
                "\tvar fpr = searchParams.get(\"fpr\");\n" +
                "\tvar shopType = window.location.hostname.split(\".\")[1];\n" +
                "\t$.ajax({\n" +
                "\t    type: \"POST\",\n" +
                "\t    url: 'https://gapi.stag.shopbase.net/admin/crisp-webhook/affiliate/log',\n" +
                "\t    data: JSON.stringify({\"ref_code\": fpr, \"shop_type\": shopType}),\n" +
                "\t    success: function (data) {\n" +
                "\t\tconsole.log(data)\n" +
                "\t    },\n" +
                "\t    error: function (data) {\n" +
                "\t\tconsole.log(data)\n" +
                "\t    }\n" +
                "\t});\n" +
                "}");
    }

    public void clickTabManageLink() {
        clickOnElement("//p[normalize-space()='Manage link']");
    }

    public void verifyPreviewSubId(String link, String subID) {
        String preview = getValue("//input[@disabled='disabled']");
        String expect = link + "&fpt=" + subID;
        if (!subID.isEmpty()) {
            assertThat(preview).isEqualTo(expect);
        } else assertThat(preview).isEqualTo("");
    }

    public void verifyErrorMessage(String msg) {
        verifyElementText("//div[@class='s-form-item__error' or @class='s-alert__content']", msg);
    }

    public void enterKeyword(String keyword) {
        enterInputFieldThenEnter("Search link", keyword, 1);
    }

    public void verifyLinkDislay(String keyword) {
        String link = getText("//div[@class='affiliate-link-table-slot link']");
        assertThat(link).contains(keyword.toLowerCase());

    }

    public void verifyNoResult(String keyword) {
        String text = getText("//div[@class='not-found-link text-center']//p");
        String ex = "No results for " + keyword + ".";
        assertThat(text).contains(ex);
        verifyElementText("//div[@class='not-found-link text-center']//p[not(@class)]", "Try your search again using a different word or phrase.");
    }

    public void verifyCannotDeleteRootlink() {
        verifyElementPresent("//div[@class='s-flex affiliate-link-item' and child::div[normalize-space()='https://www.printbase.com/?fpr=" + fpr + "']]//span[normalize-space()='Delete']", false);
        verifyElementPresent("//div[@class='s-flex affiliate-link-item' and child::div[normalize-space()='https://www.shopbase.com/?fpr=" + fpr + "']]//span[normalize-space()='Delete']", false);

    }

    public void verifyCreateSublinkSuccess(String link, String subID) {
        link = link + "&fpt=" + subID;
        verifyElementPresent("//div[@class='affiliate-link-table-slot link' and normalize-space()='" + link + "']", true);
    }

    public void clickDelete(String subID) {
        clickOnElement("//div[@class='s-flex affiliate-link-item' and child::div[contains(text(),'" + subID + "')]]//button[normalize-space()='Delete']");
    }

    public void clickDeleteOnConfirmPopup() {
        clickOnElement("//div[@class='s-modal is-active']//button[normalize-space()='Delete']");
    }

    public void verifyDeleteLinkSuccessfully(String link) {
        waitABit(3000);
        verifyElementPresent("//div[@class='s-flex affiliate-link-item' and child::div[contains(text(),'" + link + "')]]", false);
    }
    public void clickMenu() {
        clickOnElement("//span[@class='s-icon m-l is-small']");
    }

    public void clickPartnerDashboard() {
        clickOnElement("//a[normalize-space()='Partner Dashboard']");
    }

    public String getDomain() {
        String url = getCurrentUrl();
        String domain = ((url.split("/admin"))[0]).replace("https://","");
        return  domain;
    }

    public String getAccessTokenUserPartner() {
        String username = LoadObject.getProperty("partnerUser");
        String pwd = LoadObject.getProperty("partnerPWD");
        Response response = getResponseCredentialsWithAcc(username, pwd);
        String tokenUser = response.then().extract().path("access_token");
        return tokenUser;
    }
}
