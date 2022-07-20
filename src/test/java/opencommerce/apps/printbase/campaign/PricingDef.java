package opencommerce.apps.printbase.campaign;

import com.opencommerce.shopbase.dashboard.apps.printbase.steps.PrintbaseAPI;
import io.restassured.response.Response;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.PricingSteps;
import io.restassured.path.json.JsonPath;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.When;
import jnr.ffi.Struct;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PricingDef {
    @Steps
    PricingSteps pricingSteps;

    @Steps
    MyCampaignSteps myCampaignSteps;
    @Steps
    PrintbaseAPI printbaseAPI;
    String accToken = "";

    @When("^verify to calculate pricing by API as \"([^\"]*)\"$")
    public void verify_to_caculate_pricing_by_API_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            String variants = SessionData.getDataTbVal(dataTable, row, "Variants");
            String numberSide = SessionData.getDataTbVal(dataTable, row, "Number of second side");
            if (numberSide.equals("null")) {
                numberSide = "0";
            }
            int numberOfSecondSide = Integer.parseInt(numberSide);

            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Integer idProductBase = printbaseAPI.getIDProductBase(baseProduct, accessToken);

            JsonPath productbase = myCampaignSteps.getBaseProductInformation(accessToken, idProductBase);
            Float productBaseCost = (Float) productbase.get("products.target_base_cost[0]");
            int profit = productbase.get("products.target_profit[0]");

            if (variants.isEmpty()) {
                variants = productbase.get("products.variants[0].option1[0]") + "," + productbase.get("products.variants[0].option2[0]") + ";" + productbase.get("products.variants[0].option1[1]") + "," + productbase.get("products.variants[0].option2[1]");
            }
// shopbase calculate cost
            JsonPath basePrice = pricingSteps.getBasePrice(accessToken, variants, numberOfSecondSide, idProductBase);
            Float variant_base_price =Float.parseFloat( basePrice.get("products.base_price[0]").toString());
            Float suggested_variant_sales_price = Float.parseFloat(basePrice.get("products.price[0]").toString());

// get data from supplier to calculate cost
            if (!variants.isEmpty()) {
                Float variantBasePriceExp = pricingSteps.calculateVariantBaseCost(accessToken,variants, numberOfSecondSide, productBaseCost,idProductBase);
                assertThat(variant_base_price).isEqualTo(variantBasePriceExp);
            }

            assertThat(productBaseCost).isGreaterThan(0);
            assertThat(variant_base_price).isGreaterThan(0);
            assertThat(suggested_variant_sales_price).isEqualTo(pricingSteps.roundPrice(variant_base_price + profit));

        }
    }

    @When("^calculate pricing of variant \"([^\"]*)\"$")
    public void calculate_pricing_of_variant(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {

            String baseProduct = SessionData.getDataTbVal(dataTable, row, "Base product");
            String colors = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            int numberOfSecondSide = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Number of second side"));
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            Integer idProductBase = printbaseAPI.getIDProductBase(baseProduct, accessToken);
            JsonPath productbase = myCampaignSteps.getBaseProductInformation(accToken, idProductBase);
            String handel = productbase.get("product.handle");
            float productBaseCost = (Float) productbase.get("product.target_base_cost");


            Response listPricing = pricingSteps.getListPricingFromHive(idProductBase,accessToken);

            System.out.println("Product :" + handel);
            String listColor[] = colors.split(",");
            String listSize[] = size.split(",");

            float sTCProductBaseVariant = pricingSteps.calculateSTCVariant(listPricing, "White,M", numberOfSecondSide);

            for (String cl : listColor) {
                for (String sz : listSize) {
                    String variant = cl.trim().toLowerCase() + "," + sz.trim().toLowerCase();
                    float variantprice = pricingSteps.calculateVariantCost(listPricing, variant, numberOfSecondSide, productBaseCost, sTCProductBaseVariant);
                    System.out.println("|" + cl + "|" + sz + "| " + pricingSteps.roundPrice(variantprice) + "|");
                }
            }

        }
    }
}
