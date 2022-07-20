package com.opencommerce.shopbase.storefront.pages.apps.boost_convert;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class BoostConvertShopPage extends SBasePageObject {
    public BoostConvertShopPage(WebDriver driver) {
        super(driver);
    }

    String saleNotification = "//div[@class='copt-notifications']//div[contains(@class,'copt-notification copt-notification--common noti-activated noti-circle-shape noti-bottom_left')]";


    public boolean isSaleNotiExist() {
        return isElementExist(saleNotification);
    }

    public boolean isExistNotificationOfProduct(String productName) {
        String xpath = saleNotification + "//div[@class='copt-notification__info']//div[@class='copt-notification__title'][normalize-space()='" + productName + "']";
        return isElementExist(xpath);
    }

    public void verifyNotificationTitle(String sTitle) {
        String xpath = saleNotification + "//div[@class='copt-notification__text']";
        verifyElementText(xpath, sTitle);
    }

    public String getNotificationTitle() {
        String xpath = saleNotification + "//div[@class='copt-notification__text']";
        return getText(xpath);
    }

    String checkoutNoti = "//div[contains(@class,'noti-activated') and contains(@class,'copt-notification--checkout')]";

    public String getCheckoutNotification() {
        return getText(checkoutNoti + "//div[@class='copt-notification__title']");
    }

    public void verifyCheckoutNotification(boolean isShow) {
        verifyElementPresent(checkoutNoti, isShow);
    }

    //-------------------------------
    public int getNumberItemInStock() {
        String XpathString = "//div[@class='copt-product-countdown']//div[@class='copt-product-countdown__message']";
        int number = Integer.parseInt(getText(XpathString).replace("%", "").replace("items", "").replace("left in stock", "").trim());
        return number;
    }

    public void verifyMessage(String xpathParent, String message) {
        String text = getText(xpathParent + "//*[@class='message']");
        assertThat(text).isEqualTo(message);
    }


    public String getStyleMessage(String xpathParent) {
        return getAttributeValue(xpathParent + "//*[@class='message']/span", "style");
    }

    public void verifyProcessColor(String sProcessColor) {
        String style = getAttributeValue("//div[@class='progress-bar blue stripes']/span", "style");
        assertThat(style).contains("background: rgb" + convertToGRBColor(sProcessColor));
    }

    public void verifyBackground(String sBackgroundColor) {
        String style = getAttributeValue("//div[@class='progress-bar blue stripes']", "style");
        assertThat(style).contains("background: rgb" + convertToGRBColor(sBackgroundColor));
    }

    public String xpathProductCountdown = "//div[@class='copt-product-countdown']";

    public void verifyWidgetEligment(String xpathParent, String sWidgetEligment) {
        String style = getAttributeValue(xpathParent, "style");
        assertThat(style).contains("text-align: " + sWidgetEligment.toLowerCase() + ";");
    }

    public boolean isShowProductCountdown() {
        return isElementExist(xpathProductCountdown);
    }


    public void openProductPageFromNotification() {
        clickOnElement(saleNotification + "//div[@class='noti-body']//a[1]");
        waitABit(2000);
        waitForPageLoad();
        waitForEverythingComplete();
    }


    public String xpathTimerCountdown = "//div[contains(@class,'copt-countdown')]//div[contains(@class,'copt-countdown-timer')]";

    public void verifyTimerCountdownShown(boolean isShow) {
        waitABit(2000);
        verifyElementPresent(xpathTimerCountdown, isShow);
    }

    public void verifyTimerMessage(String timerMsg) {
        String xpath = xpathTimerCountdown + "//div[@class='copt-countdown-timer__message']";
        verifyElementText(xpath, timerMsg);
    }

    public void verifyTimer(String displayTimer) {
        String expectTimer[] = displayTimer.split(",");
        String xpath = xpathTimerCountdown + "//div[@class='copt-countdown-timer__digit']";
        waitUntilElementVisible(xpath, 10);
        String actualTimer[] = getText(xpath).split(":");
        assertThat(expectTimer.length).isEqualTo(actualTimer.length);
    }

    public void verifyStyleMessage(String xpathParent, String msgStyle) {
        String style = getStyleMessage(xpathParent);
        if (!msgStyle.isEmpty()) {
            String st[] = msgStyle.split(",");
            assertThat(style).contains("font-family: " + st[0]);
            assertThat(style).contains("font-size: " + st[1]);
        }
    }


}
