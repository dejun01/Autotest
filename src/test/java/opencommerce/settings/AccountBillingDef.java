package opencommerce.settings;

import com.opencommerce.shopbase.dashboard.settings.pages.AccountPage;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import common.utilities.DateTimeUtil;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AccountBillingDef {

    @Steps
    AccountSteps accountSteps;
    @Steps
    SettingSteps settingSteps;
    AccountPage accountPage;
    String discount = "";
    String month = "";
    float price = 0;
    float priceCurrentPlan = 0;
    String dayEndFreeTrial = "";
    String currentDay = DateTimeUtil.getTodayByFormat("dd/MM/yyyy");
    String dayBeginNewPlan = "";
    float priceRemaining = 0;
    String afterPrice = "";
    int day = 0;

    @And("^Seller pick a plan for your store is \"([^\"]*)\" and input coupon is \"([^\"]*)\" with verify plan with (the first shop dashboard|the second shop dashboard)$")
    public void sellerPickAPlanForYourStoreAndInputCouponWithVerify(String typePlan, String typeCoupon, String typeShop, List<List<String>> dataTable) throws ParseException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String coupon = SessionData.getDataTbVal(dataTable, row, "Coupon");
            discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            month = SessionData.getDataTbVal(dataTable, row, "Month");
            dayBeginNewPlan = accountSteps.getTextTheNextSubscriptionBill();
            accountSteps.clickBTUpgradePlan();
            accountSteps.clickBTTypePlan(typePlan);
            priceCurrentPlan = Float.parseFloat(accountSteps.choosePackage(typePlan, "Current plan"));
            price = Float.parseFloat(accountSteps.choosePackage(typePlan, "Choose this plan"));
            accountSteps.inputDiscount(coupon);
            accountSteps.clickBTApply();
            switch (typeShop) {
                case "shop shop in free trial":
                    if (typeCoupon.equals("Percentage on subscription")) {
                        afterPrice = accountSteps.roundOff((price - (price * Float.parseFloat(discount) / 100)) * Integer.parseInt(month));
                        assertThat(accountSteps.getPriceAfterInputCoupon()).isEqualTo(afterPrice);
                        assertThat(accountSteps.roundOff(price)).isEqualTo(accountSteps.getPriceBeforeInputCoupon(typePlan));
                    } else {
                        dayEndFreeTrial = accountSteps.getTextInfoTime("fromDay");
                        String date = DateTimeUtil.addMonthDay(dayEndFreeTrial, Integer.parseInt(month), Integer.parseInt(discount), "MMM dd, yyyy");
                        assertThat(accountSteps.getTextInfoTime("toDay")).isEqualTo(date);

                    }
                    break;
                case "shop out of free trial":
                    long remaingDay = DateTimeUtil.dayOfTime(currentDay, dayBeginNewPlan, "dd/MM/yyyy", "MMM dd, yyyy");
                    priceRemaining = (remaingDay - 1) * priceCurrentPlan;
                    if (price - priceRemaining > 0) {
                        afterPrice = accountSteps.roundOff((price - priceRemaining) - ((price - priceRemaining) * Float.parseFloat(discount) / 100) * Integer.parseInt(month));
                        assertThat(accountSteps.getPriceAfterInputCoupon()).isEqualTo(afterPrice);
                    } else {
                        assertThat(accountSteps.getPriceAfterInputCoupon()).isEqualTo("0.00");
                        day = (int) priceRemaining / (int) price * 30;
                    }
                    if (typeCoupon.equals("Free trial extend")) {
                        day = Integer.parseInt(discount);
                    }
                    String date = DateTimeUtil.addMonthDay(currentDay, Integer.parseInt(month), day, "dd/MMMM/yyyy H:mm:a");
                    assertThat(accountSteps.getTextInfoTime("toDay")).isEqualTo(date);
                    break;
            }
            accountSteps.clickBTConfirmChanges();

        }

    }


    @And("Seller choose a plan for your store is \"([^\"]*)\"")
    public void sellerChooseAPlanForYourStoreIs(String status, List<List<String>> dataTable) {
        accountSteps.clickBTUpgradePlan();
        accountSteps.clickBTTypePlan(status);
        price = Float.parseFloat(accountSteps.choosePackage(status, "Choose this plan"));
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String coupon = SessionData.getDataTbVal(dataTable, row, "Coupon");
            discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            month = SessionData.getDataTbVal(dataTable, row, "Limit");
            String error = SessionData.getDataTbVal(dataTable, row, "Error");
            accountSteps.inputDiscount(coupon);
            accountSteps.clickBTApply();
            if (!error.isEmpty()) {
                assertThat(accountSteps.getError()).isEqualTo(error);

            }
            accountSteps.refreshPage();

        }
    }


    @And("choose another plan")
    public void chooseAnotherPlan() {
        if (accountPage.currentPlan.matches("Standard Base")) {
            accountSteps.choosePlan("Basic Base");
            accountSteps.clickBTConfirmChanges();
            accountPage.currentPlan = "Basic Base";
        } else {
            accountSteps.choosePlan("Standard Base");
            accountSteps.clickBTConfirmChanges();
            accountPage.currentPlan = "Standard Base";
        }
    }

    @And("get current plan")
    public void getCurrentPlan() {
        accountSteps.getCurrentPlan();
    }

    @And("^confirm a plan is \"([^\"]*)\" in cycle is \"([^\"]*)\"$")
    public void confirmAPlanIs(String plan, String cycle) {
        settingSteps.chooseTheSection("Account");
        accountSteps.clickBTUpgradePlan();
        accountSteps.clickBTTypePlan(cycle);
        accountSteps.choosePlan(plan);
        accountSteps.clickBTStartPlan();
    }
}



