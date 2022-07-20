package opencommerce.notification.steps;

import com.opencommerce.shopbase.dashboard.settings.pages.SettingPages;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import opencommerce.notification.pages.NotificationSettingPage;

public class NotificationSettingSteps extends ScenarioSteps {
    NotificationSettingPage notiPage;
    SettingPages settingPages;

    @Step
    public void chooseLinkButton(String link) {
        notiPage.chooseLink(link);
    }

    @Step
    public void chooseButton(String button) {
        notiPage.chooseButton(button);
    }


    @Step
    public void verifyLinkButton(String expected) {
        notiPage.verifyLinkButton(expected);
    }

    @Step
    public void backNotifications() {
        notiPage.backNotifications();
    }


    @Step
    public void backSettings() {
        notiPage.backSettings();
    }


    @Step
    public void clickCancel() {
        notiPage.clickCancel();
    }


    @Step
    public void inputEmailAddress(String emailAddress) {
        notiPage.enterInputFieldWithLabel("Email address", emailAddress);
    }


    @Step
    public void clickOnAddAnOrderNotification() {
        notiPage.clickOnAddAnOrderNotification();
        notiPage.waitForPageLoad();
    }


    @Step
    public void clickSendTestNotification(String emailAddress) {
        notiPage.clickOnSendTestNotification(emailAddress);
    }


    @Step
    public void verifyEmailAddressInListRecipients(String emailAddress) {
        notiPage.verifyEmailAddressInListRecipients(emailAddress);
    }

    @Step
    public void clickOnSendTestNotification(String emailAddress) {
        notiPage.clickOnSendTestNotification(emailAddress);
    }

    @Step
    public void chooseStaffAccount(String staffAccount) {
        notiPage.chooseStaffAccount(staffAccount);
    }

    @Step
    public void deleteRecipients(String email) {
        notiPage.deleteRecipients(email);
    }

    @Step
    public void chooseLogo() {
        notiPage.chooseLogo();
    }

    @Step
    public void enterLogoWidth(String logoWidth) {
        notiPage.enterLogoWidth(logoWidth);

    }

    @Step
    public void enterColor(String color) {
        notiPage.enterColor(color);

    }

    @Step
    public void clickSaveButton() {
        notiPage.clickBtn("Save");

    }


    @Step
    public void verifyLogo() {
        notiPage.verifyLogo();

    }


    @Step
    public void verifyLogoWidth(String sLogoWidth) {
        notiPage.verifyLogoWidth(sLogoWidth);

    }


    @Step
    public void removeLogo() {
        notiPage.removeLogo();

    }


    @Step
    public void verifyShopName() {
        String shopname = LoadObject.getProperty("shopname");
        notiPage.verifyShopName(shopname);
    }


    @Step
    public void verifyColor(String sColor) {
        notiPage.verifyColor(sColor);
    }

    @Step
    public void clickToRevertToDefaultButton() {
        notiPage.clickBtn("Revert to default");
    }


    @Step
    public void clickToRevertButton() {
        notiPage.clickToRevertButton();
    }

    @Step
    public void enterSubject(String subject) {
        notiPage.enterInputFieldWithLabel("Email subject", subject);

    }


    @Step
    public void verifySubject(String subject) {
        notiPage.clickLinkTextWithLabel("Preview");
        notiPage.waitForEverythingComplete();
        notiPage.verifySubject(subject);
        notiPage.closePreview();
    }


    @Step
    public void clickSendTestEmail() {
        notiPage.clickLinkTextWithLabel("Send test email");
        notiPage.waitForTextToAppear("The test notification was successfully sent");

    }

    @Step
    public void openEmailAndVerify(String subject) {
        String email = LoadObject.getProperty("user.name");
        notiPage.openEmailAndVerify(email, subject);

    }


    @Step
    public void backToNotificationPage() {
        notiPage.switchToTab("Settings");
        notiPage.waitForTextToAppear("Settings");
        settingPages.clickToSection("Notifications");
        notiPage.waitForEverythingComplete();


    }

    public void clickPreview() {
        notiPage.clickLinkTextWithLabel("Preview");
        notiPage.verifyPreviewPopupDisplay();
    }

    public void verifyLogoEmail(boolean isCheck) {
    }

    public String getEmailLogo() {
        return notiPage.getEmailLogo();
    }
}

