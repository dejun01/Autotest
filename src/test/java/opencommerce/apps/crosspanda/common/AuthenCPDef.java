package opencommerce.apps.crosspanda.common;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.AuthenCPSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class AuthenCPDef {

    @Steps
    AuthenCPSteps authenCPSteps;
    @Steps
    LoginDashboardSteps loginStep;

    @Given("^open sign in crosspanda page$")
    public void open_sign_in_crosspanda_page() {
        authenCPSteps.openCrossPanda();
    }

    @When("^sign in to CrossPanda$")
    public void sign_in_to_CrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pass = SessionData.getDataTbVal(dataTable, row, "Pass");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            authenCPSteps.signIn(email, pass);
            if (!msg.isEmpty()) {
                authenCPSteps.verifyMsg(msg);
            }
            if (msg.isEmpty()) {
                authenCPSteps.verifyLoginSucc();
            }
            authenCPSteps.refreshPage();
        }
    }

    @Given("^open sign up crosspanda page$")
    public void open_sign_up_crosspanda_page() {
        authenCPSteps.openCrossPanda();
        authenCPSteps.clickLinkSignUp();
    }

    @When("^validate data Sign up page in CrossPanda$")
    public void validate_data_Sign_up_page_in_CrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pass = SessionData.getDataTbVal(dataTable, row, "Password");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            authenCPSteps.signUp(email, pass);
            if (!msg.isEmpty()) {
                authenCPSteps.verifyMsg(msg);
            }
            authenCPSteps.refreshPage();
        }
    }

    @Then("^verify sign up succesfully$")
    public void verify_sign_up_succesfully(List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String pass = SessionData.getDataTbVal(dataTable, row, "Password");
            authenCPSteps.signIn(email, pass);
            authenCPSteps.verifyLoginSucc();
        }
    }

    @Then("^remove account$")
    public void remove_account(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            authenCPSteps.removeAcc(email);
        }
    }

    @Given("^go to \"([^\"]*)\" page$")
    public void go_to_page(String page) {
        authenCPSteps.gotoPage(page);
    }

    @Then("^validate data forgot password page in CrossPanda$")
    public void validate_data_forgot_password_page_in_CrossPanda(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            authenCPSteps.verifyTiltePageForgot();
            authenCPSteps.forgotPass(email);
            if (!msg.isEmpty()) {
                authenCPSteps.verifyMsg(msg);
            } else {
                authenCPSteps.verifySendLinkSucc();
            }
            authenCPSteps.refreshPage();
        }
    }

    @Then("^valid data reset password$")
    public void valid_data_reset_password(List<List<String>> dataTable) {
    }

    String email = LoadObject.getProperty("xp.email");
    String pass = LoadObject.getProperty("xp.pass");
    String shop = LoadObject.getProperty("shop");

    //    @Given("^login to crosspanda dashboard$")
//    public void login_to_crosspanda_dashboard() {
//        authenCPSteps.openCrossPanda();
//        loginStep.enterEmail(email);
//        loginStep.enterPassword(pass);
//        loginStep.clickBtnSignIn();
////        loginStep.chooseShop(shop);
//        authenCPSteps.verifyDashboardCrosspanda();
//        authenCPSteps.closeChatBox();
//    }
    @Given("redirect to crosspanda")
    public void redirect_to_crosspanda() {
        authenCPSteps.openCrossPanda();
        authenCPSteps.verifyDashboardCrosspanda();
        authenCPSteps.closeChatBox();
    }

    @Given("^verify signin page and login as \"([^\"]*)\"$")
    public void verify_signin_page_and_login_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            authenCPSteps.verifySignUpPage();
            authenCPSteps.goToSignInPage();
            authenCPSteps.signIn(email, pass);
            authenCPSteps.verifyDashboardCrosspanda();
        }
    }


}
