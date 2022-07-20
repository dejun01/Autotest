package opencommerce.products.dashboard;

import com.opencommerce.shopbase.dashboard.products.steps.ProductFeedDetailSteps;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ProductFeedDetailDef {

    @Steps
    ProductFeedDetailSteps productFeedDetailSteps;

    private String feedName;
    public static String CSV_FILE_PATH;
    public static String name;
    public static int totalProduct;
    public static int countProduct;
    public static String linkText;

    @And("^Create a new product feed with data$")
    public void create_a_new_product_feed_with_data(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            productFeedDetailSteps.clickAddProductFeed();
            String feedNameProduct = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String allProductOrJustSome = SessionData.getDataTbVal(dataTable, row, "All products or just some of them?");
            String nameCollection = SessionData.getDataTbVal(dataTable, row, "Collection name");
            String exportMode = SessionData.getDataTbVal(dataTable, row, "Export mode");
            String variantTitle = SessionData.getDataTbVal(dataTable, row, "Variant title");
            String googleProductCategory = SessionData.getDataTbVal(dataTable, row, "Google product category");

            if (!feedNameProduct.equals("")) {
                if (feedNameProduct.matches("@(.*)@")) {
                    long currentTime = System.currentTimeMillis();
                    feedName = feedNameProduct.replaceAll("@", "") + "_" + currentTime;
                    productFeedDetailSteps.enterFeedName(feedName);
                } else {
                    productFeedDetailSteps.enterFeedName(feedNameProduct);
                }
            }

            productFeedDetailSteps.checkUncheckElement(allProductOrJustSome);
            if(allProductOrJustSome.equals("Products from selected collections")){
                productFeedDetailSteps.searchCollection(nameCollection);
                productFeedDetailSteps.chooseCollection();
                productFeedDetailSteps.clickAddCollection();
            }
            productFeedDetailSteps.checkUncheckElement(exportMode);
            productFeedDetailSteps.checkUncheckElement(variantTitle);
            productFeedDetailSteps.enterGoogleProductCategory(googleProductCategory);
        }
        productFeedDetailSteps.clickSaveButton();
        productFeedDetailSteps.clickBreadProductFeeds();
    }

    @When("^Open detail product feed screen of product feed \"([^\"]*)\"$")
    public void open_detail_product_feed_screen_of_product_feed(String nameFeed) {
        if (nameFeed.matches("@(.*)@")) {
            productFeedDetailSteps.chooseProductFeed(feedName);
        } else {
            productFeedDetailSteps.chooseProductFeed(nameFeed);
        }
    }

    @And("^Search product feed \"([^\"]*)\" on Product feeds screen$")
    public void searchProductTFeedOnAllProductFeedsScreen(String nameFeed) {
        if (nameFeed.matches("@(.*)@")) {
            productFeedDetailSteps.searchProductFeed(feedName);
        } else {
            productFeedDetailSteps.searchProductFeed(nameFeed);
        }
    }

//    @When("^verify feed information with data$")
//    public void verify_feed_information_with_data(List<List<String>> dataTable) {
//        String dataTableKey  = "productFeedData1";
//
//        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
//            String feedNameProduct = SessionData.getDataTbVal(dataTable, row, "Feed name");
//            String allProductOrJustSome = SessionData.getDataTbVal(dataTable, row, "All products or just some of them?");
//            String exportMode = SessionData.getDataTbVal(dataTable, row, "Export mode");
//            String variantTitle = SessionData.getDataTbVal(dataTable, row, "Variant title");
//            String googleProductCategory = SessionData.getDataTbVal(dataTable, row, "Google product category");
//
////            productFeedSteps.verifyFeedName(feedNameProduct);
////            productFeedSteps.verifyCheckUncheckElemant(allProductOrJustSome);
////            productFeedSteps.verifyCheckUncheckElemant(exportMode);
////            productFeedSteps.verifyCheckUncheckElemant(variantTitle);
////            productFeedSteps.verifyGoogleProductCategory(googleProductCategory);
//        }
//    }

//    @Then("^open csv file containing feed information$")
//    public void open_csv_file_containing_feed_information() throws Exception {
//        String env = LoadObject.getProperty("gapi.url").split("https://")[1];
//        switch (env) {
//            case "api.shopbase.com":
//                CSV_FILE_PATH = LoadObject.getFilePath("prod_feed_information.csv");
//                break;
//            case "gapi.stag.shopbase.net":
//                CSV_FILE_PATH = LoadObject.getFilePath("stag_feed_information.csv");
//                break;
//            case "gpai.dev.shopbase.net":
//                CSV_FILE_PATH = LoadObject.getFilePath("dev_feed_information.csv");
//                break;
//            default:
//                fail();
//        }
//
//        ArrayList<String> feedInfor = abandonedCheckoutsSteps.readingCustomerInfor(CSV_FILE_PATH);
//        for (String number : feedInfor) {
//            name = feedInfor.get(0);
//            totalProduct = feedInfor.get(1);
//        }
//    }


//    @Then("^get feed in dashboard and save feed information to csv file$")
//    public void get_feed_in_dashboard_and_save_feed_information_to_csv_file(String productFeed) {
//        name = productFeedDetailSteps.getName();
//        totalProduct = productFeedDetailSteps.getTotal(productFeed);
//
//        String env = LoadObject.getProperty("gapi.url").split("https://")[1];
//        switch (env) {
//            case "api.shopbase.com":
//                CSV_FILE_PATH = LoadObject.getFilePath("prod_feed_information.csv");
//                break;
//            case "gapi.stag.shopbase.net":
//                CSV_FILE_PATH = LoadObject.getFilePath("stag_feed_information.csv");
//                break;
//            case "gpai.dev.shopbase.net":
//                CSV_FILE_PATH = LoadObject.getFilePath("dev_feed_information.csv");
//                break;
//            default:
//                fail();
//        }
//        productFeedDetailSteps.addFeedInfoToCSV(CSV_FILE_PATH, name, totalProduct);
//    }

    @And("^Search product feed \"([^\"]*)\"$")
    public void searchProductFeed(String feedName) {
        productFeedDetailSteps.searchProductFeed(feedName);
        productFeedDetailSteps.chooseProductFeed(feedName);
    }

    @And("^edit product feed$")
    public void editProductFeed(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String feedName = SessionData.getDataTbVal(dataTable, row, "Feed name");
            String newFeedName = SessionData.getDataTbVal(dataTable, row, "New feed name");

            productFeedDetailSteps.searchProductFeed(feedName);
            productFeedDetailSteps.chooseProductFeed(feedName);

            productFeedDetailSteps.typeFeedName(newFeedName);
            productFeedDetailSteps.clickSaveButton();
            productFeedDetailSteps.clickBreadProductFeeds();
        }
    }
}
