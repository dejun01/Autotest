package opencommerce.authen;

import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.storefront.steps.shop.AuthenSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class AuthenDef {
    @Steps
    AuthenSteps authenSteps;
    @Steps
    LoginDashboardSteps loginStep;

    @Given("^open sign up page on theme \"([^\"]*)\"$")
    public void open_sign_up_page_on_theme() {
        authenSteps.openPopupSignIn();
        authenSteps.openSignUpPage();
    }


    String shop = LoadObject.getProperty("shop");

    @Then("^verify customer sign up account to login shop$")
    public void signup_account_successfully_with_theme(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String tcase = SessionData.getDataTbVal(dataTable, row, "Case");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String firstName = SessionData.getDataTbVal(dataTable, row, "First name");
            String lastName = SessionData.getDataTbVal(dataTable, row, "Last name");
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            String repeatPassword = SessionData.getDataTbVal(dataTable, row, "Repeat password");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            long currentTime = System.currentTimeMillis();
            if (email.equalsIgnoreCase("@EMAIL@")) {
                email = "qa" + currentTime + "@yopmail.com";
            }
            System.out.println("Case: " + tcase);
            authenSteps.openPopupSignIn();
            authenSteps.openSignUpPage();
            authenSteps.enterEmail(email);
            authenSteps.enterSignupFirstName(firstName);
            authenSteps.enterSignupLastName(lastName);
            authenSteps.enterPassword(password);
            authenSteps.enterSignupRepeatPassword(repeatPassword);
            authenSteps.clickBtnSignUp();
            authenSteps.verifyMsg(result, status);
            authenSteps.clickLogout();
            authenSteps.refreshPage();

        }
    }


    @Then("^verify customer sign in to shop$")
    public void verify_sign_in_account_on_storefront_with_theme(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String tcase = SessionData.getDataTbVal(dataTable, row, "Case");
            String password = SessionData.getDataTbVal(dataTable, row, "Password");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            System.out.println("Case: " + tcase);
            loginStep.openShop(shop);
            authenSteps.openPopupSignIn();
            authenSteps.enterEmail(email);
            authenSteps.enterPassword(password);
            authenSteps.clickSignin();
            authenSteps.verifyMsg(result, status);
            authenSteps.clickLogout();

        }
    }


    @Given("^customer login to shop with username is \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void user_login_to_shop_with_username_is_and_password_is(String userName, String pwd) {
        String email = userName;
        if (userName.matches("@(.*)@")) {
            email = (String) SessionData.getDataByKey(userName);
        }

        authenSteps.openPopupSignIn();
        authenSteps.enterEmail(email);
        authenSteps.enterPassword(pwd);
        authenSteps.clickSignin();
        authenSteps.verifyLoginSuccess();
    }


    @Given("^customer logout$")
    public void logout() {
        authenSteps.hoverAccountIcon();
        authenSteps.clickLogout();
        authenSteps.verifyLogoutSuccess();
    }
}
