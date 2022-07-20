package com.opencommerce.shopbase.storefront.pages.apps.review;

import com.google.gson.JsonObject;
import common.SBasePageObject;
import common.utilities.DateTimeUtil;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;


import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReviewOnSFPage extends SBasePageObject {
    public ReviewOnSFPage(WebDriver driver) {
        super(driver);
    }

    public void verifyReviewShownOnSF(boolean isDisplayReview) {
        scrollIntoElementView("//*[@data-id='review_widget' or contains(@data-id,'featured_review_carousel') or contains(@class,'rv-widget--carousel')or contains(@class,'all-reviews-page')]");
        String xpath = "//*[contains(@class,'rv-widget rv-widget--widget-card') or contains(@class,'rv-widget rv-widget--carousel')]//div[contains(@class,'rv-widget__review-listing') or contains(@class,'rv-widget__listing-body')]";
        int i = 0;
        while (i < 10 && !isElementExist(xpath)) {
            waitABit(5000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        verifyElementPresent(xpath, isDisplayReview);
    }

    public void verifyDisplayAuthorName(Boolean displayAuthorName) {
        String xpath = "//*[contains(@class,'rv-widget__author-info--name') or @class='rv-widget__author']";
        verifyElementPresent(xpath, displayAuthorName);
    }

    public void verifyDisplayDatePosted(boolean displayDatePosted) {
        String xpath = "//*[@class='rv-widget__author-info--date' or @class='rv-widget__date']";
        verifyElementPresent(xpath, displayDatePosted);
    }

    public void verifyDisplayAuthorAvatar(boolean displayAuthorAvatar) {
        String xpath = "//div[contains(@class,'rv-widget__review-author rv') or @class='rv-widget__avatar']//img | //div[contains(@class,'rv-widget__review-author')]//img";
        verifyElementPresent(xpath, displayAuthorAvatar);
    }

    public void verifyDisplayRatings(boolean displayRatings) {
        String xpath = "//div[contains(@class,'rv-flex rv-a-item-center rv-widget__body-star') or @class='app-mr8 app-flex']";
        verifyElementPresent(xpath, displayRatings);
    }

    public void verifyFontContent(String font) {
        String xpathTitle = "//div[@class='rv-widget__body-heading' or @class='rv-hidden-xs rv-widget__post-heading' or @class ='rv-widget__post-heading']";
        String xpathBody = "//div[@class='rv-widget__body-post' or @class='rv-widget__post-content']";
        verifyFont(font, xpathTitle);
        verifyFont(font, xpathBody);
    }

    public void verifyPrimaryColor(String primaryColor) {
        String xpathLabel = "//span[text()='Write a review']";
        verifyColor(xpathLabel, "color", primaryColor);
        String xpathRating = "//div[@class='app-flex app-items-center rv-widget__header-summary']//progress";
        String ratingValue = getAttributeValue("//div[@class='app-flex app-items-center rv-widget__header-summary']//progress", "value");
        if (!ratingValue.equals("0")) {
            verifyColor(xpathRating, "color", primaryColor);
        }
    }

    public void verifyFont(String font, String xpath) {
        String fontValue = findBy(xpath).getCssValue("font-family");
        assertThat(fontValue).contains(font);
    }


    public void verifyBackground(String background) {
        String xpath = "//div[contains(@class,'rv-flex-1 rv-widget__body-content rv') or @class='rv-widget__review-listing rv-widget__review-listing-masonry-new' or @class ='rv-flex rv-direction-column rv-widget__post rv-widget__post-arrow' or @class='rv-widget__post rv-hidden-xs rv-widget__post-arrow'] | //div[contains(@class,'rv-flex-1 rv-widget__body-content')]";
        verifyCssValueByElement(xpath, "background-color", background);
    }

    public void verifyStyleTitle(String styleTitle) {
        String xpathTitle = "//div[@class='rv-widget__carousel-heading' or @class='rv-widget__overall-text']";
        verifyColor(xpathTitle, "color", styleTitle);
    }

    public void verifyStarColor(String starColor) {
        verifyColor("//div[@class='review-icon-symbols']//*[@id='review-icons-star']", "fill", starColor);
    }

    public void verifyHeading(String heading) {
        String headingValue = getTextValue("//div[@class='rv-widget__carousel-heading']");
        assertThat(headingValue).isEqualTo(heading);
    }

    public void verifyReviewNumber(int reviewNumber) {
        String xpath = "//div[@class='rv-widget__post rv-widget__post-arrow' or @class='rv-widget__review-listing app-flex app-flex-wrap rv-widget__review-listing-new' or @class='rv-flex rv-direction-column rv-widget__listing-body']";
        scrollIntoElementView("//section[@data-id='review_widget' or contains(@data-id,'featured_review_carousel') or contains(@class,'rv-widget--carousel')]");
        String xpathShowMoreReviews = "//button[@class='rv-widget__btn']";
        int i = 1;
        while (countElementByXpath(xpath) != reviewNumber && i <= 5) {
            waitABit(5000);
            while (isElementExist(xpathShowMoreReviews)) {
                clickOnElement(xpathShowMoreReviews);
            }
            i++;
        }
        assertThat(countElementByXpath(xpath)).isEqualTo(reviewNumber);
    }

    public void verifyShowLinkAllReview(Boolean showLink) {
        verifyElementPresent("//*[@class='rv-widget__carousel-header-link' or @class='rv-widget__overall-store']", showLink);
    }

    public void verifyReviewLayout(String layout) {

        switch (layout) {
            case "Classic":
                verifyCssValueByElement("//div[@data-review='background-side']", "text-align", "left");
                break;
            case "Spotlight":
                verifyCssValueByElement("//div[@data-review='background-side']", "text-align", "center");
                break;
            default:
                String style = getAttributeValue("//div[contains(@class,'rv-widget__review-listing ')]", "class");
                assertThat(style).contains(layout.toLowerCase());
                break;
        }
    }

    public void verifyStyleImagePosition(String imagePosition) {
        String xpath = "//div[@class='app-flex app-items-center rv-widget__post-image']//div[@class='app-flex app-items-center app-justify-center rv-widget__post-image-container']";
        verifyElementPresent(xpath, imagePosition.equals("Above content"));
    }

    public void verifyReviewPerPage(int reviewsPerPage) {
        String xpath = "//div[@class='rv-widget__post' or @class='rv-widget__review-listing app-flex app-flex-wrap rv-widget__review-listing-new']";
        int reviewOnPage = countElementByXpath(xpath);
        int i = 0;
        while (i < 3 && reviewOnPage > reviewsPerPage) {
            waitABit(3000);
            refreshPage();
            i++;
        }
        assertThat(reviewOnPage).isBetween(0, reviewsPerPage);
    }

    public void verifyReviewLogic(String reviewLogic) {
        String xpath = "(//div[@class='rv-widget__post-content' or @class='rv-widget__body-post'])[1]";
        verifyElementText(xpath, reviewLogic);
    }

    public void chooseRating(String rating) {
        waitABit(2000);
        hoverOnElement("(//form[@id='rv_add_form']//div[@class='review-icon-symbols']//*[@id='review-icons-star'])[" + rating + "]");
        clickOnElement("(//form[@id='rv_add_form']//div[@class='review-icon-symbols']//*[@id='review-icons-star'])[" + rating + "]");
    }

    public void inputTitle(String title) {
        scrollToElement("//div[@class='rv-widget__group-field' and descendant::label[contains(text(),'Your email')]]//input");
        inputTextByJS("//div[@class='rv-widget__group-field' and descendant::label[contains(text(),'Title of review')]]//input", title);
    }

    public void inputReview(String review) {
        inputTextByJS("//div[@class='rv-widget__group-field']//textarea", review);
    }

    public void inputYourName(String yourName) {
        inputTextByJS("//div[@class='rv-widget__group-field' and descendant::label[contains(text(),'Your name')]]//input", yourName);
    }

    public void inputYourEmail(String yourEmail) {
        inputTextByJS("//div[@class='rv-widget__group-field' and descendant::label[contains(text(),'Your email')]]//input", yourEmail);
    }

    public void checkType(String type) {
        String xpathStatus = "//div[@class='app-kit-radio__container' and descendant::span[text()='" + type + "']]//input";
        String xpathClick = "//div[@class='rv-widget__radio rv-flex']//span[contains(text(),'" + type + "')]";
        scrollIntoElementView(xpathClick);
        verifyCheckedThenClick(xpathStatus, xpathClick, true);
    }

    public void uploadImgReview(String filePath) {
        String xpath = "//input[@id='rv_file_upload']";
        chooseAttachmentFile(xpath, filePath);
    }

    public void clickBtnSubmit() {
        scrollIntoElementView("//button[@class='rv-widget__btn rv-primary']");
        clickOnElement("//button[@class='rv-widget__btn rv-primary']");
    }

    public void verifyDateFormat(String dateFormat) {
        String CurentDate = DateTimeUtil.getTodayByFormat(dateFormat);
        String dateReview = getText("(//*[@class='rv-widget__author-info--date' or @class='rv-widget__date'])");
        assertThat(dateReview).isEqualTo(CurentDate);
    }

    public void veryfyDateFormatForLayoutMasory(String dateFormat) {
        String CurentDate = DateTimeUtil.getTodayByFormat(dateFormat);
        String dateReview = getTextByJS("(//div[contains(@class,'rv-widget__post')]//time[@class='rv-widget__date'])[1]");
        assertThat(dateReview).isEqualTo(CurentDate);
    }

    public void clickBtnWriteAReview() {
        scrollIntoElementView("//section[@data-id='review_widget']");
        String xpathBtn = "//button[normalize-space()='Write a review']";
        int i = 1;
        while (!isElementExist(xpathBtn) && i < 5) {
            waitABit(3000);
            refreshPage();
            i++;
        }
        clickOnElementByJs(xpathBtn);
    }

    public void clickTabTypeReview(String type) {
        scrollIntoElementView("//section[@data-id='review_widget']");
        clickOnElementByJs("//div[@class='rv-widget__review-type']//span[contains(text(),'" + type + "')]");
    }

    public void verifyReviewShow(String title, boolean showReview) {
        scrollIntoElementView("//section[@data-id='review_widget']");
        verifyElementPresent("//div[@class='rv-hidden-xs rv-widget__post-heading' and contains(text(),'" + title + "')]", showReview);
    }

    public void verifyRating(String title, String rating) {
        String xpathRating = "//div[@class='app-flex app-items-center rv-widget__post-star' and descendant::div[contains(text(),'" + title + "')]]";
        String starRating = getAttributeValue(xpathRating, "data-rating");
        assertThat(starRating).isEqualTo(rating);
    }

    public void verifyContent(String title, String content) {
        String xpath ="//div[@class='rv-flex rv-direction-column rv-widget__post rv-widget__post-arrow' and descendant::div[contains(text(),'" + title + "')]]//div[@class='rv-widget__post-content']";
        verifyElementText(xpath,content);
    }

    public void verifyYourName(String title, String yourName) {
        String xpathYourName = "//div[@class='rv-widget__review-listing app-flex app-flex-wrap rv-widget__review-listing-new' and descendant::div[contains(text(),'" + title + "')]]//div[@class='rv-widget__author']";
        verifyElementText(xpathYourName,yourName);
    }

    public void selectFilterFeatured() {
        scrollToElement("//*[contains(@class,'rv-widget rv-widget--widget-card')]");
        clickOnElement("//div[@class='app-select']");
        clickOnElement("//div[@class='app-select']//option[@value='is_starred']");
    }

    public void verifyMinimumStar(int reviewNumber, int minimumStar) {
        for (int i = 0; i < reviewNumber; i++) {
            int j = i + 1;
            String xpathDataRating = "(//div[@class='app-flex app-items-center rv-widget__post-star' or @class='app-flex app-items-center rv-hidden-xs rv-widget__post-star'])[" + j + "]";
            String rawRating = getAttributeValue(xpathDataRating, "data-rating");
            int rating = Integer.parseInt(rawRating);
            assert (rating >= minimumStar);
        }
    }

    public void verifyDisplayReviewTitle(boolean displayReviewTitle) {
        String xpathTitle = "//div[@class='rv-widget__body-heading' or @class ='rv-hidden-xs rv-widget__post-heading' or @class ='rv-widget__post-heading']";
        verifyElementPresent(xpathTitle, displayReviewTitle);

    }

    public void verifyDisplayReviewBody(boolean displayReivewBody) {
        String xpathBody = "//div[@class='rv-widget__post-content' or @class ='rv-widget__body-post']";
        verifyElementPresent(xpathBody, displayReivewBody);
    }

    public void verifyClassImagePosition(String imagePosition) {
        String xpath;
        if (imagePosition.equals("Above content")) {
            xpath = "//div[contains(@class,'rv-widget__post-image-above')]";
        }else {
            xpath = "//div[contains(@class,'rv-widget__post-image-below')]";
        }
        verifyElementPresent(xpath, true);
    }

    public void verifyMessage(String msg) {
        String messageAR = getText("//div[@class='rv-danger']").trim();
        assertThat(messageAR).isEqualTo(msg);
    }

    public void verifyThankYouPopupShow(String msg) {
        verifyElementText("//div[@class='rv-widget__thank-title']", msg);
    }

    public void closeThankYouPopup() {
        clickOnElement("//button[@class='app-modal__close']");
    }

    public void postAPIReview(String api, JsonObject requestParams) {
        Response response = given().header("Content-Type", "application/json").body(requestParams.toString()).post(api);
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
    }

    public void verifyReviewDeleted() {
        String xpath = "//div[@class='rv-widget__review-listing app-flex app-flex-wrap rv-widget__review-listing-new' or @class='rv-widget__review-listing rv-widget__review-listing-masonry-new']";
        int i = 0;
        while (isElementExist(xpath) && i < 5) {
            waitABit(3000);
            refreshPage();
            i++;
        }
        verifyElementPresent(xpath, false);
    }

    public void verifyShowStoreReview(boolean showReview) {
        scrollIntoElementView("//section[@data-id='review_widget']");
        verifyElementPresent("//div[@class='rv-widget__review-type']",showReview);
        verifyElementPresent("//div[@class='rv-widget__review-type']//span[contains(text(),'Store reviews')]",showReview);
    }

    public void waitForLoadApps(String appName){
        String xpath = "";
        if(appName.equalsIgnoreCase("Reviews") || appName.equalsIgnoreCase("Review") || appName.equalsIgnoreCase("Product Reviews")){
            xpath = "//div[contains(@class,'rv-widget')]";
        }
        if(appName.equalsIgnoreCase("Upsell") || appName.equalsIgnoreCase("Usell") || appName.equalsIgnoreCase("Boost Upsell")){
            xpath = "//div[contains(@class,'app-upsell')]";
        }
        if(appName.equalsIgnoreCase("Copt") || appName.equalsIgnoreCase("Boost Convert")){
            xpath = "//div[contains(@class,'copt')]";
        }

        waitForPageLoad();
        isElementExist(xpath, 20);
    }
}



