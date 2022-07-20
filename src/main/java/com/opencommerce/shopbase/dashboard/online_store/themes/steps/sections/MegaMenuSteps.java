package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.MegaMenuPage;
import common.SBasePageObject;
import net.thucydides.core.annotations.Step;

import java.sql.Driver;

public class MegaMenuSteps extends CommonSteps {
    MegaMenuPage megaMenuPage;

    @Step
    public void clickOnTextLink(String textLink){
        megaMenuPage.clickOnTextLink(textLink);
    }

    @Step
    public void selectLinkTitle(String value){
        megaMenuPage.selectLinkTitle(value);
    }

    @Step
    public void verifyShowMegaMenu(String linkTitle, boolean showMegaMenu) {
        megaMenuPage.verifyShowMegaMenu(linkTitle, showMegaMenu);
    }

    @Step
    public void verifyMenuTitle(String linkTitle, String menuTitle) {
        megaMenuPage.verifyMenuTitle(linkTitle, menuTitle);
    }

    @Step
    public void clickOnMenuTitle(String linkTitle, String menuTitle) {
        megaMenuPage.clickOnMenuTitle(linkTitle, menuTitle);
        waitABit(5000);
    }

    @Step
    public void hoverOnMegaMenu(String linkTitle) {
        megaMenuPage.hoverOnMegaMenu(linkTitle);
    }

    @Step
    public void selectMenuList(String menuLink){
        megaMenuPage.selectMenuList(menuLink);
    }

    @Step
    public void removeAllContentMegaMenu(){
        int n = megaMenuPage.countContent();
        System.out.println(n);
        for (int i = n; i >= 1; i--) {
            megaMenuPage.openBlockContentMegaMenuByIndex(i);
            megaMenuPage.removeContentMegaMenu(i);
        }
    }

    @Step
    public void clickOnImage(String linkTitle, String menuTitle) {
        megaMenuPage.clickOnImage(linkTitle, menuTitle);
        waitABit(5000);
    }

    @Step
    public void verifyMenuList(String linkTitle, String menuTitle, String menuList) {
    }

    @Step
    public void verifyHeadline(String linkTitle, String menuTitle, String headline) {
        megaMenuPage.verifyHeadline(linkTitle, menuTitle, headline);
    }

    public void verifyTextAreaTopContent(String linkTitle, String textAreaTopContent) {
        if(!textAreaTopContent.isEmpty()) {
            megaMenuPage.verifyTextAreaTopContent(linkTitle, textAreaTopContent);
        }
    }

    public void verifyImageTopContent(String linkTitle,String imageTopContent) {
        if(!imageTopContent.isEmpty()){
            megaMenuPage.verifyImageTopContent(linkTitle,imageTopContent);
        }
    }

    public void verifyImageCaptionTopContent(String imageTopContent,String linkTitle, String imageCaptionTopContent) {
        if(!imageCaptionTopContent.isEmpty()&&!imageTopContent.isEmpty()){
            megaMenuPage.verifyImageCaptionTopContent(linkTitle,imageCaptionTopContent);
        }
    }

    public void verifyImageBottomContent(String linkTitle, String imageBottomContent) {
        if(!imageBottomContent.isEmpty()){
            megaMenuPage.verifyImageBottomContent(linkTitle,imageBottomContent);
        }
    }

    public void verifyCaptionBottomContent(String imageBottomContent,String linkTitle, String imageCaptionBottomContent) {
        if(!imageCaptionBottomContent.isEmpty()&&!imageBottomContent.isEmpty()){
            megaMenuPage.verifyCaptionBottomContent(linkTitle,imageCaptionBottomContent);
        }
    }

    public void verifyTextAreaBottomContent(String linkTitle, String textAreaBottomContent) {
        if (!textAreaBottomContent.isEmpty()){
            megaMenuPage.verifyTextAreaBottomContent(linkTitle,textAreaBottomContent);
        }
    }
}
