package com.opencommerce.shopbase.otp.steps;

import com.opencommerce.shopbase.otp.pages.RequireOTPPages;
import net.thucydides.core.steps.ScenarioSteps;

public class RequireOTPSteps extends ScenarioSteps {
    RequireOTPPages requireOTPPages;

    public void fillAccountName(String accountName) {
        requireOTPPages.enterInputFieldWithLabel("* Account Name" , accountName);
    }

    public void fillPublicKey(String labelPublicKey, String publicKey) {
        requireOTPPages.enterInputFieldWithLabel(""+labelPublicKey+"", publicKey);
    }

    public void fillSecretKey(String labelSecretKey, String secretKey) {
        requireOTPPages.enterInputFieldWithLabel(""+labelSecretKey+"", secretKey);
    }

    public void addAccount() {
        requireOTPPages.clickBtn("+ Add account");
    }

    public void showPopupConfirmOTP() {
        requireOTPPages.showPopupConfirmOTP();
        requireOTPPages.clickLinkTextWithLabel("Choose another way to confirm");
        requireOTPPages.verifyShowOptionSentOTP();

    }

    public void choosePaymentProvidersIsPaypal() {
        requireOTPPages.choosePaymentProvidersIsPaypal();
    }

    public void closePopupcConfirmOTP() {
        requireOTPPages.closePopupConfirmOTP();
    }

    public void choosePaymentProviderType(String paymentProviderType) {
        requireOTPPages.clickBtn(""+paymentProviderType+"");
    }

    public void choosePaymentProviderName(String paymentProviderName) {
        requireOTPPages.choosePaymentProviderName(paymentProviderName);
    }

    public void useAPIKeysToConnect() {
        requireOTPPages.useAPIKeysToConnect();
    }

    public void verifyNotShowPopupOTP() {
        requireOTPPages.verifyNotShowPopupOTP();
    }

    public void fillMerchantNo(String merchantNo) {
        requireOTPPages.enterInputFieldWithLabel("* Merchant no", merchantNo);
    }

    public void fillGatewayNo(String gatewayNo) {
        requireOTPPages.enterInputFieldWithLabel("* Gateway no", gatewayNo);
    }

    public void fillSignKey(String signKey) {
        requireOTPPages.enterInputFieldWithLabel("* Sign key", signKey);
    }

    public void saveSetting() {
        requireOTPPages.saveSetting();
    }

    public void verifyWarningMsg() {
        requireOTPPages.verifyWarningMsg();
    }

    public void ChooseAnotherWayMsg(boolean b) {
        requireOTPPages.ChooseAnotherWayMsg(b);
    }

    public void chooseSendOtpBy(String method) {
        requireOTPPages.clickChooseAnotherWay();
        requireOTPPages.selectOtpMethod(method);
        requireOTPPages.clickBtn("Continue");
    }

    public String getEmail() {
        return requireOTPPages.getEmail();
    }

    public String getPhone() {
        return requireOTPPages.getPhone();
    }

    public void verifyLabelSendOTP(String method) {
        requireOTPPages.verifyLabelSendOTP(method);
    }
}
