package com.opencommerce.shopbase.dashboard.settings.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SettingPages extends SBasePageObject {
    public SettingPages(WebDriver driver) {
        super(driver);
    }

    public int MAX_RETRY_TIME = 5;
    String email = "";

    public void clickToSection(String _linkName) {
        String xPath = "//*[normalize-space()='" + _linkName + "']";
        waitElementToBeClickable(xPath);
        clickOnElement(xPath);
        String xPathRouter = "//a[@href='/admin/settings' and @class='router-link-active']";
        waitElementToBeVisible(xPathRouter, 30);
    }


    public void verifySaveSuccess() {
        String xPath = "//div[@class='s-notices is-bottom']//div";
//        verifyElementPresent(xPath, true);
        waitElementToBeVisible(xPath);
    }

    public void selectDdlWithLabelAtSettingsScreen(String _labelName, String _value) {
        String xPath = "((//*[descendant-or-self::*[text()[normalize-space()=\"" + _labelName + "\"]]]/following-sibling::div//select)[1]|(//label[text()[normalize-space()=\"" + _labelName + "\"]]/following-sibling::select)[1])";
        if (XH(xPath).isEnabled()) {
            waitElementToBePresentThenScrollIntoView(xPath).waitUntilEnabled().waitUntilVisible();
            clickOnElement(xPath + "/option[text()[normalize-space() =\"" + _value + "\"]]");
            if (isClickableBtn("Save")) {
                clickBtn("Save");
                waitForEverythingComplete();
                verifySaveSuccess();
            }
        }
    }

    public void clickAddStaffAccount() {
        String xpath = "//a[@href='/admin/settings/account/new']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        } else {
            clickBtn("Add staff account");
        }
    }

    public void enterStaffEmail(String staffEmail) {
        String xPath = "//*[child::label[normalize-space()='Email']]//following-sibling::*//input";
        waitElementToBeVisible(xPath);
        waitClearAndType(xPath, staffEmail);
        email = staffEmail;
    }

    public void verifyErrorMsg(String msg, int currentRetryTime) {
//        String xPath = "//*[contains(text(),'\" + msg + \"')]";
        System.out.println("Verify Error Msg :" + msg);
        try {
            verifyTextPresent(msg, true);
        } catch (Throwable e) {
            e.printStackTrace();
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(300);
                verifyErrorMsg(msg, currentRetryTime + 1);
            } else {
                refreshPage();
                enterStaffEmail(email);
                clickBtn("Send invite");
                verifyTextPresent(msg, true);
            }
        }
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

    public void clickOnAddProviderBtn() {
        String xPath = "//button/span[contains(text(),'Add a provider')]";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void selectProviderRadioBtn(String label) {
        selectRadioButtonWithLabel(label, true);
    }

    public void selectProviderFromDropdown(String providerName) {
        String _xPath = "//select";
        String xPath = "//select//option[text()[normalize-space()='" + providerName + "']]";
        if (isElementVisible(_xPath, 2)) {
            clickOnElement(_xPath);
            waitForEverythingComplete();
            waitElementToBeVisible(xPath);
            clickOnElement(xPath);
            //selectDdlWithLabel("Select provider","Stripe");
            //selectFromDropdownByVisibleText(_xPath,providerName);
        }
    }


    public void clickOnContinueBtn() {
        String xPath = "//button//span[contains(text(),'Continue')]";
        clickOnElement(xPath);
    }

    public void clickOnActivateBtn() {
        String xPath = "//button//span[contains(text(),'Activate')]";
        clickOnElement(xPath);
    }

    public void completeActiveProviderInStripeSite() {
        String xPathEmail = "input[@id='email']";
        String email = "quyenbui@beeketing.com";
        String xPathNextBtn = "//button";

        String xPathPws = "//input[@name='password']";
        String pwd = "rBt^oku$yOctL27*wGF2@eV4^&E1";
        String xPathSignInBtn = "//button[@type='submit']";

        String skipLink = "Skip this account form";

        String xPath_stagError = "//*[contains(text(),'rpc error: code = Unknown desc = gclient: not found data in result')]";

        if (isElementVisible(xPathEmail, 3)) {
            waitClearAndType(xPathEmail, email);
            clickOnElement(xPathNextBtn);
            waitClearAndType(xPathPws, pwd);
            clickOnElement(xPathSignInBtn);
        }
        clickLinkTextWithLabel(skipLink);
        if (isElementVisible(xPath_stagError, 3)) {
            String url = getCurrentUrl();
            String[] arrUrl = url.split("\\.", 3);
            String newUrl = arrUrl[0] + ".stag." + arrUrl[2];
            System.out.println(newUrl);
            openUrl(newUrl);
        }

    }

    public void clickOnShopNavigateingIcon() {
        String xPath = "//span[contains(text(),'Online Store')]/../../../following-sibling::a";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);

    }

    public void switchToWindowTabWithIndex(int index) {
        String xPath = "//*[@placeholder='Search']";
        String xPath1 = "//div[@class='search-bar']";
        waitForEverythingComplete();
        switchToWindowWithIndex(index);
        waitForEverythingComplete();
        isElementVisible(xPath1, 10);
        System.out.println(getTitle());
    }

    public String getOriginalTabID() {
        return getDriver().getWindowHandle();
    }

    public void closeBrowserTabWithIndex(int index) {
        closeBrowserWithIndex(index);
    }

    public void clickOnHyperLink(String hyperlinkName) {
        String xPath = "//a[contains(text(),'" + hyperlinkName + "')]";
        if (isElementVisible(xPath, 10))
            clickOnElement(xPath);
    }

    public boolean isHyperLinkDisplay(String hyperlinkName) {
        String xPath = "//a[contains(text(),'" + hyperlinkName + "')]";
        boolean result = false;
        if (isElementVisible(xPath, 10)) {
            result = true;
        }
        return result;
    }

    private String calculateTotalAmount(double price, double quantity, double shippingFee) {
        double trxFee = ((price * quantity) + shippingFee);
        String trxFeeWithSymbol = "$" + trxFee;
        return trxFeeWithSymbol;
    }


    private String getTransactionFeeFromCurrentInvoiceScreen(String orderNo) {
        String xPathOrderNo = "//table//table//tr//td/a[contains(text(),'" + orderNo + "')]";
        String xPathOrderNo1001 = "//table//table//tr//td/a[contains(text(),'Order #1003')]";
        waitElementToBeVisible(xPathOrderNo1001);
        String xPathOrderTrxFee = xPathOrderNo1001 + "/../../td[contains(text(),'$')]";
        waitElementToBeVisible(xPathOrderTrxFee);
        String trxFee = getText(xPathOrderTrxFee).substring(1);
        return trxFee;
    }

    private static DecimalFormat df = new DecimalFormat("0.00");

    public void expandOrderListByClickingOnOrderHyperLink() {
        clickOnHyperLink("order");
    }

    public boolean verifyDisplayOfExpandOrderList() {
        String xPath = "//a[contains(text(),'order')]";
        if (isElementVisible(xPath, 5)) {
            return true;
        } else {
            return false;
        }
    }

    public void verifyTransactionFeeInCurrentInvoice(String orderNo, double trxRate, double trxFee) {
        System.out.println("Expected trx fee = " + trxFee);
        String trxFeeWithSymbol = "$" + df.format(trxFee);
        System.out.println("Expected trx fee = " + trxFeeWithSymbol);

        String xPathOrderNo = "//table//table//tr//td[1]/a[contains(text(),'" + orderNo + "')]";
        String xPathExpectationTrxFee = xPathOrderNo + "/../../td[4][text()='" + trxFeeWithSymbol + "']";
        System.out.println("Expected trx fee = " + getTextValue(xPathExpectationTrxFee));
        isElementVisible(xPathExpectationTrxFee, 3);

        String trxRateWithSymbol = trxRate + "%";
        String xPathTrxRate = xPathOrderNo + "/../../td[3][text()='" + trxRateWithSymbol + "']";
        isElementVisible(xPathTrxRate, 3);
    }

    public void clickOnConfirmChangePlanBtn() {
        String xPath = "//button//span[contains(text(),'Confirm changes')]/..";
        doubleClickOnElement(xPath);
        waitForEverythingComplete(10);
        isElementVisible(xPath, 20);
    }

    public void verifyInvoiceName() {
        Calendar cal = new GregorianCalendar();
        //cal.add(Calendar.DATE, 7);
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String invoiceName = dateFormat.format(date);
        System.out.println("Invoice Name: " + invoiceName);

        String xPath_invoiceName = "//table//tbody/tr[1]/td[2]/a[text()='" + invoiceName + "']";
        verifyElementPresent(xPath_invoiceName, true);
    }

    public void verifyInvoiceType(String invoiceType) {
        String xPath_invoiceType = "//table//tbody/tr[1]/td[1][text()[normalize-space()='" + invoiceType + "']]";
        if (isElementVisible(xPath_invoiceType, 60))
            verifyElementPresent(xPath_invoiceType, true);
    }

    public void verifyInvoiceAmount(String amount) {
        String xPath_amount = "//table//tbody/tr[1]/td[3][text()='" + amount + "']";
        verifyElementPresent(xPath_amount, true);
    }

    public void verifyInvoiceStatus(String status) {
        String xPath_status = "//table//tbody/tr[1]/td[5]//span[text()='" + status + "']";
        verifyElementPresent(xPath_status, true);
    }

    public void clickOnInvoiceNameInTheFirstRow() {
        String xPath = "//table/tbody/tr[1]/td[2]/a";
        clickOnElement(xPath);
    }

    public void verifyTotalTransactionFee(String totalTrxFee) {
        //Total amount which is displayed under Amount label
        String xPath1 = "//table/tbody/tr/td[text()='Transaction fee']/following-sibling::td[2][text()='" + totalTrxFee + "']";
        verifyElementPresent(xPath1, true);

        //Total amount which is displayed at the bottom of table
        String xPath2 = "//table/tbody/tr/td[text()='Total']/following-sibling::td[text()='" + totalTrxFee + "']";
        verifyElementPresent(xPath2, true);

        //Total amount which is displayed as a Header label beside the table
        String xPath3 = "//h3[text()='" + totalTrxFee + "']";
        verifyElementPresent(xPath3, true);
    }

    public void clickSetting(String setting) {
        clickOnElement("//div[@class='settings-nav__action']//p[normalize-space()='" + setting + "']//ancestor::a");
    }

    public void verifySaveSettingSuccessfully() {
        verifyElementPresent("//div[normalize-space()='All changes were successfully saved']", true);
    }

    public boolean isChecked(String label) {
        String xPathCheckbox = "//span[normalize-space()='" + label + "']/preceding-sibling::input[@type='checkbox']";
        WebElement checkbox = getDriver().findElement(By.xpath(xPathCheckbox));
        return checkbox.isSelected();
    }

    public void checkOnCheckbox(String label) {
        String xPathCheckbox = "//span[normalize-space()='" + label + "']/preceding-sibling::input[@type='checkbox']";
        waitElementToBePresentThenScrollIntoView(xPathCheckbox).waitUntilVisible();
        clickOnElement(xPathCheckbox);
    }

    public void checkOnCheckbox(String label, boolean isChecked) {
        boolean check = isChecked(label);
        String xPathCheckbox = "//span[normalize-space()='" + label + "']/preceding-sibling::input[@type='checkbox']";
        if (isChecked == true && check != true) {
            clickOnElement(xPathCheckbox);
        } else if (isChecked == false && check == true) {
            clickOnElement(xPathCheckbox);
        }
    }


    public void enterScheduleSend(String ordinal, String time) {
        waitABit(5000);
        String xPath = "(//span[normalize-space()='" + ordinal + "']/following::b[text()='Send']/following::input)[1]";
        waitClearAndType(xPath, time);
    }

    public void selectUnit(String ordinalEmail, String unit) {
        String xPath = "(//span[normalize-space()='" + ordinalEmail + "']/following::select//option[normalize-space()='" + unit + "'])[1]";
        waitElementToBePresentThenScrollIntoView(xPath).waitUntilEnabled().waitUntilVisible();
        clickOnElement(xPath);
    }

    public void enterMessage(String ordinalMsg, String msg) {
        String xPath = "(//span[normalize-space()='" + ordinalMsg + "']/following::div[@class='s-form-item__content']//textarea)[1]";
        waitClearAndType(xPath, msg);
    }

    public boolean isChanged() {
        waitABit(7000);
        return getDriver().findElement(By.xpath("//button[normalize-space()='Save changes']")).isDisplayed();
    }

    public String getPrintBaseProcessingFeeRate() {
        String xPath = "//div[text()[normalize-space()='Processing fee per order']]/preceding-sibling::div";
        String rate = getTextValue(xPath);
        System.out.println("Rate: " + rate);
        return rate;
    }

    public void chooseTheSection(String section) {
        String xPathScreen = "//*[@class='settings-nav__title' and normalize-space()='" + section + "']";
        waitElementToBeVisible(xPathScreen);
        clickOnElement(xPathScreen);
        waitForTextToAppear(section, 10000);
    }

    public void verifyPhone(String phone) {
        verifyElementText("//div[@class='s-form-item' and descendant::label[text()='Phone']]//input", phone);

    }

    public void verifyStoreInfor(String storeInfor, String storeInforAR) {
        assertThat(storeInforAR).isEqualTo(storeInfor);
    }

    public void verifyCurrency(String currency) {
        String xpath = "//div[@class='s-form-item__content' and descendant::p[normalize-space()='Store currency']]//div[@class='s-select full-width-selector']";
        if (!currency.equals("")) {
            verifyOptionSelectedByLabel("Store currency", currency);
        } else {
            verifyElementPresent(xpath, false);
        }
    }

    public void verifyCountry(String country) {
        verifyElementText("//div[@class='s-form-item is-required' and descendant::label[text()='Country']]//input", country);
    }
    public void scrollToAddition() {
        scrollIntoElementView("//div[@class='group-title' and text()='Order processing']");
    }

    public void verifyAutoTosCheckout() {
        verifyTextPresent("By clicking button Complete order, you agree to the",true);
    }

    public void scrollToCopleteOrder() {
        scrollIntoElementView("//div[@class='step__footer']");
    }

    public void verifyTermOfService() {
        clickOnElement("//span[@class='tos']");
        waitForElementToPresent("//div[@class='checkout-modal__body__content']");
    }

    public void verifyNotShowAdditional() {
        verifyElementPresent("//span[@class='tos']", false);

    }
    public void verifyCheckedBoxTos(boolean status) {
        checkCheckbox("//label[contains(@class,'accept-tos')]",1,status);
    }


}
