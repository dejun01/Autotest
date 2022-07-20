package com.opencommerce.shopbase.dashboard.settings.steps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import java.util.Locale;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import com.github.javafaker.Faker;
import com.opencommerce.shopbase.dashboard.settings.pages.AddAccountStaffPage;
import java.util.List;



public class AddAccountStaffSteps {
    AddAccountStaffPage addAccountStaffPage;

    @Step
    public List<String> getListStaffAcc() {
        return addAccountStaffPage.getListStaffAcc();
    }

    @Step
    public String getTextAlert() {
        return addAccountStaffPage.getTextAlert();

    }

    @Step
    public String getTextDeleteSuccess(String email) {
        return addAccountStaffPage.getTextDeleteSuccess(email);
    }

    @Step
    public void chooseAccStaff(String email) {
        addAccountStaffPage.chooseAccStaff(email);

    }

    @Step
    public void clickButton(String name) {
        addAccountStaffPage.clickButton(name);
    }

    @Step
    public void enterInputConfirmPassword(String passWord) {
        addAccountStaffPage.enterInputConfirmPassword(passWord);
    }


    @Step
    public void clickBTFullPermission(boolean isFullPermission) {
        addAccountStaffPage.clickBTFullPermission(isFullPermission);
    }

    @Step
    public String getTexTAddStaffAccount() {
        return addAccountStaffPage.getTexTAddStaffAccount();

    }

    @Step
    public String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    @Step
    public void refreshPage() {
        addAccountStaffPage.refreshPage();
    }

    @Step
    public void clickAddStaffAccount() {
        addAccountStaffPage.clickAddStaffAccount();
    }

    @Step
    public void enterStaffEmail(String staffEmail) {
        addAccountStaffPage.enterStaffEmail(staffEmail);

    }

    @Step
    public void clickSendInvite() {
        addAccountStaffPage.clickSendInvite();
    }

    @Step
    public String getErrorMsg() {
        return addAccountStaffPage.getErrorMsg();
    }

    @Step
    public String choosePlan(String typeText) {
        return addAccountStaffPage.choosePlan(typeText).trim();

    }

    @Step
    public void openMailBoxWithSubject(String emailAddress, String subject) {
        boolean check = addAccountStaffPage.openMailBox(emailAddress, subject);
        assertThat(check).isTrue();
        addAccountStaffPage.verifyReceivedMail(emailAddress, subject);
    }

    @Step
    public String verifyShopInAccStaff(String status) {
        if (status.equals("not shop")) {
            return "";
        } else {
            return addAccountStaffPage.verifyShopInAccStaff(status);

        }
    }

    @Step
    public String verifyAndClick() {
        return addAccountStaffPage.verifyAndClick();

    }

    @Step
    public void clickButtonSubmit() {
        addAccountStaffPage.clickButtonSubmit();
    }

    @Step
    public String getTextNotPermission() {
        return addAccountStaffPage.getTextNotPermission();
    }

    public void backToSettingPage() {
        addAccountStaffPage.backToSettingPage();
    }

    @Step
    public void chooseAccStaffByEmail(String account) {
        List<String> listStaffAcc = getListStaffAcc();
        for (String acc : listStaffAcc) {
            chooseAccStaff(acc);
            if (addAccountStaffPage.getStaffAccount().equalsIgnoreCase(account)) {
                break;
            } else {
                backToSettingPage();
            }
        }

    }

    public void selectPermission(String permission) {
        if (!permission.isEmpty()) {

        }
    }

    public boolean isExistAccountScreen() {
        return addAccountStaffPage.isExistAccountScreen();
    }

    public void turnOffPopupConfirm(String action) {
        addAccountStaffPage.actionOnPopupConfirm(action);
    }

    public boolean isSettingAccountPage() {
        return addAccountStaffPage.isSettingAccountPage();
    }

    @Step
    public void clickButtonDeleteStaffAccount() {
        addAccountStaffPage.clickButtonDeleteStaffAccount();
    }

    @Step
    public void clickButtonConfirm() {
        addAccountStaffPage.clickButtonConfirm();
    }

    public void verifyAccess(String access,String account) {
        addAccountStaffPage.verifyAccess(access,account);
    }

    @Step
    public void verifyStaffPermissionMain( String namePermission,Boolean access) {
        addAccountStaffPage.verifyStaffPermissionMain(namePermission,access);
    }



