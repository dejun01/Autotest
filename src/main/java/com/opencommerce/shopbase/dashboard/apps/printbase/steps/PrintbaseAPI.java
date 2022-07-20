package com.opencommerce.shopbase.dashboard.apps.printbase.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.apps.printhub.pages.orders.payments.PaymentsPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import static com.opencommerce.shopbase.OrderVariable.customArtName;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.rest.RestRequests.given;

public class PrintbaseAPI {
    PaymentsPage paymentsPage;

    @Step
    public Integer getIDProductBase(String productBase, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-product-base/catalogs.json?key_search=" + productBase + "&feature=catalog&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer id = (Integer) paymentsPage.getData(js, "products.findAll{it.title=='" + productBase + "'}.id[0]");
        return id;
    }

    @Step
    public Float getBaseCostFromProductBase(String productBase, String accessToken) {
        String url = "https://" + LoadObject.getProperty("shop") + "/admin/pbase-product-base/catalogs.json?key_search=" + productBase + "&feature=catalog";
        JsonPath js = paymentsPage.getAPISbase(url, accessToken);
        Float baseCost = (Float) paymentsPage.getData(js, "products.product_base_cost[0]");
        System.out.println(baseCost);
        return baseCost;
    }

    @Step
    public Float getBaseCostAPI(Integer idProductBase, String color, String size, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns/list-pricing.json";
        JsonObject requestParams = requestBodyPricing(idProductBase, color, size);
        JsonPath jpAccessToken = paymentsPage.postAPISbase(url, requestParams, accessToken);
        Float baseCost = jpAccessToken.get("products[0].variants.base_price[0]");
        return baseCost;
    }

    @Step
    public Integer getIDCampaign(String campaignName, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns.json?access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer id = (Integer) paymentsPage.getData(js, "campaigns.findAll{it.campaign_title==\"" + campaignName + "\"}.id[0]");
        return id;
    }

    @Step
    public Integer getIDCampaignManual(String campaignName, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns.json?access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer id = (Integer) paymentsPage.getData(js, "campaigns.findAll{it.campaign_title==\"" + campaignName + "\"}.campaign_id[0]");
        return id;
    }

    @Step
    public Float getPriceCampaignAPI(Integer idCampaign, String productBase, String color, String size, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns/live/" + idCampaign + ".json?access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Float price = js.get("product.variants.findAll{it.option1=='" + productBase + "'&& it.option2=='" + color + "' && it.option3=='" + size + "'}.price[0]");
        return price;
    }

    @Step
    public Float getPriceCompareCampaignAPI(Integer idCampaign, String productBase, String color, String size, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns/live/" + idCampaign + ".json&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(shop);
        Float baseCost = js.get("products[0].variants.findAll{it.option1=='" + productBase + "',it.option2=='" + color + "',it.option2=='" + size + "'}.price[0]");
        return baseCost;
    }

    @Step
    public List<Float> getProductBaseInforWithAPI(Integer idProductBase, String color, String size, String accessToken) {
        List<Float> price = new ArrayList<>();
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns/list-pricing.json";
        JsonObject requestParams = requestBodyPricing(idProductBase, color, size);
        JsonPath jpAccessToken = paymentsPage.postAPISbase(url, requestParams, accessToken);
        Float baseCost = jpAccessToken.get("products[0].variants.base_price[0]");
        price.add(baseCost);
        Float price_pro = jpAccessToken.get("products[0].variants.base_price[0]");
        price.add(price_pro);
        Float price_compare = jpAccessToken.get("products[0].variants.base_price[0]");
        price.add(price_compare);
        System.out.println(baseCost);
        return price;
    }

    @Step
    public JsonObject requestBodyPricing(Integer idProductBase, String color, String size) {
        JsonObject requestParam = new JsonObject();
        JsonArray products = new JsonArray();
        JsonObject product = new JsonObject();
        JsonArray variants = new JsonArray();
        JsonObject variant = new JsonObject();
        JsonObject meta = new JsonObject();
        requestParam.add("products", products);
        products.add(product);
        product.addProperty("product_base_id", idProductBase);
        product.addProperty("number_of_second_side", 0);
        product.add("variants", variants);
        variants.add(variant);
        variant.addProperty("used", true);
        variant.addProperty("hex", "000000");
        variant.addProperty("option1", color);
        variant.addProperty("option2", size);

        return requestParam;
    }

    public Float getProfitLineItemInAnalytics(String campaign, String date, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/statistics/v2/top-products.json?from_time=" + date + "&to_time=" + date + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Float profit_line = 0.00f;
        try {
            profit_line = js.get("findAll{it.product_title==\"" + campaign + "\"}.total_sales[0]");

        } catch (ClassCastException e) {
            Integer profit = js.get("findAll{it.product_title==\"" + campaign + "\"}.total_sales[0]");
            profit_line = new Float(profit);
        }
        if (profit_line == null)
            return 0.00f;
        else return profit_line;
    }

