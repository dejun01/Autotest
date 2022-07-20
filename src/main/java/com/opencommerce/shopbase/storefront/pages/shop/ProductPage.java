package com.opencommerce.shopbase.storefront.pages.shop;

import common.CommonPageObject;
import common.SBasePageObject;
import common.utilities.LoadObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.opencommerce.shopbase.ProductVariable.shipFrom;
import static com.opencommerce.shopbase.ProductVariable.shipTo;
import static com.opencommerce.shopbase.dashboard.apps.printbase.pages.campaign.MyCampaignPage.downloadWebPage;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductPage extends SBasePageObject {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    String shopName = LoadObject.getProperty("shopname");
    String shop = LoadObject.getProperty("shop");

    public void clickBtnSearch() {
        if (isElementExist("//button[@data-testid='openSearchPanel']|//button[@aria-label='Search']")) {
            clickOnElement("//button[@data-testid='openSearchPanel']|//button[@aria-label='Search']");
        } else {
            String shop = getCurrentShop();
            navigateToUrl("https://" + shop + "/search");
        }
        waitForEverythingComplete();
    }

    public void gotoCartByLink() {
        String shop = getCurrentShop();
        navigateToUrl("https://" + shop + "/cart");
    }

    public String getCurrentShop() {
        String currenturl = getCurrentUrl().replace("https://", "");
        String[] _currenturl = currenturl.split("/");
        return _currenturl[0];
    }

    public void enterProduct(String productName) {
        String xPath = "//div[@class='search-input-group']//input[@placeholder='Search' or @id ='search']|//div[@id='search']//input[@placeholder='Enter keywords...' or @placeholder='Nhập từ khóa...']|//input[@placeholder='Search our store']";
        waitElementToBeVisible(xPath);
        waitForElementFinishRendering(xPath);
        clickOnElement(xPath);
        waitTypeAndEnter(xPath, productName);
        waitForTextToDisappear("Looking for...");
        waitUntilElementVisible("//*[@class='searching']", 10);
        waitABit(2000);
        String name = getTextValue(xPath);
        System.out.println("Current product name: " + name);
        System.out.println("Expect product name: " + productName);
        if (!productName.equals(name)) {
            String productSearch = productName.replace(" ", "+");
            navigateToUrl("https://" + shop + "/search?sort=&q=" + productSearch + "");
            waitForEverythingComplete();
            waitForTextToDisappear("Looking for...");
            waitUntilElementVisible("//*[@class='searching']", 10);
            waitABit(2000);
        }
    }

    public void selectProduct(String productName) {
        String xpath = "(//span[normalize-space(.)=\"" + productName + "\"]//ancestor::a)[1]|//div[contains(@class,'product')]//a[text()[normalize-space()=\"" + productName + "\"]]|//a[contains(@href,'products') and text()[normalize-space()='" + productName + "']]";
        String xpath1 = "//span[text()='" + productName + "']//ancestor::a";
        clickOnElement(xpath);
        waitABit(3000);
    }

    public void selectProductWithMappingPhub(String productName) {
        String xpath = "(//span[normalize-space(.)=\"" + productName + "\"]//ancestor::a//img)[1]|//div[contains(@class,'product')]//a[text()[normalize-space()=\"" + productName + "\"]]|//a[contains(@href,'products') and text()[normalize-space()='" + productName + "']]";
        int i = 0;
        while (i < 20 && !isElementExist(xpath)) {
            waitUntilElementVisible(xpath, 10000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        try {
            waitElementToBePresentThenScrollIntoView(xpath, 15).waitUntilClickable().click();
        } catch (Exception e) {
            clickOnElementByJs(xpath);
        }
        waitForEverythingComplete();
        waitForPageLoad();
    }

    public void verifyProductPage(String productName) {
        String xpath = "//*[contains(@class,'product__name') or contains(@class,'product-name')]|//h1[@class='mb8']";
        waitElementToBeVisible(xpath);
        String name = getText(xpath);
        assertThat(name).isEqualToIgnoringCase(productName);
    }

    public void clickToProductPage() {
        String xpath = "//div[contains(@class,'collection-product')]//span[@class='title d-block']";
        waitElementToBeVisible(xpath);
        clickOnElementByJs(xpath);
    }

    private String xpathScopedQuantityProductPage = "//div[@id='changeQuantityForm' or @class='button-quantity' or contains(@class,'quantity')]";

    public String getCurrentQuantityOfProductOrdered() {
        return getTextValue(xpathScopedQuantityProductPage + "//input");
    }

    public void addProduct() {
        clickOnElementByJs(xpathScopedQuantityProductPage + "//*[@class='quantity__adjust quantity__adjust--plus' or contains(@class,'qty-input__increase') or contains(@class,'vertical__increase')]");
    }

    public void removeProduct() {
        clickOnElement(xpathScopedQuantityProductPage + "//*[@class='quantity__adjust quantity__adjust--minus'  or contains(@class,'qty-input__decrease')  or contains(@class,'vertical__decrease')]");
    }

    public void clickBtnGoToCart() {
        String xpath = "//div[contains(@class,'cart-drawer-summary')]//button[normalize-space()='Go to cart']";
        waitUntilElementVisible(xpath, 5);
        clickOnElement(xpath);
    }

    public void openMiniCartOrCartDrawer() {
        String xpath;
        switch (theme) {
            case "bassy":
                xpath = "//div[contains(@class,'cart--drawer')]";
                break;
            case "inside":
                xpath = "//div[contains(@class,'cart-drawer')]";
                break;
            default:
                xpath = "//div[contains(@class,'cart-drawer-content--active')]";
        }

        if (!isElementExist(xpath)) {
            clickOnElement("//a[contains(@class,'mini-cart') or contains(@class,'cart-icon')]|//header//button[contains(@class,'relative')]");
            verifyElementPresent(xpath, true);
        }
    }

    public void inputCustomOption(String opt, String value) {
        String xpathOption = "//div[@class='relative input-base' and descendant::*[text()='" + opt + "']]//input";
        waitTypeAndEnter(xpathOption, value);
    }

    public void closeMiniCart() {
        String xpathClose = "//div[contains(@class,'cart--drawer')]//button[@aria-label='Close']";
        waitForElementNotVisible(xpathClose);
        clickOnElement(xpathClose);
    }

    String featuredProduct = "//section[@class='container product-page__container' or @class='container-page']";

    public void clickProductImage() {
        clickOnElement(featuredProduct + "//div[contains(@class,'VueCarousel-slide-active')]//img");
    }

    public void verifyEnableProductGalleryPopup(boolean isenableProductGalleryPopup) {
        verifyElementPresent("//div[@class='thumbnail-media-zoom']//div[@class='VueCarousel-slide flex justify-center items-center']//img", isenableProductGalleryPopup);
        if (isenableProductGalleryPopup) {
            clickOnElement("//div[@class='thumbnail-media-zoom']//div[@class='thumbnail-media-zoom__close']");
        }
    }

    public void verifyElementOnFeatureProduct(String xpath, boolean isShown) {
        verifyElementPresent(xpath, isShown);
        if (isShown)
            assertThat(getText(xpath)).isNotEmpty();
    }

    public void verifyShowBreadcrumbLinks(boolean isShown) {
        String xpath = "//div[contains(@class,'breadcrumb_text')]";
        verifyElementOnFeatureProduct(xpath, isShown);
    }

    public void verifyShowVendor(boolean isShowVendor) {
        String xpath = featuredProduct + "//p[contains(@class,'product__vendor')]";
        verifyElementOnFeatureProduct(xpath, isShowVendor);
    }

    public void verifyShowSKU(String theme, boolean isShowSKU) {
        String xpath = featuredProduct + "//*[contains(@class,'product__sku')]";
        if (theme.equals("inside")) {
            xpath = xpath + "//p";
        }
        verifyElementOnFeatureProduct(xpath, isShowSKU);
    }

    public void verifyShowPriceSavings(boolean isShown) {
        String xpath = featuredProduct + "//span[contains(@class,'product__sale-info') or contains(@class,'product__price-sale')]";
        verifyElementOnFeatureProduct(xpath, isShown);
    }

    public void verifyShowCollections(boolean isShown) {
        String xpath = "//div[contains(@class,'product__summary')]//p[contains(text(),'Collections:')]";
        verifyElementOnFeatureProduct(xpath, isShown);
    }

    public void verifyShowTypes(boolean isShowTypes) {
        String xpath = "//div[contains(@class,'product__summary')]//p[contains(text(),'Type:')]";
        verifyElementOnFeatureProduct(xpath, isShowTypes);
    }

    public void verifyShowTags(boolean isShowTags) {
        String xpath = "//div[contains(@class,'product__summary')]//p[contains(text(),'Categories:')]";
        verifyElementOnFeatureProduct(xpath, isShowTags);
    }

    public void verifyShowSocialMediaShareIcons(boolean isShowSocialMediaShareIcons) {
        verifyElementPresent("//div[contains(@class,'product__share')]", isShowSocialMediaShareIcons);
    }

    public void scrollToFooter() {
        scrollIntoElementView("//footer");
    }

    public void verifyShowStickyButton(boolean isShowStickyButton) {
        verifyElementPresent("//*[@id='sticky-bar' or contains(@class,'sticky-button')][not(@style='display: none;')]", isShowStickyButton);
    }

    public void verifyOptionsStyle(String optionsStyle) {
        if (optionsStyle.equals("Buttons")) {
            verifyElementPresent("//div[contains(@class,'product__variants')]//div[child::img or child::button]", true);
        } else
            verifyElementPresent("//div[contains(@class,'product__variants')]//div[contains(@class,'product-dropdown') or contains(@class,'select-box')]", true);
    }

    public void verifyEnableVariantGroupSwatches(boolean isEnable) {
        verifyElementPresent("//div[contains(@class,'product__variants-wrapper')]//img", isEnable);
    }

    public void verifyEnableColorSwatches(boolean isEnable) {
        verifyElementPresent("//div[contains(@class,'product__variants-wrapper')]//button[contains(@class,'color-swatches')]", isEnable);
    }

    public void verifyShowQuantityBox(boolean showQuantityBox, boolean showQuantityBoxInSameLine, boolean isShowQuantityBoxAndATCbtn) {
        verifyElementPresent("//input[@type='number']", showQuantityBox);
        if (showQuantityBox) {
            switch (theme) {
                case "inside":
                case "insidev2":
                    if (isShowQuantityBoxAndATCbtn) {
                        verifyElementPresent("//button[normalize-space()='Buy Now']", false);
                    } else {
                        verifyElementPresent("//div[@class='button-quantity__layout-horizontal flex']", showQuantityBoxInSameLine);
                    }
                    break;
                default:
                    verifyElementPresent("//div[@id='addToCartForm' and contains(@class,'flex align-end flow-wrap')]", showQuantityBoxInSameLine);
            }
        }
    }

    public void verifyTextFor0Sproduct(String textFor0Sproduct) {
    }

    public void selectSize(String variant) {
        String xPathVariant = "//button[@arialabel='Select " + variant + "']";
        clickOnElementByJs(xPathVariant);
        waitForEverythingComplete();
    }

    public void selectOption(String option) {
        String xpath = "(//div[contains(@class,'product__variant')]//button[descendant-or-self::*[normalize-space()='" + option + "' or @arialabel='Select " + option + "']])[1]|(//div[@id='product-variants']//img[@alt='" + option + "'])[1]";
        clickOnElementByJs(xpath);
    }

    public void selectColor(String color) {
        String xpath = "//div[@class='product__variants-wrapper']//button[contains(@class,'product__option--color')]";
        String xpathLabel = "//div[contains(@class,'product__variant-label')]//span[normalize-space()='Color:']//following-sibling::label";
        int count = countElementByXpath(xpath);
        for (int i = 1; i <= count; i++) {
            xpath = "(//div[@class='product__variants-wrapper']//button[contains(@class,'product__option--color')])[%d]";
            clickOnElement(String.format(xpath, i));
            if (getText(xpathLabel).equals(color)) {
                break;
            }
        }
    }

    public void selectVariant(String variant) {
        String xpath = "//div[@class='product__variants-wrapper']//button[normalize-space()='" + variant + "']";
        waitElementToBePresent(xpath);
        clickOnElement(xpath);
    }

    public void verifyVariantLabel(String variantLabel) {
        String[] option = variantLabel.split(";");
        for (int i = 0; i < option.length; i++) {
            String[] var = option[i].split(":");
            String xPathVariantLabel = "(//div[contains(@class,'product__variant-label')])[%d]";
            verifyElementText(String.format(xPathVariantLabel, i + 1) + "//span", var[0].trim() + ":");
            verifyElementText(String.format(xPathVariantLabel, i + 1) + "//label", var[1].trim());
        }
    }

    public void verifyName(String nameProduct) {
        String xPath = "//*[contains(@class,'product__name')]";
        waitElementToBeVisible(xPath, 20);
        verifyElementText(xPath, nameProduct.toUpperCase());
    }

    public void clickOnAddToCartBtn() {
        String xPath = "//div[@data-form='product']//button[child::span[normalize-space()='Add to cart']]|//button[contains(@class,'product__add-button')]";
        waitElementToBeVisible(xPath);
        clickOnElementByJs(xPath);
    }

    public String getProductPrice() {
        String xPathRoller = "//*[contains(@class,'product__price')]//span[1]";
        String xPathInSide = "//*[contains(@class,'product__price') or @class='mb25 price']";
        String xPathBassy = "//div[@class = 'container']//span[contains(@class,'has-text-weight-bold')]";
        String xPath;

        if (isElementExist(xPathRoller, 5)) {
            xPath = xPathRoller;
        } else if (isElementExist(xPathBassy, 5)) {
            xPath = xPathBassy;
        } else {
            xPath = xPathInSide;
        }
        return getTextValue(xPath);
    }

    String theme = LoadObject.getProperty("theme");

    public String getProductVariant() {
        String xPathProductVariant = "";
        String xPathLabelProductVariant = "";
        switch (theme) {
            case "bassy":
                xPathProductVariant = "//div[@id='product-variants']/div";
                xPathLabelProductVariant = "//div[contains(@class,'variants-label')]//label";
                break;
            default:
                xPathProductVariant = "//div[contains(@class,'product__variants-wrapper')]";
                xPathLabelProductVariant = "//div[contains(@class,'product__variant-label')]//label";

        }
        int count = countElementByXpath(xPathProductVariant);
        String variants = "";

        if (count > 0) {
            for (int i = 1; i <= count; i++) {
                String option;
                if (isElementExist("(" + xPathProductVariant + ")[" + i + "]" + xPathLabelProductVariant)) {
                    option = getText("(" + xPathProductVariant + ")[" + i + "]" + xPathLabelProductVariant);
                } else {
                    option = getSelectedValueDdl("(" + xPathProductVariant + ")[" + i + "]//select");
                }
                option = option.replace(":", "").trim();
                if (i >= 2) {
                    variants += " / ";
                }
                variants += option;
            }
        }
        return variants;

    }

    public void verifyProductPageDisplay(String productName) {
        verifyElementText("//h1[contains(@class,'product__name')]", productName.toUpperCase());
    }

    public void verifyPageNotFound() {
        verifyElementText("//h2", "404 PAGE NOT FOUND");
    }

    public void verifyExactURL(String productHandle) {
        String currentURL = getCurrentUrl();
        String exactURL = ("https://" + LoadObject.getProperty("shop") + "/products/" + productHandle);
        assertThat(currentURL).isEqualTo(exactURL);
    }

    public void verifyTabPosition(String tabPosition) {
        if (tabPosition.toLowerCase().contains("right")) {
            verifyElementPresent(featuredProduct + "//div[contains(@class,'product__description-right')]|//div[contains(@class,'product_collapse')]", true);
        } else {
            switch (theme) {
                case "inside":
                case "insidev2":
                    verifyElementPresent("//div[contains(@class,'product__description-bottom')]", true);
                    break;
                default:
                    verifyElementPresent("//section[contains(@class,'container-fluid')]", true);
            }
        }
    }

    public void verifyShowTrustBadgeImage(boolean showTrustBadgeImage) {
        String xpath = "//div[@id='detail-contents']//div[contains(@class,'product__trust-badge')]";
        verifyElementPresent(xpath, showTrustBadgeImage);
    }

    public void verifyVariantDeleted(String variant) {
        String xPathVariant = "//button[@arialabel='Select " + variant + "']";
        waitForElementNotAppear(xPathVariant);
    }

    public void verifyDescription(String description) {
        String xpathTab = "//nav//span[normalize-space()='Description']";
        String xpath = "//nav[descendant::span[normalize-space()='Description']]//following-sibling::section//div[@style='']//div | //div[@class='product__description-html']//p";
        scrollToElement(xpathTab);
        clickOnElement(xpathTab);
        verifyElementText(xpath, description);
    }

    public void verifyImage() {
        String xPath = "//div[@id='media-gallery-carousel']//img";
        verifyElementVisible(xPath, true);
    }

    public void verifyPrice(String price) {
        String xPath = "//div[contains(@class,'product__price')]//span[1]";
        verifyElementText(xPath, price);
    }

    public void selectValueRadio(String customOption, String value) {
        String xPath = "//label[normalize-space()='" + customOption + "']//following-sibling::div//span[normalize-space()='" + value + "']//preceding-sibling::span";
        String xPath_input = "//label[normalize-space()='" + customOption + "']//following-sibling::div//span[normalize-space()='" + value + "']//preceding-sibling::input";
        verifyCheckedThenClick(xPath_input, xPath, true);
    }

    public void selectValueDroplist(String customOption, String value) {
        String xPath = "//div[@class='select-box']//label[normalize-space()='" + customOption + "']//following-sibling::div//select";
        selectDdlByXpath(xPath, value);
    }

    public void uploadImageCO(String customOption, String value) {
        String xPath = "//label[contains(@class,'has-text-weight-bold') and child::p[normalize-space()='" + customOption + "']]//following-sibling::div//input";
        chooseAttachmentFile(xPath, value);
    }

    public void inputTextCO(String customOption, String value) {
        String xPath = "//div[contains(@class,'input-base__label') and descendant::p[normalize-space()='" + customOption + "']]//following-sibling::input";
        waitClearAndType(xPath, value);
    }

    public void verifyValue(String value, int row) {
        String xPath = "(//div[contains(@class,'product-cart__property-container')]//p)[%d]";
        waitABit(2000);
        assertThat(getText(String.format(xPath, row))).contains(value);
    }

    public void verifyCustomOption(String showCustom) {
        String xPath_show = "//div[contains(@class,'product-property__field')]//label[contains(@class,'has-text-weight-bold')]";
        List<String> listCO = getListText(xPath_show);
        Assertions.assertThat(listCO).isEqualTo(Arrays.asList(showCustom.split(",")));
    }

    public void verifyCompareAtPrice(String compareAtPrice) {
        String xPath = "//div[contains(@class,'product__price')]//span[@class='product__price--original']";
        verifyElementText(xPath, compareAtPrice);
    }

    String xpath_price = "(//div[contains(@class,'product__price')]/span|//div[contains(@class,'product__price')]/p/span)[%d]";

    public float getSalePrice() {
        String xpathSalePrice = String.format(xpath_price, 1);
        return Float.parseFloat(price(getText(xpathSalePrice)));
    }

    public float getCompareAtPrice() {
        String xpathCompareAtPrice = String.format(xpath_price, 2);
        return Float.parseFloat(price(getText(xpathCompareAtPrice)));
    }

    public void isShowSizeChart(boolean isShow) {
        String xpathSizeGuideLink = "//label[contains(@class,'product__size-guide') and text()[normalize-space()='Size Guide']]";

        if (isShow) {
            verifyElementPresent(xpathSizeGuideLink, true);
        } else {
            verifyElementPresent(xpathSizeGuideLink, false);
        }
    }

    public void selectProductOnSizeChart(String baseProduct) {
        selectDdlWithLabel("PRODUCT", baseProduct);
        waitForEverythingComplete();
    }

    public void selectUnitOnSizeChart(String unit) {
        String xpathSizeChartTab = "//div[@class='product__size-chart__tab']//div[text()[normalize-space()='" + unit + "']]";
        String xpathSizeChartActiveTab = "//div[@class='product__size-chart__tab']//div[@class='product__size-chart__tab__item active' and text()[normalize-space()='" + unit + "']]";
        if (!getAttributeValue(xpathSizeChartTab, "class").contains("active")) {
            clickOnElement(xpathSizeChartTab);
            waitForEverythingComplete();
        }
        verifyElementPresent(xpathSizeChartActiveTab, true);

    }

    public void verifyShowImageSizeChart() {
        String imageSizeChart = "//div[contains(@class,'widgets-size-chart__img')]//img";
        waitUntilElementVisible(imageSizeChart, 10);
        verifyElementPresent(imageSizeChart, true);
    }

    public void verifyDataSizeChart(String size, String width, String length) {
        String xpathSize = "//table[@class='product__size-chart__table']//td[text()='" + size + "']";
        String xpathWidth = xpathSize + "/following-sibling::td[text()='" + width + "']";
        String xpathLength = xpathSize + "/following-sibling::td[text()='" + length + "']";
        verifyElementPresent(xpathSize, true);
        verifyElementPresent(xpathWidth, true);
        verifyElementPresent(xpathLength, true);
    }

    public void closeSizeChartPopup() {
        String xpathCloseSizeChart = "//div[@class='roller-modal flex justify-center']//div[@class='roller-modal__body__icon-close']| //div[@class='inside-modal__body__icon-close absolute']";
        clickOnElement(xpathCloseSizeChart);
        waitForEverythingComplete();
    }

    public void openSizeChartPopup() {
        String xpathSizeGuideLink = "//div[contains(@id,'detail-contents')]//p[normalize-space()='Size Guide'] | //label[contains(@class,'product__size') and normalize-space()='Size Guide']";
        clickOnElement(xpathSizeGuideLink);
        waitForEverythingComplete();
    }

    public void isShowPreviewBtn(boolean isShow) {
        String xpath = "//span[contains(.,'Preview your design')]/parent::button";
        waitForEverythingComplete();
        verifyElementPresent(xpath, isShow);

    }

    public void clickPreviewBtn() {
        String xpathPreviewBtn = "//span[contains(.,'Preview your design')]/parent::button";
        waitABit(3000);
        clickOnElementByJs(xpathPreviewBtn);
    }


    public boolean isExistStyleLabel() {
        String xpath = "//div[@id='product-variants']//span[contains(text(),'Style')] | //div[contains(@class,'product__variants')]//span[contains(text(),'Style')]";
        boolean is = isElementExist(xpath);
        return isElementExist(xpath);
    }

    public boolean isExistProduct(String products) {
        String xpath = "//div[@class='product__variants-wrapper']//*[contains(text(),'" + products + "')]";
        boolean is = isElementExist(xpath);
        return isElementExist(xpath);
    }

    public void switchToProducts(String products, String verifyStyle) {
        String xpath = "//div[contains(@class,'product__variants')]//img";
        String xpathLabel = "//div[contains(@class,'product__variant-label')]//span[normalize-space()='Style:']//following-sibling::label";
        int count = countElementByXpath(xpath);
        for (int i = 1; i <= count; i++) {
            xpath = "(//div[@class='product__variants']//div[@class='flex flex-wrap align-center']//img)[%d]";
            refreshPage();
            waitForPageLoad();
            clickOnElement(String.format(xpath, i));
            if ((getText(xpathLabel)).equals(products) || (getText(xpathLabel)).equals(verifyStyle)) {
                break;
            }
        }
    }

    public void inputQuantityOnProductDetail(String quantity) {
        String xpath = "//div[@class='quantity-control__quantity']//input[@type='number']";
        waitClearAndTypeThenTab(xpath, quantity);
    }

    public void verifyStyle(String products) {
        String xpath = "//div[contains(@class,'product__variants-wrapper')]//label[contains(text(),'" + products + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyColor(String color) {
        String xpath = "//div[@class='product__variants-wrapper']//label[contains(text(),'" + color + "')]";
        verifyElementPresent(xpath, true);
    }

    public void clearLocalStorage() {
        getDriver().manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.localStorage.clear();");
        refreshPage();
        waitForEverythingComplete();
    }

    public void verifyNewURL(String urlAndHandleInitial) {
        String url = getCurrentUrl();
        String urlExpect = "https://" + shop + "/products/" + urlAndHandleInitial;
        assertThat(url).isIn(urlExpect, urlExpect + "?from-dashboard=1");
    }

    public void verifyNewPageTitle(String pageTitleInitial) {
        String pageTitle = getTitle();
        assertThat(pageTitle).isEqualTo(pageTitleInitial + " - " + shopName);
    }

    public void clickHeader() {
        clickOnElement("//div[@data-id='header']");
    }

    public void verifyPriceSaving(String price, String compareAtPrice) {
        String xpath = "//*[contains(@class,'product__sale-info')]";
        waitElementToBePresent(xpath);
        String priceSavingActual = getText(xpath);
        Float priceSavingExpect = Float.parseFloat(compareAtPrice.split(" ")[0].split("\\$")[1]) - Float.parseFloat(price.split(" ")[0].split("\\$")[1]);
        assertThat(priceSavingActual).isEqualTo("SAVE $" + String.format("%.02f", priceSavingExpect) + " USD");
    }

    public void verifyVendor(String vendor) {
        String xpath = "//*[contains(@class,'product__vendor')]//a";
        if (!"".equals(vendor)) {
            assertThat(vendor).isEqualTo(getText(xpath));
        } else {
            verifyElementVisible(xpath, false);
        }
    }

    public void verifySKU(String sku) {
        String xpath = "//*[contains(@class,'product__sku')]";
        if (!"".equals(sku)) {
            if (sku.contains("PH") || sku.contains("PB")) {
                assertThat(getText(xpath)).contains(sku);
            } else {
                assertThat(sku).isEqualTo(getText(xpath));
            }
        } else
            verifyElementVisible(xpath, false);
    }


    public void verifyProductType(String productType) {
        String xpath = "//*[contains(@class,'product__summary')]//p[contains(.,'Type')]";
        if (!"".equals(productType)) {
            assertThat("Type: " + productType).isEqualTo(getText(xpath));
        } else {
            verifyElementVisible(xpath, false);
        }
    }

    public void verifyTag(String tag) {
        String xpath = "//*[contains(@class,'product__summary')]//p[contains(.,'Categories')]";
        if (!"".equals(tag)) {
            assertThat("Categories: " + tag).isEqualTo(getText(xpath));
        } else {
            verifyElementVisible(xpath, false);
        }
    }

    public void verifyCollection(String collectionName) {
        String xpath = "//*[contains(@class,'product__summary')]//p[contains(.,'Collections')]";
        if (!"".equals(collectionName)) {
            assertThat(getText(xpath)).contains(collectionName);
        } else {
            verifyElementVisible(xpath, false);
        }
    }

    public void waitUntilCartLoadSuccessfully() {
        waitUntilElementVisible("//div[contains(@class,'product-cart-wrapper')]/div[contains(@class,'product-cart')]", 10);
    }

    public void verifyTitleDisplay(String title) {
        String xpath = "//span[normalize-space()='" + title + "'] | //div[normalize-space()='" + title + "']";
        verifyElementPresent(xpath, true);
    }

    public void clickIconCart() {
        clickOnElement("//a[contains(@class,'cart-icon')]");
    }

    public void verifySaleType(String saleType) {
        String savePrice = getText("//span[contains(@class,'product__sale-info') or contains(@class,'product__price-sale')]");
        if (saleType.toLowerCase().contains("percentage")) {
            assertThat(savePrice).contains("%");
        } else assertThat(savePrice).doesNotContain("%");
    }

    public void clickButtonBuyNow() {
        clickOnElement("//button[(@id='add-to-cart' or contains(@class,'btn btn-buy-now'))and descendant::text()[normalize-space()='Buy Now']]");
    }

    public void verifyShowPopupPreview() {
        String xpathImagePreview = "//div[contains(@class,'pod-product-preview')]//div[@class='product-image-preview']";
        waitForEverythingComplete();
        assertThat(XH(xpathImagePreview).isDisplayed()).isEqualTo(true);
    }

    public void WaitUntilVisibleIconLoadingPreview() {
        String xpath = "//div[@class='preview-overlay']//div[@class='loading-spinner']";
        int i = 0;
        while (!waitUntilElementInvisible(xpath, 50)) {
            i++;
            if (i > 6)
                break;
        }
        Boolean is = waitUntilElementInvisible(xpath, 50);
        assertThat(is).isEqualTo(true);
    }

    public void clickOption(String app) {
        String xpath = "//p[text()='" + app + "']//ancestor::div[@class='item']//div[@class='s-dropdown-trigger']";
        clickOnElement(xpath);
    }

    public void removeMappingProduct() {
        String xpath = "//span[text()[normalize-space()='Remove']]";
        clickOnElement(xpath);
    }

    public void confirmDeleteMappingProduct() {
        clickBtn("Remove");
    }

    public void verifyNotiCO(String label, String sMessageNoti) {
        String xpath = "//div[@class='relative input-base' and child::div//label[contains(text(),\"" + label + "\")]]//div[contains(.,\"" + sMessageNoti + "\")]";
        assertThat(isElementExist(xpath)).isTrue();
    }

    String xpathFeatureProduct = "//*[contains(@data-id,'featured_product') or contains(@data-id,'product_page')]";

    public void verifyAddToCartFeatureProduct(String productName) {
        verifyElementText("//div[@id='cart']//descendant::p[contains(@class,'product-cart__name')]", productName);
    }

    public void clearProductOnCart() {
        clickOnElement("//div[contains(@class,'button-quantity__layout-vertical__decrease')]");
    }

    public void verifyBuyNowFeatureProduct(String productName) {
        verifyElementText("//div[@id='checkout']//descendant::span[@class='checkout-product__name']", productName);
    }

    public void clickBtnBuyNow() {
        clickOnElement("//div[@id='detail-contents']//span[normalize-space()='Buy Now']");
        waitForPageLoad();
    }

    public void clickViewProductDetails() {
        clickOnElement(xpathFeatureProduct + "//a[contains(@class,'link-underline')]");
        waitForPageLoad();

    }

    public void clickProductName(String productName) {
        clickLinkTextWithLabel(productName);
        waitForPageLoad();
    }

    public void openHomePage() {
        clickOnElement("//header[contains(@class,'nav-inline-logo--true')]//a[contains(@class,'logo')]");
    }

    public void verifyOpenProductDetail(String productName) {
        waitForPageLoad();
        verifyElementText("//div[@class='content']//h1", productName.toUpperCase());
    }

    public void closeCheckOutPage() {
        clickOnElement("//div[@class='main']//span[contains(@class,'logo__text')]");
    }

    public void getImageProductWithURL(String sImageAc) throws IOException {
        String xpathImage = "//div[@id='product-image-gallery']//div[@class='VueCarousel-wrapper']//div[@class='VueCarousel-slide VueCarousel-slide-adjustableHeight product-slide-image VueCarousel-slide-active']//img";
        int n = 0;
        while (!isElementExist(xpathImage)) {
            refreshPage();
            n++;
            if (n > 5)
                break;
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        System.out.println(url);
        downloadWebPage(url, sImageAc);
    }

    public void verifyNamCustomOption(String sNameCO) {
        String xpath = "//div[@class='label mb5 has-text-weight-bold base-picture__label']";
        String value = getText(xpath);
        assertThat(sNameCO).isEqualTo(value);
    }

    public void selectFolder(String sFolder) {
        String xpath = "//div[@class='base-select']//select";
        selectDdlByXpath(xpath, sFolder);
    }

    public void clickPleaseSelectTheOption() {
        String xpath = "//select[@name='properties[Select]']";
        clickOnElement(xpath);
    }

    public void clickOnImage(int index) {
        String xpath = "//div[@class='base-picture__value'][" + index + "]/label/span";
        scrollToElement(xpath);
        clickOnElementByJs(xpath);
    }

    public String getValueImage(int index) {
        ArrayList<String> list = new ArrayList<>();
        clickOnImage(index);
        String xpath = "//span[@class='has-text-weight-bold mb5 base-picture__header-title']";
        scrollToElement(xpath);
        return getText(xpath);
    }

    public void verifyCO(String sNameCO) {
        String xpath = "//label[@class='d-block has-text-weight-bold']";
        String value = getText(xpath);
        assertThat(sNameCO).isEqualTo(value);
    }

    public String getNameImageInFolder(int index) {
        String xpath = "//div[@class='product-property__field']//select[@name='properties[Select]']//option[" + index + "]";
        scrollToElement(xpath);
        String value = getValue(xpath);
        return value;
    }

    public String getNameImageInGroup(int index) {
        String xpath = "//div[@class='product-property__field mt16']//select[@name='properties[Select]']//option[" + index + "]";
        scrollToElement(xpath);
        String value = getValue(xpath);
        return value;
    }

    public void verifyTitleRadio1(String sCustomOption) {
        String xpath = "//div[@class='radio-group']//label[@class='d-block has-text-weight-bold']";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyTitleTextField(String sCustomOption) {
        String xpath = "//div[@class='flex align-center input-base__label']//label";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyValueTextField(String sValue) {
        String xpath = "//div[@class='relative input-base']//input";
        String value = getValue(xpath);
        assertThat(sValue).isEqualTo(value);
    }

    public void clickOnRadioBtn(String sValue) {
        String xpath = "//input[@value='" + sValue + "']";
        clickOnElementByJs(xpath);
    }

    public void verifyTitleTextArea(String sCustomOption) {
        String xpath = "//div[@class='flex align-center textarea-base__label']//label";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyValueTextArea(String sValue) {
        String xpath = "//div[@class='textarea-base']//textarea";
        String value = getValue(xpath);
        assertThat(sValue).isEqualTo(value);
    }

    public void verifyTitleImage(String sCustomOption) {
        String xpath = "//div[@class='base-upload']//label";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyTitleDroplist(String sCustomOption) {
        String xpath = "//div[@class='select-box']//label";
        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
        String title = element.get(0).getText();
        assertThat(sCustomOption).isEqualTo(title);
    }

    public String[] verifyValueDroplist() {
        String xpath = "//div[@class='select-box']//select[@name='properties[Select]']";
        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
        String[] listValue = element.get(0).getText().split("\n");
        return listValue;
    }

    public void verifyTitleCheckbox(String sCustomOption) {
        String xpath = "//div[@class='base-checkbox']//span[contains(@class,'base-checkbox__control-label')]";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyTitlePictureFolder(String sCustomOption) {
        String xpath = "//div[@class='flex align-center base-picture__header']//div";
        String title = getText(xpath);
        assertThat(sCustomOption).isEqualTo(title);
    }

    public void verifyTitlePictureGroup(String sCustomOption) {
        String xpath = "//div[@class='select-box']//label";
        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
        String title = element.get(1).getText();
        assertThat(sCustomOption).isEqualTo(title);
    }

    public String[] verifyValuePictureGroup() {
        String xpath = "//div[@class='select-box']//select[@name='properties[Select]']";
        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
        String[] listValue = element.get(1).getText().split("\n");
        return listValue;
    }

    public void inputTextField(String sCustomOption, String sValue) {
        String xpath = "//label[contains(text(),'" + sCustomOption + "')]//ancestor::div[@class='flex align-center input-base__label']//following-sibling::input";
        waitClearAndType(xpath, sValue);
    }

    public void uploadImage(String sImage) {
        String xpath = "//input[@type='file']";
        chooseMultipleAttachmentFiles(xpath, "phub\\" + sImage);
    }

    public void cropImage() {
        String xpath = "//button[normalize-space()='Crop']";
        clickOnElementByJs(xpath);
    }

    public void selectImagePictureFolder(String _sValue) {
        String xpath = "//img[@alt='" + _sValue + "']";
        clickOnElementByJs(xpath);
    }

    public void selectImagePictureGroup(String sCustomOption, String _sValue) {
        String[] arr = _sValue.split(">");
        String xpath = "//div[@class='select-box']//label[normalize-space()='" + sCustomOption + "']//following-sibling::div//select";
        selectDdlByXpath(xpath, arr[0]);
//        List<WebElement> element = getDriver().findElements(By.xpath(xpath));
//        Select se = new Select(element.get(0));
//        se.selectByValue(arr[0]);
        selectImagePictureFolder(arr[1]);
    }

    public void clickOnAddToCartButton() {
        String xpath = "//button[@id='add-to-cart']//span[contains(text(),'Add to cart')]";
        scrollIntoElementView(xpath);
        clickOnElementByJs(xpath);
    }

    public void checkout() throws Exception {
        String xpath = "//button[@class='btn btn-primary fullwidth cart-drawer-summary__checkout']";
        hoverCartIcon();
        waitABit(3000);
        if (countElementByXpath(xpath) == 1) {
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }

    public void hoverCartIcon() {
        String xpath = "//a[@class='cart-icon']";
        hoverOnElement(xpath);
    }

    public void clickOnCheckOutBtn() {
        String xpath = "//button[normalize-space()='Checkout']";
        if (countElementByXpath(xpath) == 1) {
            clickOnElementByJs(xpath);
        } else {
            System.out.println("Continue");
        }
    }

    public void verifyImageLoaded() {
        String xpath = "//ul[contains(@class,'media-gallery-carousel__thumbs')]//img";
        int count = countElementByXpath(xpath);
        for (int i = 1; i <= count; i++) {
            xpath = "(//ul[contains(@class,'media-gallery-carousel__thumbs')]//img)[%d]";
            String lazy = getAttributeValue(String.format(xpath, i), "lazy");
            assertThat(lazy).isEqualTo("loaded");
        }
    }

    public String getListImageSF(int totalMedia) {
        String xpath = "//div[@id='product-image-gallery']//span[not(div)]//img | //div[@class='VueCarousel thumbnail-carousel mt24']//div[not(div)]//img";
        int n = countElementByXpath(xpath);
        String listIamgeSF = null;
        for (int i = 1; i <= n; i++) {
            xpath = "(//div[@id='product-image-gallery']//span[not(div)]//img)[%d] | (//div[@class='VueCarousel thumbnail-carousel mt24']//div[not(div)]//img)[%d]";
            String linkImg = getAttributeValue(String.format(xpath, i, i), "src");
            String img = linkImg.substring(linkImg.lastIndexOf("@") + 1);
            if (listIamgeSF == null) {
                listIamgeSF = img;
            } else {
                listIamgeSF = listIamgeSF + "," + img;
            }
        }
        return listIamgeSF;
    }

    public int countTotalMedia() {
        String xpath = "//div[@id='product-image-gallery']//ul//img | //div[@class='VueCarousel thumbnail-carousel mt24']//div//img";
        return countElementByXpath(xpath);
    }

    public void verifyListMedia(String listImage, int totalMedia) {
        assertThat(getListImageSF(totalMedia)).isEqualTo(listImage);
        assertThat(countTotalMedia()).isEqualTo(totalMedia);
    }

    public void clickVendor(String vendor) {
        String xpath = "//div[@class='product-page__gr-heading']//a[normalize-space()='" + vendor + "']";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void clickTag(String tag) {
        String xpath = "//div[@data-id='product_page']//a[normalize-space()='" + tag + "']";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void clickCollection(String collection) {
        String xpath = "//div[@data-id='product_page']//a[normalize-space()='" + collection + "']";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void verifyHandle(String handle) {
        waitABit(5000);
        assertThat(getCurrentUrl()).contains(handle);
    }

    public void verifyListProduct(String totalProduct) {
        String xpath = "//div[@class='collection-detail__product-details text-align-center']//span[@class='title d-block']";
        waitUntilElementVisible(xpath, 20);
        assertThat(countElementByXpath(xpath)).isEqualTo(Integer.parseInt(totalProduct));
    }

    public void verifyImageVariant(String imageVariant) {
        String xpath = "//div[@class='product__variants-wrapper']//img";
        assertThat(getAttributeValue(xpath, "lazy")).isEqualTo(imageVariant);
    }

    public void removeProductOnCartPage() {
        String xpath = "//div[contains(@class,'product-cart-wrapper')]//p[contains(@class,'product-cart__name')]";
        while (isElementExist(xpath)) {
            clickOnElement("(//div[contains(@class,'product-cart__action')]//div[contains(@class,'vertical__decrease')])[1]");
            refreshPage();
        }
    }

    public void clickStyleSFNext(String base) {
        String xpathStyle = "//div[child::span[contains(text(),'Style')]]/following-sibling::div//img";
        String xpathStyleBase = "//span[contains(text(),'Style')]/following-sibling::label";
        if (isElementExist(xpathStyle)) {
            List<WebElement> elements = getDriver().findElements(By.xpath(xpathStyle));
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).click();
                if (getText(xpathStyleBase).equalsIgnoreCase(base))
                    break;
            }
        }
    }

    public String getValueSelectedOnSizeChart() {
        String xpath = "//div[contains(@class,'select-box product__size-chart__select')]//select";
        Select select = new Select(XH(xpath));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }

    String labelOptionText = "//div[child::label[contains(.,'%s')]]";
    String labelOptionPictureChoice = "//div[contains(@class,'base-picture__header') and contains(.,'%s')]";
    String labelOptionImage = "//div[@class='base-upload' and contains(.,'%s')]";
    String lableOptionRadio = "//div[@class='radio-group']/label[text()='%s']";
    String labelOptionDroplist = "//div[@class='select-box']/label[text()='%s']";
    String labelOptionCheckbox = "//div[@class='base-checkbox']/label[text()='%s']";

    public Boolean isExistLabelOptionText(String label) {
        String xpath = String.format(labelOptionText, label);
        return isElementExist(xpath);
    }

    public void InputOptionText(String label, String text) {
        String xpath = String.format(labelOptionText, label) + "//following-sibling::input";
        String xpathArea = String.format(labelOptionText, label) + "//following-sibling::textarea";
        try {
            waitClearAndTypeThenTab(xpath, text);
        } catch (Exception e) {
            waitClearAndTypeThenTab(xpathArea, text);
        }
    }

    public Boolean isExistLabelOptionPictureChoice(String label) {
        String xpath = String.format(labelOptionPictureChoice, label);
        return isElementExist(xpath);
    }

    public Boolean isExistLabelOptionImage(String label) {
        String xpath = String.format(labelOptionImage, label);
        return isElementExist(xpath);
    }

    public void choosePictureChoice(String label, String picture) {
        String xpath = "//div[contains(@class,'base-picture__header') and child::div[contains(text(),'" + label + "')]]//following-sibling::div[contains(@class,'base-picture')]//label[child::input[@value='" + picture + "']]";
        String xpath_value = String.format(labelOptionPictureChoice, label) + "//following-sibling::span[contains(.,'" + picture + "')]";
        try {
            clickOnElement(xpath);
            assertThat(isElementExist(xpath_value, 20)).isTrue();
        } catch (Exception e) {
            clickOnElementByJs(xpath);
            assertThat(isElementExist(xpath_value)).isTrue();
        }
    }

    public void InputOptionImageAndCropImage(String label, String image, Boolean cropImage) {
        String xpath = String.format(labelOptionImage, label) + "//input";
        if (!image.isEmpty()) {
            changeStyle(xpath);
            chooseAttachmentFile(xpath, "phub" + File.separator + image);
            if (cropImage) {
                cropOutImage();
            }
            verifyAndClickBtnCropImage(cropImage);
            waitUntilUploadImageFinish();
        }
    }

    public void waitUntilUploadImageFinish() {
        String xpath = "//div[contains(@class,'upload-progress')]";
        int i = 0;
        while (isElementExist(xpath, 10)) {
            waitUntilElementInvisible(xpath, 10);
            i++;
            if (i > 10)
                break;
        }
    }

    public void verifyAndClickBtnCropImage(Boolean isCropImage) {
        String xpath = "//div[@id='modal-common']//button[contains(text(),'Crop')]";
        assertThat(isElementExist(xpath)).isEqualTo(isCropImage);
        if (isCropImage)
            clickOnElement(xpath);
    }

    public void cropOutImage() {
        String xpathIconMinusOnPopup = "//i[@class='absolute minus']";
        waitUntilElementVisible(xpathIconMinusOnPopup, 10);
        for (int i = 1; i <= 3; i++) {
            clickOnElementByJs(xpathIconMinusOnPopup);
        }
    }

    public void verifyShowPhotoGuideOnSF(String customName, Boolean isShow) {
        String xpath = "//div[@class='product-property']//label[text()='" + customName + "']/..//label[text()='Photo guide']";
        assertThat(isElementExist(xpath)).isEqualTo(isShow);

    }

    public boolean isShowPageNotExist() {
        String xpath = "//div[@id='not_found']// div[contains(.,'404 Page Not Found')]";
        return isElementExist(xpath);
    }

    public void getLinkText() {
        String xpath = "//div[@class='google__url']";
        String url = getText(xpath);
        openUrl(url);
    }

    public void verifyNameClipart(String sNameClipart) {
        String xpath_click = "//div[@class='base-picture__value']/label[@class='base-picture__item' and child::input[@value='" + sNameClipart + "']]";
        String xpath_name = "//div[@class='base-picture']//span[@class='has-text-weight-bold mb5 base-picture__header-title']";
        clickOnElement(xpath_click);
        String s = getText(xpath_name).replace(": ", "").trim();
        assertThat(sNameClipart).isEqualTo(s);
    }

    public void verifyClipart(String sNameClipart) {
        String xpath_click = "//div[@class='base-picture__value']/label[@class='base-picture__item' and child::input[@value='" + sNameClipart + "']]";
        String xpath_name = "//div[@class='base-picture']//span[@class='has-text-weight-bold mb5 base-picture__header-title']";
        clickOnElement(xpath_click);
        String s = getText(xpath_name).replace(": ", "").trim();
        assertThat(sNameClipart).isEqualTo(s);
    }


    String labelOptionText_new = "//div[contains(@class,'product-property__field')]//label[text()='%s']";

    public Boolean isExistLabelOptionTextNew(String label) {
        String xpath = String.format(labelOptionText_new, label);
        return isElementExist(xpath);
    }

    public void InputOptionTextNew(String label, String text) {
        String xpath = String.format(labelOptionText_new, label) + "/../following-sibling::input";
        String xpathArea = String.format(labelOptionText_new, label) + "/../following-sibling::textarea";
        try {
            waitClearAndTypeThenTab(xpath, text);
        } catch (Exception e) {
            waitClearAndTypeThenTab(xpathArea, text);
        }
    }

    public void verifyPhotoGuideWithContentTextOnSF(String photoName, String contentText) {
        String xpath = "//div[child::h4[normalize-space()='" + photoName + "']]//p";
        waitUntilElementVisible(xpath, 10);
        assertThat(getText(xpath)).isEqualTo(contentText);
    }

    public void verifyPhotoGuideWithContentImageOnSF(String photoName) {
        String xpath = "//div[child::h4[normalize-space()='" + photoName + "']]//img";
        waitUntilElementVisible(xpath, 10);
    }

    public String getValueDroplistDefault(String customName) {
        String xpath = "//label[text()='" + customName + "']//following-sibling::div//select";
        waitUntilElementVisible(xpath, 10);
        return getValueSelected(xpath);
    }

    public void verifyDropListInList(String customName, String value) {
        String xpath = "//label[text()='" + customName + "']//following-sibling::div//option[text()='" + value + "']";
        waitUntilElementVisible(xpath, 10);
    }


    public void verifyListValueSizeChart(String value) {
        String xpath = "//div[contains(@class,'product__size-chart')]//select//option";
        List<String> listValue = getListText(xpath);
        String[] list = value.split(",");
        for (String option : list) {
            assertThat(listValue).contains(option);
        }
    }

    public void verifyTitleSizeChart(String title) {
        String xpath = "//div[@class='roller-modal__body__content']//div[@class='product__size-chart__title']";
        assertThat(getText(xpath)).isEqualTo(title);
    }

    public void verifyDescriptionSizeChart(String description) {
        String xpath = "//div[@class='roller-modal__body__content']//p";
        assertThat(getText(xpath)).isEqualTo(description);
    }

    public void verifyIamgeProcessingManualDesign(Boolean bol) {
        String xpath = "//div[@class='product-property']/img";
        waitUntilElementVisible(xpath, 10);
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        Integer size = elements.size();
        assertThat(isElementExist(xpath)).isEqualTo(bol);
        if (bol)
            assertThat(size).isEqualTo(1);
    }

    public boolean isExitElementPersonal(String label) {
        return isElementExist("//input[@placeholder='" + label + "']");
    }

    public void clickToBtnOnPopup(String btn) {
        String xpath = "(//*[child::*[contains(@class,'heading')]]//*[text()[normalize-space()='" + btn + "']]|//button[preceding-sibling::*[text()[normalize-space()='" + btn + "']] or descendant-or-self::*[text()[normalize-space()='" + btn + "']]])[2]";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void getImageArtwork(String sImageAc) throws IOException {
        String xpathImage = "//div[@class='artwork']//img | //div[@class='cursor-pointer']";
        int n = 0;
        while (!isElementExist(xpathImage)) {
            refreshPage();
            n++;
            if (n > 5)
                break;
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("0x400@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        System.out.println(url);
        downloadWebPage(url, sImageAc);
    }

    public void verifyNumberCampaignOnSF(String campaignName) {

    }

    public void verifyCustomOptionSF(String showCustom, Boolean isShow) {
        String xPath_show = "//div[contains(@class,'product-property__field')]//label[contains(@class,'has-text-weight-bold')]";
        List<String> listCO = getListText(xPath_show);
        if (isShow)
            assertThat(listCO).contains(showCustom);
        else
            assertThat(listCO).doesNotContain(showCustom);
    }

    public void verifyLabelOptionRadio(String sCustomName, boolean isShow) {
        String xpath = String.format(lableOptionRadio, sCustomName);
        assertThat(isElementExist(xpath)).isEqualTo(isShow);

    }

    public void verifyLabelOptionDroplist(String sCustomName, boolean isShow) {
        String xpath = String.format(labelOptionDroplist, sCustomName);
        assertThat(isElementExist(xpath)).isEqualTo(isShow);
    }

    public void verifyLabelOptionCheckbox(String sCustomName, boolean isShow) {
        String xpath = String.format(labelOptionCheckbox, sCustomName);
        assertThat(isElementExist(xpath)).isEqualTo(isShow);
    }

    public void clickToStatus(String status) {
        String xpath = "//div[normalize-space()='" + status + "']";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
    }

    public void clickToRadio(String radio) {
        String xpath = "//label[@class='sb-radio']/span[contains(text(),'" + radio + "')]";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void verifyListNameOrder(String _sOrder, boolean _isExist) {
        String xpath = "//div[normalize-space()='" + _sOrder + "']";
        waitForEverythingComplete();
        assertThat(isElementExist(xpath)).isEqualTo(_isExist);
    }

    public void clickEditorPersonalize(String personalize) {
        String xpathImage = "//div[@class='d-flex justify-content-space-between align-items-center' and child::h3[normalize-space()='" + personalize + "']]//following-sibling::div//div[@class='overlay d-flex align-items-end justify-content-center']";
        String xpathEditor = "//div[@class='d-flex justify-content-space-between align-items-center' and child::h3[normalize-space()='" + personalize + "']]//following-sibling::div//i[@class='icon cursor-pointer m-l-sm']";
        hoverThenClickElement(xpathImage, xpathEditor);

    }

    public void verifyUser(String sUser) {
        String xpath = "(//thead[descendant::span[normalize-space()='USER']]//following-sibling::tbody//span[@class='type--bold text-left'])";
        waitUntilElementVisible(xpath, 10);
        assertThat(getText(xpath)).isEqualTo(sUser);
    }

    public void verifyCategory(String sCategory) {
        int indexXpath = getIndexOfColumn("CATEGORY");
        String xpath = "//thead[descendant::span[normalize-space()='CATEGORY']]//following-sibling::tbody//td[" + indexXpath + "]//span[@class='money--gray text-left']";
        waitUntilElementVisible(xpath, 10);
        assertThat(getText(xpath)).isEqualTo(sCategory);
    }

    public void verifyActivity(String sActivity) {
        int indexXpath = getIndexOfColumn("ACTIVITY");
        String xpath = "//thead[descendant::span[normalize-space()='ACTIVITY']]//following-sibling::tbody//td[" + indexXpath + "]//span[@class='money--gray text-left']";
        waitUntilElementVisible(xpath, 50);
        assertThat(sActivity).contains(getText(xpath));

    }

    public void verifyDetail(String sDetail) {
        int indexXpath = getIndexOfColumn("DETAILS");
        String xpath = "//thead[descendant::span[normalize-space()='DETAILS']]//following-sibling::tbody//td[" + indexXpath + "]//span[@class='money--gray text-left']";
        waitUntilElementVisible(xpath, 10);
        assertThat(sDetail).contains(getText(xpath));
    }

    public void clickIconDelete() {
        String xpath = "//span[@data-label='Delete']";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void verifyVariantShow(String variant, String show) {
        String xpath = "//div[@class='product__variants-wrapper']//button[normalize-space()='" + variant + "']";
        verifyElementPresent(xpath, Boolean.parseBoolean(show));
    }

    public void clickClosePopupLivePreview() {
        String xpath = "//div[@class='roller-modal__body__icon-close']";
        clickOnElement(xpath);
    }

    public void getImageProductInListWithURL(String sImageAc, int imageNumber) throws IOException {
        String xpathImage = "//ul[contains(@class,'media-gallery-carousel__thumbs mt16 mb0 p0')]//li[" + imageNumber + "]//img | //div[contains(@class,'VueCarousel-wrapper')]//div[contains(@class,'VueCarousel-slide thumbnail-carousel-slide')][" + imageNumber + "]//img";
        clickOnElement(xpathImage);
        int n = 0;
        while (!isElementExist(xpathImage)) {
            refreshPage();
            n++;
            if (n > 5)
                break;
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        System.out.println(url);
        downloadWebPage(url, sImageAc);
    }

    public void clickNextPageOnListMockupSF() {
        String xpath = "//div[contains(@class,'VueCarousel thumbnail-carousel mt24')]//button[@class='VueCarousel-navigation-button VueCarousel-navigation-next']";
        clickOnElement(xpath);
    }

    public void verifyImageMockupOnProductPage(String image, Float per, int imageNumber) throws IOException {
        String xpathImage = "//ul[contains(@class,'media-gallery-carousel__thumbs mt16 mb0 p0')]//li[" + imageNumber + "]//img | //div[contains(@class,'VueCarousel-wrapper')]//div[contains(@class,'VueCarousel-slide thumbnail-carousel-slide')][" + imageNumber + "]//img";
        clickOnElement(xpathImage);
        int n = 0;
        while (!isElementExist(xpathImage)) {
            refreshPage();
            n++;
            if (n > 5)
                break;
        }
        WebElement preview = getDriver().findElement(By.xpath(xpathImage));
        String logoSRC = preview.getAttribute("src");
        String sUrl[] = logoSRC.split("/");
        String namefile = sUrl[sUrl.length - 1];
        namefile = namefile.substring(namefile.indexOf("@") + 1);
        String url = "";
        for (int i = 0; i < sUrl.length - 1; i++)
            url = url + sUrl[i] + "/";
        url = url + namefile;
        System.out.println(url);
        CommonPageObject.verifyImageFromDasboardOrSF(url, image, per);
    }

    public void clickBuyNow() {
        String xpath = "//button[normalize-space()='Buy Now']";
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public void verifyPhoneNumber(String phoneNumber) {
        String xpath = "//div[@class='footer_content']//li[contains(normalize-space(),'" + phoneNumber + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyProductOnCart(String linkProduct) {
        verifyElementVisible("//*[contains(@class,'product-cart__name')]//a[@href='" + linkProduct + "']", true);
    }

    public int getCartNumber() {
        return Integer.parseInt(getTextValue("//span[@class=\"cart-number\"]"));
    }

    public void verifyProductNotExistOnSF(String productName, boolean status) {
        String xpath = "(//span[normalize-space(.)=" + productName + "]//ancestor::a//img)[1]|//div[contains(@class,'product')]//a[text()[normalize-space()=" + productName + "]]|//a[contains(@href,'products') and text()[normalize-space()='" + productName + "']]";
        verifyElementPresent(xpath, status);
    }

    public void verifyNotShowVariant() {
        verifyElementPresent("//div[@class='product__variants']/", false);
    }

    public void selectProductFirst() {
        String xpath = "//div[contains(@class,'row mt32')]//ancestor::a//img[1]";
        try {
            waitElementToBePresentThenScrollIntoView(xpath, 15).waitUntilClickable().click();
        } catch (Exception e) {
            clickOnElementByJs(xpath);
        }
        waitForEverythingComplete();
        waitForPageLoad();
    }

    //v2
    String xpathBlock = "//*[contains(@class,'image-with-text')]//*[contains(@class,'feature-set-content-wrap')]";

    public void verifyTextProduct(String content) {
        verifyElementText("//*[contains(@type,'Text')]//h3", content.toUpperCase());
    }

    public void verifyHeading(String heading, int indexBlock) {
        verifyElementText("(" + xpathBlock + ")[" + indexBlock + "]//h4", heading.toUpperCase());
    }

    public void verifyContent(String content, int indexBlock) {
        verifyElementText("(" + xpathBlock + ")[" + indexBlock + "]//h4", content.toUpperCase());
    }

    public void verifyMagnifyProductImages(boolean magnifyProductImages) {
        verifyElementPresent("//div[@data-id='product_page']//div[@style='position: relative;']", magnifyProductImages);
    }

    public void verifyDescriptionPosition(boolean descriptionPosition) {
        verifyElementPresent("//div[contains(@class,'product__description-right')]", descriptionPosition);
    }

    public void verifyHeadingOnProductDescription(String descriptionPosition, String heading, int index) {
        if (!heading.isEmpty())
            if (descriptionPosition.equalsIgnoreCase("Description on the right")) {
                verifyElementText("(//div[contains(@class,'product__description')]//span[contains(@class,'toggle_heading')])[" + index + "]", heading);
            } else {
                verifyElementText("(//div[contains(@class,'product__description')]//span)[" + index + "]", heading.toUpperCase());
            }
    }

    public void verifyCollapseThisTabByDefault(String descriptionPosition, boolean collapseThisTabByDefault, int index) {
        if (descriptionPosition.equalsIgnoreCase("Description on the right")) {
            verifyElementPresent("(//div[contains(@class,'product__description')]//span[contains(@class,'is-active')])[" + index + "]", collapseThisTabByDefault);
        }
    }

    public void verifyTextOnAdditionalTab(boolean collapseThisTabByDefault, String text, int index) {
        if (!text.isEmpty()) {
            if (collapseThisTabByDefault) {
                clickOnElement("(//div[contains(@class,'product__description')]//span[contains(@class,'icon-plus-arrow')])[" + index + "]");
            }
            verifyElementText("(//div[contains(@class,'product__description-html')])[" + index + "]//p", text);
        }
    }
}
