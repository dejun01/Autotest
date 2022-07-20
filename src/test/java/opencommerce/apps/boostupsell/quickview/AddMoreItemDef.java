package opencommerce.apps.boostupsell.quickview;

import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.QuickViewSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.AddMoreItemSteps;


import java.util.List;

public class AddMoreItemDef {

    @Steps
    CartSteps cartSteps;
    @Steps
    AddMoreItemSteps addMoreItemSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Then("^verify button Add more item on \"([^\"]*)\"$")
    public void verify_button_Add_more_item_on(String products) {
        cartSteps.verifyCartPage();
        reviewOnSFSteps.waitForLoadApps("Upsell");
        addMoreItemSteps.verifyButtonAddMoreItem(products);
    }

    @Then("^verify Add more item work with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void verify_Add_more_item_work_with_as(String product, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            boolean isRequired = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isRequired"));
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            addMoreItemSteps.clickBtnAddMore(product);
            addMoreItemSteps.verifyAddMoreItemPopupShown(product, price);
            if (isAddToCart) {
                addMoreItemSteps.chooseVariant(variant);
                if (isRequired && !customOption.isEmpty()) {
                    addMoreItemSteps.clickAddToCart();
                    addMoreItemSteps.verifyHighlight(customOption);
                    addMoreItemSteps.inputFieldCustome(customOption);
                }
                addMoreItemSteps.clickAddToCart();
                addMoreItemSteps.waitForPopupAddMoreClose();
                cartSteps.verifyProductOnCart(product, variant, customOption, price, "1");
            } else
                addMoreItemSteps.clickClosePopup();
        }
    }

}
