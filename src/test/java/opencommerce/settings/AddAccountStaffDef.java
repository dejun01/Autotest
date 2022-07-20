package opencommerce.settings;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AccountSteps;
import com.opencommerce.shopbase.dashboard.settings.steps.AddAccountStaffSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AddAccountStaffDef {
    String shop = LoadObject.getProperty("shop");
    String passWord = LoadObject.getProperty("user.pwd");
    String staffUserName = "";
    @Steps
    CommonSteps commonSteps;
    @Steps
    LoginDashboardSteps loginStep;
    @Steps
    AccountSteps accountSteps;
    @Steps
    AddAccountStaffSteps addAccountStaffSteps;
    int staffAccountMax;
    String permissionsName = "";
    int numberStaff;

    String statusAccStaff = "";
    String account = "";

    @Then("get number of staff account of shop current plan")
    public void getNumberOfStaffAccountOfShopCurrentPlan() {
        accountSteps.clickBTUpgradePlan();
        addAccountStaffSteps.refreshPage();
        staffAccountMax = Integer.parseInt(addAccountStaffSteps.choosePlan("Choose this plan"));
        accountSteps.clickBTConfirmChanges();
    }

    @And("Add account staff and verify staff amount")
    public void addAccountStaffAndVerifyStaffAmount() {
        if (addAccountStaffSteps.getListStaffAcc() == null) {
            numberStaff = 0;
        } else {
            numberStaff = addAccountStaffSteps.getListStaffAcc().size();
        }
        if (staffAccountMax > numberStaff) {
            int staffAccAdd = staffAccountMax - numberStaff;
            for (int i = 0; i < staffAccAdd; i++) {
                String email = addAccountStaffSteps.randomEmail();
                addAccountStaffSteps.clickAddStaffAccount();
                addAccountStaffSteps.enterStaffEmail(email);
                addAccountStaffSteps.clickSendInvite();
                addAccountStaffSteps.refreshPage();
                addAccountStaffSteps.backToSettingPage();
            }
        }
        addAccountStaffSteps.getTexTAddStaffAccount();
    }

    @When("delete all staff account")
    public void deleteAllStaffAccount() {
        List<String> listStaffAcc = new ArrayList<>();
        listStaffAcc = addAccountStaffSteps.getListStaffAcc();
        if (listStaffAcc != null) {
            for (String s : listStaffAcc) {
                addAccountStaffSteps.chooseAccStaff(s);
                addAccountStaffSteps.clickButtonDeleteStaffAccount();
                addAccountStaffSteps.enterInputConfirmPassword(passWord);
                addAccountStaffSteps.clickButtonConfirm();
//                assertThat(addAccountStaffSteps.getTextDeleteSuccess(s)).isEqualTo("Staff " + s + " deleted successfully");
                addAccountStaffSteps.backToSettingPage();
            }
        }
        assertThat(numberStaff).isEqualTo(0);
    }

    @And("verify add staff account")
    public void verifyAddStaffAccount(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            account = SessionData.getDataTbVal(dataTable, row, "Account");
            String mess = SessionData.getDataTbVal(dataTable, row, "Msg");
            String fullPermission = SessionData.getDataTbVal(dataTable, row, "Is full permission");
            String permission = SessionData.getDataTbVal(dataTable, row, "Permission");
            boolean isFullPermission = true;

            if (!fullPermission.isEmpty()) {
                isFullPermission = Boolean.parseBoolean(fullPermission);
            }

            if (account.equalsIgnoreCase("@EMAIL@")) {
                staffUserName = account = "shopbase_" + System.currentTimeMillis() + "@mailinator.girl-viet.com";
            }

            if (addAccountStaffSteps.isSettingAccountPage()) {
                addAccountStaffSteps.clickAddStaffAccount();
            }

            addAccountStaffSteps.enterStaffEmail(account);
            addAccountStaffSteps.clickSendInvite();

            if (!mess.isEmpty()) {
                assertThat(addAccountStaffSteps.getTextAlert()).isEqualTo(mess);
            }

            if (addAccountStaffSteps.isExistAccountScreen()) {
                addAccountStaffSteps.clickBTFullPermission(isFullPermission);
                addAccountStaffSteps.selectPermission(permission);
                addAccountStaffSteps.backToSettingPage();
            }
        }
    }


    @And("verify remove staff account")
    public void verifyRemoteStaffAccount(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String account = SessionData.getDataTbVal(dataTable, row, "Account");
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            String msg = SessionData.getDataTbVal(dataTable, row, "Msg");
            addAccountStaffSteps.chooseAccStaffByEmail(account);
            addAccountStaffSteps.clickButton("Delete staff account");
            addAccountStaffSteps.enterInputConfirmPassword(password);
            addAccountStaffSteps.turnOffPopupConfirm("Confirm");

            if (!msg.isEmpty()) {
                assertThat(addAccountStaffSteps.getErrorMsg()).isEqualTo(msg);
                addAccountStaffSteps.turnOffPopupConfirm("Cancel");
            }
            addAccountStaffSteps.backToSettingPage();
        }
    }

    @And("Access email and verify permission")
    public void accessEmailAndVerifyIfNotPermissionsDisplayNotification(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String subject = SessionData.getDataTbVal(dataTable, row, "Subject");
            addAccountStaffSteps.openMailBoxWithSubject(staffUserName, subject);
//            commonSteps.logout();
//            loginStep.logInfor(shop + " with username = " + staffUserName + " and pwd = " + passWord);
//            loginStep.enterEmail(staffUserName);
//            loginStep.enterPassword(passWord);
//            loginStep.clickBtnSignIn();
//            loginStep.chooseShop(shop);
//
//            String[] _tabs = permissionsName.split(">");
//            int level = _tabs.length;
//            for (int i = 0; i < level; i++) {
//                addAccountStaffSteps.refreshPage();
//                commonSteps.switchToTab(_tabs[i]);
//            }
//            assertThat(addAccountStaffSteps.getTextNotPermission()).isEqualTo("You don’t have permission to view this page");

        }
    }

    @And("Edit status acc staff \"([^\"]*)\" and get status")
    public void editStatusAccStaffAndVerifyStatus(String accStaff) {
        addAccountStaffSteps.chooseAccStaff(accStaff);
        statusAccStaff = addAccountStaffSteps.verifyAndClick();
        addAccountStaffSteps.clickButtonSubmit();
    }

    @And("Verify login staff with status")
    public void verifyLoginStaffWith() {
        loginStep.chooseShop(shop);
        if ("Activate staff account".equals(statusAccStaff)) {
            addAccountStaffSteps.verifyShopInAccStaff(shop);
        }
        if ("Deactivate staff account".equals(statusAccStaff)) ;
        addAccountStaffSteps.verifyShopInAccStaff("not shop");
    }

    @Then("Verify if not not permissions display notification \"([^\"]*)\"")
    public void verifyIfNotNotPermissionsDisplayNotification(String Err) {
        String[] _tabs = permissionsName.split(">");
        int level = _tabs.length;
        for (int i = 0; i < level; i++) {
            addAccountStaffSteps.refreshPage();
            commonSteps.switchToTab(_tabs[i]);
        }
        addAccountStaffSteps.refreshPage();
        assertThat(addAccountStaffSteps.getTextAlert()).isEqualTo(Err);
    }


    @And("^Add account staff with email \"([^\"]*)\"$")
    public void addAccountStaffWithEmail(String email) {
        addAccountStaffSteps.clickAddStaffAccount();
        addAccountStaffSteps.enterStaffEmail(email);
        addAccountStaffSteps.clickSendInvite();
        addAccountStaffSteps.refreshPage();
        addAccountStaffSteps.backToSettingPage();
    }


    @Then("verify permission of account staff$")
    public void verifyAccess(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String access = SessionData.getDataTbVal(dataTable, row, "access");
            String account = SessionData.getDataTbVal(dataTable, row, "account");
            addAccountStaffSteps.verifyAccess(access,account);
        }
    }

    @Then("Verify staff main permission$")
    public void verifyStaffPermissionMain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String namePermission = SessionData.getDataTbVal(dataTable, row, "name permission");
            boolean access = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "access"));
            addAccountStaffSteps.verifyStaffPermissionMain(namePermission, access);
        }
    }


    @And("verify Sub permission")
    public void verifySubPermission(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String main = SessionData.getDataTbVal(dataTable, row, "main");
            String sub = SessionData.getDataTbVal(dataTable, row, "sub");
            boolean access = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "access"));
            addAccountStaffSteps.verifySubPermission(main,sub,access);
        }
    }

    @And("verify permission edit Order$")
    public void verifyPermissionEditOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String main = SessionData.getDataTbVal(dataTable, row, "main");
            String sub = SessionData.getDataTbVal(dataTable, row, "sub");
            boolean access = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "access"));
            addAccountStaffSteps.verifyPermissionEditOrder(main,sub,access);
        }
    }

    @And("verify permission export Order$")
    public void verifyPermissionExportOrder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String main = SessionData.getDataTbVal(dataTable, row, "main");
            String sub = SessionData.getDataTbVal(dataTable, row, "sub");
            boolean access = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "access"));
            addAccountStaffSteps.verifyPermissionExportOrder(main,sub,access);
            addAccountStaffSteps.turnOffPopup();
        }
    }

    @And("verify Sub permission of Balance$")
    public void verifySubPermissionOfBalance(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String main = SessionData.getDataTbVal(dataTable, row, "main");
            String sub = SessionData.getDataTbVal(dataTable, row, "sub");
            boolean access = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "access"));
            addAccountStaffSteps.verifySubPermissionOfBalance(main,sub,access);
        }
    }

    @Given("^grant permission \"([^\"]*)\" for (.*) by API$")
    public void grantPermissionEditCodeForStaffByAPI(String permissions, String actor){
        addAccountStaffSteps.grantPermissionForStaffByAPI(permissions, actor);
    }
}




