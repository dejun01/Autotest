package com.opencommerce.shopbase.dashboard.online_store.navigation.steps;

import com.opencommerce.shopbase.dashboard.online_store.navigation.pages.CustomFilterPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

public class CustomFilterSteps extends ScenarioSteps {
    CustomFilterPages customFilterPages;

    @Step
    public void verifyShowDroplistFilter(Boolean status) {
        customFilterPages.verifyShowDroplistFilter(status);
    }

    @Step
    public void clickDroplistFilter() {
        customFilterPages.clickDroplistFilter();
    }

    @Step
    public void verifyFilterTitle(String filterTitle) {
        customFilterPages.verifyFilterTitle(filterTitle);
    }

    @Step
    public int countOption() {
        return customFilterPages.countOption();
    }

    @Step
    public void removeOption() {
        customFilterPages.removeOption();
    }

    @Step
    public void clickButton(String text) {
        customFilterPages.clickButton(text);
    }

    @Step
    public boolean existShowMore() {
        return customFilterPages.existShowMore();
    }

    @Step
    public int countOptionPopup() {
        return customFilterPages.countOptionPopup();
    }

    @Step
    public void verifyFilter(int countOption, int countOptionPopup) {
        customFilterPages.verifyFilter(countOption, countOptionPopup);
    }

    @Step
    public void addFilter(int index) {
        customFilterPages.addFilter(index);
    }

    @Step
    public void existOptionFilter(Boolean status) {
        customFilterPages.existOptionFilter(status);
    }

    @Step
    public void checkCheckboxFilter(String text, boolean status) {
        customFilterPages.checkCheckboxWithLabel(text, status);
    }

    @Step
    public void inputPrice(String label, String text) {
        customFilterPages.inputPrice(label, text);
    }
    @Step
    public void verifyShowTag(String tag) {
        customFilterPages.verifyShowTag(tag);
    }

    @Step
    public void verifyNotShowTag(boolean status) {
        customFilterPages.verifyNotShowTag(status);
    }

    @Step
    public void verifyText(String results) {
        customFilterPages.verifyText(results);
    }

    @Step
    public void verifyResult(String nameProduct) {
        customFilterPages.verifyResult(nameProduct);
    }

    @Step
    public void reloadPage() {
        customFilterPages.refreshPage();
    }
}
