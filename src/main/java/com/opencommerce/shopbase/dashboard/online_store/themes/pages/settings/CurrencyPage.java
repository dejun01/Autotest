package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CurrencyPage extends SBasePageObject {
    public CurrencyPage(
            WebDriver driver) {
        super(driver);
    }

    public void enterSomeCurrencies(String currencies) {
        waitClearAndType("//div[@class='s-input']//input",currencies);
    }
}
