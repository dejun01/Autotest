package com.opencommerce.shopbase.dashboard.online_store.pages.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PagesPage extends SBasePageObject {
    public PagesPage(WebDriver driver) {
        super(driver);
    }

    public void clickPage(String page) {
        clickOnElement("//a[normalize-space()='" + page + "']");
    }

    public String getCurrentURL() {
        clickOnElement("//button[normalize-space()='Edit website SEO']");
        return  getTextValue("//div[@class='s-form-item' and descendant::label[normalize-space()='URL and handle']]//input");
    }

    public void enterNewURL(String newURL) {
        inputSlowly("//section[@class='card search-engine' and descendant::div[@class='type-container']]//div[@class='s-input s-input-group s-input-group--prepend']//input", newURL);
    }

    public void verifySavePageSuccess() {
        waitForElementToPresent("//div[normalize-space()='Your page was updated']");
        verifyElementPresent("//div[normalize-space()='Your page was updated']", true);
    }

    public void verifyPageDefaul(List<String> pagesCurrent, List<String> pagesTemplate) {
        assertThat(pagesCurrent).isEqualTo(pagesTemplate);
    }
}
