package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VideoPage extends SBasePageObject {
    public VideoPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");
    String xpathVideoSection = "//section[contains(@data-id,'video') or @type='video-slider']//section";
    String xpathVideoActive = xpathVideoSection + "//div[contains(@class,'slide-active')]";

    public void verifyVideoLink(String videoLink) {
        boolean status = isElementExist("//section//iframe[contains(@src,'" + videoLink + "')]");
        assertThat(status).isEqualTo(true);
    }

    public void checkShowButton(String buttonlabel) {
        boolean status = isElementExist("//button[contains(@class,'btn') and normalize-space()='" + buttonlabel + "']");
        assertThat(status).isEqualTo(true);
    }

    public void checkHighLight(String buttonLabel, boolean isHightLight) {
        boolean status = false;
        if (isHightLight) {
            status = isElementExist("//button[contains(@class,'btn-primary') and normalize-space()='" + buttonLabel + "']");
        } else {
            status = isElementExist("//button[contains(@class,'btn-outline-white') and normalize-space()='" + buttonLabel + "']");
        }
        assertThat(true).isEqualTo(status);
    }

    public void clickOnButton(String buttonLabel) {
        clickOnElement("//button[contains(@class,'btn') and normalize-space()='" + buttonLabel + "']");
    }

    public void checkShowSectionVideoOnSF(boolean _isShow) {
        boolean status = isElementExist(xpathVideoSection);
        assertThat(status).isEqualTo(_isShow);
    }

    public void verifyDisplaySolidTextBackground(boolean displaySolidTextBackground) {
        boolean isShow;
        if (theme.equalsIgnoreCase("Roller")) {
            isShow = isElementExist(xpathVideoSection + "//div[contains(@class,'content-wrap')]//div[@class='overlay-content']");
        } else {
            isShow = isElementExist(xpathVideoActive + "//div[contains(@class,'content-wrap')]");
        }
        assertThat(isShow).isEqualTo(displaySolidTextBackground);
    }

    public void verifyPreHeading(String preheading) {
        verifyElementContainsText("//section[contains(@data-id,'video')]//div[@class='text-desktop']//div", preheading);
    }

    public void verifyHeading(String heading) {
        if (theme.equalsIgnoreCase("Inside") || theme.equalsIgnoreCase("Insidev2")) {
            verifyElementContainsText(xpathVideoActive + "//h4", heading);
        } else {
            verifyElementContainsText(xpathVideoSection + "//div[@class='text-desktop']//h3", heading);
        }
    }

    public void verifySubheading(String subheading) {
        if (theme.equalsIgnoreCase("Inside") || theme.equalsIgnoreCase("Insidev2")) {
            verifyElementContainsText(xpathVideoActive + "//p", subheading);
        } else {
            verifyElementContainsText(xpathVideoSection + "//div[@class='text-desktop']//p", subheading);
        }
    }

    public void scrollToSectionVideoOnSF() {
        scrollIntoElementView(xpathVideoSection);
    }

    public void inputVideoLink(String videoLink) {
        waitClearAndType("//div[@currentlayout and descendant::p[normalize-space()='Video link']]//input", videoLink);
    }

    public void inputPreOrSubHeading(String _blockName, String value) {
        waitClearAndType("//div[@currentlayout and descendant::p[normalize-space()='" + _blockName + "']]//textarea", value);
    }

    public void inputLabel(String _blockName, String heading) {
        waitClearAndType("//div[@currentlayout and descendant::p[normalize-space()='" + _blockName + "']]//input", heading);
    }

    public void inputSubheading(String subheading) {
        switchToIFrame("//iframe[contains(@title,'Rich Text Area')]");
        waitClearAndType("//body[@id='tinymce']", subheading);
        switchToIFrameDefault();
    }

    public void selectTextPosition(String position) {
        clickOnElementByJs("//div[@currentlayout and descendant::label[normalize-space()='Text position']]//p[normalize-space()='" + position + "']");
    }

    public void selectTextAlignment(String textAlignment) {
        clickOnElementByJs("//div[@currentlayout and descendant::label[normalize-space()='Text alignment']]//p[normalize-space()='" + textAlignment + "']");
    }

    public void verifyTextPosition(String textPosition) {
        if (theme.equalsIgnoreCase("Inside") || theme.equalsIgnoreCase("Insidev2")) {
            String key = "";
            if (textPosition.equalsIgnoreCase("Left")) {
                key = "justify-start";
            }
            if (textPosition.equalsIgnoreCase("Right")) {
                key = "justify-end";
            }
            if (textPosition.equalsIgnoreCase("Center")) {
                key = "justify-center";
            }
            verifyElementPresent(xpathVideoActive + "//div[contains(@class,'" + key + "')]", true);
        } else {
            verifyElementPresent(xpathVideoSection + "//div[contains(@class,'content-wrap--" + textPosition.toLowerCase() + "')]", true);
        }
    }

    public void verifyTextAlignment(String textAlignment) {
        if (theme.equalsIgnoreCase("Inside")) {
            verifyElementPresent(xpathVideoSection + "//div[contains(@class,'video-content-solid')]//div[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
        } else if (theme.equalsIgnoreCase("Insidev2")) {
            verifyElementPresent(xpathVideoActive + "//div[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
        } else {
            verifyElementPresent(xpathVideoSection + "//div[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
        }
    }

    public void verifyFullWidthSection(boolean fullWidthSection) {
        verifyElementPresent(xpathVideoSection + "//div[contains(@class,'container-fluid')]", fullWidthSection);
    }

    public void verifyText(String settings) {
        verifyElementPresent("//section[normalize-space()='" + settings + "']", true);
    }

    public void checkShowSectionVideo(String settings, boolean isShow) {
        verifyElementPresent("//section[normalize-space()='" + settings + "' or descendant::iframe[contains(@class,'video__container')]]", isShow);
    }

    public void chooseTextPosition(String label, String textPosition) {
        selectDdlWithLabel(label, textPosition);
    }

    public void chooseTextAlignment(String label, String textAlignment) {
        selectDdlWithLabel(label, textAlignment);
    }

    public void verifyImg(String image) {
        if (!image.isEmpty()) {
            verifyElementPresent(xpathVideoActive + "//img", true);
        }
    }

    public void clickPlayVideo() {
        clickOnElement(xpathVideoActive + "//div[contains(@class,'video-play-icon')]");
    }

    public void clickButtonNextVideo() {
        clickOnElement(xpathVideoSection + "//button[@aria-label='Next page']");
    }

    public void isExistBlockVideoOnSF(boolean isShow) {
        verifyElementPresent(xpathVideoSection + "//div[@class='video-section container']", isShow);
    }
}
