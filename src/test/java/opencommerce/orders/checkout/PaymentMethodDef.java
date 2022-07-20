package opencommerce.orders.checkout;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.payments.PaymentsSteps;
import com.opencommerce.shopbase.dashboard.settings.api.PayPalPaymentSettingsAPI;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell.PostPurchaseOfferSteps;
import com.opencommerce.shopbase.storefront.steps.gateway.AsiaBillSteps;
import com.opencommerce.shopbase.storefront.steps.gateway.OceanPaymentSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import com.opencommerce.shopbase.storefront.steps.gateway.PaypalSteps;
import com.opencommerce.shopbase.storefront.steps.gateway.CardPaySteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.OrderVariable;
import org.junit.Assert;


import static common.utilities.LoadObject.convertStatus;
import static com.opencommerce.shopbase.OrderVariable.*;
import static opencommerce.products.dashboard.ProductDetailDef.nameProductSbase;
import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.*;
import java.util.List;

public class PaymentMethodDef {
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    ThankyouSteps thankYouSteps;
    @Steps
    ShippingMethodSteps shippingMethodSteps;
    @Steps
    PaypalSteps paypalSteps;
    @Steps
    CardPaySteps cardPaySteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    PayPalPaymentSettingsAPI payPalPaymentSettingsAPI;
    @Steps
    AsiaBillSteps asiaBillSteps;
    @Steps
    OceanPaymentSteps oceanPaymentSteps;
    @Steps
    PostPurchaseOfferSteps postPurchaseOfferSteps;


    @When("search and select the {string} products")
    public void searchAndSelectTheProducts(String name) {
        productSteps.searchAndSelectProduct(name);
    }

