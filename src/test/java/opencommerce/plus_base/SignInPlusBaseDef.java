package opencommerce.plus_base;

import com.opencommerce.shopbase.plusbase.steps.SignInPlusBaseSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInPlusBaseDef {
    @Steps
    SignInPlusBaseSteps signInSteps;
    //String email = LoadObject.getProperty("Email");
    String pass = LoadObject.getProperty("user.pwd");
    //String shop = LoadObject.getProperty("shop");
    String webdriverBaseUrl = LoadObject.getProperty("webdriver.base.url");
    String username = LoadObject.getProperty("user.name");
    //String userpwd = LoadObject.getProperty("user.pwd");
    String url = LoadObject.getProperty("webdriver.base.url");

    //public static String shopName = "";
    public static String ownerEmail = "";
    long currentTime = System.currentTimeMillis();

    @Given("User sign in to PlusBase dashboard with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userSignInToPlusBaseDashboardWithEmailAndPassword(String _email, String _password) {
        signInSteps.openShop(webdriverBaseUrl + "/sign-in?plusbase=true");
        String email = _email.matches("@(.*)@") ? username : _email;
        signInSteps.enterEmail(email);
        String password = _password.matches("@(.*)@") ? pass : _password;
        signInSteps.enterPassword(password);
        signInSteps.clickBtnSignIn();
    }

    @Given("^verify customer sign up account to login PlBase shop$")
    public void verifyCustomerSignUpAccountToLoginPlBaseShop(List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sEmail = SessionData.getDataTbVal(dataTable, row, "Email");
            String sPassword = SessionData.getDataTbVal(dataTable, row, "Password");
            String sShopname = SessionData.getDataTbVal(dataTable, row, "Shopname");
            String sStatus = SessionData.getDataTbVal(dataTable, row, "status");
            long currentTime = System.currentTimeMillis();
            if (sEmail.equalsIgnoreCase("@EMAIL@")) {
                sEmail = "Email" + currentTime + "@beeketing.com";
            }
            if (sShopname.equalsIgnoreCase("@SHOPNAME@"))
                sShopname = "ShopBase" + currentTime;
            signInSteps.enterEmail(sEmail);
            signInSteps.enterPassword(sPassword);
            signInSteps.enterShopname(sShopname);
            signInSteps.clickBtnSignUp();
            if (!sStatus.equalsIgnoreCase("success")) {
                signInSteps.verifyErrorMsg(sStatus);
                signInSteps.refreshPage();
            } else {
                signInSteps.closeBrowser();
                signInSteps.clickSignUp();
            }
        }
    }

    @Given("redirect to sign up PlBase")
    public void redirectToSignUpPlBase() {
        signInSteps.openShop(webdriverBaseUrl + "/sign-up?plusbase=true");
    }
    @Given("^login to (plusbase|plusbase with url)$")
    public void loginToPlusbase(String type) {
        if("plusbase with url".equals(type)) {
            url += "/sign-in?plusbase=true";
        }
        signInSteps.openShop(url);
        signInSteps.enterEmail(username);
        signInSteps.enterPassword(pass);
        signInSteps.clickBtnSignIn();
    }

    @Then("User click Get started")
    public void clickGetStarted() {
        signInSteps.clickBtnGetStarted();
    }

    @Then("Verify display {string} page")
    public void verifyDisplayPage(String text) {
        assertThat(signInSteps.getHeader()).isEqualTo(text);

    }

    @Given("login to plusbase dashboard")
        public void loginToPlusbaseDashboard() {
        signInSteps.openShop(webdriverBaseUrl + "/sign-in?plusbase=true");
    }
}
