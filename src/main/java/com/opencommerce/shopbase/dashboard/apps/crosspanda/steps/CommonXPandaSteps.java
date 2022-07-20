package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.CommonXPandaPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.ArrayList;

public class CommonXPandaSteps extends ScenarioSteps {
    CommonXPandaPages commonXPandaPages;

    public void switchToMenu(String nameMenu) {
        commonXPandaPages.waitForEverythingComplete();
        commonXPandaPages.switchToMenu(nameMenu);
        commonXPandaPages.waitForPageLoad();
        commonXPandaPages.waitForEverythingComplete();
    }

    public void switchToMenuLevel2(String tab) {
        commonXPandaPages.waitForEverythingComplete();
        waitABit(3000);
        commonXPandaPages.switchToMenuLevel2(tab);
        commonXPandaPages.waitForPageLoad();
        commonXPandaPages.waitForEverythingComplete();
    }

    String email = LoadObject.getProperty("xp.email");
    String pass = LoadObject.getProperty("xp.pass");
    String apiURL = LoadObject.getProperty("gapi.url");
    String userId = LoadObject.getProperty("user_id");

    public Response getResponeCredentials() {

        String api = apiURL + "/xauth/credentials";
        JsonObject getBody = new JsonObject();
        getBody.addProperty("username", email);
        getBody.addProperty("password", pass);
        return commonXPandaPages.postAPI(api, getBody);
    }

    public String getAccessTokenXpanda() {
        Response response = getResponeCredentials();
        String access_token = response.then().extract().path("access_token");
        return access_token;
    }

    @Step
    public ArrayList getSaleOrderNumber(String quotationId, String accToken) {
        String api = apiURL + "/v1/panda/sale-orders?user_id=" + userId + "&page=1&key=" + quotationId + "&access_token=" + accToken + "";
        JsonPath js = commonXPandaPages.getAPI(api);
        ArrayList arr = commonXPandaPages.getDataByKey(js, "result.sale_order_number");
        return arr;
    }

    @Step
    public ArrayList getSaleOrderID(String quotationId, String accToken) {
        String api = apiURL + "/v1/panda/sale-orders?user_id=" + userId + "&page=1&key=" + quotationId + "&access_token=" + accToken + "";
        JsonPath js = commonXPandaPages.getAPI(api);
        ArrayList arr = commonXPandaPages.getDataByKey(js, "result.id");
        return arr;
    }

    @Step
    public int getProductIdAPI(int itemId, String accToken) {
        String api = apiURL + "/v1/panda/sale-orders?user_id=" + userId + "&page=1&key=I" + itemId + "&access_token=" + accToken;
        logInfor(api);
        JsonPath js;
        Object rs;
        int i = 1;
        do {
            waitABit(5000);
            js = commonXPandaPages.getAPI(api);
            rs = commonXPandaPages.getData(js, "result.product_id[0]");
            i++;
            if (i == 10) {
                break;
            }
        } while (rs == null);

        int idProduct = (int) commonXPandaPages.getData(js, "result.product_id[0]");
        return idProduct;
    }

    @Step
    public void logInfor(String api) {
    }

    @Step
    public void waitUntilLoadingSuccessfully() {
        commonXPandaPages.waitUntilInvisibleLoading(20);
        commonXPandaPages.waitForEverythingComplete();
    }

    @Step
    public void refreshPage() {
        commonXPandaPages.refreshPage();
        commonXPandaPages.waitForPageLoad();
    }

    @Step
    public void closeSmartBarCP() {
        if (commonXPandaPages.isSmartBar()) {
            commonXPandaPages.closeSmartBarCP();
        }
    }

    @Step
    public void switchToTabByName(String tabName) {
        commonXPandaPages.waitForEverythingComplete();
        waitABit(5000);
        commonXPandaPages.switchToTab(tabName);
        commonXPandaPages.waitForEverythingComplete();
    }

    public void removeBaseProduct(String baseProduct) {
        String gapi = LoadObject.getProperty("xp.gapi.url");
        String api = gapi + "/v1/qctool/panda/delete-base-product";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("handle", baseProduct);
        commonXPandaPages.postAPI(api, requestParams);
    }
}
