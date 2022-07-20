package com.opencommerce.shopbase.storefront.steps.apps.boost_convert;

import com.opencommerce.shopbase.storefront.pages.apps.boost_convert.BoostConvertShopPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class BoostConvertShopSteps extends ScenarioSteps {
    BoostConvertShopPage boostCovertShopPage;

    @Step
    public void verifySaleNotificationShow(boolean isShow) {
        waitABit(1000);
        int i = 1;
        while (isNotiExist() != isShow) {
            waitABit(5000);
            refreshPage();
            i++;
            if (i == 5)
                break;
        }
        assertThat(isNotiExist()).isEqualTo(isShow);
    }

    @Step
    public boolean isNotiExist() {
        return boostCovertShopPage.isSaleNotiExist();
    }

    @Step
    public boolean convertStatus(String status) {
        boolean st = false;
        if (status.equalsIgnoreCase("on")) {
            st = true;
        }
        return st;
    }

    @Step
    public int getNumberItemInStock() {
        int number = boostCovertShopPage.getNumberItemInStock();
        return number;
    }

    @Step
    public void verifyStyleMessage(String xpathParent, String msgStyle) {
        boostCovertShopPage.verifyStyleMessage(xpathParent, msgStyle);
    }

    @Step
    public void refreshPage() {
        boostCovertShopPage.refreshPage();
        boostCovertShopPage.waitForPageLoad();
    }

    @Step
    public void verifyProductCountdownShown(boolean isShow) {
        int i = 1;
        while (!isShowProductCountdown() == isShow) {
            boostCovertShopPage.refreshPage();
            waitABit(5000);
            i++;
            if (i == 5) {
                break;
            }
        }
        assertThat(isShowProductCountdown()).isEqualTo(isShow);
    }

    public boolean isShowProductCountdown() {
        return boostCovertShopPage.isShowProductCountdown();
    }

    @Step
    public void verifyTimerCountdownShown(boolean isShow) {
        boostCovertShopPage.verifyTimerCountdownShown(isShow);
    }

    @Step
    public void verifyShowNotificationOfProduct(String productName, boolean isShow) {
        int i = 1;
        while (boostCovertShopPage.isExistNotificationOfProduct(productName) != isShow) {
            waitABit(5000);
            refreshPage();
            i++;
            if (i == 5)
                break;
        }
        assertThat(boostCovertShopPage.isExistNotificationOfProduct(productName)).isEqualTo(isShow);
    }

    @Step
    public void verifyNotificationTitle(String sTitle) {
        boostCovertShopPage.verifyNotificationTitle(sTitle);
    }

    private String getNotificationTitle() {
        return boostCovertShopPage.getNotificationTitle();
    }

    @Step
    public void verifySaleNotificationShowed(String product, boolean isShow) {
        if (product.isEmpty()) {
            verifySaleNotificationShow(isShow);
        } else {
            verifyShowNotificationOfProduct(product, isShow);
        }
    }

    @Step
    public void verifyCheckoutNotificationMsg(String sMessage) {
        String text = boostCovertShopPage.getCheckoutNotification();
        assertThat(text).contains(sMessage);

    }


    public void verifyCheckoutNotification(boolean isShow) {
        waitABit(2000);
        boostCovertShopPage.verifyCheckoutNotification(isShow);
    }

    @Step
    public void verifyPCStyleMessage(String sMessageStyle) {
        if (!sMessageStyle.isEmpty())
            verifyStyleMessage(boostCovertShopPage.xpathProductCountdown, sMessageStyle);
    }

    ////---------------------------
    @Step
    public void logInfor(String a) {
    }

    public void verifyDisplayTimer(String displayTimer) {
        boostCovertShopPage.verifyTimer(displayTimer);
        boostCovertShopPage.verifyTimerMessage("left to buy");
    }


}
