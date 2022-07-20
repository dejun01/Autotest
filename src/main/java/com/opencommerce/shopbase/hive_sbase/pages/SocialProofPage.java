package com.opencommerce.shopbase.hive_sbase.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class SocialProofPage extends SBasePageObject {
    public SocialProofPage(WebDriver driver) {
        super(driver);
    }

    int preView;
    int preClick;
    float preCR;

    public void verifyDisplayID() {
        verifyElementPresent("//a[normalize-space()='Id']//parent::th", true);
    }

    public void verifyName() {
        verifyElementPresent("//a[normalize-space()='Name']//parent::th", true);
    }

    public void verifyDisplayWaitingtime() {
        verifyElementPresent("//a[normalize-space()='Display Time']//parent::th", true);
    }

    public void verifyDisplayDisplaytime() {
        verifyElementPresent("//a[normalize-space()='Waiting Time']//parent::th", true);
    }

    public void verifyDisplayClick() {
        verifyElementPresent("//a[normalize-space()='Click']//parent::th", true);
    }

    public void verifyDisplayCreatedAt() {
        verifyElementPresent("//a[normalize-space()='Created At']//parent::th", true);
    }

    public void verifyDisplayUpdatedAt() {
        verifyElementPresent("//a[normalize-space()='Updated At']//parent::th", true);
    }

    public void verifyDisplayEditbutton() {
        verifyElementPresent("//a[normalize-space()='Edit']", true);
    }

    public void verifyDisplayDeletebutton() {
        verifyElementPresent("//a[normalize-space()='Delete']", true);
    }

    public void verifyDisplayCopyScriptbutton() {
        verifyElementPresent("//a[normalize-space()='Copy script']", true);
    }

    public void enterName(String name) {
        inputTextByJS("//input[contains(@id,'name')]", name);
    }

    public void enterDisplaytime(String displaytime) {
        inputTextByJS("//input[contains(@id,'displayTime')]", displaytime);
    }

    public void enterWaitingtime(String waitingtime) {
        inputTextByJS("//input[contains(@id,'waitingTime')]", waitingtime);
    }
    public void enterDelaytime(String delaytime) {
        inputTextByJS("//input[contains(@id,'delayTime')]",delaytime);
    }

    public void enterTitle(String title) {
        inputSlowly("//input[contains(@name,'data-title')]", title);
    }

    public void enterContent(String content) {
        inputSlowly("//input[contains(@name,'data-content')]", content);
    }

    public void enterTime(String time) {
        inputSlowly("//input[contains(@name,'data-time')]", time);
    }

    public void enterLink(String link) {
        inputSlowly("//input[contains(@name,'data-link')]", link);
    }

    public void uploadImage(String image) {
        String Xpath = "//span[contains(@class,'file-uploads')]//child::input[@type='file']";
        chooseAttachmentFile(Xpath, image);
        waitElementToBePresent("//span[contains(@class,'file-uploads')]//following::img");
        scrollIntoElementView("//button[normalize-space()='Start Upload']");
    }

    public void clickEditbutton(String nameSP) {
        String Xpath = "//tr//descendant::a[normalize-space()='"+nameSP+"']//following::td[9]//a[normalize-space()='Edit']";
        clickOnElement(Xpath);
    }

    public void verifyCreateSocialProofSuccess() {
        verifyElementPresent("//a[normalize-space()='Create']", false);
    }

    public void verifyNotCreateSocialProof() {
        verifyElementPresent("//a[normalize-space()='Create']", true);
    }
    public void clickonAddnew() {
        clickOnElement("//a[normalize-space()='Add new']");
    }

    public void verifyMsg(String message) {
        verifyElementText("//li[normalize-space()='This value should be positive.']", message);
    }
    public void verifyErrorMsg(String message) {
        verifyElementText("//div[@class='read-more-wrap']",message);
    }

    public void clickDeletebutton() {
        String Xpath = "//tr//descendant::a[normalize-space()='Social proof MKT']//following::td[9]//a[normalize-space()='Delete']";
        clickOnElement(Xpath);
    }

    public void clickYesdelete() {
        clickOnElement("//button[@type='submit']");
    }

    public void verifyMsgWhenInputSocialProofExist(String message) {
        verifyElementText("//li[normalize-space()='Unique index duplicate entry name, this name had existed']", message);
    }

    public void verifyDisplaySocialProof() {
        verifyElementPresent("//*[normalize-space()='lisa']", true);
    }

    public int getView(String id) {
        String view = getText("//td[@objectid='" + id + "'][8]");
        preView = Integer.parseInt(view);
        System.out.println("preView là:" + preView);
        return preView;
    }

    public int getClick(String id) {
        String click = getText("//td[@objectid='" + id + "'][7]");
        preClick = Integer.parseInt(click);
        System.out.println("preClick:" + preClick);
        return preClick;
    }
    public float getCR(String id) {
        String Xpath = getText("//td[@objectid='"+ id +"'][9]").trim().replaceAll("%","");
        preCR = Float.parseFloat(Xpath);
        System.out.println("preCR:" + preCR);
        return preCR;
    }

    public void verifyViewAndClickAfterSocialProofDisplay(String id) {
        String xpath = getText("//td[@objectid='" + id + "'][8]");
        int afterView = Integer.parseInt(xpath);
        String xpath1 = getText("//td[@objectid='" + id + "'][7]");
        int afterClick = Integer.parseInt(xpath1);
        float a = (float) preClick/preView;
        float b = a*100;
        System.out.println("Click/View:"+a);
        float CR = (float) Math.round(b*100)/100;
        Assertions.assertThat(afterView).isEqualTo(preView + 1);
        Assertions.assertThat(afterClick).isEqualTo(preClick + 1);
        Assertions.assertThat(CR).isEqualTo(preCR);
    }

    public void clickSocialProof() {
        clickOnElement("//*[normalize-space()='lisa']");
    }
}



