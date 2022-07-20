package opencommerce.apps.printhub.campaigns;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.campaigns.CampaignSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class CreateCampaignsDef {
    @Steps
    CampaignSteps campaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;
    @Steps
    MyCampaignSteps myCampaignSteps;
    float targetDiscount;
    long currentTime = System.currentTimeMillis();
    public static String campaignName = "";
    String accToken = "";
    String shopID = "";
    public static Integer quotaCampaign;
    public static Integer quotaProd;

    @Given("^add base product to campaign$")
    public void addBaseProductToCampaign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String category = SessionData.getDataTbVal(dataTable, row, "Category");
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            campaignSteps.switchToTabOnCatalog(category);
            campaignSteps.clickBtnAddProductToCampaign(baseProduct);
            campaignSteps.clickBtnNewCampaign();
            campaignSteps.verifyShowMessage("Imported successfully!");

        }
    }

    @Given("^add design to campaign with artwork$")
    public void addDesignToCampaignWithArtwork(List<List<String>> dataTable) {
        String dataTableKey = "campaignDesign";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base Product");
            String strcolor = SessionData.getDataTbVal(dataTable, row, "Color");
            String strsize = SessionData.getDataTbVal(dataTable, row, "Size");
            String awFront = SessionData.getDataTbVal(dataTable, row, "Artwork Front");
            String awBack = SessionData.getDataTbVal(dataTable, row, "Artwork Back");

            //add artwork front and back
//            campaignSteps.addArtworkToCampaign(awFront, awBack, baseProduct);

            //add color
            campaignSteps.chooseProductToAddDesign(baseProduct);
            campaignSteps.openAndCloseColorTable(baseProduct);
            if (!strcolor.isEmpty()) {
                for(int i = 1; i<= campaignSteps.countNumberOfProductColor(baseProduct); i++){
                    campaignSteps.hoverColorButton(baseProduct, i);
                    String hovercolor = campaignSteps.getColorName(baseProduct);
                    if(strcolor.contains(",")){
                        String [] colorList = strcolor.split(",");
                        for(String color : colorList){
                            if(strcolor.equalsIgnoreCase(hovercolor)){
                                campaignSteps.selectProductColors(baseProduct, i);
                            }else
                                campaignSteps.unselectProductColors(baseProduct, i);
                        }
                    }else{
                        if(strcolor.equalsIgnoreCase(hovercolor)){
                            campaignSteps.selectProductColors(baseProduct, i);
                        }else
                            campaignSteps.unselectProductColors(baseProduct, i);
                    }
                }
            }

            //add size
            if (!strsize.isEmpty()) {
                if (strsize.contains(",")) {
                    String[] sizeList = strsize.split(",");
                    for (String size : sizeList) {
                        campaignSteps.selectSizeForProduct(baseProduct, size);
                    }
                } else
                    campaignSteps.selectSizeForProduct(baseProduct, strsize);
            }
        }
        campaignSteps.clickBtnContinue();
    }

    @Given("^add description to campaign$")
    public void addCampaignDescription(List<List<String>> dataTable) {
        String dataTableKey = "campaignDescriptoion";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            boolean isShowProductDetail = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Product Detail"));
            boolean isEnableSizeChart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable size chart"));
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            if (title.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                campaignName = title.replaceAll("@", "") + "_" + currentTime;
                campaignSteps.enterTitle(campaignName);

            } else {
                campaignSteps.enterTitle(title);
            }
            campaignSteps.enterDescription(description);
            campaignSteps.showProductDetail(isShowProductDetail);
            campaignSteps.showSizeChart(isEnableSizeChart);
            campaignSteps.enterTags(tags);
            campaignSteps.clickBtnContinue();
        }
    }

    @Given("^setup price to campaign$")
    public void setupPrice(List<List<String>> dataTable) {
        String dataTableKey = "campaignPrice";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            String salePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String comparePrice = SessionData.getDataTbVal(dataTable, row, "Compare at Price");

            campaignSteps.verifyPricingTabActive();
            campaignSteps.enterSalePrice(baseProduct, salePrice);
            campaignSteps.enterCompareAtPrice(baseProduct, comparePrice);
            targetDiscount = (Float.parseFloat(comparePrice) - Float.parseFloat(salePrice)) * 100.0f / 100.0f;
        }
    }

    @Given("^verify campaign created on dashboard$")
    public void verifyCampaign(List<List<String>> dataTable) {
        String dataTableKey = "campaignPrice";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String campaign = SessionData.getDataTbVal(dataTable, row, "campaign name");
            if (campaign.matches("@(.*)@")) {
                campaignSteps.verifyCampaignDisplayed(campaignName);
                System.out.println(campaignName);

            } else
                campaignSteps.verifyCampaignDisplayed(campaign);
            System.out.println(campaign);
        }
    }
}
