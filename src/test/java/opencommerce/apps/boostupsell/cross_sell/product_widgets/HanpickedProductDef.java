package opencommerce.apps.boostupsell.cross_sell.product_widgets;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell.HanpickedProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;


public class HanpickedProductDef {
    @Steps
    HanpickedProductSteps hanpickedProductSteps;


    @Then("^create a new handpicked product as \"([^\"]*)\"$")
    public void createANewHandpickedProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer name");
            String targetProducts = SessionData.getDataTbVal(dataTable, row, "Target products");
            String recommendProducts = SessionData.getDataTbVal(dataTable, row, "Recommend products");
            hanpickedProductSteps.clickBtnCreateNew();
            hanpickedProductSteps.enterOfferName(offerName);
            hanpickedProductSteps.selectTagetProduct(targetProducts);
            if (!recommendProducts.trim().isEmpty()) {
                hanpickedProductSteps.clickSelectProductRecommend();
                hanpickedProductSteps.selectProduct(recommendProducts);
            }
            hanpickedProductSteps.submitOffer();
        }
    }
}