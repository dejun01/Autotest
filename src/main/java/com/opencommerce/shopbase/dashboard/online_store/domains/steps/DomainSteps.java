package com.opencommerce.shopbase.dashboard.online_store.domains.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.domains.pages.DomainPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DomainSteps extends CommonSteps {
    DomainPage domainPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void clickDomains() {
        domainPage.clickLinkTextWithLabel("Domains");
    }

    @Step
    public void clickConnectExistingDomain() {
        domainPage.clickLinkTextWithLabel("Connect Existing Domain");
    }

    @Step
    public void enterDomain(String domain) {
        domainPage.enterDomain(domain);
    }

    @Step
    public void clickBtnNext() {
        domainPage.clickBtn("Next");
    }

    @Step
    public void clickBtnVerifyConnection() {
        domainPage.clickBtn("Verify connection");
        domainPage.waitForTextToAppear("Domains");
    }

    @Step
    public void verifyConnectdomainSuccessfully(String domain) {
//        String curentDomain = domainPage.getCurrentDomain(domain);
        if (domainPage.checkPrimarydomain()) {
            domainPage.verifyMessageConnectedDomainSuccessfully(domain);
            domainPage.verifyMessageChangePrimaryDomain(domain);
        } else {
            domainPage.verifyMessageConnectedDomainSuccessfully(domain);
        }
        domainPage.verifyConnectdomainSuccessfully(domain);
    }

    @Step
    public String getCurrentPrimaryDomain() {
        return domainPage.getCurrentPrimaryDomain();
    }

    @Step
    public void clickRemoveOnDomain(String domain) {
        domainPage.clickRemoveOnDomain(domain);
    }

    @Step
    public void clickRemoveOnConfirmPopup() {
        domainPage.clickRemoveOnConfirmPopup();

    }

    @Step
    public void verifyRemoveDomainSuccessfully() {
        domainPage.verifyRemoveDomainSuccessfully();
    }

    @Step
    public void clickChangePrimaryDomain() {
        domainPage.clickLinkTextWithLabel("Change primary domain?");
    }

    @Step
    public void chooseDomain(String connectedDomain) {
        domainPage.chooseDomain(connectedDomain);

    }

    @Step
    public void clickSave() {
        domainPage.clickBtn("Save");
    }

    @Step
    public void verifyChangePrimaryDomainSuccessfully(String connectedDomain) {
        domainPage.verifyChangePrimaryDomainSuccessfully();
    }

    @Step
    public void

    verifyMsg(String msg) {
        domainPage.verifyMsg(msg);
    }


    public List<String> getListDomains() {
        return domainPage.getListDomains();
    }

    @Step
    public void enableRedirectDomain(boolean isEnable) {
        boolean isCurrentStatus = domainPage.isEnableRedirectionDomain();
        if (isCurrentStatus != isEnable) {
            domainPage.changeStatusRedirectionDomain();
            domainPage.confirmRedirectionDomain();
            domainPage.waitSuccessMsg();
        }
        if (isEnable) {
            domainPage.verifyTextPresent("Traffic from all your domains redirects to this primary domain.", true);
        } else {
            domainPage.verifyTextPresent("Traffic from your domains is not being redirected to this primary domain.", true);
        }

        assertThat(domainPage.isEnableRedirectionDomain()).isEqualTo(isEnable);

    }


    @Step
    public void verifyCannotRemoveShopbaseDomain(String shop) {
        domainPage.verifyCannotRemoveShopbaseDomain(shop);
    }

    @Step
    public void verifyDomainPageOnlyHasPrimarydomain() {
        domainPage.verifyPrimayDomainBlockDisplay();
        domainPage.verifyDomainDomainBlockDisplay(false);
    }

    @Step
    public void verifyDomainPagehasDomainsBlock() {
        domainPage.verifyPrimayDomainBlockDisplay();
        domainPage.verifyDomainDomainBlockDisplay(true);
    }

    public void verifyDomainNavigationFromDB(String urlInput, String urlOutput) {
        domainPage.openUrlInNewTab("https://" + urlInput);
        domainPage.waitForPageLoad();
        domainPage.waitForEverythingComplete();
        domainPage.verifyDomainNavigationFromDB(urlOutput);
    }

    @Step
    public void buyNewDomain(String domain) {
        domainPage.clickToBtnBuyNewDomain();
        domainPage.searchNewDomain(domain);
        domainPage.buyNewDomain(domain);
    }
    @Step
    public double getDomainPrice() {
        return domainPage.getDomainPrice();
    }

    @Step
    public void clickBtnBuyDomain() {
        domainPage.clickBtnBuyDomain();
    }

    public void verifyPrimaryDomain(String primaryDomain) {
        domainPage.verifyPrimaryDomain(primaryDomain);
    }


    public void clickRemoveDomain() {
        domainPage.removeDomain();
    }

    public int countDomainInList() {
        return domainPage.countDomainInList();
    }

    public void openURL(String shop) {
        domainPage.openUrlInNewTab("https://" + shop);
        domainPage.waitForPageLoad();
        domainPage.waitForEverythingComplete();
    }

    public void verifyRedirectionWithDomainOnshopbase(String primaryDomain, String shop) {
        int index = 0;
        String currentURL = "";
        String expecteDomain = "";
        do {
            index++;
            domainPage.openUrlInNewTab("https://" + shop + "?render_csr=1&is_delete_cache=true");
            domainPage.switchToWindowWithIndex(1);
            domainPage.clearCache();
            currentURL = domainPage.getCurrentUrl().replace("?render_csr=1&is_delete_cache=true", "").trim();
            expecteDomain = "https://" + primaryDomain + "/";
            domainPage.closeBrowserWithIndex(1);
            domainPage.switchToTheFirstTab();
        } while ((!currentURL.equals(expecteDomain)) & index <= 5);
        assertThat(currentURL).isEqualTo(expecteDomain);

    }

    public void clickBtnEnableRedirection() {
        domainPage.changeStatusRedirectionDomain();
    }

    public void verifyPrimaryDomainOnSF(){
        String expectedDomain = "https://" + shop + "/";

        refreshPage();
        domainPage.clickButtonToOpenSF();
        domainPage.switchToTheLastestTab();
        String currentURL = domainPage.getCurrentUrl();
        assertThat(currentURL).isEqualTo(expectedDomain);
    }

    public void openSFFromDashboard(){
        domainPage.clickButtonToOpenSF();
        domainPage.switchToTheLastestTab();
    }

}
