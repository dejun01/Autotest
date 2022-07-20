package opencommerce.apps.boostupsell.upsell;

import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.upsell.PrePurchaseOfferSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import opencommerce.common.CommonDef;


import java.util.List;

public class PrePurchaseDef{

    @Steps
    CartSteps cartSteps;
    @Steps
    PrePurchaseOfferSteps prepurchaseSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Then("^verify offer pre-purchase work on site \"([^\"]*)\"$")
    public void verifyOfferPrePurchaseWorkOnSite(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sTargetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            String sMessage = SessionData.getDataTbVal(dataTable, row, "Message Offer");
            String numberProduct = SessionData.getDataTbVal(dataTable, row, "Number product");
            String sUpsellProduct = SessionData.getDataTbVal(dataTable, row, "Recommend product");
            String isShow = SessionData.getDataTbVal(dataTable, row, "isShow");
            boolean show = !isShow.equalsIgnoreCase("false");

            productSteps.searchAndSelectProduct(sTargetProduct);
            reviewOnSFSteps.waitForLoadApps("Upsell");
            prepurchaseSteps.clickAddToCart();

            prepurchaseSteps.verifyPopupPrePurchaseShown(show);
            if (show) {
                prepurchaseSteps.verifyOfferMessageShown(sMessage, true);
                prepurchaseSteps.waitUntilBtnAddToCartShown();
                List<String> listProd = prepurchaseSteps.getListProduct();
                prepurchaseSteps.logInfor(listProd);
                prepurchaseSteps.verifyNumberOfProductUpsell(numberProduct);
                prepurchaseSteps.verifyProductOffers(sUpsellProduct);
            }
        }
    }

    @Then("^add to cart product on pre-purchase popup \"([^\"]*)\"$")
    public void addToCartProductOnPrePurchasePopup(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String custom = SessionData.getDataTbVal(dataTable, row, "Custom option");
            String sProductAdded = SessionData.getDataTbVal(dataTable, row, "Products");
            String sVariant = SessionData.getDataTbVal(dataTable, row, "Variant");
            boolean isClickCheckoutButtonOnPopup = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isClickCheckoutButton"));
            prepurchaseSteps.inputCustomOption(custom);
            prepurchaseSteps.selectVariant(sProductAdded, sVariant);
            prepurchaseSteps.addProductOnUpsellPopup(sProductAdded);
            if (isClickCheckoutButtonOnPopup) {
                prepurchaseSteps.clickCheckOutInUpsellPopup();
            } else {
                prepurchaseSteps.closePopupUpsell();
                cartSteps.openCartPage();
                cartSteps.verifyCartPage();
            }
        }
    }

    @And("^verify smart offer show \"([^\"]*)\"$")
    public void verifySmartOfferShow(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String numberProduct = SessionData.getDataTbVal(dataTable, row, "Number product");
            boolean isClickCheckoutButtonOnPopup = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isClickCheckoutButton"));
            if (isShow) {
                prepurchaseSteps.verifyPopupPrePurchaseShown(true);
                prepurchaseSteps.verifyNumberOfProductUpsell(numberProduct);
            }
            if (isClickCheckoutButtonOnPopup) {
                prepurchaseSteps.clickCheckOutInUpsellPopup();
            }

        }

    }


    @Then("^verify smart offer work as \"([^\"]*)\"$")
    public void verifySmartOfferWorkAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            String[] product = targetProduct.split(",");
            for (String prod : product) {
                productSteps.searchAndSelectProduct(prod);
                reviewOnSFSteps.waitForLoadApps("Upsell");
                productSteps.clickAddToCart();
                prepurchaseSteps.verifyPopupPrePurchaseShown(isShow);
                if (isShow) {
                    prepurchaseSteps.verifyNumberOfProductUpsell("4");
                    prepurchaseSteps.verifyDiscountValueSmartOffer(discount);
                    if (isAddToCart) {
                        prepurchaseSteps.addToCartProduct1st();
                        prepurchaseSteps.verifyNumberOfProductUpsell("4");
                        prepurchaseSteps.closePopupUpsell();
                        productSteps.goToCart();
                    }

                }
            }
        }
    }

    @Then("^add to cart product on many pre-purchase popup$")
    public void offerAfterAddProductInMorePrePurchase(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target products");
            String recommendProduct = SessionData.getDataTbVal(dataTable, row, "Recommend product");
            String shop = LoadObject.getProperty("shop");
            prepurchaseSteps.navigateByUrl("https://"+ shop + targetProduct);
            reviewOnSFSteps.waitForLoadApps("Upsell");
            prepurchaseSteps.clickAddToCart();
            prepurchaseSteps.addProductOnUpsellPopup(recommendProduct);
            prepurchaseSteps.clickCheckOutInUpsellPopup();
        }
    }
}
