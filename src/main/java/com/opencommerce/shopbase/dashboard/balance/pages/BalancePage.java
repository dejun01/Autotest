package com.opencommerce.shopbase.dashboard.balance.pages;

import com.google.gson.JsonObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BalancePage extends SBasePageObject {
    public BalancePage(WebDriver driver) {
        super(driver);
    }

    public String topUpCode;
    public static boolean autoTopup;
    public static boolean autoSpay;
    public static String threshold;
    public static String amountAutoTopup;
    String shop = LoadObject.getProperty("shop");
    String linkhive = LoadObject.getProperty("hive");

    public void accessToBalancePage() {
        String xPath = "//div[@class='d-flex flex-column']";
        String xPath_Balance = "//div[contains(text(),'Balance')]|//div[contains(text(),'余额')]";
        clickOnElement(xPath);
        if (isElementVisible(xPath_Balance, 5)) {
            clickOnElementByJs(xPath_Balance);
        }
        waitForEverythingComplete();
        String xpathTitle = "//h2[text()='Balance']|//h2[text()='余额']";
        int retryCount = 0;
        while (retryCount < 5) {
            if (isElementVisible(xpathTitle, 5)) {
                retryCount = 5;
            } else {
                refreshPage();
                retryCount++;
            }
        }
    }

    public double getCurrentAvailableBalance() {
        String availableBalance = "";
//        String xPath = "//h4[contains(text(),'Total current available') or contains(text(),'当前可用总额')]//parent::div//following-sibling::h4";
        String xPath = "//h4[contains(text(),'Current available balance')]//parent::div//following-sibling::h4";
        waitUntilElementVisible(xPath, 5);
        availableBalance = getText(xPath);
        if ("-".equals(availableBalance)) {
            return 0;
        } else {
            return Double.parseDouble(availableBalance.replace("$", "").replace(",", ""));
        }
    }

    public double getAvailableToPayout() {
        String availablePayout = "";
//        String xPath = "//h4[contains(text(),'Available to payout') or contains(text(),'可提现')]//parent::div//following-sibling::h4";
        String xPath = "//span[contains(text(),'Available to payout')]//parent::div//following-sibling::h4";
        availablePayout = getText(xPath);
        if ("-".equals(availablePayout)) {
            return 0;
        } else {
            return Double.parseDouble(availablePayout.replace("$", "").replace(",", ""));
        }
    }

    public double getAvailableSoon() {
        String availableSoon = "";
//        String xPath = "//h4[contains(text(),'Pending to review') or contains(text(),'待审核')]//parent::div//following-sibling::h4";
        String xPath = "//h4[contains(text(),'Available soon')]//parent::a//parent::div//following-sibling::h4";
        availableSoon = getText(xPath);
        if ("-".equals(availableSoon)) {
            return 0;
        } else {
            return Double.parseDouble(availableSoon.replace("$", "").replace(",", ""));
        }
    }

    public void clickOnTopUpBtn() {
        String xPath = "//span[contains(text(),'Top up') or contains(text(),'充值')]";
        waitElementToBeVisible(xPath, 5000);
        clickOnElement(xPath);
    }

    public void clickOnViewHistoryBtn() {
        String xPath = "//span[contains(text(),'View transactions')]";
        clickOnElement(xPath);
    }

    public void selectTopUpAmt(String amt) {
        String xPath = "//div[@class='block-top-up--item']//div[text()[normalize-space()='" + amt + "']]";
        clickOnElement(xPath);
    }

    public void clickOnConfirmTopUpBtn() {
        String xPath = "//span[contains(text(),'Confirm top up') or contains(text(),'确认充值')]";
        clickOnElement(xPath);
        waitABit(5000);
    }

    public void clickOnRequestPayoutBtn() {
        String xPath = "//button/span[text()[normalizespace()='Request payout']]";
        clickOnElement(xPath);
    }

    public void clickOnViewPayoutsBtn() {
        String xPath = "//button/span[text()[normalizespace()='View payouts']]";
        clickOnElement(xPath);
    }

    public void inputPayoutamt(double amt) {
        String xPath = "//input[@placeholder='Enter amount to request payout']";
        waitClearAndType(xPath, Double.toString(amt));
    }

    public void clickOnSendRequestBtn() {
        String xPath = "//span[text()[normalizespace()='Send request']]";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
        }
    }

    public String getAutoTopupAmt() {
        String xPath = "//input[@type='number']";
        String autoTopupAmt = getTextValue(xPath);
        return autoTopupAmt;
    }

    public void verifyBalanceHistory(String type, String source, String shopName, String detail, String amount, String fee, String net, String status) {
        String type_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/precedingsibling::td[4]/span[text()[normalizespace()='" + type + "']]";
        String source_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/precedingsibling::td[3]/span[text()[normalizespace()='" + source + "']]";
        String shopName_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/precedingsibling::td[2]/span[text()[normalizespace()='" + shopName + "']]";
        String detail_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]";
        String amount_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/followingsibling::td[1]/span[text()[normalizespace()='" + amount + "']]";
        String fee_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/followingsibling::td[2]/span[text()[normalizespace()='" + fee + "']]";
        String net_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/followingsibling::td[3]/span[text()[normalizespace()='" + net + "']]";
        String status_xPath = "(//table//tbody//tr/td[5]/div[text()[normalizespace()='" + detail + "']])[1]/parent::td/followingsibling::td[4]//span[text()[normalizespace()='" + status + "']]";

        verifyElementPresent(type_xPath, true);
        verifyElementPresent(source_xPath, true);
        verifyElementPresent(shopName_xPath, true);
        verifyElementPresent(detail_xPath, true);
        verifyElementPresent(amount_xPath, true);
        verifyElementPresent(fee_xPath, true);
        verifyElementPresent(net_xPath, true);
        verifyElementPresent(status_xPath, true);
    }

    public boolean isStayAtViewHistoryScreen() {
        String xPath = "//span[contains(text(),'View history')]";
        boolean result;
        result = isElementVisible(xPath, 10);
        return result;
    }

    public void verifyNoBalanceInMenu() {
        String xPath = "//div[@class='d-flex flex-column']";
        String xPath_Balance = "//div[contains(text(),'Balance')]";

        clickOnElement(xPath);
        verifyElementVisible(xPath_Balance, false);
    }

    public void verifyNoBalanceInUrl() {
        openUrl("balance");
        String xPath = "//h1[contains(text(),'You don’t have permission')]";
        verifyElementVisible(xPath, true);
    }

    public void chargeBalance(double chargeAmt) {
        waitForEverythingComplete();
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/balance/charge-balance.json" + "?access_token=" + accessToken;
        JsonObject requestParams = new JsonObject();

        requestParams.addProperty("invoice_type", "charge");
        requestParams.addProperty("amount", chargeAmt);
        requestParams.addProperty("transaction_type", "charge");
        postAPI(url, requestParams);
    }

    public void verifyDetail(String paymentType, String domainURL, String duration) {
        String xpath = "//div[descendant::p[text()='Summary'] and contains(@class,'divider')]//div[@class='row']//div[not(contains(.,'$'))]";
        String detailActual = getText(xpath);
        assertThat(detailActual).contains(paymentType + "\n" + domainURL + " " + duration);
    }

    public void verifyPrice(double domainPrice) {
        String xpath = "//div[descendant::p[text()='Summary'] and contains(@class,'divider')]//div[@class='row']//div[contains(.,'$')]";
        String priceActual = getText(xpath).replace("$", "").replace(",", "");
        assertThat(Double.parseDouble(priceActual)).isEqualTo(domainPrice);
    }

    public void verifyTotal(double domainPrice) {
        String xpath = "//div[@class='row' and contains(.,'Total')]//div[contains(.,'$')]";
        String priceActual = getText(xpath).replace("$", "").replace(",", "");
        assertThat(Double.parseDouble(priceActual)).isEqualTo(domainPrice);
    }

    public void verifyAvailableBalance(double currentBalanceAmt) {
        double currentBalance = getCurrentAvailableBalanceByAPI();
        String xpath = "//div[@class='row' and contains(.,'Available balance')]//div[contains(.,'$')]";
        String priceActual = getText(xpath).replace("$", "").replace(",", "");
        assertThat(currentBalance).isEqualTo(currentBalanceAmt);
        assertThat(Double.parseDouble(priceActual)).isEqualTo(currentBalanceAmt);
    }

    public void verifyEnoughMoney(String enoughMoney) {
        String xpath = "//p[normalize-space()=\"You don't have enough balance for this payment. Please topup to ShopBase Balance in order to complete your transaction.\"]";
        switch (enoughMoney) {
            case "False":
                verifyElementVisible(xpath, true);
                break;
            case "True":
                waitForElementNotAppear(xpath);
                verifyElementVisible(xpath, false);
                break;
            default:
                fail();
        }
    }

    public void verifyTopUpAmount(double domainPrice) {
        String xpath = "//input[@placeholder='Enter amount to top-up']";
        String topUpActual = getValue(xpath).replace("$", "").replace(",", "");
        assertThat(Double.parseDouble(topUpActual)).isEqualTo(domainPrice);
    }

    public void verifyPaymentMethod(String selectAPaymentMethod) {
        String xpath = "//div[child::p[normalize-space()='Select a payment method']]//select";
        String actualPaymentMethod = getSelectedValueDdl(xpath);
        assertThat(actualPaymentMethod).isEqualTo(selectAPaymentMethod);
    }

    public void clickOnTopUpBtnOnSidebarMenuMakeAPayment() {
        String xpath = "//button[child::span[normalize-space()='Top up']]";
        clickOnElement(xpath);
        String xpathTopUpYourBalance = "//p[@class='sidebar-title' and normalize-space()='Top up your Balance']";
        waitElementToBeVisible(xpathTopUpYourBalance);
    }

    public void inputTopUpToBalanceOnSidebarMenuMakeAPayment(String topUpAmt) {
        String xpath = "//input[@placeholder='Enter amount to top-up']";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, topUpAmt.replace("$", ""));
    }

    public void clickOnBtnConfirmTopUp() {
        String xpath = "//button[child::span[normalize-space()='Confirm top up']]";
        clickOnElement(xpath);
        try {
            String xpathMakeAPayment = "//p[@class='sidebar-title' and normalize-space()='Make a payment']";
            waitElementToBeVisible(xpathMakeAPayment);
        } catch (Throwable t) {
            String xpathError = "//div[@id='inputSuggestNumber']//following-sibling::div[@class='s-form-item__error']";
            waitElementToBeVisible(xpathError);
        }
    }

    public double getCurrentAvailableBalanceByAPI() {
        String shop = LoadObject.getProperty("shop");
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/balance.json" + "?access_token=" + accessToken;
        JsonPath rs = getAPI(url);
        try {
            String currentAvailableBalance = rs.get("balance.available_amount").toString();
            return Double.parseDouble(currentAvailableBalance);
        } catch (Throwable t) {
            String currentAvailableBalance = rs.get("balance.available_amount").toString();
            return Double.parseDouble(currentAvailableBalance);
        }
    }

    public double getAvailableToPayoutByAPI() {
        String shop = LoadObject.getProperty("shop");
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/balance.json" + "?access_token=" + accessToken;
        JsonPath rs = getAPI(url);
        String currentAvailableBalance = rs.get("balance.available_payout").toString();
        return Double.parseDouble(currentAvailableBalance);
    }

    public double getAvailableSoonByAPI() {
        String shop = LoadObject.getProperty("shop");
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shop + "/admin/balance.json" + "?access_token=" + accessToken;
        JsonPath rs = getAPI(url);
        String currentAvailableBalance = rs.get("balance.available_soon").toString();
        return Double.parseDouble(currentAvailableBalance);
    }

    public void selectMoneyTransfer() {
        String xpath = "//span[text()='Money transfer']";
        String xpathCode = "//div[@class='divider']//input[@disabled='disabled']";
        clickOnElement(xpath);
        String[] text = getTextValue(xpathCode).split("-");
        topUpCode = text[text.length - 1];
    }

    public void inputTopupInfo(String amount, String account, String accHolder, String accEmail, String transId, String note) {
        String xpath_accHolder = "//label[text()[normalize-space()='Account holder']]//parent::div//following::div[@class='s-input']//input";
        String xpath_accEmail = "//input[@placeholder='johndoe@gmail.com']";
        String xpathTransID = "//input[@placeholder='Enter your transaction ID']";
        String xpathAmount = "//input[@placeholder='Enter amount to top-up']";
        String xpathAcc = "//p[contains(text(),'Select a preferred account')]//following::select";
        waitTypeAndEnter(xpathAmount, amount);
        selectDdlByXpath(xpathAcc, account);
        switch (account) {
            case "PayPal":
            case "PingPong":
                waitTypeAndEnter(xpath_accEmail, accEmail);
                waitTypeAndEnter(xpathTransID, transId);
                clickOnConfirmTopUpBtn();
                break;
            case "US Bank Account":
                waitTypeAndEnter(xpath_accHolder, accHolder);
                break;
            case "Payoneer":
                String xpath_accEmail2 = "//div[@class='col-xs-6']//input[@placeholder='johndoe@gmail.com']";
                String xpath_accHolder2 = "//div[@class='col-xs-6']//input[@placeholder='John Doe']";
                waitTypeAndEnter(xpath_accEmail2, accEmail);
                waitTypeAndEnter(xpath_accHolder2, accHolder);
                clickOnConfirmTopUpBtn();
                break;
        }
    }

    public void verifyConfirmTopup(String amount) {
        String xpath = "//p[normalize-space()= 'Successfully confirm top-up total amount: US $" + amount + "']";
        waitElementToBeVisible(xpath);
        verifyElementVisible(xpath, true);
    }

    public void filterBalanceHistoryByWithValue(String content, String value) {
        String xpathFilter = "//button//span[text()='More filters']";
//        String xpathContent = "//option[text()[normalize-space() ='Please select']]//parent::select";
        String xpathContent = "//*[normalize-space()='Filters']/following::*[contains(text(),'" + content + "')]";
        String xpathValue = "//option[text()[normalize-space() ='Select a content']]//parent::select";
        String xpathDone = "//button[normalize-space()='Done']";
        clickOnElement(xpathFilter);
        clickOnElement(xpathContent);
        clickOnElement(xpathValue);
        clickOnElement(xpathValue + "//option[text()[normalize-space() =\"" + value + "\"]]");
        clickOnElement(xpathDone);
//        selectDdlByXpath(xpathContent, content);
//        selectDdlByXpath(xpathValue, value);
//        clickOnElement(xpathFilter);
    }


    public void veiryInvoiceInfo(String type, String content, String amount, String status) {
        String xpathType = "//tr[@class='cursor-pointer'][1]//td[1]//span";
        String xpathContent = "//tr[@class='cursor-pointer'][1]//td[3]//span";
        String xpathAmount = "//tr[@class='cursor-pointer'][1]//td[4]//span";
        String xpathStatus = "//tr[@class='cursor-pointer'][1]//td[5]//span";
        assertThat(type).isEqualTo(getText(xpathType));
        assertThat(content).isEqualTo(getText(xpathContent));
        assertThat(amount).isEqualTo(getText(xpathAmount));
        assertThat(status).isEqualTo(getText(xpathStatus));

    }

    public void verifyWireTransferTransactionContent(String index, String content) {
        int colIndex = getIndexOfColumn("Content");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(getText(xpath)).isEqualTo(content + " " + topUpCode);
    }

    public int countElement(String xpath) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        int count = elements.size();
        return count;
    }

    public int getIndexOfColumn(String columnName) {
        String xpath = "//th[child::span[text()[normalize-space()='" + columnName + "']]]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public void clickOnLatestInvoice() {
        String xpath = "//tr[@class='cursor-pointer'][1]//td[3]//span";
        clickOnElement(xpath);
    }

    public void filterVerifyTopUpRequestByTopUpCode() {
        String hive = LoadObject.getProperty("hive").replaceAll("/login", "");
        String url = hive + "/app/topuprequest/list?filter%5Btop_up_code%5D%5Btype%5D=&filter%5Btop_up_code%5D%5Bvalue%5D=" + topUpCode;
        openUrl(url);
        verifyElementVisible("//*[contains(text(),'" + topUpCode + "')]", true);
    }

    public void actionTopupRequest(String action) {
        String xpath = "//a[text()='" + action + "']";
        clickOnElement(xpath);
        alertAccept();
    }

    public void logoutToHiveSbase() {
        String xpath = "//li[@class='dropdown user-menu']//a[@class='dropdown-toggle']";
        String logout = "//div[@class='pull-right']//a";
        clickOnElementByJs(xpath);
        clickOnElementByJs(logout);
    }

    public void clickEditPayoutAccount(String account) {
        String xpath = "//div[text()='" + account + ":']//following-sibling::button";
        clickOnElement(xpath);
    }

    public void inputPayoutAccount(String account, String value) {
        String xpath = "//div[text()='" + account + ":']//following-sibling::div//input";
        waitClearAndType(xpath, value);
    }

    public void getAutoTopupStatus() {
        String status = "//span[text()[normalize-space()='Enable auto recharge']]//parent::label[@class='s-checkbox']//input[@type='checkbox']";
        autoTopup = XH(status).isSelected();
    }

    public void CheckAutoTopupAndInputData() {
        String xAutoTopup = "//span[text()[normalize-space()='Enable auto recharge']]//parent::label[@class='s-checkbox']";
        //String xAmount = "//input[@type='number']";
        //String xThreshold = "//span[text()[normalize-space()='Send an email when my balance falls below']]//parent::div//select";
        waitABit(5000);
        if (autoTopup == true) {
            checkCheckbox(xAutoTopup, false);
            saveSetting();
            autoTopup = false;
        } else {
            checkCheckbox(xAutoTopup, true);
            saveSetting();
            autoTopup = true;
        }
    }

    public void clicktoUpdateCard() {
        String xpath = "//span[text()[normalize-space()='Replace credit card']]";
        clickOnElement(xpath);
        waitABit(10000);
    }

    public void inputUpdateCreditCard(String cardNumber, String expired_date, String cvv, String country) {
        String xNumber = "//div[@id='cc-number']//iframe";
        String xDate = "//div[@id='cc-expiration']//iframe";
        String xCvv = "//div[@id='cc-cvv']//iframe";
        String xCountry = "//input[@placeholder='Select country']";
        String xSave = "//span[text()[normalize-space()='Save']]//parent::button";
        String xSuccess = "//div[text()[normalize-space()='Credit card was saved successfully!']]";

        switchToIFrame(xNumber);
        enterInputFieldWithLabel("Credit Card Number", cardNumber);
        switchToIFrameDefault();

        switchToIFrame(xDate);
        enterInputFieldWithLabel("Expiration Date", expired_date);
        switchToIFrameDefault();

        switchToIFrame(xCvv);
        enterInputFieldWithLabel("CVV", cvv);
        switchToIFrameDefault();

        waitTypeAndEnter(xCountry, country);
        clickOnElement(xSave);
        waitElementToBeVisible(xSuccess);
    }

    public void verifyError(String error) {
        String xpath = "//div[@id='inputSuggestNumber']//following-sibling::div[@class='s-form-item__error']";
        waitElementToBeVisible(xpath);
        assertThat(getText(xpath)).isEqualTo(error);
    }

    public void clickToButtonPayNow() {
        String xpath = "//div[contains(@class,'sidebar')]//button[descendant::span[normalize-space()='Pay now']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathLoading = "//button[contains(@class,'is-loading')]";
        waitForElementNotAppear(xpathLoading);
    }

    public void verifyBalanceInvoiceType(String index, String type) {
        int colIndex = getIndexOfColumn("Type");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + colIndex + "]";
        assertThat(getText(xpath)).isEqualTo(type);
    }

    public void verifyBalanceInvoiceShopName(String index, String shopName) {
        int columnIndex = getIndexOfColumn("Shop name");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        if ("@Shop name@".equals(shopName)) {
            assertThat(getText(xpath)).isEqualTo(LoadObject.getProperty("shopname"));
        } else {
            assertThat(getText(xpath)).isEqualTo(shopName);
        }
    }

    public void verifyBalanceInvoiceContent(String index, String content) {
        int columnIndex = getIndexOfColumn("Content");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        assertThat(getText(xpath)).isEqualTo(content);
    }

    public void verifyBalanceInvoiceAmountDecrease(String index, double amount) {
        int columnIndex = getIndexOfColumn("Amount");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(-1 * amount);
    }

    public void verifyBalanceInvoiceAmountIncrease(String index, double amount) {
        int columnIndex = getIndexOfColumn("Amount");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(amount);
    }

    public void verifyBalanceInvoiceStatus(String index, String status) {
        int columnIndex = getIndexOfColumn("Status");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        assertThat(getText(xpath)).isEqualTo(status);
    }

    public void verifyBalanceInvoiceCreatedDate(String index, String createdDate) {
        int columnIndex = getIndexOfColumn("Created date");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        if ("@Now@".equals(createdDate)) {
            try {
                assertThat(getText(xpath)).isEqualTo("Just now");
            } catch (Throwable t1) {
                try {
                    assertThat(getText(xpath)).isEqualTo("1 minute ago");
                } catch (Throwable t2) {
                    try {
                        assertThat(getText(xpath)).isEqualTo("2 minutes ago");
                    } catch (Throwable t3) {
                        try {
                            assertThat(getText(xpath)).isEqualTo("3 minutes ago");
                        } catch (Throwable t4) {
                            assertThat(getText(xpath)).isEqualTo("4 minutes ago");
                        }
                    }
                }
            }
        } else if ("@NotNow@".equals(createdDate)) {
            System.out.println("Created date is not now");
        } else {
            assertThat(getText(xpath)).isEqualTo(createdDate);
        }
    }

    public void verifyBalanceInvoiceLatestTransactionDate(String index, String latestTransactionDate) {
        int columnIndex = getIndexOfColumn("Latest transaction date");
        String xpath = "(//table//tr[@class='cursor-pointer'])[" + index + "]//td[" + columnIndex + "]";
        if ("@Now@".equals(latestTransactionDate)) {
            try {
                assertThat(getText(xpath)).isEqualTo("Just now");
            } catch (Throwable t1) {
                try {
                    assertThat(getText(xpath)).isEqualTo("1 minute ago");
                } catch (Throwable t2) {
                    try {
                        assertThat(getText(xpath)).isEqualTo("2 minutes ago");
                    } catch (Throwable t3) {
                        try {
                            assertThat(getText(xpath)).isEqualTo("3 minutes ago");
                        } catch (Throwable t4) {
                            assertThat(getText(xpath)).isEqualTo("4 minutes ago");
                        }
                    }
                }
            }
        } else if ("@NotNow@".equals(latestTransactionDate)) {
            System.out.println("Latest transaction date is not now");
        } else {
            assertThat(getText(xpath)).isEqualTo(latestTransactionDate);
        }
    }

    public void checkedAutoTopup(boolean isCheck) {
        String xAutoTopup = "//span[text()[normalize-space()='Enable auto recharge']]//parent::label[@class='s-checkbox']";
        checkCheckbox(xAutoTopup, isCheck);
        saveSetting();
        waitABit(5000);
    }


    public double getAmountAutoTopup() {
        String xpath = "//input[@type='number']";
        double amount = Double.parseDouble(getTextValue(xpath).replace(",", "."));
        return amount;
    }

    public void openProductInSF(String productName) {
        String url = "https://" + shop + "/products/" + productName + "?from-dashboard=1";
        openUrl(url);
    }

    public void addToCartWithoutVerify() {
        String xpath = "//button[@id='add-to-cart']//span";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickToCheckoutWithoutVerify() {
        String xpath = "//button[@name='checkout']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    private int getIndexOfColumnCertical(String columnName) {
        String xpath = "//div[normalize-space()='" + columnName + "']//preceding-sibling::div";
        return countElementByXpath(xpath) + 1;
    }

    public void verifyInvoiceShopName(String shop) {
        int columnIndex = getIndexOfColumnCertical("Shop");
        String xpath = "(//div[@class='d-flex f-1']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        if ("@Shop name@".equals(shop)) {
            assertThat(getText(xpath)).isEqualTo(LoadObject.getProperty("shopname"));
        } else {
            assertThat(getText(xpath)).isEqualTo(shop);
        }
    }

    public void verifyInvoiceContent(String content) {
        int columnIndex = getIndexOfColumnCertical("Content");
        String xpath = "(//div[@class='d-flex f-1']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        assertThat(getText(xpath)).isEqualTo(content);
    }

    public void verifyInvoiceDetail(String detail) {
        int columnIndex = getIndexOfColumnCertical("Detail");
        String xpath = "(//div[@class='d-flex f-1']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        assertThat(getText(xpath)).isEqualTo(detail);
    }

    public void verifyInvoiceType(String type) {
        int columnIndex = getIndexOfColumnCertical("Type");
        String xpath = "(//div[@class='d-flex f-1 p-l-md']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        assertThat(getText(xpath)).isEqualTo(type);
    }

    public void verifyInvoiceAmountDecrease(double amount) {
        int columnIndex = getIndexOfColumnCertical("Amount");
        String xpath = "(//div[@class='d-flex f-1 p-l-md']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(-1 * amount);
    }

    public void verifyInvoiceAmountIncrease(double amount) {
        int columnIndex = getIndexOfColumnCertical("Amount");
        String xpath = "(//div[@class='d-flex f-1 p-l-md']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(amount);
    }

    public String getTimeZoneShop() {
        String shopURL = LoadObject.getProperty("shop");
        String accessToken = getAccessTokenShopBase();
        String url = "https://" + shopURL + "/admin/shop.json?access_token=" + accessToken + "";
        JsonPath rs = getAPI(url);
        String timeZone = rs.get("shop.iana_timezone").toString();
        return timeZone;
    }

    public void verifyInvoiceCreatedDate(String createdDate) {
        int columnIndex = getIndexOfColumnCertical("Created date");
        String xpath = "(//div[@class='d-flex f-1 p-l-md']//div[@class='d-flex flex-column']//div)[" + columnIndex + "]";
        if ("@Now@".equals(createdDate)) {
            try {
                assertThat(getText(xpath)).isEqualTo("Today");
            } catch (Throwable t) {
                String timeZone = getTimeZoneShop();
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("MMM d, yyyy");
                df.setTimeZone(TimeZone.getTimeZone(timeZone));
                assertThat(getText(xpath)).isEqualTo(df.format(date));
            }
        } else if ("@NotNow@".equals(createdDate)) {
            System.out.println("Created date is not now");
        } else {
            assertThat(getText(xpath)).isEqualTo(createdDate);
        }
    }

    public void openInvoice(String index) {
        String xpath = "(//div[@class='row balance-manager']//tbody//tr)[" + index + "]";
        clickOnElement(xpath);
    }

    public void verifyInvoiceTransactionType(String index, String type) {
        int colIndex = getIndexOfColumn("Type");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(getText(xpath)).isEqualTo(type);
    }

    public void verifyInvoiceTransactionContent(String index, String content) {
        int colIndex = getIndexOfColumn("Content");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(getText(xpath)).contains(content);
    }

    public void verifyInvoiceTransactionAmountDecrease(String index, double amount) {
        int colIndex = getIndexOfColumn("Amount");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(-1 * amount);
    }

    public void verifyInvoiceTransactionAmountIncrease(String index, double amount) {
        int colIndex = getIndexOfColumn("Amount");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(Double.parseDouble(getText(xpath).replace("$", ""))).isEqualTo(amount);
    }

    public void verifyInvoiceTransactionStatus(String index, String status) {
        int colIndex = getIndexOfColumn("Status");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        assertThat(getText(xpath)).isEqualTo(status);
    }

    public void verifyInvoiceTransactionDate(String index, String date) {
        int colIndex = getIndexOfColumn("Date");
        String xpath = "(//table//tbody//tr)[" + index + "]//td[" + colIndex + "]";
        if ("@Now@".equals(date)) {
            try {
                assertThat(getText(xpath)).isEqualTo("Just now");
            } catch (Throwable t1) {
                try {
                    assertThat(getText(xpath)).isEqualTo("1 minute ago");
                } catch (Throwable t2) {
                    try {
                        assertThat(getText(xpath)).isEqualTo("2 minutes ago");
                    } catch (Throwable t3) {
                        try {
                            assertThat(getText(xpath)).isEqualTo("3 minutes ago");
                        } catch (Throwable t4) {
                            assertThat(getText(xpath)).isEqualTo("4 minutes ago");
                        }
                    }
                }
            }
        } else if ("@NotNow@".equals(date)) {
            System.out.println("Date is not now");
        } else {
            assertThat(getText(xpath)).isEqualTo(date);
        }
    }

    public void completeOrderByPayLater() {
        String xpath = "//button[descendant::p[text()='Complete order']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathLoading = "//button[descendant::p[text()='Complete order'] and contains(@class,'is-loading')]";
        waitForElementNotAppear(xpathLoading);
    }

    public void payInvoiceOpen() {
        String xpath = "//h2//following-sibling::div//button[child::span[normalize-space()='Pay now']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathMakeAPayment = "//p[@class='sidebar-title' and normalize-space()='Make a payment']";
        waitElementToBeVisible(xpathMakeAPayment);
    }

    public void inputRefundBaseCost(String refundBaseCost) {
        String xpath = "//input[@id='refund_seller_amount']";
        waitClearAndType(xpath, refundBaseCost);
    }

    public void inputRefundProcessingFee(String refundProcessingFee) {
        String xpath = "//input[@id='refund_seller_amount_processing_fee']";
        waitClearAndType(xpath, refundProcessingFee);

    }

    public void inputRefundPaymentFee(String refundPaymentFee) {
        String xpath = "//input[@id='refund_seller_amount_payment_fee']";
        waitClearAndType(xpath, refundPaymentFee);
    }

    public void inputRefundSellingPrice(String refundSellingPrice) {
        String xpath = "//input[@id='refund_buyer_amount']";
        waitClearAndType(xpath, refundSellingPrice);
    }

    public void inputRefundShippingFee(String refundShippingFee) {
        String xpath = "//input[@id='refund_buyer_amount_shipping']";
        waitClearAndType(xpath, refundShippingFee);
    }

    public void inputRecoverPaymentFee(String recoverPaymentFee) {
        String xpath = "//input[@id='refund_buyer_amount_payment_fee']";
        waitClearAndType(xpath, recoverPaymentFee);
    }

    public void clickBtnRefund() {
        String xpath = "//button[text()='refund']";
        clickOnElement(xpath);
    }

    public void checkedConvertedSpayToBalance(boolean isCheck) {
        String xpath = "//p[text()[normalize-space()='Enable payout to ShopBase Balance']]//parent::span//preceding-sibling::span";
        clickOnElement(xpath);
        saveSetting();
    }

    public void clickOnBreadcrumbBalance() {
        String xpath = "//a[normalize-space()='Balance' or normalize-space()='余额']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
}
