package com.opencommerce.shopbase.dashboard.online_store.landingpage.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;

public class LandingPages extends SBasePageObject {
    public LandingPages(WebDriver driver) {
        super(driver);
    }

    String shop = LoadObject.getProperty("shop");

    public void clickCheckbox() {
        checkCheckbox("(//table[contains(@class,'table-condensed')]//label[@class='s-checkbox'])[1]",true);
    }
    public boolean checkExistPage(){
        return isElementExist("//table[contains(@class,'table-condensed')]//label[@class='s-checkbox']");
    }

    public void clickBtnAction() {
        clickOnElement("//table[contains(@class,'table-condensed')]//button");
    }

    public void clickDeletePages() {
        clickOnElementByJs("//table[contains(@class,'table-condensed')]//span[normalize-space()='Delete pages']");
        clickOnElement("//div[contains(@class,'s-modal-content')]//button[normalize-space()='Delete']");
    }

    public void verifyDeleteAllPage() {
        verifyElementPresent("//section[contains(@class,'empty-state')]",true);
    }

    public void clickBtnAddPage() {
        if(isElementExist("//section[contains(@class,'empty-state')]//button")){
            clickOnElement("//section[contains(@class,'empty-state')]//button");
        }else {
            clickOnElement("//div[contains(@class,'page-pages--landing')]//button");
        }
    }

    public void selectTemplate(String selectTemplate) {
        if(selectTemplate.equalsIgnoreCase("Blank page")) {
            clickOnElement("//div[@class='landing-templates']//div[contains(@class,'landing-item__content') and following-sibling::div[text()='" + selectTemplate + "']]//i");
        }else {
            clickOnElementByJs("//div[@class='landing-templates']//div[contains(@class,'landing-item__content') and following-sibling::div//span[normalize-space()='" + selectTemplate + "']]//img");
        }
    }

    public void inputWithTitle(String label, String pageTitle) {
        waitClearAndType("//div[child::label[normalize-space()='"+label+"']]//following-sibling::div[contains(@class,'item__content')]//input",pageTitle);
    }

    public void inputSEODescription(String seoDescription) {
        waitClearAndType("//div[@class='s-form-item']//textarea",seoDescription);
    }

    public void uploadOpenGraphImage(String openGraphImage) {
        String xpath = "//input[@type='file']";
        chooseAttachmentFile(xpath, openGraphImage);
    }

    public void clickAddPage() {
        clickOnElement("//div[contains(@class,'s-modal-content')]//button[child::span[normalize-space()='Add page']]");
    }

    public void settingPublishThisPage(boolean publishThisPage) {
        if(!publishThisPage){
            clickOnElementByJs("//div[contains(@class,'justify-space-between')]//span[@class='s-check']");
        }
    }

    public void verrifyCreateSuccess(String totalSection,String selectTemplate) {
        if(selectTemplate.equalsIgnoreCase("Blank page")){
            verifyElementPresent("//div[contains(@class,'landing-page__sidebar')]//button",true);
        }else {
            int countSection = countElementByXpath("//div[contains(@class,'landing-page__sidebar')]//div[@class='eTgtIq']");
            assertThat(countSection).isEqualTo(Integer.parseInt(totalSection));

        }
    }

    public void clickBtnClose() {
        clickOnElement("//div[contains(@class,'landing-page')]//button[child::span[normalize-space()='Close']]");
    }

    public void clickIconDetail(String pageTitle) {
        clickOnElement(" //tr[descendant::a[normalize-space()='"+pageTitle+"']]//following-sibling::td//i");
    }

    public void verifyPageTitle(String label,String pageTitle) {
        String pageTitleAR = getValue("//div[child::label[normalize-space()='"+label+"']]//following-sibling::div[contains(@class,'item__content')]//input");
        assertThat(pageTitle).contains(pageTitleAR);

    }

    public void verifySeoTitle(String label, String seoTitle) {
        String seoTitleAR = getValue("//div[child::label[normalize-space()='"+label+"']]//following-sibling::div[contains(@class,'item__content')]//input");
        assertThat(seoTitle).contains(seoTitleAR);
    }

