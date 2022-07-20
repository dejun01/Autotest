package opencommerce.online_store.themes;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.*;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import com.opencommerce.shopbase.storefront.steps.shop.FooterSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;

import static common.utilities.LoadObject.convertStatus;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePageDef {
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    NewsletterSteps newsletterSteps;
    @Steps
    FooterSteps footerSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    OnePageCheckoutSteps checkoutSteps;
    @Steps
    ImageWithTextSteps imageWithTextSteps;
    @Steps
    CustomerTestimonialSteps customerTestimonialSteps;
    @Steps
    VideoSteps videoSteps;
    @Steps
    MegaMenuSteps megaMenuSteps;
    @Steps
    LogoSectionSteps logoSectionSteps;
    @Steps
    FeaturedPromotionSteps featuredPromotionSteps;
    @Steps
    SlideshowSteps slideshowSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    BannerSteps bannerSteps;
    @Steps
    FeatureProductSteps featureProductSteps;

    String shop = LoadObject.getProperty("shop");
    String theme = LoadObject.getProperty("theme");

    @And("^verify header settings as \"([^\"]*)\"$")
    public void verifyHeaderSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String fixedHeader = SessionData.getDataTbVal(dataTable, row, "Fixed (sticky) header");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean enableSearch = convertStatus(SessionData.getDataTbVal(dataTable, row, "Enable search"));
            boolean showCart = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show cart"));
            String cartIcon = SessionData.getDataTbVal(dataTable, row, "Cart icon");
            boolean showAnnouncementBar = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show announcement bar"));
            String announcementMessage = SessionData.getDataTbVal(dataTable, row, "Announcement message");
            boolean showTopBar = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show top bar"));
            boolean showSocialMediaHeaderIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media header icons"));
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String topBarMenu = SessionData.getDataTbVal(dataTable, row, "Top bar menu");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            homePageSteps.refreshpage();
            homePageSteps.verifyEnableSearch(enableSearch);
            homePageSteps.verifyShowCart(showCart, cartIcon);
            homePageSteps.verifyShowAnnouncementBar(showAnnouncementBar, announcementMessage);
            if (theme.equals("inside")) {
                homePageSteps.verifyTopBarMenu(showTopBar, topBarMenu);
                homePageSteps.verifyLayoutHeader(layout);
                homePageSteps.verifyMainMenuInside(mainMenu, layout);
            } else {
                homePageSteps.verifyTopBar(showTopBar, showSocialMediaHeaderIcons, phoneNumber, topBarMenu);
                homePageSteps.verifyMainMenu(mainMenu);
            }
            homePageSteps.verifyLogo(desktopLogo);
            homePageSteps.verifySizeLogo(size);
        }
    }

    @And("^verify slideshow settings as \"([^\"]*)\"$")
    public void verifySlideshowSettingsAs(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        int number = SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).size();

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String textAnimation = SessionData.getDataTbVal(dataTable, row, "Text animation");
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            int slideshowIndex = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Slideshow index"));
            String backgroundImage = SessionData.getDataTbVal(dataTable, row, "Background image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String preheading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String firstButtonLabel = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstButtonLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            String highlightFirstButtonLabel = SessionData.getDataTbVal(dataTable, row, "Highlight first button label");
            String secondButtonLabel = SessionData.getDataTbVal(dataTable, row, "Second button label");
            String secondButtonLink = SessionData.getDataTbVal(dataTable, row, "Second button link");
            String highlightSecondButtonLabel = SessionData.getDataTbVal(dataTable, row, "Highlight second button label");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            Boolean isFullWidth = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width"));
            String color = SessionData.getDataTbVal(dataTable, row, "Background color");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String primaryBtnLab = SessionData.getDataTbVal(dataTable, row, "Primary button label");
            String secondaryBtnLab = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            Boolean isParallaxScrolling = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling"));
            //           homePageSteps.refreshpage();
            homePageSteps.showSlideShow(slideshowIndex);
            homePageSteps.verifyBackgroundImage(backgroundImage, slideshowIndex);
            homePageSteps.verifyAltText(altText, slideshowIndex);
            homePageSteps.verifyParallaxScrolling(isParallaxScrolling);

            switch (theme) {
                case "inside":
                    homePageSteps.verifyLayoutSlideshow(layout);
                    homePageSteps.verifyRatioSlideshow(ratio);
                    homePageSteps.verifyFullWidthSlideshow(isFullWidth);
//                    homePageSteps.verifyColorBgrSlideshow(layout, color);
                    homePageSteps.verifyHeadingOnSlideshow(heading);
//                    homePageSteps.verifyTextOnSlideshow(text);
                    homePageSteps.verifyPrimarybtnOnSlideshow(primaryBtnLab);
                    homePageSteps.verifySecondaryOnSlideshow(secondaryBtnLab);
                    homePageSteps.verifyTextAlignmentSlideshow(textAlignment);
//                    homePageSteps.verifyContentOnSlide("", heading, text, primaryBtnLab, secondaryBtnLab, slideshowIndex);
                    break;
                default:
                    homePageSteps.verifyContentOnSlide(preheading, heading, subheading, firstButtonLabel, secondButtonLabel, slideshowIndex);
            }


//            homePageSteps.verifyFirstButton(firstButtonLabel, firstButtonLink, highlightFirstButtonLabel);
//            homePageSteps.verifySecondButton(secondButtonLabel, secondButtonLink, highlightSecondButtonLabel);
//            homePageSteps.verifyTextPosition(textPosition);
//            homePageSteps.verifyTextAlignment(textAlignment);
//            homePageSteps.verifyImageLink(imageLink);

        }
    }


    @Then("^verify Rich Text settings as \"([^\"]*)\"$")
    public void verify_Rich_Text_settings_as(String dataKey, List<List<String>> dataTable) {
        String dataTableKey = "change header settings";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String linkLabel = SessionData.getDataTbVal(dataTable, row, "Link label");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String headingPosition = SessionData.getDataTbVal(dataTable, row, "Heading position");

            //           homePageSteps.refreshpage();
            homePageSteps.verifyHeadingRichText(heading);
            if (!text.isEmpty()) {
                homePageSteps.verifyTextAlignmentRichText(text, textAlignment);
            }
            homePageSteps.verifyLinkRichText(linkLabel, link);
            if (theme.equalsIgnoreCase("inside")) {
                homePageSteps.verifyHeadingPosition(headingPosition);
            }
            if (!linkLabel.isEmpty()) {
                homePageSteps.clickOnLinkLabel();
//                homePageSteps.verifyLinkToPage();
            }
        }
    }

    @And("^verify featured collection on store front \"([^\"]*)\"$")
    public void verifyFeaturedCollectionOnStoreFront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String collectionLayout = SessionData.getDataTbVal(dataTable, row, "Collection layout");
            boolean showViewMoreButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show View more button")); //Display as text link
            String viewMoreLable = SessionData.getDataTbVal(dataTable, row, "View more label");// button label

            homePageSteps.refreshpage();
            homePageSteps.verifyHeadingFeaturedCollection(heading);
            boolean isShow = !collection.isEmpty();
            homePageSteps.verifyCollectionShown(collection, isShow);

            if (!theme.equalsIgnoreCase("Bassy")) {
                homePageSteps.verifyLayoutShown(collectionLayout);
                homePageSteps.verifyBtnViewMore(showViewMoreButton, viewMoreLable);

                List<String> listOfProductInCollection = collectionListSteps.getListProductInCollectionByCollectionName(collection);
                List<String> listOfProductShowInFeatureCollection = homePageSteps.getProductShownInFeatureCollection();
                assertThat(listOfProductInCollection).containsAll(listOfProductShowInFeatureCollection);

                if (showViewMoreButton) {
                    homePageSteps.clickShowMoreFeaturedCollection();
                    homePageSteps.verifyCollectionName(collection);
                    String url = homePageSteps.getCurrentLink();
                    assertThat(url).isEqualTo("https://" + shop + "/collections/" + collection.trim().toLowerCase().replace(" ", "-"));
                    homePageSteps.backToHomePage();
                }
            }
        }
    }

    @And("^verify collection list on store front \"([^\"]*)\"$")
    public void verifyCollectionListOnStoreFront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String imageStyles = SessionData.getDataTbVal(dataTable, row, "Image styles");
            String titlePosition = SessionData.getDataTbVal(dataTable, row, "Title position");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title alignment");
            Boolean titleBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Title background"));
            String btnLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            Boolean isDisplayAsTextLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as text link"));

            homePageSteps.verifyHeadingCollectionList(heading);
            homePageSteps.verifyCollectionListShown(collection);

            switch (theme) {
                case "inside":
                    homePageSteps.verifyLayoutCollectionList(layout);
                    if (layout.equalsIgnoreCase("Default")) {
                        homePageSteps.verifyImageStyleCollectionList(imageStyles);
                        homePageSteps.verifyTitlePositionCollectionList(titlePosition);
                    } else {
                        homePageSteps.verifyTitleBgCollectionList(titleBackground);
                    }
                    homePageSteps.verifyTitleAlignmentCollectionList(titleAlignment);
                    homePageSteps.verifyButtonCollectionList(btnLabel, isDisplayAsTextLink);
                    homePageSteps.clickOnBtnLabel(btnLabel, isDisplayAsTextLink);
                    homePageSteps.verrifyLinkToColectionList(btnLabel, isDisplayAsTextLink);
                    break;
                case "roller":
                    homePageSteps.clickOnBtnLabel(btnLabel, isDisplayAsTextLink);
                    homePageSteps.verrifyLinkToColectionList(btnLabel, isDisplayAsTextLink);
                    break;
                default:
            }
        }
    }

    @When("^verify Feature product settings as \"([^\"]*)\"$")
    public void verify_Feature_product_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            boolean showArrowsOnProductGallery = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show arrows on product gallery"));
            boolean enableProductGallerySlideshow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery slideshow"));
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            boolean showVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean showPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean showProductContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show product content"));
            String optionsStyle = SessionData.getDataTbVal(dataTable, row, "Options style");
            boolean enableVariantGroupSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable variant group swatches"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantityBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box"));
            boolean showQuantityBoxInSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box in the same line"));
            String textFor0Sproduct = SessionData.getDataTbVal(dataTable, row, "Text for 0$ product");
            String tabPosition = SessionData.getDataTbVal(dataTable, row, "Tab position");
            Boolean isShowFullProDes = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show full product description"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean isQuantityAndAddToCartBtn = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box and Add to cart button"));

            homePageSteps.refreshpage();
            if (!productName.isEmpty()) {
                homePageSteps.verifyProductNameFeatureProduct(productName);
                switch (theme) {
                    case "inside":
                        homePageSteps.verifySaleType(showPriceSavings, saleType);
                        homePageSteps.verifytabPosition(isShowFullProDes, tabPosition); // Description position
                        homePageSteps.verifyShowFullProductDescription(isShowFullProDes);
                        break;
                    default:
                        homePageSteps.verifyImagePositionFeatureProduct(imagePosition); // image alignment
                        homePageSteps.verifyShowPriceSavings(showPriceSavings);
                        homePageSteps.verifyOptionsStyle(optionsStyle); //variant style or option style
                        homePageSteps.verifyShowArrowsOnProductGalleryFeatureProduct(showArrowsOnProductGallery);
                        homePageSteps.isShowQuantityBox(showQuantityBox, showQuantityBoxInSameLine, isQuantityAndAddToCartBtn);
                        homePageSteps.verifySwatches(optionsStyle, enableVariantGroupSwatches, enableColorSwatches);
                        homePageSteps.verifyEnableProductGallerySlideshowFeatureProduct(enableProductGallerySlideshow);
                        homePageSteps.verifyGalleryTransitionFeatureProduct(galleryTransition);
                        homePageSteps.verifyShowVendor(showVendor);
                        homePageSteps.verifyShowProductContent(showProductContent);
//                        homePageSteps.verifyShowsalebanners(showSaleBanners);
                        homePageSteps.verifyTextFor0Sproduct(textFor0Sproduct);
                        homePageSteps.verifytabPosition(showProductContent, tabPosition);
                }
            } else homePageSteps.verifyExampleProductDisplay();
        }
    }

    @Then("verify Featured Product section on storefront {string}")
    public void verifyFeaturedProductOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            boolean isShowPriceSaving = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowPriceSaving"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            String variantStyle = SessionData.getDataTbVal(dataTable, row, "Variant style");
            boolean hideSelector = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Hide selector when option has one value"));
            boolean onlyShowAvailableCombination = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Only show available combination"));
            boolean variantGroupSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Variant group swatches"));
            boolean colorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, " Color swatches"));
            boolean showArrowsOnProductGallery = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show arrows on product gallery"));
            boolean enableProductGallerySlideshow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery slideshow"));
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            boolean showVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean showPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean showProductContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show product content"));
            String tabPosition = SessionData.getDataTbVal(dataTable, row, "Tab position");
            boolean isShowQuantityBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity selector"));
            boolean isShowQuantityBoxAndATCbtn = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add to card button"));
            boolean isShowQuantityAndCartSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity & Add to card is same line"));

            homePageSteps.verifyProductNameFeatureProduct(productName);
            if (theme.equalsIgnoreCase("RollerV3")) {
                homePageSteps.verifyImagePositionFeatureProduct(imagePosition); // image alignment
                homePageSteps.verifyShowPriceSavings(showPriceSavings);
                homePageSteps.verifyShowArrowsOnProductGalleryFeatureProduct(showArrowsOnProductGallery);
                homePageSteps.verifyEnableProductGallerySlideshowFeatureProduct(enableProductGallerySlideshow);
                homePageSteps.verifyGalleryTransitionFeatureProduct(galleryTransition);
                homePageSteps.verifyShowVendor(showVendor);
                homePageSteps.verifyShowProductContent(showProductContent);
                homePageSteps.verifytabPosition(showProductContent, tabPosition);
            } else {
                homePageSteps.verifyProductPrice(price);
                homePageSteps.verifySaleType(isShowPriceSaving, saleType);
                homePageSteps.verifyOptionsStyle(variantStyle);
                homePageSteps.verifySwatches(variantStyle, variantGroupSwatches, colorSwatches);
                productSteps.verifyShowQuantityBox(isShowQuantityBox, isShowQuantityAndCartSameLine, isShowQuantityBoxAndATCbtn);
            }
        }
    }

    public String emailSubscribed;

    @Then("^verify Newsletter settings as \"([^\"]*)\"$")
    public void verify_Newsletter_settings_as(String dataKey, List<List<String>> dataTable) {
        homePageSteps.refreshpage();
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String currentRow = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRow = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String message = SessionData.getDataTbVal(dataTable, row, "Messages");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            boolean randomEmail = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "randomEmail"));
            String img = SessionData.getDataTbVal(dataTable, row, "Background image");

            if (index == 1) {
                homePageSteps.verifyHeadingNewsletter(heading);
                homePageSteps.verifySubheadingNewsletter(subheading);
                if (theme.equalsIgnoreCase("inside")) {
                    homePageSteps.verifyTextAlignmentNewsletter(textAlignment);
                    homePageSteps.verifyShowFullWidthNewsletter(isFull);
                    homePageSteps.verifyBgImageNewsletter(img);
                }
            }

            if (randomEmail) {
                emailSubscribed = homePageSteps.createNewEmail();
            } else {
                emailSubscribed = email;
            }

            homePageSteps.signupEmail(emailSubscribed, message);

            if (currentRow.equals(nextRow)) {
                index++;
            }
        }
    }

    @Then("^verify email subscribed on dashboard \"([^\"]*)\"$")
    public void verifyEmailSubscribedOnDB(String dataKey, List<List<String>> dataTable) {

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String email = SessionData.getDataTbVal(dataTable, row, "Email");

            if (email.isEmpty()) {
                email = emailSubscribed;
            }

            homePageSteps.searchEmail(email);
            homePageSteps.verfiNumberSubscribed(email, 1);
        }
    }

    @And("^verify logo list on store front \"([^\"]*)\"$")
    public void verifyLogoListOnStoreFront(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        int index = 1;
        homePageSteps.refreshpage();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String alt = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            int numberLogo = SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).size();

            if (!rowKey.equals(tmp)) {
                homePageSteps.verifyHeadingLogoList(heading);
            }
            homePageSteps.verifyNumberLogoShown(numberLogo);
            homePageSteps.verifyImgLogoList(image, index);
            homePageSteps.verifyAltLogo(alt, index);
            homePageSteps.verifyLinkTextLogo(link, index);
            index++;
            tmp = nextRowKey;

        }

    }

    @Then("^verify Image with text settings as \"([^\"]*)\"$")
    public void verify_ImageWithText_settings_as(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String layout = SessionData.getDataTbVal(dataTable, row, "Choose layout");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String imagesPerRow = SessionData.getDataTbVal(dataTable, row, "Images per row");
            String promotionsAnimation = SessionData.getDataTbVal(dataTable, row, "Promotions animation");

            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String yourHeadline = SessionData.getDataTbVal(dataTable, row, "Your headline");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            Boolean isDisplayAsaButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as a button"));
            if (!rowKey.equals(tmp)) {
                homePageSteps.verifyLayoutImageWithText(layout, textAlignment, imagesPerRow, promotionsAnimation);
            }
            homePageSteps.verifyImageWithText(image, index);
            homePageSteps.verifyAltTextImageWithText(altText, index);
            homePageSteps.verifyYourHeadlineImageWithText(yourHeadline, index);
            homePageSteps.verifyTextImageWithText(text, index);
            homePageSteps.verifyButtonLabel(buttonLabel, index);
            homePageSteps.verifyDisplayAsaButton(isDisplayAsaButton, index);
            index++;
            tmp = nextRowKey;

        }
    }

    @And("^verify footer on store front \"([^\"]*)\"$")
    public void verifyFooterOnStoreFront(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String copyrightText = SessionData.getDataTbVal(dataTable, row, "Copyright text");
            boolean showPaymentMethodIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean showSocialMediaFooterIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media footer icons"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            boolean fullWidth = convertStatus(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop type");
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            String footerMenu = SessionData.getDataTbVal(dataTable, row, "Footer menu");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String hideContentHeading = SessionData.getDataTbVal(dataTable, row, "Hide content heading");
            homePageSteps.refreshpage();
            if (!rowKey.equals(tmp)) {
                footerSteps.verifyCopyrightText(copyrightText);
                footerSteps.verifyShowPaymentMethod(showPaymentMethodIcons);
//                footerSteps.verifyShowSocialMedia(showSocialMediaFooterIcons);
                if (theme.equals("inside")) {
                    footerSteps.verifyFullWidth(fullWidth);
                    if (layout.equals("Minimal")) {
                        footerSteps.verifyText(text);
                        footerSteps.verifyContentAlignment(contentAlignment);
                        footerSteps.verifyFooterMenu(footerMenu);
                        footerSteps.verifyLogo(desktopLogo);
                        footerSteps.verifySizeLogo(size);
                    }
                }
            }
            if (!content.equals("")) {
                if (shopType.equalsIgnoreCase("PrintBase")) {
                    footerSteps.verifyContentPbase(type, content, index);
                    if (theme.equalsIgnoreCase("inside")) {
                        if (!heading.isEmpty()) {
                            footerSteps.verifyHeadingPbase(type, heading, Boolean.parseBoolean(hideContentHeading));
                        }
                        if (!desktopLogo.isEmpty()) {
                            footerSteps.verifyLogoPbase(desktopLogo);
                        }
                        if (!size.isEmpty()) {
                            footerSteps.verifyLogoSizePbase(size);
                        }
//                        footerSteps.verifyShowSocialMedia(showSocialMediaFooterIcons);
                        if (!hideContentHeading.isEmpty()) {
                            footerSteps.veifyHideContentHeading(Boolean.parseBoolean(hideContentHeading));
                        }
                    } else {
                        if (!desktopLogo.isEmpty()) {
                            footerSteps.verifyLogoPbase(desktopLogo);
                        }
                        if (!size.isEmpty()) {
                            footerSteps.verifyLogoSizePbase(size);
                        }
                    }
                } else {
                    footerSteps.verifyContent(type, content, index);
                    footerSteps.verifyLogo(desktopLogo);
                    footerSteps.verifySizeLogo(size);
                }
                index++;
            }
            tmp = nextRowKey;
        }
    }

    @And("^verify banner on storefront \"([^\"]*)\"$")
    public void verify_banner_on_storefront(String dataKey, List<List<String>> dataTable) {
        homePageSteps.refreshpage();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String imgBg = SessionData.getDataTbVal(dataTable, row, "Background image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String imgLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String firstBtnLbl = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstBtnLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            String highlightBtn1 = SessionData.getDataTbVal(dataTable, row, "Highlight first button label");
            String secondBtnLbl = SessionData.getDataTbVal(dataTable, row, "Second button label");
            String secondBtnLink = SessionData.getDataTbVal(dataTable, row, "Second button link");
            String highlightBtn2 = SessionData.getDataTbVal(dataTable, row, "Highlight second button label");
            String headLine = SessionData.getDataTbVal(dataTable, row, "Headline");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            Boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            homePageSteps.verifyImgBakgroundBanner(imgBg);
            homePageSteps.verifyAltTextBanner(altText);
            Boolean isParallaxScrolling = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling"));
            switch (theme) {
                case "inside":
                    homePageSteps.verifyHeadlineBanner(headLine);
                    homePageSteps.verifyDescriptionBanner(description);
                    homePageSteps.verifyShowWidthSection(isFull);
                    break;
                default:
                    homePageSteps.verifyPreHeadingBanner(preHeading);
                    homePageSteps.verifyHeadingBanner(heading);
                    homePageSteps.verifySubHeadingBanner(subHeading);
                    homePageSteps.verifyTextPosition(textPosition);
            }
            homePageSteps.verifyTextAlignmentBanner(textAlignment);
            homePageSteps.verifyParallaxScrollingBanner(isParallaxScrolling);
            homePageSteps.verifyimageLinkBanner(imgLink);
            homePageSteps.verifyFirstBtnBanner(firstBtnLbl, firstBtnLink, highlightBtn1);
            homePageSteps.verrifySecondBtnBanner(secondBtnLbl, secondBtnLink, highlightBtn2);
        }
    }

    @Then("^verify cart on storefront as \"([^\"]*)\"$")
    public void verifyCartOnStorefrontAsWith(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cartType = SessionData.getDataTbVal(dataTable, row, "Cart type");
            String isShowCheckoutButton = SessionData.getDataTbVal(dataTable, row, "Only show Checkout button in cart drawer");

            cartSteps.verifyCartType(cartType);
            if (!isShowCheckoutButton.isEmpty()) {
                cartSteps.verifyShowCheckoutBtnInMIniCart(Boolean.parseBoolean(isShowCheckoutButton), cartType);
            }
        }
    }

    @Then("verify currency setting on storefront with enable is {string} and format is {string} and supported is {string} and currencies are {string} and total are {string}")
    public void verifyCurrencySettingOnStorefront(String isEnable, String format, String
            supportedCurrencies, String currencies, String total) {
        homePageSteps.clickLocateCurrencyButton();
        homePageSteps.verifyEnableCurrency(Boolean.parseBoolean(isEnable), supportedCurrencies, currencies, total);
        homePageSteps.verifyFormatCurrency(format);
    }

    @Then("^verify language setting on storefront with enable is \"([^\"]*)\"  and supported is \"([^\"]*)\" and languages are \"([^\"]*)\" and default language is \"([^\"]*)\"$")
    public void verifyLanguageSettingOnStorefront(String isEnable, String supportedLanguages, String
            languages, String defaultLanguage) {
        homePageSteps.clickLocateCurrencyButton();
        homePageSteps.verrifyEnableLanguage(Boolean.parseBoolean(isEnable), supportedLanguages, languages, defaultLanguage);
    }

    @And("^verify apply logo on storefront with Desktop is \"([^\"]*)\" and Checkout page is \"([^\"]*)\"$")
    public void verifyApplyLogoOnStorefront(String desktop, String mobile) {
//        homePageSteps.veriyLogoHeader(desktop);
//        homePageSteps.verifyCheckoutPage(checkoutPage);
    }


    @Then("verify content Customer Testimonial section as {string}")
    public void verifyContentCustomerTestimonialSectionAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Testimonial title");
            String animation = SessionData.getDataTbVal(dataTable, row, "Text animation");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            boolean imgBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Use image"));
            boolean darkenBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Darken background image"));
            String testimonial = SessionData.getDataTbVal(dataTable, row, "Testimonial");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer name");
            String store = SessionData.getDataTbVal(dataTable, row, "Store name");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            homePageSteps.verifyTitleTestimonial(title);
            homePageSteps.verifyAnimationTestimonial(animation);
            if (!altText.equals("")) {
                homePageSteps.verifyAltTextTestimonial(altText);
            }
            homePageSteps.verifyBackgroundTestimonial(imgBackground, darkenBackground);
            homePageSteps.verifyTestimonial(testimonial);
            homePageSteps.verifyCustomer(customer);
            homePageSteps.verifyStore(store);
            homePageSteps.verifyLinkTestimonial(link);
            homePageSteps.verifyAlignmentTestimonial(alignment);
        }
    }

    @And("verify Gallery Image settings as {string}")
    public void verifyGalleryImageSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            Boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            Boolean isSpac = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "No spacing"));
            String img = SessionData.getDataTbVal(dataTable, row, "Image");
            homePageSteps.verifyHeadlineGalleryImage(headline);
            homePageSteps.veridyContentAlignment(contentAlignment);
            homePageSteps.verifyShowFullWidthSection(isFull);
            homePageSteps.verifyShowSpacSection(isSpac);
            homePageSteps.verifyGalleryImageList(img);
            if (!img.isEmpty()) {
                homePageSteps.clickOnImage();
                homePageSteps.verifyOpenZoomImage();
                homePageSteps.clickBtnCloseImage();

            }
        }

    }

    @And("verify add to cart product as {string}")
    public void verifyAddToCartProductAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            if (!collection.isEmpty()) {
                homePageSteps.hoverToProduct(productName);
//                homePageSteps.clickBtnAddToCartOnPopup();
//                homePageSteps.verifyAddToCartProduct(productName);
            }
        }
    }

    @Then("verify image with text on sf {string}")
    public void verifyContentImageWithTextOnSF(String dataKey, List<List<String>> dataTable) {
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String promotionsAnimation = SessionData.getDataTbVal(dataTable, row, "Promotions animation");


            if (index == 1) {
                imageWithTextSteps.verifyLayout(layout);
            }
            if (theme.equalsIgnoreCase("Inside")) {
                imageWithTextSteps.verifyFullWidth("Image With Text", fullWidthSection, layout);
                if (!imageLink.isEmpty()) {
                    imageWithTextSteps.verifyImageLink("Image With Text", imageLink, index);
                    imageWithTextSteps.openUrl("/");
                }
            } else {
                imageWithTextSteps.verifyPromotionsAnimation(promotionsAnimation, index);
            }
            imageWithTextSteps.verifyTextAlignment("Image With Text", textAlignment, index);
//            imageWithTextSteps.verifyAltText("Image With Text", altText, index);

            imageWithTextSteps.verifyHeadline("Image With Text", headline, index);
            imageWithTextSteps.verifyText(layout, text, index);

            if (!buttonLabel.isEmpty()) {
//                imageWithTextSteps.verifyButtonLabel("Image With Text", buttonLabel, index);
                if (!buttonLink.isEmpty()) {
                    imageWithTextSteps.verifyButtonLink(buttonLabel, buttonLink, index);
                    imageWithTextSteps.openUrl("/");
                }
            }

            if (rowKey.equals(nextRowKey)) {
                index++;
            }
        }
    }

    @Then("verify content Customer Testimonial section inside theme as {string}")
    public void verifyContentCustomerTestimonialSectionInside(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String headline = SessionData.getDataTbVal(dataTable, row, "Section Headline");
            boolean nextBackButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Next/ Back navigation"));
            boolean indicator = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show indicator"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String testimonial = SessionData.getDataTbVal(dataTable, row, "Testimonial");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer name");
            String img = SessionData.getDataTbVal(dataTable, row, "Background imag");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            boolean darkenBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Darken background image"));
