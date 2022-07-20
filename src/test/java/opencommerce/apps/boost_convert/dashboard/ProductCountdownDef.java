package opencommerce.apps.boost_convert.dashboard;

import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.ProductCountdownSteps;
import com.opencommerce.shopbase.dashboard.apps.boostconvert.steps.TimerCountdownSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boost_convert.BoostConvertShopSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ProductCountdownDef {
    @Steps
    ProductCountdownSteps productCountdownSteps;
    @Steps
    TimerCountdownSteps timerCountdownSteps;
    @Steps
    BoostConvertShopSteps cOptShopifySteps;


    @Given("^change product countdown settings of BoostCovert as \"([^\"]*)\"$")
    public void change_product_countdown_settings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settingItemsLeftInStock = SessionData.getDataTbVal(dataTable, row, "Setting items left in stock");
            String quantityOfItems = SessionData.getDataTbVal(dataTable, row, "Quantity of items");
            String sTrigger = SessionData.getDataTbVal(dataTable, row, "Trigger");
            String sPageTrigger = SessionData.getDataTbVal(dataTable, row, "Trigger pages");
            System.out.println(settingItemsLeftInStock);
            productCountdownSteps.setItemsLeftInStock(settingItemsLeftInStock, quantityOfItems);
            timerCountdownSteps.selectShowAtPage(sTrigger, sPageTrigger);
            productCountdownSteps.clickButtonSave();
        }
    }


    @When("^turn on product countdown of BoostCovert is \"([^\"]*)\"$")
    public void turn_product_countdown_of_BoostCovert(String isTurnOn) {
        productCountdownSteps.turnOnProductCountdown(isTurnOn);
        productCountdownSteps.clickButtonSave();
    }



}
