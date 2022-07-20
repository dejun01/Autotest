package com.opencommerce.shopbase.dashboard.apps.analytics.steps;

import com.opencommerce.shopbase.dashboard.apps.analytics.pages.AnalyticsPage;
import com.opencommerce.shopbase.dashboard.apps.analytics.pages.UTMAnalyticsPage;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class UTMAnalyticSteps extends ScenarioSteps {
    UTMAnalyticsPage utmAnalyticsPage;



    public JsonPath getUTMbyAPI(String shopType,String reportBy) {
        return utmAnalyticsPage.getUTMbyAPI(shopType,reportBy);
    }

    public String getDataUTM(JsonPath jsonPath, String reportBy, String utmValue, String keyValue) {
        return utmAnalyticsPage.getDataUTM(jsonPath,reportBy,utmValue,keyValue);
    }

    public void openLinkWithUTM(String productName, String utmSource, String utmMedium, String utmCampaign, String utmContent, String utmTerm,String referrer) {
        utmAnalyticsPage.openLinkWithUTM(productName,utmSource,utmMedium,utmCampaign,utmContent,utmTerm,referrer);
        utmAnalyticsPage.maximizeWindow();
    }

    @Step
    public JsonPath getDataUTMSaleReport(String shopType, String reportBy, String reportType) {
        return utmAnalyticsPage.getDataUTMSaleReport(shopType, reportBy, reportType);
    }

    @Step
    public String getDataUTMSaleReportInit(JsonPath js, String reportBy, String filterValue, String productFilter, String title, String keyValue) {
        return utmAnalyticsPage.getDataAnaUTMSalesReport(js, reportBy, filterValue, productFilter, title, keyValue);
    }

    @Step
    public String getDataUTMSaleReportAfter(JsonPath js, String reportBy, String filterValue, String productFilter, String title, String keyValue) {
        return utmAnalyticsPage.getDataAnaUTMSalesReport(js, reportBy, filterValue, productFilter, title, keyValue);
    }

    @Step
    public void chooseReportBy(String reportBy) {
        utmAnalyticsPage.selectDdlWithLabel("Report by", reportBy);
        waitABit(5000);
    }

    @Step
    public void verifyAddColumn(String additionalColumn) {
        int valueBefore = utmAnalyticsPage.countNumberOfUtmChartSalesReport();
        utmAnalyticsPage.selectDdlWithLabel("Add column", additionalColumn);
        int valueAfter = utmAnalyticsPage.countNumberOfUtmChartSalesReport();
        utmAnalyticsPage.verifyAdditionalColumn(valueBefore, valueAfter);
        utmAnalyticsPage.verifyValueInAdditionalColumn("custom_camp");
    }
}


