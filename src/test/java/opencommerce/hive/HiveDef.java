package opencommerce.hive;

import com.opencommerce.shopbase.hive.pbaseorder.steps.PBaseOrderStep;
import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import java.io.IOException;
import java.util.List;
import static com.opencommerce.shopbase.OrderVariable.orderId;

public class HiveDef {

    @Steps
    HiveStep hiveSteps;

    @Steps
    PBaseOrderStep pBaseOrderStep;

    String email = LoadObject.getProperty("emailHive");
    String pwd = LoadObject.getProperty("pwdHive");
    String linkhive = LoadObject.getProperty("hive");

    @Given("Login order detail in hive page")
    public void loginOrderDetailInHivePage() {
        pBaseOrderStep.openHivePbase(linkhive);
        pBaseOrderStep.signInHivePbase(email, pwd);
        hiveSteps.replaceUrl(linkhive, orderId);
    }

    @When("^user navigate to \"([^\"]*)\" on hive page$")
    public void user_navigate_to_on_hive_page(String _nameTab) {
        String[] _tabs = _nameTab.split(">");
        int level = _tabs.length;
        for (int i = 0; i < level; i++) {
            hiveSteps.switchToTab(_tabs[i]);
        }
    }

    @And("^verify order detail in hive page as \"([^\"]*)\"$")
    public void verifyOrderDetailInHivePage(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String artworkStatus = SessionData.getDataTbVal(dataTable, row, "Artwork status");
            String personalStatus = SessionData.getDataTbVal(dataTable, row, "Personalize status");
            String imageMockup = SessionData.getDataTbVal(dataTable, row, "Image mockup");
            String imageArtwork = SessionData.getDataTbVal(dataTable, row, "Image artwork");
            float precent = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Percent Image"));

            hiveSteps.verifyPersonalStatus(personalStatus);
            hiveSteps.verifyArtworkStatus(artworkStatus);

            String[] mockupList = imageMockup.split(";");
            for (String mockup : mockupList) {
                String[] image = mockup.split(",");
                hiveSteps.verifyImageMockup(image[0], image[1], precent);
            }

            hiveSteps.verifyImageArtwork(imageArtwork, precent);
        }
    }
}
