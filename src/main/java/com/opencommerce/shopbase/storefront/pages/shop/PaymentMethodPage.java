package com.opencommerce.shopbase.storefront.pages.shop;

import com.opencommerce.shopbase.common.pages.CommonPage;
import io.restassured.path.json.JsonPath;
import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PaymentMethodPage extends SBasePageObject {
    CommonPage commonPage;
    public void scrollToCardForm() {
        scrollIntoElementView("//span[normalize-space()='Payment method']");
    }

    public PaymentMethodPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPaymentMethodDisplayed() {
        verifyElementPresent("//h2[normalize-space()='Payment method']", true);
    }


    public boolean verifyPaymentMethod(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("Stripe")) {
            paymentMethod = paymentMethod.replace("Stripe", "test-gateway");
        }
        String xpath = "//input/following::div[contains(@class,'" + paymentMethod + "')]";
        return isElementExist(xpath);
    }

    public void switchToIframeStripe(String label) {
        switch (label) {
            case "card-number":
                switchToIFrame("//div[@id='stripe-card-number']//iframe");
                break;
            case "expired-date":
                switchToIFrame("//div[@id='stripe-card-expiry']//iframe");
                break;
            case "cvv":
                switchToIFrame("//div[@id='stripe-card-cvc']//iframe");
                break;
            case "frame-form":
                if(isElementExist("//iframe[@id='stripe-frame-form-wrapper']")) {
                    switchToIFrame("//iframe[@id='stripe-frame-form-wrapper']");
                }
                break;
        }
    }

    public void switchToIframeBraintree(String label) {
        switch (label) {
            case "card-number":
                switchToIFrame("//iframe[@id='braintree-hosted-field-number']");
                break;
            case "expired-date":
                switchToIFrame("//iframe[@id='braintree-hosted-field-expirationDate']");
                break;
            case "cvv":
                switchToIFrame("//iframe[@id='braintree-hosted-field-cvv']");
                break;
        }
    }

    public void switchToIframeCheckoutCom(String label) {
        switch (label) {
            case "card-number":
                switchToIFrame("//iframe[@id='cardNumber']");
                break;
            case "expired-date":
                switchToIFrame("//iframe[@id='expiryDate']");
                break;
            case "cvv":
                switchToIFrame("//iframe[@id='cvv']");
                break;
        }
    }

    public void enterCardNumber(String cardNumber) {
        String xPath = "//input[@placeholder='Card number']";
        waitABit(200);
        $(xPath).click();
        $(xPath).clear();
        for (int i = 0; i < cardNumber.length(); i++) {
            $(xPath).sendKeys(String.valueOf(cardNumber.charAt(i)));
            waitABit(200);
        }
    }

    public void enterDate(String date) {
        if (date.contains("/")) {
            String dates[] = date.split("/");
            String xPath = "//*[@name='exp-date' or @name='expiration']";
            $(xPath).clear();
            $(xPath).sendKeys(dates[0]);
            $(xPath).sendKeys(dates[1]);
        }
    }

    public void enterCVV(String sCVV) {
        String xPath = "//*[@name='cvv' or @name='cvc']";
        $(xPath).clear();
        $(xPath).sendKeys(sCVV);
    }

    public void verifyPaypalSelected() {
        verifyElementSelected("//input[contains(@value,'paypal-express-paypal') or contains(@value,'paypal-standard') or contains(@value,'paypal-smart-button')]", true);
    }

    private JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public void callFunctionPostPurchaseActivate() {
        String function = "sbsdk.postPurchase.activate()";
        js.executeScript(function);
    }

    public void callFunctionPostPurchaseAddProduct(String variantId, String quantity, String price) {
        String function = "sbsdk.postPurchase.addProduct(" + variantId + ", " + quantity + ", " + price + ")";
        js.executeScript(function);
    }

    public void callFunctionPostPurchaseFinalize() {
        String function = "sbsdk.postPurchase.finalize()";
        js.executeScript(function);
    }


    public void clickBtnCheckoutPaypalSmart() {
        String xPath_iframe = "(//div[contains(@id,'paypal-smart-button')]//iframe[contains(@name,'paypal_buttons')])[1]";
        String xPath_paypalSmart = "//div[@data-funding-source='paypal']";
        if (isElementExist(xPath_iframe, 60)) {
            switchToIFrame(xPath_iframe);
            waitUntilElementVisible(xPath_paypalSmart, 10000);
            clickOnElement(xPath_paypalSmart);
            switchToIFrameDefault();
        }
    }

    public boolean isPopUpToPaypal() {
        ArrayList<String> availableWindows = new ArrayList<String>(getDriver().getWindowHandles());
        if ((availableWindows.size()) > 1) {
            getDriver().switchTo().window(availableWindows.get(1));
            getDriver().close();
            getDriver().switchTo().window(availableWindows.get(0));
            return true;
        } else {
            return false;
        }
    }

    public void clickOnCreditCartBtn() {
        String xPath_iframe = "(//div[contains(@id,'paypal-smart-button')]//iframe[contains(@name,'paypal_buttons')])[last()]";
        String xPath_creditCardBtn = "//span[text()[normalize-space()='Debit or Credit Card']]";
        if (isElementExist(xPath_iframe, 60)) {
            switchToIFrame(xPath_iframe);
            $(xPath_creditCardBtn).click();
            if (isPopUpToPaypal()) {
                switchToIFrame(xPath_iframe);
                waitForEverythingComplete();
                $(xPath_creditCardBtn).click();
            }
            switchToIFrameDefault();
        }
    }

    public void clickOnAcceptCookieBtn() {
        String xPath = "//*[@id='acceptAllButton']";
        if (isElementVisible(xPath, 7)) {
            $(xPath).click();
        }
    }

    public void inputCardNumberInPaypalSmartBtn(String cardNumber) {
        String xPath_cardNumber = "//input[@id='credit-card-number']";
        inputValuePaypalSmartBtn(xPath_cardNumber, cardNumber);

    }

    public void inputExpireDateInPaypalSmartBtn(String expireDate) {
        String xPath_expiredDate = "//input[@id='expiry-date']";
        inputValuePaypalSmartBtn(xPath_expiredDate, expireDate);
    }

    public void inputCVVInPaypalSmartBtn(String cvv) {
        String xPath_cvv = "//input[@id='credit-card-security']";
        inputValuePaypalSmartBtn(xPath_cvv, cvv);
    }

    private void inputValuePaypalSmartBtn(String xpath, String value) {
        String xPath_iframe_paypalSmart = "(//div[contains(@id,'paypal-smart-button')]//iframe[contains(@name,'paypal_buttons')])[last()]";
        String xPath_iframe_creditCard = "(//div[@id='card-fields-container']//iframe)[1]";
        if (isElementExist(xPath_iframe_paypalSmart, 60)) {
            switchToIFrame(xPath_iframe_paypalSmart);
            if (isElementExist(xPath_iframe_creditCard, 60)) {
                switchToIFrame(xPath_iframe_creditCard);
                clickThenTypeCharByChar(xpath, value);
                switchToIFrameDefault();
                switchToIFrameDefault();
            }
        }
    }

    public void clickOnPayNowBtnInPaypalSmartBtn() {
        String xPath_iframe_paypalSmart = "(//div[contains(@id,'paypal-smart-button')]//iframe[contains(@name,'paypal_buttons')])[last()]";
        String xPath_iframe_creditCard = "(//div[@id='card-fields-container']//iframe)[1]";
        String xPath_payNowBtn = "//button[@id='submit-button']";
        if (isElementExist(xPath_iframe_paypalSmart, 60)) {
            switchToIFrame(xPath_iframe_paypalSmart);
            if (isElementExist(xPath_iframe_creditCard, 60)) {
                switchToIFrame(xPath_iframe_creditCard);
                clickOnElement(xPath_payNowBtn);
                switchToIFrameDefault();
                switchToIFrameDefault();
            }
        }
        waitABit(5000); //Paypal is so slow
    }

    public void selectTypePaymentMethod(String method) {
        String xpathStatus = "";
        String xpathChecked = "";
        switch (method) {
            case "Credit Card":
            case "Card":
                xpathStatus = "//input[contains(@value,'credit-card') or contains(@value,'platform') or contains(@value,'stripe-credit-card') or contains(@value, 'test-gateway') or contains(@value, 'cardpay') or contains(@value,'paypal-pro')]";
                break;
            case "Paypal":
                xpathStatus = "//input[contains(@value,'paypal-express-paypal') or contains(@value,'paypal-standard') or contains(@value,'redrawed-paypal-standard') or contains(@value,'paypal-smart-button')]";
                break;
            case "Asia-Bill":
                xpathStatus = "//input[contains(@value,'asia-bill-asia-bill') or contains(@value,'integrated-hosted-credit-card')]";
                break;
            case "COD":
                xpathStatus = "//input[contains(@value,'cod')]";
                break;
            case "Bank Transfer":
                xpathStatus = "//input[contains(@value,'bank_transfer')]";
                break;
            case "Oceanpayment":
                xpathStatus = "//input[@name='payment-method' and contains(@value,'ocean-payment')]";
                break;
            case "Cardpay":
                xpathStatus = "//input[contains(@value,'cardpay')]";
                break;
            case "Checkout.com":
                xpathStatus = "//input[contains(@value,'checkout-com')]";
                break;
            case "Paypal-Pro":
            case "PayPal Pro":
                xpathStatus = "//input[contains(@value,'paypal-pro')]";
                break;
        }
        xpathChecked = xpathStatus + "//following-sibling::span[@class='s-check']";
        verifyCheckedThenClick(xpathStatus, xpathChecked, true);
    }

    public void verifyAMsgErrorForStripe(String mess) {
        List<String> msgs = asList(mess.split(","));
        String xpath = "//p[@class='notice__text' or @class='field-message field-message--error']";
        waitUntilElementVisible(xpath, 20);
        List<String> actualMsg = getListText(xpath);
        switchToIframeStripe("frame-form");
        actualMsg.addAll(getListText(xpath));
        System.out.println("actualMsg = " + actualMsg);
        assertThat(actualMsg).containsAll(msgs);
    }

    public void selectExpressCheckout() {
        switchToIFrame("(//div[@id='paypal-smart-button' or @id='paypal-smart-button0']//iframe[@allowpaymentrequest='allowpaymentrequest'])[1]");
        waitABit(1000);
        clickOnElement("//div[@data-funding-source='paypal']");
        switchToIFrameDefault();

    }

    public void verifyFasterPaySelected() {
        verifyTextPresent("After clicking “Complete order”, you will be redirected to FasterPay to complete your purchase securely.", true);
    }

    public void verifyNoPaymentMethods() {
        verifyTextPresent("There was a problem with our payments. Contact us to complete your order.", true);
        verifyElementPresent("//*[@class='step__footer']/button[contains(text(),'Complete order')]", false);
    }

    public String getQuantity() {
        return XH("//span[@class='checkout-product__quantity']").getText();
    }

    public void switchToFasterPay() {
        clickOnElement("//li[@class='card js-card']");
    }

    public void enterCVC(String cVC) {
        switchToIFrame("//div[contains(@class,'js-cvv-stored-hosted')]//iframe");
        $("//input[@name='card[cvv]']").sendKeys(cVC);
        switchToIFrameDefault();

    }

    public void clickBtnPay() {
        clickOnElement("//button[@class='form-action button button-primary js-button' and child::span/span[text()='Pay']]");
        waitForPageLoad();
    }

    public void clickBtnLoginFasterPay() {
        clickOnElement("//button[@type='submit' and child::*[text()='Log in']]");
    }

    public void selectPaypalMethod() {
        String xpathInput = "//input[@value='paypal-express']";
        String xpathClick = "//input[@value='paypal-express']/following-sibling::span[@class='s-check']";
        verifyCheckedThenClick(xpathInput, xpathClick, true);
    }


    public void switchToIFramePaypalPro() {
        String xPath = "(//div[@class='fieldset']//iframe)[1]";
        waitForEverythingComplete();
        if (isElementVisible(xPath, 20))
            switchToIFrame(xPath);
    }

    public void switchToiFrame3DsCheckoutCom() {
        String xPath = "//iframe[@name='cko-3ds2-iframe']";
        waitForEverythingComplete();
        waitUntilElementVisible(xPath, 20);
        if (isElementVisible(xPath, 20))
            switchToIFrame(xPath);
    }

    public void switchToiFrame3Ds(String paymentGateway) {
        String xPath_iframe_paypalPro_1 = "//iframe[@id='secure-iframe-id']";
        String xPath_iframe_paypalPro_2 = "//iframe[@id='Cardinal-CCA-IFrame']";

        String xPath_iframe_checkout_com = "//iframe[@name='cko-3ds2-iframe']";
        String xPath_iframe_stripe_1 = "(//iframe[contains(@name,'privateStripeFrame')])[1]";
        String xPath_iframe_stripe_2 = "//iframe[@id='challengeFrame']";
        String xPath_iframe_stripe_3 = "//iframe[@class='FullscreenFrame']";
//        if (paymentGateway.equalsIgnoreCase("Paypal-Pro")) {
//            if (isElementExist(xPath_iframe_1, 60)) {
//                switchToIFrame(xPath_iframe_1);
//            }
//        }
//        if (isElementExist(xPath_iframe_3, 60)) {
//            switchToIFrame(xPath_iframe_3);
//        }

        switch (paymentGateway) {
            case "Stripe":
            case "ShopBase Payments":
                System.out.println("Start wait 5s");
                waitABit(5000);
                System.out.println("Wait 5s done");
                if (isElementExist(xPath_iframe_stripe_1, 10)) {
                    switchToIFrame(xPath_iframe_stripe_1);
                    System.out.println("Switch to iframe 1 successfully");
                }
                if (isElementExist(xPath_iframe_stripe_2, 10)) {
                    switchToIFrame(xPath_iframe_stripe_2);
                    System.out.println("Switch to iframe 2 successfully");
                }
                if (isElementExist(xPath_iframe_stripe_3, 10)) {
                    switchToIFrame(xPath_iframe_stripe_3);
                    System.out.println("Switch to iframe 3 successfully");
                }
                break;
            case "Paypal-Pro":
                if (isElementExist(xPath_iframe_paypalPro_1, 5)) {
                    switchToIFrame(xPath_iframe_paypalPro_1);
                }
                if (isElementExist(xPath_iframe_paypalPro_2, 5)) {
                    switchToIFrame(xPath_iframe_paypalPro_2);
                }
                break;
            case "Braintree":
                if (isElementExist(xPath_iframe_paypalPro_2, 5)) {
                    switchToIFrame(xPath_iframe_paypalPro_2);
                }
                break;
            case "Checkout.com":
                waitForEverythingComplete();
                waitUntilElementVisible(xPath_iframe_checkout_com, 5);
                if (isElementExist(xPath_iframe_checkout_com, 5))
                    switchToIFrame(xPath_iframe_checkout_com);
                break;
        }
    }

    public void switchToiFrameDefault3Ds(String paymentGateway) {
        if (paymentGateway.equalsIgnoreCase("Paypal-Pro")) {
            switchToIFrameDefault();
        }
        switchToIFrameDefault();
    }

    public void inputPassword3Ds(String password) {
        String xPath = "//input[@id='password']";
        waitClearAndType(xPath, password);
    }

    public void inputPassword3DsBraintree(String password) {
        String xPath = "//input[contains(@placeholder,'Enter Code Here')]";
        waitClearAndType(xPath, password);
    }

    public void clickOnSubmit3DsBtn() {
        String xPath = "//*[@value='Submit' or @value='SUBMIT' or @id='txtButton']";
        try {
            clickOnElement(xPath);
            waitForElementNotAppear(xPath);
        } catch (Throwable t) {
            clickOnElementByJs(xPath);
            waitForElementNotAppear(xPath);
        }
//        clickOnElement(xPath);
    }

    public void verifyAmountIn3DsPopup(String expectedAmt, String paymentGateway) {
        String xPath = "";
        waitForEverythingComplete();
        if (paymentGateway.equalsIgnoreCase("Paypal-Pro")) {
            xPath = "//label[contains(text(),'Amount')]//ancestor::td[1]//following-sibling::td//label";
            if (isElementExist(xPath, 60)) {
                verifyElementText(xPath, expectedAmt);
            }
        } else {
            xPath = "//*[@class='challengeinfotext']";
            String actual_content = getTextValue(xPath);
            assertThat(actual_content).contains(expectedAmt);
        }

    }

    public void verifyErrorMessageForPaypalPro(String message) {
        List<String> expError = asList(message.split(";"));
        String xPath = "//*[contains(@class,'field-message field-message--error') or @class='notice__text']";
        switchToIFramePaypalPro();
        if (!isElementVisible(xPath, 10)) {
            switchToIFrameDefault();
        }
        List<String> actualError = getListText(xPath);
        assertThat(actualError).containsAll(expError);
    }

    public void verifyStayingAtWhichCheckoutStep(String checkoutStep) {
        String xPath = "//*[@class='section__title'][normalize-space()='" + checkoutStep + "']";
        verifyElementVisible(xPath, true);
    }

    public String getThirdPartyGateway() {
//        String xPath = "//input[@name='payment-method'][contains(@value,'credit-card')]";
        String xPath = "//div[child::span[text() = 'Use your Credit or Debit card']]//input";
        String attributeValue = "";
        if (isElementExist(xPath)) {
            attributeValue = getAttributeValue(xPath, "value");
            attributeValue = attributeValue.split("-", 2)[1];
        }
        System.out.println(attributeValue);
        return attributeValue;
    }

    public String getShippingMethodSelected() {
        return getText("(//div[text()[normalize-space()='Method']]/following-sibling::div//span)[1]").replace("-", "").trim();
    }

    public void confirmStripe3DSecureSuccessfully() {
        String xPath = "//button[@id ='test-source-authorize-3ds']";
        waitUntilElementVisible(xPath, 8);
        waitForEverythingComplete();
        try {
            clickOnElement(xPath);
            waitForElementNotAppear(xPath);
        } catch (Throwable t) {
            clickOnElementByJs(xPath);
            waitForElementNotAppear(xPath);
        }
    }

    public void confirmStripe3DSecureUnsuccessfully() {
        String xPath = "//button[@id ='test-source-fail-3ds']";
        waitUntilElementVisible(xPath, 8);
        waitForEverythingComplete();
        try {
            clickOnElement(xPath);
            waitForElementNotAppear(xPath);
        } catch (Throwable t) {
            clickOnElementByJs(xPath);
            waitForElementNotAppear(xPath);
        }
    }

    public void waitUntilThankYouPagePresent() {
        waitUntilElementVisible("//div[@class='os-header__heading']//*[text()[normalize-space()='Thank you!']]", 7);
    }

    public boolean checkShopBasePayPalSmartButton() {
        String xPath = "//*[@class='smart-button-container-iframe']";
        return !isElementExist(xPath);
    }

    public void verifyGatewayPaymentDisplayOnSF(String gateway, String status) {
        Boolean isDisplay = true;
        if (!status.equalsIgnoreCase("activate")) {
            isDisplay = false;
        }
        if (isElementExist("//div[@class='section']//span[normalize-space()='Payment method']")) {
//            verifyElementPresent("//input[@name='payment-method' and contains(@value,'" + gateway + "')]", isDisplay);
            verifyElementPresent("//img[@alt='Oceanpayment']", isDisplay);
        }
    }

    public void inputCode(String code) {
        String xpath = "//input[@aria-label='IBAN']";
        waitClearAndType(xpath, code);
    }

    public void inputCardNumber(String sCartNumber) {
        iframe("//iframe[@title='Secure card number input frame']");
        String cardNumber = "//input[@placeholder='Card number']";
        scrollIntoElementView(cardNumber);
        waitClearAndType(cardNumber, sCartNumber);
        outIframe();
    }

    private void iframe(String locator) {
        waitUntilElementVisible(locator, 30);
        WebElement we = getDriver().findElement(By.xpath(locator));
        getDriver().switchTo().frame(we);
    }

    private void outIframe() {
        getDriver().switchTo().defaultContent();
    }

    public void inputCardholderName(String sCartName) {
        String xpath = "//input[@placeholder='Cardholder name']";
        waitClearAndType(xpath, sCartName);
    }

    public void inputDate(String sDate) {
        iframe("//iframe[@title='Secure expiration date input frame']");
        String xpath = "//input[@placeholder='MM/YY']";
        waitClearAndType(xpath, sDate);
        outIframe();
    }

    public void inputCVV(String sCVV) {
        iframe("//iframe[@title='Secure CVC input frame']");
        String xpath = "//input[@placeholder='CVV']";
        waitClearAndType(xpath, sCVV);
        outIframe();
    }

    public void clickOnPlaceYourOrderBtn() {
        String xpath = "//span[@class='step__complete-order--label']";
        if (countElementByXpath(xpath) == 1) {
            scrollIntoElementView(xpath);
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }

    public void clickOnCompleteOrder() {
        String xpath = "//button[@class='s-button step__continue-button']";
        if (countElementByXpath(xpath) == 1) {
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }


    public void verifyEUPaymentMethod(String method, int index) {
        String xpathMethod = "(//strong[@class='payment-method-label']//div)[" + index + "]";
        assertThat(getText(xpathMethod)).isEqualTo(method);
    }

    public boolean isBankDisplayed() {
        //return isElementExist("//span[normalize-space()='Chọn ngân hàng']/parent::div[@role='combobox']");
        return isElementExist("//div[@id='ideal-bank-element']");
    }

    public void choosePaymentMethod(String value) {
        String xpath = "//input[contains(@value,'" + value + "')]/following-sibling::span[@class='s-check']";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void clickAuthorized() {
        String xpath = "//*[normalize-space()='Authorize Test Payment']";
        waitForPageLoad();
        clickOnElement(xpath);
    }

    public void selectBank(String sBank) {
        String _iframedropdown = "//iframe[contains(@title,'Secure iDEAL bank selection dropdown button')]";
        String _xpath = "//*[text()[normalize-space()='Select bank']]/parent::div[@role='combobox']";
        String _iframe = "//iframe[contains(@title,'Secure iDEAL bank selection dropdown list')]";
        String _bank = "//div[normalize-space()='" + sBank + "']//parent::li";
        String _selectlist = "//ul[@class='SelectList SelectList--ltr']";
        if (isBankDisplayed()) {
            switchToIFrame(_iframedropdown);
            clickOnElement(_xpath);
            switchToIFrameDefault();
            switchToIFrame(_iframe);
            waitElementToBePresent(_selectlist);
            clickOnElement(_bank);
            switchToIFrameDefault();
        }
    }

    public void enterIBANNumber(String number) {
        String _iframe = "//iframe[@title='Secure IBAN input frame']";
        switchToIFrame(_iframe);
        enterInputFieldThenEnter("NL00 AAAA 0000 0000 00", number, 1);
        waitForEverythingComplete();
        switchToIFrameDefault();
    }

    public float getValuePayemnt(String label) {
        return getPrice("//*[child::*[text()[normalize-space()='" + label + "']]]//span");
    }

    public void switchToIframeParent() {
        String xpath = "//iframe[@id='stripe-frame-form-wrapper']";
        if (isElementExist(xpath))
            switchToIFrame(xpath);

    }

}
