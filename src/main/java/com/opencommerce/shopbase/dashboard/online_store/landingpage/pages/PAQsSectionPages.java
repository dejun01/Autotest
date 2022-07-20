package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class PAQsSectionPages extends CommonLandingPages {
    public PAQsSectionPages(WebDriver driver) {
        super(driver);
    }
    String xpath = "//section[contains(@data-id,'faq')]";
    public void verifyHeading(String heading) {
        if(!heading.isEmpty()){
            verifyElementText("//section[contains(@data-id,'faq')]//h2",heading);
        }
    }

    public void verifyOpenTheFirstContentByDefault(boolean openTheFirstContentByDefault) {
        verifyElementPresent("//section[contains(@data-id,'faq')]//div[contains(@class,'body--expanded')]",openTheFirstContentByDefault);
    }

    public void verifyType(String type) {
    }

    public void verifyTypeBackground(String typeBackground) {
        verifyTypeBackground(xpath,typeBackground);
    }

    public void verifyCustomColorBackground(String customColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(customColorBackground),4);
    }

    public void verifyStartColorBackground(String startColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(startColorBackground),4);
    }

    public void verifyEndColorBackground(String endColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(endColorBackground),4);
    }

    public void verifyDirectionBackground(String directionBackground) {
        verifyDirectionBackground(xpath,directionBackground);
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-overlay')]",convertColorHexToRGB(colorOverlayBackground),0);
    }

    public void verifyHeadingColor(String headingColor) {
        verifyStyleLanding(xpath+"//h2",convertColorHexToRGB(headingColor),2);
    }

    public void verifyHeadingFont(String headingFront) {
        verifyStyleLanding(xpath+"//h2",headingFront,3);
    }

    public void verifyHeadingSize(String headingSize) {
        verifyStyleLanding(xpath+"//h2",headingSize+"px",4);
    }

    public void verifyTitleColor(String titleColor) {
        verifyStyleLanding(xpath+"//h3",convertColorHexToRGB(titleColor),2);
    }

    public void verifyTitleFont(String titleFont) {
        verifyStyleLanding(xpath+"//h3",titleFont,3);
    }

    public void verifyTitleSize(String titleSize) {
        verifyStyleLanding(xpath+"//h3",titleSize+"px",4);
    }

    public void verifyBodyColor(String bodyColor,boolean openTheFirstContentByDefault) {
        verifyStyleLandingShow(xpath+"//div[contains(@class,'lp-accordion__body')]//div",convertColorHexToRGB(bodyColor),openTheFirstContentByDefault,2);
    }

    public void verifyBodyFont(String bodyFont,boolean openTheFirstContentByDefault) {
        verifyStyleLandingShow(xpath+"//div[contains(@class,'lp-accordion__body')]//div",bodyFont,openTheFirstContentByDefault,3);
    }

    public void verifyBodySize(String bodySize,boolean openTheFirstContentByDefault) {
        verifyStyleLandingShow(xpath+"//div[contains(@class,'lp-accordion__body')]//div",bodySize+"px",openTheFirstContentByDefault,4);
    }

    public void verifyBorderTopColor(String borderTopColor,String typeBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(borderTopColor),0);
    }

    public void verifyBorderTopSize(String borderTopSize, String typeBackground) {
        verifyStyleLanding(xpath,borderTopSize+"px",0);
    }

    public void verifyBorderBottomColor(String borderBottomColor,String typeBackground ) {
        verifyStyleLanding(xpath,convertColorHexToRGB(borderBottomColor),1);
    }

    public void verifyBorderBottomSize(String borderBottomSize, String typeBackground ) {
        verifyStyleLanding(xpath,borderBottomSize+"px",1);
    }

    public void verifyHeadingAlignment(String headingAlignment, String heading) {
        if (!heading.isEmpty()){
             verifyElementPresent(xpath+"//h2[contains(@class,'lp-text-align-"+headingAlignment+"')]",true);
        }
    }

    public void verifyMinHeight(String minHeight,String typeBackground) {
                String minHeightAR = getAttributeValueRaw(xpath, "style").split(";")[5].replace("min-height:", "").trim();
                assertThat(minHeightAR).contains(minHeight);
    }

    public void verifyMaxWidth(String maxWidth) {
        verifyMaxWidthBackground(xpath,maxWidth);
    }

    public void verifyCustomWidth(String customWidth) {
        verifyStyleLanding(xpath+"//div[contains(@style,'max-width')]",customWidth,0);
    }

    public void verifyTitle(String title) {
        if(!title.isEmpty()){
            verifyElementText("//section[contains(@data-id,'faq')]//h3",title);
        }
    }

    public void verifybody(String body,boolean openTheFirstContentByDefault) {
        if (!body.isEmpty()&& openTheFirstContentByDefault){
            verifyElementText("//section[contains(@data-id,'faq')]//p",body);
        }
    }
}
