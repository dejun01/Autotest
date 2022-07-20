package com.opencommerce.shopbase.storefront.pages.gateway;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class OceanPaymentPage extends SBasePageObject {
    public OceanPaymentPage(WebDriver driver) {
        super(driver);
    }

    public void inputCardNumber(String cardNumber) {
        waitClearAndType("//input[@placeholder='Card Number']", cardNumber);
    }

    public void inputCVV(String cvv) {
        waitClearAndType("//input[@placeholder='Secure Code']", cvv);
    }

    public void inputExpiredDate(String date) {
        waitClearAndType("//input[@placeholder='Expiration Date']", date);
    }

    public void clickOnCancelBtn() {
        clickOnElement("//button[@id='cancelbutton']");
    }
}
