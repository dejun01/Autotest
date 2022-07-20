package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.ProductFeedListSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;


public class ProductFeedListDef {
    @Steps
    ProductFeedListSteps productFeedListSteps;

    public static String linkText;
    public static String name;
    public static int countProduct;

    @And("^Delete product feed with title$")
    public void deleteProductFeed(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String feedName = SessionData.getDataTbVal(dataTable, row, "Title");

            productFeedListSteps.searchProductFeed(feedName);
            if(!productFeedListSteps.hasNoProductFeed()){
                productFeedListSteps.deleteProductFeed(feedName);
                productFeedListSteps.clickDelete();
            }
        }
    }

    @Then("^verify detail value product of the \"([^\"]*)\"$")
    public void verifyDetailValueProductOfThe(String productFeed, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String productType = SessionData.getDataTbVal(dataTable, row, "Product type");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String barcode = SessionData.getDataTbVal(dataTable, row, "Barcode");
            String gender = SessionData.getDataTbVal(dataTable, row, "gender");
            String ageGroup = SessionData.getDataTbVal(dataTable, row, "age_group");

            productFeedListSteps.searchProductFeed(productFeed);
            linkText = productFeedListSteps.getUrl(productFeed);
            productFeedListSteps.verifyTitle(title,linkText);
            productFeedListSteps.verifyDescription(description,linkText);
            productFeedListSteps.verifyProductType(productType,linkText);
            productFeedListSteps.verifyPrice(price,linkText);
            productFeedListSteps.verifyCompareAtPrice(compareAtPrice,linkText);
            productFeedListSteps.verifyBarcode(barcode,linkText);
            productFeedListSteps.verifyGender(gender,linkText);
            productFeedListSteps.verifyGender(ageGroup,linkText);
        }
    }

    @Then("^verify total product of the \"([^\"]*)\"$")
    public void verifyTotalProductOfThe(String productFeed) throws IOException {
        linkText = productFeedListSteps.getUrl(productFeed);
        productFeedListSteps.verifyCountProduct(productFeed,linkText);
    }
}
