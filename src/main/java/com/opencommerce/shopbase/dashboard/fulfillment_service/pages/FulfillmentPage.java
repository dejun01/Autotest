package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class FulfillmentPage extends SBasePageObject {
    public FulfillmentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchOrderName(String orderNumber) {
        refreshPage();
        waitClearAndTypeThenEnter("//div[@class='order-search-form' or contains(@class,'fulfill-search-input')]//input[contains(@class,'search-input') or contains(@placeholder,'Search orders')]", orderNumber);
        waitForEverythingComplete();
    }

    public void choiceOrderFulfill() {
        String xpath = "//th[@class='checkbox-all']//label[@class='s-checkbox']";
        try {
            waitElementToBeVisible(xpath);
            checkCheckbox(xpath, true);
        } catch (Exception e) {
            waitForEverythingComplete();
            checkCheckbox(xpath, true);
        }
    }

    public void verifyStatusOrderAtList(String status) {
        int count = getIndexCol("FULFILLMENT STATUS");
        try {
            assertThat(getText("//td[" + count + "]//span")).contains(status);
        } catch (Throwable t) {
            waitForEverythingComplete();
            refreshPage();
            assertThat(getText("//td[" + count + "]//span")).contains(status);
        }
    }

    public int getIndexCol(String colName) {
        return countElementByXpath("//th[child::div[normalize-space()='" + colName + "']]//preceding-sibling::th") + 1;
    }

    String xpathFiled = "//%s[contains(text(), '%s')]";

    public void verifyFulfillmentSuccess(String mess) {
        verifyElementVisible(String.format(xpathFiled, "p", mess), true);
    }

    public void verifyOrderName(String orderName) {
        verifyElementVisible(String.format(xpathFiled, "h2", orderName), true);
    }

    public void verifyFulfillmentStatus(String status) {
        verifyElementVisible(String.format(xpathFiled, "span", status), true);
    }

    public void verifyNumberPackageFulfill(int number) {
        int act = countElementByXpath("//h2[contains(text(), 'Fulfilled by PlusHub')]");
        assertThat(act).isEqualTo(number);
    }

    public void clickButton(String buttonName) {
        clickOnElement("//button//*[normalize-space() = '" + buttonName + "']");
    }

    public void clickOut() {
        clickOnElement("//th/span[contains(text(), 'PAYMENT STATUS')]");
    }

    public void searchOrderNameFulfillment(String orderNumber) {
        waitClearAndTypeThenEnter("//input[@placeholder='Search orders']", orderNumber);
    }

    public void clickAutoPurchase(boolean _isBoolean) {
        checkCheckbox("//div[contains(@class, 'switch-out-stock')]/label", _isBoolean);
    }

    public void verifyNotification(String notification) {
        waitForEverythingComplete();
        verifyElementVisible("//div//p[contains(text(), '" + notification + "')]", true);
    }

    public void searchProduct(String product) {
        waitForEverythingComplete();
        waitClearAndTypeThenEnter("//input[@placeholder='Search product']", product);
        waitForEverythingComplete();
    }

    public String getValue(String label) {
        int count = countItem(label);
        return getText("//tr/td[" + count + "]/span");
    }

    public int countItem(String label) {
        return countElementByXpath("//th[contains(., '" + label + "')]//preceding-sibling::th") + 1;
    }

    public int countcol() {
        return countElementByXpath("//table/thead/tr/th");
    }

    public String getLabelInCol(int index) {
        String xpath = "//table/thead/tr/th[" + index + "]";
        waitForEverythingComplete();
        String label = getText(xpath);
        return label.replace("\n", " ");
    }

    public Integer getValueInCol(int index) {
        return Integer.parseInt(getText("//td[" + index + "]/span").trim());
    }

    public void clickChoiceAll() {
        checkCheckbox("//span[@data-label = 'Select all orders']//label", true);
    }

    public String getValueOrder(String label) {
        int count = countItem(label);
        return getText("//tbody/tr/td[" + count + "]//span");
    }

    public void verifyButtonService(String buttonname) {
        verifyElementVisible("//button[contains(text(), '" + buttonname + "')]", true);
    }

    public void verifySearchOrderDontExist(String orderName) {
        String xpath = "//tr[@class='tr--no-hover']//p[contains(text(), 'Could not find any orders matching " + orderName + "')or text()[normalize-space()='No order']]";
        try {
            verifyElementVisible(xpath, true);
        } catch (Throwable e) {
            refreshPage();
            waitForEverythingComplete();
            verifyElementVisible(xpath, true);
        }
    }

    public void verifyAutoPurchase(boolean _isBoolean) {
        verifyElementVisible("//p[contains(text(), 'Out of stock items will not be fulfilled in the next step')]", _isBoolean);
    }

    public void redirectToHomeShopbase() {
        clickOnElement("//li/a//i[contains(@class, 'mdi-home')]");
    }

    public float getBalance() {
        return getPrice("//div[contains(text(), 'Balance')]");
    }

    public void clickAvatar() {
        clickOnElement("//span[contains(@class, 'avatar')]/img");
    }

    public float getValuePay() {
        return getPrice("//p[contains(text(), 'Total')]//parent::div//following-sibling::div//p");
    }

    public void clickChoiceStore() {
        String xpath = "//button[contains(@class, 'btn-select-store')]";
        try {
            clickOnElement(xpath);
        } catch (Throwable t) {
            waitForEverythingComplete();
            clickOnElement(xpath);
        }
    }

    public void verifyStatusOrderList(String status, String orderName) {
        int count = getIndexCol("FULFILLMENT STATUS");
        assertThat(getText("//tr[descendant::a[normalize-space()='" + orderName + "']]/td[" + count + "]//span")).contains(status);
    }

    public void choiceOrder(String orderName) {
        String xpath = "//tr[descendant::a[normalize-space()='" + orderName + "']]/td//label[@class='s-checkbox']";
        checkCheckbox(xpath, true);
    }

    public void focusOut(String label) {
        clickOnElement("//th[child::span[normalize-space()='" + label + "']]");
    }

    public void clickShopbaseFulfillment(String label) {
        clickOnElement("//div[@class='s-dropdown-content']/span[normalize-space() = '" + label + "']");
    }

    public void logout() {
        clickOnElement("//a/div[normalize-space()='Logout']");
    }

    public void verifyProduct(String product) {
        String xpath = "//tbody/tr[1]/td[contains(., '" + product + "')]";
        try {
            verifyElementVisible(xpath, true);
        } catch (Throwable t) {
            for (int i = 1; i < 3; i++) {
                refreshPage();
                searchProduct(product);
                if (isElementExist(xpath, 30)) {
                    break;
                }
            }
            verifyElementVisible(xpath, true);
        }
    }

    public void clickTitleFilter(String title) {
        clickOnElement("//div[@class='sb-collapse-item sb-p-medium' and contains(., '" + title + "')]");
    }

    public void verifyResultTag(String title, String condition) {
        String xpath = "//div[contains(text(), '" + title + ": " + condition + "') and contains(@class, 'sb-tag')]";
        waitElementToBeVisible(xpath, 20);
        verifyElementVisible(xpath, true);
    }

    public void verifyButtonFilter(String name, boolean isBoolean) {
        verifyTextPresent(name, isBoolean);
    }

    //    String xpathFilter = "//nav[contains(@class, 's-tabs-nav')]//span[contains(text(),'%s')]";
    String xpathFilter = "//div[contains(@class, 'sb-tab-navigation')]//div[contains(text(),'%s')]";

    public void verifyFilterName(String filterName, boolean isBoolean) {
        String xpath = String.format(xpathFilter, filterName);
        if (isBoolean) {
            waitElementToBeVisible(xpath);
        } else {
            waitForElementNotVisible(xpath);
        }
        verifyElementVisible(xpath, isBoolean);
    }

    public void clickTab(String filterName) {
        clickOnElement(String.format(xpathFilter, filterName));
    }

    public void clickTabFulfillOrder(String tabName) {
        String xpath = "//div[@class='fulfillment']//ul/li//*[contains(normalize-space(),'" + tabName + "')]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void actionFilter(int index) {
        clickOnElement("//div[child::span[contains(@class, 'cursor-pointer')]][" + index + "]");
    }

    public HashMap<String, String> getFilterDefault() {
        HashMap<String, String> data = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            String tabName = getText("//div[contains(@id, 'tab-navigation')]/div[" + i + "]/div").split("[(]")[0].trim();
            data.put(tabName, tabName);
        }
        return data;
    }

    public HashSet<String> getFilterTemplate() {
        HashSet<String> data = new HashSet<>();
        int count = countElementByXpath("//span/div[@class='filter-item']");
        for (int i = 1; i <= count; i++) {
            data.add(getText("//span[" + i + "]/div[@class='filter-item']/div"));
        }
        return data;
    }

    public void verifyFilterSave(String filterName) {
        String xpath = "//nav/ul/li//span[contains(text(), '" + filterName + "')]";
        waitElementToBeVisible(xpath, 20);
        verifyElementVisible(xpath, true);
    }

    public String getValueInput(String filterName) {
        return getTextValue("//input[@placeholder='" + filterName + "']");
    }

    public void checkCheckboxWithLabelInSBDS(String condition) {
        String xpath = "//div[contains(@class,'sb-selection-item--css-hovered') and descendant::div[normalize-space()= '" + condition + "']]//label";
        checkCheckbox(xpath, true);
    }

    public void verifyResultFilter(String val, String title) {
        String xpath = "//table/tbody/tr";
        int count = countElementByXpath(xpath);
        int countEle = getIndexCol(title);
        if (count > 0) {
            assertThat(val.toLowerCase()).isEqualTo(getText(xpath + "[1]/td[" + countEle + "]//span").toLowerCase());
        } else {
            verifyElementVisible("//h3[normalize-space()='Could not find any orders matching']", true);
        }
    }

    public void showVariantOfProduct(String product) {
        clickOnElement("//span[contains(text(), '" + product + "') and contains(@class, 'product-list__name')]");
    }

    public void viewLineItem(String label) {
        clickOnElement("//span[normalize-space()='" + label + "']");
    }

    public void showPopupReplace() {
        String xpath = "//tbody[1]/tr/td[last()]/div/div[last()]/div[contains(@class, 'cursor-pointer')]";
        waitElementToBeVisible(xpath, 30);
        clickOnElement(xpath);
    }

    public void verifyVariantActive(String attr, String val) {
        String xpath = "//div[contains(@class, 'flex-wrap') and child::div[normalize-space()='" + attr + "']]//span[contains(@class, 'is-active') and child::span[normalize-space()='" + val + "']]";
        waitElementToBeVisible(xpath, 10);
        verifyElementVisible(xpath, true);
    }

    public void choiceProduct(String product) {
        clickOnElement("//p[contains(text(), '" + product + "')]");
    }

    public void choiceVariant(String attr, String val) {
        clickOnElement("//div[child::div[normalize-space()='" + attr + "']]//span[normalize-space()='" + val + "']");
    }

    public void verifyShowButton(String label, boolean isBoolean) {
        verifyElementVisible("//button[child::span[normalize-space()='" + label + "']]", isBoolean);
    }

    public int getItemOfInventory(String label) {
        int count = countItem(label);
        return Integer.parseInt(getText("//tbody/tr/td[" + count + "]/span"));
    }

    public int getItemOfVariantInventory(String label, String variant) {
        int count = countElementByXpath("//div[@class='product-list']/div/table/thead/tr/th[contains(., '" + label + "')]//preceding-sibling::th") + 1;
        String xpath = "//tr[descendant::span[contains(@class, 'product-list__sub-name') and normalize-space()='" + variant + "'] and @class='cursor-default']/td[" + count + "]/span";
        return Integer.parseInt(getText(xpath));
    }

    public void moveFulfillmentPage(String label) {
        clickOnElement("//li[child::div[normalize-space()='PlusHub']]");
    }

    public void moveTab(String tabName) {
        clickOnElement("//li//span[contains(text(), '" + tabName + "')]");
        waitABit(3000);
    }

    public void unselectAllOrder() {
        checkCheckbox("//div[descendant::span[normalize-space()='Hold'] and contains(@class, 'orders-actions')]/label", false);
    }

    public void searchOrder(String orderNumber) {
        String xpath = "//input[@placeholder='Search orders']";
        waitClearAndTypeThenEnter(xpath, orderNumber);
        waitForEverythingComplete();
    }

    public String getTextNoti() {
        String xpath = "//p[contains(@class,'text-bold')]";
        return getText(xpath).trim();
    }

    public List<String> getListLineItemName() {
        int index = getIndexOfColumn("ORDER");
        String xpath = "//tbody//td[" + index + "]//div[contains(@class,'font-weight-bold')]";
        return getListText(xpath);
    }

    public String getOrder(String nameColumn, int x) {
        int index = getIndexOfColumn(nameColumn);
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]//p[" + index + "]";
        return getText(xpath).trim();
    }

    public String getProduct(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "//tbody//tr[" + x + "]//td[" + index + "]//div[contains(@class,'font-gray')]";
        return getTextByJS(xpath).trim();
    }

    private int getIndexOfCol(String nameColumn) {
        String xpath = "//th[child::span[normalize-space()='" + nameColumn + "']]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public String getQuantity(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "//tbody//tr[" + x + "]//td[" + index + "]";
        return getTextByJS(xpath).trim();
    }

    public String getOrders(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        int index2 = index - 1;
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]//div//div[" + index2 + "]";
        return getTextByJS(xpath).trim();
    }

    public String getOrderA(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]//div[" + index + "]";
        return getTextByJS(xpath).trim();
    }

    public String getProducts(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]//div//descendant::div[" + index + "]//following-sibling::span";
        return getTextByJS(xpath).trim();
    }

    public String getWarehouseProduct(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]//div//descendant::div[" + index + "]";
        return getTextByJS(xpath).trim();
    }

    public String getQuantitys(String nameColumn, int x) {
        int index = getIndexOfCol(nameColumn);
        String xpath = "(//tbody//tr[" + x + "]//td)[" + index + "]";
        return getTextByJS(xpath).trim();
    }

    public String getBaseCostPerItem(String nameColumn, int x) {
        String xpath = "//tbody//tr[" + x + "]//td[@class='base-cost']//span";
        return getTextByJS(xpath).trim();
    }

    public void userMoveToScreen(String tab) {
        clickOnElement("//div[child::div[contains(@class, 'PlusHub')]]//a[normalize-space()='" + tab + "']");
    }

    public void searchProductReplace(String product) {
        waitClearAndType("//div[contains(@class, 'is-focusable')]//input", product);
        waitForEverythingComplete();
    }

    public boolean isNoOrder() {
        return isElementExist("//p[text()[normalize-space()='No order']]");
    }

    public void selectOrderFulfill() {
        checkCheckbox("//th[contains(@class, 'order-select-header')]//label", true);
    }

    public String getShippingOfOrder(String label) {
        int index = countItem(label);
        String xpath = "//tr[1]/td[" + index + "]/*";
        return getText(xpath);
    }

    public void verifyTotalOrder(String shipping) {
        boolean flg = Float.parseFloat(shipping.replace("$", "")) > 0 ? true : false;
        verifyElementVisible("//div[@class='row' and descendant::p[normalize-space()='Total']]//p[normalize-space()='" + shipping + "']", flg);
    }

    public void verifyInvoiceDetail(String label, String val) {
        int index = countItem(label);
        verifyElementVisible("//tr/td[" + index + "][child::span[contains(text(),'" + val + "')]]", true);
    }

    public boolean isButtonExit(String label) {
        return isElementExist("//button[child::span[normalize-space()='" + label + "']]", 5);
    }

    public void clickBTMapp(String variant) {
        String xpath = "(//td[descendant::p[contains(text(),'" + variant + "')]]//following-sibling::td)[2]//button";
        clickOnElement(xpath);
    }

    public String getcountOrder() {
        String xpath = "//li[@class='is-active']//span";
        waitForEverythingComplete();
        return getTextByJS(xpath);
    }

    public int getListLineItems() {
        String xpath = "//div[contains(@class,'table-responsive')]//tbody//tr";
        return countElementByXpath(xpath);
    }

    public String getValueInWarehouse(String label) {
        int count = countItem(label);
        int number = Integer.parseInt(getText("//tr/td[" + count + "]/span")) + 1;
        return Integer.toString(number);
    }

    public String getAutoFilterOrder() {
        String xpath = " //div[contains(@class,'justify-content-space-between align-items-center')]/div";
        return getText(xpath).trim();
    }

    public void turnOffFilterAuto() {
        String xpath = "//span[contains(@class,'s-icon cursor-pointer')]/i";
        if (isElementExist(xpath)) {
            focusClickOnElement(xpath);
        }
    }

    public void verifyStatusOfBtnGetTrackingNumber(boolean is_disable) {
        String xpathGetTrackingNumberbtn = "(//button[contains(@class,'fulfillment-btn__active')])[1]";
        String getAttribute = getAttributeValue(xpathGetTrackingNumberbtn, "class");
        boolean status = true;
        if (getAttribute.contains("is-disabled") == true) {
            status = false;
        }
        Assert.assertEquals(status, is_disable);
    }

    public void verifyHeaderTab(String label) {
        verifyElementVisible("//th[child::*[text()[normalize-space()='" + label + "']]]", true);
    }

    String xpathEmptyOrder = "//*[text()[normalize-space()='Could not find any order matching' or normalize-space()='No orders found']]";

    public void verifyIcon(String className) {
        if (!isElementExist(xpathEmptyOrder)) {
            verifyElementVisible("//*[@class = '" + className + "']", true);
        }
    }

    public void verifyShowButton(String btnName) {
        String xpath = "//span[child::img[@alt='" + btnName + "']]";
        if (!isElementExist(xpathEmptyOrder)) {
            verifyElementVisible(xpath, true);
        }
    }


    public void searchOrderPlusBase(String orderNumber) {
        waitClearAndTypeThenEnter("//input[@placeholder='Search orders']", orderNumber);
        String xpathNoOrder = "//td[@class='no-data']//p[contains(text(),'Could not find any order matching')]";
        int i = 1;
        while (isElementExist(xpathNoOrder) && i <= 10) {
            refreshPage();
            String xpath = "//span[contains(@class,'s-icon cursor-pointer')]/i";
            if (isElementExist(xpath)) {
                focusClickOnElement(xpath);
            }
            waitForEverythingComplete();
            i++;
        }
        String xpath = "//span[contains(@class,'s-icon cursor-pointer')]/i";
        if (isElementExist(xpath)) {
            focusClickOnElement(xpath);
        }
        waitForEverythingComplete();
    }

    public void cancelFulfillmentOrder() {
        String xpath = "//img[@alt='Cancel fulfillment']";
        clickOnElement(xpath);
        waitForEverythingComplete();
        String xpathConfirm = "//button[normalize-space()='Confirm']";
        clickOnElement(xpathConfirm);
        waitForEverythingComplete();
    }

    public void clickCancelFilter() {
        waitForEverythingComplete();
        clickOnElement("//i[@class='mdi mdi-close mdi-18px']");
    }

    public void tickSelectOrder(String orderNumber) {
        checkCheckbox("//th[contains(@class, 'order-select-header')]//label", true);
    }

    public void verifyStatusOrderInWarehouse(String status, String orderNumber, String label) {
        String xpath = "//span[contains(text(),'" + status + "')]";
        int i = 1;
        while (isElementExist(xpath) && i <= 10) {
            clickOnElement(xpath);
            waitForEverythingComplete();
            refreshPage();
            i++;
        }
        clickOnElement("//div[contains(text(),'" + label + "')]/parent::div//i");
        isElementExist("//*[contains(text(),'" + orderNumber + "')]");
    }

    public void inputTextLineItemSearch(String textTagLineItem, String status) {
        clickOnElement("//p[contains(text(),'Line item tags')]");
        clickRadioTagLineItem(status);
        clearAndInputTextByJS("//input[@placeholder='Search line item tags name']", textTagLineItem);
    }

    public void verifyProductIsExit() {
        isElementExist("//div[contains(text(),'" + orderNumber + "')]");
    }

    public void clickRadioTagLineItem(String status) {
        clickOnElement("//*[contains(@class, 'active') and descendant::*[normalize-space()='Line item tags']]//*[(text()[normalize-space()=\"" + status + "\"])]");
    }

    public void verifyProductUnavailable(String msg) {
        verifyElementVisible("//p[contains(text(),'" + msg + "')]", true);
    }

    public void selectOrderInWarehouse() {
        clickOnElement("//span[@data-label='Select all line items']//span[@class='s-check']");
    }

    public boolean isBalanceNotEnough() {
        String xpath = "//span[text()[normalize-space()='Top up']]/parent::button";
        return isElementExist(xpath);
    }

    public String getTotalOnMakePayment() {
        String xpath = "//p[text()='Total']//ancestor::div[@class='row']//descendant::div[2]//p";
        return getText(xpath);
    }

    public void verifyWarehouseProductName(String product) {
        int index = getIndexOfCol("WAREHOUSE PRODUCT");
        String xpath = "//tbody//tr[1]//td[" + index + "]//div[contains(@class,'text-decoration-none')]//div";
        String act = getText(xpath);
        assertThat(act).isEqualTo(product);
    }

    public void verifyWarehouseVariantName(String variant) {
        int index = getIndexOfCol("WAREHOUSE PRODUCT");
        String xpath = "//tbody//tr[1]//td[" + index + "]//div[@class='sub-text']";
        String act = getText(xpath);
        assertThat(act).isEqualTo(variant);
    }

    public ArrayList getProductSB() {
        int index = getIndexOfCol("LINE ITEM");
        String xpath = "//tbody//tr//td[" + index + "]//p[contains(@class,'font-gray')]";
        int count = countElementByXpath(xpath);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= count; i++) {
            String xpath1 = "(//tbody//tr//td[" + index + "]//p[contains(@class,'font-gray')])[" + i + "]";
            arrayList.add(getText(xpath1));
        }
        return arrayList;
    }

    public ArrayList getVariantSB() {
        int index = getIndexOfCol("LINE ITEM");
        String xpath = "//tbody//tr//td[" + index + "]//p[contains(@class,'sub-text')]";
        int count = countElementByXpath(xpath);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= count; i++) {
            String xpath1 = "(//tbody//tr//td[" + index + "]//p[contains(@class,'sub-text')])[" + i + "]";
            arrayList.add(getText(xpath1));
        }
        return arrayList;
    }

    public int getIndexLineItem(String product, String variant) {
        String xpath = "//p[contains(text(),'" + product + "')]//ancestor::a//p[contains(text(),'" + variant + "')]//ancestor::tr//preceding-sibling::tr";
        return countElementByXpath(xpath) + 1;
    }

    public String getQuantityLineItem(int index) {
        int i = getIndexOfCol("QUANTITY");
        String xpath = "//tbody//tr[" + index + "]//td[" + i + "]//p";
        return getText(xpath);
    }

    public String getShippingLineItem(int index) {
        int i = getIndexOfCol("SHIPPING");
        String xpath = "//tbody//tr[" + index + "]//td[" + i + "]//p";
        return getText(xpath);
    }

    public String getBaseCostLineItem(int index) {
        int i = getIndexOfCol("BASE COST PER ITEM");
        String xpath = "//tbody//tr[" + index + "]//td[" + i + "]//p";
        return getText(xpath);
    }

    public void verifyShowOrder(String order, boolean isShow) {
        String el = "//div[text()='" + order + "']//ancestor::tr";
        verifyElementPresent(el, isShow);
    }

    public void clearFilterDate() {
        String el = "//div[contains(text(),'Order Date')]/parent::div//i";
        clickOnElement(el);
    }
}
