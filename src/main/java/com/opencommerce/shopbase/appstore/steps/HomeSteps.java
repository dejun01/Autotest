package com.opencommerce.shopbase.appstore.steps;

import com.opencommerce.shopbase.appstore.pages.HomePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomeSteps {
    String gapi = LoadObject.getProperty("gapi.url");
    HomePage homePage;
    String appstoreLink = LoadObject.getProperty("appstorelink");


    public List<String> getAppDetailbyID(String id) {
        String api = gapi + "/v1/3rd-party/apps/" + id;
        JsonPath js = homePage.getAPI(api);

        String logo = getData(js, "logo"); //0
        String name = getData(js, "app_name");//1
        String rating = getData(js, "rating");// 2
        String ratecount = getData(js, "rate_count"); //3
        String handle = getData(js, "handle"); //4
        String ctaBeforeInstall = getData(js, "cta_before_install");//5
        String ctaAfterInstall = getData(js, "cta_after_install");//6
        String shortDesc = getData(js, "short_description");//7
        String screenshoot = getData(js, "app_screenshoot");//8
        String description = getData(js, "description");//9
        String devWeb = getData(js, "website_url");//10
        String supportURL = getData(js, "support_page_url");//11
        String pricevalue = getData(js, "pricing_value");//12
        String pricetype = getData(js, "pricing_type");//13
        String ctaLink = getData(js, "cta_link");//14
        String priority = getData(js, "priority");//15
        String searchTerm = getData(js, "search_term");//16
        String ctaText = getData(js, "cta_text");//17
        return asList(logo, name, rating, ratecount, handle, ctaBeforeInstall, ctaAfterInstall, shortDesc, screenshoot,
                description, devWeb, supportURL, pricevalue, pricetype, ctaLink, priority, searchTerm, ctaText);
    }

    public String getData(JsonPath jsonPath, String key) {
        Object data = homePage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    public HashMap<String, List<String>> getListCategory() {
        HashMap<String, List<String>> categoryList = new HashMap<>();
        String api = gapi + "/v1/3rd-party/apps/categories";
        JsonPath js = homePage.getAPI(api);
        int size = (js.getList("categories")).size();
        for (int i = 0; i < size; i++) {
            String name = getData(js, "categories[" + i + "].name");
            String description = getData(js, "categories[" + i + "].description");
            categoryList.put(name, asList(name, description));
        }
        return categoryList;
    }

    public HashMap<String, List<String>> getListCollection() {
        HashMap<String, List<String>> collectionList = new HashMap<>();
        String api = gapi + "/v1/3rd-party/apps/collections?all=true";
        JsonPath js = homePage.getAPI(api);
        int size = (js.getList("list_collections")).size();
        for (int i = 0; i < size; i++) {
            String name = getData(js, "list_collections[" + i + "].collection_data.name");
            String description = getData(js, "list_collections[" + i + "].collection_data.description");
//            String keyName = getData(js, "list_collections[" + i + "].collection_data.key_name");
            collectionList.put(name, asList(name, description));
        }
        return collectionList;
    }

    public HashMap<Integer, List<String>> getListAppOfPageByApi(String type, String handle) {
        HashMap<Integer, List<String>> appList = new HashMap<>();
        String api = gapi + "/v1/3rd-party/apps?limit=16&" + type.toLowerCase() + "_handle=" + handle + "&all_type=true";
        System.out.println("api: " + api);
        JsonPath js = homePage.getAPI(api);
        int size = (js.getList("apps")).size();

        for (int i = 0; i < size; i++) {
            String name = getData(js, "apps[" + i + "].app_name");
            String description = getData(js, "apps[" + i + "].short_description");
            String pricevalue = getData(js, "apps[" + i + "].pricing_value");
            String pricetype = getData(js, "apps[" + i + "].pricing_type");
            String rating = getData(js, "apps[" + i + "].rating");
            String rateCount = getData(js, "apps[" + i + "].rate_count");
            String logo = getData(js, "apps[" + i + "].logo");
            appList.put(i, asList(name, description, pricevalue, pricetype, rating, rateCount, logo));
        }
        return appList;
    }

    public HashMap<String, List<String>> getListAppOfCategory(String handle) {
        HashMap<String, List<String>> appList = new HashMap<>();
        String api = gapi + "/v1/3rd-party/apps?category_handle=" + handle;
        JsonPath js = homePage.getAPI(api);
        int size = (js.getList("apps")).size();
        String categoryName = getData(js, "category.name");
        String categoryDesc = getData(js, "category.description");

        for (int i = 0; i < size; i++) {
            List<String> app = new ArrayList<>();
            String name = getData(js, "apps[" + i + "].name");
            String description = getData(js, "apps[" + i + "].description");
            String price = getData(js, "apps[" + i + "].price");
            String rating = getData(js, "apps[" + i + "].rating");
            String logo = getData(js, "apps[" + i + "].logo");
            app.add(description);
            app.add(handle);
            appList.put(name, asList(name, description, price, rating, logo));
        }
        return appList;
    }

    public List<String> get4Collection() {
        String api = gapi + "/v1/3rd-party/apps/collections?all=true";
        JsonPath js = homePage.getAPI(api);
        List<String> app = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String name = getData(js, "list_collections[" + i + "].collection_data.name");
            app.add(name);
        }
        return app;
    }

    public void verifyListCollectionInSidebarMenu(List<String> collectionInAPI) {
        if (collectionInAPI.size() == 0)
            homePage.collectionInSidebarDisplay(false);
        else {
            homePage.collectionInSidebarDisplay(true);
            List<String> actCol = getCollectionOnAppStore();
            assertThat(collectionInAPI).containsAll(actCol);
        }
    }

    public void navigateTolink(String url) {
        homePage.navigateToUrl(url);
    }

    public List<String> getCollectionOnAppStore() {
        return homePage.getCollectionOnAppStore();
    }

    public void verifyListCategoryInSidebarMenu(List<String> categoryName) {
        if (categoryName.size() == 0) {
            homePage.verifyListCategoryInSidebarDisplay(false);
        } else {
            homePage.verifyListCategoryInSidebarDisplay(true);
            List<String> actCate = homePage.getCategoryInSidebar();
            assertThat(categoryName).containsAll(actCate);
        }
    }


    public void verifyCategoryInHome(HashMap<String, List<String>> categoryAPI) {
        if (categoryAPI.size() == 0) {
            homePage.verifyCategoryInHome(false);
        } else {
            homePage.verifyCategoryInHome(true);
            HashMap<String, List<String>> actCate = homePage.getListCategoryInHome();
            assertThat(actCate.size()).isEqualTo(categoryAPI.size());
            for (String i : categoryAPI.keySet()) {
                for (String j : actCate.keySet()) {
                    if (i.equals(j)) {
                        String exName = categoryAPI.get(i).get(0);
                        String exDesc = categoryAPI.get(i).get(1);
                        String actName = actCate.get(i).get(0);
                        String actDesc = actCate.get(i).get(1);
                        assertThat(actName).isEqualTo(exName);
                        if (exDesc.equals("0")) {
                            assertThat(actDesc).isEqualTo("");
                        } else {
                            assertThat(actDesc.trim()).isEqualTo(exDesc.trim());
                        }
                    }
                }
            }
        }
    }

    public void verifyCollectionInHome(HashMap<String, List<String>> colAPI) {
        if (colAPI.size() == 0) {
            homePage.verifyCategoryInHome(false);
        } else {
            homePage.verifyCategoryInHome(true);
            HashMap<String, List<String>> actCol = homePage.getListCollectionInHome();
            assertThat(actCol.size()).isEqualTo(colAPI.size());
            System.out.println("act collection: " + actCol);
            System.out.println("api collection: " + colAPI);
            for (String i : colAPI.keySet()) {
                String exName = colAPI.get(i).get(0);
                String exDesc = colAPI.get(i).get(1);
                String actName = actCol.get(i).get(0);
                String actDesc = actCol.get(i).get(1);
                assertThat(actName).isEqualTo(exName);
                assertThat(actDesc).isEqualTo(exDesc);
            }
        }
    }

    public HashMap<String, List<String>> getAppsEachCollectionInHomeByApi(String colName) {
        HashMap<String, List<String>> apps = new HashMap<>();
        String api = gapi + "/v1/3rd-party/apps/collections?all=true";
        JsonPath js = homePage.getAPI(api);
        int numberApp = 0;
        int size = (js.getList("list_collections")).size();
        for (int i = 0; i < size; i++) {
            if (colName.equalsIgnoreCase(getData(js, "list_collections[" + i + "].collection_data.name"))) {
                if ((getData(js, "list_collections[" + i + "].collection_data.display_type").contains("Big Image"))) {
                    numberApp = 3;
                } else numberApp = 4;
                for (int j = 0; j < numberApp; j++) {
                    String name = getData(js, "list_collections[" + i + "].list_app_map_with[" + j + "].app_name");
                    String shortdesc = getData(js, "list_collections[" + i + "].list_app_map_with[" + j + "].short_description");
                    apps.put(name, asList(name, shortdesc));
                }
            }
        }
        return apps;
    }

    public List<String> getlistCollectionInHome() {
        return homePage.getlistCollectionInHome();
    }

    public HashMap<String, List<String>> getListAppOfCollectionInHome(String collectionName) {
        HashMap<String, List<String>> apps = new HashMap<>();
        int count = homePage.countAppOfCollectionInHome(collectionName);
        for (int i = 0; i < count; i++) {
            String name = homePage.getAppName(collectionName, i + 1);
            String desc = homePage.getAppDesc(collectionName, i + 1);
            apps.put(name, asList(name, desc));
        }
        return apps;
    }

    public void verifyAppsOfEachCollection(HashMap<String, List<String>> actApp, HashMap<String, List<String>> apiApp) {
        for (String i : actApp.keySet()) {
            for (String j : apiApp.keySet()) {
                if (i.equals(j)) {
                    List<String> appInfoAct = actApp.get(i);
                    List<String> appInfoEx = apiApp.get(j);
                    assertThat(appInfoAct).containsAll(appInfoEx);
                }
            }
        }
    }

    public void enterKeyword(String keyword) {
        homePage.enterKeyword(keyword);
    }

    public void verifyResult(String result, String appName) {
        if (result.equalsIgnoreCase("null")) {
            homePage.verifyMessageNoresult(appName);
            homePage.verifySuggestApp();
        } else {
            homePage.verifyMessageResult(appName);
            homePage.verifyAppsDisplayed(appName);
        }
    }

    public void maximizeWindow() {
        homePage.maximizeWindow();
    }

    public void clickApp(String appName) {
        homePage.clickApp(appName);
    }

    public String getAppID(String appName) {
        String appID = "";
        String name = (appName.toLowerCase()).replaceAll(" ", "+");
        String api = gapi + "/v1/3rd-party/apps?page=1&limit=16&search=" + name;
        JsonPath response = homePage.getAPI(api);
        appID = getData(response, "apps[" + 0 + "].id");
        System.out.println("id: " + appID);
        return appID;
    }

    public Boolean isExistedSeeAll(String collectionName) {
        return homePage.isExistedSeeAll(collectionName);
    }

    public void clickSeeAll(String collectionName) {
        homePage.clickSeeAll(collectionName);
    }

    public void navigateToCollectionPage(String collectionName) {
        String handle = getCollectionHandle(collectionName);
        String url = appstoreLink + "collection/" + handle;
        homePage.navigateToUrl(url);
    }

    public String getCollectionHandle(String collectionName) {
        String handle = "";
        String api = gapi + "/v1/3rd-party/apps/collections?all=true";
        JsonPath js = homePage.getAPI(api);
        int count = (js.getList("list_collections")).size();
        for (int i = 0; i < count; i++) {
            String colName = getData(js, "list_collections[" + i + "].collection_data.name");
            if (colName.equalsIgnoreCase(collectionName)) {
                handle = getData(js, "list_collections[" + i + "].collection_data.key_name");
            }
        }
        return handle;
    }

    public String getCollectionID(String collectionName) {
        String id = "";
        String api = gapi + "/v1/3rd-party/apps/collections?all=true";
        JsonPath js = homePage.getAPI(api);
        int count = (js.getList("list_collections")).size();
        for (int i = 0; i < count; i++) {
            if (getData(js, "list_collections[" + i + "].collection_data.name").equalsIgnoreCase(collectionName)) {
                id = getData(js, "list_collections[" + i + "].collection_data.id");
            }
        }
        return id;
    }

    public String getCategoryHandle(String name) {
        String api = gapi + "/v1/3rd-party/apps/categories";
        String handle = "";
        JsonPath js = homePage.getAPI(api);
        int size = (js.getList("categories")).size();
        for (int i = 0; i < size; i++) {
            String cateName = getData(js, "categories[" + i + "].name");
            if (cateName.equalsIgnoreCase(name)) {
                handle = getData(js, "categories[" + i + "].handle");
            }
        }
        return handle;
    }

    public void clickCategoryName(String name) {
        homePage.clickCategoryName(name);
    }

    public List<String> getPageInformation(String type, String handle) {
        String api = gapi + "/v1/3rd-party/apps?" + type + "_handle=" + handle + "&all_type=true";
        JsonPath js = homePage.getAPI(api);
        List<String> info = new ArrayList<>();
        String id = getData(js, type + ".id"); //0
        String name = getData(js, type + ".name"); //1
        String description = getData(js, type + ".description");//2
        String key_name = getData(js, type + ".key_name");//3
        String active = getData(js, type + ".active");//4
        String priority = getData(js, type + ".priority");//5
        String displayType = getData(js, type + ".display_type");//6
        info = asList(id, name, description, key_name, active, priority, displayType);
        return info;
    }
}
