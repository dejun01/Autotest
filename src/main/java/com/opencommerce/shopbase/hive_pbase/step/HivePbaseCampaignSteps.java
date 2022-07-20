package com.opencommerce.shopbase.hive_pbase.step;

import com.opencommerce.shopbase.hive_pbase.page.HivePbaseCampaignPage;
import cucumber.runtime.filter.Filters;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class HivePbaseCampaignSteps extends ScenarioSteps {
    HivePbaseCampaignPage hivePbaseCampaignPage;

    @Step
    public void redirectToCampaignHivePbase(Integer idCampaign) {
        hivePbaseCampaignPage.redirectToCampaignHivePbase(idCampaign);
    }

    @Step
    public void clickActionReviewCustomArt(String sAction) {
        hivePbaseCampaignPage.clickActionReviewCustomArt(sAction);
        hivePbaseCampaignPage.waitForEverythingComplete();
        hivePbaseCampaignPage.waitForPageLoad();
    }

    @Step
    public void actionRejectReason(String reason) {
        hivePbaseCampaignPage.inputRejectReason(reason);
        hivePbaseCampaignPage.clickBtnReject();
        hivePbaseCampaignPage.waitForEverythingComplete();
        hivePbaseCampaignPage.waitForPageLoad();
    }

    @Step
    public void verifyDesignStatus(String status) {
        hivePbaseCampaignPage.verifyDesignStatus(status);
    }

    @Step
    public void verifyRejectReason(String reason) {
        hivePbaseCampaignPage.verifyRejectReason(reason);
    }
    @Step
   public void verifyCampaignNam(String campaignName) {
        if(!campaignName.isEmpty())
            hivePbaseCampaignPage.verifyCampaignNam(campaignName);
    }
    @Step
    public void verifyShopDomain(String shop) {
            hivePbaseCampaignPage.verifyShopDomain(shop);
    }
    @Step
    public void verifyUserEmail(String userEmail) {
            hivePbaseCampaignPage.verifyUserEmail(userEmail);
    }
    @Step
    public void verifyStatus(String campaignName,String status) {
        hivePbaseCampaignPage.verifyStatus(campaignName,status);
    }
    @Step
    public void selectFilterCampaignName( ) {
        hivePbaseCampaignPage.selectFilterCampaignName();
    }

    @Step
    public void selectFilterShopDomain() {
        hivePbaseCampaignPage.selectFilteShopDomain();
    }
    @Step
    public void selectFilterUserEmail() {
        hivePbaseCampaignPage.selectFilteUserEmail();
    }
    @Step
    public void inputCampaignNameFilter(String campaignName) {
        if (!campaignName.isEmpty())
            hivePbaseCampaignPage.inputCampaignName(campaignName);
    }

    @Step
    public void inputShopDomainFilter(String shop) {
        if (!shop.isEmpty())
            hivePbaseCampaignPage.inputShopDomain(shop);
    }
    @Step
    public void inputUserEmailFilter(String userEmail) {
        if (!userEmail.isEmpty())
            hivePbaseCampaignPage.inputUserEmail(userEmail);
    }
    @Step
    public void clickBtnFilter() {
        hivePbaseCampaignPage.clickBtnFilter();
        hivePbaseCampaignPage.waitForEverythingComplete();
        hivePbaseCampaignPage.waitForPageLoad();
    }

    @Step
    public void openCampaignlist() {

        hivePbaseCampaignPage.clickCustomSupport();
    }
    @Step
    public void clickBtnReviewCustomArtCampaign(){
        hivePbaseCampaignPage.clickBtnReviewCustomArtCampaign();
    }

    @Step
    public void chooceStatus(String label) {
        hivePbaseCampaignPage.chooseStatus(label);
    }
    @Step
    public void chooceActionReview(String chooseAction) {
        hivePbaseCampaignPage.chooseActionReviewCustomArt(chooseAction);
        hivePbaseCampaignPage.clickBtnOke();
    }
    @Step
    public void actionRejectCustomArt(String rejectReason) {
        hivePbaseCampaignPage.inputRejectReason(rejectReason);
        hivePbaseCampaignPage.clickBtnYes();

    }

    public void actionApproveCustomArt() {
        hivePbaseCampaignPage.clickBtnYes();
    }

    public void clickOnStatus() {
        hivePbaseCampaignPage.clickOnStatus();
    }


    public void chooceCampaignOnList() {
        hivePbaseCampaignPage.chooseCampaignOnList();
    }

    public void clickBtnFilters() {
        hivePbaseCampaignPage.clickBtnFilters();
    }

    @Step
    public void redirectToMockupCampaignHivePbase(Integer idBaseProduct) {
        hivePbaseCampaignPage.redirectToMockupCampaignHivePbase(idBaseProduct);
        hivePbaseCampaignPage.clickTabMockup();
    }

    @Step
    public void clickBtnAddNewMockup() {hivePbaseCampaignPage.clickBtnAddNewMockup();}

    @Step
    public void clickCheckBoxOnMockupTab(String checkBoxName, String orinalNumber,boolean statusCheckbox) {
        switch (checkBoxName) {
            case "Delete":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("_delete",orinalNumber,statusCheckbox);
                break;
            case "Default":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("isDefault",orinalNumber,statusCheckbox);
                break;
            case "NotUse":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("notUse",orinalNumber,statusCheckbox);
                break;
            case "MockupFulfill":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("mockupFulfillPersonalize",orinalNumber,statusCheckbox);
                break;
            case "Photopea":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("usePhotopea",orinalNumber,statusCheckbox);
                break;
            case "PtsApi":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("usePtsApi",orinalNumber,statusCheckbox);
                break;
            case "Preview":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("isUsePreview",orinalNumber,statusCheckbox);
                break;
            case "PersonalizeLabel":
                hivePbaseCampaignPage.clickCheckBoxOnMockupTab("usePersonalizeLabel",orinalNumber,statusCheckbox);
                break;
        }
    }

    @Step
    public void uploadThumbnailOnMockupTab(String orinalNumber,String fileName) {
        hivePbaseCampaignPage.uploadThumbnailOnMockupTab(orinalNumber,fileName);
    }

    @Step
    public void clickBtnUpdateMockup() {hivePbaseCampaignPage.clickBtnUpdateMockup();}
}
