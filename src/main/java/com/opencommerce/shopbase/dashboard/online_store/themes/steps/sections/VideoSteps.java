package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.VideoPage;
import net.thucydides.core.annotations.Step;

public class VideoSteps {
    VideoPage videoPage;

    @Step
    public void verifyVideoLink(String videoLink) {
        videoPage.verifyVideoLink(videoLink);
    }

    @Step
    public void verifyButton(String buttonLabel, boolean isHighLight) {
        videoPage.checkShowButton(buttonLabel);
        videoPage.checkHighLight(buttonLabel, isHighLight);
    }

    @Step
    public void clickOnButton(String buttonLabel) {
        videoPage.clickOnButton(buttonLabel);
    }

    @Step
    public void checkShowSectionVideoOnSF(boolean isShow) {
        videoPage.checkShowSectionVideoOnSF(isShow);
    }

    @Step
    public void verifyDisplaySolidTextBackground(boolean displaySolidTextBackground) {
        videoPage.verifyDisplaySolidTextBackground(displaySolidTextBackground);
    }

    @Step
    public void verifyPreHeading(String preheading) {
        videoPage.verifyPreHeading(preheading);
    }

    @Step
    public void verifyHeading(String heading) {
        videoPage.verifyHeading(heading);
    }

    @Step
    public void verifySubheading(String subheading) {
        videoPage.verifySubheading(subheading);
    }

    @Step
    public void scrollToSectionVideoOnSF() {
        videoPage.scrollToSectionVideoOnSF();
    }

    public void checkAutoplayVideo(String _blockName, boolean autoplay) {
        videoPage.checkCheckboxWithLabel(_blockName, autoplay);
    }

    @Step
    public void inputVideoLink(String videoLink) {
        videoPage.inputVideoLink(videoLink);
    }

    @Step
    public void inputPreOrSubHeading(String _blockName, String preheading) {
        videoPage.inputPreOrSubHeading(_blockName, preheading);
    }

    @Step
    public void inputLabel(String _blockName, String value) {
        videoPage.inputLabel(_blockName, value);
    }

    @Step
    public void checkCheckboxWithLabel(String _labelName, boolean isCheck) {
        videoPage.checkCheckboxWithLabel(_labelName, isCheck);
    }

    @Step
    public void inputSubheading(String subheading){
        videoPage.inputSubheading(subheading);
    }

    @Step
    public void selectTextPosition(String position){
        videoPage.selectTextPosition(position);
    }

    @Step
    public void selectTextAlignment(String textAlignment) {
        videoPage.selectTextAlignment(textAlignment);
    }

    @Step
    public void verifyFullWidthSection(boolean fullWidthSection) {
        videoPage.verifyFullWidthSection(fullWidthSection);
    }

    @Step
    public void verifyTextPosition(String textPosition) {
        videoPage.verifyTextPosition(textPosition);
    }

    @Step
    public void verifyTextAlignment(String textAlignment) {
        videoPage.verifyTextAlignment(textAlignment);
    }

    @Step
    public void verifyText(String settings) {
        videoPage.verifyText(settings);
    }

    @Step
    public void checkShowSectionVideo(String settings, boolean isShow) {
        videoPage.checkShowSectionVideo(settings, isShow);
    }

    public void chooseTextPosition(String label,String textPosition) {
        videoPage.chooseTextPosition(label,textPosition);
    }

    public void chooseTextAlignment(String label, String textAlignment) {
        videoPage.chooseTextAlignment(label,textAlignment);
    }

    public void verifyImg(String image) {
        videoPage.verifyImg(image);
    }

    public void clickPlayVideo() {
        videoPage.clickPlayVideo();
    }

    public void clickButtonNextVideo() {
        videoPage.clickButtonNextVideo();
    }

    public void isExistBlockVideoOnSF(boolean isShow) {
        videoPage.isExistBlockVideoOnSF(isShow);
    }
}
