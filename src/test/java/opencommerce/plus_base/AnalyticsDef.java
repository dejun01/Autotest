package opencommerce.plus_base;
import com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.billing.PaymentMethodsSteps;
import com.opencommerce.shopbase.dashboard.orders.steps.OrderSteps;
import com.opencommerce.shopbase.plusbase.steps.AnalyticsSteps;
import com.opencommerce.shopbase.plusbase.steps.OrderPlusBaseStep;
import static com.opencommerce.shopbase.OrderVariable.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;


public class AnalyticsDef {
    @Steps
    AnalyticsSteps analyticsSteps  ;
    @Steps
    OrderPlusBaseStep orderPlusBaseSteps;

    public float profitBefore;
    public float profitOrder;
    public float subProfitBefore;
    @Given("Verify UI Overview dashboard page")
    public void verify_UI_Overview_dashboard_page() {
        analyticsSteps.verifyItemDisplayProfit("Total profits");
        analyticsSteps.verifyItemDisplayProfit("Online store conversion rate");
        analyticsSteps.verifyItemDisplayProfit("Total orders");
        analyticsSteps.verifyItemDisplayProfit("Average order profit");
        analyticsSteps.verifyItemDisplayProfit("Average order items");
        analyticsSteps.verifyItemDisplayProfit("Abandoned checkouts recovery");
        analyticsSteps.verifyItemDisplayProfit("Traffic sources");
        analyticsSteps.verifyItemDisplayProfit("Current store");
    }


    @And("get Profit of your order")
    public void getProfitYourOrder() {
        orderPlusBaseSteps.searchOrder(orderNumber);
        orderPlusBaseSteps.clickOrder(orderNumber);
        profitOrder= analyticsSteps.getProfitOrder();
    }
    @And("Verify Total profit after created order")
    public void verify_Total_Profit_after_created_order() {
        analyticsSteps.verifyTotalProfit(profitBefore,profitOrder);

    }

    @And("Verify Total profit with order boost upsell")
    public void verifyTotalProfitWithOrderBoostUpsell() {
        analyticsSteps.verifyTotalProfitWithOrderBoostUpsell(profitBefore,profitOrder,subProfitBefore);
    }
    @And("Verify AOP")
    public void verify_AOP() {
       analyticsSteps.verifyAOPOf("",0);
    }


    @Then("Verify AOP of {string}")
    public void verifyAOPOf(String label) {
        analyticsSteps.verifyAOPOf(label,0);
    }


    @And("get the {string} {string}")
    public void getThe(String item, String label) {
        subProfitBefore = analyticsSteps.getSubItemPrice(item,label);
    }


    @And("Verify AOP of the order Boost Upsell")
    public void verifyAOPOfTheOrderBoostUpsell() {
        analyticsSteps.verifyAOPOf("",profitBefore);
    }

    @And("get profit of {string}")
    public void getProfitOf(String item) {
        profitBefore = analyticsSteps.getItemPrice(item);
    }
}
