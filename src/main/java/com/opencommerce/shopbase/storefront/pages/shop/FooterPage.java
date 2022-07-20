package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FooterPage extends SBasePageObject {
    public FooterPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");

    public void verifyCopyrightText(String copyrightText) {
        String xpath ="//*[@class='footer-copyright subtle mb0' or @class='display-7 is-uppercase m0' or @class='footer-copyright justify-center subtle mb0 flex items-center']";
        String text = getText(xpath);
        assertThat(text.toUpperCase()).contains(copyrightText.toUpperCase());
    }


    public void verifyShowPaymentMethod(boolean showPaymentMethodIcons) {
        String xpath = "//*[contains(@class,'footer-credits')]//*";
        verifyElementPresent(xpath, showPaymentMethodIcons);
        if (showPaymentMethodIcons)
            assertThat(countElementByXpath(xpath)).isGreaterThan(0);
    }

    public void verifyShowSocialMedia(boolean showSocialMediaFooterIcons) {
        verifyElementPresent("//div[contains(@class,'footer-social')]", showSocialMediaFooterIcons);
        if (showSocialMediaFooterIcons)
            assertThat(countElementByXpath("//div[contains(@class,'footer-social')]//ul//a")).isGreaterThan(0);
    }

    String xpathContent = "(//div[contains(@class,'footer-item')])[%d]";

    public void verifyHeadingOfTitle(String content, int index) {
        if(theme.equalsIgnoreCase("Roller")){
            verifyElementText(String.format(xpathContent, index) + "//span", content.toUpperCase());
        }else {
            verifyElementText(String.format(xpathContent, index) + "//span", content.toUpperCase());
        }
    }

    public void verifyTextOfTitle(String value, int index) {
        verifyElementText(String.format(xpathContent, index) + "//div[@class='toggle_content']", value);
    }

    public void verifyAddress(String address) {
        switch (theme) {
            case "inside":
                if (address.isEmpty()) {
                    boolean status = isElementExist("//div[@class='mb20']//p");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//div[@class='mb20' or @class='site-footer__information p0']//p", address);
                }
                break;
            default:
                if (address.isEmpty()) {
                    boolean status = isElementExist("//ul[@class='mb20']//li");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//ul[@class='mb20']//li", address);
                }
        }
    }

    public void verifyEmail(String email) {
        switch (theme) {
            case "inside":
                if (email.isEmpty()) {
                    boolean status = isElementExist("//a[contains(@href,'mailto')][2]");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//a[contains(@href,'mailto')][1]", email);
                }
                break;
            default:
                if (email.isEmpty()) {
                    boolean status = isElementExist("//ul[@class='mb20']//li[3]");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//ul[@class='mb20']//li[3]", email);
                }
        }
    }

    public void verifyPhoneNumber(String phoneNumber) {
        switch (theme) {
            case "inside":
                if (phoneNumber.isEmpty()) {
                    boolean status = isElementExist("//a[contains(@href,'tel')][2]");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//a[contains(@href,'tel')][1]", phoneNumber);
                }
                break;
            default:
                if (phoneNumber.isEmpty()) {
                    boolean status = isElementExist("//ul[@class='mb20']//li[2]");
                    assertThat(status).isEqualTo(false);
                } else {
                    verifyElementContainsText("//ul[@class='mb20']//li[2]", phoneNumber);
                }
        }
    }

    public void verifyFullWidth(boolean fullWidth) {
        String xpath = "//div[@id='section-footer']//div[@class='container-fluid']";
        verifyElementPresent(xpath, fullWidth);
    }

    public void verifyText(String text) {
        String xpath = "//div[@class='site-footer__above']//p";
        verifyElementText(xpath, text);
    }

    public void verifyContentAlignment(String contentAlignment) {
        String xpath = "//footer[@class='site-footer text-align-center']";
        if (contentAlignment.equals("Center")) {
            verifyElementPresent(xpath, true);
        } else {
            verifyElementPresent(xpath, false);
        }
    }

    public void verifyFooterMenu(String footerMenu) {
        String xpath = "//ul[@class='site-footer__menu flex flex-wrap list-style-none p0 mb6']//li";
        if (footerMenu.equals("None")) {
            verifyElementPresent(xpath, false);
        } else {
            verifyElementPresent(xpath, true);
        }
    }

    public void verifyLogo(String logo) {
        if (!logo.equals("")) {
            if(theme.equalsIgnoreCase("Inside")) {
                verifyElementPresent("//div[@class='site-footer__above']//img", true);
            }else {
                verifyElementPresent("//div[contains(@class,'footer-logo')]//img",true);
            }
        }
    }

    public void verifySizeLogo(String size) {
        if(!size.isEmpty()) {
            waitForPageLoad();
            if (theme.equalsIgnoreCase("Inside")) {
                verifyElementPresent("//div[@class='site-footer__above']//img[@class='" + size + "']", true);
            }else {
                verifyElementPresent("//div[contains(@class,'footer-logo')]//img[@class='"+ size +"']",true);
            }
        }

    }

    public void verifyHeadingPbase(String type,String heading, boolean hideContentHeading) {
        String xpath ="//div[@id='section-footer']//div[@class='footer_content']//div[contains(@class,'footer__header')]//span";
        if(hideContentHeading) {
            if (heading.isEmpty()) {
                verifyElementContainsText(xpath, type.toUpperCase());
            } else verifyElementContainsText(xpath, heading.toUpperCase());
        }
    }

    public void veifyHideContentHeading(boolean hideContentHeading) {
        verifyElementPresent("//div[@id='section-footer']//div[@class='footer_content']//div[contains(@class,'footer__header')]//span",hideContentHeading);
    }

    public void verifyLogoPbase(String desktopLogo) {
        if(!desktopLogo.isEmpty())
            if(theme.equalsIgnoreCase("Inside")) {
            verifyElementPresent("//div[@class='footer_content']//img", true);
        } else{
                verifyElementPresent("//div[contains(@class,'footer-logo')]//img", true);
        }
    }

    public void verifySizeLogoPbase(String size) {
        waitForEverythingComplete();
        if(theme.equalsIgnoreCase("Inside")) {
            verifyElementPresent("//div[@class='footer_content']//img[@class='" + size + "']", true);
        }else {
            verifyElementPresent("//div[contains(@class,'footer-logo')]//img[@class='" + size + "']", true);
        }
    }
    public void verifyMenuFooter(String footerMenu) {
        String xpath = "//footer[@data-id='footer']//div[contains(@class,'footer__menu-container')]";
        if (footerMenu.equals("None")) {
            verifyElementPresent(xpath, false);
        } else {
            verifyElementPresent(xpath, true);
        }
    }

    public void verifyShowPaymentMethodIcons(boolean showPaymentMethodIcons) {
        verifyElementPresent("//div[contains(@class,'footer__payment-icon')]",showPaymentMethodIcons);
    }

    public void verifyEnableSocialProfile(boolean enableSocialProfile) {
        verifyElementPresent("//ul[contains(@class,'footer__social')]//li",enableSocialProfile);
    }

    public void verifyCustomText(String customText) {
        String customTextAR = getText("//footer[@data-id='footer']//div[contains(@class,'white-space-pre-line')]");
        assertThat(customTextAR).contains(customText);
    }

    public void verifyItemContent(int index, String item) {
        verifyElementPresent(String.format(xpathContent, index) + "//li[normalize-space()='"+ item +"']" , true);
    }

    public void verifyMainMenu(String mainMenu) {
        if (!mainMenu.equalsIgnoreCase("None")){
            verifyElementText("//div[contains(@class,'footer_menu')]//div[contains(@class,'h6')]//span",mainMenu.toUpperCase());
        }
    }

    public void verifyHeading(String heading) {
        verifyElementText("(//div[contains(@class,'footer_content')]//div[contains(@class,'h6')]//span)[1]",heading.toUpperCase());
    }

    public void verifyTextContent(String text) {
        verifyElementText("(//div[contains(@class,'footer_content')]//div[contains(@class,'toggle_content')]//div)[1]",text);
    }

    public void verifyContentPage(String contentPage) {
        verifyElementText("(//div[contains(@class,'footer_content')]//div[contains(@class,'h6')]//span)[2]",contentPage.toUpperCase());
    }
}
