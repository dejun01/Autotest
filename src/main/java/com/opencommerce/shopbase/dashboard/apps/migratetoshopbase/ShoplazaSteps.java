package com.opencommerce.shopbase.dashboard.apps.migratetoshopbase;

public class ShoplazaSteps {
    ShoplazzaPage shoplazzaPage;

    public void navigateToTab(String tabName) {
        shoplazzaPage.switchToTabInMigratePlatform(tabName);
    }

    public void selectPlatform(String flatform) {
        shoplazzaPage.selectRadioButton(flatform, true);
    }

    public void selectDataInput(String data) {
        shoplazzaPage.selectRadioButton(data, true);
    }

    public void enterURL(String url) {
        shoplazzaPage.enterTextAreaWithLabel("Enter your URLs, one URL per line", url);
    }

    public void clickImport() {
        shoplazzaPage.clickBtn("Import");
    }

    public void switchtoWindowMigrateApp() {
        shoplazzaPage.waitNewTabLoad();
        shoplazzaPage.waitForEverythingComplete();
        shoplazzaPage.switchToLatestTab();
    }

    public void loginMigrateApp(String shopname) {
        shoplazzaPage.waitForEverythingComplete();
        if (shoplazzaPage.isexistLoginForm()) {
            shoplazzaPage.enterInputFieldWithLabel("Enter your ShopBase domain", shopname);
            shoplazzaPage.clickBtn("Login");
        }
    }

    public void choosefileCSV(String file) {
        shoplazzaPage.choosefileCSV(file);
    }

    public void verifyMsg() {
        shoplazzaPage.verifyMsg();
    }

    public void verifyQueueImport(String url) {
        shoplazzaPage.verifyQueueImport(url);
    }

    public void switchtoShopBase() {
        shoplazzaPage.switchToTheFirstTab();
    }
}
