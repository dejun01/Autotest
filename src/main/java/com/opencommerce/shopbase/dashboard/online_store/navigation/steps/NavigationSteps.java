package com.opencommerce.shopbase.dashboard.online_store.navigation.steps;

import com.opencommerce.shopbase.dashboard.online_store.navigation.pages.NavigationPages;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

public class NavigationSteps extends ScenarioSteps {
    NavigationPages navigationPages;

    public List<String> getNavigation(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/menus.json";
        JsonPath navigationList = navigationPages.getAPISbase(api, accessToken);
        return navigationList.get("menus.title");
    }

    public void entertitle(String title) {
        navigationPages.enterInputFieldWithLabel("Title", title);
    }

    public void clickSaveMenu() {
        navigationPages.clickBtn("Save menu");
    }

    public void clickAddMenu() {
        navigationPages.clickAddMenu();
    }
}
