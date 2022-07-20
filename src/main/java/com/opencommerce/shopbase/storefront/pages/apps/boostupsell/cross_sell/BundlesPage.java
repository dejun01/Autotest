package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.cross_sell;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BundlesPage extends SBasePageObject {
    public BundlesPage(WebDriver driver) {
        super(driver);
    }

//    public String xpathBundleActive() {
//        String xpathBundleActive = "";
//        int row = countElementByXpath("//div[@class='bundle__offer']//div[contains(@class,'VueCarousel-slide')]");
//        if (row != 1) {
//            xpathBundleActive = "//div[@class='bundle__offer']//div[contains(@class,'VueCarousel-slide-active')]";
//        }
//        return xpathBundleActive;
//    }

    public void verifyOfferMessageShown(String offerMessage) {
        String xpathMessageOffer = "//div[contains(@class,'VueCarousel-slide-active')]//div[contains(@class,'bundle__offer-heading')]";
        String message = getText(xpathMessageOffer);
        assertThat(message).isEqualTo(offerMessage);
    }

    public void verifyOfferBundleShown(boolean isShow) {
        verifyElementPresent("//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-container']", isShow, 5);
    }

    public void verifyPriceOfferCrossSell(String sPrice) {
        String _price = waitElementToBePresentThenScrollIntoView("//div[contains(@class,'VueCarousel-slide-active')]//span[contains(@class,'bundle__offer-total-price--detail')][1]").getText();
        comparePrice(_price, sPrice);
    }

    public void selectVariantOnProduct(String product, String variant) {
        String xpath = "//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-product' and descendant::a[text()[normalize-space()='" + product + "']]]//div[contains(@class,'bundle__offer-select-desktop')]//select";
        clickOnElement(xpath);
        verifyElementPresent(xpath, true);
        clickOnElement(xpath + "//option[text()[normalize-space()=\"" + variant + "\"]]");
    }

    public int getNumberProductOffer() {
        int row = 0;
        String xpath = "//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-product']";
        if (isElementExist(xpath))
            row = countElementByXpath(xpath);
        return row;
    }

    public void clickToNextOffer() {
        String xpathNextOffer = "//div[@class='bundle__offer']//button[@aria-label='Next page']";
        waitUntilElementVisible(xpathNextOffer, 10);
        clickOnElement(xpathNextOffer);
    }

    public void clickButtonAddAllToCart() {
        scrollIntoElementView("//div[contains(@class,'VueCarousel-slide-active')]//div[contains(@class,'bundle__offer')]//button");
        clickOnElement("//div[contains(@class,'VueCarousel-slide-active')]//div[contains(@class,'bundle__offer')]//button");
        waitForPageLoad();
    }

    public void clickQuickViewProduct(String product) {
        String xpathQuickview = "//div[@class='bundle__offer-container']//img[@alt=\"" + product + "\"]";
        hoverThenClickElement(xpathQuickview, xpathQuickview);
    }

    public void verifyProductOnBundle(String prod) {
        verifyElementPresent("//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-product' and descendant::a[text()[normalize-space()=\"" + prod + "\"]]]", true, 3);
    }

    public String getTotalPriceOffer() {
        String price = getText("//div[contains(@class,'VueCarousel-slide-active')]//span[contains(@class,'bundle__offer-total-price--detail')][1]");
        return price(price);
    }

    public List<String> getListProductBundle() {
        String xpathName = "//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-product']//a";
        return getListText(xpathName);
    }

    public void verifyListProducts(List<String> nameProductOnBundle, List<String> nameProductOnOrder) {
        assertThat(nameProductOnBundle).isEqualTo(nameProductOnOrder);
    }

    public void verifyImgProductShown(String nameProduct) {
        verifyElementPresent("//div[contains(@class,'VueCarousel-slide-active')]//div[@class='bundle__offer-container']//img[@alt=\"" + nameProduct + "\"]", true);
    }

    public void verifyOfferMessageShownOnSmart(String msg) {
        String xpathMessageOffer = "//div[contains(@class,'VueCarousel-slide-active')]//div[contains(@class,'bundle__offer-heading')]";
        verifyElementText(xpathMessageOffer, msg);
        String message = getText(xpathMessageOffer);
        assertThat(message).isEqualTo(msg);
    }

    public float getCompareTotalPrice() {
        return getPrice("//div[@class='bundle__offer-total-price']//span[contains(@class,'upsell-color-compare-price')]");
    }

    public void verifyComparePriceNotShown() {
        verifyElementPresent("//div[@class='bundle__offer-total-price']//span[contains(@class,'upsell-color-compare-price')]", false, 5);
    }

    public void inputToTextBoxOnBundle(String label, String text) {
        waitClearAndType("//div[contains(@class,'s-form-item') and descendant::label[contains(text(),\"" + label + "\")]]//input[@class='s-input__inner']", text);
    }

    public void verifyOfferShow(String offerName, boolean isShow) {
        waitForEverythingComplete(5);
        verifyElementPresent("//div[normalize-space()='" + offerName + "']", isShow);
    }

    public void verifyProduct(String offerName, String product) {
        verifyElementPresent("//tr[descendant::div[normalize-space()='" + offerName + "']]//descendant::a[@title='" + product + "']", true);
    }

    public void verifyUpdatedAt(String offerName, String date) {
        verifyElementContainsText("//tr[descendant::div[normalize-space()='" + offerName + "']]//td[@class='updated-at']", date);
    }

    public void verifyStatus(String offerName, boolean status) {
        verifyElementSelected("//tr[descendant::div[normalize-space()='" + offerName + "']]//input", status);
    }

    public void openOfferListFromProductAdmin(String label) {
        if (isElementExist("//button[contains(@class,'s-modal-close')]")) {
            clickOnElement("//button[contains(@class,'s-modal-close')]");
        }
        clickOnElement("//div[h3[normalize-space()='" + label + "']]//a");
        waitForEverythingComplete();
    }

    public void clickSeeMore() {
        clickOnElement("//div[contains(@class,'show-more')]");
    }

    public void selectProductOnModal(String products) {
        String[] values = products.split(",");
        for (String vl : values) {
            waitUntilElementVisible("//div[contains(@class,'s-modal-content')]", 20);
            clickAndClearThenType("//div[@class='popup-body']//input", vl);
            waitABit(1000);
            String xpath = "(//div[@class='product-selector']//div[contains(@class,'product-selector__item') and  descendant::div[text()=\"" + vl + "\"]])[1]//span[contains(@class,'s-icon')]";
            try {
                clickOnElement(xpath);
            } catch (StaleElementReferenceException ex) {
                clickOnElement(xpath);
            }
            if (isElementExist("//div[contains(@class,'input-search')]//i[contains(@class,'close')]")) {
                clickOnElement("//div[contains(@class,'input-search')]//i[contains(@class,'close')]");
            }
        }
        waitABit(2000);
        assertThat(countElementByXpath("//div[@class='product-selected-inner']//div[contains(@class,'product-selector__item')]")).isEqualTo(values.length + 1);
    }

    public void clickButtonOnSectionBundles() {
        clickOnElement("//div[contains(@class,'bundle-offer')]//button");
    }

    public String getButtonLabel() {
        return getText("//div[contains(@class,'bundle-offer')]//button//span");
    }

    public void clickButtonOnPopup(String label) {
        clickOnElement("//div[@class='s-modal-footer']//button[normalize-space()='" + label + "']");
    }

    public void selectBundleOnModal(String offerName) {
        clickOnElement("//div[normalize-space()='" + offerName + "']");
    }

    public void clearSelectedProducts() {
        while (isElementExist("//span[@data-label='Remove this product']//span")) {
            clickOnElement("//span[@data-label='Remove this product']//span");
        }
    }

    public void settingPrepurchaseOffers(String label, String prepurchaseOffers) {
        selectDdlWithLabel(label, prepurchaseOffers);
    }

    public void settingIncartOffers(String label, String incartOffers) {
        selectDdlWithLabel(label, incartOffers);
    }

    public void settingQuantityDiscounts(String label, String quantityDiscounts) {
        if (!quantityDiscounts.isEmpty())
            selectDdlWithLabel(label, quantityDiscounts);
    }

    public void settingBundles(String label, String bundles) {
        selectDdlWithLabel(label, bundles);
    }

    public void settingAccessories(String label, String accessories) {
        selectDdlWithLabel(label, accessories);
    }

    public void clickAddToCartPPC() {
        clickOnElement("(//div[contains(@class,'pre-purchase__content ')]//button[normalize-space()='ADD TO CART'])[1]");
    }

    public void clickBtnSave() {
        if (isElementExist("//div[contains(@class,'save-setting-fixed__button')]//button[normalize-space()='Save changes']")) {
            clickOnElement("//div[contains(@class,'save-setting-fixed__button')]//button[normalize-space()='Save changes']");
        }
    }
    public void clickAddProduct(String product) {
        clickOnElement("(//div[contains(@class,'upsell-quantity__discounts')]//button[normalize-space()='Add'])[1]");
    }

    public void clickProductOnIncart() {
        clickOnElement("//div[contains(@class,'upsell-cart-offer')]//button");
        waitForPageLoad();
    }

    public void verifyCTASetting(String url) {
        String shop = LoadObject.getProperty("shop");
        switchToTheFirstTab();
        waitABit(5000);
        String urlAR = getCurrentUrl();
        assertThat(urlAR).contains("https://" + shop + url);
    }

    public void clickAddToCartAccessories() {
        scrollIntoElementView("(//div[contains(@class,'upsell-accessory')]//button[normalize-space()='ADD'])[1]");
        clickOnElement("(//div[contains(@class,'upsell-accessory')]//button[normalize-space()='ADD'])[1]");
        waitForPageLoad();
    }

    public void clickButtonOnCutomOptionPopup(String label) {
        clickOnElement("//*[contains(@class,'bundle-popup__container')]//button[normalize-space()='" + label + "']");
    }

    public void verifyAddCustomOptionSuccess(String nameProduct, boolean success) {
        verifyElementPresent("//*[contains(@alt, '" + nameProduct + "')]//ancestor::*[contains(@role, 'tabpanel')]//*[contains(@class, 'product-slide-image__check-icon')]", success);
    }

    public void verifyNameProductCustomOption(String nameProduct) {
        verifyElementText("//div[@style='']//*[contains(@class, 'upsell-product__product-name')]", nameProduct);
    }

    public void verifyPriceProductCustomOption(String price) {
        String _price = waitElementToBePresentThenScrollIntoView("//div[@style='']//*[contains(@class, 'qv-product-price')]").getText();
        comparePrice(_price, price);
    }

    public void verifyMsgTextValidate(String message) {
        verifyElementText("//*[@style='']//*[contains(@class, 'app-custom-option__text')]//*[contains(@class, 'app-msg-validate')]", message);
    }

    public void inputTextCustomOption(String text) {
        clickAndClearThenType("//*[@style='']//*[contains(@class, 'app-custom-option__input-option')]", text);
    }

    public void verifyMsgUploadImgValidate(String message) {
        verifyElementText("//*[@style='']//*[contains(@class, 'app-custom-option__file')]//*[contains(@class, 'app-msg-validate')]", message);
    }

    public void uploadImageCustomOption(String fileName) {
        chooseAttachmentFile("//*[contains(@class,'app-custom-option__file')]//*[contains(@class, 'input-file')]", fileName);
        waitUntilInvisibleLoading(10);
    }

    public void verifyMsgRadioOptionValidate(String message) {
        verifyElementText("//*[@style='']//*[contains(@class, 'app-custom-option__radio')]//*[contains(@class, 'app-msg-validate')]", message);
    }

    public void selectOptionCustomOption(String option) {
        selectRadioButtonPhub("//*[contains(@class, 'app-custom-option__radio')]//span[normalize-space()='" + option + "']", true);
    }

    public void selectOptionCheckboxCustomOption(String option) {
        checkCheckbox("//*[contains(@class, 'app-custom-option__checkbox')]//span[normalize-space()='" + option + "']", true);
    }

    public void pictureChoiceCustomOption(String option) {
        clickOnElement("//*[contains(@class, 'app-picture-choices')]//input[@value = '" + option + "']//following-sibling::span");
    }

    public void verifyClickImageIndex(int index) {
        clickOnElementByJs("(//*[@class = 'bundle-popup']//*[contains(@class,'product-slide-image')]//img)[" + index + "]");
    }

    public int getListSlideImage() {
        return countElementByXpath("//*[@class = 'bundle-popup']//*[contains(@class,'product-slide-image')]//img");
    }

    public void verifyTextProduct(String product, String text) {
        String xpathText = "//*[contains(text(),'"+ product +"')]//following::span[contains(., 'Text: "+ text +"')][1]";
        highlightElement(xpathText);
        verifyElementPresent(xpathText, true);
    }

    public void verifyImgProduct(String product, String img) {
        String xpathText = "//*[contains(text(),'"+ product +"')]//following::span[contains(.,'"+ img +"')][1]";
        highlightElement(xpathText);
        verifyElementPresent(xpathText, true);
    }

    public void verifyRadioProduct(String product, String option) {
        String xpathText = "//*[contains(text(),'"+ product +"')]//following::span[contains(., 'Radio option: "+ option +"')][1]";
        highlightElement(xpathText);
        verifyElementPresent(xpathText, true);
    }

    public void verifyPictureChoiceProduct(String product, String img) {
        String xpathText = "//*[contains(text(),'"+ product +"')]//following::span[contains(., 'Picture choice: "+ img +"')][1]";
        highlightElement(xpathText);
        verifyElementPresent(xpathText, true);
    }
}
