package com.opencommerce.shopbase.hive_pbase.step;

import com.opencommerce.shopbase.hive_pbase.page.HivePage;
import com.opencommerce.shopbase.common.steps.CommonSteps;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.IOException;
import java.util.ArrayList;

public class HiveStep extends CommonSteps {
    HivePage hivePage;

    @Step
    public void goToLoginHivePage() {
        hivePage.goToLoginHivePage();
        getDriver().manage().deleteAllCookies();
        hivePage.maximizeWindow();
    }

    @Step
    public void loginWithGG() {
        hivePage.loginWithGG();
    }

    @Step
    public void replaceUrl(String linkhive, int orderId) {
        hivePage.replaceUrl(linkhive, orderId);
    }

    @Step
    public void emailGG(String email) {
        hivePage.emailGG(email);
    }

    @Step
    public void PassEmailGG(String password) {
        hivePage.PassEmailGG(password);
    }

    @Step
    public void switchToTab(String nameTab) {
        hivePage.waitForEverythingComplete();
        hivePage.switchToTab(nameTab);
        hivePage.waitForPageLoad();
        hivePage.waitForEverythingComplete();
        waitABit(1000);
    }

    @Step
    public void selectOrder(String shop, String order) {
        hivePage.selectOrder(shop,order);
    }

    @Step
    public void verifyArtworkStatus(String artworkStatus) {
        hivePage.verifyArtworkStatus(artworkStatus);
    }

    @Step
    public void verifyPersonalStatus(String personalStatus) {
        hivePage.verifyPersonalStatus(personalStatus);
    }

    @Step
    public void verifyImageMockup(String image, String row, float percent) throws IOException {
        hivePage.verifyImageMockup(image, row, percent);
    }

    @Step
    public void verifyImageArtwork(String image, float percent) throws IOException {
        hivePage.verifyImageArtwork(image, percent);
    }

    public void enterShopId(String shopId) {
        hivePage.waitContentShow();
        hivePage.enterInputFieldWithLabel("Shop id",shopId);
    }

    public void clickBtnClear() {
        hivePage.clickBtn("Clear");
    }

    public void verifyClearSuccces() {
        hivePage.verifyClearSuccces();
    }

    @Step
    public void openOnboardingQuestionList() {
        String hive = LoadObject.getProperty("hive");
        openUrl(hive + "/app/onboardingquestion/list");
    }

    @Step
    public boolean isConfigQuestion(String country, String storeType) {
        return hivePage.isConfigQuestion(country, storeType);
    }

    @Step
    public void clickOnButton(String lable, String country, String storeType) {
        hivePage.clickOnButton(lable, country, storeType);
    }

    @Step
    public JSONArray getQuestionAndAnswer() {
        String questionString = hivePage.getQuestionsString();
        JSONArray questionJSon;
        questionJSon = convertStringQuestionToJson(questionString);
        return questionJSon;
    }

    public JSONArray convertStringQuestionToJson(String questionString){
        JSONArray jsonPath = new JSONArray();
        try {
            jsonPath = (JSONArray) JSONValue.parse(questionString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonPath;
    }

    public String getFirstAnswer(JSONObject object){
        ArrayList<String> answersString = new ArrayList<>();
        JSONArray answers = (JSONArray) object.get("answers");

        for (Object answer : answers) {
            JSONObject answerObject = (JSONObject)answer;
            answersString.add((String) answerObject.get("title"));
        }
        return answersString.get(0);
    }

    @Step
    public String getNameTemplate(int i) {
        return hivePage.getNameTemplate(i);
    }

    @Step
    public String getThemeTemplate(int i) {
        return hivePage.getThemeTemplate(i);
    }

    @Step
    public String getCategoryTemplate(int i) {
        return hivePage.getCategoryTemplate(i);
    }

    @Step
    public void clickButtonFilter() {
        hivePage.clickButtonFilter();
    }

    @Step
    public void checkCheckboxInDropdown(String label) {
        hivePage.checkCheckboxInDropdown(label);
    }

    @Step
    public void clickAndSelectValue(String label, String choice) {
        hivePage.clickSelectChoice(label);
        hivePage.selectChoice(choice);
    }

    @Step
    public void clickBtnFilterData() {
        hivePage.clickBtnFilterData();
    }

    @Step
    public int getNumberTemplate() {
        return hivePage.getNumberTemplate();
    }

    @Step
    public int getTemplate(String text, int index) {
        int countPage = hivePage.countNumberPage();
        int countTemplate = 0;
        if(countPage != 0) {
            hivePage.clickOnPage(1);
            for (int i = 1; i < countPage; i++) {
                countTemplate += hivePage.getTemplate(text, index);
                if (i + 1 < countPage)
                    hivePage.clickOnPage(i + 1);
            }
        }
        else countTemplate += hivePage.getTemplate(text, index);
        return countTemplate;
    }

    @Step
    public int getTemplateIndustryTheme(String theme, String industry) {
        int countPage = hivePage.countNumberPage();
        int countTemplate = 0;
        hivePage.clickOnPage(1);
        for(int i=1; i<countPage; i++){
            hivePage.clickOnPage(i);
            countTemplate += hivePage.getTemplateIndustryTheme(theme, industry);
            if(i+1 < countPage)
                hivePage.clickOnPage(i+1);
        }
        return countTemplate;
    }

}