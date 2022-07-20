package com.opencommerce.shopbase.dashboard.apps.boostconvert.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import com.opencommerce.shopbase.dashboard.apps.boostconvert.pages.NotificationsListPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class NotificationListSteps extends ScenarioSteps {
    NotificationsListPage notiPage;
    String shop_id = LoadObject.getProperty("shop_id");
    String shop = LoadObject.getProperty("shop");

    @Step
    public void controlNotificationSale(boolean _isTurnOn) {
        notiPage.controlNotificationSale(_isTurnOn);
    }

    @Step
    public void verifySyncNoti(String productName) {
        notiPage.waitNoti();
        notiPage.verifyNameProductNoti(productName);
        notiPage.verifyStatusNoti();
        notiPage.verifyTypeNoti();
    }

    public void clickAddCustomNotifications() {
        notiPage.clickBtn("Add a custom notification");
    }

    @Step
    public void selectProduct(String sProduct) {
        String data[] = sProduct.split(">");
        notiPage.selectType(data[0]);
        if (!data[0].equalsIgnoreCase("All products")) {
            notiPage.openSelector();
            notiPage.addItemOnSelectorPopup(data[1]);
        }
    }

    @Step
    public void selectLocation(String sLocation, String sDataLocation) {
        notiPage.selectLocation(sLocation);
        if (sLocation.equalsIgnoreCase("Random locations")) {
            notiPage.selectRandumLocationInCountry(sDataLocation);
        } else {
            notiPage.inputLocation(sDataLocation);
        }
    }


    @Step
    public void selectSpecificPages(String sValuePoopupShow) {
        String data[] = sValuePoopupShow.split(">");
        notiPage.openSelector();
        notiPage.addItemOnSelectorPopup(data[1]);
    }


    public void checkPopupShowSpecificPages(boolean sPopupShowSpecificPages) {
        notiPage.checkCheckboxWithLabel("Popup show at specific pages", 1, sPopupShowSpecificPages);

    }


    public void selectCustomerNoti(boolean sCustomerNoti) {
        notiPage.checkCheckboxWithLabel("Custom notifications will have random 'time ago' within 12 hours after they are created", sCustomerNoti);
    }


    public void clickCreateNow() {
        notiPage.clickBtn("Create now");
    }

    public int countNotificationCreated() {
        return notiPage.getNumberNotificationCreated();
    }

    @Step
    public void verifyNumberNotificationCreate(int numberNotiCreate, int sNumberCustomerNoti) {
        assertThat(numberNotiCreate).isEqualTo(sNumberCustomerNoti);
    }

    @Step
    public int getTotalNotification() {
        return notiPage.getTotalNotification();
    }


    @Step
    private JsonPath getAPI(String api) {
        return notiPage.getAPI(api);
    }

    public List<Integer> getNotificationWithProductTitleByAPI(String productTName, String access_token) {
        String api = "https://" + shop + "/admin/copt/social-proof/sale-notifications.json?product_title=" + productTName;
        JsonPath js = notiPage.getAPISbase(api, access_token);
        List<Integer> ids = notiPage.getDataByKey(js, "notifications.id");
        return ids;
    }


    public List<Integer> getlistSaleNotificationShownOnStorefront(String productName) {
        String url = "https://" + shop + "/api/copt/sale-notifications.json?limit=50&page=1";
        JsonPath js = getAPI(url);
        List<Integer> ids = notiPage.getDataByKey(js, "sale_notifications.findAll{it.product_title== '" + productName + "'}.id");
        return ids;

    }

    public void verifyCustomerInformationNotification(JsonPath notiListOnSDK, int notiID, String key, String value) {
        String notification = (String) notiPage.getData(notiListOnSDK, "notifications.findAll{it.product_title==" + notiID + "}." + key + "");
        assertThat(notification).isEqualTo(value);
    }

    public String getAccessTokenShopbase() {
        return notiPage.getAccessTokenShopBase();
    }

    //--------------------------

    @Step
    public int countNotification() {
        return notiPage.countSaleNotification();
    }

    public void deleteSaleNotification() {
        notiPage.clickBtn("Delete");

    }

    public void selectAllSaleNotification() {
        notiPage.selectAllSaleNotification();
    }

    public int countNotificationByAPI(String accessToken) {
        String url = "https://" + shop + "/admin/copt/social-proof/count-sale-notifications.json";
        JsonPath js = notiPage.getAPISbase(url, accessToken);
        Object n = js.get("count");
        if (n == null) {
            return 0;
        }
        return (int) n;
    }



    public void updateStatusSaleNotification(int notiID, boolean isTurnOn, String accessToken) {
    }

    public void confirmDeleteNotification() {
        notiPage.confirmDeleteRecord();
    }

    public void searchNotification(String productName) {
        notiPage.enterInputFieldThenEnter("Search by product...", productName, 1);
    }

    public void turnOnNotification(boolean isTurnOn, int index) {
        notiPage.turnOnNotification(isTurnOn, index);
    }

    public void turnOnNotificationByAPI(List<Integer> ids, boolean isTurnOn, String accessToken) {
        String url = "https://" + shop + "/admin/copt/social-proof/notifications.json";
        notiPage.putAPISbase(url, updateStatusNoti(ids, isTurnOn), accessToken);
    }

    public JsonObject updateStatusNoti(List<Integer> ids, boolean isTurnOn) {
        JsonObject requestParam = new JsonObject();
        JsonArray listNoti = new JsonArray();

        requestParam.add("notifications", listNoti);
        for (int id : ids) {
            JsonObject noti = new JsonObject();
            noti.addProperty("id", id);
            noti.addProperty("status", isTurnOn);
            listNoti.add(noti);
        }
        return requestParam;
    }
}

