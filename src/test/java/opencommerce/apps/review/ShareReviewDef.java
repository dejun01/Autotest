package opencommerce.apps.review;

import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ShareReviewSteps;
import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ShareReviewDef {
    @Steps
    ShareReviewSteps shareReviewSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;
    @Steps
    LoginDashboardSteps loginStep;

    @And("^import shared reviews \"([^\"]*)\"$")
    public void importSharedReviews(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String condition = SessionData.getDataTbVal(dataTable, row, "Condition");
            String numberOfSharedReviews = SessionData.getDataTbVal(dataTable, row, "Number of shared reviews");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            shareReviewSteps.setupToImportSharedReview(condition, numberOfSharedReviews);
            shareReviewSteps.clickOnButton("Continue");
            if (!message.isEmpty()) {
                shareReviewSteps.verifyMessage(message);
            } else {
                int i = 0;
                while (i < 5 && !shareReviewSteps.showBtn("Check now")) {
                    shareReviewSteps.waitABit(5000);
                    i++;
                }
                shareReviewSteps.clickOnButton("Check now");
            }
        }
    }

    @And("^verify shared reviews on All reviews page dashboard \"([^\"]*)\"$")
    public void verifySharedReviewOnDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String reviewType = SessionData.getDataTbVal(dataTable, row, "Review type");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            int minNumberOfStar = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Min number of star"));
            String numberOfReviewsByProduct = SessionData.getDataTbVal(dataTable, row, "Number of reviews by product");
            String numberOfReviewsByStatus = SessionData.getDataTbVal(dataTable, row, "Number of reviews by status");
            String numberOfReviewsByReviewType = SessionData.getDataTbVal(dataTable, row, "Number of reviews by review type");

            shareReviewSteps.verifyImportedReview();
            if(product.isEmpty()){
                shareReviewSteps.verifySharedReviews("Review type", Integer.parseInt(numberOfReviewsByReviewType), reviewType);
                shareReviewSteps.verifyMinStar(minNumberOfStar, Integer.parseInt(numberOfReviewsByReviewType));
            }else {
                reviewInProductAdminSteps.verifyFilterByProduct(product);
                shareReviewSteps.verifyNumberOfReviews(Integer.parseInt(numberOfReviewsByProduct));
                shareReviewSteps.verifyMinStar(minNumberOfStar, Integer.parseInt(numberOfReviewsByProduct));
            }
            if(!status.isEmpty()){
                shareReviewSteps.verifySharedReviews("Status", Integer.parseInt(numberOfReviewsByStatus), status);
            }
        }
    }

    @Then("^click on Import reviews on \"([^\"]*)\" block$")
    public void clickOnImportReviewsButton(String blockName) {
        loginStep.closeLiveChat();
        shareReviewSteps.clickOnImportReviewsButton(blockName);
    }

    @And("^verify shared review on SF \"([^\"]*)\"$")
    public void verifySharedReviewOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int numberOfReviews = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Number of reviews"));
            int minNumberOfStar = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Min number of star"));
            String reviewsType = SessionData.getDataTbVal(dataTable, row, "Reviews Type");

            reviewOnSFSteps.waitForLoadApps("Review");
            reviewInProductAdminSteps.verifyActiveTab(reviewsType, true);
            reviewOnSFSteps.verifyReviewNumber(numberOfReviews);
            reviewOnSFSteps.verifyMinimumStar(numberOfReviews, minNumberOfStar);
        }
    }

    @Then("^navigate to \"([^\"]*)\" tab$")
    public void navigateToTab(String tabName) {
        shareReviewSteps.navigateToTab(tabName);
    }

    @When("^select the first review on list$")
    public void selectTheFirstReview() {
        shareReviewSteps.selectTheFirstReview();
    }
}
