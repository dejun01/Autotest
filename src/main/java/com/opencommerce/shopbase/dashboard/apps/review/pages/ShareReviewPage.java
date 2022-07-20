package com.opencommerce.shopbase.dashboard.apps.review.pages;

import common.SBasePageObject;
import io.cucumber.java.sl.In;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Java6Assertions.assertThat;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;

public class ShareReviewPage extends SBasePageObject{
    public ShareReviewPage(WebDriver driver) {
        super(driver);
    }

    public void verifyNumberOfReviews(int expectNumber) {
        clickOnElement("//label[@class='s-checkbox']//span");
        String strActualNumber;
        if(isElementExist("//span[contains(text(),'across all pages')]")){
            strActualNumber = getText("//span[contains(text(),'across all pages')]").split(" ")[1];
        } else {
            strActualNumber = getText("//span[contains(text(),'reviews selected')]").split(" ")[0];
        }

        int actualNumber = Integer.parseInt(strActualNumber);
        assertThat(actualNumber).isEqualTo(expectNumber);
    }

    public void verifyMinStar(int minStar, int index){
        verifyElementPresent("//tr["+index+"]//span[contains(@class,'text-yellow400')]["+minStar+"]", true);
    }

    public void nextPage(){
        String xpath = "//a[@role='button' and @class='s-pagination-next']";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void clickOnImportReviewsButton(String blockName) {
        clickOnElement("//div[@class='s-flex' and descendant::h4[normalize-space()='" + blockName + "']]//following-sibling::button");
    }

    public void selectCondition(String condition) {
        clickOnElement("//div[contains(@class,'modal')]//select");
        clickOnElement("//option[normalize-space()='" + condition + "']");
    }

    public void inputNumberOfSharedReviews(String numberOfSharedReviews) {
        clickAndClearThenType("//input[@placeholder='We suggest an odd number']", numberOfSharedReviews);
    }

    public void clickOnButton(String buttonName) {
        clickOnElement("//button[span[normalize-space()='"+buttonName+"']]");
    }

    public void verifyMessage(String message) {
        verifyElementContainsText("//div[@class='s-form-item__error']", message);
    }

    public void navigateToTab(String tabName) {
        String _xpath = "//span[contains(text(),'" + tabName + "')]";
        scrollToElement(_xpath);
        clickOnElement(_xpath);
    }

    public void selectConditionFilters(String condition, String value){
        clickOnElement("//span[normalize-space()='Filter']//parent::button");
        if(!isElementExist("//span[normalize-space()='Clear all filters']//parent::button[@disabled='disabled']")){
            clickOnElement("//span[normalize-space()='Clear all filters']//parent::button");
        }
        clickOnElement("//div[contains(@class,'sidebar-filter__item') and p[normalize-space()='"+condition+"']]");
        clickOnElement("//label[span[normalize-space()='"+value+"']]//span[@class='s-check']");
    }

    public void selectTheFirstReview(){
        clickOnElement("//td");
    }

    public void verifyImportedReview(){
        int i = 0;
        while (!isElementExist("//label[@class='s-checkbox']//span") && i < 5){
            waitABit(5000);
            refreshPage();
            i++;
        }
    }


    public boolean showBtn(String label) {
        return isElementExist("//*[normalize-space()='"+label+"']");
    }
}
