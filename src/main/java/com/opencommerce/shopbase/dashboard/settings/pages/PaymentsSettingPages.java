package com.opencommerce.shopbase.dashboard.settings.pages;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.common.pages.CommonPage;
import common.SBasePageObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.kohsuke.rngom.digested.DDataPattern;
import org.openqa.selenium.*;

import java.io.*;
import java.util.*;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsSettingPages extends SBasePageObject {
    CommonPage commonPage;

    public PaymentsSettingPages(WebDriver driver) {
        super(driver);
    }

    private String xPath_thirdPartyAddNewAccountSection = "//*[text()[normalize-space()='Select a third-party provider']]//ancestor::div[contains(@class,'s-collapse-item__content')]";
    private String xPath_PayPalNewAccountSection = "(//section[contains(@class,'card payment_setting')][descendant::*[contains(text(),'PayPal credentials')]]//*[@class='s-collapse-item__content'])[last()]";

    private String locateSectionByAccountName(String accountName) {
        return "//*[text()[normalize-space()='" + accountName + "']]//ancestor::div[contains(@class,'s-collapse-item')]";
    }

    private String locateSectionByGateway(String gateway) {
        if ("PayPal".equalsIgnoreCase(gateway)) {
            return "//*[contains(@class,'card payment_setting')]";
        } else {
            return "//*[contains(@class,'card__header')]";
        }
    }

    //get input tag under Business details section by label
    private String getInputXpathInBusinessSection(String section, String label) {
        String xPath = "//div[text()[normalize-space()='" + section + "']]/..//div/label[text()[normalize-space()='" + label + "']]/../following-sibling::div//input";
        return xPath;
    }

    //get input tag under Personal details, customer billing statement, banking information section by placeholder
    private String getInputXpathWithSectionAndPlaceholder(String section, String placeholder) {
        String xPath = "//div[text()[normalize-space()='" + section + "']]/../following-sibling::div//input[@placeholder='" + placeholder + "']";
        return xPath;
    }

    //Setting>>Payments - click on "Register for ShopBase Payments account" Button
    public void clickOnRegisterForShopBasePaymentAccountBtn() {
        String xPath = "//button/span[text()[normalize-space()='Register for Shopbase Payments account']]";
        waitForEverythingComplete();
        if (isElementVisible(xPath, 5)) {
            clickOnElement(xPath);
        }
        waitABit(10000);
    }

    public String checkSpayStatus() {
        String xPath = "//section[@id='shopbase-payment-method']//button";
        return getTextByJS(xPath).trim();
    }

    //Setting>>Payments Providers and KYC form - click on "Complete account setup" Button
    public void clickOnCompleteAccountSetupBtn() {
        String xPath = "//button/span[text()[normalize-space()='Complete account setup']]";
        waitForEverythingComplete();
        waitUntilElementVisible(xPath, 5);
        waitForElementToPresent(xPath);
        clickOnElement(xPath);
        waitABit(10000);
        waitForEverythingComplete();
    }

    //Setting>>Payments Providers - click on "Verify Account Information" button
    public void clickOnVerifyAccountInformationBtn() {
        String xPath = "//button/span[text()[normalize-space()='Verify account information']]";
        waitForElementToPresent(xPath);
        clickOnElement(xPath);
    }

    public void verifyDisplayOfVerifyAccountInformationBtn() {
        String xPath = "//button/span[text()[normalize-space()='Verify account information']";
        verifyElementPresent(xPath, true);
    }

    public void verifyDisplayOfSwitchToShopBasePaymentsBtn() {
        int count = 0;
        String xPath = "//button/span[text()[normalize-space()='Switch to ShopBase Payments']]";
        //verifyElementPresent(xPath, true);
        while (!isElementVisible(xPath, 40)) {
            count++;
            refreshPage();
            waitForEverythingComplete();

            if (isElementVisible(xPath, 2)) {
                verifyElementPresent(xPath, true);
                System.out.print("How many tries: " + count);
                System.out.print(getTextValue(xPath));
                break;
            }
            if (count == 10) {
                verifyElementPresent(xPath, true);
                break;
            }
        }

    }

    public void clickOnSwitchToShopBasePaymentsBtn() {
        String xPath = "//button/span[text()[normalize-space()='Switch to ShopBase Payments']]";
        clickOnElement(xPath);
        waitForEverythingComplete();
    }

    public void verifyDisplayOfMessageAfterSubmitKYCRegisterFormInPayments() {
        String message = "Your ShopBase Payments account is under review. Please refresh this page in the next few minutes to see your account status.";
        String xPath = "//p[text()[normalize-space()='" + message + "']]";
        waitForEverythingComplete();
        verifyElementPresent(xPath, true);
    }

    //KYC register form - select business type from dropdown
    public void selectBusinessType(String businessType) {
        String xPath_dropdown = "//div/label[normalize-space()='Business type']/../following-sibling::div//select";
        String xPath_option = xPath_dropdown + "//option[text()[normalize-space()='" + businessType + "']]";
        if (isElementVisible(xPath_dropdown, 3)) {
            clickOnElement(xPath_dropdown);
            waitForEverythingComplete();
            waitElementToBeVisible(xPath_option);
            clickOnElement(xPath_option);
        }
    }

    //KYC register form - input value for business name
    public void inputLegalBusinessName(String businessName) {
        //String xPath = "//div/label[text()='Legal business name']/../following-sibling::div//input";
        String xPath = getInputXpathInBusinessSection("Business details", "Legal business name");
        waitClearAndType(xPath, businessName);
    }

    //KYC register form - input value for employer ID
    public void inputEmployerIdentificationNumber(String businessType, String id) {
        String xPath = getInputXpathInBusinessSection("Business details", "Employer Identification Number (EIN)");
        String _xPath = getInputXpathInBusinessSection("Business details", "Employer Identification Number (EIN) or Social Security number (SSN)");
        if (businessType.equals("LLC")) {
            waitForElementToPresent(_xPath);
            waitClearAndTypeThenTab(_xPath, id);
        } else {
            waitForElementToPresent(xPath);
            waitClearAndTypeThenTab(xPath, id);
        }
    }

    //KYC register form - input value for business address
    public void inputBusinessAddress(String address) {
        String xPath = getInputXpathInBusinessSection("Business details", "Address");
        waitClearAndType(xPath, address);
    }

    //KYC register form - input value for business city
    public void inputBusinessCity(String city) {
        String xPath = getInputXpathInBusinessSection("Business details", "City");
        waitClearAndType(xPath, city);
    }

    //KYC register form - input value for business zip code
    public void inputBusinessZipCode(String zipCode) {
        String xPath = getInputXpathInBusinessSection("Business details", "ZIP code");
        waitClearAndType(xPath, zipCode);
    }

    //KYC Register form - select state if this control is present
    public void selectBusinessState(String state) {
        String xPath_dropdown = "//div[text()[normalize-space()='Business details']]/following-sibling::div//label[text()='State']/../following-sibling::div//select";
        String xPath_option = "//option[text()[normalize-space()='" + state + "']]";
        if (isElementVisible(xPath_dropdown, 3)) {
            clickOnElement(xPath_dropdown);
            waitForEverythingComplete();
            waitElementToBeVisible(xPath_option);
            clickOnElement(xPath_option);
        }
    }

    //KYC register form - input value for personal first name
    public void inputPersonalFirstName(String firstName) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "John");
        waitClearAndType(xPath, firstName);
    }

    //KYC register form - input value for personal last name
    public void inputPersonalLastName(String lastName) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "Appleseed");
        waitClearAndType(xPath, lastName);
    }

    //KYC register form - input value for personal date of birth
    public void inputPersonalDOB(String dob) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "Pick a day");
        waitClearAndTypeThenTab(xPath, dob);
    }

    //KYC register form - input value for personal address
    public void inputPersonalAddress(String address) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "1592 Hillhaven Drive");
        waitClearAndType(xPath, address);
    }

    //KYC register form - input value for personal city
    public void inputPersonalCity(String city) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "Hollywood");
        waitClearAndType(xPath, city);
    }

    //KYC register form - input value for personal zip code
    public void inputPersonalZipCode(String zipCode) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "90028");
        waitClearAndType(xPath, zipCode);
    }

    //KYC register form - select personal state from dropdown
    public void inputPersonalState(String state) {
        String xPath_dropdown = "//div[text()[normalize-space()='Personal details']]/../following-sibling::div//label[text()='State']/../following-sibling::div//select";
        String xPath_option = xPath_dropdown + "//option[text()[normalize-space()='" + state + "']]";
        if (isElementVisible(xPath_dropdown, 3)) {
            clickOnElement(xPath_dropdown);
            waitForEverythingComplete();
            waitElementToBeVisible(xPath_option);
            clickOnElement(xPath_option);
        }
    }

    //KYC register form - input value for Last 4 digits of your Social Security number (SSN)
    public void inputPersonalSSN(String ssn) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Personal details", "0099");
        waitClearAndType(xPath, ssn);
    }

    //KYC register form - input value for Statement Descriptor
    public void inputStatementDescriptor(String descriptor) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Customer billing statement", "Shop name");
        waitClearAndType(xPath, descriptor);
    }

    //KYC register form - input value for billing statement phone number
    public void inputCustomerBillingStatementPhoneNum(String phoneNum) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Customer billing statement", "323-856-0140");
        clickThenTypeCharByChar(xPath, phoneNum);
    }

    //KYC register form - input value for banking info routing number
    public void inputBaningInfoRoutingNum(String routingNum) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Banking information", "Enter your routing number");
        waitClearAndType(xPath, routingNum);
    }

    //KYC register form - input value for banking info account number
    public void inputBaningInfoAccountNum(String phoneNum) {
        String xPath = getInputXpathWithSectionAndPlaceholder("Banking information", "Enter your account number");
        waitClearAndType(xPath, phoneNum);
    }

    //KYC register form - select banking info currency from dropdown
    public void inputPayoutCurrency(String currency) {
        String xPath_dropdown = "//div[text()[normalize-space()='Banking information']]/../following-sibling::div//label[text()='Payout currency']/../following-sibling::div//select";
        String xPath_option = xPath_dropdown + "//option[text()[normalize-space()='" + currency + "']]";
        if (isElementVisible(xPath_dropdown, 3)) {
            clickOnElement(xPath_dropdown);
            waitForEverythingComplete();
            waitElementToBeVisible(xPath_option);
            clickOnElement(xPath_option);
        }
    }

    public void verifyMessageAfterClickONCompleteAccountSetup() {
        String message = "Thank you for your information. Your ShopBase Payments account is under review. We will inform you as soon as possible.";
        String xPath = "//p[text()[normalize-space()='" + message + "']]";
        waitForEverythingComplete(10);
        waitUntilElementVisible(xPath, 10);
        verifyElementPresent(xPath, true);
        String _xPath = "//p";
        System.out.println(getTextValue(_xPath));
    }

    public void clickOnBreadcrumb(String text) {
        String xPath = "//a[text()[normalize-space()='" + text + "']]";
        if (isElementVisible(xPath, 10)) {
            clickOnElement(xPath);
        }
    }

    //get email from Settings>>General
    private String getAccountEmail() {
        String xPath = "//label[text()[normalize-space()='Account Email']]/../following-sibling::div//input";
        String email = getAttributeValue(xPath, "value");
        return email;
    }

    private String getShopName() {
        String xPath = "//label[text()[normalize-space()='Store name']]/../following-sibling::div//input";
        String shopName = getAttributeValue(xPath, "value");
        return shopName;
    }

    public void openMailinator() {
        String url = "https://www.mailinator.com";
        ((JavascriptExecutor) getDriver()).executeScript("window.open('" + url + "','_blank');");
    }

    public void openShopbase() {
        String url = "https://accounts.dev.shopbase.net/sign-in";
        openUrl(url);
    }

    public void checkEmailSentAfterRegisterApproving() throws InterruptedException {
        String email = getAccountEmail();
        String shopName = getShopName();
        System.out.println(email);
        String xPath_emailTextbox = "(//div[@class='input-group']//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        String xPath_CongratulationEmail = "//table//tbody/tr/td[4]/a[text()[normalize-space()='Congratulation! Your [" + shopName + "] shop is verified ShopBase Payment']]";
        String xPath_time = xPath_CongratulationEmail + "/parent::td/following-sibling::td[contains(text(),'moments ago')]";
        //String xPath_reverifyEmail = "//a[text()[normalize-space()='[" + shopName + "] - Please reverify your payment account information']]";
        openMailinator();
        switchToWindowWithIndex(1);
        WebElement inputTextbox = getDriver().findElement(By.xpath(xPath_emailTextbox));
        inputTextbox.clear();
        inputTextbox.sendKeys(email);
        inputTextbox.sendKeys(Keys.ENTER);
        //waitClearAndTypeThenEnter(xPath_emailTextbox, email);
        int count = 0;

        while (!isElementVisible(xPath_CongratulationEmail, 20)) {
            count++;
            refreshPage();
            waitForEverythingComplete();

            if (isElementVisible(xPath_CongratulationEmail, 2)) {
                verifyElementPresent(xPath_time, true);
                verifyElementPresent(xPath_CongratulationEmail, true);
                System.out.print("How many tries: " + count);
                System.out.print(getTextValue(xPath_CongratulationEmail));
                break;
            }
            if (count == 20) {
                verifyElementPresent(xPath_CongratulationEmail, true);
                break;
            }
        }

    }

    //Payment providers screen - verify shopbase payments logo
    public void verifyDisplayOfShopBasePaymentLogo() {
        String xPath = "//section[@class='card']//img[@alt='ShopBase Payment Logo']";
        verifyElementPresent(xPath, true);
    }

    //Payment providers screen - verify transactions is enabled
    public void verifyDisplayOfTransactionStatus() {
        String xPath_label = "//span[text()[normalize-space()='Transactions:']]";
        String xPath_status = xPath_label + "/following-sibling::span/*[text()[normalize-space()='Enabled']]";
        verifyElementPresent(xPath_status, true);
    }

    //Payment providers screen - verify payouts is enabled
    public void verifyDisplayOfPayoutsStatus() {
        String xPath_label = "//span[text()[normalize-space()='Payouts:']]";
        String xPath_status = xPath_label + "/following-sibling::span/*[text()[normalize-space()='Enabled']]";
        verifyElementPresent(xPath_status, true);
    }

    public void addOrderDetailsToCSV(String fileName, List<String> listOrder) {
        File file = new File(fileName);
        try {
            FileWriter outputfile = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(outputfile, '|',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
//            String[] header = {"Order Number", "Trx Type", "Total Amt", "Trx Fee", "Trx Net","Trx Date"};
//            writer.writeNext(header);
            for (String orderInfo : listOrder) {
                writer.writeNext(orderInfo.split("\\|"));
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readingOrderInfo(String filePath) throws IOException {

        ArrayList<String> orderInfo = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line; //ignoring
        while ((line = br.readLine()) != null) {
            orderInfo.add(line);
            System.out.println("==========================line:" + orderInfo);
        }
        return orderInfo;
    }

    public void clearCsv(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }

    public void inputAccountNameInSection(String section, String value) {
        String xPath = "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'Account Name')]]//input";
        if ("PayPal credentials".equalsIgnoreCase(section)) {
            xPath = xPath_PayPalNewAccountSection + xPath;
        } else {
            xPath = xPath_thirdPartyAddNewAccountSection + xPath;
        }
        waitClearAndType(xPath, value);
    }

    public void inputAccountNameUpdateUIUX(String value) {
        String xPath = "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'Account Name')]]//input";
        waitClearAndType(xPath, value);
    }

    public void inputCredentialsByLabel(String accountName, String label, String value) {
        String xPath = "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'" + label + "')]]//input";
        if (accountName.isEmpty()) {
            xPath = "((" + xPath_thirdPartyAddNewAccountSection + ")|(" + xPath_PayPalNewAccountSection + "))" + xPath;
        } else {
            xPath = locateSectionByAccountName(accountName) + xPath;
        }
        waitClearAndType(xPath, value);
    }

    public void inputCredentialsByLabelUpdateUIUX(String accountName, String label, String value) {
        String xPath = "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'" + label + "')]]//input";
        if (!accountName.isEmpty()) {
            xPath = locateSectionByAccountName(accountName) + xPath;
        }
        waitClearAndType(xPath, value);
    }

    public void clickOnSelectProviderDropDown() {
        String xPath = xPath_thirdPartyAddNewAccountSection + "//*[contains(@class,'checkbox-wrapper')]//select";
        clickOnElement(xPath);
    }

    public void selectPaymentGateway(String paymentGateway) {
        String xPath = xPath_thirdPartyAddNewAccountSection + "//option[text()[normalize-space()='" + paymentGateway + "']]";
        clickOnElement(xPath);
    }

    public void clickCheckboxUseAPI() {
        String xPath = xPath_thirdPartyAddNewAccountSection + "//*[text()[normalize-space()='Use API key to activate']]/preceding-sibling::span";
        clickOnElement(xPath);
    }

    public void clickCheckboxUseAPIUpdateUIUX() {
//        String xPath = "//*[text()[normalize-space()='Use API Keys to connect']]/preceding-sibling::span";
//        clickOnElement(xPath);
        checkCheckboxWithLabel("Use API Keys to connect");
    }


    public void setToggleStatus(String label, String location, boolean status) {
        //location = "Add another account" / {{account name}} / PayPal Add another account
        String xPath_checkbox = "//*[text()[normalize-space()='" + label + "']]/following-sibling::label/span";
        String xPath_status = "//*[text()[normalize-space()='" + label + "']]/following-sibling::label/input";
        if ("Add another account".equalsIgnoreCase(location)) {
            xPath_status = xPath_thirdPartyAddNewAccountSection + xPath_status;
            xPath_checkbox = xPath_thirdPartyAddNewAccountSection + xPath_checkbox;
        } else if ("PayPal Add another account".equalsIgnoreCase(location)) {
            xPath_checkbox = xPath_PayPalNewAccountSection + xPath_checkbox;
            xPath_status = xPath_PayPalNewAccountSection + xPath_status;
        } else {
            xPath_checkbox = locateSectionByAccountName(location) + xPath_checkbox;
            xPath_status = locateSectionByAccountName(location) + xPath_status;
        }
        verifyCheckedThenClick(xPath_status, xPath_checkbox, status);
    }

    public void setToggleStatusUpdateUIUX(String label, boolean status) {
        //location = "Add another account" / {{account name}} / PayPal Add another account
        String xPath_checkbox = "//*[text()[normalize-space()='" + label + "']]/following-sibling::label/span";
        String xPath_status = "//*[text()[normalize-space()='" + label + "']]/following-sibling::label/input";
        verifyCheckedThenClick(xPath_status, xPath_checkbox, status);
    }

    public void verifyNoticeMessage(String message) {
        String xPath = "//*[contains(text(),'" + message + "')]";
        verifyElementVisible(xPath, true);
    }

    public void verifyErrorMessage(String message) {
        String xPath = "//div[@class='s-form-item__error']";
        verifyElementText(xPath, message);
    }

    public boolean checkAnyGatewayIsActivated() {
        String xPath = "//div[@class='card__section']//*[text()[normalize-space()='Deactivate']]";
        return isElementVisible(xPath, 3);
    }

    public void clickOnAddAnotherAccountBtn(String section) {
        String xPath = "(//section[contains(@class,'card payment_setting')][descendant::*[contains(text(),'" + section + "')]]//*[text()[normalize-space()='Add another account']])[last()]";
        clickOnElement(xPath);
    }

    //Navigate to setting of each Section
    public void clickOnBtnOfEachBlock(String btnName) {
        String xPath = "//*[contains(@class,'button')][descendant::*[contains(text(),'" + btnName + "')]]";
        if (!isElementExist(xPath)) {
            clickLinkTextWithLabel(btnName);
        } else {
            clickOnElement(xPath);
        }
    }

    public void waitForPageTitleToPresent(String pageTitle) {
        String xPathHeader = "//div[contains(@class,'setting-page__title') and contains(text(),'" + pageTitle + "')]";
        waitForElementToPresent(xPathHeader);
    }

    public void selectPaymentProvider(String paymentProvider) {
        String xPath = "//table[descendant::*[contains(text(),'provider name')]]//*[text()[normalize-space()='" + paymentProvider + "']]";
        clickOnElement(xPath);
    }


    public void inputBillingStatement(String accountName, String label, String value) {
        String xPath = locateSectionByAccountName(accountName) + "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'" + label + "')]]//input";
        waitClearAndType(xPath, value);
    }

    public void verifyAccountName(String accountName) {
        String xPath = "//*[contains(@class,'provider')]//*[@role='button']//strong[text()='" + accountName + "']";
        verifyElementPresent(xPath, true, 10);
    }

    public void clickOnAccountName(String accountName) {
        String xPath = "//*[contains(@class,'provider')]//*[@role='button']//strong[text()='" + accountName + "']";
        waitForPageLoad();
        scrollToElement(xPath);
        clickOnElement(xPath);
    }

    public void verifyGatewayUsed(String accountName, String gateway) {
        String xPath = "//*[contains(text(),'Account name')]//strong[text()='" + accountName + "']/..//following-sibling::p//strong[text()='" + gateway + "']";
        verifyElementPresent(xPath, true, 10);
    }

    public void verifyGatewayUsedUpdateUIUX(String accountName, String gateway) {
        String xPath = locateSectionByGateway(gateway) + "[descendant::*[contains(text(),'" + gateway + "')]]/following-sibling::section//strong[text()='" + accountName + "']";
        verifyElementPresent(xPath, true, 10);
    }

    private void clickOnSecretKeyEyeIcon(String accountName, String label) {
        String xPath = locateSectionByAccountName(accountName) + "//*[contains(text(),'" + label + "')]/parent::div/following-sibling::div//img";
        clickOnElement(xPath);
    }

    public String getInfoByLabelOfAccount(String accountName, String label) {
        String xPath = locateSectionByAccountName(accountName) + "//div[contains(@class,'s-form-item')][descendant::*[contains(text(),'" + label + "')]]//input";
        return getTextValue(xPath);
    }

    public String getInfoOfPasswordFieldByLabelOfAccount(String accountName, String label) {
        String xPath = locateSectionByAccountName(accountName) + "//*[contains(text(),'" + label + "')]/parent::div/following-sibling::div//input";
        clickOnSecretKeyEyeIcon(accountName, label);
        return getTextValue(xPath);
    }

    public void expandGatewayEditingForm(String accountName) {
        String xPath = "//*[contains(text(),'Account name')]//strong[text()='" + accountName + "']";
        clickOnElement(xPath);
    }

    public void expandGatewayEditingFormUpdateUIUX(String accountName, String gateway) {
        String xPath = locateSectionByGateway(gateway) + "[descendant::*[contains(text(),'" + gateway + "')]]/following-sibling::section//strong[contains(text(),'" + accountName + "')]";
        clickOnElement(xPath);
    }

    public void clickOnBtnInSectionOfAccount(String accountName, String btnName) {
        String xPath = locateSectionByAccountName(accountName) + "//*[contains(text(),'" + btnName + "')]";
        scrollIntoElementView(xPath);
        clickOnElement(xPath);
    }

    public void clickBtnInSection(String section, String btnName) {
        String xPath = "//*[contains(text(),'" + btnName + "')]";
        if ("Account list".equalsIgnoreCase(section)) {
            xPath = xPath_thirdPartyAddNewAccountSection + xPath;
        }
        if ("PayPal credentials".equalsIgnoreCase(section)) {
            xPath = xPath_PayPalNewAccountSection + xPath;
        }
        clickOnElement(xPath);
    }

    public void clickBtnUpdateUIUX(String btnName, boolean isValidation) {
        String xPath = "//*[contains(text(),'" + btnName + "')]";
        String xPathMsg = "//*[contains(text(),'Activated successfully')]";
        clickOnElement(xPath);
        if (!isValidation) {
            verifyElementVisible(xPathMsg, true);
        }
    }

    public void clickBtnLoginWithPayPal() {
        String xPath = "//*[contains(text(),'Sign in/Sign up with PayPal')]";
        clickOnElement(xPath);
    }

    public void switchToPaypalLoginPage() {
        ArrayList<String> availableWindows = new ArrayList<String>(getDriver().getWindowHandles());
        if ((availableWindows.size()) > 1) {
            getDriver().switchTo().window(availableWindows.get(1));
        }
    }

    public void clickOnAcceptCookieBtn() {
        String xPath = "//*[@id='acceptAllButton']";
        if (isElementVisible(xPath, 5)) {
            waitABit(3000);
            $(xPath).click();
        }
    }

    public void clickToGoBackFromPayPalLoginPage() {
        String xPath = "//*[contains(@class,'Button') and contains(text(),'Go back to')]";
        clickOnElement(xPath);
    }

    public void verifyStatusOfAccount(String accountName, String expectedStatus) {
        String xPath = "//*[contains(@class,'card payment_setting')]//*[contains(text(),'" + accountName + "')]//preceding::span[1]";
        String actualStatus = getAttributeValue(xPath, "data-label");
        assertThat(actualStatus).isEqualTo(expectedStatus);
        System.out.println(actualStatus);
    }
//
//    public void verifyStatusOfAccountUpdateUIUX(String accountName, String gateway, String expectedStatus) {
//        String xPathOfGateway = "//*[contains(@class,'card__header')][descendant::*[contains(text(),'" + gateway + "')]]/following-sibling::section//strong[text()='" + accountName + "']";
//        String xPathOfStatus = xPathOfGateway + "/../..//preceding-sibling::div/span";
//        String actualStatus = getAttributeValue(xPathOfStatus, "data-label");
//        assertThat(actualStatus).isEqualTo(expectedStatus);
//        System.out.println(actualStatus);
//    }
//
//    public void verifyStatusOfPayPalAccountUpdateUIUX(String accountName, String expectedStatus) {
//        String xPathOfGateway = "//*[contains(@class,'card payment_setting')][descendant::*[contains(text(),'PayPal Accounts')]]/following-sibling::section//strong[contains(text(),'" + accountName + "')]";
//        String xPathOfStatus = xPathOfGateway + "/../..//preceding-sibling::div/span";
//        String actualStatus = getAttributeValue(xPathOfStatus, "data-label");
//        assertThat(actualStatus).isEqualTo(expectedStatus);
//        System.out.println(actualStatus);
//    }

    public void verifyAccountNameAfterRemoving(String accountName) {
        String xPath = "//*[contains(text(),'Account name')]//strong[text()[normalize-space()='" + accountName + "']]";
        waitForEverythingComplete();
        verifyElementVisible(xPath, false);
    }

    public void verifyAccountAfterRemovingUpdateUIUX(String accountName, String gateway) {
        String xPath = "//*[contains(@class,'card__header')][descendant::*[contains(text(),'" + gateway + "')]]/following-sibling::section//strong[text()='" + accountName + "']";
        waitForEverythingComplete();
        verifyElementVisible(xPath, false);
    }

    public void verifyDisplayOfRemoveConfirmPopup() {
        String xPath = "//*[text()[normalize-space()='Remove this account?']]";
        verifyElementVisible(xPath, true);
    }

    public void clickOnRemoveBtnInPopup() {
        String xPath = "//button[text()[normalize-space()='Remove']]";
        String xPathMsg = "//div[@class='s-toast is-dark is-bottom']//div[text()='Delete successfully']";
        clickOnElement(xPath);
        verifyElementVisible(xPathMsg, true);
    }

    public void clickOnCancelBtnInPopup() {
        String xPath = "//button[text()[normalize-space()='Cancel']]";
        clickOnElement(xPath);
    }

    public void navigateBackUpdateUIUX(String pageName) {
        String xPath = "//span[contains(text(),'" + pageName + "')]";
        clickOnElement(xPath);
    }

    public void verifyButtonIsDisabled(String section, String accountName, String btnName) {
        String xPath = "//*[text()[normalize-space()='" + btnName + "']]/parent::button[@disabled='disabled']";
        if (accountName.isEmpty()) {
            if (!section.contains("PayPal")) {
                xPath = xPath_thirdPartyAddNewAccountSection + xPath;
            } else {
                xPath = xPath_PayPalNewAccountSection + xPath;
            }
        } else {
            xPath = locateSectionByAccountName(accountName) + xPath;
        }
        verifyElementVisible(xPath, true);
    }

    //    public void checkToggleStatus(){
//        String xPath = "//*[contains(@class,'s-form-item__wrap')][descendant::*[contains(text(),'Paypal tracking info autopilot')]]//following-sibling::*[contains(@class,'s-form-item__content')]";
//        verifyCheckedThenClick(xPath,xPath,true);
//    }
    public void verifyDisplayOfFields(String accountName, String label, boolean status) {
        String xPath = "";
        if (!accountName.isEmpty()) {
            xPath = locateSectionByAccountName(accountName) + "//*[contains(text(),'" + label + "')]";
        } else {
            xPath = xPath_thirdPartyAddNewAccountSection + "//*[contains(text(),'" + label + "')]";
        }
        verifyElementVisible(xPath, status);
    }

    public List<String> getAccountName(String accountName) {
        String xPath = "//*[contains(@class,'provider')]//*[@role='button']//strong";
        if (!accountName.isEmpty()) {
            xPath = xPath + "[text()='" + accountName + "']";
        }
        List<String> listOfAccountName = new ArrayList<>();
        if (isElementVisible(xPath, 3)) {
            listOfAccountName = getListText(xPath);
        }
        return listOfAccountName;
    }

    public String getConnectedAccountEmail() {
        String xPath = "//*[text()='Connected account']/following-sibling::p[contains(@class,'connect-email')]";
        waitForEverythingComplete(20);
        return getTextValue(xPath);
    }

    public void verifyPaymentRotation(String paymentMethod, HashMap<String, List<Integer>> listOfPaymentId, List<Integer> listOfUsedPaymentId) {
        List<Integer> _listOfPaymentId = listOfPaymentId.get(paymentMethod);
        Collections.sort(_listOfPaymentId);
        Collections.sort(listOfUsedPaymentId);
        System.out.println(_listOfPaymentId);
        System.out.println(listOfUsedPaymentId);
        assertThat(_listOfPaymentId).isEqualTo(listOfUsedPaymentId);
    }

    public void verifyHeader() {
        String xpath = "//div[@class='add-customer-heading']";
        verifyElementPresent(xpath, true);
    }

    public void verifyStatement() {
        String xpath = "//div[@class='row m-b']";
        verifyElementPresent(xpath, true);
    }

    public void verifyAcceptCreditCard() {
        String xpath = "//div[@class='row disable-section'][1]";
        verifyElementPresent(xpath, true);
    }

    public void verifyShopbasePayment() {
        String xpath = "//div[@class='row disable-section'][2]";
        verifyElementPresent(xpath, true);
    }

    public void verifyPaypal() {
        String xpath = "//div[@class='row m-t-lg disable-section']";
        verifyElementPresent(xpath, true);
    }

    String paypalLoginButtonXpath = "//*[text()[normalize-space()='Log In'] or @class='btn full ng-binding' or @id='btnLogin' or text()[normalize-space()='Đăng nhập']]";

    public void clickOnLogin() {
        if (isElementVisible(paypalLoginButtonXpath, 10))
            $(paypalLoginButtonXpath).click();

    }

    public boolean checkLoginBtnDisplay() {
        return isElementVisible(paypalLoginButtonXpath, 5);
    }


    //    -----manual payment method-----
    public void selectAManualPaymentMethod(String manualPM) {
        waitForEverythingComplete();
        String xPathDropDown = "//span[contains(text(),'Manual payment methods')]";
        String xPathPaymentMethod = "//span[contains(text(),'Manual payment methods')]/following::span[contains(text(),'"+ manualPM+ "')]";
        clickOnElement(xPathDropDown);
        clickOnElement(xPathPaymentMethod);
    }

    public void verifyManualPaymentMethodStatus(String manualPM) {
        waitForPageLoad();
        String xpath = "//section[contains(@class,'activated-cod')]//*[text()[normalize-space()='" + manualPM + " is active']]";
        verifyElementVisible(xpath, true);
        scrollIntoElementView(xpath);
    }

    public void verifyTextInAdditionalDetails(String data) {
        waitForPageLoad();
        String xpath = "//section[contains(@class,'activated-cod')]//*[text()[normalize-space()='" + data + "']]";
        verifyElementVisible(xpath, true);
    }

    public void clickToDeactiveManualPaymentMethod(String manualPM) {
        waitForPageLoad();
        String xPathDeactivebtn = "//section[contains(@class,'activated-cod')]//*[text()[normalize-space()='Deactivate " + manualPM + "']]";
        if (isElementExist(xPathDeactivebtn)) {
            scrollIntoElementView(xPathDeactivebtn);
            clickOnElement(xPathDeactivebtn);
            String actMsg = commonPage.getToastMsg();
            assertThat(actMsg).isEqualTo("Deactivated successfully");
        }
    }


    public void clickOnGateway(String gateway) {
        clickOnElement("//tr//span[contains(text(),'" + gateway + "')]");
    }

    public void verifyGatewayInList(String gateway, boolean isInList) {
        verifyElementPresent("//section[@class='card payment_setting']//strong[normalize-space()='" + gateway + "']", isInList);
    }

    public void clickGatewayInlist(String gateway) {
        clickOnElement("//section[@class='card payment_setting']//div[@class='s-flex-item s-flex--fill']//strong[contains(text(),'" + gateway + "')]");
    }

    public Boolean getStatusGateway(String gateway) {
        String xpath = "//div[contains(@class,'s-collapse-item__header is-active') and descendant::strong[contains(text(),'" + gateway + "')]]";
        if (isElementExist(xpath)) return true;
        else return false;
    }

    public void verifyMsgDeactivedSuccessfully() {
        verifyElementPresent("//div[normalize-space()='Deactivated successfully']", true);
    }

    public void verifyStatusGateway(String gateway, boolean isActive) {
        verifyElementPresent("//div[contains(@class,'s-collapse-item__header') and descendant::strong[contains(text(),'" + gateway + "')]]//span[@data-label='Active']", isActive);
    }

    public void clickbtnDeactivate(String gateway) {
        clickOnElement("//div[@class='s-collapse-item is-active provider-collapse' and descendant::strong[contains(text(),'" + gateway + "')]]//button[normalize-space()='Deactivate']");
    }

    public void clickbtnActivate(String gateway) {
        clickOnElement("//div[@class='s-collapse-item is-active provider-collapse' and descendant::strong[contains(text(),'" + gateway + "')]]//button[normalize-space()='Activate']");
    }

    public void verifyMsgActivedSuccessfully() {
        verifyElementPresent("//div[normalize-space()='Activated successfully']", true);
    }

    public List<String> getAccountList() {
        String xPath = "//div[@class='row' and descendant::h2[normalize-space()='Alternative methods']]//div[@class='s-collapse-item__header']";
        List<String> listOfAccountName = new ArrayList<>();
        if (isElementVisible(xPath, 3)) {
            listOfAccountName = getListText(xPath);
        }
        return listOfAccountName;
    }

    public void clickBtnRemoveAccount(String account) {
        clickOnElement("//div[@class='s-collapse-item is-active provider-collapse' and descendant::strong[contains(text(),'" + account + "')]]//a[normalize-space()='Remove account']");
    }

    public void verifyRemoveAccountSuccessfully() {
        verifyElementPresent("//div[normalize-space()='Delete successfully']", true);
    }

//    Spay Reseller

    public String getTextUnderSpayResellerButton(int index) {
        String xPath = "//section[@id='shopbase-payment-method']//p[" + index + "]";
        return getTextValue(xPath);
    }

    public String getXpathForAddressDd(String label, int index) {
        String xPath = "";
        switch (label) {
            case "Country": {
                switch (index) {
                    case 1:
                        xPath = "//*[@for='country']/following::div//select";
                        break;
                    case 2:
                        xPath = "//*[@for='account_opener_country']/following::div//select";
                        break;
                }
                break;
            }
            case "State": {
                switch (index) {
                    case 1:
                        xPath = "//*[@for='address_state']/following::div//select";
                        break;
                    case 2:
                        xPath = "//*[@for='account_opener_address_state']/following::div//select";
                        break;
                }
                break;
            }
        }
        return xPath;
    }

    public String getShopbasePaymentAccountStatus(String field) {
        String xPath = "//section[@id='shopbase-payment-method']//*[contains(text(),'" + field + "')]//following-sibling::span[1]";
        return getTextValue(xPath);
    }

    public void clickToExpandSpayResellerInformation() {
        String xPath = "//section[@id='shopbase-payment-method']//*[contains(@class,'sbase-collapse')]";
        refreshPage();
        waitForEverythingComplete();
        clickOnElement(xPath);
    }

    public void clickToDeactivateSpayReseller() {
        String xPath = "//section[@id='shopbase-payment-method']//*[contains(text(),'Deactivate')]";
        clickOnElement(xPath);
        waitForEverythingComplete();
    }

    public void inputValueByPlaceHolder(String fieldLabel, String value, int index) {
        String xPath = "(//*[@placeholder='" + fieldLabel + "'])[" + index + "]";
        if (!value.isEmpty())
            waitClearAndType(xPath, value);
    }

    public void uploadFileByFieldLabel(String fieldLabel, String fileName) {
        String xPath = "//div[label[text()[normalize-space()='" + fieldLabel + "']]]/following-sibling::div//input";
        if (!fileName.isEmpty() && isElementExist(xPath, 5))
            chooseAttachmentFile(xPath, fileName);
    }

    public Response resetSpayResellerAccountByAPI(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/shopbase_payments/delete-connected-account.json";
        String referer = "https://" + LoadObject.getProperty("shop") + "/admin/settings/payments";
        return given().header("X-ShopBase-Access-Token", accessToken).header("Referer", referer).delete(url);
    }

    public String getConnectedAccountIDByAPI(String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/shopbase_payments/connected_account.json";
        JsonPath js = getAPISbase(url, accessToken);
        String connectedAccountID = (String) getData(js, "account.connect_id");
        System.out.println(connectedAccountID);
        return connectedAccountID;
    }

    public void openSpayKYCListOnHive() {
        String hive = LoadObject.getProperty("hive").replaceAll("/login", "");
        String url = hive + "/app/checkoutdbentity-spaykyc/list";
        openUrl(url);
        verifyElementVisible("//span[contains(text(),'Spay Kyc List')]", true);
    }

    public void openKYCFormOnHive(String connectedId) {
        String xPath = "//td[contains(text(),'" + connectedId + "')]/following-sibling::*//*[@title='Show']";
        clickOnElement(xPath);
    }

    public boolean isKYCFormOpen() {
        String shopDomain = LoadObject.getProperty("shop");
        return isLinkTextExist(shopDomain);
    }

    public void updateKYCFormHighLevelApproval() {
        if (isKYCFormOpen()) {
            clickLinkTextWithLabel("Actions");
            clickLinkTextWithLabel("Manage store");
            waitForPageLoad();
            checkCheckBoxInKYCForm(LoadObject.getProperty("shop"));
            checkCheckBoxInKYCForm("Identity Verification");
            checkCheckBoxInKYCForm("Store Verification");
            checkCheckBoxInKYCForm("Supplier Verification");
            checkCheckBoxInKYCForm("High Level Approval");
            clickBtnUpdateKYCOnHive();
            clickOnAcceptButtonOfTheAlert();
            waitForPageLoad();
        }
    }

    public void approveKYCSpayV2() {
        if (isKYCFormOpen()) {
            clickLinkTextWithLabel("Actions");
            clickLinkTextWithLabel("Edit");
            waitForPageLoad();
            checkCheckBoxInKYCForm("Identity Verification");
            checkCheckBoxInKYCForm("Store Verification");
            checkCheckBoxInKYCForm("Supplier Verification");
            checkCheckBoxInKYCForm("High Level Approval");
            clickBtnUpdateKYCOnHive();
            clickOnAcceptButtonOfTheAlert();
            waitForPageLoad();
        }
    }

    public void approveKYCFormOnHive() {
        String xPathApprove = "//*[text()[normalize-space()='Approve']]";
        clickLinkTextWithLabel("Actions");
        clickOnElement(xPathApprove);
        clickOnAcceptButtonOfTheAlert();
        waitForPageLoad();
    }

    public void rejectKYCFormOnHive() {
        String xPathReject = "//*[text()[normalize-space()='Reject']]";
        if (isKYCFormOpen()) {
            clickLinkTextWithLabel("Actions");
            clickLinkTextWithLabel("Manage store");
            waitForPageLoad();
            clickLinkTextWithLabel("Actions");
            clickOnElement(xPathReject);
            switchToRejectKYCPage();
            waitForPageLoad();
            // selectReasonToRejectKYCForm("Fraud");
            clickBtn("Ban");
        }
    }

    public void selectReasonToRejectKYCForm(String reason) {
        String xPathDdList = "//*[text()[normalize-space()='Select a reason']]//a";
        String xPathOption = "//*[@id='select_ban_reason']/option[text()[normalize-space()='" + reason + "']]";
        clickOnElement(xPathDdList);
        $(xPathOption).click();
    }

    public void switchToRejectKYCPage() {
        ArrayList<String> availableWindows = new ArrayList<String>(getDriver().getWindowHandles());
        if ((availableWindows.size()) > 1) {
            getDriver().switchTo().window(availableWindows.get(1));
        }
    }

    public void checkCheckBoxInKYCForm(String fieldName) {
        String xPath = "//*[@class='checkbox' and descendant::*[normalize-space()=\"" + fieldName + "\"]]//div";
        scrollToElement(xPath);
        boolean isStatus = XH(xPath).isSelected();
        if (!isStatus)
            $(xPath).click();
    }

    public void clickBtnUpdateKYCOnHive() {
        String xPath = "//button[@name='btn_update_and_edit']";
        scrollToElement(xPath);
        $(xPath).click();
    }

    public void actionWithSpayResellerAccountByAPI(String accessToken, String action) {
        String apiName = "";
        switch (action) {
            case "Reject": {
                apiName = "ban-connected-account";
                break;
            }
            case "Approve": {
                apiName = "";
                break;
            }
            case "Request more information": {
                apiName = " ";
                break;
            }
        }
        String url = "https://" + LoadObject.getProperty("gapi.url") + "/admin/qc-tools/" + apiName;
        String connectedID = getConnectedAccountIDByAPI(accessToken);
        String shopID = LoadObject.getProperty("shopId");
        String user = LoadObject.getProperty("user.name");
        JsonObject requestParams = requestBodySpayReseller(connectedID, shopID, user, action);
        postAPI(url, requestParams);
    }

    public JsonObject requestBodySpayReseller(String connectedID, String shopID, String user, String action) {
        JsonObject requestParam = new JsonObject();
        requestParam.addProperty("id", connectedID);
        requestParam.addProperty("shop_id", shopID);
        requestParam.addProperty("user", user);
        switch (action) {
            case "Reject": {
                requestParam.addProperty("reason", "rejected.fraud");
                requestParam.addProperty("reason_details", "fraud");
                break;
            }
            case "Approve":
            case "Request more information": {
//              add property
                break;
            }
        }

        return requestParam;
    }

    public void inputAdditionalDetails(String data) {
        String xpathIFrame = "//iframe[contains(@class,'tox-edit-area')]";
        String xpath = "//body[contains(@class,'mce-content-body')]";
        if (isElementExist(xpathIFrame, 60)) {
            switchToIFrame(xpathIFrame);
            clickAndClearThenType(xpath, data);
            switchToIFrameDefault();
        }
    }

    public void verifyLineOfErrorMessageWillBeShown(String message) {
        String xpath = "//small[contains(@class,'text-small')]";
        String msg = getText(xpath);
        assertThat(msg).isEqualTo(message);
    }

    public void clickBtnContinue() {
        String continueBtn = "//div[@class='message']//a[normalize-space()='Continue']";
        if (isElementExist(continueBtn, 7)) {
            clickOnElementByJs(continueBtn);
        }
    }

    public void enterTheField(String fieldName, String username) {
        String xpath = "//input[@name='" + fieldName + "' or @id = '" + fieldName + "']";
        waitUntilElementVisible(xpath, 20);
        WebElement inputTextbox = getDriver().findElement(By.xpath(xpath));
        inputTextbox.clear();
        inputTextbox.sendKeys(username);
    }

    public void clickBtnNextOnPayPalPage() {
        String xpath = "//button[normalize-space()='Next']";
        WebElement btnNext = getDriver().findElement(By.xpath(xpath));
        btnNext.click();
    }

    public void enterEmail(String username) {
        String xpath = "//*[normalize-space()='Email']//input";
        enterTheFieldInPayPal(xpath, username);
    }

    public void enterTheFieldInPayPal(String xpath, String val) {
        waitUntilElementVisible(xpath, 20);
        WebElement inputTextbox = getDriver().findElement(By.xpath(xpath));
        inputTextbox.clear();
        inputTextbox.sendKeys(val);
    }

    public void selectCounrty(String country) {
        String xpathDroplist = "//div[@class='Select-label Select-label-default']";
        String xpathSelectedCountry = "(//ul[contains(@id,'boardingCountry-menu')]//li[normalize-space()='" + country + "' and @aria-selected='false'])[1]";
        waitUntilElementVisible(xpathDroplist, 20);
        WebElement droplist = getDriver().findElement(By.xpath(xpathDroplist));
        WebElement selectedCountry = getDriver().findElement(By.xpath(xpathSelectedCountry));
        droplist.click();
        selectedCountry.click();
    }

    public void enterPassword(String password) {
        String xpath = "(//*[normalize-space()='Password']/following::input|//*[normalize-space()='Password']//input)[1]";
        enterTheFieldInPayPal(xpath, password);
    }

    public void clickActivateMethod() {
        String methodxpath = "//div[@class='euro-method']//button";
        int countMethod = commonPage.countElementByXpath(methodxpath);
        for (int i = 1; i <= countMethod; i++) {
            String xpath = "(" + methodxpath + ")[" + i + "]";
            clickOnElement(xpath);
            verifyElementVisible(xpath + "/preceding-sibling::div//span//span[normalize-space()='Active']", true);
        }
    }

    public void clickOnBtn(String _BtnName) {
        String _xPath = "//div[@class='euro-method']//div/following::button[normalize-space()='" + _BtnName + "']";
        clickOnElement(_xPath);
    }

    public void setToggleStatusEUPayment(String label, boolean status) {
        String xPath_checkbox = "//*[text()[normalize-space()='" + label + "']]/following::label/span";
        String xPath_status = "//*[text()[normalize-space()='" + label + "']]/following::label/input";
        verifyCheckedThenClick(xPath_status, xPath_checkbox, status);
    }

    public void inputValueForIDNumberForSpayV2(String idNumber) {
        if (isElementExist(xPathInputFieldWithLabel("", "Id number", 1)))
            enterInputFieldWithLabel("Id number", idNumber);
    }

    public void fillRequiredFieldStripeRequested() {
        HashMap<String, String> requestedFromStripe = new HashMap<>();
        requestedFromStripe.put("ID number", "R8650151");
        requestedFromStripe.put("Document back", "SpayResellerIDFront.jpg");

        String fieldName = "";
        String errorFieldNameArr = "(//div[@class='s-form-item__error' and normalize-space()='Missing required field']/preceding::label[@class='s-form-item__label'][1])[%d]";
        String errorFieldName = "//div[@class='s-form-item__error' and normalize-space()='Missing required field']/preceding::label[@class='s-form-item__label'][1]";

        int count = countElementByXpath(errorFieldName);
        for (int i = 1; i <= count; i++) {
            fieldName = getTextByJS(String.format(errorFieldNameArr, i)).trim();
            switch (fieldName) {
                case "ID number":
                    waitClearAndType(xPathInputFieldWithLabel("", fieldName, 1), requestedFromStripe.get("ID number"));
                    break;
                case "Document back":
                    uploadFileByFieldLabel("Document back", requestedFromStripe.get("Document back"));
                    break;
                case "Document front":
                    uploadFileByFieldLabel("Document front", requestedFromStripe.get("Document back"));
                    break;
            }
        }
    }

    public void checkCheckboxTermsAndConditions(String labelName) {
        String xPathStatus = "(//a[normalize-space()='" + labelName + "']/preceding::input[contains(@type,'checkbox')])[last()]";
        String xPathCheckbox = "(//a[normalize-space()='" + labelName + "']/preceding::span[@class='s-check'])[last()]";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, true);
    }

}