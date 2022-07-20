package opencommerce.apps.printhub.orders.campaign;

import com.opencommerce.shopbase.OrderVariable;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.opencommerce.shopbase.OrderVariable.customArtName;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CampaignDef {
    @Steps
    CampaignSteps campaignSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    OrderSteps orderSteps;
    @Steps
    MyCampaignSteps myCampaignSteps;
    float targetDiscount;
    int actualVariantInDashboard;
    public static String campaignName;
    String product = "";
    public static boolean isKeepArtWork_Dup = false;
    PrintbaseAPI printbaseAPI;

    @When("^input data to create campaign as \"([^\"]*)\"$")
    public void input_data_to_create_campaign_on_app_Phub_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String artworks = SessionData.getDataTbVal(dataTable, row, "Artwork");
            String design = SessionData.getDataTbVal(dataTable, row, "Design");
            String sColors = SessionData.getDataTbVal(dataTable, row, "Colors");
            String sSizes = SessionData.getDataTbVal(dataTable, row, "Sizes");
            String isAddMoreProduct = SessionData.getDataTbVal(dataTable, row, "Is add more product");
            String isRemoveProduct = SessionData.getDataTbVal(dataTable, row, "Is remove product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            boolean preview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Preview"));
            String scolorSelected = SessionData.getDataTbVal(dataTable, row, "Colors selected");
            String ssizeSelected = SessionData.getDataTbVal(dataTable, row, "Sizes selected");

            campaignSteps.closeArtworkLibraryPopup();
            boolean isAddProduct = false;
            if (!isAddMoreProduct.isEmpty()) {
                isAddProduct = Boolean.parseBoolean(isAddMoreProduct);
            }
            boolean isRemove_product = false;
            if (!isAddMoreProduct.isEmpty())
                isRemove_product = Boolean.parseBoolean(isRemoveProduct);
            if (isAddProduct) {
                campaignSteps.clickAddMoreProduct();
                campaignSteps.waitUntilInVisibleLoadingTable();
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.addProductToCampaign(sProduct);
                campaignSteps.clickUpdateToCampaign();
            }
            if (isRemove_product) {
                campaignSteps.clickAddMoreProduct();
                campaignSteps.waitUntilInVisibleLoadingTable();
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.removeProductToCampaign(sProduct);
                campaignSteps.clickUpdateToCampaign();
            }

            if (!sSizes.isEmpty()) {
                String[] sizes = sSizes.split(",");
                List<String> listSizes = new ArrayList<>(Arrays.asList(sizes));
                campaignSteps.selectSize(sProduct, listSizes);
            }

            if (!sColors.isEmpty()) {
                String[] colors = sColors.split(",");
                List<String> listColors = new ArrayList<>(Arrays.asList(colors));
                campaignSteps.selectColors(sProduct, listColors);
            }

            if (!scolorSelected.isEmpty()) {
                String[] colorSelected = scolorSelected.split(",");
                List<String> listColorSelected = new ArrayList<>(Arrays.asList(colorSelected));
                campaignSteps.verifySelectedColors(sProduct, listColorSelected);
            }

            if (!ssizeSelected.isEmpty()) {
                String[] sizeSelected = ssizeSelected.split(",");
                List<String> listSizeSelected = new ArrayList<>(Arrays.asList(sizeSelected));
                campaignSteps.verifySelectedSizes(sProduct, listSizeSelected);
            }

            if (!artworks.isEmpty() && !isRemove_product) {
                campaignSteps.selectBaseProduct(sProduct);
                campaignSteps.addArtWorks(artworks);
            }
            if (!design.isEmpty()) {
                campaignSteps.editDesigns(design);
            }
            if (preview) {
                campaignSteps.clickBtnPreview();
            }
        }
    }

    @When("^input data to create description for campaign as \"([^\"]*)\"$")
    public void input_data_to_create_description_campaign(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sTitle = SessionData.getDataTbVal(dataTable, row, "Title");
            String sDescription = SessionData.getDataTbVal(dataTable, row, "Description");
            String sIsIncludeProductDetails = SessionData.getDataTbVal(dataTable, row, "Is include product details");
            String sTag = SessionData.getDataTbVal(dataTable, row, "Tags");
            if (sTitle.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                campaignName = sTitle.replaceAll("@", "") + "_" + currentTime;
                OrderVariable.productName = campaignName;
            } else
                campaignName = sTitle;
            campaignSteps.enterTitle(campaignName);
            campaignSteps.enterDescription(sDescription);
            campaignSteps.selectIncludeProductDetailInCampaignDescription(sIsIncludeProductDetails);
            campaignSteps.enterTags(sTag);
        }
    }

    @When("^calculate number of variant for campaign as \"([^\"]*)\"$")
    public void calculate_number_of_variant_for_campaign_on_app_Phub_as(String
                                                                                dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int numberVariants = 0;
            String key = SessionData.getDataTbVal(dataTable, row, "KEY");
            clickToTabInCampaign("Design");
            int numberProduct = campaignSteps.countBaseProduct();
            for (int i = 1; i <= numberProduct; i++) {
                int numberColor = campaignSteps.countColorSelected(i);
                int numberSize = campaignSteps.countSizeSelected(i);
                System.out.println(numberColor + "----" + numberSize);
                numberVariants = numberVariants + (numberColor * numberSize);
            }
            actualVariantInDashboard = campaignSteps.countVariantInApp();
            System.out.println(actualVariantInDashboard);
            assertThat(actualVariantInDashboard).isLessThanOrEqualTo(numberVariants);
            SessionData.saveDataByKey(key, actualVariantInDashboard);
        }
    }

    @When("^input product price for campaign as \"([^\"]*)\"$")
    public void input_product_price_for_campaign_of_Phub_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String sSalePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String sCompareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            System.out.println(" product bef" + product);
            if (!product.equalsIgnoreCase(sProduct)) {
                campaignSteps.clickSetIndividualPriceInPricing(sProduct);
                product = sProduct;
                System.out.println(" Product " + product);
            }
            if (!variant.isEmpty()) {
                campaignSteps.inputSalePrice(sProduct, variant, sSalePrice);
                campaignSteps.inputComparePrice(sProduct, variant, sCompareAtPrice);
            }
            targetDiscount = (Float.parseFloat(sCompareAtPrice) - Float.parseFloat(sSalePrice)) * 100.0f / 100.0f;
        }

    }

    @Then("^verify campaign created as \"([^\"]*)\"$")
    public void verify_campaign_created_on_app_Phub_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String s_campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String isEnableDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable duplicate");
            String isEnableBulkDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable bulk duplicate");
            String ordinalNumbers = SessionData.getDataTbVal(dataTable, row, "Ordinal numbers");
            if (s_campaignName.matches("@(.*)@"))
                s_campaignName = campaignName;
            campaignSteps.searchCampaign(s_campaignName);
            campaignSteps.verifyProductAndStatus(s_campaignName, status);
            campaignSteps.verifyBtnDuplicateEnable(isEnableDuplicate, 1);
            campaignSteps.verifyBtnBulkDuplicateEnable(isEnableBulkDuplicate, 1);
            if (!ordinalNumbers.isEmpty())
                campaignSteps.verifyOrdinalNumbersOfCampaign(s_campaignName, ordinalNumbers);
        }
    }

    @Then("^redirect to product detail on Storefront$")
    public void redirect_to_product_detail_on_Storefront() {
        campaignSteps.clickBtnViewOnStore();
        campaignSteps.switchNewTabOnBrowser();
    }

    @Given("^bulk duplicate campaign as \"([^\"]*)\"$")
    public void bulk_duplicate_campaign_on_Phub_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String artwork = SessionData.getDataTbVal(dataTable, row, "Artwork");
            campaignSteps.searchKeyword(campaignName);
            campaignSteps.clickBulkDuplicateIcon(campaignName);
            campaignSteps.addArtWorkForBulkDuplicate(artwork);
            campaignSteps.clickBtnLaunchCampaigns();
            campaignSteps.waitUntilLoadingSuccess();
        }
    }

    @And("^duplicate campaign Phub as \"([^\"]*)\"$")
    public void duplicate_campaign_Phub_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String newName = SessionData.getDataTbVal(dataTable, row, "Provide a name for your new campaign");

            campaignSteps.clickDuplicateIcon(campaignName);
            if (!newName.isEmpty()) {
                campaignSteps.enterProvideNameForNewCampaign(newName);
            }
            campaignSteps.confirmDuplicateCampaign();
        }
    }


    @And("^get price variants of base product at pricing tab as \"([^\"]*)\"$")
    public void getPriceVariantsOfBaseProductAtPricingTabAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Expand variant of Base product"));
            String getSalePrice = SessionData.getDataTbVal(dataTable, row, "Get Sale price product");
            String getCompareAtPrice = SessionData.getDataTbVal(dataTable, row, "Get Compare at price product");
            float salePriceProductExpected, compareAtPriceProductExpected;
            if (!getSalePrice.isEmpty()) {
                salePriceProductExpected = Float.parseFloat(getSalePrice);
                compareAtPriceProductExpected = Float.parseFloat(getCompareAtPrice);
                float salePriceProductActual = campaignSteps.getSalePriceProduct(baseProduct);
                Assert.assertEquals(salePriceProductExpected, salePriceProductActual, 0);
                targetDiscount = (compareAtPriceProductExpected - salePriceProductExpected) * 100.0f / 100.0f;
            }
            campaignSteps.expandBaseProductBlock(baseProduct, status);
        }
    }

    @And("^search product or campaign or orders at list page in dashboard as \"([^\"]*)\"$")
    public void searchProductOrCampaignOrOrdersAtListPageInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");
            if (keyword.matches("@(.*)@"))
                keyword = campaignName;
            campaignSteps.searchKeyword(keyword);
            int i = 1;
            while (campaignSteps.isExistCampaignListNull()) {
                campaignSteps.refreshPage();
                campaignSteps.waitUntilListTableVisible();
                campaignSteps.waitABit(5000);
                i++;
                if (i > 10)
                    break;
            }
            campaignSteps.verifyResultSearch(keyword);
        }
    }

    @And("^open product details in dashboard or editor campaign \"([^\"]*)\"$")
    public void openProductDetailsInDashboar(String campaign) {
        if (campaign.matches("@(.*)@")) {
            campaign = campaignName;
        }
        campaignSteps.clickCampaignName(campaign);
        campaignSteps.waitUntilDisplayProductDetailPage(campaign);
    }


    @And("^open product details in dashboard or editor campaign as \"([^\"]*)\"$")
    public void openProductDetailsInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            campaignSteps.clickCampaignName(campaignName);
            campaignSteps.waitUntilDisplayProductDetailPage(campaignName);
        }
    }

    @And("^verify product information in dashboard as \"([^\"]*)\"$")
    public void verifyProductInformationInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String isEnableDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable duplicate");
            String isEnableBulkDuplicate = SessionData.getDataTbVal(dataTable, row, "Is enable bulk duplicate");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            campaignSteps.verifySKU(sku);
            if (!tag.isEmpty()) {
                productDetailSteps.verifyTags(tag, true);
            }
            campaignSteps.verifyBtnDuplicateEnable(isEnableDuplicate, 1);
            campaignSteps.verifyBtnBulkDuplicateEnable(isEnableBulkDuplicate, 1);
            campaignSteps.verifyDescription(description);
        }
    }

    @And("^verify product or campaign information in dashboard as \"([^\"]*)\"$")
    public void verifyProductOrCampaignInformationInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String tag = SessionData.getDataTbVal(dataTable, row, "Tags");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            if (!tag.isEmpty()) {
                productDetailSteps.verifyTag(tag);
            }
            campaignSteps.verifyBtnDuplicateEnable("true", 1);
            campaignSteps.verifyBtnBulkDuplicateEnable("true", 1);
            campaignSteps.verifyDescription(description);
        }
    }

    @And("^open product details on Storefront from product detail in dashboard$")
    public void openProductDetailsOnStorefrontFromProductDetailInDashboard() {
        campaignSteps.waitABit(5000);
        commonSteps.closePopup();
        campaignSteps.openProductDetailOnStorefront();
        campaignSteps.waitABit(5000);
        campaignSteps.switchNewTabOnBrowser();
    }

    @And("^open product details on Storefront from product detail in dashboard as \"([^\"]*)\"$")
    public void openProductDetailsOnStorefrontFromProductDetailInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            openProductDetailsOnStorefrontFromProductDetailInDashboard();
        }
    }


    @And("^verify total variant push to store as \"([^\"]*)\"$")
    public void verifyTotalVariantPushToStoreAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int countVariantOnStore = campaignSteps.countVariantOnStore();
            Assert.assertEquals(actualVariantInDashboard, countVariantOnStore);
        }
    }

    @Then("^verify product information from pod on storefront as \"([^\"]*)\"$")
    public void verifyProductInformationFromAppPhubOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String s_productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String getSalePrice = SessionData.getDataTbVal(dataTable, row, "Sale price");
            String getCompareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            Float actual_SalePrice, ex_SalePrice, actual_ComparePrice, ex_ComparePrice;
            if (s_productName.matches("@(.*)@"))
                s_productName = campaignName;
            productSteps.refreshPage();
            productSteps.verifyName(s_productName);
            if (!style.isEmpty()) {
                productSteps.selectStyle(style);
            }
            if (!color.isEmpty()) {
                productSteps.selectVariant(color);
            }
            if (!size.isEmpty())
                productSteps.selectVariant(size);
            if (!getSalePrice.isEmpty()) {
                actual_SalePrice = productSteps.getSalePriceOnSF();
                actual_ComparePrice = productSteps.getCompareAtPriceVariantOnSF();
                ex_SalePrice = Float.parseFloat(getSalePrice);
                ex_ComparePrice = Float.parseFloat(getCompareAtPrice);
                Assert.assertEquals(ex_SalePrice, actual_SalePrice);
                Assert.assertEquals(ex_ComparePrice, actual_ComparePrice);
            }
        }
    }

    @And("^implement action with campaign as \"([^\"]*)\"$")
    public void implementActionWithCampaignAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String actionName = SessionData.getDataTbVal(dataTable, row, "Action name");
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");

            campaignSteps.waitUntilLoadingSuccess();
            campaignSteps.searchCampaign(campaignName);
            if (!campaignSteps.isExistCampaignListNull()) {
                campaignSteps.selectAllCampaign();
                campaignSteps.verifyShowActionTable();
                if (isExistActionTable()) {
                    campaignSteps.clickActionbtn();
                    switch (actionName) {
                        case "Delete selected campaigns":
                            campaignSteps.clickDeleteSelectedCampaignBtn();
                            campaignSteps.confirmDelete();
                            break;
                        case "Make 1 campaign unavailable":
                            campaignSteps.clickMakeCampaignUnavailableBtn();
                            campaignSteps.confirmAction("Make 1 campaign unavailable");
                            campaignSteps.verifyShowMessage("Hide live campaign");
                            break;
                        case "Make 1 campaign available":
                            campaignSteps.clickMakeCampaignAvailableBtn();
                            campaignSteps.confirmAction("Make 1 campaign available");
                            campaignSteps.verifyShowMessage("Published live campaign");
                            break;
                    }
                }
            }
        }
    }

    private boolean isExistActionTable() {
        return campaignSteps.isExistActionTable();
    }

    @Then("^verify result after implement action as \"([^\"]*)\"$")
    public void verifyResultAfterImplementActionAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String labelName = SessionData.getDataTbVal(dataTable, row, "Label name");
            String rejectReason = SessionData.getDataTbVal(dataTable, row, "Reject reason");
            if(!customArtName.isEmpty())
                campaignName = customArtName;
            if (!campaignSteps.isExistCampaignListNull() && !campaignName.isEmpty()) {
                campaignSteps.switchToTabOnCampaignsPage("All");
                campaignSteps.verifyProductAndStatus(campaignName, labelName);
                switch (labelName) {
                    case "Unavailable":
                        campaignSteps.switchToTabOnCampaignsPage("Unavailable campaigns");
                        campaignSteps.verifyProductAndStatus(campaignName, labelName);
                        break;
                    case "Available":
                        labelName = "LIVE";
                        if (campaignSteps.isExistProduct(campaignName)) {
                            campaignSteps.switchToTabOnCampaignsPage("Available campaigns");
                            campaignSteps.verifyProductAndStatus(campaignName, labelName);
                        }
                        break;
                    case "Rejected":
                        campaignSteps.verifyRejectReason(rejectReason);
                        break;
                    case "Delete":
                        campaignSteps.verifyShowCampaign(campaignName, false);
                        break;
                }
            }
        }
    }

    @When("^add products to campaign as \"([^\"]*)\"$")
    public void addProductsToCampaignAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            campaignSteps.waitUntilInVisibleLoadingTable();
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.addProductToCampaign(sProduct);
            }
        }
        campaignSteps.clickBtnNewCampaign();
    }


    @And("^switch to \"([^\"]*)\" tab in editor screen of Phub & Pbase$")
    public void switchToTabInEditorScreenOfPhubPbase(String tabName) {
        campaignSteps.clickBtnContinue();
    }

    @And("^implement action \"([^\"]*)\"$")
    public void implementAction(String actionName) {
        campaignSteps.verifyShowSizeChartBlock();
        switch (actionName) {
            case "Enable size chart":
                campaignSteps.clickEnableSizeChartBtn();
                break;
            case "Disable size chart":
                campaignSteps.clickDisableSizeChartBtn();
                break;
        }
    }

    @And("^verify show size chart when \"([^\"]*)\" at product \"([^\"]*)\"$")
    public void verifyShowSizeChartWhenAtProduct(String actionName, String productName) {
        productSteps.verifyName(productName);
        switch (actionName) {
            case "Enable size chart":
                productSteps.isShowSizeChart(true);
                break;
            case "Disable size chart":
                productSteps.isShowSizeChart(false);
                break;
        }
    }

    @Then("^verify data of size chart on storefront (next|normal) as \"([^\"]*)\"$")
    public void verifyDataOfSizeChartOnStorefrontAs(String typeSF, String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            String unit = SessionData.getDataTbVal(dataTable, row, "Unit");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String width = SessionData.getDataTbVal(dataTable, row, "Chest width (Width)");
            String length = SessionData.getDataTbVal(dataTable, row, "Body length(Length)");
            String nextStyle = SessionData.getDataTbVal(dataTable, row + 1, "Style");
            if (nextStyle.isEmpty()) {
                nextStyle = style;
            }
            productSteps.verifyName(productName);

            if (!style.isEmpty() & !style.equalsIgnoreCase(tmp)) {
                if (typeSF.equalsIgnoreCase("next"))
                    productSteps.selectStyleSFNext(style);
                else
                    productSteps.selectStyle(style);
                productSteps.openSizeChart();
                productSteps.verifySizeChartSelected(style);
            }
            if (!baseProduct.isEmpty()) {
                productSteps.selectProductOnSizeChart(baseProduct);
            }
            if (!unit.isEmpty()) {
                productSteps.selectUnitOnSizeChart(unit);
            }
            productSteps.verifyShowImageSizeChart();
            productSteps.verifyDataSizeChart(size, width, length);
            tmp = style;
            if (style != nextStyle) {
                productSteps.closeSizeChartPopup();
            }
        }
    }

    @And("^open size chart as \"([^\"]*)\"$")
    public void openSizeChartAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            productSteps.openSizeChart();
        }
    }

    @And("^close size chart as \"([^\"]*)\"$")
    public void closeSizeChartAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            productSteps.closeSizeChartPopup();
        }
    }

    @And("^select style of product on storefront as \"([^\"]*)\"$")
    public void selectStyleOfProductOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String s_color = SessionData.getDataTbVal(dataTable, row, "Color");
            String s_variant=SessionData.getDataTbVal(dataTable, row, "Size");
            if (!productName.isEmpty())
                productSteps.verifyName(productName);
            if (!style.isEmpty()) {
                productSteps.selectStyleSFNext(style);
            }
            if (!s_color.isEmpty())
                productSteps.selectColorOnStorefront(s_color);

            productSteps.selectVariant(s_variant);
        }
    }

    @And("^click to button \"([^\"]*)\"$")
    public void clickToButton(String btnName) {
        campaignSteps.clickToButton(btnName);
        campaignSteps.waitUntilLoadingSuccess();
    }

    @Given("^select file psd on Artwork Library screen as \"([^\"]*)\"$")
    public void select_file_psd_on_Artwork_Library_screen_As(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String artworkName = SessionData.getDataTbVal(dataTable, row, "Artwork name");
            campaignSteps.selectFilePSD(artworkName);
            campaignSteps.waitUntilLoadingSuccess();
        }
    }

    @Given("^click to button Preview on storefront$")
    public void click_to_button_Preview_on_storefront() {
        campaignSteps.waitUntilLoadingSuccess();
        campaignSteps.clickBtnPreview();
    }

    @Given("^verify show button Preview your design on store front \"([^\"]*)\"$")
    public void verify_show_button_Preview_your_design_on_store_front(String isPreview) {
        boolean isShow = false;
        if (isPreview.equalsIgnoreCase("true"))
            isShow = true;
        productSteps.verifyShowPreviewBtn(isShow);
        if (isShow) {
            productSteps.clickPreviewBtn();
            productSteps.verifyShowPopupPreview();
        }
    }


    @And("^search campaign in dashboard with name \"([^\"]*)\"$")
    public void searchCampaignInDashboardWithName(String _nameCampaign) {
        if (_nameCampaign.matches("@(.*)@"))
            _nameCampaign = campaignName;
        campaignSteps.waitUntilLoadingSuccess();
        campaignSteps.searchCampaign(_nameCampaign);
        campaignSteps.selectCampaign(_nameCampaign);
        campaignSteps.refreshPage();
    }

    @And("^duplicate campaign Phub$")
    public void duplicateCampaignPhub(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String newName = SessionData.getDataTbVal(dataTable, row, "Provide a name for your new campaign");
            campaignSteps.clickDuplicateIcon(campaignName);
            if (!newName.isEmpty()) {
                campaignSteps.enterProvideNameForNewCampaign(newName);
            }
            campaignSteps.confirmDuplicateCampaign();
        }
    }

    @And("^Edit detail campaign$")
    public void editDetailCampaign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String artworks = SessionData.getDataTbVal(dataTable, row, "Artwork");
            String colorRemove = SessionData.getDataTbVal(dataTable, row, "Color remove");
            String sizesAdd = SessionData.getDataTbVal(dataTable, row, "Size add");

            campaignSteps.closeArtworkLibraryPopup();
            if (!artworks.isEmpty()) {
                campaignSteps.selectBaseProduct(product);
                campaignSteps.addArtWorks(artworks);
            }
            if (!colorRemove.isEmpty()) {
                campaignSteps.removeColor();
            }
            if (!sizesAdd.isEmpty()) {
                String[] sizes = sizesAdd.split(",");
                for (String size : sizes) {
                    campaignSteps.addVariantSize(size);
                }
            }
        }
    }

    @Then("^Edit detail campaign on dashboard$")
    public void editDetailCampaignOnDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String tagsRemove = SessionData.getDataTbVal(dataTable, row, "Tags remove");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String collections = SessionData.getDataTbVal(dataTable, row, "Collections");
            boolean isCheckBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is check box"));
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            if (!"".equals(tagsRemove)) {
                campaignSteps.removeTag(tagsRemove);
            }
            if (!"".equals(description)) {
                productDetailSteps.enterDescription(description);
            }
            campaignSteps.uncheckOnlineStore(isCheckBox);
            campaignSteps.uncheckSearchEngine(isCheckBox);
            if (!"".equals(productType)) {
                productDetailSteps.enterProductType(productType);
            }
            if (!"".equals(collections)) {
                productDetailSteps.SearchAndSelectCollection(collections);
            }
            campaignSteps.clickBtnSaveChanges();
            if (!"".equals(variant) && !"".equals(action)) {
                productDetailSteps.selectVariant(variant);
                campaignSteps.clickAction();
                campaignSteps.selectAction(action);
                campaignSteps.confirmDelete();
            }
        }
    }

    @Then("^Verify detail campaign after duplicate$")
    public void verifyDetailCampaignAfterDuplicate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags remove");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String collections = SessionData.getDataTbVal(dataTable, row, "Collections");
            boolean isCheckBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is check box"));
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");

            if (!"".equals(tags)) {
                campaignSteps.verifyTags(tags);
            }
            if (!"".equals(description)) {
                productDetailSteps.verifyDescription(description);
            }
            campaignSteps.verifyUncheckOnlineStore(isCheckBox);
            campaignSteps.verifyUncheckSearchEngine(isCheckBox);
            if (!"".equals(productType)) {
                productDetailSteps.verifyProductType(productType);
            }
            if (!"".equals(collections)) {
                productDetailSteps.verifyCollections(collections);
            }
            if (!"".equals(variant)) {
                campaignSteps.verifyVariants(variant);
            }
        }
    }


    @And("^Verify campaign \"([^\"]*)\" have status \"([^\"]*)\"$")
    public void verifyCampaignHaveStatus(String campaignName, String status) {
        campaignSteps.waitUntilLoadingSuccess();
        campaignSteps.refreshPage();
        campaignSteps.searchCampaign(campaignName);
        campaignSteps.waitUntilLoadingSuccess();
        campaignSteps.verifyStatus(campaignName, status);
        campaignSteps.selectCampaign(campaignName);
    }

    @Then("verify information campaign on Storefront")
    public void verifyInformationCampaignOnStorefront(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String color = SessionData.getDataTbVal(dataTable, row, "Color");

            campaignSteps.searchCampaignOnStorfont(campaignName);
            campaignSteps.selectCampaignOnStorefront(campaignName);
            campaignSteps.verifyCampaignName(campaignName);
            campaignSteps.verifyCampaignPrice(price);

            String[] size_list = size.split(",");
            for (String sizes : size_list) {
                campaignSteps.verifyCampaignSize(sizes);
            }
            if (!"".equals(color)) {
                String[] color_list = color.split(",");
                for (String colors : color_list) {
                    campaignSteps.verifyCampaignColor(colors);
                }
            }
        }
    }

    @When("^add products to campaign$")
    public void addProductsToCampaign(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.addProductToCampaign(sProduct);
            }
        }
        campaignSteps.clickBtnNewCampaign();
    }

    @Then("enable size chart campaign")
    public void enableSizeChartCampaign() {
        campaignSteps.enableSizeChart();
    }

    @Then("disable size chart campaign")
    public void disableSizeChartCampaign() {
        campaignSteps.disableSizeChart();
    }

    @And("verify information variant in product details")
    public void verifyInformationVariantInProductDetails() {

    }

    @And("^verify variant information of product or campaign details in dashboard as \"([^\"]*)\"$")
    public void verifyVariantInformationOfProductOrCampaignDetailsInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sSku = SessionData.getDataTbVal(dataTable, row, "SKU");
            String _sPrice = SessionData.getDataTbVal(dataTable, row, "Price");
            if(!_sSku.isEmpty()){
                campaignSteps.verifySKU(_sSku);
            }

            if (!_sPrice.isEmpty())
                campaignSteps.vefifyVariantPriceCampaignDetailInDashBoard(_sPrice, _sSku);
        }
    }

    @And("download template campaign as \"([^\"]*)\"")
    public void dowloadTemplateCampaign(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            campaignSteps.downloadTemplate(title);
        }
    }

    @And("click to tab \"([^\"]*)\" in Campaign")
    public void clickToTabInCampaign(String tabName) {
        campaignSteps.clickStep(tabName);
        campaignSteps.verifyTabInEditCampaign(tabName);
    }

    @And("add artwork for product as \"([^\"]*)\"")
    public void addArtworkForProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String template = SessionData.getDataTbVal(dataTable, row, "Template");
            campaignSteps.addAnArtWork(template);
        }
    }

    @And("add artwork to campaign as \"([^\"]*)\"")
    public void addArtworkToCampaignAs(String dataKey, List<List<String>> dataTable) {
        String accessToken = commonSteps.getAccessTokenShop();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            campaignSteps.uploadArtwork();
            campaignSteps.waitUntilLoadingSuccess();
            campaignSteps.chooseArtwork(campaignSteps.getTemplate(title, accessToken));
        }
    }

    @Then("verify demension artwork with message \"([^\"]*)\"")
    public void verifyDemensionArtworkWithMessage(String message) {
        campaignSteps.verifyDemension(message);
    }

    @Then("verify noti custom option on SF")
    public void verifyNotiCustomOptionOnSF(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _optionType = SessionData.getDataTbVal(dataTable, row, "Option type");
            String _optionValue = SessionData.getDataTbVal(dataTable, row, "Custom Option");
            String _sMessageNoti = SessionData.getDataTbVal(dataTable, row, "Message noti");
            String _isCropImage = SessionData.getDataTbVal(dataTable, row, "Crop Image");
            String _sStyle = SessionData.getDataTbVal(dataTable, row, "Style");
            boolean isCrop = true;
            if (_isCropImage.equalsIgnoreCase("false"))
                isCrop = false;
            if (!_sStyle.isEmpty()) {
                productSteps.selectStyle(_sStyle);
            }
            switch (_optionType) {
                case "Text":
                    campaignSteps.verifyAndInputOptionText(_optionValue);
                    break;
                case "Image":
                    campaignSteps.verifyAndInputOptionImage(_optionValue, isCrop);
                    break;
                case "Picture choice":
                    campaignSteps.verifyAndInputOptionPictureChoice(_optionValue);
                    break;
            }
            String[] values = _optionValue.split(">");
            if (!_sMessageNoti.isEmpty()) {
                productSteps.verifyNotiCO(values[0], _sMessageNoti);
            }

        }
    }

    @Then("verify display conditional logic on storefront as {string}")
    public void verifyDisplayConditionalLogicOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _customOptionShow = SessionData.getDataTbVal(dataTable, row, "Show custom option");
            String value_custom = SessionData.getDataTbVal(dataTable, row, "Value custom option");
            if (!value_custom.equals("")) {
                campaignSteps.selectValueOnCustomOption(value_custom);
            }
            campaignSteps.verifyCustomOption(_customOptionShow);
        }
    }

    @And("click on Save Change btn")
    public void clickOnSaveChangeBtn() {
        campaignSteps.clickOnSaveChangeBtn();
        campaignSteps.clickOnUpdateBtn();
    }

    @And("create campaign in Dashboard")
    public void createCampaignInDashboard() {
        campaignSteps.clickCreateCampaign();
    }

    @And("search campaign {string} in dashboard")
    public void searchCampaignInDashboard(String _nameCampaign) {
        if (_nameCampaign.matches("@(.*)@"))
            _nameCampaign = campaignName;
        campaignSteps.waitUntilLoadingSuccess();
        campaignSteps.searchCampaign(_nameCampaign);
    }

    @And("open CSV file downloaded")
    public void openCSVFileDownloaded() {
        campaignSteps.openFileDownloaded();
    }

    @And("search campaign inlist as {string}")
    public void searchCampInListAsString(String campaignName) {
        campaignSteps.searchCampaign(campaignName);
    }

    @And("confirm delete campaign")
    public void confirmDeleteCampaign() {
        myCampaignSteps.confirmDelete();
    }

    @And("search campaing {string} on Store front")
    public void searchCampaingOnStoreFront(String _nameCampaign) {
        campaignSteps.searchCampaignOnStorfont(_nameCampaign);
    }

    @And("verify number campaign on Store front as {string}")
    public void verifyNumberCampaignOnStoreFrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String number = SessionData.getDataTbVal(dataTable, row, "Number");
            campaignSteps.verifyNumberCampaignOnSF(_campaignName,number);
        }
    }

    @And("select campaign after search as {string}")
    public void selectCampaignAfterSearchAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            if(!_campaignName.isEmpty()){
                campaignSteps.selectCampaignOnStorefront(_campaignName);
            }
        }
    }
    @And("click Save changes on camp detail")
    public void clickSaveChangeOnCampDetail() {campaignSteps.clickSaveChangesOnCampDetail();}

    @And("verify download template success")
    public void verifyDownLoadTemplateSuccess() {
        campaignSteps.verifyFileAfterDownloaded();
    }

    @Then("^verify style incase multi product from pod on storefront as \"([^\"]*)\"$")
    public void verifyStyleInCaseMultiProduct(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String verifyStyle = SessionData.getDataTbVal(dataTable, row, "Verify Style");
            if (!style.isEmpty()) {
                productSteps.selectStyleIncaseMultiProduct(style,verifyStyle);
            }
        }
    }

    @And("click select mockup with Base as {string}")
    public void clickSelectMockupWithBase(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");
            String sCategory = SessionData.getDataTbVal(dataTable, row, "Category");
            campaignSteps.waitUntilInVisibleLoadingTable();
            if (!sCategory.isEmpty()) {
                campaignSteps.switchToTabOnCatalog(sCategory);
                campaignSteps.clickSelectMockup(sProduct);
            }
        }
    }

    @And("verify image on popup Select mockup as {string}")
    public void verifyImageProductPage(String dataKey, List<List<String>> dataTable) throws IOException {
        int i = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageEx = SessionData.getDataTbVal(dataTable, row, "Image expected");
            String _sPer = SessionData.getDataTbVal(dataTable, row, "Percent Image");
            String _sMockupSelected = SessionData.getDataTbVal(dataTable, row, "Mockup Selected");
            boolean isSelected = true;
            if(_sMockupSelected.equals("false"))
                isSelected = false;
            campaignSteps.verifyImageOnPopUPSelectMockup(_sImageEx, _sPer,isSelected, i);
            i++;
        }
    }

    @Then("^verify camp exist as \"([^\"]*)\"$")
    public void verifyCampExist(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String campaignName = SessionData.getDataTbVal(dataTable, row, "Campaign name");
            String labelName = SessionData.getDataTbVal(dataTable, row, "Label name");
            if(!customArtName.isEmpty())
                campaignName = customArtName;
            if (!campaignSteps.isExistCampaignListNull() && !campaignName.isEmpty()) {
                campaignSteps.switchToTabOnCampaignsPage("All");
                campaignSteps.verifyCampExist(campaignName, labelName);
            }
        }
    }
}