    public String getShopID(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/shop.json" + "?access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        String shopID = paymentsPage.getData(js, "shop.id").toString();
        System.out.println(shopID);
        return shopID;
    }

    public Integer getQuotaCampaign(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "daily_campaign_left");
        System.out.println(quota);
        return quota;
    }

    public Integer getQuotaPro(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "daily_left");
        System.out.println(quota);
        return quota;
    }

    public Integer getQuotaProLimit(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "daily_limit");
        System.out.println(quota);
        return quota;
    }



    public Integer getQuotaCampaignHourly(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "quantity_left");
        System.out.println(quota);
        return quota;
    }

    public Integer getQuotaCampaignHourlyLimit(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "limit");
        System.out.println(quota);
        return quota;
    }

    public Integer getQuotaCampaignDailyLimit(String shopID, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-common/campaign-limitation.json?shop_id=" + shopID + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "daily_campaign_limit");
        System.out.println(quota);
        return quota;
    }


    public String getStatusCampaign(Integer idCampaign, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns.json?campaign_ids=" + idCampaign + "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        String status = paymentsPage.getData(js, "campaigns.findAll{it.campaign_id==" + idCampaign + "}.campaign_status[0]").toString();
        return status;
    }

    public float getDesignFeeOfCampaignManual(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/pbase-campaigns/sample-manual-design-camps/list.json?limit=12&page=1"+ "&access_token=" + accessToken;
         JsonPath js = paymentsPage.getAPI(url);
        Float designFee = Float.parseFloat( paymentsPage.getData(js, "manual_design_fee").toString());
        return designFee;
    }


    public Boolean getStatusPrintFile(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String shop_id = getShopID(accessToken);
        String url = "https://" + shop + "/admin/setting/online-store-preferences.json?shop_id=" + shop_id + "&access_token=" + accessToken;
        JsonPath response =  paymentsPage.getAPI(url);
        return response.get("print_file.disable");
    }
    public ArrayList<String> getListShippingFee(String baseProduct, String address){
        String gapi=LoadObject.getProperty("gapi.url");
        String url= gapi + "v1/pod/landing-catalogs";
        JsonPath response = paymentsPage.getAPI(url);
        ArrayList<String> listShippingFee;
        if (address.equalsIgnoreCase("US")){
             listShippingFee= response.get("result.list_category.list_base_product.findAll{it.title=='"+baseProduct+"'}.size_data.shipping_groups[0].first_item_price");
        }
        else {
            listShippingFee = response.get("result.list_category.list_base_product.findAll{it.title=='"+baseProduct+"'}.size_data.shipping_groups[1].first_item_price");
        }

        return listShippingFee;
    }

//    public ArrayList<String> getShippingZone(){
//        String gapi=LoadObject.getProperty("gapi.url");
//        String url= gapi + "v1/pod/landing-catalogs";
//        JsonPath response = paymentsPage.getAPI(url);
//        ArrayList<String> listShippingZone = response.get("result.shipping_zones.id");
//        return listShippingZone;
//    }
    public Float getShippingFee(String catalog,String baseProduct, String address){
        String gapi=LoadObject.getProperty("gapi.url");
        String url= gapi + "/v1/pod/landing-catalogs";
        JsonPath response = paymentsPage.getAPI(url);
        Float shippingFee;
        if(address.equalsIgnoreCase("US")){
            shippingFee=(Float) paymentsPage.getData(response, "result.list_category.findAll{it.name=='"+catalog+"'}.list_base_product[0].findAll{it.title=='"+baseProduct+"'}.size_data[0].shipping_groups[0].findAll{it.group=='Standard shipping'}.shipping_rules[0].first_item_price[0]");
            System.out.println(paymentsPage.getData(response, "result.list_category.findAll{it.name=='"+catalog+"'}.list_base_product[0].findAll{it.title=='"+baseProduct+"'}.size_data[0].shipping_groups[0].findAll{it.group=='Standard shipping'}.shipping_rules[0].first_item_price[0]"));
        }else{
            shippingFee=(Float) paymentsPage.getData(response, "result.list_category.findAll{it.name=='"+catalog+"'}.list_base_product[0].findAll{it.title=='"+baseProduct+"'}.size_data[0].shipping_groups[0].findAll{it.group=='Standard shipping'}.shipping_rules[0].first_item_price[1]");
        }
        return shippingFee;

    }
    public Integer getLimitCollection(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/collections/count.json?text_search=&search=&title="+ "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "limit");
        System.out.println(quota);
        return quota;
    }
    public Integer getNumberCollectionCreated(String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/collections/count.json?text_search=&search=&title="+ "&access_token=" + accessToken;
        JsonPath js = paymentsPage.getAPI(url);
        Integer quota = (Integer) paymentsPage.getData(js, "total");
        System.out.println(quota);
        return quota;
    }

}
