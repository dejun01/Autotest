package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ProductWidgetPage extends SBasePageObject {
    public ProductWidgetPage(WebDriver driver) {
        super(driver);
    }

    String xpathCustomizeWidgetByName = "//*[child::*[text()[normalize-space() ='%s']]]/parent::div/following-sibling::div";

    public void turnOnWidgetWithName(String widgetName, boolean isOn) {
        String xpathToggle = String.format(xpathCustomizeWidgetByName, widgetName) + "//label";
        verifyCheckedThenClick(xpathToggle + "//input[@type='checkbox']", xpathToggle + "//span[@class='s-check']", isOn);
    }

    public void clickBtnCustomize(String widgetName) {
        String xpath = String.format(xpathCustomizeWidgetByName, widgetName);
        clickBtn(xpath ,"Customize",1);
    }

}
