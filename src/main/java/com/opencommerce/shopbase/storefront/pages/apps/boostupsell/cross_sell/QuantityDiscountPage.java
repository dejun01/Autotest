package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class QuantityDiscountPage extends SBasePageObject {

    public QuantityDiscountPage(WebDriver driver) {
        super(driver);
    }

    public void verifyWidgetQuantityShow(boolean isShowOffer) {
        verifyElementPresent("//div[@class='app-upsell']/div[contains(@class,'upsell-quantity')]", isShowOffer);
    }

    public void verifyMessageOfffer(String messageOffer) {
        String xpathMesg = "//div[contains(@class,'upsell-quantity__title')]";
        String actMesg = getText(xpathMesg);
        assertThat(actMesg).contains(messageOffer);
    }

    public void verifyItemsDiscount(String item) {
        String xpathDiscount = "//div[@class='upsell-quantity__discount-text' and text()[normalize-space()=\"" + item + "\"]]";
        verifyElementPresent(xpathDiscount, true);
    }

    public void clickAddToCartOnWidget(String quantity) {
        String xpathBtnAddToCart = "//div[@class='upsell-quantity__discount' and descendant::div[contains(normalize-space(.),'" + quantity + " items')]]/following-sibling::div//button";
        clickOnElement(xpathBtnAddToCart);
        waitForPageLoad();
        waitForEverythingComplete();
    }

    public void verifyMessageDiscountOnCartPage(String expMsg) {
        String xpathMessageSuccess = "//div[contains(@class,'upsell-cart-alert')]";
        String actMsg;
        try {
            waitUntilElementVisible(xpathMessageSuccess, 10);
            actMsg = getText(xpathMessageSuccess);
        } catch (Exception e){
            refreshPage();
            waitElementToBePresent("//div[@id='cart']");
            waitUntilElementVisible(xpathMessageSuccess, 10);
            actMsg = getText(xpathMessageSuccess);
        }
        assertThat(actMsg).contains(expMsg);
    }

    public void settingToggleBtnAdd(boolean stsBtn) {
        String xpathToggle="//div[@class='list-offer__container-setting']";
        verifyCheckedThenClick(xpathToggle + "//input[@type='checkbox']", xpathToggle + "//span[@class='s-check']", stsBtn);
    }

    public void verifyBtnAddShown(boolean stsBtn) {
        verifyElementPresent("(//div[contains(@class,'upsell-quantity')]//button)[1]//span[text()='Add']",stsBtn);
    }

    public void clickButtonOnSectionQuantity(){
        clickOnElement("//div[contains(@class,'quantity-offer')]//button");
    }

    public String getButtonLabel(){
        return getText("//div[contains(@class,'quantity-offer')]//button//span");
    }

    public void clickButtonOnPopup(String label){
        clickOnElement("//div[@class='s-modal-footer']//button[normalize-space()='"+label+"']");
    }

    public void selectOfferOnModal(String offerName){
        clickOnElementByJs("//div[contains(@class,'offer-name') and normalize-space()='"+offerName+"']");
    }

    public void changeStatusOfferInProduct(String offer, boolean isActive){
        checkCheckbox("//tr[descendant::div[normalize-space()='"+offer+"']]", isActive);
    }
}
