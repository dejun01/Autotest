package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.common.pages.CommonPage;
import com.opencommerce.shopbase.dashboard.online_store.preferences.pages.PreferencesPage;
import common.CommonPageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.dashboard.settings.pages.PaymentsSettingPages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsSettingSteps extends ScenarioSteps {
    PaymentsSettingPages paymentSettingPages;
    CommonPage commonPage;
    CommonPageObject commonPageObject;
    PreferencesPage preferencesPage;

    @Step
    public void clickOnRegisterForShopBasePaymentAccountBtn() {
        paymentSettingPages.clickOnRegisterForShopBasePaymentAccountBtn();
    }

    @Step
    public void clickOnCompleteAccountSetupBtn() {
        paymentSettingPages.clickOnCompleteAccountSetupBtn();
    }

    @Step
    public void selectBusinessType(String businessType) {
        if (!businessType.isEmpty())
            paymentSettingPages.selectBusinessType(businessType);
    }

    @Step
    public void inputLegalBusinessName(String businessName) {
        paymentSettingPages.inputLegalBusinessName(businessName);
    }

    @Step
    public void inputEmployerIdentificationNumber(String businessType, String id) {
        paymentSettingPages.inputEmployerIdentificationNumber(businessType, id);
    }

    @Step
    public void inputBusinessAddress(String address) {
        paymentSettingPages.inputBusinessAddress(address);
    }

    @Step
    public void inputBusinessCity(String city) {
        paymentSettingPages.inputBusinessCity(city);
    }

    @Step
    public void inputBusinessZipCode(String zipCode) {
        paymentSettingPages.inputBusinessZipCode(zipCode);
    }

    @Step
    public void selectBusinessState(String state) {
        paymentSettingPages.selectBusinessState(state);
    }

    @Step
    public void inputPersonalFirstName(String firstName) {
        paymentSettingPages.inputPersonalFirstName(firstName);
    }

    @Step
    public void inputPersonalLastName(String lastName) {
        paymentSettingPages.inputPersonalLastName(lastName);
    }

    @Step
    public void inputPersonalDOB(String dob) {
        paymentSettingPages.inputPersonalDOB(dob);
    }

    @Step
    public void inputPersonalAddress(String address) {
        paymentSettingPages.inputPersonalAddress(address);
    }

    @Step
    public void inputPersonalCity(String city) {
        paymentSettingPages.inputPersonalCity(city);
    }

    @Step
    public void inputPersonalZipCode(String zipCode) {
        paymentSettingPages.inputPersonalZipCode(zipCode);
    }

    @Step
    public void inputPersonalState(String state) {
        paymentSettingPages.inputPersonalState(state);
    }

    @Step
    public void inputPersonalSSN(String ssn) {
//        paymentSettingPages.inputPersonalSSN(ssn);
        if (!ssn.isEmpty())
            paymentSettingPages.enterInputFieldWithLabel("Last 4 digits of your Social Security number (SSN)", ssn);
    }

    @Step
    public void inputStatementDescriptor(String descriptor) {
        paymentSettingPages.inputStatementDescriptor(descriptor);
    }

    @Step
    public void inputCustomerBillingStatementPhoneNum(String phoneNum) {
        paymentSettingPages.inputCustomerBillingStatementPhoneNum(phoneNum);
    }

    @Step
    public void inputBaningInfoRoutingNum(String routingNum) {
        paymentSettingPages.inputBaningInfoRoutingNum(routingNum);
    }

    @Step
    public void inputBaningInfoAccountNum(String phoneNum) {
        paymentSettingPages.inputBaningInfoAccountNum(phoneNum);
    }

    @Step
    public void selectPayoutCurrency(String currency) {
        paymentSettingPages.inputPayoutCurrency(currency);
    }

    @Step
    public void verifyMessageAfterClickONCompleteAccountSetup() {
        paymentSettingPages.verifyMessageAfterClickONCompleteAccountSetup();
    }

    @Step
    public void clickOnBreadcrumb(String text) {
        paymentSettingPages.clickOnBreadcrumb(text);
    }

    @Step
    public void verifyDisplayOfVerifyAccountInformationBtn() {
        paymentSettingPages.verifyDisplayOfVerifyAccountInformationBtn();
    }

    @Step
    public void verifyDisplayOfMessageAfterSubmitKYCRegisterFormInPayments() {
        paymentSettingPages.verifyDisplayOfMessageAfterSubmitKYCRegisterFormInPayments();
    }

    @Step
    public void refreshPage() {
        paymentSettingPages.refreshPage();
    }

    @Step
    public void checkEmailSentAfterRegisterApproving() throws InterruptedException {
        paymentSettingPages.checkEmailSentAfterRegisterApproving();
    }

    @Step
    public void verifyDisplayOfSwitchToShopBasePaymentsBtn() {
        paymentSettingPages.verifyDisplayOfSwitchToShopBasePaymentsBtn();
    }

    @Step
    public void clickOnSwitchToShopBasePaymentsBtn() {
        paymentSettingPages.clickOnSwitchToShopBasePaymentsBtn();
    }

    @Step
    public void verifyDisplayOfShopBasePaymentLogo() {
        paymentSettingPages.verifyDisplayOfShopBasePaymentLogo();
    }

    @Step
    public void verifyDisplayOfTransactionStatus() {
        paymentSettingPages.verifyDisplayOfTransactionStatus();
    }

    @Step
    public void verifyDisplayOfPayoutsStatus() {
        paymentSettingPages.verifyDisplayOfPayoutsStatus();
    }

    @Step
    public void addOrderDetailsToCSV(String fileName, List<String> listOrder) {
        paymentSettingPages.addOrderDetailsToCSV(fileName, listOrder);
    }

    @Step
    public ArrayList<String> readingOrderInfo(String filePath) throws IOException {
        ArrayList<String> orderInfo;
        orderInfo = paymentSettingPages.readingOrderInfo(filePath);
        return orderInfo;
    }

    @Step
    public void clearCsv(String filePath) throws IOException {
        paymentSettingPages.clearCsv(filePath);
    }

    @Step
    public void clickOnSelectProviderDropDown() {
        paymentSettingPages.clickOnSelectProviderDropDown();
    }

    @Step
    public void selectPaymentGateway(String paymentGateway) {
        paymentSettingPages.selectPaymentGateway(paymentGateway);
    }

    @Step
    public void selectPaymentProvider(String paymentProvider) {
        paymentSettingPages.selectPaymentProvider(paymentProvider);
        paymentSettingPages.waitForPageTitleToPresent(paymentProvider);
    }

    @Step
    public void clickCheckboxUseAPI() {
        paymentSettingPages.clickCheckboxUseAPI();
    }

    @Step
    public void clickCheckboxUseAPIUpdateUIUX() {
        paymentSettingPages.checkCheckboxWithLabel("Use API Keys to connect");
    }

    @Step
    public void inputCredentialsByLabel(String accountName, String label, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, label, value);
    }


    @Step
    public void verifyNoticeMessage(String message) {
        paymentSettingPages.verifyNoticeMessage(message);
    }

    @Step
    public void verifyErrorMessage(String message) {
        paymentSettingPages.verifyErrorMessage(message);
    }

    @Step
    public void verifyAccountName(String accountName) {
        paymentSettingPages.verifyAccountName(accountName);
    }

    @Step
    public void verifyGatewayUsed(String accountName, String gateway) {
        paymentSettingPages.verifyGatewayUsed(accountName, gateway);
    }

    @Step
    public void verifyGatewayUsedUpdateUIUX(String accountName, String gateway) {
        if (!"PayPal".equalsIgnoreCase(gateway)) {
            paymentSettingPages.verifyGatewayUsedUpdateUIUX(accountName, gateway);
        } else {
            paymentSettingPages.verifyGatewayUsedUpdateUIUX(accountName, gateway + " Accounts");
        }

    }

    @Step
    public String getInfoOfPasswordFieldByLabelOfAccount(String accountName, String label) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, label);
    }

    @Step
    public void expandGatewayEditingForm(String accountName) {
        paymentSettingPages.expandGatewayEditingForm(accountName);
    }

    @Step
    public void expandGatewayEditingFormUpdateUIUX(String accountName, String gateway) {
        paymentSettingPages.expandGatewayEditingFormUpdateUIUX(accountName, gateway);
    }

    @Step
    public void verifyStatusOfAccount(String accountName, String expectedStatus) {
        paymentSettingPages.waitForEverythingComplete();
        paymentSettingPages.verifyStatusOfAccount(accountName, expectedStatus);
    }

