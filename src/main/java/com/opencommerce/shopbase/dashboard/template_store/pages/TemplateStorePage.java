package com.opencommerce.shopbase.dashboard.template_store.pages;

import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class TemplateStorePage extends SBasePageObject {
    public TemplateStorePage(WebDriver webDriver){
        super(webDriver);
    }

    public String getNameTemplateFirst(){
        return getText("(//*[contains(@class,'template-card')]//*[contains(@class,'template-card__title')])[1]");
    }

    public void clickBtnPreviewFirst(){
        clickOnElement("(//*[contains(@class,'template-card')]//*[contains(text(),'Preview')])[1]");
        waitForPageLoad();
    }

    public void clickBtnOnPreviewDetail (String button){
        clickOnElement("//*[contains(@class,'template-preview__navbar')]//*[contains(text(),'"+ button +"')]");
    }

    public void verifyPreviewTemplate(String nameTemplate){
        verifyElementContainsText("//*[contains(@class,'template-preview__navbar--title')]", nameTemplate);
    }

    public void selectShop(String shop){
        clickOnElement("//input[contains(@placeholder,'Choose a store')]");
        waitElementToBePresent("//*[contains(@class, 'list-shop')]//*[contains(text(),'"+ shop+"')]");
        clickOnElement("//*[contains(@class, 'list-shop')]//*[contains(text(),'"+ shop+"')]");
    }

    public void clickBtnText(String button){
        clickOnElement("//*[contains(@class,'header--desktop')]//*[contains(text(),'"+ button +"')]");
        waitForPageLoad();
    }

    public void verifyMessageAddTemplate(String message){
        String xpath= "//*[contains(text(),'" + message + "')]";
        waitUntilElementVisible(xpath, 50);
        verifyElementPresent(xpath, true);
    }

    public void createShop (){
        clickOnElement("//span[contains(text(),'Create')]");
        waitForPageLoad();
    }

    public void clickBtnInPopup(String label){
        String xpath = "//*[contains(@class,'template-modal')]//*[contains(text(),'" + label + "')]";
        clickOnElementByJs(xpath);
    }

    public List<String> getCollections(){
        return getListText("//*[contains(@class,'collection-section')]//*[contains(@class,'collection-section__heading--title')]");
    }

    public int getNumberTemplate(String collection){
        return getListText("//*[contains(text(),'"+collection+"')]//ancestor::*[contains(@class,'collection-section')]//*[@class='template-card']").size();
    }

    public void clickAllTemplate(){
        String xpath = "//*[contains(@class,'homepage')]//*[contains(text(),'Browse all templates')]";
        scrollIntoElementView(xpath);
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void checkOption(String text, Boolean status){
        String xpath = "//*[contains(text(),'" + text + "')]//ancestor::div[contains(@class,'base-checkbox')]";
        checkCheckboxTemplate(xpath,status);
        waitForPageLoad();
    }

    public void verifyResultCountTemplate(int expect){
        String xpath = "//*[contains(@class,'filter__left')]//p";
        highlightElement(xpath);
        verifyElementContainsText(xpath, String.valueOf(expect) + " templates");
    }

    public void checkCheckboxTemplate(String _parentXpath, boolean _isCheck){
        String xPathStatus = _parentXpath + "//input[@type='checkbox']";
        String xPathCheckbox = _parentXpath + "//span[contains(@class,'base-checkbox__span')]";
        highlightElement(xPathCheckbox);
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, _isCheck);
    }

    public void clickButtonSearch() {
        clickOnElement("//*[contains(@class, 'cta-search')]");
    }

    public void enterTemplate(String nameFirstTemplate) {
        String xpath = "//*[contains(@class,'input-search')]//input";
        waitClearAndTypeThenEnter(xpath, nameFirstTemplate);
    }

    public void clickNameTemplate(String nameFirstTemplate) {
        waitForPageLoad();
        clickOnElement("//*[contains(@class,'template-card__title') and contains(text(),'"+nameFirstTemplate+"')]");
    }

    public void verifyNameTemplate(String nameFirstTemplate) {
        waitForPageLoad();
        verifyElementPresent("//*[contains(@class,'template-card__title') and contains(text(),'"+nameFirstTemplate+"')]", true);
    }

    public void verifyNameTemplateDetail (String nameFirstTemplate) {
        String xpath = "//*[contains(@class,'template-detail__info--title')]";
        waitElementToBePresent(xpath);
        verifyElementContainsText(xpath,nameFirstTemplate);
    }

    public void verifyThemeTemplate(String themeFirstTemplate) {
        verifyElementContainsText("//a[contains(@class,'template-detail__theme--name')]", themeFirstTemplate);
    }

    public void verifyCategory(String categoriesFirstTemplate) {
        verifyElementContainsText("//*[contains(@class,'template-detail__intro--tag')]//following-sibling::div", categoriesFirstTemplate);
    }

    public boolean isElementClearAll(){
        return isElementExist("//*[contains(@class,'filter__left')]//*[contains(text(),'Clear all')]");
    }
    public void clickOnClearAll(){
        clickOnElement("//*[contains(@class,'filter__left')]//*[contains(text(),'Clear all')]");
        waitForPageLoad();
    }

    public void searchTemplate(String text) {
        String xpath = "//*[contains(@placeholder,'Search by theme')]";
        clickOnElement(xpath);
        waitClearAndTypeThenEnter(xpath, text);
    }

    public void verifyNoResultTemplate() {
        verifyElementContainsText("//*[contains(@class, 'empty-state')]//h3", "No search results");
    }
}