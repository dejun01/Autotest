package com.opencommerce.shopbase.dashboard.apps.crosspanda.pages;

import common.SBasePageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;


@DefaultUrl("https://dashboard.crosspanda.com/sign-in")
public class AuthenCPPage extends SBasePageObject {
    public AuthenCPPage(WebDriver driver) {
        super(driver);
    }

    public void verifyGotoApp() {
        waitForPageLoad();
        waitABit(3000);
        waitUntilElementVisible("//div[@class='banner-panda']", 10);
        verifyTextPresent("High-Quality Assurance - Better prices", true);
    }

    public void sendPassLink() {
        clickOnElement("//button[child::span[text()='Send password reset link']]");
    }

    public void waitForGoToPage() {
        waitUntilElementVisible("//button[child::span[text()='Send password reset link']]", 10);
    }

    public void clickLinkText(String page) {
        clickOnElement("//a[text()[normalize-space()='" + page + "']]");
    }

    public void verifyDashboardCrosspanda() {
        verifyElementPresent("//*[contains(@class,'panda-ui-dashboard__main')]", true);
    }

    public boolean isExistPageLogin() {
        return isElementExist("//div[@class='unite-ui-login']");
    }

    public boolean isOpenChatBox() {
        return isElementExist("//div[@data-visible='true']");
    }

    public void closeChatBox() {
        clickOnElement("//div[@data-visible='true']//span[@class='crisp-1bkorcf crisp-oc2kqi']");
    }

    public void verifyTitlePage() {
        String title = getTitle();
        assertThat(title).isEqualTo("CrossPanda - Sign in");
    }
}
