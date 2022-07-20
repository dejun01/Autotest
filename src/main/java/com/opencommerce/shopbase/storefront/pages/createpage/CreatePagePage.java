package com.opencommerce.shopbase.storefront.pages.createpage;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreatePagePage extends SBasePageObject {
    public CreatePagePage(WebDriver driver) {
        super(driver);
    }

    public void clickToAddPageBtn() {
        clickOnElementByJs("//span[contains(.,'Add page')]");
    }

    public void enterInputTitle(String _label, String value) {
        waitClearAndType("//div[@class='s-input']//input[@type='" + _label + "']", value);
    }

    public void enterInputSomeText(String _label, String value) {
        waitClearAndType("//div[@role='" + _label + "']", value);
    }

    public void clickToInsertLinkBtn() {
        clickOnElement("//i[contains(@class,'link')]");
        clickOnElement("//span[text()='Insert Link']");
    }

    public void enterInputLinkTextbox(String _label, String value) {
        waitClearAndType("//div[@role='dialog']//label[text()='" + _label + "']//parent::div//input", value);
    }

    public void clickToVideoBtn() {
        clickOnElement("//button[@title='Insert/edit media']");
    }

    public void enterInputVideoLink(String _label, String value) {
        waitClearAndType("//label[text()='" + _label + "']//following::div/input[@type='url']", value);
        clickToInsertBtn();
    }

    public void clickToInsertBtn() {
        clickOnElementByJs("//button[text()='Save']");
    }

    public void selectPageTemplate(String value) {
        selectByText("//select", value);
    }

    public void checkToVisibilityRadioBtn(String _label) {
        String status = "//span[@class='s-control-label' and contains(.,'" + _label + "')]/parent::label/input";
        String statusRadioBtn = "//span[@class='s-control-label' and contains(.,'" + _label + "')]";
        verifyCheckedThenClick(status, statusRadioBtn, true);

    }

    public void clickToSeoBtn() {
        clickOnElement("//button[@slot='edit-link']");
    }

    public boolean seoBtnIsDisplayed() {
        return isElementVisible("//button[@slot='edit-link']", 3);
    }

    public void enterInputEditUrl(String value) {
        waitClearAndType("//div[contains(@class,'prepend')]//input[@type='text']", value);
    }

    public void clickToSaveBtn() {
        clickOnElement("//div[@class='row save-setting-content']//span[contains(.,'Save')]");
    }

    public void verifyNewPageCreatedOnDashboard(String _label) {
        waitElementToBeVisible("//a[contains(.,'" + _label + "')]");
        isElementExistNow("//a[contains(.,'" + _label + "')]");
    }

    public void verifyPageNotFound() {
        verifyElementVisible("//h2[contains(.,'Not Found')]", true);
    }

    public void verifyNewPageTitle(String _label, boolean _isCheck) {
        waitElementToBeVisible("//div[@class='row']//*[contains(text(),'" + _label + "')]");
        verifyElementVisible("//div[@class='row']//*[contains(text(),'" + _label + "')]", _isCheck);
    }

    public void verifyInsertedLink(String _label, boolean _isCheck) {
        waitElementToBeVisible("//a[@href='" + _label + "']");
        verifyElementVisible("//a[@href='" + _label + "']", _isCheck);
    }

    public void videoLinkInserted(String _label, boolean _isCheck) {
        waitElementToBeVisible("//p/iframe");
        verifyElementVisible("//p//iframe[contains(@src,'" + _label + "')]", _isCheck);
    }

    public void clickToReviewBtn() {
        clickOnElement("//button//span[text()='Write a review']");
    }

    public void verifyReviewFormVisible(boolean _isCheck) {
        verifyElementPresent("//div[@class='all-reviews-page']", _isCheck);
    }

    public void verifyContactFormVisible(boolean _isCheck) {
        waitElementToBeVisible("//form[@id='contact_form']");
        verifyElementVisible("//form[@id='contact_form']", _isCheck);
    }

    public void checkToAllPagesCheckbox() {
        clickOnElement("//tr[descendant::span[normalize-space()='Title']]//span[@class='s-check']");
    }

    public void checkToNewPageCheckbox(String _label) {
        clickOnElement("//tr[descendant::a[normalize-space()='" + _label + "']]//span[@class='s-check']");
    }

    public void clickToActionBtn() {
        clickOnElement("//div[@role='button']//span[text()='Action']");
    }

    public void clickToActionItem(String _label) {
        clickOnElement("//span[@class='s-dropdown-item' and contains(.,'" + _label + "')]");
    }

    public void clickToDeleteBtn(String _label) {
        clickOnElement("//button//span[contains(.,'" + _label + "')]");
    }

    public void clickToEditedPage(String _label) {
        clickOnElement("//a[contains(.,'" + _label + "')]");
    }


    public void verifyNoPagesFound(String _label) {
        verifyElementVisible("//h2[text()='" + _label + "']", true);
    }

    public void verifyDeletedPage(String _label, boolean _isCheck) {
        verifyElementPresent("//a[contains(.,'" + _label + "')]", _isCheck);
    }

    public void verifyPageStatus(String _label, boolean _isCheck) {
        verifyElementVisible("//tr[descendant::a[normalize-space()='" + _label + "']]//span[text()='Hidden']", _isCheck);
    }

    public void verifyAllPagesDeleted(boolean _isCheck) {
        verifyElementPresent("//table", _isCheck);
    }

    public void verifyMsgErr() {
        verifyElementVisible("//span[text()='there is 1 error']", true);
    }

    public void seacrhPage(String _value) {
        waitClearAndType("//input[@placeholder='Search pages']", _value);
        waitABit(3000);
    }

    public void clickIconInsert() {
        clickOnElement("//button[@title='Insert/edit link']");
    }
}
