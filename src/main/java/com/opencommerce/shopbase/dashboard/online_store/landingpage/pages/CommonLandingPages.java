package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CommonLandingPages extends SBasePageObject {
    public CommonLandingPages(WebDriver driver) {
        super(driver);
    }
    public void verifyStyleLanding(String xpath,String style, int index) {
        if (!style.isEmpty()) {
            String colorOverAR = getAttributeValueRaw(xpath, "style").split(";")[index].replace("background-color:", "").replace("color:", "").replace("font-family:", "").replace("font-size:", "").replace("text-align:", "").replace("max-width:", "").replace("min-height:", "").replace("border-bottom:", "").replace("border-top:", "").trim();
            assertThat(colorOverAR).contains(style);
        }
    }
    public void verifyStyleLandingShow(String xpath,String style,boolean isShowStyle, int index) {
        if (!style.isEmpty() && isShowStyle) {
            String colorOverAR = getAttributeValueRaw(xpath, "style").split(";")[index].replace("background-color:", "").replace("color:", "").replace("font-family:", "").replace("font-size:", "").replace("text-align:", "").replace("max-width:", "").replace("min-height:", "").replace("border-bottom:", "").replace("border-top:", "").trim();
            assertThat(colorOverAR).contains(style);
        }
    }
    public void verifyDirectionBackground(String xpath,String directionBackground) {
        String direction = "";
        switch (directionBackground) {
            case "Vertical":
                direction = "";
                break;
            case "Horizontal":
                direction = "to right";
                break;
            case "Diagonal":
                direction = "to right bottom";
                break;
        }
        String directionAR = getAttributeValueRaw(xpath, "style").split(";")[4].replace("background-image:", "").trim();
        assertThat(directionAR).contains(direction);
    }
    public void verifyMaxWidthBackground(String xpath,String maxWidth) {
        switch (maxWidth) {
            case "Default":
                verifyElementPresent(xpath + "//div[contains(@class,'lp-container')]", true);
                break;
            case "None":
                verifyElementPresent(xpath + "//div[contains(@class,'lp-section-padding')]", true);
                break;
            case "Custom":
                verifyElementPresent(xpath + "//div[contains(@style,'max-width')]", true);
                break;
        }
    }
    public void verifyTypeBackground(String xpath, String typeBackground) {
        String type = "";
        switch (typeBackground) {
            case "Gradient":
                type = "linear-gradient";
                break;
            case "Image":
                type = "url";
                break;
            case "Custom":
                type = "";
                break;
        }
        String typeAR = getAttributeValueRaw(xpath, "style").split(";")[4].trim();
        Assertions.assertThat(typeAR).contains(type);
    }
}