    public void verifySeoDescription(String label, String seoDescription) {
        String seoDescriptionAR = getValue("//div[@class='s-form-item']//textarea");
        assertThat(seoDescription).contains(seoDescriptionAR);
    }

    public void verifyopenGraphImage(String openGraphImage) {
        verifyElementPresent("//div[@class='s-form-item']//img",true);
    }

    public void verifyOpenLinkSF(String pageTitle, boolean publishThisPage) {
        String url = getTextContent("//td[descendant::a[normalize-space()='"+pageTitle+"']]//p");
        openUrl("https://"+shop+url);
        if(!publishThisPage){
           verifyElementPresent("//main[contains(@class,'main-content ')]//h2[contains(@class,'notfound-template__title')]",true);
        }
    }

    public void openLandingEditor(String titleLanding) {
        clickOnElement("//td[descendant::a[normalize-space()='"+titleLanding+"']]//a");
        waitForPageLoad();
    }

    public void inputTextBox(String label, String title) {
        if(!title.isEmpty()) {
            waitClearAndType("//label[child::p[normalize-space()='" + label + "']]//parent::div//following-sibling::div//input", title);
        }else {
            clearValueByJS("//label[child::p[normalize-space()='" + label + "']]//parent::div//following-sibling::div//input");
        }
    }

    public void inputTextArea(String label, String body) {
        if (!body.isEmpty()) {
            waitClearAndType("//label[child::p[normalize-space()='" + label + "']]//parent::div//following-sibling::div//textarea", body);
        }else {
            clearValueByJS("//label[child::p[normalize-space()='" + label + "']]//parent::div//following-sibling::div//textarea");
        }
    }

    public void openVisualSetting(String label) {
        clickOnElement("//h3[normalize-space()='"+label+"']//preceding-sibling::span[contains(@class,'s-collapse-item')]//i");
    }

    public void inputColor(String label, String startColorBackground) {
        clickOnElementByJs("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[@class='color-preview']");
        clickOnElementByJs("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-suggest-item pick')]");
        scrollToElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-render-preview')]//input");
        waitClearAndType("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-render-preview')]//input",startColorBackground);
        clickOnElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[@class='color-preview']");
    }

    public void scrollToLabel(String label) {
        scrollIntoElementView("//label[child::p[normalize-space()='"+label+"']]");
    }

    public void selectLabel(String label, String typeBackground) {
        clickOnElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//nav//p[text()[normalize-space() ='"+typeBackground+"']]");
    }

    public void clickBtnChange(String label, String titleFontTypography) {
        clickOnElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//button");
    }
    public void clickBtnChange(String label, int index) {
        clickOnElement("(//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//button)["+index+"]");
    }

    public void searchFont(String label, String value) {
        waitClearAndType("//input[@placeholder='Search']",value);
    }

    public void chooseFont(String label, String value) {
        clickOnElement("//ul//p[normalize-space()='" + value + "']");
    }

    public void closeSelectFontPopup() {
        clickOnElement("//span[@class='s-icon pointer is-small']");
    }

    public void verifyChangeFontSuccessfully(String label, String value) {
        verifyElementText("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//p[contains(@class,'font-picker__title')]", value);
    }

    public void openOnStorefront(String title) {
        String url = getTextContent("//td[descendant::a[normalize-space()='"+title+"']]//p");
        openUrl("https://"+shop+url+"/?sbase_debug=1");
        waitForPageLoad();
    }

    public void checkCheckBox(String label, boolean isCheck) {
        String xpathStatus ="//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//input";
        String xpathCheck="//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//label";
        waitElementToBePresent(xpathCheck);
        verifyCheckedThenClick(xpathStatus,xpathCheck,isCheck);
    }

    public void verifyShowHTML(String html) {
        boolean isShow = false;
        if(!html.isEmpty()){
            isShow = true;
        }
        verifyElementPresent("//div[contains(@data-id,'custom_html')]",isShow);
    }

