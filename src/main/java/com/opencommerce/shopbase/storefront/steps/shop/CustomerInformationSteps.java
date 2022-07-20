package com.opencommerce.shopbase.storefront.steps.shop;

import com.github.javafaker.Country;
import com.opencommerce.shopbase.storefront.pages.shop.ProductPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.storefront.pages.shop.CustomerInformationPages;

import static com.opencommerce.shopbase.OrderVariable.*;
import static com.opencommerce.shopbase.storefront.pages.shop.CustomerInformationPages.SHIPPING_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerInformationSteps extends ScenarioSteps {
    CustomerInformationPages customerInformationPages;

    public static String customerName;
    public static String customerName1;

    @Step
    public void selectCountry(String sCountry, String sState) {
        if (customerInformationPages.isCountryDdDisplayed()) {
            if (!sCountry.isEmpty()) {
                customerInformationPages.selectDdlWithLabel("Country", sCountry);
//                customerInformationPages.selectDdlWithLabel("Country*", sCountry); //Cmt lại do Fs chưa bật
                if (!sState.isEmpty())
                    customerInformationPages.selectStateOrProvince(sState);
            }
        }
    }


    @Step
    public String getStateCode(String sState) {
        return customerInformationPages.getStateCode(sState);
    }

    @Step
    public void clickBtnContinueToShippingMethod() {
        customerInformationPages.clickOnBtnContinueToShippingMethod();
        customerInformationPages.waitForEverythingComplete(60);
        customerInformationPages.waitUntilInvisibleLoading(5);
        subTotal = customerInformationPages.getSubtotal();
    }

    @Step
    public void verifyShippingMethodPage() {
        customerInformationPages.verifyShippingMethodPage();
    }

    @Step
    public void verifyContact(String sEmail) {
        if (!sEmail.isEmpty())
            assertThat(customerInformationPages.getContentShippingAddress("Contact")).isEqualTo(sEmail);

    }

    @Step
    public void verifyShippingAddress(String sAddress, String sApartment, String sCity, String stateCode, String sZipCode, String sCountry, String sPhone) {
        String expectAddess = sAddress + ", " + sApartment + ", " + sCity + ", " + stateCode + ", " + sZipCode + ", " + sCountry + ", " + sPhone.replaceAll("\\s", "");
        assertThat(customerInformationPages.getContentShippingAddress("Ship to")).isEqualTo(expectAddess.replaceAll(", ,", ","));
    }

    @Step
    public void verifyErrorMsg(String sExpected) {
        String[] errorMsgs = sExpected.split(",");
        for (String msg : errorMsgs) {
            customerInformationPages.verifyErrorMsg(msg);
        }
    }

    @Step
    public void checkSavedInforNexttime(Boolean ischeck) {
        customerInformationPages.checkCheckboxWithLabel("Save this information for next time", 1, ischeck);
    }

    @Step
    public void refreshPage() {
        customerInformationPages.refreshPage();
        customerInformationPages.waitForEverythingComplete();
    }


    @Step
    public void verifyCustomerInformationByLabel(String field, String value) {
        if (!value.isEmpty()) {
            switch (field) {
                case "Country":
                case "State":
                    assertThat(customerInformationPages.getDropdownFieldValue(field, value)).isEqualTo(value);
                    break;
                default:
                    assertThat(customerInformationPages.getInputFieldValue(field)).isEqualTo(value);
                    break;
            }
        }
    }

    @Step
    public void inputTextToCustomerInformation(String label, String value) {
        String xPathIFramePayPal = "//iframe[@class='paypal-checkout-sandbox-iframe']";
        customerInformationPages.waitForEverythingComplete();
        customerInformationPages.waitUntilElementInvisible(xPathIFramePayPal, 30);
        if (!value.isEmpty()) {
            customerInformationPages.waitUntilInputFieldVisible(label);
            customerInformationPages.enterInputFieldWithLabel(label, value);
        }
    }

    @Step
    public void inputCustomerPhoneAgain() {
        if (customerInformationPages.isCustomerInformationPageExist()) {
            customerInformationPages.enterInputFieldWithLabel("phone-number", "(+84) 987 724 095");
            clickBtnContinueToShippingMethod();
        }
    }

    @Step
    public void clickBtnCheckoutPaypalExpressOrPayLater(String type) {
        waitABit(5000);
        customerInformationPages.clickBtnCheckoutPaypalExpressOrPayLater(type);
    }

    @Step
    public void enterCustomerInformation(boolean isSaveforNextTime) {
        enterCustomerInformationWithData("Jone", "Doe", isSaveforNextTime + "");
    }

    @Step
    public void enterCustomerInformationWithData(String firstName, String lastName, String isSaveforNextTime) {

        enterEmail("tester@mailtothis.com");
        enterFirstName(firstName);
        enterLastName(lastName);
        selectCountry("United States", "California");
        enterAddress("814 Mission Street");
        enterApartment("814");
        enterZipCode("90001");
        enterCity("San Francisco");
        enterPhone("4047957606", true);
        if (!isSaveforNextTime.isEmpty()) {
            checkSaveInformation(Boolean.parseBoolean(isSaveforNextTime.toLowerCase()));
        }
        customerName1 = getCustomerName();
        clickBtnContinueToShippingMethod();
        customerInformationPages.waitForEverythingComplete();
    }

    @Step
    public void enterCustomerInformationOnSF(boolean isSaveforNextTime, String currentTime) {
        customerInformationPages.verifyScreenCheckout();
        enterEmail(currentTime + "@mailinator.girl-viet.com");
        enterFirstName("Tester");
        enterLastName(currentTime);
        enterAddress("814 Mission Street");
        enterApartment("814");
        selectCountry("United States", "California");
        enterZipCode("90001");
        enterCity("Los Angeles");
        enterPhone("4047957606", true);
        checkSaveInformation(isSaveforNextTime);
        customerName = getCustomerName();
        customerInformationPages.waitForEverythingComplete();
    }

    @Step
    public void enterCustomerInformationOnSF() {
        String currentTime = Long.toString(System.currentTimeMillis());
        enterEmail(currentTime + "@mailinator.girl-viet.com");
        enterFirstName("Tester");
        enterLastName(currentTime);
        enterAddress("1600 W Loop S");
        enterApartment("1600");
        selectCountry("United States", "Texas");
        enterZipCode("77027");
        enterCity("Houston");
        enterPhone("4047957606", true);
        customerInformationPages.waitForEverythingComplete();
        clickBtnContinueToShippingMethod();
    }

    public void verifyCheckoutNotSupportShippingCountry(String country, boolean isSupport) {
        customerInformationPages.verifySupportedShippingCountry(country, isSupport);
    }

    @Step
    public void clickStepOnBreadcrumb(String step) {
        customerInformationPages.clickBreadcrumbStepOnCheckout(step);
    }

    @Step
    public void enterEmail(String sEmail) {
        if (!sEmail.isEmpty()) {
            customerInformationPages.enterEmail(sEmail);
            waitABit(5000);
            customerEmail = sEmail;
        }
    }

    @Step
    public void enterFirstName(String sFirstName) {
        if (!sFirstName.isEmpty()) {
            customerInformationPages.clickOnElement(customerInformationPages.xPathInputFieldWithLabel("", "first-name", 1));
            customerInformationPages.enterInputFieldWithLabel("first-name", sFirstName);
        }
    }

    @Step
    public void enterLastName(String sLastName) {
        if (!sLastName.isEmpty())
            customerInformationPages.enterInputFieldWithLabel("last-name", sLastName);
    }

    @Step
    public void enterAddress(String sAddress) {
        if (!sAddress.isEmpty())
            customerInformationPages.enterAddress(sAddress);
    }

    @Step
    public void enterPhone(String sPhone, boolean hasPhone) {
        if (hasPhone && !sPhone.isEmpty()) {
            String placeholder = customerInformationPages.getPlaceHolderOfPhoneNumber();
            customerInformationPages.enterInputFieldWithLabel(placeholder, sPhone);
            customerInformationPages.clickOnTitle();
        }
    }

    @Step
    public void checkSaveInformation(boolean isCheck) {
        if (isCheck) {
            customerInformationPages.checkSaveInformation();
        }
    }

    @Step
    public void clickBtnContinueToPaymentMethod() {
        customerInformationPages.clickBtn("Continue to payment method");
        customerInformationPages.waitForPageLoad();
        customerInformationPages.waitForEverythingComplete();
    }

    @Step
    public boolean isBreadcrumbDisplay() {
        return customerInformationPages.isBreadcrumbDisplay();
    }

    @Step
    public void enterApartment(String sApartment) {
        if (!sApartment.isEmpty())
            customerInformationPages.enterInputFieldWithLabel("apartment-number", sApartment);
    }

    @Step
    public void enterCity(String sCity) {
        if (!sCity.isEmpty())
            customerInformationPages.enterInputFieldWithLabel("City", sCity);
//            customerInformationPages.enterInputFieldWithLabel("city", sCity); //Cmt lại do Fs chưa bật
    }

    @Step
    public void enterZipCode(String sZipCode) {
        if (!sZipCode.isEmpty()) {
            customerInformationPages.enterInputFieldWithLabel("Zip Code", sZipCode);
//            customerInformationPages.enterInputFieldWithLabel("zip-code", sZipCode); //Cmt lại do Fs chưa bật
        }
    }

    @Step
    public void verifyEmail(String sEmail) {
        customerInformationPages.verifyEmail(sEmail);
    }

    @Step
    public void verifyFirstName(String firstName) {
        waitABit(5000);
        if (!firstName.isEmpty())
            customerInformationPages.verifyTextInputField("First name (optional)", firstName);
    }

    @Step
    public void verifyLastName(String lastName) {
        if (!lastName.isEmpty())
            customerInformationPages.verifyTextInputField("Last name", lastName);
    }

    @Step
    public void verifyAddress(String address) {
        if (!address.isEmpty())
            customerInformationPages.verifyTextInputField("Address", address);
    }

    @Step
    public void verifyApartment(String appartment) {
        verifyCustomerInformationByLabel("apartment-number", appartment);

    }

    @Step
    public void verifyCity(String city) {
        if (!city.isEmpty())
            customerInformationPages.verifyTextInputField("City", city);
    }

    @Step
    public void verifyCountry(String country, String state) {
        if (!country.isEmpty()) {
            customerInformationPages.verifyOptionSelectedByLabel("Country", country);
        }
        if (!state.isEmpty()) {
            customerInformationPages.verifyOptionSelectedByLabel("State", state);
        }
    }

    @Step
    public void verifyZipCode(String sZipCode) {
        if (!sZipCode.isEmpty())
            customerInformationPages.verifyTextInputField("Zip Code", sZipCode);
    }

    @Step
    public String getCustomerName() {
        return customerInformationPages.getCustomerName();
    }

    public void verifyPhone(String sPhone) {
        verifyCustomerInformationByLabel("phone-number", sPhone);

    }

    public boolean isExistContinueShippingMethodBtn() {
        return customerInformationPages.isExistContinueShippingMethodBtn();
    }

    public String getCurrentUrl() {
        return customerInformationPages.getCurrentUrl();
    }

    @Step
    public void enterCpfOrCnpj(String cpfOrCnpj) {
        if (!cpfOrCnpj.isEmpty()) {
            customerInformationPages.enterInputFieldWithLabel("CPF/CNPJ number", cpfOrCnpj);
        }
    }

    public void verifyLogo(String expLogo, Boolean isCheck) {
    }

    public void waitUntilLoadingCompletely() {
        customerInformationPages.waitUntilLoadingCompletely();
    }

    public void enterInfoUser(String label, String val) {
        customerInformationPages.enterInfoUser(label, val);
    }

    public void chooseCountry(String country) {
        customerInformationPages.selectDdlWithLabel("Country", country);
    }

    public void verifyStateField(String state) {
        customerInformationPages.StateField(state);
    }

    public void verifyCodeSuggest() {
        customerInformationPages.verifyCodeSuggest();
    }

    public void selectState(String state) {
        customerInformationPages.selectStateOrProvince(state);
    }

    public void verifyMsg(String msg) {
        customerInformationPages.verifyMsg(msg);
    }

    public void orderCamp(String sEmail, String sLastName, String sAddress, String sCity, String sCountry, String sState, String sZipCode, String sPhone) throws Exception {
        customerInformationPages.inputEmail(sEmail);
        customerInformationPages.inputLastName(sLastName);
        customerInformationPages.inputAddress(sAddress);
        customerInformationPages.inputCity(sCity);
        customerInformationPages.selectCountry(sCountry);
        customerInformationPages.selectState(sState);
        customerInformationPages.inputZipCode(sZipCode);
        customerInformationPages.inputPhoneNumber(sPhone);
        customerInformationPages.shippingMethod();
        customerInformationPages.paymentMethod();
    }

    @Step
    public void verifyCssOfCheckoutPage() {
        assertThat(customerInformationPages.executeJavaScript("return window.getComputedStyle(document.querySelector('#checkout')).display")).isEqualTo("flex");
    }

    @Step
    public void verifyLabelInCustomerInformation(String field) {
        if (shippingAddressHashMap.get("Shipping Country").equalsIgnoreCase("Vietnam") && field.equalsIgnoreCase("zip")) {
            assertThat(customerInformationPages.getLabelText("zip")).doesNotContain("*");
        } else {
            assertThat(customerInformationPages.getLabelText(field)).contains("*");
        }
    }

    @Step
    public void verifyLabelDroplist(String countries) {
        assertThat(customerInformationPages.getLabelTextOfCountryAndState(countries).contains("*"));
    }

    public void verifyWarningDisplayed(String msg) {
        customerInformationPages.verifyTextPresent(msg, true);
    }
}

