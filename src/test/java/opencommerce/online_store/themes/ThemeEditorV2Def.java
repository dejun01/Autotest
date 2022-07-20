package opencommerce.online_store.themes;

import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorV2Steps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.VideoSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings.CartSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.BannerSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.ImageWithTextSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static common.utilities.LoadObject.convertStatus;

public class ThemeEditorV2Def {
    @Steps
    ThemeEditorV2Steps themeEditorV2Steps;
    @Steps
    VideoSteps videoSteps;
    @Steps
    CartSteps cartSteps;
    @Steps
    BannerSteps bannerSteps;
    @Steps
    ImageWithTextSteps imageWithTextSteps;

    String theme = LoadObject.getProperty("theme");

    @Then("click Customize button on Current theme")
    public void clickCustomizeCurrentTheme() {
        themeEditorV2Steps.clickCustomizeCurrentTheme();
    }

    @Then("open preview page {string}")
    public void openPreviewPage(String page) {
        themeEditorV2Steps.openPreviewPage(page);
    }

    @Then("open section setting {string}")
    public void openSection(String section) {
        themeEditorV2Steps.openSectionSettingWithName(section);
    }

    @Then("save change setting")
    public void saveChangeSetting() {
        themeEditorV2Steps.clickButtonSaveChange();
    }

    @Then("add section {string}")
    public void addSection(String section) {
        themeEditorV2Steps.addSectionWithName(section);
    }

    @Then("remove section {string}")
    public void removeSection(String section) {
        themeEditorV2Steps.openSectionSettingWithName(section);
        themeEditorV2Steps.clickOnButton("Remove section");
    }

    @Then("add block {string} in section {string}")
    public void addBlockInSection(String block, String section) {
        themeEditorV2Steps.addBlockWithName(section, block);
    }

    @Then("remove block {string} in section {string}")
    public void removeBlockInSection(String block, String section) {
        themeEditorV2Steps.openBlockWithName(section, block);
        themeEditorV2Steps.clickOnButton("Remove block");
    }