    public void verifyCustomHTML(String html) {
        verifyElementText("//div[contains(@data-id,'custom_html')]//p",html);
    }

    public void verifyFullWidthSection(boolean fullWidthSection) {
        verifyElementPresent("//div[@class='lp-container-fluid' and contains(@data-id,'custom_html')]",fullWidthSection);
    }

    public void uploadImageWithLabel(String label, String image) {
        if(isElementExist("//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm']")){
            clickOnElement("//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm']");
        }
        String xpath = "//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
    }

    public void opendMenuItem(String label,int index) {
        clickOnElement("(//div[child::span[normalize-space()='"+label+"']]//parent::div//preceding-sibling::span//i)["+index+"]");
    }

    public void selectDropListWithLable(String label, String value) {
        selectDdlWithLabel(label,value);
    }

    public int countContent(String label) {
        return countElementByXpath("//div[child::span[normalize-space()='"+label+"']]//parent::div//preceding-sibling::span//i");
    }

    public void openBlockContentByIndex(String label,int i) {
        clickOnElement("(//div[child::span[normalize-space()='"+label+"']]//parent::div//preceding-sibling::span//i)["+i+"]");
    }

    public void removeContent(String label, int i) {
        scrollIntoElementView("//h3[normalize-space()='Visual settings']//preceding-sibling::span[contains(@class,'s-collapse-item')]//i");
        String xpath = "(//button[contains(text(),'Remove content') or contains(text(),'Remove image')])[" + i + "]";
        clickOnElement(xpath);
    }

    public void clickAddContent() {
        clickOnElement("//div//parent::div//following-sibling::div//div[@class='s-collapse-item__header']//i[contains(@class,'mdi-plus')]");
    }

    public void setScheduleTime(String endDate) {
        clickThenTypeCharByChar("Pick a day", endDate);
    }

    public void uploadImageWithLabel(String label, String image, int index) {
        if(isElementExist("(//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm'])["+index+"]")){
            clickOnElement("(//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm'])["+index+"]");
        }
        String xpath = "//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[descendant::button[normalize-space()='Upload image']]//preceding-sibling::input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
    }

    public void inputTextBox(String label, String value, int index) {
        waitClearAndType("(//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//input)["+index+"]",value);
    }

    public void inputTextContentWithIframe(String body, int index) {
        switchToIFrame("(//div[contains(@class,'is-active')]//iframe)["+index+"]");
        waitClearAndType("(//body[@id='tinymce'])["+index+"]", body);
        switchToIFrameDefault();
    }

    public void checkCheckBox(String label, boolean isCheck, int index) {
        String xpathStatus ="(//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//input)["+index+"]";
        String xpathCheck="(//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//label)["+index+"]";
        waitElementToBePresent(xpathCheck);
        verifyCheckedThenClick(xpathStatus,xpathCheck,isCheck);
    }

    public void selectLabel(String label, String value, int index) {
        clickOnElement("(//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//nav//p[text()[normalize-space() ='"+value+"']])["+index+"]");

    }

    public void enterProduct(String label, String productName) {
        clearProduct();
        if (!productName.isEmpty()) {
            enterInputFieldWithLabel("Product", productName);
            String xpath = "//div[@class='s-dropdown-item__content' and normalize-space()='" + productName + "']";
            waitElementToBeVisible(xpath, 10);
            clickOnElement(xpath);
            verifyElementPresent("//a[@class='s-delete is-small']", true);
        }
    }
    private void clearProduct() {
        String xpath = "//a[@class='s-delete is-small']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
        verifyElementPresent("//input[@placeholder='Type a keyword to search products']", true);
    }

    public void inputColor(String label, String value, int index) {
        clickOnElementByJs("(//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[@class='color-preview'])["+index+"]");
        clickOnElementByJs("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-suggest-item pick')]");
        scrollToElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-render-preview')]//input");
        waitClearAndType("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[contains(@class,'color-render-preview')]//input",value);
        clickOnElement("//label[child::p[normalize-space()='"+label+"']]//parent::div//following-sibling::div//div[@class='color-preview']");
    }
}

