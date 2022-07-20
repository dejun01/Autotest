package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;


public class CreateOfferPage extends SBasePageObject {
    public CreateOfferPage(WebDriver driver) {
        super(driver);
    }

    public void chooseUpsellTypeOffer(String offerType) {
        String xpathUpsellType = "//div[contains(@class,'up-sell-types')]//div[text()='" + offerType + "']";
        waitElementToBeVisible(xpathUpsellType);
        clickOnElement(xpathUpsellType);
    }

    public void chooseTargetOrRecommendProductType(String targetType, String blockName) {
        String _xpathParent = "//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='" + blockName + "']]]";
        selectRadioButtonWithLabel(_xpathParent, targetType, 1, true);
    }

    public void clickButtonSelectCollectionOrProduct(String blockName) {
        String xpath = "//div[contains(@class,'usell-block-setup') and descendant::*[text()[normalize-space()='" + blockName + "']]]//button";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnCreateOffer() {
        waitElementToBeVisible("//*[contains(@class,'list-offer__container')]//span[contains(text(),'Create')]");
        clickOnElement(" //*[contains(@class,'list-offer__container')]//span[contains(text(),'Create')]");
    }

    public void scrollToDiscount() {
        scrollIntoElementView("//div[contains(@class,'usell-block-setup usell-discount')]");
    }

    public void turnOnOffDiscount(boolean isDiscount) {
        String xPathStatus = "//*[child::*[text()[normalize-space()=\"Offer's discount\"] or text()[normalize-space()='Offer’s discount']]]//input";
        String xPathCheckbox = "(//*[child::*[text()[normalize-space()=\"Offer's discount\"] or text()[normalize-space()='Offer’s discount']]])[1]//span[@class='s-check']";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, isDiscount);
    }
    //Quantity Discount

    public void clickAddMoreDiscount() {
        clickOnElement("//div[contains(@class,'add-more-condition')]//a");
    }

    public void inputQuantity(String quantity, int index) {
        String xpath = "//div[contains(@class,'quantity-min-quantity-wrap')]//input[@id='input-min-quantity-" + index + "']";
        waitClearAndType(xpath, quantity);
    }

    public void inputDiscount(String discount, int index) {
        String xpath = "//input[@id='input-value-discount-" + index + "']";
        waitClearAndType(xpath, discount);
    }

    public void chooseTyeDiscount(String typeDiscount, int index) {
        clickOnElement("(//div[@class='s-select usell-full-width'])[" + index + "]//option[@value='" + typeDiscount + "']");
    }

    public void deleteQuantity(int index) {
        clickOnElementByJs("//div[contains(@class,'items-start')]["+index+"]//i");
    }

    public int countDiscount(){
        return countElementByXpath("//div[contains(@class,'items-start')]");
    }

    public void enterNameOffer(String offerName) {
        String xpathNameOffer = "//div[contains(@class,'offer-name')]//input";
        clearTextByJS(xpathNameOffer);
        waitClearAndType(xpathNameOffer, offerName);
    }

    public void enterMessageOffer(String offerMessage) {
        String xpathNameOffer = "//div[contains(@class,'offer-message')]//input";
        waitElementToBeVisible(xpathNameOffer);
        waitForElementFinishRendering(xpathNameOffer);
        clickAndClearThenType(xpathNameOffer, offerMessage);
    }

    public void enterOfferTitle(String offerTitle) {
        String xpathNameOffer = "(//input[@id='input-offer-message'])[2]";
        clearTextByJS(xpathNameOffer);
        waitTypeAndEnter(xpathNameOffer, offerTitle);
    }

    public void chooseProductsTarrgetRule(String label) {
        clickOnElement("//div[child::p[normalize-space()='" + label + "']]");

    }

    public void saveChanges() {
        if (isElementExist("//div[@class='save-setting-fixed' and not(@style='display: none;')]")) {
            clickBtn("Save changes");
        }
    }

    public void removeAllProductSellected() {
        if (isElementExist("//span[@data-label='Remove All']/span"))
            clickOnElement("//span[@data-label='Remove All']/span");
    }

    public void navigateUpsell(String menu) {
        String xPath = "//div[contains(@class,'boost-upsell')]//following-sibling::ul//li[normalize-space()='" + menu + "']";
        waitUntilElementVisible(xPath, 60);
        clickOnElement(xPath);
    }

    public void onOffToggleSmart(String smartType, boolean status) {
        String xPathStatus = "//span[normalize-space()='" + smartType + "']/preceding-sibling::input";
        String xPathCheckbox = "//span[normalize-space()='" + smartType + "']/preceding-sibling::span";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, status);
    }

    public void clickBtnSetUpRecommendRule(String smartType) {
        clickOnElement("//span[normalize-space()='" + smartType + "']/following::button[1]");
    }

    public void chooseRecommendVariantWith(String recommendVariantWith) {
        clickOnElement("//option[@value='" + recommendVariantWith + "']");
    }

    public void checkBaseCategoryByLabel(String category, boolean isCheck) {
        checkCheckboxWithLabel(category, isCheck);
    }

    public int countBaseCategory() {
        return countElementByXpath("//label[@class='s-checkbox']");
    }

    public void checkBaseCategoryByIndex(int i, boolean isCheck) {
        checkCheckbox("(//label[@class='s-checkbox'])["+i+"]", isCheck);
    }

    public void removeAllProductSelected(){
        if(isElementExist("//*[@data-label='Remove All']//span"))
            clickOnElement("//*[@data-label='Remove All']//span");
    }

    public void enterPriorityOffer(String priority, String offerName) {
        String xpathPriorityOffer = "//*[normalize-space() = '"+ offerName +"']//ancestor::tr//*[contains(@class, 'priority')]//input";
        clearTextByJS(xpathPriorityOffer);
        waitClearAndTypeThenTab(xpathPriorityOffer, priority);
    }

    public void clickToEditOffer(String _label) {
        clickOnElement("//td[@class='offer-name pointer']//span[contains(text(),'"+_label+"')]");
    }

    public boolean isDiscountExisted() {
        return isElementVisible("//div[contains(@class,'usell-block-setup usell-discount')]", 3);
    }
}
