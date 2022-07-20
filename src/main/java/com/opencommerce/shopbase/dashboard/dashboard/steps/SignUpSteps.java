package com.opencommerce.shopbase.dashboard.dashboard.steps;

import com.opencommerce.shopbase.dashboard.dashboard.pages.SignUpPage;
import com.opencommerce.shopbase.dashboard.home.page.HomePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class SignUpSteps {
    SignUpPage signUpPage;
    String shop = LoadObject.getProperty("shop");
    HomePage homePage;
    String gapi = LoadObject.getProperty("gapi.url");

    @Step
    public void enterFirstName(String sFirstName) {
        signUpPage.enterFirstName(sFirstName);
    }

    @Step
    public void enterLastName(String sLastName) {
        signUpPage.enterLastName(sLastName);
    }


    @Step
    public void selectStoreCountry(String sStoreCountry) {
        if (!sStoreCountry.isEmpty())
            signUpPage.selectStoreCountry(sStoreCountry);
    }

    @Step
    public void selectYourPersonalLocation(String sYourPersonalLocation) {
        if (!sYourPersonalLocation.isEmpty())
            signUpPage.selectYourPersonalLocation(sYourPersonalLocation);
    }

    @Step
    public void enterEmailCustomer(String sEmail) {
//        signUpPage.enterInputFieldWithLabel("facebook.com/myusername", sBusiness);
        signUpPage.enterEmailCustomer(sEmail);
    }

    @Step
    public void enterPhoneNumber(String sPhoneNumber) {
        signUpPage.enterPhoneNumber(sPhoneNumber);
    }

    @Step
    public void clickBtnEnterMyShop() {
        signUpPage.clickBtnEnterMyShop(0);
        signUpPage.waitForEverythingComplete();
    }

    @Step
    public void clickBtnCreate() {
        signUpPage.clickBtn("Create");
    }

    @Step
    public void clickBtnAddANewShop() {
        signUpPage.clickBtn("Add a new shop");
    }

    @Step
    public void enterShopName(String shop) {
        signUpPage.enterInputFieldWithLabel("Your shop name", shop);
    }

    @Step
    public void verifyExistPageDefault(String title) {
        signUpPage.verifyExistPageDefault(title);
    }

    @Step
    public boolean isDashboardHomepage() {
        return signUpPage.isDashboardHomepage();
    }

    @Step
    public void clickSelectAnotherShop() {
        signUpPage.clickSelectAnotherShop();
    }


    @Step
    public void getAPICountry() {
        signUpPage.getAPICountry();
    }

    public void clickStateProvince(String sStateOrProvince) {
        if (!sStateOrProvince.isEmpty())
            signUpPage.clickStateProvince();
    }


    public void selectStateProvince(String sStateOrProvince) {
        if (!sStateOrProvince.isEmpty())
            signUpPage.selectStateProvince(sStateOrProvince);
    }

    @Step
    public void openSignUpPage(String url) {
        signUpPage.openSignInPage(url);
    }

    @Step
    public void clickOnSignUpHyperLink() {
        signUpPage.clickOnSignUpHyperLink();
    }

    @Step
    public void inputEmail(String email) {
        signUpPage.inputEmail(email);
    }

    @Step
    public void inputPassword(String password) {
        signUpPage.inputPassword(password);
    }

    @Step
    public void inputShopName(String shopName) {
        signUpPage.inputShopName(shopName);
    }

    @Step
    public void clickOnSignUpbtn() {
        signUpPage.clickOnSignUpbtn();
    }

    @Step
    public void clickOnSkipBtn() {
        signUpPage.clickOnSkipBtn();
        signUpPage.waitForEverythingComplete();
    }

    @Step
    public void inputFirstName(String firstName) {
        signUpPage.inputFirstName(firstName);
    }

    @Step
    public void inputLastName(String lastName) {
        signUpPage.inputLastName(lastName);
    }

    @Step
    public void inputStoreCountry(String storeCountry) {
        signUpPage.inputStoreCountry(storeCountry);
    }

    @Step
    public void inputPersonalLocation(String personalLocation) {
        signUpPage.inputPersonalLocation(personalLocation);
    }

    @Step
    public void inputPhoneNumber(String phoneNumP1, String phoneNumP2) {
        signUpPage.inputPhoneNumber(phoneNumP1, phoneNumP2);
    }

    @Step
    public void clickOnEnterMyShopBtn() {
        signUpPage.clickOnEnterMyShopBtn();
        signUpPage.waitForEverythingComplete();
        signUpPage.waitForPageLoad();

    }

    @Step
    public void clickOnNextBtn() {
        signUpPage.clickOnNextBtn();
    }

    @Step
    public void clickOnStartNowBtn() {
        signUpPage.clickOnStartNowBtn();
    }

    @Step
    public void clickBtnNext() {
        signUpPage.clickBtnNext();
    }

    @Step
    public void clickBtnStartNow() {
        if (signUpPage.isIntroPageExist())
            signUpPage.clickBtn("Start now");
    }

    @Step
    public void clickBtnNoThank() {
        signUpPage.clickBtnNoThanks();
        signUpPage.waitForPageLoad();
    }

    @Step
    public void clickTakeMeToMyStore() {
        if (signUpPage.isElementExist("//button[normalize-space()='Take me to my store']")) {
            signUpPage.clickBtn("Take me to my store");
        }
    }

    @Step
    public void clickSkip() {
        signUpPage.clickBtn("Skip");
    }

    @Step
    public void clickNoIDontWantToImport() {
        signUpPage.clickBtn("No, I don't want to import");
    }

    @Step
    public void logInfor(String inf) {
    }

    @Step
    public void verifyCreatedShopPrintBase() {
        signUpPage.verifyCreatedShopPrintBase();
    }

    @Step
    public void verifyStoreName(String storeName) {
        signUpPage.verifyStoreName(storeName);
    }

    @Step
    public void waitUntilInvisibleLoading() {
        signUpPage.waitUntilInvisibleLoading(10);
    }

    @Step
    public void selectQuestion(String value_prod, String value_stag) {
        signUpPage.selectQuestion(value_prod, value_stag);
    }

    @Step
    public void chooseBusinessType(String businessType) {
        signUpPage.chooseBusinessType(businessType);
    }

    @Step
    public void chooseType(String type) {
        signUpPage.chooseType(type);
    }

    @Step
    public void selectCategoryNiche(String categoryNiche) {
        signUpPage.inputKeySearch(categoryNiche);
        signUpPage.chooseCategoryNiche(categoryNiche);
    }

    @Step
    public void inputColor(String color) {
        signUpPage.inputColor(color);
    }

    @Step
    public void chooseFont(String font) {
        signUpPage.chooseFont(font);
    }

    @Step
    public void answerQuestions(String answers) {
        if (signUpPage.isExistQuestionPage()) {
            String[] answer = answers.split(";");
            for (int i = 0; i < answer.length; i++) {
                int j = i + 1;
                signUpPage.selectAnswer(answer[i], j);
            }
        }
    }

    @Step
    public void clickBtnNoThanks() {
        signUpPage.clickBtnNoThanks();
    }

    @Step
    public void chooseStoreImport(String storeImport) {
        signUpPage.chooseStoreImport(storeImport);
    }

    @Step
    public void clickBtnImport() {
        signUpPage.clickBtnImport();
    }

    @Step
    public void verifyCreatedShopSuccess() {
        signUpPage.verifyCreatedShopSuccess();
    }

    @Step
    public void clickBtnUseSample() {
        signUpPage.waitForPageLoad();
        signUpPage.clickBtnSample();
        signUpPage.waitForEverythingComplete();
    }

    @Step
    public void enterPassword(String pw) {
        signUpPage.enterInputFieldWithLabel("Password", pw);
    }

    @Step
    public void enterEmail(String email) {
        signUpPage.enterInputFieldWithLabel("Email", email);
    }

    @Step
    public void clickBtnSignUp() {
        signUpPage.clickBtn("Sign up");
    }

    @Step
    public void verifyErrorMessage(String msg) {
        if (!msg.isEmpty()) {
            signUpPage.verifyErrorMessage(msg);
        }
    }

    @Step
    public void clickbtnSignIn() {
        signUpPage.clickBtn("Sign in");
    }

    @Step
    public String getAccessToken(String user, String pw, String shop) {
        return signUpPage.getAccessTokenShopBase(user, pw, shop);
    }

    public void verifyShopData(String data1, String data2) {
        signUpPage.verifyShopData(data1, data2);
    }

    public void verifySampleData(List<String> sampleData1, List<String> sampleData2) {
        signUpPage.verifySampleData(sampleData1, sampleData2);
    }

    public void clickCapchar() {
        signUpPage.clickCapchar();


    }

    @Step
    public boolean isExistQuestionPage() {
        return signUpPage.isExistQuestionPage();
    }

    @Step
    public void verifyDisplayOffer(String shopname, Boolean isDisplay) {
        signUpPage.verifyDisplayOffer(shopname, isDisplay);
    }

    @Step
    public void choosePlatform(String flatform) {
        signUpPage.clickBtn(flatform);
    }

    @Step
    public String getData(JsonPath jsonPath, String key) {
        Object data = homePage.getData(jsonPath, key);
        if (data == null) {
            data = 0;
        }
        return data.toString();
    }

    @Step
    public HashMap<String, String> getContentAnswerOnBoarding() {
        String url = signUpPage.getCurrentUrl();
        String shopID = url.split("=")[1];

        HashMap<String, String> items = new HashMap<>();
        String acctoken = "";
        acctoken = signUpPage.getAccessTokenUser();
        String api = gapi + "/signup/onboarding-question?shop_id=" + shopID;
        JsonPath js = homePage.getAPISbase(api, acctoken);
        int size = (js.getList("content")).size();
        for (int i = 0; i < size; i++) {
            String question = getData(js, "content[" + i + "].title"); //0
            String anwsers = getData(js, "content[" + i + "].answers[0].title"); //0
            items.put(question, anwsers);
        }
        return items;


    }


    public void selectAnswers(HashMap<String, String> items) {
        for (String i : items.keySet()) {
            String item = items.get(i);
            signUpPage.selectQuestionAndAnswers(item, i);
        }
    }
}

