package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroSectionPages extends CommonLandingPages {
    public HeroSectionPages(WebDriver driver) {
        super(driver);
    }

    String xpath = "//section[contains(@class,'hero')]";
    public void verifyTile(String title) {
        verifyElementText(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//p",title);
    }

    public void verifyBodyText(String body) {
        verifyElementText(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//div",body);
    }

    public void verifyButtonLabel(String buttonLabel) {
        verifyElementText(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a",buttonLabel);
    }

    public void verifyButtonLink(String buttonLink) {
        String linkAR = getAttributeValueRaw(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a","href");
        assertThat(linkAR).contains(buttonLink);
    }

    public void verifyTypeBackground(String typeBackground) {
        verifyTypeBackground(xpath+"//div[contains(@class,'lp-flex lp-justify')]",typeBackground);
    }

    public void verifyCustomColorBackground(String customColorBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",convertColorHexToRGB(customColorBackground),4);
    }

    public void verifyStartColorBackground(String startColorBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",convertColorHexToRGB(startColorBackground),4);
    }

    public void verifyEndColorBackground(String endColorBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",convertColorHexToRGB(endColorBackground),4);
    }

    public void verifyDirectionBackground(String directionBackground) {
        verifyDirectionBackground(xpath+"//div[contains(@class,'lp-flex lp-justify')]",directionBackground);
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-overlay')]",convertColorHexToRGB(colorOverlayBackground),0);
    }

    public void verifyTitleColorTypography(String titleColorTypography) {
        verifyStyleLanding(xpath+"//p",convertColorHexToRGB(titleColorTypography),0);
    }

    public void verifyTitleFontTypography(String titleFontTypography) {
        verifyStyleLanding(xpath+"//p",titleFontTypography,1);
    }

    public void verifyTitleSizeTypography(String titleSizeTypography) {
        verifyStyleLanding(xpath+"//p",titleSizeTypography+"px",2);
    }

    public void verifyBodyColorTypography(String bodyColorTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//div",convertColorHexToRGB(bodyColorTypography),0);
    }

    public void verifyBodyFontTypography(String bodyFontTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//div",bodyFontTypography,1);
    }

    public void verifyBodySizeTypography(String bodySizeTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//div",bodySizeTypography+"px",2);
    }

    public void verifyButtonColorTypography(String buttonColorTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a",convertColorHexToRGB(buttonColorTypography),5);
    }

    public void verifyButtonLabelColorTypography(String buttonLabelColorTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a",convertColorHexToRGB(buttonLabelColorTypography),0);
    }

    public void verifyButtonLabelFontTypography(String buttonLabelFontTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a",buttonLabelFontTypography,1);
    }

    public void verifyButtonLabelSizeTypography(String buttonLabelSizeTypography) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a",buttonLabelSizeTypography+"px",2);
    }

    public void verifyBorderTopColorSeparator(String borderTopColorSeparator) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",convertColorHexToRGB(borderTopColorSeparator),0);
    }

    public void verifyBorderBottomColorSeparator(String borderBottomColorSeparator) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",convertColorHexToRGB(borderBottomColorSeparator),1);
    }

    public void verifyPositionLayout(String positionLayout) {
        verifyElementPresent(xpath+"//div[contains(@class,'lp-flex lp-justify-"+positionLayout+"')]",true);
    }

    public void verifyAlignmentLayout(String alignmentLayout) {
    }

    public void verifyMinHeightLayout(String minHeightLayout,String typeBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-flex lp-justify')]",minHeightLayout,5);
    }

    public void verifyMaxWidthLayout(String maxWidthLayout) {
    }

    public void verifyAlignmenTitle(String alignmentLayout) {
        verifyStyleLanding(xpath+"//p",alignmentLayout,3);
    }

    public void verifyAlignmenBody(String alignmentLayout) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//div",alignmentLayout,3);
    }

    public void verifyAlignmenButton(String alignmentLayout) {
        String alignment = "";
        switch (alignmentLayout){
            case "left":
                alignment = "start";
                break;
            case "center":
                alignment = "center";
                break;
            case "right":
                alignment = "end";
                break;
        }
        if(!alignmentLayout.isEmpty()){
            String alignmentAR = getAttributeValueRaw(xpath+"//div[contains(@class,'lp-hero__text-wrapper')]//a", "style").split(";")[7].replace("align-self:", "").trim();
            assertThat(alignmentAR).contains(alignment);
        }
    }
}
