package com.opencommerce.shopbase.dashboard.online_store.domains.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DomainPage extends SBasePageObject {
    public DomainPage(WebDriver driver) {
        super(driver);
    }

    public void enterDomain(String domain) {
        enterInputFieldWithLabel("e.g. example.com", domain);
    }

    public void verifyConnectdomainSuccessfully(String domain) {
        verifyElementPresent("//section[descendant::h4[normalize-space()='Domains']]//td[normalize-space()='"+domain+"']",true);
    }


    public String getCurrentPrimaryDomain() {
        return getText("//section[descendant::h4[normalize-space()='Primary domain']]//td[1]");
    }

    public void clickRemoveOnDomain(String domain) {
        clickOnElement("//tr[child::td[normalize-space()='" + domain + "']]//a[normalize-space()='Remove']");
    }

    public void clickRemoveOnConfirmPopup() {
        clickOnElement("//div[@class='s-modal is-active modal-domain']//button[normalize-space()='Remove']");
    }

    public void verifyRemoveDomainSuccessfully() {
        verifyElementText("//span[contains(@class,'alert__title')]", "Successfully removed domain");
    }
    public boolean checkPrimarydomain( ){
        return isElementExist("//span[contains(text(),'will be set as primary domain')]");
    }
    public void verifyMessageConnectedDomainSuccessfully(String domain) {
        verifyElementText("(//span[contains(@class,'alert__title')])[1]", "Your domain " + domain + " was connected successfully");
        verifyElementText("(//div[@class='s-alert__description']//p)[1]", "Your domain is now pointing to your online store. It can take up to 24 hours for the changes to propagate and 60 minutes to generating SSL.");
    }
    public void verifyMessageChangePrimaryDomain(String domain) {
        verifyElementText("(//span[contains(@class,'alert__title')])[2]", "Domain " + domain + " will be set as primary domain");
        verifyElementText("(//div[@class='s-alert__description']//p)[2]", "Domain " + domain + " will automatically be set as primary domain once it has successfully generated SSL.");
    }


    public void chooseDomain(String connectedDomain) {
        selectRadioButtonWithLabel(connectedDomain, true);
    }

    public void verifyChangePrimaryDomainSuccessfully() {
        verifyElementText("//span[contains(@class,'alert__title')]", "Primary domain successfully changed");
    }

    public void verifyMsg(String msg) {
        verifyElementText("//div[@class='s-form-item__error']|//*[@class='s-alert is-red']|//div[@class='s-alert__content']", msg);
    }

    public void verifyCannotRemoveShopbaseDomain(String shop) {
        verifyElementPresent("//tr[child::td[normalize-space()='" + shop + "']]//a[normalize-space()='Remove']", false);
    }

    public void verifyPrimayDomainBlockDisplay() {
        verifyElementPresent("//section[descendant::h4[normalize-space()='Primary domain']]", true);
    }

    public void verifyDomainDomainBlockDisplay(boolean isDis) {
        verifyElementPresent("//section[contains(@class,'domain-type box-shadow-container') and descendant::h4[normalize-space()='Domains']]", isDis);
    }

    String sectionDomain = "//section[descendant::h4[normalize-space()='Domains']]";

    public List<String> getListDomains() {
        if (isElementExist(sectionDomain))
            return getListText(sectionDomain + "//table//tbody//tr//td[1]");
        return null;
    }

    public boolean isEnableRedirectionDomain() {
        String statusNeedChange = getText("//div[contains(@class,'traffic-status')]//a");
        if (statusNeedChange.equalsIgnoreCase("Enable redirection"))
            return false;
        return true;
    }

    public void changeStatusRedirectionDomain() {
        clickOnElement("//div[contains(@class,'traffic-status')]//a");
    }

    public void confirmRedirectionDomain() {
        waitForElementToPresent("//div[@class='s-modal is-active modal-domain']");
        clickOnElement("//div[@class='s-modal is-active modal-domain']//button[@class='s-button is-primary']");
    }

    public void verifyDomainNavigationFromDB(String urlOutput) {
        waitABit(5000);
        switchToTheLastestTab();
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl).isEqualTo(urlOutput);

    }

    public void clickToBtnBuyNewDomain() {
        String xpath = "//a[normalize-space()='Buy new domain']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void searchNewDomain(String domain) {
        String xpath = "//input[@placeholder='e.g. example.com']";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, domain);
    }

    public void buyNewDomain(String domain) {
        String xpath = "//div[@class='search-result__multiple-result']//div[child::p[text()='" + domain + "']]//button[child::span[text()='Buy']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public double getDomainPrice() {
        String xpath = "//h4[contains(.,'Total')]";
        waitElementToBeVisible(xpath);
        String price = getText(xpath).split(":")[1].replace("$", "").replace(",", "").trim();
        return Double.parseDouble(price);
    }

    public void clickBtnBuyDomain() {
        String xpath = "//button[normalize-space()='Buy domain']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathMakeAPayment = "//p[@class='sidebar-title' and normalize-space()='Make a payment']";
        waitElementToBeVisible(xpathMakeAPayment);
    }

    public String getCurrentDomain(String domain) {

        return domain.replace("sbprod.tk","").replace("sbstag.tk","").trim();
    }


    public void verifyPrimaryDomain(String primaryDomain) {
        if(!isElementExist("//tr[descendant::td[contains(text(),'"+primaryDomain+"')]]//span[normalize-space()='Generating SSL']")){
            verifyElementText("//section[descendant::h4[normalize-space()='Primary domain']]//td[1]",primaryDomain);
        }

    }

    public void removeDomain() {
        if(isElementExist("(//tbody//a[@class='s-button is-text'])[1]"))
        clickOnElement("(//tbody//a[@class='s-button is-text'])[1]");
    }

    public int countDomainInList() {
        return  countElementByXpath("//section[descendant::h4[normalize-space()='Domains']]//td[@class='p15']");
    }

    public void clearCache() {
        deleteAllCookies();
        refreshPage();
        waitABit(6000);
        waitForPageLoad();
    }

    public void clickButtonToOpenSF() {
        waitElementToBeVisible("//i[contains(@class,'open-in-new')]", 30);
        clickOnElement("//i[contains(@class,'open-in-new')]");
    }

    public void waitSuccessMsg() {
        verifyElementVisible("//div[@class='s-alert__content' and contains(.,'Successfully updated traffic redirect')]", true);
        waitUntilElementInvisible("//div[@class='s-alert__content']", 5);
    }
}
