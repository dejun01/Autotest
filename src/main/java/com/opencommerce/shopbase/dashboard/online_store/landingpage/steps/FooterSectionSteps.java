package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.FooterSectionsPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class FooterSectionSteps extends ScenarioSteps {
    FooterSectionsPages footerSectionsPages;

    @Step
    public void verifyShowLogo(boolean showLogo,String desktopLogo) {
        footerSectionsPages.verifyShowLogo(showLogo,desktopLogo);
    }

    @Step
    public void verifyDesktopLogo(boolean showLogo, String desktopLogo) {
        footerSectionsPages.verifyDesktopLogo(showLogo,desktopLogo);
    }

    @Step
    public void verifyMobileLogo(String mobileLogo) {
        footerSectionsPages.verifyMobileLogo(mobileLogo);
    }

    @Step
    public void verifyTagline(String tagline) {
        footerSectionsPages.verifyTagline(tagline);
    }

    @Step
    public void verifyPhone(String label,String phone) {
        footerSectionsPages.verifyContactInfo(label,phone);
    }

    @Step
    public void verifyEmail(String label,String email) {
        footerSectionsPages.verifyContactInfo(label,email);
    }

    @Step
    public void verifyFacebook(String label,String facebook) {
        footerSectionsPages.verifySocialLinks(label,facebook);
    }

    @Step
    public void verifyTwitter(String label,String twitter) {
        footerSectionsPages.verifySocialLinks(label,twitter);
    }

    @Step
    public void verifyYoutube(String label,String youtube) {
        footerSectionsPages.verifySocialLinks(label,youtube);
    }

    @Step
    public void verifyPinterest(String label,String pinterest) {
        footerSectionsPages.verifySocialLinks(label,pinterest);
    }

    @Step
    public void verifyInstagram(String label,String instagram) {
        footerSectionsPages.verifySocialLinks(label,instagram);
    }

    @Step
    public void verifyBlog(String label,String blog) {
        footerSectionsPages.verifySocialLinks(label,blog);
    }

    @Step
    public void verifyType(String type) {
        footerSectionsPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        footerSectionsPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        footerSectionsPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        footerSectionsPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        footerSectionsPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        footerSectionsPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        footerSectionsPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyDesktopLogoSize(boolean showLogo, String desktopLogo, String desktopLogoSize) {
        footerSectionsPages.verifyDesktopLogoSize(showLogo,desktopLogo,desktopLogoSize);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        footerSectionsPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        footerSectionsPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        footerSectionsPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyBodyColor(String bodyColor) {
        footerSectionsPages.verifyBodyColor(bodyColor);
    }

    @Step
    public void verifyBodyFont(String bodyFont) {
        footerSectionsPages.verifyBodyFont(bodyFont);
    }

    @Step
    public void verifyBodySize(String bodySize) {
        footerSectionsPages.verifyBodySize(bodySize);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor, String typeBackground) {
        footerSectionsPages.verifyBorderTopColor(borderTopColor, typeBackground);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize, String typeBackground) {
        footerSectionsPages.verifyBorderTopSize(borderTopSize, typeBackground);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor, String typeBackground) {
        footerSectionsPages.verifyBorderBottomColor(borderBottomColor, typeBackground);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize, String typeBackground) {
        footerSectionsPages.verifyBorderBottomSize(borderBottomSize,typeBackground);
    }

    @Step
    public void verifyMinHeight(String minHeight, String typeBackground) {
        footerSectionsPages.verifyMinHeight(minHeight,typeBackground);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        footerSectionsPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        footerSectionsPages.verifyCustomWidth(customWidth);
    }
}
