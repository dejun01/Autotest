package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.SettingPages;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;


public class SettingSteps extends ScenarioSteps {
    SettingPages sPage;

    @Step
    public void clickToSection(String tab) {
        sPage.clickToSection(tab);
    }

    @Step
    public void selectCurrency(String currency) {
        sPage.selectDdlWithLabelAtSettingsScreen("Store currency", currency);
    }

    @Step
    public void clickSave() {
        if (sPage.isClickableBtn("Save")) {
            sPage.clickBtn("Save");
            sPage.waitForEverythingComplete();
            sPage.verifySaveSuccess();
        }
    }

    @Step
    public void clickAddStaffAccount() {
        sPage.clickAddStaffAccount();
    }

    @Step
    public String enterStaffEmail(String staffEmail) {
        sPage.enterStaffEmail(staffEmail);
        System.out.println(staffEmail);
        return staffEmail;
    }

    @Step
    public void clickSendInvite() {
        sPage.clickSendInvite();
    }

    @Step
    public void refreshPage() {
        sPage.refreshPage();
    }

    @Step
    public void verifyErrorMsg(String msg) {
        sPage.verifyErrorMsg(msg, 0);
    }

    @Step
    public void clickOnAddProviderBtn() {
        sPage.clickOnAddProviderBtn();
    }

    @Step
    public void selectProviderRadioBtn(String label) {
        sPage.selectProviderRadioBtn(label);
    }

    @Step
    public void selectProvider(String providerName) {
        sPage.selectProviderFromDropdown(providerName);
    }

    @Step
    public void clickOnContinueBtn() {
        sPage.clickOnContinueBtn();
    }

    @Step
    public void clickOnActivateBtn() {
        sPage.clickOnActivateBtn();
    }

    @Step
    public void completeActiveProviderInStripeSite() {
        sPage.completeActiveProviderInStripeSite();
    }

    @Step
    public void clickOnShopNavigatingIcon() {
        sPage.clickOnShopNavigateingIcon();
    }

    @Step
    public void switchToWindowTabWithIndex(int index) {
        sPage.switchToWindowTabWithIndex(index);
    }

    @Step("return original window")
    public String getOriginalTabID() {
        return sPage.getOriginalTabID();
    }

    @Step
    public void closeBrowserTabWithIndex(int index) {
        sPage.closeBrowserTabWithIndex(index);
    }

    @Step
    public void clickOnHyperLink(String hyperlinkName) {
        sPage.clickOnHyperLink(hyperlinkName);
    }

    @Step
    public void verifyTransactionFeeInCurrentInvoice(String orderNo, double trxRate, double trxFee) {
        sPage.verifyTransactionFeeInCurrentInvoice(orderNo, trxRate, trxFee);
    }

    @Step
    public void expandOrderListByClickingOnOrderHyperLink() {
        sPage.expandOrderListByClickingOnOrderHyperLink();
    }

    @Step
    public void clickOnConfirmChangePlanBtn() {
        sPage.clickOnConfirmChangePlanBtn();
    }

    @Step
    public void verifyInvoiceName() {
        sPage.verifyInvoiceName();
    }

    @Step
    public void verifyInvoiceType(String invoiceType) {
        sPage.verifyInvoiceType(invoiceType);
    }

    @Step
    public void verifyInvoiceAmount(String amount) {
        sPage.verifyInvoiceAmount(amount);
    }

    @Step
    public void verifyInvoiceStatus(String status) {
        sPage.verifyInvoiceStatus(status);
    }

    @Step
    public void clickOnInvoiceNameInTheFirstRow() {
        sPage.clickOnInvoiceNameInTheFirstRow();
        sPage.refreshPage();
    }

    @Step
    public void verifyTotalTransactionFee(String totalTrxFee) {
        sPage.verifyTotalTransactionFee(totalTrxFee);
    }

