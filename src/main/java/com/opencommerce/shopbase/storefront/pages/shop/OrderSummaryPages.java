package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.taxAmount;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderSummaryPages extends SBasePageObject {
    public OrderSummaryPages(WebDriver driver) {
        super(driver);
    }

    OnepageCheckoutPage onepageCheckoutPage;

    public void verifyCheckoutPage() {
        waitUntilElementVisible("//div[@class='section__header']//h2[contains(.,'Shipping address')]", 50);
    }

    public void verifyDiscountApply(String expDiscount) {
        String xpathDiscount = "//span[@class='reduction-code']//span[@class='reduction-code__text']";
        String actualDiscount;
        try {
            actualDiscount = getText(xpathDiscount);
        } catch (Exception e) {
            refreshPage();
            waitElementToBeVisible(xpathDiscount);
            actualDiscount = getText(xpathDiscount);
        }
        assertThat(actualDiscount).contains(expDiscount.toUpperCase());
    }

    public String getPriceDiscount() {
//        String xpath = "//span[@class='reduction-code']//span[@class='reduction-code__text']//following::span[@class='order-summary__emphasis']";
        //Update xpath, the old xpath return more than 1 result
        String xpath = "//td[span[text()[normalize-space()='Discount']]]//following-sibling::td/span";
        return getText(xpath);
    }

    public String getTotalAmt() {
        String xPath = "//*[@class='payment-due__price']";
        return getTextValue(xPath);
    }

    public void verifyProductOnSummary(String productName, String variant, String price) {
        String xpathProduct = "";
        if (!variant.isEmpty())
            xpathProduct = "//tr[descendant::*[text()[normalize-space()=\"" + productName + "\"]] and descendant::*[text()[normalize-space()=\"" + variant + "\"]]]";
        else
            xpathProduct = "//tr[descendant::*[text()[normalize-space()=\"" + productName + "\"]]]";
        String xpathPrice = xpathProduct + "//span[contains(@class,'discounted-price')]";
        String actualPrice = getText(xpathPrice);
        comparePrice(actualPrice, price);
    }

    public String getSubtotal() {
        String xpath = "//span[@class='order-summary__emphasis']";
        return getText(xpath);
    }

    public void verifyErrorMsg(String msg) {
        String xpath = "//*[(@class='field-message field-message--error' or @class='notice__text') and contains(text(),'" + msg + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyScreenCheckout() {
        verifyElementPresent("//*[text()[normalize-space()='Contact information']]", true);
    }

    public void verifyDiscountAutomatic() {
        verifyElementText("//li[@class='reduction-code']", "");
    }

    public void verifyNotification() {
        verifyElementText("//*[@class='notice__content']", "A discount has been applied to this order. You can’t add another discount.");
    }

    public float getPriceProduct(String sProduct) {
        String xpathPrice = "(//span[normalize-space()='" + sProduct + "']/following::*[contains(text(),'$')])[1]";
        return getPrice(xpathPrice);
    }

    public String getDiscountProductReduction(String sProduct) {
        String xpathProductReduction = "(//td[@class='checkout-product__description'and child::span[text()='" + sProduct + "']]/following::span[@class='reduction-code__text'])[1]";
        return XH(xpathProductReduction).getText();
    }

    public String getDiscount() {
        String xpath = "(//*[@class='total-line__price']//*[@class='order-summary__emphasis'])[2]";
        return price(getText(xpath));
    }

    public float getTotalPrice() {
        waitForPageLoad();
        String xpath = "//table[@class='total-line-table']//span[normalize-space()='Total:']/following::span[@class='payment-due__price']";
        return getPrice(xpath);
    }

    public float getSubTotalPrice() {
        String xpath = "//table[@class ='total-line-table']//td[normalize-space()='Subtotal']/following::span[@class='order-summary__emphasis']";
        return getPrice(xpath);
    }

    public String getSubTotalPriceStr() {
        String xpath = "//table[@class = 'total-line-table']//td[normalize-space()='Subtotal']/following::span[@class='order-summary__emphasis']";
        return getTextValue(xpath);
    }

    public void verifyErrorMessageExist(String mess) {
        String xPath = "//p[contains(@class, 'field-message--error') and text()[normalize-space()='" + mess + "']]";
        verifyElementPresent(xPath, true);
    }

    ///checkout
    String xpathCheckoutProductTable = "//table[@class='checkout-product-table']";
    String xpathCheckoutProduct = xpathCheckoutProductTable + "//tr[%d]";

    public int getNumberOfCheckoutProduct() {
        return countElementByXpath(xpathCheckoutProductTable + "//tr");
    }

    public String getProductNameByLine(int i) {
        String xpath = String.format(xpathCheckoutProduct, i) + "//span[@class='checkout-product__name']";
        return getText(xpath);
    }


    public float getProductTotalByLine(int i) {
        String xpath = String.format(xpathCheckoutProduct, i) + "//td[@class='checkout-product__price']//span[contains(@class,'discounted-price')]";
        return Float.parseFloat(price(getText(xpath)));
    }

    public int getProductQuantityByLine(int index) {
        String xpath = String.format(xpathCheckoutProduct, index) + "//span[@class='checkout-product-thumbnail__quantity']";
        return Integer.parseInt(getText(xpath));
    }

    public int getLineItem(String productName, int quantity, String discountCode) {
        int row = 0;
        String xpath = xpathCheckoutProductTable + "//tr[descendant::*[text()='" + productName + "']][descendant::*[text()='" + quantity + "']]";
        if (!discountCode.isEmpty()) {
            xpath = xpath + "[descendant::*[text()='" + discountCode + "']]";
        }

        if (isElementExist(xpath)) {
            row = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return row;
    }

    public void waitUntilApplyDiscountSucessfully() {
        waitForElementNotPresent("//button[normalize-space()='Apply' and contains(@class,'is-loading')]");
    }

    public List<String> getListProductOnOrder() {
        return getListText("//span[@class='checkout-product__name']");

    }

    public List<String> getProductList() {
        List<String> listProduct = new ArrayList<>();
        WebElement mytable = getDriver().findElement(By.xpath("//table[@class='checkout-product-table']/tbody"));
        List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 1; row <= rows_count; row++) {
            String xPath_row = "//table[@class='checkout-product-table']/tbody/tr[" + row + "]";
            //Loop will execute till the last cell of that specific row.
            String xPath_productName = xPath_row + "/td[2]/span";
            String xPath_price = xPath_row + "/td[3]/span";
            String xPath_qty = xPath_row + "//span[@class='checkout-product-thumbnail__quantity']";

            String productName = getTextValue(xPath_productName);
            String price = getTextValue(xPath_price);
            String quantity = getTextValue(xPath_qty);

            listProduct.add(productName + "|" + price + "|" + quantity);
        }
        return listProduct;
    }

    public String getDiscountCode() {
        String xPath = "//tr[@class='total-line']//span[@class='reduction-code__text']";
        String discount = "";
        if (isElementExist(xPath)) {
            discount = getTextValue(xPath);
        }
        return discount;
    }

    public String getCurrency() {
        String xPath = "//*[@class='payment-due__currency']";
        String currency = "";
        if (isElementExist(xPath)) {
            currency = getText(xPath);
        }
        return currency;
    }

    public String getDiscountAmount() {
        String discount = "";
        String xpath = "(//span[@class='reduction-code']/following::span)[1]";
        if (isElementExist(xpath)) {
            discount = getText(xpath);
        }
        return discount;
    }

    public String getShippingFee() {
        String xpath = "//td[normalize-space()='Shipping']//following-sibling::td//span";
        String str_fee = getText(xpath);
//        String shippingSF = getText("//td[normalize-space()='Shipping']//following-sibling::td//span")
        if (str_fee.equals("Free") || str_fee.equals("-") || str_fee.equalsIgnoreCase("Calculated at next step")) {
            return "$0.00";
        } else {
            return getText(xpath);
        }
    }

    public String getProductVariantByLineItem(int index) {
        return "";
    }

    public void verifyFreeShipping() {
        verifyElementText("//tr[@class='total-line' and child::td[normalize-space()='Shipping']]//span", "Free");
    }

    public void verifyTagDiscount(String code) {
        verifyElementText("//span[@class='reduction-code__text']", code);
    }

    public String getTax() {
        String xpath = "//td[normalize-space()='Taxes']//following-sibling::td//span";
        if (isElementExist(xpath)) {
            String tax_fee = getText(xpath);
            if (!tax_fee.equals("-")) {
                return tax_fee;
            }
        }
        return "$0.00";
    }

    public void verifyTaxOnSF(String taxType) {
        taxAmount = (float) Math.round(taxAmount * 100.0f) / 100.0f;
        float totalTax = removeCurrency(getTax());
        String xpathTaxExclude = "//tr[@class='total-line' and child::td[normalize-space()='Taxes']]//span";
        String xpathTaxInclude = "//tr[contains(text(), 'Including ') or contains(text(), 'Tax included')]";
        switch (taxType) {
            case "no tax":
                verifyElementVisible(xpathTaxExclude, false);
                verifyElementVisible(xpathTaxInclude, false);
                break;
            case "exclude":
                assertThat(totalTax).isEqualTo(taxAmount);
                break;
            default:
                verifyElementVisible(xpathTaxInclude, true);
                break;
        }
    }

    public void scrollToViewBlockDiscount() {
        String xpath = "//*[@placeholder='Enter your discount code here']";
        scrollIntoElementView(xpath);
    }
}
