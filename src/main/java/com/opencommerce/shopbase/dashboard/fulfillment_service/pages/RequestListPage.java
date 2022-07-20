package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import com.google.common.base.CharMatcher;
import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.opencommerce.shopbase.OrderVariable.*;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestListPage extends SBasePageObject {

    public String urlAli = "https://vi.aliexpress.com/item/4000125407141.html";

    public RequestListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String url = LoadObject.getProperty("webdriver.base.url");

    public int getNumberInTab(String tab) {
        String xpath = "//li[@class='is-active']//descendant::p";
        refreshPage();
        waitElementToBeVisible(xpath);
        String numberDef = getText(xpath).replace(tab, "").trim();
        String name = numberDef.isEmpty() ? "0" : numberDef.replaceAll("[^0-9]", "");
        int numberTab = Integer.parseInt(name);
        return numberTab;
    }

    public void clickButton(String name) {
        clickBtn(name);
        waitForPageLoad();
    }

    public void reloadPage() {
        getDriver().navigate().refresh();
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void checkNumberAfterRequesProduct(int numberTabInProcess, String tabName) {
        int newNumber = this.getNumberInTab(tabName);
        assertThat(newNumber).isEqualTo(numberTabInProcess + 1);
    }

    public void searchQuotationWithURL() {
        String xpathProduct = "//input[@placeholder='Search quote by Quotation number, Source URL, Product name']";
        waitClearAndTypeThenEnter(xpathProduct, urlAli);
    }

    public String getQuotationId() {
        String xpath = "//tr[1]//td[1]//span[@class='name']";
        return getText(xpath);
    }

    public void clickTab(String quotationTab) {
        String xpath = "//span/p[contains(.,'" + quotationTab + "')]";
        waitElementToBeClickable(xpath);
        String xpathOut = "//thead/tr/th[5]";
        clickOnElement(xpathOut);
        clickOnElement(xpath);
    }

    public void checkClickIconDetail(String quotationId) {
        String xpath = "//thead//tr/th";
        int countElement = this.countElement(xpath);
        for (int i = 1; i <= countElement; i++) {
            String xpathI = "(" + xpath + ")[" + i + "]";
            if (getText(xpathI).equals("ACTION")) {
                String xpathAction = "//div[@class='table-quotation']//tbody/tr/td[" + i + "]/a";
                clickOnElement(xpathAction);
                String xpathTitleQuotationDetail = "//div[@class='quotation-detail']/h3";
                String titleActual = getText(xpathTitleQuotationDetail);
                String titleExp = "Quotation " + quotationId;
                assertThat("Quotation " + quotationId).isEqualTo(getText(xpathTitleQuotationDetail));
            }
        }

    }

    public int countElement(String xpath) {
        int countElement = countElementByXpath(xpath);
        return countElement;
    }

    public void switchToTabInRequestList(String tab) {
        String xpathTab = "//div[@class='s-tabs']//p[contains(text(),'" + tab + "')]";
        waitUntilElementVisible(xpathTab, 10);
        if (!isElementExist(xpathTab + "/ancestor::li[@class='is-active']"))
            clickOnElement(xpathTab);
        waitForEverythingComplete();
    }

    public void goToQuotationDetail(String quotationId) {
        waitABit(2000);
        String xpath = "//span[text()[normalize-space()='" + quotationId + "']]/ancestor::tr//span[@class='name']";
        clickOnElement(xpath);
    }

    public void closeCripsChat() {
        String xpath1 = "//div[@data-chat-status='ongoing' and @data-visible='true']";
        String xpath2 = "//div[@id='crisp-chatbox']//div[@data-visible='true']//span[@class='cc-wdhl cc-1f87']";
        if (isElementExist(xpath1)) {
            clickOnElement("//span[@class='cc-1asz']");
        }
    }

    public int getColRequestList(String column) {
        String xpath = "//th[child::strong[text()[normalize-space()='" + column + "']]]/preceding-sibling::th";
        if(!isElementExist(xpath))
        {
            xpath = "//th[child::strong[text()[normalize-space()='SOURCE URL']]]/preceding-sibling::th";
        }
        return countElementByXpath(xpath) + 1;
    }

    public String getFirstDataInRequestList(int col) {
        String xpath = "//tr[1]//td[" + col + "]//a[contains(@class,'source-url')]";
        return getText(xpath);
    }

    public String getWarehouseProduct(int col) {
        String xpath1 = "//tr[1]//td[" + col + "]//span[@class='warehouse-product__name']";
        String xpath = "//tr[1]//td[" + col + "]//a[@class='warehouse-product__name']";
        if (isElementExist(xpath)) {
            return getText(xpath);
        } else {
            return getText(xpath1);
        }
    }

    public String getMinimumQuantity() {
        String xpath = "//tr[1]//span[@class='minimum-price']";
        return getText(xpath);
    }

    public String getExpiraton() {
        String xpath = "//tr[1]//span[@class='money--gray expiration']";
        return getText(xpath);
    }

    public String getRequestAt() {
        String xpath = "//tr[1]//span[@class='money--gray requested-at']";
        return getText(xpath);
    }

    public void searchProduct(String content) {
        String xpath = "//input[contains(@class, 's-input__inner order-search-input')]";
        waitClearAndType(xpath, content);
    }

    public void verifyQuotationNotShow() {
        String xpath = "//p[text()[normalize-space()='No quotation']]";
        waitUntilElementVisible(xpath, 10);
        verifyElementPresent(xpath, true);
    }

    public void verifyQuotationShow() {
        String xpath = "//tr[1]//td[1]//span[@class='name']";
        verifyElementPresent(xpath, true);
    }

    public boolean isShow() {
        String xpath = "//tr[1]//td[1]//span[@class='name']";
        return isElementExist(xpath);
    }

    public void verifySourceUrl(String link) {
        String xpath = "//tr//a[text()[normalize-space()='"+link+"']]";
        verifyElementVisible(xpath, true);
    }
    public void verifyStatus(String status) {
       String xpath = "//span[text()[normalize-space()='"+status+"']]";
       verifyElementVisible(xpath, true);

    }

    public void waitSearchResult(String quotationId) {
        String xpath = "//span[text()[normalize-space()='" + quotationId + "']]//ancestor::tr";
        waitUntilElementVisible(xpath, 20);
    }
}
