package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ConfigIntroInHivePage extends SBasePageObject {
    public ConfigIntroInHivePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyIntroSuccess(String mesage) {
        String msg = getText("//div[@class='alert alert-success alert-dismissable']");
        assertThat(msg).contains(mesage);
    }

    public void clickDeletebutton(String name) {
        clickOnElement("//a[normalize-space()='" + name + "']//ancestor::tr//child::a[@title='Delete']");
    }

    public void clickYesbutton() {
        clickOnElement("//button[@class='btn btn-danger']");
    }

}
