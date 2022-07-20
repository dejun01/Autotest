package opencommerce.plus_base.settings;

import com.github.javafaker.PhoneNumber;
import com.opencommerce.shopbase.plusbase.steps.PaymentProvidersSteps;
import com.opencommerce.shopbase.storefront.steps.gateway.PaypalSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CustomerInformationSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OnePageCheckoutSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.PaymentMethodSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.*;
import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentProvidersDef {
    @Steps
    CustomerInformationSteps customerInformationSteps;
    @Steps
    PaypalSteps paypalSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    OnePageCheckoutSteps onePageCheckoutSteps;
    @Steps
    PaymentMethodSteps paymentMethodSteps;
    @Steps
    PaymentProvidersSteps paymentProvidersSteps;

    String nameAfterEdit = "";
    String notes = "";
    String brandName = "";
    String phone = null;

    String shopName = LoadObject.getProperty("shop");


    @And("Get {string} before edit")
    public void getBeforeEdit(String name) {
        nameAfterEdit = paymentProvidersSteps.getNameAfterEdit(name);

    }

    @And("Edit payment providers")
    public void editPaymentProviders(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "type");
            String note = SessionData.getDataTbVal(dataTable, row, "note");
            if (type.equals("Statement descriptor") && note.equals("name")) {
                notes = paymentProvidersSteps.randomName().substring(0, 14);
                paymentProvidersSteps.editName(notes, "Enter your descriptor");
                paymentProvidersSteps.clickBTSaveChanges();
            }
            if (type.equals("Brand name") && note.equals("brandname")) {
                brandName = paymentProvidersSteps.randomName();
                paymentProvidersSteps.editName(brandName, "Enter your brand name");
                paymentProvidersSteps.clickBTSaveChanges();
            }
            if (type.equals("Phone number") && note.equals("phone")) {
                phone = paymentProvidersSteps.randomPhone();
                paymentProvidersSteps.editName(phone, "Enter your phone");
                paymentProvidersSteps.clickBTSaveChanges();
            }
        }
    }

    @Then("Verify payment providers after edit")
    public void verifyPaymentProvidersAfterEdit(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "type");
            String note = SessionData.getDataTbVal(dataTable, row, "note");
            if (note.equals("name")) {
                assertThat(paymentProvidersSteps.getNameAfterEdit(type)).isEqualTo(notes);
            }
            if (note.equals("phone")) {
                assertThat(paymentProvidersSteps.getNameAfterEdit(type)).isEqualTo(phone);
            }
            if (note.equals("brandName")) {
                assertThat(paymentProvidersSteps.getNameAfterEdit(type)).isEqualTo(brandName);
            }
        }
    }

    @Then("Verify display all data clone from template store")
    public void verifyDisplayAllDataCloneFromTemplateStore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String information = SessionData.getDataTbVal(dataTable, row, "information");
            paymentProvidersSteps.verifyDisplayInforTemplateStore(information);

        }
    }

    @And("Checkout by paypal and verify brand name")
    public void checkoutByPaypalAndVerifyBrandName(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            paymentMethod = SessionData.getDataTbVal(dataTable, row, "Payment method");
            //Payment Method: Credit Card/Paypal
            String sCardNumber = SessionData.getDataTbVal(dataTable, row, "Card number");
            String sCardholder = SessionData.getDataTbVal(dataTable, row, "Cardholder name");
            String sExpiredDate = SessionData.getDataTbVal(dataTable, row, "Expired Date");
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
            String cardIssuingBank = SessionData.getDataTbVal(dataTable, row, "Card Issuing Bank");
            /*cardIssuingBank is used for AsiaBill*/
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
            if (isShopBasePayPalSmartButton && "Paypal".equalsIgnoreCase(paymentMethod)) {
                cardType = "standard";
            }
            //Payment Rotation
            boolean isRotation = convertStatus(SessionData.getDataTbVal(dataTable, row, "Rotation"));
            //Get current gateway
            String currentGateway = paymentMethodSteps.getThirdPartyGateway();
            if (isRotation) {
                if ("Credit Card".equalsIgnoreCase(paymentMethod)) {
                    switch (currentGateway) {
                        case "stripe-credit-card":
                            paymentGateway = "Stripe";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Stripe card number");
                            break;
                        case "platform-credit-card":
                            paymentGateway = "ShopBase Payments";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Stripe card number");
                            break;
                        case "paypal-pro-credit-card":
                            paymentGateway = "Paypal-Pro";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "PayPal Pro card number");
                            break;
                        case "braintree-credit-card":
                            paymentGateway = "Braintree";
                            sCardNumber = SessionData.getDataTbVal(dataTable, row, "Braintree card number");
                            is3Ds = true;
                            pwd3Ds = "1234";
                            break;
                        case "checkout-com-credit-card":
                            paymentGateway = "Checkout.com";
                            break;
                        case "cardpay-credit-card":
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
                        if (!isShopBasePayPalSmartButton) {
                            paymentMethodSteps.enterPaymentInfo(sCardNumber, sCardholder, sExpiredDate, sCVV, paymentGateway);
                        } else {
                            paypalSteps.confirmPayNowPaypal(paypalAcccount, password, cardType, paidTotalAmtByPaypal, paypalBrandName);
                        }
                        paymentMethodSteps.clickOnPayNowBtnInPaypalSmartBtn();
                    }
                }
            }

            if (paymentGateway.equalsIgnoreCase("Asia-Bill")) {
                //Payment gateway: AsiaBill
                if (isOnePageCheck) {
                    onePageCheckoutSteps.clickPlaceYourOrder();
                } else {
                    paymentMethodSteps.clickBtnCompleteOrder();
                }

                if (paymentGateway.equalsIgnoreCase("Oceanpayment")) {
                    //Payment gateway: Oceanpayment
                    if (isOnePageCheck) {
                        onePageCheckoutSteps.clickPlaceYourOrder();
                    } else {
//                    paymentMethodSteps.clickBtnCompleteOrder();
                    }
                }

                if (!errorMessage.isEmpty()) {
                    paymentMethodSteps.verifyErrorMessageForPaymentMethod(paymentGateway, errorMessage);
                    System.out.println(errorMessage);
                }
                String code = SessionData.getDataTbVal(dataTable, row, "code");
                if (paymentGateway.equalsIgnoreCase("SEPA Direct Debit")) {
                    paymentMethodSteps.inputCode(code);
                    onePageCheckoutSteps.clickPlaceYourOrder();
                }
            }
        }
    }
}
