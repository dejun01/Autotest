package opencommerce.otp.requireOTP;

import com.opencommerce.odoo.Session;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AddAccountStaffSteps;
import com.opencommerce.shopbase.otp.steps.RequireOTPSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class RequireOTPDef {
    @Steps
    RequireOTPSteps requireOTPSteps;
    @Steps
    AddAccountStaffSteps addAccountStaffSteps;

    String currentEmail = "";
    String currentPhone = "";

    String userName = System.getProperty("user.name");


    @When("choose payment providers")
    public void choosePaymentProviders(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String paymentProviderNameDefaut = "Stripe";
            String paymentProviderType = SessionData.getDataTbVal(dataTable, row, "payment provider type");
            String paymentProviderName = SessionData.getDataTbVal(dataTable, row, "payment provider name");

            requireOTPSteps.choosePaymentProviderType(paymentProviderType);
            requireOTPSteps.choosePaymentProviderName(paymentProviderName);

            if (paymentProviderName.equals(paymentProviderNameDefaut)) {
                requireOTPSteps.useAPIKeysToConnect();
            }
        }
    }

    @Then("verify popup require OTP is displayed")
    public void require_OTP() {
        requireOTPSteps.showPopupConfirmOTP();
        requireOTPSteps.closePopupcConfirmOTP();
    }


    @And("fill keys to activate payment provider")
    public void fillKeysToActivatePaymentProvider(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String accountName = SessionData.getDataTbVal(dataTable, row, "account name");
            String labelPublicKey = SessionData.getDataTbVal(dataTable, row, "label public key/client id");
            String labelSecretKey = SessionData.getDataTbVal(dataTable, row, "label secret key");
            String publicKey = SessionData.getDataTbVal(dataTable, row, "public key/client id");
            String secretKey = SessionData.getDataTbVal(dataTable, row, "secret key");

            requireOTPSteps.fillAccountName(accountName);
            requireOTPSteps.fillPublicKey(labelPublicKey, publicKey);
            requireOTPSteps.fillSecretKey(labelSecretKey, secretKey);
            requireOTPSteps.addAccount();
        }

    }

    @When("choose payment providers is Paypal")
    public void choosePaymentProvidersIsPaypal() {
        requireOTPSteps.choosePaymentProvidersIsPaypal();
    }

    @Then("verify not show popup require OTP")
    public void verifyNotShowPopupRequireOTP() {
        requireOTPSteps.verifyNotShowPopupOTP();
    }

    @And("fill information alternative provider")
    public void fillInformationAlternativeProvider(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String accountName = SessionData.getDataTbVal(dataTable, row, "account name");
            String merchantNo = SessionData.getDataTbVal(dataTable, row, "merchant no");
            String gatewayNo = SessionData.getDataTbVal(dataTable, row, "gateway no");
            String signKey = SessionData.getDataTbVal(dataTable, row, "sign key");

            requireOTPSteps.fillAccountName(accountName);
            requireOTPSteps.fillMerchantNo(merchantNo);
            requireOTPSteps.fillGatewayNo(gatewayNo);
            requireOTPSteps.fillSignKey(signKey);
        }

    }

    @And("resend OTP {int} times")
    public void resendOTPTimes(int count) {
        for (int i = 1; i <= count; i++) {
            requireOTPSteps.closePopupcConfirmOTP();
            requireOTPSteps.saveSetting();
        }
    }

    @And("choose send OTP by {string}")
    public void chooseSendOtpBy(String method) {
        requireOTPSteps.chooseSendOtpBy(method);
    }

    @And("get profile data")
    public void getProfileData() {
        currentEmail = requireOTPSteps.getEmail();
        currentPhone = requireOTPSteps.getPhone();
    }

    @And("verify label send OTP to current {string}")
    public void verifyLabelSendOTPToCurrent(String method) {
        if (method.equals("phone")) {
            method = currentPhone;
        } else {
            method = currentEmail;
        }
        requireOTPSteps.verifyLabelSendOTP(method);
    }

    @And("verify warning and choose another way message is {string}")
    public void verifyWarningAndChooseAnotherWayMessageIs(String b) {
        requireOTPSteps.verifyWarningMsg();
        requireOTPSteps.ChooseAnotherWayMsg(Boolean.parseBoolean(b));
    }

    @And("Access email and verify")
    public void accessEmailAndVerify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            userName = "changeprofile@mailinator.girl-viet.com";
            addAccountStaffSteps.openMailBoxWithSubject(userName, subject);
        }
    }

    @Then("close popup OTP")
    public void closePopupOTP() {
        requireOTPSteps.closePopupcConfirmOTP();
    }
}
