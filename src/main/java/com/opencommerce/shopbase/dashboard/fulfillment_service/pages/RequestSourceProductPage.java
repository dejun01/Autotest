package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RequestSourceProductPage extends BFlowPageObject {
    public RequestSourceProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTheFirstQuotation() {
        String xpath = "//tr[1]//td[@class='cursor-default']//span[@class='name']";
        return getText(xpath);
    }

    public void addMoreLinks() {
        String xpath = "//a[text()[normalize-space()='Add more links']]";
        clickOnElement(xpath);
    }

    public void inputProductLink(String url, int y) {
        int index = y + 1;
        if (y == 0) {
            String xpathLine1 = "//input[@placeholder='Enter product links from your website, AliExpress, eBay, Amazon,…']";
            waitClearAndType(xpathLine1, url);
        } else {
            String xpathLine2 = "//form//div[contains(@class,'justify-content-between')][" + index + "]//input[@placeholder='Paste an external link or browse a product from your store']";
            waitClearAndType(xpathLine2, url);
        }
    }

    public String getMsgRequestQuotation() {
        String xpathMsg = "//div[@class='s-form-item__error']";
        return getText(xpathMsg);
    }

    public void verifyDisableSubmitRequest() {
        String xpath = "//button[@disabled='disabled']//child::span[text()[normalize-space()='Submit request']]";
        verifyElementPresent(xpath, true);
    }

    public void verifyDisableSaveCustomerInfor() {
        String xpath = "//button[@disabled='disabled']//child::span[text()[normalize-space()='Save']]";
        verifyElementPresent(xpath, true);
    }

    public void verifyDataCustomerInfor(String email, String skype, String phone, String fb) {
        String valueEmail = getValue("//input[@placeholder='Email address']");
        String valueSkype = getValue("//input[@placeholder='Skype ID']");
        String valuePhone = getValue("//input[@placeholder='Your phone number']");
        String valueFB = getValue("//input[@placeholder='Facebook URL']");
        assertThat(valueEmail).isEqualTo(email);
        assertThat(valueSkype).isEqualTo(skype);
        assertThat(valuePhone).isEqualTo(phone);
        assertThat(valueFB).isEqualTo(fb);
    }
}
