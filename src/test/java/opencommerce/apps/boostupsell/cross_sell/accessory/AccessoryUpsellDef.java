package opencommerce.apps.boostupsell.cross_sell.accessory;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell.AccessoryUpsellSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AccessoryUpsellDef {

    @Steps
    AccessoryUpsellSteps accessoryUpsellSteps;

    @Steps
    CreateOfferSteps createOfferSteps;

    @Steps
    ProductSteps productSteps;

    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Then("^click button create accessories offer \"([^\"]*)\"$")
    public void clickCreateAccessoriesOffer(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isEditOffer = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is edit offer"));

            if(!isEditOffer){
                createOfferSteps.clickBtnCreateOffer();
            }
        }
    }

    @Then("^click button Submit offer on app$")
    public void clickButtonSubmitOfferOnApp() {
        if(accessoryUpsellSteps.isSubmitBtnVisible()){
            createOfferSteps.clickButtonName("Submit offer");
        }else{
            createOfferSteps.clickButtonName("Save");
            accessoryUpsellSteps.switchToAcessoryList();
        }
    }


    @And("add to cart accessory {string}")
    public void addToCartAccessory(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productTarget = SessionData.getDataTbVal(dataTable, row, "Product target");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String accessoryProd = SessionData.getDataTbVal(dataTable, row, "Accessories products");
            boolean isValid = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is Valid"));

            reviewOnSFSteps.waitForLoadApps("Upsell");

            if(!customOption.isEmpty()){
                if(isValid){
                    accessoryUpsellSteps.clickBtnAdd(accessoryProd);
                    accessoryUpsellSteps.chooseProductVariant(variant);
                    accessoryUpsellSteps.inputCustomOption(customOption);
                    accessoryUpsellSteps.clickBtnAddToCartOnPopup();
                    accessoryUpsellSteps.verifyDisableBtnAdd(productTarget);
                }else {
                    accessoryUpsellSteps.clickOnViewMoreProd();
                    accessoryUpsellSteps.clickBtnAdd(accessoryProd);
                    accessoryUpsellSteps.inputCustomOption(customOption);
                    accessoryUpsellSteps.clickBtnAddToCartOnPopup();
                    accessoryUpsellSteps.verifyDisableBtnAdd(productTarget);
                }
            }else {
                accessoryUpsellSteps.clickBtnAdd(accessoryProd);
                accessoryUpsellSteps.verifyDisableBtnAdd(productTarget);
            }
        }
    }

    @And("verify on page cart {string}")
    public void verifyOnPageCart(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productTarget = SessionData.getDataTbVal(dataTable, row, "Product target");
            String variant = SessionData.getDataTbVal(dataTable, row, "Variant");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            accessoryUpsellSteps.verifyProductTarget(productTarget);
            accessoryUpsellSteps.verifyProductVariant(variant);
            accessoryUpsellSteps.verifyProductPrice(price);
            accessoryUpsellSteps.verifyProductCustomOption(customOption);
        }
    }
}