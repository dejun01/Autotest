package opencommerce.notification.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

public class NotificationSettingPage extends SBasePageObject {
    public NotificationSettingPage(WebDriver driver) {
        super(driver);
    }
    String shopname = LoadObject.getProperty("shopname");


    public void chooseLink(String _linkName) {
        String xPath = "(//*[ text()[normalize-space()='" + _linkName + "']])[1]";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void chooseButton(String buttonName) {
        String xPath = "(//*[ text()[normalize-space()='" + buttonName + "']])[1]";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
        waitElementToBeVisible("//h4[contains(text(),'Add an order notification')]");
    }

    public void verifyLinkButton(String expected) {
        String xpath = "(//*[ text()[normalize-space()='" + expected + "']])[1]";
        waitABit(1000);
        waitElementToBeVisible(xpath);
        verifyElementText(xpath, expected);
    }

    public void backNotifications() {
        clickLinkTextWithLabel("Notifications");
    }

    public void backSettings() {
        clickLinkTextWithLabel("Settings");
    }

    public void clickCancel() {
        String xPath = "(//*[ text()[normalize-space()='Cancel']])[1]";
        clickOnElement(xPath);
    }

    public void clickOnAddAnOrderNotification() {
        clickOnElement("//span[contains(text(),'Add an order notification')]");
    }

    public void verifyEmailAddressInListRecipients(String emailAddress) {
        verifyElementPresent("(//*[ text()[normalize-space()='" + emailAddress + "']])", true);
    }

    public void clickOnSendTestNotification(String emailAddress) {
        clickOnElement("((//*[ text()[normalize-space()='" + emailAddress + "']])//followingsibling::div)//a");
        waitForElementNotVisible("//div[@class='stoast issuccess isbottom']//div[normalize-space()='The test notification was successfully sent']");
    }

    public void chooseStaffAccount(String staffAccount) {
        clickOnElement("//div[@class='sselect']//select");
        clickOnElement("//div[@class='sselect']//select//option[contains(text(),'" + staffAccount + "')]");
    }

    public void chooseLogo() {
        String xPath = "//input[@type='file']";
        chooseAttachmentFile(xPath, "Logo1.jpg");
    }

    public void enterLogoWidth(String logoWidth) {
        enterInputFieldWithLabel("Logo width (pixels)", logoWidth);
    }

    public void enterColor(String color) {
        String xpath = "//label[text()='Color']//followingsibling::div//input";
        inputSlowly(xpath, color);
        //        enterInputFieldWithLabel("Color", color);
    }

    public void verifyLogo() {
        waitABit(10000);
        switchToIFrame("//div[@id='iframewrapper']//iframe");
        waitElementToBeVisible("//th[@class='column mobile12']//img[contains(@src,'logo')]");
        verifyElementPresent("//th[@class='column mobile12']//img[contains(@src,'logo')]", true);
    }

    public void verifyLogoWidth(String logoWidth) {
        String xPath = "//th[@class='column mobile12']//img[contains(@width,\"" + logoWidth + "\")]";
        verifyElementPresent(xPath, true);
    }

    public void verifyColor(String sColor) {
        verifyColor("//table[@class='mobilefullwidth']", "backgroundcolor", sColor);
        verifyColor("//th[@class='column']//a[ text()[normalize-space()='Visit our store']]", "color", sColor);
        verifyColor("//div[@class='sansserif']//a", "color", sColor);
        switchToIFrameDefault();
    }

    public void removeLogo() {
        clickOnElement("//a[ text()[normalize-space()='Remove']]");
    }

    public void verifyShopName(String shopname) {
        waitABit(2000);
        switchToIFrame("//div[@id='iframewrapper']//iframe");
        waitElementToBeVisible("//th[@class='column mobile12']//a");
        String xPath = "//th[@class='column mobile12']//a[ text()[normalize-space()='" + shopname + "']]";
        verifyElementPresent(xPath, true);
    }

    public void clickToRevertButton() {
        clickOnElement("//span[@class='sflex sflexaligncenter']//span[contains(text(),'Revert to default')]");
    }

    public void closePreview() {
        waitElementToBeVisible("//button[contains(@class,'close')]");
        clickOnElement("//button[contains(@class,'close')]");
        waitForEverythingComplete(100);
    }

    public void verifySubject(String subject) {
        switchToIFrame("//div[@id='iframe-wrapper']//iframe");
        waitElementToBeVisible("//div[contains(text(),'" + subject + "')]");
        verifyElementPresent("//div[contains(text(),'" + subject + "')]", true);
        switchToIFrameDefault();
    }


    public void openEmailAndVerify(String email, String subject) {
        openUrl("https://www.mailinator.com/");
        clickOnElement("//a[contains(text(),'EMAIL')]");
        waitTypeAndEnter("//input[@id='inbox_field']", email);
        clickOnElement("//button[contains(text(),'GO!')]");
        verifyElementPresent("//td[contains(text(),'moments ago') or contains(text(),'minute ago')]", true);
        clickOnElement("(//td[normalize-space()='" + subject + "'])[1]");
        verifyElementPresent("//b[contains(text(),'" + subject + "')]", true);
    }

    public void deleteRecipients(String email) {
        clickOnElement("(//*[ text()[normalize-space()='" + email + "']])//followingsibling::div//span[2]//span");
        waitElementToBeVisible("//h4[contains(text(),'Are you sure you want to delete this order notification?')]");
        clickOnElement("//span[contains(text(),'Delete')]");
    }

    public void verifyPreviewPopupDisplay() {
        verifyElementText("//div[@class='s-modal is-active setting-page__notification--preview']//h4", "Preview");
    }

    public String getEmailLogo() {
        switchToIFrame("//div[@class='s-modal is-active setting-page__notification--preview']//iframe");
        String logo = getAttributeValue("//table//img[@alt='" + shopname + "']", "src");
        switchToIFrameDefault();
        clickOnElement("//button[@class='s-modal-close is-large']");
        return logo;
    }
}
