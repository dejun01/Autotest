package com.opencommerce.shopbase.dashboard.apps.printhub.pages.scalablepress;

import common.SBasePageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("https://scalablepress.com/manager/v2")
public class ScalablePressPage extends SBasePageObject {
    public ScalablePressPage(WebDriver driver) {
        super(driver);
    }

    public void openScalablePressPage() {
        openUrl("https://scalablepress.com/manager/v2");
        maximizeWindow();
    }

    public void enterUsername(String userName) {
        waitClearAndType("//input[@placeholder ='Email']", userName);
    }

    public void enterPwd(String pwd) {
        waitClearAndType("//input[@placeholder ='Password']", pwd);

    }

    public void selectAllItems() {
        clickOnElementByJs("//div[contains(@class,'OrderItemsTable')]/div/div[contains(@class,'headerText')]//input");
        waitABit(1000);
    }

    public void clickBtnNextStep() {
        clickOnElement("(//div[contains(@class,'ui segment')]//div[text()='Next step'])[1]");
    }


    public void enterInputField(String label, String value) {
        waitClearAndType("//div[@class='field'][child::*[text()='" + label + "']]//input", value);
    }

    public void enterRemarks(String value) {
        waitClearAndType("//textarea", value);

    }

    public void selectPreferredResolution(String credit) {
        String xpath = "//div[@class='field']//input[@type='radio'][following-sibling::*[text()='" + credit + "']]";
        clickOnElementByJs(xpath);

    }

    public void enterReason(String reason) {
        String xpathDdl = "//div[@class='field'][child::*[text()='Reason for claim']]";
        clickOnElement(xpathDdl + "//i");
        waitForElementToPresent(xpathDdl + "//div[@class='ui transition visible menu']");
        clickOnElement("//div[@class='item'][text()='" + reason + "']");
    }

    public void clickBtnFileClaim() {
        clickOnElement("//div[text()='File Claim']");
        waitUntilInvisibleLoading(10000);
        waitForTextToAppear("Items on claim file", 5000);
    }

    public void getLinkClaim(String orderId) {
        String linkClaim = getCurrentUrl();
        System.out.println(orderId + " - " + linkClaim);
    }
}
