package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.CollectionDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.ArrayList;
import java.util.List;

public class CollectionListDef {
    @Steps
    CollectionListSteps collectionListSteps;

    @Steps
    CollectionDetailSteps collectionDetailSteps;

    @Steps
    ProductDetailSteps productDetailSteps;

    @Steps
    ProductSteps productSteps;
    @Steps
    ProductListSteps productListSteps;

    String accessToken = "";
    String shop = LoadObject.getProperty("shop");

    @And("^create Collection by API on Shopbase$")
    public void createCollectionByAPIOnShopbase(List<List<String>> dataTable) {
        if (accessToken.isEmpty()) {
            accessToken = collectionListSteps.getAccessTokenShopbase();
        }

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String sCollectionName = SessionData.getDataTbVal(dataTable, row, "Name collection");
            String sProductName = SessionData.getDataTbVal(dataTable, row, "Products");

            List<Long> productIds = new ArrayList<>();

            if (!sProductName.isEmpty()) {
                String[] productNames = sProductName.split(",");
                for (String product : productNames) {
                    System.out.println(product);
                    long productID = productDetailSteps.getProductIDByName(product, accessToken);
                    productIds.add(productID);
                }
            }
            int sizeCollection = collectionListSteps.countCollection(sCollectionName, accessToken);
            if (sizeCollection == 0)
                collectionListSteps.createCollectionAPI(sCollectionName, accessToken);
            long collectionID = 0;
            if (!sCollectionName.isEmpty()) {
                collectionID = collectionListSteps.getCollectionIDByName(sCollectionName, accessToken);
            }
            collectionListSteps.updateProductCollectionAPI(collectionID, productIds, accessToken);
        }
    }

    @When("^Search collection \"([^\"]*)\" on Collections screen$")
    public void searchCollectionOnCollectionsScreen(String collectionName) {
        if (collectionName.matches("@(.*)@") && collectionName.contains("Manual")) {
            collectionListSteps.searchCollection(CollectionDetailDef.collectionNameManual);
        } else if (collectionName.matches("@(.*)@") && collectionName.contains("Auto")) {
            collectionListSteps.searchCollection(CollectionDetailDef.collectionNameAuto);
        } else {
            collectionListSteps.searchCollection(collectionName);
        }
    }

    @And("^Open detail collection of collection \"([^\"]*)\"$")
    public void openDetailCollectionScreenOfThisCollection(String collectionName) {
        if (collectionName.matches("@(.*)@") && collectionName.contains("Manual")) {
            collectionListSteps.chooseCollection(CollectionDetailDef.collectionNameManual);
        } else if (collectionName.matches("@(.*)@") && collectionName.contains("Auto")) {
            collectionListSteps.chooseCollection(CollectionDetailDef.collectionNameAuto);
        } else {
            collectionListSteps.searchCollection(collectionName);
        }
    }

    @And("^Delete all collection$")
    public void deleteAllCollection() {
        if (!collectionListSteps.hasNoProduct()) {
            collectionListSteps.selectAllCollection();
            collectionListSteps.clickAction();
            collectionListSteps.chooseActionDeleteSelectedCollection();
            collectionListSteps.clickBtnDelete();
        }
    }

    @And("delete collection")
    public void deleteCollectionHasHandle(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String handle = SessionData.getDataTbVal(dataTable, row, "Handle");

            collectionListSteps.selectCollection(title);
            collectionListSteps.clickAction();
            collectionListSteps.chooseActionDeleteSelectedCollection();
            collectionListSteps.clickBtnDelete();
        }
    }

    @And("search collection with title {string}")
    public void searchCollectionWithTitle(String title) {
        collectionListSteps.searchCollection(title);
        collectionListSteps.chooseCollection(title);
    }

    @Then("verify list product in collection on store front")
    public void verifyListProductInCollectionOnStoreFront(List<List<String>> dataTable) {
        collectionDetailSteps.clickBtnView();
        productSteps.switchToLatestTab();
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String imageStatus = SessionData.getDataTbVal(dataTable, row, "Image status");

            collectionListSteps.verifyImageProductThumbnail(product, ProductDetailDef.imageVariant, imageStatus);
        }
    }


    @And("open collection list on Store front")
    public void openCollectionListOnStoreFront() {
        collectionListSteps.openCollectionList();
    }

    @And("verify number collections on Store front")
    public void verifyNumberCollectionsOnStoreFront() {
        collectionListSteps.verifyNumberCollectionsOnStoreFront(CollectionDetailDef.totalCollection);
    }

    @Then("verify list collection in dashboard")
    public void verifyListCollectionInDashboard() {
        collectionListSteps.verifyPageCollection(shop);
    }

    @And("verify number collections on Dashboard")
    public void verifyNumberCollectionsOnDashboard() {
        collectionListSteps.verifyNumberCollectionsOnDashboard(CollectionDetailDef.totalCollection);
    }


    @Then("add product to collection from dashboard")
    public void addProductToCollectionFromDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String nameCollection = SessionData.getDataTbVal(dataTable, row, "Name collection");
            if(!action.isEmpty()){
                productListSteps.clickAction();
                collectionListSteps.clickAddToCollection(action);
            }
            collectionListSteps.searchCollection(nameCollection);
            collectionListSteps.selectCollectionFromDashboard(nameCollection);

        }
    }
}
