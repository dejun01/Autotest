package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class RichTextPages extends CommonLandingPages {
    public RichTextPages(WebDriver driver) {
        super(driver);
    }
    String xpath = "//section[contains(@data-id,'rich_text')]";
    public void verifyTitle(String title, String positionLayout) {
        if (!title.isEmpty()) {
            if (positionLayout.equalsIgnoreCase("Inline")) {
                verifyElementText("//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-col-6 lp-mb16')]", title);
            } else {
                verifyElementText("//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-mb16')]", title);
            }
        }
    }

    public void verifyBody(String body, String positionLayout) {
        if (!body.isEmpty()) {
            if (positionLayout.equalsIgnoreCase("Inline")) {
                verifyElementText("//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-col-6')]//div[contains(@class,'lp-mb16')]", body);
            } else {
                verifyElementText("//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-row')]//div[contains(@class,'lp-mb24')]", body);
            }
        }
    }

    public void verifyButtonLabel(String buttonLabel) {
        verifyElementText("//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-text')]//a", buttonLabel);
    }

    public void verifyButtonLink(String buttonLink) {
        String buttonLinkAR = getAttributeValueRaw("//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-text')]//a", "href");
        assertThat(buttonLinkAR).contains(buttonLink);
    }

    public void verifyTypeBackground(String typeBackground) {
        verifyTypeBackground("//section[contains(@data-id,'rich_text')]",typeBackground);
    }

    public void verifyCustomColorBackground(String customColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(customColorBackground),0);
    }

    public void verifyStartColorBackground(String startColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(startColorBackground),0);
    }

    public void verifyEndColorBackground(String endColorBackground) {
        verifyStyleLanding(xpath,convertColorHexToRGB(endColorBackground),0);
    }

    public void verifyDirectionBackground(String directionBackground) {
        verifyDirectionBackground(xpath,directionBackground);
    }

    public void verifyImageBackground(String imageBackground) {
    }

    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-overlay')]",convertColorHexToRGB(colorOverlayBackground),0);
    }

    public void verifyTitleColorTypography(String titleColorTypography, String positionLayout, String title) {
        if (!title.isEmpty()) {
            String xpathTitle;
            if (!titleColorTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-col-6 lp-mb16')]";
                } else {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-mb16')]";
                }
                verifyStyleLanding(xpathTitle,titleColorTypography,2);
            }
        }
    }

    public void verifyTitleFontTypography(String titleFontTypography, String positionLayout, String title) {
        if (!title.isEmpty()) {
            String xpathTitle;
            if (!titleFontTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-col-6 lp-mb16')]";
                } else {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-mb16')]";
                }
                verifyStyleLanding(xpathTitle,titleFontTypography,3);
            }
        }
    }

    public void verifyTitleSizeTypography(String titleSizeTypography, String positionLayout, String title) {
        if (!title.isEmpty()) {
            String xpathTitle;
            if (!titleSizeTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-col-6 lp-mb16')]";
                } else {
                    xpathTitle = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'container')]//div[contains(@class,'lp-mb16')]";
                }
                verifyStyleLanding(xpathTitle,titleSizeTypography + "px",4);
            }
        }
    }

    public void verifyBodyColorTypography(String bodyColorTypography, String positionLayout, String body) {
        if (!body.isEmpty()) {
            String xpathBody;
            if (!bodyColorTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-col-6')]//div[contains(@class,'lp-mb16')]";
                } else {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-row')]//div[contains(@class,'lp-mb24')]";
                }
                verifyStyleLanding(xpathBody,bodyColorTypography,2);
            }
        }
    }

    public void verifyBodyFontTypography(String bodyFontTypography, String positionLayout, String body) {
        if (!body.isEmpty()) {
            String xpathBody;
            if (!bodyFontTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-col-6')]//div[contains(@class,'lp-mb16')]";
                } else {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-row')]//div[contains(@class,'lp-mb24')]";
                }
                verifyStyleLanding(xpathBody,bodyFontTypography,3);
            }
        }
    }

    public void verifyBodySizeTypography(String bodySizeTypography, String positionLayout, String body) {
        if (!body.isEmpty()) {
            String xpathBody;
            if (!bodySizeTypography.isEmpty()) {
                if (positionLayout.equalsIgnoreCase("Inline")) {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-col-6')]//div[contains(@class,'lp-mb16')]";
                } else {
                    xpathBody = "//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-row')]//div[contains(@class,'lp-mb24')]";
                }
                verifyStyleLanding(xpathBody,bodySizeTypography+"px",4);
            }
        }
    }

    public void verifyTypeButton(String typeButton) {
        verifyElementPresent("//section[contains(@data-id,'rich_text')]//div[contains(@class,'lp-text')]//a[contains(@class,'" + typeButton + "')]", true);
    }

    public void verifyButtonColorTypography(String buttonColor) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",buttonColor,2);
    }

    public void verifyButtonLabelColor(String buttonLabelColor) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",buttonLabelColor,3);
    }

    public void verifyButtonLabelFont(String buttonLabelFont) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",buttonLabelFont,4);
    }

    public void verifyButtonLabelSize(String buttonLabelSize) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",buttonLabelSize+"px",5);
    }

    public void verifyBorderTopColorSeparator(String borderTopColorSeparator, String typeBackground) {
        verifyStyleLanding(xpath,borderTopColorSeparator,1);
    }

    public void verifyBorderBottomColorSeparator(String borderBottomColorSeparator, String typeBackground) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",borderBottomColorSeparator,2);
    }

    public void verifyPositionLayout(String positionLayout) {
        if (positionLayout.equalsIgnoreCase("Inline")) {
            verifyElementPresent("//section[contains(@data-id,'rich_text')]//div[@class='lp-row']", true);
        } else {
            verifyElementPresent("//section[contains(@data-id,'rich_text')]//div[@class='lp-col-lg-6 lp-mb16']", true);
        }
    }

    public void verifyAlignmentLayout(String alignmentLayout, String positionLayout) {
        verifyStyleLanding(xpath+"//div[@class='lp-col-6 lp-mb16']",alignmentLayout,3);
        String alignAR;
        if (positionLayout.equalsIgnoreCase("Inline")) {
            verifyStyleLanding(xpath+"//div[@class='lp-col-6 lp-mb16']",alignmentLayout,5);
        } else {
            verifyStyleLanding(xpath+"//div[@class='lp-col-lg-6 lp-mb16']",alignmentLayout,5);
        }
    }

    public void verifyMinHeightLayout(String minHeightLayout, String typeBackground) {
        verifyStyleLanding(xpath,minHeightLayout,6);
    }

    public void verifyMaxWidthLayout(String maxWidthLayout) {
        verifyMaxWidthBackground(xpath,maxWidthLayout);
    }

    public void verifyLinkColor(String linkColor) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",linkColor,2);
    }

    public void verifyLinkFont(String linkFont) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",linkFont,3);
    }

    public void verifyLinkSize(String linkSize) {
        verifyStyleLanding(xpath+"//div[contains(@class,'lp-text')]//a",linkSize+"px",4);
    }

    public void verifyCustomWidth(String customWidth) {
        verifyStyleLanding(xpath+"//div[contains(@style,'max-width')]",customWidth,0);
    }
}
