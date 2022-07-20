package com.opencommerce.shopbase.dashboard.authen.steps;
import com.opencommerce.shopbase.dashboard.authen.pages.AccountSecurityPage;
import net.thucydides.core.annotations.Step;

public class AccountSecuritySteps {
    AccountSecurityPage accountPage;


    public void changePhone(String phone) {
        accountPage.changePhone(phone);

    }

    public void clickButtonSave() {
        accountPage.clickButtonSave();
    }

    public void inputOtp(String otpCode) {
        accountPage.inputOtp(otpCode);
    }

    public void inputPassword(String password) {
        accountPage.inputPassword(password);
    }

    public void clickButtonConfirm() {
        accountPage.clickButtonConfirm();

    }

    public void changeEmail(String email) {
        accountPage.changeEmail(email);
    }


    public void changeOwnerPhone() {
        accountPage.changeOwnerPhone();
    }

//
//    public void verifyMsgOtp(String msgotp) {
//        accountPage.verifyMsgOtp(msgotp);
//    }
//
//    public void verifyMsgPassword(String msgpassword) {
//        accountPage.verifyMsgPassword(msgpassword);
//    }

    public void verifyError(String msg) {
        accountPage.verifyError(msg);
    }

    @Step
    public void verifyChangePhoneNumberSuccessfully(String currentPhone) {
        accountPage.verifyChangePhoneNumberSuccessfully(currentPhone);
    }
}