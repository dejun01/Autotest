package com.opencommerce.shopbase.storefront.pages.shop;

import au.com.bytecode.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import common.SBasePageObject;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Java6Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ThankyouPage extends SBasePageObject {
    public ThankyouPage(WebDriver driver) {
        super(driver);
    }

    public void verifyThankyouPage() {
        waitForEverythingComplete();
        String xPath = "//h2[@class='os-header__title' and text()[normalize-space()='Thank you!']]";

        waitUntilElementVisible(xPath, 60);
        if (isElementVisible(xPath, 20)) {
            System.out.println("the locator is present");
        } else {
            System.out.println("the locator not presenting");
        }
        if (isElementExist(xPath)) {
            System.out.println("the locator is Exist");
        }
        else {
            System.out.println("the locator not Existing");
        }
        verifyElementVisible(xPath, true);
    }

    public void verifyExistProduct(String product) {
        String xpath = "//tr[@class='checkout-product']//span[@class='checkout-product__name']";
        verifyElementText(xpath, product);
    }

    public String getPriceProduct() {
        String xpathPrice = "//*[@class='checkout-product__price']";
        return XH(xpathPrice).getText().replace("$", "");
    }

    public String getOrderNumber() {
        String xpath = "//div[@class='os-order-number']";
        return getTextValue(xpath).replace("Order ", "");
    }

    public String getProductImage() {
        String xpath = "//div[@class ='checkout-product-thumbnail__wrapper']//img";
        return getAttributeValue(xpath, "src");
    }

    public String getBillingAddress() {
        String xpath = "//div[@class='content-box'][2]//descendant::address[2]";
        return getTextValue(xpath);
    }

    public String getShippingAddress() {
        String xpath = "//div[@class='content-box'][2]//descendant::address[1]";
        return getTextValue(xpath);
    }

    public String getFirstName() {
        return getBillingAddress().split(" ")[0];
    }

    public String getNameProduct() {
        String xpath = "//span[@class ='checkout-product__name']";
        return getTextValue(xpath);
    }

    public String getQuantityProduct() {
        String xpath = "//div[@class ='checkout-product-thumbnail']//span";
        return getTextValue(xpath);
    }

    public String getPrices() {
        String xpath = "//td[@class ='checkout-product__price']//span";
        return getTextValue(xpath);
    }

    public String getSubtotal() {
        String xpath = "//td[text()[normalize-space()='Subtotal']]//following-sibling::td";
        String subTotal = "";
        if (isElementExist(xpath)) {
            subTotal = getTextValue(xpath);
        }
        return subTotal;
    }

    public String getTotal() {
        String xpath = "((//span[text()[normalize-space()='Total:']]//ancestor::tr)//td[2]//span)[2]";
        return getTextValue(xpath);
    }

    public String getShippingMethod() {
        String xpath = "//h3[text()[normalize-space()='Shipping method']]//following-sibling::p";
        return getTextValue(xpath);
    }

    public String getPaymentMethod() {
        String xpath = "(//h3[text()[normalize-space()='Payment method']]//following-sibling::ul)//descendant::span";
        return getTextValue(xpath);
    }

    public String getShippingPrice() {
        String xpath = "//td[text()[normalize-space()='Shipping']]//following-sibling::td";
        return getTextValue(xpath).split("\\s", 0)[0];
    }

    public void verifyCustomOptionDetail(String customOptionName, String input) {
        verifyElementVisible("//span[@class='checkout-product__name']/following::span[normalize-space()='" + customOptionName + ": " + input + "']", true);
    }

    public void verifyQuantityOfProductInOrder(String product, int quantity) {
        String xpath = "//tr[@class='checkout-product' and child::td//span[@class='checkout-product__name' and text()='" + product + "']]//span[@class='checkout-product-thumbnail__quantity']";
        int actual = Integer.parseInt(getText(xpath));
        assertThat(actual).isEqualTo(quantity);
    }

    public void clickOnShopNameHyperLink() {
        String xPath = "//div[@class='main']//a/span";
        clickOnElement(xPath);
    }

    public String getShippingAddressOnSF() {
        String xPath = "//*[normalize-space()='Shipping address']/following-sibling::address[@class='address']";
        waitElementToBeVisible(xPath);
        String addressSF = getText(xPath).replace("\n", " ");
        return addressSF;
    }

    public String getBillingAddressOnSF() {
        String xPath = "//*[normalize-space()='Billing address']/following-sibling::address[@class='address']";
        if (isElementExist(xPath)) {
            waitElementToBeVisible(xPath);
            return getText(xPath).replace("\n", " ");
        }
        return "";
    }

    public String getCustomerEmail() {
        String xPath = "//*[contains(text(),'Contact information')]//following-sibling::p[1]";
        return getTextValue(xPath);
    }

    public String getCustomerName() {
        String customerName = getTextValue("//*[normalize-space()='Shipping address']/following::address[1]").split("\n")[0];
        return customerName;
    }

    public String getCheckoutToken() {
//        String xPath = "//*[@id='checkout-layout']";
//        waitElementToBeVisible(xPath);
        waitForEverythingComplete(20);
        String[] arrUrl = getCurrentUrl().split("\\?");
        arrUrl = arrUrl[0].split("/");
        return arrUrl[arrUrl.length - 1];
    }

    public void verifyEmail(String mail) {
        String xPath = "//span[@class='step__footer__contact']//a";
        assertThat(getAttributeValue(xPath, "href").contains(mail));
    }

    public int getOrderId() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return ((Long) js.executeScript("return window.sbsdk.checkout.getOrder().id")).intValue();
    }

    public void addProductPostPurchase(String productPostPurchase) {
        String xpath = "//div[text()[normalize-space()='" + productPostPurchase + "']]/ancestor::div[contains(@class,'upsell-only-blocks')]//button";
        String xpathNo = "//div[contains(@class, 'upsell-only-blocks')][1]//div[contains(@class, 'upsell-only-blocks__action')]/a";
        String expXpath = productPostPurchase.isEmpty() ? xpathNo : xpath;
        if (isElementExist(expXpath, 10)) {
            clickOnElement(expXpath);
        }
    }

    public boolean isExamplePageDisplayed() {
        String xPath = "//title[text()[normalize-space()='404 - Not Found' or 'Example Domain']]";
        waitForPageLoad();
        return isElementExist(xPath, 30);
    }

    public String getTotalTax() {
        String xpath = "//td[contains(.,\"Taxes\")]//ancestor::tr//span[@class='order-summary__emphasis']";
        if (isElementExist(xpath))
            return getText(xpath);
        else
            return "$0.00";

    }

    @Step
    public void addOrderIdIntoCSV(String fileName) {
        String _sFileName=LoadObject.getFilePath(fileName);
        File file = new File(_sFileName);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] header = {"Order number", "Checkout token"};
            writer.writeNext(header);

            String[] orderInfo = {orderNumber, checkoutToken};
            writer.writeNext(orderInfo);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public List<String> readListOrderInCSV(String fileName) throws IOException, CsvValidationException, ParseException {
        String filePath = LoadObject.getFilePath(fileName);
        List<List<String>> listOrderInCSV = SessionData.loadDataTableByCSV(filePath);
        List<String> listOrderCSV = new ArrayList<>();
        for (int row : SessionData.getDataTbRowsNoHeader(listOrderInCSV).keySet()) {
            String order_number = SessionData.getDataTbVal(listOrderInCSV, row, "Order number");
            String checkout_token = SessionData.getDataTbVal(listOrderInCSV, row, "Checkout token");
            listOrderCSV.add(order_number);
            listOrderCSV.add(checkout_token);
        }
        return listOrderCSV;
    }
}