//            customerTestimonialSteps.verifyHeadlineTestimonial(headline);
            if (!altText.equals("")) {
                homePageSteps.verifyAltTextTestimonial(altText);
            }
            customerTestimonialSteps.verifyBackgroundTestimonial(darkenBackground);
            customerTestimonialSteps.verifyTestimonial(testimonial);
            customerTestimonialSteps.verifyCustomer(customer);
            customerTestimonialSteps.verifyNextBackButton(nextBackButton);
            customerTestimonialSteps.verifyShowIndicator(indicator);
        }
    }

    @When("^verify video on storefront \"([^\"]*)\"$")
    public void verifyVideoSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean autoplayVideo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Autoplay video"));
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String preheading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String firstButtonLabel = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstButtonLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            boolean highlightFirstButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight first button"));
            String secondButtonLabel = SessionData.getDataTbVal(dataTable, row, "Second button label");
            String secondButtonLink = SessionData.getDataTbVal(dataTable, row, "Second button link");
            boolean highlightSecondButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight Second button"));
            boolean displaySolidTextBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display solid text background"));
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");

            homePageSteps.refreshpage();
            if (videoLink.isEmpty()) {
                videoSteps.checkShowSectionVideoOnSF(false);
            } else {
                videoSteps.scrollToSectionVideoOnSF();
                if (autoplayVideo) {
                    videoSteps.verifyVideoLink(videoLink);
                } else {
                    videoSteps.verifyDisplaySolidTextBackground(displaySolidTextBackground);
                    videoSteps.verifyHeading(heading);
                    videoSteps.verifySubheading(subheading);
                    videoSteps.verifyImg(image);
                    videoSteps.verifyTextPosition(textPosition);
                    videoSteps.verifyTextAlignment(textAlignment);
                    if (theme.equalsIgnoreCase("Roller")) {
                        if (!firstButtonLabel.isEmpty()) {
                            videoSteps.verifyButton(firstButtonLabel, highlightFirstButton);
                            videoSteps.clickOnButton(firstButtonLabel);
                            if (firstButtonLink.isEmpty()) {
                                videoSteps.verifyVideoLink(videoLink);
                            }
                            homePageSteps.refreshpage();
                            videoSteps.scrollToSectionVideoOnSF();
                        }
                        if (!secondButtonLabel.isEmpty()) {
                            videoSteps.verifyButton(secondButtonLabel, highlightSecondButton);
                            videoSteps.clickOnButton(secondButtonLabel);
                            if (secondButtonLink.isEmpty()) {
                                videoSteps.verifyVideoLink(videoLink);
                            }
                            homePageSteps.refreshpage();
                            videoSteps.scrollToSectionVideoOnSF();
                        }
                        videoSteps.verifyPreHeading(preheading);
                    } else {
                        videoSteps.verifyFullWidthSection(fullWidthSection);
                    }
                }
            }
        }
    }

    @When("^verify mega menu on sf \"([^\"]*)\"$")
    public void verifyMegMenuOnSF(String dataKey, List<List<String>> dataTable) {
        boolean showMegaMenu = false;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean enableMegaMenu = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable mega menu"));
            String linkTitle = SessionData.getDataTbVal(dataTable, row, "Link title");
            boolean enableMenu = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable menu"));
            String menuTitle = SessionData.getDataTbVal(dataTable, row, "Menu title");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String menuList = SessionData.getDataTbVal(dataTable, row, "Menu list");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image URL");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String textAreaTopContent = SessionData.getDataTbVal(dataTable, row, "Text area Top content");
            String imageTopContent = SessionData.getDataTbVal(dataTable, row, "Image Top content");
            String imageCaptionTopContent = SessionData.getDataTbVal(dataTable, row, "Image caption Top content");
            String imageBottomContent = SessionData.getDataTbVal(dataTable, row, "Image Bottom content");
            String imageCaptionBottomContent = SessionData.getDataTbVal(dataTable, row, "Image caption Bottom content");
            String imageLinkBottomContent = SessionData.getDataTbVal(dataTable, row, "Image link Bottom content");
            String textAreaBottomContent = SessionData.getDataTbVal(dataTable, row, "Text area Bottom content");

            homePageSteps.refreshpage();
            if (theme.equalsIgnoreCase("Inside")) {
                if (enableMegaMenu && enableMenu) {
                    showMegaMenu = true;
                }
                megaMenuSteps.verifyShowMegaMenu(linkTitle, showMegaMenu);
            }
            if (showMegaMenu || theme.equalsIgnoreCase("Roller") || theme.equalsIgnoreCase("RollerV3")) {
                megaMenuSteps.hoverOnMegaMenu(linkTitle);
                if (theme.equalsIgnoreCase("Inside")) {
                    if (!menuTitle.isEmpty()) {
                        megaMenuSteps.verifyMenuTitle(linkTitle, menuTitle);
                        if (!link.isEmpty()) {
                            megaMenuSteps.clickOnMenuTitle(linkTitle, menuTitle);
                            imageWithTextSteps.verifyNavigationPage(link);
                        }
                    }

                    if (!image.isEmpty() && !imageUrl.isEmpty()) {
                        megaMenuSteps.clickOnImage(linkTitle, menuTitle);
                        imageWithTextSteps.verifyNavigationPage(imageUrl);
                    }

                    megaMenuSteps.verifyMenuList(linkTitle, menuTitle, menuList);
                    if (!image.isEmpty()) {
                        megaMenuSteps.verifyHeadline(linkTitle, menuTitle, headline);
                    }
                } else {
                    megaMenuSteps.verifyTextAreaTopContent(linkTitle, textAreaTopContent);
                    megaMenuSteps.verifyImageTopContent(linkTitle, imageTopContent);
                    megaMenuSteps.verifyImageCaptionTopContent(imageTopContent, linkTitle, imageCaptionTopContent);
                    megaMenuSteps.verifyImageBottomContent(linkTitle, imageBottomContent);
                    megaMenuSteps.verifyCaptionBottomContent(imageBottomContent, linkTitle, imageCaptionBottomContent);
                    megaMenuSteps.verifyTextAreaBottomContent(linkTitle, textAreaBottomContent);

                }
            }
        }
    }

    @Then("verify Logo settings on storefront as {string}")
    public void verifyLogoSettingsOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");

            homePageSteps.refreshpage();
            if (index == 1) {
                logoSectionSteps.verifyHeading(heading);
                logoSectionSteps.verifyTextAlignment(textAlignment);
            }
            logoSectionSteps.verifyAlytText(altText, index);
            if (!link.isEmpty()) {
                logoSectionSteps.verifyLinkImage(link, index);
            }
            if (rowKey.equals(nextRowKey)) {
                index++;
            }

        }
    }

    @Then("verify Featured Promotion on storefront as {string}")
    public void verifyFeaturedPromotionOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            if (index == 1) {
                featuredPromotionSteps.verifyFullWidthSection(fullWidthSection);
            }
            featuredPromotionSteps.verifyImage(image, index);
            featuredPromotionSteps.verifyText(text, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            }
        }
    }

    @And("verify product widgets on Homepage on SF")
    public void verifyProductWidgetsOnHomepageOnSF() {
        homePageSteps.openHomepage();
        homePageSteps.verifyBestSellersOnSF("Best sellers");
        homePageSteps.verifyRecentlyViewedFeaturedProductsOnSF("Recently viewed & featured recommendations");
        homePageSteps.verifyCartRecommendations("Cart recommendations");
    }

    @And("verify product widgets on Product page on SF")
    public void verifyProductWidgetsOnProductPageOnSF() {
        homePageSteps.verifyProductsFromTheSameCollectionsOnF("Products From The Same Collections");
        homePageSteps.verifyReviewsOnSF();
        homePageSteps.verifyWhoBoughtThisAlsoBoughtOnSF("Who bought this also bought");
        homePageSteps.verifyBestSellersOnSF("Best sellers");
        homePageSteps.verifyHandpickedProductsOnSF("Handpicked products just for you");
        homePageSteps.verifyCartRecommendations("Cart recommendations");
        homePageSteps.verifyRecentlyViewedFeaturedProductsOnSF("Recently viewed & featured recommendations");
    }

    @And("verify product widgets on Cart page on SF")
    public void verifyProductWidgetsOnCartPageOnSF() {
        homePageSteps.refreshpage();
        homePageSteps.verifyCartRecommendations("Cart recommendations");
        homePageSteps.verifyProductsFromTheSameCollectionsOnF("Products From The Same Collections");
        homePageSteps.verifyBestSellersOnSF("Best sellers");
        homePageSteps.verifyRecentlyViewedFeaturedProductsOnSF("Recently viewed & featured recommendations");
    }

    @And("verify product widgets on Collection pages on SF")
    public void verifyProductWidgetsOnCollectionPagesOnSF() {
        homePageSteps.verifyBestSellersOnSF("Best sellers");
        homePageSteps.verifyCartRecommendations("Cart recommendations");
        homePageSteps.verifyRecentlyViewedFeaturedProductsOnSF("Recently viewed & featured recommendations");
    }

    @Then("verify slideShow for bassy {string}")
    public void verifySlideshowForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String previousRowKey = SessionData.getDataTbVal(dataTable, row - 1, "KEY");
            String autoRotateSlides = SessionData.getDataTbVal(dataTable, row, "Auto-rotate slides");
            String changeSlidesEvery = SessionData.getDataTbVal(dataTable, row, "Change slides every");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, " Text alignment");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String textColor = SessionData.getDataTbVal(dataTable, row, "Text color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");

            if (!rowKey.equals(previousRowKey)) {
                slideshowSteps.verifyImage(image);
            }

