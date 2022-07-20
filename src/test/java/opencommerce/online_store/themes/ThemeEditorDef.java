package opencommerce.online_store.themes;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.*;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings.*;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
//import sun.swing.SwingUtilities2;

import java.util.List;

import static common.utilities.LoadObject.convertStatus;


public class ThemeEditorDef {
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    HeaderSteps headerSteps;
    @Steps
    SlideshowSteps slideshowSteps;
    @Steps
    ColorsSteps colorsSteps;
    @Steps
    TypographySteps typographySteps;
    @Steps
    RichTextSteps richTextSteps;
    @Steps
    FeatureProductSteps featureProductSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    ProductGridSteps productGridSteps;
    @Steps
    NewsletterSteps newsletterSteps;
    @Steps
    FeaturedCollectionSteps featuredCollectionSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    ImageWithTextSteps imageWithTextSteps;
    @Steps
    LogoListSteps logoListSteps;
    @Steps
    FooterSteps footerSteps;
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    BannerSteps bannerSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    CurrencySteps currencySteps;
    @Steps
    LanguageSteps languageSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    CustomerTestimonialSteps customerTestimonialSteps;
    @Steps
    GalleryImageSteps galleryImageSteps;
    @Steps
    MegaMenuSteps megaMenuSteps;
    @Steps
    VideoSteps videoSteps;
    @Steps
    LogoSectionSteps logoSectionSteps;
    @Steps
    FeaturedPromotionSteps featuredPromotionSteps;


    String theme = LoadObject.getProperty("theme");

    @When("^change theme styles to \"([^\"]*)\" and set image on slideshow to default is \"([^\"]*)\"$")
    public void changeThemeStylesToAndSetImageOnSlideshowToDefaultIs(String themeStyle, String isSetImageToDefault) {
        themeEditorSteps.navigateToSettingTab();
        themeEditorSteps.clickBtnChangeThemeStyle();
        themeEditorSteps.verifyPopupShown("Select style");
        themeEditorSteps.selectStyle(themeStyle);
        themeEditorSteps.setImageOnSlideShowToDefault(Boolean.parseBoolean(isSetImageToDefault));
        themeEditorSteps.clickBtnChange();
    }

    @And("^verify color settings on Dashboard as \"([^\"]*)\"$")
    public void verifyColorSettingsOnDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String background = SessionData.getDataTbVal(dataTable, row, "Background");
            String topBarBackground = SessionData.getDataTbVal(dataTable, row, "Top bar background");
            String menuBackground = SessionData.getDataTbVal(dataTable, row, "Menu background");
            String announcementBarBackground = SessionData.getDataTbVal(dataTable, row, "Announcement bar background");
            String productDescriptionBackground = SessionData.getDataTbVal(dataTable, row, "Product description background");
            String newsletterSectionBackground = SessionData.getDataTbVal(dataTable, row, "Newsletter section background");
            String miniCartBackground = SessionData.getDataTbVal(dataTable, row, "Mini cart background");
            String footerBackground = SessionData.getDataTbVal(dataTable, row, "Footer background");
            String announcementMessageText = SessionData.getDataTbVal(dataTable, row, "Announcement message text");
            String logoText = SessionData.getDataTbVal(dataTable, row, "Logo text");
            String pageHeadings = SessionData.getDataTbVal(dataTable, row, "Page headings");
            String pageContentText = SessionData.getDataTbVal(dataTable, row, "Page content text");
            String footerHeadings = SessionData.getDataTbVal(dataTable, row, "Footer headings");
            String footerText = SessionData.getDataTbVal(dataTable, row, "Footer text");
            String saleTag = SessionData.getDataTbVal(dataTable, row, "Sale tag");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String topMenu = SessionData.getDataTbVal(dataTable, row, "Top menu");
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            String links = SessionData.getDataTbVal(dataTable, row, "Links");
            String footerLinks = SessionData.getDataTbVal(dataTable, row, "Footer link");
            String buttons = SessionData.getDataTbVal(dataTable, row, "Buttons");
            String buttonsLabel = SessionData.getDataTbVal(dataTable, row, "Buttons label");
            String bannerButtons = SessionData.getDataTbVal(dataTable, row, "Banner buttons");
            String bannerButtonsLabel = SessionData.getDataTbVal(dataTable, row, "Banner buttons label");
            String miniCartButton = SessionData.getDataTbVal(dataTable, row, "Mini cart button");
            String miniCartButtonLabel = SessionData.getDataTbVal(dataTable, row, "Mini cart button label");
            themeEditorSteps.navigateToSettingTab();
            themeEditorSteps.openSettingBlock("Colors");
            colorsSteps.verifyColorSetting("Background", background);
            colorsSteps.verifyColorSetting("Top bar background", topBarBackground);
            colorsSteps.verifyColorSetting("Menu background", menuBackground);
            colorsSteps.verifyColorSetting("Announcement bar background", announcementBarBackground);
            colorsSteps.verifyColorSetting("Product description background", productDescriptionBackground);
            colorsSteps.verifyColorSetting("Newsletter section background", newsletterSectionBackground);
            colorsSteps.verifyColorSetting("Mini cart background", miniCartBackground);
            colorsSteps.verifyColorSetting("Footer background", footerBackground);
            colorsSteps.verifyColorSetting("Announcement message text", announcementMessageText);
            colorsSteps.verifyColorSetting("Logo text", logoText);
            colorsSteps.verifyColorSetting("Page headings", pageHeadings);
            colorsSteps.verifyColorSetting("Page content text", pageContentText);
            colorsSteps.verifyColorSetting("Footer headings", footerHeadings);
            colorsSteps.verifyColorSetting("Footer text", footerText);
            colorsSteps.verifyColorSetting("Sale tag", saleTag);
            colorsSteps.verifyColorSetting("Price", price);
            colorsSteps.verifyColorSetting("Compare at price", compareAtPrice);
            colorsSteps.verifyColorSetting("Top menu", topMenu);
            colorsSteps.verifyColorSetting("Main menu", mainMenu);
            colorsSteps.verifyColorSetting("Links", links);
            colorsSteps.verifyColorSetting("Footer link", footerLinks);
            colorsSteps.verifyColorSetting("Buttons", buttons);
            colorsSteps.verifyColorSetting("Buttons label", buttonsLabel);
            colorsSteps.verifyColorSetting("Banner buttons", bannerButtons);
            colorsSteps.verifyColorSetting("Banner buttons label", bannerButtonsLabel);
            colorsSteps.verifyColorSetting("Mini cart button", miniCartButton);
            colorsSteps.verifyColorSetting("Mini cart button label", miniCartButtonLabel);
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("^verify typography settings as \"([^\"]*)\"$")
    public void verifyTypographySettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String headingFront = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingStyle = SessionData.getDataTbVal(dataTable, row, "Heading style");
            String headingBaseSize = SessionData.getDataTbVal(dataTable, row, "Heading base size");
            String capitalizeHeadings = SessionData.getDataTbVal(dataTable, row, "Capitalize headings");
            String bodyTextFront = SessionData.getDataTbVal(dataTable, row, "Body text font");
            String bodyTextStyle = SessionData.getDataTbVal(dataTable, row, "Body text style");
            String bodyTextBaseSize = SessionData.getDataTbVal(dataTable, row, "Body text base size");
            String buttonTextFont = SessionData.getDataTbVal(dataTable, row, "Button text font");
            String buttonTextStyle = SessionData.getDataTbVal(dataTable, row, "Button text style");
            String capitalizeButtonText = SessionData.getDataTbVal(dataTable, row, "Capitalize buttons text");
            String fontAccentText = SessionData.getDataTbVal(dataTable, row, "Font Accent text");
            String styleAccentText = SessionData.getDataTbVal(dataTable, row, "Style Accent text");
            String capitalizeText = SessionData.getDataTbVal(dataTable, row, "Capitalize text");
            String spaceLetters = SessionData.getDataTbVal(dataTable, row, "Space letters ");
            String useAccentTextForSubheadings = SessionData.getDataTbVal(dataTable, row, "Use accent text for subheadings");
            String italicizeProduct = SessionData.getDataTbVal(dataTable, row, "Italicize product titles, collection titles, input fields, label fields, and dates.");
                themeEditorSteps.navigateToSettingTab();
                themeEditorSteps.openSettingBlock("Typography");
            typographySteps.verifyFontBySessionName("Headings", headingFront);
            typographySteps.verifyFontStyleBySessionName("Headings", headingStyle);

            typographySteps.verifyBaseSize("Headings", headingBaseSize);

