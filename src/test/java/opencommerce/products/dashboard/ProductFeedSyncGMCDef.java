package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.ProductFeedDetailSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductFeedSyncGMCSteps;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductFeedSyncGMCDef {

    @Steps
    ProductFeedSyncGMCSteps productFeedSyncGMCSteps;
    @Steps
    ProductFeedDetailSteps productFeedDetailSteps;


    @Then("verify file product feed display exactly")
    public void verifyFileProductFeedDisplayExactly(List<List<String>> dataTable) throws IOException, CsvValidationException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String expectedResult = SessionData.getDataTbVal(dataTable, row, "Expected");

            int rowFeed = productFeedSyncGMCSteps.getRowFeedUrl(feedName);
            productFeedSyncGMCSteps.downloadProductFeedUrl(rowFeed);

            String fileFeedDownloaded = productFeedSyncGMCSteps.openFileFeedDownloaded();
            List<List<String>> actual = SessionData.loadDataTableByCSV(fileFeedDownloaded);
            String filePath = LoadObject.getFilePath(expectedResult);
            List<List<String>> expect = SessionData.loadDataTableByCSV(filePath);
            assertThat(actual).isEqualTo(expect);
            productFeedSyncGMCSteps.switchToTheFirstTab();
        }
    }

    @And("add a new product feed with data")
    public void addANewProductFeedWithDataAs(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            productFeedDetailSteps.clickAddProductFeed();
            String feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String allProductOrCollection = SessionData.getDataTbVal(dataTable, row, "All products or just some of them?");
            String collectionName = SessionData.getDataTbVal(dataTable, row, "Collection name");
            String allVariantOrJustSome = SessionData.getDataTbVal(dataTable, row, "All variations or just some of them?");
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String keyWord = SessionData.getDataTbVal(dataTable, row, "Key word");
            String productTitle = SessionData.getDataTbVal(dataTable, row, "Product title");
            String productTitlePreference = SessionData.getDataTbVal(dataTable, row, "Product title preference");
            String productDescriptionPreference = SessionData.getDataTbVal(dataTable, row, "Product description preference");
            String submitProductsAsCustomProducts = SessionData.getDataTbVal(dataTable, row, "Submit products as custom products");
            String defaultBrandName = SessionData.getDataTbVal(dataTable, row, "Default brand name");

            productFeedSyncGMCSteps.enterFeedName(feedName);
            productFeedDetailSteps.checkUncheckElement(allProductOrCollection);
            if (allProductOrCollection.equals("Products from selected collections")) {
                productFeedDetailSteps.searchCollection(collectionName);
                productFeedDetailSteps.chooseCollection();
                productFeedDetailSteps.clickAddCollection();
            }
            productFeedSyncGMCSteps.setUpAllVariantOrJustSome(allVariantOrJustSome);
            if (allVariantOrJustSome.equals("Export all variations of a product")) {
                productFeedSyncGMCSteps.clickCheckBoxExcludeVariationsOfAProduct();
                productFeedSyncGMCSteps.setConditionExclude(condition);
                productFeedSyncGMCSteps.enterKeyWord(keyWord);
            } else if (allVariantOrJustSome.equals("Export only variations of a product matching")) {
                productFeedSyncGMCSteps.setConditionMatching(condition);
                productFeedSyncGMCSteps.enterKeyWordMatching(keyWord);
            }
            productFeedSyncGMCSteps.setProductTitle(productTitle);
            productFeedSyncGMCSteps.setProductTitlePreference(productTitlePreference);
            productFeedSyncGMCSteps.setProductDescriptionPreference(productDescriptionPreference);
            productFeedSyncGMCSteps.submitProductsAsCustomProducts(submitProductsAsCustomProducts);
            productFeedSyncGMCSteps.setupBrandName(defaultBrandName);
            productFeedDetailSteps.clickSaveButton();
            productFeedSyncGMCSteps.backToProfuctFeedList();

        }
    }

    @When("delete all product feed")
    public void deleteAllProductFeed() {
        while(productFeedSyncGMCSteps.existFeedCanBeDelete()){
            productFeedSyncGMCSteps.deleteProductFeed();
        }
    }

    @When("setting GMC for product feed")
    public void goToProductFeedName(List<List<String>> dataTable) {
        for (int row: SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
            String feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String settingGMC = SessionData.getDataTbVal(dataTable, row, "Is enable GMC");
            productFeedSyncGMCSteps.goToProductFeedname(feedName);
            productFeedSyncGMCSteps.settingGMC(Boolean.parseBoolean(settingGMC));
            productFeedDetailSteps.clickSaveButton();
            productFeedSyncGMCSteps.backToProfuctFeedList();
        }
    }

    @Then("verify GMC in all products feed is disabled")
    public void verifyGMCInAllProductsFeedIsDisabled() {
        productFeedSyncGMCSteps.verifyGMCInAllProductsFeedIsDisabled();
    }

    @Then("verify GMC method of all feed after setting")
    public void verifyGMCMethodOfAllFeedAfterSetting(List<List<String>> dataTable) {
        for(int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()){
            String feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String status = SessionData.getDataTbVal(dataTable, row, "Is enable GMC");
            productFeedSyncGMCSteps.goToProductFeedname(feedName);
            productFeedSyncGMCSteps.verifyFeedStatus(Boolean.parseBoolean(status));
            productFeedSyncGMCSteps.backToProfuctFeedList();
        }
    }
}
