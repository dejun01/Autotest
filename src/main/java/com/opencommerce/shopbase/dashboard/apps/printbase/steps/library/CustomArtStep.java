package com.opencommerce.shopbase.dashboard.apps.printbase.steps.library;

import com.opencommerce.shopbase.dashboard.apps.printbase.library.CustomArtPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CustomArtStep extends ScenarioSteps {
    CustomArtPage customArtPage;

    @Step
    public void verifyCustomArtSceen() {
        customArtPage.verifyCustomArtSceen();
    }

    @Step
    public void clickButtonImportOrEdit(String btn, String campaign) {
        customArtPage.clickButtonImportOrEdit(btn, campaign);
    }

    @Step
    public boolean isShowPopupCreateNewManualDesign() {
        return customArtPage.isShowPopupCreateNewManualDesign();
    }
    @Step
    public void clickDontNotify(){
        customArtPage.clickDontNotify();
    }
    @Step
    public void clickCreateCampaignBtn(){
        customArtPage.clickCreateCampaignBtn();
    }

    @Step
    public String getCustomArtNameFirst() {
        String name = customArtPage.getCustomArtNameOnUI();
        return name;
    }
}
