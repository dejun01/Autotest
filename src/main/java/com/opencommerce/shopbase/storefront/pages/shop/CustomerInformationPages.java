package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomerInformationPages extends SBasePageObject {
    public CustomerInformationPages(WebDriver driver) {
        super(driver);
    }

    public static String SHIPPING_ADDRESS = "//span[normalize-space()='Shipping address']";

    public void selectStateOrProvince(String sState) {
        String xpath = "//select[@name='provinces']//option[text()[normalize-space()='" + sState + "']]";
        clickOnElement(xpath);
    }

    public String getStateCode(String sState) {
        String xpath = "//select[@name='provinces']//option[text()[normalize-space()='" + sState + "']]";
        return $(xpath).getValue();
    }

    public void verifyShippingMethodPage() {
        verifyElementText("//div[contains(@class,'section--shipping-method')]//*[@class='section__title']", "Shipping method");
    }

    public String getContentShippingAddress(String label) {
        String xpath = "//div[@class='content-box']//div[text()[normalize-space()='" + label + "']]/following-sibling::div";
        return XH(xpath).getText();
    }

    public void verifyErrorMsg(String msg) {
        String xpath = "//*[@class='field-message field-message--error' and text()[normalize-space()='" + msg + "']]";
        verifyElementPresent(xpath, true);
    }

    public String getInputFieldValue(String field) {
        return XH(xPathInputFieldWithLabel("", field, 1)).getValue();
    }

    public String getDropdownFieldValue(String field, String value) {
        return XH("//*[child::*[normalize-space()='" + field + "']]//option[text()[normalize-space()='" + value + "']]").getText().trim();
    }


    public void clickBtnCheckoutPaypalExpressOrPayLater(String type) {
        String expressIframe = "(//div[@id='paypal-smart-button' or @id='paypal-smart-button0']//iframe[@allowpaymentrequest='allowpaymentrequest'])[%d]";
        String expressOrPayLater = "";
        if (type.equalsIgnoreCase("Paypal express")) {
            expressOrPayLater = "//div[@data-funding-source='paypal']";
            switchToIFrame(String.format(expressIframe, 1));
            $(expressOrPayLater).click();
        } else {
            expressOrPayLater = "//div[@aria-label='paypal paylater']";
            switchToIFrame(String.format(expressIframe, 2));
            $(expressOrPayLater).click();
        }
        waitForEverythingComplete(30);
        switchToIFrameDefault();
    }

    public void verifyCustomerInformationPageDisplayed() {
        verifyElementPresent("//h2[normalize-space()='Contact information']", true);
    }

    public void clickShopNameCheckoutPage(String shopname) {
        clickOnElement("//div[@class='main__header']//a[normalize-space()='" + shopname + "']");
    }

    public boolean isCountryDdDisplayed() {
        return isElementExist("//select[@name='countries']");
    }

    public void verifySupportedShippingCountry(String country, boolean isSupport) {
        clickOnElement("//select[@name='countries']");
        String xpath = "//select[@name='countries']//option[text()[normalize-space()='" + country + "']]";
        verifyElementPresent(xpath, isSupport);
    }

    public void clickBreadcrumbStepOnCheckout(String step) {
        String xpath = "//li[@class='breadcrumb']//a[text()[normalize-space()='" + step + "']]";
        String currentPageXpath = "//li[@class='breadcrumb']//span[@class='breadcrumb--current']//span[text()[normalize-space()='" + step + "']]";
        if (!isElementExist(currentPageXpath)) {
            $(xpath).click();
        }
        waitABit(1000);
    }


    public void checkSaveInformation() {
        clickOnElement("//label[@id='checkout_shipping_address_remember_me' or @id='save-information']//span[@class='s-check']");
    }

    public String getDiscount() {
        String xpath = "(//*[@class='total-line__price']//*[@class='order-summary__emphasis'])[2]";
        return XH(xpath).getText().replace("- $", "");
    }


    public boolean isInformationExist(String lableName) {
        return isElementExist("//input[@placeholder = '" + lableName + "']");
    }

    public boolean isCustomerInformationPageExist() {
        String xPath = "//*[text()[normalize-space()='Contact information']]";
        return isElementExist(xPath);
    }

    public boolean isBreadcrumbDisplay() {
        String xPath = "//ul[@class='breadcrumbs']";
        return isElementVisible(xPath, 5);
    }

    public void verifyScreenCheckout() {
        verifyElementPresent("//*[text()[normalize-space()='Contact information']]", true);
    }

    public float getPriceProduct(String sProduct) {
        String xpathPrice = "(//span[normalize-space()='" + sProduct + "']/following::*[contains(text(),'$')])[1]";
        String priceProduct = XH(xpathPrice).getText().replace("$", "");
        return Float.parseFloat(priceProduct);
    }

    public void verifyEmailAfterLogin(String sEmail) {
        verifyElementText("//p[@class='logged-in-customer-information__email']", sEmail);
    }

    public String getCustomerName() {
        return getAttributeValue("//input[@autocomplete='shipping family-name' and @name = 'last-name']", "value");
    }

    public void verifyEmail(String sEmail) {
        verifyElementText("//*[@class='logged-in-customer-information__email']|//input[@name='email-address']", sEmail);
    }

    public boolean isExistContinueShippingMethodBtn() {
        String xpath = "//button[@class='s-button step__continue-button' and text()[normalize-space()='Continue to shipping method']]";
        return isElementExist(xpath);
    }

    public void clickOnTitle() {
        clickOnElement("//input[@name='street-address']");
    }

    public String getPlaceHolderOfPhoneNumber() {
        return getAttributeValue("//input[@name='phone-number' or @id='checkout_shipping_address_phone']", "placeholder");
    }

    public void clickOnBtnContinueToShippingMethod() {
        String xPath = "//*[text()[normalize-space()='Continue to shipping method']]";
        try {
            clickOnElement(xPath);
        } catch (Exception e) {
            clickOnElementByJs(xPath);
        }
    }

    public boolean isPhoneNumberStillRequired() {
        String xPath = "//*[text()[normalize-space()='Please enter a phone number.']]";
        if (isElementVisible(xPath, 5)) {
            return true;
        }
        return false;
    }

    public String getSubtotal() {
        String xpath = "//td[text()[normalize-space()='Subtotal']]//following-sibling::td";
        String subTotal = "";
        if (isElementExist(xpath)) {
            subTotal = getTextValue(xpath);
        }
        return subTotal;
    }

    public void waitUntilLoadingCompletely() {
        waitForEverythingComplete();
        waitUntilElementVisible("//form[@id='customer-information']", 60);
    }

    public void enterEmail(String sEmail) {
        if (!isElementExist("//div[@class='logged-in-customer-information__info']")) {
            enterInputFieldWithLabel("Email", sEmail);
//                enterInputFieldWithLabel("email", sEmail); //Cmt lại do FS chưa bật
        }
    }

    public void waitUntilInputFieldVisible(String label) {
        waitUntilElementVisible(xPathInputFieldWithLabel("", label, 1), 30);
    }

    public void enterAddress(String sAddress) {
        String xPath = "//*[@name='street-address']";
        clickOnElement(xPath);
        waitClearAndTypeThenTab(xPath, sAddress);
    }

    public void enterInfoUser(String label, String val) {
        String xpath = "//input[contains(@placeholder,'" + label + "')]";
        waitElementToBeVisible(xpath, 30);
        waitClearAndType(xpath, val);
    }

    public void StateField(String state) {
        String xpath = "//label[@class='s-label' and following-sibling::select[@name='provinces']]";
        verifyElementText(xpath, state);
    }

    public void verifyCodeSuggest() {
        String xpath = "//div[@class='s-autocomplete control' and descendant::label[normalize-space()='Zip Code']]//a[@class='s-dropdown-item']";
        verifyElementPresent(xpath, true);
    }

    public void verifyMsg(String msg) {
        String xpath = "//*[@class='field-message field-message--error' and text()[normalize-space()='" + msg + "']]";
        if (!msg.isEmpty()) {
            verifyElementPresent(xpath, true);
        } else {
            verifyElementPresent(xpath, false);
        }
    }

    public void inputEmail(String sEmail) {
        String xpath = "//input[@id='checkout_shipping_address_email']";
        waitClearAndType(xpath, sEmail);
    }

    public void inputLastName(String sLastName) {
        String xpath = "//input[@id='checkout_shipping_address_last_name']";
        waitClearAndType(xpath, sLastName);
    }

    public void inputAddress(String sAddress) {
        String xpath = "//input[@id='checkout_shipping_address_address_line1']";
        waitClearAndType(xpath, sAddress);
    }

    public void inputCity(String sCity) {
        String xpath = "//input[@id='checkout_shipping_address_city']";
        waitClearAndType(xpath, sCity);
    }

    public void selectCountry(String sCountry) {
        String xpath = "//select[@autocomplete='shipping country']";
        Select select = new Select(getDriver().findElement(By.xpath(xpath)));
        select.selectByValue(sCountry);
    }

    public void selectState(String sState) {
        String xpath = "//select[@autocomplete='shipping address-level1']";
        Select select = new Select(getDriver().findElement(By.xpath(xpath)));
        select.selectByValue(sState);
    }

    public void inputZipCode(String sZipCode) {
        String xpath = "//input[@id='checkout_shipping_address_zip']";
        waitClearAndType(xpath, sZipCode);
    }

    public void inputPhoneNumber(String sPhone) {
        String xpath = "//input[@id='checkout_shipping_address_phone']";
        waitClearAndType(xpath, sPhone);
    }

    public void shippingMethod() {
        String xpath = "//button[@class='s-button step__continue-button']";
        if (countElementByXpath(xpath) == 1) {
            scrollIntoElementView(xpath);
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }

    public void paymentMethod() {
        String xpath = "//button[@class='s-button step__continue-button']";
        if (countElementByXpath(xpath) == 1) {
            scrollIntoElementView(xpath);
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }

    public void verifyCssOfCheckoutPage(String javaScript) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript(javaScript);
    }


    public String getLabelText(String field) {
        waitForEverythingComplete();
        return getTextContent("//label[contains(@for,'" + field + "')]");
    }

    public String getLabelTextOfCountryAndState(String field) {
        waitForEverythingComplete();
        return getTextContent("//select[contains(@name,'" + field + "')]/preceding-sibling::label");
    }
}

