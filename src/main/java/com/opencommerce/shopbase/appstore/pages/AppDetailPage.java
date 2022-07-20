package com.opencommerce.shopbase.appstore.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AppDetailPage extends SBasePageObject {
    public AppDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getLogo() {
        return getAttributeValue("//img[@class='detail-app__info__logo']", "src");
    }

    public void verifyName(String exName) {
        verifyElementText("//h1[@class='detail-app__info__title']", exName);
    }

    public void verifyRating(String exRating) {
        if (!exRating.equalsIgnoreCase("0"))
            verifyElementText("//p[@class='detail-app__info__rating__total']", exRating);
        else verifyElementPresent("//p[@class='detail-app__info__rating__total']", false);
    }

    public void verifyCTABeforeInstall(String cta) {
        verifyElementText("//p[@class='detail-app__info__cta-button']", "Install this app");
    }

    public void verifyShortDesc(String desc) {
        verifyElementText("//p[@class='detail-app__info__description']", desc);
    }

    public void verifyDesciption(String desc) {
        String xpath = "//p[normalize-space()='Show full description']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
        String actDes = "";
        List<String> desList = getListText("//div[@class='app-info__content__txt']//p");
        for (int i = 0; i < desList.size(); i++) {
            String des = desList.get(i);
            actDes += des;
            assertThat(desc).contains(des);
        }
        System.out.println("expect des: " + desc);
        System.out.println("actual des: " + actDes);
//        assertThat(actDes).isEqualTo(desc);
    }

    public void verifyDevWeb(String devWeb) {
        String web = getAttributeValue("//a[normalize-space()='Visit our site']", "href");
        assertThat(web).isEqualTo(devWeb);
    }

    public void verifySupportURL(String supportURL) {
        String link = getAttributeValue("//a[normalize-space()='Contact us for support']", "href");
        assertThat(link).isEqualTo(supportURL);
    }

    public void verifyPrice(String pricevalue, String pricetype) {
        String price = "";
        switch (pricetype) {
            case "fee_per_month":
                price = "$" + pricevalue + "/per month";
                break;
            case "free_plan_available":
                price = "Free plan available";
                break;
            case "one_time_charge":
                price = "$" + pricevalue + " for life time";
                break;
            case "free_day_trial":
                price = pricevalue + "days free trial";
                break;
            case "":
            case "free":
                price = "Free";
                break;
        }
//        String price = pricevalue + pricetype;
        verifyElementText("//div[@class='detail-app__info']//p[@class='detail-app__info__rating__type']", price);
    }
}
