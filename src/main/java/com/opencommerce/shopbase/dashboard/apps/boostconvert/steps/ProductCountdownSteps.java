package com.opencommerce.shopbase.dashboard.apps.boostconvert.steps;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.pages.ProductCountdownPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ProductCountdownSteps extends ScenarioSteps {
    ProductCountdownPage productCountdownPage;


    @Step
    public void clickButtonSave() {
        productCountdownPage.saveSetting("Your settings was updated successfully");
    }

    @Step
    public void turnOnProductCountdown(String isTurnOn) {
        boolean isOn = Boolean.parseBoolean(isTurnOn.toLowerCase());
        productCountdownPage.turnOnToggleWithLabel(isOn);
        waitABit(1000);
    }


    public void setItemsLeftInStock(String settingItemsLeftInStock, String itemsLeftInStock) {
        productCountdownPage.selectItemsLeftInStock(settingItemsLeftInStock);
        if (!settingItemsLeftInStock.equalsIgnoreCase("Show your actual number of stocks")) {
            productCountdownPage.clickBtnChange();
            productCountdownPage.inputRangeOfItems(itemsLeftInStock);
        }
    }
}
