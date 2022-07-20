package opencommerce.hive;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.tier_program.steps.TierDashboardSteps;
import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import com.opencommerce.shopbase.hive.pbaseorder.steps.TierSteps;
import com.opencommerce.shopbase.storefront.api.InfoAPI;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

public class TierProgramDef {
    @Steps
    TierSteps tierSteps;
    HashMap<String, String> pre = new HashMap<>();
    HashMap<String, String> current = new HashMap<>();
    HashMap<String, List<String>> tierInfo = new HashMap<>();
    HashMap<String, List<String>> tierList = new HashMap<>();

    @Steps
    CustomerInformationSteps cusSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    LoginDashboardSteps loginSteps;
    String shop = LoadObject.getProperty("shop");
    @Steps
    PaymentMethodSteps paySteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    ShippingMethodSteps shipSteps;
    @Steps
    PBaseOrderStep pBaseOrderStep;
    @Steps
    InfoAPI infoAPI;
    @Steps
    CommonSteps commonSteps;
    @Steps
    TierDashboardSteps tierDashboardSteps;
    String orderName;
    String checkoutToken;
    int orderId;
    int coinRedeem;
    double availableBcoin;
    int amountReward;

    @And("verify tier program list page")
    public void verifyTierProgramListPage() {
        tierSteps.navigatetoTierListPage();
        tierSteps.verifyNumberOfTier();
        tierSteps.verifyTierInformation();
    }

