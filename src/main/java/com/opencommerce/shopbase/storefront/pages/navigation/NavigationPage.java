package com.opencommerce.shopbase.storefront.pages.navigation;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class NavigationPage extends SBasePageObject {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }


    public void clickToAddOrCreateBtn(String _label) {
        clickOnElement("//a[contains(.,'" +_label+"')]");
    }

    public void inputMenuTitle(String _value) {
        waitClearAndType("//div[@class='s-input']/input", _value);
    }

    public void clickToAddMenuItemBtn() {
        clickOnElement("//button[@class='tree-node-add']");
    }

    public void inputMenuItemName(String _value) {
        waitClearAndType("//input[@id='editMenuItemName']", _value);
    }

    public void selectFirstLink(String _label) {
        waitClearAndType("//input[@placeholder='Search or paste a link']", _label);
        clickOnElement("//span[@class='s-dropdown-item']/span[contains(.,'"+_label+"')]");
    }

    public void selectSecondlink(String _label) {
        clickOnElement("//div[@class='s-dropdown-item__content' and contains(.,'"+_label+"')]");
    }

    public void clickToAddBtn(String _label) {
        clickOnElement("//button/span[text()='"+_label+"']");
    }

    public void clickToSaveBtn(String _label) {
        clickOnElement("//button[contains(.,'"+_label+"')]");
    }

    public void verifyMenuCreated(String _label, boolean _isCheck) {
        verifyElementVisible("//a[@class='s-button is-text' and contains(.,'"+_label+"')]", _isCheck);
    }

    public void clickToCustomizeBtn(String _label) {
        clickOnElement("//button[@class='s-button is-primary s-ml8' and contains(.,'"+_label+"')]");
    }

    public void clickToHeaderSetting(String _label) {
        clickOnElement("//p[@class='beFyhq' and contains(.,'"+_label+"')]");
    }

    public void selectNewMenuCreated(String value) {
        selectByText("//div[@class='s-select is-expanded']//select", value);
    }

    public void clickToSaveThemeBtn(String _label) {
        clickOnElement("//button//span[contains(.,'"+_label+"')]");
        clickOnElement("//button/span[contains(.,'Close')]");
        waitABit(5000);
    }

    public void verifyMenuDisplayed(String _label, boolean _isCheck) {
        waitElementToBeVisible("//a[contains(@class,'items-center') and contains(.,'"+_label+"')]");
        verifyElementVisible("//a[contains(@class,'items-center') and contains(.,'"+_label+"')]", _isCheck);
    }

    public void clickToMenuItemOnSF(String _label) {
        clickOnElement("//a[contains(@class,'items-center') and contains(.,'"+_label+"')]");
        waitABit(3000);
    }

    public void clickToMenuOnDashboard(String _label) {
        clickOnElement("//a[@class='s-button is-text' and contains(.,'"+_label+"')]");
    }

    public void clickToEditMenuItemBtn(String _label, String _editBtn) {
        clickOnElement("//div[contains(@class,'menu-name') and contains(.,'"+_label+"')]/parent::div//span[normalize-space()='"+_editBtn+"']");
    }

    public void clickToRemoveOldLink() {
        clickOnElement("//label[text()='Link']/parent::div//a[@class='s-delete is-small']");
    }

    public void confirmDeleteMenuBtn(String _label) {
        clickOnElement("//div[contains(@class,'s-modal-content')]//span[contains(.,'"+_label+"')]");
    }

    public void clickBtnOnNavigationPage(String _label) {
        clickOnElement("//span[text()='"+_label+"']");
        waitForPageLoad();
    }

    public void checkPagelocks(String _label) {
        clickOnElement("//span[@class='s-control-label' and contains(.,'"+_label+"')]");
    }

    public void clickToSavePageLocksBtn(String _label) {
        clickOnElement("//button[@class='s-button is-primary']//span[contains(.,'"+_label+"')]");
    }

    public void waitToMsgInvisible(String _msg) {
        waitElementToBeVisible("//div[text()='"+_msg+"']");
        waitUntilElementInvisible("//div[text()='"+_msg+"']", 3);
    }

    public void verifyPageNotFound() {
        verifyElementVisible("//h2[contains(.,'404 Page Not Found')]", true);
    }

    public void verifyPageContentShowOnSF(String _label) {
        verifyElementVisible("//h1[contains(.,'"+_label+"')]", true);
    }

    public void inputLinkToTxtbox(String _label, String _value) {
        waitClearAndType("//section[@class='card'][descendant::label[normalize-space()='"+_label+"']]//input", _value);
    }

    public void verifyMessage(String _label, boolean _isCheck) {
        verifyElementVisible("//span[text()='"+_label+"']", _isCheck);
    }


    public void verifyErrorMessage(String _label, boolean _isCheck) {
        verifyElementVisible("//li[contains(.,'"+_label+"')]", _isCheck);
    }


    public void clickToDiscardBtn() {
        clickOnElement("//button[contains(.,'Discard')]");
    }

    public void backToRedirectList() {
        clickOnElement("//ol[@class='s-breadcrumb']");
    }

    public void searchUrlRedirectList(String _label, String _value) {
        waitClearAndType("//input[@placeholder='"+_label+"']", _value);
    }

    public void verifyUrlRedirectCreatedOnDB(String _redFr, String redTo, boolean _isCheck) {
        waitElementToBeVisible("//tr[descendant::a[normalize-space()='"+_redFr+"']]//a[contains(.,'"+redTo+"')]");
        verifyElementVisible("//tr[descendant::a[normalize-space()='"+_redFr+"']]//a[contains(.,'"+redTo+"')]", _isCheck);
    }

    public void clickToEditUrlRedirect(String _redFr) {
        waitElementToBeClickable("//div[contains(@class,'s-flex')]//a[contains(.,'"+_redFr+"')]");
        clickOnElement("//div[contains(@class,'s-flex')]//a[contains(.,'"+_redFr+"')]");
    }

    public void selectRedirectOnList(String _redFr) {
        clickOnElement("//tr[descendant::a[normalize-space()='"+_redFr+"']]//span[@class='s-check']");
    }

    public void clickToActionBtn() {
        waitElementToBeClickable("//span[text()='Action']");
        clickOnElement("//span[text()='Action']");
    }

    public void clickToDeleteItem() {
        clickOnElement("//span[@class='s-dropdown-item']");
        clickOnElement("//button[@class='s-button is-danger']//span[contains(.,'Delete')]");
    }

    public void verifyRedirectNotFoundOnList() {
        verifyElementVisible("//h2[text()='No redirects found']", true);
    }

    public void selectAllRedirect() {
        clickOnElement("//tr[@class]//span[@class='s-check']");
    }

    public void verifyRedirectListEmpty() {
        verifyElementVisible("//td[@class='no-product']", true);
    }

    public void verifyDeleteBtnUndisplayed() {
        verifyElementVisible("//button[@class='s-button is-danger']", false);
    }

    public void clickToRemoveMenuItem(String _label) {
        clickOnElement("//div[@class='page-menu-form__menu-item'][descendant::div[normalize-space()='"+_label+"']]//i[contains(@class,'trash')]");
    }

    public void verifyMenuItemDeleted(String _label) {
        verifyElementVisible("//li[@class='footer_link mb10']/a[contains(.,'"+_label+"')]", false);
    }

    public void verifySaveBtnDisabled() {
        verifyElementPresent("//button[@disabled and contains(.,'Save')]", true);
    }

    public void settingLegalTemplate(String _label) {
        clickOnElement("//a[descendant::p[contains(.,'"+_label+"')]]");
    }

    public void addFromTemplate(String _label) {
        clickOnElement("//div[contains(@class,'white-bg')][descendant::label[contains(.,'"+_label+"')]]//button");
    }

    public void inputNewTemplate(String _label, String _value) {
        waitClearAndType("//div[contains(@class,'white-bg')][descendant::label[contains(.,'"+_label+"')]]//textarea", _value);
    }

    public void verifyLegalTemplate(String _label, boolean _isCheck) {
        verifyElementVisible("//div[@class='policy-content' and contains(.,'"+_label+"')]", _isCheck);
    }

    public void switchToMenuTab(String _label) {
        clickOnElement("//span[contains(.,'"+_label+"')]");
    }

    public void verifyPageLocked() {
        verifyElementVisible("//span[contains(.,'page can’t be found')]", true);
    }
}
