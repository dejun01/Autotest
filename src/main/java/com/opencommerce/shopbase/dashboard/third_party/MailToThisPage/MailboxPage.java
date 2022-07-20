package com.opencommerce.shopbase.dashboard.third_party.MailToThisPage;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class MailboxPage extends SBasePageObject {
    public MailboxPage(WebDriver driver) {
        super(driver);
    }
    private String xpathSubject;
    private int MAX_RETRY_TIME = 15;

    public void openEmailWithSubject(String subject) {
        String xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";
        clickOnElement(xpathSubject);
        waitForEverythingComplete();
    }

    public void enterEmail(String email) {
        String xpathEmailTextbox = "(//div[@class='input-group']//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        waitUntilElementVisible(xpathEmailTextbox,6);
        $(xpathEmailTextbox).clear();
        $(xpathEmailTextbox).sendKeys(email);
        $(xpathEmailTextbox).sendKeys(Keys.ENTER);
        waitElementToBeVisible("//div[@class='x_content']//div[@class='table-responsive']");
    }

    public void switchToIFrameMail() {
        switchToIFrameByID("msg_body");
    }

    public void switchToIFrameBodyMail() {
        switchToIFrameByID("html_msg_body");
    }

    public boolean openMailBox(String emailAddress, String subject) {
        openUrl("https://www.mailinator.com");
        String xpathEmail = "(//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";

        WebElement inputTextbox = getDriver().findElement(By.xpath(xpathEmail));
        inputTextbox.clear();
        inputTextbox.sendKeys(emailAddress);
        inputTextbox.sendKeys(Keys.ENTER);
        waitElementToBeVisible("//table[contains(@class,'able-striped')]");

        return isElementVisible(xpathSubject, 10);
    }

    public void verifyReceivedMail(String emailAddress, String subject) {
        boolean check = openMailBox(emailAddress, subject);
        int retryTimes = 0;
        while (!check && retryTimes <= MAX_RETRY_TIME) {
            {
                clickOnElement("//span[@class = 'input-group-btn']//button[normalize-space()='GO!']");
                retryTimes++;
                waitABit(12000);
                check = openMailBox(emailAddress, subject);
            }
        }
        assertThat(check).isTrue();
        clickOnElement(xpathSubject);
        waitForEverythingComplete();
    }

    public void verifyEmailContent(String content, boolean isPresent) {
        String xpath = "//*[contains(.,\"" + content + "\")]";
        verifyElementPresent(xpath, isPresent);
    }
}
