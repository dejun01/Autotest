package com.opencommerce.shopbase.dashboard.home.page;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import static org.assertj.core.api.Java6Assertions.assertThat;

public class HomePage extends SBasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    String shop = LoadObject.getProperty("shop");
    String xpathWidget = "//div[contains(@class,'widget-dashboard widget-dashboard')]";

    public void verifyHomeDisplay() {
        waitForEverythingComplete();
        waitABit(10000);
        verifyElementPresent("//main[contains(@class,'unite-ui-dashboard__main')]", true);
    }

    public void verifyWidgetDisplay(boolean isShow) {
        verifyElementPresent(xpathWidget, isShow);
    }

    public int getNumberOfWidget() {
        return countElementByXpath(xpathWidget);
    }

    public void verifyTitleWidget(String title, int res) {
        verifyElementText("(" + xpathWidget + "//*[not(@class='widget-dashboard-sub-title') and contains(@class,'main-title')])[" + res + "]", title);
    }

    public void verifyDescWidget(String desc, int res) {
        if (!desc.isEmpty()) {
            String act = getText("(" + xpathWidget + ")[" + res + "]//p[contains(@class,'widget-dashboard-main-description')]");
            desc = desc.replaceAll("\\s+", " ");
            assertThat(act.replaceAll("\\s+", " ")).isEqualTo(desc.trim());
        }
    }

    public void verifyTypeWidge(String type, int res) {
        String classofWidget = getAttributeValue("(" + xpathWidget + ")[" + res + "]", "class");
        assertThat(classofWidget).contains(type.toLowerCase());
    }

    public void verifyPrimaryBtn(String text, String link, int res) {
        String xpath = "(" + xpathWidget + ")[" + res + "]//a[@class='s-button is-primary s-button-primary' or class='widget-dashboard-primary-button']";
        if (!text.isEmpty()) {
            verifyElementText(xpath, text);
            String href = getAttributeValue(xpath, "href");
            assertThat(href).isEqualTo(link);
        } else verifyElementPresent(xpath, false);
    }

    public void verifySecondaryBtn(String text, String link, int res) {
        String xpath = "(" + xpathWidget + ")[" + res + "]//a[@class='widget-dashboard-secondary-button' or @class='s-button s-button-secondary']";
        if (!text.isEmpty()) {
            verifyElementText(xpath, text);
            String href = getAttributeValue(xpath, "href");
            assertThat(href).isEqualTo(link);
        } else verifyElementPresent(xpath, false);
    }

    public void verifyAssetWidget(String type, String video, String image, int res) {
        if (type.equalsIgnoreCase("image")) {
            String imageURL = getAttributeValue("(" + xpathWidget + ")[" + res + "]//img", "src");
            assertThat(imageURL).isEqualTo(image);
        } else {
            String videoURL = getAttributeValue("(" + xpathWidget + ")[" + res + "]//iframe", "src");
            assertThat(videoURL).isEqualTo(video);
        }
    }

    public void verifyImage(String image, int res) {
        String imageURL = getAttributeValue("(" + xpathWidget + ")[" + res + "]//img", "src");
        assertThat(imageURL).isEqualTo(image);
    }

    public int getNumbeItemInlist(int res) {
        return countElementByXpath("(//div[@class='widget-dashboard widget-dashboard'])[" + res + "]//div[@class='list-sub-item s-flex']");
    }

    public void verifySubListTitle(String title, int widgetRes, int itemRes) {
        verifyElementText("((//div[@class='widget-dashboard widget-dashboard'])[" + widgetRes + "]//div[@class='list-sub-item s-flex']//h5[@class='widget-dashboard-sub-title'])[" + itemRes + "]", title);
    }

    public void verifySubListDesc(String des, int widgetRes, int itemRes) {
        verifyElementText("((//div[@class='widget-dashboard widget-dashboard'])[" + widgetRes + "]//div[@class='list-sub-item s-flex']//p[@class='widget-dashboard-sub-description'])[" + itemRes + "]", des);

    }

    public void verifySubListImage(String image, String linkImage, int widgetRes, int itemRes) {
        String xpath = "((//div[@class='widget-dashboard widget-dashboard'])[" + widgetRes + "]//div[@class='list-sub-item s-flex']//img)[" + itemRes + "]";
        String imgSRC = getAttributeValue(xpath, "src");
        assertThat(imgSRC).isEqualTo(image);
    }

    public void verifyReward(String reward, int res) {
        String xpath = "(" + xpathWidget + ")[" + res + "]//p[@class='race-challenge-reward-text']";
        verifyElementText(xpath, reward);
    }

    public void verifyGoal(String goal, int res) {
        String xpath = "(" + xpathWidget + ")[" + res + "]//p[contains(@class,'current-point')]";
        String point = getText(xpath);
        String actGoal = (point.split("/"))[1];
        assertThat(actGoal).isEqualTo(goal);
    }

    public boolean isEndRaceChallenge(int res) {
        return isElementExist("(" + xpathWidget + ")[" + res + "]//p[@class='race-challenge-end-time s-mb8' and normalize-space()='Race finished. Wish you luck next time.']");
    }

    public void verifyFinishChallenge(int res) {
        verifyElementText("(" + xpathWidget + ")[" + res + "]//p[@class='race-challenge-end-time s-mb8']", "Race finished. Wish you luck next time.");
    }

    public void clickonAddnewbutton() {
        clickOnElement("//a[normalize-space()='Add new']");
    }

    public void verifyCreateWidgetRaceChallengeSuccess() {
        verifyElementPresent("//a[normalize-space()='Create']", false);
    }

    public void clickDeletebutton(String name) {
        clickOnElement("//a[normalize-space()='" + name + "']//ancestor::tr//child::a[@title='Delete']");
    }

    public void clickYesbutton() {
        clickOnElement("//button[@class='btn btn-danger']");
    }

    public void verifyTitleWidgetRaceChallenge() {
        verifyElementPresent("//h4[normalize-space()='Widget race challenge test']", true);
    }

    public void verifyDescriptionWidgetRaceChallenge() {
        verifyElementPresent("//p[normalize-space()='Get 10 sales in your first 14 days and get $25 cash']", true);
    }

    public void verifyGoalWidgetRaceChallenge() {
        verifyElementPresent("//h4[normalize-space()='Widget race challenge test']//ancestor::div[@class='widget-dashboard-main-content text-center']//p[strong='0']", true);
        verifyElementPresent("//p[text()='/10']", true);
    }

    public void verifyBntText() {
        verifyElementPresent("//a[normalize-space()='Bnt Text']", true);
    }

    public void verifySecBntText() {
        verifyElementPresent("//a[contains(text(),'Sec Bnt Text')]", true);
    }

    public void verifyPointWidgetRaceChallenge() {
        verifyElementPresent("//h4[normalize-space()='Widget race challenge test']//ancestor::div[@class='widget-dashboard-main-content text-center']//p[strong='1']", true);
        verifyElementPresent("//p[text()='/10']", true);
    }

    public void verifyCurrentPoint(String shoptype, String point) {
        String actPoint = getText("//div[@class='current-item']//strong");
        int a = (int) Math.ceil(Double.parseDouble(point));
        if (shoptype.equalsIgnoreCase("ShopBase"))
            assertThat(actPoint).isEqualTo("$ " + String.valueOf(a));
        else assertThat(actPoint).isEqualTo(String.valueOf(a) + " points");
    }

    public String convertDate(long time) {
        java.sql.Timestamp timeStamp = new java.sql.Timestamp(time);
        java.sql.Date date = new java.sql.Date(timeStamp.getTime());
        DateFormat curDate = new SimpleDateFormat("dd/MM/yyyy");
        return curDate.format(date);
    }

    public void verifyContestTime(String startdate, String enddate) {
        String startime = convertDate(Long.parseLong(startdate + "000"));
        String endtime = convertDate(Long.parseLong(enddate + "000"));
        String time = getText("//p[@class='timming-contest ext-contest']");
        String date[] = time.split("-");
        String actStart = date[0].trim();
        String actEnd = (date[1].replace(" (UTC)", "")).trim();
        assertThat(actStart).isEqualTo(startime);
        assertThat(actEnd).isEqualTo(endtime);
    }

    public void verifyHeader(String header) {
//        verifyElementText("//h3[@class='contest-main-title text-center']", header);
        String act = getText("//h3[@class='contest-main-title text-center']");
        assertThat(act).contains(header);
    }

    public void verifySubtext(String subtext) {
        verifyElementText("//h3[@class='contest-main-title text-center']//span", subtext);
    }

    public void verifyImageContest(String image) {
        String actImg = getAttributeValue("//div[contains(@class,'on-contest contest-progress-container')]", "style");
        assertThat(actImg).contains(image);
    }

    public void verifyLinkContest(String link) {
        String actLink = getAttributeValue("//div[contains(@class,'growth-team contest-container on-contest-progress')]//a[normalize-space()='Learn more']", "href");
        assertThat(actLink).contains(link);
    }

    public void verifyIconStart(String icon) {
        String act = getAttributeValue("//div[@class='icon-wrap icon-start']//img", "src");
        assertThat(act).isEqualTo(icon);
    }

    public void verifyIconFinish(String icon) {
        String act = getAttributeValue("//div[@class='icon-wrap icon-end']//img", "src");
        assertThat(act).isEqualTo(icon);
    }

    public void verifyPriColorProBar(String s) {
    }

    public int getNumberOfPrices() {
        return countElementByXpath("//div[contains(@class,'item-grid item-level')]");
    }

    public void verifyTheshold(String shoptype, String threshold, int res) {
        String act = getText("((//div[contains(@class,'item-grid item-level')])[" + res + "]//span)[1]");
        if (shoptype.equalsIgnoreCase("ShopBase"))
            assertThat(act.replaceAll(",", "")).isEqualTo("$" + threshold);
        else assertThat(act.replaceAll(",", "")).isEqualTo(threshold + " points");

    }

    public void verifyPrize(String prize, int res) {
        String act = getText("((//div[contains(@class,'item-grid item-level')])[" + res + "]//span)[2]");
        assertThat(act).isEqualTo(prize);
    }

    public void clickTitleTabOnboarding(String title) {
        clickOnElement("//div[@class='onboarding-box newUI']//div[@class='left-side']//div[normalize-space()='" + title + "']");
    }

    public void verifyTitleItem(String title, int res) {
        verifyElementText("(//div[@class='onboarding-box newUI']//div[@class='s-flex right-side-content']//span[@class='title text-bold-medium'])[" + res + "]", title.trim());
    }

    public void verifyDescItem(String desc, int res) {
        verifyElementText("(//div[@class='onboarding-box newUI']//div[@class='s-flex right-side-content']//p)[" + res + "]", desc);
    }

    public void verifyImageItem(String img, int res) {
        String src = getAttributeValue("(//div[@class='onboarding-box newUI']//div[@class='s-flex right-side-content']//img)[" + res + "]", "src");
        assertThat(src).isEqualTo(img);
    }

    public void verifyButtonItem(String btn, int res) {
        verifyElementText("(//div[@class='onboarding-box newUI']//div[@class='s-flex right-side-content']//button)[" + res + "]", btn);
    }

    public void clickNextSlideOnboarding() {
        clickOnElement("//div[@class='onboarding-box newUI']//i[@class='mdi mdi-chevron-right mdi-24px']");
    }

    public void clickOtherWaysToGetStarted() {
        clickOnElement("//span[normalize-space()='Other ways to get started']");
    }

    public int getListActiveTasks() {
        int total = countElementByXpath("//div[contains(@class,'step-card steps-item s-py16 d-flex align-items-center')]");
        int marked = countElementByXpath("//div[@class='checkmark s-flex s-flex--justify-center s-flex--align-center finished']");
        return total - marked;
    }

    public void verifyHideOtherWaysToGetStarted() {
        verifyElementPresent("//span[normalize-space()='Other ways to get started']", false);
    }

    public void skipAllTaskInProgressBar() {
        hoverOnElement("//div[@class='onboarding-progress-bar text-pointer s-pl24 s-pr24']");
        String xpath = "//a[normalize-space()='Skip this step']";
        while (isElementExist(xpath)) {
            clickOnElement(xpath);
            waitABit(500);
        }
        clickBtn("Back to Home");
    }

    public void verifyHideOnboarding() {
        verifyElementPresent("//div[@class='onboarding-box newUI']", false);
    }

    public void verifyHideProgresbar() {
        verifyElementPresent("//div[@class='onboarding-progress-bar text-pointer s-pl24 s-pr24']", false);
    }

    public void verifyDisplayDisablePassworkBlock(boolean isDisplay) {
        refreshPage();
        verifyElementPresent("//div[@class='disabled-password text-center']", isDisplay);
    }

    public void clickSkip() {
        clickOnElement("//a[not(@class='btn-skip text-link text-normail text-pointer s-ml8 disabled') and normalize-space()='Skip']");
    }

    public void verifyDisplayConfirmPlan(boolean isDisplay) {
        verifyElementPresent("//div[@class='start-selling text-center']", isDisplay);
    }

    public void verifyCompletedItemOnboarding(String title) {
        verifyElementPresent("//div[@class='item done' and normalize-space()='" + title + "']", true);
    }

    public void clickBtnOnboarding(String btn, String res) {
        if (res.contains("on1"))
            clickOnElement("(//div[@class='onboarding-box newUI']//button[normalize-space()='" + btn + "'])[2]");
        else
            clickOnElement("//div[@class='onboarding-box newUI']//button[normalize-space()='" + btn + "']");
    }

    public void clickButtonOtherWays(String label) {
        clickOnElement("//div[@class='step-card--left']//button[normalize-space()='" + label + "']");
    }

    public void verifyCompleteItemOtherWays(String item) {
        verifyElementPresent("//div[contains(@class,'step-card steps-item') and descendant::span[normalize-space()='" + item + "']]//div[contains(@class,'finished')]", true);
    }

    public void verifyDisableButtonOtherWays(String item) {
        verifyElementPresent("//div[contains(@class,'step-card steps-item') and descendant::span[normalize-space()='" + item + "']]//a[contains(@class,'disabled')]", true);
    }

    public void selectPlanInTheHomePage() {
        clickOnElement("//span[normalize-space()='Select a plan']");
    }


    public void openUpsellscreen() {
        clickOnElement("//a[contains(@href,'up-sell')]");
    }

    public void clickBtnCreateOffer() {
        clickOnElement("//span[normalize-space()='Create offer']");
    }

    public void chooseRecommendVariantWith(String recommendVariantWith) {
        clickOnElement("//span[normalize-space()='" + recommendVariantWith + "']");
    }

    public void clickBtnSubmitOffer() {
        clickOnElement("//span[normalize-space()='Submit offer']");
    }

    public void clickBtnDisablePassword() {
        clickOnElement("//span[normalize-space()='Disable password']");
    }
}
