package com.opencommerce.shopbase.dashboard.apps.printhub.steps.dashboard;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.dashboard.DashboardPrinthubPages;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DashboardPrinthubSteps {
    DashboardPrinthubPages dashboardPrinthubPages;
    String shop = LoadObject.getProperty("shop");

    public void verifyDashboardPrinthubScreen() {
        dashboardPrinthubPages.verifyDashboardPrinthubScreen();
    }


    public HashMap<Integer, List<String>> getlistWidgetPhub(String screen) {
        String acctoken = "";
        String api = "https://" + shop + "/admin/dashboards/widget-dashboard.json?no_cache=true&package=pro&region=china&business_type=General+Dropship&screen=" + screen + "";
        if (acctoken.isEmpty()) {
            acctoken = dashboardPrinthubPages.getAccessTokenShopBase();
            System.out.println("token: " + acctoken);
        }
        JsonPath js = dashboardPrinthubPages.getAPISbase(api, acctoken);
        HashMap<Integer, List<String>> widget = new HashMap<>();
        int count = (js.getList("list")).size();
        for (int i = 0; i < count; i++) {
            String id = getData(js, "list[" + i + "].id"); //0
            String name = getData(js, "list[" + i + "].name"); //1
            String type = getData(js, "list[" + i + "].widget_type"); //2
            String mainTitle = getData(js, "list[" + i + "].widget_config.en.main_title"); //3
            String mainTDesc = getData(js, "list[" + i + "].widget_config.en.main_description"); //4
            String assets_type = getData(js, "list[" + i + "].widget_config.en.assets_type"); //5
            String priBtnText = getData(js, "list[" + i + "].widget_config.en.primary_button_text"); //6
            String priBtnLink = getData(js, "list[" + i + "].widget_config.en.primary_button_link"); //7
            String scBtnText = getData(js, "list[" + i + "].widget_config.en.secondary_button_text"); //8
            String scBtnLink = getData(js, "list[" + i + "].widget_config.en.secondary_button_link"); //9
            String video = getData(js, "list[" + i + "].widget_config.en.video_url"); //10
            String image = getData(js, "list[" + i + "].widget_config.en.img_url"); //11
            String day = getData(js, "list[" + i + "].widget_config.en.day"); //12
            String reward = getData(js, "list[" + i + "].widget_config.en.reward"); //13
            String goal = getData(js, "list[" + i + "].widget_config.en.goal"); //14
            widget.put(i, asList(id, name, type, mainTitle, mainTDesc, assets_type, priBtnText, priBtnLink, scBtnText, scBtnLink, video, image, day, reward, goal));
        }
        System.out.println("widget: " + widget);
        return widget;
    }

    public HashMap<String, List<String>> getListOfAWidgetPhub(String id) {
        String acctoken = "";
        String api = "https://" + shop + "/admin/dashboards/widget-dashboard.json?no_cache=true";
        if (acctoken.isEmpty()) {
            acctoken = dashboardPrinthubPages.getAccessTokenShopBase();
        }
        HashMap<String, List<String>> widget = new HashMap<>();
        JsonPath js = dashboardPrinthubPages.getAPISbase(api, acctoken);
        int count = (js.getList("list")).size();
        for (int i = 0; i < count; i++) {
            if (id.equalsIgnoreCase(getData(js, "list[" + i + "].id"))) {
                int countList = (js.getList("list[" + i + "].widget_config.en.list_sub")).size();
                for (int j = 0; i < countList; i++) {
                    String title = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].title");
                    String description = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].description");
                    String link = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].link");
                    String imgURL = getData(js, "list[" + i + "].widget_config.en.list_sub[" + j + "].img_url");
                    widget.put(String.valueOf(j), asList(title, description, link, imgURL));
                }
            }
        }
        return widget;
    }

