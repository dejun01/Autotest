package opencommerce.settings;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.api.PaymentsAPI;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.settings.steps.PaymentsSettingSteps;

import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static com.opencommerce.shopbase.OrderVariable.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsSettingDef {
    String accessToken = "";
    @Steps
    PaymentsSettingSteps paymentsSettingSteps;
    @Steps
    PaymentsAPI paymentsAPI;
    @Steps
    CommonSteps commonSteps;


    public static String CSV_FILE_PATH;

    private static String accountName = "";
    private static String payPalaccountName = "";
    private static String payPalaccountPwd = "";

    //Stripe
    private static String publicKey = "";
    private static String secretKey = ""; //secret key = private key

    //PayPal Express form
    private static String clientID = "";
    private static String clientSecretKey = "";

    //PayPal Pro
    private static String payPalManagerPartner = "";
    private static String payPalManagerVendor = "";
    private static String payPalManagerUser = "";
    private static String payPalManagerPassword = "";
    private static boolean is3Ds = false;
    private static String cardinalAPIID = "";
    private static String cardinalAPIKey = "";
    private static String cardinalOrgUnitID = "";

    //Braintree - use public key and private key of stripe form
    private static String privateKey = "";
    private static String merchantID = "";
    private static String statementPhoneNumber = "";

    //Cardpay
    private static String terminalCode = "";
    private static String terminalPassword = "";
    private static String callbackSecret = "";

    //checkout.com - use public key and private key of stripe form
    private static String cityName = "";

    private static String statementDescriptor = "";
    long currentTime = System.currentTimeMillis();

    //Spay Reseller

    private static String userReseller = "";
    private static boolean hadShopbasePayment = false;
    private static double noOfOrder = 0;
    private static double GMV = 0;
    private static double rateTrackingNo = 0;
    private static boolean haveNotification = false;
    private static boolean enoughConditionSpayReseller = false;


    @Given("^Reload page$")
    public void reloadPage() {
        paymentsSettingSteps.refreshPage();
    }


    @Given("^activate \"([^\"]*)\" gateway successfully in \"([^\"]*)\" mode$")
    public void activateGateway(String gateway, String mode, List<List<String>> dataTable) {
        if (!dataTable.isEmpty()) {
            submitCredentialsInfoUpdateUIUX(gateway, mode, false, dataTable);
        }
    }

    @Given("^validate activation input for \"([^\"]*)\" in \"([^\"]*)\" mode$")
    public void validateActivationInput(String gateway, String mode, List<List<String>> dataTable) {
        if (!dataTable.isEmpty()) {
            submitCredentialsInfoUpdateUIUX(gateway, mode, true, dataTable);
        }
    }

    //Update UI-UX Payment Provider
    private void submitCredentialsInfoUpdateUIUX(String gateway, String mode, boolean isValidation, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //mode = Activate/Edit/Reactivate
            //gateway = Stripe/PayPal/PayPal Pro/Braintree/Cardpay/Checkout.com
            String stripeAccountName = SessionData.getDataTbVal(dataTable, row, "EU account name");
            if ("Activate".equalsIgnoreCase(mode)) {
                if (isValidation) {
                    accountName = SessionData.getDataTbVal(dataTable, row, "Account name");
                } else {
                    accountName = gateway + "-" + System.currentTimeMillis();
                }
                if (!stripeAccountName.isEmpty()) {
                    accountName = stripeAccountName;
                }

            }

            //Stripe Form
            publicKey = SessionData.getDataTbVal(dataTable, row, "Public Key");
            secretKey = SessionData.getDataTbVal(dataTable, row, "Secret Key");

            //PayPal form
            clientID = SessionData.getDataTbVal(dataTable, row, "Client ID");
            clientSecretKey = SessionData.getDataTbVal(dataTable, row, "Client Secret Key");

            //PayPal Pro form
            payPalManagerPartner = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Partner");
            payPalManagerVendor = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Vendor");
            payPalManagerUser = SessionData.getDataTbVal(dataTable, row, "PayPal Manager User");
            payPalManagerPassword = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Password");
            is3Ds = convertStatus(SessionData.getDataTbVal(dataTable, row, "3D secure"));
            cardinalAPIID = SessionData.getDataTbVal(dataTable, row, "Cardinal API ID");
            cardinalAPIKey = SessionData.getDataTbVal(dataTable, row, "Cardinal API Key");
            cardinalOrgUnitID = SessionData.getDataTbVal(dataTable, row, "Cardinal Org Unit ID");

            //Braintree
            merchantID = SessionData.getDataTbVal(dataTable, row, "Merchant ID");
            privateKey = SessionData.getDataTbVal(dataTable, row, "Private Key");
            statementPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");

            //Cardpay
            terminalCode = SessionData.getDataTbVal(dataTable, row, "Terminal Code");
            terminalPassword = SessionData.getDataTbVal(dataTable, row, "Terminal Password");
            callbackSecret = SessionData.getDataTbVal(dataTable, row, "Callback Secret");

            //Checkout.com
            cityName = SessionData.getDataTbVal(dataTable, row, "City name");

            statementDescriptor = SessionData.getDataTbVal(dataTable, row, "Statement descriptor");

            String _case = SessionData.getDataTbVal(dataTable, row, "Case");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Message");
            String noticeMessage = SessionData.getDataTbVal(dataTable, row, "Notice Message");

            if ("Activate".equalsIgnoreCase(mode)) {
                if (!"PayPal".equalsIgnoreCase(gateway) && !"PayPal-live".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.clickToChooseThirdPartyProvider();
                    paymentsSettingSteps.selectPaymentProvider(gateway);
                } else {
                    paymentsSettingSteps.clickToChooseActivePayPalWithAPIKey();
                }

                //turn on test mode
                if ("Stripe".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.clickCheckboxUseAPIUpdateUIUX();
                } else if (gateway.contains("live")) {
                    paymentsSettingSteps.setTestModeToggleStatusUpdateUIUX(false);
                } else if ("PayPal".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.setTestModeToggleStatusUpdateUIUX(true);
                } else if (!"PayPal".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.setTestModeToggleStatusUpdateUIUX(true);
                }

                //Input account name
                paymentsSettingSteps.inputAccountNameUpdateUIUX(accountName);
            } else if ("Edit".equalsIgnoreCase(mode) || "Reactivate".equalsIgnoreCase(mode)) {
                System.out.println(accountName);
                paymentsSettingSteps.expandGatewayEditingFormUpdateUIUX(accountName, gateway);
            }

            switch (gateway) {
                case "Stripe":
                case "Stripe-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPublicKeyUpdateUIUX(publicKey);
                            paymentsSettingSteps.inputSecretKeyUpdateUIUX(secretKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputSecretKey(accountName, secretKey);
                            if (!statementDescriptor.isEmpty()) {
                                paymentsSettingSteps.inputStatementDescriptor(accountName, statementDescriptor);
                            }
                            break;
                    }
                    break;
                case "PayPal":
                case "PayPal-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputClientIDUpdateUIUX(clientID);
                            paymentsSettingSteps.inputClientSecretKeyUpdateUIUX(clientSecretKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputClientID(accountName, clientID);
                            paymentsSettingSteps.inputClientSecretKey(accountName, clientSecretKey);
                            break;
                    }
                    break;
                case "PayPal Pro":
                case "PayPal Pro-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPayPalManagerPartnerUpdateUIUX(payPalManagerPartner);
                            paymentsSettingSteps.inputPayPalManagerVendorUpdateUIUX(payPalManagerVendor);
                            paymentsSettingSteps.inputPayPalManagerUserUpdateUIUX(payPalManagerUser);
                            paymentsSettingSteps.inputPayPalManagerPasswordUpdateUIUX(payPalManagerPassword);
                            if (is3Ds) {
                                paymentsSettingSteps.set3DSToggleStatusUpdateUIUX(true);
                                paymentsSettingSteps.inputCardinalAPIIDUpdateUIUX(cardinalAPIID);
                                paymentsSettingSteps.inputCardinalAPIKeyUpdateUIUX(cardinalAPIKey);
                                paymentsSettingSteps.inputCardinalOrgUnitIDUpdateUIUX(cardinalOrgUnitID);
                            } else {
                                paymentsSettingSteps.set3DSToggleStatusUpdateUIUX(false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIID("", false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIKey("", false);
                                paymentsSettingSteps.verifyDisplayOfCardinalOrgUnitID("", false);
                            }
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPayPalManagerPartner(accountName, payPalManagerPartner);
                            paymentsSettingSteps.inputPayPalManagerVendor(accountName, payPalManagerVendor);
                            paymentsSettingSteps.inputPayPalManagerUser(accountName, payPalManagerUser);
                            paymentsSettingSteps.inputPayPalManagerPassword(accountName, payPalManagerPassword);
                            if (is3Ds) {
                                paymentsSettingSteps.set3DSToggleStatusInAccountSection(accountName, true);
                                paymentsSettingSteps.inputCardinalAPIID(accountName, cardinalAPIID);
                                paymentsSettingSteps.inputCardinalAPIKey(accountName, cardinalAPIKey);
                                paymentsSettingSteps.inputCardinalOrgUnitID(accountName, cardinalOrgUnitID);
                            } else {
                                paymentsSettingSteps.set3DSToggleStatusInAccountSection(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIID(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIKey(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalOrgUnitID(accountName, false);
                            }
                            break;
                    }
                    break;
                case "Braintree":
                case "Braintree-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputMerchantIDUpdateUIUX(merchantID);
                            paymentsSettingSteps.inputPublicKeyUpdateUIUX(publicKey);
                            paymentsSettingSteps.inputPrivateKeyUpdateUIUX(privateKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputMerchantID(accountName, merchantID);
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputPrivateKey(accountName, privateKey);
                            break;
                    }
                    break;
                case "Unlimint":
                case "Unlimint-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputTerminalCodeUpdateUIUX(terminalCode);
                            paymentsSettingSteps.inputTerminalPasswordUpdateUIUX(terminalPassword);
                            paymentsSettingSteps.inputCallbackSecretUpdateUIUX(callbackSecret);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputTerminalCode(accountName, terminalCode);
                            paymentsSettingSteps.inputTerminalPassword(accountName, terminalPassword);
                            paymentsSettingSteps.inputCallbackSecret(accountName, callbackSecret);
                            break;
                    }
                    break;
                case "Checkout.com":
                case "Checkout.com-live":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPublicKeyUpdateUIUX(publicKey);
                            paymentsSettingSteps.inputPrivateKeyUpdateUIUX(privateKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputPrivateKey(accountName, privateKey);
                            break;
                    }
                    break;
            }

            switch (mode) {
                case "Activate":
                    paymentsSettingSteps.clickOnActivateBtnUpdateUIUX(isValidation);
                    if (isValidation) {
                        if (!errorMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyErrorMessage(errorMessage);
                            paymentsSettingSteps.navigateToPaymentProvidersMainPage(gateway);
                        }
                        if (!noticeMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyNoticeMessage(noticeMessage);
                            paymentsSettingSteps.navigateToPaymentProvidersMainPage(gateway);
                        }
                    }
                    break;
                case "Edit":
                    paymentsSettingSteps.clickOnSaveBtn(accountName);
                    if (!isValidation) {
                        paymentsSettingSteps.verifyNoticeMessage("All changes were successfully saved");
                        System.out.println("All changes were successfully saved");
                    } else {
                        if (!errorMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyErrorMessage(errorMessage);
                        }
                        if (!noticeMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyNoticeMessage(noticeMessage);
                        }
                    }
                    break;
                case "Reactivate":
                    paymentsSettingSteps.verifyStatusOfAccount(accountName, "Inactive");
                    paymentsSettingSteps.clickOnActivateBtn(accountName);
                    paymentsSettingSteps.verifyNoticeMessage("Activated successfully");
                    System.out.println("Reactivate successfully");
                    break;
            }
            reloadPage();
        }
    }

    private void submitCredentialsInfo(String gateway, String mode, boolean isValidation, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            //mode = Activate/Edit/Reactivate
            //gateway = Stripe/PayPal Express/PayPal Pro/Braintree/Cardpay/Checkout.com
            if ("Activate".equalsIgnoreCase(mode)) {
                if (isValidation) {
                    accountName = SessionData.getDataTbVal(dataTable, row, "Account name");
                } else {
                    accountName = gateway + "-" + System.currentTimeMillis();
                }
            }

            //Stripe Form
            publicKey = SessionData.getDataTbVal(dataTable, row, "Public Key");
            secretKey = SessionData.getDataTbVal(dataTable, row, "Secret Key");

            //PayPal Express form
            clientID = SessionData.getDataTbVal(dataTable, row, "Client ID");
            clientSecretKey = SessionData.getDataTbVal(dataTable, row, "Client Secret Key");

            //PayPal Pro form
            payPalManagerPartner = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Partner");
            payPalManagerVendor = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Vendor");
            payPalManagerUser = SessionData.getDataTbVal(dataTable, row, "PayPal Manager User");
            payPalManagerPassword = SessionData.getDataTbVal(dataTable, row, "PayPal Manager Password");
            is3Ds = convertStatus(SessionData.getDataTbVal(dataTable, row, "3D secure"));
            cardinalAPIID = SessionData.getDataTbVal(dataTable, row, "Cardinal API ID");
            cardinalAPIKey = SessionData.getDataTbVal(dataTable, row, "Cardinal API Key");
            cardinalOrgUnitID = SessionData.getDataTbVal(dataTable, row, "Cardinal Org Unit ID");

            //Braintree
            merchantID = SessionData.getDataTbVal(dataTable, row, "Merchant ID");
            privateKey = SessionData.getDataTbVal(dataTable, row, "Private Key");
            statementPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");

            //Cardpay
            terminalCode = SessionData.getDataTbVal(dataTable, row, "Terminal Code");
            terminalPassword = SessionData.getDataTbVal(dataTable, row, "Terminal Password");
            callbackSecret = SessionData.getDataTbVal(dataTable, row, "Callback Secret");

            //Checkout.com
            cityName = SessionData.getDataTbVal(dataTable, row, "City name");

            statementDescriptor = SessionData.getDataTbVal(dataTable, row, "Statement descriptor");

            String _case = SessionData.getDataTbVal(dataTable, row, "Case");
            String errorMessage = SessionData.getDataTbVal(dataTable, row, "Message");
            String noticeMessage = SessionData.getDataTbVal(dataTable, row, "Notice Message");

            if ("Activate".equalsIgnoreCase(mode)) {
                if (!"PayPal Express".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.clickOnAddAnotherAccountBtnInThirdPartySection();
                    paymentsSettingSteps.clickOnSelectProviderDropDown();
                    paymentsSettingSteps.selectPaymentGateway(gateway);
                } else {
                    paymentsSettingSteps.clickOnAddAnotherAccountBtnInPayPalSection();
                }

                //turn on test mode
                if ("Stripe".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.clickCheckboxUseAPI();
                } else if ("PayPal Express".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.setTestModeToggleStatusInPayPalSection(true);
                } else if (!"PayPal Express".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.setTestModeToggleStatusInThirdPartySection(true);
                }

                //Input account name
                if (!"PayPal Express".equalsIgnoreCase(gateway)) {
                    paymentsSettingSteps.inputAccountNameInThirdPartySection(accountName);
                } else {
                    paymentsSettingSteps.inputAccountNameInPayPalSection(accountName);
                }
            } else if ("Edit".equalsIgnoreCase(mode) || "Reactivate".equalsIgnoreCase(mode)) {
                System.out.println(accountName);
                paymentsSettingSteps.clickOnAccountName(accountName);
            }

            switch (gateway) {
                case "Stripe":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPublicKey(publicKey);
                            paymentsSettingSteps.inputSecretKey(secretKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputSecretKey(accountName, secretKey);
                            if (!statementDescriptor.isEmpty()) {
                                paymentsSettingSteps.inputStatementDescriptor(accountName, statementDescriptor);
                            }
                            break;
                    }
                    break;
                case "PayPal Express":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputClientID(clientID);
                            paymentsSettingSteps.inputClientSecretKey(clientSecretKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputClientID(accountName, clientID);
                            paymentsSettingSteps.inputClientSecretKey(accountName, clientSecretKey);
                            break;
                    }
                    break;
                case "PayPal Pro":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPayPalManagerPartner(payPalManagerPartner);
                            paymentsSettingSteps.inputPayPalManagerVendor(payPalManagerVendor);
                            paymentsSettingSteps.inputPayPalManagerUser(payPalManagerUser);
                            paymentsSettingSteps.inputPayPalManagerPassword(payPalManagerPassword);
                            if (is3Ds) {
                                paymentsSettingSteps.set3DSToggleStatusInThirdPartySection(true);
                                paymentsSettingSteps.inputCardinalAPIID(cardinalAPIID);
                                paymentsSettingSteps.inputCardinalAPIKey(cardinalAPIKey);
                                paymentsSettingSteps.inputCardinalOrgUnitID(cardinalOrgUnitID);
                            } else {
                                paymentsSettingSteps.set3DSToggleStatusInThirdPartySection(false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIID("", false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIKey("", false);
                                paymentsSettingSteps.verifyDisplayOfCardinalOrgUnitID("", false);
                            }
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPayPalManagerPartner(accountName, payPalManagerPartner);
                            paymentsSettingSteps.inputPayPalManagerVendor(accountName, payPalManagerVendor);
                            paymentsSettingSteps.inputPayPalManagerUser(accountName, payPalManagerUser);
                            paymentsSettingSteps.inputPayPalManagerPassword(accountName, payPalManagerPassword);
                            if (is3Ds) {
                                paymentsSettingSteps.set3DSToggleStatusInAccountSection(accountName, true);
                                paymentsSettingSteps.inputCardinalAPIID(accountName, cardinalAPIID);
                                paymentsSettingSteps.inputCardinalAPIKey(accountName, cardinalAPIKey);
                                paymentsSettingSteps.inputCardinalOrgUnitID(accountName, cardinalOrgUnitID);
                            } else {
                                paymentsSettingSteps.set3DSToggleStatusInAccountSection(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIID(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalAPIKey(accountName, false);
                                paymentsSettingSteps.verifyDisplayOfCardinalOrgUnitID(accountName, false);
                            }
                            break;
                    }
                    break;
                case "Braintree":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputMerchantID(merchantID);
                            paymentsSettingSteps.inputPublicKey(publicKey);
                            paymentsSettingSteps.inputPrivateKey(privateKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputMerchantID(accountName, merchantID);
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputPrivateKey(accountName, privateKey);
                            if (!statementDescriptor.isEmpty()) {
                                paymentsSettingSteps.inputStatementDescriptor(accountName, statementDescriptor);
                            }
                            if (!statementPhoneNumber.isEmpty()) {
                                paymentsSettingSteps.inputPhoneNumber(accountName, statementPhoneNumber);
                            }
                            break;
                    }
                    break;
                case "Cardpay":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputTerminalCode(terminalCode);
                            paymentsSettingSteps.inputTerminalPassword(terminalPassword);
                            paymentsSettingSteps.inputCallbackSecret(callbackSecret);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputTerminalCode(accountName, terminalCode);
                            paymentsSettingSteps.inputTerminalPassword(accountName, terminalPassword);
                            paymentsSettingSteps.inputCallbackSecret(accountName, callbackSecret);
                            if (!statementDescriptor.isEmpty()) {
                                paymentsSettingSteps.inputStatementDescriptor(accountName, statementDescriptor);
                            }
                            break;
                    }
                    break;
                case "Checkout.com":
                    switch (mode) {
                        case "Activate":
                            paymentsSettingSteps.inputPublicKey(publicKey);
                            paymentsSettingSteps.inputPrivateKey(privateKey);
                            break;
                        case "Edit":
                        case "Reactivate":
                            paymentsSettingSteps.inputPublicKey(accountName, publicKey);
                            paymentsSettingSteps.inputPrivateKey(accountName, privateKey);
                            if (!statementDescriptor.isEmpty()) {
                                paymentsSettingSteps.inputStatementDescriptor(accountName, statementDescriptor);
                            }
                            if (!cityName.isEmpty()) {
                                paymentsSettingSteps.inputCityName(accountName, cityName);
                            }
                            break;
                    }
                    break;
            }

            switch (mode) {
                case "Activate":
                    if (!"PayPal Express".equalsIgnoreCase(gateway)) {
                        paymentsSettingSteps.clickOnActivateBtnInThirdPartySection();
                    } else {
                        paymentsSettingSteps.clickOnActivateBtnInPayPalSection();
                    }
                    if (!isValidation) {
                        paymentsSettingSteps.verifyNoticeMessage("Activated successfully");
                        System.out.println("Activated successfully");
                    } else {
                        if (!errorMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyErrorMessage(errorMessage);
                        }
                        if (!noticeMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyNoticeMessage(noticeMessage);
                        }
                    }
                    break;
                case "Edit":
                    paymentsSettingSteps.clickOnSaveBtn(accountName);
                    if (!isValidation) {
                        paymentsSettingSteps.verifyNoticeMessage("All changes were successfully saved");
                        System.out.println("All changes were successfully saved");
                    } else {
                        if (!errorMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyErrorMessage(errorMessage);
                        }
                        if (!noticeMessage.isEmpty()) {
                            System.out.println(_case);
                            paymentsSettingSteps.verifyNoticeMessage(noticeMessage);
                        }
                    }
                    break;
                case "Reactivate":
                    paymentsSettingSteps.verifyStatusOfAccount(accountName, "Inactive");
                    paymentsSettingSteps.clickOnActivateBtn(accountName);
                    paymentsSettingSteps.verifyNoticeMessage("Activated successfully");
                    System.out.println("Reactivate successfully");
                    break;
            }
//            reloadPage();
        }
    }

    @Given("^verify \"([^\"]*)\" gateway info after \"([^\"]*)\"$")
    public void verifyGatewayInfo(String gateway, String mode) {
        //mode = Activating/Deactivating/Editing/Restricted
        String gatewayStatus = "";
        System.out.println(accountName);
        switch (mode) {
            case "Activating":
            case "Editing":
                gatewayStatus = "Active";
                break;
            case "Deactivating":
                gatewayStatus = "Inactive";
                break;
            case "Restricted":
                gatewayStatus = "Restricted";
                break;
        }
        paymentsSettingSteps.verifyStatusOfAccount(accountName, gatewayStatus);
//        if (!"PayPal".equalsIgnoreCase(gateway)) {
//            paymentsSettingSteps.verifyStatusOfAccountUpdateUIUX(accountName, gateway, gatewayStatus);
//        } else {
//            paymentsSettingSteps.verifyStatusOfPayPalAccountUpdateUIUX(accountName, gatewayStatus);
//        }
        //  paymentsSettingSteps.verifyGatewayUsedUpdateUIUX(accountName, gateway);

        paymentsSettingSteps.expandGatewayEditingFormUpdateUIUX(accountName, gateway);
        System.out.println(accountName);

        switch (gateway) {
            case "Stripe":
                String actual_PublicKey = paymentsSettingSteps.getPublicKey(accountName);
                String actual_SecretKey = paymentsSettingSteps.getSecretKey(accountName);
                assertThat(actual_PublicKey).isEqualTo(publicKey);
                assertThat(actual_SecretKey).isEqualTo(secretKey);
                if ("Editing".equalsIgnoreCase(mode) && !statementDescriptor.isEmpty()) {
                    String actual_descriptor = paymentsSettingSteps.getStatementDescriptor(accountName);
                    assertThat(actual_descriptor).isEqualTo(statementDescriptor);
                }
                break;
            case "PayPal":
                String actual_clientId = paymentsSettingSteps.getClientID(accountName);
                String actual_clientSecretKey = paymentsSettingSteps.getClientSecretKey(accountName);
                assertThat(actual_clientId).isEqualTo(clientID);
                assertThat(actual_clientSecretKey).isEqualTo(clientSecretKey);
                break;
            case "PayPal Pro":
                String actual_payPalManagerPartner = paymentsSettingSteps.getPayPalManagerPartner(accountName);
                String actual_payPalManagerVendor = paymentsSettingSteps.getPayPalManagerVendor(accountName);
                String actual_payPalManagerUser = paymentsSettingSteps.getPayPalManagerUser(accountName);
                String actual_payPalManagerPassword = paymentsSettingSteps.getPayPalManagerPassword(accountName);
                assertThat(actual_payPalManagerPartner).isEqualTo(payPalManagerPartner);
                assertThat(actual_payPalManagerVendor).isEqualTo(payPalManagerVendor);
                assertThat(actual_payPalManagerUser).isEqualTo(payPalManagerUser);
                assertThat(actual_payPalManagerPassword).isEqualTo(payPalManagerPassword);
                if (is3Ds) {
                    String actual_cardinalAPIID = paymentsSettingSteps.getCardinalAPIID(accountName);
                    String actual_cardinalAPIKey = paymentsSettingSteps.getCardinalAPIKey(accountName);
                    String actual_cardinalOrgUnitID = paymentsSettingSteps.getCardinalOrgUnitID(accountName);
                    assertThat(actual_cardinalAPIID).isEqualTo(cardinalAPIID);
                    assertThat(actual_cardinalAPIKey).isEqualTo(cardinalAPIKey);
                    assertThat(actual_cardinalOrgUnitID).isEqualTo(cardinalOrgUnitID);
                }
                break;
            case "Braintree":
                String actual_merchantID = paymentsSettingSteps.getMerchantID(accountName);
                String actual_publicKey = paymentsSettingSteps.getPublicKey(accountName);
                String actual_privateKey = paymentsSettingSteps.getPrivateKey(accountName);
                assertThat(actual_merchantID).isEqualTo(merchantID);
                assertThat(actual_publicKey).isEqualTo(publicKey);
                assertThat(actual_privateKey).isEqualTo(privateKey);
                if ("Editing".equalsIgnoreCase(mode) && !statementDescriptor.isEmpty()) {
                    String actual_descriptor = paymentsSettingSteps.getStatementDescriptor(accountName);
                    assertThat(actual_descriptor).isEqualTo(statementDescriptor);
                    String actual_phoneNumber = paymentsSettingSteps.getStatementPhoneNumber(accountName);
                    assertThat(actual_phoneNumber).isEqualTo(statementPhoneNumber);
                }
                break;
            case "Unlimint":
                String actual_terminalCode = paymentsSettingSteps.getTerminalCode(accountName);
                String actual_terminalPassword = paymentsSettingSteps.getTerminalPassword(accountName);
                String actual_callbackSecret = paymentsSettingSteps.getCallbackSecret(accountName);
                assertThat(actual_terminalCode).isEqualTo(terminalCode);
                assertThat(actual_terminalPassword).isEqualTo(terminalPassword);
                assertThat(actual_callbackSecret).isEqualTo(callbackSecret);
                if ("Editing".equalsIgnoreCase(mode) && !statementDescriptor.isEmpty()) {
                    String actual_descriptor = paymentsSettingSteps.getStatementDescriptor(accountName);
                    assertThat(actual_descriptor).isEqualTo(statementDescriptor);
                }
                break;
            case "Checkout.com":
                String actual_publicKey1 = paymentsSettingSteps.getPublicKey(accountName);
                String actual_privateKey1 = paymentsSettingSteps.getPrivateKey(accountName);
                assertThat(actual_publicKey1).isEqualTo(publicKey);
                assertThat(actual_privateKey1).isEqualTo(privateKey);
                if ("Editing".equalsIgnoreCase(mode) && !statementDescriptor.isEmpty()) {
                    String actual_descriptor = paymentsSettingSteps.getStatementDescriptor(accountName);
                    assertThat(actual_descriptor).isEqualTo(statementDescriptor);
                    String actual_cityName = paymentsSettingSteps.getCityName(accountName);
                    assertThat(actual_cityName).isEqualTo(cityName);
                }
                break;
        }
        reloadPage();
    }

    @Given("^deactivate gateway \"([^\"]*)\" by account name \"([^\"]*)\"$")
    public void deactivateGateway(String gateway, String _accountName) {
        String section = "";
        if (!_accountName.isEmpty()) {
            accountName = _accountName;
        }
        System.out.println(accountName);
        paymentsSettingSteps.expandGatewayEditingFormUpdateUIUX(accountName, gateway);
        paymentsSettingSteps.clickOnDeactivateBtn(accountName);
        paymentsSettingSteps.verifyNoticeMessage("Deactivated successfully");
        System.out.println("Deactivate successfully");
        reloadPage();
    }

    @Given("^remove gateway account \"([^\"]*)\" by account name \"([^\"]*)\"$")
    public void removeGateway(String gateway, String _accountName) {
        String section = "";
        if (!_accountName.isEmpty()) {
            accountName = _accountName;
        }
        System.out.println(accountName);
        paymentsSettingSteps.expandGatewayEditingFormUpdateUIUX(accountName, gateway);
        paymentsSettingSteps.clickOnRemoveAccountBtn(accountName);
        paymentsSettingSteps.verifyDisplayOfRemoveConfirmPopup();
        paymentsSettingSteps.clickOnCancelBtnInPopup();
        paymentsSettingSteps.clickOnRemoveAccountBtn(accountName);
        paymentsSettingSteps.clickOnRemoveBtnInPopup();
        paymentsSettingSteps.verifyAccountAfterRemovingUpdateUIUX(accountName, gateway);
    }

    @Given("^remove all account$")
    public void removeAllAccount() {
        List<String> listOfAccountName = paymentsSettingSteps.getAccountName("");
        int size = listOfAccountName.size();
        if (size > 0) {
            for (String accountName : listOfAccountName) {
                if (!accountName.contains("ShopBase Payments")) {
                    System.out.println("Start delete " + accountName);
                    paymentsSettingSteps.removeAccount(accountName);
                    System.out.println("Delete " + accountName + " successfully");
                }
            }
        }
    }

    @Given("^get list of active payment account by API$")
    public void getListOfActivePaymentAccountByAPI() {
        listOfActivePaymentAccount = paymentsAPI.getListOfActivePaymentMethod();
        System.out.println("------------- List of active account: " + listOfActivePaymentAccount);
    }

    @Given("^verify payment rotation after checkout$")
    public void verifyRotationAfterCheckout() {
        if (!"Paypal".equalsIgnoreCase(paymentMethod)) {
            paymentsSettingSteps.verifyPaymentRotationCreditCard(listOfActivePaymentAccount, listOfUsedPaymentAccount);
        } else {
            paymentsSettingSteps.verifyPaymentRotationPayPal(listOfActivePaymentAccount, listOfUsedPaymentAccount);
        }
    }

    @Given("^activate PayPal gateway successfully by PCP flow$")
    public void activatePayPalGatewayByPCP(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            payPalaccountName = SessionData.getDataTbVal(dataTable, row, "Email");
            payPalaccountPwd = SessionData.getDataTbVal(dataTable, row, "Password");
        }
        System.out.println(payPalaccountName + " " + payPalaccountPwd);
//        paymentsSettingSteps.loginToActivatePayPalPaymentProvider(payPalaccountName,payPalaccountPwd);
        paymentsSettingSteps.navigateToSignInOrSigUpPayPal();
        paymentsSettingSteps.switchWindowAndGetTitle();
        paymentsSettingSteps.enterAccount(payPalaccountName, payPalaccountPwd);
    }


    @Then("^verify PayPal gateway info after \"([^\"]*)\" by PCP flow$")
    public void verifyPayPalGatewayInfoActivatedByPCPFlow(String mode) {
        String gatewayStatus = "";
        System.out.println(" payPalaccountName " + payPalaccountName);
        switch (mode) {
            case "Activating":
                gatewayStatus = "Active";
                break;
            case "Deactivating":
                gatewayStatus = "Inactive";
                break;
            case "Restricted":
                gatewayStatus = "Restricted";
                break;
        }
        paymentsSettingSteps.verifyStatusOfAccount("PayPal Express Checkout", gatewayStatus);
        paymentsSettingSteps.expandGatewayEditingFormUpdateUIUX("PayPal Express Checkout", "PayPal");
        String actualConnectedAccountEmail = paymentsSettingSteps.getConnectedAccountName();
        assertThat(actualConnectedAccountEmail).isEqualTo(payPalaccountName);
    }

    //    -----COD-----

    @Given("^Go to \"([^\"]*)\" section$")
    public void GoToManualPaymentMethodSection(String manualPM) {
        paymentsSettingSteps.goToAManualPaymentMethodPage(manualPM);
    }

    @When("^Add \"([^\"]*)\" to 'Additional details' field and active \"([^\"]*)\"$")
    public void inputAdditionalDetails(String data, String manualPM) {
        paymentsSettingSteps.inputAdditionalDetails(data);
        paymentsSettingSteps.clickBtnActivemanualPM(manualPM);
    }

    @When("Change Additional details field to {string}")
    public void changeAdditionalDetail(String data) {
        paymentsSettingSteps.clickBtnEditActiveCOD();
        paymentsSettingSteps.inputAdditionalDetails(data);
        paymentsSettingSteps.clickBtnSaveChanges();
    }

    @Then("^Verify \"([^\"]*)\" is active$")
    public void verifyActiveManualPaymentMethod(String manualPM) {
        paymentsSettingSteps.verifyStatusManualPaymentMethodActivate(manualPM);
    }

    @And("^Verify The 'Additional details' field is \"([^\"]*)\"$")
    public void CheckDataInAdditionalDetail(String data) {
        paymentsSettingSteps.verifyAdditional(data);
    }

    @And("^deactivate \"([^\"]*)\"$")
    public void deactiveManualPaymentMethod(String manualPM) {
        paymentsSettingSteps.clickToDeactiveManualPaymentMethod(manualPM);
        commonSteps.waitUntilToastMsgInvisible();
    }

    @When("^\"([^\"]*)\" ocean payment gateway successfully in \"([^\"]*)\" mode$")
    public void oceanPaymentGatewaySuccessfullyInMode(String action, String mode, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String accName = SessionData.getDataTbVal(dataTable, row, "Account name");
            String account = SessionData.getDataTbVal(dataTable, row, "Account");
            String terminal = SessionData.getDataTbVal(dataTable, row, "Terminal");
            String secureCode = SessionData.getDataTbVal(dataTable, row, "Secure Code");
            Boolean isActive = paymentsSettingSteps.getStatusGateway("OceanPayment");
            System.out.println("Active: " + isActive);
            switch (action) {
                case "activate":
                    if (mode.equalsIgnoreCase("Activate")) {
                        paymentsSettingSteps.clickbtnChooseAlternativeProvider();
                        paymentsSettingSteps.chooseGateway("Ocean");
                        paymentsSettingSteps.inputAccountNameInOceanPaymentSection(accName + currentTime);
                        paymentsSettingSteps.inputAccountInPaymentSection(account);
                        paymentsSettingSteps.inputTerminalInPaymentSection(terminal);
                        paymentsSettingSteps.inputSecureCodeInPaymentSection(secureCode);
                        paymentsSettingSteps.clickBtnAddAccount();
                        paymentsSettingSteps.verifyAddGatewaySuccessfully(accName + currentTime);
                    } else {
                        paymentsSettingSteps.clickGatewayInlist("OceanPayment");
//                        if (!isActive) {
                        paymentsSettingSteps.clickbtnActivate("OceanPayment");
                        paymentsSettingSteps.verifyActiveSuccessfully();
//                        }
                    }
                    break;
                case "deactivate":
                    paymentsSettingSteps.clickGatewayInlist("OceanPayment");
//                    if (isActive) {
                    paymentsSettingSteps.clickbtnDeactivate("OceanPayment");
                    paymentsSettingSteps.verifyDeactiveSuccessfully();
//                    }
                    break;
            }
        }
    }


    @Then("remove all account gateways")
    public void removeAllAccountGateways() {
        List<String> listOfAccount = paymentsSettingSteps.getAccountList();
        int size = listOfAccount.size();
        if (size > 0) {
            for (String account : listOfAccount) {
                System.out.println("Start delete " + account);
                paymentsSettingSteps.clickGatewayInlist(account);
                paymentsSettingSteps.clickBtnRemoveAccount(account);
                paymentsSettingSteps.clickbtnRemoveOnConfirmPopup();
                paymentsSettingSteps.verifyRemoveAccountSuccessfully();
                System.out.println("Delete " + account + " successfully");
            }
        }
    }

    @When("verify the message is displayed")
    public void verifyTheMessageIsDisplayed(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            paymentsSettingSteps.verifyTheMsgPresent(text);
        }
    }

    @Given("^click on Sign in\\/Sign up with PayPal button$")
    public void clickOnSignInSignUpWithPayPalButton() {
        paymentsSettingSteps.clickToChooseActiveByLoginWithPayPal();
    }


    //    ----SPay Reseller----

    @And("verify conditions of user for register Spay Reseller")
    public void verifyConditionsOfUserForRegisterSpayReseller(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            userReseller = SessionData.getDataTbVal(dataTable, row, "Username");
            hadShopbasePayment = convertStatus(SessionData.getDataTbVal(dataTable, row, "Had shopbase payment"));
            noOfOrder = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Number of order"));
            GMV = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "GMV"));
            rateTrackingNo = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Rate tracking no"));
        }

        if (!hadShopbasePayment) {
            haveNotification = true;
            if ((rateTrackingNo >= 60) && ((noOfOrder > 50) | (GMV > 2000))) {
                enoughConditionSpayReseller = true;
            }
        }
    }

