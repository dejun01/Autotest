package com.opencommerce.shopbase.dashboard.risk_level.steps;

import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.risk_level.pages.RiskLevelPages;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.steps.ScenarioSteps;

public class RiskLevelSteps extends ScenarioSteps {
    RiskLevelPages riskLevelPages;
    String gapi = LoadObject.getProperty("gapi.url");
    int shopId = Integer.parseInt(LoadObject.getProperty("shopid"));
    String userId = LoadObject.getProperty("userid");

    public void updateCriteria(int id, long time, int total, int value) {
        String api = gapi + "/admin/qc-tools/risk_level_system/risk_level_criteria";
        riskLevelPages.putAPI(api, requestBodyUpdateCriteria(id, time, total, value));
        System.out.println("id, time, total, value = " + id + "," + time + ","+ total+ "," + value);
    }

    public JsonObject requestBodyUpdateCriteria(int id, long time, int total, int value) {
        JsonObject requestParam = new JsonObject();
        requestParam.addProperty("shop_id", shopId);
        requestParam.addProperty("id", id);
        requestParam.addProperty("time", time);
        requestParam.addProperty("total", total);
        requestParam.addProperty("value", value);
        return requestParam;
    }

    public void verifyRiskLevel(String riskLevel) {
        waitABit(15000);
        String level = getRiskLevel(userId);
        riskLevelPages.verifyRiskLevel(riskLevel, level);
    }

    public String getRiskLevel(String userId) {
        String api = gapi + "/admin/qc-tools/risk_level_system/connected_account?user_id=" + userId;
        JsonPath js = riskLevelPages.getAPI(api);
        String level = (String) riskLevelPages.getData(js, "smp_risk_level");
        return level;

    }

}
