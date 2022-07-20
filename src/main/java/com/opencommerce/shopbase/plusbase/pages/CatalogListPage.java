package com.opencommerce.shopbase.plusbase.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogListPage extends SBasePageObject {
    public CatalogListPage(WebDriver driver) {
        super(driver);
    }

    public void clickLabelFilter(String label) {
        clickOnElement("//*[text()[normalize-space()='" + label + "'] and ancestor::*[contains(@class, 'filter')]]");
    }

    public void choiceConditions(String label, String conditions) {
        clickOnElement("//div[contains(@class, 'active') and child::div[normalize-space()='" + label + "']]//span[contains(text(), '" + conditions + "')]");
    }

    public void fillValue(String label, String value) {
        String xpath = "//div[contains(@class, 'active') and child::div[normalize-space()='" + label + "']]//input[@type='number']";
        waitClearAndType(xpath, value);
    }

    public void verifyResultFilter(String label, String conditions, String value) {
        String xpathEle = "//div//child::div[normalize-space()='" + label + "']";
        for (int i = 1; i <= countElementByXpath(xpathEle); i++) {
            String xpath = "(//div[child::div[text()[normalize-space()='" + label + "']]]//div[contains(@class,'price-value')])[" + i + "]";
            String xpathOutOfStock = "//div[@class = 'catalog-products-review row']/*[contains(@class, 'catalog-products-review__product-review')][" + i + "]//span[text()[normalize-space()='Out of stock']]";
            float price = 0;
            if (!isElementExist(xpathOutOfStock)) {
                if (getText(xpath).contains("-")) {
                    price = removeCurrency(getText(xpath).split("-")[0]);
                } else {
                    price = getPrice(xpath);
                }
                if (conditions.contains("greater")) {
                    assertThat(price).isGreaterThanOrEqualTo(Float.parseFloat(value));
                }
                if (conditions.contains("less")) {
                    assertThat(price).isLessThanOrEqualTo(Float.parseFloat(value));
                }
            }
        }
        if (countElementByXpath(xpathEle) == 0) {
            verifySearchNoData();
        }
        verifyElementVisible("//div[contains(@class,'sb-tag') and contains(., '" + conditions + ": " + value + "')]", true);
    }

    public void moveToProductDetail(String product) {
        clickOnElement("//*[contains(text(), '" + product + "')]");
    }

    public void verifyProductDetail(String product) {
        verifyElementVisible("//*[contains(text(),'" + product + "')]", true);
    }

    public void verifyResult(String btnClaer) {
        verifyElementVisible("//button[child::span[normalize-space()='" + btnClaer + "'] and @disabled]", true);
    }

    public void clickCategoryOrSubCategoryName(String option, String name) {
        String xpathcate = "//div[contains(@class, 'sb-tab-navigation__item') and child::div[normalize-space()='" + name + "']]";
        String xpathSubCate = "//div[contains(@class, 'sub-categories') ]//button[child::span[normalize-space()='" + name + "']]";
        String xpath = "category".equals(option) ? xpathcate : xpathSubCate;
        clickOnElement(xpath);
    }

    public void removedTagWithValue(String tag) {
        clickOnElement("//*[descendant::*[text()[normalize-space()='Tag: " + tag + "']]]/span//span");
    }

    public void verifyProduct(String product) {
        try {
            verifyTextPresent(product, true);
        } catch (Throwable t) {
            refreshPage();
            waitABit(4000);
            verifyTextPresent(product, true);
        }
    }

    public void verifySearchNoData() {
        verifyTextPresent("Sorry, there's no matched product for", true);
    }

    public void verifyBaseCost(String baseCost) {
        verifyElementVisible("//div[contains(@class, 'price__base-cost') and contains(normalize-space(),'Product cost') and contains(normalize-space(),'" + baseCost + "')]", true);
    }

    public void verifySellingPrice(String sellingPrice) {
        verifyElementVisible("//div[contains(@class, 'price__selling-price') and contains(normalize-space(),'Selling price') and contains(normalize-space(),'" + sellingPrice + "')]", true);
    }

    public void moveToScreenInPlusbase(String label) {
        clickOnElement("//*[@class='menu_level_1']//li[descendant::span[normalize-space()='" + label + "']]");
        waitForEverythingComplete();
    }

    public void verifyVariant(String[] variant) {
        int count = countElementByXpath("//tbody/tr/td[@class='group-title']/span[last()]");
        if (variant.length == count) {
            for (int i = 1; i <= count; i++) {
                String text = getText("//tbody[" + i + "]/tr/td[@class='group-title']/span[last()]");
                assertThat(variant).contains(text.toLowerCase());
            }
        }
    }

    public void clickButtonImport(String buttonName) {
        String xpath = "//button[child::span[contains(text(), '" + buttonName + "')]]";
        waitElementToBeVisible(xpath, 60);
        clickOnElement(xpath);
    }

    public boolean checkElementExit() {
        return isElementExist("//span[@class='sb-button--loading-state']");
    }

    public void clickDeleteButton(String label) {
        clickOnElement("//footer/button[normalize-space()='" + label + "']");
    }

    public void verifySellingPriceInProduct(String[] priceVariants) {
        int count = countElementByXpath("//tbody/tr/td[@class='group-title']/span[last()]");
        int index = countElement("Price");
        if (priceVariants.length == count) {
            for (int i = 1; i <= count; i++) {
                String text = getText("//tbody[" + i + "]/tr[last()]/td[" + index + "]");
                assertThat(text).isEqualTo(priceVariants[i - 1]);
            }
        }
    }

    private int countElement(String label) {
        return countElementByXpath("//table[@id='all-variants']//tr/th[normalize-space()='" + label + "']//preceding-sibling::th") + 1;
    }

    public void verifyProductGroup(String product, String text) {
        verifyElementVisible("//div[descendant::h3[normalize-space()='" + product + "'] and contains(@class, 'justify-space-between')]//span[contains(text(), '" + text + "')]", true);
    }

    public void clickProduct(String product) {
        clickOnElement("//tr[1]//span[normalize-space()='" + product + "']");
    }


    public void clickOnProduct(String productName) {
        waitForEverythingComplete();
        clickOnElementByJs("//div[child::*[text()[normalize-space()='" + productName + "']]]//preceding-sibling::div//img");
    }

    public String getProductName(String productName) {
        return getText("//div[contains(text(),'" + productName + "')]");
    }

    public void searchProduct(String productName) {
        waitForEverythingComplete();
        waitTypeAndEnter("//input[@class='s-input__inner product-search-input']", productName);
    }

    public void verifyButtonImportToStore(String status) {
        waitForEverythingComplete();
        String xpath = "//button[child::span[text()[normalize-space()='Import to your store']] and @disabled='" + status + "']";
        boolean expStatus = status.equals("disabled") ? true : false;
        verifyElementPresent(xpath, expStatus);
    }

    public String getProdName(String productName) {
        return getText("//div[contains(text(),'" + productName + "')]");
    }

    public float getValueMarkup(String product, String variant, String label) {
        int count = countHeaderItemMarkup(product, label);
        float val = getPrice("//*[@class='sb-card__body' and descendant::*[contains(@placeholder, '" + product + "')]]//tr[contains(., '" + variant + "')]/td[" + count + "]//span");
        return val;
    }

    public void fillDataMarkup(String product, String label, String variant, String val) {
        int count = countHeaderItemMarkup(product, label);
        waitClearAndType("//*[@class='sb-card__body' and descendant::*[contains(@placeholder, '" + product + "')]]//tr[contains(., '" + variant + "')]/td[" + count + "]//div[contains(@class, 'sb-input--small')][1]//input", val);
    }

    public int countHeaderItemMarkup(String product, String label) {
        return countElementByXpath("//*[@class='sb-card__body' and descendant::*[contains(@placeholder, '" + product + "')]]//th[contains(., '" + label + "')]//preceding-sibling::th") + 1;
    }

    public void clickVariant(String label, String val) {
        clickOnElement("//*[contains(@class, 'variant__attr') and contains(., '" + label + "')]//button[child::*[text()[normalize-space()='" + val + "']]]");
    }

    public float getSellingPriceCurrent(String label) {
        return getPrice("//*[child::*[text()[normalize-space()='" + label + "']]]/*[last()]");
    }

    public float getShippingCurrent(String label) {
        return getPrice("//*[contains(text(), '" + label + "')]/*");
    }

    public void clickTab(String tabName, String product) {
        clickOnElement("//*[contains(@class, 'tab-navigation__item') and contains(., '" + tabName + "')]");
    }

    public void focusOut() {
        clickOnElement("//*[text()[normalize-space()='Shipping method']]");
    }
}
