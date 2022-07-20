package com.opencommerce.shopbase.dashboard.apps.printhub.steps.scalablepress;

import com.opencommerce.shopbase.dashboard.apps.printhub.pages.scalablepress.ScalablePressPage;

public class ScalablePressSteps {
    ScalablePressPage scalablePressPage;

    public void openScalablePressPage() {
        scalablePressPage.openScalablePressPage();

    }

    public void enterUsername(String userName) {
        scalablePressPage.enterUsername(userName);
    }

    public void enterPwd(String pwd) {
        scalablePressPage.enterPwd(pwd);
    }

    public void clickBtnLogin() {
        scalablePressPage.clickBtn("Log in");
        scalablePressPage.waitForPageLoad();
    }

    public void openClaimPage(String url) {
        scalablePressPage.openUrl(url);
    }

    public void selectAllItems() {
        scalablePressPage.selectAllItems();

    }

    public void clickBtnNextStep() {
        scalablePressPage.clickBtnNextStep();
    }

    public void enterEmail(String contactEmail) {
        scalablePressPage.enterInputField("Contact email", contactEmail);
    }

    public void enterReason(String reason) {
        scalablePressPage.enterReason(reason);
    }

    public void enterRemarks(String s) {
        scalablePressPage.enterRemarks(s);
    }

    public void selectPreferredResolution(String value) {
        scalablePressPage.selectPreferredResolution(value);
    }

    public void clickBtnFileClaim() {
        scalablePressPage.clickBtnFileClaim();
    }

    public boolean isNotClaimed() {
        return scalablePressPage.isElementExist("(//div[@class='ui blue button'][text()='Next step'])[1]");
    }

    public void getLinkClaim(String orderId) {
        scalablePressPage.getLinkClaim(orderId);
    }
}
