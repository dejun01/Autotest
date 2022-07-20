package opencommerce.authen;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.AuthenSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.onboarding.steps.OnboardingSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class TranslateLanguageDef {

    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    AuthenSteps authenSteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    OnboardingSteps onboardingSteps;
    String url = LoadObject.getProperty("webdriver.base.url");

    @Given("^change and verify translate language is \"([^\"]*)\" in page is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void changeAndVerifyTranslateLanguageIsInPageIs(String language, String page, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");
            switch (page) {
                case "Sign up":
                    loginSteps.openURL(url + "/sign-up?display_language_bar=enabled");
                    break;
                case "Sign in":
                    loginSteps.openURL(url + "/sign-in?display_language_bar=enabled");
                    break;
            }
            loginSteps.selectLanguage(language);
            loginSteps.verifyTranslateText(language, element, english, china);
        }
    }

    @Given("change and verify translate language")
    public void changeAndVerifyTranslateLanguage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            String language = SessionData.getDataTbVal(dataTable, row, "Language");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");
            loginSteps.changeLanguage(language);
            loginSteps.verifyTranslateText(language, element, english, china);
        }
    }


    @And("^user enter information and verify language is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void enterMerchantInformationAndVerifyLanguage(String language, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");
            loginSteps.verifyTranslateText(language, element, english, china);
            if (!rowKey.equals(nextRowKey)) {
                signUpSteps.inputStoreCountry("Vietnam");
                signUpSteps.inputPersonalLocation("Vietnam");;
                onboardingSteps.inputContact("0912345678");
                onboardingSteps.clickbtnNext();
            }
        }
    }

    @Then("^verify translate language is \"([^\"]*)\" and choose Business type is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void verifyTranslateLanguageIsAndChooseBusinessType(String language, String businessType, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");
            loginSteps.verifyTranslateText(language, element, english, china);
            if (!rowKey.equals(nextRowKey)) {
                onboardingSteps.chooseBusinessType(businessType);
            }
        }

    }

    @And("^verify translate language is \"([^\"]*)\" in select store type is \"([^\"]*)\" of Business type is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void verifyTranslateLanguageIsInSelectStoreTypeIsOfBusinessType(String language, String storeType, String businessType, String dataKey, List<List<String>> dataTable) {
        onboardingSteps.verifyChooseStoreType();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");
            loginSteps.verifyTranslateText(language, element, english, china);
            if (!rowKey.equals(nextRowKey)) {
                onboardingSteps.chooseStoreType(businessType, storeType);
            }
        }
    }


    @And("^user enter shopname is \"([^\"]*)\" and verify language is \"([^\"]*)\" as \"([^\"]*)\"$")
    public void userEnterShopnameIsAndVerifyLanguageIsAs(String shopName, String language, String dataKey, List<List<String>> dataTable) {
        loginSteps.selectLanguage(language);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String element = SessionData.getDataTbVal(dataTable, row, "Element");
            String english = SessionData.getDataTbVal(dataTable, row, "English");
            String china = SessionData.getDataTbVal(dataTable, row, "China");

            loginSteps.verifyTranslateText(language, element, english, china);
            if (!rowKey.equals(nextRowKey)) {
                long currentTime = System.currentTimeMillis();
                loginSteps.clickbtnAddANewShop();
                loginSteps.enterYourShopname(shopName + currentTime);
                loginSteps.clickbtnCreate();
            }

        }
    }
}
