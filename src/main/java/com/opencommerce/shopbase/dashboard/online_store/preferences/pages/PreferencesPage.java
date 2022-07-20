package com.opencommerce.shopbase.dashboard.online_store.preferences.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
public class PreferencesPage extends SBasePageObject {
    public PreferencesPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPopupTitle() {
        verifyElementText("//div[@class='s-modal-wrapper']//h2", "Add logos to your store");
    }

    public void verifyAddLogoSuccessfully() {
        verifyElementPresent("//div[normalize-space()='Saved successfully']", true);
    }

    public String getLogo() {
        refreshPage();
        return getAttributeValue("//div[contains(@class,'logo-builder preferences__sections')]//img", "src");
    }

    public void clickBtnSaveOnPopup() {
        clickOnElement("//div[@class='s-modal is-active modal-add-logo']//button[normalize-space()='Save']");
    }

    public void verifyMatchLogo(Boolean isCheck, String preValue, String expValue) {
        if (!isCheck) {
            assertThat(preValue).isEqualToIgnoringCase(expValue);
        } else assertThat(preValue).isNotEqualTo(expValue);
    }

    public void clickBtnChange() {
        if (isElementExist("//div[@id='fb-pixel-section']//a[@class='text-normal' and normalize-space()='Change']"))
            clickOnElement("//div[@id='fb-pixel-section']//a[@class='text-normal' and normalize-space()='Change']");
    }

    public void inputIdAndToken(String _label, String _field, String _value, String index) {
        String inputXpath = "(//section[descendant::h1[contains(.,'"+_label+"')]]//input[@placeholder='"+_field+"'])[" + index + "]";
        waitUntilElementVisible(inputXpath, 5000);
        scrollToConversionId();
        waitClearAndType(inputXpath, _value);
    }

    public void verifyMsg(String msg) {
        verifyElementText("//div[@class ='s-form-item__error']", msg);
    }

    public void hoverOverText() {
        hoverOnElement("//b[@class = 's-hover-pointer s-popover__reference']");
    }


    public void scrollToConversionId() {
        scrollIntoElementView("//div[@id='fb-pixel-section']");
    }

    public void clickBtnSave() {
        waitUntilElementVisible("//button[normalize-space()='Save']", 5);
        clickOnElement("//button[normalize-space()='Save']");
    }

    public void verifyMsgWithLabel(String msg) {
        waitForTextToAppear(msg);
    }

    public void clickOverLay() {
        clickOnElement("//h1[contains(text(),'Klaviyo integration')]");
    }

    public void removeConversionId(String _label) {
        if (isElementExist("(//section[descendant::h1[contains(.,'"+_label+"')]]//i[contains(@class,'trash')])[2]")) {
            clickOnElement("(//section[descendant::h1[contains(.,'"+_label+"')]]//i[contains(@class,'trash')])[2]");
        }
    }

    public void inputAccessToken(String accessToken, String index) {
        String inputXpath = "(//div[@id='fb-pixel-section']//input[contains(@placeholder,'access token')])[" + index + "]";
        waitUntilElementVisible(inputXpath, 5000);
        scrollToConversionId();
        waitClearAndType(inputXpath, accessToken);
    }

    public List<String> getlistConversionId(String _label) {
        if (isElementExist("//section[descendant::h1[contains(.,'"+_label+"')]]")) {
            return getListText("//section[descendant::h1[contains(.,'"+_label+"')]]//div[@class='row f-1']");
        }
        return null;
    }

    public void addConversionId(String _label) {
        clickOnElement("//span[contains(.,'"+_label+"')]");
    }

    public int countcvIdInList(String _label) {
        return countElementByXpath("//section[descendant::h1[contains(.,'"+_label+"')]]//div[@class='row f-1']");
    }

    public void inputPassword(String inputPassword) {
        String xpath = "//div[@id='password-protection']//following::input[@type='text']";
        waitClearAndType(xpath, inputPassword);
    }

    public void verifyPagePW(Boolean isshowPWOnSF) {
        waitForPageLoad();
        verifyElementPresent("//input[@type='password' ]", isshowPWOnSF);
    }

