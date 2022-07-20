package com.opencommerce.shopbase.common.steps;

import com.opencommerce.shopbase.common.pages.CommonHivePage;
import net.thucydides.core.annotations.Step;

public class CommonHiveSteps {
    CommonHivePage commonHivePage;

    @Step
    public void clickTab(String tabName) {
        commonHivePage.clickTab(tabName);
    }

    @Step
    public void clickAddNew() {
        commonHivePage.clickLinkTextWithLabel("Add new");
    }

    @Step
    public void clickCreate() {
        commonHivePage.clickBtn("Create");
    }

    @Step
    public void clickCreateAndReturnToList() {
        commonHivePage.clickBtn("Create and return to list");
    }

    @Step
    public void clickUpdateAndClose() {
//        commonHivePage.clickUpdateAndClose();
        commonHivePage.clickBtn("Update and close");
    }

    @Step
    public void enterInput(String label, String value) {
        commonHivePage.scrollIntoViewInput(label, 1);
        commonHivePage.enterInputFieldWithLabel(label, value);
    }

    @Step
    public void enterInput(String label, String value, int res) {
        commonHivePage.scrollIntoViewInput(label, res);
        commonHivePage.enterInputFieldWithLabel(label, value, res);
    }

    @Step
    public void checkCheckbox(String label, boolean isOn) {
        commonHivePage.scrollIntoViewCheckbox(label);
        commonHivePage.checkCheckboxWithLabel(label, isOn);
    }

    @Step
    public void enterTextarea(String label, String value) {
        commonHivePage.enterTextAreaWithLabel(label, value);
    }

    @Step
    public void selectDroplist(String label, String value) {
        commonHivePage.selectDroplist(label, value);
    }

    @Step
    public void verifyDetailPageDisplay(String page) {
        commonHivePage.verifyPageTitleDisplay(page);
    }

    @Step
    public void uploadImage(String label, String image) {
        commonHivePage.uploadImage(label, image);
    }

    @Step
    public void clickEdit(String id) {
        commonHivePage.clickEdit(id);
    }

    @Step
    public void verifyNameDisplay(String value) {
        commonHivePage.verifyNameDisplay(value);
    }

    @Step
    public void verifyNameDisplay(String id, String value) {
        commonHivePage.verifyNameDisplay(id, value);
    }


    @Step
    public String getIdItem(String name) {
        return commonHivePage.getIdItem(name);
    }

    @Step
    public void verifyFiledDisplay(String id, String slug) {
        commonHivePage.verifyFiledDisplay(id, slug);
    }

    @Step
    public void verifyActive(String id, Boolean isActive) {
        commonHivePage.verifyActive(id, isActive);
    }

    public void selectMultDroplist(String label, String value) {
        String va[] = value.split(",");
        for (int i = 0; i < va.length; i++) {
            selectDroplist(label, va[i]);
        }
    }

    public void clickButton(String label) {
        commonHivePage.clickBtn(label);
    }

    public void closeDatePicker(String label) {
        commonHivePage.closeDatePicker(label);
    }

    public void selectRadioButton(String label, boolean isOn) {
        commonHivePage.selectRadioButtonWithLabel(label, isOn);
    }

    public void inputDataForBaseProduct(String color, String size, String weight, String cost) {
        commonHivePage.inputDataForBaseProduct(color, size, weight, cost);
    }

    public void getDataOfBaseProduct() {
        commonHivePage.getDataOfBaseProduct();
    }

    public void clickBtnUpdate() {
        commonHivePage.clickBtnUpdate();
    }
}
