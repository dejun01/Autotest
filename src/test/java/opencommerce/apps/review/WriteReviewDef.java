package opencommerce.apps.review;

import com.opencommerce.other.yopmail.review.steps.NotificationsSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.campaign.MyCampaignSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductDetailSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.DateTimeUtil;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class WriteReviewDef {
    @Steps
    AllReviewsSteps allReviewsSteps;

    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @Steps
    MyCampaignSteps myCampaignSteps;

    @Steps
    ProductDetailSteps productSteps;

    @Steps
    NotificationsSteps notificationsSteps;


    @When("^user delete all reviews$")
    public void deleteAllReviews() {
        if (allReviewsSteps.countReviews() > 0) {
            allReviewsSteps.selectAllReviews();
            allReviewsSteps.clickBtnAction();
            allReviewsSteps.selectDelete();
            allReviewsSteps.confirmDelete();
        }

    }

    @Then("^verify deleted all reviews$")
    public void verifyDeletedAllReviews() {
        allReviewsSteps.verifyDeletedAllReviews();
    }

    @When("^import AliExpress reviews into product reviews$")
    public void importAliExpressReviewIntoProductReviews(List<List<String>> dataTable) {
        allReviewsSteps.clickBtnImport(1);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String urls = SessionData.getDataTbVal(dataTable, row, "URLs AliExpress");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            if (!productName.equals("")) {
                allReviewsSteps.chooseProduct(productName);
            }
            if(!urls.isEmpty()) {
                allReviewsSteps.enterURL(urls);
            }
            allReviewsSteps.clickBtnContinue();
            allReviewsSteps.verifyMessage(msg);
        }

    }

    @When("^setup AliExpress filter$")
    public void setupAliExpressFilter(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String star = SessionData.getDataTbVal(dataTable, row, "Import reviews from star");
            String maxNumber = SessionData.getDataTbVal(dataTable, row, "Maximum number of reviews per link");
            String country = SessionData.getDataTbVal(dataTable, row, "Country");
            allReviewsSteps.setupStar(star);
            allReviewsSteps.setupMaxNumber(maxNumber);
            allReviewsSteps.setupCountry(country);
            allReviewsSteps.checkKeepOriginalDate();
            allReviewsSteps.clickBtnGetReviews();
        }
    }

    @Then("^confirm get reviews$")
    public void confirmGetReview() {
        allReviewsSteps.confirmGetReview();
    }

    @Then("verify review AliExpress import on productpage")
    public void verifyReviewAliExpressImportOnProductpage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String numberOfReview = SessionData.getDataTbVal(dataTable, row, "Number of review");
            String minimumOfStar = SessionData.getDataTbVal(dataTable, row, "Minimum of star");
            int reviewNumber = Integer.parseInt(numberOfReview);
            int minimumStar = Integer.parseInt(minimumOfStar);
            reviewOnSFSteps.verifyReviewNumber(reviewNumber);
            reviewOnSFSteps.verifyMinimumStar(reviewNumber, minimumStar);
        }
    }

    @Given("^user click Settings on menu")
    public void clickSettingsOnMenu() {
        allReviewsSteps.clickSettingsOnMenu();
    }

    @Given("^update Reviews settings as \"([^\"]*)\"$")
    public void updateReviewsSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String autoPublishReivew = SessionData.getDataTbVal(dataTable, row, "Auto-publish reviews");
            Boolean autoPublish = Boolean.parseBoolean(autoPublishReivew);
            String minimumStar = SessionData.getDataTbVal(dataTable, row, "Minimum star");
            String keywordBlock = SessionData.getDataTbVal(dataTable, row, "Auto-block reviews");
            allReviewsSteps.checkAutoPublish(autoPublish);
            allReviewsSteps.selectMinimumStar(minimumStar);
            allReviewsSteps.inputKeywordBlock(keywordBlock);

        }
        allReviewsSteps.saveChanges();
    }

    @Then("verify reviews are show on SF as {string}")
    public void verifyReviewsAreShowOnSFAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showReview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show review on SF"));
            String rating = SessionData.getDataTbVal(dataTable, row, "Rating");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String yourName = SessionData.getDataTbVal(dataTable, row, "Your name");

            waitToLoadApps("Reviews");
            reviewOnSFSteps.verifyReviewShow(type, title, showReview);
            if (showReview) {
                reviewOnSFSteps.verifyRating(title, rating);
                reviewOnSFSteps.verifyContent(title, content);
                reviewOnSFSteps.verifyYourName(title, yourName);
            }
        }
    }

    @And("verify list review on dashboard as {string}")
    public void verifyListReviewOnDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String yourName = SessionData.getDataTbVal(dataTable, row, "Your name");
            int rating = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Rating"));
            allReviewsSteps.verifyContent(title, content);
            allReviewsSteps.verifyStatus(title, status);
            allReviewsSteps.verifyYourName(title, yourName);
            allReviewsSteps.verifyRating(title, rating);
        }
    }

    @And("search and select reviews as {string}")
    public void searchAndSelectReviews(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String review = SessionData.getDataTbVal(dataTable, row, "Name");
            allReviewsSteps.searchAndSelectReviews(review);
        }

    }

    @And("edit reviews as {string}")
    public void editReviewsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String rating = SessionData.getDataTbVal(dataTable, row, "Rating");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String reviewType = SessionData.getDataTbVal(dataTable, row, "Review type");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            allReviewsSteps.editName(name);
            allReviewsSteps.editRating(rating);
            allReviewsSteps.editTitle(title);
            allReviewsSteps.editBody(body);
            allReviewsSteps.editReviewType(reviewType);
            if (reviewType.equals("Product review")) {
                allReviewsSteps.editProduct(product);
            }
            allReviewsSteps.clickBtnSave();
        }
    }

    @And("verify change review on Product page as {string}")
    public void verifyChangeReviewOnProductPageAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String reviewType = SessionData.getDataTbVal(dataTable, row, "Review type");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String rating = SessionData.getDataTbVal(dataTable, row, "Rating");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");

            reviewOnSFSteps.waitForLoadApps("Reviews");
            reviewOnSFSteps.clickTabTypeReview(reviewType);
            reviewOnSFSteps.verifyYourName(title, name);
            reviewOnSFSteps.verifyContent(title, body);
            reviewOnSFSteps.verifyRating(title, rating);
        }
    }

    @When("write review by API")
    public void writeReviewByAPI(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int rating = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Rating"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String reviewBody = SessionData.getDataTbVal(dataTable, row, "Review");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String name = SessionData.getDataTbVal(dataTable, row, "Your name");
            String mail = SessionData.getDataTbVal(dataTable, row, "Your email");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String accessToken = myCampaignSteps.getAccessTokenShopBase();
            if (title.equals("@AUTO@")) {
                String curentTime = DateTimeUtil.getTodayByFormat("dd/MM/yyyy");
                title = "Review " + curentTime;
            }
            long idProduct = 0;
            if (!product.isEmpty()) {
                idProduct = productSteps.getProductIDByName(product, accessToken);
            }
            reviewOnSFSteps.writeReviewAPI(rating, title, reviewBody, image, name, mail, idProduct);
        }
    }

    @When("click button write review")
    public void clickButtonWriteReview() {
        reviewOnSFSteps.clickBtnWriteAReview();
    }

    @When("^write reviews as \"([^\"]*)\"$")
    public void writeAReview(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rating = SessionData.getDataTbVal(dataTable, row, "Rating");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String review = SessionData.getDataTbVal(dataTable, row, "Review");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String yourName = SessionData.getDataTbVal(dataTable, row, "Your name");
            String yourEmail = SessionData.getDataTbVal(dataTable, row, "Your email");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");

            reviewOnSFSteps.waitForLoadApps("Reviews");
            reviewOnSFSteps.clickBtnWriteAReview();
            reviewOnSFSteps.chooseRating(rating);
            String curentTime = DateTimeUtil.getTodayByFormat("dd/MM/yyyy");
            if (title.equals("@AUTO@")) {
                title = "Review " + curentTime;
            }
            reviewOnSFSteps.inputTitle(title);
            reviewOnSFSteps.inputReview(review);
            if(!image.isEmpty()) {
                reviewOnSFSteps.uploadImage(image);
            }
            reviewOnSFSteps.inputYourName(yourName);
            reviewOnSFSteps.inputYourEmail(yourEmail);
            reviewOnSFSteps.checkType(type);
            if (!result.equals("image error")) {
                reviewOnSFSteps.clickBtnSubmit();
                reviewOnSFSteps.waitABit(5000);
            }
            if (result.equals("Success")) {
                reviewOnSFSteps.verifyThankYouPopupShow(msg);
                reviewOnSFSteps.closeThankYouPopup();
            } else {
                reviewOnSFSteps.verifyErrorMessage(msg);
            }
        }
    }

    @And("verify review deleted on SF")
    public void verifyReviewDeletedOnSF() {

        reviewOnSFSteps.waitForLoadApps("Reviews");
        reviewOnSFSteps.verifyReviewDeleted();
    }

    @When("{string} review in current page")
    public void reviewInCurrentPage(String action) {
        allReviewsSteps.chooseAllReviewInPage();
        allReviewsSteps.clickBtnAction();
        allReviewsSteps.selectAction(action);
    }

    @Then("verify content of file downloaded")
    public void verifyContentOfFileDownloaded() throws IOException, CsvValidationException, ParseException {
        List<List<String>> actualRessult = allReviewsSteps.readListReviewInExportFile();
        List<List<String>> expectedRessult = allReviewsSteps.getListReviewInCurrentPage();
        allReviewsSteps.verifyListReview(actualRessult, expectedRessult);
    }

    @When("{string} all review")
    public void exportAllReview(String action) {
        allReviewsSteps.selectAllReviews();
        allReviewsSteps.selectAction(action);
    }

    @Then("verify send mail to merchant")
    public void verifySendMailToMerchant(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String userName = SessionData.getDataTbVal(dataTable, row, "User name");
            String fileDetail = SessionData.getDataTbVal(dataTable, row, "File detail");
            notificationsSteps.verifySendMailToMerchant(title, message, userName, fileDetail);
        }

    }

    @And("wait for app \"([^\"]*)\" load$")
    public void waitToLoadApps(String appName){
        reviewOnSFSteps.waitForLoadApps(appName);
    }

    @Given("^user open dashboard$")
    public void openDashboard(){
        allReviewsSteps.openDashboard();
    }

}
