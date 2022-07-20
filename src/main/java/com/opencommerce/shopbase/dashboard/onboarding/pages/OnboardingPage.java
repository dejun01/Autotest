package com.opencommerce.shopbase.dashboard.onboarding.pages;

import common.SBasePageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OnboardingPage extends SBasePageObject {
    public OnboardingPage(WebDriver driver) {
        super(driver);
    }

    public void verifyIntroductionPage() {
        waitForEverythingComplete();
        verifyElementPresent("//div[contains(@class,'survey__introduce')]", true);
    }

    public void verifyAddYourContactPage() {
        verifyElementPresent("//div[contains(@class,'survey__profile')]", true);
    }

    public void inputContact(String contact) {
        waitClearAndType("//div[@class='iti']//input[@id='phone-number']", contact);
    }

    public void clickBusinessType(String businessType) {
        clickOnElement(" //div[contains(@class,'survey__type-business')]//ul[contains(@class,'list-type-business')]//li[contains(text(),'" + businessType + "')]");
    }

    public void clickStoreType(String businessType, String storeType) {
        switch (businessType) {
            case "Print-on-Demand":
            case "Print On Demand":
            case "定制商品":
                clickBtn(storeType);
                break;
            case "Dropshipping":
                if("PlusBase".equals(storeType)) {
                    clickOnElement("//button[child::span[contains(text(), '"+storeType+"')]]");
                } else {
                    clickOnElement("//div[contains(@class,'compare-table')]//button[@type=\"button\"]//span[contains(text(),'"+storeType+"')]");
                }
                break;
            default:
                verifyChooseCategoryPage();
        }
    }

    public void searchAndSelectProductCategory(String category) {
        waitClearAndType("//input[@placeholder='Search for your category. Ex: Baby']", category);
        clickOnElement("//div[contains(@class, 's-select-searchable__item option--choice')]//p[normalize-space()='" + category + "']");
    }

    public void verifyChooseCategoryPage() {
        verifyElementPresent("//div[@class='survey__your-type-store']", true);
    }

    public void verifyChooseYourBusinessTypePage() {
        verifyElementPresent("//div[@class='survey__your-type-store']", true);
    }

    public void verifyShowSelectStoreToClonePage(boolean isShow) {
        verifyElementPresent("//h1[normalize-space()='Import content from one of your existing stores']", isShow, 10);
    }

    public boolean verifyCustomizedQuestionsPage() {
        return isElementExist("//div[@class='s-m40 survey__onboarding-question']");
    }

    public void verifyCustomizeStorePage() {
        verifyElementText("//div[@class='row']//h2[@class='s-mb64']", "Your store is ready, let’s customize it!");
    }

    public void selectCountry(String label, String country) {
        clickOnElement("//div[@placeholder='" + label + "']");
        waitClearAndType("//div[@placeholder='" + label + "']//input", country);
        clickOnElement("//div[@placeholder='" + label + "']//div[normalize-space()='" + country + "']");
    }

    public void clickBtnStartNow() {
        clickOnElement("//div[contains(@class,'survey__action')]//button");
    }

    public void clickbtnNext() {
        waitElementToBeVisible("//button[@class='s-button is-primary']");
        clickOnElement("//button[@class='s-button is-primary']");
    }

    public void verifyChooseStoreType() {
        verifyElementPresent("//div[contains(@class,'survey__your-type-content')]", true);
    }

    public boolean isCustomizeStoreDisplay() {
        return isElementExist("//div[@class='survey__choose-primary-color-new survey__choose-primary-color s-my40']");
    }

    public void clickOnButton(String btnLabel){
        clickOnElement("//button[normalize-space()='" + btnLabel +"']");
    }

    String xpathYourContactPage = "//div[contains(@class,'s-form-item') and descendant::label[normalize-space()='%s']]";

    public void inputCountryCode(String value){
        waitClearAndType(String.format(xpathYourContactPage, "How should we contact you?") + "//div[contains(@class,'s-select')]//input", value);
    }

    public void selectSocialType(String value) {
        clickOnElement(String.format(xpathYourContactPage, "Your personal social profile (optional)") + "//option[normalize-space()='"+value+"']");
    }

    public void inputSocialAddress(String value) {
        waitClearAndType(String.format(xpathYourContactPage, "Your personal social profile (optional)") + "//input", value);
    }

    public void verifyMsg(String message) {
        verifyElementContainsText(String.format(xpathYourContactPage, "How should we contact you?") + "//div[contains(@class,'error')]", message);
    }

    public void chooseProductCategory(String productCategory) {
        clickOnElement("//span[contains(@class,'survey__suggestions-item') and normalize-space()='"+productCategory+"']");
    }

    public void verifyShowOnBoardingQuestions(boolean isShow) {
        verifyElementPresent("//div[@class='s-m40 survey__onboarding-question']", isShow);
    }

    public void verifyCreatedShop() {
        waitElementToBeVisible("//main[contains(@class,'ui-dashboard')]", 60);
        verifyElementPresent("//main[contains(@class,'ui-dashboard')]", true);
    }

    public void selectAnswer(String question, String answer) {
        String questionFormat = question.trim();
        waitElementToBePresentThenScrollIntoView("//div[p[normalize-space()='"+ questionFormat +"']]");
        clickOnElement("//option[normalize-space()='"+ answer +"']");
    }

    public boolean isDisablePasswordBtnDisplayed() {
        return isElementVisible("//div[@class='s-alert__content']", 5);
    }

    public void verifyDisabledPassword(boolean _isDisplayed) {
        verifyElementVisible("//div[@class='s-alert__content']", _isDisplayed);
    }

    public void chooseRevenue(String revenue) {
        clickOnElement("//li[normalize-space()='"+ revenue +"']");
    }

    public void clickbtnNextonBusinessTypeScreen() {
        clickOnElement("//button[@class='s-button btn-next is-primary']");
    }
}
