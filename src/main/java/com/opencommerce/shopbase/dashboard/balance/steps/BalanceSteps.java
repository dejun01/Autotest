package com.opencommerce.shopbase.dashboard.balance.steps;

import com.opencommerce.shopbase.common.pages.CommonPage;
import com.opencommerce.shopbase.dashboard.balance.pages.BalancePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;


import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;

public class BalanceSteps {
    BalancePage balancePages;
    CommonPage commonPage;

    private static DecimalFormat df = new DecimalFormat("#0.00");

    @Step
    public void accessToBalancePage() {
        balancePages.accessToBalancePage();
    }

    @Step
    public double getCurrentAvailableBalance() {
        return balancePages.getCurrentAvailableBalance();
    }

    @Step
    public double getAvailableSoon() {
        return balancePages.getAvailableSoon();
    }

    @Step
    public void clickOnTopUpBtn() {
        balancePages.clickOnTopUpBtn();
    }

    @Step
    public void clickOnViewHistoryBtn() {
        balancePages.clickOnViewHistoryBtn();
        balancePages.waitForEverythingComplete();
        balancePages.waitForPageLoad();
    }

    @Step
    public void selectTopUpAmt(String amt) {
        balancePages.selectTopUpAmt(amt);
    }

    @Step
    public void clickOnConfirmTopUpBtn() {
        balancePages.clickOnConfirmTopUpBtn();
    }

    @Step
    public void clickOnRequestPayoutBtn() {
        balancePages.clickOnRequestPayoutBtn();
    }

    @Step
    public void clickOnViewPayoutsBtn() {
        balancePages.clickOnViewPayoutsBtn();
    }

    @Step
    public void inputPayoutamt(double amt) {
        balancePages.inputPayoutamt(amt);
    }

    @Step
    public void clickOnSendRequestBtn() {
        balancePages.clickOnSendRequestBtn();
    }

    @Step
    public void verifyAvailabaleBalanceAmtAfterTopup(double amt, double amtBefore, double topupAmt) {
        amtBefore = Math.round(amtBefore) * 100.0 / 100.0;
        double _amtBefore = Math.round(amt + topupAmt) * 100.0 / 100.0;
        assertThat(amtBefore).isEqualTo(_amtBefore);
    }

    @Step
    public void verifyAvailabaleBalanceAmtAfterAutoTopup(double amt, double topupAmt) {
        assertThat(amt).isEqualTo(topupAmt);
    }


    @Step
    public void reloadPage() {
        balancePages.refreshPage();
    }

    @Step
    public void verifyAvailableSoonAfterCheckout(double availableSoon, double newAvailableSoon, double net) {
        double _newAvailableSoon = 0.0d;
        newAvailableSoon = Math.round(newAvailableSoon * 100.0) / 100.0;
        _newAvailableSoon = Math.round((availableSoon + net) * 100.0) / 100.0;
        assertThat(newAvailableSoon).isEqualTo(_newAvailableSoon);
    }

    public void verifyBalanceHistory(String type, String source, String shopName, String detail, String amount, String fee, String net, String status) {
        balancePages.verifyBalanceHistory(type, source, shopName, detail, amount, fee, net, status);
    }

    public boolean isStayAtViewHistoryScreen() {
        return balancePages.isStayAtViewHistoryScreen();
    }

    public void verifyNoBalanceInMenu() {
        balancePages.verifyNoBalanceInMenu();
    }

    public void verifyNoBalanceInUrl() {
        balancePages.verifyNoBalanceInUrl();
    }

    public double getAvailableToPayout() {
        return balancePages.getAvailableToPayout();
    }

    public void chargeBalance(double chargeAmt) {
        balancePages.chargeBalance(chargeAmt);
    }

    @Step
    public void verifyDetail(String paymentType, String domainURL, String duration) {
        balancePages.verifyDetail(paymentType, domainURL, duration);
    }

    @Step
    public void verifyPrice(double domainPrice) {
        balancePages.verifyPrice(domainPrice);
    }

    @Step
    public void verifyTotal(double domainPrice) {
        balancePages.verifyTotal(domainPrice);
    }

    @Step
    public void verifyAvailableBalance(double currentBalanceAmt) {
        balancePages.verifyAvailableBalance(currentBalanceAmt);
    }

    @Step
    public void verifyEnoughMoney(String enoughMoney) {
        balancePages.verifyEnoughMoney(enoughMoney);
    }

