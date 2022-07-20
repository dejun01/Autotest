package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.quickview;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickViewPage extends SBasePageObject {
    public QuickViewPage(WebDriver driver) {
        super(driver);
    }

    public void verifyQuickViewShown() {
        String xpath = "//div[@class='upsell-quick-view__content']";
        waitForEverythingComplete();
        waitElementToBeVisible(xpath, 20);
    }

    public void verifyTitleQuickView(String title) {

        String xpathTitle = "//div[@class='app-modal upsell-quick-view']//div[contains(@class,'qv-product-name')]";
        String actTitle = getText(xpathTitle);
        assertThat(actTitle).isEqualTo(title);
    }

    public void verifyPriceProductOnQuickView(String price) {
        String xpathTitle = "//div[@class='app-modal upsell-quick-view']//div[@class='qv-product-price upsell-color-price']";
        String actPrice = getText(xpathTitle);
        comparePrice(actPrice, price);
    }

    public void verifyVariantTitle(String variants) {
        String xpathVariant = "//div[@class='app-modal upsell-quick-view']//div[contains(text(),'" + variants + "')]";
        verifyElementPresent(xpathVariant, true);
    }

    public void verifyThumailShow(boolean isShowThumdNail) {
        String xpath = "//div[@class='app-modal upsell-quick-view']//div[@id='product-image-carousel']";
        verifyElementPresent(xpath, isShowThumdNail);
    }

    public void verifyBundleShowOnQuickView(boolean isShowBundle) {
        String xpath = "//div[@class='app-modal upsell-quick-view']//div[@class='bundle__offer-container']";
        verifyElementPresent(xpath, isShowBundle);
    }

    public void verifyRecommendProduct(boolean isShowRecommendForYou) {
        String xpath = "//div[@class='app-modal upsell-quick-view']//div[@class='upsell-product-widget']";
        verifyElementPresent(xpath, isShowRecommendForYou);
    }

    public void clickAddToCart() {
        clickOnElement("//div[@class='upsell-quick-view__add-to-cart']//button");
        waitForEverythingComplete();
    }

    public void closeQuickview() {
        if (isElementExist("//div[@class='upsell-quick-view__content']")) {
            clickOnElement("//button[@class='app-modal__close']");
        }
    }

    public void verifyQuickviewShown(boolean isShow) {
        waitForEverythingComplete();
        String xpath = "//div[@class='upsell-quick-view__content']";
        if(isShow)
        verifyElementPresent(xpath, isShow, 3000);
        else
            waitForElementNotPresent(xpath);
    }

    public void verifyCloseQickView() {
        waitForElementNotPresent("//div[contains(@class,'quick-view')]");
    }
}