//            slideshowSteps.verifyAltText(altText);
            slideshowSteps.verifyTextAlignment(textAlignment);
            slideshowSteps.verifyHeadling(heading);
            slideshowSteps.verifySubheading(subheading);
            slideshowSteps.verifyButtonLabel(buttonLabel);
            slideshowSteps.verifyButtonLink(buttonLink);
            slideshowSteps.verifyTextColor(textColor);
            slideshowSteps.verifyButtonLabelColor(buttonLabelColor);
            slideshowSteps.verifyButtonColor(buttonColor);
        }
    }

    @Then("verify introduction text for bassy {string}")
    public void verifyIntroductionTextForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");

            homePageSteps.verifyText(heading);
            homePageSteps.verifyBodyText("Introduction Text", body);
        }
    }

    @Then("verify video on store front for bassy {string}")
    public void verifyVideoForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settings = SessionData.getDataTbVal(dataTable, row, "Settings");
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");

            boolean isShow = !videoLink.isEmpty();
            videoSteps.checkShowSectionVideo(settings, isShow);

            if (isShow) {
                videoSteps.verifyText(settings);
                videoSteps.verifyVideoLink(videoLink);
            }
        }
    }

    @Then("verify header for bassy {string}")
    public void verifyHeaderForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String logo = SessionData.getDataTbVal(dataTable, row, "Logo");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String menu = SessionData.getDataTbVal(dataTable, row, "Menu");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String barColor = SessionData.getDataTbVal(dataTable, row, "Bar");
            String textColor = SessionData.getDataTbVal(dataTable, row, "Text color");
            boolean enableSearch = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable search"));
            boolean showAnnouncement = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show announcement"));
            boolean homePageOnly = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Home page only"));

            homePageSteps.verifyLogoonSF(logo);
            homePageSteps.verifyMenu(menu);
            homePageSteps.verifyShowAnnouncement(buttonLink, showAnnouncement);
            homePageSteps.verifyTextAnnounce(text, showAnnouncement, buttonLink);
            homePageSteps.verifyButtonLink(buttonLink, showAnnouncement);
            homePageSteps.verifyBarColor(barColor, showAnnouncement);
            homePageSteps.verifyTextColor(textColor, showAnnouncement, buttonLink);
            homePageSteps.verifyEnableSearch(enableSearch);
            homePageSteps.verifyHomePageOnly(homePageOnly);


        }
    }

    @Then("verify Footer for Bassy {string}")
    public void verifyFooterForBassy(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String footerMenu = SessionData.getDataTbVal(dataTable, row, "Footer menu");
            String customText = SessionData.getDataTbVal(dataTable, row, "Custom text");
            boolean showPaymentMethodIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean enableSocialProfile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable Social Profile"));
            footerSteps.verifyMenuFooter(footerMenu);
            footerSteps.verifyCustomText(customText);
            System.out.println(showPaymentMethodIcons);
            footerSteps.verifyShowPaymentMethodIcons(showPaymentMethodIcons);
            footerSteps.verifyEnableSocialProfile(enableSocialProfile);


        }

    }

    //Theme inside v2
    @And("^verify banner section on storefront \"([^\"]*)\"$")
    public void verifyBannerOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imgLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            Boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String primaryButtonLabel = SessionData.getDataTbVal(dataTable, row, "Primary button label");
            String primaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Primary button link");
            String secondaryButtonLabel = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            String secondaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");
            bannerSteps.verifyImageBanner(image);
            bannerSteps.verifyimageLinkBanner(imgLink);
            if (!altText.isEmpty()) {
                bannerSteps.verifyAltTextBanner(altText);
            }
            bannerSteps.verifyOpacity(opacity);
            bannerSteps.verifyTextPosition(textPosition);
            bannerSteps.verifyTextAlignmentBanner(textAlignment);
            bannerSteps.verifyHeadingBanner(heading);
            bannerSteps.verifyPrimaryBtnBanner(primaryButtonLabel, primaryButtonLink);
            bannerSteps.verrifySecondBtnBanner(secondaryButtonLabel, secondaryButtonLink);

            if (theme.equalsIgnoreCase("RollerV3")) {
                bannerSteps.verifyPreHeadingBanner(preHeading);
                bannerSteps.verifySubHeadingBanner(subHeading);
            } else {
                bannerSteps.verifyShowWidthSection(fullWidthSection);
                bannerSteps.verifyDescriptionBanner(text);
            }
        }
    }

    @Then("verify Image With Text section on storefront {string}")
    public void verifyImageWithTextOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String fullWidth = SessionData.getDataTbVal(dataTable, row, "Full width section");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            boolean buttonType = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Button type"));

            if (!layout.isEmpty()) {
                imageWithTextSteps.verifyLayout(layout);
            }
            if (!fullWidth.isEmpty())
                imageWithTextSteps.verifyShowWidthSection(Boolean.parseBoolean(fullWidth));
            if (!textAlignment.isEmpty())
                imageWithTextSteps.verifyTextAlignment(textAlignment);
            imageWithTextSteps.verifyImage(image, indexBlock);
            imageWithTextSteps.verifyAltText(altText, indexBlock);
            imageWithTextSteps.verifyimageLink(imageLink, indexBlock);
            if (!heading.isEmpty())
                imageWithTextSteps.verifyHeading(heading, indexBlock);
            if (!text.isEmpty())
                imageWithTextSteps.verifyDescription(text, indexBlock);
            if (!buttonLink.isEmpty()) {
                imageWithTextSteps.verifyButton(buttonLabel, buttonLink, indexBlock);
            }
            imageWithTextSteps.verifyButtonType(buttonType, indexBlock);
        }
    }

    @Then("^verify not show block in \"([^\"]*)\" section on SF \"([^\"]*)\"$")
    public void verifyNotShowBlockSF(String nameSection, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String nameBlockSF = SessionData.getDataTbVal(dataTable, row, "Name block SF");
            switch (nameSection) {
                case "Banner":
                    bannerSteps.isExistBlockBannerSF(nameSection, nameBlockSF, false);
                    break;
                case "Image With Text":
                    imageWithTextSteps.isExistBlockSF(false);
                    break;
                case "Logo":
                    logoSectionSteps.isExistBlockSF(false);
                    break;
                case "Slideshow":
                    slideshowSteps.isExistBlockSlideSF(false);
                    break;
                case "Video Slider":
                    videoSteps.isExistBlockVideoOnSF(false);
                    break;
                default:
                    videoSteps.checkShowSectionVideoOnSF(false);
            }
        }
    }

    //Logo
    @And("^verify Logo section on storefront as \"([^\"]*)\"$")
    public void verifyLogoOnSFAs(String dataKey, List<List<String>> dataTable) throws IOException {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");

            if (!heading.isEmpty())
                logoSectionSteps.verifyHeading(heading, indexBlock);
            if (!textAlignment.isEmpty())
                logoSectionSteps.verifyTextAlignment(textAlignment);
            logoSectionSteps.verifyImage(image, indexBlock);
            logoSectionSteps.verifyAltText(altText, indexBlock);
            logoSectionSteps.verifyimageLink(imageLink, indexBlock);


        }
    }

    @And("verify section Video settings on storefront as {string}")
    public void verifySectionVideoSettingsOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String rowNextKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean autoplayVideo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Autoplay video"));
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            boolean displaySolidTextBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display solid text background"));
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");

            videoSteps.scrollToSectionVideoOnSF();
            videoSteps.verifyFullWidthSection(fullWidthSection);

            if (!autoplayVideo || !displaySolidTextBackground) {
                videoSteps.verifyImg(image);
                videoSteps.verifyDisplaySolidTextBackground(displaySolidTextBackground);
                videoSteps.verifyHeading(heading);
                videoSteps.verifySubheading(subheading);
                videoSteps.verifyTextPosition(textPosition);
                videoSteps.verifyTextAlignment(textAlignment);
            }
            if (!autoplayVideo) {
                videoSteps.clickPlayVideo();
            }
            videoSteps.verifyVideoLink(videoLink);

            if (rowKey.equals(rowNextKey)) {
                videoSteps.clickButtonNextVideo();
            }
        }
    }

    @And("verify show {string} section on storefront is {string}")
    public void verifyShowSectionSF(String nameSection, String isShow) {
        homePageSteps.verifyShowSectionSF(nameSection, Boolean.parseBoolean(isShow));
    }

    @Then("verify Slideshow section on storefront {string}")
    public void verifySlideshowOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String fullWidth = SessionData.getDataTbVal(dataTable, row, "Full width on desktop");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String changeSlidesEvery = SessionData.getDataTbVal(dataTable, row, "Change slides every");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String enableParallax = SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling");

            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image URL");
            String backgroundColor = SessionData.getDataTbVal(dataTable, row, "Background color");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String firstButtonLabel = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstButtonLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            String secondButtonLabel = SessionData.getDataTbVal(dataTable, row, "Second button label");
            String secondButtonLink = SessionData.getDataTbVal(dataTable, row, "Second button link");
            String sectionName = "slideshow";

            if (!layout.isEmpty()) {
                homePageSteps.verifyLayoutSlide(layout);
            }
            if (!fullWidth.isEmpty())
                homePageSteps.verifyFullWidthSection(sectionName, Boolean.parseBoolean(fullWidth));
            if (!ratio.isEmpty())
                homePageSteps.verifyRatio(sectionName, ratio);
            if (!opacity.isEmpty())
                homePageSteps.verifyOpacity(sectionName, opacity);
            if (!enableParallax.isEmpty())
                homePageSteps.verifyParallaxScrolling(sectionName, Boolean.parseBoolean(enableParallax));
            homePageSteps.verifyImageExist(sectionName, image, indexBlock);
            homePageSteps.verifyAltText(sectionName, altText, indexBlock);
            if (!imageUrl.isEmpty())
                homePageSteps.verifyimageURL(sectionName, imageUrl, shop, indexBlock);
            if (layout.equalsIgnoreCase("Box"))
                homePageSteps.verifyBackgroundImage(sectionName, backgroundColor, indexBlock);
            if (!headline.isEmpty())
                homePageSteps.verifyHeadline(sectionName, headline, indexBlock);
            if (!text.isEmpty())
                homePageSteps.verifyText(sectionName, text, indexBlock);
            if (!textAlignment.isEmpty())
                homePageSteps.verifyTextAlignment(sectionName, textAlignment, indexBlock);
            if (!firstButtonLabel.isEmpty()) {
                homePageSteps.verifyButton(sectionName, firstButtonLabel, firstButtonLink, indexBlock, shop);
            }
            if (!secondButtonLabel.isEmpty()) {
                homePageSteps.verifyButton(sectionName, secondButtonLabel, secondButtonLink, indexBlock, shop);
            }
        }
    }

    //   theme roller v3
    @Then("verify Slideshow on storefront {string}")
    public void verifySlideshowOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String textAnimation = SessionData.getDataTbVal(dataTable, row, "Text animation");
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String parallaxScrolling = SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling");
            boolean enableParallaxScrolling = Boolean.parseBoolean(parallaxScrolling);
            String backgroundImage = SessionData.getDataTbVal(dataTable, row, "Background image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String firstBtnLbl = SessionData.getDataTbVal(dataTable, row, "First button label");
            Boolean highlightBtn1 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight first button label"));
            String secondBtnLbl = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            Boolean highlightBtn2 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight second button label"));

            slideshowSteps.verifyTextAnimation(textAnimation);
            slideshowSteps.verifyGalleryTransition(galleryTransition);
            slideshowSteps.verifyOpacity(opacity);
            if (!parallaxScrolling.isEmpty()) {
                slideshowSteps.verifyEnableParallaxScrolling(enableParallaxScrolling);
            }
            slideshowSteps.verifyBackgroundImage(backgroundImage, indexBlock);
            slideshowSteps.verifyAltText(altText, indexBlock);
            slideshowSteps.verifyPreHeading(preHeading, indexBlock);
            slideshowSteps.verifyHeading(heading, indexBlock);
            slideshowSteps.verifySubHeading(subHeading, indexBlock);
            slideshowSteps.verifyTextPosition(textPosition, indexBlock);
            slideshowSteps.verifyTextAlignment(textAlignment, indexBlock);
            slideshowSteps.verifyFirstButtonLabel(firstBtnLbl, highlightBtn1, indexBlock);
            slideshowSteps.verifySecondaryButtonLabel(secondBtnLbl, highlightBtn2, indexBlock);
        }
    }

    @And("verify Header on Storefront {string}")
    public void verifyHeaderOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            Boolean fixedHeader = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Fixed (sticky) header"));
            Boolean enableSearch = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable search"));
            Boolean showCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show cart"));
            Boolean showAnnouncementBar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show announcement bar"));
            Boolean showTopBar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show top bar"));
            Boolean showSocialMediaHeaderIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social media header icons"));
            String cartIcon = SessionData.getDataTbVal(dataTable, row, "Cart icon");
            String announcementMessage = SessionData.getDataTbVal(dataTable, row, "Announcement message");
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String topBarMenu = SessionData.getDataTbVal(dataTable, row, "Top bar menu");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            homePageSteps.verifyFixedHeader(fixedHeader);
            homePageSteps.verifyEnableSearch(enableSearch);
            homePageSteps.verifyShowCart(showCart, cartIcon);
            homePageSteps.verifyShowAnnouncementBar(showAnnouncementBar, announcementMessage);
            homePageSteps.verifyTopBar(showTopBar, showSocialMediaHeaderIcons, phoneNumber, topBarMenu);
            homePageSteps.verifyMainMenu(mainMenu);
            homePageSteps.verifyLogo(desktopLogo);
            homePageSteps.verifySizeLogo(size);
        }
    }

    @Then("verify Footer theme Roller on sf {string}")
    public void verifyFooterThemeRollerOnSf(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String copyrightText = SessionData.getDataTbVal(dataTable, row, "Copyright text");
            boolean showPaymentMethodIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean showSocialMediaFooterIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media footer icons"));
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String contentPage = SessionData.getDataTbVal(dataTable, row, "Content page");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            footerSteps.verifyCopyrightText(copyrightText);
            footerSteps.verifyShowPaymentMethod(showPaymentMethodIcons);
//            footerSteps.verifyShowSocialMedia(showSocialMediaFooterIcons);
            footerSteps.verifyMainMenu(mainMenu);
            footerSteps.verifyHeading(heading);
            footerSteps.verifyTextContent(text);
            footerSteps.verifyContentPage(contentPage);
            footerSteps.verifyLogo(desktopLogo);
            footerSteps.verifySizeLogo(size);
        }
    }


    @And("^verify Rich Text section on storefront \"([^\"]*)\"$")
    public void verifyRichTextOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String headingPosition = SessionData.getDataTbVal(dataTable, row, "Heading position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            boolean buttonType = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Button type"));
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");

            if (!heading.isEmpty())
                homePageSteps.verifyHeadingRichTextV2(heading);
            if (!textAlignment.isEmpty())
                homePageSteps.verifyTextAlignmentRichTextV2(textAlignment);
            if (!text.isEmpty())
                homePageSteps.verifyDescriptionRichTextV2(text);
            if (!buttonLink.isEmpty()) {
                homePageSteps.verifyButton(buttonLabel, buttonLink);
            }
           homePageSteps.verifyButtonTypeRichTextV2(buttonType);
        }

        }
    }


