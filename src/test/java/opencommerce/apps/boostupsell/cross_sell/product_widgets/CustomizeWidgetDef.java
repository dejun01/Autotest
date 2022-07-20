package opencommerce.apps.boostupsell.cross_sell.product_widgets;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.cross_sell.CustomizeWidgetSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell.ProductWidgetSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomizeWidgetDef {
    @Steps
    CustomizeWidgetSteps customizeWidgetSteps;
    @Steps
    ProductWidgetSteps productWidgetSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @And("^reset widget's settings to default as \"([^\"]*)\"$")
    public void resetWidgetSettingsToDefault(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            customizeWidgetSteps.resetData();
            customizeWidgetSteps.saveSetting();
            customizeWidgetSteps.backToListOffer();
        }
    }

    @Then("^customize widget as \"([^\"]*)\"$")
    public void customizeWidgetAs(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {

            String header = SessionData.getDataTbVal(dataTable, row, "Header");
            String fontHeader = SessionData.getDataTbVal(dataTable, row, "Font header");
            String fontProductName = SessionData.getDataTbVal(dataTable, row, "Font product name");
            String fontProductPrice = SessionData.getDataTbVal(dataTable, row, "Font product price");
            String numberOfProducts = SessionData.getDataTbVal(dataTable, row, "Number of products");
//            String maxProducts = SessionData.getDataTbVal(dataTable, row, "Max product per slide");

            boolean isTurnOnAddCartButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is on add to cart button").toLowerCase());
            String sCallToActionText = SessionData.getDataTbVal(dataTable, row, "Call to action text");
            String placeWidget = SessionData.getDataTbVal(dataTable, row, "Place widget");

            customizeWidgetSteps.enterHeader(header);
            customizeWidgetSteps.settingFontHeader(fontHeader);
            customizeWidgetSteps.settingFontProductName(fontProductName);
            customizeWidgetSteps.settingFontProductPrice(fontProductPrice);
            customizeWidgetSteps.selectNumberOfProductsToBeShown(numberOfProducts);
//            customizeWidgetSteps.selectMaxProductsPerSlide(maxProducts);
            customizeWidgetSteps.settingButtonAddCart(isTurnOnAddCartButton, sCallToActionText);
            customizeWidgetSteps.choosePageShowWidget(placeWidget);
            customizeWidgetSteps.saveSetting();
        }
    }

    @Then("^verify widget's settings as \"([^\"]*)\"$")
    public void verifyWidgetSetting(String dataKey, List<List<String>> dataTable) {
        productSteps.clearLocalStorage();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product added");
            String show = SessionData.getDataTbVal(dataTable, row, "Is show");
            String sWidgetName = SessionData.getDataTbVal(dataTable, row, "Widget name");
            String fontHeader = SessionData.getDataTbVal(dataTable, row, "Font header");
            String fontProductName = SessionData.getDataTbVal(dataTable, row, "Font product name");
            String fontProductPrice = SessionData.getDataTbVal(dataTable, row, "Font product price");
            String numberOfProducts = SessionData.getDataTbVal(dataTable, row, "Number of products");
//            String maxProductsPerSlide = SessionData.getDataTbVal(dataTable, row, "Max product per slide");
            String isTurnOnAddCartButton = SessionData.getDataTbVal(dataTable, row, "Is on add to cart button").toLowerCase();
            String sCallToActionText = SessionData.getDataTbVal(dataTable, row, "Call to action text");
            String placeWidget = SessionData.getDataTbVal(dataTable, row, "Place widget");
            String collectionName = SessionData.getDataTbVal(dataTable, row, "Collection name");

            List<String> listRecommendedProducts = new ArrayList<>();
            List<String> listPagesShowWidget = new ArrayList<>();
            boolean isShow = true;

            if (!show.isEmpty()) {
                isShow = Boolean.parseBoolean(show);
            }

            if (!collectionName.isEmpty()) {
                listRecommendedProducts = collectionListSteps.getListProductInCollectionByCollectionName(collectionName);
            }

            listPagesShowWidget = new ArrayList<String>(Arrays.asList(placeWidget.split(",")));

            for (String page : listPagesShowWidget) {
                System.out.println("Verify page " + page);
                productSteps.openPage(page);
                //go to cart to verify widget on cart page
                if (!productName.isEmpty()) {
                    productSteps.addMultipleProductsToCart(productName);
                    productSteps.goToCart();
                }
                reviewOnSFSteps.waitForLoadApps("Upsell");
                productWidgetSteps.scrollToFooter();
                productWidgetSteps.verifyProductWidgetShown(sWidgetName, isShow);
                if (isShow) {
//                    productWidgetSteps.verifyFontHeader(sWidgetName, fontHeader);
                    productWidgetSteps.verifyFontProductName(sWidgetName, fontProductName);
                    productWidgetSteps.verifyFontProductPrice(sWidgetName, fontProductPrice);
//                    productWidgetSteps.verifyNumberOfProductsToBeShown(sWidgetName, numberOfProducts);
//                    if (Integer.parseInt(numberOfProducts) > Integer.parseInt(maxProductsPerSlide)) {
//                        productWidgetSteps.switchToNextSlide(sWidgetName);
//                        productWidgetSteps.switchToPreSlide(sWidgetName);
//                        productWidgetSteps.verifyMaxProductsPerSlide(sWidgetName, maxProductsPerSlide);
//                    }
                    if (!isTurnOnAddCartButton.isEmpty())
                        productWidgetSteps.verifyButtonAddCart(sWidgetName, Boolean.parseBoolean(isTurnOnAddCartButton), sCallToActionText);
                    if (listRecommendedProducts.size() > 0) {
                        productWidgetSteps.verifyRecommendedProduct(sWidgetName, listRecommendedProducts);
                    }
                }
            }

        }
    }
}
