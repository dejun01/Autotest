package opencommerce.apps.review;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ShareReviewSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ReviewInProductAdminDef {
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;
    @Steps
    AllReviewsSteps allReviewsSteps;
    @Steps
    ShareReviewSteps shareReviewSteps;
    @Steps
    CommonSteps commonSteps;

    @When("^open product \"([^\"]*)\" on Dashboard$")
    public void searchAndSelectProduct(String productName) {
        reviewInProductAdminSteps.searchProductOnDashboard(productName);
        reviewInProductAdminSteps.openProductOnDashboard(productName);
    }

    @When("^open Import Reviews popup")
    public void openImportReviewsPopup(){
        reviewInProductAdminSteps.openImportReviewsPopup();
    }

    @When("^import AliExpress reviews into product admin")
    public void importAliExpressReviewIntoProductReviews(List<List<String>> dataTable) {
        reviewInProductAdminSteps.chooseImportBy("AliExpress");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String urls = SessionData.getDataTbVal(dataTable, row, "URLs AliExpress");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            reviewInProductAdminSteps.enterLinkURL(urls);
            reviewInProductAdminSteps.clickButtonOnPopup("Continue");
            allReviewsSteps.verifyMessage(msg);
        }
    }

    @And("import reviews from a spreadsheet into Product admin with cvs file is {string}")
    public void importReviewsFromASpreadsheetIntoProductReviewsWithCvsFileIs(String fileName) {
        reviewInProductAdminSteps.chooseImportBy("CSV file");
        allReviewsSteps.chooseFileCSV(fileName);
        reviewInProductAdminSteps.clickButtonOnPopup("Import reviews");
    }

    @When("click {string} button on popup")
    public void clickButtonOnPopup(String label){
        reviewInProductAdminSteps.clickButtonOnPopup(label);
    }

    @When("^verify active tab \"([^\"]*)\" on SF$")
    public void verifyActiveTab(String label){
        reviewInProductAdminSteps.verifyActiveTab(label, true);
    }

    @When("^verify auto filter reviews by product \"([^\"]*)\"$")
    public void verifyFilterByProduct(String tagName){
        reviewInProductAdminSteps.verifyFilterByProduct(tagName);
    }

    @When("^open product \"([^\"]*)\" on Dashboard PrintBase$")
    public void openProductOnDBPBase(String product){
        reviewInProductAdminSteps.searchProductOnDBPbase(product);
        reviewInProductAdminSteps.openProductOnDashboard(product);
    }

    @And("^import shared reviews in product admin \"([^\"]*)\"$")
    public void importSharedReviewsInProductAdmin(String dataKey, List<List<String>> dataTable) {
        reviewInProductAdminSteps.chooseImportBy("PrintBase shared reviews");

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String numberOfSharedReviews = SessionData.getDataTbVal(dataTable, row, "Number of shared reviews");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            shareReviewSteps.setupToImportSharedReview(condition, numberOfSharedReviews);
            reviewInProductAdminSteps.clickButtonOnPopup("Import reviews");
            if(!message.isEmpty()){
                shareReviewSteps.verifyMessage(message);
            }
        }
    }

    @And("^verify Reviews Analytics \"([^\"]*)\"$")
    public void verifyReviewsAnalytics(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String totalReviews = SessionData.getDataTbVal(dataTable, row, "Total reviews");
            String avgRating = SessionData.getDataTbVal(dataTable, row, "Avg rating");
            String rating = SessionData.getDataTbVal(dataTable, row, "Rating");

            if(!totalReviews.isEmpty()){
                String token = commonSteps.getAccessTokenShop();
                reviewInProductAdminSteps.verifyReviewsAnalytics("Total reviews", totalReviews, rating, token);
                reviewInProductAdminSteps.verifyReviewsAnalytics("Average rating", avgRating, rating, token);
            }
        }
    }

}