//    @Step
//    public void verifyStatusOfAccountUpdateUIUX(String accountName, String gateway, String expectedStatus) {
//        paymentSettingPages.verifyStatusOfAccountUpdateUIUX(accountName, gateway, expectedStatus);
//    }
//
//    @Step
//    public void verifyStatusOfPayPalAccountUpdateUIUX(String accountName, String expectedStatus) {
//        paymentSettingPages.verifyStatusOfPayPalAccountUpdateUIUX(accountName, expectedStatus);
//    }

    @Step
    public void clickOnAccountName(String accountName) {
        paymentSettingPages.clickOnAccountName(accountName);
    }

    @Step
    public void removeAccount(String accountName) {
        clickOnAccountName(accountName);
        clickOnRemoveAccountBtn(accountName);
        verifyDisplayOfRemoveConfirmPopup();
        clickOnRemoveBtnInPopup();
    }

    @Step
    public void verifyAccountNameAfterRemoving(String accountName) {
        paymentSettingPages.verifyAccountNameAfterRemoving(accountName);
    }

    @Step
    public void verifyAccountAfterRemovingUpdateUIUX(String accountName, String gateway) {
        paymentSettingPages.verifyAccountAfterRemovingUpdateUIUX(accountName, gateway);
    }

    @Step
    public void clickOnRemoveBtnInPopup() {
        paymentSettingPages.clickOnRemoveBtnInPopup();
    }

    @Step
    public void clickOnCancelBtnInPopup() {
        paymentSettingPages.clickOnCancelBtnInPopup();
    }

    @Step
    public void verifyDisplayOfRemoveConfirmPopup() {
        paymentSettingPages.verifyDisplayOfRemoveConfirmPopup();
    }

    //------------- Third Party section --------------
    @Step
    public void clickOnAddAnotherAccountBtnInThirdPartySection() {
        paymentSettingPages.clickOnAddAnotherAccountBtn("Account list");
    }

    @Step
    public void waitForPaymentProviderConfigureScreen(String paymentProvider) {
        paymentSettingPages.waitForPageTitleToPresent(paymentProvider);
    }

    @Step
    public void clickToChooseThirdPartyProvider() {
        paymentSettingPages.clickOnBtnOfEachBlock("Choose third-party provider");
        paymentSettingPages.waitForPageTitleToPresent("Third-party payment providers");
    }

    @Step
    public void navigateToPaymentProvidersMainPage(String gateway) {
        if (!"PayPal".equalsIgnoreCase(gateway)) {
            paymentSettingPages.clickLinkTextWithLabel("Third-party payment providers");
        }
        paymentSettingPages.clickLinkTextWithLabel("Payment providers");
    }

    @Step
    public void setTestModeToggleStatusInThirdPartySection(boolean status) {
        paymentSettingPages.setToggleStatus("Test mode", "Add another account", status);
    }

    @Step
    public void setTestModeToggleStatusUpdateUIUX(boolean status) {
        paymentSettingPages.setToggleStatusUpdateUIUX("Test mode", status);
    }

    @Step
    public void set3DSToggleStatusInThirdPartySection(boolean status) {
        paymentSettingPages.setToggleStatus("3D secure payment", "Add another account", status);
    }

    @Step
    public void set3DSToggleStatusUpdateUIUX(boolean status) {
        paymentSettingPages.setToggleStatusUpdateUIUX("3D secure payment", status);
    }

    @Step
    public void clickOnActivateBtnInThirdPartySection() {
        paymentSettingPages.clickBtnInSection("Account list", "Activate");
    }

    @Step
    public void clickOnActivateBtnUpdateUIUX(boolean isValidation) {
        paymentSettingPages.clickBtnUpdateUIUX("Add account", isValidation);
    }

    @Step
    public void inputAccountNameInThirdPartySection(String accountName) {
        paymentSettingPages.inputAccountNameInSection("Account list", accountName);
    }

    @Step
    public void inputAccountNameUpdateUIUX(String accountName) {
        paymentSettingPages.inputAccountNameUpdateUIUX(accountName);
    }

    @Step
    public void verifyButtonIsDisabledInThirdPartySection(String btnName) {
        paymentSettingPages.verifyButtonIsDisabled("Account list", "", btnName);
    }

    //------------- PayPal section --------------
    @Step
    public void clickOnAddAnotherAccountBtnInPayPalSection() {
        paymentSettingPages.clickOnAddAnotherAccountBtn("PayPal credentials");
    }


    @Step
    public void clickToChooseActivePayPalWithAPIKey() {
        paymentSettingPages.clickOnBtnOfEachBlock("Activate with API key");
        //   paymentSettingPages.clickBtn("Active with API key");
        paymentSettingPages.waitForPageTitleToPresent("PayPal");
    }

    @Step
    public void clickToChooseActiveByLoginWithPayPal() {
        paymentSettingPages.clickBtnLoginWithPayPal();
        paymentSettingPages.waitForPageTitleToPresent("PayPal");
    }

    @Step
    public void switchToPaypalLoginPage() {
        paymentSettingPages.switchToPaypalLoginPage();
        paymentSettingPages.waitForPageLoad();
    }

    @Step
    public void clickOnActivateBtnInPayPalSection() {
        paymentSettingPages.clickBtnInSection("PayPal credentials", "Activate");
    }

    @Step
    public void setTestModeToggleStatusInPayPalSection(boolean status) {
        paymentSettingPages.setToggleStatus("Test mode", "PayPal Add another account", status);
    }

    @Step
    public void inputAccountNameInPayPalSection(String accountName) {
        paymentSettingPages.inputAccountNameInSection("PayPal credentials", accountName);
    }

    @Step
    public void verifyButtonIsDisabledInPayPalSection(String btnName) {
        paymentSettingPages.verifyButtonIsDisabled("PayPal credentials", "", btnName);
    }


    //------------- Account section --------------
    @Step
    public void clickOnSaveBtn(String accountName) {
        paymentSettingPages.clickOnBtnInSectionOfAccount(accountName, "Save");
    }

    @Step
    public void clickOnActivateBtn(String accountName) {
        paymentSettingPages.clickOnBtnInSectionOfAccount(accountName, "Activate");
    }

    @Step
    public void clickOnDeactivateBtn(String accountName) {
        paymentSettingPages.clickOnBtnInSectionOfAccount(accountName, "Deactivate");
    }

    @Step
    public void clickOnRemoveAccountBtn(String accountName) {
        paymentSettingPages.clickOnBtnInSectionOfAccount(accountName, "Remove account");
    }

    //------------- Activating form --------------
    @Step
    public void inputPublicKey(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Public Key", value);
    }

    @Step
    public void inputPublicKeyUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Public Key", value);
    }

    @Step
    public void inputPublicKeyUpdateUIUX(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX(accountName, "Public Key", value);
    }

    @Step
    public void inputSecretKey(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Secret Key", value);
    }

    @Step
    public void inputSecretKeyUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Secret Key", value);
    }

    @Step
    public void inputSecretKeyUpdateUIUX(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX(accountName, "Secret Key", value);
    }

    @Step
    public void inputPublicKey(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Public Key", value);
    }

    @Step
    public void inputSecretKey(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Secret Key", value);
    }

    @Step
    public void inputStatementDescriptor(String accountName, String value) {
        paymentSettingPages.inputBillingStatement(accountName, "Statement descriptor", value);
    }

    @Step
    public void inputClientID(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Client ID", value);
    }

    @Step
    public void inputClientIDUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Client ID", value);
    }

    @Step
    public void inputClientSecretKey(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Client Secret Key", value);
    }

    @Step
    public void inputClientSecretKeyUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Client Secret Key", value);
    }

    @Step
    public void inputClientID(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Client ID", value);
    }

    @Step
    public void inputClientSecretKey(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Client Secret Key", value);
    }

    @Step
    public void verifyButtonIsDisabledInAccountSection(String accountName, String btnName) {
        paymentSettingPages.verifyButtonIsDisabled("", accountName, btnName);
    }

    //---inputPayPalManager old version--
    @Step
    public void inputPayPalManagerPartner(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "PayPal Manager Partner", value);
    }

    @Step
    public void inputPayPalManagerVendor(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "PayPal Manager Vendor", value);
    }

    @Step
    public void inputPayPalManagerUser(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "PayPal Manager User", value);
    }

    @Step
    public void inputPayPalManagerPassword(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "PayPal Manager Password", value);
    }

    //---inputPayPalManager UpdateUIUX--
    @Step
    public void inputPayPalManagerPartnerUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "PayPal Manager Partner", value);
    }

    @Step
    public void inputPayPalManagerVendorUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "PayPal Manager Vendor", value);
    }

    @Step
    public void inputPayPalManagerUserUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "PayPal Manager User", value);
    }

    @Step
    public void inputPayPalManagerPasswordUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "PayPal Manager Password", value);
    }

    //-----------

    @Step
    public void inputPayPalManagerPartner(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "PayPal Manager Partner", value);
    }

    @Step
    public void inputPayPalManagerVendor(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "PayPal Manager Vendor", value);
    }

    @Step
    public void inputPayPalManagerUser(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "PayPal Manager User", value);
    }

    @Step
    public void inputPayPalManagerPassword(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "PayPal Manager Password", value);
    }

    @Step
    public void inputCardinalAPIID(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Cardinal API ID", value);
    }

    @Step
    public void inputCardinalAPIIDUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Cardinal API ID", value);
    }

    @Step
    public void inputCardinalAPIID(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Cardinal API ID", value);
    }

    @Step
    public void inputCardinalAPIKey(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Cardinal API Key", value);
    }

    @Step
    public void inputCardinalAPIKeyUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Cardinal API Key", value);
    }

    @Step
    public void inputCardinalAPIKey(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Cardinal API Key", value);
    }

    @Step
    public void inputCardinalOrgUnitID(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Cardinal Org Unit ID", value);
    }

    @Step
    public void inputCardinalOrgUnitIDUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Cardinal Org Unit ID", value);
    }

    @Step
    public void inputCardinalOrgUnitID(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Cardinal Org Unit ID", value);
    }

    @Step
    public void inputMerchantID(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Merchant ID", value);
    }

    @Step
    public void inputMerchantIDUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Merchant ID", value);
    }

    @Step
    public void inputMerchantID(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Merchant ID", value);
    }

    @Step
    public void inputPrivateKey(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Private Key", value);
    }

    @Step
    public void inputPrivateKeyUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Private Key", value);
    }

    @Step
    public void inputPrivateKey(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Private Key", value);
    }

    @Step
    public void inputPhoneNumber(String value) {
        if (!value.isEmpty())
            paymentSettingPages.inputCredentialsByLabel("", "Phone number", value);
    }

    @Step
    public void inputPhoneNumber(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Phone number", value);
    }

    @Step
    public void inputTerminalCode(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Terminal Code", value);
    }

    @Step
    public void inputTerminalCodeUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Terminal Code", value);
    }

    @Step
    public void inputTerminalCode(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Terminal Code", value);
    }

    @Step
    public void inputTerminalPassword(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Terminal Password", value);
    }

    @Step
    public void inputTerminalPasswordUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Terminal Password", value);
    }

    @Step
    public void inputTerminalPassword(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Terminal Password", value);
    }

    @Step
    public void inputCallbackSecret(String value) {
        paymentSettingPages.inputCredentialsByLabel("", "Callback Secret", value);
    }

    @Step
    public void inputCallbackSecretUpdateUIUX(String value) {
        paymentSettingPages.inputCredentialsByLabelUpdateUIUX("", "Callback Secret", value);
    }

    @Step
    public void inputCallbackSecret(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "Callback Secret", value);
    }

    @Step
    public void inputCityName(String accountName, String value) {
        paymentSettingPages.inputCredentialsByLabel(accountName, "City name", value);
    }

    @Step
    public void set3DSToggleStatusInAccountSection(String account, boolean status) {
        paymentSettingPages.setToggleStatus("3D secure payment", account, status);
    }

    //-------------- Get inputted credentials info --------------
    @Step
    public String getPublicKey(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Public Key");
    }

    @Step
    public String getSecretKey(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Secret Key");
    }

    @Step
    public String getPrivateKey(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Private Key");
    }

    @Step
    public String getClientID(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Client ID");
    }

    @Step
    public String getClientSecretKey(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Client Secret Key");
    }

    @Step
    public String getPayPalManagerPartner(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "PayPal Manager Partner");
    }

    @Step
    public String getPayPalManagerVendor(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "PayPal Manager Vendor");
    }

    @Step
    public String getPayPalManagerUser(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "PayPal Manager User");
    }

    @Step
    public String getPayPalManagerPassword(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "PayPal Manager Password");
    }

    @Step
    public String getCardinalAPIID(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Cardinal API ID");
    }

    @Step
    public String getCardinalAPIKey(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Cardinal API Key");
    }

    @Step
    public String getCardinalOrgUnitID(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Cardinal Org Unit ID");
    }

    @Step
    public String getMerchantID(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Merchant ID");
    }

    @Step
    public String getTerminalCode(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Terminal Code");
    }

    @Step
    public String getTerminalPassword(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Terminal Password");
    }

    @Step
    public String getCallbackSecret(String accountName) {
        return paymentSettingPages.getInfoOfPasswordFieldByLabelOfAccount(accountName, "Callback Secret");
    }

    @Step
    public String getStatementDescriptor(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Statement descriptor");
    }

    @Step
    public String getStatementPhoneNumber(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "Phone number");
    }

    @Step
    public String getCityName(String accountName) {
        return paymentSettingPages.getInfoByLabelOfAccount(accountName, "City name");
    }

    //Verify display of 3DS fields
    @Step
    public void verifyDisplayOfCardinalAPIID(String accountName, boolean status) {
        paymentSettingPages.verifyDisplayOfFields(accountName, "Cardinal API ID", status);
    }

    @Step
    public void verifyDisplayOfCardinalAPIKey(String accountName, boolean status) {
        paymentSettingPages.verifyDisplayOfFields(accountName, "Cardinal API Key", status);
    }

    @Step
    public void verifyDisplayOfCardinalOrgUnitID(String accountName, boolean status) {
        paymentSettingPages.verifyDisplayOfFields(accountName, "Cardinal Org Unit ID", status);
    }

    //Get account list
    @Step
    public List<String> getAccountName(String accountName) {
        return paymentSettingPages.getAccountName(accountName);
    }

    @Step
    public void verifyPaymentRotationCreditCard(HashMap<String, List<Integer>> listOfPaymentId, List<Integer> listOfUsedPaymentId) {
        paymentSettingPages.verifyPaymentRotation("credit-card", listOfPaymentId, listOfUsedPaymentId);
    }

    @Step
    public void verifyPaymentRotationPayPal(HashMap<String, List<Integer>> listOfPaymentId, List<Integer> listOfUsedPaymentId) {
        paymentSettingPages.verifyPaymentRotation("paypal-express", listOfPaymentId, listOfUsedPaymentId);
    }

    @Step
    public void verifyHeader() {
        paymentSettingPages.verifyHeader();
    }

    @Step
    public void verifyStatement() {
        paymentSettingPages.verifyStatement();
    }

    @Step
    public void verifyAcceptCreditCard() {
        paymentSettingPages.verifyAcceptCreditCard();
    }

    @Step
    public void verifyShopbasePayment() {
        paymentSettingPages.verifyShopbasePayment();
    }

    @Step
    public void verifyPaypal() {
        paymentSettingPages.verifyPaypal();
    }

    @Step
    public void loginToActivatePayPalPaymentProvider(String username, String pwd) {
//        paymentSettingPages.clickBtnLoginWithPayPal();
//        paymentSettingPages.clickBtnContinue();
//        paymentSettingPages.waitForPageLoad();
//        waitABit(3000);
//        switchWindowAndGetTitle();
//        paymentSettingPages.maximizeWindow();
//        paymentSettingPages.clickOnAcceptCookieBtn();
        paymentSettingPages.enterTheField("email", username);
//        paymentSettingPages.enterInputFieldWithLabel("email", username);
        paymentSettingPages.clickBtnNextOnPayPalPage();
//        paymentSettingPages.enterInputFieldWithLabel("password", pwd);
        paymentSettingPages.enterTheField("password", pwd);
        System.out.println("pwd " + pwd);
        if (paymentSettingPages.checkLoginBtnDisplay()) {
            paymentSettingPages.clickOnLogin();
        }
        paymentSettingPages.clickToGoBackFromPayPalLoginPage();
        paymentSettingPages.switchToWindowWithIndex(0);
    }

    @Step
    public void navigateToSignInOrSigUpPayPal() {
        paymentSettingPages.clickBtnLoginWithPayPal();
        paymentSettingPages.clickBtnContinue();
        paymentSettingPages.waitForPageLoad();
        waitABit(3000);
        paymentSettingPages.switchToPaypalLoginPage();
        paymentSettingPages.maximizeWindow();
        paymentSettingPages.clickOnAcceptCookieBtn();
    }

    @Step
    public void enterAccount(String username, String pwd) {
        System.out.println("html body = " + paymentSettingPages.executeJavaScript("document.documentElement.outerHTML"));
        paymentSettingPages.enterEmail(username);
        paymentSettingPages.selectCounrty("Vietnam");
        paymentSettingPages.clickBtnNextOnPayPalPage();
        paymentSettingPages.enterPassword(pwd);
        if (paymentSettingPages.checkLoginBtnDisplay()) {
            paymentSettingPages.clickOnLogin();
        }
        paymentSettingPages.clickToGoBackFromPayPalLoginPage();
        paymentSettingPages.switchToWindowWithIndex(0);
    }

    @Step
    public String switchWindowAndGetTitle() {
        System.out.println("window title = " + paymentSettingPages.getTitle());
        System.out.println("url PayPal = " + paymentSettingPages.getCurrentUrl());
        return paymentSettingPages.getCurrentUrl();
    }

    @Step
    public String getConnectedAccountName() {
        return paymentSettingPages.getConnectedAccountEmail();
    }


