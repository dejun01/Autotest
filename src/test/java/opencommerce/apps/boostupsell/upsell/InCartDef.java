package opencommerce.apps.boostupsell.upsell;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.InCartOfferSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.QuickViewSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class InCartDef {
    @Steps
    InCartOfferSteps inCartOfferSteps;
    @Steps
    CreateOfferSteps createOfferSteps;
    @Steps
    QuickViewSteps quickViewSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Then("^verify in-cart offer work on SF \"([^\"]*)\"$")
    public void verifyOfferInCartWorkOnSite(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            String targetVariant = SessionData.getDataTbVal(dataTable, row, "Target variant");
            String recommendVariant = SessionData.getDataTbVal(dataTable, row, "Recommend variant");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            String recommendProduct = SessionData.getDataTbVal(dataTable, row, "Recommend product");
            String recommendType = SessionData.getDataTbVal(dataTable, row, "Recommend type");

            boolean isShow = !recommendProduct.isEmpty();
            String recommendTitle;
            if(recommendType.equalsIgnoreCase("Specific products")){
                recommendTitle = recommendProduct;
            }else {
                recommendTitle = recommendVariant;
            }

            reviewOnSFSteps.waitForLoadApps("Upsell");
            inCartOfferSteps.checkShowRecommendVariant(targetProduct, targetVariant, isShow);
            if(isShow){
                inCartOfferSteps.verifyTitleOfRecommendVariant(targetProduct, targetVariant, recommendTitle);
                inCartOfferSteps.verifyPriceOfRecommendVariant(targetProduct, targetVariant, price);

                if(recommendVariant.isEmpty()){
                    if(isAddToCart){
                        inCartOfferSteps.clickBtnAddOnProductIncart(targetProduct, targetVariant);
                        quickViewSteps.verifyQuickviewShown(false);
                    }
                }else {
                    inCartOfferSteps.clickBtnAddOnProductIncart(targetProduct, targetVariant);
                    inCartOfferSteps.verifyProductName(recommendProduct);
                    inCartOfferSteps.verifySelectedRecommendVariant(recommendVariant);
                    if (isAddToCart) {
                        quickViewSteps.clickAddToCart();
                    } else {
                        quickViewSteps.closePopupQuickview();
                    }
                }

            }
        }
    }

    @When("^open offer \"([^\"]*)\"$")
    public void openOffer(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            inCartOfferSteps.openOffer(offerName);
        }
    }

    @When("^verify offer's data after convert \"([^\"]*)\"$")
    public void verifyOfferDataAfterConvert(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String targetType = SessionData.getDataTbVal(dataTable, row, "Target type");
            String targetValues = SessionData.getDataTbVal(dataTable, row, "Target values");
            String recommendType = SessionData.getDataTbVal(dataTable, row, "Recommend type");
            String recommendValues = SessionData.getDataTbVal(dataTable, row, "Recommend values");

            inCartOfferSteps.verifyOfferType(offerType);
            inCartOfferSteps.verifyOfferName(offerName);
            inCartOfferSteps.verifyValueSelected("Choose Target products", targetType, targetValues);
            inCartOfferSteps.verifyValueSelected("Choose Recommended products", recommendType, recommendValues);
        }
    }

    @When("^verify message after save offer \"([^\"]*)\"$")
    public void verifyMessageAfterClickSave(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            inCartOfferSteps.verifyNotiMessage(message);
        }
    }

    @Given("^create offer and click button \"([^\"]*)\" \"([^\"]*)\"$")
    public void createOffer(String button, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String offerTitle = SessionData.getDataTbVal(dataTable, row, "Offer's title");
            String targetType = SessionData.getDataTbVal(dataTable, row, "Target type");
            String targetProducts = SessionData.getDataTbVal(dataTable, row, "Target products");
            String bundleProducts = SessionData.getDataTbVal(dataTable, row, "Bundle products");
            String isShowWithTarget = SessionData.getDataTbVal(dataTable, row, "isShowWithTarget");
            String recommendType = SessionData.getDataTbVal(dataTable, row, "Recommend type");
            String recommendProducts = SessionData.getDataTbVal(dataTable, row, "Recommend products");
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String recommendVariantWith = SessionData.getDataTbVal(dataTable, row, "Recommend variant with");

            createOfferSteps.clickBtnCreateOffer();
            createOfferSteps.chooseUpsellOfferType(offerType);
            createOfferSteps.enterOfferName(offerName);
            createOfferSteps.enterOfferMessage(offerMessage);
            createOfferSteps.enterOfferTitle(offerTitle);

            createOfferSteps.chooseProductWithBlock("Choose Target products", targetType, targetProducts);
            createOfferSteps.chooseProductWithBlock("Choose Bundle products", "", bundleProducts);
            createOfferSteps.isShowBundleTarget(isShowWithTarget);

            if (recommendType.equalsIgnoreCase("Specific by base category")) {
                createOfferSteps.chooseCategory("Choose Recommended products", recommendType, recommendProducts);
            } else {
                createOfferSteps.chooseProductWithBlock("Choose Recommended products", recommendType, recommendProducts);
            }

            if (offerType.equalsIgnoreCase("In-cart")) {
                createOfferSteps.chooseRecommendVariantWith(recommendVariantWith);
            } else {
                createOfferSteps.setDiscountOffer(isDiscount, discountValue);
            }
            createOfferSteps.clickButtonName(button);
        }
    }

    @Given("^input data to edit offer \"([^\"]*)\"$")
    public void selectOfferType(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");

            if (!offerType.isEmpty()) {
                createOfferSteps.chooseUpsellOfferType(offerType);
            }
        }
    }

    @And("^check show popup when click area in In-cart SF \"([^\"]*)\"$")
    public void checkShowPopupWhenClickAreaInInCart(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Target product");
            String targetVariant = SessionData.getDataTbVal(dataTable, row, "Target variant");
            String recommendProduct = SessionData.getDataTbVal(dataTable, row, "Recommend product");
            String recommendVariant = SessionData.getDataTbVal(dataTable, row, "Recommend variant");
            boolean hasCustomOption = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "hasCustomOption"));
            boolean isClickOnProductImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isClickOnProductImage"));
            boolean isClickOnProductTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isClickOnProductTitle"));
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));

            reviewOnSFSteps.waitForLoadApps("Upsell");
            inCartOfferSteps.verifyIncartShown();
            //Verify quick view show when click Img
            if (isClickOnProductImage) {
                inCartOfferSteps.clickOnRecommendProductImage(targetProduct, targetVariant);
                quickViewSteps.verifyQuickviewShown(true);
                quickViewSteps.closePopupQuickview();
            }
            // verify quick view show when click Product title
            if (isClickOnProductTitle) {
                inCartOfferSteps.clickOnRecommendProductTitle(targetProduct, targetVariant);
                quickViewSteps.verifyQuickviewShown(true);
                quickViewSteps.closePopupQuickview();
            }
            //verify popup quick view show when click add to cart
            if (isAddToCart) {
                if (hasCustomOption || !recommendVariant.isEmpty()) {
                    inCartOfferSteps.clickBtnAddOnProductIncart(targetProduct, targetVariant);
                    quickViewSteps.verifyQuickviewShown(true);
                    quickViewSteps.clickAddToCart();
                } else {
                    inCartOfferSteps.clickBtnAddOnProductIncart(targetProduct, targetVariant);
                    quickViewSteps.verifyQuickviewShown(false);
                }
                inCartOfferSteps.verifyAddToCart(recommendProduct, recommendVariant);
            }
        }
    }
}
