package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.SlideshowPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import static common.utilities.LoadObject.convertStatus;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;



public class SlideshowSteps extends ScenarioSteps {
    SlideshowPage slideshowPage;

    public void cleanSlideshow() {
        int n = slideshowPage.countQuantityOfSlideshow();
        for (int i = n; i >= 1; i--) {
            openBlockSlideshow(i);
            slideshowPage.removeContent();
        }
    }

    @Step
    public void selectTextAnimation(String textAnimation) {
        if (!textAnimation.isEmpty())
            slideshowPage.selectDdlWithLabel("Text animation", textAnimation);
    }

    @Step
    public void selectGalleryTransition(String galleryTransition) {
        if (!galleryTransition.isEmpty())
            slideshowPage.selectDdlWithLabel("Gallery transition", galleryTransition);
    }

    @Step
    public void openBlockSlideshow(int slideshowIndex) {
        if (!slideshowPage.isExistSlideshow(slideshowIndex)) {
            slideshowPage.clickAddSlideshow();
        }
        slideshowPage.openBlockSlideshow(slideshowIndex);
    }

    @Step
    public void closeBlockSlideshow(int slideshowIndex) {
        slideshowPage.openBlockSlideshow(slideshowIndex);
    }

    @Step
    public void uploadBackgroundImage(String backgroundImage) {
        if (!backgroundImage.isEmpty())
            slideshowPage.uploadBackgroundImage(backgroundImage);
    }

    @Step
    public void enterAltText(String altText) {
        if (!altText.isEmpty())
            slideshowPage.enterInputSettingSileshowWithLabel("Alt text", altText);
    }

    @Step
    public void enterPreheading(String preheading) {
        if (!preheading.isEmpty())
            slideshowPage.enterTextAreaSettingSileshowWithLabel("Preheading", preheading);
    }

    @Step
    public void enterHeading(String heading) {
        slideshowPage.enterInputSettingSileshowWithLabel("Heading", heading);
    }

    @Step
    public void enterSubheading(String subheading) {
        if (!subheading.isEmpty())
            slideshowPage.enterTextAreaSettingSileshowWithLabel("Subheading", subheading);
    }

    @Step
    public void selectTextPosition(String textPosition) {
        if (!textPosition.isEmpty())
            slideshowPage.selectDdlSettingSileshowWithLabel("Text position", textPosition);
    }

    @Step
    public void selectTextAlignment(String textAlignment) {
        if (!textAlignment.isEmpty())
            slideshowPage.selectDdlSettingSileshowWithLabel("Text alignment", textAlignment);
    }

    public void selectImageLink(String imageLink) {

    }

    @Step
    public void enterFirstButtonLink(String firstButtonLink) {
        if (!firstButtonLink.isEmpty())
            slideshowPage.enterInputSettingSileshowWithLabel("First button link", firstButtonLink);

    }

    @Step
    public void enterFirstButtonLabel(String firstButtonLabel) {
        if (!firstButtonLabel.isEmpty())
            slideshowPage.enterInputSettingSileshowWithLabel("First button label", firstButtonLabel);
    }

    @Step
    public void highlightFirstButtonlabel(String highlightFirstButtonLabel) {
        if (!highlightFirstButtonLabel.isEmpty()) {
            boolean isCheck = convertStatus(highlightFirstButtonLabel);
            slideshowPage.highlightButtonlabel("Highlight first button label", isCheck);
        }
    }

    @Step
    public void enterSecondButtonLabel(String secondButtonLabel) {
        if (!secondButtonLabel.isEmpty())
            slideshowPage.enterInputSettingSileshowWithLabel("Second button label", secondButtonLabel);
    }

    @Step
    public void enterSecondButtonLink(String secondButtonLink) {
        if (!secondButtonLink.isEmpty())
            slideshowPage.enterInputSettingSileshowWithLabel("Second button link", secondButtonLink);
    }

    @Step
    public void highlightSecondButtonlabel(String highlightSecondButtonLabel) {
        if (!highlightSecondButtonLabel.isEmpty()) {
            boolean isCheck = convertStatus(highlightSecondButtonLabel);
            slideshowPage.highlightButtonlabel("Highlight second button label", isCheck);
        }

    }

    public void chooseAlignment(String textAlignment) {
        slideshowPage.clickLinkTextWithLabel(textAlignment);
    }

    public void chooseLayout(String layout) {
        slideshowPage.clickLinkTextWithLabel(layout);
    }

    public void chooseRatio(String ratio) {
        slideshowPage.clickLinkTextWithLabel(ratio);
    }

    public void checkUncheckFullWidthOnDesktop(Boolean isFullWidth) {
        slideshowPage.checkCheckboxWithLabel("Full width on desktop", isFullWidth);
    }

