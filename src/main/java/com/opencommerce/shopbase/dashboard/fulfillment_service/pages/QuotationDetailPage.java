package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertThat;

public class QuotationDetailPage extends SBasePageObject {
    public QuotationDetailPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectQuotation(String quotation) {
        String xpath = "//input[@class='s-input__inner']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, quotation);

    }

    public void cancelQuotation() {
        clickOnElement("//div[text()[normalize-space()='Actions']]");
        clickOnElement("//div[text()[normalize-space()='Cancel request']]");
    }

    public void clickBTAction(String qID) {
        try {
            waitABit(3000);
            String xpath = "//span[contains(text(),'" + qID + "')]";
            focusClickOnElement(xpath);
            waitForEverythingComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int getRowOfVariant(String variantName) {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[descendant::span[contains(text(),'" + variantName + "')]]";
        int row = 0;
        if (isElementExist(xpath)) {
            row = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return row;
    }

    public int getColPurchaseInQuotation(String col) {
        String xpath = "//th[child::strong[contains(text(),'" + col + "')]]/preceding-sibling::th";
        int index = countElementByXpath(xpath) + 1;
        return index;
    }

    public void clickBTPay(String pay) {
        clickBtn(pay);
        waitForEverythingComplete();
    }

    public void clickOnStockProducts() {
        String xpathbtn = "(//div[contains(@class,'type box')])[2]";
        if (isElementExist(xpathbtn)) {
            clickOnElement(xpathbtn);
        }
    }

    public void inputVariant(String varQuantity, int index) {
        waitForEverythingComplete();
        int col = getColPurchaseInQuotation("ORDERED QUANTITY");
        scrollIntoElementView("//table[@class='table pos-rlt']//tbody//tr[" + index + "]/td[" + col + "]//input");
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]/td[" + col + "]//input";
        waitClearAndTypeThenEnter(xpath, varQuantity);
    }

    public String verifyInfoUnitPriceRow(int index) {
        int i = getColPurchaseInQuotation("UNIT PRICE");
        scrollIntoElementView("//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[" + i + "]/span");
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[" + i + "]/span";
        return getText(xpath);
    }

    public String verifyInfoSubTotalRow(int index) {
        int i = getColPurchaseInQuotation("SUBTOTAL");
        scrollIntoElementView("//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[" + i + "]/span");
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[" + index + "]//td[" + i + "]/span";
        return getText(xpath);
    }

    public void clickBTViewHistory() {
        String xpath = "//a[@href='/admin/balance/history']";
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public String getNameProductQuotation() {
        String xpath = "//div[contains(@class,'quotation-title')]";
        return getText(xpath);
    }

    public void clickOptionValue(String optionValue) {
        String[] option = optionValue.split(":");
        String xpath = "//div[text()='" + option[0] + "']/parent::div//button[child::span[text()[normalize-space()='" + option[1] + "']]]";
        String xpathPopup = "//div[@class='s-animation-content s-modal-content']";
        if (!isElementExist(xpathPopup)) {
            clickOnElement(xpath);
        } else {
            clickOnElement(xpathPopup + xpath);
        }
    }

    public String getPriceVariantQuotation() {
        String xpathPrice = "//div[@class='price-value']";
        return getText(xpathPrice);
    }

    public String getPurchaseOrderLeadTime() {
        String xpath = "//div[text()[normalize-space()='Processing time']]//parent::div[contains(@class,'purchase-order-est')]//div[contains(@class,'content')]";
        return getText(xpath);
    }

    public String getExpirationDate() {
        String xpath = "//div[contains(@class,'exp-date')]//div[@class='content']";
        return getText(xpath);
    }

    public String getPriceInQuantity(String quantity) {
        String xpath = "//div[text()[normalize-space()='" + quantity + "']]/parent::div[contains(@class,'price')]//div[@class='price-value']";
        return getText(xpath);
    }

    public void verifyNotiQuotationDetailInExpired() {
        String xpath = "//div[@class='s-alert-content-wrap']";
        verifyElementPresent(xpath, true);
    }

    public void verifyNotShowPurchaseOrder() {
        String xpath = "//div[contains(@class,'box purchase-order-type')]";
        verifyElementPresent(xpath, false);
    }

    public String getShippingMethodDefault() {
        String xpath = "//div[contains(@class,'shipping-fee attr')]//a";
        return getText(xpath);
    }

    public void openPopupSelectShippingMethod() {
        String xpath = "//div[contains(@class,'shipping-fee attr')]//a";
        clickOnElement(xpath);
    }

    public int getColInPopupShippingMethod(String nameCol) {
        String xpath = "//th[text()[normalize-space()='" + nameCol + "']]/preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public String getCostShippingMethodInPopup(String shippingCompany, int col) {
        String xpath = "//td[text()[normalize-space()='" + shippingCompany + "']]/parent::tr//td[" + col + "]";
        return getText(xpath);
    }

    public String getEstimatedDeliveryTime(String shippingCompany, int col) {
        String xpath = "//td[text()[normalize-space()='" + shippingCompany + "']]/parent::tr//td[" + col + "]";
        return getText(xpath);
    }

    public void clickSelectCountryInPopupShippingMethod() {
        String xpath = "//div[@class='s-select']//select";
        clickOnElement(xpath);
    }

    public void selectOptionCountryInPopupShippingMethod(String country) {
        String xpath = "//div[@class='s-select']//select//option[text()[normalize-space()='" + country + "']]";
        clickOnElement(xpath);
    }

    public void clickStockproduct() {
        String xpath = "//span[text()[normalize-space()='Stock products in ShopBase warehouse and shorten the shipping time']]";
        clickOnElement(xpath);
    }

    public void verifyShippingMethodNotShow(String shippingCompany) {
        String xpath = "//div[@class='shipping-method']//td[text()[normalize-space()='" + shippingCompany + "']]";
        verifyElementPresent(xpath, false);
    }

    public void clickStockProducts(String name) {
        String xpath = "//div[text()='" + name + "']";
        clickOnElement(xpath);
    }

    public String getErrWillBeFulfilled() {
        String xpath = "//p[contains(@class,'text-blue')]";
        return getText(xpath).trim();
    }

    public String getErrLineItemsCanNotFulfill() {
        String xpath = "//p[contains(@class,'text-color-danger')]";
        return getText(xpath).trim();
    }

    public String getTotal() {
        String xpath = "//div[child::span[text()='Total']]//following-sibling::div//span";
        return getText(xpath).trim();
    }

    public String getInfoQuantitiRow(String productVariant) {
        String[] data = productVariant.split(",");
        String xpath = "//p[text()='" + data[0] + "']//parent::div[p[text()='" + data[1] + "']]//ancestor::tr//td[contains(@class,'line-quantity')]//span";
        return getText(xpath);
    }

    public String verifyInfoSubCostRow(String productVariant) {
        String[] data = productVariant.split(",");
        String xpath = "//p[text()='" + data[0] + "']//parent::div[p[text()='" + data[1] + "']]//ancestor::tr//td[contains(@class,'line-cost')]//span";
        return getText(xpath);
    }

    public int getRowOfVariants(String variant) {
        String xpath = "//table//tr[@class='border-bottom'][descendant::p[contains(text(),'" + variant + "')]]";
        int row = 0;
        if (isElementExist(xpath)) {
            row = countElementByXpath(xpath + "/following-sibling::tr");
        }
        return row;

    }

    public void clickInput(String s) {
        String xpath = "//p[text()='" + s + "']//ancestor::label//span[@class='s-check']";
        clickOnElement(xpath);
    }

    public String getTotalInfo(String text) {
        String xpath = "//div[child::p[contains(text(),'" + text + "')]]/following-sibling::div/p";
        return getText(xpath).trim();
    }

    public String getQuantityOrderUnfulfilled() {
        String xpath = "(//div[@class='section-total-order']//span)[1]";
        waitElementToBePresent(xpath);
        return getText(xpath);
    }

    public int getOrderedQuantity(String variant) {
        int index = getIndexOfColumn("ORDERED QUANTITY");
        String xpath = "//table[@class='table pos-rlt']//tbody//tr[descendant::span[contains(text(),'" + variant + "')]]/td[" + index + "]//span/span";
        int quantity = Integer.parseInt(getText(xpath));
        return quantity;
    }

    public String getTotalOrderedQuantity() {
        int index = getIndexOfColumn("ORDERED QUANTITY");
        String xpath = "//td[text()='Total']//ancestor::tr//td[" + index + "]";
        return getText(xpath);
    }

    public String getTotalBaseCost() {
        int index = getIndexOfColumn("UNIT PRICE");
        String xpath = "//td[text()='Total']//ancestor::tr//td[" + index + "]";
        return getText(xpath);
    }

    public String getTotalSubTotal() {
        int index = getIndexOfColumn("SUBTOTAL");
        String xpath = "//td[text()='Total']//ancestor::tr//td[" + index + "]";
        return getText(xpath);
    }

    public void verifyUndisplayTabFulfillCurrentOrder() {
        String xpath = "//div[text()='Fulfill current orders']";
        waitUntilElementInvisible(xpath, 20);
        verifyElementPresent(xpath, false, 15);
    }

    public void selectRequest() {
        String xpath = "//input[@value='product-cost']";
        waitUntilElementVisible(xpath, 10);
        focusClickOnElement(xpath);
    }

    public String getStatus(String text) {
        String xpath = "//span[text()[normalize-space()='" + text + "']]";
        waitUntilElementVisible(xpath, 10);
        return getText(xpath);
    }

    public void clickOnTab(String tab) {
        String xpath = "//div[contains(@class,'type-title')and normalize-space()='" + tab + "']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public boolean showTabFulfullCurrent() {
        waitABit(3000);
        String xpath = "//div[text()='Fulfill current orders']/parent::div";
        waitUntilElementInvisible(xpath, 10);
        return isElementExist(xpath);
    }

    public boolean isNotEnoughMoney() {
        String xpath = "//p[text()[normalize-space()=\"You don't have enough balance for this payment. Please topup to ShopBase Balance in order to complete your transaction.\"]]";
        waitUntilElementInvisible(xpath, 10);
        return isElementExist(xpath);
    }

    public void hoverToTextOther() {
        String el = "//p[contains(text(),'line items will be fulfilled')]";
        hoverOnElement(el);
    }
}