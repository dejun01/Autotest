package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.UpgradePlanSteps;
import common.utilities.DateTimeUtil;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FulfillmentPackageDef {
    @Steps
    AccountSteps accountSteps;

    @Steps
    UpgradePlanSteps upgradePlanSteps;


    @When("^go to Upgrade plan page$")
    public void go_to_Upgrade_plan_page() {
        upgradePlanSteps.waitButtonUpgradePlanPresent();
        accountSteps.clickBTUpgradePlan();
    }

    @Then("verify Upgrade plan page")
    public void verify_Upgrade_plan_page() {
        upgradePlanSteps.verifyPackages();
    }

    @Then("verify block Disable Fulfillment Service")
    public void verify_block_Disable_Fulfillment_Service() {
        upgradePlanSteps.verifyBlockDisableFulfillmentService();
    }

    @And("verify block Enable Payment Gateway")
    public void verify_block_Enable_Payment_Gateway() {
        upgradePlanSteps.verifyBlockEnablePaymentGateway();

    }

    @And("verify data is displayed on Upgrade plan page")
    public void verifyDataIsDisplayedOnUpgradePlanPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String blockName = SessionData.getDataTbVal(dataTable, row, "Button name");
            String aPackage = SessionData.getDataTbVal(dataTable, row, "Package");

            accountSteps.verifyBlockSellMoreWithAnOnlineStoreIsDisplayed(blockName);
            accountSteps.verifyPackagesIsDisplayed(aPackage);

        }
    }

    Double packagePrice = 0d;

    @And("choose a package is \"([^\"]*)\"")
    public void chooseAPackageIS(String packageName) {
        if (accountSteps.ableToSelectPackage(packageName)) {
            accountSteps.choosePlan(packageName);
            accountSteps.clickBTConfirmChanges();
        }
    }


    @And("go to View history page")
    public void goToViewHistoryPage() {
        accountSteps.goToViewHistoryPage();
    }

    String shopName = LoadObject.getProperty("shopname");
    Double amountPayback = 18.37d;

    @Then("verify refund package when changing plan")


    public void verifyRefundPackageWhenChangingPlan(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            String createDate = accountSteps.getCreatedDate();
            int rowRecord = accountSteps.getRowInvoices(type, shopName, content, status);
            accountSteps.verifyAmountRefunded(amountPayback, (rowRecord + 1));
        }
    }

    @And("Calculating the payback amount of current package")
    public Double calculatingTheRemainingAmountOfCurrentPackage() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");

        String currentDay = DateTimeUtil.getTodayByFormat("dd/MM/yyyy");
        String dayBeginNewPlan = accountSteps.getTextTheNextSubscriptionBill();

        Date date = df.parse(dayBeginNewPlan);
        String dayBeginNewPlanNew = df1.format(date);

        accountSteps.clickBTUpgradePlan();
        double price = accountSteps.getPriceOfCurrentPackage();
        long remainingDay = DateTimeUtil.calculateDayBetweenTwoDate(currentDay, dayBeginNewPlanNew) - 1;
        amountPayback = (price * remainingDay / 30);
        amountPayback = (double) Math.round(amountPayback * 100) / 100;
        System.out.println(amountPayback);
        return amountPayback;
    }

    @And("Switch to PlusHub Service Only")
    public void switchToShopBaseFulfillmentServiceOnly() {
        accountSteps.clickButtonYesIWantThisOption();
        accountSteps.clickButtonConfirmNewPlan();
        accountSteps.clickBTConfirmChanges();
    }
}
