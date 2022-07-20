package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;

public class BulkEditorPages extends SBasePageObject {

    public BulkEditorPages(WebDriver driver) {
        super(driver);
    }

    public void editTitle(String title, String titleUpdate) {
        String xpath = "//div[@col-id='title']//span[normalize-space()='" + title + "']";
        String xpath_value = "//div[@col-id='title']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, titleUpdate);
        waitABit(1000);
    }

    public void editTags(String tags, String tagsUpdate) {
        String xpath = "//div[@col-id='tags']//span[normalize-space()='" + tags + "']";
        String xpath_value = "//div[@class='s-autocomplete control']//input[@class='s-input__inner']";
        String xpath_add = "//span[child::strong[text()='Add']]//following-sibling::span";
        doubleClickOnElement(xpath);
        inputSlowly(xpath_value, tagsUpdate);
        clickOnElement(xpath_add);
        waitABit(1000);
    }

    public void editProductType(String type, String typeUpdate) {
        String xpath = "//div[@col-id='product_type' and normalize-space()='" + type + "']";
        String xpath_value = "//div[@col-id='product_type']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, typeUpdate);
        waitABit(1000);
    }

    public void editVendor(String vendor, String vendorUpdate) {
        String xpath = "//div[@col-id='vendor' and normalize-space()='" + vendor + "']";
        String xpath_value = "//div[@col-id='vendor']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, vendorUpdate);
        waitABit(1000);
    }

    public void editPrice(String option, String price, String priceUpdate) {
        String xpath = "//div[descendant::span[normalize-space()='"+ option +"']]//following-sibling::div[@col-id='price' and normalize-space()='$" + price + ".00']";
        String xpath_value = "//div[@col-id='price']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, priceUpdate);
        waitABit(1000);
    }

    public void editCompareAtPrice(String compareAtPrice, String compareAtPriceUpdate) {
        String xpath = "//div[@col-id='compare_at_price' and normalize-space()='$" + compareAtPrice + ".00']";
        String xpath_value = "//div[@col-id='compare_at_price']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, compareAtPriceUpdate);
        waitABit(1000);
    }

    public void editCostPerItem(String costPerItem, String costPerItemUpdate) {
        String xpath = "//div[@col-id='cost_per_item' and normalize-space()='$" + costPerItem + ".00']";
        String xpath_value = "//div[@col-id='cost_per_item']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, costPerItemUpdate);
        waitABit(1000);
    }

    public void editSku(String sku, String skuUpdate) {
        String xpath = "//div[@col-id='sku' and normalize-space()='" + sku + "']";
        String xpath_value = "//div[@col-id='sku']//input[@class='s-input__inner']";
        scrollToRight();
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, skuUpdate);
        waitABit(1000);
    }

    public void editWeight(String weight, String weightUpdate) {
        String xpath = "//div[@col-id='weight' and normalize-space()='" + weight + "']";
        String xpath_value = "//div[@col-id='weight']//input[@class='s-input__inner']";
        doubleClickOnElement(xpath);
        waitClearAndType(xpath_value, weightUpdate);
        waitABit(1000);
    }

    public void verifyTitle(String titleUpdate) {
        scrollToLeft();
        String xpath = "//div[normalize-space()='1']//following-sibling::div[@col-id='title']//span[@class='bold']";
        assertThat(getText(xpath)).isEqualTo(titleUpdate);
        waitABit(1000);
    }

    public void verifyProductType(String typeUpdate) {
        String xpath = "//div[normalize-space()='1']//following-sibling::div[@col-id='product_type']";
        assertThat(getText(xpath)).isEqualTo(typeUpdate);
        waitABit(1000);
    }

    public void verifyVendor(String vendorUpdate) {
        String xpath = "//div[normalize-space()='1']//following-sibling::div[@col-id='vendor']";
        assertThat(getText(xpath)).isEqualTo(vendorUpdate);
        waitABit(1000);
    }

    public void clickCompareAtPrice() {
        String xpath = "//div[normalize-space()='1']//preceding-sibling::div[@col-id='compare_at_price'] | //div[normalize-space()='1']//following-sibling::div[@col-id='compare_at_price']";
        scrollToLeft();
        clickOnElement(xpath);
        waitABit(2000);
    }

    public void scrollToLeft() {
        WebElement element = getDriver().findElement(By.xpath("//div[@class='ag-body-horizontal-scroll-viewport']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollLeft -= 1000", element);
    }

    public void scrollToRight() {
        WebElement element = getDriver().findElement(By.xpath("//div[@class='ag-body-horizontal-scroll-viewport']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollLeft += 1000", element);
    }
}