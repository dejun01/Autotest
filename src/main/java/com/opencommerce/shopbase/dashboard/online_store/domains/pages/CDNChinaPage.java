package com.opencommerce.shopbase.dashboard.online_store.domains.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;


public class CDNChinaPage extends SBasePageObject {
    public CDNChinaPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu(){
        String xpath = "//div[@class='d-flex flex-column']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void switchToChina(String server){
        clickOnElement("//*[contains(text(),'"+ server + "')]");
        if("切换到美国后台版".equals(server)){
            clickOnElement("//div[@class = 's-modal-body']//button[span[normalize-space()='确定']]");
        } else {
            clickOnElement("//div[@class = 's-modal-body']//button[span[normalize-space()='Confirm']]");
        }
        waitForPageLoad();
    }

    public void searchPage(String title) {
        String xPath = "//input[@type='text' and @placeholder='Search pages']";
        waitTypeAndEnter(xPath, title);
    }

    public void choosePage(String namePage) {
        String xPath = "//a[text()='" + namePage + "']";
        waitUntilElementVisible(xPath, 5);
        clickLinkTextWithLabel(namePage);
    }

    public void clickOnAction(String button){
        waitElementToBeVisible("//i[contains(@class,'mdi-eye')]");
        waitABit(500);
        clickOnElement("//*[contains(@class, 'action-bar__item') or contains(@class,'publish-actions')]//*[contains(text(),'"+button+"')]");
        waitForPageLoad();
    }

    public void openThemeInSideBar(){
        clickOnElement("//*[contains(@class, 'mdi-open-in-new')]");
        waitForPageLoad();
    }

    public void verifyNameProductAndCollection(String text){
        waitForPageLoad();
        switchToLatestTab();
        String xpath = "//*[contains(@class, 'product__name') or contains(@class,'breadcrumbs-title') or contains(@class,'text-wrapper')]";
        verifyElementText(xpath, text.toUpperCase());
    }

    public void verifyTextHomeIsTranslated(String input){
        String xpath = "//div[@class='']//span[contains(text(),'"+ input + "')]";
        isElementExist(xpath);
    }

    public void switchLanguage(String language) {
        clickOnElement("//*[contains(text(),'Select language') or contains(text(),'语言')]");
        clickOnElement("//div[normalize-space()='"+language+"']");
        waitForPageLoad();
    }
}
