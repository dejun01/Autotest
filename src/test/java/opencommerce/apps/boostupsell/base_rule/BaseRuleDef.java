package opencommerce.apps.boostupsell.base_rule;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.BaseRuleSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class BaseRuleDef {
    @Steps
    BaseRuleSteps baseRuleStep;
    @Steps
    CreateOfferSteps createOfferSteps;
    @Steps
    ProductSteps productSteps;

    @Then("^input data to create rule base offer \"([^\"]*)\"$")
    public void inputDataToCreateRuleBase(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String offerTitle = SessionData.getDataTbVal(dataTable, row, "Offer's title");
            String productTargetMustMatch = SessionData.getDataTbVal(dataTable, row, "Product target must match");
            String targetRules = SessionData.getDataTbVal(dataTable, row, "Target rules");
            String productRecommendMustMatch = SessionData.getDataTbVal(dataTable, row, "Product recommend must match");
            String recommendRules = SessionData.getDataTbVal(dataTable, row, "Recommend rules");
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String discountQuantity = SessionData.getDataTbVal(dataTable, row, "Discount quantity");

            switch (offerType){
                case "Quantity discount":
                    createOfferSteps.setUpDiscountQuantityOffer(discountQuantity);
                    break;
                case "Accessory":
                    baseRuleStep.setUpRuleBase("Choose Accessories products", productRecommendMustMatch, recommendRules);
                    break;
                default:
                    createOfferSteps.chooseUpsellOfferType(offerType);
                    baseRuleStep.setUpRuleBase("Choose Recommended products", productRecommendMustMatch, recommendRules);
                    createOfferSteps.setDiscountOffer(isDiscount, discountValue);
            }

            if(!offerName.isEmpty()){
                createOfferSteps.enterOfferName(offerName);
            }
            if(!offerMessage.isEmpty()){
                createOfferSteps.enterOfferMessage(offerMessage);
            }
            if(!offerTitle.isEmpty()){
                createOfferSteps.enterOfferTitle(offerTitle);
            }
            baseRuleStep.setUpRuleBase("Choose Target products", productTargetMustMatch, targetRules);
        }
    }

    @When("^verify accessory work on sf \"([^\"]*)\"$")
    public void verifyAccessoryWorkOnSF(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            String recommendProducts = SessionData.getDataTbVal(dataTable, row, "Recommend Products");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));

            productSteps.searchAndSelectProduct(targetProduct);
            baseRuleStep.verifyShowAccessory(isShow);
            if(isShow){
                baseRuleStep.verifyOfferAcessoryMessage(offerMessage);
                baseRuleStep.verifyItemInAccessory(recommendProducts);
            }
        }
    }
}
