package com.opencommerce.shopbase.storefront.pages.apps.boostupsell.upsell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class PostPurchaseOfferPage extends SBasePageObject {
    public PostPurchaseOfferPage(WebDriver driver) {
        super(driver);
    }

    public void verifyOfferPostPurchasePopup(Boolean isShow) {
        if (isShow) {
            waitUntilElementVisible(("//div[@class='post-purchase__body']"), 30);
            verifyElementPresent(("//div[@class='post-purchase__body']"), true, 30);

        } else waitForElementNotPresent("//div[@class='post-purchase__body']");
    }

    public void verifyOfferTitle(String title) {
        verifyElementText("//div[contains(@class,'post-purchase__heading')]", title);
    }

    public void verifyMessage(String mess) {
        verifyElementText("//div[contains(@class,'post-purchase__description')]", mess);
    }

    String xpathProduct = "//div[@class='upsell-only-blocks__detail' and descendant::*[text()[normalize-space()=\"%s\"]]]";

    public void verifyPriceDiscountPopup(String prod, Float price_discount) {
        String xpath = "(" + String.format(xpathProduct, prod.trim()) + "//span[@slot='custom-text']/following-sibling::span)[1]";
        Float price_discount_ac = get_price(xpath);
        assertThat(price_discount_ac).isEqualTo(price_discount);
    }

    public Float get_price(String xpath) {
        verifyElementPresent(xpath, true, 5);
        String sPrice = waitElementToBePresentThenScrollIntoView(xpath).getText().trim();
        String _sPrice = sPrice.replace("₫", "").replace("$", "").replace(",", "").replace("£", "").trim();
        Float price = Float.parseFloat(_sPrice);
        System.out.println("Price actual :" + price);
        return price;
    }


    public void waitUntilInvisibleLoadingDoneComplete(int timeout) {
        waitUntilElementInvisible("//button[@class='widgets--btn widgets--btn-null btn--done loading-button']", timeout);
    }

    public void clickConfirmPaypalBtn() {
        verifyElementPresent("//div[@class='buttons reviewButton']/input", true, 50);
        clickOnElementByJs("//div[@class='buttons reviewButton']/input");
    }

    public void clickOnNoThankByTitle(String prod) {
        String xPath = String.format(xpathProduct, prod.trim()) + "//following::div[contains(@class,'upsell-only-blocks__action')]/a";
        if (isElementVisible(xPath, 10))
            clickOnElement(xPath);
    }


    public int countNumberOfProductOnUpsell() {
        waitForEverythingComplete();
        waitABit(2000);
        String xpathRows = "//div[@class='upsell-only-blocks__detail']";
        int actualNumber = countElementByXpath(xpathRows);
        return actualNumber;
    }

    public int getIndexOfProductInOffer(String productName) {
        String xpath = String.format(xpathProduct, productName.trim());
        waitForEverythingComplete();
        if (isElementExist(xpath))
            return countElementByXpath(xpath + "/preceding::div[contains(@class,'app-py24 app-relative upsell-only-block')]") + 1;
        return 0;
    }

    public void clickOnPassPostPurchase() {
        String xPath = "//div[@class='upsell-only-blocks__detail']//following::div[contains(@class,'upsell-only-blocks__action')]/a";
        if (isElementVisible(xPath, 10))
            clickOnElementByJs(xPath);
    }

    String xpathPP = "(//div[@class='upsell-only-blocks__detail'])[%d]";

    public void clickToAddToOrder(int index) {
        String xpath = String.format(xpathPP, index) + "/following-sibling::div[contains(@class,'upsell-only-blocks__action')]/button";
        clickOnElementByJs(xpath);
    }

    public void inputCustomOption(String option, String value, int index) {
        String xpathOption = String.format(xpathPP, index) + "//div[child::*[text()='" + option + "']]//following::input[contains(@class,'app-custom-option__input-option')]";
        waitTypeAndEnter(xpathOption, value);
    }

    public void clickPersonalizationInformation(int index) {
        String xpath = String.format(xpathPP, index) + "//div[text()[normalize-space()='Personalization information']]";
        scrollIntoElementView(xpath);
        clickOnElementByJs(xpath);
    }

    public void selectVariantByIndex(String variant, int index) {
        String xpath = String.format(xpathPP, index) + "//select";
        clickOnElement(xpath);
        verifyElementPresent(xpath, true);
        clickOnElement(xpath + "//option[text()[normalize-space()=\"" + variant + "\"]]");
    }

    public String getProductNameByIndex(int index) {
        String xpath = String.format(xpathPP, index) + "//div[@class='upsell-only-blocks__product-name upsell-color-product-name']";
        return getText(xpath);
    }

    public String getProductOriginalPriceByIndex(int index) {
        String price = "";
        String xpath = String.format(xpathPP, index) + "//div[contains(@class,'upsell-only-blocks__product-original-price')]";
        if (isElementExist(xpath, 2)) {
            price = price(getText(xpath));
        }
        return price;
    }

    public String getProductPriceByIndex(int index) {
        String xpath = String.format(xpathPP, index) + "//div[contains(@class,'product-price')]";
        return price(getText(xpath).replace("Only", "").trim());
    }

    public String getBlockSaveValueByIndex(int index) {
        String xpath = String.format(xpathPP, index) + "//div[contains(@class,'post-purchase__block-save')]";
        if (isElementExist(xpath, 2)) {
            return getText(xpath);
        }
        return "";
    }

    public String getPriceAfterDiscount(String productName) {
        String xPath = "//a[div[text()[normalize-space()='" + productName + "']]]//preceding-sibling::div//div[contains(@class,'product-price')]";
        String price = "";
        if (isElementVisible(xPath, 3))
            price = getText(xPath);
        return price;
    }

    public String getOriginPrice(String productName) {
        String xPath = "//a[div[text()[normalize-space()='" + productName + "']]]//preceding-sibling::div//div[contains(@class,'original-price')]";
        String price = "";
        if (isElementVisible(xPath, 3))
            price = getText(xPath);
        return price;
    }

    public void verifyMgsSuccessOrder() {
        String mgs = "Congrats! You have successfully placed your order!";
        waitForElementToPresent("//div[contains(@class,'post-purchase__alert')]");
        verifyElementText("//div[contains(@class,'post-purchase__alert')]", mgs);
    }

    public void clickClosePopup() {
        clickOnElement("//div[contains(@class,'post-purchase__close')]");
    }

    public void isShowPopup(Boolean isShow, String offerTitle) {
        String xpath = "//*[normalize-space() = '" + offerTitle + "']";
        verifyElementPresent(xpath, isShow);
    }

    public boolean isPPCShow() {
        String xpath = "//div[@class='post-purchase__header']";
        return isElementVisible(xpath, 20);
    }

    public void addProductPPC(String productName) {
        String xpath = "//div[text()[normalize-space()='" + productName + "']]//following::span[text()[normalize-space()='Add to order']][1]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }
}
