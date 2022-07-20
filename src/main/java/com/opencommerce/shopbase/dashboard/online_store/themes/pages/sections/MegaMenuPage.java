package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

public class MegaMenuPage extends SBasePageObject {
   public MegaMenuPage(WebDriver driver){
       super(driver);
   }

   public void clickOnTextLink(String textLink){
       waitElementToBeVisible("//p[normalize-space()='"+textLink+"']", 5);
       clickOnElementByJs("//p[normalize-space()='"+textLink+"']");
   }

   public void selectLinkTitle(String value){
       waitElementToBeVisible("//div[@currentlayout and descendant::p[normalize-space()='Link title']]//select");
       clickOnElementByJs("//div[@currentlayout and descendant::p[normalize-space()='Link title']]//select");
       clickOnElementByJs("//div[descendant::p[normalize-space()='Link title']]//option[normalize-space()='"+value+"']");
   }

    public void verifyShowMegaMenu(String linkTitle, boolean showMegaMenu) {
       String xpath = "//a[@data-dropdown-rel='"+linkTitle.toLowerCase()+"']//span[@style='display: none;']";
       verifyElementPresent("//a[@data-dropdown-rel='"+linkTitle.toLowerCase()+"']//span[@style='display: none;']", !showMegaMenu);
    }

    public void verifyMenuTitle(String linkTitle, String menuTitle) {
       if(!menuTitle.isEmpty()){
           verifyElementPresent("//div[@data-dropdown='"+linkTitle.toLowerCase()+"']//a[normalize-space()='"+menuTitle+"']", true);
       }
    }

    public void hoverOnMegaMenu(String linkTitle) {
       hoverOnElement("//a[@data-dropdown-rel='"+linkTitle.toLowerCase()+"']");
    }

    public void clickOnMenuTitle(String linkTitle, String menuTitle) {
        clickOnElementByJs("//div[@data-dropdown='"+linkTitle.toLowerCase()+"']//a[normalize-space()='"+menuTitle+"']");
        waitABit(5);
    }

    public void selectMenuList(String menuLink){
        clickOnElementByJs("//div[contains(@class,'is-active')]//select");
        clickOnElementByJs("//div[contains(@class,'is-active')]//option[normalize-space()='"+menuLink+"']");
    }

    public int countContent(){
       return countElementByXpath("//div[@class='card without-padding']//div[contains(@class,'s-collapse-item draggable-item')]");
    }

    public void openBlockContentMegaMenuByIndex(int i){
       clickOnElementByJs("(//div[@class='card without-padding']//div[@role='button'])[" + i + "]");
    }

    public void removeContentMegaMenu(int i){
        String xpath = "(//button[contains(text(),'Remove content')])[" + i + "]";
        clickOnElement(xpath);
    }

    public void clickOnImage(String linkTitle, String menuTitle) {
        clickOnElementByJs("//div[@data-dropdown='"+linkTitle.toLowerCase()+"']//div[@class='mega-menu__column-menu' and descendant::a[normalize-space()='"+menuTitle+"']]//img");
    }

    public void verifyHeadline(String linkTitle, String menuTitle, String headline) {
       verifyElementPresent("//div[@data-dropdown='"+linkTitle.toLowerCase()+"']//div[@class='mega-menu__column-menu' and descendant::a[normalize-space()='"+menuTitle+"']]//div[normalize-space()='"+headline+"']", true);
    }

    public void verifyTextAreaTopContent(String linkTitle, String textAreaTopContent) {
       verifyElementText("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'richtext-top')]//p",textAreaTopContent);
    }

    public void verifyImageTopContent(String linkTitle,String imageTopContent) {
       verifyElementPresent("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'mega-menu__image-top')]//img",true);
    }

    public void verifyImageCaptionTopContent(String linkTitle, String imageCaptionTopContent) {
       verifyElementText("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'mega-menu__image-top')]//div",imageCaptionTopContent.toUpperCase());
    }

    public void verifyImageBottomContent(String linkTitle, String imageBottomContent) {
        verifyElementPresent("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'mega-menu__image-bottom')]//img",true);
    }

    public void verifyCaptionBottomContent(String linkTitle, String imageCaptionBottomContent) {
        verifyElementText("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'mega-menu__image-bottom')]//div",imageCaptionBottomContent.toUpperCase());
    }

    public void verifyTextAreaBottomContent(String linkTitle, String textAreaBottomContent) {
        verifyElementText("//div[@data-dropdown='"+linkTitle+"']//div[contains(@class,'richtext-bottom')]//p",textAreaBottomContent);
    }
}