    @Step
    public boolean verifyDisplayOfExpandOrderList() {
        boolean result = sPage.verifyDisplayOfExpandOrderList();
        return result;
    }


    @Step
    public void turnOnSendAbandonedCheckoutEmails() {
        if (!sPage.isChecked("Automatically send abandoned checkout emails")) {
            sPage.checkOnCheckbox("Automatically send abandoned checkout emails");
        }
    }

    @Step
    public void setUpEmailToSend(String ordinal, String send, String unit) {
        if (!sPage.isChecked(ordinal)) {
            sPage.checkOnCheckbox(ordinal);
            sPage.enterScheduleSend(ordinal, send);
            sPage.selectUnit(ordinal, unit);
            sPage.clickBtn("Save changes");

        }
    }

    @Step
    public void enterScheduleSend(String ordinal, String time) {
        sPage.enterScheduleSend(ordinal, time);
        sPage.waitForEverythingComplete();
    }

    @Step
    public void selectUnit(String ordinal, String unit) {
        sPage.selectUnit(ordinal, unit);
        sPage.waitForEverythingComplete();
    }

    @Step
    public boolean isChanged() {
        return sPage.isChanged();
    }

    @Step
    public void saveChanges() {
        sPage.clickBtn("Save changes");
        sPage.waitForTextToAppear("All changes were successfully saved");
        sPage.waitForEverythingComplete();
    }

    @Step
    public void turnOnSendAbandonedCheckoutSMS() {
        if (!sPage.isChecked("Automatically send abandoned checkout text messages")) {
            sPage.checkCheckboxWithLabel("Automatically send abandoned checkout text messages");
            sPage.clickBtn("I understand");
            sPage.waitForEverythingComplete();
        }
    }

    @Step
    public void checkOnCheckbox(String label, boolean isChecked) {
        sPage.checkOnCheckbox(label, isChecked);
        sPage.waitForEverythingComplete();
    }

    @Step
    public void enterMsg(String ordinalMsg, String msg) {
        sPage.enterMessage(ordinalMsg, msg);
    }

    @Step
    public String getPrintBaseProcessingFeeRate() {
        return sPage.getPrintBaseProcessingFeeRate();
    }

    @Step
    public boolean isHyperLinkDisplay(String hyperlinkName) {
        return sPage.isHyperLinkDisplay(hyperlinkName);
    }

    public void chooseTheSection(String section) {
        sPage.chooseTheSection(section);
    }

    @Step
    public void verifyPhone(String phone) {
        sPage.verifyPhone(phone);
    }

    @Step
    public String getAccessToken() {
        return sPage.getAccessTokenUser();
    }

    public void verifyCurrency(String currency) {
        sPage.verifyCurrency(currency);
    }

    public void verifyCountry(String country) {
        sPage.verifyCountry(country);
    }

    public void clickCreateFromTemplate() {
        sPage.waitForPageLoad();
        sPage.clickBtn("Create from template");
    }

    public void checkedCheckboxShowlegal(String label, boolean isShow) {
        sPage.scrollToAddition();
        sPage.checkCheckboxWithLabel(label, isShow);
    }

    public void settingAdditional(String isManual, String isAuto) {
        if (isManual.equals("Yes"))
            sPage.selectRadioButtonWithLabel("Manual confirmation", true);
        else if (isAuto.equals("Yes")) {
            sPage.selectRadioButtonWithLabel("Auto confirmation", true);
        }
    }

    public void scrollToCompleOrder() {
        sPage.scrollToCopleteOrder();
    }

    public void verifyCheckoxTosCheckout() {
        sPage.verifyCheckedBoxTos(true);
    }

    public void verifyAutoTosCheckout() {
        sPage.verifyAutoTosCheckout();
    }

    public void verifyModelTermOfService() {
        sPage.verifyTermOfService();
    }

    public void verifyNotShowAdditionalCheckout() {
        sPage.verifyNotShowAdditional();
    }


}
