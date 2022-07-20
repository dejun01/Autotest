package com.opencommerce.shopbase.dashboard.apps.review.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CustomizationPage extends SBasePageObject {
    public CustomizationPage(WebDriver driver) {
        super(driver);
    }

    public void inputStyleWithLabel(String label, String value) {
        waitTypeOnElement("//div[contains(@class,'is-required') and descendant::label[normalize-space()='" + label + "']]//input", value);
    }

    public void checkDisplayAuthorName(Boolean displayAuthorName) {
        checkCheckboxWithLabel("Author name", displayAuthorName);
    }

    public void checkDisplayDatePosted(Boolean displayDatePosted) {
        checkCheckboxWithLabel("Date posted", displayDatePosted);
    }

    public void checkDisplayAuthorAvatar(Boolean displayAuthorAvatar) {
        checkCheckboxWithLabel("Author avatar", displayAuthorAvatar);
    }

    public void checkDisplayRatings(Boolean displayRatings) {
        checkCheckboxWithLabel("Ratings", displayRatings);
    }

    public void selectWidgetLayout(String widgetLayout) {
        clickOnElementByJs("//span[@class='s-control-label']//img[@alt='" + widgetLayout + "']");
    }

    public void selectImagePosition(String imageposition) {
        selectDdlWithLabel("Image position", imageposition);
    }

    public void selectDefaultReviewLogic(String defaultReviewLogic) {
        selectDdlWithLabel("Default review logic", defaultReviewLogic);

    }

    public void selectReviewCardLayout(String reviewCardLayout) {
        clickOnElementByJs("//span[@class='s-control-label']//img[@alt='" + reviewCardLayout + "']");
    }

    public void selectReviewLogic(String reviewLogic) {
        selectDdlWithLabel("Review logic", reviewLogic);
    }

    public void checkLink(String label, boolean enableLink) {
        String statusElement = "//div[@class='row' and descendant::*[contains(text(),'" + label + "')] and child::div[@class='col-md-7']]//input";
        String checkboxElement = "//div[@class='row' and descendant::*[contains(text(),'" + label + "')] and child::div[@class='col-md-7']]//label";

        verifyCheckedThenClick(statusElement, checkboxElement, enableLink);
    }


    public void clickMenuOnApp(String menu) {
        String xpathMenu = "//li//a[contains(text(),'"+menu+"')]";
        waitElementToBePresent(xpathMenu);
        clickOnElementByJs(xpathMenu);
    }

    public void checkDisplayTitle(Boolean displayReviewTitle) {
        checkCheckboxWithLabel("Review title", displayReviewTitle);

    }

    public void checkDisplayBody(Boolean displayReviewBody) {
        checkCheckboxWithLabel("Review body", displayReviewBody);
    }

    public void clickSaveChangeButton() {
        clickOnElementByJs("//button[@class='s-button btn s-button is-primary is-default']");
    }
}


