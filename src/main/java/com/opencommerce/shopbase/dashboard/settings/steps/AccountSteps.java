package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.AccountPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;



public class AccountSteps {
    AccountPage accountPage;

    @Step
    public void clickBTUpgradePlan() {
        accountPage.clickBTUpgradePlan();
    }

    @Step
    public void refreshPage() {
        accountPage.refreshPage();
    }

    @Step
    public String choosePackage(String typePlan, String typeText) {
        return accountPage.choosePackage(typePlan, typeText);
    }

    @Step
    public void clickBTTypePlan(String typePlan) {
        accountPage.clickBTTypePlan(typePlan);
    }

    @Step
    public void inputDiscount(String valueCoupon) {
        if (!valueCoupon.isEmpty()) {
            accountPage.inputDiscount(valueCoupon);
        }
    }

    @Step
    public void clickBTApply() {
        accountPage.clickBTApply();
    }

    @Step
    public void clickBTConfirmChanges() {
        accountPage.clickBTConfirmChanges();
        accountPage.waitUntilInvisibleLoading(7);
    }

    @Step
    public String roundOff(double d) {
        return String.format("%1.2f", d);
    }

    @Step
    public String getPriceBeforeInputCoupon(String typePlan) {
        if (typePlan.equals("Monthly")) {
            return accountPage.getPriceBeforeInputCoupon().replace("$", "");
        } else {
            return accountPage.getPriceBeforeInputCoupon();
        }
    }

    @Step
    public String getPriceAfterInputCoupon() {
        return accountPage.getPriceAfterInputCoupon().replace("$", "");
    }

    @Step
    public String getError() {
        return accountPage.getError();
    }

    @Step
    public String getTextInfoTime(String text) {
        String textInfo = accountPage.getTextInfo();
        String[] c = textInfo.substring(textInfo.lastIndexOf("from") + 5, textInfo.length() - 1).split("to");
        if (text.equals("fromDay")) {
            return c[0].trim();
        } else {
            return c[1].trim();
        }
    }

    @Step
    public String getTextTheNextSubscriptionBill() {
        return accountPage.getTextTheNextSubscriptionBill();
    }

    @Step
    public void clickBtnPlanType(String typePlan) {
        accountPage.clickBtnPlanType(typePlan);
    }

    @Step
    public void choosePlan(String typePackage) {
        accountPage.choosePlan(typePackage);
    }

    @Step
    public void clickButtonYesIWantThisOption() {
        accountPage.clickBtn("Yes, I want this option");
    }

    @Step
    public void verifyBlockSellMoreWithAnOnlineStoreIsDisplayed(String btnName) {
        accountPage.verifyTextPresent("Sell more with an online store", true);
    }

    @Step
    public void verifyPackagesIsDisplayed(String aPackage) {
        // aPackage = Fulfillment Only,Base,Standard,Pro
        String[] packageName = aPackage.split(",");
        for (String _packageName : packageName) {
            accountPage.verifyTextPresent(_packageName, true);
        }

    }

    @Step
    public void clickButtonConfirmNewPlan() {
        accountPage.clickBtn("Confirm new plan");
    }

    @Step
    public void goToViewHistoryPage() {
        accountPage.goToViewHistoryPage();
    }


    public double calculateAmount() {

        return 0;
    }

    public String getCreatedDate() {
        return "";
    }

    public int getRowInvoices(String type, String shopname, String content, String status) {
        return accountPage.getRowInvoice(type, shopname, content, status);
    }

    public void verifyAmountRefunded(Double amount, int rowIndex) {
        int colIndex = accountPage.getColumOfInvoiceTableByLabel("Amount");
        double actualAmount = accountPage.getActualAmountRefund(rowIndex, colIndex);
        assertThat(actualAmount).isEqualTo(amount);
    }

    @Step
    public double getPriceOfCurrentPackage() {
        return Double.parseDouble(accountPage.getPriceOfCurrentPackage().replace("$", ""));
    }
    public void clickViewActivityLog() {
        accountPage.clickViewActivityLog();
    }

    public void filterActivityLog(String module,String activity) {
        accountPage.filterActivityLog(module,activity);
    }

    public void clickOnLastestActivity(String detail) {
        accountPage.clickOnLastestActivity(detail);
    }

    public void verifyActivityLog(String module, String activity, String detail) {
        accountPage.verifyActivityLog(module,activity,detail);
    }

    public void getLogDetail() {
        accountPage.getLogDetail();
    }

    public void verifyLogDetail(String attribute, String value) {
        accountPage.verifyLogDetail(attribute,value);
    }

    public void getCurrentPlan() {
        accountPage.getCurrentPlan();
    }

    public void closeStore() {
        accountPage.closeStore();
    }

    public void reopenStore() {
        accountPage.reopenStore();
    }

    @Step
    public void navigateToProfilePage() {
        accountPage.navigateToProfilePage();
    }

    public void changeFirstNameAndLastName() {
        accountPage.changeFirstNameAndLastName();
    }

    public void changePhoneNumber() {
        accountPage.changePhoneNumber();
    }
    @Step
    public boolean ableToSelectPackage(String packageName) {
        return accountPage.ableToSelectPackage(packageName);
    }

    public String getTotalActivityLogByAPI() {
        return accountPage.getTotalActivityLogByAPI();
    }

    public void changeFirstNameTo(String firstName) {
        accountPage.changeFirstNameTo(firstName);
    }

    public void clickSwitchToAnotherShopbutton() {
        accountPage.clickSwitchToAnotherShop();
    }
    public void clickBTStartPlan() {
        accountPage.clickBtn("Start plan");
    }
    public void clickAccountInTheSelectPlan() {
        accountPage.clickAccountInTheSelectPlan();
    }

    public void verifyDisplayReopenStoreWhenStoreNotEndSub() {
        accountPage.verifyDisplayReopenStoreWhenStoreNotEndSub();
    }
}

