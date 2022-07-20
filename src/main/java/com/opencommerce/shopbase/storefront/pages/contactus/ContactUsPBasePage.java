package com.opencommerce.shopbase.storefront.pages.contactus;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ContactUsPBasePage extends SBasePageObject {
    public ContactUsPBasePage(WebDriver driver) {
        super(driver);
    }
    public void clickbtnSend() {
        clickOnElement("//button[normalize-space()='Send']");
    }

    public void verifyMess(String mss) {
        verifyElementPresent("//*[contains(@class,'cl-success') or contains(@class,'page-content__alert-success')]",false);
//            verifyElementText("//*[@msg-invalid='true']", mss);

    }

    public void verifySuccessMss(String mss) {
        verifyElementText("//*[contains(@class,'cl-success') or contains(@class,'page-content__alert-success')]", mss);
    }


    public void enterInputField(String _label, String value) {
        waitClearAndType("//div[@class='relative input-base']//input[@name='" + _label + "']", value);
    }

    public void enterTextAreaFieldMessage(String sMessage) {
        waitClearAndType("//div[@class='textarea-base']//textarea[contains(@class,'textarea')]", sMessage);
    }

    public void selectIssueType(String sIssueType) {
        selectDdlByXpath("(//div[@class='select-box'])[1] ", sIssueType);
    }

    public void switchIFrame() {
        switchToIFrame("//iframe[@name='ifmail' or @id=\"html_msg_body\"]");
    }

    public void verifyContentMailBylabel(String label, String textContent) {
        String textContentAR = getTextByJS("//strong[contains(text(),'" + label + "')]//parent::p").replace(label + ":", "").trim();
        assertThat(textContentAR).contains(textContent);
    }

    public void typeMailMailinator(String mail) {
        String xpath = "//input[@id='email-address']";

        WebElement inputEmail = getDriver().findElement(By.xpath(xpath));
        inputEmail.clear();
        inputEmail.sendKeys(mail);
        inputEmail.sendKeys(Keys.ENTER);
    }

    public void openEmailWithSubject(String subject) {
        waitElementToBeVisible("(//li[descendant::div[contains(text(),'"+subject+"')]])[1]");
        clickOnElement("(//li[descendant::div[contains(text(),'"+subject+"')]])[1]");
    }
}
