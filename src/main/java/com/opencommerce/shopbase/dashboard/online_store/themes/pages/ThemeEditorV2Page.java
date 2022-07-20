package com.opencommerce.shopbase.dashboard.online_store.themes.pages;

import com.opencommerce.shopbase.common.pages.CommonThemeEditorPage;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ThemeEditorV2Page extends CommonThemeEditorPage {
    public ThemeEditorV2Page(WebDriver driver) {
        super(driver);
    }
    String theme = LoadObject.getProperty("theme");

    public int countSectionByXpath(String sectionName) {
        return countElement(String.format(xpathSection, sectionName));
    }

    public int countBlockByXpath(String blockName, String sectionName) {
        return countElement("//*[normalize-space() = '"+ sectionName +"']//following-sibling::*[contains(@class,'theme-editor-v2__section')]//span[contains(text(),'"+ blockName +"')]");
    }

    public void clickButtonBack(){
        clickOnElementByJs("//button[contains(@style,'visibility: visible;')]");
    }

    public boolean isExistImage(String label){
        return isElementExistNow(String.format(xpathElementInsideBlock, label) + "//img");
    }

    public void selectRadioButton(String value) {
        selectRadioButtonWithLabel("//div[@class='theme-editor-v2__input-radio']", value, 1, true);
    }
    public void isExistSectionSF(String nameSection, boolean status) {
        if(theme.equalsIgnoreCase("RollerV3")){
            verifyElementPresent("//section[contains(@type,'" + nameSection.toLowerCase() + "')]//section[contains(@class,'" + nameSection.toLowerCase() + "')]", status);
        }else {
            verifyElementPresent("//*[contains(@type,'" + nameSection.toLowerCase() + "')]", status);
        }
    }

    public void selectSettingsSection(String _section) {
        waitElementToBeVisible("//div[contains(@class,'theme-editor-v2__new-element')]//span[text()='"+_section+"']");
        clickOnElementByJs("//div[contains(@class,'theme-editor-v2__new-element')]//span[text()='"+_section+"']");
    }
}
