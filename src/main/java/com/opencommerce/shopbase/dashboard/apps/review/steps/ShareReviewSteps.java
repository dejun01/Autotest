package com.opencommerce.shopbase.dashboard.apps.review.steps;


import com.opencommerce.shopbase.dashboard.apps.review.pages.ShareReviewPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ShareReviewSteps extends ScenarioSteps {
    ShareReviewPage shareReviewPage;

    @Step
    public void verifyNumberOfReviews(int numberOfReviews){
        shareReviewPage.verifyNumberOfReviews(numberOfReviews);
    }

    @Step
    public void verifySharedReviews(String condition, int numberOfReviews, String value){
        shareReviewPage.selectConditionFilters(condition, value);
        shareReviewPage.clickOnButton("Done");
        shareReviewPage.verifyNumberOfReviews(numberOfReviews);
    }

    @Step
    public void verifyMinStar(int minStar, int nummbersOfReview){
        int index=1;
        for(int i=1; i <= nummbersOfReview; i++){
            if(i!= 1 && i%20 == 1){
                shareReviewPage.nextPage();
                index = 1;
            }
            shareReviewPage.verifyMinStar(minStar, index);
            index++;
        }
    }

    @Step
    public void clickOnImportReviewsButton(String blockName){
        shareReviewPage.clickOnImportReviewsButton(blockName);
    }

    @Step
    public void setupToImportSharedReview(String condition, String numberOfSharedReviews){
        if(!condition.isEmpty()){
            shareReviewPage.selectCondition(condition);
        }
        shareReviewPage.inputNumberOfSharedReviews(numberOfSharedReviews);
    }

    @Step
    public void clickOnButton(String buttonName){
        shareReviewPage.clickOnButton(buttonName);
    }

    @Step
    public void verifyMessage(String message){
        shareReviewPage.verifyMessage(message);
    }

    @Step
    public void navigateToTab(String tabName){
        shareReviewPage.navigateToTab(tabName);
    }

    @Step
    public void selectTheFirstReview(){
        shareReviewPage.selectTheFirstReview();
    }

    @Step
    public void verifyImportedReview(){
        shareReviewPage.verifyImportedReview();
    }

    public boolean showBtn(String label) {
        return shareReviewPage.showBtn(label);
    }
}
