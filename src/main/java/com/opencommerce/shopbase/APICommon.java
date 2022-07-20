package com.opencommerce.shopbase;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class APICommon extends SBasePageObject {
    public APICommon(WebDriver driver){
        super(driver);
    }

    public void waiting(long milliSeconds) {
        waitABit(milliSeconds);
    }

}