    public void enterBackgroundColor(String layout, String color) {
        if (layout.equalsIgnoreCase("Box")) {
            slideshowPage.enterInputFieldWithLabel("Background color", color);
        }
    }

    public void enterText(String text) {
        slideshowPage.enterText(text);
    }

    public void enterPrimaryBtnLab(String primaryBtnLab) {
        slideshowPage.enterInputFieldWithLabel("Primary button label", primaryBtnLab);
    }

    public void enterSecondaryBtnLab(String secondaryBtnLab) {
        slideshowPage.enterInputFieldWithLabel("Secondary button label", secondaryBtnLab);
    }

    @Step
    public void verifyImage(String image){
//        BufferedImage expectImage = ImageIO.read(new File(image));
//        BufferedImage actualImage;

//        ImageDiffer imgDiff = new ImageDiffer();
//        ImageDiff diff = imgDiff.makeDiff(actualImage, expectImage);
//        Assert.assertFalse("Result of Image comparsion", diff.hasDiff());
    }

    @Step
    public void verifyAltText(String altText){
        slideshowPage.verifyAltText(altText);
    }

    public void checkEnableParallaxScrolling(Boolean isParallaxScrolling) {
        slideshowPage.checkCheckboxWithLabel("Enable parallax scrolling",isParallaxScrolling);
    }

    public void verifyTextAlignment(String textAlignment) {
        slideshowPage.verifyTextAlignment(textAlignment);
    }

    public void verifyHeadling(String heading) {
        slideshowPage.verifyHeadling(heading);
    }

    public void verifySubheading(String subheading) {
        slideshowPage.verifySubheading(subheading);
    }

    public void verifyButtonLink(String buttonLink) {
        slideshowPage.verifyButtonLink(buttonLink);
    }

    public void verifyButtonLabel(String buttonLabel) {
        slideshowPage.verifyButtonLabel(buttonLabel);
    }

    public void verifyTextColor(String textColor) {
        slideshowPage.verifyTextColor(textColor);
    }

    public void verifyButtonLabelColor(String buttonLabelColor) {
        slideshowPage.verifyButtonLabelColor(buttonLabelColor);
    }

    public void verifyButtonColor(String buttonColor) {
        slideshowPage.verifyButtonColor(buttonColor);
    }

    public void isExistBlockSlideSF(boolean isCheck) {
        slideshowPage.isExistBlockSlideSF(isCheck);
    }

    public void verifyTextAnimation(String textAnimation) {
        slideshowPage.verifyTextAnimation(textAnimation);
    }

    public void verifyGalleryTransition(String galleryTransition) {
        slideshowPage.verifyGalleryTransition(galleryTransition);
    }

    public void verifyOpacity(String opacity) {
        slideshowPage.verifyOpacity(opacity);
    }

    public void verifyEnableParallaxScrolling(Boolean enableParallaxScrolling) {
        slideshowPage.verifyEnableParallaxScrolling(enableParallaxScrolling);
    }

    public void verifyBackgroundImage(String backgroundImage, int indexBlock) {
        if (!backgroundImage.isEmpty()) {
            slideshowPage.verifyImageExist(true, indexBlock);
        } else slideshowPage.verifyImageExist(false, indexBlock);
    }

    public void verifyAltText(String altText, int indexBlock) {
        slideshowPage.verifyAltText(altText,indexBlock);
    }

    public void verifyPreHeading(String preHeading, int indexBlock) {
        slideshowPage.verifyPreHeading(preHeading,indexBlock);
    }

    public void verifyHeading(String heading, int indexBlock) {
        slideshowPage.verifyHeading(heading,indexBlock);
    }

    public void verifySubHeading(String subHeading, int indexBlock) {
        slideshowPage.verifySubHeading(subHeading,indexBlock);
    }
    public void verifyTextPosition(String textPosition, int indexBlock) {
        slideshowPage.verifyTextPosition(textPosition,indexBlock);
    }

    public void verifyTextAlignment(String textAlignment, int indexBlock) {
        slideshowPage.verifyTextAlignment(textAlignment,indexBlock);
    }

    public void verifyFirstButtonLabel(String firstBtnLbl,boolean highlightBtn1, int indexBlock) {
        slideshowPage.verifyFirstButtonLabel(firstBtnLbl,highlightBtn1,indexBlock);
    }
    public void verifySecondaryButtonLabel(String secondBtnLbl,boolean highlightBtn2, int indexBlock) {
        slideshowPage.verifySecondaryButtonLabel(secondBtnLbl,highlightBtn2,indexBlock);
    }
}
