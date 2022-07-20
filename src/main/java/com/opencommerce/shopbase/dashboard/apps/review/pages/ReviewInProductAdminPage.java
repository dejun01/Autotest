package com.opencommerce.shopbase.dashboard.apps.review.pages;

import common.SBasePageObject;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewInProductAdminPage extends SBasePageObject {
    public ReviewInProductAdminPage(WebDriver driver){
        super(driver);
    }

    public void searchProductOnDashboard(String productName){
        clickOnElement("//div[contains(@class,'search-container')]//input");
        waitClearAndTypeThenEnter("//div[contains(@class,'search-container')]//input", productName);
        waitForEverythingComplete();
    }

    public void searchProductOnDBPbase(String product){
        clickOnElement("//input[contains(@class,'product-search-input')]");
        waitClearAndTypeThenEnter("//input[contains(@class,'product-search-input')]", product);
        waitForEverythingComplete();
    }

    public void openProductOnDashboard(String productName){
        waitElementToBeVisible("//tr//span[normalize-space()='"+ productName +"']");
        clickOnElement("//tr//span[normalize-space()='"+ productName +"']");
    }

    public void clickOnImportButton(){
        waitForPageLoad();
        waitElementToBeVisible("//button[span[normalize-space()='Import more reviews' or normalize-space()='Import reviews']]");
        clickOnElement("//button[span[normalize-space()='Import more reviews' or normalize-space()='Import reviews']]");
    }

    public void chooseImportBy(String importType){
        clickOnElement("//div[@class='s-flex s-flex--justify-space-between' and descendant::h4[normalize-space()='"+importType+"']]//button");
    }

    public void verifyReviewsAnalytics(String field, String value){
        String expect = field + ": " + value;
        verifyElementContainsText("//p[contains(text(),'"+field+"')]", expect);
    }

    public int countRating(String shopDomain, String token, int rating){
        JsonPath jp = getAPI("https://"+ shopDomain + "/admin/reviews/count.json?search=&page=1&limit=20&ignoreSample=true&ratings="+ rating +"&sort_field=id&sort_mode=desc&access_token=" + token);
        return jp.get("count");
    }

    public void enterLinkURL(String url, int index){
        waitClearAndType("(//input[@placeholder = 'Enter URL of product in AliExpress'])[" + index + "]", url);
    }

    public void clickAddMoreButton(){
        clickOnElementByJs("//*[contains(text(),\"Add more\")]");
    }


    public void clickButtonOnPopup(String label){
        waitElementToBeClickable("//div[contains(@class,'s-modal')]//button[span[normalize-space()='"+label+"']]");
        clickOnElement("//div[contains(@class,'s-modal')]//button[span[normalize-space()='"+label+"']]");
    }

    public void verifyActiveTab(String label, boolean isCheck){
        scrollIntoElementView("//section[@data-id='review_widget']");
        waitABit(5000);
        boolean actual = isElementExist("//div[@class='rv-widget__review-type']//span[contains(text(),'"+label+"') and contains(@class,'review-type--disable')]");
        assertThat(actual).isEqualTo(!isCheck);
    }

    public void verifyFilterByProduct(String product){
        String expect = "Product: " + product;
        verifyElementContainsText("//span[@class='has-ellipsis']", expect);
    }

}
