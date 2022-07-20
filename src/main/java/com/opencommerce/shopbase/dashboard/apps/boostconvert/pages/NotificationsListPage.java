package com.opencommerce.shopbase.dashboard.apps.boostconvert.pages;

import common.SBasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class NotificationsListPage extends SBasePageObject {
    public NotificationsListPage(WebDriver driver) {
        super(driver);
    }


    public void controlNotificationSale(boolean _isTurnOn) {
        String xpathToggle = "(//div[@class='table-row']//div[@class='noti-status'])[1]";
        String xpathStatus = xpathToggle + "//input[@type='checkbox']";
        String xpathCLick = xpathToggle + "//span[@class='slider']/label";
        verifyCheckedThenClick(xpathStatus, xpathCLick, _isTurnOn);


    }

    public void waitNoti() {
        int i = 1;
        do {
            waitABit(5000);
            refreshPage();
            i++;
            if (i == 5)
                break;
        }
        while (isElementExist("//div[@class='wrapper-table default-table overflow-content']//div[@class='sample-data']"));
        if (isElementExist("//div[@class='wrapper-table default-table overflow-content']//div[@class='sample-data']")) {
            System.out.println("Sync error! Please check again");
        }
    }

    public void verifyNameProductNoti(String productName) {
        verifyElementPresent("(//a[text()[normalize-space()='" + productName + "']])[1]", true);
    }

    public void verifyTypeNoti() {
        verifyElementPresent("//div[@class='noti-type']//span[text()[normalize-space()='sync']]", true);
    }

    public void verifyStatusNoti() {
        verifyElementText("(//div[@class='table-row']//div[@class='noti-status'])[1]//span[@class='slider']//label", "ON");
    }


    public int rowSalesNoti() {
        String xpathNumberNoti = "";
        int s = 0;
        //String xpathRowNoti = "//div[@class='notification-table' and not (descendant::*[@class='sample-data'])]//div[@class='table-row']";
        // return countElementByXpath(xpathRowNoti);
        return s;
    }


    String xpathLocal = "//div//label[descendant::*[normalize-space()='%s']]";

    public void selectLocation(String sLocation) {
        selectRadioButtonWithLabel(sLocation, 1, true);
    }

    public void selectRandumLocationInCountry(String sDataLocation) {
        String countries[] = sDataLocation.split(",");
        String xpath = xPathInputFieldWithLabel("", "Type to search", 1);
        clickOnElement(xpath);
        for (String country : countries) {
            inputSlowly(xpath, country );
            waitABit(1000);
            $(xpath).sendKeys(Keys.ENTER);
            waitABit(100);
        }

    }

    public void inputLocation(String sDataLocation) {
        String location = sDataLocation.replace(">", "\n");
        waitTypeOnElement("//textarea", location);

    }

    public int getNumberNotificationCreated() {
        String noti = getText("//div[contains(@class,'s-tag')]//p");
        String number = noti.replace("There will be ", "").replace(" new notification created", "").replace(" new notifications created", "");
        return Integer.parseInt(number);
    }

    public int getTotalNotification() {
        String a = getText("//*[contains(@class,'noti-sales')]");
        return Integer.parseInt(a.replace("All sales notifications (", "").replace(")", ""));
    }

    ////-----------------------
    String tableListNotification = "//div[@id='app-list']//table";

    public int countSaleNotification() {
        waitElementToBePresent("//div[@id='app-list']//span[@class='s-check']");
        return countElementByXpath(tableListNotification + "//tbody//tr[not(descendant::*[normalize-space()='No Notifications'])]");
    }

    public void selectAllSaleNotification() {
        checkCheckbox(tableListNotification + "//thead//tr/th", true);
    }


    public void turnOnNotification(boolean isTurnOn, int index) {
        int colIndex = getColIndexByLabel("Status");
        String xpath = tableListNotification + "//tbody//tr[" + index + "]//td[" + colIndex + "]";
        checkCheckbox(xpath, isTurnOn);
    }

    private int getColIndexByLabel(String status) {
        String xpath = tableListNotification + "//thead//tr//th[normalize-space()='Name']/preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public void selectType(String style) {
        String xpath = "//div[@class='s-flex s-flex--vertical s-form-item']";
        selectDdlByXpath(xpath, style);
    }
}
