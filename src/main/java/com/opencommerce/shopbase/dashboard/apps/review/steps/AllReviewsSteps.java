package com.opencommerce.shopbase.dashboard.apps.review.steps;

import com.opencommerce.shopbase.dashboard.apps.review.pages.AllreviewsPage;
import com.opencsv.exceptions.CsvValidationException;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AllReviewsSteps extends ScenarioSteps {
    AllreviewsPage allreviewsPage;

    @Step
    public void selectAllReviews() {
        allreviewsPage.selectAllReviews();
    }

    @Step
    public void clickBtnAction() {
        allreviewsPage.clickBtnAction();
    }

    @Step
    public void selectDelete() {
        allreviewsPage.selectDelete();
    }

    @Step
    public void confirmDelete() {
        allreviewsPage.confirmDelete();
    }

    @Step
    public void verifyDeletedAllReviews() {

        allreviewsPage.verifyDeletedAllReviews();
    }

    @Step
    public void chooseProduct(String product) {
        allreviewsPage.chooseProduct(product);
    }

    @Step
    public void enterURL(String urls) {
        allreviewsPage.enterURL(urls);
    }

    @Step
    public void clickBtnImport(int index) {
        allreviewsPage.clickBtnImport(index);
    }

    @Step
    public void clickBtnContinue() {
        allreviewsPage.clickBtn("Continue");
        allreviewsPage.waitForEverythingComplete();
    }

    @Step
    public void setupStar(String star) {
        allreviewsPage.chooseStar(star);
    }

    @Step
    public void setupMaxNumber(String maxNumber) {
        allreviewsPage.inputMaxNumber(maxNumber);
    }

    @Step
    public void setupCountry(String country) {
        allreviewsPage.chooseCountry(country);
    }

    @Step
    public void checkKeepOriginalDate() {
        allreviewsPage.checkKeepOriginalDate();
    }

    @Step
    public void clickBtnGetReviews() {
        int i = 1;
        while (i < 5 && !allreviewsPage.showBtnCheckNow()) {
            allreviewsPage.clickBtnGetReviews();
            allreviewsPage.waitForEverythingComplete();
            i++;
        }
    }

    @Step
    public void confirmGetReview() {
        allreviewsPage.clickBtnCheckNow();
        allreviewsPage.refreshPage();
    }

    @Step
    public void checkAutoPublish(Boolean autoPublish) {
        allreviewsPage.checkAutoPublish(autoPublish);
    }

    @Step
    public void selectMinimumStar(String minimumStar) {
        allreviewsPage.selectMinimumStar(minimumStar);
    }

    @Step
    public void inputKeywordBlock(String keywordBlock) {
        allreviewsPage.inputKeywordBlock(keywordBlock);
    }

    @Step
    public void clickSettingsOnMenu() {
        allreviewsPage.clickSettingsOnMenu();
    }

    @Step
    public void clickBtnSavechanges() {
        allreviewsPage.clickBtn("Save changes");
    }

    @Step
    public void verifyContent(String title, String content) {
        allreviewsPage.verifyContent(title, content);
    }

    @Step
    public void verifyStatus(String title, String status) {
        allreviewsPage.verifyStatus(title, status);
    }

    @Step
    public void verifyYourName(String title, String yourName) {
        allreviewsPage.verifyYourName(title, yourName);
    }

    @Step
    public void searchAndSelectReviews(String review) {
        allreviewsPage.inputKeySearchAndEnter(review);
        allreviewsPage.clickChooseReview(review);
    }

    @Step
    public void editName(String name) {
        allreviewsPage.editTextField("Name", name);
    }

    @Step
    public void editRating(String rating) {
        allreviewsPage.editRating(rating);
    }

    @Step
    public void editTitle(String title) {
        allreviewsPage.editTextField("Title", title);
    }

    @Step
    public void editBody(String body) {
        allreviewsPage.editTextField("Body", body);
    }

    @Step
    public void editReviewType(String reviewType) {
        allreviewsPage.editReviewType(reviewType);
    }

    @Step
    public void editProduct(String product) {
        allreviewsPage.editProduct(product);
    }

    @Step
    public void verifyMessage(String msg) {
        if (msg.equals("Success")) {
            allreviewsPage.verifyAliExpressFilterPopupDislay();
        } else allreviewsPage.verifyErrorMessage(msg);
    }

    @Step
    public void chooseFileCSV(String fileName) {
        allreviewsPage.chooseFileCSV(fileName);
    }

    @Step
    public void chooseOverwrite() {
        allreviewsPage.chooseOverwrite();
    }

    @Step
    public void clickImport() {
        allreviewsPage.clickBtn("Import");
    }

    @Step
    public void clickBtnCheckNow() {
        allreviewsPage.clickBtn("Check now");
        allreviewsPage.refreshPage();
    }

    @Step
    public void clickBtnFilter() {
        allreviewsPage.clickBtnFilter();
    }

    @Step
    public void chooseOption(String option) {
        allreviewsPage.chooseOption(option);
    }

    @Step
    public void chooseValue(String value) {
        allreviewsPage.chooseValue(value);
    }

    @Step
    public void selectValue(String option, String value) {
        allreviewsPage.selectValue(option, value);
    }

    @Step
    public void clickDoneButton() {
        allreviewsPage.clickBtn("Done");
    }

    @Step
    public void verifyReviewShow(String reviewShow, boolean isShow) {
        allreviewsPage.verifyReviewShow(reviewShow, isShow);
    }

    @Step
    public void searchAndCheckReviews(String review) {
        allreviewsPage.inputKeySearchAndEnter(review);
        allreviewsPage.checkChooseReview(review);
    }

    @Step
    public void selectAction(String action) {
        allreviewsPage.selectAction(action);
    }

    @Step
    public void deleteAllProductGroup() {
        int countProductGroup = allreviewsPage.countProductGroup();
        for(int i = 0; i < countProductGroup; i++){
            allreviewsPage.selectFirstProductGroup();
            allreviewsPage.deleteSelectedProductGroup();
        }
    }

    @Step
    public void verifyGroupsNumberEquals0(int groupNumber) {
        allreviewsPage.verifyGroupsNumberEquals0(groupNumber);
    }

    @Step
    public void clickBtnAddProductGroup() {
        allreviewsPage.clickBtnAddProductGroup();
        allreviewsPage.waitForPageLoad();
    }

    @Step
    public void inputInforProductGroup(String title, String groupType, String value, boolean groupStatus) {
        allreviewsPage.inputTextField("Title", title);
        allreviewsPage.selectGroupType(groupType);
        allreviewsPage.inputValueGroup(value);
        allreviewsPage.checkActivate(groupStatus);
    }

    @Step
    public void clickBtnSave() {
        allreviewsPage.clickBtnSave();
    }

    @Step
    public void editGroup(String nameGroup, String title, String groupBy, String value) {
        allreviewsPage.chooseGroup(nameGroup);
        allreviewsPage.waitForPageLoad();
        allreviewsPage.editTextField("Title", title);
        allreviewsPage.editGroupBy(groupBy);
        allreviewsPage.inputValueGroup(value);
    }

    @Step
    public void checkChooseGroup(String groupName) {
        allreviewsPage.checkChooseGroup(groupName);
    }

    @Step
    public void actionGroup(String action) {
        allreviewsPage.clickBtnAction();
        allreviewsPage.selectAction(action);
    }

    @Step
    public List<List<String>> readListReviewInCSV(String fileName) throws IOException, CsvValidationException, ParseException {
        String filePath = LoadObject.getFilePath(fileName);
        List<List<String>> listReviewInCSV = SessionData.loadDataTableByCSV(filePath);
        List<List<String>> listReviewCSV = new ArrayList<>();
        for (int row : SessionData.getDataTbRowsNoHeader(listReviewInCSV).keySet()) {
            String nameOfCustomer = SessionData.getDataTbVal(listReviewInCSV, row, "name_of_customer");
            String title = SessionData.getDataTbVal(listReviewInCSV, row, "title");
            String rating = SessionData.getDataTbVal(listReviewInCSV, row, "rating");
            String review = SessionData.getDataTbVal(listReviewInCSV, row, "review");
            String dateOfReview = SessionData.getDataTbVal(listReviewInCSV, row, "date_of_review");
            String dateReview = convertDateFormat(dateOfReview, "yyy/MM/dd", "MM/dd/yyyy");
            listReviewCSV.add(asList(nameOfCustomer, title, rating, review, dateReview));
        }
        return listReviewCSV;
    }

    @Step
    public String convertDateFormat(String dateValue, String pattern1, String pattern2) throws ParseException {
        String sourceDate = dateValue.substring(0, 10).replace("-", "/");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern1);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(pattern2);
        Date date = dateFormat1.parse(sourceDate);
        dateValue = dateFormat2.format(date);
        return dateValue;
    }

    @Step
    public List<List<String>> getListReviewOnDashboard() {
        List<List<String>> listReview = new ArrayList<>();
        int lineItem = allreviewsPage.countReviews();
        for (int i = 1; i <= lineItem; i++) {
            List<String> review;
            review = allreviewsPage.getReviewInformation(i);
            listReview.add(review);
        }
        return listReview;
    }

    @Step
    public void verifyListReview(List<List<String>> actualRessult, List<List<String>> expectedRessult) {
        for (int i = 0; i < actualRessult.size(); i++) {
            assertThat(actualRessult.get(i)).isEqualTo(expectedRessult.get(i));
        }
    }

    @Step
    public void saveChanges() {
        allreviewsPage.saveSetting();
    }

    @Step
    public int countReviews() {
        return allreviewsPage.countReviews();
    }

    @Step
    public void verifyMsgShow(String msg) {
//        allreviewsPage.verifyMsgShow(msg);
    }

    @Step
    public void checkNotification(boolean notification) {
        allreviewsPage.checkNotification(notification);
    }

    @Step
    public void inputEmail(String email) {
        allreviewsPage.inputEmail(email);
    }

    @Step
    public void checkSendNotificationBadReview(boolean sendNotificationBadReview) {
        allreviewsPage.checkSendNotificationBadReview(sendNotificationBadReview);
    }

    @Step
    public void chooseAllReviewInPage() {
        allreviewsPage.chooseAllReviewInPage();
    }

    @Step
    public String openFileDownloaded() {
        return allreviewsPage.getPathFileDownloaded();

    }

    @Step
    public List<List<String>> readListReviewInExportFile() throws IOException, CsvValidationException, ParseException {
        String filePath = openFileDownloaded();
        waitABit(5000);
        List<List<String>> listReviewInExportFile = SessionData.loadDataTableByCSV(filePath);
        List<List<String>> reviewInExportFile = new ArrayList<>();
        for (int row : SessionData.getDataTbRowsNoHeader(listReviewInExportFile).keySet()) {
            String nameOfCustomer = SessionData.getDataTbVal(listReviewInExportFile, row, "name_of_customer");
            String title = SessionData.getDataTbVal(listReviewInExportFile, row, "title");
            String rating = SessionData.getDataTbVal(listReviewInExportFile, row, "rating");
            String review = SessionData.getDataTbVal(listReviewInExportFile, row, "review");
            String dateOfReview = SessionData.getDataTbVal(listReviewInExportFile, row, "date_of_review");
            String dateReview = convertDateFormat(dateOfReview, "yyy/MM/dd", "MM/dd/yyyy");
            reviewInExportFile.add(asList(nameOfCustomer, title, rating, review, dateReview));
        }
        return reviewInExportFile;
    }

    @Step
    public List<List<String>> getListReviewInCurrentPage() {
        List<List<String>> listReview = new ArrayList<>();
        int reviewNumber = countReviews();
        for (int i = 1; i <= reviewNumber; i++) {
            List<String> review;
            review = allreviewsPage.getReviewInformation(i);
            listReview.add(review);
        }
        return listReview;
    }

    @Step
    public void verifyRating(String title, int rating) {
        allreviewsPage.verifyRating(title, rating);
    }

    public static String shop = LoadObject.getProperty("shop");

    public void openDashboard() {
        allreviewsPage.navigateToUrl("https://" + shop + "/admin");
    }

    public String genEmail(String mail) {
        return ("shopbase" + System.currentTimeMillis() + mail);
    }
}

