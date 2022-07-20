package com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell.ProductWidgetPage;
import net.thucydides.core.annotations.Step;

public class ProductWidgetSteps {
    ProductWidgetPage widgetPage;

    @Step
    public boolean convertStatus(
            String status) {
        boolean st = false;
        if (status.equalsIgnoreCase("on")) {
            st = true;
        }
        return st;
    }

    public void turnOnWidgetWithName(String widgetName, boolean isOn) {
        widgetPage.turnOnWidgetWithName(widgetName, isOn);
        widgetPage.waitForEverythingComplete();
    }

    public void clickBtnCustomize(String widgetName) {
        widgetPage.clickBtnCustomize(widgetName);
    }
}
