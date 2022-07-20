package opencommerce.hive;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.hive_pbase.step.HivePbaseCampaignSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.customArtName;
import static org.assertj.core.api.Assertions.assertThat;

public class HivePbaseCampaignPageDef {
    @Steps
    HivePbaseCampaignSteps hivePbaseCampaignSteps;

    @Steps
    PrintbaseAPI printbaseAPI;
    @Steps
    MyCampaignSteps myCampaignSteps;
    Integer idCampaign;
    String accessToken;
    Integer idBaseProduct;
    String shop = LoadObject.getProperty("shop");
    String userEmail = LoadObject.getProperty("user.name");

    @And("redirect to campaign {string} hive-pbase")
    public void redirectToCampaignHivePbase(String campaign) {
        accessToken = myCampaignSteps.getAccessTokenShopBase();
        idCampaign = printbaseAPI.getIDCampaignManual(campaign, accessToken);
        int i = 1;
        while (idCampaign == null) {
            idCampaign = printbaseAPI.getIDCampaignManual(campaign, accessToken);
            i++;
            if (i > 10)
                break;
        }
        hivePbaseCampaignSteps.redirectToCampaignHivePbase(idCampaign);
    }

    @When("action review custom art in campaign hive-pbase")
    public void actionReviewCustomArtInCampaignHivePbase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String rejectReason = SessionData.getDataTbVal(dataTable, row, "Reject Reason");
            hivePbaseCampaignSteps.clickActionReviewCustomArt(sAction);
            switch (sAction) {
                case "Design approve":
                    hivePbaseCampaignSteps.clickActionReviewCustomArt("Approve");
                    hivePbaseCampaignSteps.verifyDesignStatus("approved");
                    int i = 0;
                    while (!printbaseAPI.getStatusCampaign(idCampaign, accessToken).equalsIgnoreCase("available")) {
                        i++;
                        hivePbaseCampaignSteps.waitABit(10000);
                        if (i > 60)
                            break;
                    }
                    assertThat(printbaseAPI.getStatusCampaign(idCampaign, accessToken)).isEqualTo("available");
                    break;
                case "Design reject":
                    hivePbaseCampaignSteps.actionRejectReason(rejectReason);
                    hivePbaseCampaignSteps.verifyDesignStatus("rejected");
                    hivePbaseCampaignSteps.verifyRejectReason(rejectReason);
                    break;
                default:
                    hivePbaseCampaignSteps.actionRejectReason(rejectReason);
                    hivePbaseCampaignSteps.verifyDesignStatus("Reject");
                    hivePbaseCampaignSteps.verifyRejectReason(rejectReason);
                    break;
            }
        }
    }

    @And("open campaign list on hive")
    public void openCampaignListOnHive() {
        hivePbaseCampaignSteps.openCampaignlist();
    }

    @And("action review custom art in campaign list as {string}")
    public void actionReviewCustomArtInCampaignListAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String chooseAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String rejectReason = SessionData.getDataTbVal(dataTable, row, "Reject Reason");
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            hivePbaseCampaignSteps.chooceCampaignOnList();
            hivePbaseCampaignSteps.chooceActionReview(chooseAction);
            switch (chooseAction) {
                case "Design approves":
                    hivePbaseCampaignSteps.actionApproveCustomArt();
                    hivePbaseCampaignSteps.clickOnStatus();
                    hivePbaseCampaignSteps.chooceStatus("Approved");
                    hivePbaseCampaignSteps.clickBtnFilter();
                    hivePbaseCampaignSteps.verifyStatus(campaignName, "approved");
                    break;
                case "Design reject":
                    hivePbaseCampaignSteps.actionRejectCustomArt(rejectReason);
                    hivePbaseCampaignSteps.clickOnStatus();
                    hivePbaseCampaignSteps.chooceStatus("Rejected");
                    hivePbaseCampaignSteps.clickBtnFilter();
                    hivePbaseCampaignSteps.verifyStatus(campaignName, "rejected");
                    break;
            }
        }
    }

    @Then("filter campaign on custom art list as {string}")
    public void filterCampaignOnCustomArtListAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String isshop = SessionData.getDataTbVal(dataTable, row, "Is filter shop");
            String isemail = SessionData.getDataTbVal(dataTable, row, "Is filter email");
            hivePbaseCampaignSteps.clickBtnReviewCustomArtCampaign();
            Boolean is_Shop = false;
            Boolean is_Email = false;
            if (isshop.equalsIgnoreCase("true"))
                is_Shop = true;
            if (isemail.equalsIgnoreCase("true"))
                is_Email = true;
            hivePbaseCampaignSteps.clickBtnFilters();
            if (!campaignName.isEmpty())
                hivePbaseCampaignSteps.selectFilterCampaignName();
            if (is_Shop)
                hivePbaseCampaignSteps.selectFilterShopDomain();
            if (is_Email)
                hivePbaseCampaignSteps.selectFilterUserEmail();
            hivePbaseCampaignSteps.inputCampaignNameFilter(campaignName);
            if (is_Shop)
                hivePbaseCampaignSteps.inputShopDomainFilter(shop);
            if (is_Email)
                hivePbaseCampaignSteps.inputUserEmailFilter(userEmail);
            hivePbaseCampaignSteps.clickBtnFilter();
        }
    }


    @And("verify campaign on custom art list as {string}")
    public void verifyCampaignOnCustomArtListAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String isshop = SessionData.getDataTbVal(dataTable, row, "Is filter shop");
            String isemail = SessionData.getDataTbVal(dataTable, row, "Is filter email");
            Boolean is_Shop = false;
            Boolean is_Email = false;
            if (!campaignName.isEmpty()) {
                hivePbaseCampaignSteps.verifyCampaignNam(campaignName);
            }
            if (!isshop.isEmpty()) {
                hivePbaseCampaignSteps.verifyShopDomain(shop);
            }
            if (!isemail.isEmpty()) {
                hivePbaseCampaignSteps.verifyUserEmail(userEmail);
            }
        }
    }

    @And("redirect to mockup camp {string} hive-pbase")
    public void redirectToMockupCampHivePbase(String baseProduct) {
        accessToken = myCampaignSteps.getAccessTokenShopBase();
        idBaseProduct = printbaseAPI.getIDProductBase(baseProduct, accessToken);
        int i = 1;
        while (idBaseProduct == null) {
            idBaseProduct = printbaseAPI.getIDProductBase(baseProduct, accessToken);
            i++;
            if (i > 10)
                break;
        }
        hivePbaseCampaignSteps.redirectToMockupCampaignHivePbase(idBaseProduct);

    }

    @And("Add or edit new mockup camp on hive-pbase as {string}")
    public void addOrEditNewMockupCampOnHivePbase(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isCheckboxName = SessionData.getDataTbVal(dataTable, row, "Checkbox name");
            String isFileName = SessionData.getDataTbVal(dataTable, row, "File Name");
            String isOrinalNumber = SessionData.getDataTbVal(dataTable, row, "Mockup ordinal number");
            String checkboxStatus = SessionData.getDataTbVal(dataTable, row, "Status checkbox");
            boolean isStatus = false;
            if(isCheckboxName.equals("Add"))
                hivePbaseCampaignSteps.clickBtnAddNewMockup();
            if(checkboxStatus.equals("true"))
                isStatus = true;
            if(isOrinalNumber.isEmpty())
                isOrinalNumber = "last()";
            if (!isCheckboxName.isEmpty()) {
                hivePbaseCampaignSteps.clickCheckBoxOnMockupTab(isCheckboxName,isOrinalNumber,isStatus);
            }
            if (!isFileName.isEmpty()) {
                hivePbaseCampaignSteps.uploadThumbnailOnMockupTab(isOrinalNumber,isFileName);
            }
            hivePbaseCampaignSteps.clickBtnUpdateMockup();
        }
    }
}
