package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.opencommerce.shopbase.OrderVariable.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseOrderDetailPage extends SBasePageObject {

    public String numberProd = "0";

    public PurchaseOrderDetailPage(WebDriver webDriver) {
        super(webDriver);
    }

    // Purchase Order List
    public List<String> getListPurchaseOrder() {
        String xpath = "//table[@class='s-table table table-hover purchase-order-table']//tbody/tr";
        return getListText(xpath);
    }

    public String accToPage(String page) {
        String xpath = "//p[contains(text(),'" + page + "')]";
        clickOnElement(xpath);
        waitForEverythingComplete();
        return getText(xpath);

    }

    public void inputPurchaseOrder(String purchaseOrderNumber) {
        String xpath = "//input[@class='s-input__inner purchase-order-search']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, purchaseOrderNumber);
        waitForEverythingComplete();
    }

    public String verifyListInfoPO(String name) {
        String xpath = "//table[@class='s-table table table-hover purchase-order-table']//tbody/tr//td[@class='" + name + "']";
        return getText(xpath);

    }

    public void clickPurchaseOrderNumber(String name) {
        String xpath = "//table[@class='s-table table table-hover purchase-order-table']//tbody/tr//td[@class='" + name + "']";
        clickOnElement(xpath);

    }


    // Purchase Order Detail

    public void clickBTViewinvoice() {
        clickBtn("View invoice");
        waitForEverythingComplete();
    }

    public String getTextInvoiceDetail() {
        String xpath = "//div[contains(@class,'justify-content-between')]//h2";
        return getText(xpath);
    }

    public String getTextPurchaseOrder() {
        String xpath = "//div[@class='d-flex s-flex--align-center']/h2";
        return getText(xpath);
    }

    public List<String> getListVariant() {
        String xpath = "//table[@class='table pos-rlt']//tbody/tr";
        return getListText(xpath);
    }

    public List<String> getListDescription() {
        String xpath = "//table[@class='table pos-rlt']//tbody/tr/td[2]/span";
        return getListText(xpath);

    }

    public List<String> getListUnitPrice() {
        String xpath = "//table[@class='table pos-rlt']//tbody/tr/td[4]/span";
        return getListText(xpath);

    }

    public String getQuantityOrderTotal() {
        String xpath = "//table[@class='table pos-rlt']//tfoot//td[3]//span";
        return getText(xpath);
    }

    public String getTotalOrder() {
        String xpath = "//table[@class='table pos-rlt']//tfoot//td[5]//span";
        return getText(xpath);
    }

    public String getStatus() {
        String xpath = "//div[@class='title-bar__orders-show-badge']/span";
        return getText(xpath);
    }

    public void clickSourceURL() {
        String xpath = "//div[@class='s-pt16']//child::a";
        clickOnElement(xpath);
    }

    public void verifyLink(String url) {
        String currentTab = getDriver().getWindowHandle();
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        int index = tabs.indexOf(currentTab);
        getDriver().switchTo().window(tabs.get(index + 1));
        assertThat(getDriver().getCurrentUrl()).isEqualTo(url);
        getDriver().close();
        getDriver().switchTo().window(tabs.get(index));
        waitForEverythingComplete();

    }

    public String verifyInfoInvoiceDetail(int index) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr/td[" + index + "]";
        return getText(xpath);

    }

    public String getAmount() {
        int index = getIndext("Amount");
        String xpath = "//div[text()='Amount']//parent::div//following-sibling::div[contains(@class,'flex-column')]//div[" + index + "]";
        return getText(xpath);

    }

    private int getIndext(String label) {
        return countElementByXpath("//div[contains(text(), '" + label + "')]//preceding-sibling::div") + 1;
    }

    public String getTextPurchaseNumber() {
        String xpath = "//table[contains(@class,'purchase-order-table')]//tbody//tr[1]//a";
        return getText(xpath);
    }

    public int getVariant(String variantName) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[descendant::span[contains(text(),'" + variantName + "')]]";
        int row = 0;
        if (isElementExist(xpath)) {
            row = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return row;
    }

    public String verifySubQuantity(int index) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[3]/span";
        return getText(xpath);
    }

    public String verifyUnitPrice(int index) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[4]/span";
        return getText(xpath);
    }

    public String verifySubTotal(int index) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[5]/span";
        return getText(xpath);
    }

    public void clickProductInventory() {
        String xpath = "//tbody/tr[not(@class)]";
        clickOnElement(xpath);
    }

    public String getInfoWithProd(String nameCol, String variant) {
        int numberCol = getIndexColumTableQuotation(nameCol);
        String xpathCon = "//table//tbody/tr[1]/td[" + numberCol + "]";
        if (!variant.isEmpty()) {
            xpathCon = "//span[contains(text(), '" + variant + "')]//ancestor::tr[contains(@class, 'cursor-default')]/td[" + numberCol + "]";
        }
        return getText(xpathCon);
    }

    public void verifyDataInventoryOfProduct(int quatity) {
        for (String item : actInfoProduct.keySet()) {
            int act = Integer.parseInt(actInfoProduct.get(item));
            int ext = Integer.parseInt(expInfoProduct.get(item)) + quatity;
            assertThat(act).isEqualTo(ext);
        }
    }

    public int getIndexColumTableQuotation(String colName) {
        String xpath = "//div[@class='product-list']/div/table/thead/tr//th[child::*[normalize-space()='" + colName + "']]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public void searchProductInPurchase(String product) {
        String xpath = "//input[@placeholder='Search product, purchase number']";
        waitClearAndTypeThenEnter(xpath, product);
    }

    public String getPOINName() {
        String xpath = "//tr[1]/td[@class='name']//a";
        try {
            verifyElementVisible(xpath, true);
            return getText(xpath);
        } catch (Throwable e) {
            waitABit(4000);
            refreshPage();
            verifyElementVisible(xpath, true);
            return getText(xpath);
        }
    }

    public void accToTab(String tab) {
        String xpath = "(//ul//*[text()[normalize-space() = '"+tab+"']]|//ul//*[contains(text(), '"+tab+"') and contains(text(), '(')])";
        clickOnElement(xpath);

    }

    public void accToBalance() {
        String xpath = "//p[@class='user-email text-truncate']";
        String xPath_Balance = "//div[contains(text(),'Balance')]";
        scrollToElement(xpath);
        clickOnElement(xpath);
        if (isElementVisible(xPath_Balance, 5)) {
            clickOnElementByJs(xPath_Balance);
        }
        waitForEverythingComplete();
        String xpathTitle = "//h2[text()='Balance']";
        waitElementToBeVisible(xpathTitle, 20);
    }
    public String getTextPurchaseNumberAfterGetTN() {
        String xpath = "//table[contains(@class,'purchase-order-table')]//tbody//tr[2]//a";
        return getText(xpath);
    }

}



