package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing.HomePages;
import net.thucydides.core.steps.ScenarioSteps;

public class HomeSteps extends ScenarioSteps {

    HomePages homePages;

    public void clickLinkToMoreStores() {
        homePages.clickLinkToMoreStores();
    }

    public void selectPlatform(String platform) {
        if(!platform.equals("ShopBase")){
            homePages.clickDropDownList();
            homePages.selectPlatform(platform);
        }
    }

    public void enterStoreURL(String store) {
        homePages.enterStoreURL(store);
    }

    public void verifyConnectSusscess() {
        homePages.verifyConnectSusscess();
    }
}
