package com.opencommerce.shopbase.storefront.steps.apps.review;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencommerce.shopbase.storefront.pages.apps.review.ReviewOnSFPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;


public class ReviewOnSFSteps extends ScenarioSteps {

    ReviewOnSFPage reviewOnSFPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void verifyReviewShownOnSF(boolean isDisplayReview) {
        reviewOnSFPage.verifyReviewShownOnSF(isDisplayReview);
    }

    @Step
    public void verifyDisplayAuthorName(Boolean displayAuthorName) {
        reviewOnSFPage.verifyDisplayAuthorName(displayAuthorName);
    }

    @Step
    public void verifyDisplayDatePosted(boolean displayDatePosted) {
        reviewOnSFPage.verifyDisplayDatePosted(displayDatePosted);
    }

    @Step
    public void verifyDisplayAuthorAvatar(boolean displayAuthorAvatar) {
        reviewOnSFPage.verifyDisplayAuthorAvatar(displayAuthorAvatar);
    }

    @Step
    public void verifyDisplayRatings(boolean displayRatings) {
        reviewOnSFPage.verifyDisplayRatings(displayRatings);
    }

    @Step
    public void verifyFont(String font) {
        reviewOnSFPage.verifyFontContent(font);
    }

    @Step
    public void verifyStyleTitle(String styleTitle) {
        reviewOnSFPage.verifyStyleTitle(styleTitle);
    }

    @Step
    public void verifyBackground(String background) {
        reviewOnSFPage.verifyBackground(background);
    }

    @Step
    public void verifyStarColor(String starColor) {
        reviewOnSFPage.verifyStarColor(starColor);
    }

    @Step
    public void verifyHeading(String heading) {
        reviewOnSFPage.verifyHeading(heading);
    }

    @Step
    public void verifyReviewNumber(int reviewNumber) {
        reviewOnSFPage.verifyReviewNumber(reviewNumber);
    }

    @Step
    public void verifyShowLinkAllReview(Boolean showLink) {
        reviewOnSFPage.verifyShowLinkAllReview(showLink);
    }

    @Step
    public void verifyReviewLayout(String layout) {
        reviewOnSFPage.verifyReviewLayout(layout);
    }

    @Step
    public void verifyPrimaryColor(String primaryColor) {
        reviewOnSFPage.verifyPrimaryColor(primaryColor);
    }

    @Step
    public void verifyDateFormat(String dateFormat) {
        reviewOnSFPage.verifyDateFormat(dateFormat);
    }

    @Step
    public void veryfyDateFormatForLayoutMasory(String dateFormat) {
        reviewOnSFPage.veryfyDateFormatForLayoutMasory(dateFormat);
    }

    @Step
    public void verifyImagePosition(String layout, String imagePosition) {
        if (layout.equals("List")) {
            reviewOnSFPage.verifyClassImagePosition(imagePosition);
        }
        if (layout.equals("Mansory")) {
            reviewOnSFPage.verifyStyleImagePosition(imagePosition);
        }

    }

    @Step
    public void verifyReviewPerPage(int reviewsPerPage) {
        reviewOnSFPage.verifyReviewPerPage(reviewsPerPage);
    }

    @Step
    public void verifyReviewLogic(String reviewLogic) {
        reviewOnSFPage.verifyReviewLogic(reviewLogic);
    }

    @Step
    public void clickBtnWriteAReview() {
        reviewOnSFPage.clickBtnWriteAReview();
    }

    @Step
    public void chooseRating(String rating) {
        reviewOnSFPage.chooseRating(rating);
    }

    @Step
    public void inputTitle(String title) {
        reviewOnSFPage.inputTitle(title);
    }

    @Step
    public void inputReview(String review) {
        reviewOnSFPage.inputReview(review);
    }

    @Step
    public void uploadImage(String filePath) {
        reviewOnSFPage.uploadImgReview(filePath);
    }

