package opencommerce.apps.printbase.library;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.library.CustomArtStep;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import org.yecht.Data;

import java.util.List;
import static com.opencommerce.shopbase.OrderVariable.customArtName;

public class CustomArtDef {
    @Steps
    CustomArtStep customArtStep;

    @And("^click (Import to store|Edit from sample) button with campaign \"([^\"]*)\"$")
    public void clickToButtonSaveChange(String btn, String campaign) {
        if (!customArtName.isEmpty()) {
            customArtStep.clickButtonImportOrEdit(btn, customArtName);
        } else {
            customArtStep.clickButtonImportOrEdit(btn, campaign);
        }
        if (customArtStep.isShowPopupCreateNewManualDesign()) {
            customArtStep.clickDontNotify();
            customArtStep.clickCreateCampaignBtn();
        }
    }

    @And("get Custom Art name on Custom Art list")
    public void getCustomArtNameFirst() {
        customArtName = customArtStep.getCustomArtNameFirst();

    }
}
