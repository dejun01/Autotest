package opencommerce.online_store.landingpage;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.landingpage.steps.*;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.FeaturedPromotionSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.ImageWithTextSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings.TypographySteps;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class LandingPageDef {
    @Steps
    LandingPageSteps landingPageSteps;
    @Steps
    TypographySteps typographySteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    ImageWithTextSteps imageWithTextSteps;
    @Steps
    HeroSectionSteps heroSectionSteps;
    @Steps
    HeaderSectionSteps headerSectionSteps;
    @Steps
    RichTextSteps richTextSteps;
    @Steps
    PAQsSectionSteps paQsSectionSteps;
    @Steps
    FooterSectionSteps footerSectionSteps;
    @Steps
    GalleryImageSectionsSteps galleryImageSectionsSteps;
    @Steps
    TimerCountdownSteps timerCountdownSteps;
    @Steps
    FormSectionSteps formSectionSteps;
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    FeaturedPromotionSectionSteps featuredPromotionSectionSteps;
    @Steps
    TrustIndicatorSteps trustIndicatorSteps;
    @Steps
    FeaturedProductSteps featuredProductSteps;
    @Steps
    ProductImageSteps productImageSteps;

    @Steps
    CommonSteps commonSteps;
    @Steps
    ProductRichTextSteps productRichTextSteps;
    @Steps
    ProductListSteps productListSteps;

    @And("remove all pages")
    public void removeAllPages() {
        if (landingPageSteps.checkExistPage()) {
            landingPageSteps.clickCheckbox();
            landingPageSteps.clickBtnAction();
            landingPageSteps.clickDeletePages();
            landingPageSteps.verifyDeleteAllPage();
        }
    }

    @And("create landing page as {string}")
    public void createLandingPageAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String selectTemplate = SessionData.getDataTbVal(dataTable, row, "Select a template");
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String seoTitle = SessionData.getDataTbVal(dataTable, row, "SEO Title");
            String seoDescription = SessionData.getDataTbVal(dataTable, row, "SEO Description");
            String openGraphImage = SessionData.getDataTbVal(dataTable, row, "Open Graph Image");
            boolean publishThisPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Publish this page"));
            String totalSection = SessionData.getDataTbVal(dataTable, row, "Total section");

            landingPageSteps.clickBtnAddPage();
            landingPageSteps.selectTemplate(selectTemplate);
            landingPageSteps.inputWithTitle("Page title", pageTitle);
            landingPageSteps.settingPublishThisPage(publishThisPage);
            landingPageSteps.inputWithTitle("SEO Title (Optional)", seoTitle);
            landingPageSteps.inputSEODescription(seoDescription);
            landingPageSteps.uploadOpenGraphImage(openGraphImage);
            landingPageSteps.clickAddPage();
            landingPageSteps.verrifyCreateSuccess(totalSection, selectTemplate);
            landingPageSteps.clickBtnClose();
        }
    }

    @And("verifyLandingPage {string}")
    public void verifylandingpage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String seoTitle = SessionData.getDataTbVal(dataTable, row, "SEO Title");
            String seoDescription = SessionData.getDataTbVal(dataTable, row, "SEO Description");
            String openGraphImage = SessionData.getDataTbVal(dataTable, row, "Open Graph Image");
            boolean publishThisPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Publish this page"));

            landingPageSteps.clickIconDetail(pageTitle);
            landingPageSteps.verifyPageTitle("Page title", pageTitle);
            landingPageSteps.verifySeoTitle("SEO Title (Optional)", seoTitle);
            landingPageSteps.verifySeoDescription("SEO Description (Optional)", seoDescription);
            landingPageSteps.verifyopenGraphImage(openGraphImage);
            landingPageSteps.verifyOpenLinkSF(pageTitle, publishThisPage);
        }
    }

    @And("open landing editor {string}")
    public void openLandingEditor(String titleLanding) {
        landingPageSteps.openLandingEditor(titleLanding);
    }

    @And("change hero section  {string}")
    public void changeHeroSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColorTypography = SessionData.getDataTbVal(dataTable, row, "Title color Typography");
            String titleFontTypography = SessionData.getDataTbVal(dataTable, row, "Title font Typography");
            String titleSizeTypography = SessionData.getDataTbVal(dataTable, row, "Title size Typography");
            String bodyColorTypography = SessionData.getDataTbVal(dataTable, row, "Body color Typography");
            String bodyFontTypography = SessionData.getDataTbVal(dataTable, row, "Body font Typography");
            String bodySizeTypography = SessionData.getDataTbVal(dataTable, row, "Body size Typography");
            String buttonColorTypography = SessionData.getDataTbVal(dataTable, row, "Button color Typography");
            String buttonLabelColorTypography = SessionData.getDataTbVal(dataTable, row, "Button label color Typography");
            String buttonLabelFontTypography = SessionData.getDataTbVal(dataTable, row, "Button label font Typography");
            String buttonLabelSizeTypography = SessionData.getDataTbVal(dataTable, row, "Button label size Typography");
            String borderTopColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border top color Separator");
            String borderBottomColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border bottom color Separator");
            String positionLayout = SessionData.getDataTbVal(dataTable, row, "Position Layout");
            String alignmentLayout = SessionData.getDataTbVal(dataTable, row, "Alignment Layout");
            String minHeightLayout = SessionData.getDataTbVal(dataTable, row, "Min height Layout");
            String maxWidthLayout = SessionData.getDataTbVal(dataTable, row, "Max width Layout");

            landingPageSteps.openSection("Hero");
            landingPageSteps.inputTextBox("Title", title);
            imageWithTextSteps.inputTextContentWithIframe(body);
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Button link", buttonLink);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.scrollToLabel("Title font");
            landingPageSteps.selectDdlWithLabel("Type", typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColorBackground);
                landingPageSteps.inputColor("End color", endColorBackground);
                landingPageSteps.selectDdlWithLabel("Direction", directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                landingPageSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Title color", titleColorTypography);
            landingPageSteps.changeFont("Title font", titleFontTypography);
            landingPageSteps.inputTextBox("Title size", titleSizeTypography);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentLayout, 1);
            landingPageSteps.inputColor("Body color", bodyColorTypography);
            landingPageSteps.changeFont("Body font", bodyFontTypography);
            landingPageSteps.inputTextBox("Body size", bodySizeTypography);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentLayout, 2);
            landingPageSteps.inputColor("Button color", buttonColorTypography);
            landingPageSteps.inputColor("Button label color", buttonLabelColorTypography);
            landingPageSteps.changeFont("Button label font", buttonLabelFontTypography);
            landingPageSteps.inputTextBox("Button label size", buttonLabelSizeTypography);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentLayout, 3);
            landingPageSteps.inputColor("Border top color", borderTopColorSeparator);
            landingPageSteps.inputColor("Border bottom color", borderBottomColorSeparator);
            landingPageSteps.selectDdlWithLabel("Position", positionLayout);
            landingPageSteps.selectDdlWithLabel("Min height", minHeightLayout);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }

    }

    @And("open {string} on Storefront")
    public void openOnStorefront(String title) {
        landingPageSteps.openOnStorefront(title);
    }

    @And("change Custom HTML section  {string}")
    public void changeCustomHTMLSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String html = SessionData.getDataTbVal(dataTable, row, "HTML");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            landingPageSteps.openSection("Custom HTML");
            landingPageSteps.inputTextArea("HTML", html);
            landingPageSteps.checkCheckBox("Full width section", fullWidthSection);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }
    }

    @And("verify show Custom HTML on SF {string}")
    public void verifyShowCustomHTMLOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String html = SessionData.getDataTbVal(dataTable, row, "HTML");
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            landingPageSteps.verifyShowHTML(html);
            landingPageSteps.verifyCustomHTML(html);
            landingPageSteps.verifyFullWidthSection(fullWidthSection);
        }
    }

    @And("verify show Hero on SF {string}")
    public void verifyShowHeroOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColorTypography = SessionData.getDataTbVal(dataTable, row, "Title color Typography");
            String titleFontTypography = SessionData.getDataTbVal(dataTable, row, "Title font Typography");
            String titleSizeTypography = SessionData.getDataTbVal(dataTable, row, "Title size Typography");
            String bodyColorTypography = SessionData.getDataTbVal(dataTable, row, "Body color Typography");
            String bodyFontTypography = SessionData.getDataTbVal(dataTable, row, "Body font Typography");
            String bodySizeTypography = SessionData.getDataTbVal(dataTable, row, "Body size Typography");
            String buttonColorTypography = SessionData.getDataTbVal(dataTable, row, "Button color Typography");
            String buttonLabelColorTypography = SessionData.getDataTbVal(dataTable, row, "Button label color Typography");
            String buttonLabelFontTypography = SessionData.getDataTbVal(dataTable, row, "Button label font Typography");
            String buttonLabelSizeTypography = SessionData.getDataTbVal(dataTable, row, "Button label size Typography");
            String borderTopColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border top color Separator");
            String borderBottomColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border bottom color Separator");
            String positionLayout = SessionData.getDataTbVal(dataTable, row, "Position Layout");
            String alignmentLayout = SessionData.getDataTbVal(dataTable, row, "Alignment Layout");
            String minHeightLayout = SessionData.getDataTbVal(dataTable, row, "Min height Layout");
            String maxWidthLayout = SessionData.getDataTbVal(dataTable, row, "Max width Layout");

            heroSectionSteps.verifyTile(title);
            heroSectionSteps.verifyBodyText(body);
            heroSectionSteps.verifyButtonLabel(buttonLabel);
            heroSectionSteps.verifyButtonLink(buttonLink, buttonLabel);
            heroSectionSteps.verifyTypeBackground(typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                heroSectionSteps.verifyCustomColorBackground(customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                heroSectionSteps.verifyStartColorBackground(startColorBackground);
                heroSectionSteps.verifyEndColorBackground(endColorBackground);
                heroSectionSteps.verifyDirectionBackground(directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                heroSectionSteps.verifyImageBackground(imageBackground);
                heroSectionSteps.verifyColorOverlayBackground(colorOverlayBackground);
            }
            heroSectionSteps.verifyTitleColorTypography(titleColorTypography);
            heroSectionSteps.verifyTitleFontTypography(titleFontTypography);
            heroSectionSteps.verifyTitleSizeTypography(titleSizeTypography);
            heroSectionSteps.verifyAlignmenTitle(alignmentLayout);
            heroSectionSteps.verifyBodyColorTypography(bodyColorTypography);
            heroSectionSteps.verifyBodyFontTypography(bodyFontTypography);
            heroSectionSteps.verifyBodySizeTypography(bodySizeTypography);
            heroSectionSteps.verifyAlignmenBody(alignmentLayout);
            heroSectionSteps.verifyButtonColorTypography(buttonColorTypography);
            heroSectionSteps.verifyButtonLabelColorTypography(buttonLabelColorTypography);
            heroSectionSteps.verifyButtonLabelFontTypography(buttonLabelFontTypography);
            heroSectionSteps.verifyButtonLabelSizeTypography(buttonLabelSizeTypography);
            heroSectionSteps.verifyAlignmenButton(alignmentLayout);
            heroSectionSteps.verifyBorderTopColorSeparator(borderTopColorSeparator);
            heroSectionSteps.verifyBorderBottomColorSeparator(borderBottomColorSeparator);
            heroSectionSteps.verifyPositionLayout(positionLayout);
            heroSectionSteps.verifyMinHeightLayout(minHeightLayout, typeBackground);

        }
    }

    @And("change Header section  {string}")
    public void changeHeaderSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showLogo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show logo"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile logo");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String menuTitle = SessionData.getDataTbVal(dataTable, row, "Menu title ");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String desktopLogoSize = SessionData.getDataTbVal(dataTable, row, "Desktop logo size");
            String mobileLogoSize = SessionData.getDataTbVal(dataTable, row, "Mobile logo size");
            String menuItemColor = SessionData.getDataTbVal(dataTable, row, "Menu item color");
            String menuItemFont = SessionData.getDataTbVal(dataTable, row, "Menu item font");
            String menuItemSize = SessionData.getDataTbVal(dataTable, row, "Menu item size");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String submenuBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Submenu background color");
            String submenuItemColor = SessionData.getDataTbVal(dataTable, row, "Submenu item color");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String desktopLayout = SessionData.getDataTbVal(dataTable, row, "Desktop layout");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");

            landingPageSteps.openSection("Header");
            landingPageSteps.checkCheckBox("Show logo", showLogo);
            if (showLogo) {
                landingPageSteps.uploadImageWithLabel("Desktop logo", desktopLogo);
                landingPageSteps.uploadImageWithLabel("Mobile logo", mobileLogo);
            }
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Button link", buttonLink);
            landingPageSteps.opendMenuItem("Menu item", 1);
            landingPageSteps.inputTextBox("Menu title", menuTitle);
            landingPageSteps.inputTextBox("Link", link);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.scrollToLabel("Desktop logo size");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            landingPageSteps.selectDdlWithLabel("Desktop logo size", desktopLogoSize);
            landingPageSteps.selectDdlWithLabel("Mobile logo size", mobileLogoSize);
            landingPageSteps.inputColor("Menu item color", menuItemColor);
            landingPageSteps.changeFont("Menu item font", menuItemFont);
            landingPageSteps.inputTextBox("Menu item size", menuItemSize);
            landingPageSteps.inputColor("Button color", buttonColor);
            landingPageSteps.inputColor("Button label color", buttonLabelColor);
            landingPageSteps.changeFont("Button label font", buttonLabelFont);
            landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
            landingPageSteps.inputColor("Submenu background color", submenuBackgroundColor);
            landingPageSteps.inputColor("Submenu item color", submenuItemColor);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Desktop layout", desktopLayout);
            landingPageSteps.selectDdlWithLabel("Alignment", alignment);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }
    }

    @And("change Rich Text section  {string}")
    public void changeRichTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColorTypography = SessionData.getDataTbVal(dataTable, row, "Title color Typography");
            String titleFontTypography = SessionData.getDataTbVal(dataTable, row, "Title font Typography");
            String titleSizeTypography = SessionData.getDataTbVal(dataTable, row, "Title size Typography");
            String bodyColorTypography = SessionData.getDataTbVal(dataTable, row, "Body color Typography");
            String bodyFontTypography = SessionData.getDataTbVal(dataTable, row, "Body font Typography");
            String bodySizeTypography = SessionData.getDataTbVal(dataTable, row, "Body size Typography");
            String typeButton = SessionData.getDataTbVal(dataTable, row, "Type Button");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String linkColor = SessionData.getDataTbVal(dataTable, row, "Link color");
            String linkFont = SessionData.getDataTbVal(dataTable, row, "Link font");
            String linkSize = SessionData.getDataTbVal(dataTable, row, "Link size");
            String borderTopColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border top color Separator");
            String borderBottomColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border bottom color Separator");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String positionLayout = SessionData.getDataTbVal(dataTable, row, "Position Layout");
            String alignmentLayout = SessionData.getDataTbVal(dataTable, row, "Alignment Layout");
            String minHeightLayout = SessionData.getDataTbVal(dataTable, row, "Min height Layout");
            String maxWidthLayout = SessionData.getDataTbVal(dataTable, row, "Max width Layout");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Rich text");
            landingPageSteps.inputTextBox("Title", title);
            imageWithTextSteps.inputTextContentWithIframe(body);
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Button link", buttonLink);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.scrollToLabel("Title font");
            landingPageSteps.selectDdlWithLabel("Type", typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColorBackground);
                landingPageSteps.inputColor("End color", endColorBackground);
                landingPageSteps.selectDdlWithLabel("Direction", directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                themeEditorSteps.uploadImage(imageBackground);
                landingPageSteps.scrollToLabel("Title color");
                landingPageSteps.inputColor("Overlay color", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Title color", titleColorTypography);
            landingPageSteps.changeFont("Title font", titleFontTypography);
            landingPageSteps.inputTextBox("Title size", titleSizeTypography);
            landingPageSteps.inputColor("Body color", bodyColorTypography);
            landingPageSteps.changeFont("Body font", bodyFontTypography);
            landingPageSteps.inputTextBox("Body size", bodySizeTypography);
            landingPageSteps.selectDdlWithLabel("Type", typeButton);
            if (typeButton.equalsIgnoreCase("Button")) {
                landingPageSteps.inputColor("Button color", buttonColor);
                landingPageSteps.inputColor("Button label color", buttonLabelColor);
                landingPageSteps.changeFont("Button label font", buttonLabelFont);
                landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
            } else {
                landingPageSteps.inputColor("Link color", linkColor);
                landingPageSteps.changeFont("Link font", linkFont);
                landingPageSteps.inputTextBox("Link size", linkSize);
            }
            landingPageSteps.inputColor("Border top color", borderTopColorSeparator);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColorSeparator);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Position", positionLayout);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentLayout);
            landingPageSteps.selectDdlWithLabel("Min height", minHeightLayout);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidthLayout);
            if (maxWidthLayout.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();

        }
    }

    @And("verify Header section on SF {string}")
    public void verifyHeaderSectionOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showLogo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show logo"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile logo");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String menuTitle = SessionData.getDataTbVal(dataTable, row, "Menu title ");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction ");
            String desktopLogoSize = SessionData.getDataTbVal(dataTable, row, "Desktop logo size");
            String mobileLogoSize = SessionData.getDataTbVal(dataTable, row, "Mobile logo size");
            String menuItemColor = SessionData.getDataTbVal(dataTable, row, "Menu item color");
            String menuItemFont = SessionData.getDataTbVal(dataTable, row, "Menu item font");
            String menuItemSize = SessionData.getDataTbVal(dataTable, row, "Menu item size");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String submenuBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Submenu background color");
            String submenuItemColor = SessionData.getDataTbVal(dataTable, row, "Submenu item color");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String desktopLayout = SessionData.getDataTbVal(dataTable, row, "Desktop layout");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");

            headerSectionSteps.verifyShowLogo(showLogo, desktopLogo);
            headerSectionSteps.verifyDesktopLogo(showLogo, desktopLogo);
            headerSectionSteps.verifyButtonLabel(buttonLabel);
            headerSectionSteps.verifyButtonLink(buttonLink);
            headerSectionSteps.verifyMenuTitle(menuTitle);
            headerSectionSteps.verifyLink(link, menuTitle);
            headerSectionSteps.verifyType(type);
            headerSectionSteps.verifyCustomColor(customColor);
            headerSectionSteps.verifyStartColor(startColor);
            headerSectionSteps.verifyEndColor(endColor);
            headerSectionSteps.verifyDirection(direction);
            headerSectionSteps.verifyDesktopLogoSize(showLogo, desktopLogo, desktopLogoSize);
            headerSectionSteps.verifyMenuItemColor(menuItemColor, menuTitle);
            headerSectionSteps.verifyMenuItemFont(menuItemFont, menuTitle);
            headerSectionSteps.verifyMenuItemSize(menuItemSize, menuTitle);
            headerSectionSteps.verifyButtonColor(buttonColor);
            headerSectionSteps.verifyButtonLabelColor(buttonLabelColor);
            headerSectionSteps.verifyButtonLabelFont(buttonLabelFont);
            headerSectionSteps.verifyButtonLabelSize(buttonLabelSize);
            headerSectionSteps.verifySubmenuBackgroundColor(submenuBackgroundColor);
            headerSectionSteps.verifySubmenuItemColor(submenuItemColor);
            headerSectionSteps.verifyBorderTopColor(borderTopColor);
            headerSectionSteps.verifyBorderBottomColor(borderBottomColor);
            headerSectionSteps.verifyBorderBottomSize(borderBottomSize);
            headerSectionSteps.verifyDesktopLayout(desktopLayout);
            headerSectionSteps.verifyAlignment(alignment, showLogo);
            headerSectionSteps.verifyMaxWidth(maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                headerSectionSteps.verifyCustomWidth(customWidth);
            }
        }
    }

    @And("verify Rich Text on SF {string}")
    public void verifyRichTextOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColorTypography = SessionData.getDataTbVal(dataTable, row, "Title color Typography");
            String titleFontTypography = SessionData.getDataTbVal(dataTable, row, "Title font Typography");
            String titleSizeTypography = SessionData.getDataTbVal(dataTable, row, "Title size Typography");
            String bodyColorTypography = SessionData.getDataTbVal(dataTable, row, "Body color Typography");
            String bodyFontTypography = SessionData.getDataTbVal(dataTable, row, "Body font Typography");
            String bodySizeTypography = SessionData.getDataTbVal(dataTable, row, "Body size Typography");
            String typeButton = SessionData.getDataTbVal(dataTable, row, "Type Button");
            String buttonColor = SessionData.getDataTbVal(dataTable, row, "Button color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String linkColor = SessionData.getDataTbVal(dataTable, row, "Link color");
            String linkFont = SessionData.getDataTbVal(dataTable, row, "Link font");
            String linkSize = SessionData.getDataTbVal(dataTable, row, "Link size");
            String borderTopColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border top color Separator");
            String borderBottomColorSeparator = SessionData.getDataTbVal(dataTable, row, "Border bottom color Separator");
            String positionLayout = SessionData.getDataTbVal(dataTable, row, "Position Layout");
            String alignmentLayout = SessionData.getDataTbVal(dataTable, row, "Alignment Layout");
            String minHeightLayout = SessionData.getDataTbVal(dataTable, row, "Min height Layout");
            String maxWidthLayout = SessionData.getDataTbVal(dataTable, row, "Max width Layout");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            homePageSteps.refreshpage();
            richTextSteps.verifyTitle(title, positionLayout);
            richTextSteps.verifyBody(body, positionLayout);
            richTextSteps.verifyButtonLabel(buttonLabel);
            richTextSteps.verifyButtonLink(buttonLink);
            richTextSteps.verifyBorderBottomColorSeparator(borderBottomColorSeparator, typeBackground);
            richTextSteps.verifyBorderTopColorSeparator(borderTopColorSeparator, typeBackground);
            richTextSteps.verifyTypeBackground(typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                richTextSteps.verifyCustomColorBackground(customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                richTextSteps.verifyStartColorBackground(startColorBackground);
                richTextSteps.verifyEndColorBackground(endColorBackground);
                richTextSteps.verifyDirectionBackground(directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                richTextSteps.verifyImageBackground(imageBackground);
                richTextSteps.verifyColorOverlayBackground(colorOverlayBackground);
            }
            richTextSteps.verifyTitleColorTypography(titleColorTypography, positionLayout, title);
            richTextSteps.verifyTitleFontTypography(titleFontTypography, positionLayout, title);
            richTextSteps.verifyTitleSizeTypography(titleSizeTypography, positionLayout, title);
            richTextSteps.verifyBodyColorTypography(bodyColorTypography, positionLayout, body);
            richTextSteps.verifyBodyFontTypography(bodyFontTypography, positionLayout, body);
            richTextSteps.verifyBodySizeTypography(bodySizeTypography, positionLayout, body);
            richTextSteps.verifyTypeButton(typeButton);
            if (typeButton.equalsIgnoreCase("lp-btn")) {
                richTextSteps.verifyButtonColorTypography(buttonColor);
                richTextSteps.verifyButtonLabelColor(buttonLabelColor);
                richTextSteps.verifyButtonLabelFont(buttonLabelFont);
                richTextSteps.verifyButtonLabelSize(buttonLabelSize);
            } else {
                richTextSteps.verifyLinkColor(linkColor);
                richTextSteps.verifyLinkFont(linkFont);
                richTextSteps.verifyLinkSize(linkSize);
            }
            richTextSteps.verifyPositionLayout(positionLayout);
            richTextSteps.verifyAlignmentLayout(alignmentLayout, positionLayout);
            richTextSteps.verifyMinHeightLayout(minHeightLayout, typeBackground);
            richTextSteps.verifyMaxWidthLayout(maxWidthLayout);
            if (maxWidthLayout.equalsIgnoreCase("Custom")) {
                richTextSteps.verifyCustomWidth(customWidth);
            }
        }
    }

    @And("change FAQs section  {string}")
    public void changeFAQsSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean openTheFirstContentByDefault = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Open the first content by default"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String headingAlignment = SessionData.getDataTbVal(dataTable, row, "Heading alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");

            landingPageSteps.openSection("FAQs");
            landingPageSteps.inputTextBox("Heading", heading);
            landingPageSteps.checkCheckBox("Open the first content by default", openTheFirstContentByDefault);
            landingPageSteps.selectDdlWithLabel("Type", type);
            landingPageSteps.opendMenuItem("Content", 1);
            landingPageSteps.inputTextBox("Title", title);
            landingPageSteps.inputTextContentWithIframe(body, 1);
            landingPageSteps.closeMenuItem("Content", 1);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.scrollToLabel("Heading color");
            landingPageSteps.selectDdlWithLabel("Type", typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColorBackground);
                landingPageSteps.inputColor("End color", endColorBackground);
                landingPageSteps.selectDdlWithLabel("Direction", directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                themeEditorSteps.uploadImage(imageBackground);
                landingPageSteps.scrollToLabel("Title color");
                landingPageSteps.inputColor("Overlay color", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Heading color", headingColor);
            landingPageSteps.changeFont("Heading font", headingFont);
            landingPageSteps.inputTextBox("Heading size", headingSize);
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.inputColor("Body color", bodyColor);
            landingPageSteps.changeFont("Body font", bodyFont);
            landingPageSteps.inputTextBox("Body size", bodySize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Heading alignment", headingAlignment);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();

        }
    }

    @And("verify FAQs on SF {string}")
    public void verifyFAQsOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean openTheFirstContentByDefault = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Open the first content by default"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String typeBackground = SessionData.getDataTbVal(dataTable, row, "Type Background");
            String customColorBackground = SessionData.getDataTbVal(dataTable, row, "Custom color Background");
            String startColorBackground = SessionData.getDataTbVal(dataTable, row, "Start color Background");
            String endColorBackground = SessionData.getDataTbVal(dataTable, row, "End color Background");
            String directionBackground = SessionData.getDataTbVal(dataTable, row, "Direction Background");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String headingAlignment = SessionData.getDataTbVal(dataTable, row, "Heading alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");

            paQsSectionSteps.verifyHeading(heading);
            paQsSectionSteps.verifyOpenTheFirstContentByDefault(openTheFirstContentByDefault);
            paQsSectionSteps.verifyType(type);
            paQsSectionSteps.verifyTitle(title);
            paQsSectionSteps.verifybody(body, openTheFirstContentByDefault);
            paQsSectionSteps.verifyTypeBackground(typeBackground);
            if (typeBackground.equalsIgnoreCase("Custom")) {
                paQsSectionSteps.verifyCustomColorBackground(customColorBackground);
            }
            if (typeBackground.equalsIgnoreCase("Gradient")) {
                paQsSectionSteps.verifyStartColorBackground(startColorBackground);
                paQsSectionSteps.verifyEndColorBackground(endColorBackground);
                paQsSectionSteps.verifyDirectionBackground(directionBackground);
            }
            if (typeBackground.equalsIgnoreCase("Image")) {
                paQsSectionSteps.verifyImageBackground(imageBackground);
                paQsSectionSteps.verifyColorOverlayBackground(colorOverlayBackground);
            }
            paQsSectionSteps.verifyHeadingColor(headingColor);
            paQsSectionSteps.verifyHeadingFont(headingFont);
            paQsSectionSteps.verifyHeadingSize(headingSize);
            paQsSectionSteps.verifyTitleColor(titleColor);
            paQsSectionSteps.verifyTitleFont(titleFont);
            paQsSectionSteps.verifyTitleSize(titleSize);
            paQsSectionSteps.verifyBodyColor(bodyColor, openTheFirstContentByDefault);
            paQsSectionSteps.verifyBodyFont(bodyFont, openTheFirstContentByDefault);
            paQsSectionSteps.verifyBodySize(bodySize, openTheFirstContentByDefault);
            paQsSectionSteps.verifyBorderTopColor(borderTopColor, typeBackground);
            paQsSectionSteps.verifyBorderTopSize(borderTopSize, typeBackground);
            paQsSectionSteps.verifyBorderBottomColor(borderBottomColor, typeBackground);
            paQsSectionSteps.verifyBorderBottomSize(borderBottomSize, typeBackground);
            paQsSectionSteps.verifyHeadingAlignment(headingAlignment, heading);
            paQsSectionSteps.verifyMinHeight(minHeight, typeBackground);
            paQsSectionSteps.verifyMaxWidth(maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                paQsSectionSteps.verifyCustomWidth(customWidth);
            }
        }
    }

    @And("change Footer section  {string}")
    public void changeFooterSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showLogo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show logo"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile log");
            String tagline = SessionData.getDataTbVal(dataTable, row, "Tagline");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String facebook = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String twitter = SessionData.getDataTbVal(dataTable, row, "Twitter");
            String youtube = SessionData.getDataTbVal(dataTable, row, "Youtube");
            String pinterest = SessionData.getDataTbVal(dataTable, row, "Pinterest");
            String instagram = SessionData.getDataTbVal(dataTable, row, "Instagram");
            String blog = SessionData.getDataTbVal(dataTable, row, "Blog");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String desktopLogoSize = SessionData.getDataTbVal(dataTable, row, "Desktop logo size");
            String mobileLogoSize = SessionData.getDataTbVal(dataTable, row, "Mobile logo size");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Footer");
            landingPageSteps.checkCheckBox("Show logo", showLogo);
            if (showLogo) {
                landingPageSteps.uploadImageWithLabel("Desktop logo", desktopLogo);
                landingPageSteps.uploadImageWithLabel("Mobile logo", mobileLogo);
            }
            landingPageSteps.inputTextContentWithIframe(tagline, 1);
            landingPageSteps.inputTextBox("Phone", phone);
            landingPageSteps.inputTextBox("Email", email);
            landingPageSteps.inputTextBox("Facebook", facebook);
            landingPageSteps.inputTextBox("Twitter", twitter);
            landingPageSteps.inputTextBox("Youtube", youtube);
            landingPageSteps.inputTextBox("Pinterest", pinterest);
            landingPageSteps.inputTextBox("Instagram", instagram);
            landingPageSteps.inputTextBox("Blog", blog);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                landingPageSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Overlay color", colorOverlayBackground);
            }
            landingPageSteps.selectDdlWithLabel("Desktop logo size", desktopLogoSize);
            landingPageSteps.selectDdlWithLabel("Mobile logo size", mobileLogoSize);
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.inputColor("Body color", bodyColor);
            landingPageSteps.changeFont("Body font", bodyFont);
            landingPageSteps.inputTextBox("Body size", bodySize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();

        }
    }

    @And("verify Footer on SF {string}")
    public void verifyFooterOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showLogo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show logo"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String mobileLogo = SessionData.getDataTbVal(dataTable, row, "Mobile log");
            String tagline = SessionData.getDataTbVal(dataTable, row, "Tagline");
            String phone = SessionData.getDataTbVal(dataTable, row, "Phone");
            String email = SessionData.getDataTbVal(dataTable, row, "Email");
            String facebook = SessionData.getDataTbVal(dataTable, row, "Facebook");
            String twitter = SessionData.getDataTbVal(dataTable, row, "Twitter");
            String youtube = SessionData.getDataTbVal(dataTable, row, "Youtube");
            String pinterest = SessionData.getDataTbVal(dataTable, row, "Pinterest");
            String instagram = SessionData.getDataTbVal(dataTable, row, "Instagram");
            String blog = SessionData.getDataTbVal(dataTable, row, "Blog");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String desktopLogoSize = SessionData.getDataTbVal(dataTable, row, "Desktop logo size");
            String mobileLogoSize = SessionData.getDataTbVal(dataTable, row, "Mobile logo size");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            footerSectionSteps.verifyShowLogo(showLogo, desktopLogo);
            footerSectionSteps.verifyDesktopLogo(showLogo, desktopLogo);
            footerSectionSteps.verifyMobileLogo(mobileLogo);
            footerSectionSteps.verifyTagline(tagline);
            footerSectionSteps.verifyPhone("HOT LINE", phone);
            footerSectionSteps.verifyEmail("EMAIL", email);
            footerSectionSteps.verifyFacebook("facebook", facebook);
            footerSectionSteps.verifyTwitter("twitter", twitter);
            footerSectionSteps.verifyYoutube("youtube", youtube);
            footerSectionSteps.verifyPinterest("pinterest", pinterest);
            footerSectionSteps.verifyInstagram("instagram", instagram);
            footerSectionSteps.verifyBlog("blog", blog);
            footerSectionSteps.verifyType(type);
            if (type.equalsIgnoreCase("Custom")) {
                footerSectionSteps.verifyCustomColor(customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                footerSectionSteps.verifyStartColor(startColor);
                footerSectionSteps.verifyEndColor(endColor);
                footerSectionSteps.verifyDirection(direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                footerSectionSteps.verifyImageBackground(imageBackground);
                footerSectionSteps.verifyColorOverlayBackground(colorOverlayBackground);
            }
            footerSectionSteps.verifyDesktopLogoSize(showLogo, desktopLogo, desktopLogoSize);
            footerSectionSteps.verifyTitleColor(titleColor);
            footerSectionSteps.verifyTitleFont(titleFont);
            footerSectionSteps.verifyTitleSize(titleSize);
            footerSectionSteps.verifyBodyColor(bodyColor);
            footerSectionSteps.verifyBodyFont(bodyFont);
            footerSectionSteps.verifyBodySize(bodySize);
            footerSectionSteps.verifyBorderTopColor(borderTopColor, type);
            footerSectionSteps.verifyBorderTopSize(borderTopSize, type);
            footerSectionSteps.verifyBorderBottomColor(borderBottomColor, type);
            footerSectionSteps.verifyBorderBottomSize(borderBottomSize, type);
            footerSectionSteps.verifyMinHeight(minHeight, type);
            footerSectionSteps.verifyMaxWidth(maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                footerSectionSteps.verifyCustomWidth(customWidth);
            }

        }
    }

    @And("change Gallery Image section  {string}")
    public void changeGalleryImageSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean noSpacing = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "No spacing"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String imageRatio = SessionData.getDataTbVal(dataTable, row, "Image ratio");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Gallery image");
            if (index == 1) {
                landingPageSteps.inputTextBox("Title", title);
                landingPageSteps.checkCheckBox("No spacing", noSpacing);
                landingPageSteps.openVisualSetting("Visual settings");
                landingPageSteps.selectDdlWithLabel("Type", type);
                if (type.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputColor("Custom color", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    landingPageSteps.inputColor("Start color", startColor);
                    landingPageSteps.inputColor("End color", endColor);
                    landingPageSteps.selectDdlWithLabel("Direction", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                    landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
                }
                landingPageSteps.inputColor("Title color", titleColor);
                landingPageSteps.changeFont("Title font", titleFont);
                landingPageSteps.inputTextBox("Title size", titleSize);
                landingPageSteps.inputColor("Border top color", borderTopColor);
                landingPageSteps.inputTextBox("Border top size", borderTopSize);
                landingPageSteps.inputColor("Border bottom color", borderBottomColor);
                landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
                landingPageSteps.selectDropListWithLable("Image ratio", imageRatio);
                landingPageSteps.selectDdlWithLabel("Alignment", alignment);
                landingPageSteps.selectDdlWithLabel("Min height", minHeight);
                landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
                if (maxWidth.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputTextBox("Custom width", customWidth);
                }
                landingPageSteps.removeAllContent("Image");
                landingPageSteps.scrollToLabel("Images");
            }
            landingPageSteps.clickAddContent();
            landingPageSteps.opendMenuItem("Image", index);
            landingPageSteps.uploadImageWithLabel("Image", image, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
                themeEditorSteps.closeThemeEditor();
            }
        }

    }

    @And("verify Gallery Image section  {string}")
    public void verifyGalleryImageSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            boolean noSpacing = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "No spacing"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String imageRatio = SessionData.getDataTbVal(dataTable, row, "Image ratio");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            galleryImageSectionsSteps.verifynoSpacing(noSpacing);
            galleryImageSectionsSteps.verifytTitle(title);
            galleryImageSectionsSteps.verifyImage(image);
            galleryImageSectionsSteps.verifyType(type);
            galleryImageSectionsSteps.verifyCustomColor(customColor);
            galleryImageSectionsSteps.verifyStartColor(startColor);
            galleryImageSectionsSteps.verifyEndColor(endColor);
            galleryImageSectionsSteps.verifyDirection(direction);
            galleryImageSectionsSteps.verifyImageBackground(imageBackground);
            galleryImageSectionsSteps.verifyColorOverlayBackground(colorOverlayBackground);
            galleryImageSectionsSteps.verifyTitleColor(titleColor);
            galleryImageSectionsSteps.verifyTitleFont(titleFont);
            galleryImageSectionsSteps.verifyTitleSize(titleSize);
            galleryImageSectionsSteps.verifyBorderTopColor(borderTopColor);
            galleryImageSectionsSteps.verifyBorderTopSize(borderTopSize);
            galleryImageSectionsSteps.verifyBorderBottomColor(borderBottomColor);
            galleryImageSectionsSteps.verifyBorderBottomSize(borderBottomSize);
            galleryImageSectionsSteps.verifyImageRatio(imageRatio);
            galleryImageSectionsSteps.verifyAlignment(alignment);
            galleryImageSectionsSteps.verifyMinHeight(minHeight);
            galleryImageSectionsSteps.verifyMaxWidth(maxWidth);
            galleryImageSectionsSteps.verifyCustomWidth(customWidth);
        }
    }

    @And("change Countdown Timer section  {string}")
    public void changeCountdownTimerSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String endDate = SessionData.getDataTbVal(dataTable, row, "End date");
            boolean showInWeek = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show in week"));
            boolean hideOnTimeout = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Hide on timeout"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String timerColor = SessionData.getDataTbVal(dataTable, row, "Timer color");
            String timerFont = SessionData.getDataTbVal(dataTable, row, "Timer font");
            String timerSize = SessionData.getDataTbVal(dataTable, row, "Timer size");
            String alignmentTimer = SessionData.getDataTbVal(dataTable, row, "Alignment Timer");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Countdown Timer");
            landingPageSteps.inputTextBox("Heading", heading);
            landingPageSteps.inputTextBox("Title", title);
            landingPageSteps.inputTextArea("Body", body);
            landingPageSteps.inputEndDate(endDate);
            landingPageSteps.checkCheckBox("Show in week", showInWeek);
            landingPageSteps.checkCheckBox("Hide on timeout", hideOnTimeout);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Media")) {
                themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Heading  color", headingColor);
            landingPageSteps.changeFont("Heading  font", headingFont);
            landingPageSteps.inputTextBox("Heading  size", headingSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading);
            landingPageSteps.inputColor("Timer  color", timerColor);
            landingPageSteps.changeFont("Timer  font", timerFont);
            landingPageSteps.inputTextBox("Timer  size", timerSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentTimer);
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentTitle);
            landingPageSteps.inputColor("Body  color", bodyColor);
            landingPageSteps.changeFont("Body  font", bodyFont);
            landingPageSteps.inputTextBox("Body  size", bodySize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentBody);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Position", position);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            landingPageSteps.inputTextBox("Custom width", customWidth);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();

        }
    }

    @And("verify Countdown Timer section  {string}")
    public void verifyCountdownTimerSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String endDate = SessionData.getDataTbVal(dataTable, row, "End date");
            boolean showInWeek = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show in week"));
            boolean hideOnTimeout = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Hide on timeout"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String timerColor = SessionData.getDataTbVal(dataTable, row, "Timer color");
            String timerFont = SessionData.getDataTbVal(dataTable, row, "Timer font");
            String timerSize = SessionData.getDataTbVal(dataTable, row, "Timer size");
            String alignmentTimer = SessionData.getDataTbVal(dataTable, row, "Alignment Timer");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            timerCountdownSteps.verifyHeading(heading);
            timerCountdownSteps.verifyTitle(title);
            timerCountdownSteps.verifyBody(body);
            timerCountdownSteps.verifyEndDate(endDate);
            timerCountdownSteps.verifyShowInWeek(showInWeek);
            timerCountdownSteps.verifyHideOnTimeout(hideOnTimeout);
            timerCountdownSteps.verifyType(type);
            timerCountdownSteps.verifyCustomColor(customColor);
            timerCountdownSteps.verifyStartColor(startColor);
            timerCountdownSteps.verifyEndColor(endColor);
            timerCountdownSteps.verifyDirection(direction);
            timerCountdownSteps.verifyImageBackground(imageBackground);
            timerCountdownSteps.verifyColorOverlayBackground(colorOverlayBackground);
            timerCountdownSteps.verifyHeadingColor(headingColor);
            timerCountdownSteps.verifyHeadingFont(headingFont);
            timerCountdownSteps.verifyHeadingSize(headingSize);
            timerCountdownSteps.verifyAlignmentHeading(alignmentHeading);
            timerCountdownSteps.verifyTimerColor(timerColor);
            timerCountdownSteps.verifyTimerFont(timerFont);
            timerCountdownSteps.verifyTimerSize(timerSize);
            timerCountdownSteps.verifyAlignmentTimer(alignmentTimer);
            timerCountdownSteps.verifyTitleColor(titleColor);
            timerCountdownSteps.verifyTitleFont(titleFont);
            timerCountdownSteps.verifyTitleSize(titleSize);
            timerCountdownSteps.verifyAlignmentTitle(alignmentTitle);
            timerCountdownSteps.verifyBodyColor(bodyColor);
            timerCountdownSteps.verifyBodyFont(bodyFont);
            timerCountdownSteps.verifyBodySize(bodySize);
            timerCountdownSteps.verifyAlignmentBody(alignmentBody);
            timerCountdownSteps.verifyBorderTopColor(borderTopColor);
            timerCountdownSteps.verifyBorderTopSize(borderTopSize);
            timerCountdownSteps.verifyBorderBottomColor(borderBottomColor);
            timerCountdownSteps.verifyBorderBottomSize(borderBottomSize);
            timerCountdownSteps.verifyPosition(position);
            timerCountdownSteps.verifyMinHeight(minHeight);
            timerCountdownSteps.verifyMaxWidth(maxWidth);
            timerCountdownSteps.verifyCustomWidth(customWidth);

        }
    }

    @And("change Contact Form section  {string}")
    public void changeContactFormSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String namePlaceholder = SessionData.getDataTbVal(dataTable, row, "Name placeholder");
            boolean requireNameField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Name field"));
            String emailPlaceholder = SessionData.getDataTbVal(dataTable, row, "Email placeholder");
            boolean requireEmailField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Email field"));
            String phonePlaceholder = SessionData.getDataTbVal(dataTable, row, "Phone placeholder");
            boolean requirePhoneField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Phone field"));
            String addressPlaceholder = SessionData.getDataTbVal(dataTable, row, "Address placeholder");
            boolean requireAddressField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Address field"));
            String messagePlaceholder = SessionData.getDataTbVal(dataTable, row, "Message placeholder");
            boolean requireMessageField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Message field"));
            String submitButtonLabel = SessionData.getDataTbVal(dataTable, row, "Submit button label");
            String submitTo = SessionData.getDataTbVal(dataTable, row, "Submit to");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String formFieldBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Form field background color");
            String placeholderColor = SessionData.getDataTbVal(dataTable, row, "Placeholder color");
            String inputColor = SessionData.getDataTbVal(dataTable, row, "Input color");
            String inputSize = SessionData.getDataTbVal(dataTable, row, "Input size");
            String inputFont = SessionData.getDataTbVal(dataTable, row, "Input font");
            String buttonPosition = SessionData.getDataTbVal(dataTable, row, "Button position");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");

            landingPageSteps.openSection("Contact Form");
            landingPageSteps.inputTextBox("Title", title);
            landingPageSteps.inputTextContentWithIframe(body, 1);
            landingPageSteps.inputTextBox("Name placeholder", namePlaceholder);
            landingPageSteps.checkCheckBox("Require Name field", requireNameField);
            landingPageSteps.inputTextBox("Email placeholder", emailPlaceholder);
            landingPageSteps.checkCheckBox("Require Email field", requireEmailField);
            landingPageSteps.inputTextBox("Phone placeholder", phonePlaceholder);
            landingPageSteps.checkCheckBox("Require Phone field", requirePhoneField);
            landingPageSteps.inputTextBox("Address placeholder", addressPlaceholder);
            landingPageSteps.checkCheckBox("Require Address field", requireAddressField);
            landingPageSteps.inputTextBox("Message placeholder", messagePlaceholder);
            landingPageSteps.checkCheckBox("Require Message field", requireMessageField);
            landingPageSteps.inputTextBox("Submit button label", submitButtonLabel);
            landingPageSteps.inputTextBox("Submit to", submitTo);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Media")) {
                themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentTitle, 1);
            landingPageSteps.inputColor("Body color", bodyColor);
            landingPageSteps.changeFont("Body font", bodyFont);
            landingPageSteps.inputTextBox("Body size", bodySize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentBody, 2);
            landingPageSteps.inputColor("Field background color", formFieldBackgroundColor);
            landingPageSteps.inputColor("Input color", inputColor);
            landingPageSteps.inputTextBox("Input size", inputSize);
            landingPageSteps.changeFont("Input font", inputFont);
            landingPageSteps.selectDdlWithLabel("Button position", buttonPosition);
            landingPageSteps.inputColor("Button background color", buttonBackgroundColor);
            landingPageSteps.inputColor("Button border color", buttonBorderColor);
            landingPageSteps.inputColor("Button label color", buttonLabelColor);
            landingPageSteps.changeFont("Button label font", buttonLabelFont);
            landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Position", position);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }
    }

    @And("verify Contact Form section  {string}")
    public void verifyContactFormSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String namePlaceholder = SessionData.getDataTbVal(dataTable, row, "Name placeholder");
            boolean requireNameField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Name field"));
            String emailPlaceholder = SessionData.getDataTbVal(dataTable, row, "Email placeholder");
            boolean requireEmailField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Email field"));
            String phonePlaceholder = SessionData.getDataTbVal(dataTable, row, "Phone placeholder");
            boolean requirePhoneField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Phone field"));
            String addressPlaceholder = SessionData.getDataTbVal(dataTable, row, "Address placeholder");
            boolean requireAddressField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Address field"));
            String messagePlaceholder = SessionData.getDataTbVal(dataTable, row, "Message placeholder");
            boolean requireMessageField = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Require Message field"));
            String submitButtonLabel = SessionData.getDataTbVal(dataTable, row, "Submit button label");
            String submitTo = SessionData.getDataTbVal(dataTable, row, "Submit to");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String formFieldBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Form field background color");
            String placeholderColor = SessionData.getDataTbVal(dataTable, row, "Placeholder color");
            String inputColor = SessionData.getDataTbVal(dataTable, row, "Input color");
            String inputSize = SessionData.getDataTbVal(dataTable, row, "Input size");
            String inputFont = SessionData.getDataTbVal(dataTable, row, "Input font");
            String buttonPosition = SessionData.getDataTbVal(dataTable, row, "Button position");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            formSectionSteps.verifyTitle(title);
            formSectionSteps.verifyBody(body);
            formSectionSteps.verifyNamePlaceholder(namePlaceholder);
            formSectionSteps.verifyRequireNameField(requireNameField);
            formSectionSteps.verifyEmailPlaceholder(emailPlaceholder);
            formSectionSteps.verifyRequireEmailField(requireEmailField);
            formSectionSteps.verifyPhonePlaceholder(phonePlaceholder);
            formSectionSteps.verifyRequirePhoneField(requirePhoneField);
            formSectionSteps.verifyAddressPlaceholder(addressPlaceholder);
            formSectionSteps.verifyRequireAddressField(requireAddressField);
            formSectionSteps.verifyMessagePlaceholder(messagePlaceholder);
            formSectionSteps.verifyRequireMessageField(requireMessageField);
            formSectionSteps.verifySubmitButtonLabel(submitButtonLabel);
            formSectionSteps.verifySubmitTo(submitTo);
            formSectionSteps.verifyType(type);
            if (type.equalsIgnoreCase("Custom")) {
                formSectionSteps.verifyCustomColor(customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                formSectionSteps.verifyStartColor(startColor);
                formSectionSteps.verifyEndColor(endColor);
                formSectionSteps.verifyDirection(direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                formSectionSteps.verifyImageBackground(imageBackground);
                formSectionSteps.verifyColorOverlayBackground(colorOverlayBackground);
            }
            formSectionSteps.verifyTitleColor(titleColor, title);
            formSectionSteps.verifyTitleFont(titleFont, title);
            formSectionSteps.verifyTitleSize(titleSize, title);
            formSectionSteps.verifyAlignmentTitle(alignmentTitle, title);
            formSectionSteps.verifyBodyColor(bodyColor, body);
            formSectionSteps.verifyBodyFont(bodyFont, body);
            formSectionSteps.verifyBodySize(bodySize, body);
            formSectionSteps.verifyAlignmentBody(alignmentBody, body);
            formSectionSteps.verifyFormFieldBackgroundColor(formFieldBackgroundColor);
            formSectionSteps.verifyPlaceholderColor(placeholderColor);
            formSectionSteps.verifyInputColor(inputColor);
            formSectionSteps.verifyInputSize(inputSize);
            formSectionSteps.verifyInputFont(inputFont);
            formSectionSteps.verifyButtonPosition(buttonPosition, submitButtonLabel);
            formSectionSteps.verifyButtonBackgroundColor(buttonBackgroundColor, submitButtonLabel);
            formSectionSteps.verifyButtonBorderColor(buttonBorderColor, submitButtonLabel);
            formSectionSteps.verifyButtonLabelColor(buttonLabelColor, submitButtonLabel);
            formSectionSteps.verifyButtonLabelFont(buttonLabelFont, submitButtonLabel);
            formSectionSteps.verifyButtonLabelSize(buttonLabelSize, submitButtonLabel);
            formSectionSteps.verifyBorderTopColor(borderTopColor);
            formSectionSteps.verifyBorderTopSize(borderTopSize);
            formSectionSteps.verifyBorderBottomColor(borderBottomColor);
            formSectionSteps.verifyBorderBottomSize(borderBottomSize);
            formSectionSteps.verifyPosition(position);
            formSectionSteps.verifyMinHeight(minHeight);
            formSectionSteps.verifyMaxWidth(maxWidth);
            formSectionSteps.verifyCustomWidth(customWidth);
        }
    }

    @And("change Featured Promotion section  {string}")
    public void changeFeaturedPromotionSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String icon = SessionData.getDataTbVal(dataTable, row, "Icon");
            String backgroundImage = SessionData.getDataTbVal(dataTable, row, "Background Image");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Featured Promotion");
            if (index == 1) {
                landingPageSteps.inputTextBox("Heading", heading);
                landingPageSteps.openVisualSetting("Visual settings");
                landingPageSteps.selectDdlWithLabel("Type", type);
                if (type.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputColor("Custom color", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    landingPageSteps.inputColor("Start color", startColor);
                    landingPageSteps.inputColor("End color", endColor);
                    landingPageSteps.selectDdlWithLabel("Direction", direction);
                }
                if (type.equalsIgnoreCase("Media")) {
                    themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                    landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
                }
                landingPageSteps.inputColor("Heading  color", headingColor);
                landingPageSteps.changeFont("Heading  font", headingFont);
                landingPageSteps.inputTextBox("Heading  size", headingSize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading);
                landingPageSteps.inputColor("Title color", titleColor);
                landingPageSteps.changeFont("Title font", titleFont);
                landingPageSteps.inputTextBox("Title size", titleSize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentTitle);
                landingPageSteps.inputColor("Body  color", bodyColor);
                landingPageSteps.changeFont("Body  font", bodyFont);
                landingPageSteps.inputTextBox("Body  size", bodySize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentBody);
                landingPageSteps.inputColor("Border top color", borderTopColor);
                landingPageSteps.inputTextBox("Border top size", borderTopSize);
                landingPageSteps.inputColor("Border bottom color", borderBottomColor);
                landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
                landingPageSteps.selectDdlWithLabel("Position", position);
                landingPageSteps.selectDdlWithLabel("Alignment", alignment);
                landingPageSteps.selectDdlWithLabel("Min height", minHeight);
                landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
                landingPageSteps.inputTextBox("Custom width", customWidth);
                landingPageSteps.removeAllContent("Feature");
                landingPageSteps.scrollToLabel("Features");
            }
            landingPageSteps.clickAddContent();
            landingPageSteps.opendMenuItem("Feature", index);
            landingPageSteps.uploadImageWithLabel("Icon", icon, index);
            landingPageSteps.uploadImageWithLabel("Background Image", backgroundImage, index);
            landingPageSteps.inputTextBox("Title", title, index);
            landingPageSteps.inputTextContentWithIframe(body, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
                themeEditorSteps.closeThemeEditor();
            }
        }

    }

    @And("verify Featured Promotion section  {string}")
    public void verifyFeaturedPromotionSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String icon = SessionData.getDataTbVal(dataTable, row, "Icon");
            String backgroundImage = SessionData.getDataTbVal(dataTable, row, "Background Image");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String alignmentTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Title");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String position = SessionData.getDataTbVal(dataTable, row, "Position");
            String alignment = SessionData.getDataTbVal(dataTable, row, "Alignment");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (index == 1) {
                featuredPromotionSectionSteps.verifyHeading(heading);
                featuredPromotionSectionSteps.verifyType(type);
                if (type.equalsIgnoreCase("Custom")) {
                    featuredPromotionSectionSteps.verifyCustomColor(customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    featuredPromotionSectionSteps.verifyStartColor(startColor);
                    featuredPromotionSectionSteps.verifyEndColor(endColor);
                    featuredPromotionSectionSteps.verifyDirection(direction);
                }
                if (type.equalsIgnoreCase("Media")) {
                    featuredPromotionSectionSteps.verifyImageBackground(imageBackground);
                    featuredPromotionSectionSteps.verifyColorOverlayBackground(colorOverlayBackground);
                }
                featuredPromotionSectionSteps.verifyHeadingColor(headingColor);
                featuredPromotionSectionSteps.verifyHeadingFont(headingFont);
                featuredPromotionSectionSteps.verifyHeadingSize(headingSize);
                featuredPromotionSectionSteps.verifyAlignmentHeading(alignmentHeading);
                featuredPromotionSectionSteps.verifyTitleColor(titleColor);
                featuredPromotionSectionSteps.verifyTitleFont(titleFont);
                featuredPromotionSectionSteps.verifyTitleSize(titleSize);
                featuredPromotionSectionSteps.verifyAlignmentTitle(alignmentTitle);
                featuredPromotionSectionSteps.verifyBodyColor(bodyColor);
                featuredPromotionSectionSteps.verifyBodyFont(bodyFont);
                featuredPromotionSectionSteps.verifyBodySize(bodySize);
                featuredPromotionSectionSteps.verifyAlignmentBody(alignmentBody);
                featuredPromotionSectionSteps.verifyBorderTopColor(borderTopColor);
                featuredPromotionSectionSteps.verifyBorderTopSize(borderTopSize);
                featuredPromotionSectionSteps.verifyBorderBottomColor(borderBottomColor);
                featuredPromotionSectionSteps.verifyBorderBottomSize(borderBottomSize);
                featuredPromotionSectionSteps.verifyPosition(position);
                featuredPromotionSectionSteps.verifyAlignment(alignment);
                featuredPromotionSectionSteps.verifyMinHeight(minHeight);
                featuredPromotionSectionSteps.verifyMaxWidth(maxWidth);
                featuredPromotionSectionSteps.verifyCustomWidth(customWidth);
            }
            featuredPromotionSectionSteps.verifyIcon(icon, index);
            featuredPromotionSectionSteps.verifyNackgroundImage(backgroundImage, index);
            featuredPromotionSectionSteps.verifyTitle(title, index);
            featuredPromotionSectionSteps.verifyBody(body, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            }
        }
    }

    @And("change Trust Indicators section  {string}")
    public void changeTrustIndicatorsSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean showIndicatorBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show indicator background"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show ratings"));
            String customerName = SessionData.getDataTbVal(dataTable, row, "Customer name");
            boolean showAvatar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show avatar"));
            String avatarImage = SessionData.getDataTbVal(dataTable, row, "Avatar image");
            String date = SessionData.getDataTbVal(dataTable, row, "Date");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String backgroundColor = SessionData.getDataTbVal(dataTable, row, "Background color");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String bodyAlignment = SessionData.getDataTbVal(dataTable, row, "Body Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String rattingAlignment = SessionData.getDataTbVal(dataTable, row, "Ratting Alignment");
            String customerNameColor = SessionData.getDataTbVal(dataTable, row, "Customer name color");
            String customerNameFont = SessionData.getDataTbVal(dataTable, row, "Customer name font");
            String customerNameSize = SessionData.getDataTbVal(dataTable, row, "Customer name size");
            String dateColor = SessionData.getDataTbVal(dataTable, row, "Date color");
            String dateFont = SessionData.getDataTbVal(dataTable, row, "Date font");
            String dateSize = SessionData.getDataTbVal(dataTable, row, "Date size");
            String dateAlignment = SessionData.getDataTbVal(dataTable, row, "Date Alignment");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String pagingColor = SessionData.getDataTbVal(dataTable, row, "Paging color");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Featured Promotion");
            if (index == 1) {
                landingPageSteps.checkCheckBox("Show indicator background", showIndicatorBackground);
                landingPageSteps.inputTextBox("Heading", heading);
                landingPageSteps.openVisualSetting("Visual settings");
                landingPageSteps.selectDdlWithLabel("Type", type);
                if (type.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputColor("Custom color", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    landingPageSteps.inputColor("Start color", startColor);
                    landingPageSteps.inputColor("End color", endColor);
                    landingPageSteps.selectDdlWithLabel("Direction", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                    landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
                }
                landingPageSteps.inputColor("Heading  color", headingColor);
                landingPageSteps.changeFont("Heading  font", headingFont);
                landingPageSteps.inputTextBox("Heading  size", headingSize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading);
                landingPageSteps.inputColor("Background color", backgroundColor);
                landingPageSteps.inputColor("Title color", titleColor);
                landingPageSteps.changeFont("Title font", titleFont);
                landingPageSteps.inputTextBox("Title size", titleSize);
                landingPageSteps.selectDdlWithLabel("Alignment", titleAlignment);
                landingPageSteps.inputColor("Body  color", bodyColor);
                landingPageSteps.changeFont("Body  font", bodyFont);
                landingPageSteps.inputTextBox("Body  size", bodySize);
                landingPageSteps.selectDdlWithLabel("Alignment", bodyAlignment);
                landingPageSteps.inputColor("Border top color", borderTopColor);
                landingPageSteps.inputTextBox("Border top size", borderTopSize);
                landingPageSteps.inputColor("Rating icon color", ratingIconColor);
                landingPageSteps.inputTextBox("Rating icon size", ratingIconSize);
                landingPageSteps.selectDdlWithLabel("Alignment", rattingAlignment);
                landingPageSteps.inputColor("Customer name color", customerNameColor);
                landingPageSteps.changeFont("Customer name font", customerNameFont);
                landingPageSteps.inputTextBox("Customer name size", customerNameSize);
                landingPageSteps.inputColor("Date color", dateColor);
                landingPageSteps.changeFont("Date font", dateFont);
                landingPageSteps.inputTextBox("Date size", dateSize);
                landingPageSteps.selectDdlWithLabel("Alignment", dateAlignment);
                landingPageSteps.inputColor("Border bottom color", borderBottomColor);
                landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
                landingPageSteps.inputColor("Paging color", pagingColor);
                landingPageSteps.selectDdlWithLabel("Min height", minHeight);
                landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
                landingPageSteps.inputTextBox("Custom width", customWidth);
                landingPageSteps.removeAllContent("Indicator");
                landingPageSteps.scrollToLabel("Trust Indicators");
            }
            landingPageSteps.clickAddContent();
            landingPageSteps.opendMenuItem("Indicator", index);
            landingPageSteps.inputTextBox("Title", title, index);
            landingPageSteps.inputTextContentWithIframe(body, index);
            landingPageSteps.checkCheckBox("Show ratings", showRatings, index);
            landingPageSteps.inputTextBox("Customer name", customerName, index);
            landingPageSteps.checkCheckBox("Show avatar", showAvatar, index);
            landingPageSteps.uploadImageWithLabel("Avatar image", avatarImage, index);
            landingPageSteps.inputTextBox("Date", date, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
                themeEditorSteps.closeThemeEditor();
            }
        }
    }

    @And("verify Trust Indicators section  {string}")
    public void verifyTrustIndicatorsSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean showIndicatorBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show indicator background"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show ratings"));
            String customerName = SessionData.getDataTbVal(dataTable, row, "Customer name");
            boolean showAvatar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show avatar"));
            String avatarImage = SessionData.getDataTbVal(dataTable, row, "Avatar image");
            String date = SessionData.getDataTbVal(dataTable, row, "Date");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String backgroundColor = SessionData.getDataTbVal(dataTable, row, "Background color");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String bodyAlignment = SessionData.getDataTbVal(dataTable, row, "Body Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String rattingAlignment = SessionData.getDataTbVal(dataTable, row, "Ratting Alignment");
            String customerNameColor = SessionData.getDataTbVal(dataTable, row, "Customer name color");
            String customerNameFont = SessionData.getDataTbVal(dataTable, row, "Customer name font");
            String customerNameSize = SessionData.getDataTbVal(dataTable, row, "Customer name size");
            String dateColor = SessionData.getDataTbVal(dataTable, row, "Date color");
            String dateFont = SessionData.getDataTbVal(dataTable, row, "Date font");
            String dateSize = SessionData.getDataTbVal(dataTable, row, "Date size");
            String dateAlignment = SessionData.getDataTbVal(dataTable, row, "Date Alignment");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String pagingColor = SessionData.getDataTbVal(dataTable, row, "Paging color");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (index == 1) {
                trustIndicatorSteps.verifyShowIndicatorBackground(showIndicatorBackground);
                trustIndicatorSteps.verifyHeading(heading);
                trustIndicatorSteps.verifyType(type);
                if (type.equalsIgnoreCase("Custom")) {
                    trustIndicatorSteps.verifyCustomColor(customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    trustIndicatorSteps.verifyStartColor(startColor);
                    trustIndicatorSteps.verifyEndColor(endColor);
                    trustIndicatorSteps.verifyDirection(direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    trustIndicatorSteps.verifyImageBackground(imageBackground);
                    trustIndicatorSteps.verifyColorOverlayBackground(colorOverlayBackground);
                }
                trustIndicatorSteps.verifyHeadingColor(headingColor);
                trustIndicatorSteps.verifyHeadingFont(headingFont);
                trustIndicatorSteps.verifyHeadingSize(headingSize);
                trustIndicatorSteps.verifyAlignmentHeading(alignmentHeading);
                trustIndicatorSteps.verifyBackgroundColor(backgroundColor);
                trustIndicatorSteps.verifyTitleColor(titleColor);
                trustIndicatorSteps.verifyTitleFont(titleFont);
                trustIndicatorSteps.verifyTitleSize(titleSize);
                trustIndicatorSteps.verifyTitleAlignment(titleAlignment);
                trustIndicatorSteps.verifyBodyColor(bodyColor);
                trustIndicatorSteps.verifyBodyFont(bodyFont);
                trustIndicatorSteps.verifyBodySize(bodySize);
                trustIndicatorSteps.verifyBodyAlignment(bodyAlignment);
                trustIndicatorSteps.verifyBorderTopColor(borderTopColor);
                trustIndicatorSteps.verifyBorderTopSize(borderTopSize);
                trustIndicatorSteps.verifyRatingIconColor(ratingIconColor);
                trustIndicatorSteps.verifyRatingIconSize(ratingIconSize);
                trustIndicatorSteps.verifyRattingAlignment(rattingAlignment);
                trustIndicatorSteps.verifycustomerNameColor(customerNameColor);
                trustIndicatorSteps.verifyCustomerNameFont(customerNameFont);
                trustIndicatorSteps.verifyCustomerNameSize(customerNameSize);
                trustIndicatorSteps.verifyDateColor(dateColor);
                trustIndicatorSteps.verifyDateFont(dateFont);
                trustIndicatorSteps.verifydDateSize(dateSize);
                trustIndicatorSteps.verifyDateAlignment(dateAlignment);
                trustIndicatorSteps.verifyBorderBottomColor(borderBottomColor);
                trustIndicatorSteps.verifyBorderBottomSize(borderBottomSize);
                trustIndicatorSteps.verifyPagingColor(pagingColor);
                trustIndicatorSteps.verifyMinHeight(minHeight);
                trustIndicatorSteps.verifyMaxWidth(maxWidth);
                trustIndicatorSteps.verifyCustomWidth(customWidth);
            }
            trustIndicatorSteps.verifyTitle(title, index);
            trustIndicatorSteps.verifyBody(body, index);
            trustIndicatorSteps.verifyShowRatings(showRatings, index);
            trustIndicatorSteps.verifyCustomerName(customerName, index);
            trustIndicatorSteps.verifyShowAvatar(showAvatar, index);
            trustIndicatorSteps.verifyAvatarImage(avatarImage, index);
            trustIndicatorSteps.verifyDate(date, index);
            if (rowKey.equals(nextRowKey)) {
                index++;
            }
        }
    }

    @And("change Feautre Product section {string}")
    public void changeFeautreProductSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Featured Product");
            landingPageSteps.inputTextBox("Heading", heading);
            landingPageSteps.enterProduct("Product", product);
            landingPageSteps.checkCheckBox("Show Title", showTitle);
            if (showTitle) {
                landingPageSteps.inputTextBox("Title", title);
            }
            landingPageSteps.checkCheckBox("Show Price", showPrice);
            landingPageSteps.checkCheckBox("Show Compare-at-price", showCompareAtPrice);
            landingPageSteps.checkCheckBox("Show Sale tag", showSaleTag);
            landingPageSteps.selectDdlWithLabel("Sale type", saleType);
            landingPageSteps.checkCheckBox("Show Ratings", showRatings);
            landingPageSteps.checkCheckBox("Show Variant picker", showVariantPicker);
            landingPageSteps.checkCheckBox("Select variants by clicking their images", isSelectVariantsByClick);
            landingPageSteps.checkCheckBox("Enable color swatches", enableColorSwatches);
            landingPageSteps.checkCheckBox("Show Quantity", showQuantity);
            landingPageSteps.inputTextBox("Default quantity", defaultQuantity);
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Destination", destination);
            landingPageSteps.checkCheckBox("Show Description", showDescription);
            landingPageSteps.checkCheckBox("Show Trust badge", showTrustBadge);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Color overlay", colorOverlayBackground);
            }
            landingPageSteps.inputColor("Heading  color", headingColor);
            landingPageSteps.changeFont("Heading  font", headingFont);
            landingPageSteps.inputTextBox("Heading  size", headingSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading);
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.selectDdlWithLabel("Alignment", titleAlignment);
            landingPageSteps.inputColor("Price color", priceColor);
            landingPageSteps.inputTextBox("Price size", priceSize);
            landingPageSteps.inputColor("Compare-at-price color", compareAtPriceColor);
            landingPageSteps.inputTextBox("Compare-at-price size", compareAtPriceSize);
            landingPageSteps.inputColor("Sale tag background", saleTagBackground);
            landingPageSteps.inputColor("Sale tag label color", saleTagLabelColor);
            landingPageSteps.inputTextBox("Sale tag size", saleTagSize);
            landingPageSteps.changeFont("Price font", priceFont);
            landingPageSteps.selectDdlWithLabel("Alignment", priceAlignment);
            landingPageSteps.inputColor("Rating icon color", ratingIconColor);
            landingPageSteps.inputTextBox("Rating icon size", ratingIconSize);
            landingPageSteps.selectDdlWithLabel("Alignment", ratingAlignment);
            landingPageSteps.inputColor("Variant label color", variantLabelColor);
            landingPageSteps.changeFont("Variant label font", variantLabelFont);
            landingPageSteps.inputTextBox("Variant label size", variantLabelSize);
            landingPageSteps.selectDdlWithLabel("Alignment", variantPickerAlignment);
            landingPageSteps.inputColor("Button background color", buttonBackgroundColor);
            landingPageSteps.inputColor("Button border color", buttonBorderColor);
            landingPageSteps.inputColor("Button label color", buttonLabelColor);
            landingPageSteps.changeFont("Button label font", buttonLabelFont);
            landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
            landingPageSteps.inputColor("Description color", descriptionColor);
            landingPageSteps.changeFont("Description font", descriptionFont);
            landingPageSteps.inputTextBox("Description size", descriptionSize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Product image position", productImagePosition);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }

    }

    @And("verify Feautre Product section  {string}")
    public void verifyFeautreProductSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (!product.isEmpty()) {
                featuredProductSteps.verifyHeading("Featured Product", heading);
                featuredProductSteps.verifyShowTitle("Featured Product", showTitle);
                if (showTitle) {
                    featuredProductSteps.verifyTitle("Featured Product", title, showTitle, product);
                }
                featuredProductSteps.verifyShowPrice("Featured Product", showPrice);
                featuredProductSteps.verifyShowCompareAtPrice("Featured Product", showCompareAtPrice);
                featuredProductSteps.verifyShowSaleTag("Featured Product", showSaleTag);
                featuredProductSteps.verifySaleType("Featured Product", saleType);
                featuredProductSteps.verifyShowRatings("Featured Product", showRatings);
                featuredProductSteps.verifyShowVariantPicker("Featured Product", showVariantPicker);
                featuredProductSteps.verifyShowQuantity("Featured Product", showQuantity);
                featuredProductSteps.verifyDefaultQuantity("Featured Product", defaultQuantity);
                featuredProductSteps.verifyButtonLabel("Featured Product", buttonLabel);
                featuredProductSteps.verifyDestination("Featured Product", destination);
                commonSteps.backToThePreviousScreen();
                featuredProductSteps.verifyShowDescription("Featured Product", showDescription);
                featuredProductSteps.verifyShowTrustBadge("Featured Product", showTrustBadge);
                featuredProductSteps.verifyType("Featured Product", type);
                if (type.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomColor("Featured Product", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    featuredProductSteps.verifyStartColor("Featured Product", startColor);
                    featuredProductSteps.verifyEndColor("Featured Product", endColor);
                    featuredProductSteps.verifyDirection("Featured Product", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    featuredProductSteps.verifyColorOverlayBackground("Featured Product", colorOverlayBackground);
                }
                featuredProductSteps.verifyHeadingColor("Featured Product", headingColor, heading);
                featuredProductSteps.verifyHeadingFont("Featured Product", headingFont, heading);
                featuredProductSteps.verifyHeadingSize("Featured Product", headingSize, heading);
                featuredProductSteps.verifyAlignmentHeading("Featured Product", alignmentHeading, heading);
                featuredProductSteps.verifyTitleColor("Featured Product", titleColor, showTitle);
                featuredProductSteps.verifyTitleFont("Featured Product", titleFont, showTitle);
                featuredProductSteps.verifyTitleSize("Featured Product", titleSize, showTitle);
                featuredProductSteps.verifyTitleAlignment("Featured Product", titleAlignment, showTitle);
                featuredProductSteps.verifyPriceColor("Featured Product", priceColor, showPrice);
                featuredProductSteps.verifyPriceSize("Featured Product", priceSize, showPrice);
                featuredProductSteps.verifyCompareAtPriceColor("Featured Product", compareAtPriceColor, showCompareAtPrice);
                featuredProductSteps.verifyCompareAtPriceSize("Featured Product", compareAtPriceSize, showCompareAtPrice);
                featuredProductSteps.verifySaleTagBackground("Featured Product", saleTagBackground, showSaleTag);
                featuredProductSteps.verifySaleTagLabelColor("Featured Product", saleTagLabelColor, showSaleTag);
                featuredProductSteps.verifySaleTagSize("Featured Product", saleTagSize, showSaleTag);
                featuredProductSteps.verifyPriceFont("Featured Product", priceFont, showPrice);
                featuredProductSteps.verifyPriceAlignment("Featured Product", priceAlignment, showPrice);
                featuredProductSteps.verifyRatingIconColor("Featured Product", ratingIconColor, showRatings);
                featuredProductSteps.verifyRatingIconSize("Featured Product", ratingIconSize, showRatings);
                featuredProductSteps.verifyRatingAlignment("Featured Product", ratingAlignment, showRatings);
                featuredProductSteps.verifyVariantLabelColor("Featured Product", variantLabelColor, showVariantPicker);
                featuredProductSteps.verifyVariantLabelFont("Featured Product", variantLabelFont, showVariantPicker);
                featuredProductSteps.verifyVariantLabelSize("Featured Product", variantLabelSize, showVariantPicker);
                featuredProductSteps.verifyVariantPickerAlignment("Featured Product", variantPickerAlignment, showVariantPicker);
                featuredProductSteps.verifyButtonBackgroundColor("Featured Product", buttonBackgroundColor, buttonLabel);
                featuredProductSteps.verifyButtonBorderColor("Featured Product", buttonBorderColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelColor("Featured Product", buttonLabelColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelFont("Featured Product", buttonLabelFont, buttonLabel);
                featuredProductSteps.verifyButtonLabelSize("Featured Product", buttonLabelSize, buttonLabel);
                featuredProductSteps.verifyDescriptionColor("Featured Product", descriptionColor, showDescription);
                featuredProductSteps.verifyDescriptionFont("Featured Product", descriptionFont, showDescription);
                featuredProductSteps.verifyDescriptionSize("Featured Product", descriptionSize, showDescription);
                featuredProductSteps.verifyBorderTopColor("Featured Product", borderTopColor);
                featuredProductSteps.verifyBorderTopSize("Featured Product", borderTopSize);
                featuredProductSteps.verifyBorderBottomColor("Featured Product", borderBottomColor);
                featuredProductSteps.verifyBorderBottomSize("Featured Product", borderBottomSize);
                featuredProductSteps.verifyProductImagePosition("Featured Product", productImagePosition);
                featuredProductSteps.verifyMinHeight("Featured Product", minHeight);
                featuredProductSteps.verifyMaxWidth("Featured Product", maxWidth);
                if (maxWidth.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomWidth("Featured Product", customWidth);
                }
            } else {
                featuredProductSteps.verifyProductDefault("Featured Product", "EXAMPLE PRODUCT TITLE");
            }
        }
    }

    @And("change Product Image section  {string}")
    public void changeProductImageSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String overlayColorImage = SessionData.getDataTbVal(dataTable, row, "Overlay color Image");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Product + Image");
            landingPageSteps.inputTextBox("Heading", heading);
            landingPageSteps.enterProduct("Product", product);
            landingPageSteps.checkCheckBox("Show Title", showTitle);
            if (showTitle) {
                landingPageSteps.inputTextBox("Title", title);
            }
            landingPageSteps.checkCheckBox("Show Price", showPrice);
            landingPageSteps.checkCheckBox("Show Compare-at-price", showCompareAtPrice);
            landingPageSteps.checkCheckBox("Show Sale tag", showSaleTag);
            landingPageSteps.selectDdlWithLabel("Sale type", saleType);
            landingPageSteps.checkCheckBox("Show Ratings", showRatings);
            landingPageSteps.checkCheckBox("Show Variant picker", showVariantPicker);
            landingPageSteps.checkCheckBox("Select variants by clicking their images", isSelectVariantsByClick);
            landingPageSteps.checkCheckBox("Enable color swatches", enableColorSwatches);
            landingPageSteps.checkCheckBox("Show Quantity", showQuantity);
            landingPageSteps.inputTextBox("Default quantity", defaultQuantity);
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Destination", destination);
            landingPageSteps.checkCheckBox("Show Description", showDescription);
            landingPageSteps.checkCheckBox("Show Trust badge", showTrustBadge);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Overlay color", colorOverlayBackground, 1);
            }
            landingPageSteps.inputColor("Heading  color", headingColor);
            landingPageSteps.changeFont("Heading  font", headingFont);
            landingPageSteps.inputTextBox("Heading  size", headingSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading);
            landingPageSteps.selectDropListWithLable("Ratio", ratio);
            if (!type.equalsIgnoreCase("Image")) {
                landingPageSteps.inputColor("Overlay color", overlayColorImage, 1);
            } else {
                landingPageSteps.inputColor("Overlay color", overlayColorImage, 2);
            }
            landingPageSteps.inputColor("Title color", titleColor);
            landingPageSteps.changeFont("Title font", titleFont);
            landingPageSteps.inputTextBox("Title size", titleSize);
            landingPageSteps.selectDdlWithLabel("Alignment", titleAlignment);
            landingPageSteps.inputColor("Price color", priceColor);
            landingPageSteps.inputTextBox("Price size", priceSize);
            landingPageSteps.inputColor("Compare-at-price color", compareAtPriceColor);
            landingPageSteps.inputTextBox("Compare-at-price size", compareAtPriceSize);
            landingPageSteps.inputColor("Sale tag background", saleTagBackground);
            landingPageSteps.inputColor("Sale tag label color", saleTagLabelColor);
            landingPageSteps.inputTextBox("Sale tag size", saleTagSize);
            landingPageSteps.changeFont("Price font", priceFont);
            landingPageSteps.selectDdlWithLabel("Alignment", priceAlignment);
            landingPageSteps.inputColor("Rating icon color", ratingIconColor);
            landingPageSteps.inputTextBox("Rating icon size", ratingIconSize);
            landingPageSteps.selectDdlWithLabel("Alignment", ratingAlignment);
            landingPageSteps.inputColor("Variant label color", variantLabelColor);
            landingPageSteps.changeFont("Variant label font", variantLabelFont);
            landingPageSteps.inputTextBox("Variant label size", variantLabelSize);
            landingPageSteps.selectDdlWithLabel("Alignment", variantPickerAlignment);
            landingPageSteps.inputColor("Button background color", buttonBackgroundColor);
            landingPageSteps.inputColor("Button border color", buttonBorderColor);
            landingPageSteps.inputColor("Button label color", buttonLabelColor);
            landingPageSteps.changeFont("Button label font", buttonLabelFont);
            landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
            landingPageSteps.inputColor("Description color", descriptionColor);
            landingPageSteps.changeFont("Description font", descriptionFont);
            landingPageSteps.inputTextBox("Description size", descriptionSize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Product image position", productImagePosition);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }
    }

    @And("verify Product Image section  {string}")
    public void verifyProductImageSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String overlayColorImage = SessionData.getDataTbVal(dataTable, row, "Overlay color Image");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (!product.isEmpty()) {
                featuredProductSteps.verifyHeading("Product Image", heading);
                featuredProductSteps.verifyShowTitle("Product Image", showTitle);
                if (showTitle) {
                    featuredProductSteps.verifyTitle("Product Image", title, showTitle, product);
                }
                featuredProductSteps.verifyShowPrice("Product Image", showPrice);
                featuredProductSteps.verifyShowCompareAtPrice("Product Image", showCompareAtPrice);
                featuredProductSteps.verifyShowSaleTag("Product Image", showSaleTag);
                featuredProductSteps.verifySaleType("Product Image", saleType);
                featuredProductSteps.verifyShowRatings("Product Image", showRatings);
                featuredProductSteps.verifyShowVariantPicker("Product Image", showVariantPicker);
                featuredProductSteps.verifyShowQuantity("Product Image", showQuantity);
                featuredProductSteps.verifyDefaultQuantity("Product Image", defaultQuantity);
                featuredProductSteps.verifyButtonLabel("Product Image", buttonLabel);
                featuredProductSteps.verifyDestination("Product Image", destination);
                featuredProductSteps.verifyShowDescription("Product Image", showDescription);
                featuredProductSteps.verifyShowTrustBadge("Product Image", showTrustBadge);
                featuredProductSteps.verifyType("Product Image", type);
                if (type.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomColor("Product Image", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    featuredProductSteps.verifyStartColor("Product Image", startColor);
                    featuredProductSteps.verifyEndColor("Product Image", endColor);
                    featuredProductSteps.verifyDirection("Product Image", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    featuredProductSteps.verifyColorOverlayBackground("Product Image", colorOverlayBackground);
                }
                featuredProductSteps.verifyHeadingColor("Product Image", headingColor, heading);
                featuredProductSteps.verifyHeadingFont("Product Image", headingFont, heading);
                featuredProductSteps.verifyHeadingSize("Product Image", headingSize, heading);
                featuredProductSteps.verifyAlignmentHeading("Product Image", alignmentHeading, heading);
                productImageSteps.verifyRatio(ratio);
                productImageSteps.verifyOverlayColorImage(overlayColorImage);
                featuredProductSteps.verifyTitleColor("Product Image", titleColor, showTitle);
                featuredProductSteps.verifyTitleFont("Product Image", titleFont, showTitle);
                featuredProductSteps.verifyTitleSize("Product Image", titleSize, showTitle);
                featuredProductSteps.verifyTitleAlignment("Product Image", titleAlignment, showTitle);
                featuredProductSteps.verifyPriceColor("Product Image", priceColor, showPrice);
                featuredProductSteps.verifyPriceSize("Product Image", priceSize, showPrice);
                featuredProductSteps.verifyCompareAtPriceColor("Product Image", compareAtPriceColor, showCompareAtPrice);
                featuredProductSteps.verifyCompareAtPriceSize("Product Image", compareAtPriceSize, showCompareAtPrice);
                featuredProductSteps.verifySaleTagBackground("Product Image", saleTagBackground, showSaleTag);
                featuredProductSteps.verifySaleTagLabelColor("Product Image", saleTagLabelColor, showSaleTag);
                featuredProductSteps.verifySaleTagSize("Product Image", saleTagSize, showSaleTag);
                featuredProductSteps.verifyPriceFont("Product Image", priceFont, showPrice);
                featuredProductSteps.verifyPriceAlignment("Product Image", priceAlignment, showPrice);
                featuredProductSteps.verifyRatingIconColor("Product Image", ratingIconColor, showRatings);
                featuredProductSteps.verifyRatingIconSize("Product Image", ratingIconSize, showRatings);
                featuredProductSteps.verifyRatingAlignment("Product Image", ratingAlignment, showRatings);
                featuredProductSteps.verifyVariantLabelColor("Product Image", variantLabelColor, showVariantPicker);
                featuredProductSteps.verifyVariantLabelFont("Product Image", variantLabelFont, showVariantPicker);
                featuredProductSteps.verifyVariantLabelSize("Product Image", variantLabelSize, showVariantPicker);
                featuredProductSteps.verifyVariantPickerAlignment("Product Image", variantPickerAlignment, showVariantPicker);
                featuredProductSteps.verifyButtonBackgroundColor("Product Image", buttonBackgroundColor, buttonLabel);
                featuredProductSteps.verifyButtonBorderColor("Product Image", buttonBorderColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelColor("Product Image", buttonLabelColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelFont("Product Image", buttonLabelFont, buttonLabel);
                featuredProductSteps.verifyButtonLabelSize("Product Image", buttonLabelSize, buttonLabel);
                featuredProductSteps.verifyDescriptionColor("Product Image", descriptionColor, showDescription);
                featuredProductSteps.verifyDescriptionFont("Product Image", descriptionFont, showDescription);
                featuredProductSteps.verifyDescriptionSize("Product Image", descriptionSize, showDescription);
                featuredProductSteps.verifyBorderTopColor("Product Image", borderTopColor);
                featuredProductSteps.verifyBorderTopSize("Product Image", borderTopSize);
                featuredProductSteps.verifyBorderBottomColor("Product Image", borderBottomColor);
                featuredProductSteps.verifyBorderBottomSize("Product Image", borderBottomSize);
                productImageSteps.verifyProductImagePosition(productImagePosition);
                featuredProductSteps.verifyMinHeight("Product Image", minHeight);
                featuredProductSteps.verifyMaxWidth("Product Image", maxWidth);
                if (maxWidth.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomWidth("Product Image", customWidth);
                }
            }
        }
    }

    @And("change Product Rich Text section  {string}")
    public void changeProductRichTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String richTextTitle = SessionData.getDataTbVal(dataTable, row, "Rich text title");
            String richTextBody = SessionData.getDataTbVal(dataTable, row, "Rich text body");
            String typeButton = SessionData.getDataTbVal(dataTable, row, "Type button");
            String buttonLabelRichText = SessionData.getDataTbVal(dataTable, row, "Button label Rich text");
            String buttonLinkRichText = SessionData.getDataTbVal(dataTable, row, "Button link Rich text");
            String linkLabelRichText = SessionData.getDataTbVal(dataTable, row, "Link label Rich text");
            String linkRichText = SessionData.getDataTbVal(dataTable, row, "Link Rich text");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColorRichText = SessionData.getDataTbVal(dataTable, row, "Title color Rich text");
            String titleFontRichText = SessionData.getDataTbVal(dataTable, row, "Title font Rich text");
            String titleSizeRichText = SessionData.getDataTbVal(dataTable, row, "Title size Rich text");
            String alignmentRichTextTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text title");
            String bodyColorRichText = SessionData.getDataTbVal(dataTable, row, "Body color Rich text");
            String bodyFontRichText = SessionData.getDataTbVal(dataTable, row, "Body font Rich text");
            String bodySizeRichText = SessionData.getDataTbVal(dataTable, row, "Body size Rich text");
            String alignmentRichTextBody = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text body");
            String buttonColorRichText = SessionData.getDataTbVal(dataTable, row, "Button color Rich text");
            String buttonBorderColorRichText = SessionData.getDataTbVal(dataTable, row, "Button border color Rich text");
            String buttonLabelColorRichText = SessionData.getDataTbVal(dataTable, row, "Button label color Rich text");
            String buttonLabelFontRichText = SessionData.getDataTbVal(dataTable, row, "Button label font Rich text");
            String buttonLabelSizeRichText = SessionData.getDataTbVal(dataTable, row, "Button label size Rich text");
            String alignmentRichTextButton = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text button");
            String linkColorRichText = SessionData.getDataTbVal(dataTable, row, "Link color Rich text");
            String linkFontRichText = SessionData.getDataTbVal(dataTable, row, "Link font Rich text");
            String linkSizeRichText = SessionData.getDataTbVal(dataTable, row, "Link size Rich text");
            String alignmentRichTextLink = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text link");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Product + Rich Text");
            landingPageSteps.inputTextBox("Heading", heading);
            landingPageSteps.inputTextBox("Rich text title", richTextTitle);
            landingPageSteps.inputTextContentWithIframe(richTextBody, 1);
            landingPageSteps.selectDdlWithLabel("Type", typeButton, 1);
            if (typeButton.equalsIgnoreCase("Button")) {
                landingPageSteps.inputTextBox("Button label", buttonLabelRichText);
                landingPageSteps.inputTextBox("Button link", buttonLinkRichText);
            } else {
                landingPageSteps.inputTextBox("Link label", linkLabelRichText, 1);
                landingPageSteps.inputTextBox("Link", linkRichText);
            }
            landingPageSteps.enterProduct("Product", product);
            landingPageSteps.checkCheckBox("Show Title", showTitle);
            if (showTitle) {
                landingPageSteps.inputTextBox("Title", title);
            }
            landingPageSteps.checkCheckBox("Show Price", showPrice);
            landingPageSteps.checkCheckBox("Show Compare-at-price", showCompareAtPrice);
            landingPageSteps.checkCheckBox("Show Sale tag", showSaleTag);
            landingPageSteps.selectDdlWithLabel("Sale type", saleType);
            landingPageSteps.checkCheckBox("Show Ratings", showRatings);
            landingPageSteps.checkCheckBox("Show Variant picker", showVariantPicker);
            landingPageSteps.checkCheckBox("Select variants by clicking their images", isSelectVariantsByClick);
            landingPageSteps.checkCheckBox("Enable color swatches", enableColorSwatches);
            landingPageSteps.checkCheckBox("Show Quantity", showQuantity);
            landingPageSteps.inputTextBox("Default quantity", defaultQuantity);
            if (typeButton.equalsIgnoreCase("Button")) {
                landingPageSteps.inputTextBox("Button label", buttonLabel, 2);
            } else {
                landingPageSteps.inputTextBox("Button label", buttonLabel, 1);
            }
            landingPageSteps.inputTextBox("Destination", destination);
            landingPageSteps.checkCheckBox("Show Description", showDescription);
            landingPageSteps.checkCheckBox("Show Trust badge", showTrustBadge);
            landingPageSteps.openVisualSetting("Visual settings");
            landingPageSteps.selectDdlWithLabel("Type", type);
            if (type.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputColor("Custom color", customColor);
            }
            if (type.equalsIgnoreCase("Gradient")) {
                landingPageSteps.inputColor("Start color", startColor);
                landingPageSteps.inputColor("End color", endColor);
                landingPageSteps.selectDdlWithLabel("Direction", direction);
            }
            if (type.equalsIgnoreCase("Image")) {
                themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                landingPageSteps.inputColor("Overlay color", colorOverlayBackground, 1);
            }
            landingPageSteps.inputColor("Heading  color", headingColor);
            landingPageSteps.changeFont("Heading  font", headingFont);
            landingPageSteps.inputTextBox("Heading  size", headingSize);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading, 1);
            landingPageSteps.inputColor("Title color", titleColorRichText, 1);
            landingPageSteps.changeFont("Title font", titleFontRichText, 1);
            landingPageSteps.inputTextBox("Title size", titleSizeRichText, 1);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentRichTextTitle, 2);
            landingPageSteps.inputColor("Body color", bodyColorRichText);
            landingPageSteps.changeFont("Body font", bodyFontRichText);
            landingPageSteps.inputTextBox("Body size", bodySizeRichText);
            landingPageSteps.selectDdlWithLabel("Alignment", alignmentRichTextBody, 3);
            if (typeButton.equalsIgnoreCase("Button")) {
                landingPageSteps.inputColor("Button color", buttonColorRichText, 1);
                landingPageSteps.inputColor("Button border color", buttonBorderColorRichText, 1);
                landingPageSteps.inputColor("Button label color", buttonLabelColorRichText, 1);
                landingPageSteps.changeFont("Button label font", buttonLabelFontRichText, 1);
                landingPageSteps.inputTextBox("Button label size", buttonLabelSizeRichText, 1);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentRichTextButton, 3);
            } else {
                landingPageSteps.inputColor("Link color", linkColorRichText);
                landingPageSteps.changeFont("Link font", linkFontRichText);
                landingPageSteps.inputTextBox("Link size", linkSizeRichText);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentRichTextLink, 3);
            }
            landingPageSteps.inputColor("Title color", titleColor, 2);
            landingPageSteps.changeFont("Title font", titleFont, 2);
            landingPageSteps.inputTextBox("Title size", titleSize, 2);
            landingPageSteps.selectDdlWithLabel("Alignment", titleAlignment, 4);
            landingPageSteps.inputColor("Price color", priceColor);
            landingPageSteps.inputTextBox("Price size", priceSize);
            landingPageSteps.inputColor("Compare-at-price color", compareAtPriceColor);
            landingPageSteps.inputTextBox("Compare-at-price size", compareAtPriceSize);
            landingPageSteps.inputColor("Sale tag background", saleTagBackground);
            landingPageSteps.inputColor("Sale tag label color", saleTagLabelColor);
            landingPageSteps.inputTextBox("Sale tag size", saleTagSize);
            landingPageSteps.changeFont("Price font", priceFont);
            landingPageSteps.selectDdlWithLabel("Alignment", priceAlignment, 5);
            landingPageSteps.inputColor("Rating icon color", ratingIconColor);
            landingPageSteps.inputTextBox("Rating icon size", ratingIconSize);
            landingPageSteps.selectDdlWithLabel("Alignment", ratingAlignment);
            landingPageSteps.inputColor("Variant label color", variantLabelColor);
            landingPageSteps.changeFont("Variant label font", variantLabelFont);
            landingPageSteps.inputTextBox("Variant label size", variantLabelSize);
            landingPageSteps.selectDdlWithLabel("Alignment", variantPickerAlignment, 6);
            landingPageSteps.inputColor("Button background color", buttonBackgroundColor);
            if (typeButton.equalsIgnoreCase("Button")) {
                landingPageSteps.inputColor("Button border color", buttonBorderColor, 2);
                landingPageSteps.inputColor("Button label color", buttonLabelColor, 2);
                landingPageSteps.changeFont("Button label font", buttonLabelFont, 2);
                landingPageSteps.inputTextBox("Button label size", buttonLabelSize, 2);
            } else {
                landingPageSteps.inputColor("Button border color", buttonBorderColor, 1);
                landingPageSteps.inputColor("Button label color", buttonLabelColor, 1);
                landingPageSteps.changeFont("Button label font", buttonLabelFont, 1);
                landingPageSteps.inputTextBox("Button label size", buttonLabelSize, 1);
            }
            landingPageSteps.inputColor("Description color", descriptionColor);
            landingPageSteps.changeFont("Description font", descriptionFont);
            landingPageSteps.inputTextBox("Description size", descriptionSize);
            landingPageSteps.inputColor("Border top color", borderTopColor);
            landingPageSteps.inputTextBox("Border top size", borderTopSize);
            landingPageSteps.inputColor("Border bottom color", borderBottomColor);
            landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
            landingPageSteps.selectDdlWithLabel("Product image position", productImagePosition);
            landingPageSteps.selectDdlWithLabel("Min height", minHeight);
            landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
            if (maxWidth.equalsIgnoreCase("Custom")) {
                landingPageSteps.inputTextBox("Custom width", customWidth);
            }
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
            themeEditorSteps.closeThemeEditor();
        }
    }

    @And("verify Product Rich Text section  {string}")
    public void verifyProductRichTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String richTextTitle = SessionData.getDataTbVal(dataTable, row, "Rich text title");
            String richTextBody = SessionData.getDataTbVal(dataTable, row, "Rich text body");
            String typeButton = SessionData.getDataTbVal(dataTable, row, "Type button");
            String buttonLabelRichText = SessionData.getDataTbVal(dataTable, row, "Button label Rich text");
            String buttonLinkRichText = SessionData.getDataTbVal(dataTable, row, "Button link Rich text");
            String linkLabelRichText = SessionData.getDataTbVal(dataTable, row, "Link label Rich text");
            String linkRichText = SessionData.getDataTbVal(dataTable, row, "Link Rich text");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String titleColorRichText = SessionData.getDataTbVal(dataTable, row, "Title color Rich text");
            String titleFontRichText = SessionData.getDataTbVal(dataTable, row, "Title font Rich text");
            String titleSizeRichText = SessionData.getDataTbVal(dataTable, row, "Title size Rich text");
            String alignmentRichTextTitle = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text title");
            String bodyColorRichText = SessionData.getDataTbVal(dataTable, row, "Body color Rich text");
            String bodyFontRichText = SessionData.getDataTbVal(dataTable, row, "Body font Rich text");
            String bodySizeRichText = SessionData.getDataTbVal(dataTable, row, "Body size Rich text");
            String alignmentRichTextBody = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text body");
            String buttonColorRichText = SessionData.getDataTbVal(dataTable, row, "Button color Rich text");
            String buttonBorderColorRichText = SessionData.getDataTbVal(dataTable, row, "Button border color Rich text");
            String buttonLabelColorRichText = SessionData.getDataTbVal(dataTable, row, "Button label color Rich text");
            String buttonLabelFontRichText = SessionData.getDataTbVal(dataTable, row, "Button label font Rich text");
            String buttonLabelSizeRichText = SessionData.getDataTbVal(dataTable, row, "Button label size Rich text");
            String alignmentRichTextButton = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text button");
            String linkColorRichText = SessionData.getDataTbVal(dataTable, row, "Link color Rich text");
            String linkFontRichText = SessionData.getDataTbVal(dataTable, row, "Link font Rich text");
            String linkSizeRichText = SessionData.getDataTbVal(dataTable, row, "Link size Rich text");
            String alignmentRichTextLink = SessionData.getDataTbVal(dataTable, row, "Alignment Rich text link");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String productImagePosition = SessionData.getDataTbVal(dataTable, row, "Product image position");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (!product.isEmpty()) {
                featuredProductSteps.verifyHeading("Product Rich Text", heading);
                productRichTextSteps.verifyTitleRichText(richTextTitle);
                productRichTextSteps.verifyBodyRichText(richTextBody);
                productRichTextSteps.verifyTypeButtonRichText(typeButton);
                if (typeButton.equalsIgnoreCase("Button")) {
                    productRichTextSteps.verifyButtonLabelRichText(buttonLabelRichText, typeButton);
                    productRichTextSteps.verifyButtonLinkRichText(buttonLinkRichText, typeButton);
                } else {
                    productRichTextSteps.verifyLinkLabelRichText(linkLabelRichText, typeButton);
                    productRichTextSteps.verifyLinkRichText(linkRichText, typeButton);
                }
                featuredProductSteps.verifyShowTitle("Product Rich Text", showTitle);
                if (showTitle) {
                    featuredProductSteps.verifyTitle("Product Rich Text", title, showTitle, product);
                }
                featuredProductSteps.verifyShowPrice("Product Rich Text", showPrice);
                featuredProductSteps.verifyShowCompareAtPrice("Product Rich Text", showCompareAtPrice);
                featuredProductSteps.verifyShowSaleTag("Product Rich Text", showSaleTag);
                featuredProductSteps.verifySaleType("Product Rich Text", saleType);
                featuredProductSteps.verifyShowRatings("Product Rich Text", showRatings);
                featuredProductSteps.verifyShowVariantPicker("Product Rich Text", showVariantPicker);
                featuredProductSteps.verifyShowQuantity("Product Rich Text", showQuantity);
                featuredProductSteps.verifyDefaultQuantity("Product Rich Text", defaultQuantity);
                featuredProductSteps.verifyButtonLabel("Product Rich Text", buttonLabel);
                featuredProductSteps.verifyDestination("Product Rich Text", destination);
                featuredProductSteps.verifyShowDescription("Product Rich Text", showDescription);
                featuredProductSteps.verifyShowTrustBadge("Product Rich Text", showTrustBadge);
                featuredProductSteps.verifyType("Product Rich Text", type);
                if (type.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomColor("Product Rich Text", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    featuredProductSteps.verifyStartColor("Product Rich Text", startColor);
                    featuredProductSteps.verifyEndColor("Product Rich Text", endColor);
                    featuredProductSteps.verifyDirection("Product Rich Text", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    featuredProductSteps.verifyColorOverlayBackground("Product Rich Text", colorOverlayBackground);
                }
                featuredProductSteps.verifyHeadingColor("Product Rich Text", headingColor, heading);
                featuredProductSteps.verifyHeadingFont("Product Rich Text", headingFont, heading);
                featuredProductSteps.verifyHeadingSize("Product Rich Text", headingSize, heading);
                featuredProductSteps.verifyAlignmentHeading("Product Rich Text", alignmentHeading, heading);
                productRichTextSteps.verifyTitleColorRichText(titleColorRichText, richTextTitle);
                productRichTextSteps.verifyTitleFontRichText(titleFontRichText, richTextTitle);
                productRichTextSteps.verifyTitleSizeRichText(titleSizeRichText, richTextTitle);
                productRichTextSteps.verifyAlignmentRichTextTitle(alignmentRichTextTitle, richTextTitle);
                productRichTextSteps.verifyBodyColorRichText(bodyColorRichText, richTextBody);
                productRichTextSteps.verifyBodyFontRichText(bodyFontRichText, richTextBody);
                productRichTextSteps.verifyBodySizeRichText(bodySizeRichText, richTextBody);
                productRichTextSteps.verifyAlignmentRichTextBody(alignmentRichTextBody, richTextBody);
                if (typeButton.equalsIgnoreCase("Button")) {
                    productRichTextSteps.verifyButtonColorRichText(buttonColorRichText, buttonLabelRichText);
                    productRichTextSteps.verifyButtonBorderColorRichText(buttonBorderColorRichText, buttonLabelRichText);
                    productRichTextSteps.verifyButtonLabelColorRichText(buttonLabelColorRichText, buttonLabelRichText, typeButton);
                    productRichTextSteps.verifyButtonLabelFontRichText(buttonLabelFontRichText, buttonLabelRichText, typeButton);
                    productRichTextSteps.verifyButtonLabelSizeRichText(buttonLabelSizeRichText, buttonLabelRichText, typeButton);
                    productRichTextSteps.verifyAlignmentRichTextButton(alignmentRichTextButton, buttonLabelRichText, typeButton);
                } else {
                    productRichTextSteps.verifyLinkColorRichText(linkColorRichText, linkRichText, typeButton);
                    productRichTextSteps.verifyLinkFontRichText(linkFontRichText, linkRichText, typeButton);
                    productRichTextSteps.verifyLinkSizeRichText(linkSizeRichText, linkRichText, typeButton);
                    productRichTextSteps.verifyAlignmentRichTextLink(alignmentRichTextLink, linkRichText, typeButton);
                }
                featuredProductSteps.verifyTitleColor("Product Rich Text", titleColor, showTitle);
                featuredProductSteps.verifyTitleFont("Product Rich Text", titleFont, showTitle);
                featuredProductSteps.verifyTitleSize("Product Rich Text", titleSize, showTitle);
                featuredProductSteps.verifyTitleAlignment("Product Rich Text", titleAlignment, showTitle);
                featuredProductSteps.verifyPriceColor("Product Rich Text", priceColor, showPrice);
                featuredProductSteps.verifyPriceSize("Product Rich Text", priceSize, showPrice);
                featuredProductSteps.verifyCompareAtPriceColor("Product Rich Text", compareAtPriceColor, showCompareAtPrice);
                featuredProductSteps.verifyCompareAtPriceSize("Product Rich Text", compareAtPriceSize, showCompareAtPrice);
                featuredProductSteps.verifySaleTagBackground("Product Rich Text", saleTagBackground, showSaleTag);
                featuredProductSteps.verifySaleTagLabelColor("Product Rich Text", saleTagLabelColor, showSaleTag);
                featuredProductSteps.verifySaleTagSize("Product Rich Text", saleTagSize, showSaleTag);
                featuredProductSteps.verifyPriceFont("Product Rich Text", priceFont, showPrice);
                featuredProductSteps.verifyPriceAlignment("Product Rich Text", priceAlignment, showPrice);
                featuredProductSteps.verifyRatingIconColor("Product Rich Text", ratingIconColor, showRatings);
                featuredProductSteps.verifyRatingIconSize("Product Rich Text", ratingIconSize, showRatings);
                featuredProductSteps.verifyRatingAlignment("Product Rich Text", ratingAlignment, showRatings);
                featuredProductSteps.verifyVariantLabelColor("Product Rich Text", variantLabelColor, showVariantPicker);
                featuredProductSteps.verifyVariantLabelFont("Product Rich Text", variantLabelFont, showVariantPicker);
                featuredProductSteps.verifyVariantLabelSize("Product Rich Text", variantLabelSize, showVariantPicker);
                featuredProductSteps.verifyVariantPickerAlignment("Product Rich Text", variantPickerAlignment, showVariantPicker);
                featuredProductSteps.verifyButtonBackgroundColor("Product Rich Text", buttonBackgroundColor, buttonLabel);
                featuredProductSteps.verifyButtonBorderColor("Product Rich Text", buttonBorderColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelColor("Product Rich Text", buttonLabelColor, buttonLabel);
                featuredProductSteps.verifyButtonLabelFont("Product Rich Text", buttonLabelFont, buttonLabel);
                featuredProductSteps.verifyButtonLabelSize("Product Rich Text", buttonLabelSize, buttonLabel);
                featuredProductSteps.verifyDescriptionColor("Product Rich Text", descriptionColor, showDescription);
                featuredProductSteps.verifyDescriptionFont("Product Rich Text", descriptionFont, showDescription);
                featuredProductSteps.verifyDescriptionSize("Product Rich Text", descriptionSize, showDescription);
                featuredProductSteps.verifyBorderTopColor("Product Rich Text", borderTopColor);
                featuredProductSteps.verifyBorderTopSize("Product Rich Text", borderTopSize);
                featuredProductSteps.verifyBorderBottomColor("Product Rich Text", borderBottomColor);
                featuredProductSteps.verifyBorderBottomSize("Product Rich Text", borderBottomSize);
                productImageSteps.verifyProductImagePosition(productImagePosition);
                featuredProductSteps.verifyMinHeight("Product Rich Text", minHeight);
                featuredProductSteps.verifyMaxWidth("Product Rich Text", maxWidth);
                if (maxWidth.equalsIgnoreCase("Custom")) {
                    featuredProductSteps.verifyCustomWidth("Product Rich Text", customWidth);
                }
            }
        }
    }

    @And("change Product List section  {string}")
    public void changeProductListSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean highlightThisProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight this product"));
            String highlightText = SessionData.getDataTbVal(dataTable, row, "Highlight text");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showDescription = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Description"));
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String productBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Product background color");
            String highlightBorderColor = SessionData.getDataTbVal(dataTable, row, "Highlight border color");
            String highlightTextColor = SessionData.getDataTbVal(dataTable, row, "Highlight text color");
            String highlightTextFont = SessionData.getDataTbVal(dataTable, row, "Highlight text font");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String overlayColorImage = SessionData.getDataTbVal(dataTable, row, "Overlay color Image");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String descriptionColor = SessionData.getDataTbVal(dataTable, row, "Description color");
            String descriptionFont = SessionData.getDataTbVal(dataTable, row, "Description font");
            String descriptionSize = SessionData.getDataTbVal(dataTable, row, "Description size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String typeLayout = SessionData.getDataTbVal(dataTable, row, "Type Layout");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            landingPageSteps.openSection("Product List");
            if (index == 1) {
                landingPageSteps.inputTextBox("Heading", heading);
                landingPageSteps.openVisualSetting("Visual settings");
                landingPageSteps.selectDdlWithLabel("Type", type, 1);
                if (type.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputColor("Custom color", customColor);
                }
                if (type.equalsIgnoreCase("Gradient")) {
                    landingPageSteps.inputColor("Start color", startColor);
                    landingPageSteps.inputColor("End color", endColor);
                    landingPageSteps.selectDdlWithLabel("Direction", direction);
                }
                if (type.equalsIgnoreCase("Image")) {
                    themeEditorSteps.uploadImageWithLabel("Image", imageBackground);
                    landingPageSteps.inputColor("Overlay color", colorOverlayBackground, 1);
                }
                landingPageSteps.inputColor("Heading  color", headingColor);
                landingPageSteps.changeFont("Heading  font", headingFont);
                landingPageSteps.inputTextBox("Heading  size", headingSize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentHeading, 1);
                landingPageSteps.inputColor("Product background color", productBackgroundColor);
                landingPageSteps.inputColor("Highlight border color", highlightBorderColor);
                landingPageSteps.inputColor("Highlight text color", highlightTextColor);
                landingPageSteps.changeFont("Highlight text font", highlightTextFont);
                landingPageSteps.inputColor("Title color", titleColor);
                landingPageSteps.changeFont("Title font", titleFont);
                landingPageSteps.inputTextBox("Title size", titleSize);
                landingPageSteps.selectDdlWithLabel("Alignment", titleAlignment, 2);
                landingPageSteps.inputColor("Price color", priceColor);
                landingPageSteps.inputTextBox("Price size", priceSize);
                landingPageSteps.inputColor("Compare-at-price color", compareAtPriceColor);
                landingPageSteps.inputTextBox("Compare-at-price size", compareAtPriceSize);
                landingPageSteps.inputColor("Sale tag background", saleTagBackground);
                landingPageSteps.inputColor("Sale tag label color", saleTagLabelColor);
                landingPageSteps.inputTextBox("Sale tag size", saleTagSize);
                landingPageSteps.changeFont("Price font", priceFont);
                landingPageSteps.selectDdlWithLabel("Alignment", priceAlignment, 3);
                landingPageSteps.inputColor("Body color", bodyColor);
                landingPageSteps.changeFont("Body font", bodyFont);
                landingPageSteps.inputTextBox("Body size", bodySize);
                landingPageSteps.selectDdlWithLabel("Alignment", alignmentBody, 4);
                landingPageSteps.inputColor("Rating icon color", ratingIconColor);
                landingPageSteps.inputTextBox("Rating icon size", ratingIconSize);
                landingPageSteps.selectDdlWithLabel("Alignment", ratingAlignment);
                landingPageSteps.inputColor("Variant label color", variantLabelColor);
                landingPageSteps.changeFont("Variant label font", variantLabelFont);
                landingPageSteps.inputTextBox("Variant label size", variantLabelSize);
                landingPageSteps.selectDdlWithLabel("Alignment", variantPickerAlignment, 6);
                landingPageSteps.inputColor("Button background color", buttonBackgroundColor);
                landingPageSteps.inputColor("Button border color", buttonBorderColor);
                landingPageSteps.inputColor("Button label color", buttonLabelColor);
                landingPageSteps.changeFont("Button label font", buttonLabelFont);
                landingPageSteps.inputTextBox("Button label size", buttonLabelSize);
                landingPageSteps.inputColor("Description color", descriptionColor);
                landingPageSteps.changeFont("Description font", descriptionFont);
                landingPageSteps.inputTextBox("Description size", descriptionSize);
                landingPageSteps.inputColor("Border top color", borderTopColor);
                landingPageSteps.inputTextBox("Border top size", borderTopSize);
                landingPageSteps.inputColor("Border bottom color", borderBottomColor);
                landingPageSteps.inputTextBox("Border bottom size", borderBottomSize);
                landingPageSteps.selectDdlWithLabel("Type", typeLayout, 2);
                landingPageSteps.selectDdlWithLabel("Min height", minHeight);
                landingPageSteps.selectDdlWithLabel("Max width", maxWidth);
                if (maxWidth.equalsIgnoreCase("Custom")) {
                    landingPageSteps.inputTextBox("Custom width", customWidth);
                }
                landingPageSteps.removeAllContent("Product");

            }
            landingPageSteps.clickAddContent();
            landingPageSteps.opendMenuItem("Product", index);
            landingPageSteps.enterProduct("Product", product);
            landingPageSteps.checkCheckBox("Highlight this product", highlightThisProduct);
            landingPageSteps.inputTextBox("Highlight text", highlightText);
            landingPageSteps.checkCheckBox("Show Title", showTitle);
            if (showTitle) {
                landingPageSteps.inputTextBox("Title", title);
            }
            landingPageSteps.checkCheckBox("Show Price", showPrice);
            landingPageSteps.checkCheckBox("Show Compare-at-price", showCompareAtPrice);
            landingPageSteps.checkCheckBox("Show Sale tag", showSaleTag);
            landingPageSteps.selectDdlWithLabel("Sale type", saleType);
            landingPageSteps.inputTextContentWithIframe(body,index);
            landingPageSteps.checkCheckBox("Show Ratings", showRatings);
            landingPageSteps.checkCheckBox("Show Variant picker", showVariantPicker);
            landingPageSteps.checkCheckBox("Select variants by clicking their images", isSelectVariantsByClick);
            landingPageSteps.checkCheckBox("Enable color swatches", enableColorSwatches);
            landingPageSteps.checkCheckBox("Show Quantity", showQuantity);
            landingPageSteps.inputTextBox("Default quantity", defaultQuantity);
            landingPageSteps.inputTextBox("Button label", buttonLabel);
            landingPageSteps.inputTextBox("Destination", destination);
            landingPageSteps.checkCheckBox("Show Description", showDescription);
            landingPageSteps.checkCheckBox("Show Trust badge", showTrustBadge);
            if (rowKey.equals(nextRowKey)) {
                index++;
            } else {
                themeEditorSteps.saveSetting();
                themeEditorSteps.closeSessionSetting();
                themeEditorSteps.closeThemeEditor();
            }
        }

    }

    @And("verify Product List section  {string}")
    public void verifyProductListSection(String dataKey, List<List<String>> dataTable) {
        int index = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String nextRowKey = SessionData.getDataTbVal(dataTable, row + 1, "KEY");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            boolean highlightThisProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight this product"));
            String highlightText = SessionData.getDataTbVal(dataTable, row, "Highlight text");
            boolean showTitle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Title"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean showPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Price"));
            boolean showCompareAtPrice = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Compare-at-price"));
            boolean showSaleTag = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Sale tag"));
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            String body = SessionData.getDataTbVal(dataTable, row, "Body");
            boolean showRatings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Ratings"));
            boolean showVariantPicker = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Variant picker"));
            boolean isSelectVariantsByClick = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Select variants by clicking their images"));
            boolean enableColorSwatches = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable color swatches"));
            boolean showQuantity = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Quantity"));
            String defaultQuantity = SessionData.getDataTbVal(dataTable, row, "Default quantity");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String destination = SessionData.getDataTbVal(dataTable, row, "Destination");
            boolean showTrustBadge = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Trust badge"));
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String customColor = SessionData.getDataTbVal(dataTable, row, "Custom color");
            String startColor = SessionData.getDataTbVal(dataTable, row, "Start color");
            String endColor = SessionData.getDataTbVal(dataTable, row, "End color");
            String direction = SessionData.getDataTbVal(dataTable, row, "Direction");
            String imageBackground = SessionData.getDataTbVal(dataTable, row, "Image Background");
            String colorOverlayBackground = SessionData.getDataTbVal(dataTable, row, "Color overlay Background");
            String headingColor = SessionData.getDataTbVal(dataTable, row, "Heading color");
            String headingFont = SessionData.getDataTbVal(dataTable, row, "Heading font");
            String headingSize = SessionData.getDataTbVal(dataTable, row, "Heading size");
            String alignmentHeading = SessionData.getDataTbVal(dataTable, row, "Alignment Heading");
            String productBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Product background color");
            String highlightBorderColor = SessionData.getDataTbVal(dataTable, row, "Highlight border color");
            String highlightTextColor = SessionData.getDataTbVal(dataTable, row, "Highlight text color");
            String highlightTextFont = SessionData.getDataTbVal(dataTable, row, "Highlight text font");
            String titleColor = SessionData.getDataTbVal(dataTable, row, "Title color");
            String titleFont = SessionData.getDataTbVal(dataTable, row, "Title font");
            String titleSize = SessionData.getDataTbVal(dataTable, row, "Title size");
            String titleAlignment = SessionData.getDataTbVal(dataTable, row, "Title Alignment");
            String priceColor = SessionData.getDataTbVal(dataTable, row, "Price color");
            String priceSize = SessionData.getDataTbVal(dataTable, row, "Price size");
            String compareAtPriceColor = SessionData.getDataTbVal(dataTable, row, "Compare-at-price color");
            String compareAtPriceSize = SessionData.getDataTbVal(dataTable, row, "Compare-at-price size");
            String saleTagBackground = SessionData.getDataTbVal(dataTable, row, "Sale tag background");
            String saleTagLabelColor = SessionData.getDataTbVal(dataTable, row, "Sale tag label color");
            String saleTagSize = SessionData.getDataTbVal(dataTable, row, "Sale tag size");
            String priceFont = SessionData.getDataTbVal(dataTable, row, "Price font");
            String priceAlignment = SessionData.getDataTbVal(dataTable, row, "Price Alignment");
            String bodyColor = SessionData.getDataTbVal(dataTable, row, "Body color");
            String bodyFont = SessionData.getDataTbVal(dataTable, row, "Body font ");
            String bodySize = SessionData.getDataTbVal(dataTable, row, "Body size");
            String alignmentBody = SessionData.getDataTbVal(dataTable, row, "Alignment Body");
            String ratingIconColor = SessionData.getDataTbVal(dataTable, row, "Rating icon color");
            String ratingIconSize = SessionData.getDataTbVal(dataTable, row, "Rating icon size");
            String ratingAlignment = SessionData.getDataTbVal(dataTable, row, "Rating Alignment");
            String variantLabelColor = SessionData.getDataTbVal(dataTable, row, "Variant label color");
            String variantLabelFont = SessionData.getDataTbVal(dataTable, row, "Variant label font");
            String variantLabelSize = SessionData.getDataTbVal(dataTable, row, "Variant label size");
            String variantPickerAlignment = SessionData.getDataTbVal(dataTable, row, "Variant picker Alignment");
            String buttonBackgroundColor = SessionData.getDataTbVal(dataTable, row, "Button background color");
            String buttonBorderColor = SessionData.getDataTbVal(dataTable, row, "Button border color");
            String buttonLabelColor = SessionData.getDataTbVal(dataTable, row, "Button label color");
            String buttonLabelFont = SessionData.getDataTbVal(dataTable, row, "Button label font");
            String buttonLabelSize = SessionData.getDataTbVal(dataTable, row, "Button label size");
            String borderTopColor = SessionData.getDataTbVal(dataTable, row, "Border top color");
            String borderTopSize = SessionData.getDataTbVal(dataTable, row, "Border top size");
            String borderBottomColor = SessionData.getDataTbVal(dataTable, row, "Border bottom color");
            String borderBottomSize = SessionData.getDataTbVal(dataTable, row, "Border bottom size");
            String typeLayout = SessionData.getDataTbVal(dataTable, row, "Type Layout");
            String minHeight = SessionData.getDataTbVal(dataTable, row, "Min height");
            String maxWidth = SessionData.getDataTbVal(dataTable, row, "Max width");
            String customWidth = SessionData.getDataTbVal(dataTable, row, "Custom width");
            if (index == 1) {
                if (!product.isEmpty()) {
                    featuredProductSteps.verifyHeading("Product list", heading);
                    featuredProductSteps.verifyType("Product list", type);
                    if (type.equalsIgnoreCase("Custom")) {
                        featuredProductSteps.verifyCustomColor("Product list", customColor);
                    }
                    if (type.equalsIgnoreCase("Gradient")) {
                        featuredProductSteps.verifyStartColor("Product list", startColor);
                        featuredProductSteps.verifyEndColor("Product list", endColor);
                        featuredProductSteps.verifyDirection("Product list", direction);
                    }
                    if (type.equalsIgnoreCase("Image")) {
                        featuredProductSteps.verifyColorOverlayBackground("Product list", colorOverlayBackground);
                    }
                    featuredProductSteps.verifyHeadingColor("Product list", headingColor, heading);
                    featuredProductSteps.verifyHeadingFont("Product list", headingFont, heading);
                    featuredProductSteps.verifyHeadingSize("Product list", headingSize, heading);
                    featuredProductSteps.verifyAlignmentHeading("Product list", alignmentHeading, heading);

                    featuredProductSteps.verifyTitleColor("Product list", titleColor, showTitle);
                    featuredProductSteps.verifyTitleFont("Product list", titleFont, showTitle);
                    featuredProductSteps.verifyTitleSize("Product list", titleSize, showTitle);
                    featuredProductSteps.verifyTitleAlignment("Product list", titleAlignment, showTitle);
                    featuredProductSteps.verifyPriceColor("Product list", priceColor, showPrice);
                    featuredProductSteps.verifyPriceSize("Product list", priceSize, showPrice);
                    featuredProductSteps.verifyCompareAtPriceColor("Product list", compareAtPriceColor, showCompareAtPrice);
                    featuredProductSteps.verifyCompareAtPriceSize("Product list", compareAtPriceSize, showCompareAtPrice);
                    featuredProductSteps.verifySaleTagBackground("Product list", saleTagBackground, showSaleTag);
                    featuredProductSteps.verifySaleTagLabelColor("Product list", saleTagLabelColor, showSaleTag);
                    featuredProductSteps.verifySaleTagSize("Product list", saleTagSize, showSaleTag);
                    featuredProductSteps.verifyPriceFont("Product list", priceFont, showPrice);
                    featuredProductSteps.verifyPriceAlignment("Product list", priceAlignment, showPrice);
                    featuredProductSteps.verifyDescriptionColor("Product list", bodyColor, !body.isEmpty());
                    featuredProductSteps.verifyDescriptionFont("Product list", bodyFont, !body.isEmpty());
                    featuredProductSteps.verifyDescriptionSize("Product list", bodySize, !body.isEmpty());
                    featuredProductSteps.verifyRatingIconColor("Product Rich Text", ratingIconColor, showRatings);
                    featuredProductSteps.verifyRatingIconSize("Product Rich Text", ratingIconSize, showRatings);
                    featuredProductSteps.verifyRatingAlignment("Product Rich Text", ratingAlignment, showRatings);
                    featuredProductSteps.verifyVariantLabelColor("Product Rich Text", variantLabelColor, showVariantPicker);
                    featuredProductSteps.verifyVariantLabelFont("Product Rich Text", variantLabelFont, showVariantPicker);
                    featuredProductSteps.verifyVariantLabelSize("Product Rich Text", variantLabelSize, showVariantPicker);
                    featuredProductSteps.verifyVariantPickerAlignment("Product Rich Text", variantPickerAlignment, showVariantPicker);
                    featuredProductSteps.verifyButtonBackgroundColor("Product Rich Text", buttonBackgroundColor, buttonLabel);
                    featuredProductSteps.verifyButtonBorderColor("Product Rich Text", buttonBorderColor, buttonLabel);
                    featuredProductSteps.verifyButtonLabelColor("Product Rich Text", buttonLabelColor, buttonLabel);
                    featuredProductSteps.verifyButtonLabelFont("Product Rich Text", buttonLabelFont, buttonLabel);
                    featuredProductSteps.verifyButtonLabelSize("Product Rich Text", buttonLabelSize, buttonLabel);
                    featuredProductSteps.verifyBorderTopColor("Product Rich Text", borderTopColor);
                    featuredProductSteps.verifyBorderTopSize("Product Rich Text", borderTopSize);
                    featuredProductSteps.verifyBorderBottomColor("Product Rich Text", borderBottomColor);
                    featuredProductSteps.verifyBorderBottomSize("Product Rich Text", borderBottomSize);
                    featuredProductSteps.verifyTypeLayout("Product Rich Text",typeLayout);
                    featuredProductSteps.verifyMinHeight("Product Rich Text", minHeight);
                    featuredProductSteps.verifyMaxWidth("Product Rich Text", maxWidth);
                    if (maxWidth.equalsIgnoreCase("Custom")) {
                        featuredProductSteps.verifyCustomWidth("Product Rich Text", customWidth);
                    }
                }

            }
            if(!product.isEmpty()) {
                productListSteps.verifyHighlightProduct(highlightThisProduct,index);
                productListSteps.verifyHighlightText(highlightText,index);
                productListSteps.verifyShowTitle(showTitle,index);
                if (showTitle) {
                    productListSteps.verifyTitle(title, showTitle, product,index);
                }
                productListSteps.verifyShowPrice(showPrice,index);
                productListSteps.verifyShowCompareAtPrice( showCompareAtPrice,index);
                productListSteps.verifyShowSaleTag(showSaleTag,index);
                productListSteps.verifySaleType( saleType,index);
                productListSteps.verifyShowRatings(showRatings,index);
                productListSteps.verifyShowVariantPicker(showVariantPicker,index);
                productListSteps.verifyShowQuantity(showQuantity,index);
                productListSteps.verifyDefaultQuantity(defaultQuantity,index);
                productListSteps.verifyButtonLabel(buttonLabel,index);
                productListSteps.verifyDestination( destination,index);
                productListSteps.verifyShowTrustBadge( showTrustBadge,index);
            }
            if (rowKey.equals(nextRowKey)) {
                index++;
            }
        }
    }
}
