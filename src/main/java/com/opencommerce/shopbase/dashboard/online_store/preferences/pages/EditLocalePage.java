package com.opencommerce.shopbase.dashboard.online_store.preferences.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditLocalePage extends SBasePageObject {

    public EditLocalePage(WebDriver driver) {
        super(driver);
    }

    public void selectOptionOnPrePage(String _label, String _value) {
        selectByText("//select[@label='"+_label+"']", _value);
    }

    public void checkAllLangOnPrePage() {
        clickOnElement("//select[@label='Select language']");
    }

    public void checkAllLangOneditPack() {
        clickOnElement("//section[@id='search-section']//select[descendant::option[contains(.,'English')]]");
    }

    public int countAllLangOnPrePage() {
        return countElementByXpath("//select[@label='Select language']//option");
    }

    public int countAllLangOnEditPack() {
        return countElementByXpath("//section[@id='search-section']//select[descendant::option[contains(.,'English')]]//option");
    }

    public void verifySelectedValueOnEditPack(String _option) {
        String actual = getSelectedValue("//section[@id='search-section']//select[descendant::option[contains(.,'"+_option+"')]]");
        assertThat(actual).isEqualTo(_option);
    }

    public void selectOptionOnEditPack(String _option) {
        selectByText("//section[@id='search-section']//select[descendant::option[contains(.,'"+_option+"')]]", _option);
    }

    public void searchKeyOnTable(String _value) {
        waitClearAndType("//input[@placeholder='Search keys or translations']", _value);
    }

    public void countResultKeyOnTable(int result) {
        countElementByXpath("//tbody/tr");
    }

    public void hoverOnKey(String _key) {
        hoverOnElement("//tr[descendant::td[contains(.,'"+_key+"')]]//textarea");
    }

    public void verifyEditIcon() {
        verifyElementPresent("//span[contains(@class,'pencil-icon') and contains(@class,'hidden')]", false);
    }

    public void inputToEditPhrase(String _key, String _value) {
        clickOnElement("//tr[descendant::td[contains(.,'"+_key+"')]]//textarea");
        waitClearAndType("//tr[descendant::td[contains(.,'"+_key+"')]]//textarea", _value);
    }

    public void verifyEditedPhrase(String _value) {
        verifyElementPresent("//textarea[@title='"+_value+"']", true);
    }

    public void actionWithPhrase(String _key, String _action) {
        clickOnElement("//tr[descendant::td[contains(.,'"+_key+"')]]//i[contains(@class,'"+_action+"')]");
    }

    public void selectSortAction(String _label, String _value) {
        selectByText("//th[descendant::span[text()='"+_label+"']]//select", _value);
    }

    public void selectRowPerPage(int _value) {
        selectByText("//section[descendant::p[normalize-space()='Row per page']]//select", String.valueOf(_value));
    }

    public int countAllKey() {
        return countElementByXpath("//tbody//tr");
    }

    public void verifySortAscending() {
        verifySortedAscending("//tbody//div[@class='table-data']");
    }

    public void verifySortDescendingAction() {
        verifySortedDescending("//tbody//div[@class='table-data']");
    }

    public void clickToActionBtn() {
        clickOnElement("//button[@class='s-button' and contains(.,'Actions')]");
    }

    public void selectEditLangAction() {
        clickOnElement("//span[@class='s-dropdown-item' and contains(.,'Edit language')]");
    }

    public void clickOnLocaleBtn() {
        scrollIntoElementView("//div[@class='currency-language relative']");
        clickOnElement("//div[@class='currency-language relative']");
    }

    public void selectLangOnSF(String _label) {
        clickOnElement("//span[@class='currency-dropdown__item__name' and contains(text(),'"+_label+"')]");
    }

    public void saveLangBtn() {
        clickOnElement("//div[contains(@class,'select-currency-language')]//button[contains(@class,'btn-primary')]");
        waitForPageLoad();
    }

    public void verifyThemePhrase(String _lang) {
        verifyElementPresent("//header//p[text()='"+_lang+"']", true);
    }

    public void verifyAppPhrase(String _lang) {
        scrollIntoElementView("//h1[contains(@class,'product__name')]");
        verifyElementPresent("//div[@class='copt-countdown-timer__message' and contains(.,'"+_lang+"')]", true);
    }

    public void waitSavedMessInvisible() {
        waitUntilElementInvisible("//div[contains(@class,'is-success')]", 5);
    }

    public void closeLangPopup() {
        clickOnElement("//div[contains(@class,'icon-close')]");
    }
}
