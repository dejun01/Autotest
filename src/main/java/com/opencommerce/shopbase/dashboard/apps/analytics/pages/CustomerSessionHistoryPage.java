package com.opencommerce.shopbase.dashboard.apps.analytics.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerSessionHistoryPage extends SBasePageObject {
    public CustomerSessionHistoryPage(WebDriver driver) {
        super(driver);
    }

    public void verifySource(String source, String expectedVal) {
        String xpath = "(//p[normalize-space()='" + source + "']/following::p)[1]";
        String value = getText(xpath);
        assertThat(value).isEqualTo(expectedVal);
    }

    public void verifyFirstPageVisited(String firstpagevisited) {
        String xpath = "(//p[normalize-space()='First page visited']//following::span)[1]";
        String value = getText(xpath);
        assertThat(value).isEqualTo(firstpagevisited);
    }

    public void clickButtonViewAllSessions() {
        clickBtn("View all sessions");
    }

    public int getTotalSessionsOfCustomer() {
        String xpath = "//p[normalize-space()='TOTAL SESSIONS']//following-sibling::span";
        int value = Integer.parseInt(getText(xpath));
        return value;
    }

    public void verifyLastSessionDescription(int totalSessionsActual, String activity) {
        String xpath = "//p[contains(text(),'" + Integer.toString(totalSessionsActual) + "') and contains(text(),'" + activity + "')]";
        verifyElementVisible(xpath, true);
    }

    public void verifySessionCovertToOrder(int totalSession, boolean b) {
        String xpath = "//p[contains(text(),'" + totalSession + "') and contains(text(),'session')]//ancestor::div[@class='row m-b ']//following-sibling::div[@class='row']//a";
        verifyElementVisible(xpath,b);
    }

    public void verifyUTM(String utmSource, String expectedVal) {
        String xpath = "(//p[normalize-space()='" + utmSource + "']/following::p)[1]"; // utmSource = custom_source
        String value = getText(xpath);
        assertThat(value).isEqualTo(expectedVal);

    }
}

