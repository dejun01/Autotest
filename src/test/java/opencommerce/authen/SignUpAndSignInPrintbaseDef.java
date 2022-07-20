package opencommerce.authen;

import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;

import java.util.List;

public class SignUpAndSignInPrintbaseDef {

    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    SignUpSteps signUpSteps;


    String username = LoadObject.getProperty("user.name");
    String userpwd = LoadObject.getProperty("user.pwd");
    String url = LoadObject.getProperty("webdriver.base.url");
    public static String shops = "";


    @Given("^create a shop with name \"([^\"]*)\"$")
    public void createAShopWithName(String shop) {
        if (shop.matches("@(.*)@"))
            shop = shop.replaceAll("@", "") + System.currentTimeMillis();
        shops = shop.replaceAll("@", "") + System.currentTimeMillis();
        signUpSteps.logInfor("Shop: " + shop);
        if (signUpSteps.isDashboardHomepage()) {
            signUpSteps.clickSelectAnotherShop();
            signUpSteps.enterShopName(shop);
            signUpSteps.waitUntilInvisibleLoading();
            signUpSteps.clickBtnCreate();
        } else {
            if (!url.contains("printbase") && !url.contains("plusbase")) {
                signUpSteps.clickBtnAddANewShop();
            }
            signUpSteps.enterShopName(shop);
            signUpSteps.waitUntilInvisibleLoading();
            signUpSteps.clickBtnCreate();
            signUpSteps.clickCapchar();
        }
        signUpSteps.waitUntilInvisibleLoading();
    }

    @Given("Input information merchant")
    public void inputInformationMerchant(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sStoreCountry = SessionData.getDataTbVal(dataTable, row, "Store country");
            String sYourPersonalLocation = SessionData.getDataTbVal(dataTable, row, "Your personal location");
            String sPhoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String sBusiness = SessionData.getDataTbVal(dataTable, row, "Business");
            String value_prod = SessionData.getDataTbVal(dataTable, row, "Value prod");
            String value_stag = SessionData.getDataTbVal(dataTable, row, "Value stag");
            String _printbase = SessionData.getDataTbVal(dataTable, row, "Print base");
            boolean isPrintbase = false;
            if (_printbase.equalsIgnoreCase("true"))
                isPrintbase = true;
            signUpSteps.clickBtnStartNow();
            signUpSteps.selectStoreCountry(sStoreCountry);
            signUpSteps.selectYourPersonalLocation(sYourPersonalLocation);
            signUpSteps.enterPhoneNumber(sPhoneNumber);
            if (!sBusiness.equals("")) {
                signUpSteps.enterEmailCustomer(sBusiness);
            }
            signUpSteps.clickBtnNext();
            signUpSteps.clickBtnNoThank();
            if (isPrintbase = false) {
                signUpSteps.selectQuestion(value_prod, value_stag);
                signUpSteps.clickTakeMeToMyStore();
            }
            signUpSteps.verifyCreatedShopPrintBase();
        }
    }

    @Then("verify message {string} if store name is empty")
    public void verifyMessageIfStoreNameIsEmpty(String message) {
        loginSteps.verifyErrorLoginMsg(message);
    }

    @Then("Verify data default Pages")
    public void verifyDataDefaultPages(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            signUpSteps.verifyExistPageDefault(title);
        }
    }

    @Given("^login dashboard email is \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void loginDashboardEmail(String email, String pw) {
        loginSteps.openURL(url + "/sign-in");
        loginSteps.enterEmail(email);
        loginSteps.enterPassword(pw);
        loginSteps.clickBtnSignIn();
        loginSteps.verifySelectShopScreen();
    }

    @When("^login dashboard email is \"([^\"]*)\" and password is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void loginDashboardEmailAndPassword(String email, String pw, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            loginSteps.openURL(url + "/logout");
            loginSteps.enterEmail(email);
            loginSteps.enterPassword(pw);
            loginSteps.clickBtnSignIn();
            loginSteps.verifySelectShopScreen();
        }
    }

}