    @When("^verify payment method information with \"([^\"]*)\"$")
    public void verify_payment_method_information(String paymentGateway, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {

            String sCartNumber = SessionData.getDataTbVal(dataTable, row, "Card Number");
            String sCardholderName = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sDate = SessionData.getDataTbVal(dataTable, row, "MM/YY");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            String sExpected = SessionData.getDataTbVal(dataTable, row, "Expected");

            paymentMethodSteps.enterPaymentInfo(sCartNumber, sCardholderName, sDate, sCVV, paymentGateway);
            if (!onePageCheckoutSteps.isOnePage()) {
                paymentMethodSteps.selectBillingAddress("Same as shipping address");
                paymentMethodSteps.clickBtnCompleteOrder();
            } else {
                onePageCheckoutSteps.clickPlaceYourOrder();
            }


            if (sExpected.equalsIgnoreCase("success") | sExpected.isEmpty()) {
                thankYouSteps.verifyThankYouPage();
                OrderVariable.orderNumber = thankYouSteps.getOrderNumber();
            } else {
                paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, sExpected);
                customerInformationSteps.refreshPage();
            }

        }
    }

    @Given("^express checkout successfully on shopbase$")
    public void express_checkout_successfully_on_shopbase() {
        paymentMethodSteps.selectExpressCheckout();
        paypalSteps.loginPaypal("buyer@shopbase.com", "123456@a");
        customerInformationSteps.enterPhone("(+84) 987 724 095", true);
        customerInformationSteps.clickBtnContinueToShippingMethod();
        customerInformationSteps.verifyShippingMethodPage();
        customerInformationSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.verifyPaypalSelected();
        paymentMethodSteps.clickBtnCompleteOrder();
    }

    @Given("^verify payment method when switch payment method from PayPal to Stripe$")
    public void verify_payment_method_when_switch_payment_method_from_PayPal_to_Stripe() {
        paidTotalAmtByPaypal = orderSummarySteps.getSubTotalPriceStr();
        System.out.println("-------------Get current URL" + customerInformationSteps.getCurrentUrl());
        paypalBrandName = payPalPaymentSettingsAPI.getBrandName();
        paymentMethodSteps.selectExpressCheckout();
        paypalSteps.confirmPayNowPaypal("buyer@shopbase.com", "123456@a", "express", paidTotalAmtByPaypal, paypalBrandName);
        paymentMethodSteps.waitABit(5000);
        customerInformationSteps.enterPhone("8663588747", true);
        customerInformationSteps.clickBtnContinueToShippingMethod();
//        customerInformationSteps.verifyShippingMethodPage();
        customerInformationSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.verifyPaypalSelected();
        paymentMethodSteps.enterPaymentMethodByStripe();
        paymentMethodSteps.clickBtnCompleteOrder();
        thankYouSteps.verifyThankYouPage();
    }

    @Given("^checkout by FasterPay successfully on shopbase$")
    public void checkout_by_FasterPay_successfully_on_shopbase() {
        customerInformationSteps.enterCustomerInformation(false);
        shippingMethodSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.clickFasterPayCheckbox();
        paymentMethodSteps.clickBtnCompleteOrder();
        paymentMethodSteps.waitABit(3000);
        paymentMethodSteps.loginFasterPay("thuthu@gmail.com", "Thu2921996");
        paymentMethodSteps.waitABit(2000);
        thankYouSteps.verifyThankYouPage();
    }

    @Given("^verify checkout when product not enough \"([^\"]*)\"$")
    public void verify_checkout_when_product_not_enough(String quantity) {
        paymentMethodSteps.verifyOutOfStockMsg();
        paymentMethodSteps.verifyQuantity(quantity);
        paymentMethodSteps.clickBtnContinue();
    }

    @Given("^verify checkout when no methods have been setting$")
    public void verify_checkout_when_no_methods_have_been_setting() {
        customerInformationSteps.enterCustomerInformation(false);
        customerInformationSteps.clickBtnContinueToShippingMethod();
        shippingMethodSteps.clickBtnContinueToPaymentMethod();
        paymentMethodSteps.verifyNoPaymentMethods();

    }

    @When("^input card information of Stripe and complete order$")
    public void enter_payment_method_by_stripe(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String spayMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment Gateway");

            paymentMethodSteps.selectTypePaymentMethod(spayMethod);
            paymentMethodSteps.enterCardNumber(sCardNumber, "Stripe");
            paymentMethodSteps.enterCardholderName(sCardholder);
            paymentMethodSteps.enterDate(sExpiredDate, "Stripe");
            paymentMethodSteps.enterCVV(sCVV, "Stripe");

            endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);

            if (cardType.isEmpty()) {
                cardType = "Visa";
            }
            if (paymentGateway.isEmpty()) {
                paymentGateway = "Stripe";
            }
            if (billingAddress.isEmpty()) {
                billingAddress = "Same as shipping address";
            }
            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
            }
            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            if (isOnePageCheck) {
                onePageCheckoutSteps.clickPlaceYourOrder();
            } else {
                paymentMethodSteps.clickBtnCompleteOrder();
            }
        }
    }


    @When("^input payment information and complete order$")
    public void inputPaymentInfo(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            isPrintbaseshop =  convertStatus(SessionData.getDataTbVal(dataTable, row, "Is Printbase shop"));
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            //input Expired Date = mm/yy if gateway = AsiaBill
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            /*Card type:
             * If there is Paypal smart button: express/credit
             * If there is Paypal standard: express/standard
             * If there is the rest of gateway excluding Paypal: Card type = Visa or Master Card or etc ...
             * */
            String paypalAcccount = SessionData.getDataTbVal(dataTable, row, "Paypal account");
            String password = SessionData.getDataTbVal(dataTable, row, "Password");

            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            /* Payment gateway: Stripe, ShopBase Payments,Paypal Express, Paypal-Pro,Braintree, Checkout.com,Unlimint */
            String paypalSmartBtn = SessionData.getDataTbVal(dataTable, row, "Paypal smart button");
            /* Paypal Smart button: Yes/No or empty */
            is3Ds = convertStatus(SessionData.getDataTbVal(dataTable, row, "3DS"));
            //3Ds: yes/no or empty
            String pwd3Ds = SessionData.getDataTbVal(dataTable, row, "3DS password");
            String status3Ds = SessionData.getDataTbVal(dataTable, row, "3Ds status");
            /* 3Ds status:
            - For Paypal Pro : value = (fixed value = cancel/try again) and (custom error code)
                    - For Braintree: (custom error code)
            - For Unlimint: success/failure (3ds only) and decline (decline case for both 3ds and non-3ds)
            */
            errorMessage = SessionData.getDataTbVal(dataTable, row, "Error message");
            //3Ds Error Message: Error message content
            boolean isCancel = convertStatus(SessionData.getDataTbVal(dataTable, row, "Cancel"));
            //isCancel: for paypal and Unlimint. click on cancel from their own payment page to back to shopbase SF
            boolean isPaypalSmartBtn = false;
            if (!paypalSmartBtn.isEmpty()) {
                isPaypalSmartBtn = convertStatus(paypalSmartBtn);
            }
            //Set key value for defining AC fail reason message
            key = SessionData.getDataTbVal(dataTable, row, "Key");
            //define shopbase paypal smart button
            isShopBasePayPalSmartButton = paymentMethodSteps.checkShopBasePayPalSmartButton();
//            if (isShopBasePayPalSmartButton && "Paypal".equalsIgnoreCase(paymentMethod)) {
//                cardType = "standard";
//            }
            //Get PayPal brand name
            if ("Paypal".equalsIgnoreCase(paymentGateway)) {
                paypalBrandName = payPalPaymentSettingsAPI.getBrandName();
            }
            //Payment Rotation
            boolean isRotation = convertStatus(SessionData.getDataTbVal(dataTable, row, "Rotation"));
            if (isRotation) {
                //Get current gateway
                String currentGateway = paymentMethodSteps.getThirdPartyGateway();
                if ("Credit Card".equalsIgnoreCase(paymentMethod)) {
                    switch (currentGateway) {
                        case "stripe-credit-card":
                            paymentGateway = "Stripe";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Stripe card number");
                            break;
                        case "platform":
                            paymentGateway = "ShopBase Payments";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Stripe card number");
                            break;
                        case "paypal-pro":
                            paymentGateway = "Paypal-Pro";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "PayPal Pro card number");
                            break;
                        case "braintree":
                            paymentGateway = "Braintree";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Braintree card number");
                            is3Ds = true;
                            pwd3Ds = "1234";
                            break;
                        case "checkout-com":
                            paymentGateway = "Checkout.com";
                            break;
                        case "cardpay":
                            paymentGateway = "Unlimint";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Cardpay card number");
                            break;
                    }
                }
            }

            //get list product + total amount before complete order
            totalAmtBefore = orderSummarySteps.getTotalAmt();
            listProductBefore = orderSummarySteps.getProductList();
            listProductBefore.add(totalAmtBefore);
            paidTotalAmtByPaypal = orderSummarySteps.getTotalAmt();
            System.out.println("Paid by paypal: " + paidTotalAmtByPaypal);
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            if (cardType.isEmpty()) {
                cardType = "Visa";
            }
            if (!sCardNumber.isEmpty()) {
                endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);
                switch (endingCardNo) {
                    case "0259":
                    case "2685":
                        disputeType = "chargeback";
                        break;
                    case "1976":
                        disputeType = "inquiry";
                        break;
                }
            }
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
            }

            //set default value of Billing address = Same as shipping address
            if (billingAddress.isEmpty()) {
                billingAddress = "Same as shipping address";
            }
            if (billingAddressHashMap.isEmpty() && !shippingAddressHashMap.isEmpty()) {
                billingAddressHashMap.put("Billing Name", shippingAddressHashMap.get("Shipping Name").trim());
                billingAddressHashMap.put("Billing Address1", shippingAddressHashMap.get("Shipping Address1").trim());
                billingAddressHashMap.put("Billing Address2", shippingAddressHashMap.get("Shipping Address2").trim());
                billingAddressHashMap.put("Billing City", shippingAddressHashMap.get("Shipping City").trim());
                billingAddressHashMap.put("Billing Zip", shippingAddressHashMap.get("Shipping Zip").trim());
                billingAddressHashMap.put("Billing Province", shippingAddressHashMap.get("Shipping Province").trim());
                billingAddressHashMap.put("Billing Country", shippingAddressHashMap.get("Shipping Country").trim());
                billingAddressHashMap.put("Billing Phone", shippingAddressHashMap.get("Shipping Phone").trim());
            }

            if (!paymentGateway.equalsIgnoreCase("Paypal")
                    && !paymentGateway.equalsIgnoreCase("Unlimint")
                    && !paymentGateway.equalsIgnoreCase("Asia-Bill")) {
                //Payment gateway: Stripe - Spay - Braintree - Checkout.com - Paypal Pro
                paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
                if (isOnePageCheck) {
                    onePageCheckoutSteps.clickPlaceYourOrder();
                } else {
                    paymentMethodSteps.clickBtnCompleteOrder();
                }
                if (is3Ds) {
                    paymentMethodSteps.verifyPaymentInfoIn3DsPopup(totalAmtBefore, paymentGateway);
                    if ("Stripe".equalsIgnoreCase(paymentGateway) || "ShopBase Payments".equalsIgnoreCase(paymentGateway)) {
                        if (!"fail".equalsIgnoreCase(status3Ds)) {
                            paymentMethodSteps.submit3DsPassword(pwd3Ds, paymentGateway);
                        } else {
                            //for stripe only
                            paymentMethodSteps.submit3DsFail(paymentGateway);
                            paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                            System.out.println(errorMessage);
                        }
                    } else {
                        paymentMethodSteps.submit3DsPassword(pwd3Ds, paymentGateway);
                        if ("cancel".equalsIgnoreCase(status3Ds)) {
                            if (!isOnePageCheck) {
                                paymentMethodSteps.verifyStayingAtWhichCheckoutStep("Payment method");
                            } else {
                                onePageCheckoutSteps.verifyBtnPlaceYourOrder();
                            }
                        } else if ("try again".equalsIgnoreCase(status3Ds)) {
                            paymentMethodSteps.submit3DsPassword(pwd3Ds, paymentGateway);
                        } else if (!status3Ds.isEmpty()) {
                            paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                        }
                    }
                }
            }

            if (paymentGateway.equalsIgnoreCase("Paypal")) {
                //Payment gateway: Paypal
                if (!isPaypalSmartBtn) {
                    //Payment gateway: Paypal standard
                    if (!isCancel) {
                        if (isOnePageCheck) {
                            onePageCheckoutSteps.clickPlaceYourOrder();
                        } else {
                            paymentMethodSteps.clickBtnCompleteOrder();
                        }
                        paypalSteps.confirmPayNowPaypal(paypalAcccount, password, cardType, paidTotalAmtByPaypal, paypalBrandName);
                    }
                } else {
                    //Payment gateway: Paypal express + Paypal smart button
                    if (cardType.equalsIgnoreCase("express")) {
                        paymentMethodSteps.clickBtnCheckoutPaypalSmart();
                        paypalSteps.confirmPayNowPaypal(paypalAcccount, password, cardType, paidTotalAmtByPaypal, paypalBrandName);
                        customerInformationSteps.waitABit(5000);
                    } else if (cardType.equalsIgnoreCase("credit")) {
                        paymentMethodSteps.clickOnCreditCartBtn();

                        if (isShopBasePayPalSmartButton) {
                            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
                        } else {
                            paypalSteps.confirmPayNowPaypal(paypalAcccount, password, cardType, paidTotalAmtByPaypal, paypalBrandName);
                        }
                        paymentMethodSteps.clickOnPayNowBtnInPaypalSmartBtn();
                    }
                }
            }

            if (paymentGateway.equalsIgnoreCase("Unlimint")) {
                //Payment gateway: Unlimint
                if (isOnePageCheck) {
                    onePageCheckoutSteps.clickPlaceYourOrder();
                } else {
                    paymentMethodSteps.clickBtnCompleteOrder();
                }
                if (!isCancel) {
                    cardPaySteps.verifyTotalAmount(totalAmtBefore);
                    cardPaySteps.inputCardInfo(sCardNumber, sCardholder, sExpiredDate, sCVV);
                    cardPaySteps.clickOnPayBtn();
                    if (is3Ds) {
                        switch (status3Ds) {
                            case "success":
                                cardPaySteps.submit3DS(status3Ds);
                                break;
                            case "failure":
                                cardPaySteps.submit3DS(status3Ds);
                                paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                                break;
                            case "decline":
                                cardPaySteps.submit3DS("success");
                                paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                                break;
                        }
                    } else {
                        if ("decline".equalsIgnoreCase(status3Ds)) {
                            paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                        }
                    }
                } else {
                    cardPaySteps.clickOnCancelBtn();
                    paymentMethodSteps.verifyStayingAtWhichCheckoutStep("Payment method");
                    verifyCheckoutDetailBeforeAndAfterCancelPayment();
                }
            }

            if (paymentGateway.equalsIgnoreCase("Asia-Bill")) {
                //Payment gateway: AsiaBill
                if (isOnePageCheck) {
                    onePageCheckoutSteps.clickPlaceYourOrder();
                } else {
                    paymentMethodSteps.clickBtnCompleteOrder();
                }
                if (!isCancel) {
                    //sExpiredDate must be in form "mm/yy"
                    asiaBillSteps.inputAsiaBillCardInfo(sCardNumber, sExpiredDate, sCVV);
                    asiaBillSteps.clickOnPayNowBtn();
                } else {
                    asiaBillSteps.clickOnCancelBtn();
                    asiaBillSteps.clickOnAcceptButtonOfTheAlert();
                    paymentMethodSteps.verifyStayingAtWhichCheckoutStep("Payment method");
                    verifyCheckoutDetailBeforeAndAfterCancelPayment();
                }
            }

            if (paymentGateway.equalsIgnoreCase("Oceanpayment")) {
                //Payment gateway: Oceanpayment
                if (isOnePageCheck) {
                    onePageCheckoutSteps.clickPlaceYourOrder();
                } else {
//                    paymentMethodSteps.clickBtnCompleteOrder();
                }
                if (!isCancel) {
                    oceanPaymentSteps.inputOceanPaymentCardInfo(sCardNumber, sExpiredDate, sCVV);
                    oceanPaymentSteps.clickOnPayNowBtn();
                } else {
                    oceanPaymentSteps.clickOnCancelBtn();
                    oceanPaymentSteps.clickOnAcceptButtonOfTheAlert();
                    paymentMethodSteps.verifyStayingAtWhichCheckoutStep("Payment method");
                    verifyCheckoutDetailBeforeAndAfterCancelPayment();
                }
            }

            if (!errorMessage.isEmpty()) {
                paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                System.out.println(errorMessage);
            }
            String code = SessionData.getDataTbVal(dataTable, row, "code");
            if(paymentGateway.equalsIgnoreCase("SEPA Direct Debit")){
                paymentMethodSteps.inputCode(code);
                onePageCheckoutSteps.clickPlaceYourOrder();
            }
        }
    }


    private void verifyCheckoutDetailBeforeAndAfterCancelPayment() {
        isOnPostPurchase = false;
        totalAmtAfter = orderSummarySteps.getTotalAmt();
        listProductAfter = orderSummarySteps.getProductList();
        listProductAfter.add(totalAmtAfter);
        paymentMethodSteps.compareProductList(listProductBefore, listProductAfter);
    }

    @Given("^complete payment for post purchase item$")
    public void complatePaymentForPostPurchaseItem() {
        switch (paymentGateway) {
            case "Paypal":
                paypalBrandName = payPalPaymentSettingsAPI.getBrandName();
                paypalSteps.clickOnAcceptCookieBtn();
                if (!paypalBrandName.isEmpty() && !isPrintbaseshop) {
                    paypalSteps.verifyBrandName(paypalBrandName);
                }
                paypalSteps.waitABit(10000); // must put wait here cause payment page loads too slow
                paypalSteps.clickBtnPaynowPayPal();
                break;
            case "Unlimint":
                if (is3Ds) {
                    cardPaySteps.inputCVV("100");
                    cardPaySteps.clickOnPayBtn();
                    cardPaySteps.submit3DS("success");
                } else {
                    cardPaySteps.inputCVV("100");
                    cardPaySteps.clickOnPayBtn();
                }
                break;
            case "Asia-Bill":
                asiaBillSteps.inputAsiaBillCardInfo("4000020951595032", "03/25", "113");
                asiaBillSteps.clickOnPayNowBtn();
                break;
            case "Oceanpayment":
                oceanPaymentSteps.inputOceanPaymentCardInfo("5454545454545454", "11/22", "113");
                oceanPaymentSteps.clickOnPayNowBtn();
                break;
            case "Stripe":
                if(paymentMethod.equalsIgnoreCase("Bancontact")|paymentMethod.equalsIgnoreCase("iDEAL")|paymentMethod.equalsIgnoreCase("giropay")){
                    paymentMethodSteps.clickBtnAuthorize();
                }
                break;
        }
    }


    @Then("^cancel payment for post-purchase item then verify checkout detail$")
    public void cancelPaymentForPostPurchaseItem() {
        switch (paymentGateway) {
            case "Paypal":
                verifyCheckoutDetailBeforeAndAfterCancelPayment();
                break;
            case "Unlimint":
                cardPaySteps.clickOnCancelBtn();
                verifyCheckoutDetailBeforeAndAfterCancelPayment();
                break;
            case "Asia-Bill":
                asiaBillSteps.clickOnCancelBtn();
                asiaBillSteps.clickOnAcceptButtonOfTheAlert();
                verifyCheckoutDetailBeforeAndAfterCancelPayment();
                break;
            case "Oceanpayment":
                oceanPaymentSteps.clickOnCancelBtn();
                oceanPaymentSteps.clickOnAcceptButtonOfTheAlert();
                verifyCheckoutDetailBeforeAndAfterCancelPayment();
                break;
        }
    }

    @Then("^verify 3Ds Unlimint for post-purchase item \"([^\"]*)\" unsuccessfully then verify checkout detail$")
    public void verify3DsFailWhenPayingForPostPurchaseItem(String postPurchaseItemName) {
        cardPaySteps.inputCVV("100");
        cardPaySteps.clickOnPayBtn();
        cardPaySteps.submit3DS("failure");
        postPurchaseOfferSteps.removeProductInPostPurchaseFromOrder(postPurchaseItemName);
        verifyCheckoutDetailBeforeAndAfterCancelPayment();
    }


    @When("^input billing address$")
    public void inputBillingAddress(List<List<String>> dataTable) {
        paymentMethodSteps.chooseBillingAddress("Use a different billing address");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sApartment = SessionData.getDataTbVal(dataTable, row, "Apartment");
            String sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");

            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);

            //add billing address into HashMap
            billingAddressHashMap.put("Billing Name", sFirstName + " " + sLastName);
            billingAddressHashMap.put("Billing Address1", sAddress);
            billingAddressHashMap.put("Billing Address2", sApartment);
            billingAddressHashMap.put("Billing City", sCity);
            billingAddressHashMap.put("Billing Zip", sZipCode);
            billingAddressHashMap.put("Billing Province", sState);
            billingAddressHashMap.put("Billing Country", sCountry);
            billingAddressHashMap.put("Billing Phone", sPhone);

            billingAddress = sFirstName + " " + sLastName + " " + sAddress + " " + sCity + " " + sZipCode + " " + sState + " " + sCountry + " " + sPhone.replaceAll("\\s", "");
            System.out.println("Expect billing: " + billingAddress);
        }
    }

    @Given("^click on Complete order button$")
    public void clickOnCompleteOrderBtn() {
        paymentMethodSteps.clickOnCompleteOrderBtn();
    }

    @When("^checkout successfully via stripe with cart \"([^\"]*)\"$")
    public void checkoutSuccessfullyViaStripe(String productNames, List<List<String>> dataTable) {
        //Add products into cart
        if (productNames.matches("@(.*)@")) {
            productNames = nameProductSbase;
        }
        productSteps.addMultipleProductsToCart(productNames);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        //Input Customer and payment info
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
            String savedForNextTime = SessionData.getDataTbVal(dataTable, row, "Saved");
            Boolean isSavedForNextTime = Boolean.parseBoolean(savedForNextTime);
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            String disCount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String productPostPurchase = SessionData.getDataTbVal(dataTable, row, "post purchase");
            //input customer info
            if (sEmail.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                customerEmail = sEmail.replaceAll("@", "") + currentTime + "@mailtothis.com";
                customerInformationSteps.enterEmail(customerEmail);
            }
            else {
                customerInformationSteps.enterEmail(sEmail);
            }
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);
            if (!disCount.isEmpty()) {
                orderSummarySteps.enterDiscountCode(disCount);
            }
            if (!savedForNextTime.isEmpty()) {
                customerInformationSteps.checkSavedInforNexttime(isSavedForNextTime);
            }
            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }
            //select shipping method
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
                shippingMethodSteps.clickContinueToPaymentMethod();
            }
            //Input payment info
            endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            cardType = cardType.isEmpty() ? "Visa" : cardType;

            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            String label = isOnePageCheck ? "Place Your Order" : "Complete order";
            paymentMethodSteps.clickButtonForLabel(label);

            if (!productPostPurchase.isEmpty()) {
                thankyouSteps.waitABit(3000);
                thankyouSteps.addProductPostPurchase(productPostPurchase);
            }
        }
        orderNumber = thankyouSteps.getOrderNumber();
        orderNameList.add(thankyouSteps.getOrderNumber());
        orderId = thankYouSteps.getOrderId();
        shippingFee = orderSummarySteps.getShippingFee();
        subTotal = thankyouSteps.getSubTotal();
        totalAmt = orderSummarySteps.getTotalAmt();
        customerName = thankyouSteps.getCustomerName();
        if (orderSummarySteps.isUseDisCount()) {
            discountAmount = orderSummarySteps.getDiscount();
        }
    }

    @When("^checkout successfully with cart \"([^\"]*)\"$")
    public void checkout_successfully_with_cart(String productNames, List<List<String>> dataTable) {
        if (productNames.matches("@(.*)@")) {
            productNames = nameProductSbase;
        }
        productSteps.addMultipleProductsToCart(productNames);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        //Input Customer and payment info
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
            String savedForNextTime = SessionData.getDataTbVal(dataTable, row, "Saved");
            Boolean isSavedForNextTime = Boolean.parseBoolean(savedForNextTime);
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            String disCount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String productPostPurchase = SessionData.getDataTbVal(dataTable, row, "post purchase");
            //input customer info

            customerInformationSteps.enterEmail(sEmail);
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);
            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }
            //select shipping method
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
                shippingMethodSteps.clickContinueToPaymentMethod();
            }
            //Input payment info
            endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            cardType = cardType.isEmpty() ? "Visa" : cardType;

            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            String label = isOnePageCheck ? "Place Your Order" : "Complete order";
            paymentMethodSteps.clickButtonForLabel(label);

        }
        orderNumber = thankyouSteps.getOrderNumber();
    }

    @When("^checkout successfully via OceanPayment with cart \"([^\"]*)\"$")
    public void checkoutSuccessfullyViaOceanPaymentWithCart(String productName, List<List<String>> dataTable) {
        if (productName.matches("@(.*)@")) {
            productName = nameProductSbase;
        }
        productSteps.addMultipleProductsToCart(productName);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
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
//            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            String disCount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String productPostPurchase = SessionData.getDataTbVal(dataTable, row, "post purchase");
            //input customer info
            customerInformationSteps.enterEmail(sEmail);
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);
            if (!disCount.isEmpty()) {
                orderSummarySteps.enterDiscountCode(disCount);
            }

            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }
            //select shipping method
            if (!onePageCheckoutSteps.isOnePage()) {
                shippingMethodSteps.clickContinueToPaymentMethod();
            }
            totalAmtBefore = orderSummarySteps.getTotalAmt();
            listProductBefore = orderSummarySteps.getProductList();
            listProductBefore.add(totalAmtBefore);
            //Input payment info
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            if (cardType.isEmpty()) {
                cardType = "Visa";
            }
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
            }
            if (isOnePageCheck) {
                onePageCheckoutSteps.clickPlaceYourOrder();
            } else {
                paymentMethodSteps.clickBtnCompleteOrder();
            }

            boolean isCancel = convertStatus(SessionData.getDataTbVal(dataTable, row, "Cancel"));
            if (!isCancel) {
                oceanPaymentSteps.inputOceanPaymentCardInfo(sCardNumber, sExpiredDate, sCVV);
                oceanPaymentSteps.clickOnPayNowBtn();
            } else {
                oceanPaymentSteps.clickOnCancelBtn();
                oceanPaymentSteps.clickOnAcceptButtonOfTheAlert();
                paymentMethodSteps.verifyStayingAtWhichCheckoutStep("Payment method");
                verifyCheckoutDetailBeforeAndAfterCancelPayment();
            }
            if (!productPostPurchase.isEmpty()) {
                thankyouSteps.waitABit(3000);
                thankyouSteps.addProductPostPurchase(productPostPurchase);
//                flagPPC = true;
            }
        }
    }

    //    -----Manual Payment Method-----
    @And("^choose manual payment method is \"([^\"]*)\"$")
    public void chooseManualPaymentMethod(String manualPM) {
        paymentMethodSteps.selectTypePaymentMethod(manualPM);
        paymentGateway = manualPM;
        paymentMethodSteps.clickCompleteOrder();
        billingAddress = "Same as shipping address";
    }

    @Then("^verify \"([^\"]*)\" ocean payment gateway successfully in storefront$")
    public void verifyOceanPaymentGatewaySuccessfullyInStorefront(String status) {
        paymentMethodSteps.verifyGatewayPaymentDisplayOnSF("ocean", status);
    }

    @And("go to payment method on checkout page")
    public void goToPaymentMethodOnCheckoutPage() {
        productSteps.searchAndSelectProduct("shirt");
        productSteps.clickAddToCart();
        productSteps.clickCheckOut();

    }

    @Then("checkout of order fulfillment successfully via stripe with cart \"([^\"]*)\"$")
    public void checkoutOfOrderFulfillmentSuccessfullyViaStripeWithCart(String product, List<List<String>> dataTable) {
        productSteps.addProductsToCart(product);
        cartSteps.clickButtonCheckout();
        float shippingBeforeAddPPC = 0;
        float subtotalBeforeAddPPC = 0;
        boolean isPlusbase = false;
        String productPostPurchase = "";
        //Input Customer and payment info
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String sFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            String sLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            String sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String sState = SessionData.getDataTbVal(dataTable, row, "State");
            String sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            paymentGateway1 = SessionData.getDataTbVal(dataTable, row, "Payment gateway 1");
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            String disCount = SessionData.getDataTbVal(dataTable, row, "Discount");
            productPostPurchase = SessionData.getDataTbVal(dataTable, row, "post purchase");
            isPlusbase = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is plusbase"));
            //input customer info
            customerInformationSteps.enterInfoUser("Email", sEmail);
            customerInformationSteps.enterInfoUser("First name", sFirstName);
            customerInformationSteps.enterInfoUser("Last name", sLastName);
            customerInformationSteps.enterInfoUser("Zip Code", sZipCode);
            customerInformationSteps.enterInfoUser("Phone", sPhone);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterInfoUser("City", sCity);
            customerInformationSteps.enterInfoUser("Address", sAddress);
            if (!disCount.isEmpty()) {
                orderSummarySteps.enterDiscountCode(disCount);
                discountAmount = orderSummarySteps.getDiscount();
            }
            //select shipping method
            String label = "Place Your Order";
            if (!onePageCheckoutSteps.isOnePage()) {
                label = "Complete order";
                customerInformationSteps.clickBtnContinueToShippingMethod();
                shippingMethodSteps.clickContinueToPaymentMethod();
            }
            //Input payment info
            endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            cardType = cardType.isEmpty() ? "Visa" : cardType;

            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            paymentMethodSteps.clickButtonForLabel(label);
            shippingBeforeAddPPC = paymentMethodSteps.getValuePayemnt("Shipping");
            subtotalBeforeAddPPC = paymentMethodSteps.getValuePayemnt("Subtotal");
            thankyouSteps.addProductPostPurchase(productPostPurchase);
        }
        orderNumber = thankyouSteps.getOrderNumber();
        orderNameList.add(thankyouSteps.getOrderNumber());
        shippingFee = orderSummarySteps.getShippingFee();
        subTotal = thankyouSteps.getSubTotal();
        totalAmt = orderSummarySteps.getTotalAmt();
        orderId = thankYouSteps.getOrderId();
        if(!productPostPurchase.isEmpty() && isPlusbase) {
            paymentMethodSteps.verifyValuePaymentAfterAddPPC(Float.parseFloat(shippingFee.replace("$", "")), shippingBeforeAddPPC);
            paymentMethodSteps.verifyValuePaymentAfterAddPPC(Float.parseFloat(subTotal.replace("$", "")), subtotalBeforeAddPPC);
        }
    }

    @And("order camp")
    public void orderCamp(List<List<String>> dataTable) throws Exception {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String _sLastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String _sAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            String _sCity = SessionData.getDataTbVal(dataTable, row, "City");
            String _sCountry = SessionData.getDataTbVal(dataTable, row, "Country");
            String _sState = SessionData.getDataTbVal(dataTable, row, "State");
            String _sZipCode = SessionData.getDataTbVal(dataTable, row, "Zip code");
            String _sPhone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String _sCartNumber = SessionData.getDataTbVal(dataTable, row, "Cart number");
            String _sCartName = SessionData.getDataTbVal(dataTable, row, "Cart name");
            String _sDate = SessionData.getDataTbVal(dataTable, row, "MM/YY");
            String _sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            productSteps.orderCamp();
            customerInformationSteps.orderCamp(_sEmail,_sLastName,_sAddress,_sCity,_sCountry, _sState, _sZipCode, _sPhone);
            paymentMethodSteps.orderCamp(_sCartNumber, _sCartName, _sDate, _sCVV);
        }
    }

    @Then("verify EU payment method has {string}")
    public void verifyEuPaymentMethod(String methods){
        String[] listMethod = methods.split(",");
        int index = 1;
        for(String method : listMethod) {
            paymentMethodSteps.verifyEUPaymentMethods(method, index);
            index++;
        }
    }

    @And("choose payment method {string}")
    public void choosePaymentMethod(String methods){
        String value = "";
        switch (methods){
            case "Bancontact":
                value = "bancontact";
                break;
            case "SEPA Direct Debit":
                value = "sepa";
                break;
            case "Credit Card":
            case "Card":
                value = "stripe-credit-card";
                break;
            case "giropay":
                value = "giro";
                break;
            case "iDEAL":
                value = "ideal";
                break;
        }
        paymentMethodSteps.choosePaymentMethods(value);
        paymentMethod = methods;
        paymentGateway = "Stripe";
    }
    @Then("complete order on Stripe page")
    public void completeOrderOnStripePage(){
        paymentMethodSteps.clickCompleteOrder();
        paymentMethodSteps.clickBtnAuthorize();
    }
    @Then("enter IBAN number {string} and complete order")
    public void enterIBANNumber(String number){
        paymentMethodSteps.inputIBANNumber(number);
        paymentMethodSteps.clickCompleteOrder();
    }
    @Then("choose {string} bank")
    public void chooseBank(String sBank){
        paymentMethodSteps.selectBank(sBank);
    }

    @When("user click on Buy with Paypal button and checkout with email {string} and password {string}")
    public void userClickOnBuyWithPaypalButtonAndCheckoutWithEmailAndPassword(String email, String pwd) {
        paymentGateway = "Paypal";
        paypalSteps.checkoutBuyWithPaypal(email, pwd);
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

    @Then("checkout successfully via stripe with cart {string} with mapping Phub")
    public void checkoutSuccessfullyViaStripeWithCartWithMappingPhub(String productNames, List<List<String>> dataTable) {
        if (productNames.matches("@(.*)@")) {
            productNames = nameProductSbase;
        }
        productSteps.addMultipleProductsToCartWithMappingPhub(productNames);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        //Input Customer and payment info
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
            String savedForNextTime = SessionData.getDataTbVal(dataTable, row, "Saved");
            Boolean isSavedForNextTime = Boolean.parseBoolean(savedForNextTime);
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            paymentGateway = SessionData.getDataTbVal(dataTable, row, "Payment gateway");
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
            String sCVV = SessionData.getDataTbVal(dataTable, row, "CVV");
            cardType = SessionData.getDataTbVal(dataTable, row, "Card type");
            String disCount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String productPostPurchase = SessionData.getDataTbVal(dataTable, row, "post purchase");
            //input customer info
            if (sEmail.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                customerEmail = sEmail.replaceAll("@", "") + currentTime + "@mailtothis.com";
                customerInformationSteps.enterEmail(customerEmail);
            }
            else {
                customerInformationSteps.enterEmail(sEmail);
            }
            customerInformationSteps.enterFirstName(sFirstName);
            customerInformationSteps.enterLastName(sLastName);
            customerInformationSteps.selectCountry(sCountry, sState);
            customerInformationSteps.enterAddress(sAddress);
            customerInformationSteps.enterApartment(sApartment);
            customerInformationSteps.enterZipCode(sZipCode);
            customerInformationSteps.enterCity(sCity);
            customerInformationSteps.enterPhone(sPhone, true);
            if (!disCount.isEmpty()) {
                orderSummarySteps.enterDiscountCode(disCount);
            }
            if (!savedForNextTime.isEmpty()) {
                customerInformationSteps.checkSavedInforNexttime(isSavedForNextTime);
            }
            if (customerInformationSteps.isExistContinueShippingMethodBtn()) {
                customerInformationSteps.clickBtnContinueToShippingMethod();
            }
            //select shipping method
            boolean isOnePageCheck = true;
            if (!onePageCheckoutSteps.isOnePage()) {
                isOnePageCheck = false;
                shippingMethodSteps.clickContinueToPaymentMethod();
            }
            //Input payment info
            endingCardNo = sCardNumber.substring(sCardNumber.length() - 4);
            paymentMethodSteps.selectTypePaymentMethod(paymentMethod);
            cardType = cardType.isEmpty() ? "Visa" : cardType;

            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
            String label = isOnePageCheck ? "Place Your Order" : "Complete order";
            paymentMethodSteps.clickButtonForLabel(label);

            if (!productPostPurchase.isEmpty()) {
                thankyouSteps.waitABit(3000);
                thankyouSteps.addProductPostPurchase(productPostPurchase);
            }
        }
        orderNumber = thankyouSteps.getOrderNumber();
        orderNameList.add(thankyouSteps.getOrderNumber());
        orderId = thankYouSteps.getOrderId();
        shippingFee = orderSummarySteps.getShippingFee();
        subTotal = thankyouSteps.getSubTotal();
        totalAmt = orderSummarySteps.getTotalAmt();
        customerName = thankyouSteps.getCustomerName();
        if (orderSummarySteps.isUseDisCount()) {
            discountAmount = orderSummarySteps.getDiscount();
        }
    }

    @And("verify special character of placeholder in billing address")
    public void verifyPlaceholderInBillingAddress() {
        paymentMethodSteps.chooseBillingAddress("Use a different billing address");
        customerInformationSteps.verifyLabelInCustomerInformation("email");
        customerInformationSteps.verifyLabelInCustomerInformation("first_name");
        customerInformationSteps.verifyLabelInCustomerInformation("last_name");
        customerInformationSteps.verifyLabelInCustomerInformation("address");
        customerInformationSteps.verifyLabelInCustomerInformation("address_line2");
        customerInformationSteps.verifyLabelInCustomerInformation("country");
        customerInformationSteps.verifyLabelInCustomerInformation("city");
        customerInformationSteps.verifyLabelInCustomerInformation("zip");
        customerInformationSteps.verifyLabelInCustomerInformation("phone");
    }
}
