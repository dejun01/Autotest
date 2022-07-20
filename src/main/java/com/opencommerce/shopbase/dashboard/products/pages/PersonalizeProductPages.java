package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PersonalizeProductPages extends SBasePageObject {
    public PersonalizeProductPages(WebDriver driver) {
        super(driver);
    }

    public void verifyScreenPersonalize() {
        String xpath = "//div[@id='editor-content']";
        assertThat(isElementExist(xpath, 50)).isEqualTo(true);
    }

    public void uploadImagePersonalize(String sImage, String message) {
        String xpath = "//input[@id='upload-image']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + sImage);
        if (!"".equals(message)) {
            String xpathMsg = "//div[@class='s-toast is-danger is-bottom']//div";
            assertThat(getText(xpathMsg).equals(message));
        }
        clearTextByJS(xpath);
    }

    public void clickTemplateActionBtn() {
        String xpath = "//div[@class='template-actions']";
        waitUntilElementVisible(xpath, 50);
        waitElementToBeClickable(xpath).click();
    }

    public void replaceImagePersonalize(String sImage) {
        String xpath = "//span[contains(@data-label,'Replace image')]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + sImage);
        clearTextByJS(xpath);
    }

    public void clickAddLayerText() {
        String xpath = "//button[contains(.,'Add text')]";
        waitUntilElementVisible(xpath, 50);
        waitElementToBeClickable(xpath);
        clickOnElement(xpath);
    }

    public void addLayerImage(String sImage) {
        String xpath = "//input[@id='add-image-layer']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + sImage);
        clearTextByJS(xpath);
    }

    public void inputValueForText(String sLayerValue) {

    }

    public void inputFont(String s) {
    }

    public void inputSizeOfFont(String size) {
    }

    public void clickOpenLayerDetail(String sLayerName) {
        String xpathlayerFirst = "//div[contains(@class,'s-tooltip-fixed layer-name')]";
        String xpathLayerName = "//div[contains(@class,'s-tooltip-fixed layer-name')]//span[contains(text(),'" + sLayerName + "')]";
        try {
            waitElementToBeClickable(xpathLayerName);
            clickOnElement(xpathLayerName);
        } catch (Exception e) {
            waitElementToBeClickable(xpathlayerFirst);
            clickOnElement(xpathlayerFirst);
        }
    }

    String xpathBtnToolbarFromOrder = "//div[contains(@class,'s-modal-body')]//button[@type='button' and child::span[contains(.,'%s')]]";
    String xpathBtnToolbarFromProduct = "//button[@type='button' and child::span[contains(.,'%s')]]";

    public void verifyBtnEnable(String btnName, boolean b) {
        String xpath = String.format(xpathBtnToolbarFromProduct, btnName);
        assertThat(XH(xpath).isEnabled()).isEqualTo(b);
    }

    public void clickActionToolBar(String btnName) {
        String xpath = String.format(xpathBtnToolbarFromProduct, btnName);
        clickOnElement(xpath);
    }

    public void verifyBtnEnableFromOrder(String btnName, boolean b) {
        String xpath = String.format(xpathBtnToolbarFromOrder, btnName);
        assertThat(XH(xpath).isEnabled()).isEqualTo(b);

    }

    public void clickActionToolBarFromOrder(String btnName) {
        String xpath = String.format(xpathBtnToolbarFromOrder, btnName);
        verifyBtnEnableFromOrder(btnName, true);
        clickOnElement(xpath);
    }

    public void clickButtonConfirmInPopup(String btnName) {
        String xpathPopup = "//div[@class='s-animation-content s-modal-content']";
        if (isElementExist(xpathPopup)) {
            clickBtn(btnName);
        }
    }

    public void waitForUntilInVisibleLoading(int timeoutInSeconds) {
        String xpath = "//button[@class='s-button is-outline-blue is-small is-loading']|//img[@class='sbase-spinner']|//*[@class='button-dual-ring']|//div[@class='s-progress-bar']";
        int i = 0;
        while (isElementExist(xpath, 3) && i < 6) {

            System.out.println("doi loading");
            waitUntilElementInvisible(xpath, timeoutInSeconds);
            i++;
        }
        waitForElementNotAppear(xpath);
    }

    public int countListLayer() {
        String xpath = "//div[@class='layer__list']//div[@class='single-layer__container']";
        if (isElementExist(xpath))
            return countElementByXpath(xpath);
        else return 0;
    }


    public String getTextOfHelpLinkPersonlize() {
        String xpath = "//a[@class='s-center-absolute help_link']";
        return getText(xpath).trim();
    }

    public void verifyTagBetaPackage() {
        String xpath = "//*[contains(@class,'tag-beta') and contains(.,'Beta')]";
        assertThat(isElementExist(xpath)).isTrue();
    }

    public void clickAndVerifyToggleOnPopupCreatePrintFile(boolean statusToggle) {
        String xpathStatus = "//label[@class='s-switch']//span[1]";
        String xpathOnToggle = "//label[@class='s-switch']//input";
        waitUntilElementVisible(xpathOnToggle, 10);
        if (statusToggle) {
            verifyCheckedThenClickOnPopUpPF(xpathStatus, xpathOnToggle, statusToggle);
        } else {
            assertThat(getAttributeValue(xpathStatus, "value")).isEqualTo(statusToggle);
        }
    }

    public void verifyPopUpPrintFileNoGenerate() {
        String xpath = "//div[contains(@class,'s-animation-content')]//section//p";
        verifyElementText(xpath, "If not, the Print file will be created but we won't generate it when the product is placed.");
    }

    public void selectRadioThenVerifyPopUpPrintfileGenerate(int number, String status) {
        String xpath = "//div[contains(@class,'s-animation-content')]//div[contains(@class,'font-size-popup-save_print_file')][" + number + "]//input";
        if (status.equals("No"))
            clickOnElement(xpath);
        verifyElementSelected(xpath, true);
    }

    public void verifyProductAfterGenerated() {
        String xpath = "//div[@class='m-t-md preview-image' and descendant::h3[text()='Print Files']]";
        waitUntilElementVisible(xpath, 10);
        verifyElementVisible(xpath, true);
    }

    public void verifyPopUpPrintFileGenerateInOrder() {
        String xpath = "//label[input[@value='only_order_items']]//span";
        verifyElementText(xpath, "No, only generate for this ordered");
    }

    public void verifyPopUpPrintFileGenerateWhenSettingOffGenerate() {
        String xpath = "//label[input[@value='only_order_items']]//span";
        verifyElementText(xpath, "No, only generate for the ordered items from now");

    }

    public void verifyPopUpPrintFileNoGenerateInOrder() {
        String xpath = "//div[contains(@class,'s-animation-content')]//section//p[@class='text-normal m-t-md font-size-popup-save_print_file']";
        verifyElementText(xpath, "If not, the Print file will be created but we won't generate it when the product is placed.");
    }

    public void verifyCheckedThenClickOnPopUpPF(String _xPathStatus, String _xPathCheckbox, boolean _isCheck) {
        boolean isStatus = XH(_xPathStatus).isSelected();
        if (_isCheck != isStatus) {
            clickOnElementByJs(_xPathCheckbox);
        }
    }

    public void verifyOrderAfterGenerated() {
        String xpath = "//div[@class='title-bar__orders-show-badge']//span[contains(@class,'s-tag hide-when-printing is')]";
        waitUntilElementVisible(xpath, 10);
        verifyElementVisible(xpath, true);
    }

    public void verifyNotiPopupCreatePrintfileAfterConfigGenerate(String noti) {
        String xpath = "//div[@class='s-modal is-active confirm-print-file-order-popup']//section/p[contains(@class,'text')]";
        assertThat(getText(xpath)).isEqualTo(noti);
    }

    public void verifyBtnAddPrintFile() {
        String xpath = "//div[contains(@class,'d-flex self-flex-end text-right text-gray400')]//div[contains(.,'Add print file')]";
        assertThat(isElementExist(xpath)).isFalse();
    }

    public void editCutomOption(String label){
        String xpath = "//div[contains(@class,'custom-option__list')]//div[contains(@class, 'children-expand content__center')]//a[contains(.,'" +label+"')]";
        clickOnElementByJs(xpath);
    }
}
