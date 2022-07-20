package opencommerce.offer;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.balance.steps.BalanceSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.SettingSteps;
import common.utilities.LoadObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;


public class EncourageReopenStoreDef {
    @Steps
    AccountSteps accountSteps;
    @Steps
    BalanceSteps balanceSteps;
    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    SettingSteps settingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    SignUpSteps signUpSteps;
    String shopname = LoadObject.getProperty("shopname");


    @Then("check claim offer successfully")
    public void checkClaimOfferSuccessfully() {
        loginSteps.chooseShopClose(shopname);
        accountSteps.verifyDisplayReopenStoreWhenStoreNotEndSub();
        accountSteps.reopenStore();
        balanceSteps.accessToBalancePage();
        accountSteps.goToViewHistoryPage();
    }
    @Then("check not display offer when store has claimed")
    public void checkNotDisplayOfferWhenStoreHasClaimed() {
        commonSteps.switchToTab("Settings");
        settingSteps.chooseTheSection("Account");
        accountSteps.closeStore();
        accountSteps.clickSwitchToAnotherShopbutton();
        signUpSteps.verifyDisplayOffer(shopname, false);
    }

}
