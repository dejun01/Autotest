package com.opencommerce.shopbase.Pbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class RefundOrderPrintBasePage extends SBasePageObject {
    public RefundOrderPrintBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOrder(String orderName) {
        String xpath = "//tbody//td//a[contains(text(), '" + orderName + "')]";
        clickOnElement(xpath);
    }

    public void verifyShowOrderName(String orderName) {
        String xpath = "//section[@class = 'content']/h5";
        waitElementToBeVisible(xpath);
        assertThat(getText(xpath)).contains(orderName);
    }

    public void clickButton(String name) {
        String xpath = "//div[contains(@class, 'form-actions')]//a[contains(text(), '" + name + "')]";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void verifyOrderNameRefund(String orderName) {
        String xpath = "//section[@class = 'content']//h5[1]";
        waitElementToBeVisible(xpath);
        assertThat(getText(xpath)).contains(orderName);
    }

    public void verifyQuantityRefund(String quantity) {
        int index = getIndexElemInHive("Quantity");
        String xpathquantity = "//tbody//td[" + index + "]//a";
        clickOnElement(xpathquantity);
        String xpath = "//ul[contains(@id , 'select2-results')]/li[last()]";
        assertThat(quantity).isEqualTo(getText(xpath));
    }

    public String getAvailableAmount(String propertie) {
        String xpath = "//input[@id = '" + propertie + "']";
        return getAttributeValue(xpath, "max");
    }

    public String getRefundedAmount(String propertie) {
        String xpath = "//input[@id = '" + propertie + "']";
        return getValue(xpath);
    }

    public void choiceQuantity(String quantity) {
        String xpath = "//ul[contains(@id , 'select2-results')]/li/div[contains(text(), '" + quantity + "')]";
        clickOnElement(xpath);
    }

    public void fillInValue(String label) {
        String xpath = "//div[child::label[text()='" + label + "']]//following-sibling::div/input[not(@disabled)]";
        String value = getAttributeValue(xpath, "max");
        waitClearAndTypeThenTab(xpath, value);
    }

    public void focuOut() {
        String xpath = "//label[contains(text(), 'Refund base cost')]";
        focusClickOnElement(xpath);
    }

    String xpathDef = "//table//tr[descendant::a[normalize-space()='%s']]/td[%d]";

    public void verifyStatusOrder(String label, String orderName, String status) {
        int index = getIndexElemInSBase(label);
        String xpath = String.format(xpathDef, orderName, index) + "//*[text()[normalize-space()='" + status + "']]";
        waitElementToBeVisible(xpath, 30);
    }

    public float getProfit(String label, String orderName) {
        int index = getIndexElemInSBase(label);
        String xpath = String.format(xpathDef, orderName, index);
        return getPrice(xpath);
    }

    public int countFieldByLabelBlock(String blockName) {
        return countElementByXpath("//div[@class='box box-primary'][descendant::label[text()='" + blockName + "']]//input[@type='number']");
    }

    String xpathFiled = "(//div[@class='box box-primary'][descendant::label[text()='%s']] //div[@class='form-group clearfix'])[%d]";

    public String getLabelInBlockByIndex(String blockName, int index) {
        String xpath = String.format(xpathFiled + "//label", blockName, index);
        return getText(xpath);
    }

    public float getValueRefundedByIndex(String blockName, int index) {
        String xpath = String.format(xpathFiled + "//input", blockName, index);
        return getValue(xpath).isEmpty() ? 0 : removeCurrency(getValue(xpath));
    }

    public float getValueAvailableByIndex(String blockName, int index) {
        String xpath = String.format(xpathFiled + "//input", blockName, index);
        return removeCurrency(getAttributeValue(xpath, "max"));
    }

    public int getIndexElemInHive(String colname) {
        return countElementByXpath("//th[contains(text(), '" + colname + "')]//preceding-sibling::th") + 1;
    }

    public int getIndexElemInSBase(String colname) {
        return countElementByXpath("//th[child::span[contains(text(), '" + colname + "')]]/preceding-sibling::th") + 1;
    }

    public void verifyOrderName(String orderName) {
        String xpath = "//div[contains(@class, 'title-bar__heading-group') and contains(., '" + orderName + "')]";
        verifyElementVisible(xpath, true);
    }

    public void verifyOrderStatusOrderDetail(String status) {
        String xpath = "//div[contains(@class, 'title-bar__heading-group')]//span[contains(text(), '" + status + "')]";
        verifyElementVisible(xpath, true);
    }

    public void verifyRefundQuantity(int quantity) {
        String xpath = "//div[@class='card__header']//h2[@class='stack-item__title']";
        String orderRemoved = "items(" + quantity + ")";
        assertThat(getText(xpath)).contains(orderRemoved);
    }

    public float getPriceCustomer(String label) {
        String xpath = "//tr[child::td[contains(text(),'" + label + "')]]/td[last()]";
        return getPrice(xpath);
    }

    public float getValueForLabel(String label) {
        String xpath = "//div[child::span[contains(text(),'" + label + "')]]/span[last()]";
        return getPrice(xpath);
    }

    public void verifyProfit(String label, Float value) {
        String xpath = "//span[text()='" + label + "']//ancestor::div[contains(@class, 'type--bold')]/span[last()]";
        float profit = Float.parseFloat(getText(xpath).replace("$", ""));
        assertThat(value).isBetween(profit - 0.01f, profit + 0.01f);
    }

    public void verifyRefundTimeLine(Float refundedAmount) {
        String xpath = "//div[contains(@class, 'timeline')][1]/div[@class='content']//div[contains(@class,'content-body')]";
        String str = "ShopBase refunded $" + refundedAmount.toString();
        try {
            assertThat(getText(xpath)).contains(str);
        } catch (Throwable t) {
            waitForEverythingComplete(20);
            refreshPage();
            assertThat(getText(xpath)).contains(str);
        }
    }

    public void clickLinkWithLabel(String label) {
        String xpath = "//a[contains(@class, 'dropdown-item')]//div[contains(text(), '" + label + "')]";
        clickOnElement(xpath);
    }

    public void clickAvatar() {
        String xpath = "//span[contains(@class, 'avatar')]/img";
        clickOnElement(xpath);
    }

    public void clickTypeFilter() {
        String xpath = "//div[contains(@class,'small-popup')]//div[contains(@class, 'is-expanded') and not(@style)]/select";
        clickOnElement(xpath);
    }

    public void clickOptionTypeFilter() {
        String xpath = "//div[contains(@class,'small-popup')]//div[contains(@class, 'is-expanded') and not(@style)]//option[contains(text(),'Type')]";
        clickOnElement(xpath);
    }

    public void clickSelectType() {
        String xpath = "//div[contains(@class,'small-popup')]//div[contains(@class, 'is-expanded') and not(@style)]//option[contains(text(),'Type')]";
        clickOnElement(xpath);
    }

    public void clickOptionType() {
        String xpath = "//div[contains(@class,'small-popup')]//div[@class= 's-select is-expanded']//option[contains(text(), 'In')]";
        clickOnElement(xpath);
    }

    public void moveToInvoiceDetail(String orderName, String nameShop) {
        String xpath = "(//span[text()='" + nameShop + "']//ancestor::tr//span[text()='PlusBase order collecting'])[1]";
        String xpathOrder = "//div[@class='d-flex f-1']/div[@class='d-flex flex-column']/div[last()]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        switchToLatestTab();
        assertThat(getText(xpathOrder)).contains(orderName);
    }

    public void verifyInvoiceDetail(Float IN, Float OUT, boolean withdrawSellerBalance) {
        int count = getIndexElemInSBase("Amount");
        String xpathOUT = "//tbody//span[contains(text(), 'refund for buyer')]//ancestor::tr/td[" + count + "]/span";
        String xpathIN = "//tbody//span[contains(text(), 'order_Refund for seller')]//ancestor::tr/td[" + count + "]/span";
        if (withdrawSellerBalance) {
            verifyElementVisible(xpathOUT, false);
        } else {
            assertThat(getPrice(xpathOUT)).isEqualTo(OUT);
        }
        assertThat(getPrice(xpathIN)).isEqualTo(IN);
    }

    public void clickDropDown() {
        String xpath = "//i[contains(@class, 'mdi-arrow-down-drop-circle-outline')]";
        clickOnElement(xpath);
    }

    public void userNavigateScreen(String tabName) {
        String xpath = "//a//span[contains(text(), '" + tabName + "')]";
        try {
            clickOnElement(xpath);
        } catch (Throwable e) {
            waitABit(4000);
            clickOnElement(xpath);
        }
        waitForEverythingComplete();
    }

    public void searchEmailInMailinator(String email) {
        String xpath = "//input[@placeholder='Enter Public Mailinator Inbox']";
        waitClearAndTypeThenEnter(xpath, email);
    }

    public void showCalculation() {
        String xpath = "//span[contains(text(), 'Show calculation')]";
        clickOnElement(xpath);
    }

    public void verifyApprovedButton(String label) {
        if (!isElementVisible("//label[text()= '" + label + "']", 200)) {
            clickBtn("Approve");
        }
    }

    public void clickViewHistory(String buttonName) {
        try {
            clickBtn(buttonName);
        } catch (Throwable t) {
            waitForEverythingComplete(20);
            refreshPage();
            waitABit(10);
            clickBtn(buttonName);
        }
    }

    public void clickApporveButton(String buttonName) {
        String xpath = "//button[text()[normalize-space()='" + buttonName + "']]";
        if (isElementExist(xpath)) {
            clickBtn(buttonName);
        }
    }

    public void clickToSection(String nameScreen) {
        String xPath = "//*[normalize-space()='" + nameScreen + "']";
        waitElementToBePresentThenScrollIntoView(xPath).waitUntilEnabled().waitUntilVisible();
        clickOnElement(xPath);
    }

    public boolean isBoolean(String label) {
        String xpath = "//*[@class='box-body']//*[contains(@class, 'form-group') and contains(., '" + label + "')]";
        float value = removeCurrency(getValue(xpath + "//input"));
        float available = getPrice(xpath + "/div[last()]");
        return value > available;
    }

    public void searchOrder(String orderNumber) {
        waitClearAndTypeThenEnter("//*[@class='order-search-form']//input[@placeholder='Search orders']", orderNumber);
    }
}