//    @Step
//    public String getData(JsonPath jsonPath, String key) {
//        Object data = dashboardPrinthubPages.getData(jsonPath, key);
//        if (data == null) {
//            data = 0;
//        }
//        return data.toString();
//    }

    public void verifyWidgetDisplay(boolean isShow) {
        dashboardPrinthubPages.verifyWidgetDisplay(isShow);
    }

    public int getNumberOfWidget() {
        dashboardPrinthubPages.refreshPage();
        return dashboardPrinthubPages.getNumberOfWidget();
    }

    public void verifyTitleWidget(String title, int res) {
        dashboardPrinthubPages.verifyTitleWidget(title, res);
    }

    public void verifyDescWidget(String des, int res) {
        dashboardPrinthubPages.verifyDescWidget(des, res);
    }

    public void verifyTypeWidget(String type, int res) {
        dashboardPrinthubPages.verifyTypeWidge(type, res);
    }

    public void verifyDetailWidget(List<String> widget, int res) {
        String type = widget.get(2);
        switch (type) {
            case "Basic":
                dashboardPrinthubPages.verifyPrimaryBtn(widget.get(6), widget.get(7), res);
                dashboardPrinthubPages.verifySecondaryBtn(widget.get(8), widget.get(9), res);
                dashboardPrinthubPages.verifyAssetWidget(widget.get(5), widget.get(10), widget.get(11), res);
                break;
            case "Center":
                dashboardPrinthubPages.verifyPrimaryBtn(widget.get(6), widget.get(7), res);
                dashboardPrinthubPages.verifyImage(widget.get(11), res);
                break;
            case "List":
                HashMap<String, List<String>> list = getListOfAWidgetPhub(widget.get(0));
                int numberList = dashboardPrinthubPages.getNumbeItemInlist(res);
                assertThat(numberList).isEqualTo(list.size());
                for (int i = 0; i < numberList; i++) {
                    List<String> value = list.get(i);
                    dashboardPrinthubPages.verifySubListTitle(value.get(0), res, i + 1);
                    dashboardPrinthubPages.verifySubListDesc(value.get(1), res, i + 1);
                    dashboardPrinthubPages.verifySubListImage(value.get(2), value.get(3), res, i + 1);
                }
                break;
        }
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = dashboardPrinthubPages.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    @Step
    public HashMap<Integer, List<String>> getListItem(String time, String fromDate, String toDate) {
        String acctoken = "";
        String api = "https://" + shop + "/admin/phub-analytics.json?page=1&limit=2&from_date=" + fromDate + "&to_date=" + toDate + "&q=";
        if (acctoken.isEmpty()) {
            acctoken = dashboardPrinthubPages.getAccessTokenShopBase();
            System.out.println("token: " + acctoken);
        }
        HashMap<Integer, List<String>> item = new HashMap<>();
        JsonPath js = dashboardPrinthubPages.getAPISbase(api, acctoken);
        Object[] a1 = js.getMap("items").keySet().toArray();
        int t = 0;
        for (Object i : a1) {
            String id = String.valueOf(i);
            String title = getData(js, "items." + i + ".title"); //0
            String type = getData(js, "items." + i + ".item_type"); //1
            String quantity = getData(js, "items." + i + ".quantity"); //2
            String qltRefund = getData(js, "items." + i + ".refund_quantity"); //3
            String qltActual = getData(js, "items." + i + ".actual_quantity"); //4
            item.put(t, asList(title, type, quantity, qltRefund, qltActual));
            t++;
        }
        System.out.println("item: " + item);
        return item;
    }

    @Step
    public void verifyItemName(String expName) {
        dashboardPrinthubPages.verifyItemName(expName);
    }

    @Step
    public void verifyItemType(String title, String expType) {
        dashboardPrinthubPages.verifyItemType(title, expType);
    }

    @Step
    public void verifyQuantity(String title, String expQuantity) {
        dashboardPrinthubPages.verifyQuantity(title, expQuantity);
    }

    @Step
    public void verifyTotalOrder(String totalOrder) {
        dashboardPrinthubPages.verifyTotalOrder(totalOrder);
    }

    @Step
    public void verifyTotalItem(String totalItem) {
        dashboardPrinthubPages.verifyTotalItem(totalItem);
    }

    @Step
    public void verifyGoldbaseItem(String goldbaseItem) {
        dashboardPrinthubPages.veryfiGoldbaseItem(goldbaseItem);
    }

    @Step
    public void verifySilverbaseItem(String silverbaseItem) {
        dashboardPrinthubPages.verifySilverbaseItem(silverbaseItem);
    }

    @Step
    public void enterKeyword(String keyword) {
        dashboardPrinthubPages.enterKeyword(keyword);
    }

    @Step
    public void verifyresult(String keyword, String result) {
        if (result.equalsIgnoreCase("yes")) {
            dashboardPrinthubPages.verifyItemDislay(keyword);
        } else dashboardPrinthubPages.verifyNoResult(keyword);
    }

    @Step
    public List<String> getSummaryAnalyticPrinthubDashboard(String time, String fromDate, String toDate) {
        List<String> summary = new ArrayList<>();
        String acctoken = "";
        String api = "https://" + shop + "/admin/phub-analytics/statistics.json?from_date=" + fromDate + "&to_date=" + toDate;
        System.out.println(api);
        if (acctoken.isEmpty()) {
            acctoken = dashboardPrinthubPages.getAccessTokenShopBase();
            System.out.println("token: " + acctoken);
        }
        JsonPath js = dashboardPrinthubPages.getAPISbase(api, acctoken);
        String totalOrder = getData(js, "total_order");
        String totalItem = getData(js, "total_item");
        String goldBaseItem = getData(js, "total_gold_base_item");
        String silverBaseItem = getData(js, "total_silver_base_item");

        summary = asList(totalOrder, totalItem, goldBaseItem, silverBaseItem);
        return summary;

    }

    @Step
    public void chooseFilterTime(String time) {
        dashboardPrinthubPages.clickFilterTime();
        dashboardPrinthubPages.chooseTimeRange(time);
    }

    @Step
    public void verifyQuantityRefund(String title, String qltRef) {
        dashboardPrinthubPages.verifyQuantityRefund(title, qltRef);
    }

    @Step
    public void verifyQuantityActual(String title, String qltAct) {
        dashboardPrinthubPages.verifyQuantityActual(title, qltAct);
    }

    @Step
    public int countItems() {
        return dashboardPrinthubPages.countItems();
    }

    @Step
    public List<String> getListTitle(int numberItems) {
        return dashboardPrinthubPages.getListTitle(numberItems);
    }
}

