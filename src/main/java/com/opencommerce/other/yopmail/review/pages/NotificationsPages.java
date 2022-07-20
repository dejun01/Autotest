package com.opencommerce.other.yopmail.review.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotificationsPages extends SBasePageObject {
    public NotificationsPages(WebDriver driver) {
        super(driver);
    }

    public void openURL(String url) {
        openUrl(url);
        waitForPageLoad();
    }

    public void inputEmail(String email) {
        String xpath = "//div[@class='tooltip click']//input";
        waitClearAndType(xpath, email);
    }

    public void clickBtnCheckInbox() {
        clickOnElement("//div[@id='refreshbut']//button");
    }

    public void verifyNotification(String reviewTitle, boolean isShow) {
        switchToIFrame("//iframe[@name='ifmail']");
        verifyElementPresent("//td//h3[text()='" + reviewTitle + "']", isShow);
        switchToIFrameDefault();
    }

    public void verifyTitle(String title) {
        switchToIFrame("//iframe[@name='ifmail']");
        verifyElementText("//div[contains(@class,'ellipsis')]", title);
    }

    public void verifyMessage(String message) {
        verifyElementText("//tr//td//h1", message);
    }

    public void verifyUserName(String userName) {
        verifyElementText("(//tr//td//p)[3]", userName);
    }

    public void verifyFileDetail(String fileDetail) {
        verifyElementText("(//tr//td//p)[5]", fileDetail);
    }
}
