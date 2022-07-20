package com.opencommerce.shopbase.dashboard.online_store.navigation.steps;

import com.opencommerce.shopbase.dashboard.online_store.navigation.pages.RedirectURLPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;

import static org.assertj.core.api.Assertions.assertThat;

public class RedirectURLSteps {
    RedirectURLPages redirectURLPages;
    ThemeEditorPage themeEditorPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void checkRedirectURL(String productName, String newURL, boolean isCreate) {
        redirectURLPages.switchToTab("Online Store");
        redirectURLPages.switchToTab("Navigation");
        redirectURLPages.clickURLRedirects();
        redirectURLPages.veriryRedirectURLProduct(productName, newURL, isCreate);
    }

    @Step
    public void checkRedirectURLCollection(String currentURL, String newURL, boolean isCreate) {
        redirectURLPages.switchToTab("Online Store");
        redirectURLPages.switchToTab("Navigation");
        redirectURLPages.clickURLRedirects();
        redirectURLPages.veriryRedirectURLCollection(currentURL, newURL, isCreate);
    }

    @Step
    public void checkRedirectURLPage(String currentURL, String newURL, boolean isCreate) {
        redirectURLPages.switchToTab("Online Store");
        redirectURLPages.switchToTab("Navigation");
        redirectURLPages.clickURLRedirects();
        redirectURLPages.veriryRedirectURLPage(currentURL, newURL, isCreate);
    }

    @Step
    public void clickURLRedirects() {
        redirectURLPages.clickURLRedirects();
    }

    @Step
    public void searchRedirectURL(String url) {
        redirectURLPages.searchRedirectURL(url);
    }

    @Step
    public void changeRedirectURL(String newFrom, String url) {
        redirectURLPages.chooseURLRedirect(url);
        redirectURLPages.enterURLRedirectFrom(newFrom);
        redirectURLPages.clickBtn("Save redirect");
        redirectURLPages.verifySaveRedirectSuccessfully();
        redirectURLPages.verifyURLRedirect(newFrom, url);
    }

    @Step
    public void verifyApplyNewRedirectOnSF(String newFrom, String url) {
        themeEditorPage.clickIconNavigatetoHomepage();
        themeEditorPage.switchToWindowWithIndex(1);
        String enterURL = ("https://" + shop + "/" + newFrom);
        themeEditorPage.navigateToUrl(enterURL);
        String exactURL = ("https://" + shop + url);
        String currentURL = themeEditorPage.getCurrentUrl();
        assertThat(currentURL).isEqualTo(exactURL);
        redirectURLPages.closeBrowser();
        redirectURLPages.switchToWindowWithIndex(0);
    }

    @Step
    public void clickCreateURLredirect() {
        redirectURLPages.clickCreateURLredirect();
    }

    @Step
    public void createRedirect(String redirectFrom, String redirectTo) {
        redirectURLPages.enterURLRedirectFrom(redirectFrom);
        redirectURLPages.enterURLRedirectTo(redirectTo);
        redirectURLPages.clickBtn("Save redirect");
        redirectURLPages.verifyCreateRedirectSucessfully();
    }

    @Step
    public void backtoNavigationPage() {
        redirectURLPages.switchToTab("Online Store");
        redirectURLPages.switchToTab("Navigation");
    }
}
