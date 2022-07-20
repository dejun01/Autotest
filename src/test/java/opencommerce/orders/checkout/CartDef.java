package opencommerce.orders.checkout;

import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class CartDef {
    @Steps
    CartSteps cartSteps;

    @When("^open cart page$")
    public void open_cart_page() {
        cartSteps.openCartPage();
    }

    @Then("^update product on cart page \"([^\"]*)\"$")
    public void updateProductOnCartPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String sutotal = SessionData.getDataTbVal(dataTable, row, "Sutotal");
            String messageDiscount = SessionData.getDataTbVal(dataTable, row, "Message discount ");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            if (!quantity.isEmpty()) {
                cartSteps.setQuantityProductOnCart(product, variant, quantity);
                cartSteps.verifySubToltalOnCart(sutotal);
                cartSteps.verifyMessageDiscountOnCartWithApp(messageDiscount);
                cartSteps.verifyDiscountCode(discount);
            }
        }
    }
}
