package opencommerce.apps.boostupsell;

import com.google.gson.JsonArray;
import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.CreateOfferSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CartSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ThankyouSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static com.opencommerce.shopbase.OrderVariable.accesstoken;
import static com.opencommerce.shopbase.OrderVariable.checkoutToken;


public class CreateOfferDef {
    @Steps
    CreateOfferSteps createOfferSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    ThankyouSteps thankyouSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ProductDetailSteps productDetailSteps;


    @And("^input data to create offer \"([^\"]*)\"$")
    public void inputDataToCreateOffer(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerType = SessionData.getDataTbVal(dataTable, row, "Offer type");
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String oldOffer = SessionData.getDataTbVal(dataTable, row, "Old offer");
            String offerMessage = SessionData.getDataTbVal(dataTable, row, "Offer's message");
            String offerTitle = SessionData.getDataTbVal(dataTable, row, "Offer's title");
            String targetType = SessionData.getDataTbVal(dataTable, row, "Target type");
            String targetProducts = SessionData.getDataTbVal(dataTable, row, "Target products");
            String bundleProducts = SessionData.getDataTbVal(dataTable, row, "Bundle products");
            String accessoryType = SessionData.getDataTbVal(dataTable, row, "Accessory type");
            String accessoryProducts = SessionData.getDataTbVal(dataTable, row, "Accessory products");
            String isShowWithTarget = SessionData.getDataTbVal(dataTable, row, "isShowWithTarget");
            String recommendType = SessionData.getDataTbVal(dataTable, row, "Recommend type");
            String recommendProducts = SessionData.getDataTbVal(dataTable, row, "Recommend products");
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isDiscount"));
            String discountValue = SessionData.getDataTbVal(dataTable, row, "Discount value");
            String recommendVariantWith = SessionData.getDataTbVal(dataTable, row, "Recommend variant with");

            if(!oldOffer.isEmpty()){
                createOfferSteps.clickToEditOffer(oldOffer);
            }
            createOfferSteps.chooseUpsellOfferType(offerType);
            createOfferSteps.enterOfferName(offerName);
            createOfferSteps.enterOfferMessage(offerMessage);
            createOfferSteps.enterOfferTitle(offerTitle);
            createOfferSteps.chooseProductWithBlock("Choose Target products", targetType, targetProducts);
            if(!bundleProducts.isEmpty()){
                createOfferSteps.chooseProductWithBlock("Choose Bundle products", "", bundleProducts);
                createOfferSteps.isShowBundleTarget(isShowWithTarget);
            }else{
                createOfferSteps.chooseProductWithBlock("Choose Accessories products", accessoryType, accessoryProducts);
            }
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
        }
    }

    @Given("^create offer by API \"([^\"]*)\"$")
    public void createOfferByAPI(String dataKey, List<List<String>> dataTable) {
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

            if (accesstoken.isEmpty()){
                accesstoken = createOfferSteps.getAccessTokenShop();
            }

            // format target type
            String targetTypeValue;
            switch (targetType){
                case "Specific products":
                    targetTypeValue = "product";
                    break;
                case "Specific collections":
                    targetTypeValue = "collection";
                    break;
                default:
                    targetTypeValue = "all";
            }

            // get list target product id
            JsonArray targetIDArray = new JsonArray();
            if (!targetProducts.isEmpty()){
                String[] targetProductList = targetProducts.split(",");
                long targetID;
                for (String product : targetProductList) {
                    targetID = productDetailSteps.getProductIDByName(product.trim(), accesstoken);
                    targetIDArray.add(targetID);
                }
            }

            // format recommend type
            String recommendTypeValue;
            switch (recommendType){
                case "Specific products":
                    recommendTypeValue = "product";
                    break;
                case "Specific collections":
                    recommendTypeValue = "collection";
                    break;
                case "Specific by base category":
                    recommendTypeValue = "base-category";
                    break;
                default:
                    recommendTypeValue = "all";
            }

            // get list recommend product id
            JsonArray recommendIDArray = new JsonArray();
            if (recommendType.equalsIgnoreCase("Specific products")){
                String[] recommendProductList = recommendProducts.split(",");
                long recommendID;
                for (String product : recommendProductList) {
                    recommendID = productDetailSteps.getProductIDByName(product.trim(), accesstoken);
                    recommendIDArray.add(recommendID);
                }
            }

            // list category with rule Specific by base category (in-cart offer)
            int id = 0;
            JsonArray categoryIDArray = new JsonArray();
            if (recommendType.equalsIgnoreCase("Specific by base category")){
                String[] categoryList = recommendProducts.split(",");
                for (String s : categoryList){
                    id = createOfferSteps.getCategoryIDByName(s);
                    categoryIDArray.add(id);
                }
            }

            createOfferSteps.createOfferByAPI(offerType, offerName, offerMessage, offerTitle, targetTypeValue, targetIDArray, recommendTypeValue, recommendIDArray, categoryIDArray, recommendVariantWith, isDiscount, discountValue);
        }
    }

    @Then("^click button Create offer$")
    public void clickButtonCreateOffer() {
        createOfferSteps.clickBtnCreateOffer();
    }


    @And("^wait page \"([^\"]*)\" show$")
    public void waitPageShow(String page) {
        createOfferSteps.waitUntillPageShown(page);
    }

    @Then("^click button \"([^\"]*)\" on app$")
    public void clickButtonOnApp(String button) {
        createOfferSteps.clickButtonName(button);
    }

    @And("^set up recommendation rules of smart \"([^\"]*)\" as \"([^\"]*)\"$")
    public void setUpRecommendationRulesAs(String smartType, String dataKey, List<List<String>> dataTable) {
        createOfferSteps.clickBtnSetUpRule(smartType);
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String targetRule = SessionData.getDataTbVal(dataTable, row, "TargetRules");
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            boolean isDiscount = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is Discount"));
            String discount = SessionData.getDataTbVal(dataTable, row, "Discount");
            if (!targetRule.isEmpty() && !products.isEmpty()) {
                createOfferSteps.setTargetSsmartRule(targetRule, products);
            }
            if (!discount.isEmpty()) {
                createOfferSteps.setDiscountOfferSmart(isDiscount, discount);
            }
            createOfferSteps.saveSetting();
        }
    }


    @And("^user navigate to \"([^\"]*)\" app Boost Upsell$")
    public void userNavigateToAppBoostUpsell(String menu) {
        createOfferSteps.navigationMenuAppUsell(menu);
    }


    @Then("^setting smart \"([^\"]*)\" with \"([^\"]*)\"$")
    public void settingSmartWithAndDiscountIs(String smartType, String status) {
        createOfferSteps.turnOnOffSmartOffer(smartType, status);
    }

    @When("add multiple products \"([^\"]*)\" to cart then checkout")
    public void addMultipleProductsToCartThenCheckout(String productNames) {
        productSteps.addMultipleProductsToCart(productNames);
        productSteps.goToCart();
        cartSteps.clickButtonCheckoutOnCart();
        checkoutToken = thankyouSteps.getCheckoutToken();
        System.out.println("checkout token: " + checkoutToken);
    }

    @Then("^setting priority offer on the dashboard \"([^\"]*)\"$")
    public void settingPriorityOffer(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer's name");
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            createOfferSteps.enterOfferPriority(priority,offerName);
        }
    }
}
