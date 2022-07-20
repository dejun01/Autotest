package com.opencommerce.shopbase.dashboard.onboarding.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.onboarding.pages.OnboardingPage;
import net.thucydides.core.annotations.Step;

public class OnboardingSteps extends CommonSteps {
    OnboardingPage onboardingPage;

    public void verifyIntroductionPage() {
        onboardingPage.verifyIntroductionPage();
    }

    public void clickBtnStartNow(String btnLabel) {
        onboardingPage.clickBtn(btnLabel);
    }

    public void verifyAddYourContactPage() {
        onboardingPage.verifyAddYourContactPage();
    }

    public void inputStoreCountry(String country) {
        onboardingPage.selectCountry("Select Store country", country);
    }

    public void inputYourPersonalLocation(String perLocation) {
        onboardingPage.selectCountry("Select personal location", perLocation);
    }

    public void inputContact(String contact) {
        String[] contactList = contact.split(">");
        if(contactList.length > 1){
            onboardingPage.inputCountryCode(contactList[0]);
            onboardingPage.inputContact(contactList[1]);
        }else {
            onboardingPage.inputContact(contactList[0]);
        }
    }
    public void inputSocialProfile(String socialProfile) {
        onboardingPage.enterInputFieldWithLabel("Your personal social profile (optional)", socialProfile);
    }

    public void inputPersonalSocialProfile(String socialProfile) {
        String[] socialProfileList = socialProfile.split(">");
        if(socialProfileList.length > 1){
            onboardingPage.selectSocialType(socialProfileList[0]);
            onboardingPage.inputSocialAddress(socialProfileList[1]);
        }else {
            onboardingPage.inputSocialAddress(socialProfileList[0]);
        }
    }

    public void clickbtnNext() {
        onboardingPage.clickbtnNext();
    }

    public void verifyChooseYourBusinessTypePage() {
        onboardingPage.verifyChooseYourBusinessTypePage();
    }

    public void chooseBusinessType(String businessType) {
        onboardingPage.clickBusinessType(businessType);

    }
    public void chooseStoreType(String businessType, String storeType) {
        onboardingPage.clickStoreType(businessType, storeType);
    }

    public void verifyChooseCategoryPage() {
        onboardingPage.verifyChooseCategoryPage();
    }

    public void chooseProductCategory(String productCategory) {
        String[] categoryAndAction = productCategory.split(">");

        if(categoryAndAction.length > 1){
            onboardingPage.chooseProductCategory(categoryAndAction[0]);
            onboardingPage.clickBtn(categoryAndAction[1]);
        }else {
            onboardingPage.clickBtn(categoryAndAction[0]);
        }

    }

    public void clickAction(String action) {
        onboardingPage.clickBtn(action);
    }

    public void verifyShowSelectStoreToClonePage(boolean isShow) {
        onboardingPage.verifyShowSelectStoreToClonePage(isShow);
    }

    public void selectStore(String store) {
        onboardingPage.selectDdlWithLabel("Select a store", store);
    }

    public boolean verifyCustomizedQuestionsPage() {
        return onboardingPage.verifyCustomizedQuestionsPage();
    }

    public void answerQuestions(String question, String ans) {
        onboardingPage.selectDdlWithLabel(question, ans);
    }

    public void verifyCustomizeStorePage() {
        onboardingPage.verifyCustomizeStorePage();
    }

    public void inputPrimaryColor(String color) {
        onboardingPage.enterInputFieldWithLabel("Primary color", color);
    }

    public void selectFont(String font) {
        onboardingPage.enterInputFieldWithLabel("Font", font);
    }

    public void clickTakeMeToMyStore() {
        onboardingPage.clickBtn("Take me to my store");
    }

    public String getAccessTokenShopbase() {
        return onboardingPage.getAccessTokenShopBase();
    }

    public void inputFirstName(String firstName) {
        onboardingPage.enterInputFieldWithLabel("Enter your name", firstName);
    }

    public void inputLastName(String lastName) {
        onboardingPage.enterInputFieldWithLabel("Enter your last name", lastName);
    }

    public void clickBtnStartNow() {
        onboardingPage.clickBtnStartNow();
    }

    public void verifyChooseStoreType() {
        onboardingPage.verifyChooseStoreType();
    }

    public void verifyMsg(String message) {
        onboardingPage.verifyMsg(message);
    }

    public void verifyShowOnBoardingQuestions(boolean isShow) {
        onboardingPage.verifyShowOnBoardingQuestions(isShow);
    }

    @Step
    public void verifyCreatedShop() {
        onboardingPage.verifyCreatedShop();
    }

    @Step
    public void selectAnswer(String question, String answer) {
        onboardingPage.selectAnswer(question, answer);
    }

    @Step
    public void disablePassword() {
        boolean isDisplayed = onboardingPage.isDisablePasswordBtnDisplayed();
        if(isDisplayed){
            onboardingPage.clickBtn("Disable password");
            waitABit(2000);
        }
    }

    @Step
    public void verifyDisabledPassword(boolean isDisplayed) {
        onboardingPage.verifyDisabledPassword(isDisplayed);
    }

    public void chooseRevenue(String revenue) {
        onboardingPage.chooseRevenue(revenue);
    }

    public void clickbtnNextonBusinessTypeScreen() {
        onboardingPage.clickbtnNextonBusinessTypeScreen();
    }
}