    @Then("hide or show section")
    public void hideOrShowSection(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String section = SessionData.getDataTbVal(dataTable, row, "Section");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));

            themeEditorV2Steps.showOrHideSection(section, isShow);
        }
    }

    @Then("hide or show block")
    public void hideOrShowBlock(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String section = SessionData.getDataTbVal(dataTable, row, "Section");
            String block = SessionData.getDataTbVal(dataTable, row, "Block");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));

            themeEditorV2Steps.showOrHideBlock(section, block, isShow);
        }
    }

    @When("remove all section {string}")
    public void removeAllSection(String nameSection) {
        int countSection = themeEditorV2Steps.countSectionByXpath(nameSection);
        if (countSection != 0) {
            for (int i = 0; i < countSection; i++) {
                themeEditorV2Steps.openSectionSettingWithName(nameSection);
                themeEditorV2Steps.clickOnButton("Remove section");
            }
            themeEditorV2Steps.clickButtonSaveChange();
        }
    }

    @When("^remove block in \"([^\"]*)\" section \"([^\"]*)\"$")
    public void removeBlockInSection(String sectionName, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String blockName = SessionData.getDataTbVal(dataTable, row, "Block name");
            int countBlock = themeEditorV2Steps.countBlockByXpath(blockName, sectionName);
            for (int i = 1; i <= countBlock; i++) {
                themeEditorV2Steps.openBlockWithName(sectionName, blockName, 1);
                themeEditorV2Steps.clickOnButton("Remove block");
            }
        }
    }

    @When("add block in {string} section")
    public void addBlock(String sectionName, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String blockName = SessionData.getDataTbVal(dataTable, row, "Block name");
            themeEditorV2Steps.addBlockWithName(sectionName, 1, blockName);
        }
    }

    @Then("^setting section Banner \"([^\"]*)\"$")
    public void settingBannerSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String firstBtnLbl = SessionData.getDataTbVal(dataTable, row, "Primary button label");
            String firstLink = SessionData.getDataTbVal(dataTable, row, "Primary button link");
            boolean highlightBtn1 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight first button label"));
            String secondBtnLbl = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            String secondLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");
            boolean highlightBtn2 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight second button label"));
            boolean fullWidthSection = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width section"));
            boolean isParallaxScrolling = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling"));
            themeEditorV2Steps.openSectionSettingWithName("Banner");
            switch (theme) {
                case "RollerV3":
                    if (themeEditorV2Steps.isExistImage("Background image"))
                        themeEditorV2Steps.removeImageUploaded("Background image");
                    if (!image.isEmpty())
                        themeEditorV2Steps.uploadImageWithFile("Background image", image);
                    themeEditorV2Steps.selectValueInDropDown("Ratio", ratio);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Enable parallax scrolling", isParallaxScrolling);
                    themeEditorV2Steps.inputTextAreaWithLabel("Preheading", preHeading);
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    themeEditorV2Steps.inputTextAreaWithLabel("Subheading", subHeading);
                    themeEditorV2Steps.selectValueInDropDown("Text position", textPosition);
                    themeEditorV2Steps.selectValueInDropDown("Text alignment", textAlignment);
                    themeEditorV2Steps.inputTextBoxWithLabel("First button label", firstBtnLbl);
                    themeEditorV2Steps.selectValueInAutocomplete("First button link", firstLink);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Highlight first button label", highlightBtn1);
                    themeEditorV2Steps.inputTextBoxWithLabel("Second button label", secondBtnLbl);
                    themeEditorV2Steps.selectValueInAutocomplete("Second button link", secondLink);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Highlight second button label", highlightBtn2);
                    break;
                default:
                    if (themeEditorV2Steps.isExistImage("Image"))
                        themeEditorV2Steps.removeImageUploaded("Image");
                    if (!image.isEmpty())
                        themeEditorV2Steps.uploadImageWithFile("Image", image);
                    themeEditorV2Steps.clickButtonGroup("Desktop text position", textPosition);
                    themeEditorV2Steps.clickButtonGroup("Desktop text alignment", textAlignment);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Full width section", fullWidthSection);
            }
            themeEditorV2Steps.selectValueInAutocomplete("Image link", imageLink);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", altText);
            themeEditorV2Steps.scrollSliderBarByPercent("Opacity", opacity);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("^setting block \"([^\"]*)\" in Banner section \"([^\"]*)\"$")
    public void settingBlockInBannerSection(String nameBlock, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String primaryButtonLabel = SessionData.getDataTbVal(dataTable, row, "Primary button label");
            String primaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Primary button link");
            String secondaryButtonLabel = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            String secondaryButtonLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");

            themeEditorV2Steps.openBlockWithName("Banner", nameBlock);
            switch (nameBlock) {
                case "Heading":
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    break;
                case "Text":
                    themeEditorV2Steps.inputTextAreaWithLabel("Text", text);
                    break;
                case "Button":
                    themeEditorV2Steps.inputTextBoxWithLabel("Label", primaryButtonLabel, 1);
                    themeEditorV2Steps.selectValueInAutocomplete("Link", primaryButtonLink, 1);
                    themeEditorV2Steps.inputTextBoxWithLabel("Label", secondaryButtonLabel, 2);
                    themeEditorV2Steps.selectValueInAutocomplete("Link", secondaryButtonLink, 2);
                    break;
            }
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("^setting section Image With Text \"([^\"]*)\"$")
    public void settingImageWithTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String fullWidth = SessionData.getDataTbVal(dataTable, row, "Full width section");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            themeEditorV2Steps.openSectionSettingWithName("Image With Text");
            themeEditorV2Steps.clickButtonGroup("Layout", layout);
            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            if (!fullWidth.isEmpty())
                themeEditorV2Steps.checkCheckBoxWithLabel("Full width section", Boolean.parseBoolean(fullWidth));
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("^setting block in section Image With Text \"([^\"]*)\"$")
    public void settingBlockInImageWithTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Button label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");
            String buttonType = SessionData.getDataTbVal(dataTable, row, "Button type");
            themeEditorV2Steps.openBlockWithName("Image With Text", "Image With Text", indexBlock);
            if (themeEditorV2Steps.isExistImage("Image"))
                themeEditorV2Steps.removeImageUploaded("Image");
            if (!image.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Image", image);
            themeEditorV2Steps.selectValueInAutocomplete("Image link", imageLink);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", altText);
            themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
            themeEditorV2Steps.inputTextWithIframe("Text", text);
            themeEditorV2Steps.selectValueInDropDown("Button type", buttonType);
            themeEditorV2Steps.inputTextBoxWithLabel("Button label", buttonLabel);
            themeEditorV2Steps.selectValueInAutocomplete("Button link", buttonLink);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("change Newsletter settings section as {string}")
    public void changeNewsletterSettingsSectionAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            boolean fullWidthOnDesktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width on desktop"));
            String image = SessionData.getDataTbVal(dataTable, row, "Image");

            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            themeEditorV2Steps.checkCheckBoxWithLabel("Full width on desktop", fullWidthOnDesktop);
            if (themeEditorV2Steps.isExistImage("Image"))
                themeEditorV2Steps.removeImageUploaded("Image");
            if (!image.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Image", image);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("setting block {string} in Newsletter section {string}")
    public void settingBlockInNewsletterSection(String nameBlock, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            themeEditorV2Steps.openBlockWithName("Newsletter", nameBlock);
            switch (nameBlock) {
                case "Heading":
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    break;
                case "Text":
                    themeEditorV2Steps.inputTextWithIframe("Text", text);
                    break;
            }
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("setting section Video slider {string}")
    public void settingSectionVideoSlider(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean fullWithVideo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Full width video"));
            boolean autoplayVideo = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Autoplay video"));
            themeEditorV2Steps.openSectionSettingWithName("Video Slider");
            themeEditorV2Steps.checkCheckBoxWithLabel("Full width video", fullWithVideo);
            themeEditorV2Steps.checkCheckBoxWithLabel("Autoplay video", autoplayVideo);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("setting block in section Video slider {string}")
    public void settingBlockInSectionVideoSlider(String dataKey, List<List<String>> dataTable) {
        int indexBlock = 1;
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String rowKey = SessionData.getDataTbVal(dataTable, row, "KEY");
            String rowPreviousKey = SessionData.getDataTbVal(dataTable, row - 1, "KEY");
            String videoLink = SessionData.getDataTbVal(dataTable, row, "Video link");
            String thumbnail = SessionData.getDataTbVal(dataTable, row, "Thumbnail");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Sub heading");
            boolean displayBackground = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Display soiled text background"));
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");

            if (rowKey.equals(rowPreviousKey)) {
                indexBlock++;
            } else {
                indexBlock = 1;
            }

            themeEditorV2Steps.openBlockWithName("Video Slider", "Video", indexBlock);
            if (!videoLink.isEmpty()) {
                themeEditorV2Steps.inputTextBoxWithLabel("Video link", videoLink);
            }
            if (themeEditorV2Steps.isExistImage("Thumbnail"))
                themeEditorV2Steps.removeImageUploaded("Thumbnail");
            if (!thumbnail.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Thumbnail", thumbnail);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", altText);
            themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
            themeEditorV2Steps.inputTextWithIframe("Sub heading", subHeading);
            themeEditorV2Steps.checkCheckBoxWithLabel("Display soiled text background", displayBackground);
            themeEditorV2Steps.clickButtonGroup("Text position", textPosition);
            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    // Logo
    @Then("^setting section Logo \"([^\"]*)\"$")
    public void settingLogoSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");

            themeEditorV2Steps.openSectionSettingWithName("Logo");
            themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("^setting block in section Logo \"([^\"]*)\"$")
    public void settingBlockInLogoSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String selectImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String inputAltText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String inputImageLink = SessionData.getDataTbVal(dataTable, row, "Image link");

            themeEditorV2Steps.openBlockWithName("Logo", "Logo", indexBlock);
            if (themeEditorV2Steps.isExistImage("Image"))
                themeEditorV2Steps.removeImageUploaded("Image");
            if (!selectImage.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Image", selectImage);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", inputAltText);
            themeEditorV2Steps.selectValueInAutocomplete("Image link", inputImageLink);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @When("^navigate to tab \"([^\"]*)\"$")
    public void navigateToTab(String tab) {
        themeEditorV2Steps.openTab(tab);
    }

    @When("^select setting section \"([^\"]*)\"$")
    public void selectSection(String sectionName) {
        themeEditorV2Steps.selectSettingsSection(sectionName);
    }

    @When("^setting cart page as \"([^\"]*)\"$")
    public void userChangeCartPageInDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String cartType = SessionData.getDataTbVal(dataTable, row, "Cart type");
            boolean isShowCheckoutButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Only show Checkout button in cart drawer"));

            themeEditorV2Steps.selectValueInDropDown("Cart type", cartType);
            themeEditorV2Steps.checkCheckBoxWithLabel("Only show Checkout button in Cart Drawer", isShowCheckoutButton);
            themeEditorV2Steps.clickButtonSaveChange();
        }
    }

    @When("^setting cart goal \"([^\"]*)\"$")
    public void changeSectionCartGoal(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String message = SessionData.getDataTbVal(dataTable, row, "Motivational message");
            boolean enableCartGoal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable cart goal"));
            String amount = SessionData.getDataTbVal(dataTable, row, "Goal amount");
            String goalReached = SessionData.getDataTbVal(dataTable, row, "Goal reached");

            themeEditorV2Steps.checkCheckBoxWithLabel("Enable Cart Goal", enableCartGoal);
            themeEditorV2Steps.inputTextBoxWithLabel("Motivational message", message);
            themeEditorV2Steps.inputTextBoxWithLabel("Goal amount", amount);
            themeEditorV2Steps.inputTextBoxWithLabel("Goal reached", goalReached);
            themeEditorV2Steps.clickButtonSaveChange();
        }
    }

    @And("change header layout as {string}")
    public void userChangeHeaderLayoutAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");

            themeEditorV2Steps.openSectionSettingWithName("Header");
            themeEditorV2Steps.clickButtonGroup("Layout", layout);
            themeEditorV2Steps.clickButtonSaveChange();
        }
    }

    @And("change search settings as {string}")
    public void userChangeSearchSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean enableSort = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable sort"));
            String logic = SessionData.getDataTbVal(dataTable, row, "Default logic");

            themeEditorV2Steps.checkCheckBoxWithLabel("Enable sort in search results", enableSort);
            themeEditorV2Steps.selectValueInDropDown("Default logic", logic);
            themeEditorV2Steps.clickButtonSaveChange();
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("change global settings as {string}")
    public void userChangeGlobalSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean sortProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sort in collection page"));
            String pagination = SessionData.getDataTbVal(dataTable, row, "Pagination");
            String imgDisplay = SessionData.getDataTbVal(dataTable, row, "Product image display");
            String contentAlign = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            boolean showSale = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sales banner"));
            boolean showAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Add to cart button"));
            String shape = SessionData.getDataTbVal(dataTable, row, "Shape");
            boolean showTopIcon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable back to top icon"));

            themeEditorV2Steps.checkCheckBoxWithLabel("Enable sort in collection list", sortProduct);
            themeEditorV2Steps.selectValueInDropDown("Pagination", pagination);
            themeEditorV2Steps.selectValueInDropDown("Product image display", imgDisplay);
            themeEditorV2Steps.selectValueInDropDown("Content alignment", contentAlign);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show sales banners", showSale);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show Add-to-cart button", showAddToCart);
            themeEditorV2Steps.selectValueInDropDown("Shape", shape);
            themeEditorV2Steps.checkCheckBoxWithLabel("Enable back to top icon", showTopIcon);
            themeEditorV2Steps.clickButtonSaveChange();
        }
    }

    @Then("^setting section Featured Product \"([^\"]*)\"$")
    public void settingSectionFeaturedProduct(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String product = SessionData.getDataTbVal(dataTable, row, "Product");
            String settingBy = SessionData.getDataTbVal(dataTable, row, "Setting by");
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            String productPhotoPosition = SessionData.getDataTbVal(dataTable, row, "Product photo position");
            boolean showArrowsOnProductGallery = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show arrows on product gallery"));
            boolean enableProductGallerySlideshow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable product gallery slideshow"));
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");
            String imagePosition = SessionData.getDataTbVal(dataTable, row, "Image position");
            boolean showVendor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean showPriceSavings = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean showProductContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show product content"));

            themeEditorV2Steps.openSectionSettingWithName("Featured Product");
            themeEditorV2Steps.selectValueInAutocomplete("Product", product);
            if (!productPhotoPosition.isEmpty()) {
                themeEditorV2Steps.clickButtonGroup("Product photo position", productPhotoPosition);
            }
            themeEditorV2Steps.selectRadioButton(settingBy);
            if (theme.equalsIgnoreCase("RollerV3")) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Show arrows on product gallery", showArrowsOnProductGallery);
                themeEditorV2Steps.checkCheckBoxWithLabel("Enable product gallery slideshow", enableProductGallerySlideshow);
                themeEditorV2Steps.selectValueInDropDown("Gallery transition", galleryTransition);
                themeEditorV2Steps.selectValueInDropDown("Image position", imagePosition);
                themeEditorV2Steps.checkCheckBoxWithLabel("Show vendor", showVendor);
                themeEditorV2Steps.checkCheckBoxWithLabel("Show price savings", showPriceSavings);
                themeEditorV2Steps.checkCheckBoxWithLabel("Show product content", showProductContent);
                themeEditorV2Steps.selectValueInDropDown("Description position", descriptionPosition);
            } else {
                if (!productPhotoPosition.isEmpty()) {
                    themeEditorV2Steps.clickButtonGroup("Product photo position", productPhotoPosition);
                }
                themeEditorV2Steps.selectRadioButton(settingBy);

                if (settingBy.equals("Customize") && !descriptionPosition.isEmpty()) {
                    themeEditorV2Steps.selectValueInDropDown("Description position", descriptionPosition);
                }
            }
                themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("setting block {string} in Featured Product section {string}")
    public void settingBlockInSectionFeaturedProduct(String block, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settingBy = SessionData.getDataTbVal(dataTable, row, "Setting by");

            switch (block) {
                case "Price":
                    String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
                    String showPriceSavings = SessionData.getDataTbVal(dataTable, row, "Show price savings");

                    themeEditorV2Steps.openBlockWithName("Featured Product", "Price");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.selectValueInDropDown("Sale type", saleType);
                        if (!showPriceSavings.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Show price savings", Boolean.parseBoolean(showPriceSavings));
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;

                case "Variants selector":
                    String variantStyle = SessionData.getDataTbVal(dataTable, row, "Variant style");
                    String hideSelector = SessionData.getDataTbVal(dataTable, row, "Hide selector when option has one value");
                    String onlyShowAvailableCombination = SessionData.getDataTbVal(dataTable, row, "Only show available combination");
                    String variantGroupSwatches = SessionData.getDataTbVal(dataTable, row, "Variant group swatches");
                    String colorSwatches = SessionData.getDataTbVal(dataTable, row, "Color swatches");

                    themeEditorV2Steps.openBlockWithName("Featured Product", "Variants selector");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.selectValueInDropDown("Variant style", variantStyle);
                        if (!hideSelector.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Hide selector when option has one value", Boolean.parseBoolean(hideSelector));
                        }
                        if (!onlyShowAvailableCombination.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Link product options, only show available combination", Boolean.parseBoolean(onlyShowAvailableCombination));
                        }
                        if (!variantGroupSwatches.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Enable variant group swatches", Boolean.parseBoolean(variantGroupSwatches));
                        }
                        if (!colorSwatches.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Enable color swatches", Boolean.parseBoolean(colorSwatches));
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Buy Buttons":
                    boolean isShowQuantitySelector = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity selector"));
                    boolean isShowCardButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add to card button"));
                    boolean isShowQuantityAndCartSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity & Add to card is same line"));
                    boolean isShowCheckOutButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Dynamic checkout button"));
                    boolean isShowBuyPaypal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, " Buy with Paypal"));

                    themeEditorV2Steps.openBlockWithName("Featured Product", "Buy buttons");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box", isShowQuantitySelector);
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box & Add to cart button only", isShowCardButton);
                        if (isShowQuantitySelector) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Set quantity box & Add to cart button in the same line", isShowQuantityAndCartSameLine);
                        }
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show dynamic checkout button", isShowCheckOutButton);
                        if (isShowCheckOutButton) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Buy with Paypal", isShowBuyPaypal);
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;
            }
        }
    }

    @And("^setting section Product \"([^\"]*)\"$")
    public void changeProductSetting(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settingBy = SessionData.getDataTbVal(dataTable, row, "Setting by");
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            boolean stickyButtonDesktop = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Sticky button desktop"));
            boolean enableBreadcrumb = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Breadcrumb"));

            themeEditorV2Steps.openSectionSettingWithName("Product");
            themeEditorV2Steps.checkCheckBoxWithLabel("Enable breadcrumb", enableBreadcrumb);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show on desktop", stickyButtonDesktop);

            if (!settingBy.isEmpty()) {
                themeEditorV2Steps.selectRadioButton(settingBy);
            }

            if (settingBy.equals("Customize") && !descriptionPosition.isEmpty()) {
                themeEditorV2Steps.selectValueInDropDown("Description position", descriptionPosition);
            }
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("^setting block \"([^\"]*)\" in Product section \"([^\"]*)\"$")
    public void changeProductBlocks(String blockName, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String settingBy = SessionData.getDataTbVal(dataTable, row, "Setting by");

            switch (blockName) {
                case "Price":
                    String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
                    String showPriceSavings = SessionData.getDataTbVal(dataTable, row, "Show price savings");

                    themeEditorV2Steps.openBlockWithName("Product", "Price");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.selectValueInDropDown("Sale type", saleType);
                        if (!showPriceSavings.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Show price savings", Boolean.parseBoolean(showPriceSavings));
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;

                case "Variant Selector":
                    String variantStyle = SessionData.getDataTbVal(dataTable, row, "Variant style");
                    String hideSelector = SessionData.getDataTbVal(dataTable, row, "Hide selector when option has one value");
                    String onlyShowAvailableCombination = SessionData.getDataTbVal(dataTable, row, "Only show available combination");
                    String variantGroupSwatches = SessionData.getDataTbVal(dataTable, row, "Variant group swatches");
                    String colorSwatches = SessionData.getDataTbVal(dataTable, row, "Color swatches");

                    themeEditorV2Steps.openBlockWithName("Product", "Variants selector");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.selectValueInDropDown("Variant style", variantStyle);
                        if (!hideSelector.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Hide selector when option has one value", Boolean.parseBoolean(hideSelector));
                        }
                        if (!onlyShowAvailableCombination.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Link product options, only show available combination", Boolean.parseBoolean(onlyShowAvailableCombination));
                        }
                        if (!variantGroupSwatches.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Enable variant group swatches", Boolean.parseBoolean(variantGroupSwatches));
                        }
                        if (!colorSwatches.isEmpty()) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Enable color swatches", Boolean.parseBoolean(colorSwatches));
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;

                case "Buy Buttons":
                    boolean isShowQuantitySelector = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity selector"));
                    boolean isShowCardButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add to card button"));
                    boolean isShowQuantityAndCartSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity & Add to card is same line"));
                    boolean isShowCheckOutButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Dynamic checkout button"));
                    boolean isShowBuyPaypal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, " Buy with Paypal"));

                    themeEditorV2Steps.openBlockWithName("Product", "Buy buttons");
                    themeEditorV2Steps.selectRadioButton(settingBy);

                    if (settingBy.equals("Customize")) {
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box", isShowQuantitySelector);
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box & Add to cart button only", isShowCardButton);
                        if (isShowQuantitySelector) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Set quantity box & Add to cart button in the same line", isShowQuantityAndCartSameLine);
                        }
                        themeEditorV2Steps.checkCheckBoxWithLabel("Show dynamic checkout button", isShowCheckOutButton);
                        if (isShowCheckOutButton) {
                            themeEditorV2Steps.checkCheckBoxWithLabel("Buy with Paypal", isShowBuyPaypal);
                        }
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;
            }
        }
    }

    @And("setting Product in Theme Settings {string}")
    public void settingProductInThemeSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            String saleType = SessionData.getDataTbVal(dataTable, row, "Sale type");
            String showPriceSavings = SessionData.getDataTbVal(dataTable, row, "Show price savings");
            String variantStyle = SessionData.getDataTbVal(dataTable, row, "Variant style");
            String hideSelector = SessionData.getDataTbVal(dataTable, row, "Hide selector when option has one value");
            String onlyShowAvailableCombination = SessionData.getDataTbVal(dataTable, row, "Only show available combination");
            String variantGroupSwatches = SessionData.getDataTbVal(dataTable, row, "Variant group swatches");
            String colorSwatches = SessionData.getDataTbVal(dataTable, row, "Color swatches");
            boolean isShowQuantitySelector = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity selector"));
            boolean isShowCardButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Add to card button"));
            boolean isShowQuantityAndCartSameLine = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Quantity & Add to card is same line"));
            boolean isShowCheckOutButton = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Dynamic checkout button"));
            boolean isShowBuyPaypal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, " Buy with Paypal"));

            themeEditorV2Steps.openTab("Settings");
            themeEditorV2Steps.selectSettingsSection("Product");

            themeEditorV2Steps.selectValueInDropDown("Description position", descriptionPosition);
            themeEditorV2Steps.selectValueInDropDown("Sale type", saleType);
            if (!showPriceSavings.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Show price savings", Boolean.parseBoolean(showPriceSavings));
            }
            themeEditorV2Steps.selectValueInDropDown("Variant style", variantStyle);
            if (!hideSelector.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Hide selector when option has one value", Boolean.parseBoolean(hideSelector));
            }
            if (!onlyShowAvailableCombination.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Link product options, only show available combination", Boolean.parseBoolean(onlyShowAvailableCombination));
            }
            if (!variantGroupSwatches.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Enable variant group swatches", Boolean.parseBoolean(variantGroupSwatches));
            }
            if (!colorSwatches.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Enable color swatches", Boolean.parseBoolean(colorSwatches));
            }

            themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box", isShowQuantitySelector);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show quantity box & Add to cart button only", isShowCardButton);
            if (isShowQuantitySelector) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Set quantity box & Add to cart button in the same line", isShowQuantityAndCartSameLine);
            }
            themeEditorV2Steps.checkCheckBoxWithLabel("Show dynamic checkout button", isShowCheckOutButton);
            if (isShowCheckOutButton) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Buy with Paypal", isShowBuyPaypal);
            }
        }
    }

    @Then("^setting section Slideshow \"([^\"]*)\"$")
    public void settingSlideshowSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String fullWidth = SessionData.getDataTbVal(dataTable, row, "Full width on desktop");
            String ratio = SessionData.getDataTbVal(dataTable, row, "Ratio");
            String changeSlidesEvery = SessionData.getDataTbVal(dataTable, row, "Change slides every");
            String opacity = SessionData.getDataTbVal(dataTable, row, "Opacity");
            String enable = SessionData.getDataTbVal(dataTable, row, "Enable parallax scrolling");
            String textAnimation = SessionData.getDataTbVal(dataTable, row, "Text animation");
            String galleryTransition = SessionData.getDataTbVal(dataTable, row, "Gallery transition");

            themeEditorV2Steps.openSectionSettingWithName("Slideshow");
            if (!layout.isEmpty()){
                themeEditorV2Steps.clickButtonGroup("Layout", layout);
            }
            if (!fullWidth.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Full width on desktop", Boolean.parseBoolean(fullWidth));
            }
            themeEditorV2Steps.clickButtonGroup("Ratio", ratio);
            themeEditorV2Steps.scrollSliderBarByPercent("Change slides every", changeSlidesEvery);
            if (!opacity.isEmpty()){
                themeEditorV2Steps.scrollSliderBarByPercent("Opacity", opacity);
            }
            if (!enable.isEmpty()) {
                themeEditorV2Steps.checkCheckBoxWithLabel("Enable parallax scrolling", Boolean.parseBoolean(enable));
            }
            if (!textAnimation.isEmpty()) {
                themeEditorV2Steps.selectValueInDropDown("Text animation", textAnimation);
            }
            if (!galleryTransition.isEmpty()) {
                themeEditorV2Steps.selectValueInDropDown("Gallery transition", galleryTransition);
            }
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("^setting block in section Slideshow \"([^\"]*)\"$")
    public void settingBlockInSlideshowSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
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
            themeEditorV2Steps.openBlockWithName("Slideshow", "Slide", indexBlock);
            if (themeEditorV2Steps.isExistImage("Image"))
                themeEditorV2Steps.removeImageUploaded("Image");
            if (!image.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Image", image);
            themeEditorV2Steps.selectValueInAutocomplete("Image URL", imageUrl);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", altText);
            themeEditorV2Steps.inputTextBoxWithLabelThenTab("Background color", backgroundColor);
            themeEditorV2Steps.inputTextBoxWithLabel("Headline", headline);
            themeEditorV2Steps.inputTextWithIframe("Text", text);
            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            themeEditorV2Steps.inputTextBoxWithLabel("First button label", firstButtonLabel);
            themeEditorV2Steps.selectValueInAutocomplete("First button link", firstButtonLink);
            themeEditorV2Steps.inputTextBoxWithLabel("Second button label", secondButtonLabel);
            themeEditorV2Steps.selectValueInAutocomplete("Second button link", secondButtonLink);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("setting block Slideshow {string}")
    public void settingBlockSlideshow(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int indexBlock = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Index block"));
            String backgroundImage = SessionData.getDataTbVal(dataTable, row, "Background image");
            String altText = SessionData.getDataTbVal(dataTable, row, "Alt text");
            String preHeading = SessionData.getDataTbVal(dataTable, row, "Preheading");
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String subHeading = SessionData.getDataTbVal(dataTable, row, "Subheading");
            String imageLink = SessionData.getDataTbVal(dataTable, row, "Image link");
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Text position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");
            String firstBtnLbl = SessionData.getDataTbVal(dataTable, row, "First button label");
            String firstLink = SessionData.getDataTbVal(dataTable, row, "First button link");
            Boolean highlightBtn1 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight first button label"));
            String secondBtnLbl = SessionData.getDataTbVal(dataTable, row, "Secondary button label");
            String secondLink = SessionData.getDataTbVal(dataTable, row, "Secondary button link");
            Boolean highlightBtn2 = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Highlight second button label"));
            themeEditorV2Steps.openBlockWithName("Slideshow", "Slide", indexBlock);
            if (themeEditorV2Steps.isExistImage("Background image"))
                themeEditorV2Steps.removeImageUploaded("Background image");
            if (!backgroundImage.isEmpty())
                themeEditorV2Steps.uploadImageWithFile("Background image", backgroundImage);
            themeEditorV2Steps.inputTextBoxWithLabel("Alt text", altText);
            themeEditorV2Steps.inputTextAreaWithLabel("Preheading", preHeading);
            themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
            themeEditorV2Steps.inputTextAreaWithLabel("Subheading", subHeading);
            themeEditorV2Steps.selectValueInDropDown("Text position", textPosition);
            themeEditorV2Steps.selectValueInDropDown("Text alignment", textAlignment);
            if (!imageLink.isEmpty())
                themeEditorV2Steps.selectValueInAutocomplete("Image link", imageLink);
            themeEditorV2Steps.inputTextBoxWithLabel("First button label", firstBtnLbl);
            if (!firstLink.isEmpty())
                themeEditorV2Steps.selectValueInAutocomplete("First button link", firstLink);
            themeEditorV2Steps.checkCheckBoxWithLabel("Highlight first button label", highlightBtn1);
            themeEditorV2Steps.inputTextBoxWithLabel("Second button label", secondBtnLbl);
            if (!secondLink.isEmpty())
                themeEditorV2Steps.selectValueInAutocomplete("Second button link", secondLink);
            themeEditorV2Steps.checkCheckBoxWithLabel("Highlight second button label", highlightBtn2);
            themeEditorV2Steps.clickButtonBack();
        }

    }

    @And("setting Header on Theme editor {string}")
    public void settingHeaderOnThemeEditor(String dataKey, List<List<String>> dataTable) {
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
            String mainMenuTopPadding = SessionData.getDataTbVal(dataTable, row, "Main menu top padding");
            themeEditorV2Steps.openSectionSettingWithName("Header");
            themeEditorV2Steps.checkCheckBoxWithLabel("Fixed ('sticky') header", fixedHeader);
            themeEditorV2Steps.checkCheckBoxWithLabel("Enable search", enableSearch);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show cart", showCart);
            themeEditorV2Steps.selectValueInDropDown("Cart icon", cartIcon);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show announcement bar", showAnnouncementBar);
            if (showAnnouncementBar) {
                themeEditorV2Steps.inputTextWithIframe("Announcement message", announcementMessage);
            }
            themeEditorV2Steps.checkCheckBoxWithLabel("Show top bar", showTopBar);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show social media icons", showSocialMediaHeaderIcons);
            themeEditorV2Steps.inputTextBoxWithLabel("Phone number", phoneNumber);
            themeEditorV2Steps.selectValueInDropDown("Top bar menu", topBarMenu);
            themeEditorV2Steps.uploadImageWithFile("Desktop logo", desktopLogo);
            themeEditorV2Steps.clickButtonGroup("Size", size);
            themeEditorV2Steps.selectValueInDropDown("Main menu", mainMenu);
            themeEditorV2Steps.scrollSliderBarByPercent("Main menu top padding", mainMenuTopPadding);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @Then("Setting Mega Menu {string}")
    public void settingMegaMenu(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String linkTitle = SessionData.getDataTbVal(dataTable, row, "Link title");
            String textAreaTopContent = SessionData.getDataTbVal(dataTable, row, "Text area Top content");
            String imageTopContent = SessionData.getDataTbVal(dataTable, row, "Image Top content");
            String imageCaptionTopContent = SessionData.getDataTbVal(dataTable, row, "Image caption Top content");
            String imageLinkTopContent = SessionData.getDataTbVal(dataTable, row, "Image link Top content");
            String imageBottomContent = SessionData.getDataTbVal(dataTable, row, "Image Bottom content");
            String imageCaptionBottomContent = SessionData.getDataTbVal(dataTable, row, "Image caption Bottom content");
            String imageLinkBottomContent = SessionData.getDataTbVal(dataTable, row, "Image link Bottom content");
            String textAreaBottomContent = SessionData.getDataTbVal(dataTable, row, "Text area Bottom content");
            themeEditorV2Steps.openBlockWithName("Header", "Mega Menu", 1);
            themeEditorV2Steps.inputTextBoxWithLabel("Link title", linkTitle);
            if (!textAreaTopContent.isEmpty()) {
                themeEditorV2Steps.inputTextWithIframe("Text area", textAreaTopContent, 1);
            }
            if (!imageTopContent.isEmpty()) {
                themeEditorV2Steps.uploadImageWithFile("Image", imageTopContent, 1);
            }
            if (!imageCaptionTopContent.isEmpty()) {
                themeEditorV2Steps.inputTextBoxWithLabel("Image caption", imageCaptionTopContent, 1);
            }
            if (!imageLinkTopContent.isEmpty()) {
                themeEditorV2Steps.selectValueInAutocomplete("Image link", imageLinkTopContent, 1);
            }
            if (!imageBottomContent.isEmpty()) {
                themeEditorV2Steps.uploadImageWithFile("Image", imageBottomContent, 2);
            }
            if (!imageCaptionBottomContent.isEmpty()) {
                themeEditorV2Steps.inputTextBoxWithLabel("Image caption", imageCaptionBottomContent, 2);
            }
            if (!imageLinkBottomContent.isEmpty()) {
                themeEditorV2Steps.selectValueInAutocomplete("Image link", imageLinkBottomContent, 2);
            }
            if (!textAreaBottomContent.isEmpty()) {
                themeEditorV2Steps.inputTextWithIframe("Text area", textAreaBottomContent, 2);
            }
        }
    }

    @Then("setting Footer {string}")
    public void settingFooter(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String copyrightText = SessionData.getDataTbVal(dataTable, row, "Copyright text");
            boolean showSocialMediaFooterIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media footer icons"));
            String desktopLogo = SessionData.getDataTbVal(dataTable, row, "Desktop logo");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            themeEditorV2Steps.openSectionSettingWithName("Footer");
            themeEditorV2Steps.uploadImageWithFile("Desktop logo", desktopLogo);
            themeEditorV2Steps.clickButtonGroup("Size", size);
            themeEditorV2Steps.inputTextBoxWithLabel("Copyright text", copyrightText);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show social media footer icons", showSocialMediaFooterIcons);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @And("setting block {string} in Footer {string}")
    public void settingBlockInFooter(String blockName, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {

            switch (blockName) {
                case "Menu":
                    String mainMenu = SessionData.getDataTbVal(dataTable, row, "Main menu");
                    themeEditorV2Steps.openBlockWithName("Footer", "Menu");
                    themeEditorV2Steps.selectValueInDropDown("Main menu", mainMenu);
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Title":
                    String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
                    String text = SessionData.getDataTbVal(dataTable, row, "Text");
                    themeEditorV2Steps.openBlockWithName("Footer", "Title");
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    themeEditorV2Steps.inputTextWithIframe("Text", text);
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Page":
                    String contentPage = SessionData.getDataTbVal(dataTable, row, "Content page");
                    themeEditorV2Steps.openBlockWithName("Footer", "Page");
                    if (!contentPage.isEmpty()) {
                        themeEditorV2Steps.selectValueInDropDown("Content page", contentPage);
                    }
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Icon":
                    String icon = SessionData.getDataTbVal(dataTable, row, "Icon");
                    themeEditorV2Steps.openBlockWithName("Payment Icons", "Icon");
                    themeEditorV2Steps.uploadImageWithFile("Icon", icon);
                    themeEditorV2Steps.clickButtonBack();
                    break;
            }
        }
    }

    @And("setting Payment Icons {string}")
    public void settingPaymentIcons(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean showPaymentMethodIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show payment method icons"));
            themeEditorV2Steps.openSectionSettingWithName("Payment Icons");
            themeEditorV2Steps.checkCheckBoxWithLabel("Show payment method icons", showPaymentMethodIcons);
            themeEditorV2Steps.clickButtonBack();
        }
    }

    @When("setting Product page {string}")
    public void settingProductPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean magnifyProductImages = convertStatus(SessionData.getDataTbVal(dataTable, row, "Magnify product images by hovering on desktop"));
            boolean enableProductGalleryPopup = convertStatus(SessionData.getDataTbVal(dataTable, row, "Enable product gallery popup"));
            boolean showBreadcrumbLink = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show breadcrumb link"));
            boolean showVendor = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show vendor"));
            boolean showSku = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show SKU"));
            boolean showPriceSavings = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show price savings"));
            boolean showCollections = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show collections"));
            boolean showTypes = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show types"));
            boolean showTags = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show tags"));
            boolean showSocialMediaShareIcons = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show social media share icons"));
            boolean showStickyButton = convertStatus(SessionData.getDataTbVal(dataTable, row, "Show Sticky button"));
            String displayOn = SessionData.getDataTbVal(dataTable, row, "Display on");
            String descriptionPosition = SessionData.getDataTbVal(dataTable, row, "Description position");
            themeEditorV2Steps.openSectionSettingWithName("Product");
            themeEditorV2Steps.checkCheckBoxWithLabel("Magnify product images by hovering on desktop",magnifyProductImages);
            themeEditorV2Steps.checkCheckBoxWithLabel("Enable product gallery popup",enableProductGalleryPopup);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show breadcrumb link",showBreadcrumbLink);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show vendor",showVendor);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show SKU",showSku);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show price savings",showPriceSavings);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show collections",showCollections);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show types",showTypes);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show tags",showTags);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show social media share icons",showSocialMediaShareIcons);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show Sticky button",showStickyButton);
            if(showStickyButton) {
                themeEditorV2Steps.selectValueInDropDown("Display on", displayOn);
            }
            themeEditorV2Steps.selectValueInDropDown("Description position",descriptionPosition);
            themeEditorV2Steps.clickButtonBack();


        }
    }

    @And("setting block {string} in {string} section {string}")
    public void settingBlockInSection(String blockName, String sectionName, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            boolean collapseThisTabByDefault = convertStatus(SessionData.getDataTbVal(dataTable, row, "Collapse this tab by default"));
            switch (blockName){
                case "Product Description":
                    themeEditorV2Steps.openBlockWithName(sectionName, blockName);
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Collapse this tab by default",collapseThisTabByDefault);
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Additional Tab":
                    String text = SessionData.getDataTbVal(dataTable, row, "Text");
                    themeEditorV2Steps.openBlockWithName(sectionName, blockName);
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    themeEditorV2Steps.inputTextWithIframe("Text",text);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Collapse this tab by default",collapseThisTabByDefault);
                    themeEditorV2Steps.clickButtonBack();
                    break;
                case "Page":
                    String contentPage = SessionData.getDataTbVal(dataTable, row, "Content page");
                    themeEditorV2Steps.openBlockWithName(sectionName, blockName);
                    themeEditorV2Steps.selectValueInDropDown("Content page",contentPage);
                    themeEditorV2Steps.checkCheckBoxWithLabel("Collapse this tab by default",collapseThisTabByDefault);
                    themeEditorV2Steps.clickButtonBack();
                    break;
            }
        }
    }
    @When("change blogs settings  {string}")
    public void changeBlogsSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String containerType = SessionData.getDataTbVal(dataTable, row, "Container type");
            boolean showSidebar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sidebar"));
            String placement = SessionData.getDataTbVal(dataTable, row, "Placement");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String postsToShow = SessionData.getDataTbVal(dataTable, row, "Posts to show");
            String postsPerRow = SessionData.getDataTbVal(dataTable, row, "Posts per row");
            themeEditorV2Steps.openSectionSettingWithName("Blog");
            themeEditorV2Steps.selectValueInDropDown("Container type", containerType);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show sidebar", showSidebar);
            if (showSidebar) {
                themeEditorV2Steps.selectValueInDropDown("Placement", placement);
            }
            themeEditorV2Steps.selectValueInDropDown("Layout", layout);
            themeEditorV2Steps.scrollSliderBarByPercent("Posts to show", postsToShow);
            if (layout.equalsIgnoreCase("Grid")) {
                themeEditorV2Steps.scrollSliderBarByPercent("Posts per row", postsPerRow);
            }
        }
    }

    @When("change blog post settings  {string}")
    public void changeBlogPostSettings(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String containerType = SessionData.getDataTbVal(dataTable, row, "Container type");
            boolean showSidebar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sidebar"));
            String placement = SessionData.getDataTbVal(dataTable, row, "Placement");
            String design = SessionData.getDataTbVal(dataTable, row, "Design");
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));
            boolean showDate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show date"));
            boolean showTagsArticleContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags article content"));
            boolean showSocial = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social"));
            boolean showRelatedArticle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show related article"));
            boolean showComments = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show comments"));
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            boolean showTagsRelatedArticles = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags related articles"));
            boolean showPublishedDate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show published date"));
            boolean showExcerpt = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show excerpt"));
            boolean showReadMoreLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show read more link"));
            themeEditorV2Steps.openSectionSettingWithName("Blog Post");
            themeEditorV2Steps.selectValueInDropDown("Container type", containerType);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show sidebar", showSidebar);
            if (showSidebar) {
                themeEditorV2Steps.selectValueInDropDown("Placement", placement);
            }
            themeEditorV2Steps.selectValueInDropDown("Design", design);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show author", showAuthor);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show date", showDate);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show tags", showTagsArticleContent, 1);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show social", showSocial);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show related article", showRelatedArticle);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show comments", showComments);
            themeEditorV2Steps.selectValueInDropDown("Content alignment", contentAlignment);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show tags", showTagsRelatedArticles, 2);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show published date", showPublishedDate);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show excerpt", showExcerpt);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show read more link", showReadMoreLink);
        }
    }

    @When("change blogs settings roller  {string}")
    public void changeBlogsSettingsRoller(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layoutOnLargeScreen = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean showFeatureImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show feature image"));
            String featureImageHeight = SessionData.getDataTbVal(dataTable, row, "Feature image height");
            boolean showDate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show date"));
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));

            themeEditorV2Steps.openSectionSettingWithName("Blog");
            themeEditorV2Steps.selectValueInDropDown("Layout on large screen", layoutOnLargeScreen);
            if(layoutOnLargeScreen.equalsIgnoreCase("Grid")){
                themeEditorV2Steps.selectValueInDropDown("Feature image height", featureImageHeight);
            }
            themeEditorV2Steps.checkCheckBoxWithLabel("Show feature image", showFeatureImage);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show date", showDate);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show author", showAuthor);
        }
    }

    @When("change blogs post settings roller {string}")
    public void changeBlogPostSettingRoller(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()){
            String featureImageHeight = SessionData.getDataTbVal(dataTable, row, "Feature image height");
            boolean showDate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show date"));
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));

            themeEditorV2Steps.openSectionSettingWithName("Blog Post");
            themeEditorV2Steps.selectValueInDropDown("Feature image height", featureImageHeight);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show date", showDate);
            themeEditorV2Steps.checkCheckBoxWithLabel("Show author", showAuthor);
        }
    }

    @When("change product setting {string}")
    public void changeProductSetting(String arg0) {
    }

    //RichText
    @Then("^setting section Rich Text \"([^\"]*)\"$")
    public void settingRichTextSection(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String textPosition = SessionData.getDataTbVal(dataTable, row, "Heading position");
            String textAlignment = SessionData.getDataTbVal(dataTable, row, "Text alignment");

            themeEditorV2Steps.openSectionSettingWithName("Rich Text");
            if (!textPosition.isEmpty()){
                themeEditorV2Steps.selectValueInDropDown("Heading position", textPosition);
            }
            themeEditorV2Steps.clickButtonGroup("Text alignment", textAlignment);
            themeEditorV2Steps.clickButtonBack();

        }
    }
    @Then("^setting block \"([^\"]*)\" in Rich Text section \"([^\"]*)\"$")
    public void settingBlockInRichTextSection(String nameBlock, String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String heading = SessionData.getDataTbVal(dataTable, row, "Heading");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String buttonType = SessionData.getDataTbVal(dataTable, row, "Button type");
            String buttonLabel = SessionData.getDataTbVal(dataTable, row, "Label");
            String buttonLink = SessionData.getDataTbVal(dataTable, row, "Button link");

            themeEditorV2Steps.openBlockWithName("Rich Text", nameBlock);
            switch (nameBlock) {
                case "Heading":
                    themeEditorV2Steps.inputTextBoxWithLabel("Heading", heading);
                    break;
                case "Text":
                    themeEditorV2Steps.inputTextWithIframe("Text", text);
                    break;
                case "Button":
                    if (!buttonType.isEmpty()){
                        themeEditorV2Steps.selectValueInDropDown("Button type", buttonType);
                    }
                    themeEditorV2Steps.inputTextBoxWithLabel("Label", buttonLabel);
                    themeEditorV2Steps.selectValueInAutocomplete("Button link", buttonLink);
                    break;
            }
            themeEditorV2Steps.clickButtonBack();
        }
    }
}
