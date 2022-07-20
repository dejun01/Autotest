package com.opencommerce.shopbase.dashboard.apps.boostconvert.steps;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.pages.PopTypesPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class PopTypesSteps extends ScenarioSteps {
    PopTypesPage popTypesPage;

    @Step
    public void turnOnOffNotiType(String typeNoti, boolean status) {
        popTypesPage.turnOnOffNotiType(typeNoti, status);
    }

    @Step
    public void clickSettings(String labelName) {
        popTypesPage.waitUntilInvisibleLoading(10000);
        popTypesPage.clickSetting(labelName);
        popTypesPage.waitForPageLoad();
    }

    @Step
    public void enterTitle(String sTitle) {
        if (!sTitle.isEmpty())
            popTypesPage.enterInputFieldWithLabel("Title", sTitle);
    }

    @Step
    public void enterProductName(String sProductName) {
        if (!sProductName.isEmpty())
            popTypesPage.enterInputFieldWithLabel("Product name", sProductName);

    }

    @Step
    public void enterTime(String sTime) {
        if (!sTime.isEmpty())
            popTypesPage.enterInputFieldWithLabel("Time", sTime);
    }

    @Step
    public void triggerOnPages(String trigger, String customPages) {
        popTypesPage.selectOptionTrigger(trigger);

        if (trigger.equalsIgnoreCase("On pages I specify")) {
            String listpage = "";
            String shop = LoadObject.getProperty("shop");
            if (customPages.isEmpty()) {
                listpage = "https://" + shop;
            } else {
                String pages[] = customPages.split(",");
                for (String page : pages) {
                    listpage = listpage + "https://" + shop + page + "\n";
                }
            }
            popTypesPage.enterCustomPage(listpage);
        }
    }


    public void saveSetting() {
        popTypesPage.saveSetting();
    }

    public void enterMessage(String sMessage) {
        popTypesPage.enterInputFieldWithLabel("Message", sMessage);
    }

    public void clickResetToDefault() {
        popTypesPage.clickLinkTextWithLabel("Reset to default");
    }
}
