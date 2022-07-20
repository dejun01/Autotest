package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class GlobalSettingsPage extends SBasePageObject {
    public GlobalSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void selectShape(String shape) {
        selectDdlByXpath("//div[@class='card' and descendant::span[text()='Shape']]//select",shape);
    }
}
