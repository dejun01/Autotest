package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.WidgetDashboardPage;

public class WidgetDashboardSteps {
    WidgetDashboardPage widgetDashboardPage;

    public void enterList(String lists) {
        String list[] = lists.split("<");
        for (int i = 0; i < list.length; i++) {
            String value[] = list[i].split(",");
            widgetDashboardPage.enterTitle(value[0], i + 1);
            widgetDashboardPage.enterDescription(value[1], i + 1);
            widgetDashboardPage.enterLink(value[2], i + 1);
            widgetDashboardPage.enterImageURL(value[3], i + 1);
            if (i < list.length - 1) {
                widgetDashboardPage.clickButtonAddNewPrice();
            }
        }
    }

    public void deleleAllList() {
        int count = widgetDashboardPage.getNumberOfList();
        for (int i = 0; i < count - 1; i++) {
            widgetDashboardPage.clickLinkTextWithLabel("Delete");
        }
    }

    public void selectWidgetType(String type) {
        widgetDashboardPage.selectDdlWithLabel("Widget Type", type);
    }

    public void uploadImage(String image) {
        widgetDashboardPage.uploadImage(image);
    }

    public void verifyCreateWidgetPrinthubSuccess() {
        widgetDashboardPage.verifyCreateWidgetPrinthubSuccess();
    }

    public void selectStartDate(String id, String value) {
        String startdate[] = value.split(">");
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_startDate_date_year", startdate[0]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_startDate_date_month", startdate[1]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_startDate_date_day", startdate[2]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_startDate_time_hour", startdate[3]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_startDate_time_minute", startdate[4]);

    }

    public void selectEndDate(String id, String value) {
        String enddate[] = value.split(">");
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_endDate_date_year", enddate[0]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_endDate_date_month", enddate[1]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_endDate_date_day", enddate[2]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_endDate_time_hour", enddate[3]);
        widgetDashboardPage.selectDroplist("s2id_sb516eeab1c_endDate_time_minute", enddate[4]);
    }

    public void clickEditBtn(String widgetId) {
        widgetDashboardPage.clickEditBtn(widgetId);
    }
}
