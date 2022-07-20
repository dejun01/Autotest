package com.opencommerce.shopbase.dashboard.dashboard.steps;

import com.opencommerce.shopbase.dashboard.dashboard.pages.AppStorePage;
import com.opencommerce.shopbase.dashboard.authen.pages.LoginDashboardPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;


public class AppStoreSteps extends ScenarioSteps {
    AppStorePage appStorePage;

    LoginDashboardPage loginDashboardPage;

    @Step
    public void openAppStorePage(String url) {
        appStorePage.openAppStore(url);
        appStorePage.maximizeWindow();
    }

    @Step
    public void searchApp(String value_search) {
        appStorePage.searchApp(value_search);
    }

    @Step
    public void verifySearchResult(String value_search) {
        appStorePage.verifySearchAppResult(value_search);
    }

    @Step
    public void verifySearchNoData(String value_search) {
        appStorePage.verifySeachNoData(value_search);
    }

    @Step
    public void selectAppname(String appName) {
        appStorePage.selectAppname(appName);
    }

    @Step
    public void clickSignupButton() {
        appStorePage.clickBtn("Sign up", 1);
    }

    @Step
    public void enterPassword(String value_pwd) {
        appStorePage.enterPassword(value_pwd);
    }

    @Step
    public void enterShopname(String value_shopname) {
        appStorePage.enterShopname(value_shopname);
    }

    @Step
    public void verifyDirectToHomedashboard() {
        loginDashboardPage.verifyTextPresent("Here’s what’s happening with your store today.", true);
    }

    @Step
    public boolean isAppInstalled(String appName) {
        return appStorePage.isAppInstalled(appName);
    }

    @Step
    public void deleteApp(String appName) {
        appStorePage.clickBtnDeleteApp(appName);
        confirmDelete("Uninstall");
    }

    @Step
    public void confirmDelete(String uninstall) {
        appStorePage.confirmDelete(uninstall);
    }

    @Step
    public void verifyAppInstalled(String appName, boolean isInstall) {
        appStorePage.verifyButtonDeleteApp(appName, isInstall);
    }

    @Step
    public void goToAppStore() {
        appStorePage.clickBtnVisitShopBaseAppStore();
    }

    @Step
    public void switchToNewTab() {
        appStorePage.switchToWindowWithIndex(1);
    }

    @Step
    public void addApp() {
        appStorePage.clickToInstallBtn();
    }

    @Step
    public void installApp() {
        appStorePage.clickBtn("Install app");
        appStorePage.waitForPageLoad();
        appStorePage.waitForEverythingComplete();

    }

    @Step
    public void searchAppToInstall(String appName) {
        appStorePage.enterAppNameIntoSearchField(appName);
        appStorePage.clickSelectedApp(appName);
    }

    @Step
    public void goToAppDashboard() {
        appStorePage.switchToWindowWithIndex(0);
        appStorePage.waitForEverythingComplete();
        appStorePage.refreshPage();
        appStorePage.waitForEverythingComplete();
    }

    @Step
    public void switchToOldTab() {
        appStorePage.switchToWindowWithIndex(0);
    }

    @Step
    public String getAppAccessToken() {
        return appStorePage.getAppAccessToken();
    }

    @Step
    public void registerWebhookWithAppSAccessToken(String shop, String topic, String appAccessToken, String address) {
        appStorePage.registerWebhookWithAppSAccessToken(shop, topic, appAccessToken, address);
    }

    @Step
    public void verifyWebhookOnAddress(String address, int numberBefore, String shop) throws Exception {
        appStorePage.verifyWebhookOnAddress(address, numberBefore, shop);
    }

    @Step
    public int getNumberOfWebhookBeforeOnAddress(String address) {
        return appStorePage.getNumberOfWebhookBeforeOnAddress(address);
    }


    @Step
    public void verifyElementOnPage() {
        appStorePage.verifyTextPresent("Apps", true);
        appStorePage.verifyTextPresent("Get started with an app recommended just for you", true);
        appStorePage.verifyTextPresent("Use apps to manage and grow your business.", true);
        appStorePage.verifyTextPresent("Or looking for other apps that can extend your store's features?", true);
        appStorePage.verifyTextPresent("Apps can add new functionality, drive sales, and optimize how you run your business.", true);
        appStorePage.verifyButtonVisitShopBaseAppStore();
    }

    @Step
    public void verifyActionClickAnyAppName() {
        appStorePage.clickIntoAreaAppName();
    }


    @Step
    public void uninstallApp(String appName) {
        appStorePage.uninstallApp(appName);
    }


    @Step
    public void verifyFulfillmentServiceOnOrderDetail(String productName) {
        appStorePage.verifyFulfillmentServiceOnOrderDetail(productName);
    }

    @Step
    public void selectApp(String appName) {
        appStorePage.selectApp(appName);
    }

    public void turnOffSitekitPopup() {
        waitABit(5000);
        if (appStorePage.isSitekitPopupExist()) {
            appStorePage.closePopupSitekit();
        }
    }

    public void selectPrintHubOnAppsList() {
        appStorePage.selectPrintHubOnAppsList();
    }


    @Step
    public void searchAppAndSelectAppPlusbase(String appName) {
        appStorePage.enterAppNameIntoSearchField(appName);
        appStorePage.clickSelectAppPlusbase(appName);
    }

    @Step
    public void verifyBuildInScreen(String shopName, String appName,String noti){
        appStorePage.verifyBuildInScreen(shopName, appName);
        appStorePage.verifyNotifyDisableApp(noti);
    }
}
