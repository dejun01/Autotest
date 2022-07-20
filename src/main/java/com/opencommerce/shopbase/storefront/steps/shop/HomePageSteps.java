package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.HomePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomePageSteps extends ScenarioSteps {
    HomePage homePage;
    String shop = LoadObject.getProperty("shop");
    String shopname = LoadObject.getProperty("shopname");

    public void verifyEnableSearch(boolean enableSearch) {
        homePage.verifyEnableSearch(enableSearch);
    }

    public void verifyShowCart(boolean showCart, String cartIcon) {
        homePage.verifyShowCart(showCart);
        if (showCart) {
            homePage.verifyCartIcon(cartIcon);
        }
    }

    public void verifyShowAnnouncementBar(boolean showAnnouncementBar, String announcementMessage) {
        homePage.verifyAnnouncementBar(showAnnouncementBar,announcementMessage);
    }

    public void verifyTopBar(boolean showTopBar, boolean showSocialMediaHeaderIcons, String phoneNumber, String topBarMenu) {
        if (!showSocialMediaHeaderIcons && phoneNumber.isEmpty() && phoneNumber.isEmpty() && topBarMenu.equalsIgnoreCase("None"))
            showTopBar = false;

        homePage.verifyShowTopBar(showTopBar);
        if (showTopBar) {
            homePage.verifyShowSocialMediaHeaderIcons(showSocialMediaHeaderIcons);
            homePage.verifyPhoneNumber(phoneNumber);
            homePage.verifyTopBarMenu(topBarMenu);
        }
    }

    public void verifyMainMenu(String mainMenu) {
        boolean isShowMenu = true;
        if (mainMenu.equalsIgnoreCase("None")) {
            isShowMenu = false;
        }
        homePage.verifyMainMenuShown(isShowMenu);
    }

    public void verifyLogo(String logo) {
        boolean isshowLogo = true;
        if (logo.isEmpty()) {
            isshowLogo = false;
        }
        homePage.verifyLogoExist(isshowLogo);
    }

    public void verifyBackgroundImage(String backgroundImage, int slideshowIndex) {
        homePage.verifyBackgroundImage(backgroundImage, slideshowIndex);
    }

    public void verifyAltText(String altText, int slideshowIndex) {
    }


    public void verifyContentOnSlide(String preheading, String heading, String subheading, String firstButtonLabel, String secondButtonLabel, int slideshowIndex) {
        String expectedCotent = contentOnSlide(preheading, heading, subheading, firstButtonLabel, secondButtonLabel);
        String actualContent = homePage.getHeadingContent(slideshowIndex);
        assertThat(actualContent).isEqualTo(expectedCotent);
    }

    private String contentOnSlide(String preheading, String heading, String subheading, String firstButtonLabel, String secondButtonLabel) {
        String expectedCotent = "";
        List<String> listContent = asList(preheading, heading, subheading, firstButtonLabel, secondButtonLabel);
        int i = 0;
        for (String content : listContent) {
            if (!content.isEmpty()) {
                i++;
                if (i > 1) {
                    expectedCotent += "\n";
                }
                expectedCotent += content.toUpperCase();
            }
        }
        return expectedCotent;
    }

    public void verifButton(String buttonLabel, String buttonLink, String highlightButtonLabel, int index) {
        homePage.verifyButtonLabel(buttonLabel, index);
        homePage.verifyButtonLink(buttonLink, index);
        homePage.verifyHighlightButtonLabel(highlightButtonLabel, index);
    }

    public void showSlideShow(int slideshowIndex) {
        int indexSlideIsShowing = homePage.getSlideShowing();
        float n = slideshowIndex - indexSlideIsShowing;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                homePage.clickBtnNextPage();
            }
        } else {
            for (float i = n; i > 0; i++) {
                homePage.clickBtnPreviousPage();
            }
        }
    }

    public void verifyHeadingRichText(String heading) {
        homePage.verifyHeadingRichText(heading);
    }

    public void verifyTextAlignmentRichText(String text, String textAlignment) {
        homePage.verifyTextAlignmentRichText(text, textAlignment);
    }

    public void verifyLinkRichText(String linkLabel, String link) {
        homePage.verifyLinkLabelRichText(linkLabel);

    }

    public void verifyHeadingFeaturedCollection(String heading) {
        homePage.verifyHeadingFeaturedCollection(heading);
    }

    public void verifyCollectionShown(String collection, boolean isShow) {
        homePage.verifyFeaturedCollection(collection, isShow);
    }

    public void verifyLayoutShown(String collectionLayout) {
        homePage.verifLayoutTypeFeaturedCollection(collectionLayout);
    }

    public void verifyBtnViewMore(boolean showViewMoreButton, String viewMoreLable) {
        homePage.verifyBtnViewMoreShow(showViewMoreButton, viewMoreLable);
    }

    public void verifyCollectionListShown(String collections) {
        String[] collection = collections.split(",");
        int numberCollection = collection.length;
        homePage.verifyNumberCollectionShown(numberCollection);
        verifyNameCollection(collections);
    }

    public void verifyNameCollection(String collectionNames) {
        homePage.verifyNameCollection(collectionNames);
    }

    public void verifyCollectionData(String nameCollection, String img, String alt) {
        homePage.verifyCollectionData(nameCollection, img, alt);
    }

    public void verifyHeadingCollectionList(String heading) {
        if (!heading.isEmpty()) {
            homePage.verifyHeadingCollectionList(heading);
        }
    }

    public void verifyProductNameFeatureProduct(String productName) {
        homePage.verifyProductNameFeatureProduct(productName);
    }

    public void verifyShowArrowsOnProductGalleryFeatureProduct(boolean isShowarrowsonproductgallery) {
//        homePage.hoverProductImageFeatureProduct();
        homePage.verifyShowarrowsonproductgalleryFeatureProduct(isShowarrowsonproductgallery);
    }

    public void verifyImagePositionFeatureProduct(String imagePosition) {
        homePage.verifyIMagePositionFeatureProduct(imagePosition);
    }

    public void verifyShowVendor(boolean isShowVendor) {
        homePage.verifyShowVendor(isShowVendor);
    }

    public void verifyShowPriceSavings(boolean isShowPriceSavings) {
        homePage.verifyShowPriceSavings(isShowPriceSavings);
    }

    public void verifyShowProductContent(boolean isShowproductdescription) { //dang fix bug nen de lai sau
    }

    public void verifyShowsalebanners(boolean isShowsalebanners) { //dung cho collection list page
    }

    public void verifyOptionsStyle(String optionsStyle) {
        homePage.verifyOptionsStyle(optionsStyle);
    }

    public void verifySwatches(String optionsStyle, boolean enableVariantGroupSwatches, boolean enableColorSwatches) {
        homePage.verifySwatches(optionsStyle, enableVariantGroupSwatches, enableColorSwatches);
    }

    public void isShowQuantityBox(boolean showQuantityBox, boolean showQuantityBoxInSameLine, boolean isQuantityAndAddToCartBtn) {
        homePage.verifyShowquantitybox(showQuantityBox, showQuantityBoxInSameLine, isQuantityAndAddToCartBtn);
    }

    public void verifyTextFor0Sproduct(String textFor0Sproduct) {
        homePage.verifyTextFor0Sproduct(textFor0Sproduct);
    }


    public void verifyEnableProductGallerySlideshowFeatureProduct(boolean isEnableproductgalleryslideshow) {
//        homePage.clickProductImageFeatureProduct();
        homePage.verifyEnableproductgalleryslideshowFeatureProduct(isEnableproductgalleryslideshow);
    }

    public void verifyGalleryTransitionFeatureProduct(String galleryTransition) {
        homePage.verifygalleryTransitionFeatureProduct(galleryTransition);
    }

    public void verifyExampleProductDisplay() {
        homePage.verifyExampleProductDisplay();
    }

    public void verifyHeadingNewsletter(String heading) {
        homePage.verifyHeadingNewsletter(heading);
    }

    public void verifySubheadingNewsletter(String subheading) {
        homePage.verifySubheadingNewsletter(subheading);
    }

    public void signupEmail(String email, String message) {
        homePage.enterEmail(email);
        homePage.clickbtnSignUp();
        homePage.verifySignUp(message);
    }

    public String createNewEmail() {
        Date date = new Date();
        long timestamp = date.getTime() / 1000;
        return "shopbase" + timestamp + "@beeketing.net";
    }

    public void searchEmail(String email) {
        homePage.searchEmail(email);
    }

    public void verfiNumberSubscribed(String email, int expectedNumber) {
        homePage.verfiNumberSubscribed(email, expectedNumber);
    }


    //---------------Logo List------------------
    public void verifyHeadingLogoList(String heading) {
        homePage.verifyHeading(heading);
    }

    public void verifyNumberLogoShown(int numberLogo) {
        homePage.verifyNumberLogoShown(numberLogo);
    }

    public void verifyImgLogoList(String image, int index) {
        homePage.verifyImgLogoList(image, index);
    }

    public void verifyAltLogo(String alt, int index) {
        homePage.verifyAltLogo(alt, index);
    }

    public void verifyLinkTextLogo(String link, int index) {
        link = "https://" + shop + link;
        homePage.verifyLinkTextLogo(link, index);
    }

    // Image With text
    public void verifyLayoutImageWithText(String layout, String textAlignment, String imagesPerRow, String promotionsAnimation) {
        homePage.verifyLayoutImageWithText(layout);
        if (layout.equals("Image with text on the right")) {
            homePage.verifyTextAlignmentImageWithText(textAlignment);
        } else {
            homePage.verifyImagePerRowImageWithText(imagesPerRow);
            homePage.verifyPromotionsAnimationImageWithText(promotionsAnimation);
        }
    }

    public void verifyImageWithText(String image, int resID) {
        homePage.verifyImageWithText(image, resID);
    }

    public void verifyAltTextImageWithText(String altText, int resID) {
        homePage.verifyAltTextImageWithTex(altText, resID);
    }

    public void verifyYourHeadlineImageWithText(String yourHeadline, int resID) {
        homePage.verifyYourHeadlineImageWithText(yourHeadline, resID);
    }

    public void verifyTextImageWithText(String text, int resID) {
        homePage.verifyTextImageWithText(text, resID);
    }

    public void verifyButtonLabel(String buttonLabel, int resID) {
        homePage.verifyButtonLabel(buttonLabel, resID);
    }

    public void verifyDisplayAsaButton(boolean isDisplayAsaButton, int index) {
        homePage.verifyDisplayAsaButton(isDisplayAsaButton, index);
    }

    public void openHomepageStorefront(String shop) {
        homePage.openUrlInNewTab(shop);
        homePage.switchToWindowWithIndex(1);
        homePage.waitForPageLoad();
        homePage.deleteAllCookies();
        waitABit(2000);
    }

    public void verifyRedirectToPrimarydomain(String domains, String primaryDomain, Boolean isSta) {
        String currentURL = homePage.getCurrentUrl();
        primaryDomain = ("https://" + primaryDomain + "/");
        domains = ("https://" + domains + "/");
        if (isSta == true)
            assertThat(currentURL).isEqualTo(primaryDomain);
        else assertThat(currentURL).isEqualTo(domains);
    }

    public void backtoDomainPage() {
        homePage.switchToWindowWithIndex(0);
    }


    public void verifyRedirectDomainToPrimaryDomain(String domain, String primarydomain, boolean isEnableRedirectionDomain) {
        if (!domain.equals(shop)) {
            domain = "http://" + domain + "/?render_csr=1&is_delete_cache=true";
            openHomepageStorefront(domain);
            String currentURL = homePage.getCurrentUrl().replace("?render_csr=1&is_delete_cache=true", "").trim();
            if (isEnableRedirectionDomain) {
                assertThat(currentURL).isEqualTo("https://" + primarydomain + "/");
            } else {
                assertThat(currentURL).isNotEqualToIgnoringWhitespace(domain);
            }
            homePage.closeBrowser();
            homePage.switchToTheFirstTab();
        }
    }

    public List<String> getProductShownInFeatureCollection() {
        return homePage.getProductShownInFeatureCollection();
    }

    public void clickShowMoreFeaturedCollection() {
        homePage.clickShowMoreFeaturedCollection();
    }

    public String getCurrentLink() {
        return homePage.getCurrentUrl();
    }

    public void verifyTextExisted(String heading) {
        if (!heading.isEmpty())
            homePage.verifyElementPresent("//div[@id='app']//*[contains(text(),'" + heading + "')]", false);
    }

    public void closeBrowser() {
        homePage.closeBrowser();
        homePage.switchToTheFirstTab();
    }

    @Step
    public void verifyImgBakgroundBanner(String imgBg) {
        if (!imgBg.isEmpty()) {
            homePage.verifyImageBackgroundBannerExist(true);
        } else homePage.verifyImageBackgroundBannerExist(false);
    }

    @Step
    public void verifyAltTextBanner(String altText) {
        homePage.verifyAltTextBanner(altText);
    }

    @Step
    public void verifyPreHeadingBanner(String preHeading) {
        if (!preHeading.isEmpty()) {
            homePage.verifyPreHeadingBanner(preHeading);
        }
    }

    @Step
    public void verifyHeadingBanner(String heading) {
        if (!heading.isEmpty()) {
            homePage.verifyHeadingBanner(heading);
        }
    }

    @Step
    public void verifySubHeadingBanner(String subHeading) {
        if (!subHeading.isEmpty()) {
            homePage.verifySubHeadingBanner(subHeading);
        }
    }

    @Step
    public void verifyFirstBtnBanner(String firstBtnLbl, String firstBtnLink, String highlightBtn1) {
        if (!firstBtnLbl.isEmpty()) {
            homePage.verifyFirstBtnLabelBanner(firstBtnLbl.toUpperCase());
            homePage.verifyFirstBtnLinkBanner(firstBtnLink, firstBtnLbl, shop);
            homePage.verifyHighlightFirstBtnBanner(highlightBtn1);
        }
    }

    @Step
    public void verrifySecondBtnBanner(String secondBtnLbl, String secondBtnLink, String highlightBtn2) {
        if (!secondBtnLbl.isEmpty()) {
            homePage.verifySecondBtnLabelBanner(secondBtnLbl.toUpperCase());
            homePage.verifySecondBtnLinkBanner(secondBtnLink, secondBtnLbl, shop);
            homePage.verifyHighlightSecondBtnBanner(highlightBtn2);
        }
    }

    @Step
    public void verifyimageLinkBanner(String imgLink) {
        if (!imgLink.isEmpty()) {
            homePage.verifyimageLinkBanner(imgLink, shop);
        }
    }

    @Step
    public void verifyTextPosition(String textPosition) {
        if (!textPosition.isEmpty()) {
            homePage.verifyTextPositionBanner(textPosition);
        }
    }

    @Step
    public void verifyTextAlignmentBanner(String textAlignment) {
        if (!textAlignment.isEmpty()) {
            homePage.verifyTextAlignmentBanner(textAlignment);
        }
    }

    @Step
    public void verifytabPosition(Boolean showProductContent, String tabPosition) {
        if (!tabPosition.isEmpty()) {
            homePage.verifytabPosition(showProductContent, tabPosition);
        }
    }


    @Step
    public void verifyCollectionName(String collection) {
        homePage.waitToLoadCollectionPage();
        homePage.waitForTextToAppear(collection.toUpperCase());
    }

    @Step
    public void backToHomePage() {
        homePage.navigateToPreviousPage();
    }

    @Step
    public void verifyColor(String xpath, String attribute, String cssValue) {
        homePage.verifyColor(xpath, attribute, cssValue);
    }

    @Step
    public void verifyTypography(String xpath, String attribute, String cssValue) {
        homePage.verifyTypography(xpath, attribute, cssValue);
    }

    @Step
    public void clickLocateCurrencyButton() {
        homePage.clickLocateCurrencyButton();
    }

    @Step
    public void verifyEnableCurrency(Boolean isEnable, String supportedCurrencies, String currencies, String total) {
        homePage.verifyEnableCurrency(isEnable);
        if (isEnable) {
            homePage.verifySupportedCurrencies(supportedCurrencies, currencies, total);
        }
    }

    @Step
    public void verifyFormatCurrency(String format) {
        homePage.verifyFormatCurrency(format);
    }

    @Step
    public void verrifyEnableLanguage(boolean isEnable, String supportedLanguages, String languages, String defaultLanguage) {
//        homePage.verrifyEnableLanguage(isEnable);
        if (isEnable) {
            homePage.verifySupportLanguage(supportedLanguages, languages);
            homePage.verifyDefaultLanguage(defaultLanguage);
        }
    }

    @Step
    public void verifySaleType(Boolean isShow, String saleType) {
        if (isShow) {
            homePage.verifySaleType(saleType);
        }
    }

    @Step
    public void verifyShowQuantityBoxAndAddToCartButton(Boolean isShow) {
        homePage.verifyShowQuantityBoxAndAddToCartButton(isShow);
    }

    @Step
    public void verifyShowTrustBadgeImage(Boolean isShow) {
        homePage.verifyShowTrustBadgeImage(isShow);
    }

    @Step
    public void verifyRatioSlideshow(String ratio) {
        homePage.verifyRaito(ratio);

    }

    @Step
    public void verifyLayoutSlideshow(String layout) {
        homePage.verifyLayoutSlideshow(layout);
    }

    @Step
    public void verifyFullWidthSlideshow(Boolean isFullWidth) {
        homePage.verifyFullWidthSlideshow(isFullWidth);
    }

    @Step
    public void verifyColorBgrSlideshow(String layout, String color) {
        if (layout.equalsIgnoreCase("Box")) {
            homePage.verifyColorBgrSlideshow(color);
        }
    }

    @Step
    public void verifyHeadingOnSlideshow(String heading) {
        homePage.verifyHeadingOnSlideshow(heading);
    }

    @Step
    public void verifyTextOnSlideshow(String text) {
        homePage.verifyTextOnSlideshow(text);
    }

    @Step
    public void verifyPrimarybtnOnSlideshow(String primaryBtnLab) {
        homePage.verifyPrimarybtnOnSlideshow(primaryBtnLab);
    }

    @Step
    public void verifySecondaryOnSlideshow(String secondaryBtnLab) {
        homePage.verifySecondaryOnSlideshow(secondaryBtnLab);
    }

    @Step
    public void verifyTextAlignmentSlideshow(String textAlignment) {
        homePage.verifyTextAlignmentSlideshow(textAlignment);
    }

    @Step
    public void verifyLayoutCollectionList(String layout) {
        homePage.verifyLayoutCollectionList(layout);
    }

    @Step
    public void verifyImageStyleCollectionList(String imageStyles) {
        homePage.verifyImageStyleCollectionList(imageStyles);
    }

    @Step
    public void verifyTitlePositionCollectionList(String titlePosition) {
        homePage.verifyTitlePositionCollectionList(titlePosition);
    }

    @Step
    public void verifyTitleBgCollectionList(Boolean isDisplay) {
        homePage.verifyTitleBgCollectionList(isDisplay);
    }

    @Step
    public void verifyButtonCollectionList(String btnLabel, Boolean isDisplay) {
        homePage.verifyButtonCollectionList(btnLabel, isDisplay);
    }

    @Step
    public void clickOnBtnLabel(String btnLabel, Boolean isDisplay) {
        homePage.clickOnBtnLabel(btnLabel, isDisplay);
    }

    @Step
    public void verrifyLinkToColectionList(String btnLabel, Boolean isDisplay) {
        homePage.verrifyLinkToColectionList(btnLabel, isDisplay);
    }

    @Step
    public void verifyTitleAlignmentCollectionList(String titleAlignment) {
        homePage.verifyTitleAlignmentCollectionList(titleAlignment);
    }

    @Step
    public void verifyHomepageDisplay() {
        homePage.verifyHomepageDisplay();
    }

    @Step
    public void verifySectionExisted(String section) {
        homePage.verifySectionExisted(section);
    }

    @Step
    public void refreshpage() {
        homePage.refreshPage();
        homePage.waitForEverythingComplete();
    }

    @Step
    public void verifyHeadingPosition(String headingPosition) {
        homePage.verifyHeadingPosition(headingPosition);
    }

    @Step
    public void verifyHeadlineBanner(String headLine) {
        homePage.verifyElementText("//*[contains(@data-id,'banner')]//h3", headLine.toUpperCase());
    }

    @Step
    public void verifyDescriptionBanner(String description) {
        homePage.verifyElementText("//*[contains(@data-id,'banner')]//div[contains(@class,'banner-section__subtitle')]", description);
    }

    @Step
    public void verifyShowWidthSection(Boolean isFull) {
        homePage.verifyShowWidthSection(isFull);
    }

    @Step
    public void verifyTextAlignmentNewsletter(String textAlignment) {
        homePage.verifyTextAlignmentNewsletter(textAlignment);
    }

    @Step
    public void verifyShowFullWidthNewsletter(Boolean isFull) {
        homePage.verifyShowFullWidthNewsletter(isFull);
    }

    @Step
    public void verifyBgImageNewsletter(String img) {
        homePage.verifyBgImageNewsletter(img);
    }

    @Step
    public void verifyTitleTestimonial(String title) {
        homePage.verifyTitleTestimonial(title);
    }

    @Step
    public void verifyAnimationTestimonial(String animation) {
        homePage.verifyAnimationTestimonial(animation);
    }

    @Step
    public void verifyAltTextTestimonial(String altText) {
        homePage.verifyAltTextTestimonial(altText);
    }

    @Step
    public void verifyBackgroundTestimonial(boolean imgBackground, boolean darkenBackground) {
        homePage.verifyBackgroundTestimonial(imgBackground, darkenBackground);
    }

    @Step
    public void verifyTestimonial(String testimonial) {
        homePage.verifyTestimonial(testimonial);
    }

    @Step
    public void verifyCustomer(String customer) {
        homePage.verifyCustomer(customer);
    }

    @Step
    public void verifyStore(String store) {
        homePage.verifyStore(store);
    }

    @Step
    public void verifyLinkTestimonial(String link) {
        homePage.verifyLinkTestimonial(link);
    }

    @Step
    public void verifyAlignmentTestimonial(String alignment) {
        homePage.verifyAlignmentTestimonial(alignment);
    }

    @Step
    public String getDesktopLogo() {
        return homePage.getDesktopLogo();
    }

    @Step
    public String getFavicon() {
        return homePage.getFavicon();
    }

    @Step
    public void verifyApplylogoSuccessfully(String act, String exp) {
        assertThat(exp).containsIgnoringCase(act);
    }

    @Step
    public void verifyTopBarMenu(boolean showTopbar, String topBarMenu) {
        homePage.verifyShowTopBar(showTopbar);
        if (showTopbar) {
            homePage.verifyTopBarMenu(topBarMenu);
        }

    }

    @Step
    public void verifyLayoutHeader(String layout) {
        homePage.verifyLayoutHeader(layout);
    }

    public void verifyMainMenuInside(String mainMenu, String layout) {
        boolean showMenu;
        if (layout.equalsIgnoreCase("Minimal") || mainMenu.equalsIgnoreCase("None")) {
            showMenu = false;
        } else {
            showMenu = true;
        }
        homePage.verifyMainMenuHeaderInside(showMenu);
    }

    public void verifyImgProductCard(String imgDisplay) {
        homePage.verifyImgProductCard(imgDisplay);
    }

    public void verifyAlignProductCard(String contentAlign) {
        homePage.verifyAlignProductCard(contentAlign);
    }

    public void verifyShowSale(boolean showSale) {
        homePage.verifyShowSale(showSale);
    }

    public void verifyShowBtnAddToCart(boolean showAddToCart) {
        homePage.verifyShowBtnAddToCart(showAddToCart);
    }

    public void verifyShape(String shape) {
        homePage.verifyShape(shape);
    }

    public void verifyShowTopIcon(boolean showTopIcon) {
        homePage.verifyShowTopIcon(showTopIcon);
    }

    public void verifyHeadlineGalleryImage(String headline) {
        if (!headline.isEmpty()) {
            homePage.verifyHeadlineGalleryImage(headline);
        }
    }

    public void veridyContentAlignment(String contentAlignment) {
        homePage.veridyContentAlignment(contentAlignment);
    }

    public void verifyShowFullWidthSection(Boolean isFull) {
        homePage.verifyShowFullWidthSection(isFull);
    }

    public void verifyShowSpacSection(Boolean isSpac) {
        homePage.verifyShowSpacSection(isSpac);
    }

    public void verifyGalleryImageList(String img) {
        boolean issshowImg = true;
        if ((img.isEmpty())) {
            issshowImg = false;
        }
        homePage.verifyGalleryImageList(issshowImg);
    }

    public void verifyShowFullProductDescription(Boolean isShowFullProDes) {
        homePage.verifyShowFullProductDescription(isShowFullProDes);
    }

    public void hoverToProduct(String productName) {
        homePage.hoverToProduct(productName);
    }

    public void verifyAddToCartProduct(String productName) {
        homePage.verifyAddToCartProduct(productName);
    }

    public void clickBtnAddToCartOnPopup() {
        homePage.clickBtnAddToCartOnPopup();
    }

    public void clickOnImage() {
        homePage.clickOnImage();
    }

    public void clickBtnCloseImage() {
        homePage.clickBtnCloseImage();
    }

    public void verifyOpenZoomImage() {
        homePage.verifyOpenZoomImage();
    }

    public void clickOnLinkLabel() {
        homePage.clickOnLinkLabel();
    }

    public void verifyLinkToPage() {
        homePage.verifyLinkToPage();
    }

    public void clickOnImageBanner() {
        homePage.clickOnImageBanner();
    }


    public void verifyBestSellersOnSF(String label) {
        homePage.verifyWidgetOnSF(label);
    }


    public void verifyRecentlyViewedFeaturedProductsOnSF(String label) {
        homePage.verifyWidgetOnSF(label);
    }


    public void verifyCartRecommendations(String label) {
        homePage.verifyWidgetOnSF(label);
    }


    public void openHomepage() {
        homePage.openHomepage();
    }


    public void verifyReviewsOnSF() {
        homePage.verifyReviewsOnSF();
    }

    public void verifyWhoBoughtThisAlsoBoughtOnSF(String label) {
        homePage.verifyWidgetOnSF(label);
    }

    public void verifyHandpickedProductsOnSF(String label) {
        homePage.verifyWidgetOnSF(label);
    }

    public void verifyProductsFromTheSameCollectionsOnF(String label) {
        homePage.verifyProductsFromTheSameCollectionsOnF(label);
    }

    public void verifySizeLogo(String size) {
        homePage.verifySizeLogo(size);
    }

    @Step
    public void verifyText(String text) {
        homePage.verifyText(text);
    }

    @Step
    public void verifyBodyText(String sectionName, String text) {
        homePage.verifyBodyText(sectionName, text);
    }

    public void verifyParallaxScrolling(Boolean isParallaxScrolling) {
        homePage.verifyParallaxScrolling(isParallaxScrolling);
    }

    public void verifyParallaxScrollingBanner(Boolean isParallaxScrolling) {
        homePage.verifyParallaxScrollingBanner(isParallaxScrolling);
    }

    public void verifyLogoonSF(String logo) {
        boolean isshowLogo = true;
        if (logo.isEmpty()) {
            isshowLogo = false;
        }
        homePage.verifyLogo(isshowLogo);
    }

    public void verifyMenu(String menu) {
        boolean isShowMenu = true;
        if (menu.equalsIgnoreCase("None")) {
            isShowMenu = false;
        }
        homePage.verifyMenuShown(isShowMenu);
    }

    public void verifyTextAnnounce(String text, boolean showAnnouncement, String buttonLink) {
        if (showAnnouncement) {
            homePage.verifyTextAnnounce(text, buttonLink);
        }
    }

    public void verifyButtonLink(String buttonLink, boolean showAnnouncement) {
        homePage.verifyButtonLinkonHeader(buttonLink, showAnnouncement);
    }

    public void verifyShowAnnouncement(String buttonLink, boolean showAnnouncement) {
        homePage.verifyShowAnnouncement(buttonLink, showAnnouncement);
    }

    public void verifyBarColor(String barColor, boolean showAnnouncement) {
        homePage.verifyBarColor(barColor, showAnnouncement);
    }

    public void verifyTextColor(String textColor, boolean showAnnouncement, String buttonLink) {
        homePage.verifyTextColor(textColor, showAnnouncement, buttonLink);
    }

    public void verifyHomePageOnly(boolean homePageOnly) {
        homePage.verifyHomePageOnly(homePageOnly);
    }

    // Theme inside v2
    @Step
    public void verifyLayoutSlide(String layout) {
        homePage.verifyLayoutSlide(layout);
    }

    @Step
    public void verifyFullWidthSection(String sectionName, Boolean isFullWidth) {
        homePage.verifyFullWidthSection(sectionName, isFullWidth);
    }

    @Step
    public void verifyRatio(String sectionName, String ratio) {
        String r;
        if (ratio.equals("Fit first slide")) {
            r = "fit";
        } else {
            r = ratio.replace(":", "by");
        }
        homePage.verifyRatio(sectionName, r);
    }

    @Step
    public void verifyOpacity(String sectionName, String opacity) {
        homePage.verifyOpacity(sectionName, opacity);
    }

    @Step
    public void verifyParallaxScrolling(String sectionName, Boolean isParallaxScrolling) {
        homePage.verifyParallaxScrolling(sectionName, isParallaxScrolling);
    }

    @Step
    public void verifyImageExist(String sectionName, String img, int indexBlock) {
        if (!img.isEmpty()) {
            homePage.verifyImageExist(sectionName, true, indexBlock);
        } else homePage.verifyImageExist(sectionName, false, indexBlock);
    }

    @Step
    public void verifyAltText(String sectionName, String altText, int indexBlock) {
        homePage.verifyAltText(sectionName, altText, indexBlock);
    }

    @Step
    public void verifyimageURL(String sectionName, String imgURL, String shop, int indexBlock) {
        homePage.verifyimageURL(sectionName, imgURL, shop, indexBlock);
    }

    @Step
    public void verifyBackgroundImage(String sectionName, String backgroundColor, int indexBlock) {
        homePage.verifyBackgroundImage(sectionName, backgroundColor, indexBlock);
    }

    @Step
    public void verifyHeadline(String sectionName, String headline, int indexBlock) {
        homePage.verifyHeadline(sectionName, headline, indexBlock);
    }

    @Step
    public void verifyText(String sectionName, String text, int indexBlock) {
        homePage.verifyText(sectionName, text, indexBlock);
    }

    @Step
    public void verifyTextAlignment(String sectionName, String textAlignment, int indexBlock) {
        homePage.verifyTextAlignment(sectionName, textAlignment, indexBlock);
    }

    @Step
    public void verifyButton(String sectionName, String buttonLabel, String buttonLink, int indexBlock, String shop) {
        if(!buttonLink.isEmpty())
            homePage.verifyButton(sectionName, buttonLabel, buttonLink, indexBlock, shop);
    }

    public void verifyShowSectionSF(String sectionName, boolean isShow) {
        homePage.verifyShowSectionSF(sectionName, isShow);
    }

    @Step
    public void verifyProductPrice(String price) {
        String[] priceList = price.split(">");
        homePage.verifyPrice(priceList[0]);
        if(priceList.length > 1){
            homePage.verifyComparePrice(priceList[1]);
        }
    }
    @Step
    public void verifyFixedHeader(Boolean fixedHeader) {
        homePage.verifyFixedHeader(fixedHeader);
    }

    //v2
    String shopDomain = LoadObject.getProperty("shop");
    @Step
    public void verifyTextAlignmentRichTextV2(String textAlignment) {
        homePage.verifyTextAlignmentRichTextV2(textAlignment);
    }
    @Step
    public void verifyDescriptionRichTextV2(String text) {
        homePage.verifyDescriptionRichTextV2(text);
    }
    @Step
    public void verifyButton(String buttonLabel, String link) {
        if(!link.isEmpty()){
            homePage.verifyButton(buttonLabel,link, shopDomain);
        }
    }
    @Step
    public void verifyButtonTypeRichTextV2(boolean buttonType) {
        homePage.verifyButtonTypeRichTextV2(buttonType);
    }
    @Step
    public void verifyHeadingRichTextV2(String heading) {
        homePage.verifyHeadingRichTextV2(heading);
    }
}
