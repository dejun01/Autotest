package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing;

import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;

public class RequestProductPage extends BFlowPageObject {
    public RequestProductPage(WebDriver driver) {
        super(driver);
    }

    public String getValueFB() {
        return getTextValue("//input[@placeholder='Facebook link']");
    }

    public String getValueSkype() {
        return getTextValue("//input[@placeholder='Skype account']");
    }

    public String getValuePhone() {
        return getTextValue("//input[@placeholder='Phone number']");
    }

    public String getValueOther() {
        return getTextValue("//input[@placeholder='Other']");
    }

    public void clickAdd() {
        clickOnElement("//button[child::span[text()='Add more product links']]");
    }
    public void inputProductLink(String linkProduct, int i) {
        int index = i + 1;
        waitClearAndType("(//div[@class='links ant-row']//input[contains(@id,'link')])[" + index + "]", linkProduct);
    }

}
