
package opencommerce.third_party;

import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.steps.gateway.DashboardStripeSteps;
import com.opencommerce.shopbase.OrderVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DashboardStripeDef {
    @Steps
    DashboardStripeSteps dashboardStripeSteps;

    public static String payoutAmt;
    public static List<String> listOrder = new ArrayList<>();

    @Given("^Open Dashboard Stripe page and login$")
    public void openDashboardStripeLoginPage(){
        String url = LoadObject.getProperty("stripe.url");
        dashboardStripeSteps.openDashboardStripeLoginPage(url);
        logInToDashboardStripe();
    }

    @Given("^Open Dashboard Stripe page in new tab$")
    public void openDashboardStripeLoginPageInNewTab(){
        String url = LoadObject.getProperty("stripe.url");
        dashboardStripeSteps.openDashboardStripeLoginPageInNewTab(url);
    }

    @Given("^Log in to Dashboard Stripe$")
    public void logInToDashboardStripe(){
        String email = LoadObject.getProperty("user.StripeAccount");
        String password = LoadObject.getProperty("user.StripePwd");

        dashboardStripeSteps.inputEmail(email);
        dashboardStripeSteps.inputPassword(password);
        dashboardStripeSteps.clickOnSignInBtn();
    }

    @Given("^Enable Viewing test data$")
    public void enableViewTestData(){
        dashboardStripeSteps.enableViewTestData();
    }

    @Given("^Search and Select shop$")
    public void changePackageGroupTrialDay(){
        String connectedID = LoadObject.getProperty("user.connectedId");
        dashboardStripeSteps.searchShopByConnectedId(connectedID);
        dashboardStripeSteps.selectShopByConnectedIdAfterSearching();
    }

    @Given("^Send funds \"([^\"]*)\" if available balance is less than \"([^\"]*)\"$")
    public void sendFundsIfBalanceIsLow(String funds, String amt){
        double currentBalanceAmt;
        String _funds = funds.replace("$","");
        double _amt = Double.parseDouble(amt.replace("$",""));
        dashboardStripeSteps.openPayoutToBankPopup();
        currentBalanceAmt = dashboardStripeSteps.availableAmt();
        if(currentBalanceAmt<=_amt){
            dashboardStripeSteps.sendFunds(_funds);
        }else{
            dashboardStripeSteps.clickOnCancelBtn();
        }
    }

    @Given("^Pay out to bank with amount = \"([^\"]*)\" successfully$")
    public void payoutToBankSuccessfully(String amt){
        //String _amt = amt.replace("$","");
        dashboardStripeSteps.openPayoutToBankPopup();
        dashboardStripeSteps.payoutToBank(amt);
        String trxDate = "";
        // Get the current hour-of-day at GMT
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        trxDate = dateFormat.format(date);
        System.out.println("Converted String: " + trxDate);

        payoutAmt = amt;
        String orderNumber = "N/A";
        String trxType = "payout";
        String trxFee = "$0.00";
//        listOrder.add(trxDate +"|"+ trxType +"|"+ amt +"|"+ trxFee);
        listOrder.add(orderNumber + "|" + trxType + "|" + amt + "|" + trxFee + "|" + amt + "|" +trxDate);
        System.out.println("=======================Order details: "+listOrder);
    }

    @Given("^go to transaction detail page and verify detail$")
    public void verifyTrxDetail(List<List<String>> dataTable){
        dashboardStripeSteps.navigateToTrxDetailPage(OrderVariable.trxId);
        String status = "";

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            status = SessionData.getDataTbVal(dataTable, row, "Trx Status");
        }
        dashboardStripeSteps.verifyTrxTotalAmt(OrderVariable.totalAmt);
        dashboardStripeSteps.verifyTrxStatus(status);
    }
}