package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.*;


public class ClaimHivePage extends SBasePageObject {
    public ClaimHivePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void Login() {
        clickBtn("Log in");
    }

    public void verifyLoginSS() {
        waitUntilElementVisible("//ul[@class='sidebar-menu']", 20);
    }

    public void searchByClaimID(String claimID) {
        String xpathInputSearch = "//input[@name='filter[name][value]']";
        waitClearAndTypeThenEnter(xpathInputSearch, claimID);

    }

    public void enterClaimID(String condition) {
        String xpathFilter = "//a//i[contains(@class,'fa-filter')]";
        String xpathNameFilter = "//a[normalize-space()='" + condition + "']//i";
        focusClickOnElement(xpathFilter);
        focusClickOnElement(xpathNameFilter);
    }

    public String getClaimDetail(String claimID, String nameColumn) {
        int index = getIndexCol(nameColumn);
        String xpath = "(//a[contains(text(),'" + claimID + "')]//parent::td)[1]//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    private int getIndexCol(String nameCol) {
        String xpath = "//a[contains(text(),'" + nameCol + "')]//parent::th//preceding-sibling::th";
        return countElementByXpath(xpath) - 1;
    }

    public void clickClaimID(String claimID) {
        String xpath = "(//a[contains(text(),'" + claimID + "')]//parent::td)[1]";
        clickOnElement(xpath);
    }

    public void verifyPreferredSolutionApproved(boolean Present) {
        String xPath = "//div[contains(@class,'claim-solution')]";
        verifyElementPresent(xPath, Present);
    }

    public String getTextInfoOrder(String name) {
        String xpath = "//label[contains(text(),'" + name + "')]//parent::div/descendant::span";
        return getText(xpath);
    }
    public void searchByClaimIDSBase(String claimID) {
        String xpath = "//input[@placeholder='Search order, claim']";
        waitClearAndTypeThenEnter(xpath, claimID);

    }

    public String getTextCreatedAt(String claimID) {
        int index = getIndexColumn("DATE");
        String xpath = "//a[normalize-space()='" + claimID + "']//ancestor::td//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    public String getTextOrderNumber(String claimID) {
        int index = getIndexColumn("ORDER");
        String xpath = "//a[normalize-space()='" + claimID + "']//ancestor::td//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    public String getTextPreferredSolution(String claimID) {
        int index = getIndexColumn("PREFERRED RESOLUTION");
        String xpath = "//a[normalize-space()='" + claimID + "']//ancestor::td//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    public String getTextStatus(String claimID) {
        int index = getIndexColumn("STATUS");
        String xpath = "//a[normalize-space()='" + claimID + "']//ancestor::td//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    private int getIndexColumn(String nameCol) {
        String xpath = "//span[normalize-space()='" + nameCol + "']/parent::th";
        return countElementByXpath(xpath);
    }

    public void chooseAction(String status) {
        String xpathInput = "//div[contains(@class,'claim-status')]";
//        scrollToElement("//label[text()[normalize-space()='Referred solution']]");
        scrollTo(0,450);
        hoverThenClickElement(xpathInput, "//div[contains(@class,'claim-status')]/a//span[text()='New']");
        verifyElementPresent("//div[contains(@class,'select2-container-active')]", true);
        waitUntilElementVisible("//div[@role='option' and contains(text(),'" + status + "')]", 5);
        clickOnElement("//div[@role='option' and contains(text(),'" + status + "')]");
    }

    public void chooseSolutionAction(String solution) {
        String elementToHover = "//div[contains(@class,'claim-solution')]";
        String elementToClick = "//div[contains(@class,'claim-solution')]/a//span[text()='Resend']";
        hoverThenClickElement(elementToHover, elementToClick);
        verifyElementPresent("//div[contains(@class,'select2-container-active')]", true);
        clickOnElement("//div[@role='option' and contains(text(),'" + solution + "')]");
    }

    public void inputRefundAmount(String amount) {
        scrollToElement("//label[text()[normalize-space()='Referred solution']]");
        String xpathInput = "//input[contains(@class,'refund-amount')]";
        hoverThenClickElement(xpathInput, xpathInput);
        waitClearAndType(xpathInput, amount);

    }

    public String getInfoClaimInListClaim(String product, String nameColumn) {
        int index = getIndex(nameColumn);
        String xpath = "//span[contains(text(),'" + product + "')]//parent::td//following-sibling::td[" + index + "]";
        return getText(xpath);

    }

    public void clickOnTrackingNumber(String product) {
        int index = getIndex("Tracking number");
        String xpath = "//span[contains(text(),'" + product + "')]//parent::td//following-sibling::td[" + index + "]//a";
        scrollTo(0,1000);
        focusClickOnElement(xpath);
        waitABit(2000);
        switchToNextTab();
        waitForEverythingComplete();


    }

    private int getIndex(String nameCol) {
        String xpath = "//th[contains(text(),'" + nameCol + "')]//preceding-sibling::th";
        return countElementByXpath(xpath);
    }

    public void clickBtnUpdate() {
        String xpath  = "//button[@name='btn_update_and_edit']";
        clickOnElement(xpath);
    }

    public String getMsgUpdateClaim() {
        String xpath = "//div[@class='alert alert-success alert-dismissable']";
        return getText(xpath);
    }
}
