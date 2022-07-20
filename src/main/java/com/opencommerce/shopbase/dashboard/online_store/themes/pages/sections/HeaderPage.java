package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static common.utilities.LoadObject.convertStatus;

public class HeaderPage extends SBasePageObject {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }
    public int MAX_RETRY_TIME = 10;

    public void deleteImageByLabel(String label) {
        String xpath = "//div[@class='card__section' or @class='s-form-item']//div[child::div[@class='help-label'] and descendant::*[normalize-space()='" + label + "']]//img[@class='iZocYm']";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }

    public void uploadImageByLabel(String xpathparent, String label, String filename) {
        String xpath = "(" + xpathparent + "//div[@class='card__section' or @class='s-form-item'][descendant::*[normalize-space()='" + label + "']]//input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, filename);
    }

    public void enterAnnouncementMessage(String announcementMessage) {
        waitABit(10000);
        String xpathIframe = "//iframe[contains(@id,'tiny-vue')]";
        waitForElementToPresent(xpathIframe);
        switchToIFrame(xpathIframe);
        $("//body[@id='tinymce']").clear();
        $("//body[@id='tinymce']").sendKeys(announcementMessage);
        switchToIFrameDefault();
    }

    public void chooseLayoutHeaderInside(String layout) {
        clickOnElement("//a[descendant::*[text()='" + layout + "']]");
    }

    public void enableFullWithSection(boolean fullWidthSection) {
        checkCheckboxWithLabel("Full width section", fullWidthSection);
    }

    public void chooseLayoutWithLable(String layout, String type) {
        String xpath = "//label[child::p[text()='" + layout + "']]//parent::div//following-sibling::div//a[descendant::*[text()='" + type + "']]";
        clickOnElement(xpath);
    }

    public void selectCartIconInside(String cartIcon) {
        clickOnElement("//a[descendant::*[text()='" + cartIcon + "']]");
    }

    public void clickOnTextAdvanceSetting() {
        clickOnElement("//h3[normalize-space()='Advance Settings']");
    }

    public void clickBtnSave() {
        clickOnElement("//div[@class='s-modal is-active modal-add-logo']//button[normalize-space()='Save']");
    }

    public String getLogo() {
        return getAttributeValue("", "csr");
    }

    public void verifySaveSuccessfully(int currentRetryTime) {
        try {
            waitForTextToAppear("Saved successfully", 1000);
        } catch (Throwable t) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(3000);
                verifySaveSuccessfully(currentRetryTime + 1);
            }
        }
    }

    public void checkHeaderSettings(String label, boolean isCheck) {
        String xpathStatus ="//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//input";
        String xpathCheck="//div[@class='s-form-item__content' and descendant ::span[normalize-space()='"+label+"']]//label";
        waitElementToBePresent(xpathCheck);
        verifyCheckedThenClick(xpathStatus,xpathCheck,isCheck);
    }

    public void uploadMobileLog(String xpathparent, String label, String filename) {
        String xpath = "(" + xpathparent + "//div[@class='card__section'][descendant::*[normalize-space()='" + label + "']]//input[@type='file'])[3]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, filename);
    }

    public void settingSize(String layout, String size) {
        if(!size.isEmpty()) {
            String xpath = "(//label[child::p[text()='" + layout + "']]//parent::div//following-sibling::div//a[descendant::*[text()='" + size + "']])[1]";
            clickOnElement(xpath);
        }
    }

    public void selectMenu(String label,String menu) {
        selectDdlWithLabel("Menu",menu);
    }
}
