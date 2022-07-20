package opencommerce.apps.boostupsell.cross_sell.quantity_discount;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.shop.OrderSummarySteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell.QuantityDiscountOfferSteps;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;


import java.util.List;

public class QuantityDiscountDef {
    @Steps
    CreateOfferSteps createOfferSteps;
    @Steps
    OrderSummarySteps orderSummarySteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    QuantityDiscountOfferSteps quantityDiscountOfferSteps;
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;


    @And("^input data to create offer quantity discount \"([^\"]*)\"$")
    public void inputDataToCreateOfferQuantityDiscount(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String targetType = SessionData.getDataTbVal(dataTable, row, "Target type");
            String targetProducts = SessionData.getDataTbVal(dataTable, row, "Target products");
            String discountQuantity = SessionData.getDataTbVal(dataTable, row, "Discount quantity");
            createOfferSteps.enterOfferName(offerName);
            createOfferSteps.enterOfferMessage(offerMessage);
            if(!targetProducts.isEmpty()){
                createOfferSteps.chooseProductWithBlock("Choose Target products", targetType, targetProducts);
            }
            createOfferSteps.setUpDiscountQuantityOffer(discountQuantity);
        }
    }

    @And("^verify offer quantity discount show as \"([^\"]*)\"$")
    public void verifyOfferQuantityDiscountShowAs(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean isShowOffer = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowOffer"));
            String messageOffer = SessionData.getDataTbVal(dataTable, row, "Message Offer");
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");

            productSteps.searchAndSelectProduct(product);
            reviewOnSFSteps.waitForLoadApps("Upsell");
            quantityDiscountOfferSteps.verifyWidgetQuantityDiscountShow(isShowOffer);

            if (isShowOffer) {
                quantityDiscountOfferSteps.verifyMessageOffer(messageOffer);
                quantityDiscountOfferSteps.verifyItemsQuantityDiscount(discount);
            }
        }
    }

    @Then("^add to cart quantity discount \"([^\"]*)\"$")
    public void addToCartQuantityDiscount(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            String quantity = SessionData.getDataTbVal(dataTable, row, "Quantity");
            String customOption = SessionData.getDataTbVal(dataTable, row, "Custom option");
            productSteps.inputCustomOption(customOption);
            if (isAddToCart)
                quantityDiscountOfferSteps.addToCartOnWidget(quantity);
            else {
                productSteps.addToCartWithQuantity(quantity);
            }
        }
    }

    @Then("^verify detail on cart page \"([^\"]*)\"$")
    public void verifyDetailOnCartPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            String messageDiscountOnCart = SessionData.getDataTbVal(dataTable, row, "Message discount");
            String discountApply = SessionData.getDataTbVal(dataTable, row, "Discount code");
            String subtotal = SessionData.getDataTbVal(dataTable, row, "Subtotal");

            cartSteps.verifyCartPage();
            quantityDiscountOfferSteps.verifyMessageDiscountOnCartPage(messageDiscountOnCart);
            cartSteps.verifyDiscountCart(discountApply);
            cartSteps.verifySubTotalCart(subtotal);


        }

    }

    @And("setting button Add to cart shown is {string}")
    public void settingButtonAddToCartShownIs(String status) {
        boolean stsBtn=Boolean.parseBoolean(status);
        quantityDiscountOfferSteps.settingBtnAddQTT(stsBtn);
    }

    @Then("verify button Add to cart showwn is {string}")
    public void verifyButtonAddToCartShowwnIs(String status) {
        boolean stsBtn=Boolean.parseBoolean(status);
        quantityDiscountOfferSteps.verifyBtnAddShow(stsBtn);
    }

    @Then("^click button to create quantity discount in product admin$")
    public void createQuantityDiscount() {
        quantityDiscountOfferSteps.clickButtonToCreateQuantityDiscount();
    }

    @Then("^select quantity discount \"([^\"]*)\"$")
    public void selectOfferOnModal(String offerName) {
        quantityDiscountOfferSteps.selectOfferOnModal(offerName);
    }

    @Then("^delete offer on modal$")
    public void deleteOfferOnModal(){
        reviewInProductAdminSteps.clickButtonOnPopup("Delete offer");
        reviewInProductAdminSteps.clickButtonOnPopup("Delete offer");
    }

    @Then("^active or inactive offer \"([^\"]*)\"$")
    public void changeStatusOfferInProduct(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offer = SessionData.getDataTbVal(dataTable, row, "Offer");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            quantityDiscountOfferSteps.changeStatusOfferInProduct(offer, status);
        }
    }

}

