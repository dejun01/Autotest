package com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;

import static com.opencommerce.shopbase.OrderVariable.orderNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class ManageOrdersPage extends SBasePageObject {
    float productCost, totalCost, actualTotal;

    public ManageOrdersPage(WebDriver driver) {
        super(driver);
    }

    public void searchOrderPhub(String keyword) {
        String xpathSearchBox = "//div[@class='order-manager']//input[@placeholder='Search orders by name or product name']";
        waitTypeAndEnter(xpathSearchBox, keyword);
        String xpathOrderName = "//div[@class='table-responsive']//*[@class='cursor-pointer']//span[text()[normalize-space()='" + keyword + "']]";
        String xpathNoOrder = "//*[@class='no-order']";
        int i = 1;
        while (isElementExist(xpathNoOrder) && i <= 20) {
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        waitForEverythingComplete();
        verifyElementPresent(xpathOrderName, true);

    }

    String xpath_order_phub = "//*[text()[normalize-space()='%s']]";

    public void openOrderDetail(String orderName) {
        String xpathOrderBlock = String.format(xpath_order_phub, orderName);
        int i = 0;
        while (i < 10 && !isElementExist(xpathOrderBlock)) {
            waitABit(10000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        verifyElementPresent(xpathOrderBlock, true);
        clickOnElementByJs(xpathOrderBlock);
    }

    public void verifyOrderStatus(String orderName, String status) {
        String xpathStatus = "(" + String.format(xpath_order_phub, orderName) + "//ancestor::tr//span[contains(@class,'is-green')]//span)[2]";
        String st = getTextByJS(xpathStatus);
        Assert.assertEquals(st, status);
    }

    String xpath_order_pbase = "//div[@class='d-flex']/a[normalize-space()='%s']";

    public void openOrderDetail_pBase(String orderName) {
        String xpathOrderBlock = String.format(xpath_order_pbase, orderName);
        verifyElementPresent(xpathOrderBlock, true);
        clickOnElementByJs(xpathOrderBlock);
        waitUntilElementVisible("//div[@id='order-title-bar']", 50);
        String showcalulation = "//span[text()[normalize-space()='Show calculation']]";
        String handlingfee = "//span[@class='s-icon is-small cursor-pointer']";
        verifyElementPresent(showcalulation, true);

        clickOnElement(showcalulation);
        clickOnElement(handlingfee);
    }

    public void verifyTotal(float total, String orderName) {
        String xpathTotal = "//span[normalize-space()='" + orderName + "']/ancestor::tr//span[contains(@class,'order-total-value')][1]";
        actualTotal = Float.parseFloat(price(getTextByJS(xpathTotal)));
        assertThat(actualTotal).isEqualTo(total);
    }

    public float getValueOrder(String orderName) {
        String xpathCost = String.format(xpath_order_phub, orderName) + "//span[contains(@class,'order-total-value')][1]";
        float valueOrder = Float.parseFloat(price(getTextByJS(xpathCost)));
        return valueOrder;
    }

    public void verifyProductCost() {
        String xpathCost = "//span[text()[normalize-space()='Product cost:']]//b";
        float cost = Float.parseFloat(xpathCost.replace("$", ""));

    }

    public void verifyTotalCost() {
        String xpathParent = "//div[@class='row']//*[@class='text-right']";
        String xpathTotalCost = xpathParent + "[4]";
        totalCost = Float.parseFloat(price(getTextByJS(xpathTotalCost)));

        String xpathShipping = xpathParent + "[2]";
        float shipping = Float.parseFloat(price(getTextByJS(xpathShipping)));

        String xpathTax = xpathParent + "[3]";
        float tax = Float.parseFloat(price(getTextByJS(xpathTax)));
        float expectedTotalCost = Math.round((productCost + shipping + tax) * 100.0f) / 100.0f;
        assertThat(totalCost).isEqualTo(expectedTotalCost);
    }

    public void verifyTotalProfit() {
        String xpathProfit = "(//div[@class='row']//*[@class='text-green text-right'])[1]";
        float actualProfit = Float.parseFloat(price(getTextByJS(xpathProfit)));
        float expectedProfit = Math.round((actualTotal - totalCost) * 100.0f) / 100.0f;
        assertThat(expectedProfit).isEqualTo(actualProfit);
    }

    String xpath_lineItem = "//div[child::p[normalize-space()='%s'] and child::p[normalize-space()='%s']]";

    public void verifyLineItems(String productName, String lineitems, boolean isSync) {
        String xpathLineItems = String.format(xpath_lineItem, productName, lineitems);
        verifyElementPresent(xpathLineItems, isSync);
    }

    public String getStatusLineItem(String productName, String lineItems) {
        String xpath = String.format(xpath_lineItem, productName, lineItems) + "//following-sibling::div//span[@class='tag-status-text']";
        String status = getTextByJS(xpath);
        return status;
    }

    public void verifySKU(String productName, String lineitems, String sku) {
        String xpathSKU = String.format(xpath_lineItem, productName, lineitems) + "//following-sibling::p[2]";
        String actualSKU = getTextByJS(xpathSKU);
        Assert.assertEquals(actualSKU.contains(sku), true);

    }

    public void switchToNameTab(String tabName) {
        String xpathTab = "//div[@class='s-tabs']//p[contains(text(),'" + tabName + "')]";
        String xpathItem = "//tr//a[contains(@href,'/admin/order')]";
        String xpathActiveTab = xpathTab + "/ancestor::li[@class='is-active']";
        int i = 0;
        while (i < 20 && !isElementClickable(xpathTab)) {
            waitABit(10000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        clickOnElement(xpathTab);
        verifyElementPresent(xpathActiveTab, true);
        waitForElementToPresent(xpathItem);
    }

    public int getOrderAmount(String orderName) {
        String xpathSup = "//span[contains(text(),'" + orderName + "')]/ancestor::tr/following-sibling::tr[1]//div[@class='title__supplier']/span";
        waitElementToBePresent(xpathSup);
        return countElementByXpath(xpathSup);
    }

    public String getStatusOrder(String orderName) {
        String xpathStatus = "(//span[text()[normalize-space()='" + orderName + "']]//ancestor::tr//span[contains(@class,'is-green')]//span)[2]";
        String st = getTextByJS(xpathStatus);
        return st;
    }

    String xpath_infomation = "//span[normalize-space()='%s']/ancestor::tr/following::tr[1]//span[contains(text(),'%s')]/b";


    public Float getProductCost(String orderName) {
        String xpathSup = String.format(xpath_infomation, orderName, "Product cost");
        Float productCost;
        productCost = Float.parseFloat(price(getTextByJS(xpathSup)));
        return productCost;
    }

    public float getPriceShip(String orderName) {
        String xpathSup = String.format(xpath_infomation, orderName, "Shipping");
        waitElementToBePresent(xpathSup);
        Float priceShip;
        priceShip = Float.parseFloat(price(getTextByJS(xpathSup)));
        return priceShip;
    }

    public float getTax(String orderName) {
        String xpathSup = String.format(xpath_infomation, orderName, "Tax");
        waitElementToBePresent(xpathSup);
        Float tax = Float.parseFloat(price(getTextByJS(xpathSup)));
        return tax;
    }

    public float getTotalCost(String orderName) {
        String xpathTotalCost = String.format(xpath_infomation, orderName, "Total Cost");
        totalCost = Float.parseFloat(price(getTextByJS(xpathTotalCost)));
        return totalCost;
    }

    public float getInfOrder(String orderName, String field) {
        String xpathTotalCost = String.format(xpath_infomation, orderName, field);
        float value = Float.parseFloat(price(getTextByJS(xpathTotalCost)));
        return value;
    }

    public float getTotalProfit(String orderName) {
        String xpathTotalCost = String.format(xpath_infomation, orderName, 5);
//                "//a[normalize-space()=\"" + orderName + "\"]/ancestor::tr/following::tr[1]//div[@class='row']//*[@class='text-right'][5]";
        float totalProfit = Float.parseFloat(price(getTextByJS(xpathTotalCost)));
        return totalProfit;
    }

    public void clickOrderNumber(String numberOrder) {
        clickOnElement("//a[contains(text() ,'" + numberOrder + "']]");
        waitForEverythingComplete();
        waitABit(3000);
    }

    public void clickToOrderDetailButton(String product, String btn) {
        String button = "//p[contains(text(),'" + product + "')]/ancestor::div[@class='m-b-sm']//button//span[contains(text(),'Action')]";
        clickOnElementByJs(button);
        String xpath_action = button + "/ancestor::div[contains(@class,'s-dropdown d')]";
        String xpathButton = xpath_action + "//span[contains(text(),\"" + btn + "\")]";
        clickOnElementByJs(xpathButton);
    }

    public void clickConfirm(String confirmBtn) {
        String xpath = "//button/span[contains(text(),\"" + confirmBtn + "\")]";
        waitUntilElementVisible(xpath, 50);
        clickOnElementByJs(xpath);
    }

    public void verifySKU(String sku) {
        String xpath = "//div[@class='col-xs-5 col-sm-5']//p[contains(normalize-space(),'" + sku + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyVariantOrderPhub(String variants) {
        String xpath = "//p[normalize-space()='" + variants + "']";
        assertThat(getTextByJS(xpath)).isEqualTo(variants);
    }

    public void verifyCost(String cost) {
        String xpath = "//div[text()[normalize-space()='Cost:']]//b";
        String textCost = getTextByJS(xpath).replace("$", "");
        assertThat(textCost).isEqualTo(cost);
    }


    public void verifyCostByItem(String baseProduct, String cost) {
        String xpath = "//div[child::p[contains(normalize-space(),'" + baseProduct + "')]]//following-sibling::div[text()[normalize-space()='Cost:']]//b";
        String textCost = getTextByJS(xpath).replace("$", "");
        assertThat(textCost).isEqualTo(cost);
    }

    public void verifyStatus(String status) {
        String xpath = "//span[@class='tag-status-text']";
        assertThat(getTextByJS(xpath)).isEqualTo(status);
    }

    public void verifyFieldOrder(String field, String value) {
        String xpath = "//span[text()[normalize-space()='" + field + "']]//b";
        String valueActual = getTextByJS(xpath).replace("$", "");
        assertThat(valueActual).isEqualTo(value);
    }

    public void verifyProfit(String profit) {
        String xpath = "//td[@class='type--bold']//div";
        assertThat(getTextByJS(xpath)).isEqualTo(profit);
    }

    public void verifyTotalCost(Float totalCost) {
        String xpath = "//span[text()[normalize-space()='Total Cost:']]//b";
        String text = getTextByJS(xpath).replace("$", "");
        Float actTotalCost = Float.parseFloat(text);
        assertThat(actTotalCost).isEqualTo(totalCost);
    }

    public void clickMappingProduct() {
        String xpath = "//button[normalize-space()='Map product']";
        int i = 0;
        while (i < 20 && !isElementExist(xpath)) {
            waitABit(100000);
            refreshPage();
            waitForEverythingComplete();
            waitElementToBePresent(xpath);
            i++;
        }
        clickOnElementByJs(xpath);
        waitForEverythingComplete();
    }

    public void clickEditMappingProduct() {
        String xpath = "//button[normalize-space()='Edit mapping']";
        clickOnElementByJs(xpath);
        waitForEverythingComplete();
    }

    public void searchAndSelectOrder(String order) {
        String xpath_Search = "//input[@placeholder='Search orders by name or product name']";
        waitClearAndType(xpath_Search, order);
        waitForEverythingComplete();
        String xpath_select = "//td[@class='checkbox']//label";
//        clickOnElement(xpath_select);
        hoverThenClickElement("//td[@class='checkbox']//span", "//td[@class='checkbox']//label");
    }

    public void clickBtnActionOrderImportPhub() {
        String xpath = "//button[child::span[text()[normalize-space()='Actions']]]";
        clickOnElement(xpath);
    }

    public void selectPayForSelectedOrderImportPhub() {
        String xpath = "//span[text()[normalize-space()='Pay for selected orders']]";
        clickOnElement(xpath);
    }

    public void clickConfirmPaymentOrderImportPhub() {
        clickOnElement("//button[text()[normalize-space()='Confirm payment']]");
        waitABit(4000);
    }

    public void importOrder(String data, String orderName) throws IOException {
        String xpathTitle = "//h4[normalize-space()='Import orders by CSV file']";
        waitElementToBeVisible(xpathTitle);
        String xpath = "//label[child::a[child::span[text()='Choose file']]]//input";
        String _filePath = LoadObject.getFilePath("ImportOrder_CSVFile.csv");
        FileWriter fw = new FileWriter(_filePath, false);
        fw.write("Name,Reference Id,Lineitem Quantity,Lineitem Sku,Product Name,Product Id,Color,Size,Shipping Name,Shipping Address1,Shipping Address2,Shipping City,Shipping Zip,Shipping Province,Shipping Country,Shipping Phone,Artwork Front Url,Artwork Back Url,Mockup Front Url,Mockup Back Url");
        if (data.contains("@OrderName@")) {
            fw.write(data.replace("@OrderName@", orderName));
        } else {
            fw.write(data);
        }

        fw.flush();
        fw.close();
        chooseAttachmentFile(xpath, "ImportOrder_CSVFile.csv");
    }

    public void clickBTAction(String action) {
        String xpath = "//button//span[text()= '" + action + "']";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
        waitUntilInvisibleLoading(20);
    }

    public String verifyTotalCostLineItem() {
        String xpath = "(//div[child::p[text()='Total Cost:']]//following-sibling::div//p)[4]";
        return getTextByJS(xpath);
    }

    public String getTextError() {
        String xpath = "//h4[@class='title']";
        return getText(xpath);
    }

    public void chooseOrder(String orderName) {
        String xpath = "//td[descendant::a[normalize-space()='" + orderName + "']]//preceding-sibling::td//span[@class='s-check']";
        waitElementToBePresent(xpath);
        focusClickOnElement(xpath);
    }

    public String getProduct() {
        String xpath = "//td[@class='order-detail']//div[@class='title__supplier']//following-sibling::div//p";
        return getTextByJS(xpath);
    }

    public String getVariantTitle() {
        String xpath = "//td[@class='order-detail']//div[@class='title__supplier']//following-sibling::div//div[@class='row']//following-sibling::div/div[normalize-space()='Cost']//preceding-sibling::div";
        return getTextByJS(xpath);
    }

    public String getLineitemsSku() {
        String xpath = "//td[@class='order-detail']//div[@class='title__supplier']//following-sibling::div//div[@class='row']//following-sibling::div/div[contains(@class,'text-bold')]//preceding-sibling::div";
        return getTextByJS(xpath);
    }

    public void clickAction(String action) {
        String xpath = "//span[contains(text(),'" + action + "')]";
        clickOnElement(xpath);
    }

    public void verifyMessageShippingProfile(String orderNumber, String err, String message, boolean flag) {
        String xpath = "//tr[descendant::*[normalize-space()='" + orderNumber + "']]";
        String id = getAttributeValue(xpath, "fragment");
        String xpathErr = xpath + "//following-sibling::tr[@fragment = '" + id + "']//*[text()[normalize-space()='" + err + "']]";
        if (flag) {
            hoverOnElement(xpathErr);
            verifyTextPresent(message, flag);
        }
    }

    public void verifyTab(String tabName) {
        String xpath = "//li[descendant::p[contains(text(), '" + tabName + "')]]";
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void moveToPrintHubScreen(String name) {
        clickOnElement("//ul//div[contains(., '" + name + "')]");
        waitForEverythingComplete();
    }

    public void accessScreen(String tabName) {
        clickOnElement("//ul[@class='tree-menu-ul']/li//a[normalize-space()='" + tabName + "']");
    }

    public void clickConfirmBalance(String type) {
        verifyTextPresent(type + " ShopBase Balance", true);
        verifyTextPresent("Are you sure you want to deactivate ShopBase Balance. Your orders won't be processed by Print Hub anymore.", true);
        clickOnElement("//div[child::*[contains(@id , 'modal')]][1]//*[contains(@class, 'modal-footer')]//button[child::span[normalize-space()='" + type + "']]");
    }

    public void verifyButton(String buttonView, boolean flag) {
        verifyElementVisible("//*[text()[normalize-space()='" + buttonView + "'] and contains(@class, 'disable')]", flag);
    }

    public void verifyPriceCharge(String label, String val) {
        int count = countElementByXpath("//div[child::*[text()[normalize-space()='" + label + "']]]//preceding-sibling::div") + 1;
        verifyElementVisible("//div[contains(@class, 'no-gutters')][2]/div[" + count + "][contains(., '" + val + "')]", true);
    }

    public void clickButton(String btnName) {
        clickOnElement("//*[text()[normalize-space()='" + btnName + "']]");
    }

    public void verifyInfoCharge(String label, String val) {
        int count = countElementByXpath("//tr/th[text()[normalize-space()='" + label + "']]//preceding-sibling::th") + 1;
        verifyElementVisible("//tr/td[" + count + "][text()[normalize-space()='" + val + "']]", true);
    }

    public void showListOrder() {
        clickOnElement("//span[@class = 's-icon is-big']");
    }

    public void verifyStatusOrderCharge(String details, String status, String amount) {
        verifyElementVisible("//tr[1][contains(., '" + details + "') and contains(., '" + status + "') and contains(., '" + amount + "')]", true);
    }

    public void moveToDetailPayment(String details) {
        clickOnElement("//tr[1]//a[text()[normalize-space()='" + details + "']]");
    }

    public Float getOutstandingBalanceDefault(String label) {
        int count = countElementByXpath("//div[child::*[text()[normalize-space()='" + label + "']]]//preceding-sibling::div") + 1;
        return getPrice("//div[contains(@class, 'no-gutters')][2]/div[" + count + "]/p");
    }

    public void verifyLineInOrder(String product, String status, boolean isShow) {
        String xpath = "//div[contains(@class, 'align-items') and contains(., '" + product + "')]//span[contains(@class, 'tag-status')]";
        if (isShow) {
            String act = getTextByJS(xpath);
            assertThat(act).isEqualTo(status);
        } else {
            verifyTextPresent(status, isShow);
        }
    }

    public void verifyTotalOrder(String orderNumber, String total, boolean isShow) {
        String xpath = "//tr[contains(., '" + orderNumber + "')]/td[last()]//span[contains(@class, 'money')]";
        if (isShow) {
            String act = getTextByJS(xpath);
            assertThat(act).isEqualTo(total);
        } else {
            verifyTextPresent(total, isShow);
        }
    }

    public void clickhInTabAll(String status) {
        waitForEverythingComplete();
        clickOnElement("//p[contains(text(),'"+status+"')]");
    }

    public void verifyOrderHasBeenEdited() {
        isElementExist("//span[contains(@class,'s-tooltip')]/span[contains(@class,'text-danger')]");
    }


    public void holdOrderPhub(String act) {
        clickOnElementByJs("//span[contains(text(),'Action')]");
        for (int i=0;i<=2;i++){
            clickOnElementByJs("//span[contains(text(),'"+act+"')]");
        }
    }

    public void veriryStatusOrder(String status) {
        waitForEverythingComplete();
        clickOnElement("//p[contains(text(),'"+status+"')]");
        isElementExist("//span[contains(text(),'"+orderNumber+"')]");

    }
}
