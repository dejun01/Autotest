package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing;

import common.BFlowPageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

import java.util.List;

@DefaultUrl("https://dashboard.crosspanda.com/request-product")
public class AnonymousRequestProductPage extends BFlowPageObject {

    public AnonymousRequestProductPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getListLinkRequest() {
        return getListTextByAttribute("//div[@class='links ant-row']//input[@type='text']", "placeholder");
    }


    public void enterNote(String note) {
        waitClearAndType("(//input[@placeholder='Tell us about your expectation on price, product quality, package...'])[1]", note);
    }

    String listddlValue = "//div[contains(@class,'ant-select-dropdown ant-select-dropdown--single ant-select-dropdown-placement-bottomLeft')][not(contains(@style,'display: none'))]//li";

    public List<String> getListValueDddlist() {
        waitUntilElementVisible(listddlValue,10);
        return getListText(listddlValue);
    }

    public void clickValueSupplier(String supplier) {
        clickOnElement(listddlValue + "[text()[normalize-space()='" + supplier + "']]");
    }

    public List<String> getListContactType() {
        return getListText("//div[contains(@class,'input-contact')]//label");
    }
}
