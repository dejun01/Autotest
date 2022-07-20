package com.opencommerce.shopbase.dashboard.online_store.themes.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ThemeEditorPage extends SBasePageObject {

    public ThemeEditorPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");
    public int MAX_RETRY_TIME = 10;

    public void verifyMessageSaveSuccess(int currentRetryTime) {
        try {
            waitForTextToAppear("All changes were successfully saved", 10000);
        } catch (Throwable t) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(3000);
                verifyMessageSaveSuccess(currentRetryTime + 1);
            }
        }

    }

    public void clickSection(String section) {
        isElementExist("(//div[@class='honiv'][descendant::p[normalize-space()='" + section + "']])[1]");
        clickOnElementByJs("(//div[@class='honiv'][descendant::p[normalize-space()='" + section + "']])[1]");
        waitForPageLoad();
    }

    public void choosePreview(String page) {
        String xpath = "//option[normalize-space()='" + page + "']";
        waitUntilElementVisible(xpath, 50);
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void verify(String menu) {
        if (menu.equals("None")) {
            verifyElementPresent("//div[@class='header-nav-container visible-lg col-lg-8 center-lg center-xl']//li", false);
        } else {
            verifyElementPresent("//div[@class='header-nav-container visible-lg col-lg-8 center-lg center-xl']//li", true);
        }
    }

    public void clickIconNavigatetoHomepage() {
        waitForPageLoad();
        waitABit(2000);
        clickOnElement("//i[@class='mdi mdi-open-in-new mdi-18px']");
    }

    //-----------------------------

    public void navigateToSettingTab() {
        clickOnElement("//p[normalize-space()='Settings']");
    }

    public void navigateToSectionsTab() {
        clickOnElement("//p[contains(text(),'Sections')]");
    }

    public void verifyPopupShown(String namePopup) {
        verifyElementPresent("//div[contains(@class,'s-modal is-active')]//*[@class='s-modal-header' and child::*[text()='" + namePopup + "']]", true);
    }

    public void selectStyle(String themeStyle) {
        String layout = "//*[@class='style-selector__items']//div[@class='style-selector__item' and child::*[normalize-space()='" + themeStyle + "']]";
        hoverThenClickElement(layout, layout + "//span[text()='Select']");
    }

    public void closeSessionSetting() {
        try {
            scrollTo(0, 0);
            clickOnElement("//*[@class='arrow-back']");
        } catch (Exception e) {
            refreshPage();
            waitForEverythingComplete();
        }

    }

    public int countQuantitySections() {
        return countElementByXpath("//div[@class='draggable']//*[contains(@class,'mdi-eye')]");
    }

    String xpathSection = "//div[parent::div[@class='draggable']][descendant::*[contains(@class,'mdi-eye')]][descendant::p[normalize-space(text())='%s']]";

    public void turnOnSectionByIndex(String sectionName, boolean isOn) {
        boolean isStatus = true;
        String xpath = String.format(xpathSection, sectionName) + "//i[contains(@class,'mdi-eye')]";
        String getStatus = getAttributeValue(xpath, "class");
        if (getStatus.contains("off")) {
            isStatus = false;
        }
        if (isStatus != isOn) {
            clickOnElement(xpath);

        }

    }

    public int maxRetry = 3;

    public void hoverOnFirstSection(int currentRetry) {
        String xpathFirstSection = "//div[parent::div[@class='draggable']][1]";
        String xpathButtonBelow = xpathFirstSection + "//div[normalize-space()='+ Add section below']";

        try {
            hoverOnElement(xpathFirstSection);
            clickOnElementByJs(xpathButtonBelow);
        } catch (Exception e) {
            if (currentRetry <= maxRetry) {
                hoverOnElement("//div[@class='hHPuvL'][1]");
                hoverOnFirstSection(currentRetry + 1);
            }
        }
    }

    public void addSection(String sectionName) {
        waitForEverythingComplete();
        waitUntilElementVisible("//div[child::*[normalize-space(text())='\" + sectionName + \"']]", 5);
        clickOnElementByJs("//div[child::*[normalize-space(text())='" + sectionName + "']]");
    }

    public void clickSettingtab(int currentRetryTime) {
        waitABit(10000);
        try {
            clickOnElement("//p[normalize-space()='Settings']");
        } catch (Throwable t) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(3000);
                clickSettingtab(currentRetryTime + 1);

            }
        }

    }

    public void clickSectionTab() {
        waitABit(10000);
        clickOnElement("//p[contains(text(),'Sections')]");
    }

    public void selectCollectionOrProduct(String collection) {
        if (isElementExist("//a[@class='s-delete is-small']")) {
            clickOnElement("//a[@class='s-delete is-small']");
        }
        if(!collection.isEmpty()){
            String xpathInput = "//div[contains(@class,'select-wrapper')]//div[contains(@class,'is-focusable')]//input";
            String xpathDropItem = "//div[contains(@class,'is-focusable')]//*[@class='s-dropdown-item__content' and normalize-space()='" + collection + "']";

            inputSlowly(xpathInput, collection);
            waitElementToBeClickable(xpathDropItem);
            clickOnElementByJs(xpathDropItem);
        }
    }

    public int countContent() {
        String xpath;
        if (theme.equalsIgnoreCase("Bassy")) {
            xpath = "//h3[child::span[normalize-space()='Content']]//parent::div//following-sibling::div//div[contains(@class,'s-collapse-item draggable-item')]";
        } else {
            xpath = "//h3[child::span[normalize-space()='content']]//parent::div//following-sibling::div//div[contains(@class,'s-collapse-item draggable-item')]";
        }
        return countElementByXpath(xpath);
    }

    String sectionActive = "//div[@class='s-collapse-item draggable-item is-active']";

    public void removeContent(int i) {
        String xpath = "(//button[contains(text(),'Remove content') or contains(text(),'Remove section')])[" + i + "]";
        clickOnElement(xpath);
    }


    public void clickAddContent() {
        String xpathAddContent = "//h3[child::span[normalize-space()='content']]//parent::div//following-sibling::div//div[@class='s-collapse-item__header']//i[contains(@class,'mdi-plus')]";
        clickOnElement(xpathAddContent);
    }

    public void openBlockContentByIndex(int i) {
        if(theme.equalsIgnoreCase("Bassy")){
            clickOnElement("(//h3[child::span[normalize-space()='Content']]//parent::div//following-sibling::div//div[contains(@class,'s-collapse-item draggable-item')])[" + i + "]");
        }else {
            clickOnElement("(//h3[child::span[normalize-space()='content']]//parent::div//following-sibling::div//div[contains(@class,'s-collapse-item draggable-item')])[" + i + "]");
        }
    }

    public void openLastBlockContent() {
        String content = "(//div[contains(@class,'s-collapse-item draggable-item')])[last()]";
        clickOnElement(content);
        waitABit(500);
    }

    public void uploadImageByLabel(String xpathparent, String label, String filename) {
        String xpath = "(" + xpathparent + "//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='" + label + "']]//input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, filename);
    }

    public void uploadImageWithFile(String filename) {
        String xpath;
        if(theme.equalsIgnoreCase("Bassy")){
            xpath = "//div[@class='s-form-item image_picker']//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
        }else {
            xpath = "//div[@class='s-collapse-item draggable-item is-active']//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
        }
        changeStyle(xpath);
        chooseAttachmentFile(xpath, filename);
        waitABit(1000);
    }

    public void clickContent(String label, int resID) {
        clickOnElement("(//div[contains(@class,'s-collapse-item draggable-item') and descendant::span[normalize-space()='" + label + "']])[" + resID + "]");
    }

    public void selectDataLink(String dataLink) {
        String xpathInput = " //div[@name='link']//div[contains(@class,'input')]//input";
        String xpathDropItem = "//div[contains(@class,'is-focusable')]//*[@class='s-dropdown-item'  and normalize-space()='" + dataLink + "']";
        clickThenTypeCharByChar(xpathInput, dataLink);
        waitUntilElementVisible(xpathDropItem, 10);
        clickOnElement(xpathDropItem);
    }

    public void chooseTypeLink(String typeLink) {
        String xpathInput = "//div[@name='link']//input";
        String xpathDropItem = "//div[contains(@class,'is-opened-top')]//*[@class='s-dropdown-item'  and normalize-space()='" + typeLink + "']";
        clickThenTypeCharByChar(xpathInput, typeLink);
        waitUntilElementVisible(xpathDropItem, 10);
        clickOnElementByJs(xpathDropItem);
    }

    public void selectLinkPage(String typeLink, String dataLink) {
        chooseTypeLink(typeLink);
        selectDataLink(dataLink);
    }


    String xpathSettingTheme = "//div[@role='tablist'][descendant::*[normalize-space(text())='theme settings']]";

    public boolean isOpenThemeSettings() {
        return isElementExist(xpathSettingTheme + "//div[@class='s-collapse-item is-active']");
    }

    public void openBlockThemeSetting() {
        clickOnElement(xpathSettingTheme + "//div[@role='button']");
    }

    public void enterLinkSocialWithLabel(String label, String value) {
        enterInputFieldWithLabel(xpathSettingTheme, label, value, 1);
    }

    public String getHeading() {
        return getTextValue("//div[child::div[@class='help-label'][descendant::*[normalize-space()='Headline'] or descendant::*[normalize-space()='Heading'] or descendant::*[contains(text(),'title')]]]//input");
    }

    public boolean iSectionExisted(String section) {
        return isElementExist(String.format(xpathSection, section));
    }

    public void clickBtnRemoveSection() {
        clickOnElement("//button[child::*[normalize-space()='Remove section']]");
    }


    public void clickBtnCustomizeCurrentTheme() {
        theme = theme.substring(0, 1).toUpperCase() + theme.substring(1).toLowerCase();
        clickOnElementByJs("//div[@class='card__section published-theme' and descendant::h3[normalize-space()='" + theme + "']]//button[normalize-space()='Customize']");
        waitForEverythingComplete(20);
    }

    public String getCurrentTheme() {
        return getText("//section[contains(@class,'current-theme')]//h3");
    }

    public void confirmPopup() {
        String xpath = "//div[@class='s-dialog s-modal is-active']";
        if (isElementExist(xpath)) {
            clickBtn("OK");
        }
    }

    public void verifyThemesManagementPageDisplayed() {
        verifyElementPresent("//div[@class='page-themes']", true);
    }

    public void removeFeature(int i) {
        String xpath = "(//button[contains(text(),'Remove feature')])[" + i + "]";
        clickOnElement(xpath);
    }

    public void verifySectionWidget(String label) {
        waitForEverythingComplete();
        String xpath = "//div[@id='app']//p[normalize-space()='" + label + "']";
        verifyElementPresent(xpath, true);
    }

    public void clickBtnBack() {
        clickOnElement("//div[@id='app']//i[contains(@class,'chevron-left')]");
        waitForPageLoad();
    }

    public void clickSelectBox() {
        String xpathSelectBox = "//div[@class='help-label' and child::label[contains(normalize-space(),'Image list')]]//following::div[@class='s-form-item select']";
        clickOnElement(xpathSelectBox);
    }

    public void selectOption(String settingImageList) {
        String xpathOptionSelect = "//div[@class='help-label' and child::label[contains(normalize-space(),'Image list')]]//following::div[@class='s-form-item select']//option[contains(normalize-space(),'"+settingImageList+"')]";
        clickOnElement(xpathOptionSelect);
    }

    public void selectDropDown(String label, String value){
        clickOnElement("//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//option[normalize-space()='"+value+"']");
    }

    public void selectDropDownInActiveBlock(String label, String value){
        clickOnElement("//div[@class='s-collapse-item draggable-item is-active']//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//option[@value='"+value.toLowerCase(Locale.ROOT)+"']");
    }

    public void inputTextBox(String label, String text){
        String xpath = "//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//input";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, text);
    }

    public void clickAddColumn() {
        String xpathAddContent = "//div[@class='s-collapse-item__header']//i[contains(@class,'mdi-plus')]";
        clickOnElement(xpathAddContent);
    }

    public void checkFullWidthSection(String label) {
        checkCheckboxWithLabel(label);
    }

    public void clickAddSection() {
        clickOnElement("//h3[child::span[normalize-space()='Content']]//parent::div//following-sibling::div//div[@class='s-collapse-item__header']//i[contains(@class,'mdi-plus')]");
    }

    public void sellectTextAlignment(String label, String textAlignment) {
        selectDdlWithLabel(label,textAlignment);
    }

    public void inputButtonLabelColor(String buttonLabelColor) {
        waitClearAndType("//div[contains(@class,'is-active')]//div[@currentlayout and descendant::p[normalize-space()='Button label']]//div[contains(@class,'s-input-group')]//input",buttonLabelColor);
    }

    public void inputTextColor(String label, String textColor) {
        waitClearAndType("//div[contains(@class,'card__section')]//div[@currentlayout and descendant::p[normalize-space()='Text']]//div[contains(@class,'s-input-group')]//input", textColor);
    }

    public void uploadLogo(String logo) {
        if(!logo.isEmpty()){
            if(isElementExist("//h3[child::span[normalize-space()='Logo']]//parent::div//following-sibling::div//img[@class='iZocYm']")){
                clickOnElement("//h3[child::span[normalize-space()='Logo']]//parent::div//following-sibling::div//img[@class='iZocYm']");
            }
            String xpath = "//div[@class='s-form-item image_picker']//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
            changeStyle(xpath);
            chooseAttachmentFile(xpath, logo);
            waitABit(1000);

        }
    }

    public void selectFooterMenu(String label, String footerMenu) {
        selectDdlWithLabel(label,footerMenu);
    }

    public void inputCustomText(String customText) {
        waitClearAndType("//div[contains(@class,'card__section')]//div[@currentlayout and descendant::p[normalize-space()='Custom text']]//textarea",customText);
    }

    public int countPaymentIcon() {
        return countElementByXpath("//h3[child::span[normalize-space()='Payment Icon']]//parent::div//following-sibling::div//div[contains(@class,'s-collapse-item draggable-item')]");
    }

    public void openContent(int index) {
        clickOnElementByJs("(//div[contains(@class,'draggable-item')])["+ index +"]//span[contains(@class,'arrow')]");
    }

    public void clickRemoveButton() {
        clickOnElementByJs("//div[contains(@class,'is-active')]//a[normalize-space()='Remove icon' or normalize-space()='Remove section']");
    }

    public void clickAddMorePaymentIcon() {
        String xpathAddContent = "//h3[child::span[normalize-space()='Payment Icon']]//parent::div//following-sibling::div//div[@class='s-collapse-item__header']//i[contains(@class,'mdi-plus')]";
        clickOnElement(xpathAddContent);
    }

    public void uploadImagePaymentIcon(String image) {
        String xpath = "//div[contains(@class,'s-collapse-item')]//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
        clickOnElement("//div[@class='card without-padding']");
    }

    public void verifyColorSetting(String title, String label, String backgroundHeader) {
        String collorSetting = getColorSettings(title,label);
        assertThat(collorSetting).isEqualToIgnoringCase(backgroundHeader);
    }

    private String getColorSettings(String title,String label) {
        return getTextValue("//h3[child::span[normalize-space()='"+title+"']]//parent::div//following-sibling::div[@class='card__section' ]/div/*[descendant::*[text()='"+label+"']]//input");
    }

    public void clickCustomize() {
        clickOnElement("//div[@class='published-theme__actions']//button[normalize-space()='Customize']");
    }

    public void selectDroplistByLabel(String label, String chooseLayout) {
        selectDdlWithLabel(label,chooseLayout);

    }

    public void inputText(String text,int index) {
        waitClearAndType("(//div[@class='card__section']//textarea)["+index+"]",text);
    }

    public void inputTextArea(String title, String textAreaTopContent) {
        switchToIFrame("(//div[@currentlayout and descendant::p[normalize-space()='"+title+"']]/following-sibling::div//iframe)[1]");
        waitClearAndType("//body[@id='tinymce']", textAreaTopContent);
        switchToIFrameDefault();
    }

    public void uploadImageWithLabel(String title, String imageTopContent) {
        String xpath = "(//div[@currentlayout and descendant::p[normalize-space()='"+title+"']]/following-sibling::div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, imageTopContent);
        waitABit(1000);

    }

    public void inputTextBoxWithLabel(String title,String label, String imageCaptionTopContent) {
        waitClearAndType("(//div[@currentlayout and descendant::p[normalize-space()='"+title+"']]/following-sibling::div//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//input)[1]",imageCaptionTopContent);
    }
}


