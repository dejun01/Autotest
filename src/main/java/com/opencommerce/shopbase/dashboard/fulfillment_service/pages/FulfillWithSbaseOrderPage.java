package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FulfillWithSbaseOrderPage extends SBasePageObject {
    public FulfillWithSbaseOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickFulfillWith(String nameWith) {
        String xpath1 = "//span[text()='Fulfill with']//parent::button";
        String xpath2 = "//span[contains(text(),'" + nameWith + "')]";
        focusClickOnElement(xpath1);
        clickOnElement(xpath2);
    }
    public void searchByOrder(String orderName) {
        String xpath = "//input[@placeholder='Search orders']";
        waitClearAndTypeThenEnter(xpath, orderName);
    }
    public String getInfoOrderFulfilment(String orderName, String nameColumn) {
        int index = getIndextColumn(nameColumn);
        String xpathProductName = "//span[text()[normalize-space()='"+orderName+"']]//ancestor::td//following-sibling::td["+index+"]";
        return getText(xpathProductName);
    }
    private int getIndextColumn(String nameCol) {
        String xpath = "//th[child::*[contains(text(),'"+nameCol+"')]]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }
    public void editAddress(String address,String country,String city,String region,String code){
        String xpathAddress = "//input[@id='asf_address']";
        String xpathCity = "//input[@id='asf_city']";
        String xpathCountry = "//input[@placeholder='Select country']";
        String nameCountry = "//div[@class='s-select-searchable__item-list']//div[contains(text(),'"+country+"')]";
        String nameState = "//div[@class='s-select-searchable__item-list']//div[contains(text(),'"+region+"')]";
        String xpathRegion = "//input[@placeholder='Select State']";
        String xpathCode ="//input[@placeholder='Enter postal code']" ;
        String xpathSave = "//span[contains(text(),'Save')]";
        waitClearAndTypeThenEnter(xpathAddress,address);
        focusClickOnElement(xpathCountry);
        clickOnElement(nameCountry);
        waitClearAndTypeThenEnter(xpathCity,city);
        focusClickOnElement(xpathRegion);
        clickOnElement(nameState);
        waitClearAndTypeThenEnter(xpathCode,code);
        clickOnElement(xpathSave);
    }
    public List<String> getListAction(){
        String xpathError = "//div[@role='tooltip']";
        return getListText(xpathError);
    }
    public void clickActions(String action){
        String xpath = "//*[contains(text(),'"+action+"')]";
        clickOnElement(xpath);

    }
    public void selectOrder(String orderNumber){
        String xpath = "//td[descendant::a[contains(text(),'"+orderNumber+"')]]//preceding-sibling::td//input";
        focusClickOnElement(xpath);
    }

    public void clickBTFulfillWith() {
        String xpath  = "//span[text()='Fulfill with']//parent::button";
        clickOnElement(xpath);
    }

    public void selectSbFulfillment() {
        String xpath  = "//button[child::span[text()='Fulfill with']]//parent::div//following-sibling::div[@class='s-dropdown-menu']//span[contains(text(),'PlusHub')]";
        clickOnElement(xpath);
    }

    public void clickBTFulfill() {
        String xpath = "//span[contains(text(),'Fulfill order')]//parent::button";
        clickOnElement(xpath);
    }

    public void clickBTAction() {
        String xpath = "//button[child::span[text()='Actions']]";
        focusClickOnElement(xpath);
    }

    public void clickTab(String tabName) {
        clickOnElement("//li/a[child::*[contains(text(), '"+tabName+"')]]");
    }


}
