package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.TimerCountdownPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class TimerCountdownSteps extends ScenarioSteps {
    TimerCountdownPages timerCountdownPages;

    @Step
    public void verifyHeading(String heading) {
        timerCountdownPages.verifyHeading(heading);
    }

    @Step
    public void verifyTitle(String title) {
        timerCountdownPages.verifyTitle(title);
    }

    @Step
    public void verifyBody(String body) {
        timerCountdownPages.verifyBody(body);
    }

    @Step
    public void verifyEndDate(String endDate) {
        timerCountdownPages.verifyEndDate(endDate);
    }

    @Step
    public void verifyShowInWeek(boolean showInWeek) {
        timerCountdownPages.verifyShowInWeek(showInWeek);
    }

    @Step
    public void verifyHideOnTimeout(boolean hideOnTimeout) {
        timerCountdownPages.verifyHideOnTimeout(hideOnTimeout);
    }

    @Step
    public void verifyType(String type) {
        timerCountdownPages.verifyType(type);
    }

    @Step
    public void verifyCustomColor(String customColor) {
        timerCountdownPages.verifyCustomColor(customColor);
    }

    @Step
    public void verifyStartColor(String startColor) {
        timerCountdownPages.verifyStartColor(startColor);
    }

    @Step
    public void verifyEndColor(String endColor) {
        timerCountdownPages.verifyEndColor(endColor);
    }

    @Step
    public void verifyDirection(String direction) {
        timerCountdownPages.verifyDirection(direction);
    }

    @Step
    public void verifyImageBackground(String imageBackground) {
        timerCountdownPages.verifyImageBackground(imageBackground);
    }

    @Step
    public void verifyColorOverlayBackground(String colorOverlayBackground) {
        timerCountdownPages.verifyColorOverlayBackground(colorOverlayBackground);
    }

    @Step
    public void verifyHeadingColor(String headingColor) {
        timerCountdownPages.verifyHeadingColor(headingColor);
    }

    @Step
    public void verifyHeadingFont(String headingFont) {
        timerCountdownPages.verifyHeadingFont(headingFont);
    }

    @Step
    public void verifyHeadingSize(String headingSize) {
        timerCountdownPages.verifyHeadingSize(headingSize);
    }

    @Step
    public void verifyAlignmentHeading(String alignmentHeading) {
        timerCountdownPages.verifyAlignmentHeading(alignmentHeading);
    }

    @Step
    public void verifyTimerColor(String timerColor) {
        timerCountdownPages.verifyTimerColor(timerColor);
    }

    @Step
    public void verifyTimerFont(String timerFont) {
        timerCountdownPages.verifyTimerFont(timerFont);
    }

    @Step
    public void verifyTimerSize(String timerSize) {
        timerCountdownPages.verifyTimerSize(timerSize);
    }

    @Step
    public void verifyAlignmentTimer(String alignmentTimer) {
        timerCountdownPages.verifyAlignmentTimer(alignmentTimer);
    }

    @Step
    public void verifyTitleColor(String titleColor) {
        timerCountdownPages.verifyTitleColor(titleColor);
    }

    @Step
    public void verifyTitleFont(String titleFont) {
        timerCountdownPages.verifyTitleFont(titleFont);
    }

    @Step
    public void verifyTitleSize(String titleSize) {
        timerCountdownPages.verifyTitleSize(titleSize);
    }

    @Step
    public void verifyAlignmentTitle(String alignmentTitle) {
        timerCountdownPages.verifyAlignmentTitle(alignmentTitle);
    }

    @Step
    public void verifyBodyColor(String bodyColor) {
        timerCountdownPages.verifyBodyColor(bodyColor);
    }

    @Step
    public void verifyBodyFont(String bodyFont) {
        timerCountdownPages.verifyBodyFont(bodyFont);
    }

    @Step
    public void verifyBodySize(String bodySize) {
        timerCountdownPages.verifyBodySize(bodySize);
    }

    @Step
    public void verifyAlignmentBody(String alignmentBody) {
        timerCountdownPages.verifyAlignmentBody(alignmentBody);
    }

    @Step
    public void verifyBorderTopColor(String borderTopColor) {
        timerCountdownPages.verifyBorderTopColor(borderTopColor);
    }

    @Step
    public void verifyBorderTopSize(String borderTopSize) {
        timerCountdownPages.verifyBorderTopSize(borderTopSize);
    }

    @Step
    public void verifyBorderBottomColor(String borderBottomColor) {
        timerCountdownPages.verifyBorderBottomColor(borderBottomColor);
    }

    @Step
    public void verifyBorderBottomSize(String borderBottomSize) {
        timerCountdownPages.verifyBorderBottomSize(borderBottomSize);
    }

    @Step
    public void verifyPosition(String position) {
        timerCountdownPages.verifyPosition(position);
    }

    @Step
    public void verifyMinHeight(String minHeight) {
        timerCountdownPages.verifyMinHeight(minHeight);
    }

    @Step
    public void verifyMaxWidth(String maxWidth) {
        timerCountdownPages.verifyMaxWidth(maxWidth);
    }

    @Step
    public void verifyCustomWidth(String customWidth) {
        timerCountdownPages.verifyCustomWidth(customWidth);
    }
}
