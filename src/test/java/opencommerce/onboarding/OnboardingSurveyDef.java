package opencommerce.onboarding;

import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import com.opencommerce.shopbase.dashboard.onboarding.steps.OnboardingSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class OnboardingSurveyDef {

    @Steps
    OnboardingSteps onboardingSteps;
    @Steps
    HiveStep hiveStep;
    @Steps
    LoginDashboardSteps loginSteps;

    @When("^User verify introduction page and click button \"([^\"]*)\"$")
    public void userVerifyIntroductionPage(String btnLabel) {
        onboardingSteps.verifyIntroductionPage();
        onboardingSteps.clickBtnStartNow(btnLabel);
    }

    @And("^user add your contact \"([^\"]*)\"$")
    public void userAddYourContact(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Store country");
            String perLocation = SessionData.getDataTbVal(dataTable, row, "Personal location");
            String contact = SessionData.getDataTbVal(dataTable, row, "Contact");
            String socialProfile = SessionData.getDataTbVal(dataTable, row, "Social profile");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            if (!country.isEmpty()) {
                onboardingSteps.inputStoreCountry(country);
            }
            if (!perLocation.isEmpty()) {
                onboardingSteps.inputYourPersonalLocation(perLocation);
            }
            onboardingSteps.inputContact(contact);
            onboardingSteps.inputPersonalSocialProfile(socialProfile);
            onboardingSteps.clickbtnNext();
            if (!message.isEmpty()) {
                onboardingSteps.verifyMsg(message);
            }
        }
    }

    @And("^User choose your business type is \"([^\"]*)\" and store type is \"([^\"]*)\"$")
    public void userChooseYourBusinessType(String businessType, String storeType) {
        onboardingSteps.verifyChooseYourBusinessTypePage();
        onboardingSteps.chooseBusinessType(businessType);
        if(!storeType.isEmpty()){
            onboardingSteps.chooseStoreType(businessType, storeType);
        }
    }

    @And("^user choose your product category \"([^\"]*)\"$")
    public void userChoosesProductCategory(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productCategory = SessionData.getDataTbVal(dataTable, row, "Product category");

            onboardingSteps.verifyChooseCategoryPage();
            if (!productCategory.isEmpty()) {
                onboardingSteps.chooseProductCategory(productCategory);
            }
        }
    }

    @And("^select store to clone \"([^\"]*)\"$")
    public void selectStoreToClone(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String selectedStore = SessionData.getDataTbVal(dataTable, row, "Selected store");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            onboardingSteps.verifyShowSelectStoreToClonePage(isShow);
            if (isShow) {
                if (!selectedStore.isEmpty()) {
                    onboardingSteps.selectStore(selectedStore);
                }
                onboardingSteps.clickAction(action);
            }
        }
    }

    @And("^User customize store with primary color is \"([^\"]*)\" and font is \"([^\"]*)\"$")
    public void userCustomizeStore(String color, String font) {
        onboardingSteps.verifyCustomizeStorePage();
        onboardingSteps.inputPrimaryColor(color);
        onboardingSteps.selectFont(font);
        onboardingSteps.clickTakeMeToMyStore();
    }


    @And("user login dashboard and select shop {string}")
    public void chooseShopAs(String shopName) {
        String shop = LoadObject.getProperty(shopName);
        String username = LoadObject.getProperty("user.name");
        String pwd = LoadObject.getProperty("user.pwd");

        loginSteps.openSignInPageInNewTab();
         loginSteps.loginShopbase(username, pwd);
        loginSteps.chooseShop(shop);
    }

    @Given("clear shop data as {string}")
    public void clearShopData(String shopId) {
        if (shopId.isEmpty()) {
            shopId = LoadObject.getProperty("shopId");
        }
        String id;
        try {
            Integer.parseInt(shopId);
            id = shopId;
        } catch (NumberFormatException e) {
            id = LoadObject.getProperty(shopId);
        }
        hiveStep.switchToTab("Storefront tool");
        hiveStep.switchToTab("Clear shop data");
        hiveStep.enterShopId(id);
        hiveStep.clickBtnClear();
        hiveStep.verifyClearSuccces();
    }

    @And("verify created shop")
    public void verifyCreatedShop() {
        onboardingSteps.verifyCreatedShop();
    }

    @And("customized onboarding questions {string}")
    public void userCompleteTheCustomizedQuestions(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            String storeType = SessionData.getDataTbVal(dataTable, row, "Store type");

            hiveStep.switchToTheFirstTab();
            hiveStep.openOnboardingQuestionList();
            boolean isConfigQuestion = hiveStep.isConfigQuestion(country, storeType);

            JSONArray questionsAndAnswers = new JSONArray();
            if (isConfigQuestion) {
                hiveStep.clickOnButton("Show", country, storeType);
                questionsAndAnswers = hiveStep.getQuestionAndAnswer();
            }

            onboardingSteps.switchToTheLastestTab();
            onboardingSteps.verifyShowOnBoardingQuestions(isConfigQuestion);
            if (isConfigQuestion) {
                for (Object object : questionsAndAnswers) {
                    JSONObject jsonObject = (JSONObject) object;

                    String question = (String) jsonObject.get("title");
                    String answer = hiveStep.getFirstAnswer(jsonObject);

                    onboardingSteps.selectAnswer(question, answer);
                }
                onboardingSteps.clickbtnNext();
            }
        }
    }

    @And("^disable password in dashboard$")
    public void disablePasswordInDashBoard() {
        onboardingSteps.disablePassword();
        onboardingSteps.verifyDisabledPassword(false);
    }
}


