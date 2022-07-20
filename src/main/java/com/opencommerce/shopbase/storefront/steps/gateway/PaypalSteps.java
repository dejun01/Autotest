package com.opencommerce.shopbase.storefront.steps.gateway;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.storefront.pages.gateway.PaypalPage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PaypalSteps extends ScenarioSteps {
    PaypalPage paypalPage;


    @Step
    public void clickActivityTabSandbox() {
        paypalPage.clickActivityTabSandbox();
    }


    @Step
    public void loginSandboxDashboard(String email) {
        paypalPage.openUrl("https://www.sandbox.paypal.com");
        paypalPage.maximizeWindow();
        paypalPage.clickButtonLoginSandboxDashboard();
        paypalPage.enterEmailLoginSandboxer(email);
        if (paypalPage.isBtnNextPresent()) {
            paypalPage.clickBtnNext();
        }
        paypalPage.enterPassLoginSandbox("123456@a");
        paypalPage.clickBtnLogin();
        paypalPage.verifySandboxDashboard();
    }

    @Step
    public void confirmPayNowPaypal(String email, String pass, String paypalType, String totalAmt, String branName) {
        //paypalType = express/normal
        if ("express".equalsIgnoreCase(paypalType)) {
            switchToPaypalLoginPage();
        }
        paypalPage.maximizeWindow();
        paypalPage.clickOnAcceptCookieBtn();
        if (paypalPage.checkLoginBtnDisplay()) {
            paypalPage.clickOnLogin();
        }
        if(paypalPage.changeBtnDisplay()) {
            paypalPage.clickChangeBtn();
        }
        paypalPage.enterUserName(email);
        //Sometime button next is displayed
        paypalPage.clickBtnNext();
        paypalPage.enterPwd(pass);
        paypalPage.clickOnLogin();

//        if (!totalAmt.isEmpty()) {
//            String paidAmtByPapal = "";
//            paidAmtByPapal = paypalPage.getPaidTotalAmt();
//            assertThat(totalAmt).isEqualTo(paidAmtByPapal);
//        }
        paypalPage.clickOnAcceptCookieBtn();
        if (branName.isEmpty()) {
            paypalPage.verifyBrandName(branName);
        }

        paypalPage.clickBtnPaynowPayPal();
        waitABit(5000); //Depend on network and server, maybe the waiting time can be decreased if server is faster

        if ("express".equalsIgnoreCase(paypalType)) {
            paypalPage.switchToWindowWithIndex(0);
        }
    }

    public void clickBtnContinuePayPal() {
        paypalPage.clickBtnContinuePayPal();
    }

    public void clickBtnPaynowPayPal() {
        paypalPage.clickBtnPaynowPayPal();
    }

    private void clickBtnNext() {
        paypalPage.clickBtnNext();
    }

    @Step
    public void loginPaypal(String username, String pwd) {
        switchToPaypalLoginPage();
        paypalPage.maximizeWindow();
        paypalPage.clickBtnLoginPayPal();
        paypalPage.enterUserName(username);
        //Sometime button next is displayed
        paypalPage.clickBtnNext();
        paypalPage.enterPwd(pwd);
        paypalPage.clickOnLogin();
        boolean isExistContinue = paypalPage.isExistBtnContinue();
        if (isExistContinue) {
            //waitABit(5000);
            clickBtnContinuePayPal();
        }
        //waitABit(3000);
        clickBtnPaynowPayPal();
        paypalPage.switchToWindowWithIndex(0);
    }

    @Step
    public void checkoutBuyWithPaypal(String email, String pwd) {
        paypalPage.clickBtnCheckoutBuyWithPaypal();
        paypalPage.switchToPaypalLoginPage();
        paypalPage.enterUserName(email);
        System.out.println(email);
        if (paypalPage.isBtnNextPresent()) {
            paypalPage.clickBtnNext();
        }
        paypalPage.enterPwd(pwd);
        System.out.println(pwd);
        paypalPage.clickOnLogin();
        paypalPage.clickBtnPaynowPayPal();
        paypalPage.switchToWindowWithIndex(0);
    }

    @Step
    public void switchToPaypalLoginPage() {
        paypalPage.switchToPaypalLoginPage();
        paypalPage.waitForPageLoad();
//        paypalPage.waitBtnLoginPaypalPresent();
    }

    @Step
    public void clickBtnLogin() {
        paypalPage.clickBtnLogin();
    }

    @Step
    public void enterPwd(String pwd) {
        paypalPage.enterPwd(pwd);

    }

    @Step
    public void enterUsername(String username) {
        paypalPage.enterUserName(username);
    }

    public boolean isShowPayPalLogin() {
        return paypalPage.isShowPayPalLogin();
    }

    public void clickOnAcceptCookieBtn() {
        paypalPage.clickOnAcceptCookieBtn();
    }

    public void verifyBrandName(String brandName) {
        paypalPage.verifyBrandName(brandName);
    }
}
