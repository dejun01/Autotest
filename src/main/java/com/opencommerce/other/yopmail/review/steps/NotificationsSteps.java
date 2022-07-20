package com.opencommerce.other.yopmail.review.steps;

import com.opencommerce.other.yopmail.review.pages.NotificationsPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class NotificationsSteps extends ScenarioSteps {
    NotificationsPages notificationsPages;

    @Step
    public void openURL(String url) {
        notificationsPages.openURL(url);
    }

    @Step
    public void typeEmailAndCheckInbox(String email) {
        notificationsPages.inputEmail(email);
        notificationsPages.clickBtnCheckInbox();
        notificationsPages.waitForPageLoad();
    }

    @Step
    public void verifyNotification(String reviewTitle, boolean isShow) {
        notificationsPages.verifyNotification(reviewTitle, isShow);
    }

    @Step
    public void verifySendMailToMerchant(String title, String message, String userName, String fileDetail) {
        notificationsPages.verifyTitle(title);
        notificationsPages.verifyMessage(message);
        notificationsPages.verifyUserName(userName);
        notificationsPages.verifyFileDetail(fileDetail);
    }
}
