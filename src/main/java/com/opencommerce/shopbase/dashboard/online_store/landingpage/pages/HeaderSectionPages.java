package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class HeaderSectionPages extends CommonLandingPages {
    public HeaderSectionPages(WebDriver driver) {
        super(driver);
    }

    String xpath = "//header[contains(@class,'header-section')]";

    public void verifyShowLogo(boolean showLogo, String desktopLogo) {
        if (!desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//div[contains(@class,'lp-visible-lg')]//img", showLogo);
        }
    }

    public void verifyDesktopLogo(boolean showLogo, String desktopLogo) {
        if (showLogo && !desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//div[contains(@class,'lp-visible-lg')]//img", true);
        }
    }

    public void verifyButtonLabel(String buttonLabel) {
        if (!buttonLabel.isEmpty()) {
            verifyElementText(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]", buttonLabel);
        }
    }

    public void verifyButtonLink(String buttonLink) {
        if (!buttonLink.isEmpty()) {
            String linkAR = getAttributeValueRaw(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]", "href");
            assertThat(linkAR).contains(buttonLink);
        }
    }

    public void verifyMenuTitle(String menuTitle) {
        if (!menuTitle.isEmpty()) {
            verifyElementText(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-nav__link')]", menuTitle);
        }
    }

    public void verifyLink(String link, String menuTitle) {
        if (!link.isEmpty() && !menuTitle.isEmpty()) {
            String linkAR = getAttributeValueRaw(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-nav__link')]", "href");
            assertThat(linkAR).contains(link);
        }
    }

    public void verifyType(String typeBackground) {
        verifyTypeBackground(xpath + "//div[contains(@class,'lp-visible-lg')]",typeBackground);
    }

    public void verifyCustomColor(String customColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",convertColorHexToRGB(customColor),0);
    }

    public void verifyStartColor(String startColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",convertColorHexToRGB(startColor),0);
    }

    public void verifyEndColor(String endColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",convertColorHexToRGB(endColor),0);
    }

    public void verifyDirection(String directionBackground) {
        verifyDirectionBackground(xpath + "//div[contains(@class,'lp-visible-lg')]",directionBackground);
    }

    public void verifyDesktopLogoSize(boolean showLogo, String desktopLogo, String desktopLogoSize) {
        if (showLogo && !desktopLogo.isEmpty()) {
            verifyElementPresent(xpath + "//div[contains(@class,'lp-visible-lg')]//img[@class='" + desktopLogoSize + "']", true);
        }
    }

    public void verifyMenuItemColor(String menuItemColor, String menuTitle) {
        if (!menuTitle.isEmpty()) {
            verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//ul[contains(@class,'lp-list')]",convertColorHexToRGB(menuItemColor),2);

        }
    }

    public void verifyMenuItemFont(String menuItemFont, String menuTitle) {
        if (!menuTitle.isEmpty()) {
            verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//ul[contains(@class,'lp-list')]",menuItemFont,3);

        }
    }

    public void verifyMenuItemSize(String menuItemSize, String menuTitle) {
        if (!menuTitle.isEmpty()) {
            verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//ul[contains(@class,'lp-list')]",menuItemSize + "px",4);
        }
    }

    public void verifyButtonColor(String buttonColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]",convertColorHexToRGB(buttonColor),0);
    }

    public void verifyButtonLabelColor(String buttonLabelColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]",convertColorHexToRGB(buttonLabelColor),3);
    }

    public void verifyButtonLabelFont(String buttonLabelFont) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]",buttonLabelFont,4);
    }

    public void verifyButtonLabelSize(String buttonLabelSize) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//a[contains(@class,'lp-btn')]",buttonLabelSize + "px",5);
    }

    public void verifySubmenuBackgroundColor(String submenuBackgroundColor) {
    }

    public void verifySubmenuItemColor(String submenuItemColor) {
    }

    public void verifyBorderTopColor(String borderTopColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",convertColorHexToRGB(borderTopColor),1);
    }

    public void verifyBorderBottomColor(String borderBottomColor) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",convertColorHexToRGB(borderBottomColor),2);
    }

    public void verifyBorderBottomSize(String borderBottomSize) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]",borderBottomSize + "px",2);
    }

    public void verifyDesktopLayout(String desktopLayout) {
        boolean ischeck = true;
        switch (desktopLayout) {
            case "Inline":
                ischeck = false;
                break;
            case "Minimal":
                ischeck = true;
                break;
        }
        verifyElementPresent(xpath + "//div[contains(@class,'lp-visible-lg')]//div[contains(@class,'lp-hamburger')]", ischeck);
    }

    public void verifyAlignment(String alignment, boolean showLogo) {
        if (showLogo) {
            verifyElementPresent(xpath + "//div[contains(@class,'lp-visible-lg')]//div[contains(@class,'lp-justify-" + alignment + "')]", true);
        }
    }

    public void verifyMaxWidth(String maxWidth) {
        verifyMaxWidthBackground(xpath+ "//div[contains(@class,'lp-visible-lg')]",maxWidth);
    }

    public void verifyCustomWidth(String customWidth) {
        verifyStyleLanding(xpath + "//div[contains(@class,'lp-visible-lg')]//div[contains(@style,'max-width')]",customWidth,0);
    }
}
