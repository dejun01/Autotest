package com.opencommerce.shopbase.dashboard.orders.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MoreFilterPage extends SBasePageObject {
    public MoreFilterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getListTitleMoreFilter() {
        String xpath = "//div[@role='tablist']//p";
        return getListText(xpath);
    }

    public List<String> getListTitleMoreFilters() {
        String xpath = "//div[@role='tablist']//span[contains(@class,'s-flex-item')]";
        return getListText(xpath);
    }

    public void enterFilterByConditon(String condition, String value) {
        String xpath = "//input[@placeholder='Search by " + condition.toLowerCase() + "']";
        waitClearAndTypeThenEnter(xpath, value);
    }

    public String getErr() {
        String xpath = "//div[@class='d-flex']//p[contains(@class,'text-center')]";
        return getText(xpath);
    }

    public void clickCondition(String condition) {
        String xpath = "//div[@role='tablist']//p[contains(text(),'" + condition + "')]";
        clickOnElement(xpath);
    }

    public void clickConditionName(String condition) {
        String xpath = "//div[@role='tablist']//span[contains(text(),'" + condition + "')]//preceding-sibling::input";
        focusClickOnElement(xpath);
    }

    public void clickBtnFilter(String filterType) {
        String xpath = "(//span[normalize-space()= '"+filterType+"']//parent::button[contains(@class,'is-round')])[1]";
        clickOnElement(xpath);
    }

    public String getTextByShopBaseFulfillment() {
        String xpath = "//h2[@class='s-flex-item stack-item__title']";
        return getText(xpath);
    }

    public String getTextSearch() {
        String xpath = "//span[contains(@class,'cursor-pointer')]//parent::div";
        return getText(xpath);
    }

    public String getOrderNumber() {
        String xpath = "//table//td//a[@class='order-link']";
        return getText(xpath);
    }

    public void clickBTSaveFilter(String filterTemplates) {
        String xpath = "//span[contains(text(),'Save filter')]";
        clickOnElement(xpath);
        waitElementToBeVisible("//div[contains(@class,'modal-save-filter')]//input");
        waitClearAndTypeThenEnter("//div[contains(@class,'modal-save-filter')]//input", filterTemplates);

    }

    public void chooseFilterTemplates(String filterTemplates) {
        String xpath = "//span[text()='Filter templates']";
        clickOnElement(xpath);
        String xpath_1 = "//div[contains(text(), '"+filterTemplates+"')]";
        clickOnElement(xpath_1);
    }

    public void clickBTDone() {
        waitABit(3000);
        String xpath = "//span[contains(text(),'Done')]";
        focusClickOnElement(xpath);
    }

    public void verifyFilter(String value) {
        checkCheckbox("//p[text()='Status']//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//label[child::span[contains(text(),'"+value+"')]]",true);
    }

    public void clickApplyFilter(){
        String xpath = "//span[contains(text(),'Apply')]";
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public void clickOnCondition(String condition){
        String xpath = "//div[@class='sb-collapse-item sb-p-medium']//div[contains(text(),'"+condition+"')]";
        clickOnElement(xpath);
    }

    public void enterValueFilter(String condition, String value){
        String xpath = "(//div[normalize-space()='"+condition+"' and contains(@class,'collapse-item')])[1]/following-sibling::div//input";
        waitClearAndTypeThenEnter(xpath,value);
    }

    public void chooseValueFilter(String condition, String value){
        String xpath = "(//div[normalize-space()='"+condition+"' and contains(@class,'collapse-item')])[1]/following-sibling::div//div[normalize-space()='"+value+"']//preceding::span[1]";
        clickOnElement(xpath);
    }
    public void chooseSearchType(String searchType){
        String xpath = "//div[@role='tooltip' and contains(@x-placement,'start')]//div//li[normalize-space()='"+searchType+"']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
}