    public void settingOnOrOffGeneratePrintFile(boolean statusGenerate) {
        String xpathChecked = "//div[@id='print-file-generating']//div[contains(@class,'preference-section__content')]//input";
        String xpathStatus = "//div[@id='print-file-generating']//div[contains(@class,'preference-section__content')]//label//span[1]";
        verifyCheckedThenClickOnOnlineStore(xpathChecked ,xpathStatus,statusGenerate);
    }

    public void verifySettingGeneratePrintFilebyAPI(Boolean status,Boolean statusGenerate) {
        assertThat(status).isEqualTo(statusGenerate);
    }

    public void clearFieldInputWithLabel(String label) {
        String xpath = "//div[contains(@class,'s-form-item')]//div[normalize-space()='" + label + "']//input";
        XH(xpath).click();
        XH(xpath).clear();
    }

    public boolean haveChange() {
        return !isElementExist("//button[contains(@class,'is-disabled') and normalize-space()='Save']", 3);
    }

    public void settingProductDescription(String setting) {
        selectRadioButtonWithLabel(setting, true);
    }

    public void inputPasswordSF(String sInputPW) {
        String xpath = "//input[@type=\'password\']";
        waitClearAndType(xpath, sInputPW);
    }

    public void clickBtnEnter() {
        clickOnElement("//a[@class='btn btn-primary']");
    }
    public void verifyMessageValidate(String sMessage) { verifyElementText("//*[contains(@class, 'cl-red')]",sMessage);}

    public void clickBtnSearch() {
        clickOnElement("//div[@class='search-bar']");
    }

    public String getCurrentShop() {
        String currenturl = getCurrentUrl().replace("https://", "");
        String[] _currenturl = currenturl.split("/");
        return _currenturl[0];
    }

    public void inputProductName(String productName) {
        String xPath = "//div[@class ='search-bar']//input[@placeholder = 'Search' ]";
        isElementVisible(xPath, 10);
        waitTypeAndEnter(xPath, productName);
        waitElementToBeVisible("//div[@class='collection-detail__product-image mb12']",10);
    }
    public void verifyMsgSaveSuccess(){
        waitElementToBePresent("//div[@class='s-toast is-dark is-bottom']//div");
    }

    public void verifyCheckedThenClickOnOnlineStore(String _xPathStatus, String _xPathCheckbox, boolean _isCheck) {
        boolean isStatus = XH(_xPathStatus).isSelected();
        if (_isCheck != isStatus) {
            clickOnElementByJs(_xPathCheckbox);
            clickOnElementByJs("//button[normalize-space()='Save']");
        }
    }

    public void clickBtnLearnMore (){
        String xpath= "//*[@id = 'edit-robots-txt-section']//*[normalize-space()='Learn more']";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnEditRobotstxt (){
        String xpath= "//*[@id = 'edit-robots-txt-section']//button[normalize-space()='Edit robots.txt']";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public String contentRobotTxt(){
        return getTextByJS("//*[@placeholder='Your robots.txt file']");
    }

    public void enterDataContentRobot(String content){
        clearAndInputTextByJS("//*[@placeholder='Your robots.txt file']", content);
    }

    public void clickBtnByText(String text) {
        clickOnElement("//span[contains(text(), \""+text+"\")]");
        waitForPageLoad();
    }

    public void verifyNavigateInRobotSection(String currentURL, String exactURL){
        assertThat(currentURL).isEqualTo(exactURL);
    }

    public void clickOnTabList(String text){
        clickOnElement("//*[contains(text(),'"+ text +"')]//ancestor::div[contains(@class,'s-collapse-item')]//button");
        waitForPageLoad();
    }

    public void openFileRobot(String url){
        navigateToUrl(url);
    }

    public void verifyContent(String currentURL, String exactURL){
        refreshPage();
        assertThat(currentURL).isEqualTo(exactURL);
    }

    public String contentRobotTxtSF(){
        return getText("//pre");
    }
}
