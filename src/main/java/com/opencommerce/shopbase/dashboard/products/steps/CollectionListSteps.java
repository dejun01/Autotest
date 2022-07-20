package com.opencommerce.shopbase.dashboard.products.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.dashboard.dashboard.steps.models.Collection.Collection;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.products.pages.CollectionListPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import java.util.List;

public class CollectionListSteps extends ScenarioSteps {
    CollectionListPages collectionListPages;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void clickAddCollection() {
        collectionListPages.clickBtn("Create collection");
    }

    @Step
    public void enterColTitle(String colName) {
        collectionListPages.enterInputFieldWithLabel("Title", colName);
    }

    @Step
    public void searchCollection(String title) {
        collectionListPages.searchCollection(title);
    }

    @Step
    public void chooseCollection(String nameCollection) {
        collectionListPages.chooseCollection(nameCollection);
    }

    public List<String> getListProductInCollectionByCollectionName(String collectionName) {
        String accesstoken = collectionListPages.getAccessTokenShopBase();

        Long idCollection = getCollectionIDByName(collectionName, accesstoken);
        List<String> listProduct = getListProductInCollectionByID(idCollection, accesstoken);
        return listProduct;
    }

    public List<String> getListProductInCollectionByID(Long idCollection, String accesstoken) {
        String url = "https://" + shop + "/admin/products/collections.json?limit=20&page=1&collection_id=" + idCollection + "&sort_order=alpha-asc";
        JsonPath listProduct = collectionListPages.getAPISbase(url, accesstoken);
        return collectionListPages.getDataByKey(listProduct, "products.title");
    }

    public Long getCollectionIDByName(String collectionName, String accesstoken) {
        String url = "https://" + shop + "/admin/collections.json?limit=50&search=&page=1&title=" + collectionName;
        JsonPath listCollection = collectionListPages.getAPISbase(url, accesstoken);
        Long idCollecton = listCollection.get("collections.findAll{it.title=='" + collectionName + "'}.id[0]");
        return idCollecton;
    }

    public String getAccessTokenShopbase() {
        return collectionListPages.getAccessTokenShopBase();
    }

    public int countCollection(String collectionName, String accessToken) {
        String shop = LoadObject.getProperty("shop");
        String url = "https://" + shop + "/admin/collections.json?limit=50&text_search=&search=&page=1&title=" + collectionName;
        JsonPath js = collectionListPages.getAPISbase(url, accessToken);
        int i = (int) collectionListPages.getData(js, "collections.findAll{it.title=='" + collectionName + "'}.size()");
        return i;
    }

    public void createCollectionAPI(String sCollectionName, String accessToken) {
        String api = "https://" + shop + "/admin/custom_collections.json";
        JsonObject requestParams = requestBodyCollection(sCollectionName);
        collectionListPages.postAPISbase(api, requestParams, accessToken);
    }

    private JsonObject requestBodyCollection(String sCollectionName) {
        JsonObject requestParam = new JsonObject();
        JsonObject custom_collections = new JsonObject();
        requestParam.add("custom_collection", custom_collections);
        custom_collections.addProperty("collection_type", "custom");
        custom_collections.addProperty("title", sCollectionName);
        return requestParam;
    }

    public void updateProductCollectionAPI(long collectionID, List<Long> productIds, String accessToken) {
        String api = "https://" + shop + "/admin/custom_collections/" + collectionID + ".json";
        JsonObject requestParams = requestBodyUpdateProductCollection(productIds);
        collectionListPages.putAPISbase(api, requestParams, accessToken);
    }

    private JsonObject requestBodyUpdateProductCollection(List<Long> productIds) {
        JsonObject requestParam = new JsonObject();
        JsonObject custom_collections = new JsonObject();
        JsonArray collect = new JsonArray();
        requestParam.add("custom_collection", custom_collections);

        custom_collections.add("collects", collect);
        if (!productIds.isEmpty()) {
            for (long productID : productIds) {
                JsonObject product = new JsonObject();
                product.addProperty("product_id", productID);
                collect.add(product);
            }
        }
        return requestParam;
    }

    @Step
    public String getCurrentURL() {
        return collectionListPages.getCurrentURL();
    }

    @Step
    public void changeURLCollection(String newURL, boolean isCreate) {
        collectionListPages.enterNewURL(newURL);
        if (isCreate == true) {
            collectionListPages.checkCheckboxWithLabel("Create a URL redirect from");
        }
        collectionListPages.clickBtn("Save");
        collectionListPages.verifySaveCollectionSuccess();
    }

    @Step
    public boolean hasNoProduct() {
        return collectionListPages.hasNoProduct();
    }

    @Step
    public void selectAllCollection() {
        collectionListPages.selectAllCollection();
    }

    @Step
    public void selectCollection(String collection) {
        collectionListPages.selectCollection(collection);
    }

    @Step
    public void clickAction() {
        collectionListPages.clickBtn("Actions");
    }

    @Step
    public void chooseActionDeleteSelectedCollection() {
        collectionListPages.chooseAction("Delete selected collections");
    }

    @Step
    public void clickBtnDelete() {
        collectionListPages.clickBtn("Delete");
    }

    @Step
    public void openDetailColection(String title) {
        collectionListPages.openDetailColection(title);
    }

    @Step
    public void verifyImageProductThumbnail(String product, String imageVariant, String imageStatus) {
        collectionListPages.verifyImageProductThumbnail(product, imageVariant, imageStatus);
    }

    public JsonPath getCollectionAndNormalize(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/collections.json";
        JsonPath collectionList = collectionListPages.getAPISbase(api, accessToken);
        Collection[] desCollection = collectionList.getObject("collections", Collection[].class);
        for (int i = 0; i < desCollection.length; i++) {
            desCollection[i].normalizeCollection();
        }
        return collectionList;
    }
    @Step
    public void openCollectionList() {
        collectionListPages.openCollectionList();
    }

    @Step
    public void verifyNumberCollectionsOnStoreFront(int totalCollection) {
        collectionListPages.verifyNumberCollectionsOnStoreFront(totalCollection);
    }

    public void verifyNumberCollectionsOnDashboard(int totalCollection) {
        collectionListPages.verifyNumberCollectionsOnDashboard(totalCollection);
    }

    @Step
    public void verifyPageCollection(String shop) {
        collectionListPages.verifyPageCollection(shop);
    }
    @Step
    public void clickAddToCollection(String action) {
        collectionListPages.clickAddToCollection(action);
    }
    @Step
    public void selectCollectionFromDashboard(String nameCollection) {
        collectionListPages.selectCollectionFromDashboard(nameCollection);
    }
}