    @Then("user edit tier")
    public void userEditTier(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tier = SessionData.getDataTbVal(dataTable, row, "Tier");
            String nextRowTier = SessionData.getDataTbVal(dataTable, row + 1, "Tier");
            String name = SessionData.getDataTbVal(dataTable, row, "Tier name");
            String threshold = SessionData.getDataTbVal(dataTable, row, "Threshold");
            String thresholdKeep = SessionData.getDataTbVal(dataTable, row, "Threshold keep");
            String cycle = SessionData.getDataTbVal(dataTable, row, "Cycle");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String msg = SessionData.getDataTbVal(dataTable, row, "Messages");
            System.out.println(" Tier: " + tier + ", Name: " + name + ", threshold: " + threshold + ", threshold Keep:" + thresholdKeep + ", Cycle:" + cycle + ", Discount:" + discount + ", Msg: " + msg);

            if (tierSteps.isExistedBtnEdit()) {
                tierSteps.clickBtnEditTier(tier);
            }
            tierSteps.enterTierName(name);
            tierSteps.enterThreshold(threshold);
            tierSteps.enterThresholdKeep(thresholdKeep);
            tierSteps.enterCycle(cycle);
            tierSteps.enterDiscount(discount);
            tierSteps.clickBtnUpdateAndClose();
            tierSteps.verifyMessages(msg);

        }
    }

    @And("get tier information of user by api")
    public void getTierInformationOfUserByApi() {
        pre = tierSteps.getTierInformationOfUserByApi();
        System.out.println("pre: " + pre);
    }

    @And("checkout an order and verify update Bcoin and Available coint")
    public void checkoutAnOrderAndVerifyUpdateBcoinAndAvailabelCoint(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            int quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            String base = SessionData.getDataTbVal(dataTable, row, "Base Product");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");

            double coin = tierSteps.convertCoin(base, quantity);

            loginSteps.openShop(shop);
            productSteps.searchAndSelectProduct(productName);
            productSteps.selectVariant(size);
            productSteps.setQuantityProduct(String.valueOf(quantity));
            productSteps.clickAddToCart();
            productSteps.clickCheckOut();
            cusSteps.enterCustomerInformation(false);
            shipSteps.chooseShippingMethod("");
            paySteps.enterPaymentMethodByStripe();
            paySteps.clickPlaceYourOrder();
            thankyouSteps.verifyThankYouPage();
            orderName = thankyouSteps.getOrderNumber();
            checkoutToken = thankyouSteps.getCheckoutToken();
            System.out.println("Checkout token: " + checkoutToken);
            orderId = Integer.parseInt(infoAPI.getOrderID(checkoutToken).toString().trim());

            tierSteps.waitToAPIupdate(50000);
            tierSteps.clearCache();
            current = tierSteps.getTierInformationOfUserByApi();
            System.out.println("current: " + current);
            tierSteps.verifyBcoin(pre, current, action, coin);
            tierSteps.verifyTierLevel(tierInfo, pre, current);
        }
    }

    @And("get tier list by api")
    public void getTierListByApi() {
        tierInfo = tierSteps.getTierListTierApi();
        System.out.println("tier list: " + tierInfo);
    }


    @And("cancel order on hive-pbase and verify Tier")
    public void cancelOrderOnHivePbaseAndVerifyTier(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
//            Integer quantity = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Quantity"));
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base Product");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            int quantity = Integer.parseInt(LoadObject.getProperty("quantityCancel"));

            pre = tierSteps.getTierInformationOfUserByApi();
            System.out.println("order id: " + orderId);
//            pBaseOrderStep.navigateToPBase(orderId);
            pBaseOrderStep.navigateToCancelOrderPBase(orderId);
//            pBaseOrderStep.clickBtnCancelOrderPBase();
            pBaseOrderStep.chooseQuantity(quantity);
            pBaseOrderStep.cancelOrderPBase();
            double coin = tierSteps.convertCoin(baseProduct, quantity);

            current = tierSteps.getTierInformationOfUserByApi();
            tierSteps.verifyBcoin(pre, current, action, coin);
            tierSteps.verifyTierLevel(tierInfo, pre, current);
        }

    }

    @Then("verify user tier information")
    public void verifyUserTierInformation() {
        tierSteps.clearCache();
        tierDashboardSteps.refreshPage();
        pre = tierSteps.getTierInformationOfUserByApi();
        tierList = tierSteps.getTierListTierApi();
        String avaiBcoin = pre.get("AvailableBCoin");
        String bCoin = pre.get("BCoin");
        String tier = pre.get("Tier");
        String tierName = "";
        for (String i : tierList.keySet()) {
            if (tier.equals(i)) {
                List<String> tierInfo = tierList.get(i);
                tierName = tierInfo.get(0);
            }
        }
        tierDashboardSteps.verifyAvailableBcoin(avaiBcoin);
        tierDashboardSteps.verifyBcoin(bCoin);
        tierDashboardSteps.verifyNameTier(tierName);
    }

    @When("Verify list Tier Benefits")
    public void verifyListTierBenefits(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tier = SessionData.getDataTbVal(dataTable, row, "Tier");
            String benefits = SessionData.getDataTbVal(dataTable, row, "Benefits");
            tierDashboardSteps.verifyStatusofBenefits(tier, benefits);
        }
    }

    @Then("Verify display Redeem Rewards popup")
    public void verifyDisplayRedeemRewardsPopup() {
        tierDashboardSteps.verifyDisplayRedeemRewardsPopup();
    }

    @Then("Redeem Rewards form Redeem Rewards popup")
    public void redeemRewardsFormRedeemRewardsPopup() {
        tierDashboardSteps.verifyDisplayAvailableBcoin();
        coinRedeem = tierDashboardSteps.getCoinRedeem();
        availableBcoin = tierDashboardSteps.getAvailableBcoin();
        amountReward = tierDashboardSteps.getAmountReward();

        tierDashboardSteps.verifyStatusRedeemButton();
        Boolean isEnableBtnRedeem = tierDashboardSteps.getStatusBtnRedeem(availableBcoin);
        if (isEnableBtnRedeem) {
            tierDashboardSteps.clickOnRedeemButtonOnTheRedeemPopup();
            tierDashboardSteps.clickOnRedeemButtonOnTheConfirmScreen();
            tierDashboardSteps.clickOnSeeYourBalanceButton();
            tierDashboardSteps.verifyInvoiceWhenRedeemSuccess(amountReward);
            commonSteps.backToThePreviousScreen();
            tierDashboardSteps.verifyAvailableBcoinAfterRedeem(availableBcoin,coinRedeem);
        }
    }
}

