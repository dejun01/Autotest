package com.opencommerce.shopbase.dashboard.online_store.pages.steps;

import com.opencommerce.shopbase.dashboard.online_store.pages.pages.PagesPage;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class PagesSteps {
    PagesPage pagesPage;

    @Step
    public void choosePage(String page) {
        pagesPage.clickPage(page);
    }

    @Step
    public String getCurrentURL() {
        return pagesPage.getCurrentURL();
    }

    @Step
    public void changeURLPage(String newURL, boolean isCreate) {
        pagesPage.enterNewURL(newURL);
        if (isCreate == true) {
            pagesPage.checkCheckboxWithLabel("Create a URL redirect from");
        }
        pagesPage.clickBtn("Save");
        pagesPage.verifySavePageSuccess();
    }

    public List<String> getListPages(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/pages.json";
        JsonPath pagesList = pagesPage.getAPISbase(api, accessToken);
        return pagesList.get("pages.title");
    }
}