//    -----COD-----

    @Step
    public void inputAdditionalDetails(String data) {
        paymentSettingPages.waitForPageLoad();
        paymentSettingPages.inputAdditionalDetails(data);
    }

    @Step
    public void verifyAdditional(String data) {
        paymentSettingPages.verifyTextInAdditionalDetails(data);
    }

    @Step
    public void goToAManualPaymentMethodPage(String manualPM) {
        paymentSettingPages.selectAManualPaymentMethod(manualPM);
    }

    @Step
    public void verifyStatusManualPaymentMethodActivate(String manualPM) {
        paymentSettingPages.verifyManualPaymentMethodStatus(manualPM);
    }

    public void clickBtnActivemanualPM(String manualPM) {
        paymentSettingPages.clickBtn("Active " + manualPM);
    }

    public void clickBtnSaveChanges() {
        paymentSettingPages.clickBtn("Save changes");
    }

    public void clickToDeactiveManualPaymentMethod(String manualPM) {
        paymentSettingPages.clickToDeactiveManualPaymentMethod(manualPM);
    }

    public void clickBtnEditActiveCOD() {
        paymentSettingPages.waitForPageLoad();
        paymentSettingPages.clickBtn("Edit");
    }

    public void verifyLineOfErrorMessageWillBeShown(String message) {
        paymentSettingPages.verifyLineOfErrorMessageWillBeShown(message);
    }

    public void chooseGateway(String gateway) {
        paymentSettingPages.clickOnGateway(gateway);
    }

    public void clickbtnChooseAlternativeProvider() {
        paymentSettingPages.clickBtn("Choose alternative provider");
    }

    public void inputAccountNameInOceanPaymentSection(String accName) {
        paymentSettingPages.enterInputFieldWithLabel("* Account Name", accName);
    }

    public void inputAccountInPaymentSection(String account) {
        paymentSettingPages.enterInputFieldWithLabel("* Acount", account);
    }

    public void inputTerminalInPaymentSection(String terminal) {
        paymentSettingPages.enterInputFieldWithLabel("* Terminal", terminal);
    }

    public void inputSecureCodeInPaymentSection(String secureCode) {
        paymentSettingPages.enterInputFieldWithLabel("* Secure code", secureCode);
    }

    public void clickBtnAddAccount() {
        paymentSettingPages.clickBtn("Add account");
    }

    public void verifyAddGatewaySuccessfully(String gateway) {
        paymentSettingPages.verifyGatewayInList(gateway, true);
    }

    public void clickGatewayInlist(String gateway) {
        paymentSettingPages.clickGatewayInlist(gateway);

    }

    public Boolean getStatusGateway(String gateway) {
        return paymentSettingPages.getStatusGateway(gateway);
    }

    public void clickbtnDeactivate(String gateway) {
        paymentSettingPages.clickbtnDeactivate(gateway);
    }

    public void verifyDeactiveSuccessfully() {
        paymentSettingPages.verifyMsgDeactivedSuccessfully();
        paymentSettingPages.verifyStatusGateway("OceanPayment", false);
    }

    public void clickbtnActivate(String gateway) {
        paymentSettingPages.clickbtnActivate(gateway);
    }

    public void verifyActiveSuccessfully() {
        paymentSettingPages.verifyMsgActivedSuccessfully();
        paymentSettingPages.verifyStatusGateway("OceanPayment", true);
    }

    public List<String> getAccountList() {
        return paymentSettingPages.getAccountList();
    }

    public void clickBtnRemoveAccount(String account) {
        paymentSettingPages.clickBtnRemoveAccount(account);
    }

    public void clickbtnRemoveOnConfirmPopup() {
        paymentSettingPages.clickBtn("Remove");
    }

    public void verifyRemoveAccountSuccessfully() {
        paymentSettingPages.verifyRemoveAccountSuccessfully();
    }

    @Step
    public void verifyTheMsgPresent(String text) {
        paymentSettingPages.verifyTextPresent(text, true);
    }


    //    -----SPay Reseller-----

    @Step
    public boolean isSpayResellerButtonClickable() {
        return paymentSettingPages.isClickableBtn("Register for Shopbase Payments account");
    }

    @Step
    public void clickToRegisterForSpayReseller() {
        paymentSettingPages.clickBtn("Register for Shopbase Payments account");
        paymentSettingPages.waitForPageLoad();
    }

    @Step
    public void clickToSwitchToShopbasePayments() {
        paymentSettingPages.clickBtn("Switch to ShopBase Payments");
        paymentSettingPages.waitForPageLoad();
    }

    @Step
    public void selectValueForCountry(String country, int index) {
        selectDdOfAddress("Country", country, index);
    }

    @Step
    public void selectValueForState(String state, int index) {
        if (!state.isEmpty()) {
            selectDdOfAddress("State", state, index);
        }
    }

    @Step
    public void selectDdOfAddress(String label, String value, int index) {
        String xPath = paymentSettingPages.getXpathForAddressDd(label, index);
        paymentSettingPages.selectDdlByXpath(xPath, value);
    }

    @Step
    public void inputValueForLegalBusinessName(String legalBusinessName) {
        if (!legalBusinessName.isEmpty())
            paymentSettingPages.enterInputFieldWithLabel("Legal business name", legalBusinessName);
    }

    @Step
    public void inputValueForIdentificationNumber(String identificationNumber) {
        if (!identificationNumber.isEmpty())
            paymentSettingPages.enterInputFieldWithLabel("Employer Identification Number (EIN)", identificationNumber);
    }

    @Step
    public void inputValueForAddress(String address, int index) {
        paymentSettingPages.inputValueByPlaceHolder("1592 Hillhaven Drive", address, index);
    }

    @Step
    public void inputValueForCity(String city, int index) {
        paymentSettingPages.inputValueByPlaceHolder("Hollywood", city, index);
    }

    @Step
    public void inputValueForZipCode(String zipCode, int index) {
        paymentSettingPages.inputValueByPlaceHolder("90028", zipCode, index);
    }

    @Step
    public void inputValueForFirstName(String firstName) {
        paymentSettingPages.enterInputFieldWithLabel("First name", firstName);
    }

    @Step
    public void inputValueForLastName(String lastName) {
        paymentSettingPages.enterInputFieldWithLabel("Last name", lastName);
    }

    @Step
    public void inputValueForJobTitle(String jobTitle) {
        if (!jobTitle.isEmpty())
            paymentSettingPages.enterInputFieldWithLabel("Job title", jobTitle);
    }

    @Step
    public void inputValueForEmail(String email) {
        paymentSettingPages.enterInputFieldWithLabel("Email", email);
    }

    @Step
    public void inputValueForIDNumber(String idNumber) {
        if (!idNumber.isEmpty())
            paymentSettingPages.enterInputFieldWithLabel("ID number", idNumber);
            //paymentSettingPages.enterInputFieldWithLabel("Personal ID number", idNumber);  // This code is for feature switch revise_spay_nov_2021
    }

    @Step
    public void inputValueForStatement(String statementDescriptor) {
        paymentSettingPages.enterInputFieldWithLabel("Shop name", statementDescriptor);
    }

    @Step
    public void uploadFileForBusinessRegistration(String value) {
        paymentSettingPages.uploadFileByFieldLabel("Business registration", value);
    }

    @Step
    public void uploadFileForIDDocument(String version, String value) {
        switch (version) {
            case "front":
                paymentSettingPages.uploadFileByFieldLabel("ID document (front)", value);
                break;
            case "back":
                paymentSettingPages.uploadFileByFieldLabel("ID document (back)", value);
                break;
            case "picture":
                paymentSettingPages.uploadFileByFieldLabel("A picture of you with ID document", value);
                break;
        }
    }

    @Step
    public void uploadFileForDisputeHistory(String value) {
        paymentSettingPages.uploadFileByFieldLabel("Dispute history in other platforms", value);
    }

    @Step
    public void inputValueForProductDetails(String productDetails) {
        paymentSettingPages.enterInputFieldWithLabel("Product details (Description of merchant's product)", productDetails);
    }

    @Step
    public void submitKYCFormSpayReseller() {
        paymentSettingPages.clickBtn("Complete account setup");
        paymentSettingPages.waitForEverythingComplete();
    }

    @Step
    public String getTextUnderSpayResellerButton(int index) {
        return paymentSettingPages.getTextUnderSpayResellerButton(index);
    }

    @Step
    public String getShopbasePaymentAccountStatus(String field) {
        return paymentSettingPages.getShopbasePaymentAccountStatus(field);
    }

    @Step
    public void clickToExpandSpayResellerInformation() {
        paymentSettingPages.clickToExpandSpayResellerInformation();
    }

    @Step
    public void clickToDeactivateSpayReseller() {
        paymentSettingPages.clickToDeactivateSpayReseller();
    }


    @Step
    public String getAccessTokenShopBase() {
        return paymentSettingPages.getAccessTokenShopBase();
    }

    @Step
    public void resetSpayResellerAccountByAPI() {
        String accessToken = getAccessTokenShopBase();
        paymentSettingPages.resetSpayResellerAccountByAPI(accessToken);
    }

    @Step
    public void openSpayKYCListOnHive() {
        paymentSettingPages.openSpayKYCListOnHive();
    }

    @Step
    public void openKYCFormOnHive(String connectedId) {
        paymentSettingPages.openKYCFormOnHive(connectedId);
    }

    @Step
    public void updateKYCFormHighLevelApproval() {
        paymentSettingPages.updateKYCFormHighLevelApproval();
    }

    @Step
    public void approveKYCSpayV2() {
        paymentSettingPages.approveKYCSpayV2();
    }

    @Step
    public void approveKYCFormOnHive() {
        paymentSettingPages.approveKYCFormOnHive();
    }

    @Step
    public void rejectKYCFormOnHive() {
        paymentSettingPages.rejectKYCFormOnHive();
    }

    @Step
    public boolean isEnableSpayResellerButtonDisplayed() {
        return paymentSettingPages.isClickableBtn("Switch to ShopBase Payments");
    }

    @Step
    public String getConnectedAccountIDByAPI(String accessToken) {
        return paymentSettingPages.getConnectedAccountIDByAPI(accessToken);
    }

    @Step
    public void logInfor(String s) {
    }

    public void clickBtnCancel() {
        paymentSettingPages.clickBtn("Cancel");
    }

    public void clickBackToPaymentProviders() {
        commonPage.clickBackToPaymentProviders();
    }

    @Step
    public void clickRadioBtnEUPayment() {
        paymentSettingPages.clickOnElement("//div//strong[normalize-space()='European payment methods']/following::label//span[@class='s-check']");
        commonPageObject.waitElementToBeVisible("//div[@class='euro-method-list-v2 s-mt16']");
    }

    @Step
    public void clickbtnActivateMethod() {
        paymentSettingPages.clickActivateMethod();
        paymentSettingPages.clickOnBtn("Save");
        preferencesPage.verifyMsgSaveSuccess();
    }

    @Step
    public void setToggleEUPaymentStatus(boolean status) {
        paymentSettingPages.setToggleStatusEUPayment("European payment methods", status);
        paymentSettingPages.waitElementToBeVisible("//div[@class='euro-method-list-v2 s-mt16']");
    }

    public void inputValueForIDNumberForSpayV2(String idNumber) {
        paymentSettingPages.inputValueForIDNumberForSpayV2(idNumber);
    }

    @Step
    public String checkSpayStatus() {
        return paymentSettingPages.checkSpayStatus();
    }

    @Step
    public void fillRequiredFieldStripeRequested() {
        paymentSettingPages.fillRequiredFieldStripeRequested();
    }

    @Step
    public void clickOnVerifyAccountInformation() {
        paymentSettingPages.clickBtn("Verify account information");
        paymentSettingPages.waitForEverythingComplete(15);
    }

    @Step
    public void selectSpayType(String SpayType){
        paymentSettingPages.clickBtn(SpayType);
    }

    @Step
    public void selectCheckboxTermsAndConditions(){
        paymentSettingPages.checkCheckboxTermsAndConditions("Terms of Service");
        paymentSettingPages.checkCheckboxTermsAndConditions("Restricted and prohibited Products and Services list");
    }
}

