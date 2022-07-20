package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class ClaimListPage extends SBasePageObject {
    public ClaimListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getClaimInFirst() {
        String xpath = "//tr[@class='claim-row'][1]//td[@class='name']//a";
        return getText(xpath);
    }

    public void goToClaimDetail() {
        String xpath = "//tr[@class='claim-row'][1]//td[@class='name']//a";
        clickOnElement(xpath);
    }

    public String getCount() {
        String xpath = "//li[@class='is-active']//p";
        String countText = getText(xpath);
        String[] count = countText.split("\\(");
        return count[1].replace("(", "").replace(")", "").trim();

    }

    public String getTextClaim() {
        try {
            String xpath = "//table//tbody//tr[1]//td[@class='name']//a";
            return getText(xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getInfoClaimInListClaimPage(String claim, String nameColumn) {
        int index = getIndexcol(nameColumn);
        String xpath = "//a[contains(text(),'" + claim + "')]//ancestor::td//following-sibling::td[" + index + "]";
        return getText(xpath);
    }

    private int getIndexcol(String nameCol) {
        String xpath = "//th[child::span[contains(text(),'" + nameCol + "')]]//preceding-sibling::th";
        return countElementByXpath(xpath);
    }

    public void cancelClaim(String claim) {
        clickOnElement("//a[contains(text(),'" + claim + "')]//ancestor::td//following-sibling::td[5]//span[text()='Cancel']");
        waitUntilElementVisible("//button[contains(text(),'Discard')]", 2);
        clickOnElement("//button[contains(text(),'Cancel')]");
    }

    public String getClaimID(String claim) {
        String xpath = "//a[contains(text(),'" + claim + "')]//ancestor::td";
        clickOnElement(xpath);
        waitABit(300);
        String urlCurrent = getDriver().getCurrentUrl();
        return urlCurrent;
    }

    public void selectOrderName() {
        String xpath = "//td[@class='order-name']//a";
        clickOnElement(xpath);
    }

    public void waitToDataClaimShow() {
        String xpath = "//tr[@class='claim-row'][1]//td[@class='name']//a";
        waitElementToBeVisible(xpath, 30);
    }
}
