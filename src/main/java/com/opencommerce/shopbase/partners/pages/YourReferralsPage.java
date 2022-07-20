package com.opencommerce.shopbase.partners.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class YourReferralsPage extends SBasePageObject {
    public YourReferralsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyHeading(String heading) {
        verifyElementText("//h1", heading);
    }

    public void verifyDescription(String des) {
        verifyElementText("(//p[contains(@class,'profit-statistic-desc')]//span)[1]", des);
    }

    public void verifyFilterDisplay() {
        verifyElementPresent("//div[contains(@class,'profit-statistic-actions-filter')]", true);
    }

    public void verifyStatisticTable() {
        verifyElementPresent("//div[contains(@class,'s-mb64 profit-statistic-table')]", true);
    }

    public void verifyButtonPayoutDisplay() {
        verifyElementText("//div[@class='profit-statistic ver2']//button[@class='s-button is-primary']", "Request payouts");
    }

    public void verifySignUp(String totalUser) {
        String signup = getText("//div[child::p[normalize-space()='Sign-ups'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(signup).isEqualTo(totalUser);
    }

    public void verifyCashBack(String totalCashback) {
        String cashback = getText("//div[child::p[normalize-space()='Cashback'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(cashback).isEqualTo(totalCashback);
    }

    public void verifySaleItems(String saleItems) {
        String sale = getText("//div[child::p[normalize-space()='Sales Items'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(sale).isEqualTo(saleItems);
    }

    public void verifyClickPBase(String clickPbase) {
        String click = getText("//div[child::p[normalize-space()='Clicks'] and @class='stats-info text-center']//p[@class='stats-data']");
        assertThat(click).isEqualTo(clickPbase);
    }
}
