package opencommerce.apps.review;

import com.opencommerce.other.yopmail.review.steps.NotificationsSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.CustomizationSteps;
import com.opencommerce.shopbase.storefront.steps.apps.review.ReviewOnSFSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;


public class CustomizationDef {
    @Steps
    CustomizationSteps customizationSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ReviewOnSFSteps reviewOSFSteps;
    @Steps
    AllReviewsSteps allReviewsSteps;
    @Steps
    NotificationsSteps notificationsSteps;


    @When("^Customization review app on Dashboard \"([^\"]*)\"$")
    public void customization(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String carouselBackground = SessionData.getDataTbVal(dataTable, row, "Carousel background");
            String widgetBackground = SessionData.getDataTbVal(dataTable, row, "Widget background");
            String primaryColor = SessionData.getDataTbVal(dataTable, row, "Primary color");
            String starColor = SessionData.getDataTbVal(dataTable, row, "Star color");
            String dateFomat = SessionData.getDataTbVal(dataTable, row, "Date fomat");
            Boolean displayReviewTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display review title"));
            Boolean displayReviewBody = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display review body"));
            Boolean displayAuthorName = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display author name"));
            Boolean displayDatePosted = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display date posted"));
            Boolean displayAuthorAvatar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display author avatar"));
            Boolean displayRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display ratings"));
            String widgetLayout = SessionData.getDataTbVal(dataTable, row, "Widget Layout");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            String reviewsPerPage = SessionData.getDataTbVal(dataTable, row, "Reviews per page");
            String defaultReviewLogic = SessionData.getDataTbVal(dataTable, row, "Default review logic");
            String reviewCardLayout = SessionData.getDataTbVal(dataTable, row, "Review card layout");
            String numberOfReviews = SessionData.getDataTbVal(dataTable, row, "Number of reviews");
            String reviewLogic = SessionData.getDataTbVal(dataTable, row, "Review logic");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean displayCheckoutPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display review carousel in checkout page"));
            boolean linkWidgets = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Link widgets"));
            boolean linkCarousel = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Link carousel"));

            customizationSteps.selectFont(font);
            customizationSteps.selectStyle(style);
            customizationSteps.inputCarouselBackground(carouselBackground);
            customizationSteps.inputWidgetBackground(widgetBackground);
            customizationSteps.inputPrimaryColor(primaryColor);
            customizationSteps.inputStarcolor(starColor);
            customizationSteps.selectDatefomat(dateFomat);
            customizationSteps.checkDisplayTitle(displayReviewTitle);
            customizationSteps.checkDisplayBody(displayReviewBody);
            customizationSteps.checkDisplayAuthorName(displayAuthorName);
            customizationSteps.checkDisplayDatePosted(displayDatePosted);
            customizationSteps.checkDisplayAuthorAvatar(displayAuthorAvatar);
            customizationSteps.checkDisplayRatings(displayRatings);
            customizationSteps.selectWidgetLayout(widgetLayout);
            customizationSteps.selectImagePosition(imagePosition);
            customizationSteps.inputReviewsperpage(reviewsPerPage);
            customizationSteps.selectDefaultReviewLogic(defaultReviewLogic);
            customizationSteps.selectReviewCardLayout(reviewCardLayout);
            customizationSteps.inputNumberOfReviews(numberOfReviews);
            customizationSteps.selectReviewLogic(reviewLogic);
            customizationSteps.inputHeading(heading);
            customizationSteps.checkDisplayCheckoutPage(displayCheckoutPage);
            customizationSteps.checkLinkWidgets(linkWidgets);
            customizationSteps.checkLinkCarousel(linkCarousel);
            customizationSteps.clickSaveChangeButton();
            customizationSteps.waitABit(5000);
        }
    }

    @And("^verify style of reviews on storefront as \"([^\"]*)\"$")
    public void verifyStyleOfReviews(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            boolean navigateToChekoutPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Navigate to chekout page"));
            boolean isDisplayReview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is display review"));
            boolean displayReviewTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display review title"));
            boolean displayReivewBody = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display review body"));
            boolean displayAuthorName = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display author name"));
            boolean displayDatePosted = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display date posted"));
            boolean displayAuthorAvatar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display author avatar"));
            boolean displayRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display ratings"));
            String font = SessionData.getDataTbVal(dataTable, row, "Font");
            String style = SessionData.getDataTbVal(dataTable, row, "Style");
            String background = SessionData.getDataTbVal(dataTable, row, "Background");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String primaryColor = SessionData.getDataTbVal(dataTable, row, "Primary color");
            String starColor = SessionData.getDataTbVal(dataTable, row, "Star color");
            String reviewLogic = SessionData.getDataTbVal(dataTable, row, "Review logic");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            String numberReviewsPerPage = SessionData.getDataTbVal(dataTable, row, "Reviews per page");
            String numberOfReview = SessionData.getDataTbVal(dataTable, row, "Number of reviews");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String dateFormat = SessionData.getDataTbVal(dataTable, row, "Date format");
            boolean showLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show link all review"));
            String styleTitle = "#141414";

            if (style.equalsIgnoreCase("Dark")) {
                if (!navigateToChekoutPage) {
                    styleTitle = "#f8f8f8";
                    background = "#141414";
                }
                if (layout.equals("Masonry")) {
                    background = "rgba(238,238,238,0.15)";
                }
            }
            productSteps.openPage(page);
            if (navigateToChekoutPage) {
                productSteps.clickAddToCart();
                productSteps.clickCheckOut();
            }

            reviewOSFSteps.waitForLoadApps("Reviews");
            if (isDisplayReview) {
                reviewOSFSteps.verifyReviewShownOnSF(isDisplayReview);
                reviewOSFSteps.verifyDisplayAuthorName(displayAuthorName);
                reviewOSFSteps.verifyDisplayDatePosted(displayDatePosted);
                reviewOSFSteps.verifyDisplayReviewTitle(displayReviewTitle);
                reviewOSFSteps.verifyDisplayReviewBody(displayReivewBody);
                if (!layout.equals("Masonry")) {
                    reviewOSFSteps.verifyDisplayAuthorAvatar(displayAuthorAvatar);
                }
                reviewOSFSteps.verifyDisplayRatings(displayRatings);

                if (!primaryColor.isEmpty()) {
                    reviewOSFSteps.verifyPrimaryColor(primaryColor);
                }
                if (!font.isEmpty() && displayReviewTitle && displayReivewBody) {
                    reviewOSFSteps.verifyFont(font);
                }
                if (!navigateToChekoutPage) {
                    reviewOSFSteps.verifyStyleTitle(styleTitle);
                }
                if (!navigateToChekoutPage) {
                    reviewOSFSteps.verifyBackground(background);
                }
                if (displayRatings) {
                    reviewOSFSteps.verifyStarColor(starColor);
                }
                reviewOSFSteps.verifyReviewLayout(layout);
                if (!numberOfReview.isEmpty()) {
                    int reviewNumber = Integer.parseInt(numberOfReview);
                    if (reviewNumber > 0) {
                        reviewOSFSteps.verifyReviewNumber(reviewNumber);
                    }
                }
                if (!numberReviewsPerPage.isEmpty()) {
                    int reviewsPerPage = Integer.parseInt(numberReviewsPerPage);
                    reviewOSFSteps.verifyReviewPerPage(reviewsPerPage);
                }
                if (!heading.isEmpty()) {
                    reviewOSFSteps.verifyHeading(heading);
                }
                if (showLink)
                    reviewOSFSteps.verifyShowLinkAllReview(showLink);
                if (!dateFormat.equals("")) {
                    if (!layout.equals("Masonry")) {
                        reviewOSFSteps.verifyDateFormat(dateFormat);
                    } else {
                        reviewOSFSteps.veryfyDateFormatForLayoutMasory(dateFormat);
                    }
                }
                if (!imagePosition.equals("") && displayReivewBody) {
                    reviewOSFSteps.verifyImagePosition(layout, imagePosition);
                }
                if (!reviewLogic.equals("")) {
                    reviewOSFSteps.verifyReviewLogic(reviewLogic);
                }
            }
        }
    }

    @And("user click {string} on menu")
    public void userClickMenuOnApp(String menu) {
        customizationSteps.clickMenuOnApp(menu);
    }

    public String email;
    @When("setting notifications as {string}")
    public void settingNotifications(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean notification = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Notifications"));
//            String email = SessionData.getDataTbVal(dataTable, row, "Email to receive notifications");
            boolean sendNotificationBadReview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Only send notification when review under 3 star"));
            allReviewsSteps.checkNotification(notification);

            email = allReviewsSteps.genEmail("@yopmail.com");
            allReviewsSteps.inputEmail(email);
            allReviewsSteps.checkSendNotificationBadReview(sendNotificationBadReview);
            allReviewsSteps.clickBtnSavechanges();
        }
    }

    @Then("open yopmail")
    public void openYopMail() {
        notificationsSteps.openURL("http://www.yopmail.com/en/");
        notificationsSteps.typeEmailAndCheckInbox(email);
    }

    @Then("open yopmail as {string}")
    public void openYopMailAs(String email) {
        notificationsSteps.openURL("http://www.yopmail.com/en/");
        notificationsSteps.typeEmailAndCheckInbox(email);
    }

    @And("verify email notification as {string}")
    public void verifyEmailNotification(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String reviewTitle = SessionData.getDataTbVal(dataTable, row, "Review title");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Is show"));
            notificationsSteps.verifyNotification(reviewTitle, isShow);
        }
    }

    @When("setting store review as {string}")
    public void settingStoreReviewAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean storeReview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show store review"));
            customizationSteps.checkStoreReview(storeReview);
            customizationSteps.clickSaveChangeButton();
        }
    }

    @And("verify show store review as {string}")
    public void verifyShowStoreReviewAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showReview = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show store review"));

            reviewOSFSteps.waitForLoadApps("Reviews");
            reviewOSFSteps.verifyShowStoreReview(showReview);
        }
    }


}