            if (theme.equalsIgnoreCase("bassy")) {
                typographySteps.verifyFontBySessionName("Accent text", fontAccentText);
                typographySteps.verifyFontStyleBySessionName("Accent text", styleAccentText);
                typographySteps.verifyCapitalize("Accent text", "Capitalize text", capitalizeText);
                typographySteps.verifyCapitalize("Accent text", "Space letters", spaceLetters);
                typographySteps.verifyCapitalize("Accent text", "Use accent text for subheadings", useAccentTextForSubheadings);
                typographySteps.verifyFontBySessionName("Body text", bodyTextFront);
                typographySteps.verifyFontStyleBySessionName("Body text", bodyTextStyle);
                typographySteps.verifyBaseSize("Body text", bodyTextBaseSize);
                typographySteps.verifyCapitalize("Body text", "Italicize product titles, collection titles, input fields, label fields, and dates.", italicizeProduct);
            } else {
                typographySteps.verifyCapitalize("Headings", "Capitalize headings", capitalizeHeadings);
                typographySteps.verifyFontBySessionName("Button text", buttonTextFont);
                typographySteps.verifyFontStyleBySessionName("Button text", buttonTextStyle);
                typographySteps.verifyCapitalize("Button text", "Capitalize buttons text", capitalizeButtonText);
                typographySteps.verifyFontBySessionName("body text", bodyTextFront);
                typographySteps.verifyFontStyleBySessionName("body text", bodyTextStyle);
                typographySteps.verifyBaseSize("body text", bodyTextBaseSize);
            }

        }
    }

    @And("^change header settings as \"([^\"]*)\"$")
    public void changeHeaderSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String fixedHeader = SessionData.getDataTbVal(dataTable, row, "Fixed (sticky) header");
            String enableSearch = SessionData.getDataTbVal(dataTable, row, "Enable search");
            String showCart = SessionData.getDataTbVal(dataTable, row, "Show cart");
            String cartIcon = SessionData.getDataTbVal(dataTable, row, "Cart icon");
            String showAnnouncementBar = SessionData.getDataTbVal(dataTable, row, "Show announcement bar");
            String announcementMessage = SessionData.getDataTbVal(dataTable, row, "Announcement message");
            String showTopBar = SessionData.getDataTbVal(dataTable, row, "Show top bar");
            String showSocialMediaHeaderIcons = SessionData.getDataTbVal(dataTable, row, "Show social media header icons");
            String phoneNumber = SessionData.getDataTbVal(dataTable, row, "Phone number");
            String topBarMenu = SessionData.getDataTbVal(dataTable, row, "Top bar menu");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile logo");
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            themeEditorSteps.choosePreview("Homepage");
            themeEditorSteps.navigateToSectionsTab();
            themeEditorSteps.openSessionBlock("Header");
            headerSteps.checkCheckboxSetting("Fixed ('sticky') header", fixedHeader);
            headerSteps.checkCheckboxSetting("Enable search", enableSearch);
            headerSteps.checkCheckboxSetting("Show cart", showCart);
            headerSteps.selectCartIcon(cartIcon);
            headerSteps.settingShowAnnouncementBar(showAnnouncementBar, announcementMessage);
            headerSteps.settingTopBar(showTopBar, showSocialMediaHeaderIcons, phoneNumber, topBarMenu);
            headerSteps.settingDesktopLogo(desktopLogo);
            headerSteps.settingSize(size);
            headerSteps.settingMobileLogo(mobileLogo);
            headerSteps.selectMainMenu(mainMenu);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("^change slideshow settings as \"([^\"]*)\"$")
    public void changeSlideshowHeadingAs(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
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
            homePageSteps.refreshpage();
            if (!rowKey.equals(tmp)) {
                themeEditorSteps.navigateToSectionsTab();
                themeEditorSteps.openSessionBlock("Slideshow");
                slideshowSteps.cleanSlideshow();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            slideshowSteps.uploadBackgroundImage(backgroundImage);
            slideshowSteps.enterAltText(altText);
            slideshowSteps.enterHeading(heading);
            slideshowSteps.checkEnableParallaxScrolling(isParallaxScrolling);
            switch (theme) {
                case "inside":
                    slideshowSteps.chooseLayout(layout);
                    slideshowSteps.chooseRatio(ratio);
                    slideshowSteps.checkUncheckFullWidthOnDesktop(isFullWidth);
                    slideshowSteps.enterBackgroundColor(layout, color);
//                    slideshowSteps.enterText(text);
                    slideshowSteps.chooseAlignment(textAlignment);
                    slideshowSteps.enterPrimaryBtnLab(primaryBtnLab);
                    slideshowSteps.enterSecondaryBtnLab(secondaryBtnLab);
                    break;
                default:
                    slideshowSteps.selectTextAnimation(textAnimation);
                    slideshowSteps.selectGalleryTransition(galleryTransition);
                    slideshowSteps.enterPreheading(preheading);
                    slideshowSteps.enterSubheading(subheading);
                    slideshowSteps.selectTextPosition(textPosition);
                    slideshowSteps.selectTextAlignment(textAlignment);
                    slideshowSteps.selectImageLink(imageLink);
                    slideshowSteps.enterFirstButtonLabel(firstButtonLabel);
                    slideshowSteps.enterFirstButtonLink(firstButtonLink);
                    slideshowSteps.highlightFirstButtonlabel(highlightFirstButtonLabel);
                    slideshowSteps.enterSecondButtonLabel(secondButtonLabel);
                    slideshowSteps.enterSecondButtonLink(secondButtonLink);
                    slideshowSteps.highlightSecondButtonlabel(highlightSecondButtonLabel);
            }
            slideshowSteps.closeBlockSlideshow(slideshowIndex);

            tmp = rowKey;
            if (!rowKey.equals(nextRowKey)) {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
            }

        }
    }

    @When("^turn on sections as \"([^\"]*)\"$")
    public void turnOnSectionsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String sections = SessionData.getDataTbVal(dataTable, row, "Sections");
            themeEditorSteps.turnOnSections(sections);
            themeEditorSteps.saveSetting();
        }
    }

    @When("^change Rich text settings as \"([^\"]*)\"$")
    public void change_Rich_text_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String linkLabel = SessionData.getDataTbVal(dataTable, row, "Link label");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String btnLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String btn = SessionData.getDataTbVal(dataTable, row, "Button link");
            String headingPosition = SessionData.getDataTbVal(dataTable, row, "Heading position");
            themeEditorSteps.clickSection("Rich text");
            richTextSteps.enterHeading(heading);
            richTextSteps.enterText(text);
            richTextSteps.chooseTextAlignmentRichText(textAlignment);
            switch (theme) {
                case "inside":
                    richTextSteps.enterBtnLabel(btnLabel);
//                    richTextSteps.enterLink(btn);
                    richTextSteps.chooseHeadingPosition(headingPosition);
                    break;
                default:
                    richTextSteps.enterLinkLabel(linkLabel);
                    richTextSteps.enterLink(link);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();

        }
    }

    @Then("^change featured collection setting as \"([^\"]*)\"$")
    public void changeFeaturedCollectionSettingAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String collectionLayout = SessionData.getDataTbVal(dataTable, row, "Collection layout");
            Boolean showViewMoreButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show View more button"));
            String viewMoreLabel = SessionData.getDataTbVal(dataTable, row, "View more label");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String btnLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            Boolean isDisplayAsTextLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as text link"));

            featuredCollectionSteps.enterHeading(heading);
            themeEditorSteps.chooseCollectionsOrProducts(collection);

            featuredCollectionSteps.chooseCollectionLayout(collectionLayout);
            switch (theme) {
                case "inside":
                    featuredCollectionSteps.chooseAlignment(alignment);
                    featuredCollectionSteps.enterButtonLabel(btnLabel);
                    featuredCollectionSteps.checkUncheckDisplayAsTextLink(isDisplayAsTextLink);
                    break;
                case "roller":
                    featuredCollectionSteps.settingBtnViewMore(showViewMoreButton, viewMoreLabel);
                    break;
                default:
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @Then("^change collection list setting as \"([^\"]*)\"$")
    public void changeCollectionListSettingAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String previousRowKey = SessionData.getDataTbVal(dataTable, row - 1, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String collection = SessionData.getDataTbVal(dataTable, row, "Collection");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");

            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String imageStyles = SessionData.getDataTbVal(dataTable, row, "Image styles");
            String titlePosition = SessionData.getDataTbVal(dataTable, row, "Title position");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title alignment");
            Boolean titleBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Title background"));
            String btnLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            Boolean isDisplayAsTextLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as text link"));

            if (!rowKey.equals(previousRowKey)) {
                if (theme.equalsIgnoreCase("inside")) {
                    collectionListSteps.chooseLayout(layout);
                    if (layout.equalsIgnoreCase("Default")) {
                        collectionListSteps.chooseImageStyle(imageStyles);
                        collectionListSteps.chooseTitlePosition(titlePosition);
                    } else {
                        collectionListSteps.checkUncheckTitleBg(titleBackground);
                    }
                    collectionListSteps.chooseTitleAlignment(titleAlignment);
                    collectionListSteps.enterButtonLabel(btnLabel);
                    collectionListSteps.checkUncheckDisplayBtnAsTextlink(isDisplayAsTextLink);
                }
                featuredCollectionSteps.enterHeading(heading);
                themeEditorSteps.removeAllContent();
            }
            if (theme.equalsIgnoreCase("Bassy")) {
                themeEditorSteps.clickAddSection();
            } else {
                themeEditorSteps.clickAddContent();
            }
            themeEditorSteps.openBlockContent();
            collectionListSteps.chooseCollectionsList(collection);
            themeEditorSteps.uploadImageWithFile(image);
            collectionListSteps.inputAltImgLogo(altText);

            if (!rowKey.equals(nextRowKey)) {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
            }
        }
    }


    @Then("^change logo list setting as \"([^\"]*)\"$")
    public void changeLogoListSettingAs(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String alt = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");

            if (!rowKey.equals(tmp)) {
                themeEditorSteps.clickSection("Logo");
                themeEditorSteps.removeAllContent();
            }
            logoListSteps.enterHeading(heading);

            themeEditorSteps.clickAddContent();
            logoListSteps.openBlockContent();
            logoListSteps.uploadImage(image);
            logoListSteps.enterAltText(alt);
            logoListSteps.inputLinkLogo(link);
            logoListSteps.closeBlockContent();
            tmp = rowKey;
            if (!rowKey.equals(nextRowKey)) {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
            }
        }
    }


    @When("^change Feature product settings as \"([^\"]*)\"$")
    public void change_Feature_product_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String productName = SessionData.getDataTbVal(dataTable, row, "Product name");
            Boolean showArrowsOnProductGallery = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show arrows on product gallery"));
            Boolean enableProductGallerySlideshow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery slideshow"));
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            Boolean showVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            Boolean showPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            Boolean showProductContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show product content"));
            Boolean showShowFullProductDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show full product description"));
            String productImageAlignment = SessionData.getDataTbVal(dataTable, row, "Product image alignment");
            homePageSteps.refreshpage();
            themeEditorSteps.clickSection("Featured Product");
            featureProductSteps.enterProduct(productName);
            switch (theme) {
                case "inside":
                    featureProductSteps.chooseProductImgAlignment(productImageAlignment);
                    featureProductSteps.selectShowFullProductDescription(showShowFullProductDescription);
                    break;
                default:
                    featureProductSteps.selectShowarrowsonproductgallery(showArrowsOnProductGallery);
                    featureProductSteps.selectEnableproductgalleryslideshow(enableProductGallerySlideshow);
                    featureProductSteps.chooseGalleryTransition(galleryTransition);
                    featureProductSteps.chooseImagePosition(imagePosition);
                    featureProductSteps.selectShowVendor(showVendor);
                    featureProductSteps.selectShowpricesavings(showPriceSavings);
                    featureProductSteps.selectShowProductContent(showProductContent);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @When("^change Product page settings as \"([^\"]*)\"$")
    public void change_Product_page_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            Boolean enableProductGalleryPopup = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery popup"));
            Boolean showBreadcrumbLinks = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show breadcrumb links"));
            Boolean showVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            Boolean showSku = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show SKU"));
            Boolean showPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            Boolean showCollections = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show collections"));
            Boolean showTypes = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show types"));
            Boolean showTags = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags"));
            Boolean showSocialMediaShareIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social media share icons"));
            Boolean showStickyButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sticky button"));

            themeEditorSteps.choosePreview("Product page");
            themeEditorSteps.clickSection("Product");
            switch (theme) {
                case "inside":
                    productSteps.selectEnableProductBreakcrumb(showBreadcrumbLinks);
                    break;
                default:
                    productSteps.selectEnableProductGalleryPopup(enableProductGalleryPopup);
                    productSteps.slectShowBreadcrumbLinks(showBreadcrumbLinks);
                    productSteps.selectShowPriceSavings(showPriceSavings);
            }
            productSteps.selectShowVendor(showVendor);
            productSteps.selectShowSKU(showSku);
            productSteps.selectShowCollections(showCollections);
            productSteps.selectShowTypes(showTypes);
            productSteps.selectShowTags(showTags);
            productSteps.selectShowSocialMediaShareIcons(showSocialMediaShareIcons);
            productSteps.selectShowStickyButton(showStickyButton);

            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @When("^change Product grid setting as \"([^\"]*)\"$")
    public void change_Product_grid_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showSaleBanners = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sale banners"));
            String optionsStyle = SessionData.getDataTbVal(dataTable, row, "Options style");
            boolean enableVariantGroupSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable variant group swatches"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantityBox = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box"));
            boolean showQuantityBoxInSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box in the same line"));
            String linkProductOptions = SessionData.getDataTbVal(dataTable, row, "Link product options");
            String textFor0Sproduct = SessionData.getDataTbVal(dataTable, row, "Text for 0$ product");
            String tabPosition = SessionData.getDataTbVal(dataTable, row, "Tab position");
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showTrustBadgeImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show trust badge image"));
            boolean isShowPriceSaving = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean isshowQuantityBoxAndATC = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show quantity box and Add to cart button"));

            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickSection("Product");
            switch (theme) {
                case "inside":
                    productGridSteps.selectDescriptionPosition(descriptionPosition);
                    productGridSteps.selectSaleType(saleType);
                    productGridSteps.selectShowPriceSaving(isShowPriceSaving);
                    productGridSteps.selectShowQuantityBoxAndATC(isshowQuantityBoxAndATC);
                    productGridSteps.selectVariantStyle(optionsStyle);
                    break;
                default:
//                    productGridSteps.selectShowsalebanners(showSaleBanners);
                    productGridSteps.chooseLinkProductOptions(linkProductOptions);
                    productGridSteps.enterTextFor0Sproduct(textFor0Sproduct);
                    productGridSteps.selectTabPosition(tabPosition);
                    productGridSteps.chooseOptionStyle(optionsStyle);
            }

            productGridSteps.settingOptionsStyleAndSwatches(optionsStyle, enableVariantGroupSwatches, enableColorSwatches);
            productGridSteps.selectShowQuantityBox(showQuantityBox, showQuantityBoxInSameLine);
            productGridSteps.showTrustBadgeImage(showTrustBadgeImage);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.clickSectionTab();
            themeEditorSteps.waitABit(5000);
        }
    }

    @When("^change Newsletter settings as \"([^\"]*)\"$")
    public void change_Newsletter_settings_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            Boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String img = SessionData.getDataTbVal(dataTable, row, "Background image");

            themeEditorSteps.clickSection("Newsletter");
            if (theme.equalsIgnoreCase("inside")) {
                newsletterSteps.chooseTextAlignment(textAlignment);
                newsletterSteps.checkUncheckShowFullWidthSection(isFull);
                newsletterSteps.uploadImage(img);
            }
            newsletterSteps.enterHeadingNewsletter(heading);
            newsletterSteps.enterSubheadingNewsletter(subheading);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("^change footer settings as \"([^\"]*)\"$")
    public void changeFooterSettingsAs(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String copyrightText = SessionData.getDataTbVal(dataTable, row, "Copyright text");
            boolean showPaymentMethodIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean showSocialMediaFooterIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media footer icons"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean isFull = convertStatus(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            String footerMenu = SessionData.getDataTbVal(dataTable, row, "Footer menu");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            switch (theme) {
                case "inside":
                    if (!rowKey.equals(tmp)) {
                        themeEditorSteps.clickSection("Footer");
                        footerSteps.chooseLayout(layout);
                        footerSteps.enterCopyrightText(copyrightText);
                        footerSteps.isCheckboxShowPaymentMethod(showPaymentMethodIcons);
                        footerSteps.isFullWidthSection(isFull);
                        if (!link.equals("")) {
                            footerSteps.openThemeSettings();
                            footerSteps.enterLink(link);
                        }
                        if (layout.equals("Rich")) {
                            themeEditorSteps.removeAllContent();
                        } else {
                            headerSteps.settingDesktopLogo(desktopLogo);
                            headerSteps.settingSize(size);
                            headerSteps.settingMobileLogo(mobileLogo);
                            richTextSteps.enterText(text);
                            footerSteps.chooseContentAlignment(contentAlignment);
                            footerSteps.chooseMenu(footerMenu);
                        }
                    }
                    if (layout.equals("Rich")) {
                        footerSteps.clickAddContent();
                        footerSteps.chooseTypeContent(type);
                        footerSteps.opentBlockContent(type);
                        footerSteps.addContent(type, content);
                        footerSteps.closeBlockContent();
                    }

                    break;
                default:
                    if (!rowKey.equals(tmp)) {
                        themeEditorSteps.clickSection("Footer");
                        footerSteps.enterCopyrightText(copyrightText);
                        footerSteps.isCheckboxShowPaymentMethod(showPaymentMethodIcons);
                        footerSteps.isCheckboxShowSocialMedia(showSocialMediaFooterIcons);
                        headerSteps.settingDesktopLogo(desktopLogo);
                        headerSteps.settingSize(size);
                        headerSteps.settingMobileLogo(mobileLogo);
                        themeEditorSteps.removeAllContent();
                    }
                    footerSteps.clickAddContent();
                    footerSteps.chooseTypeContent(type);
                    footerSteps.openLastBlockContent();
                    footerSteps.addContent(type, content);
                    footerSteps.closeBlockContent();

            }
            tmp = nextRowKey;
            if (!rowKey.equals(nextRowKey)) {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
            }
        }
        System.out.println(tmp);

    }

    @And("^change theme settings as \"([^\"]*)\"$")
    public void changeThemeSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String facebook = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String twitter = SessionData.getDataTbVal(dataTable, row, "Twitter");
            String youTube = SessionData.getDataTbVal(dataTable, row, "YouTube");
            String pinterest = SessionData.getDataTbVal(dataTable, row, "Pinterest");
            String intagram = SessionData.getDataTbVal(dataTable, row, "Intagram");
            String blog = SessionData.getDataTbVal(dataTable, row, "Blog");
            themeEditorSteps.clickSection("Footer");
            themeEditorSteps.openThemeSettings();
            themeEditorSteps.enterLinkSocialWithLabel("Facebook", facebook);
            themeEditorSteps.enterLinkSocialWithLabel("Twitter", twitter);
            themeEditorSteps.enterLinkSocialWithLabel("YouTube", youTube);
            themeEditorSteps.enterLinkSocialWithLabel("Pinterest", pinterest);
            themeEditorSteps.enterLinkSocialWithLabel("Instagram", intagram);
            themeEditorSteps.enterLinkSocialWithLabel("Blog", blog);
        }
    }

    String shop = LoadObject.getProperty("shop");

    @When("^verify remove sections$")
    public void verifyRemoveSections(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String section = SessionData.getDataTbVal(dataTable, row, "Section");
            String sectionHeading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean hasHeading = true;

            System.out.print("\n" + section);
            if (!sectionHeading.isEmpty()) {
                hasHeading = Boolean.parseBoolean(sectionHeading);
            }
            String url = "https://" + shop + "?sbase_debug=1";
            if (themeEditorSteps.iSectionExisted(section)) {
                themeEditorSteps.clickSection(section);
                String heading = "";
                if (hasHeading) {
                    heading = themeEditorSteps.getHeading();
                    themeEditorSteps.removeSection();
                    themeEditorSteps.saveSetting();
                    themeEditorSteps.openPageOnNewWindow(url);
                    homePageSteps.verifyHomepageDisplay();
                    homePageSteps.refreshpage();
                    homePageSteps.verifyTextExisted(heading);
                    homePageSteps.closeBrowser();
                } else {
                    themeEditorSteps.removeSection();
                    themeEditorSteps.saveSetting();
                }
            }
        }
    }


    @When("^change banner setting as \"([^\"]*)\"$")
    public void change_banner_setting_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String imgBg = SessionData.getDataTbVal(dataTable, row, "Background image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String imgLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String firstBtnLbl = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            Boolean highlightBtn1 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight first button label"));
            String secondBtnLbl = SessionData.getDataTbVal(dataTable, row, "Second button label");
            String secondLink = SessionData.getDataTbVal(dataTable, row, "Second button link");
            Boolean highlightBtn2 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight second button label"));
            String headLine = SessionData.getDataTbVal(dataTable, row, "Headline");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            Boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            Boolean isParallaxScrolling = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling"));
            //            homePageSteps.refreshpage();
            themeEditorSteps.clickSection("Banner");
            bannerSteps.uploadImageBachground(imgBg);
            bannerSteps.enterAltTextBanner(altText);
            bannerSteps.chooseTexAlignmentBanner(textAlignment);
            bannerSteps.enterImageLinkBanner(imgLink);
            bannerSteps.enterFirstBtnLblBanner(firstBtnLbl);
            bannerSteps.enterFirstBtnLinkBanner(firstLink);
            bannerSteps.enterSencondBtnLblBanner(secondBtnLbl);
            bannerSteps.enterSecondBtnLinkBanner(secondLink);
            slideshowSteps.checkEnableParallaxScrolling(isParallaxScrolling);

            switch (theme) {
                case "inside":
                    bannerSteps.enterHeadline(headLine);
                    bannerSteps.enterDescription(description);
                    bannerSteps.checkUncheckFullWidthSection(isFull);
                    break;
                default:
                    bannerSteps.enterPreHeadingBanner(preHeading);
                    bannerSteps.enterHeadingBanner(heading);
                    bannerSteps.enterSubHeadingBanner(subHeading);
                    bannerSteps.chooseTextPositionBanner(textPosition);
                    bannerSteps.checkUncheckHighlightBtn1Banner(highlightBtn1);
                    bannerSteps.checkUncheckHighlightBtn2Banner(highlightBtn2);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("^choose preview page \"([^\"]*)\"$")
    public void choosePreviewPage(String page) {
        themeEditorSteps.choosePreview(page);
    }

    @When("user click tab {string}")
    public void user_click_tab(String tab) {
        themeEditorSteps.clickSettingTab();

    }

    @When("user change color setting as {string}")
    public void user_change_color_setting_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String background = SessionData.getDataTbVal(dataTable, row, "Background");
            String topBarBg = SessionData.getDataTbVal(dataTable, row, "Top bar background");
            String menuBg = SessionData.getDataTbVal(dataTable, row, "Menu background");
            String announBarBg = SessionData.getDataTbVal(dataTable, row, "Announcement bar background");
            String newsletterBg = SessionData.getDataTbVal(dataTable, row, "Newsletter section background");
            String productDescBg = SessionData.getDataTbVal(dataTable, row, "Product description background");
            String miniCartBg = SessionData.getDataTbVal(dataTable, row, "Mini cart background");
            String footerBg = SessionData.getDataTbVal(dataTable, row, "Footer background");
            String announMsgText = SessionData.getDataTbVal(dataTable, row, "Announcement message text");
            String logoText = SessionData.getDataTbVal(dataTable, row, "Logo text");
            String pageHeadings = SessionData.getDataTbVal(dataTable, row, "Page headings");
            String pageContentText = SessionData.getDataTbVal(dataTable, row, "Page content text");
            String footerHeadings = SessionData.getDataTbVal(dataTable, row, "Footer headings");
            String footerText = SessionData.getDataTbVal(dataTable, row, "Footer text");
            String price = SessionData.getDataTbVal(dataTable, row, "Price");
            String compareAtPrice = SessionData.getDataTbVal(dataTable, row, "Compare at price");
            String topMenu = SessionData.getDataTbVal(dataTable, row, "Top menu");
            String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
            String links = SessionData.getDataTbVal(dataTable, row, "Links");
            String footerLink = SessionData.getDataTbVal(dataTable, row, "Footer link");
            String buttons = SessionData.getDataTbVal(dataTable, row, "Buttons");
            String buttonsLabel = SessionData.getDataTbVal(dataTable, row, "Buttons label");
            String bannerButtons = SessionData.getDataTbVal(dataTable, row, "Banner buttons");
            String bannerBtnLabel = SessionData.getDataTbVal(dataTable, row, "Banner buttons label");
            String miniCartButton = SessionData.getDataTbVal(dataTable, row, "Mini cart button");
            String miniCartBtnlabel = SessionData.getDataTbVal(dataTable, row, "Mini cart button label");
            String headerBackground = SessionData.getDataTbVal(dataTable, row, "Header background");
            String headerLinks = SessionData.getDataTbVal(dataTable, row, "Header links");
            String pageBackground = SessionData.getDataTbVal(dataTable, row, "Page background");
            String pageBodyText = SessionData.getDataTbVal(dataTable, row, "Page body text");
            String pageButtonLabel = SessionData.getDataTbVal(dataTable, row, "Page button label");
            String pageButton = SessionData.getDataTbVal(dataTable, row, "Page button");
            String drawerButton = SessionData.getDataTbVal(dataTable, row, "Drawer button");
            String drawerText = SessionData.getDataTbVal(dataTable, row, "Drawer text");
            String drawerBackground = SessionData.getDataTbVal(dataTable, row, "Drawer background");

            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickSection("Colors");

            if (theme.equalsIgnoreCase("bassy")) {
                colorsSteps.inputColor("Header", "Background", headerBackground);
                colorsSteps.inputColor("Header", "Links", headerLinks);
                colorsSteps.inputColor("Page", "Background", pageBackground);
                colorsSteps.inputColor("Page", "Headings", pageHeadings);
                colorsSteps.inputColor("Page", "Body text", pageBodyText);
                colorsSteps.inputColor("Page", "Buttons label", pageButtonLabel);
                colorsSteps.inputColor("Page", "Buttons", pageButton);
                colorsSteps.inputColor("Footer", "Text", footerText);
                colorsSteps.inputColor("Footer", "Background", footerBg);
                colorsSteps.inputColor("Drawers", "Button", drawerButton);
                colorsSteps.inputColor("Drawers", "Text", drawerText);
                colorsSteps.inputColor("Drawers", "Background", drawerBackground);


            } else {
                if (!background.isEmpty()){
                    colorsSteps.inputColor("Background", background);
                }
                if (!topBarBg.isEmpty()){
                    colorsSteps.inputColor("Top bar background", topBarBg);
                }
                if (!menuBg.isEmpty()){
                    colorsSteps.inputColor("Menu background", menuBg);
                }
                if (!announBarBg.isEmpty()){
                    colorsSteps.inputColor("Announcement bar background", announBarBg);
                }
                if (!newsletterBg.isEmpty()){
                    colorsSteps.inputColor("Newsletter section background", newsletterBg);
                }
                if (!productDescBg.isEmpty()){
                    colorsSteps.inputColor("Product description background", productDescBg);
                }
                if (!miniCartBg.isEmpty()){
                    colorsSteps.inputColor("Mini cart background", miniCartBg);
                }
                if (!footerBg.isEmpty()){
                    colorsSteps.inputColor("Footer background", footerBg);
                }
                if (!announMsgText.isEmpty()){
                    colorsSteps.inputColor("Announcement message text", announMsgText);
                }
                if (!logoText.isEmpty()){
                    colorsSteps.inputColor("Logo text", logoText);
                }
                if (!pageHeadings.isEmpty()){
                    colorsSteps.inputColor("Page headings", pageHeadings);
                }
                if (!pageContentText.isEmpty()){
                    colorsSteps.inputColor("Page content text", pageContentText);
                }
                if (!footerHeadings.isEmpty()){
                    colorsSteps.inputColor("Footer headings", footerHeadings);
                }
                if (!footerText.isEmpty()){
                    colorsSteps.inputColor("Footer text", footerText);
                }
                if (!price.isEmpty()){
                    colorsSteps.inputColor("Price", price);
                }
                if (!compareAtPrice.isEmpty()){
                    colorsSteps.inputColor("Compare at price", compareAtPrice);
                }
                if (!topMenu.isEmpty()){
                    colorsSteps.inputColor("Top menu", topMenu);
                }
                if (!mainMenu.isEmpty()){
                    colorsSteps.inputColor("Main menu", mainMenu);
                }
                if (!links.isEmpty()){
                    colorsSteps.inputColor("Links", links);
                }
                if (!footerLink.isEmpty()){
                    colorsSteps.inputColor("Footer link", footerLink);
                }
                if (!buttons.isEmpty()){
                    colorsSteps.inputColor("Buttons", buttons);
                }
                if (!buttonsLabel.isEmpty()){
                    colorsSteps.inputColor("Buttons label", buttonsLabel);
                }
                if (!bannerButtons.isEmpty()){
                    colorsSteps.inputColor("Banner buttons", bannerButtons);
                }
                if (!bannerBtnLabel.isEmpty()){
                    colorsSteps.inputColor("Banner buttons label", bannerBtnLabel);
                }
                if (!miniCartButton.isEmpty()){
                    colorsSteps.inputColor("Mini cart button", miniCartButton);
                }
                if (!miniCartBtnlabel.isEmpty()){
                    colorsSteps.inputColor("Mini cart button label", miniCartBtnlabel);
                }
            }

            themeEditorSteps.saveSetting();
        }
    }

    @When("user change typography as {string}")
    public void user_change_typography_as(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String headingsFont = SessionData.getDataTbVal(dataTable, row, "Headings font");
            Boolean isCapitalize = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Capitalize headings"));
            String bodyTextFont = SessionData.getDataTbVal(dataTable, row, "Body text font");
            String buttonTextFont = SessionData.getDataTbVal(dataTable, row, "Button text font");
            Boolean isCapitalizeBtnText = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Capitalize buttons text"));
            String fontAccentText = SessionData.getDataTbVal(dataTable, row, "Font Accent text");
            Boolean capitalizeText = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Capitalize text"));
            Boolean spaceLetters = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Space letters"));
            Boolean useAccentTextForSubheadings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Use accent text for subheadings"));


            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickSection("Typography");

            typographySteps.changeFont("Headings", headingsFont);
            if(theme.equalsIgnoreCase("Bassy")){
                typographySteps.changeFont("Accent text",fontAccentText);
                typographySteps.checkCapitalizeText(capitalizeText);
                typographySteps.checkSpaceLetters(spaceLetters);
                typographySteps.checkUseAccentTextForSubheadings(useAccentTextForSubheadings);
                typographySteps.changeFont("Body text",bodyTextFont);

            }else {
                typographySteps.checkCapitalizeHeadings(isCapitalize);
                typographySteps.changeFont("body text", bodyTextFont);
                typographySteps.changeFont("Button text", buttonTextFont);
                typographySteps.checkCapitalizeBtnText(isCapitalizeBtnText);
            }

            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @When("^change setting cart page as \"([^\"]*)\"$")
    public void userChangeCartPageInDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cartType = SessionData.getDataTbVal(dataTable, row, "Cart type");
            String isShowCheckoutButton = SessionData.getDataTbVal(dataTable, row, "Only show Checkout button in cart drawer");

            String label;
            if (theme.equalsIgnoreCase("inside")) {
                label = "Only show Checkout button in Cart Drawer";
            } else {
                label = "Only show Checkout button in Mini cart";
            }

            cartSteps.chooseCartType(cartType);
            if (!isShowCheckoutButton.isEmpty()) {
                cartSteps.chechUncheckOnlyshowCheckoutbuttoninMinicart(label, Boolean.parseBoolean(isShowCheckoutButton));
            }
            themeEditorSteps.saveSetting();
        }
    }

    @When("^user change currency setting in dashboard with enable is \"([^\"]*)\" and format is \"([^\"]*)\" and supported is \"([^\"]*)\" and currencies are \"([^\"]*)\"$")
    public void userChangeCurrencySettingInDashboard(String isEnable, String format, String supportedCurrencies, String currencies) {
        themeEditorSteps.clickSettingTab();
        themeEditorSteps.clickSection("Currency");
        currencySteps.checkUncheckEnableCurrencyConverion(Boolean.parseBoolean(isEnable));
        currencySteps.chooseFormat(format);
        currencySteps.chooseSupportCurrencies(supportedCurrencies, currencies);

        themeEditorSteps.saveSetting();
        themeEditorSteps.closeSessionSetting();
    }

    @When("^user change language setting in dashboard with enable is \"([^\"]*)\"  and supported is \"([^\"]*)\" and languages are \"([^\"]*)\" and default language is \"([^\"]*)\"$")
    public void userChangeLanguageSettingInDashboard(String isEnable, String supportedLanguages, String languages, String defaultLanguage) {
        themeEditorSteps.clickSettingTab();
        themeEditorSteps.clickSection("Language");
        languageSteps.checkUncheckEnableLanguageTranslation(Boolean.parseBoolean(isEnable));
        languageSteps.chooseSupportedLanguages(supportedLanguages, languages);
        languageSteps.enterDefaultLanguage(defaultLanguage);

        themeEditorSteps.saveSetting();
        themeEditorSteps.closeSessionSetting();
    }

    @And("user click Customize on current theme")
    public void userClickCustomizeOnCurrentTheme() {
        themeEditorSteps.clickBtnCustomize();
    }

    @And("^user verify logo in theme is \"([^\"]*)\"$")
    public void userVerifyLogoInTheme(String t) {

    }


    @And("change Customer Testimonial settings as {string}")
    public void changeCustomerTestimonialSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Testimonial title");
            String animation = SessionData.getDataTbVal(dataTable, row, "Text animation");
            String image = SessionData.getDataTbVal(dataTable, row, "Background image");
            String text = SessionData.getDataTbVal(dataTable, row, "Alt text");
            boolean useImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Use image"));
            boolean darkenBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Darken background image"));
            String testimonial = SessionData.getDataTbVal(dataTable, row, "Testimonial");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer name");
            String store = SessionData.getDataTbVal(dataTable, row, "Store name");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String typeLink = SessionData.getDataTbVal(dataTable, row, "Type link");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            themeEditorSteps.clickSection("Customer testimonial");
            customerTestimonialSteps.inputTitle(title);
            customerTestimonialSteps.selectAnimation(animation);
            customerTestimonialSteps.clickContent();
            customerTestimonialSteps.uploadImage(image);
            customerTestimonialSteps.inputText(text);
            customerTestimonialSteps.checkUseImage(useImage);
            customerTestimonialSteps.checkDarkenBackground(darkenBackground);
            customerTestimonialSteps.inputTestimonial(testimonial);
            customerTestimonialSteps.inputCustomer(customer);
            customerTestimonialSteps.inputStore(store);
            customerTestimonialSteps.selectLink(typeLink, link);
            customerTestimonialSteps.selectAlignment(alignment);
            customerTestimonialSteps.clickBtnSave();
        }
    }

    @When("^change footer settings \"([^\"]*)\"$")
    public void changeFooterSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String copyrightText = SessionData.getDataTbVal(dataTable, row, "Copyright text");
            boolean showPaymentMethodIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean showSocialMediaFooterIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social media footer icons"));
            String shopType = SessionData.getDataTbVal(dataTable, row, "Shop type");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            boolean hideContentHeading = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Hide content heading"));

            themeEditorSteps.clickSection("Footer");
            if (theme.equalsIgnoreCase("inside")) {
                footerSteps.openBlockWithLabel(type);
            } else {
                footerSteps.isCheckboxShowSocialMedia(showSocialMediaFooterIcons);
            }
            if (shopType.equalsIgnoreCase("PrintBase")) {
                footerSteps.addContentpbase(type, content);
                if (theme.equalsIgnoreCase("inside")) {
                    footerSteps.addHeading(type, heading);
                    headerSteps.settingDesktopLogo(desktopLogo);
                    headerSteps.settingSize(size);
                    footerSteps.isCheckboxShowSocialMedia(showSocialMediaFooterIcons);
                    footerSteps.isCheckHideContentHeading(type, hideContentHeading);
                } else {
                    headerSteps.settingDesktopLogo(desktopLogo);
                    headerSteps.settingSize(size);
                }
            } else {
                footerSteps.addContent(type, content);
            }
            footerSteps.enterCopyrightText(copyrightText);
            footerSteps.isCheckboxShowPaymentMethod(showPaymentMethodIcons);
            themeEditorSteps.saveSetting();
        }
    }

    @When("^change video settings as \"([^\"]*)\"$")
    public void changeVideoSettings(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean autoplayVideo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Autoplay video"));
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
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
            boolean backgroundTransparency = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable background transparency"));
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            themeEditorSteps.clickSection("Video Slider");
            if (index == 1) {
                videoSteps.checkAutoplayVideo("Autoplay video", autoplayVideo);
                if (theme.equalsIgnoreCase("Inside")) {
                    themeEditorSteps.checkFullWidthSection("Full width section");
                }
                themeEditorSteps.removeAllContent();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            videoSteps.inputVideoLink(videoLink);
            themeEditorSteps.uploadImageWithFile(image);
            logoSectionSteps.inputAltText("Alt text", altText);
            videoSteps.inputLabel("Heading", heading);
            videoSteps.checkCheckboxWithLabel("Display solid text background", displaySolidTextBackground);

            if (theme.equalsIgnoreCase("Roller")) {
                videoSteps.inputPreOrSubHeading("Preheading", preheading);
                videoSteps.inputPreOrSubHeading("Subheading", subheading);
                videoSteps.chooseTextPosition("Text position", textPosition);
                videoSteps.chooseTextAlignment("Text alignment", textAlignment);
                videoSteps.checkCheckboxWithLabel("Enable background transparency", backgroundTransparency);
                videoSteps.inputLabel("First button label", firstButtonLabel);
                videoSteps.checkCheckboxWithLabel("Highlight first button label", highlightFirstButton);
                videoSteps.inputLabel("Second button label", secondButtonLabel);
                videoSteps.checkCheckboxWithLabel("Highlight second button label", highlightSecondButton);
            } else {
                bannerSteps.checkUncheckFullWidthSection(fullWidthSection);
                videoSteps.inputSubheading(subheading);
                videoSteps.selectTextPosition(textPosition);
                videoSteps.selectTextAlignment(textAlignment);
            }
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @And("change Customer Testimonial settings inside theme as {string}")
    public void changeCustomerTestimonialThemeInside(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String headline = SessionData.getDataTbVal(dataTable, row, "Section Headline");
            boolean nextBackButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Next/ Back navigation"));
            boolean indicator = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show indicator"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String testimonial = SessionData.getDataTbVal(dataTable, row, "Testimonial");
            String customer = SessionData.getDataTbVal(dataTable, row, "Customer name");
            String image = SessionData.getDataTbVal(dataTable, row, "Background image");
            String text = SessionData.getDataTbVal(dataTable, row, "Alt text");
            boolean darkenBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Darken background image"));
            homePageSteps.refreshpage();
            themeEditorSteps.clickSection("Customer testimonial");
            customerTestimonialSteps.inputHeadline(headline);
            customerTestimonialSteps.checkCheckBoxWithLabel("Show Next/ Back navigation", nextBackButton);
            customerTestimonialSteps.checkCheckBoxWithLabel("Show indicator", indicator);
            customerTestimonialSteps.selectTextAlignment(textAlignment);
            customerTestimonialSteps.clickContent();
            customerTestimonialSteps.uploadImage(image);
            customerTestimonialSteps.inputText(text);
            customerTestimonialSteps.checkDarkenBackground(darkenBackground);
            customerTestimonialSteps.inputTestimonialInside(testimonial);
            customerTestimonialSteps.inputCustomer(customer);
            customerTestimonialSteps.clickBtnSave();
        }
    }

    @When("change Gallery Image settings theme inside as {string}")
    public void changeGalleryImageSettingsThemeInsideAs(String dataKey, List<List<String>> dataTable) {
        String tmp = "";
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            Boolean isFull = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            Boolean isSpac = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "No spacing"));
            String img = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            if (!rowKey.equals(tmp)) {
                themeEditorSteps.clickSection("Gallery Image");
                if (theme.equalsIgnoreCase("inside")) {
                    galleryImageSteps.enterHeadline(headline);
                    galleryImageSteps.chooseContentAlignment(contentAlignment);
                    galleryImageSteps.checkUncheckShowFullWidthSection(isFull);
                    galleryImageSteps.checkUncheckShowNoSpacing(isSpac);
                    themeEditorSteps.removeAllContent();
                }
                themeEditorSteps.clickAddContent();
                themeEditorSteps.openBlockContent();
                themeEditorSteps.uploadImageWithFile(img);
                galleryImageSteps.inputAltText(altText);
                tmp = rowKey;
                if (!rowKey.equals(nextRowKey)) {
                    themeEditorSteps.saveSetting();
                    themeEditorSteps.closeSessionSetting();
                }

            }

        }

    }

    @Then("change setting image with text {string}")
    public void changeSettingContentImageWithText(String dataKey, List<List<String>> dataTable) {
        themeEditorSteps.clickSection("Image with text");
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String chooseLayout = SessionData.getDataTbVal(dataTable, row, "Choose layout");
            String imagesPerRow = SessionData.getDataTbVal(dataTable, row, "Images per row");
            String promotionsAnimation = SessionData.getDataTbVal(dataTable, row, "Promotions animation");
            String yourHeadline = SessionData.getDataTbVal(dataTable, row, "Your headline");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            boolean displayAsButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as button"));


            if (index == 1) {
                imageWithTextSteps.selectLayout(layout);
                if (!layout.equalsIgnoreCase("Box")) {
                    imageWithTextSteps.checkCheckBox("Full width section", fullWidthSection);
                }
                imageWithTextSteps.selectTextAlignment(textAlignment);
                themeEditorSteps.removeAllContent();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            imageWithTextSteps.inputLink("Button link", buttonLink);
            themeEditorSteps.uploadImageWithFile(image);
            imageWithTextSteps.inputTextBox("Alt text", altText);
            imageWithTextSteps.checkCheckBoxContentActive("Display as a button", displayAsButton);
            imageWithTextSteps.inputLink("Image link", imageLink);
            imageWithTextSteps.inputTextBox("Your headline", headline);
            imageWithTextSteps.inputTextContentWithIframe(text);
            imageWithTextSteps.inputTextBox("Button label", buttonLabel);

            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @Then("change mega menu {string}")
    public void changeMegaMenu(String dataKey, List<List<String>> dataTable) {
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean enableMegaMenu = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable mega menu"));
            String linkTitle = SessionData.getDataTbVal(dataTable, row, "Link title");
            boolean enableMenu = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable menu"));
            String menuTitle = SessionData.getDataTbVal(dataTable, row, "Menu title");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String menuList = SessionData.getDataTbVal(dataTable, row, "Menu list");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String imageUrl = SessionData.getDataTbVal(dataTable, row, "Image URL");
            String headline = SessionData.getDataTbVal(dataTable, row, "Headline");

            if (index == 1) {
                imageWithTextSteps.checkCheckBox("Enable mega menu", enableMegaMenu);
                if (enableMegaMenu) {
                    megaMenuSteps.clickOnTextLink("Edit mega menu");
                    megaMenuSteps.removeAllContentMegaMenu();
                }
            }
            if (enableMegaMenu) {
                megaMenuSteps.selectLinkTitle(linkTitle);
                imageWithTextSteps.checkCheckBox("Enable menu", enableMenu);

                themeEditorSteps.clickAddColumn();
                themeEditorSteps.openBlockContent();
                imageWithTextSteps.inputTextBox("Menu title", menuTitle);
                imageWithTextSteps.inputLink("Link", link);
                megaMenuSteps.selectMenuList(menuList);
                themeEditorSteps.uploadImageWithFile(image);
                imageWithTextSteps.inputLink("Image URL", imageUrl);
                imageWithTextSteps.inputTextBox("Headline", headline);
            }

            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @And("change Logo settings as {string}")
    public void changeLogoSettingsAs(String dataKey, List<List<String>> dataTable) {
        homePageSteps.refreshpage();
        themeEditorSteps.clickSection("Logo");
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");

            if (index == 1) {
                logoSectionSteps.enterHeading(heading);
                logoSectionSteps.chooseTextAlignment(textAlignment);
                themeEditorSteps.removeAllContent();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            themeEditorSteps.uploadImageWithFile(image);
            logoSectionSteps.inputAltText("Alt text", altText);
            logoSectionSteps.inputImageLink("Link", imageLink);

            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }


    }

    @And("change Featured Promotion settings as {string}")
    public void changeFeaturedPromotionSettingsAs(String dataKey, List<List<String>> dataTable) {
        themeEditorSteps.clickSection("Featured promotion");
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");

            if (index == 1) {
                imageWithTextSteps.checkCheckBox("Full width section", fullWidthSection);
                themeEditorSteps.removeFeature();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            themeEditorSteps.uploadImageWithFile(image);
            logoSectionSteps.inputAltText("Alt text", altText);
            featuredPromotionSteps.inputText("Text", text);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @And("verify product widgets on Homepage Theme editor")
    public void verifyProductWidgetsOnHomepageThemeEditor() {
        themeEditorSteps.verifySectionBestSellers("Best sellers");
        themeEditorSteps.verifySectionRecentlyViewedFeaturedProducts("Recently viewed & featured products");
        themeEditorSteps.verifySectionCartRecommendations("Cart recommendations");
    }

    @And("verify product widgets on Product page Theme editor")
    public void verifyProductWidgetsOnProductPageThemeEditor() {
        themeEditorSteps.verifySectionProductsFromTheSameCollections("Products from the same collections");
        themeEditorSteps.verifySectionReview("Reviews");
        themeEditorSteps.verifyWhoBoughtThisAlsoBought("Who bought this also bought");
        themeEditorSteps.verifySectionBestSellers("Best sellers");
        themeEditorSteps.verifyHandpickedProducts("Handpicked products");
        themeEditorSteps.verifySectionCartRecommendations("Cart recommendations");
        themeEditorSteps.verifySectionRecentlyViewedFeaturedProducts("Recently viewed & featured products");
    }

    @And("verify product widgets on Cart page Theme editor")
    public void verifyProductWidgetsOnCartPageThemeEditor() {
        themeEditorSteps.navigationToSectionTab();
        themeEditorSteps.verifySectionCartRecommendations("Cart recommendations");
        themeEditorSteps.verifySectionProductsFromTheSameCollections("Products from the same collections");
        themeEditorSteps.verifySectionBestSellers("Best sellers");
        themeEditorSteps.verifySectionRecentlyViewedFeaturedProducts("Recently viewed & featured products");
    }

    @And("verify product widgets on Collection pages Theme editor")
    public void verifyProductWidgetsOnCollectionPagesThemeEditor() {
        themeEditorSteps.verifySectionBestSellers("Best sellers");
        themeEditorSteps.verifySectionCartRecommendations("Cart recommendations");
        themeEditorSteps.verifySectionRecentlyViewedFeaturedProducts("Recently viewed & featured products");
    }

    @And("change slideshow settings for bassy as {string}")
    public void changeSlideShowSettingForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String previousRowKey = SessionData.getDataTbVal(dataTable, row - 1, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String autoRotateSlides = SessionData.getDataTbVal(dataTable, row, "Auto-rotate slides");
            String changeSlidesEvery = SessionData.getDataTbVal(dataTable, row, "Change slides every");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subheading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String textColor = SessionData.getDataTbVal(dataTable, row, "Text color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");

            if (!rowKey.equals(previousRowKey)) {
                if (!autoRotateSlides.isEmpty()) {
                    imageWithTextSteps.checkCheckBox("Auto-rotate slides", Boolean.parseBoolean(autoRotateSlides));
                }
                if (!changeSlidesEvery.isEmpty()) {
                    themeEditorSteps.selectDropDown("Change slides every", changeSlidesEvery);
                }
                imageWithTextSteps.RemoveAllContent("Slide");
            }

            themeEditorSteps.clickAddSection();
            themeEditorSteps.openBlockContent();
            themeEditorSteps.uploadImageWithFile(image);
            imageWithTextSteps.inputTextBox("Alt text", altText);
            themeEditorSteps.sellectTextAlignment(textAlignment);
            imageWithTextSteps.inputTextBox("Heading", heading);
            imageWithTextSteps.inputTextBox("Subheading", subheading);
            imageWithTextSteps.inputTextBox("Button label", buttonLabel);
            imageWithTextSteps.inputLink("Button link", buttonLink);
            if (!textColor.isEmpty()) {
                imageWithTextSteps.inputTextBox("Text", textColor);
            }
            if (!buttonColor.isEmpty()) {
                imageWithTextSteps.inputTextBox("Button", buttonColor);
            }
            if (!buttonLabelColor.isEmpty()) {
                themeEditorSteps.inputButtonLabelColor(buttonLabelColor);
            }
            if (!rowKey.equals(nextRowKey)) {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @Then("change introduction text settings for bassy as {string}")
    public void changeIntroductionTextForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");

            themeEditorSteps.inputTextBox("Heading", heading);
            richTextSteps.enterText(body);
            themeEditorSteps.saveSetting();
        }
    }

    @Then("change video setting for bassy as {string}")
    public void changeVideoForBassy(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settings = SessionData.getDataTbVal(dataTable, row, "Settings");
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");

            themeEditorSteps.inputTextBox("Settings", settings);
            themeEditorSteps.inputTextBox("Video link", videoLink);
            themeEditorSteps.saveSetting();
        }
    }

    @And("change header settings for bassy as {string}")
    public void changeHeaderSettingsForBassyAs(String dataKey, List<List<String>> dataTable) {
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

            themeEditorSteps.uploadLogo(logo);
            themeEditorSteps.inputTextBox("Alt text", altText);
            headerSteps.selectMenu(menu);
            headerSteps.checkHeaderSettings("Enable search", enableSearch);
            headerSteps.checkHeaderSettings("Show announcement", showAnnouncement);
            headerSteps.checkHeaderSettings("Home page only", homePageOnly);
            imageWithTextSteps.inputLink("Button link", buttonLink);
            themeEditorSteps.inputTextBox("Text", text);
            themeEditorSteps.inputTextBox("Bar", barColor);
            themeEditorSteps.inputTextColor("Text", textColor);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("change Footer settings for bassy as {string}")
    public void changeFooterSettingsForBassyAs(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String footerMenu = SessionData.getDataTbVal(dataTable, row, "Footer menu");
            String customText = SessionData.getDataTbVal(dataTable, row, "Custom text");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String facebook = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String youtube = SessionData.getDataTbVal(dataTable, row, "Youtube");
            String pinterest = SessionData.getDataTbVal(dataTable, row, "Pinterest");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            boolean showPaymentMethodIcons = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            boolean enableSocialProfile = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable Social Profile"));
            if (index == 1) {
                themeEditorSteps.selectFooterMenu(footerMenu);
                headerSteps.checkHeaderSettings("Show payment method icons", showPaymentMethodIcons);
                themeEditorSteps.inputCustomText(customText);
                headerSteps.checkHeaderSettings("Enable", enableSocialProfile);
                if (enableSocialProfile) {
                    themeEditorSteps.inputTextBox("Facebook", facebook);
                    themeEditorSteps.inputTextBox("Youtube", youtube);
                    themeEditorSteps.inputTextBox("Pinterest", pinterest);
                }
                themeEditorSteps.removeAllPaymentIcon();
            }
            themeEditorSteps.clickAddMorePaymentIcon();
            themeEditorSteps.openBlockContent();
            themeEditorSteps.uploadImage(image);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }
    }

    @And("user change theme style as {string}")
    public void userChangeThemeStyleAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String themeStyle = SessionData.getDataTbVal(dataTable, row, "Select style");
            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickBtnChangeThemeStyle();
            themeEditorSteps.verifyPopupShown("Select style");
            themeEditorSteps.selectStyle(themeStyle);
            themeEditorSteps.setImageOnSlideShowToDefault(true);
            themeEditorSteps.clickBtnChange();
            themeEditorSteps.saveSetting();

        }

    }

    @Then("verify color settings {string}")
    public void verifyColorSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String backgroundHeader = SessionData.getDataTbVal(dataTable, row, "Background Header");
            String linksHeader = SessionData.getDataTbVal(dataTable, row, "Links Header");
            String backgroundPage = SessionData.getDataTbVal(dataTable, row, "Background Page");
            String headingsPage = SessionData.getDataTbVal(dataTable, row, "Headings Page");
            String bodyTextPage = SessionData.getDataTbVal(dataTable, row, "Body text Page");
            String lineColorPage = SessionData.getDataTbVal(dataTable, row, "Line color Page");
            String buttonsLabelPage = SessionData.getDataTbVal(dataTable, row, "Buttons label Page");
            String buttonsPage = SessionData.getDataTbVal(dataTable, row, "Buttons Page");
            String linksAndAccentsPage = SessionData.getDataTbVal(dataTable, row, "Links and accents Page");
            String productBackgroundPage = SessionData.getDataTbVal(dataTable, row, "Product background Page");
            String saleTagsPage = SessionData.getDataTbVal(dataTable, row, "Sale tags Page");
            String formFieldsPage = SessionData.getDataTbVal(dataTable, row, "Form fields Page");
            String newsletterAndTablesBackgroundPage = SessionData.getDataTbVal(dataTable, row, "Newsletter and tables background Page");
            String footerBackground = SessionData.getDataTbVal(dataTable, row, "Footer Background");
            String footerText = SessionData.getDataTbVal(dataTable, row, "Footer Text");
            String drawerButton = SessionData.getDataTbVal(dataTable, row, "Drawer Button");
            String drawerText = SessionData.getDataTbVal(dataTable, row, "Drawer Text");
            String drawerLinesAndBorders = SessionData.getDataTbVal(dataTable, row, "Drawer Lines and borders");
            String drawerBackground = SessionData.getDataTbVal(dataTable, row, "Drawer Background");
            themeEditorSteps.navigateToSettingTab();
            themeEditorSteps.openSettingBlock("Colors");
            themeEditorSteps.verifyColor("Header", "Background", backgroundHeader);
            themeEditorSteps.verifyColor("Header", "Links", linksHeader);
            themeEditorSteps.verifyColor("Page", "Background", backgroundPage);
            themeEditorSteps.verifyColor("Page", "Headings", headingsPage);
            themeEditorSteps.verifyColor("Page", "Body text", bodyTextPage);
            themeEditorSteps.verifyColor("Page", "Line color", lineColorPage);
            themeEditorSteps.verifyColor("Page", "Buttons label", buttonsLabelPage);
            themeEditorSteps.verifyColor("Page", "Buttons", buttonsPage);
            themeEditorSteps.verifyColor("Page", "Links and accents", linksAndAccentsPage);
            themeEditorSteps.verifyColor("Page", "Product background", productBackgroundPage);
            themeEditorSteps.verifyColor("Page", "Sale tags", saleTagsPage);
            themeEditorSteps.verifyColor("Page", "Form fields", formFieldsPage);
            themeEditorSteps.verifyColor("Page", "Newsletter and tables background", newsletterAndTablesBackgroundPage);
            themeEditorSteps.verifyColor("Footer", "Background", footerBackground);
            themeEditorSteps.verifyColor("Footer", "Text", footerText);
            themeEditorSteps.verifyColor("Drawers", "Button", drawerButton);
            themeEditorSteps.verifyColor("Drawers", "Text", drawerText);
            themeEditorSteps.verifyColor("Drawers", "Lines and borders", drawerLinesAndBorders);
            themeEditorSteps.verifyColor("Drawers", "Background", drawerBackground);
            themeEditorSteps.clickBtnBack();
        }
    }

    @Then("change setting image with text Roller {string}")
    public void changeSettingImageWithTextRoller(String dataKey, List<List<String>> dataTable) {
        themeEditorSteps.clickSection("Image with text");
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String chooseLayout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String imagesPerRow = SessionData.getDataTbVal(dataTable, row, "Images per row");
            String promotionsAnimation = SessionData.getDataTbVal(dataTable, row, "Promotions animation");
            String yourHeadline = SessionData.getDataTbVal(dataTable, row, "Headline");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            boolean displayAsButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display as button"));


            if (index == 1) {
                themeEditorSteps.selectDroplistByLabel("Choose layout", chooseLayout);
                if (chooseLayout.equalsIgnoreCase("Image with text above")) {
                    themeEditorSteps.selectDroplistByLabel("Images per row", imagesPerRow);
                    themeEditorSteps.selectDroplistByLabel("Promotions animation", promotionsAnimation);
                } else {
                    themeEditorSteps.selectDroplistByLabel("Text alignment", textAlignment);
                }
                themeEditorSteps.removeAllContent();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            imageWithTextSteps.inputLink("Button link", buttonLink);
            themeEditorSteps.uploadImageWithFile(image);
            imageWithTextSteps.inputTextBox("Alt text", altText);
            imageWithTextSteps.checkCheckBoxContentActive("Display as a button", displayAsButton);
            imageWithTextSteps.inputTextBox("Your headline", yourHeadline);
            imageWithTextSteps.inputTextContentWithIframe(text);
            imageWithTextSteps.inputTextBox("Button label", buttonLabel);

            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }

    }

    @Then("change mega menu Roller {string}")
    public void changeMegaMenuRoller(String dataKey, List<List<String>> dataTable) {
        int index = 1;

        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String linkTitle = SessionData.getDataTbVal(dataTable, row, "Link title");
            String textAreaTopContent = SessionData.getDataTbVal(dataTable, row, "Text area Top content");
            String imageTopContent = SessionData.getDataTbVal(dataTable, row, "Image Top content");
            String imageCaptionTopContent = SessionData.getDataTbVal(dataTable, row, "Image caption Top content");
            String imageBottomContent = SessionData.getDataTbVal(dataTable, row, "Image Bottom content");
            String imageCaptionBottomContent = SessionData.getDataTbVal(dataTable, row, "Image caption Bottom content");
            String imageLinkBottomContent = SessionData.getDataTbVal(dataTable, row, "Image link Bottom content");
            String textAreaBottomContent = SessionData.getDataTbVal(dataTable, row, "Text area Bottom content");

            if (index == 1) {
                themeEditorSteps.inputTextBox("Link title", linkTitle);
                themeEditorSteps.removeAllContent();
            }
            themeEditorSteps.clickAddContent();
            themeEditorSteps.openBlockContent();
            themeEditorSteps.inputTextArea("Top content",textAreaTopContent);
            themeEditorSteps.uploadImageWithLabel("Top content",imageTopContent);
            themeEditorSteps.inputTextBoxWithLabel("Top content","Image caption",imageCaptionTopContent);
            themeEditorSteps.inputTextArea("Bottom content",textAreaBottomContent);
            themeEditorSteps.uploadImageWithLabel("Bottom content",imageBottomContent);
            themeEditorSteps.inputTextBoxWithLabel("Bottom content","Image caption",imageCaptionBottomContent);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
            }
        }


    }
}

