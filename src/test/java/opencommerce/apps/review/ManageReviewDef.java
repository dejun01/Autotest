package opencommerce.apps.review;

import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class ManageReviewDef {
    @Steps
    AllReviewsSteps allReviewsSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ReviewOnSFSteps reviewOnSFSteps;

    @And("import reviews from a spreadsheet into Product Reviews with cvs file is {string}")
    public void importReviewsFromASpreadsheetIntoProductReviewsWithCvsFileIs(String fileName) {
        allReviewsSteps.clickBtnImport(2);
        allReviewsSteps.chooseFileCSV(fileName);
        allReviewsSteps.chooseOverwrite();
        allReviewsSteps.clickImport();
        allReviewsSteps.clickBtnCheckNow();
    }

    @And("verify review import on manage reviews in cvs file is {string}")
    public void verifyReviewImportOnManageReviewsInCvsFileIs(String fileName) throws IOException, CsvValidationException, ParseException {
        List<List<String>> expectedRessult = allReviewsSteps.readListReviewInCSV(fileName);
        List<List<String>> actualRessult = allReviewsSteps.getListReviewOnDashboard();
        Collections.reverse(actualRessult);
        allReviewsSteps.verifyListReview(actualRessult, expectedRessult);
    }

    @And("choose filter reviews in dashoard as \"([^\"]*)\"$")
    public void checkFilterReviewsInDashoardAs(String dataKey, List<List<String>> dataTable) {
        allReviewsSteps.clickBtnFilter();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Option");
            String value = SessionData.getDataTbVal(dataTable, row, "value");
            allReviewsSteps.chooseOption(option);
            if (option.equals("Status") | option.equals("Photos") | option.equals("Featured") | option.equals("Review type")) {
                allReviewsSteps.chooseValue(value);
            }
            if (option.equals("Rating") | option.equals("Source")) {
                allReviewsSteps.selectValue(option, value);
            }
            if (option.equals("Product")) {
                allReviewsSteps.chooseProduct(value);
            }

        }
    }

    @And("click done button")
    public void clickDoneButton() {
        allReviewsSteps.clickDoneButton();
    }

    @Then("verify list review after filter as \"([^\"]*)\"$")
    public void verifyListReviewAfterFilterAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String listReviewShow = SessionData.getDataTbVal(dataTable, row, "Review show");
            String listReviewDontShow = SessionData.getDataTbVal(dataTable, row, "Review don't show");
            if (!listReviewShow.equals("")) {
                String[] reviewShow = listReviewShow.split(",");
                for (String s : reviewShow) {
                    allReviewsSteps.verifyReviewShow(s, true);
                }
            }
            if (!listReviewDontShow.equals("")) {
                String[] reviewDontShow = listReviewDontShow.split(",");
                for (String s : reviewDontShow) {
                    allReviewsSteps.verifyReviewShow(s, false);
                }
            }
        }

    }

    @And("action to reviews as \"([^\"]*)\"$")
    public void actionToReviewsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String review = SessionData.getDataTbVal(dataTable, row, "Review");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            allReviewsSteps.searchAndCheckReviews(review);
            allReviewsSteps.clickBtnAction();
            allReviewsSteps.selectAction(action);
            if (action.equals("Delete")) {
                allReviewsSteps.confirmDelete();
            }
        }
    }

    @Then("verify review on product page as {string}")
    public void verifyReviewOnProductPageAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String review = SessionData.getDataTbVal(dataTable, row, "Review");
            String showReview = SessionData.getDataTbVal(dataTable, row, "Show review");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            boolean isShowReview = Boolean.parseBoolean(showReview);
            String featured = SessionData.getDataTbVal(dataTable, row, "Featured");
            boolean isFeatured = Boolean.parseBoolean(featured);

            productSteps.searchAndSelectProduct(product);
            reviewOnSFSteps.verifyReviewShow(type, review, isShowReview);
            reviewOnSFSteps.verifyReviewFeatured(review, isFeatured);
        }
    }

    @When("delete all product group")
    public void deleteAllProductGroup() {
        allReviewsSteps.deleteAllProductGroup();
    }

    @Then("verify groups number equals {int}")
    public void verifyGroupsNumberEquals0(int groupNumber) {
        allReviewsSteps.verifyGroupsNumberEquals0(groupNumber);
    }

    @When("add product group")
    public void addProductGroup(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String groupType = SessionData.getDataTbVal(dataTable, row, "Group by");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            String active = SessionData.getDataTbVal(dataTable, row, "Activate");
            boolean groupStatus = Boolean.parseBoolean(active);
            allReviewsSteps.clickBtnAddProductGroup();
            allReviewsSteps.inputInforProductGroup(title, groupType, value, groupStatus);
            allReviewsSteps.clickBtnSave();
        }
    }

    @Then("check review with groups of products")
    public void checkReviewWithGroupsOfProducts(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String product1 = SessionData.getDataTbVal(dataTable, row, "Product 1");
            String product2 = SessionData.getDataTbVal(dataTable, row, "Product 2");
            String listReviewShow = SessionData.getDataTbVal(dataTable, row, "Review show");
            String[] reviewShow = listReviewShow.split(",");
            productSteps.searchAndSelectProduct(product1);
            for (String s : reviewShow) {
                reviewOnSFSteps.verifyReviewShow("Product reviews", s, true);
            }
            productSteps.searchAndSelectProduct(product2);
            for (String s : reviewShow) {
                reviewOnSFSteps.verifyReviewShow("Product reviews", s, true);
            }

        }
    }

    @When("edit product group")
    public void editProductGroup(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String nameGroup = SessionData.getDataTbVal(dataTable, row, "Name group");
            String groupBy = SessionData.getDataTbVal(dataTable, row, "Group by");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String value = SessionData.getDataTbVal(dataTable, row, "Value");
            allReviewsSteps.editGroup(nameGroup, title, groupBy, value);
            allReviewsSteps.clickBtnSave();
        }
    }

    @When("action product group {string}")
    public void actionProductGroup(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String groupName = SessionData.getDataTbVal(dataTable, row, "Group name");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            allReviewsSteps.checkChooseGroup(groupName);
            allReviewsSteps.actionGroup(action);
        }
    }

    @Then("verify review with product group on storefront {string}")
    public void verifyReviewWithProductGroupOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product1 = SessionData.getDataTbVal(dataTable, row, "Product 1");
            String product2 = SessionData.getDataTbVal(dataTable, row, "Product 2");
            String listReviewOfProduct1 = SessionData.getDataTbVal(dataTable, row, "Review of product 1");
            boolean reviewShowOnProduct2 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Review show on product page 2"));
            productSteps.searchAndSelectProduct(product1);
            String[] reviewOfProduct1 = listReviewOfProduct1.split(",");
            for (String s : reviewOfProduct1) {
                reviewOnSFSteps.verifyReviewShow("Product reviews", s, true);
            }
            productSteps.searchAndSelectProduct(product2);
            for (String s : reviewOfProduct1) {
                reviewOnSFSteps.verifyReviewShow("Product reviews", s, reviewShowOnProduct2);
            }
        }

    }

    @And("import reviews from CSV file invalid is {string}")
    public void importReviewsFromCSVFileInvalidIs(String fileName) {
        allReviewsSteps.clickBtnImport(2);
        allReviewsSteps.chooseFileCSV(fileName);
        allReviewsSteps.verifyMsgShow("Maximum file size is 20 MB");
    }

    @And("verify review show on Dashboard")
    public void verifyReviewShowOnDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String review = SessionData.getDataTbVal(dataTable, row, "Review");
            boolean reviewIsShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            allReviewsSteps.verifyReviewShow(review, reviewIsShow);
        }
    }


}

