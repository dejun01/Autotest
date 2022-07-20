package com.opencommerce.shopbase.dashboard.settings.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.TippingPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.dashboard.settings.pages.PaymentsSettingPages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TippingSteps extends ScenarioSteps {
    TippingPages tippingPage;

    @Step
    public void showTippingOptions(boolean isShowing) {
        tippingPage.checkCheckboxWithLabel("Show tipping options at check out", isShowing);
    }

    @Step
    public void saveTippingOptions() {
        tippingPage.saveChanges();
    }

    @Step
    public void verifyTippingUI(boolean isShowing) {
        tippingPage.verifyTippingOptionDisplayed(isShowing);
    }

    @Step
    public void enterTippingOption(int index, String value) {
        tippingPage.enterInputFieldWithLabel("Option " + index, value);
    }

    @Step
    public void verifyMessageAfterEnterInput(int index, String expectedMessage) {
        tippingPage.verifyMessageAfterEnterInput(index, expectedMessage);
    }

    @Step
    public void verifyTippingOptionAfterSaved(int index, String expectedValue) {
        tippingPage.verifyTippingOptionAfterSaved(index, expectedValue);
    }

    @Step
    public void verifyTippingIsNotSelected(String labelName) {
        tippingPage.verifyTippingBlockSelected(labelName, false);
        tippingPage.verifyDisplayOfTipLine(false);
    }

    @Step
    public void verifyLabelName(String expectedLabelName) {
        tippingPage.verifyLabelName(expectedLabelName);
    }

    @Step
    public void verifyDisplayOfTipLine(boolean isTipLineDispayed) {
        tippingPage.verifyDisplayOfTipLine(isTipLineDispayed);
    }

    @Step
    public void clickOnTippingBlock() {
        tippingPage.clickOnTippingBlock();
    }

    @Step
    public void verifyTippingOptionOnSFWhenEnable(int numberOfTippingOptionsOnDashboard) {
        tippingPage.verifyNumberOfTippingOptionsOnSF(numberOfTippingOptionsOnDashboard);
//        tippingPage.verifyButtonAddCustomTipOnSF("Add tip", "Disabled");
    }

    @Step
    public void verifyTippingValueOnSF(int index, double tippingOption) {
        tippingPage.verifyTippingValueOnSF(index, tippingOption);
    }

    @Step
    public void clickToAddTippingOption(int index) {
        tippingPage.clickToAddTippingOption(index);
        tippingPage.verifyDisplayOfTipLine(true);
        tippingPage.verifyTippingAmountOnCartSummary(index);
    }

    @Step
    public void clickButtonApply() {
        tippingPage.clickBtn("Apply");
        tippingPage.waitUntilApplyDiscountSucessfully();
    }

    @Step
    public void enterDiscountCode(String discountCode) {
        if (!discountCode.isEmpty()) {
            tippingPage.enterInputFieldWithLabel("Enter your discount code here", discountCode);
            clickButtonApply();
        }
    }

    @Step
    public double getTippingAmountOnCartSummary() {
        return tippingPage.getTippingAmountOnCartSummary();
    }


}
