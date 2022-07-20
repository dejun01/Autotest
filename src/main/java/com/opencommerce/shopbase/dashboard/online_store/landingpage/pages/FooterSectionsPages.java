package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class FooterSectionsPages extends CommonLandingPages {
    public FooterSectionsPages(WebDriver driver) {
        super(driver);
    }

    String xpath = "//footer[contains(@data-id,'footer')]";

    public void verifyCustomWidth(String customWidth) {
        verifyStyleLanding(xpath+"//div[contains(@style,'max-width')]", customWidth, 0);
    }

    public void verifyMaxWidth(String maxWidth) {
        verifyMaxWidthBackground(xpath,maxWidth);
    }

    public void verifyMinHeight(String minHeight, String typeBackground) {
        verifyStyleLanding(xpath, minHeight, 5);
    }

    public void verifyBorderBottomSize(String borderBottomSize, String typeBackground) {
        verifyStyleLanding(xpath, borderBottomSize + "px", 1);
    }

    public void verifyBorderBottomColor(String borderBottomColor, String typeBackground) {
        verifyStyleLanding(xpath, convertColorHexToRGB(borderBottomColor), 1);
    }

    public void verifyBorderTopSize(String borderTopSize, String typeBackground) {
        verifyStyleLanding(xpath, borderTopSize + "px", 0);
    }

    public void verifyBorderTopColor(String borderTopColor, String typeBackground) {
        verifyStyleLanding(xpath, convertColorHexToRGB(borderTopColor), 0);
    }

    public void verifyBodySize(String bodySize) {
        if (!bodySize.isEmpty()) {
            verifyCssValueByElement(xpath + "//a[normalize-space()='facebook']", "font-size", bodySize + "px");
        }
    }

    public void verifyBodyFont(String bodyFont) {
        if (!bodyFont.isEmpty()) {
            verifyCssValueByElement(xpath + "//a[normalize-space()='facebook']", "font-family", bodyFont);
        }
    }

    public void verifyBodyColor(String bodyColor) {
        verifyStyleLanding(xpath + "//a[normalize-space()='facebook']", convertColorHexToRGB(bodyColor), 0);
    }

    public void verifyTitleSize(String titleSize) {
        verifyStyleLanding(xpath + "//li[normalize-space()='SOCIAL']", titleSize + "px", 2);
    }

    public void verifyTitleFont(String titleFont) {
        verifyStyleLanding(xpath + "//li[normalize-space()='SOCIAL']", titleFont, 1);
    }

    public void verifyTitleColor(String titleColor) {
        verifyStyleLanding(xpath + "//li[normalize-space()='SOCIAL']", convertColorHexToRGB(titleColor), 0);
    }

    public void verifyDesktopLogoSize(boolean showLogo, String desktopLogo, String desktopLogoSize) {
        if (showLogo && !desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//img[@class='" + desktopLogoSize + "']", true);
        }
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-overlay')]", convertColorHexToRGB(colorOverlayBackground), 0);
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyDirection(String directionBackground) {
        verifyDirectionBackground(xpath, directionBackground);
    }

    public void verifyEndColor(String endColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(endColor), 4);
    }

    public void verifyStartColor(String startColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(xpath), 4);
    }

    public void verifyCustomColor(String customColor) {
        verifyStyleLanding(xpath, convertColorHexToRGB(customColor), 4);
    }

    public void verifyType(String typeBackground) {
        verifyTypeBackground(xpath, typeBackground);
    }

    public void verifySocialLinks(String label, String Social) {
        if (!Social.isEmpty()) {
            String textAR = getAttributeValue(xpath + "//a[normalize-space()='" + label + "']", "href");
            assertThat(textAR).contains(Social);
        }
    }

    public void verifyTagline(String tagline) {
        if (!tagline.isEmpty()) {
            verifyElementText(xpath + "//div[contains(@class,'lp-col-12 lp-col-lg-7')]//p", tagline);
        }
    }

    public void verifyContactInfo(String label, String contact) {
        if (!contact.isEmpty()) {
            verifyElementText(xpath + "//li[normalize-space()='" + label + "']//following-sibling::li[1]//a", contact);
        }
    }

    public void verifyMobileLogo(String mobileLogo) {
    }

    public void verifyDesktopLogo(boolean showLogo, String desktopLogo) {
        if (showLogo && !desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//img", true);
        }
    }

    public void verifyShowLogo(boolean showLogo, String desktopLogo) {
        if (!desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//img", showLogo);
        }
    }
}