    @Step
    public void verifyTopUpAmount(double domainPrice) {
        balancePages.verifyTopUpAmount(domainPrice);
    }

    @Step
    public void verifyPaymentMethod(String selectAPaymentMethod) {
        balancePages.verifyPaymentMethod(selectAPaymentMethod);
    }

    @Step
    public void clickOnTopUpBtnOnSidebarMenuMakeAPayment() {
        balancePages.clickOnTopUpBtnOnSidebarMenuMakeAPayment();
    }

    @Step
    public void topUpToBalanceOnSidebarMenuMakeAPayment(String topUpAmt) {
        balancePages.inputTopUpToBalanceOnSidebarMenuMakeAPayment(topUpAmt);
        balancePages.clickOnBtnConfirmTopUp();
    }

    @Step
    public double getCurrentAvailableBalanceByAPI() {
        return balancePages.getCurrentAvailableBalanceByAPI();
    }

    @Step
    public double getAvailableToPayoutByAPI() {
        return balancePages.getAvailableToPayoutByAPI();

    }

    @Step
    public double getAvailableSoonByAPI() {
        return balancePages.getAvailableSoonByAPI();
    }

    public void selectMoneyTransfer() {
        balancePages.selectMoneyTransfer();
    }


    public void inputTopupInfo(String amount, String account, String accHolder, String accEmail, String transId, String note) {
        balancePages.inputTopupInfo(amount, account, accHolder, accEmail, transId, note);
    }

    public void verifyConfirmTopup(String amount) {
        balancePages.verifyConfirmTopup(amount);
    }

    public void filterBalanceHistoryByWithValue(String content, String value) {
        balancePages.filterBalanceHistoryByWithValue(content, value);
    }


    public void verifyInvoiceInfo(String type, String content, String amount, String status) {
        balancePages.veiryInvoiceInfo(type, content, amount, status);
    }

    public void verifyWireTransferTransactionContent(String index, String content) {
        balancePages.verifyWireTransferTransactionContent(index, content);

    }

    public void clickOnLatestInvoice() {
        balancePages.clickOnLatestInvoice();
    }

    public void filterVerifyTopUpRequestByTopUpCode() {
        balancePages.filterVerifyTopUpRequestByTopUpCode();
    }

    public void actionTopupRequest(String action) {
        balancePages.actionTopupRequest(action);
    }

    public void logoutToHiveSbase() {
        balancePages.logoutToHiveSbase();
    }

    public void clickEditPayoutAccount(String account) {
        balancePages.clickEditPayoutAccount(account);
    }

    public void inputPayoutAccount(String account, String value) {
        balancePages.inputPayoutAccount(account, value);
    }

    public void getAutoTopupStatus() {
        balancePages.getAutoTopupStatus();
    }

    public void CheckAutoTopupAndInputData() {
        balancePages.CheckAutoTopupAndInputData();
    }

    public void clicktoUpdateCard() {
        balancePages.clicktoUpdateCard();
    }

    public void inputUpdateCreditCard(String cardNumber, String expired_date, String cvv, String country) {
        balancePages.inputUpdateCreditCard(cardNumber, expired_date, cvv, country);
    }

    @Step
    public void verifyError(String error) {
        balancePages.verifyError(error);
    }

    @Step
    public void clickToButtonPayNow() {
        balancePages.clickToButtonPayNow();
    }

    @Step
    public void verifyBalanceInvoiceType(String index, String type) {
        balancePages.verifyBalanceInvoiceType(index, type);
    }

    @Step
    public void verifyBalanceInvoiceShopName(String index, String shopName) {
        balancePages.verifyBalanceInvoiceShopName(index, shopName);
    }

    @Step
    public void verifyBalanceInvoiceContent(String index, String content) {
        balancePages.verifyBalanceInvoiceContent(index, content);
    }

    @Step
    public void verifyBalanceInvoiceAmountDecrease(String index, double amount) {
        balancePages.verifyBalanceInvoiceAmountDecrease(index, amount);
    }

    @Step
    public void verifyBalanceInvoiceAmountIncrease(String index, double amount) {
        balancePages.verifyBalanceInvoiceAmountIncrease(index, amount);
    }

    @Step
    public void verifyBalanceInvoiceStatus(String index, String status) {
        balancePages.verifyBalanceInvoiceStatus(index, status);
    }

    @Step
    public void verifyBalanceInvoiceCreatedDate(String index, String createdDate) {
        balancePages.verifyBalanceInvoiceCreatedDate(index, createdDate);
    }

