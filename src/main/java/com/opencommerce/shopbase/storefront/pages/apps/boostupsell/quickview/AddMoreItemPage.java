package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.quickview;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AddMoreItemPage extends SBasePageObject {
    public AddMoreItemPage(WebDriver driver) {
        super(driver);
    }

    public void verifyBtnAddMoreItemOnProduct(String prod) {
        String xpathBtnAddMoreItem = "//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='"+prod+"']]//div[contains(text(),'Add more items')]";
        waitForPageLoad();
        waitForEverythingComplete();
        verifyElementPresent(xpathBtnAddMoreItem, true);
    }

    public void verifyAddmoreItemShown(boolean isShown) {
        waitABit(2000);
        verifyElementPresent("//div[@class='app-modal upsell-quick-view']", isShown);
    }

    public void verifyTitleProductOnPopup(String expProduct) {
        String actProduct = getText("//div[contains(@class,'qv-product-name')]").toUpperCase();
        assertThat(actProduct).contains(expProduct.toUpperCase());

    }

    public void verifyPriceProduct(String expPrice) {
        String actPrice = getText("//div[@class='qv-product-price upsell-color-price']");
        comparePrice(actPrice, expPrice);
    }

    public void selectOptionVariantOnPopup(String nameOption, String valueOption) {
        String xpathOption = "//div[@class='app-modal upsell-quick-view']//*[contains(text(),\"" + nameOption + "\")]//ancestor::div[@class='qv-product-variants-wrapper']//a[text()[normalize-space()='" + valueOption + "']]";
        clickOnElement(xpathOption);
    }

    public void increaseQuuanatity(String quantity) {
        String xpathQuantityField = "//div[@class='qty-input quantity']//input[@class='quantity__num']";
        waitClearAndType(xpathQuantityField, quantity);
    }

    public void clickBtnClosePopup() {
        clickOnElement("//button[@class='app-modal__close']");
    }

    public void clickAddToCart() {
        clickOnElement("//div[@class='upsell-quick-view__add-to-cart']//button");
    }

    public void clickAddMore(String prod) {
        clickOnElement("//div[contains(@class,'product-cart__details') and descendant::a[normalize-space()='"+prod+"']]//div[contains(text(),'Add more items')]");
        waitForEverythingComplete();
    }

    public void verifyHighligtOnOption(String option) {
        waitForEverythingComplete();
        waitABit(2000);
        String xpath = "//div[contains(@class,'app-custom-option__label') and child::*[text()=\"" + option + "\"]]//following::div[text()[normalize-space()='Please finish this field']]";
        verifyElementPresent(xpath, true);
    }

    public void inputCustomOption(String option, String value) {
        String xpathOption = "//div[child::*[text()='" + option + "']]//following::input[contains(@class,'app-custom-option__input-option')]";
        waitTypeAndEnter(xpathOption, value);
    }


    public void waitForPopupAddMoreNotShown() {
        String xpath = "//div[@class='app-modal upsell-quick-view']";
        waitUntilElementInvisible(xpath,5);
    }
}
