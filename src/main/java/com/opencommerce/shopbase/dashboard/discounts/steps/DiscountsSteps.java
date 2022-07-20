package com.opencommerce.shopbase.dashboard.discounts.steps;

import com.opencommerce.shopbase.dashboard.discounts.pages.DiscountsPages;
import net.thucydides.core.steps.ScenarioSteps;

public class DiscountsSteps extends ScenarioSteps {
    DiscountsPages discountsPages;
    public void selectAllDiscount() {
        discountsPages.selectAllDiscount();
    }

    public void clickBtnActions() {
        discountsPages.clickBtnActions();
    }

    public void clickDelete() {
        discountsPages.clickDelete();
    }

    public void clickBtnCreateDiscount() {
        discountsPages.clickBtnCreateDiscount();
    }

    public void inputDiscountTitle(String title) {
        discountsPages.inputDiscountTitle(title);
    }

    public void selectDiscountType(String type) {
        discountsPages.selectDiscountType(type);
    }

    public void clickBtnSave() {
        discountsPages.clickBtn("Save");
    }

    public void confirmDelete() {
        discountsPages.clickBtn("Delete");
    }

    public int countDiscount() {
       return discountsPages.countDiscount();
    }

    public void selectValueType(String valueType) {
        discountsPages.selectValueType(valueType);
    }

    public void inputValue(String discountValue) {
        discountsPages.inputValue(discountValue);

    }

    public void checkMaxDiscount(boolean maxDiscount) {
        discountsPages.checkCheckboxWithLabel("Maximum discount amount",maxDiscount);
    }

    public void inputMaxValue(String maxValue) {
        discountsPages.inputMaxValue(maxValue);
    }

    public void verifyDiscountCreated() {
        discountsPages.verifyDiscountCreated();
    }
}
