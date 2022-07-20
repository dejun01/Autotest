package com.opencommerce.shopbase.dashboard.apps.review.steps;

import com.opencommerce.shopbase.dashboard.apps.review.pages.CustomizationPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CustomizationSteps extends ScenarioSteps {
    CustomizationPage customizationPage;

    @Step
    public void selectFont(String font) {
        customizationPage.selectDdlWithLabel("Font", font);
    }

    @Step
    public void selectStyle(String style) {
        customizationPage.selectDdlWithLabel("Style", style);
    }

    @Step
    public void inputCarouselBackground(String carouselBackground) {
        customizationPage.inputStyleWithLabel("Carousel background", carouselBackground);
    }

    @Step
    public void inputWidgetBackground(String widgetBackground) {
        customizationPage.inputStyleWithLabel("Widget background", widgetBackground);
    }

    @Step
    public void inputPrimaryColor(String primarycolor) {
        customizationPage.inputStyleWithLabel("Primary color", primarycolor);
    }

    @Step
    public void inputStarcolor(String starcolor) {
        customizationPage.inputStyleWithLabel("Rating star color", starcolor);
    }

    @Step
    public void selectDatefomat(String dateFomat) {
        customizationPage.selectDdlWithLabel("Date format", dateFomat);
    }

    @Step
    public void checkDisplayAuthorName(Boolean displayAuthorName) {
        customizationPage.checkDisplayAuthorName(displayAuthorName);
    }

    @Step
    public void checkDisplayDatePosted(Boolean displayDatePosted) {
        customizationPage.checkDisplayDatePosted(displayDatePosted);
    }

    @Step
    public void checkDisplayAuthorAvatar(Boolean displayAuthorAvatar) {
        customizationPage.checkDisplayAuthorAvatar(displayAuthorAvatar);
    }

    @Step
    public void checkDisplayRatings(Boolean displayRatings) {
        customizationPage.checkDisplayRatings(displayRatings);
    }

    @Step
    public void selectWidgetLayout(String widgetLayout) {

        customizationPage.selectWidgetLayout(widgetLayout);
    }

    @Step
    public void selectImagePosition(String imageposition) {

        customizationPage.selectImagePosition(imageposition);
    }

    @Step
    public void inputReviewsperpage(String reviewsPerPage) {

        customizationPage.inputStyleWithLabel("Reviews per page", reviewsPerPage);
    }

    @Step
    public void selectDefaultReviewLogic(String defaultReviewLogic) {
        customizationPage.selectDefaultReviewLogic(defaultReviewLogic);
    }

    @Step
    public void selectReviewCardLayout(String reviewCardLayout) {
        customizationPage.selectReviewCardLayout(reviewCardLayout);
    }

    @Step
    public void inputNumberOfReviews(String numberOfReviews) {
        customizationPage.inputStyleWithLabel("Number of reviews", numberOfReviews);
    }

    @Step
    public void selectReviewLogic(String reviewLogic) {

        customizationPage.selectReviewLogic(reviewLogic);
    }

    @Step
    public void inputHeading(String heading) {

        customizationPage.inputStyleWithLabel("Heading (optional)", heading);
    }

    @Step
    public void checkDisplayCheckoutPage(boolean displayCheckoutPage) {
        customizationPage.checkLink("Continue displaying in Checkout page", displayCheckoutPage);
    }

    @Step
    public void checkLinkWidgets(boolean linkWidgets) {
        customizationPage.checkLink("Show link in Review Widget", linkWidgets);
    }

    @Step
    public void checkLinkCarousel(boolean linkCarousel) {
        customizationPage.checkLink("Show link in Review Carousel", linkCarousel);
    }

    @Step
    public void clickSaveChangeButton() {
        customizationPage.clickSaveChangeButton();
        customizationPage.waitForPageLoad();
    }

    @Step
    public void clickMenuOnApp(String menu) {
        customizationPage.clickMenuOnApp(menu);
    }

    public void checkDisplayTitle(Boolean displayReviewTitle) {
        customizationPage.checkDisplayTitle(displayReviewTitle);

    }

    public void checkDisplayBody(Boolean displayReviewBody) {
        customizationPage.checkDisplayBody(displayReviewBody);
    }

    public void checkStoreReview(boolean storeReview) {
        customizationPage.checkLink("Show store reviews on storefront",storeReview);
    }
}
