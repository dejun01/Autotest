package opencommerce.plus_base;
import com.opencommerce.shopbase.dashboard.dashboard.steps.SignUpSteps;
import com.opencommerce.shopbase.dashboard.onboarding.steps.OnboardingSteps;
import com.opencommerce.shopbase.plusbase.steps.CreateStorePlusBaseSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
public class CreateStorePlusBaseDef {
    @Steps
    OnboardingSteps onboardingSteps;
    @Steps
    SignUpSteps signUpSteps;
    @Steps
    CreateStorePlusBaseSteps createStoreSteps;
    String url = LoadObject.getProperty("webdriver.base.url");

    @And("User add your contact")
    public void userAddYourContact(List<List<String>> data) {
        for (int row : SessionData.getDataTbRowsNoHeader(data).keySet()) {
            String country = SessionData.getDataTbVal(data, row, "Country");
            String personnalLocation = SessionData.getDataTbVal(data, row, "Personnal location");
            String contact = SessionData.getDataTbVal(data, row, "Contact");
            String socicalProfile = SessionData.getDataTbVal(data, row, "Socical profile");
            onboardingSteps.verifyAddYourContactPage();
            onboardingSteps.inputStoreCountry(country);
            onboardingSteps.inputYourPersonalLocation(personnalLocation);
            onboardingSteps.inputContact(contact);
            onboardingSteps.inputSocialProfile(socicalProfile);
            onboardingSteps.clickbtnNext();
        }
    }

    @And("User chooses plbase product category")
    public void userChoosesPlbaseProductCategory() {
        createStoreSteps.clickNoThank();
    }

    @And("User chooses plbase product niche")
    public void userChoosesPlbaseProductNiche() {
        createStoreSteps.clickBtnWantDropshipStore();
    }

    @Then("Verify display {string} logo")
    public void verifyDisplayLogo(String logo) {
        assertThat(createStoreSteps.getLogo()).isEqualTo(logo);
    }

    @And("create a plusbase shop with name {string}")
    public void createAPlusbaseShopWithName(String shop) {
        if (shop.matches("@(.*)@"))
            shop = shop.replaceAll("@", "") + System.currentTimeMillis();
            signUpSteps.logInfor("Shop: " + shop);
            signUpSteps.enterShopName(shop);
            signUpSteps.waitUntilInvisibleLoading();
            signUpSteps.clickBtnCreate();
            signUpSteps.clickCapchar();
        }
}