//    @Then("verify Spay Reseller on dashboard")
//    public void verifySpayResellersOnDashboard() {
//        if (!enoughConditionSpayReseller) {
//            assertThat(paymentsSettingSteps.isSpayResellerButtonClickable()).isFalse();
//            paymentsSettingSteps.verifyTextUnderSpayResellerButton(false);
//        } else {
//            assertThat(paymentsSettingSteps.isSpayResellerButtonClickable()).isTrue();
//            paymentsSettingSteps.verifyTextUnderSpayResellerButton(true);
//        }
//    }

    @Then("verify notification on dashboard")
    public void verifyNotificationOnDashboard() {
    }

    @Then("reset Spay Reseller account")
    public void resetSpayResellerAccountByAPI() {
        paymentsSettingSteps.resetSpayResellerAccountByAPI();
    }

    @When("submit KYC form to register for Spay Reseller")
    public void submitKYCFormToRegisterForSpayReseller(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            String legalBusinessName = SessionData.getDataTbVal(dataTable, row, "Legal business name");
            String identificationNumber = SessionData.getDataTbVal(dataTable, row, "Employer Identification Number");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String zipCode = SessionData.getDataTbVal(dataTable, row, "ZIP");
            String state = SessionData.getDataTbVal(dataTable, row, "State");

            boolean isBusinessRegistrationUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Business registration"));
            String firstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String jobTitle = SessionData.getDataTbVal(dataTable, row, "Job title");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String idNumber = SessionData.getDataTbVal(dataTable, row, "ID number");
            boolean isIDFrontUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "ID front"));
            boolean isIDBackUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "ID back"));
            boolean isPictureWithIDUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Picture with ID"));
            String productDetails = SessionData.getDataTbVal(dataTable, row, "Product details");
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            boolean isDisputeHistoryUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Dispute history"));

            paymentsSettingSteps.clickToRegisterForSpayReseller();
            //Input Business details
            paymentsSettingSteps.selectValueForCountry(country, 1);
            paymentsSettingSteps.selectBusinessType(businessType);
            paymentsSettingSteps.inputValueForLegalBusinessName(legalBusinessName);
            paymentsSettingSteps.inputValueForIdentificationNumber(identificationNumber);
            paymentsSettingSteps.inputValueForAddress(address, 1);
            paymentsSettingSteps.inputValueForCity(city, 1);
            paymentsSettingSteps.inputValueForZipCode(zipCode, 1);
            paymentsSettingSteps.selectValueForState(state, 1);
            if (isBusinessRegistrationUpload) {
                paymentsSettingSteps.uploadFileForBusinessRegistration("SpayResellerBusinessRegistration.jpg");
            }
            paymentsSettingSteps.inputValueForFirstName(firstName);
            paymentsSettingSteps.inputValueForLastName(lastName);
            paymentsSettingSteps.inputValueForJobTitle(jobTitle);
            paymentsSettingSteps.inputValueForEmail(email);
            paymentsSettingSteps.inputValueForAddress(address, 2);
            paymentsSettingSteps.inputValueForCity(city, 2);
            paymentsSettingSteps.inputValueForZipCode(zipCode, 2);
            paymentsSettingSteps.selectValueForCountry(country, 2);
            paymentsSettingSteps.selectValueForState(state, 2);
            paymentsSettingSteps.inputValueForIDNumber(idNumber);
            if (isIDFrontUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("front", "SpayResellerIDFront.jpg");
            }
            if (isIDBackUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("back", "SpayResellerIDBack.jpg");
            }
            if (isPictureWithIDUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("picture", "PictureWithID.jpg");
            }
            paymentsSettingSteps.inputValueForProductDetails(productDetails);
            paymentsSettingSteps.inputPhoneNumber(phoneNumber);
            if (isDisputeHistoryUpload) {
                paymentsSettingSteps.uploadFileForDisputeHistory("DisputeHistory.csv");
            }
            paymentsSettingSteps.inputValueForStatement(firstName);
            paymentsSettingSteps.submitKYCFormSpayReseller();
        }
    }

    @Then("^verify status (New|Under Review|Info Needed|Approved|Deactivated|Rejected) of Spay Reseller account$")
    public void verifyStatusOfSpayResellerAccount(String status) {
        String actualFirstText = paymentsSettingSteps.getTextUnderSpayResellerButton(1);
        String actualSecondText = "";
        if (status.equalsIgnoreCase("New")) {
            actualSecondText = paymentsSettingSteps.getTextUnderSpayResellerButton(2);
        }
        String expectedFirstText = "";
        String expectedSecondText = "";
        switch (status) {
            case "New":
                expectedFirstText = "Accept credit cards through your online store at a rate starts from 3.95% + 30¢.";
                expectedSecondText = "Note that you can use the same ShopBase Payments account for more than one store.";
                break;
            case "Under Review":
                expectedFirstText = "Your ShopBase Payments account is under review. Kindly wait for our update once your account is approved.";
                break;
            case "Info Needed":
                expectedFirstText = "Please provide more information to start receiving transactions and payouts.";
                break;
            case "Approved":
                expectedFirstText = "Your ShopBase Payments account is successfully registered.";
                assertThat(paymentsSettingSteps.isEnableSpayResellerButtonDisplayed()).isTrue();
                break;
            case "Deactivated":
                expectedFirstText = "Your ShopBase Payments account is successfully registered.";
                break;
            case "Rejected":
                expectedFirstText = "Your account is rejected.";
                break;
        }
        System.out.println(status);
        System.out.println(actualFirstText);
        System.out.println(expectedFirstText);
        assertThat(actualFirstText).contains(expectedFirstText);
        if (status.equalsIgnoreCase("New")) {
            assertThat(actualSecondText).contains(expectedSecondText);
        }
    }


    @Then("verify Spay Reseller after activation")
    public void verifySpayResellerAfterActivation() {
        String transactionsStatus = paymentsSettingSteps.getShopbasePaymentAccountStatus("Transactions");
        String payoutStatus = paymentsSettingSteps.getShopbasePaymentAccountStatus("Payouts");
        assertThat(transactionsStatus).isEqualTo("Enabled");
        assertThat(payoutStatus).isEqualTo("Enabled");
//        paymentsSettingSteps.clickToExpandSpayResellerInformation();
    }


    @And("(Approve|Enable|Reject|Deactivate) Spay Reseller account$")
    public void changeStatusOfResellerAccount(String status) {
        if (accessToken.isEmpty()) {
            accessToken = paymentsSettingSteps.getAccessTokenShopBase();
        }
        String connectedAccountID = paymentsSettingSteps.getConnectedAccountIDByAPI(accessToken);
        paymentsSettingSteps.logInfor("connectedAccountID" + connectedAccountID);
        switch (status) {
            case "Approve": {
                paymentsSettingSteps.openSpayKYCListOnHive();
                paymentsSettingSteps.openKYCFormOnHive(connectedAccountID);
                paymentsSettingSteps.updateKYCFormHighLevelApproval();
                paymentsSettingSteps.approveKYCFormOnHive();
                break;
            }
            case "Enable": {
                paymentsSettingSteps.clickToSwitchToShopbasePayments();
                break;
            }
            case "Reject": {
                paymentsSettingSteps.openSpayKYCListOnHive();
                paymentsSettingSteps.openKYCFormOnHive(connectedAccountID);
                paymentsSettingSteps.rejectKYCFormOnHive();
                break;
            }
            case "Deactivate": {
                paymentsSettingSteps.clickToExpandSpayResellerInformation();
                paymentsSettingSteps.clickToDeactivateSpayReseller();
                paymentsSettingSteps.verifyNoticeMessage("Deactivated successfully");
                System.out.println("Deactivate successfully");
                reloadPage();
                break;
            }
        }
    }

    @Then("Verify error message {string} will be shown")
    public void verifyErrorMessageWillBeShown(String message) {
//        paymentsSettingSteps.verifyErrorToastMessageWillBeShown(message);
        paymentsSettingSteps.verifyLineOfErrorMessageWillBeShown(message);
    }

    @And("Verify status of {string} when back to Payment Providers page")
    public void verifyStatusOfMethodWhenBackToPaymentProvidersPage(String method) {
        paymentsSettingSteps.clickBtnCancel();
        paymentsSettingSteps.clickBackToPaymentProviders();
        paymentsSettingSteps.verifyStatusManualPaymentMethodActivate(method);
    }

    @Then("activate {string} gateway successfully in {string} mode and activate EU payment method")
    public void activateEUGateway(String gateway, String mode, List<List<String>> dataTable) {
        List<String> listOfAccountName = paymentsSettingSteps.getAccountName("Stripe-EU");
        int size = listOfAccountName.size();
        if (size > 0) {
            for (String accountNameToDelete : listOfAccountName) {
                paymentsSettingSteps.removeAccount(accountNameToDelete);
            }
        }
        if (!dataTable.isEmpty()) {
            submitCredentialsInfoUpdateUIUX(gateway, mode, false, dataTable);
        }
        paymentsSettingSteps.clickOnAccountName(accountName);
        paymentsSettingSteps.setToggleEUPaymentStatus(true);
        paymentsSettingSteps.clickbtnActivateMethod();
        paymentsSettingSteps.refreshPage();
    }

    @Given("check status Spay account then reset Spay V2 account if needed")
    public void checkStatusSpayAccountThenResetSpayVAccountIfNeeded() {
        String spayStatus = paymentsSettingSteps.checkSpayStatus();
        if (spayStatus.equalsIgnoreCase("Switch to ShopBase Payments"))
            paymentsSettingSteps.resetSpayResellerAccountByAPI();
        paymentsSettingSteps.refreshPage();
    }

    @And("approve Spay V2 account")
    public void approveSpayVAccount() {
        if (accessToken.isEmpty()) {
            accessToken = paymentsSettingSteps.getAccessTokenShopBase();
        }
        String connectedAccountID = paymentsSettingSteps.getConnectedAccountIDByAPI(accessToken);

        paymentsSettingSteps.openSpayKYCListOnHive();
        paymentsSettingSteps.openKYCFormOnHive(connectedAccountID);
        paymentsSettingSteps.approveKYCSpayV2();
    }

    @When("select {string} and submit KYC form to register for Spay Reseller")
    public void selectSMPAndSubmitKycForm(String SpayType, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            String legalBusinessName = SessionData.getDataTbVal(dataTable, row, "Legal business name");
            String identificationNumber = SessionData.getDataTbVal(dataTable, row, "Employer Identification Number");
            String address = SessionData.getDataTbVal(dataTable, row, "Address");
            String city = SessionData.getDataTbVal(dataTable, row, "City");
            String zipCode = SessionData.getDataTbVal(dataTable, row, "ZIP");
            String state = SessionData.getDataTbVal(dataTable, row, "State");

            boolean isBusinessRegistrationUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Business registration"));
            String firstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String jobTitle = SessionData.getDataTbVal(dataTable, row, "Job title");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String idNumber = SessionData.getDataTbVal(dataTable, row, "ID number");
            boolean isIDFrontUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "ID front"));
            boolean isIDBackUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "ID back"));
            boolean isPictureWithIDUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Picture with ID"));
            String productDetails = SessionData.getDataTbVal(dataTable, row, "Product details");
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            boolean isDisputeHistoryUpload = convertStatus(SessionData.getDataTbVal(dataTable, row, "Dispute history"));

            paymentsSettingSteps.clickOnRegisterForShopBasePaymentAccountBtn();
            paymentsSettingSteps.selectSpayType(SpayType);
            //Input Business details
            paymentsSettingSteps.selectValueForCountry(country, 1);
            paymentsSettingSteps.selectBusinessType(businessType);
            paymentsSettingSteps.inputValueForLegalBusinessName(legalBusinessName);
            paymentsSettingSteps.inputValueForIdentificationNumber(identificationNumber);
            paymentsSettingSteps.inputValueForAddress(address, 1);
            paymentsSettingSteps.inputValueForCity(city, 1);
            paymentsSettingSteps.inputValueForZipCode(zipCode, 1);
            paymentsSettingSteps.selectValueForState(state, 1);
            if (isBusinessRegistrationUpload) {
                paymentsSettingSteps.uploadFileForBusinessRegistration("SpayResellerBusinessRegistration.jpg");
            }
            paymentsSettingSteps.inputValueForFirstName(firstName);
            paymentsSettingSteps.inputValueForLastName(lastName);
            paymentsSettingSteps.inputValueForJobTitle(jobTitle);
            paymentsSettingSteps.inputValueForEmail(email);
            paymentsSettingSteps.inputValueForAddress(address, 2);
            paymentsSettingSteps.inputValueForCity(city, 2);
            paymentsSettingSteps.inputValueForZipCode(zipCode, 2);
            paymentsSettingSteps.selectValueForCountry(country, 2);
            paymentsSettingSteps.selectValueForState(state, 2);
            paymentsSettingSteps.inputValueForIDNumber(idNumber);
            if (isIDFrontUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("front", "SpayResellerIDFront.jpg");
            }
            if (isIDBackUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("back", "SpayResellerIDBack.jpg");
            }
            if (isPictureWithIDUpload) {
                paymentsSettingSteps.uploadFileForIDDocument("picture", "PictureWithID.jpg");
            }
            paymentsSettingSteps.inputValueForProductDetails(productDetails);
            paymentsSettingSteps.inputPhoneNumber(phoneNumber);
            if (isDisputeHistoryUpload) {
                paymentsSettingSteps.uploadFileForDisputeHistory("DisputeHistory.csv");
            }
            paymentsSettingSteps.inputValueForStatement(firstName);
            paymentsSettingSteps.selectCheckboxTermsAndConditions();
            paymentsSettingSteps.submitKYCFormSpayReseller();
        }
    }

    @When("select {string} and Submit KYC Spay V2 register form for Stripe")
    public void selectSpayTypeAndSubmitKyc(String SpayType, List<List<String>> dataTable) {
        String spayStatus = "";
        String country = "";
        String businessType = "";
        String businessName = "";
        String employerEIN = "";
        String businessAddress = "";
        String businessCity = "";
        String businessZipCode = "";
        String businessState = "";
        String personalFirstName = "";
        String personalLastName = "";
        String personalJobTitle = "";
        String email = "", idNumber = "";
        String personalLast4DigitSSN = "";
        String statementDescriptor = "";
        String phoneNum = "";
        String routingNum = "";
        String accountNum = "";
        boolean idDocument = true;


        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            country = SessionData.getDataTbVal(dataTable, row, "Country");
            businessType = SessionData.getDataTbVal(dataTable, row, "Business type");
            businessName = SessionData.getDataTbVal(dataTable, row, "Legal business name");
            employerEIN = SessionData.getDataTbVal(dataTable, row, "Employer EIN/SSN");
            businessAddress = SessionData.getDataTbVal(dataTable, row, "Address");
            businessCity = SessionData.getDataTbVal(dataTable, row, "City");
            businessZipCode = SessionData.getDataTbVal(dataTable, row, "ZIP Code");
            businessState = SessionData.getDataTbVal(dataTable, row, "State");
            idDocument = convertStatus(SessionData.getDataTbVal(dataTable, row, "ID document"));
            personalFirstName = SessionData.getDataTbVal(dataTable, row, "First Name");
            personalLastName = SessionData.getDataTbVal(dataTable, row, "Last Name");
            personalJobTitle = SessionData.getDataTbVal(dataTable, row, "Job title");
            email = SessionData.getDataTbVal(dataTable, row, "Email");
            personalLast4DigitSSN = SessionData.getDataTbVal(dataTable, row, "Last 4 SSN");
            idNumber = SessionData.getDataTbVal(dataTable, row, "ID number");
            statementDescriptor = SessionData.getDataTbVal(dataTable, row, "Statement Descriptor");
            phoneNum = SessionData.getDataTbVal(dataTable, row, "Phone number");
            routingNum = SessionData.getDataTbVal(dataTable, row, "Routing number");
            accountNum = SessionData.getDataTbVal(dataTable, row, "Account number");
        }

        spayStatus = paymentsSettingSteps.checkSpayStatus();

        switch (spayStatus) {
            case ("Register for Shopbase Payments account"): {
                paymentsSettingSteps.clickOnRegisterForShopBasePaymentAccountBtn();
                paymentsSettingSteps.selectSpayType(SpayType);
                //Business detail section
                paymentsSettingSteps.selectValueForCountry(country, 1);
                paymentsSettingSteps.selectBusinessType(businessType);
                if (!businessType.equals("Individual/sole proprietorship")) {
                    paymentsSettingSteps.inputValueForLegalBusinessName(businessName);
                    paymentsSettingSteps.inputValueForIdentificationNumber(employerEIN);
                }

                paymentsSettingSteps.inputValueForAddress(businessAddress, 1);
                paymentsSettingSteps.inputValueForCity(businessCity, 1);
                paymentsSettingSteps.inputValueForZipCode(businessZipCode, 1);
                paymentsSettingSteps.selectValueForState(businessState, 1);
                paymentsSettingSteps.inputValueForIDNumberForSpayV2(idNumber);

                //Personal details section

                paymentsSettingSteps.inputValueForFirstName(personalFirstName);
                paymentsSettingSteps.inputValueForLastName(personalLastName);
                paymentsSettingSteps.inputValueForJobTitle(personalJobTitle);
                paymentsSettingSteps.inputValueForEmail(email);
                paymentsSettingSteps.inputValueForAddress(businessAddress, 2);
                paymentsSettingSteps.inputValueForCity(businessCity, 2);
                paymentsSettingSteps.inputValueForZipCode(businessZipCode, 2);
                paymentsSettingSteps.selectValueForCountry(country, 2);
                paymentsSettingSteps.selectValueForState(businessState, 2);
                paymentsSettingSteps.inputPersonalSSN(personalLast4DigitSSN);
                if (idDocument) {
                    paymentsSettingSteps.uploadFileForIDDocument("picture", "SpayResellerIDFront.jpg");
                }
                //Customer billing statement section
                paymentsSettingSteps.inputStatementDescriptor(statementDescriptor);
                paymentsSettingSteps.inputCustomerBillingStatementPhoneNum(phoneNum);
                paymentsSettingSteps.inputBaningInfoRoutingNum(routingNum);
                paymentsSettingSteps.inputBaningInfoAccountNum(accountNum);
                paymentsSettingSteps.selectCheckboxTermsAndConditions();
                break;
            }
            case ("Complete account setup"): {
                paymentsSettingSteps.clickOnCompleteAccountSetupBtn();
                paymentsSettingSteps.fillRequiredFieldStripeRequested();
                break;
            }
            case ("Verify account information"): {
                paymentsSettingSteps.clickOnVerifyAccountInformation();
                paymentsSettingSteps.fillRequiredFieldStripeRequested();
                break;
            }
        }
        //click on Complete Account Setup in KYC form
        paymentsSettingSteps.clickOnCompleteAccountSetupBtn();
    }
}



