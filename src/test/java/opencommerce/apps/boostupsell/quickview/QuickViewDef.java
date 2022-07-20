package opencommerce.apps.boostupsell.quickview;

import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.cross_sell.ProductWidgetSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.AddMoreItemSteps;
import com.opencommerce.shopbase.storefront.steps.apps.boostupsell.quickview.QuickViewSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class QuickViewDef {
    @Steps
    QuickViewSteps quickViewSteps;
    @Steps
    AddMoreItemSteps addMoreItemSteps;
    @Steps
    ProductWidgetSteps productWidgetSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Then("^verify quick view show \"([^\"]*)\"$")
    public void verifyQuickViewShow(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String variants = SessionData.getDataTbVal(dataTable, row, "Variant");
            boolean isShowThumbNail = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowThumbNail"));
            boolean isShowBundle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowBundle"));
            boolean isShowRecommendForYou = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowRecommendForYou"));
            boolean isAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isAddToCart"));
            String textValidate = SessionData.getDataTbVal(dataTable, row, "Text validate");
            if (isShow) {
                quickViewSteps.verifyQuickViewShown();
                quickViewSteps.verifyTitleProduct(title);
                quickViewSteps.verifyPriceProduct(price);
                quickViewSteps.verifyVariantTitle(variants);
                quickViewSteps.verifyThumdnailShow(isShowThumbNail);
                quickViewSteps.verifyBundleShowOnQuickview(isShowBundle);
                quickViewSteps.verifyRecommendForYou(isShowRecommendForYou);
                if (isAddToCart) {
                    quickViewSteps.clickAddToCart();
                } else
                    addMoreItemSteps.clickClosePopup();
                if(!textValidate.isEmpty()){
                    quickViewSteps.verifyValidateField(textValidate);
                    quickViewSteps.verifyQuickViewShown();
                }

            }
        }
    }

    @Then("^add to cart product on widget as \"([^\"]*)\"$")
    public void addToCartProductOnWidgetAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String widgetName = SessionData.getDataTbVal(dataTable, row, "Widget name");
            String numberProduct = SessionData.getDataTbVal(dataTable, row, "Number product");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            productWidgetSteps.scrollToFooter();
            reviewOnSFSteps.waitForLoadApps("Upsell");
            productWidgetSteps.verifyProductWidgetShown(widgetName, true);
            if (!numberProduct.isEmpty()) {
                productWidgetSteps.addToCartProductIndex(widgetName, numberProduct);
            }
            if (!productName.isEmpty()) {
                productWidgetSteps.addToCartProductName(widgetName, productName);
            }
        }
    }



}
