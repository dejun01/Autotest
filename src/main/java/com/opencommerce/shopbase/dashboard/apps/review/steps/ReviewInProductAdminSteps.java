package com.opencommerce.shopbase.dashboard.apps.review.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.review.pages.ReviewInProductAdminPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class ReviewInProductAdminSteps extends CommonSteps {
    ReviewInProductAdminPage reviewInProductAdminPage;

    String shopDomain = LoadObject.getProperty("shop");

    @Step
    public void searchProductOnDashboard(String productName){
        reviewInProductAdminPage.searchProductOnDashboard(productName);
    }

    @Step
    public void searchProductOnDBPbase(String product){
        reviewInProductAdminPage.searchProductOnDBPbase(product);
    }

    @Step
    public void openProductOnDashboard(String productName){
        reviewInProductAdminPage.openProductOnDashboard(productName);
    }

    @Step
    public void openImportReviewsPopup(){
        reviewInProductAdminPage.clickOnImportButton();
    }

    @Step
    public void chooseImportBy(String importType){
        reviewInProductAdminPage.chooseImportBy(importType);
    }

    @Step
    public void verifyReviewsAnalytics(String field, String value, String rating, String token){
        if(field.equalsIgnoreCase("Average rating") && value.isEmpty()){
            value = calculateAvgRating(rating, token) + "/5";
        }
        reviewInProductAdminPage.verifyReviewsAnalytics(field, value);
    }

    @Step
    public void enterLinkURL(String urls){
        String[] urlList = urls.split(",");
        for(int i=0; i < urlList.length; i++){
            if(i != 0){
                reviewInProductAdminPage.clickAddMoreButton();
            }
            reviewInProductAdminPage.enterLinkURL(urlList[i], i+1);
        }
    }

    @Step
    public void clickButtonOnPopup(String label){
        reviewInProductAdminPage.clickButtonOnPopup(label);
    }

    @Step
    public void verifyActiveTab(String label, boolean isCheck){
        reviewInProductAdminPage.verifyActiveTab(label, isCheck);
    }

    @Step
    public void verifyFilterByProduct(String product){
        reviewInProductAdminPage.verifyFilterByProduct(product);
    }

    public double calculateAvgRating(String ratings, String token){
        String[] starList = ratings.split(",");
        int numberRating = 0;
        int reviewNumber = 0;
        int total = 0;

        for(String i: starList){
            int rating = Integer.parseInt(i);
            numberRating = reviewInProductAdminPage.countRating(shopDomain, token, rating);

            total += rating*numberRating;
            reviewNumber += numberRating;
        }
        return (double) total/reviewNumber;
    }
}
