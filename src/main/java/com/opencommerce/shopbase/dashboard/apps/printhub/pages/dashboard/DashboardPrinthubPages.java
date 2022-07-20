package com.opencommerce.shopbase.dashboard.apps.printhub.pages.dashboard;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DashboardPrinthubPages extends SBasePageObject {
    public DashboardPrinthubPages(WebDriver driver) {
        super(driver);
    }

    String xpathWidget = "//div[contains(@class,'widget-dashboard widget-dashboard')]";

    public void verifyDashboardPrinthubScreen() {
        verifyElementPresent("//h2[normalize-space()='Welcome to PrintHub!']", true);
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

//    public String getNameItem(int res) {
//        return getText("((//table[@class='s-table phub-dashboard-table s-mt10 table-hover expanded table-variants']//tr)[" + res + "]//td)[1]");
//    }

    public void verifyItemName(String expName) {
        verifyElementPresent("//table//tr//td[normalize-space()='" + expName + "']", true);

    }

    public void verifyItemType(String name, String expType) {
        verifyElementText("(//table//tr[child::td[normalize-space()='" + name + "']]//td)[2]", expType);
    }

    public void verifyQuantity(String name, String expQuantity) {
        int qlt = Integer.parseInt(expQuantity);
        expQuantity = String.format("%,d", qlt);
        verifyElementText("(//table//tr[child::td[normalize-space()='" + name + "']]//td)[3]", expQuantity);
    }

    //    public void verifyTotalOrder(String totalOrder) {
//        verifyElementText("(//div[@class='phub-analytics-item__content s-ml16'])[1]//h3", totalOrder);
//
//    }
    public void verifyTotalOrder(String totalOrder) {
        int qlt = Integer.parseInt(totalOrder);
        totalOrder = String.format("%,d", qlt);
        verifyElementText("(//div[@class='phub-analytics-item__content s-ml16'])[1]//h3", totalOrder);

    }

    public void verifyTotalItem(String totalItem) {
        int qlt = Integer.parseInt(totalItem);
        totalItem = String.format("%,d", qlt);
        verifyElementText("(//div[@class='phub-analytics-item__content s-ml16'])[2]//h3", totalItem);
    }

    public void veryfiGoldbaseItem(String goldbaseItem) {
        int qlt = Integer.parseInt(goldbaseItem);
        goldbaseItem = String.format("%,d", qlt);
        verifyElementText("(//div[@class='phub-analytics-item__content s-ml16'])[3]//h3", goldbaseItem);
    }

    public void verifySilverbaseItem(String silverbaseItem) {
        verifyElementText("(//div[@class='phub-analytics-item__content s-ml16'])[4]//h3", silverbaseItem);
    }

    public void enterKeyword(String keyword) {
//        enterInputFieldThenEnter("Search Item Name", keyword, 1);
        String xpath = "//input[@placeholder='Search Item Name']";
        clearTextByJS(xpath);
        waitTypeAndEnter(xpath, keyword);
    }

    public void verifyItemDislay(String keyword) {
        String item = getText("//table[@class ='s-table phub-dashboard-table s-mt10 table-hover expanded table-variants']/tbody/tr/td[1]");
        assertThat(item).contains(keyword);
    }

    public void verifyNoResult(String keyword) {
//        String text = getText("//div[@class='phub-dashboard-table--not-found']/p");
        verifyElementText("//div[@class='phub-dashboard-table--not-found']//p", "No items match your search. Please try another keyword.");
    }

    public void clickFilterTime() {
        clickOnElement("//div[@class='calendar-btn s-mb16 s-popover__reference']");
        clickOnElement("//i[@class='mdi mdi-calendar-multiple mdi-18px']");
    }

    public void chooseTimeRange(String time) {
        clickOnElement("//div[@class='s-picker-panel__sidebar']/button[normalize-space()='" + time + "']");
    }

    public void verifyQuantityActual(String title, String qltAct) {
        int qlt = Integer.parseInt(qltAct);
        qltAct = String.format("%,d", qlt);
        verifyElementText("(//table//tr[child::td[normalize-space()='" + title + "']]//td)[5]", qltAct);
    }

    public void verifyQuantityRefund(String title, String qltRef) {
        int qlt = Integer.parseInt(qltRef);
        qltRef = String.format("%,d", qlt);
        verifyElementText("(//table//tr[child::td[normalize-space()='" + title + "']]//td)[4]", qltRef);
    }

    public int countItems() {
        return countElementByXpath("//table[@class='s-table phub-dashboard-table s-mt10 table-hover expanded table-variants']/tbody//tr");
    }

    public List<String> getListTitle(int numberItems) {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < numberItems; i++) {
            String title = getText("((//table//tr)[" + (i + 2) + "]//td)[1]");
            titles.add(title);
        }
        return titles;
    }
}
