package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomePage extends SBasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    String logoList = "//section[@class='section logo-list']//div[contains(@class,'logo-wrap')]";
    String xpathFeatureCollection = "//section[contains(@data-id,'featured_collection')]";
    String xpathImageWithText = "//section[contains(@data-id,'image_with_text')]";
    String xpathFeatureProduct = "//*[contains(@data-id,'featured_product') or contains(@data-id,'product_page') or @type='featured-product']";
    String xpathNewLetter = "//section[contains(@class,'section subscribe') or contains(@class,'section newsletter')]";
    String theme = LoadObject.getProperty("theme");
    String shop = LoadObject.getProperty("shop");
    String xpathInsideSection = "//section[@type='" + "%s".toLowerCase().replace(" ", "-") + "']";
    String xpathSlideBlock = "(" + xpathInsideSection + "//*[@role='tabpanel'])[%d]";

    public void verifyEnableSearch(boolean enableSearch) {
        if (theme.equals("roller") || theme.equalsIgnoreCase("RollerV3")) {
            verifyElementPresent(cLass("search-bar"), enableSearch);
        } else {
            if (theme.equalsIgnoreCase("Bassy")) {
                verifyElementPresent(cLass(("visible-lg")), enableSearch);
            } else {
                verifyElementPresent(cLass(("search-icon")), enableSearch);
            }
        }
    }

    public void verifyShowCart(boolean showCart) {
        if (theme.equalsIgnoreCase("roller")||theme.equalsIgnoreCase("RollerV3")) {
            verifyElementPresent(cLass("cart-area"), showCart);
        } else verifyElementPresent(cLass("mini-cart"), showCart);
    }

    public void verifyCartIcon(String cartIcon) {
        if (theme.equals("roller")||theme.equalsIgnoreCase("RollerV3"))
            verifyElementPresent(cLass("cart-icon"), true);

    }

    public void verifyAnnouncementBar(boolean showAnnouncementBar,String announcementMessage) {
        String xpath;
        if(theme.equals("roller")){
            xpath = "//*[contains(@class,'announcement-bar')]";
        }else {
            xpath = "//*[contains(@class,'header nav-inline-logo')]//div[contains(@class, 'promo_banner')]";
        }
        verifyElementPresent(xpath, showAnnouncementBar);
        if(showAnnouncementBar) {
            verifyElementText(xpath + "//p", announcementMessage);
        }
    }

    public void verifyShowTopBar(boolean showTopBar) {
        String xpath = "//*[contains(@class,'top-bar') or contains(@class,'header__topbar')]";
        verifyElementPresent(xpath, showTopBar);
    }

    public void verifyShowSocialMediaHeaderIcons(boolean showSocialMediaHeaderIcons) {
        verifyElementPresent(cLass("social-icons"), showSocialMediaHeaderIcons);
    }

    public void verifyPhoneNumber(String phoneNumber) {
        verifyElementText(cLass("contact-number"), phoneNumber);
    }

    public void verifyTopBarMenu(String topBarMenu) {
        String xpath = "//*[@class='header__topbar py8' or @class='top-bar']//ul[contains(@class,'list-style-none')]//li";
        int size = countElementByXpath(xpath);
        if (topBarMenu.equalsIgnoreCase("None")) {
            assertThat(size).isEqualTo(0);
        } else {
            assertThat(size).isGreaterThanOrEqualTo(1);
        }
    }

    public void verifyLogoExist(boolean isShow) {
        verifyElementPresent("//div[@class='col-lg-2' or @class='logo']//img", isShow);
    }

    public void verifyMainMenuShown(boolean isShowMenu) {
        String xpath = "//*[contains(@class,'site-nav')]";
        if (theme.equals("roller")) {
            verifyElementPresent(iD("AccessibleNav"), isShowMenu);
        }if(theme.equalsIgnoreCase("RollerV3")){
            verifyElementPresent(iD("accessibleNav"), isShowMenu);
        }else verifyElementPresent(xpath, isShowMenu);


    }

    String slideShow = "(//*[@data-id='homepage_slideshow'])[%d]";

    public String getHeadingContent(int slideshowIndex) {
        return getTextValue(String.format(slideShow, slideshowIndex) + "//div[@class='content' or contains(@class,'container slideshow-section__caption')]");
    }

    public void verifyButtonLink(String buttonLink, int index) {
    }

    public void verifyHighlightButtonLabel(String highlightButtonLabel, int index) {
    }

    public void verifyBackgroundImage(String backgroundImage, int slideshowIndex) {
        String xpath = String.format(slideShow, slideshowIndex);
        String data_srcset = getAttributeValue(xpath + "//div[contains(@class,'carousel-background') or contains(@class,'slideshow-section__background')]//img", "data-srcset");
        String src = getAttributeValue(xpath + "//div[contains(@class,'carousel-background') or contains(@class,'slideshow-section__background')]//img", "src");

        if (!backgroundImage.isEmpty()) {
            assertThat(data_srcset).isNotEmpty();

        } else {
            assertThat(data_srcset).isEmpty();
            assertThat(src).startsWith("https://cdn.btdmp.com/dist/themes/2/2/images/big-image");
        }
    }

    @Step
    public int getSlideShowing() {
        String xpath = "//*[@data-id='homepage_slideshow']//div[@role='tabpanel']";
        int index = 0;
        if (isElementExist(xpath)) {
            index = countElementByXpath(xpath + "/preceding-sibling::div[@role='tabpanel']") + 1;
        }
        return index;
    }

    public void clickBtnNextPage() {
        clickOnElement("//button[@aria-label='Next page']");
    }

    public void clickBtnPreviousPage() {
        clickOnElement("//button[@aria-label='Previous page']");

    }


    public void verifyHeadingRichText(String heading) {
        waitForPageLoad();
        switch (theme) {
            case "inside":
            case "insidev2":
                verifyElementText("//div[contains(@class,'rich-text-section')]//h4", heading.toUpperCase());
                break;
            default:
                verifyElementText("//section[@class='section annouce-block']//h2", heading.toUpperCase());
        }
    }

    public void verifyTextAlignmentRichText(String text, String textAlignment) {
        String xpath;
        if (theme.equalsIgnoreCase("inside")) {
            xpath = "//*[contains(@class,'rich-text-section')]//div[contains(@class,'text-align-" + textAlignment.toLowerCase(Locale.ROOT) + "')]//div";
        } else {
            xpath = "//*[contains(@data-id,'rich_text')]//div[contains(@class,'text-align-" + textAlignment.toLowerCase(Locale.ROOT) + "')]//p";
        }
        String actualText = getText(xpath);
        assertThat(actualText).isEqualTo(text);
    }

    public void verifyLinkLabelRichText(String linkLabel) {
        if (linkLabel.equalsIgnoreCase("@BLANK@")) {
            linkLabel = "";
        }
        String xpath = "";
        switch (theme) {
            case "inside":
            case "insidev2":
                xpath = "//div[contains(@class,'rich-text-section')]//a";
                break;
            default:
                xpath = "//section[@class='section annouce-block']//a";
        }
        verifyElementText(xpath, linkLabel);
    }

    public void verifyHeadingFeaturedCollection(String heading) {
        String xpathHeader = "";
        String value = "";

        if (theme.equalsIgnoreCase("bassy")) {
            if (!heading.isEmpty()) {
                xpathHeader = "//section[contains(@data-id,'featured_collection')]//h1";
                value = heading;
            }

        } else {
            xpathHeader = "//h2[contains(@class,'product-carousel__heading')]";
            value = heading.toUpperCase();
        }

        String actHeader = getText(xpathHeader);
        assertThat(actHeader).isEqualTo(value);
    }

    public void verifyFeaturedCollection(String collection, boolean isShow) {
        String xpath;
        if (theme.equalsIgnoreCase("bassy")) {
            xpath = "//section[contains(@data-id,'featured_collection')]//a[contains(@href,'/products/')]";
            verifyElementPresent(xpath, isShow);
        } else {
            xpath = "//section[contains(@data-id,'featured_collection')]//a[contains(@href,'/collections/" + collection.toLowerCase(Locale.ROOT) + "')]";
            verifyElementPresent(xpath, isShow);
        }

    }

    public void verifLayoutTypeFeaturedCollection(String collectionLayout) {
        switch (collectionLayout) {
            case "Slider":
                verifyElementPresent(xpathFeatureCollection + "//div[contains(@class,'VueCarousel')]", true);
                break;
            case "Grid":
                verifyElementPresent(xpathFeatureCollection + "//div[contains(@class,'col-wrap')]", true);
                break;
            case "Mix":
                verifyElementPresent(xpathFeatureCollection + "//div[contains(@class,'flex-wrap')]//div[contains(@class,'col-left')]", true);
                break;
            default:
                verifyElementPresent(xpathFeatureCollection + "//div[@class='row']", true);
        }
    }

    public void verifyBtnViewMoreShow(boolean isShow, String viewMoreLable) {
        String xpathLinkViewMore = xpathFeatureCollection + "//a[contains(@class,'view-more-link')]";
        String xpathBtnViewMore = xpathFeatureCollection + "//a[contains(@class,'view-more btn btn-subtle') or @class='view-more btn btn-outline']";
        switch (theme) {
            case "inside":
            case "insidev2":
                if (isShow) {
                    String actLableBtnViewmore = getText(xpathLinkViewMore);
                    assertThat(actLableBtnViewmore).isEqualTo(viewMoreLable);
                } else {
                    String actLableBtnViewmore = getText(xpathBtnViewMore);
                    assertThat(actLableBtnViewmore).isEqualTo(viewMoreLable.toUpperCase());
                }
                break;
            default:
                if (isShow) {
                    String actLableBtnViewmore = getText(xpathBtnViewMore);
                    assertThat(actLableBtnViewmore).isEqualTo(viewMoreLable.toUpperCase());
                } else {
                    verifyElementPresent(xpathBtnViewMore, isShow);
                }
        }
    }

    public void verifyHeadingCollectionList(String heading) {
        String xpathHeading = "";
        String value = heading;

        switch (theme) {
            case "inside":
            case "insidev2":
                xpathHeading = "//*[contains(@data-id,'collection_list')]//h4";
                value = heading.toUpperCase();
                break;
            case "roller":
                xpathHeading = "//section[contains(@data-id,'collection_list')]//h2";
                value = heading.toUpperCase();
                break;
            default:
                xpathHeading = "//section[contains(@data-id,'collection_list')]//h1";
        }

        verifyElementText(xpathHeading, value);
    }

    public void verifyNameCollection(String col) {
        String xpath = "";

        switch (theme) {
            case "inside":
            case "insidev2":
                xpath = xpathCollectionList + "//h5[contains(@class,'collection-list--name')]";
                break;
            case "roller":
                xpath = xpathCollectionList + "//p[contains(@class,'collection-name')]";
                break;
            default:
                xpath = "//h2[contains(@class,'collections__item-title')]";
        }

        List<String> listCollectionname = getListText(xpath);
        assertThat(Arrays.asList(col.toUpperCase().split(","))).containsAll(listCollectionname);
    }

    public void verifyCollectionData(String nameCollection, String img, String alt) {
        verifyNameCollection(nameCollection);
        verifyLinkCollections(nameCollection);
    }

    public void verifyLinkCollections(String nameCollection) {
        String nameCol = nameCollection.toLowerCase();
        String xpathLinkCollection = "//a[@href ='/collections/" + nameCol + "']";
        verifyElementPresent(xpathLinkCollection, true);
    }

    public void verifyProductNameFeatureProduct(String productName) {
        verifyElementText(xpathFeatureProduct + "//a[contains(@class,'product__name')]", productName.toUpperCase());
    }

    public void verifyIMagePositionFeatureProduct(String imagePosition) {
        switch (theme) {
            case "inside":
            case "insidev2":
                if (imagePosition.equalsIgnoreCase("Right")) {
                    verifyElementPresent(xpathFeatureProduct + "//div[@class='col-12 col-lg-6 order-lg-2 offset-lg-1']", true);
                } else verifyElementPresent(xpathFeatureProduct + "//div[@class='col-12 col-lg-6']", true);
                break;
            default:
                String xpath = xpathFeatureProduct + "//div[@class='product__gallery media-gallery']";
                verifyElementPresent(xpath, true);
        }
    }

    public void verifyShowVendor(Boolean isShowvendor) {
        verifyElementPresent(xpathFeatureProduct + "//p[contains(@class,'product__vendor')]", isShowvendor);
    }

    public void verifyShowPriceSavings(Boolean isShowpricesavings) {
        switch (theme) {
            case "inside":
            case "insidev2":
                verifyElementPresent(xpathFeatureProduct + "//span[contains(@class,'product__price-sale')]", isShowpricesavings);
                break;
            default:
                verifyElementPresent(xpathFeatureProduct + "//span[contains(@class,'product__sale-info')]", isShowpricesavings);
        }
    }

    public void verifyOptionsStyle(String optionsStyle) {
        if (optionsStyle.toLowerCase().contains("buttons")) {
            verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__variants')]//div[child::img or child::button]", true);
        } else {
            switch (theme) {
                case "inside":
                case "insidev2":
                    verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__variants')]//div[contains(@class,'option')]", true);
                    break;
                default:
                    verifyElementPresent(xpathFeatureProduct + "//div[@class='product__variants']//div[contains(@class,'product-dropdown')]", true);
            }
        }
    }

    public void verifySwatches(String optionsStyle, Boolean isEnablevariantgroupswatches, Boolean
            isEnablecolorswatches) {
        if (optionsStyle.equals("Buttons")) {
            verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__variants')]//img", isEnablevariantgroupswatches);
            verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__variants')]//button[contains(@class,'color-swatches')]", isEnablecolorswatches);
        }
    }

    public void verifyShowquantitybox(Boolean isShowquantitybox, Boolean isShowquantityboxinthesameline,
                                      boolean isQuantityAndAddToCartBtn) {
        verifyElementPresent(xpathFeatureProduct + "//input[@type='number']", isShowquantitybox);
        if (isShowquantitybox) {
            switch (theme) {
                case "inside":
                    if (isQuantityAndAddToCartBtn) {
                        verifyElementPresent(xpathFeatureProduct + "//button[normalize-space()='Buy Now']", !isQuantityAndAddToCartBtn);
                    } else {
                        verifyElementPresent(xpathFeatureProduct + "//div[@class='button-quantity__layout-horizontal flex']", isShowquantityboxinthesameline);
                    }
                    break;
                default:
                    verifyElementPresent(xpathFeatureProduct + "//div[@class='quantity-control__quantity']", isShowquantityboxinthesameline);
            }
        }
    }

    public void verifyTextFor0Sproduct(String textFor0Sproduct) {
    }

    public void clickProductImageFeatureProduct() {
        clickOnElement(xpathFeatureProduct + "//img[@class='sb-lazy progressive']");
    }

    public void verifyEnableproductgalleryslideshowFeatureProduct(Boolean isEnableproductgalleryslideshow) {
        if (theme.equalsIgnoreCase("RollerV3")){
            clickProductImageFeatureProduct();
            verifyElementPresent(xpathFeatureProduct + "//div[@class='thumbnail-media-zoom']",isEnableproductgalleryslideshow);
            if (isElementExist(xpathFeatureProduct + "//div[@class='thumbnail-media-zoom']//*[contains(@class,'close-icon')]"))
                 clickOnElement(xpathFeatureProduct + "//div[@class='thumbnail-media-zoom']//*[contains(@class,'close-icon')]");
        }else {
            verifyElementPresent(xpathFeatureProduct + "//div[@class='VueCarousel-navigation']", isEnableproductgalleryslideshow);
            if (isEnableproductgalleryslideshow) {
                hoverOnElement(xpathFeatureProduct + "//div[contains(@class,'VueCarousel-slide-active')]");
                clickOnElement(xpathFeatureProduct + "//button[@aria-label='Next page']");
            }
        }
    }

    public void verifygalleryTransitionFeatureProduct(String galleryTransition) {
        boolean isShowGallerry = false;
        if (galleryTransition.equals("Fade")) {
            isShowGallerry = true;
        }
        verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'carousel--fade')]", isShowGallerry);
    }

    public void hoverProductImageFeatureProduct() {
        hoverOnElement(xpathFeatureProduct + "//img");
    }

    public void verifyShowarrowsonproductgalleryFeatureProduct(Boolean isShowarrowsonproductgallery) {
        verifyElementPresent("//*[@id='media-gallery-carousel']/div[2]/button[1]", isShowarrowsonproductgallery);
    }

    public void verifyExampleProductDisplay() {
        if (!theme.equalsIgnoreCase("RollerV3"))
        verifyElementPresent("//*[contains(@data-id,'featured_product') or contains(@class,'product section')]//h2[normalize-space()='Example product title']", true);
    }

    public void verifyHeadingNewsletter(String heading) {
        waitForPageLoad();
        verifyElementText(xpathNewLetter + "//h2", heading.toUpperCase());
    }

    public void verifySubheadingNewsletter(String subheading) {
        verifyElementText(xpathNewLetter + "//div[@class='announce-text mb24' or contains(@class,'newsletter-sub-heading')]", subheading);
    }

    public void enterEmail(String email) {
        enterInputFieldWithLabel("Your email", email);
    }

    public void clickbtnSignUp() {
        clickBtn("Sign up");
    }

    public void verifySignUp(String message) {
        if (!message.isEmpty()) {
            String xpath = xpathNewLetter + "//*[contains(@class,'message') or contains(@class,'cl-green') or contains(@class,'custom-options-warning') or contains(@class,'msg-success')]";
            scrollIntoElementView(xpath);
            verifyElementText(xpath, message);
        }
    }

    public void verifyNumberCollectionShown(int numberCollection) {
        String xpath = "";
        if (theme.equalsIgnoreCase("inside")) {
            xpath = "//*[contains(@data-id,'collection_list')]//div[contains(@class,'collection-card')]";
        } else xpath = "//*[contains(@data-id,'collection_list')]//div[contains(@class,'row')]//a";
        int actNumber = countElementByXpath(xpath);
        assertThat(actNumber).isEqualTo(numberCollection);
    }

    public void verifyHeading(String heading) {
        String xpathHeading = "//section[@class='section logo-list']//h2";
        verifyElementText(xpathHeading, heading.toUpperCase());
    }


    public void verifyNumberLogoShown(int lg) {
        int actNumber = countElementByXpath(logoList);
        assertThat(actNumber).isEqualTo(lg);
    }

    public void verifyAltLogo(String alt, int index) {
        String xpath = "(" + logoList + ")[" + index + "]//img";
        String act = getAttributeValue(xpath, "alt");
        assertThat(act).isEqualTo(alt);
    }

    public void verifyLinkTextLogo(String link, int index) {
        String xpath = "(" + logoList + ")[" + index + "]//a";
        String act = getAttributeValue(xpath, "href");
        assertThat(act).isEqualTo(link);
    }

    public void verifyImgLogoList(String img, int index) {
        String xpath = "(" + logoList + ")[" + index + "]//img";
        String act = getAttributeValue(xpath, "src");
        if (!img.isEmpty()) {
            assertThat(act).contains("img.btdmp.com");
        } else {
            assertThat(act).startsWith("data:image/png");
        }
    }


    public void verifyLayoutImageWithText(String layout) {
        if (layout.equals("Image with text above")) {
            verifyElementPresent("//section[contains(@data-id,'image_with_text') and contains(@class,'feature-block')]", true);
        } else
            verifyElementPresent("//section[contains(@data-id,'image_with_text') and contains(@class,'feature-set')]", true);
    }

    public void verifyTextAlignmentImageWithText(String textAlignment) {
        if (textAlignment.equalsIgnoreCase("Centre")) {
            textAlignment = "center";
        }
        verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'feature-content text-align-" + textAlignment.toLowerCase() + "')]", true);
    }

    public void verifyImagePerRowImageWithText(String imagesPerRow) {
        switch (imagesPerRow) {
            case "2":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'col-12 col-md-6')]", true);
                break;
            case "3":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'col-12 col-md-4')]", true);
                break;
            case "4":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'col-12 col-md-3')]", true);
                break;
        }
    }

    public void verifyPromotionsAnimationImageWithText(String promotionsAnimation) {
        switch (promotionsAnimation) {
            case "Fade in":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'content--fade-in')]", true);
                break;
            case "Fade up":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'content--fade-up')]", true);
                break;
            case "Fade down":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'content--fade-down')]", true);
                break;
            case "None":
                verifyElementPresent("//section[contains(@data-id,'image_with_text')]//div[contains(@class,'content--fade')]", false);
                break;
        }
    }


    public void verifyImageWithText(String image, int resID) {
        verifyElementPresent("(//section[contains(@data-id,'image_with_text')]//img)[" + resID + "]", true);
    }

    public void verifyAltTextImageWithTex(String altText, int resID) {
        verifyElementPresent("(" + xpathImageWithText + "//img[@alt='" + altText + "'])[" + resID + "]", true);
    }

    public void verifyYourHeadlineImageWithText(String yourHeadline, int resID) {
        verifyElementText("(" + xpathImageWithText + "//h2)[" + resID + "]", yourHeadline.toUpperCase());
    }

    public void verifyTextImageWithText(String text, int resID) {
        verifyElementText("(" + xpathImageWithText + "//p)[" + resID + "]", text);
    }

    public void verifyButtonLabel(String buttonLabel, int resID) {
        verifyElementPresent("(" + xpathImageWithText + "//a[normalize-space()='" + buttonLabel + "'])[" + resID + "]", true);
    }

    public void verifyDisplayAsaButton(Boolean isDisplayAsaButton, int index) {
        verifyElementPresent("(" + xpathImageWithText + "//a[contains(@class,'btn-primary')])[" + index + "]", isDisplayAsaButton);
    }

    public List<String> getProductShownInFeatureCollection() {
        return getListText(xpathFeatureCollection + "//div[contains(@class,'collection-product-wrap relative')]//span[@itemprop='name']");
    }

    public void clickShowMoreFeaturedCollection() {
        clickOnElement(xpathFeatureCollection + "//*[contains(@class,'view-more')]");
        waitForPageLoad();
        waitABit(2000);
    }

    public void verifyImageBackgroundBannerExist(boolean isExisted) {
        verifyElementPresent("//*[contains(@data-id,'banner')]//img[contains(@data-srcset,'file')]", isExisted);
    }

    public void verifyAltTextBanner(String altText) {
        String curAlt = getAttributeValue("//*[contains(@data-id,'banner')]//img", "alt");
        assertThat(curAlt).isEqualTo(altText);
    }

    public void verifyPreHeadingBanner(String preHeading) {
        verifyElementText("//section[contains(@data-id,'banner')]//div[contains(@class,'text-wrap__preheading')]", preHeading);
    }

    public void verifyHeadingBanner(String heading) {
        verifyElementText("//section[contains(@data-id,'banner')]//h2", heading);
    }

    public void verifySubHeadingBanner(String subHeading) {
        verifyElementText("//section[contains(@data-id,'banner')]//div[contains(@class,'text-wrap__subheading')]", subHeading);
    }

    public void verifyFirstBtnLabelBanner(String firstBtnLbl) {
        switch (theme) {
            case "inside":
                verifyElementText("//*[contains(@data-id,'banner')]//a[contains(@class,'btn-primary')]", firstBtnLbl.toUpperCase());
                break;
            default:
                verifyElementText("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__first-link')]", firstBtnLbl);
        }
    }

    public void verifyFirstBtnLinkBanner(String firstBtnLink, String firstBtnLbl, String shop) {
        if (!firstBtnLink.isEmpty()) {
            switch (theme) {
                case "inside":
                    String xPath = "//*[contains(@data-id,'banner')]//a[normalize-space()='" + firstBtnLbl + "']";
                    String attributeAR = getAttributeValue(xPath, "href").replace("https://" + shop, "").trim();
                    assertThat(attributeAR).isEqualTo(firstBtnLink);
                    break;
                default:
                    verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__first-link')]", true);
            }

        }
    }

    public void verifyHighlightFirstBtnBanner(String highlightBtn1) {
        if (theme.equalsIgnoreCase("roller")) {
            if (Boolean.parseBoolean(highlightBtn1)) {
                verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__first-link') and contains(@class,'btn-primary')]", true);
            } else
                verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__first-link') and contains(@class,'btn-outline')]", true);
        }
    }

    public void verifySecondBtnLabelBanner(String secondBtnLbl) {
        switch (theme) {
            case "inside":
                verifyElementText("//*[contains(@data-id,'banner')]//a[contains(@class,'btn-outline')]", secondBtnLbl);
                break;
            default:
                verifyElementText("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__second-link')]", secondBtnLbl);
        }
    }

    public void verifySecondBtnLinkBanner(String secondBtnLink, String secondBtnLbl, String shop) {
        if (!secondBtnLink.isEmpty()) {
            switch (theme) {
                case "inside":
                    String xPath = "//*[contains(@data-id,'banner')]//a[normalize-space()='" + secondBtnLbl + "']";
                    String attributeAR = getAttributeValue(xPath, "href").replace("https://" + shop, "").trim();
                    assertThat(attributeAR).isEqualTo(secondBtnLink);
                    break;
                default:
                    verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__second-link')]", true);
            }

        }
    }

    public void verifyHighlightSecondBtnBanner(String highlightBtn2) {
        if (theme.equalsIgnoreCase("roller")) {
            if (Boolean.parseBoolean(highlightBtn2)) {
                verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__second-link') and contains(@class,'btn-primary')]", true);
            } else
                verifyElementPresent("//section[contains(@data-id,'banner')]//a[contains(@class,'carousel__second-link') and contains(@class,'btn-outline')]", true);
        }
    }

    public void verifyTextPositionBanner(String textPosition) {
        if (textPosition.equalsIgnoreCase("Centre"))
            textPosition = "center";
        verifyElementPresent("//section[contains(@data-id,'banner')]//div[contains(@class,'text-wrap--" + textPosition + "')]", true);
    }

    public void verifyTextAlignmentBanner(String textAlignment) {
        if (textAlignment.equalsIgnoreCase("Centre"))
            textAlignment = "center";
        String xpath = "";
        switch (theme) {
            case "inside":
                xpath = "//*[contains(@data-id,'banner')]//div[contains(@class,'banner-section--position-";
                break;
            default:
                xpath = "//section[contains(@data-id,'banner')]//div[contains(@class,'text-align-";
        }
        verifyElementPresent(xpath + textAlignment + "')]", true);
    }

    public void verifytabPosition(Boolean showProductContent, String tabPosition) {
        if (showProductContent) {
            if (tabPosition.toLowerCase().contains("right")) {
                verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__description-right')]", true);
            } else {
                switch (theme) {
                    case "inside":
                        verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__description-bottom')]", true);
                        break;
                    default:
                        verifyElementPresent(xpathFeatureProduct + "//section[contains(@class,'container-fluid')]", true);
                }
            }
        }
    }

    public void waitToLoadCollectionPage() {
        waitUntilElementVisible(iD("collection"), 5000);
    }

    public void verifyTypography(String xpath, String attribute, String cssValue) {
        String font = XH(xpath).getCssValue(attribute);
        Assertions.assertThat(font).contains(cssValue);

    }

    public void clickLocateCurrencyButton() {
        clickOnElement("//footer//div[contains(@class,'locale-currency-button')]");
    }

    public void verifyEnableCurrency(Boolean isEnable) {
        verifyElementPresent("//div[@class='currency-dropdown__title']", isEnable);
    }

    public void verifySupportedCurrencies(String supportedCurrencies, String currencies, String total) {
        String[] expCur = currencies.split(" ");
        List<String> actCur = getListCurrencyCode();
        if (supportedCurrencies.contains("Some currencies")) {
            assertThat(actCur.size()).isEqualTo(expCur.length);
            for (int i = 0; i < expCur.length; i++) {
                assertThat(actCur).contains(expCur[i]);
            }
        } else assertThat(actCur.size()).isEqualTo(Integer.parseInt(total));
    }

    public List<String> getListCurrencyCode() {
        String xpath = "//span[@class='mr8 currency-dropdown__item__code']";
        if (isElementExist(xpath))
            return getListText(xpath);
        return null;
    }

    public List<String> getListLanguage() {
        String xpath = "//span[@class='locale-dropdown__item__name']";
        if (isElementExist(xpath))
            return getListText(xpath);
        return null;
    }

    public void verifyFormatCurrency(String format) {
        String actualPrice = getText("//span[contains(@class,'price')]");
        if (format.equals("Without currency ($10)")) {
            assertThat(actualPrice).doesNotContain("USD");
        } else assertThat(actualPrice).contains("USD");
    }

    public void verrifyEnableLanguage(boolean isEnable) {
        verifyElementPresent("//div[@class='locale-dropdown__title']", isEnable);
    }

    public void verifySupportLanguage(String supportedLanguages, String languages) {
        String[] expLanguage = (languages.split(" "));
        List<String> actLanguage = getListLanguage();
        if (supportedLanguages.contains("Some languages")) {
            assertThat(actLanguage.size()).isEqualTo(expLanguage.length);
            for (int i = 0; i < (expLanguage.length); i++) {
                assertThat(actLanguage).contains((expLanguage[i]).substring(3));
            }
        } else assertThat(actLanguage.size()).isEqualTo(59);
    }

    public void verifyDefaultLanguage(String defaultLanguage) {
        String actLanguage = getText("//div[@class='active locale-dropdown__item']//span");
        assertThat(actLanguage.toLowerCase()).contains(defaultLanguage);
    }

    public void verifySaleType(String saleType) {
        String savePrice = getText(xpathFeatureProduct + "//span[contains(@class,'product__price-sale')]");
        if (saleType.contains("Percentage")) {
            assertThat(savePrice).contains("%");
        } else assertThat(savePrice).doesNotContain("%");
    }

    public void verifyShowQuantityBoxAndAddToCartButton(Boolean isShow) {
        verifyElementPresent(xpathFeatureProduct + "//button[normalize-space()='Buy Now']", isShow);
    }

    public void verifyShowTrustBadgeImage(Boolean isShow) {
        verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__trust-badge')]", isShow);
    }

    public void verifyRaito(String ratio) {
        String r;
        switch (ratio) {
            case "21:9":
                r = "21by9";
                break;
            case "16:9":
                r = "16by9";
                break;
            case "3:1":
                r = "3by1";
                break;
            case "4:1":
                r = "4by1";
                break;
            default:
                r = "fit";
        }
        verifyElementPresent("//section[@data-id='homepage_slideshow']//div[contains(@class,'relative slideshow-section__background slideshow-section__background-" + r + "')]", true);
    }

    public void verifyLayoutSlideshow(String layout) {
        if (layout.equalsIgnoreCase("Box")) {
            verifyElementPresent("//div[@class='slideshow-section section slideshow-box-layout']", true);
        } else verifyElementPresent("//div[@class='slideshow-section section']", true);
    }

    public void verifyFullWidthSlideshow(Boolean isFullWidth) {
        verifyElementPresent("//section[@data-id='homepage_slideshow']//div[@class='container-fluid']", isFullWidth);
    }

    public void verifyColorBgrSlideshow(String color) {
        verifyColor("//div[contains(@class,'container slideshow-section__caption')]", "background-color", color);
    }

    public void verifyHeadingOnSlideshow(String heading) {
        verifyElementText("//*[@data-id='homepage_slideshow']//div[@class='content' or contains(@class,'container slideshow-section__caption')]//h1", heading.toUpperCase());

    }

    public void verifyTextOnSlideshow(String text) {
        verifyElementText("//div[@data-id='homepage_slideshow']//div[@class='content' or contains(@class,'container slideshow-section__caption')]//div[contains(@class,'text')]", text.toUpperCase());
    }

    public void verifyPrimarybtnOnSlideshow(String primaryBtnLab) {
        if (!primaryBtnLab.isEmpty()) {
            verifyElementText("//div[@class='content' or contains(@class,'container slideshow-section__caption')]//a[contains(@class,'btn-primary')]//span", primaryBtnLab.toUpperCase());
        }
    }

    public void verifySecondaryOnSlideshow(String secondaryBtnLab) {
        if (!secondaryBtnLab.isEmpty()) {
            verifyElementText("//div[@class='content' or contains(@class,'container slideshow-section__caption')]//a[contains(@class,'btn-outline')]//span", secondaryBtnLab.toUpperCase());
        }
    }

    public void verifyTextAlignmentSlideshow(String textAlignment) {
        String align = "right";
        switch (textAlignment) {
            case "Left":
                align = "left";
                break;
            case "Center":
                align = "center";
        }
        verifyElementPresent("//div[@class='container slideshow-section__caption text-align-" + align + "']", true);

    }

    String xpathCollectionList = "//*[contains(@data-id,'collection_list')]";

    public void verifyLayoutCollectionList(String layout) {
        switch (layout) {
            case "Mix":
                verifyElementPresent(xpathCollectionList + "//div[contains(@class,'collection-list--mix')]", true);
                break;
            default:
                verifyElementPresent(xpathCollectionList + "//a[contains(@class,'collection-list__basic')]", true);
        }
    }

    public void verifyImageStyleCollectionList(String imageStyles) {
        String style = "square";
        switch (imageStyles) {
            case "Landscape":
                style = "4by3";
                break;
            case "Portraits":
                style = "4by5";
                break;
        }
        verifyElementPresent(xpathCollectionList + "//img[parent::div[contains(@class,'aspect-ratio is-" + style + "')]]", true);
    }

    public void verifyTitlePositionCollectionList(String titlePosition) {
        if (titlePosition.equalsIgnoreCase("Inside images")) {
            verifyElementPresent(xpathCollectionList + "//h5[contains(@class,'on-image')]", true);
        } else verifyElementPresent(xpathCollectionList + "//h5[contains(@class,'on-image')]", false);

    }

    public void verifyTitleBgCollectionList(Boolean isDisplay) {
        verifyElementPresent(xpathCollectionList + "//h5[contains(@class,'title-background')]", isDisplay);
    }

    public void verifyButtonCollectionList(String btnLabel, Boolean isDisplay) {
        if (!btnLabel.isEmpty()) {
            if (isDisplay) {
                verifyElementText(xpathCollectionList + "//a[contains(@class,'btn-view-all')]", btnLabel);
                verifyElementPresent(xpathCollectionList + "//a[contains(@class,'view-more-link')]", true);
            } else {
                verifyElementPresent(xpathCollectionList + "//a[contains(@class,'btn-subtle')]", true);
                verifyElementText(xpathCollectionList + "//a[contains(@class,'btn-view-all')]", btnLabel.toUpperCase());
            }
        } else verifyElementPresent(xpathCollectionList + "//a[contains(@class,'btn-view-all')]", false);
    }

    public void clickOnBtnLabel(String btnLabel, Boolean isDisplay) {
        if (!btnLabel.isEmpty()) {
            if (isDisplay) {
                clickOnElement(xpathCollectionList + "//a[contains(@class,'view-more-link')]");
            } else clickOnElement(xpathCollectionList + "//a[contains(@class,'btn-view-all')]");
        }
    }

    public void verrifyLinkToColectionList(String btnLabel, Boolean isDisplay) {
        if (!btnLabel.isEmpty()) {
            if (isDisplay) {
                verifyElementPresent("//div[@id='list-collections']", true);
            }
        }
    }

    public void verifyTitleAlignmentCollectionList(String titleAlignment) {
        if (titleAlignment.equalsIgnoreCase("Center")) {
            verifyElementPresent(xpathCollectionList + "//div[@class='text-align-center']", true);
        } else verifyElementPresent(xpathCollectionList + "//div[@class='text-align-center']", false);
    }

    public void verifyHomepageDisplay() {
        waitForEverythingComplete();
        verifyElementPresent("//div[contains(@class,'default-layout')]", true);
    }

    public void verifySectionExisted(String section) {
        verifyElementPresent("//section[@data-name='Image With Text' or @data-name='Featured Product']", false);
    }

    public void verifyHeadingPosition(String headingPosition) {
        if (headingPosition.equalsIgnoreCase("In margin with text")) {
            verifyElementPresent("//div[contains(@class,'rich-text-section')]//div[@class='col-12 rich-text-section__heading col-lg-4']", true);
        } else {
            verifyElementPresent("//div[contains(@class,'rich-text-section')]//div[@class='col-12 rich-text-section__heading col-lg-8']", true);
        }
    }

    public void verifyShowWidthSection(Boolean isFull) {
        if (!isFull) {
            verifyElementPresent("//*[contains(@data-id,'banner')]//div[contains(@class,'banner-section--no-full')]", true);
        } else
            verifyElementPresent("//*[contains(@data-id,'banner')]//div[contains(@class,'container d-block')]", true);
    }

    public void verifyTextAlignmentNewsletter(String textAlignment) {
        verifyElementPresent(xpathNewLetter + "//div[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
    }

    public void verifyShowFullWidthNewsletter(Boolean isFull) {
        verifyElementPresent(xpathNewLetter + "//div[contains(@class,'newsletter-shadow--container')]", !isFull);
    }

    public void verifyBgImageNewsletter(String img) {
        if (!img.isEmpty()) {
            verifyElementPresent(xpathNewLetter + "//img", true);
        } else verifyElementPresent(xpathNewLetter + "//div[contains(@class,'newsletter-shadow--no-img')]", true);
    }

    public String getDesktopLogo() {
        String logo = getAttributeValue("//section[contains(@class,'header')]//img", "src");
        if (logo.contains("@")) {
            String result[] = logo.split("@");
            return result[result.length - 1];
        } else return logo;
    }

    public String getFavicon() {
        String logo = "";
        logo = getAttributeValue("//head//link[@rel='shortcut icon']", "href");
        if (logo.contains("@")) {
            String result[] = logo.split("@");
            return result[result.length - 1];
        } else return logo;
    }

    public void verifyTitleTestimonial(String title) {
        String xpath = "//div[contains(@class,'testimonial-container')]//p[@class='h3 text-align-center mb24']";
        verifyElementText(xpath, title.toUpperCase());
    }

    public void verifyAnimationTestimonial(String animation) {
        String xpath = "(//div[child::div[@class='testimonial-content']])[1]";
        String classText = getAttributeValue(xpath, "class");
        if (!animation.equals("None")) {
            assertThat(classText).contains(animation.replace(" ", "-").toLowerCase());
        } else {
            assertThat(classText).endsWith("testimonial-wrap--darken");
        }

    }

    public void verifyAltTextTestimonial(String altText) {
        String alt;
        if (theme.equalsIgnoreCase("roller")) {
            alt = getAttributeValue("//div[@class='VueCarousel-inner' and descendant::div[@class='testimonial-content']]//img", "alt");
        } else
            alt = getAttributeValue("//div[contains(@class,'testimonial-slide') and descendant::div[contains(@class,'testimonial-wrap')]]//img", "alt");
        assertThat(alt).isEqualTo(altText);

    }

    public void verifyBackgroundTestimonial(boolean imgBackground, boolean darkenBackground) {
        verifyElementPresent("(//div[@class='VueCarousel-inner' and descendant::div[@class='testimonial-content']]//div[@role='tabpanel'])[1]//div[@class='overlay darken-background']", darkenBackground);
        verifyElementPresent("(//div[@class='VueCarousel-inner' and descendant::div[@class='testimonial-content']]//div[@role='tabpanel'])[1]//div[@class='overlay']", imgBackground);

    }

    public void verifyTestimonial(String testimonial) {
        verifyElementText("(//div[@class='testimonial-content']//div)[1]", testimonial);
    }

    public void verifyCustomer(String customer) {
        verifyElementText("(//div[@class='testimonial-content']//p[@class='testimonial-name is-uppercase'])[1]", customer.toUpperCase());
    }

    public void verifyStore(String store) {
        verifyElementText("//div[@class='testimonial-content']//a[@class='testimonial-company']", store);
    }

    public void verifyLinkTestimonial(String link) {
        verifyElementPresent("//a[@href ='/collections/" + link.toLowerCase() + "']", true);

    }

    public void verifyAlignmentTestimonial(String alignment) {
        verifyCssValueByElement("(//div[@class='testimonial-content'])[1]", "text-align", alignment);

    }

    public void verifyLayoutHeader(String layout) {
        String xpath;
        switch (layout) {
            case "Inline":
                xpath = "//div[@class='flex items-center']";
                break;
            case "Rich":
                xpath = "//div[@class='main-nav-wrapper__block']";
                break;
            default:
                xpath = "//div[@class='header__minimal no-gutters flex items-center']";
        }
        verifyElementPresent(xpath, true);
    }

    public void verifyMainMenuHeaderInside(boolean showMenu) {
        verifyElementPresent("//li[@class='site-nav__item relative m0']", showMenu);
    }

    public void verifyImgProductCard(String imgDisplay) {
        String xpathPortraits = "//div[contains(@class,'collection-product-wrap relative')]//div[contains(@class,'4by5')]";
        String xpathSquare = "//div[contains(@class,'collection-product-wrap relative')]//div[contains(@class,'square')]";
        switch (imgDisplay) {
            case "Portraits":
                verifyElementPresent(xpathPortraits, true);
                break;
            case "Square ratio":
                verifyElementPresent(xpathSquare, true);
                break;
            default:
                verifyElementPresent(xpathPortraits, false);
                verifyElementPresent(xpathSquare, false);
        }
    }

    public void verifyAlignProductCard(String contentAlign) {
        String xpath = "//div[@class='collection-detail__product-details text-align-" + contentAlign.toLowerCase() + "']";
        verifyCssValueByElement(xpath, "text-align", contentAlign.toLowerCase());
    }

    public void verifyShowSale(boolean showSale) {
        verifyElementPresent("//div[@class='banner_holder']//div[normalize-space()='Sale']", showSale);
    }

    public void verifyShowBtnAddToCart(boolean showAddToCart) {
        String xpath = "//div[@class='collection-product-wrap relative collection-product-wrap--add-cart']";
        verifyElementPresent(xpath, showAddToCart);
    }

    public void verifyShape(String shape) {
        String xpath = "//*[contains(@class,'shape-sharp-t-l')]";
        String xpathStyleRound = "//*[contains(@class,'shape-round-all')]";
        String xpathStyleCut = "//*[contains(@class,'shape-cut-t-l')]";
        switch (shape) {
            case "Round":
                verifyCssValueByElement(xpathStyleRound, "border-radius", "24px");
                break;
            case "Cut":
                verifyCssValueByElement(xpathStyleCut, "-webkit-clip-path", "polygon");
                break;
            default:
                verifyElementPresent(xpath, true);
        }
    }

    public void verifyShowTopIcon(boolean showTopIcon) {
        if(theme.equals("insidev2")){
            scrollIntoElementView("//footer");
        }else{
            scrollIntoElementView("//div[@id='section-footer']");
        }
        verifyElementPresent("//button[contains(@class,'scroll-to-top')]", showTopIcon);
    }

    String xpathGalleryImage = "//*[contains(@data-id,'gallery_image')]";

    public void verifyHeadlineGalleryImage(String headline) {
        scrollIntoElementView("//*[contains(@data-id,'gallery_image')]//h4");
        verifyElementText("//*[contains(@data-id,'gallery_image')]//h4", headline.toUpperCase());
    }

    public void veridyContentAlignment(String contentAlignment) {
        if (contentAlignment.equalsIgnoreCase("Center")) {
            verifyElementPresent(xpathGalleryImage + "//h4[@class='text-align-center mb24']", true);
        } else verifyElementPresent(xpathGalleryImage + "//h4[@class='text-align-left mb24']", true);

    }

    public void verifyShowFullWidthSection(Boolean isFull) {
        if (isFull) {
            verifyElementPresent(xpathGalleryImage + "//div[@class='container-fluid']", true);
        } else verifyElementPresent(xpathGalleryImage + "//div[@class='container']", true);
    }

    public void verifyShowSpacSection(Boolean isSpac) {
        if (isSpac) {
            verifyElementPresent(xpathGalleryImage + "//div[@class='row gallery-image__custom-row no-gutters']", true);
        } else verifyElementPresent(xpathGalleryImage + "//div[@class='row gallery-image__custom-row']", true);
    }

    public void verifyGalleryImageList(boolean issshowImg) {
        verifyElementPresent(xpathGalleryImage + "//img[@data-srcset]", issshowImg);

    }

    public void verifyShowFullProductDescription(Boolean isShowFullProDes) {
        if (isShowFullProDes) {
            verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__description ')]", true);
        } else verifyElementPresent(xpathFeatureProduct + "//div[contains(@class,'product__description ')]", false);
    }

    public void hoverToProduct(String productName) {
        scrollIntoElementView("//div[contains(@class,'rich-text-section')]//h4");
        hoverThenClickElement("//section[contains(@class,'featured-collection')]//img[@alt='" + productName + "']", "//section[contains(@class,'featured-collection')]//span[normalize-space()='Add to cart']");
    }

    public void clickBtnAddToCartOnPopup() {
        clickOnElement("//div[contains(@class,'inside-modal__body')]//span[normalize-space()='Add to cart']");
        waitForPageLoad();
    }

    public void verifyAddToCartProduct(String productName) {
        verifyElementText("//div[@id='cart']//descendant::p[contains(@class,'product-cart__name')]", productName);
    }

    public void clickOnImage() {
        clickOnElement(xpathGalleryImage + "//div[contains(@class,'aspect-ratio is-square')]");
    }

    public void verifyOpenZoomImage() {
        verifyElementPresent(xpathGalleryImage + "//div[contains(@class,'popover-bottom__overlay')]", true);
    }

    public void clickBtnCloseImage() {
        clickOnElement(xpathGalleryImage + "//div[contains(@class,'inside-modal__body__icon-close')]");
    }

    public void clickOnLinkLabel() {
        clickOnElement("//*[contains(@data-id,'rich_text')]//a");
        waitForPageLoad();
    }

    public void verifyLinkToPage() {
        verifyElementPresent("//div[@id='list-collections']", true);
    }

    public void clickOnImageBanner() {
        clickOnElement("//div[contains(@class,'banner-section')]//div[@class='banner-section__wrap relative']/a");
    }


    public void searchEmail(String email) {
        waitClearAndTypeThenEnter("//input[@type='text']", email);
        waitForEverythingComplete();
    }

    public void verfiNumberSubscribed(String email, int expect) {
        int actual = countElementByXpath("//tr[descendant::span[normalize-space()='" + email + "']]");
        assertThat(actual).isEqualTo(expect);
    }

    public void verifyimageLinkBanner(String imgLink, String shop) {
        switch (theme) {
            case "inside":
                String attributeAR1 = getAttributeValue("//*[contains(@data-id,'banner')]//div[contains(@class,'banner-section__image')]//ancestor::a", "href").replace("https://" + shop, "").trim();
                assertThat(attributeAR1).isEqualTo(imgLink);
                break;
            default:
                verifyElementPresent("//section[contains(@data-id,'banner')]//div[contains(@class,'carousel-background')]//ancestor::a", true);

        }
    }

    public void verifyWidgetOnSF(String label) {
        waitForEverythingComplete();
        String xpath = "//div[@class='usell-section']//h1[normalize-space()='" + label + "']";
        verifyElementPresent(xpath, true);
    }

    public void openHomepage() {
        clickOnElement("//main[@class='main-content']//a[normalize-space()='Home']");
        waitForPageLoad();
    }

    public void verifyReviewsOnSF() {
        verifyElementPresent("//div[@data-id='review_widget']", true);
    }

    public void verifyProductsFromTheSameCollectionsOnF(String label) {
        verifyElementPresent("//div[@data-name='" + label + "']", true);
    }

    public void verifySizeLogo(String size) {
        waitForPageLoad();
        verifyElementPresent("//div[contains(@class,'col-lg') or @class='logo']//img[contains(@class,'" + size + "')]", true);
    }

    public void verifyText(String text) {
        verifyElementContainsText("//section[contains(@data-id,'introduction_text')]//h1", text);
    }

    public void verifyBodyText(String sectionName, String text) {
        verifyElementContainsText("//section[contains(@data-id,'introduction_text')]//div[contains(@class,'text-align')]", text);
    }

    public void verifyParallaxScrolling(Boolean isParallaxScrolling) {
        if (theme.equalsIgnoreCase("inside")) {
            verifyElementPresent("//div[contains(@class,'slideshow-section')]//img[contains(@class,'parallax')]", isParallaxScrolling);
        } else
            verifyElementPresent("//section[contains(@class,'slideshow-carousel')]//img[contains(@class,'parallax')]", isParallaxScrolling);
    }

    public void verifyParallaxScrollingBanner(Boolean isParallaxScrolling) {
        verifyElementPresent("//*[contains(@class,'banner')]/img[contains(@class,'parallax')]", isParallaxScrolling);
    }

    public void verifyLogo(boolean isshowLogo) {
        verifyElementPresent("//div[contains(@class,'col-lg-2')]//img", isshowLogo);
    }

    public void verifyMenuShown(boolean isShowMenu) {
        verifyElementPresent("//div[contains(@class,'flex-grow')]//div[contains(@class,'header-section__nav-item')]", isShowMenu);
    }

    public void verifyTextAnnounce(String text, String buttonLink) {
        if (!buttonLink.isEmpty()) {
            verifyElementText("//div[contains(@class,'header-section')]//p", text);
        } else {
            verifyElementText("//div[contains(@class,'header-section')]//h5", text);
        }
    }

    public void verifyButtonLinkonHeader(String buttonLink, boolean showAnnouncement) {
        if (!buttonLink.isEmpty() && showAnnouncement == true) {
            String linkAR = getAttributeValue("//div[contains(@class,'header-section')]//a[contains(@class,'announcement-bar-link')]", "href");
            assertThat(linkAR).contains("https://" + shop + buttonLink);
        }
    }

    public void verifyShowAnnouncement(String buttonLink, boolean showAnnouncement) {
        if (!buttonLink.isEmpty()) {
            verifyElementPresent("//div[contains(@class,'header-section')]//p", showAnnouncement);
        } else {
            verifyElementPresent("//div[contains(@class,'header-section')]//h5", showAnnouncement);
        }
    }

    public void verifyBarColor(String barColor, boolean showAnnouncement) {
        if (showAnnouncement) {
            String barColorAR = getAttributeValue("//div[contains(@class,'header-section')]//div[contains(@class,'text-align')]", "style").split(";")[0].replace("background-color:", "").trim();
            assertThat(barColor).contains(barColorAR);
        }

    }

    public void verifyTextColor(String textColor, boolean showAnnouncement, String buttonLink) {
        String textColorAR;
        if (showAnnouncement) {
            if (!buttonLink.isEmpty()) {
                textColorAR = getAttributeValue("//div[contains(@class,'header-section')]//p", "style").split(";")[0].replace("color:", "").trim();
            } else {
                textColorAR = getAttributeValue("//div[contains(@class,'header-section')]//h5", "style").split(";")[0].replace("color:", "").trim();
            }
            assertThat(textColor).contains(textColorAR);
        }
    }

    public void verifyHomePageOnly(boolean homePageOnly) {

    }

    //Theme inside v2
    public void verifyLayoutSlide(String layout) {
        if (layout.equalsIgnoreCase("Box")) {
            verifyElementPresent("//div[@class='slideshow-section section slideshow-box-layout']", true);
        } else verifyElementPresent("//div[@class='slideshow-section section']", true);
    }

    public void verifyFullWidthSection(String sectionName, Boolean status) {
        verifyElementPresent(String.format(xpathInsideSection, sectionName) + "//div[@class='container-fluid']", status);
    }

    public void verifyRatio(String sectionName, String ratio) {
        verifyElementPresent(String.format(xpathInsideSection, sectionName) + "//div[contains(@class,'slideshow-section__background-" + ratio + "')]", true);
    }

    public void verifyOpacity(String sectionName, String opacity) {
        verifyElementPresent(String.format(xpathInsideSection, sectionName) + "//*[contains(@style,'opacity:" + opacity + "')]", true);
    }

    public void verifyParallaxScrolling(String sectionName, Boolean isParallaxScrolling) {
        verifyElementPresent(String.format(xpathInsideSection, sectionName) + "//img[contains(@class,'parallax')]", isParallaxScrolling);
    }

    public void verifyImageExist(String sectionName, boolean isExisted, int indexBlock) {
        String xpath = String.format(xpathSlideBlock, sectionName, indexBlock) + "//img[contains(@srcset,'file')]";
        verifyElementPresent(xpath, isExisted);
    }

    public void verifyAltText(String sectionName, String altText, int indexBlock) {
        String curAlt = getAttributeValue(String.format(xpathSlideBlock, sectionName, indexBlock) + "//img", "alt");
        AssertionsForClassTypes.assertThat(curAlt).isEqualTo(altText);
    }

    public void verifyimageURL(String sectionName, String imgURL, String shop, int indexBlock) {
        String href = getAttributeValue(String.format(xpathSlideBlock, sectionName, indexBlock) + "//img//ancestor::a", "href").replace("https://" + shop, "").trim();
        AssertionsForClassTypes.assertThat(href).isEqualTo(imgURL);
    }

    public void verifyBackgroundImage(String sectionName, String backgroundColor, int indexBlock) {
        String xpath = String.format(xpathSlideBlock, sectionName, indexBlock) + "//*[contains(@style, 'background-color:" + backgroundColor + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyHeadline(String sectionName, String headline, int indexBlock) {
        String xpath = String.format(xpathSlideBlock, sectionName, indexBlock) + "//h1";
        verifyElementText(xpath, headline.toUpperCase());
    }

    public void verifyText(String sectionName, String text, int indexBlock) {
        verifyElementText(String.format(xpathSlideBlock, sectionName, indexBlock) + "//*[contains(@class,'section__caption-text')]", text);
    }

    public void verifyTextAlignment(String sectionName, String textAlignment, int indexBlock) {
        String xpath = String.format(xpathSlideBlock, sectionName, indexBlock) + "//*[contains(@class, 'text-align-" + textAlignment.toLowerCase() + "')]";
        verifyElementPresent(xpath, true);
    }

    public void verifyButton(String sectionName, String buttonLabel, String buttonLink, int indexBlock, String shop) {
        String href = getAttributeValue(String.format(xpathSlideBlock, sectionName, indexBlock) + "//a[normalize-space()='"+buttonLabel+"']","href").replace("https://" + shop, "").trim();
        AssertionsForClassTypes.assertThat(href).contains(buttonLink);
    }

    public void verifyShowSectionSF(String sectionName, boolean isShow) {
        verifyElementPresent("//section[@type='"+sectionName.toLowerCase(Locale.ROOT).replace(" ", "-")+"']", isShow);
    }

    public void verifyPrice(String value) {
        String actual = getText("//span[contains(@class,'product__price-span')]");
        assertThat(actual).isEqualTo(value);
    }

    public void verifyComparePrice(String value) {
        String actual = getText("//span[contains(@class,'product__price-original')]//del");
        assertThat(actual).isEqualTo(value);
    }

    public void verifyFixedHeader(Boolean fixedHeader) {
        scrollIntoElementView("//section[@class='section product-carousel']");
        waitForEverythingComplete();
        verifyElementPresent("//section[@class='responsive header sticky']",fixedHeader);
    }

    //v2
    String xpathRichTextBlock = "//*[contains(@type,'rich-text')]";
            //"//*[contains(@class,'rich-text')]//*[contains(@class,'feature-set-content-wrap')]";

    public void verifyTextAlignmentRichTextV2(String textAlignment) {
        verifyElementPresent("//*[contains(@type,'rich-text')]//*[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
    }
    public void verifyDescriptionRichTextV2(String text) {
        verifyElementText("(" + xpathRichTextBlock+ ")//div[contains(@class,'rich-text-section__text mb16')]", text);
    }

    public void verifyButton(String buttonLabel, String link, String shop) {
        String href = getAttributeValue("(" + xpathRichTextBlock+ ")//a[normalize-space()='"+buttonLabel+"']","href").replace("https://" + shop, "").trim();
        AssertionsForClassTypes.assertThat(href).contains(link);
    }
    public void verifyButtonTypeRichTextV2(boolean buttonType) {
        verifyElementPresent("(" + xpathRichTextBlock+ ")//a[contains(@class, 'btn')]", buttonType);
    }
    public void verifyHeadingRichTextV2(String heading) {
        verifyElementText("(" + xpathRichTextBlock+ ")//h4", heading.toUpperCase());
    }

}
