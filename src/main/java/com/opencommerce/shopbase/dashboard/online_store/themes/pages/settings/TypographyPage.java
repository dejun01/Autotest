package com.opencommerce.shopbase.dashboard.online_store.themes.pages.settings;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TypographyPage extends SBasePageObject {
    public TypographyPage(WebDriver driver) {
        super(driver);
    }
    String theme = LoadObject.getProperty("theme");

    String xpathSettingBySessionAndLabelName = "//div[@class='card'][descendant::*[text()='%s']]//div[@class='card__section' ]";

    public String getFontBySessionName(String sessionName) {
        String xpath = String.format(xpathSettingBySessionAndLabelName, sessionName) + "//div[@class='font-picker']//p[@class='font-picker__title s-mb8']";
        return getText(xpath);
    }

    public String getFontStyleBySessionName(String sessionName) {
        String xpath = String.format(xpathSettingBySessionAndLabelName, sessionName) + "//div[@class='font-picker']//p[@class='font-picker__style-name']";
        return getText(xpath);
    }

    public void verifyBaseSize(String sessionName, String baseSize) {
        if(theme.equalsIgnoreCase("bassy")){
            String basseSizeAR = getValue(String.format(xpathSettingBySessionAndLabelName, sessionName) + "//div[contains(@class,'s-select is-expanded')]//select");
            assertThat(basseSizeAR).contains(baseSize);
        }else {
            verifyElementText(String.format(xpathSettingBySessionAndLabelName, sessionName) + "//div[contains(@class,'s-flex--spacing-tight')]//label", baseSize);
        }
    }

    public void verifyCapitalize(String sessionName, String label, boolean isCheck) {
        String xpath = String.format(xpathSettingBySessionAndLabelName + "//div[@class='s-form-item checkbox'][descendant::*[normalize-space(text()='%s')]]", sessionName, label) + "//input[contains(@type,'checkbox')]";
        verifyElementSelected(xpath, isCheck);
    }


    public void clickBtnChange(String position) {
        clickOnElement("//div[@class='card' and descendant::span[normalize-space()='" + position + "']]//button[child::span[normalize-space()='Change']]");
    }

    public void searchFont(String value) {
        waitClearAndTypeThenEnter("//input[@placeholder='Search']", value);
    }

    public void chooseFont(String value) {
        clickOnElement("//ul//p[normalize-space()='" + value + "']");
    }

    public void closeSelectFontPopup() {
        clickOnElement("//span[@class='s-icon pointer is-small']");
    }

    public void verifyChangeFontSuccessfully(String position, String value) {
        verifyElementText("//div[@class='card' and descendant::span[normalize-space()='" + position + "']]//p[contains(@class,'font-picker__title')]", value);
    }
}
