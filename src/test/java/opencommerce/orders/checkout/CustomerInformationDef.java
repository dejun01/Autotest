package opencommerce.orders.checkout;

import com.opencommerce.shopbase.dashboard.settings.api.PayPalPaymentSettingsAPI;
import com.opencommerce.shopbase.storefront.steps.gateway.PaypalSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.opencommerce.shopbase.OrderVariable.*;
import static common.utilities.LoadObject.convertStatus;
import static org.junit.Assert.fail;


public class CustomerInformationDef {
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    ShippingMethodSteps shippingMethodSteps;
    @Steps
    PaypalSteps paypalSteps;
    @Steps
    PaymentsSettingSteps paymentsSettingSteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    PayPalPaymentSettingsAPI payPalPaymentSettingsAPI;
    @Steps
    ProductSteps productSteps;

    @Given("^input card information of Paypal Pro$")
    public void inputCardInforPapalPro(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {

            String cardNum = SessionData.getDataTbVal(dataTable, row, "Card number");
            String expiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String cvv = SessionData.getDataTbVal(dataTable, row, "CVV");

            customerInformationSteps.clickBtnContinueToPaymentMethod();
            paymentMethodSteps.enterCardNumberOfPaypalPro(cardNum);
            paymentMethodSteps.enterDateOfPaypalPro(expiredDate);
            paymentMethodSteps.enterCVVOfPaypalPro(cvv);
            paymentMethodSteps.chooseBillingAddress("Same as shipping address");

            paymentMethodSteps.clickCompleteOrder();
        }

    }
    //--------------------------------------


    @When("^checkout by Stripe successfully$")
    public void checkout_Stripe_sucsess() {
        customerInformationSteps.enterCustomerInformation(true);
        customerInformationSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.enterPaymentMethodByStripe();
        paymentMethodSteps.selectBillingAddress("Same as shipping address");
        paymentMethodSteps.clickBtnCompleteOrder();
        thankyouSteps.verifyThankYouPage();
    }


    @Given("^input customer and shipping information$")
    public void input_customer_and_shipping_information() {
        customerInformationSteps.enterCustomerInformation(true);
    }

    @Given("^input Customer information$")
    public void inputCustomerInformation(List<List<String>> dataTable) {
        System.out.println("--------------Get url: " + customerInformationSteps.getCurrentUrl());
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String abandonedEmail = SessionData.getDataTbVal(dataTable, row, "Abandoned Email");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String sCpfOrCnpj = SessionData.getDataTbVal(dataTable, row, "CPF/CNPJ number");
            String sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String savedForNextTime = SessionData.getDataTbVal(dataTable, row, "Saved");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");
            Boolean isSavedForNextTime = Boolean.parseBoolean(savedForNextTime);
            if (sEmail.matches("@(.*)@") && !sEmail.equals("@BLANK@")) {
                long currentTime = System.currentTimeMillis();
                sEmail = "email_" + currentTime + "@" + (sEmail.replaceAll("@", ""));
            }

            if (!abandonedEmail.isEmpty()) {
                if (shopDomain.contains("onshopbase.com")) {
                    sEmail = abandonedEmail + "_prod@mailtothis.com";
                } else {
                    sEmail = abandonedEmail + "_stag@mailtothis.com";
                }
            }
            shippingAddress = "";
            billingAddress = "";
            customerInformationSteps.enterEmail(sEmail);
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCpfOrCnpj(sCpfOrCnpj);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);

            //add shipping address and billing address into HashMap
            shippingAddressHashMap.put("Shipping Name", sFirstName + " " + sLastName);
            shippingAddressHashMap.put("Shipping Address1", sAddress);
            shippingAddressHashMap.put("Shipping Address2", sApartment);
            shippingAddressHashMap.put("Shipping City", sCity);
            shippingAddressHashMap.put("Shipping Zip", sZipCode);
            shippingAddressHashMap.put("Shipping Province", sState);
            shippingAddressHashMap.put("Shipping Country", sCountry);
            shippingAddressHashMap.put("Shipping Phone", sPhone);


            if (sCountry.equals("Brazil")) {
                shippingAddressHashMap.put("Shipping CPF/CNPJ Number", sCpfOrCnpj);
                shippingAddress = (sFirstName + " " + sLastName + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipCode + " " + sState + " " + sCountry + " " + sCpfOrCnpj + " " + sPhone.replaceAll("\\s", "")).replaceAll("\\s{2,}", " ");
            } else {
                shippingAddress = (sFirstName + " " + sLastName + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipCode + " " + sState + " " + sCountry + " " + sPhone.replaceAll("\\s", "")).replaceAll("\\s{2,}", " ");
            }

