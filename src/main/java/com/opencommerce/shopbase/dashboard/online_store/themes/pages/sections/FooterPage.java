package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FooterPage extends ThemeEditorPage {


    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public void isCheckedShowPaymentMethod(boolean showPaymentMethodIcons) {
        checkCheckboxWithLabel("Show payment method icons", 1, showPaymentMethodIcons);
    }

    public void isCheckedShowSocialIcon(boolean showSocialMediaFooterIcons) {
        checkCheckboxWithLabel("Show social media footer icons", 1, showSocialMediaFooterIcons);
    }

    public void chooseTypeContent(String type) {
        String xpathDropItem = "//div[@class='s-dropdown-content']/span[contains(normalize-space(.),'" + type + "')]";
        waitElementToBePresent(xpathDropItem);
        clickOnElement(xpathDropItem);

    }

    String BlockOpened = "//div[@class='card__section']//div[@class='s-collapse-item draggable-item is-active']";

    public void enterHeadingTitleFooter(String heading) {
        enterInputFieldWithLabel(BlockOpened, "Heading", heading, 1);
    }

    public void enterTextTitleFooter(String text) {
        waitElementToBeVisible(BlockOpened + "//div[@class='tox-edit-area']//iframe");
        switchToIFrame(BlockOpened + "//div[@class='tox-edit-area']//iframe");
        waitClearAndType("//body[@class='mce-content-body ']", text);
        switchToIFrameDefault();
    }

    public void chooseMenuFooter(String menu) {
        selectDdlWithLabel(BlockOpened, "Main menu", menu, 1);
    }

    public void choosePage(String content) {
        selectDdlWithLabel(BlockOpened, "Content page", content, 1);
    }

    public void closeBlockContent() {
        clickOnElement("(//div[contains(@class,'s-collapse-item draggable-item')])//div[@role='button']//i[contains(@class,'mdi mdi-menu-down mdi')]");
    }

    public void uploadLogo(String logo) {
        String xpath = "(//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='Logo']]//input[@type='file'])[1]";
    //    changeStyle(xpath);
        chooseAttachmentFile(xpath, logo);
        waitElementToBePresent("//div[@class='s-form-item image_picker']//img[@class='ilETcc']");
    }

    public void removeLogo() {
        String xpath = "(//div[@currentlayout and descendant::p[normalize-space()='Logo']])//img[@class='iZocYm']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
            verifyElementPresent("(//div[@currentlayout and descendant::p[normalize-space()='Logo']])//button[normalize-space()='Upload image']", true);
        }
    }

    public void enterContentOfStoreInformation(String _labelName, String value) {
        waitClearAndType("//div[@currentlayout and descendant::p[contains(text(),'" + _labelName + "')]]//input", value);
    }

    public void openBlockWithLabel(String label) {
        clickOnElement("//div[@class='s-collapse-item draggable-item' and descendant::div[normalize-space()='" + label + "']]");
    }

    public void opentBlockContent(String type) {
        String xpath = "//div[@role='button']//span[contains(text(),'" + type + "')]";
        clickOnElement(xpath);
    }

    public void openThemeSettings() {
        String xpath = "//div[@role ='button' and descendant::h3[@class='s-subheading']]";
        clickOnElement(xpath);
    }

    public void enterLink(String link) {
        String xpath = "//input[@type ='text' and contains(@placeholder,'/Shopbase')]";
        for (int i = 1; i <= countElementByXpath(xpath); i++) {
            String xpathLink = "(//input[@type ='text' and contains(@placeholder,'/Shopbase')])[" + i + "]";
            waitClearAndType(xpathLink, link);
        }
    }

    public void enterStoreInforInside(String label, String value) {
        String xpath = "//div[@class='s-collapse-item draggable-item is-active' and descendant::span[contains(text(),'Store information')]]//div[@currentlayout and descendant::p[contains(text(),'" + label + "')]]//input";
        waitElementToBePresent(xpath);
        waitClearAndType(xpath, value);
    }

    public void addHeading(String _labelName, String value) {
        waitClearAndType("//div[@currentlayout and descendant::p[contains(text(),'" + _labelName + "')]]//input", value);
    }

    public void isCheckHideContentHeading(boolean hideContentHeading) {
        checkCheckboxWithLabel("Hide content heading",1,hideContentHeading);
    }
}
