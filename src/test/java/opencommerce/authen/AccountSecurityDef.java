package opencommerce.authen;


import com.opencommerce.shopbase.dashboard.authen.steps.AccountSecuritySteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class AccountSecurityDef {
    @Steps
    AccountSecuritySteps accountStep;
    public String currentPhone;

    @And("^Change phone \"([^\"]*)\"$")
    public void changePhone(String phone) {
        accountStep.changePhone(phone);
        currentPhone = phone;
        accountStep.clickButtonSave();
    }

    @And("^Click button Save$")
    public void clickButtonSave() {
        accountStep.clickButtonSave();
    }

    @And("^Input otp \"([^\"]*)\"$")
    public void inputOtp(String otpCode) {
        accountStep.inputOtp(otpCode);
        accountStep.clickButtonConfirm();
    }

    @And("^Input current password$")
    public void inputPassword() {
        accountStep.inputPassword(LoadObject.getProperty("user.pwd"));
    }

    @And("^Click button Confirm$")
    public void clickButtonConfirm() {
        accountStep.clickButtonConfirm();
    }

    @And("Change email {string}")
    public void changeEmail(String email) {
        accountStep.changeEmail(email);
    }

    @And("^Change owner phone$")
    public void changeOwnerPhone() {
        accountStep.changeOwnerPhone();

    }

    @And("^Input password, input otp & verify status$")
    public void inputPassOtpAndVerify(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            String otpCode = SessionData.getDataTbVal(dataTable, row, "Otp");
            String msgpassword = SessionData.getDataTbVal(dataTable, row, "Msg password");
            String msgotp = SessionData.getDataTbVal(dataTable, row, "Msg otp");

            if ("@currentPassword@".equals(password)) {
                accountStep.inputPassword(LoadObject.getProperty("user.pwd"));
            } else {
                accountStep.inputPassword(password);
            }

            accountStep.inputOtp(otpCode);
            accountStep.clickButtonConfirm();

//            if (!password.equals("123456")) {
            if (password.equals("1234567")) {
                accountStep.verifyError(msgpassword);
            }
//            if (!otpCode.equals("140396")) {
            if (otpCode.equals("123456")) {
                accountStep.verifyError(msgotp);
            }
        }
    }


    @Then("Verify change phone number successfully")
    public void verifyChangePhoneNumberSuccessfully() {
        accountStep.verifyChangePhoneNumberSuccessfully(currentPhone);
    }
}


