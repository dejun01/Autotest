package com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.MyCampaignPage;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import common.utilities.LoadObject;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static io.restassured.RestAssured.preemptive;
import static org.assertj.core.api.Assertions.assertThat;

public class PricingSteps extends ScenarioSteps {


    public JsonPath getBasePrice(String accTokenShop, String variants, int numberOfSecondSide, Integer productBaseId) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-campaigns/list-pricing.json";
        JsonObject requestParams = requestBodyListPricing(variants, numberOfSecondSide, productBaseId);
        Response response = given().when()
                .header("X-ShopBase-Access-Token", accTokenShop)
                .body(requestParams.toString()).post(url);
        System.out.println(response);

        assertThat(response.getStatusCode()).isBetween(200, 201);
        return response.then().extract().jsonPath();
    }

    private JsonObject requestBodyListPricing(String variants, int numberOfSecondSide, Integer productBaseId) {
        JsonObject requestParams = new JsonObject();
        JsonArray products = new JsonArray();
        JsonObject product = new JsonObject();
        JsonArray listvariants = new JsonArray();


        requestParams.add("products", products);
        products.add(product);
        String shopId = LoadObject.getProperty("shop_id");
        requestParams.addProperty("shop_id", Long.parseLong(shopId));
        product.addProperty("product_base_id", productBaseId);
        product.addProperty("number_of_second_side", numberOfSecondSide);
        product.add("variants", listvariants);
        String vars[] = variants.split(";");
        for (String var : vars) {
            JsonObject variant = new JsonObject();
            String options[] = var.split(",");
            for (int i = 0; i < options.length; i++) {
                variant.addProperty("option" + (i + 1), options[i]);
            }
            variant.addProperty("used", true);
            listvariants.add(variant);
        }

        return requestParams;

    }

    public Response getListPricingFromHive(Integer idProductBase, String accesstoken) {
        String shopDomain = LoadObject.getProperty("shop");
        String url = "https://" + shopDomain + "/admin/pbase-product-base/products.json?ids=" + idProductBase + "&access_token=" + accesstoken;
        Response response = RestAssured.get(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        return response;
    }

    public Response getListPricingFromScalablepress(String handle, Integer idProductBase) {
        String shopDomain = LoadObject.getProperty("shop");
        String url = "https://api.scalablepress.com/v2/products/" + handle + "/items";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.authentication = preemptive().basic("quan@beeketing.com ", "test_eDu4yh3iwMfkMm9K3uMtYw");
        Response response = RestAssured.get(url);
        assertThat(response.getStatusCode()).isBetween(200, 201);
        return response;
    }

    public float calculateVariantBaseCost(String accesstoken, String variants, int numberSecondSide, float productBaseCost, Integer idProductBase) {
        Response listPricing = getListPricingFromHive(idProductBase, accesstoken);
        float sTCProductBaseVariant = calculateSTCVariant(listPricing, "White,M", numberSecondSide);

        float variantCostMin;

        String vars[] = variants.split(";");
        variantCostMin = calculateVariantCost(listPricing, vars[0], numberSecondSide, productBaseCost, sTCProductBaseVariant);

        System.out.println("Variant: " + vars[0] + ": " + roundPrice(variantCostMin));
        for (int i = 1; i < vars.length; i++) {
            float price = calculateVariantCost(listPricing, vars[i], numberSecondSide, productBaseCost, sTCProductBaseVariant);
            System.out.println("Variant: " + vars[i] + ": " + roundPrice(price));

            if (price < variantCostMin)
                variantCostMin = price;
        }
        return variantCostMin;
    }

    public float roundPrice(float price) {
        int phanNguyen = (int) price;
        float du = price - phanNguyen;
        if (du < 0.5) {
            du = 0.49f;
        } else {
            du = 0.99f;
        }
        return phanNguyen + du;
    }

    public float calculateVariantCost(Response listPricing, String variants, int numberSecondSide, float productBaseCost, float sTCProductBaseVariant) {
        float stcVariant = calculateSTCVariant(listPricing, variants, numberSecondSide);

        float variantprice = 0;
        if (stcVariant > sTCProductBaseVariant && sTCProductBaseVariant>0) {
            variantprice = stcVariant - sTCProductBaseVariant + productBaseCost + 5 * numberSecondSide;
        } else {
            variantprice = productBaseCost + 5 * numberSecondSide;
        }
        return roundPrice(variantprice);
    }

    public Float calculateSTCVariant(Response listPricing, String variants, int numberSecondSide) {
        String options[] = variants.split(",");
        String color = options[0];
        String size = options[1];
        List<Float> garmentCosts = listPricing.then().extract().jsonPath().get("products[0].variants.findAll{it.option1=='" + color + "'&& it.option2=='" + size + "'}.garment_cost");
        float stc = 0f;
        if (garmentCosts.size() > 0) {
            Float weight = Float.parseFloat(listPricing.then().extract().jsonPath().get("products[0].variants.findAll{it.option1=='" + color + "'&& it.option2=='" + size + "'}.weight[0]").toString());
            Float shipingCost = (float) getShippingCost(weight);
            stc = (float) (garmentCosts.get(0) * 1.1  + 1 + numberSecondSide * 5 + shipingCost);
        }
        return stc;
    }


    private double getShippingCost(float weight) {
        double cost = 0;
        if (weight <= 0.2) {
            cost = 2.58;
        } else if (weight <= 0.25) {
            cost = 2.64;
        } else if (weight <= 0.3) {
            cost = 2.74;
        } else if (weight <= 0.35) {
            cost = 2.84;
        } else if (weight <= 0.4) {
            cost = 2.84;
        } else if (weight <= 0.45) {
            cost = 2.96;
        } else if (weight <= 0.5) {
            cost = 2.96;
        } else if (weight <= 0.55) {
            cost = 3.11;
        } else if (weight <= 0.6) {
            cost = 3.15;
        } else if (weight <= 0.65) {
            cost = 3.26;
        } else if (weight <= 0.7) {
            cost = 3.26;
        } else if (weight <= 0.75) {
            cost = 3.26;
        } else if (weight <= 0.8) {
            cost = 3.58;
        } else if (weight <= 0.85) {
            cost = 3.65;
        } else if (weight <= 0.9) {
            cost = 3.66;
        } else if (weight <= 0.95) {
            cost = 3.76;
        } else if (weight <= 1) {
            cost = 3.76;
        } else if (weight <= 2) {
            cost = 5.61;
        } else if (weight <= 3) {
            cost = 6.52;
        } else if (weight <= 4) {
            cost = 7.37;
        } else if (weight <= 5) {
            cost = 8.2;
        } else if (weight <= 6) {
            cost = 9.02;
        } else if (weight <= 7) {
            cost = 10.64;
        } else if (weight <= 8) {
            cost = 10.64;
        } else if (weight <= 9) {
            cost = 11.43;
        } else if (weight <= 10) {
            cost = 12.23;
        }
        return cost;
    }

}
