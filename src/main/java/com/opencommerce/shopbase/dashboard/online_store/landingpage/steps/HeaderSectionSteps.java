package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.HeaderSectionPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class HeaderSectionSteps extends ScenarioSteps {
    HeaderSectionPages headerSectionPages;

    @Step
    public void verifyShowLogo(boolean showLogo, String desktopLogo) {
        headerSectionPages.verifyShowLogo(showLogo, desktopLogo);
    }

    @Step
    public void verifyDesktopLogo(boolean showLogo, String desktopLogo) {
        headerSectionPages.verifyDesktopLogo(showLogo, desktopLogo);
    }

    @Step
    public void verifyButtonLabel(String buttonLabel) {
        headerSectionPages.verifyButtonLabel(buttonLabel);
    }

    @Step
    public void verifyButtonLink(String buttonLink) {
        headerSectionPages.verifyButtonLink(buttonLink);
    }

    @Step
    public void verifyMenuTitle(String menuTitle) {
        headerSectionPages.verifyMenuTitle(menuTitle);
    }

    @Step
    public void verifyLink(String link, String menuTitle) {
        headerSectionPages.verifyLink(link, menuTitle);
    }

    @Step
    public void verifyType(String type) {
        headerSectionPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        headerSectionPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        headerSectionPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        headerSectionPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        headerSectionPages.verifyDirection(direction);
    }

    @Step
    public void verifyDesktopLogoSize(boolean showLogo, String desktopLogo, String desktopLogoSize) {
        headerSectionPages.verifyDesktopLogoSize(showLogo, desktopLogo, desktopLogoSize);
    }

    @Step
    public void verifyMenuItemColor(String menuItemColor, String menuTitle) {
        headerSectionPages.verifyMenuItemColor(menuItemColor, menuTitle);
    }

    @Step
    public void verifyMenuItemFont(String menuItemFont, String menuTitle) {
        headerSectionPages.verifyMenuItemFont(menuItemFont, menuTitle);
    }

    @Step
    public void verifyMenuItemSize(String menuItemSize, String menuTitle) {
        headerSectionPages.verifyMenuItemSize(menuItemSize, menuTitle);
    }

    @Step
    public void verifyButtonColor(String buttonColor) {
        headerSectionPages.verifyButtonColor(buttonColor);
    }

    @Step
    public void verifyButtonLabelColor(String buttonLabelColor) {
        headerSectionPages.verifyButtonLabelColor(buttonLabelColor);
    }

    @Step
    public void verifyButtonLabelFont(String buttonLabelFont) {
        headerSectionPages.verifyButtonLabelFont(buttonLabelFont);
    }

    @Step
    public void verifyButtonLabelSize(String buttonLabelSize) {
        headerSectionPages.verifyButtonLabelSize(buttonLabelSize);
    }

    @Step
    public void verifySubmenuBackgroundColor(String submenuBackgroundColor) {
        headerSectionPages.verifySubmenuBackgroundColor(submenuBackgroundColor);
    }

    @Step
    public void verifySubmenuItemColor(String submenuItemColor) {
        headerSectionPages.verifySubmenuItemColor(submenuItemColor);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        headerSectionPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        headerSectionPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        headerSectionPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyDesktopLayout(String desktopLayout) {
        headerSectionPages.verifyDesktopLayout(desktopLayout);
    }

    @Step
    public void verifyAlignment(String alignment, boolean showLogo) {
        headerSectionPages.verifyAlignment(alignment, showLogo);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        headerSectionPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        headerSectionPages.verifyCustomWidth(customWidth);
    }
}
