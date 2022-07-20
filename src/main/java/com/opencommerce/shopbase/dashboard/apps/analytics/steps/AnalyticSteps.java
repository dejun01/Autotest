package com.opencommerce.shopbase.dashboard.apps.analytics.steps;

import com.opencommerce.shopbase.dashboard.apps.analytics.pages.AnalyticsPage;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyticSteps extends ScenarioSteps {
    AnalyticsPage aPage;

    @Step
    public String logInfor(String s) {
        return s;
    }

    @Step
    public String getMainChartValue(String chartName) {
        return aPage.getMainChartValue(chartName);
    }

    @Step
    public String getValueOnlineStoreConversionRate(String valueName) {
        return aPage.getValueOnlineStoreConversionRate(valueName);
    }

    @Step
    public String getValueTopProduct(String productName) {
        return aPage.getValueTopProduct(productName);
    }

    @Step
    public void openShopOnNewTab(String shop) {
        aPage.openShopOnNewTab(shop);
    }

    @Step
    public void verifyDataAnalyticsChanged(double dataInit, double dataIncreaseExpect, double dataActual) {
        assertThat(dataInit + dataIncreaseExpect).isEqualTo(dataActual);
    }

    @Step
    public void switchToTheFirstTab() {
        aPage.switchToTheFirstTab();
    }

    @Step
    public void refreshPage() {
        waitABit(10000);
        aPage.refreshPage();
        aPage.waitForPageLoad();
    }

    @Step
    public void switchToLastestTab() {
        aPage.switchToLatestTab();
    }

    @Step
    public void verifyOnlineStoreConversionRate(double onlineStoreConversionRateActual, double sessionConvertedActual, double viewProductActual) {
        aPage.verifyOnlineStoreConversionRate(onlineStoreConversionRateActual, sessionConvertedActual, viewProductActual);
    }

    @Step
    public void verifyAverageOrderValue(double averageOrderValueActual, double totalSaleActual, double totalOrderActual) {
        aPage.verifyAverageOrderValue(averageOrderValueActual, totalSaleActual, totalOrderActual);
    }

    @Step
    public void verifyAverageOrderItem(double averageOrderItemActual, double topProductActual, double totalOrderActual) {
        aPage.verifyAverageOrderItem(averageOrderItemActual, topProductActual, totalOrderActual);

    }

    @Step
    public String getValueFromAbandonedCheckoutRecoveryChart(String eventName) {
        return aPage.getValueFromAbandonedCheckoutRecoveryChart(eventName);
    }

    @Step
    public String getIndicatorFromAbandonedCheckoutsRecoveryChart(String indicator) {
        return aPage.getIndicatorFromAbandonedCheckoutsRecoveryChart(indicator);
    }

    private double roundOff(double s) {
        return Math.round(s * 100.0) / 100.0;
    }

    @Step
    public void verifyRecoveryRate(String recoveryRateAfter, String emailSentAfter, String orderCompletedAfter) {
        Double expectedRecoveryRate = roundOff(Double.parseDouble(orderCompletedAfter) / Double.parseDouble(emailSentAfter));
        if (recoveryRateAfter.endsWith("%")) {
            recoveryRateAfter = recoveryRateAfter.replace("%", "");
        }
        assertThat(Double.parseDouble(recoveryRateAfter)).isEqualTo(expectedRecoveryRate);
    }


    public String getLiveData(String type) {
        return aPage.getLiveData(type);
    }

    public String getLiveVisitor() {
        return aPage.getLiveVisitor();
    }

    public String getLiveViewConversionRateByAPI(String action) {
        return aPage.getLiveViewConversionRateByAPI(action);
    }

    public String getDataFirstAndReturningCusInit(String filterBy, String customerType) {
        return aPage.getDataCustomerAnalyticsByAPI(filterBy, customerType);
    }

    public String getDataReturningRateInit(String filterBy, String customerType) {
        return aPage.getDataCustomerAnalyticsByAPI(filterBy, customerType);
    }

    public String getDataFirstAndReturningCusAfter(String filterBy, String customerType) {
        return aPage.getDataCustomerAnalyticsByAPI(filterBy, customerType);
    }

    public String getDataReturningRateAfter(String filterBy, String customerType) {
        return aPage.getDataCustomerAnalyticsByAPI(filterBy, customerType);
    }

    public String getDashboardReturningRate() {
        return aPage.getDashboardReturningRate();
    }

    @Step
    public void verifyUtmSource(String utmSource) {
        aPage.verifyUTM("Source", utmSource);
    }

    @Step
    public void verifyUtmMedium(String utmMedium) {

        aPage.verifyUTM("Medium", utmMedium);
    }

    @Step
    public void verifyUtmCampaign(String utmCampaign) {

        aPage.verifyUTM("Campaign", utmCampaign);
    }

    @Step
    public void verifyUtmSourceIdentifier(String utmSourceIdentifier) {
        aPage.verifyUTM("Source identifier", utmSourceIdentifier);
    }

    @Step
    public void verifyUtmReferringSite(String utmReferringSite) {
        aPage.verifyUTM("Referring site", utmReferringSite);
    }

    public void verifyUtmFirstPageVisited(String utmFirstPageVisited) {
        aPage.verifyUtmFirstPageVisited(utmFirstPageVisited);
    }

    public void selectDdlWithLabel(String option) {
        aPage.selectDdlWithLabel(option);
    }

    public void choiceOptionReportBy(String option) {
        aPage.choiceOptionReportBy(option);
    }

    public void addColumn(String column) {
        aPage.addColumn(column);
    }

    public String getActualValue(int i) {
        return aPage.getActualValue(i);
    }

    public int getIndexColumn() {
        return aPage.getIndexColumn();
    }

    public String getVisitorsBefore() {
        return aPage.getVisitorsBefore();
    }

    public String getValueBefore(int i, String valueAdd, String value) {
        return aPage.getValue(i, valueAdd, value);
    }

    public String getValueAfter(int i, String valueAdd, String value) {
        return aPage.getValue(i, valueAdd, value);
    }

    @Step
    public void verifyOverviewContent() {
        aPage.verifyOverviewContent();
    }

    public JsonPath getAnalyticsAPI(String shopType, String reportType) {
        return aPage.getAnalyticsAPI(shopType, reportType);
    }

    public String getDataAnalyticsByAPI(JsonPath json, String source, String key) {
        return aPage.getDataAnalyticsByAPI(json, source, key);
    }
}


