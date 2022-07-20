package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.fulfillment;

import com.google.gson.JsonArray;
import io.restassured.path.json.JsonPath;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.fulfillment.InventoryPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class InventorySteps extends ScenarioSteps {
    InventoryPage inventoryPage;

    public JsonPath getInventoryInformation(String productName, String accesstoken) {
        String user_id = LoadObject.getProperty("user_id");
        String url = LoadObject.getProperty("gapi.url") + "/v1/panda-sourcing/products?user_id=" + user_id + "&page=1&key=" + productName + "&status=private%2Cimporting&limit=10&includes=inventory";
        return inventoryPage.getAPICrosspanda(url, accesstoken);
    }

    @Step
    public double countNumberOfProductByStatus(JsonPath inventoryInformation, String productName, String variant, String status) {
        String result = "result.find{it.title=='" + productName + "'}.variants.find{it.option==" + varriant(variant) + "}." + status;
        return (int) inventoryPage.getData(inventoryInformation, result);
    }


    public JsonArray varriant(String variants) {
        JsonArray list = new JsonArray();

        String listvariant[] = variants.split(",");
        for (String vr : listvariant) {
            list.add(vr);
        }
        return list;
    }

    @Step
    public void compare2List(List<Double> actualResult, List<Double> expectedResult) {
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
