package com.opencommerce.shopbase.dashboard.apps.migratetoshopbase;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

public class ShoplazzaPage extends SBasePageObject {
    public ShoplazzaPage(WebDriver driver) {
        super(driver);
    }

    public void chooseAttachmentFile(String xpath, String fileName) {
        String _filePath = LoadObject.getFilePath(fileName);
        try {
            $(xpath).sendKeys(_filePath);
            waitABit(1000);
        } catch (Exception e) {
            WebElement El = getDriver().findElement(By.xpath(xpath));
            ((RemoteWebElement) El).setFileDetector(new LocalFileDetector());
            El.sendKeys(_filePath);
            waitABit(3000);
        }
    }

    public void choosefileCSV(String file) {
        chooseAttachmentFile("//input[@type='file']", file);
    }

    public void verifyMsg() {
        verifyElementPresent("//*[normalize-space()='Queued to import products, please check your ShopBase store in a moment']", true);
    }

    public void switchToTabInMigratePlatform(String tabName) {
        String xpath = "//ul[contains(@class,'theme-default') or contains(@class,'nav-sidebar')]//li//*[text()[normalize-space()='" + tabName + "']]|(//span[following-sibling::*[normalize-space()='" + tabName + "']])[1]";
        clickOnElement(xpath);
    }

    public void selectRadioButton(String flatform, boolean isCheck) {
        String xpathStatus = "//div[child::label[normalize-space()='" + flatform + "']]//input[@type='radio']";
        String xpathClick = "//div[child::label[normalize-space()='" + flatform + "']]//label";
        verifyCheckedThenClick(xpathStatus, xpathClick, isCheck);
    }

    public void verifyQueueImport(String url) {
        String status = getText("((//tr[descendant::div[normalize-space()='"+ url + "']])[1]//div)[2]");
        if(!status.equalsIgnoreCase("completed")) {
            refreshPage();
        }
        verifyElementPresent("(//tr[descendant::div[normalize-space()='" + url + "']])[1]//div[normalize-space()='completed']", true);
    }

    public boolean isexistLoginForm() {
        waitABit(3000);
        return isElementExist("//h4[@class='title' and normalize-space()='Login']");
    }

    public void waitNewTabLoad() {
        waitABit(3000);
    }
}
