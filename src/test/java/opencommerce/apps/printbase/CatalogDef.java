package opencommerce.apps.printbase;

import com.opencommerce.shopbase.dashboard.apps.printhub.steps.orders.campaign.CampaignSteps;
import io.cucumber.java.en.And;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.CatalogSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CatalogDef {
    @Steps
    CatalogSteps catalogSteps;
    @Steps
    CampaignSteps campaignSteps;
    static Float productPriceTarget = 0.00f;


    @Given("^verify catalog screen")
    public void verify_catalog_screen_of_Phub_as(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String showPriceWithShipping = SessionData.getDataTbVal(dataTable, row, "Show price with shipping");
            String shippingAddress = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            Float shippingCost = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping cost"));
            String sProduct = SessionData.getDataTbVal(dataTable, row, "Product");

            boolean isShowPriceWithShipping = false;
            if (!showPriceWithShipping.isEmpty()) {
                isShowPriceWithShipping = Boolean.parseBoolean(showPriceWithShipping);
            }
            float productPrice = catalogSteps.getBaseProductPrice(sProduct);

            catalogSteps.selectShowPriceWithShipping(isShowPriceWithShipping);
            if (isShowPriceWithShipping)
                catalogSteps.selectShippingAddress(shippingAddress);

            float productPriceWithShipping = catalogSteps.getBaseProductPrice(sProduct);
            catalogSteps.verifyPriceProductWithShipping(productPrice + shippingCost, productPriceWithShipping);
            if (isShowPriceWithShipping) {
                catalogSteps.verifyShippingNote();
            }
            catalogSteps.verifyProcessingTime();


        }
    }
    @And("^switch to \"([^\"]*)\" tab$")
    public void switchToTab(String tabName) {
        catalogSteps.clickTabName(tabName);

    }

    @And("^verify the productbase in catalog as \"([^\"]*)\"$")
    public void verifyTheProductbaseInCatalog(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String catalog = SessionData.getDataTbVal(dataTable, row, "Catalog");
            String productBase = SessionData.getDataTbVal(dataTable, row, "Product base");
            String s_targetBaseCost = SessionData.getDataTbVal(dataTable, row, "Target BaseCost");
            String showPriceWithShipping = SessionData.getDataTbVal(dataTable, row, "Price shipping");
            String shippingAddress = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            Float shippingCost = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping cost"));
            String processingTime = SessionData.getDataTbVal(dataTable, row, "Processing Time");
            if (!s_targetBaseCost.isEmpty())
                productPriceTarget = Float.parseFloat(s_targetBaseCost);
            boolean isShowPriceWithShipping = false;
            if (!showPriceWithShipping.isEmpty()) {
                isShowPriceWithShipping = Boolean.parseBoolean(showPriceWithShipping);
            }
            campaignSteps.switchToTabOnCatalog(catalog);
            catalogSteps.verifyProductInCatalog(productBase);
            catalogSteps.selectShowPriceWithShipping(isShowPriceWithShipping);
            float productPrice;
            if (isShowPriceWithShipping) {
                catalogSteps.selectShippingAddress(shippingAddress);
                productPrice = catalogSteps.getForProductBase(productBase);
                assertThat(productPrice).isEqualTo(productPriceTarget + shippingCost);

                catalogSteps.verifyShippingNoteForProduct(productBase);
            } else {
                productPrice = catalogSteps.getForProductBase(productBase);
                assertThat(productPrice).isEqualTo(productPriceTarget);

            }
            if (!processingTime.isEmpty())
                catalogSteps.verifyProcessingTimeForProduct(productBase, processingTime);

        }
    }

    @And("verify shipping fee in catalog screen")
    public void verifyShippingFeeInCatalogScreen(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String showPriceWithShipping = SessionData.getDataTbVal(dataTable, row, "Show price with shipping");
            String shippingAddress = SessionData.getDataTbVal(dataTable, row, "Shipping address");
            Float shippingFee = Float.parseFloat(SessionData.getDataTbVal(dataTable, row, "Shipping fee"));

            boolean isShowPriceWithShipping = false;
            if (!showPriceWithShipping.isEmpty()) {
                isShowPriceWithShipping = Boolean.parseBoolean(showPriceWithShipping);
            }
            catalogSteps.selectShowPriceWithShipping(isShowPriceWithShipping);
            if (isShowPriceWithShipping)
                catalogSteps.selectShippingAddress(shippingAddress);

            float productPriceWithShipping = catalogSteps.getProductPrice();
            catalogSteps.verifyPriceProductWithShipping(shippingFee, productPriceWithShipping);
            if (isShowPriceWithShipping) {
                catalogSteps.verifyShippingNote();
            }
            catalogSteps.verifyProcessingTime();


        }
    }
}