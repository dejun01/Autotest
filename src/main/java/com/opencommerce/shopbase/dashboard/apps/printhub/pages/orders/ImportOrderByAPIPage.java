package com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders;

import common.SBasePageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ImportOrderByAPIPage extends SBasePageObject {
    public ImportOrderByAPIPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchOrder(String order_name) {
            String xpath = "//input[@placeholder='Search orders by name or product name']";
            waitClearAndType(xpath, order_name);
            waitForEverythingComplete();
    }

    public String  getDate(String order_name, String name) {
        int index = getIndex(name);
        String xpath = "//td[descendant::a[normalize-space()='" + order_name + "']]//following-sibling::td[" + index + "]//descendant::span["+index+"]";
        waitUntilElementVisible(xpath,10);
        return getTextByJS(xpath);

    }

    private int getIndex(String s) {
        String xpath = "//th[child::span[text()='" + s + "']]//preceding-sibling::th";
        int index = countElementByXpath(xpath)  - 1 ;
        return index;
    }

    public void clickOrderName(String order_name) {
        String xpath = "//td[descendant::a[normalize-space()='"+order_name+"']]//a";
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

    public String getShippingAddress(int index) {
        String xpath = "(//div[child::div[text()='Shipping address']]//div)["+index+"]";
        return getTextByJS(xpath);
    }

    public String getNotData() {
        String xpath = "//p[contains(@class,'text-center')]";
        return getTextByJS(xpath);
    }

    public void clickCheckBox(String order_name) {
        String xpath = "//td[descendant::a[normalize-space()='"+order_name+"']]//preceding-sibling::td//span[@class='s-check']";
        focusClickOnElement(xpath);
    }

    public void clickBTAction() {
        String xpathAction = "//span[contains(text(),'Pay for selected orders')]";
        clickOnElement(xpathAction);
    }
    public void selectApp(String app) {
        String xpath = "//p[text()='"+app+"']";
        doubleClickOnElement(xpath);

    }
}
