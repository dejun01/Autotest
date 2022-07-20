package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Java6Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AddAccountStaffPage extends SBasePageObject {
    String xpathSubject = "";
    private int MAX_RETRY_TIME = 15;

    public AddAccountStaffPage(WebDriver driver) {
        super(driver);
    }

    public String getTexTAddStaffAccount() {
        String xpath = "//span[contains(text(),'Add staff account')]";
        if (isElementExist(xpath)) {
            return getTextValue(xpath);
        } else {
            return "";
        }
    }


    public List<String> getListStaffAcc() {
        String xpath = "//div[@class='clearfix p-im m-t-lg box-shadow-container']//div[@class='m-t-sm']//a";
        if (isElementExist(xpath)) {
            return getListText(xpath);
        }
        return null;

    }

    public String getTextAlert() {
        String xpath = "//div[contains(@class,'alert')]//li";
        if (isElementExist(xpath)) {
            return getTextValue(xpath);
        }
        return "";
    }

    public String getTextDeleteSuccess(String email) {
        return getTextValue("//*[contains(text(),'Staff " + email + " deleted successfully')]");
    }

    public void chooseAccStaff(String email) {
        String xpath = "//div[@class='clearfix p-im m-t-lg box-shadow-container']//div[@class='m-t-sm']//a[contains(text(),'" + email + "')]";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void enterInputConfirmPassword(String passWord) {
        String xpath = "//input[@class='s-input__inner']";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, passWord);

    }

    public String getPermission() {
        String namePermission = "";
        String save = "//span[contains(text(),'Save changes')]";
        String xpath = "//span[contains(text(),'has full permissions')]//following::input";
        List<WebElement> listPermission = getDriver().findElements(By.xpath(xpath));
        for (int i = 0; i < listPermission.size(); i++) {
            boolean _xpath = listPermission.get(i).isSelected();
            if (_xpath == true) {
                getListPermission(i);
                namePermission = getPermission(i);
                if (isElementExist(save)) {
                    clickOnElement(save);
                    break;
                }
            } else {
                namePermission = getPermission(i);
                break;

            }

        }
        return namePermission;

    }

    private void getListPermission(int i) {
        clickOnElement("//span[contains(text(),'has full permissions')]//following::input[" + (i + 1) + "]//following-sibling::span[@class='s-check']");
    }

    private String getPermission(int i) {
        return getText("//span[contains(text(),'has full permissions')]//following::input[" + (i + 1) + "]//following-sibling::span//following-sibling::span").trim();
    }

    public void clickBTFullPermission(boolean isFullPermission) {
        String xpathStatus = "//span[contains(text(),'has full permissions')]//preceding::input[1]";
        String xpathToClick = "//span[contains(text(),'has full permissions')]//preceding-sibling::span";
        verifyCheckedThenClick(xpathStatus, xpathToClick, isFullPermission);
    }

    public void refreshPage() {
        waitABit(1000);
        getDriver().navigate().refresh();
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void clickAddStaffAccount() {
        try {
            String xpathLinkText = "//a[@href='/admin/settings/account/new']";
            String xpath = "//button[child::span[normalize-space()='Add staff account']]";
            if (isElementExist(xpathLinkText)) {
                clickOnElement(xpathLinkText);
            } else {
                clickOnElement(xpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterStaffEmail(String staffEmail) {
        String xPath = "//*[child::label[normalize-space()='Email']]//following-sibling::*//input";
        waitElementToBeVisible(xPath, 2);
        waitClearAndType(xPath, staffEmail);
    }

    public void clickSendInvite() {
        String xPath = "//button[child::*[normalize-space()='Send invite']]";
        String xPathLoading = "//button[child::*[normalize-space()='Send invite'] and @disabled='disabled']";
        waitElementToBeVisible(xPath);
        waitForElementNotAppear(xPathLoading);
        if (XH(xPath).isEnabled() && !isElementExist(xPathLoading)) {
            clickOnElement(xPath);
        } else {
            waitForElementNotAppear(xPathLoading);
            XH(xPath).waitUntilEnabled().waitUntilVisible().waitUntilClickable().click();
        }
        waitABit(1000);
    }

    public void clickButton(String name) {
        String xpath = "//span[contains(text(),'" + name + "')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        waitForPageLoad();
        waitForEverythingComplete();
    }

    public void clickButtonSubmit() {
        String xpath = "//div[@class='pull-right full-width']//button[contains(@class,'is-primary')]";
        clickOnElement(xpath);
    }

    public String getErrorMsg() {
        return getTextValue("//div[@class='s-notices is-bottom']//div[@class='s-toast is-danger is-bottom']|//div[@class='s-form-item__error']");

    }

    public String choosePlan(String typeText) {
        String staffAcc = "";
        String xpath = "//div[@class='pricing-list s-mt32']//following::button";
        List<WebElement> items = getDriver().findElements(By.xpath(xpath));
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getText().contains(typeText)) {
                staffAcc = getStaffAcc(i);
                items.get(i).click();
                break;
            }
        }
        return staffAcc;
    }

    private String getStaffAcc(int i) {
        return getTextValue("(//div[@class='m-t features']//span[@class='value flex'])[" + (i + 1) + "]");
    }

    public boolean openMailBox(String emailAddress, String subject) {
        openUrl("https://www.mailinator.com");
        maximizeWindow();
        deleteAllCookies();

        String xpathEmail = "(//div[@class='container']//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        waitElementToBeVisible(xpathEmail);
        $(xpathEmail).sendKeys(emailAddress + Keys.ENTER);
        xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";
        waitElementToBeVisible(xpathSubject);
        return isElementVisible(xpathSubject, 100);
    }

    public void verifyReceivedMail(String emailAddress, String subject) {

        clickOnElement(xpathSubject);
    }

    public String verifyShopInAccStaff(String status) {
        String xpath = "//ul[@class='ui-login-domain-platform']//ancestor::li//span[text()='" + status + "']";
        return getTextValue(xpath);
    }

    public String verifyAndClick() {
        String status = "";
        String xpath_1 = "//span[contains(text(),'Activate staff account')]";
        String xpath_2 = "//span[contains(text(),'Deactivate staff account')]";
        if (isElementExist(xpath_1)) {
            status = getTextValue(xpath_1);
            clickOnElement(xpath_1);
        } else {
            status = getTextValue(xpath_2);
            clickOnElement(xpath_2);

        }
        return status;
    }

    public String getTextNotPermission() {
        return getTextValue("//h1[text() ='You don’t have permission to view this page']");
    }

    public void backToSettingPage() {
        String xpath = "//a[@href='/admin/settings/account']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public String getStaffAccount() {
        return getText("//div[contains(@class,'general-group')]//div[preceding-sibling::div[normalize-space()='Staff profile']]/div");
    }

    public boolean isExistAccountScreen() {
        String xpath = "//div[contains(@class,'setting-detail-account-page')]";
        return isElementExist(xpath);
    }

    public void actionOnPopupConfirm(String action) {
        clickOnElement("//div[@class='s-modal is-active']//span[contains(text(),'" + action + "')]");
    }

    public boolean isSettingAccountPage() {
        String xpath = "//h1[text()='Account']";
        return isElementExist(xpath, 5);
    }

    public void clickButtonDeleteStaffAccount() {
        String xpath = "//button[child::span[normalize-space()='Delete staff account']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathTitle = "//h4[text()='Please confirm your password']";
        waitElementToBeVisible(xpathTitle);
    }

    public void clickButtonConfirm() {
        String xpath = "//button[child::span[normalize-space()='Confirm']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathTitle = "//h4[text()='Please confirm your password']";
        waitForElementNotAppear(xpathTitle);
        waitForPageLoad();
        waitForEverythingComplete();
    }

    public void verifyAccess(String access, String account) {
        String xpath = "//a[normalize-space()='" + account + "']//following-sibling::div//child::p";
        String status = getText(xpath);
        assertEquals(access, status);
    }

    public void verifyStaffPermissionMain(String namePermission, Boolean access) {
        String nameMain = "//span[normalize-space()='" + namePermission + "']//ancestor::a";
        waitElementToBeVisible(nameMain, 20);
        clickOnElement(nameMain);
        waitForEverythingComplete(20);
        if (access) {
            switch (namePermission) {
                case "Analytics":
                    String analytic = "//*[normalize-space()='Overview dashboard']";
                    assertThat(isElementVisible(analytic, 5)).isEqualTo(true);
                    break;
                case "Home":
                    String button = "//div[contains(normalize-space(),'Good') and @class='heading']";
                    assertThat(isElementVisible(button, 5)).isEqualTo(true);
                    break;
                default:
                    String name = "//*[normalize-space()='" + namePermission + "' and contains(@class,'heading') or @id='page-header' or @class='fs-large']";
                    assertThat(isElementVisible(name, 5)).isEqualTo(true);
            }
        } else {
            String msg = "//*[normalize-space()='You don’t have permission to view this page']";
            assertThat(isElementVisible(msg, 5)).isEqualTo(true);
        }
    }


    public void verifySubPermission(String main, String sub, Boolean access) {
        switch (main) {
            case "Settings":
                String setting = "//span[normalize-space()='Settings']//ancestor::a";
                clickOnElement(setting);
                waitForEverythingComplete();
                String subSetting = "";
                if (sub.matches("Sales channel")) {
                    subSetting = "//p[normalize-space()='Sales channels' or normalize-space()='Sales channel']"; //prod: Sales channels // stag :Sales channel
                } else {
                    subSetting = "//*[normalize-space()='" + sub + "']//ancestor::div[contains(@class,'action')]";
                }
                String status = find(subSetting).getAttribute("class");
                boolean appear = status.contains("disabled");
                if (access) {
                    assertThat(appear).isEqualTo(false);
                }
                if (!access) {
                    assertThat(appear).isEqualTo(true);
                }
                break;
            case "Analytics":
                String analytic = "//span[normalize-space()='Analytics']//ancestor::a";
                waitElementToBeVisible(analytic);
                clickOnElement(analytic);
                String subPermission = "//span[normalize-space()='Sales Reports' or normalize-space()='Sale Report']"; // staging : Sales Reports // prod : Sale Report
                clickOnElement(subPermission);
                waitForPageLoad();
                if (access) {
                    String subPermissionPage = "//div[normalize-space()='Sales reports' ]"; // stag: Sales reports // prod : Sales reports
                    assertThat(isElementVisible(subPermissionPage, 5)).isEqualTo(true);
                } else {
                    String msg = "//h1[text()='You don’t have permission to view this page']";
                    assertThat(isElementVisible(msg, 5)).isEqualTo(true);
                }
                break;

            default:
                String mainPermission = "//span[normalize-space()='" + main + "']//ancestor::a";
                waitElementToBeVisible(mainPermission);
                clickOnElement(mainPermission);
                String subPermissionOnlineStore = "//*[normalize-space()='" + sub + "' and @class='unite-ui-dashboard__aside--wrap']";
                waitElementToBeVisible(subPermissionOnlineStore);
                clickOnElement(subPermissionOnlineStore);
                if (access) {
                    String subPermissionPage = "//*[normalize-space()='" + sub + "' and contains(@class,'heading')  or contains(@class,'fs-large')]";
                    assertThat(isElementVisible(subPermissionPage, 5)).isEqualTo(true);
                } else {
                    String msg = "//h1[text()='You don’t have permission to view this page']";
                    assertThat(isElementVisible(msg, 5)).isEqualTo(true);
                }
        }
    }

    public void verifyPermissionEditOrder(String main, String sub, Boolean access) {
        String ordersMain = "//span[normalize-space()='Orders']//ancestor::a";
        clickOnElement(ordersMain);
        String orders = "(//div[@class='sb-mr-xs word-break'])[1]";
        clickOnElement(orders);
        String editOrders = "//span[normalize-space()='" + sub + "']";
        clickOnElement(editOrders);
        waitForEverythingComplete();
        if (access) {

            switch (sub) {
                case "Refund items":
                    String refund = "//*[normalize-space()='" + sub + "' and @class='title-bar__title']";
                    assertThat(isElementVisible(refund, 5)).isEqualTo(true);
                case "Mark as fulfilled":
                    String fulfillment = "//span[normalize-space()='Fulfillment']";
                    assertThat(isElementVisible(fulfillment, 5)).isEqualTo(true);
            }
        } else {
            String msg = "//h1[text()='You don’t have permission to view this page']";
            assertThat(isElementVisible(msg, 5)).isEqualTo(true);
        }
    }

    public void verifyPermissionExportOrder(String main, String sub, Boolean access) {
        String ordersMain = "//span[normalize-space()='Orders']//ancestor::a";
        clickOnElement(ordersMain);
        String exportOrders = "//span[normalize-space()='Export order']";
        clickOnElement(exportOrders);
        waitForEverythingComplete();
        if (access) {
            String export = "//div[normalize-space()='Export orders']";
            assertThat(isElementVisible(export, 5)).isEqualTo(true);
        } else {
            String msg = "//h1[text()='You don’t have permission to view this page']";
            assertThat(isElementVisible(msg, 5)).isEqualTo(true);
        }
    }

    public void verifySubPermissionOfBalance(String main, String sub, Boolean access) {
        String shopname = LoadObject.getProperty("shop");
        String siderbar = "//p[text()='" + shopname + "']//parent::div";
        clickOnElement(siderbar);
        waitForEverythingComplete();
        String balanceMain = "//div[contains (text(), 'Balance' ) ]";
        verifyTextPresent(balanceMain, false);
        clickOnElement(balanceMain);
        if (access) {
            String name = "//*[contains(@class,'m-t-ex container')]//child::div[normalize-space()='Balance']";
            assertThat(isElementVisible(name, 5)).isEqualTo(true);

        } else {
            String msg = "//*[normalize-space()='You don’t have permission to view this page']";
            assertThat(isElementVisible(msg, 5)).isEqualTo(true);
        }
    }

    public void turnOffPopup() {
        clickOnElement("//div[@class='sb-popup__header']//button");
    }
}