    public void verifySubPermission(String main,String sub,Boolean access) {
        addAccountStaffPage.verifySubPermission(main,sub,access);
    }

    public void verifyPermissionEditOrder(String main, String sub, Boolean access) {
        addAccountStaffPage.verifyPermissionEditOrder(main,sub,access);
    }

    public void verifyPermissionExportOrder(String main, String sub, Boolean access) {
        addAccountStaffPage.verifyPermissionExportOrder(main,sub,access);
    }

    public void verifySubPermissionOfBalance(String main, String sub, Boolean access) {
        addAccountStaffPage.verifySubPermissionOfBalance(main,sub,access);
    }

    public void turnOffPopup() {
        addAccountStaffPage.turnOffPopup();
    }

    @Step
    public void grantPermissionForStaffByAPI(String permission, String actor) {
        String ownerName = LoadObject.getProperty("user.name");
        String ownerPwd = LoadObject.getProperty("user.pwd");
        String shopDomain = LoadObject.getProperty("shop");
        String staffName = LoadObject.getProperty(actor + ".name");

        // Get shop owner token
        String token = addAccountStaffPage.getAccessTokenShopBase(ownerName, ownerPwd, shopDomain);

        // Get staff info
        Response resp = given().get("https://" + shopDomain + "/admin/shop/staffs.json?access_token=" + token);
        JsonPath jp = resp.then().extract().jsonPath();

        // Get staff id
        String staffId = getValueByKey(jp, staffName, "id");

        // List permission
        String[] permissionList = permission.split(",");
        JsonArray permissionJSA = new JsonArray();
        String value;
        for (String s : permissionList) {
            value = s.trim().toLowerCase(Locale.ROOT).replace(" ", "_");
            permissionJSA.add(value);
        }

        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("id", Integer.parseInt(staffId));
        requestParams.addProperty("shop_id", Integer.parseInt(getValueByKey(jp, staffName, "shop_id")));
        requestParams.addProperty("first_name", getValueByKey(jp, staffName, "first_name"));
        requestParams.addProperty("last_name", getValueByKey(jp, staffName, "last_name"));
        requestParams.addProperty("name", getValueByKey(jp, staffName, "name"));
        requestParams.addProperty("email", getValueByKey(jp, staffName, "email"));
        requestParams.addProperty("role", getValueByKey(jp, staffName, "role"));
        requestParams.add("permissions", permissionJSA);
        requestParams.addProperty("is_full_access", Boolean.parseBoolean(getValueByKey(jp, staffName, "is_full_access")));
        requestParams.addProperty("verify_email_status", getValueByKey(jp, staffName, "verify_email_status"));
        requestParams.addProperty("is_receive_notification", Boolean.parseBoolean(getValueByKey(jp, staffName, "is_receive_notification")));
        requestParams.addProperty("phone_user", getValueByKey(jp, staffName, "phone_user"));
        requestParams.addProperty("avatar", getValueByKey(jp, staffName, "avatar"));
        requestParams.addProperty("last_login", Integer.parseInt(getValueByKey(jp, staffName, "last_login")));
        requestParams.addProperty("invitation_status", getValueByKey(jp, staffName, "invitation_status"));
        requestParams.addProperty("sent_invite_email_status", getValueByKey(jp, staffName, "sent_invite_email_status"));
        requestParams.addProperty("status", getValueByKey(jp, staffName, "status"));
        requestParams.addProperty("is_dev_store", Boolean.parseBoolean(getValueByKey(jp, staffName, "is_dev_store")));
        requestParams.addProperty("staff_code", getValueByKey(jp, staffName, "staff_code"));
        requestParams.addProperty("crisp_token_id", getValueByKey(jp, staffName, "crisp_token_id"));

        Response response1 = given().header("Content-Type", "application/json").header("x-shopbase-access-token", token).body(requestParams.toString()).put("https://" +shopDomain+ "/admin/shop/staffs/" + staffId+ ".json");
        Assertions.assertThat(response1.getStatusCode()).isBetween(200, 201);
    }

    public String getValueByKey(JsonPath rs, String email, String key) {
        String value;
        value = rs.get("staffs.findAll{it.email=='" + email + "'}." + key).toString();
        return value.replace("]","").replace("[","");
    }
}
