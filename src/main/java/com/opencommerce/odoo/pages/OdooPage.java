package com.opencommerce.odoo.pages;

import io.restassured.path.json.JsonPath;
import common.BFlowPageObject;
import org.openqa.selenium.WebDriver;
import common.SBasePageObject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OdooPage extends BFlowPageObject {
    public OdooPage(WebDriver driver) {
        super(driver);
    }
    public void clickMenuOdoo() {
        clickOnElement("//ul[@class='o_menu_apps']");
    }

    public void selectTab(String tab) {
        clickOnElement("//li[@class='dropdown show']//a[text()[normalize-space()='" + tab + "']]");
    }

    public void logInOdoo() {
        clickOnElement("//button[text()='Log in']");
    }

    public void verifyCountry(String country) {
        int count = countItem("COUNTRY");
        String xpath = "//tr[1]/td["+count+"]";
        waitABit(4000);
        waitForEverythingComplete();
        assertThat(getText(xpath)).isEqualTo(country);
    }
    private int countItem(String label) {
        return countElementByXpath("//th[child::span[contains(text(), '"+label+"')]]//preceding::th") + 1;
    }

    public List<String> getShippingMethodElectronicList(String orderName) {
        return getListText("//td[descendant::span[contains(text(), '"+orderName+"')]]//following-sibling::td[@class='shipping-method']//option");
    }

    public void selectStatusOnQuotation(String itemStatus) {
        String xpath = "//tr[td//label[text()='Status']]//select";
        String xpathOption = "//option[text()='"+ itemStatus +"']";
        clickOnElementByJs(xpath);
        clickOnElement(xpathOption);
    }

    public String getValueOnStatusQuotation() {
        String xpath = "//tr[td//label[text()='Status']]//select";
        waitUntilElementVisible(xpath, 10);
        return getValueSelected(xpath);
    }
}
