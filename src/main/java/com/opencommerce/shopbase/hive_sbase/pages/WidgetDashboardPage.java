package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class WidgetDashboardPage extends SBasePageObject {
    public WidgetDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void enterTitle(String value, int res) {
        waitClearAndType("(//div[@class='form-group' and child::label[normalize-space()='Title']]//input[not(@id='homeDashboardWidgetTitle')])[" + res + "]", value);
    }

    public void enterDescription(String value, int res) {
        waitClearAndType("(//div[@class='form-group' and child::label[normalize-space()='Description']]//input[not(@id='homeDashboardWidgetDescription')])[" + res + "]", value);
    }

    public void clickButtonAddNewPrice() {
        clickBtn("//a[normalize-space()='Add more']");
    }

    public int getNumberOfList() {
        return countElementByXpath("//a[normalize-space()='Delete']");
    }

    public void enterLink(String value, int res) {
        waitClearAndType("(//div[@class='form-group' and child::label[normalize-space()='Link']]//input)[" + res + "]", value);
    }

    public void enterImageURL(String value, int res) {
        waitClearAndType("(//div[@class='form-group' and child::label[normalize-space()='Image url']]//input)[" + res + "]", value);

    }

    public void uploadImage(String image) {
        String xpath = "//input[@type='file' and @name='homeDashboardWidgetUploadImg']" ;
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
    }

    public void verifyCreateWidgetPrinthubSuccess() {
        verifyElementPresent("//a[normalize-space()='Create']", false);
    }


    public void selectDroplist(String id, String value) {
        clickOnElement("//div[@id='" + id + "']");
        clickOnElement("//div[contains(@class,'select2-drop-active')]//*[normalize-space()='" + value + "']");
    }

    public void clickEditBtn(String widgetId) {
        clickOnElement("//td[normalize-space()='" + widgetId + "']//ancestor::tr//child::a[@title='Edit']");
    }
}
