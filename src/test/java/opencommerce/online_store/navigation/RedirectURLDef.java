package opencommerce.online_store.navigation;

import com.opencommerce.shopbase.dashboard.online_store.pages.steps.PagesSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CollectionDetailSteps;
import com.opencommerce.shopbase.storefront.steps.shop.CustomPagesSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.online_store.navigation.steps.RedirectURLSteps;

import java.util.List;

public class RedirectURLDef {
    @Steps
    RedirectURLSteps redirectURLSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    ProductDetailSteps productDetailSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    com.opencommerce.shopbase.storefront.steps.shop.ProductSteps sfProductSteps;
    @Steps
    CollectionDetailSteps sfCollectionDetailSteps;
    @Steps
    PagesSteps pagesSteps;
    @Steps
    CustomPagesSteps sfCusPagesSteps;

    @Then("^user change url product \"([^\"]*)\" and check redirect on Storefront$")
    public void user_change_url_product_and_check_redirect_on_Storefront(String productName, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean isCreate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Create a URL redirect"));
            long currentTime = System.currentTimeMillis();
            String newURL = productName + currentTime;
            productListSteps.chooseProduct(productName);
            String currentURL = productDetailSteps.getCurrentURL();
            productDetailSteps.changeURL(newURL, isCreate);
            redirectURLSteps.checkRedirectURL(currentURL, newURL, isCreate);
            sfProductSteps.checkApplyRedirectProductOnSF(currentURL, newURL, productName, isCreate);
            sfProductSteps.backToProductPage();
        }
    }

    @Then("^user change url collection \"([^\"]*)\" and check redirect on Storefront$")
    public void user_change_url_collection_and_check_redirect_on_Storefront(String collectionName, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean isCreate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Create a URL redirect"));
            long currentTime = System.currentTimeMillis();
            String newURL = collectionName + currentTime;
            collectionListSteps.chooseCollection(collectionName);
            String currentURL = collectionListSteps.getCurrentURL();
            collectionListSteps.changeURLCollection(newURL, isCreate);
            redirectURLSteps.checkRedirectURLCollection(currentURL, newURL, isCreate);
            sfCollectionDetailSteps.checkApplyRedirectCollectionOnSF(currentURL, newURL, collectionName, isCreate);
            sfCollectionDetailSteps.backtoCollectionPage();
        }
    }

    @Then("^user change url pages \"([^\"]*)\" and check redirect on Storefront$")
    public void user_change_url_pages_and_check_redirect_on_Storefront(String page, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean isCreate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Create a URL redirect"));
            long currentTime = System.currentTimeMillis();
            String newURL = page + currentTime;
            pagesSteps.choosePage(page);
            String currentURL = pagesSteps.getCurrentURL();
            pagesSteps.changeURLPage(newURL, isCreate);
            redirectURLSteps.checkRedirectURLPage(currentURL, newURL, isCreate);
            sfCusPagesSteps.checkAppyRedirectPageOnSF(currentURL, newURL, page, isCreate);
            sfCusPagesSteps.backtoPage();
        }
    }

    @Then("^user edit redirect on URL redirect URL page and check apply on Storefornt$")
    public void user_edit_redirect_on_URL_redirect_URL_page_and_check_apply_on_Storefornt() {
        long currentTime = System.currentTimeMillis();
        redirectURLSteps.clickURLRedirects();
        redirectURLSteps.searchRedirectURL("dress");
        redirectURLSteps.changeRedirectURL(String.valueOf(currentTime), "dress");
        redirectURLSteps.verifyApplyNewRedirectOnSF(String.valueOf(currentTime), "/products/dress");
    }

    @Then("^user create new redirect URL on URL redirect page$")
    public void user_create_new_redirect_URL_on_URL_redirect_page( List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String redirect = SessionData.getDataTbVal(dataTable, row, "Create a URL redirect");
            long currentTime = System.currentTimeMillis();
            redirectURLSteps.backtoNavigationPage();
            redirectURLSteps.clickURLRedirects();
            redirectURLSteps.clickCreateURLredirect();
            redirectURLSteps.createRedirect(String.valueOf(currentTime), redirect);
            redirectURLSteps.verifyApplyNewRedirectOnSF(String.valueOf(currentTime), redirect);
        }
    }

    @Given("^verify redirect product with product handle$")
    public void verify_redirect_product_with_product_handle( List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productHandle = SessionData.getDataTbVal(dataTable, row, "Product handle");
            Boolean cases = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Case"));
            sfProductSteps.enterURLwithProHandle(productHandle);
            sfProductSteps.verifyRedirectproductWithHandle(cases, productHandle);
        }
    }
}
