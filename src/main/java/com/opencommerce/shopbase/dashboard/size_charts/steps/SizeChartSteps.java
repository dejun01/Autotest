package com.opencommerce.shopbase.dashboard.size_charts.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.dashboard.size_charts.pages.SizeChartPage;

public class SizeChartSteps extends ScenarioSteps {
    SizeChartPage sizechartPage;

    @Step
    public void addSizeChart() {
        sizechartPage.clickBtn("Add size chart");
        sizechartPage.waitForPageLoad();
    }

    @Step
    public void enableSizeChart() {
        sizechartPage.enableSizeChart();
    }

    @Step
    public void disableSizeChart() {
        sizechartPage.disableSizeChart();
    }

    @Step
    public void inputStyle(String style) {
        sizechartPage.inputStyle(style);
    }

    @Step
    public void addImage(String image) {
        sizechartPage.addImage(image);
    }

    @Step
    public void verifyMessage(String message) {
        sizechartPage.verifyMessage(message);
    }

    @Step
    public void clickBtnSave() {
        sizechartPage.clickBtn("Save");
        sizechartPage.waitUntilElementVisible("//button//span[normalize-space()='Assign to products']", 10);
    }

    @Step
    public void assignToProduct() {
        sizechartPage.waitForPageLoad();
        sizechartPage.waitUntilElementVisible("//button//span[normalize-space()='Assign to products']", 20);
        sizechartPage.clickBtn("Assign to products");
    }

    @Step
    public void selectAllSizeChart() {
        sizechartPage.selectAllSizeChart();
    }

    @Step
    public int countNumberOfSizeChart() {
        return sizechartPage.countNumberOfSizeChart();
    }

    @Step
    public void chooseActionDeleteSelectedProduct() {
        sizechartPage.chooseAction("Delete selected size charts");
    }

    @Step
    public void verifyMsgNoSizeChart() {
        sizechartPage.verifyMsgNoSizeChart();
    }

    @Step
    public void verifyShowSizeChart(boolean show) {
        sizechartPage.verifyShowSizeChart(show);
    }

    @Step
    public void selectProdProperties(String prodProperties) {
        sizechartPage.selectDdProductProperties(prodProperties);
    }

    @Step
    public void selectDdColCondition(String cons) {
        sizechartPage.selectDdColCondition(cons);
    }

    @Step
    public void inputConValue(String conValue) {
        sizechartPage.inputConditionValue(conValue);
    }

    @Step
    public String getTag() {
        return sizechartPage.getTag();
    }

    @Step
    public void refreshPage() {
        sizechartPage.refreshPage();
    }

}