    @Step
    public void verifyBalanceInvoiceLatestTransactionDate(String index, String latestTransactionDate) {
        balancePages.verifyBalanceInvoiceLatestTransactionDate(index, latestTransactionDate);
    }

    @Step
    public void checkedAutoTopup(boolean isCheck) {
        balancePages.checkedAutoTopup(isCheck);
    }

    @Step
    public double getAmountAutoTopup() {
        return balancePages.getAmountAutoTopup();
    }

    @Step
    public void openProductInSF(String productName) {
        balancePages.openProductInSF(productName);
        balancePages.maximizeWindow();
    }

    @Step
    public void addToCartWithoutVerify() {
        balancePages.addToCartWithoutVerify();
    }

    @Step
    public void clickToCheckoutWithoutVerify() {
        balancePages.clickToCheckoutWithoutVerify();
    }

    @Step
    public void verifyInvoiceShopName(String shop) {
        balancePages.verifyInvoiceShopName(shop);
    }

    @Step
    public void verifyInvoiceContent(String content) {
        balancePages.verifyInvoiceContent(content);
    }

    @Step
    public void verifyInvoiceDetail(String detail) {
        balancePages.verifyInvoiceDetail(detail);
    }

    @Step
    public void verifyInvoiceType(String type) {
        balancePages.verifyInvoiceType(type);
    }

    @Step
    public void verifyInvoiceAmountDecrease(double amount) {
        balancePages.verifyInvoiceAmountDecrease(amount);
    }

    @Step
    public void verifyInvoiceAmountIncrease(double amount) {
        balancePages.verifyInvoiceAmountIncrease(amount);
    }

    @Step
    public void verifyInvoiceCreatedDate(String createdDate) {
        balancePages.verifyInvoiceCreatedDate(createdDate);
    }

    @Step
    public void openInvoice(String index) {
        balancePages.openInvoice(index);
    }

    @Step
    public void verifyInvoiceTransactionType(String index, String type) {
        balancePages.verifyInvoiceTransactionType(index, type);
    }

    @Step
    public void verifyInvoiceTransactionContent(String index, String content) {
        balancePages.verifyInvoiceTransactionContent(index, content);
    }

    @Step
    public void verifyInvoiceTransactionAmountDecrease(String index, double amount) {
        balancePages.verifyInvoiceTransactionAmountDecrease(index, amount);
    }

    @Step
    public void verifyInvoiceTransactionAmountIncrease(String index, double amount) {
        balancePages.verifyInvoiceTransactionAmountIncrease(index, amount);
    }

    @Step
    public void verifyInvoiceTransactionStatus(String index, String status) {
        balancePages.verifyInvoiceTransactionStatus(index, status);
    }

    @Step
    public void verifyInvoiceTransactionDate(String index, String date) {
        balancePages.verifyInvoiceTransactionDate(index, date);
    }

    @Step
    public void verifyDataBalanceChanged(double dataInit, double dataIncreaseExpect, double dataActual) {
        assertThat((double) Math.round((dataInit + dataIncreaseExpect) * 100) / 100).isEqualTo(dataActual);
    }

    @Step
    public void completeOrderByPayLater() {
        balancePages.completeOrderByPayLater();
    }

    @Step
    public void payInvoiceOpen() {
        balancePages.payInvoiceOpen();
    }

    public void inputRefundBaseCost(String refundBaseCost) {
        balancePages.inputRefundBaseCost(refundBaseCost);
    }

    public void inputRefundProcessingFee(String refundProcessingFee) {
        balancePages.inputRefundProcessingFee(refundProcessingFee);
    }

    public void inputRefundPaymentFee(String refundPaymentFee) {
        balancePages.inputRefundPaymentFee(refundPaymentFee);
    }

    public void inputRefundSellingPrice(String refundSellingPrice) {
        balancePages.inputRefundSellingPrice(refundSellingPrice);
    }

    public void inputRefundShippingFee(String refundShippingFee) {
        balancePages.inputRefundShippingFee(refundShippingFee);
    }

    public void inputRecoverPaymentFee(String recoverPaymentFee) {
        balancePages.inputRecoverPaymentFee(recoverPaymentFee);
    }

    public void clickBtnRefund() {
        balancePages.clickBtnRefund();
    }

    public void checkedConvertedSpayToBalance(boolean isCheck) {
        balancePages.checkedConvertedSpayToBalance(isCheck);
    }

    public void clickOnBreadcrumbBalance() {
        balancePages.clickOnBreadcrumbBalance();
    }
}
