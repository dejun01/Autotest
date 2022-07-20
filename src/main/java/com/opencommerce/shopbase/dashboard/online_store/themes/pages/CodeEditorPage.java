package com.opencommerce.shopbase.dashboard.online_store.themes.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CodeEditorPage extends SBasePageObject {
    public CodeEditorPage(WebDriver driver) {
        super(driver);
    }

    public void deleteTheme(){
        clickOnElement("//span[normalize-space()='Remove']");
        clickOnElement("//div[contains(@class,'s-modal')]//button[span[normalize-space()='Remove']]");
    }

    public int countThemes(){
        return countElementByXpath("//li[@class='themes-list__row']") - 1;
    }

    public void openDropListAction(){
        clickOnElement("(//button[normalize-space()='Actions'])[2]");
    }

    public void verifyMsg(String msg){
        waitForTextToAppear(msg);
    }

    public int countFileRevision(){
        return countElementByXpath("//select//option");
    }

    public void editCode(String code){
        Actions act = new Actions(getDriver());
        String xpath = "//div[@class='view-lines']";
        clickOnElement(xpath);

        act.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        act.sendKeys(code).perform();
    }

    public void verifyShowFile(String filePath, boolean isShow){
        String xpathOpenFolder;
        String[] path = filePath.split("/");
        String xpath = "//div[contains(@class,'context-body')]";

        for (int i=0; i < path.length - 1; i++){
            xpathOpenFolder = xpath + "//div[contains(@class,'sb-ide__file-row') and descendant::span[normalize-space()='" + path[i].trim() + "'] and contains(@class,'is-open')]";
            xpath += "//div[contains(@class,'sb-ide__file-row') and descendant::span[normalize-space()='" + path[i].trim() + "']]//parent::div";
            if(!isElementExist(xpathOpenFolder)){
                clickOnElement(xpath);
            }
        }
        xpath += "//span[normalize-space()='" + path[path.length - 1].trim() + "']";
        verifyElementPresent(xpath, isShow);
    }

    public String getXpathByFile(String filePath){
        String[] path = filePath.split("/");

        StringBuilder xpath = new StringBuilder("//div[contains(@class,'context-body')]");
        for (int i=0; i < path.length - 1; i++){
            xpath.append("//div[contains(@class,'sb-ide__file-row') and descendant::span[normalize-space()='").append(path[i].trim()).append("']]//parent::div");
        }
        xpath.append("//span[normalize-space()='").append(path[path.length - 1].trim()).append("']");
        return xpath.toString();
    }

    public void openFile(String filePath){
        String xpathOpenFolder;
        String[] path = filePath.split("/");
        String xpath = "//div[contains(@class,'context-body')]";

        for (int i=0; i < path.length - 1; i++){
            xpathOpenFolder = xpath + "//div[contains(@class,'sb-ide__file-row') and descendant::span[normalize-space()='" + path[i].trim() + "'] and contains(@class,'is-open')]";
            xpath += "//div[contains(@class,'sb-ide__file-row') and descendant::span[normalize-space()='" + path[i].trim() + "']]//parent::div";
            if(!isElementExist(xpathOpenFolder)){
                clickOnElement(xpath);
            }
        }
        xpath += "//span[normalize-space()='" + path[path.length - 1].trim() + "']";
        clickOnElement(xpath);
    }

    public void clickOnTextLink(String textLink){
        clickOnElement("//span[normalize-space()='"+textLink+"']");
    }

    public int countNumberOfMoreThemeBlock(){
        return countElementByXpath("//li[@class='themes-list__row']") - 1;
    }

    public void verifyNumberOfThemeInList(int number){
        assertThat(countNumberOfMoreThemeBlock()).isEqualTo(number);
    }

    public void verifyThemesName(String themeName){
        verifyElementPresent("//p[contains(@class,'theme-title') and normalize-space()='"+ themeName+ "']", true);
    }

    public void verifyCurrentTheme(String themeName){
        String currentTheme = getText("//h3[contains(@class,'published-theme__title')]");
        assertThat(currentTheme).isEqualTo(themeName);
    }

    public void selectVersion(String version){
        clickOnElement("//select//option[normalize-space()="+ version +"]");
    }

    public void verifyShowActionWithTheme(String themeName, String action, boolean isShowAction){
        verifyElementPresent("//div[contains(@class,'s-flex s-flex--spacing-tight') and descendant::*[normalize-space()='"+themeName+"']]//span[normalize-space()='"+action+"']", isShowAction);
    }

    public void verifyCurrentVersion(String version) {
        String actual = getAttributeValue("(//select//option)[1]",("innerText")).trim();
        assertThat(actual).isEqualTo(version);
    }

    public void hoverOnFile(String xpathFile) {
        hoverOnElement(xpathFile);
    }

    public void clickOnDropList(String xpathFile) {
        String xpathDropList = xpathFile + "//parent::div//button";
        clickOnElement(xpathDropList);
    }

    public void selectAction(String xpathFile, String action) {
        String xpathButton = xpathFile + "//parent::div//span[normalize-space()='"+ action +"']";
        clickOnElement(xpathButton);
    }

    public void inputTextBoxOnPopup(String s) {
        clickOnElement("//div[contains(@class,'modal')]//input");
        waitClearAndType("//div[contains(@class,'modal')]//input", s);
    }

    public void clickOnButton(String buttonName) {
        clickOnElement("//button//span[normalize-space()='"+ buttonName+ "']");
    }

    public void verifyTextOnDashBoard(String text, boolean isShow) {
        verifyElementPresent("//div[@class='view-lines']//span[contains(text(),'"+ text +"')]", isShow);
    }

    public void verifyTextOnStorefront(String text, boolean isShow) {
        verifyElementPresent("//*[contains(text(),'"+text+"')]", isShow);
    }

    public void clickOnBackIcon(String page) {
        clickOnElement("//i[contains(@class,'keyboard-backspace')]");
        waitElementToBePresent("//h1[normalize-space()='"+ page +"']");
    }

    public void verifyEditorCodePage(String themeName) {
        waitForPageLoad();
        verifyElementPresent("//h3[normalize-space()='"+ themeName+ "']", true);
    }

    public void clickOnIcon(String search) {
        clickOnElement("//span[@data-label="+ search +"]");
    }

    public void searchFile(String fileName) {
        waitClearAndType("//input", fileName);
    }

    public void selectFile(String filePath) {
        waitElementToBeVisible("//span[normalize-space()="+ filePath +"]");
        clickOnElement("//span[normalize-space()="+ filePath +"]");
    }
}