    @Step
    public void inputYourName(String yourName) {
        reviewOnSFPage.inputYourName(yourName);
    }

    @Step
    public void inputYourEmail(String yourEmail) {
        reviewOnSFPage.inputYourEmail(yourEmail);
    }

    @Step
    public void checkType(String type) {
        reviewOnSFPage.checkType(type);
    }

    @Step
    public void clickBtnSubmit() {
        reviewOnSFPage.clickBtnSubmit();
    }

    @Step
    public void clickTabTypeReview(String type) {
        reviewOnSFPage.clickTabTypeReview(type);
    }

    @Step
    public void verifyReviewShow(String type, String title, Boolean showReview) {
        waitForLoadApps("Reviews");
        reviewOnSFPage.clickTabTypeReview(type);
        try {
            reviewOnSFPage.verifyReviewShow(title, showReview);
        }catch (Exception e){
            reviewOnSFPage.refreshPage();
            waitForLoadApps("Reviews");
            reviewOnSFPage.clickTabTypeReview(type);
            reviewOnSFPage.verifyReviewShow(title, showReview);
        }
    }

    @Step
    public void verifyRating(String title, String rating) {
        reviewOnSFPage.verifyRating(title, rating);
    }

    @Step
    public void verifyContent(String title, String content) {
        reviewOnSFPage.verifyContent(title, content);
    }

    @Step
    public void verifyYourName(String title, String yourName) {
        reviewOnSFPage.verifyYourName(title, yourName);
    }

    @Step
    public void verifyReviewFeatured(String review, boolean featured) {
        reviewOnSFPage.selectFilterFeatured();
        reviewOnSFPage.verifyReviewShow(review, featured);

    }

    @Step
    public void verifyMinimumStar(int reviewNumber, int minimumStar) {
        reviewOnSFPage.verifyMinimumStar(reviewNumber, minimumStar);
    }

    @Step
    public void verifyDisplayReviewTitle(boolean displayReviewTitle) {
        reviewOnSFPage.verifyDisplayReviewTitle(displayReviewTitle);
    }

    @Step
    public void verifyDisplayReviewBody(boolean displayReivewBody) {
        reviewOnSFPage.verifyDisplayReviewBody(displayReivewBody);
    }

    @Step
    public void verifyErrorMessage(String msg) {
        reviewOnSFPage.verifyMessage(msg);
    }

    @Step
    public void verifyThankYouPopupShow(String msg) {
        reviewOnSFPage.verifyThankYouPopupShow(msg);
    }

    @Step
    public void closeThankYouPopup() {
        reviewOnSFPage.closeThankYouPopup();
    }

    public void writeReviewAPI(int rating, String title, String review, String image, String yourName, String yourEmail, long IdProduct) {
        String api = "https://" + shop + "/api/review.json";
        JsonObject requestParams = requestBodyReview(rating, title, review, image, yourName, yourEmail, IdProduct);
        reviewOnSFPage.postAPIReview(api, requestParams);
    }

    private JsonObject requestBodyReview(int rating, String title, String reviewBody, String image, String name, String email, long IdProduct) {
        JsonObject customReview = new JsonObject();
        JsonArray images = new JsonArray();
        String[] imgs = image.split(",");
        for (String img : imgs) {
            images.add(img);
        }
        customReview.addProperty("content", reviewBody);
        customReview.addProperty("email", email);
        customReview.add("images", images);
        customReview.addProperty("name", name);
        customReview.addProperty("product_id", IdProduct);
        customReview.addProperty("rating", rating);
        customReview.addProperty("title", title);
        return customReview;
    }

    public void verifyReviewDeleted() {
        reviewOnSFPage.verifyReviewDeleted();
    }

    public void verifyShowStoreReview(boolean showReview) {
        reviewOnSFPage.verifyShowStoreReview(showReview);
    }

    public void waitForLoadApps(String appName){
        reviewOnSFPage.waitForLoadApps(appName);
    }
}
