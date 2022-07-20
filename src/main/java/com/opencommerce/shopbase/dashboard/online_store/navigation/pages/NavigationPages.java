package com.opencommerce.shopbase.dashboard.online_store.navigation.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class NavigationPages extends SBasePageObject {
    public NavigationPages(WebDriver driver) {
        super(driver);
    }

    public void clickAddMenu() {
        clickOnElement("//a[contains(text(),'Add menu')]");
    }
}