            System.out.println("inputtedShippingAddress: " + shippingAddress);

            if (!savedForNextTime.isEmpty()) {
                customerInformationSteps.checkSavedInforNexttime(isSavedForNextTime);
            }
            String stateCode = "";
            if (!sState.isEmpty()) {
                stateCode = customerInformationSteps.getStateCode(sState);
            }
            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }

            if (!sExpected.isEmpty())
                if (sExpected.equalsIgnoreCase("success")) {
                    customerInformationSteps.verifyShippingMethodPage();
                    customerInformationSteps.verifyContact(sEmail);
                    customerInformationSteps.verifyShippingAddress(sAddress, sApartment, sCity, stateCode, sZipCode, sCountry, sPhone);
                } else {
                    customerInformationSteps.verifyErrorMsg(sExpected);
                    customerInformationSteps.refreshPage();
                    customerInformationSteps.waitUntilLoadingCompletely();
                }
        }
        if (onePageCheckoutSteps.isOnePage()) {
            paymentMethodSteps.enterCardNumber("", "Stripe");
        }
    }

    @Given("^input Customer information as \"([^\"]*)\"$")
    public void inputCustomerInformationAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String savedForNextTime = SessionData.getDataTbVal(dataTable, row, "Saved");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");
            Boolean isSavedForNextTime = Boolean.parseBoolean(savedForNextTime);
            if (sEmail.matches("@(.*)@") && !sEmail.equals("@BLANK@")) {
                long currentTime = System.currentTimeMillis();
                sEmail = currentTime + "@" + (sEmail.replaceAll("@", ""));
            }

            shippingAddress = "";
            billingAddress = "";
            customerInformationSteps.enterEmail(sEmail);
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);

            shippingAddress = (sFirstName + " " + sLastName + " " + sAddress + " " + sApartment + " " + sCity + " " + sZipCode + " " + sState + " " + sCountry + " " + sPhone.replaceAll("\\s", "")).replaceAll("\\s{2,}", " ");
            System.out.println("inputtedShippingAddress: " + shippingAddress);

            if (!savedForNextTime.isEmpty()) {
                customerInformationSteps.checkSavedInforNexttime(isSavedForNextTime);
            }
            String stateCode = "";
            if (!sState.isEmpty()) {
                stateCode = customerInformationSteps.getStateCode(sState);
            }
            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }

            if (!sExpected.isEmpty())
                if (sExpected.equalsIgnoreCase("success")) {
                    customerInformationSteps.verifyShippingMethodPage();
                    customerInformationSteps.verifyContact(sEmail);
                    customerInformationSteps.verifyShippingAddress(sAddress, sApartment, sCity, stateCode, sZipCode, sCountry, sPhone);
                } else {
                    customerInformationSteps.verifyErrorMsg(sExpected);
                    customerInformationSteps.refreshPage();
                }
        }
    }

    @When("^verify customer information$")
    public void verifyCustomerInformation(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");

            customerInformationSteps.verifyEmail(sEmail);
            customerInformationSteps.verifyFirstName(sFirstName);
            customerInformationSteps.verifyLastName(sLastName);
            customerInformationSteps.verifyAddress(sAddress);
            customerInformationSteps.verifyApartment(sApartment);
            customerInformationSteps.verifyCity(sCity);
            customerInformationSteps.verifyCountry(sCountry, sState);
            customerInformationSteps.verifyZipCode(sZipCode);
            customerInformationSteps.verifyPhone(sPhone);

        }
    }

    @Given("^checkout by (Paypal express|Pay later) success with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void checkout_by_Paypal_express_success(String type, String email, String pass) {
        paidTotalAmtByPaypal = orderSummarySteps.getSubTotalPriceStr();
        System.out.println("-------------Get current URL" + customerInformationSteps.getCurrentUrl());
        String paypalType = "express";
        paypalBrandName = payPalPaymentSettingsAPI.getBrandName();
        paymentGateway = "Paypal";
        paymentMethod = "Paypal";
        customerInformationSteps.clickBtnCheckoutPaypalExpressOrPayLater(type);
        paypalSteps.confirmPayNowPaypal(email, pass, paypalType, paidTotalAmtByPaypal, paypalBrandName);
        customerInformationSteps.waitABit(5000);
        customerInformationSteps.inputTextToCustomerInformation("phone-number", "(703) 450 9242");
        if (!onePageCheckoutSteps.isOnePage()) {
            customerInformationSteps.clickBtnContinueToShippingMethod();
            customerInformationSteps.inputCustomerPhoneAgain();
            customerInformationSteps.verifyShippingMethodPage();
            shippingMethodSteps.clickContinueToPaymentMethod();
        }
        paymentMethodSteps.verifyPaypalSelected();
        if (!onePageCheckoutSteps.isOnePage()) {
            paymentMethodSteps.clickCompleteOrder();
        } else {
            onePageCheckoutSteps.clickPlaceYourOrder();
        }
    }

    @Given("^verify country \"([^\"]*)\" is \"([^\"]*)\" in Customer Information page$")
    public void verifyCheckoutNotSupportShippingCountry(String sCountry, String isSupportStr) {
        boolean isSupport = false;
        if (isSupportStr.equalsIgnoreCase("supported")) {
            isSupport = true;
        }
        if (sCountry.contains(",")) {
            String[] countries = sCountry.split(",");
            for (String country : countries) {
                customerInformationSteps.verifyCheckoutNotSupportShippingCountry(country, isSupport);
            }
        } else {
            customerInformationSteps.verifyCheckoutNotSupportShippingCountry(sCountry, isSupport);
        }
    }

    @Given("^navigate to screen \"([^\"]*)\" in checkout flow by breadcrumb$")
    public void navigateToCheckoutScreenByBreadcrumb(String stepName) {
        customerInformationSteps.clickStepOnBreadcrumb(stepName);
    }

    @Given("^Checkout successfully on shopbase$")
    public void checkout_successfully_all_in(List<List<String>> dataTable) {
        if (customerInformationSteps.isBreadcrumbDisplay()) {
            checkout_successfully(dataTable);
        } else {
            checkout_successfully_one_page(dataTable);
        }
    }

    public void checkout_successfully(List<List<String>> dataTable) {
        String email = "";
        String firstName = "";
        String lastName = "";
        String country = "";
        String address = "";
        String city = "";
        String zipCode = "";
        String state = "";
        String phoneNum = "";
        String cardNum = "";
        String cardholderName = "";
        String expiredDate = "";
        String cvv = "";
        String paymentMethod = "";

        String dataTableKey = "Checkout Information 1";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //customer information
            email = SessionData.getDataTbVal(dataTable, row, "Email");
            firstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            lastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            country = SessionData.getDataTbVal(dataTable, row, "Country");
            address = SessionData.getDataTbVal(dataTable, row, "Address");
            city = SessionData.getDataTbVal(dataTable, row, "City");
            zipCode = SessionData.getDataTbVal(dataTable, row, "ZIP Code");
            state = SessionData.getDataTbVal(dataTable, row, "State");
            phoneNum = SessionData.getDataTbVal(dataTable, row, "Phone number");
        }
        //input information to customer information form
        customerInformationSteps.enterEmail(email);
        customerInformationSteps.enterFirstName(firstName);
        customerInformationSteps.enterLastName(lastName);
        customerInformationSteps.selectCountry(country, state);
        customerInformationSteps.enterAddress(address);
        customerInformationSteps.enterZipCode(zipCode);
        customerInformationSteps.enterCity(city);
        customerInformationSteps.enterPhone(phoneNum, true);
        customerInformationSteps.checkSaveInformation(true);
        //go to next step
        customerInformationSteps.clickBtnContinueToShippingMethod();
        //go to next step
        customerInformationSteps.clickBtnContinueToPaymentMethod();

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //Card information
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment Method");
            cardNum = SessionData.getDataTbVal(dataTable, row, "Card number");
            cardholderName = SessionData.getDataTbVal(dataTable, row, "Cardholder Name");
            expiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            cvv = SessionData.getDataTbVal(dataTable, row, "CVV");
            //input card information
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            paymentMethodSteps.enterCardNumber(cardNum, "Stripe");
            paymentMethodSteps.enterCardholderName(cardholderName);
            paymentMethodSteps.enterDate(expiredDate, "Stripe");
            paymentMethodSteps.enterCVV(cvv, "Stripe");
            paymentMethodSteps.chooseBillingAddress("Same as shipping address");

            paymentMethodSteps.clickCompleteOrder();
        }

        thankyouSteps.verifyThankYouPage();
        orderNumber = thankyouSteps.getOrderNumber();
        totalAmt = orderSummarySteps.getTotalAmt();
        shippingFee = orderSummarySteps.getShippingFee();
        listProduct = orderSummarySteps.getProductList();

    }

    @Given("^Checkout one page successfully on shopbase$")
    public void checkout_successfully_one_page(List<List<String>> dataTable) {
        String email = "";
        String firstName = "";
        String lastName = "";
        String country = "";
        String address = "";
        String city = "";
        String zipCode = "";
        String state = "";
        String phoneNum = "";
        String cardNum = "";
        String cardholderName = "";
        String expiredDate = "";
        String cvv = "";
        String paymentMethod = "";

        String dataTableKey = "Checkout Information 1";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //customer information
            email = SessionData.getDataTbVal(dataTable, row, "Email");
            firstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            lastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            country = SessionData.getDataTbVal(dataTable, row, "Country");
            address = SessionData.getDataTbVal(dataTable, row, "Address");
            city = SessionData.getDataTbVal(dataTable, row, "City");
            zipCode = SessionData.getDataTbVal(dataTable, row, "ZIP Code");
            state = SessionData.getDataTbVal(dataTable, row, "State");
            phoneNum = SessionData.getDataTbVal(dataTable, row, "Phone number");
        }
        //input information to customer information form
        customerInformationSteps.enterEmail(email);
        customerInformationSteps.enterFirstName(firstName);
        customerInformationSteps.enterLastName(lastName);
        customerInformationSteps.selectCountry(country, state);
        customerInformationSteps.enterAddress(address);
        customerInformationSteps.enterZipCode(zipCode);
        customerInformationSteps.enterCity(city);
        customerInformationSteps.enterPhone(phoneNum, true);

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //Card information
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment Method");
            cardNum = SessionData.getDataTbVal(dataTable, row, "Card number");
            cardholderName = SessionData.getDataTbVal(dataTable, row, "Cardholder Name");
            expiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            cvv = SessionData.getDataTbVal(dataTable, row, "CVV");
            //input card information
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            paymentMethodSteps.enterCardNumber(cardNum, "Stripe");
            paymentMethodSteps.enterCardholderName(cardholderName);
            paymentMethodSteps.enterDate(expiredDate, "Stripe");
            paymentMethodSteps.enterCVV(cvv, "Stripe");

            paymentMethodSteps.clickPlaceYourOrder();
        }

        thankyouSteps.verifyThankYouPage();
        orderNumber = thankyouSteps.getOrderNumber();
        totalAmt = orderSummarySteps.getTotalAmt();
        shippingFee = orderSummarySteps.getShippingFee();
        listProduct = orderSummarySteps.getProductList();
        System.out.println("orderNumber: " + orderNumber);
        System.out.println("List Product : " + listProduct);
    }


    @Then("select country and verify state field")
    public void selectCountryAndVerifyStateField(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String errMsg = SessionData.getDataTbVal(dataTable, row, "Message");
            customerInformationSteps.chooseCountry(country);
            customerInformationSteps.verifyStateField(state);
            customerInformationSteps.clickBtnContinueToShippingMethod();
            customerInformationSteps.verifyErrorMsg(errMsg);
        }
    }

    @Then("verify zip code and state for matching")
    public void verifyZipCodeAndStateForMatching(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String zipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String state = SessionData.getDataTbVal(dataTable, row, "State");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            customerInformationSteps.chooseCountry(country);
            customerInformationSteps.enterZipCode(zipCode);
            customerInformationSteps.verifyCodeSuggest();
            customerInformationSteps.selectState(state);
            customerInformationSteps.verifyMsg(msg);
        }
    }

    @And("verify css of checkout page")
    public void verifyCssOfCheckoutPage() {
        customerInformationSteps.verifyCssOfCheckoutPage();
    }

    @Then("Verify special character of placeholder in Customer Information")
    public void verifyLabelInCustomerInformation() {
        customerInformationSteps.verifyLabelInCustomerInformation("email");
        customerInformationSteps.verifyLabelInCustomerInformation("first_name");
        customerInformationSteps.verifyLabelInCustomerInformation("last_name");
        customerInformationSteps.verifyLabelInCustomerInformation("address");
        customerInformationSteps.verifyLabelInCustomerInformation("address_line2");
        customerInformationSteps.verifyLabelDroplist("countries");
        customerInformationSteps.verifyLabelInCustomerInformation("city");
        customerInformationSteps.verifyLabelInCustomerInformation("zip");
        customerInformationSteps.verifyLabelInCustomerInformation("phone");
    }

    @And("verify warning message {string}")
    public void verifyWarningMessage(String msg) {
        customerInformationSteps.verifyWarningDisplayed(msg);
    }

    @And("enter the field in customer information form")
    public void enterTheFieldInCustomerInformationForm(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            customerInformationSteps.enterEmail(email);
        }
    }
}
