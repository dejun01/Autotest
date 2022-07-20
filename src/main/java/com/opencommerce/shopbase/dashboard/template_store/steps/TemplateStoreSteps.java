package com.opencommerce.shopbase.dashboard.template_store.steps;

import com.opencommerce.shopbase.dashboard.template_store.pages.TemplateStorePage;
import common.utilities.LoadObject;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TemplateStoreSteps {
    TemplateStorePage templateStorePage;
    String api;
    List<String> collectionArray;

    @Step
    public String getNameTemplateFirst() {
        return templateStorePage.getNameTemplateFirst();
    }

    @Step
    public void clickBtnPreviewFirst() {
        templateStorePage.clickBtnPreviewFirst();
    }

    @Step
    public void verifyPreviewTemplate(String nameTemplate) {
        templateStorePage.verifyPreviewTemplate(nameTemplate);
    }

    @Step
    public void selectShop(String shop) {
        templateStorePage.selectShop(shop);
    }

    @Step
    public void clickBtnInPopup(String button) {
        templateStorePage.clickBtnInPopup(button);
    }

    @Step
    public void verifyMessageAddTemplate(String message) {
        templateStorePage.verifyMessageAddTemplate(message);
    }

    @Step
    public void createShop() {
        templateStorePage.createShop();
    }

    @Step
    public void clickBtnOnPreviewDetail(String button) {
        templateStorePage.clickBtnOnPreviewDetail(button);
    }

    @Step
    public void clickBtn(String text) {
        templateStorePage.clickBtnText(text);
    }

    @Step
    public List<String> getCollections(){
        collectionArray = templateStorePage.getCollections();
        return templateStorePage.getCollections();
    }

    @Step
    public void clickAllTemplate(){
        templateStorePage.clickAllTemplate();
    }

    @Step
    public void checkOption(String text, Boolean status){
        templateStorePage.checkOption(text,status);
    }

    @Step
    public void verifyResultCountTemplate(int expect){
        if(expect !=0)
            templateStorePage.verifyResultCountTemplate(expect);
        else
            templateStorePage.verifyNoResultTemplate();
    }

    @Step
    public void clickButtonSearch() {
        templateStorePage.clickButtonSearch();
    }

    @Step
    public void enterTemplate(String nameFirstTemplate) {
        templateStorePage.enterTemplate(nameFirstTemplate);
    }

    @Step
    public void clickNameTemplate(String nameFirstTemplate) {
        templateStorePage.clickNameTemplate(nameFirstTemplate);
    }

    @Step
    public void verifyNameTemplateDetail(String nameFirstTemplate) {
        templateStorePage.verifyNameTemplateDetail(nameFirstTemplate);
    }

    @Step
    public void verifyThemeTemplate(String themeFirstTemplate) {
        templateStorePage.verifyThemeTemplate(themeFirstTemplate);
    }

    @Step
    public void verifyCategory(String categoriesFirstTemplate) {
        templateStorePage.verifyCategory(categoriesFirstTemplate);
    }

    @Step
    public void clickClearAll(){
        if(templateStorePage.isElementClearAll()){
            templateStorePage.clickOnClearAll();
        }
    }

    @Step
    public void searchTemplate(String text) {
        templateStorePage.searchTemplate(text);
    }

    @Step
    public void verifyNameTemplate(String nameFirstTemplate) {
        templateStorePage.verifyNameTemplate(nameFirstTemplate);
    }
}