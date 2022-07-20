package opencommerce.hive_sbase;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.FooterSteps;
import com.opencommerce.shopbase.hive.pbaseorder.steps.TierSteps;
import com.opencommerce.shopbase.hive_sbase.steps.SocialProofSteps;
import common.CommonPageObject;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class SocialProofDef {
    @Steps
    SocialProofSteps socialProofSteps;
    @Steps
    CommonSteps commonSteps;

    int preView;
    int preClick;
    float preCR;
    String id = LoadObject.getProperty("id");
    long currentTime = System.currentTimeMillis();
    String nameSP="";


    @When("verify social proof list page")
    public void verify_social_proof_list_page() {
        socialProofSteps.navigatetoSocialProofListpage();
        socialProofSteps.verifySocialProofInformation();
    }


    @Then("create social proof")
    public void createSocialProof(List<List<String>> dataTable) {
        socialProofSteps.clickonAddnewbutton();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String displaytime = SessionData.getDataTbVal(dataTable, row, "Display time");
            String waitingtime = SessionData.getDataTbVal(dataTable, row, "Waiting time");
            String delaytime = SessionData.getDataTbVal(dataTable, row, "Delay time");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String time = SessionData.getDataTbVal(dataTable, row, "Time");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String message = SessionData.getDataTbVal(dataTable, row, "Messages");
            if (name.equals("@Social proof@")) {
                name = name + currentTime +"";
                nameSP = name;
            }
            socialProofSteps.enterName(name);
            socialProofSteps.enterDisplaytime(displaytime);
            socialProofSteps.enterWaitingtime(waitingtime);
            socialProofSteps.enterDelaytime(delaytime);
            socialProofSteps.enterTitle(title);
            socialProofSteps.enterContent(content);
            socialProofSteps.enterTime(time);
            socialProofSteps.uploadImage(image);
            socialProofSteps.clickStartUpload();
            socialProofSteps.enterLink(link);
            socialProofSteps.clickCreatebutton();
            if (message.equals(""))
            {
                socialProofSteps.verifyNotCreateSocialProof();
                socialProofSteps.clickRemove();
            }
            if (message.equals("This value should be positive."))
            {
                socialProofSteps.verifyMsg(message);
                socialProofSteps.clickRemove();
            }
            if (message.equals("Wrong delay time, please check"))
            {
                socialProofSteps.verifyErrorMsg(message);
                socialProofSteps.clickRemove();
            }
            if (message.equals("Unique index duplicate entry name, this name had existed"))
            {
                socialProofSteps.verifyMsgWhenInputSocialProofExist(message);
                socialProofSteps.clickRemove();
            }
            if (message.equals("has been successfully created."))
                socialProofSteps.verifyCreateSocialProofSuccess();
        }
    }

    @Then("edit social proof")
    public void editSocialProof(List<List<String>> dataTable) {
        socialProofSteps.navigatetoSocialProofListpage();
        preView = socialProofSteps.getView(id);
        preClick = socialProofSteps.getClick(id);
        preCR = socialProofSteps.getCR(id);
        socialProofSteps.clickonEditbutton(nameSP);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String displaytime = SessionData.getDataTbVal(dataTable, row, "Display time");
            String waitingtime = SessionData.getDataTbVal(dataTable, row, "Waiting time");
            String delaytime = SessionData.getDataTbVal(dataTable, row, "Delay time");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String time = SessionData.getDataTbVal(dataTable, row, "Time");

            socialProofSteps.enterName(name);
            socialProofSteps.enterDisplaytime(displaytime);
            socialProofSteps.enterWaitingtime(waitingtime);
            socialProofSteps.enterDelaytime(delaytime);
            socialProofSteps.enterTitle(title);
            socialProofSteps.enterContent(content);
            socialProofSteps.enterTime(time);
            socialProofSteps.enterLink(link);
            socialProofSteps.clickonUpdatebutton();
        }
    }

    @And("delete social proof")
    public void deleteSocialProof() {
        socialProofSteps.navigatetoSocialProofListpage();
        socialProofSteps.clickDetelebutton();
        socialProofSteps.clickYesbutton();
    }

    @When("verify display Social Proof")
    public void verifyDisplaySocialProof() {
        socialProofSteps.verifyDisplaySocialProof();
        socialProofSteps.clickSocialProof();
    }

    @And("verify view and click after social proof display")
    public void verifyViewAndClickAfterSocialProofDisplay() {
        socialProofSteps.navigatetoSocialProofListpage();
        commonSteps.refreshPage();
        socialProofSteps.verifyViewAndClickAfterSocialProofDisplay(id);

    }
}

