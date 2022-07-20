package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell;

import com.opencommerce.shopbase.dashboard.settings.pages.ShippingPage;
import common.SBasePageObject;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessoryUpsellPages extends SBasePageObject {

    public AccessoryUpsellPages(WebDriver driver) {
        super(driver);
    }

    public void clickDelete(){
        clickOnElement("//span[@class='s-dropdown-item' and normalize-space()='Delete']");
    }

    public void clickBtnAddToCartOnPopup(){
        clickOnElement("//div[contains(@class,'upsell-quick-view')]//button[@data-button='add-to-cart']");
        waitForPageLoad();
    }

    public void inputCustomOption(String customOption){
        clickAndClearThenType("//*[@placeholder='Please fill out this field']", customOption);
    }

    public void verifyDisableBtnAdd(String product){
        verifyElementVisible("//div[@class='upsell-accessory-products__action']//button[@disabled]", true);
    }

    public void chooseProductVariants(String _label, String _value){
        String xpath = "//div[contains(@class,'variant-label') and contains(.,'"+_label+"')]//a[contains(@class,'product-option') and text()='"+_value+"']";
        boolean prvExisted = isElementVisible(xpath, 3);
        if(prvExisted){
            clickOnElement(xpath);
        }
    }

    public void verifyProductTarget(String productTarget){
        String _value = productTarget.toUpperCase();
        verifyElementText("//*[contains(@class,'product-cart__name')]//a", _value);
    }

    public void verifyVariant(String variant){
        if (!variant.isEmpty()){
            verifyElementText("//*[contains(@class,'product-cart__options')]", variant);
        }
    }

    public void verifyCustomOption(String customOption){
        if (!customOption.isEmpty()){
            verifyElementText("//p[contains(@class,'product-cart__property')]", customOption);
        }
    }

    public void verifyPrice(String price){
        if (!price.isEmpty()){
            verifyElementText("//*[contains(@class,'product-cart__price')]//span", price);
        }
    }

    public void clickAddAccessoryBtn(String _label) {
        clickOnElement("//div[contains(@class,'app-items-center ')][descendant::a[text()='"+_label+"']]//span");
    }

    public void clickOnViewMoreProd() {
        clickOnElement("//div[contains(@class,'show-more')]");
    }

    public boolean isSubmitBtnVisible() {
        return isElementVisible("//button//span[contains(text(),'Submit offer')]", 3);
    }
}
