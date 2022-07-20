package opencommerce.balance.steps;

import net.thucydides.core.annotations.Step;
import opencommerce.balance.pages.BalancePage;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;

public class BalanceSteps {
    BalancePage balancePages;

    private static DecimalFormat df = new DecimalFormat("#0.00");

    @Step
    public void accessToBalancePage() {
        balancePages.accessToBalancePage();
    }

    @Step
    public String getCurrentAvailableBalance() {
        return balancePages.getCurrentAvailableBalance();
    }

    @Step
    public String getAvailableSoon() {
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
    public void inputPayoutamt(String amt) {
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


    public void selectStore(String shopName) {
        balancePages.clickBtn("View invoices");
        balancePages.clickBtn("More filters");
        balancePages.selectStore(shopName);
        balancePages.clickBtn("Done");
    }
}
