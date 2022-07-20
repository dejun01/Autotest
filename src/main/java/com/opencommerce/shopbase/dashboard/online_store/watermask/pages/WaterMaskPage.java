package com.opencommerce.shopbase.dashboard.online_store.watermask.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class WaterMaskPage extends SBasePageObject {
    public WaterMaskPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckboxEnableWaterMask(boolean isEnable) {
        String xpathStatus = "//label[contains(.,'Enable Watermark')]/input";
        String xpathSelect = "//label[contains(.,'Enable Watermark')]/span[@class='s-check']";
        verifyCheckedThenClick(xpathStatus, xpathSelect, isEnable);
    }

    public void clickChooseType(String type) {
        String radio = "//input[@value='" + type + "']";
        String select = "//input[@value='" + type + "']//following-sibling::span[@class='s-check']";
        verifyCheckedThenClick(radio, select, true);
        assertThat(XH(radio).isSelected()).isTrue();
    }

    public void inputText(String value) {
        String xpathInput = "//input[@type='text']";
        clickAndClearThenType(xpathInput, value);
        assertThat(getTextValue(xpathInput)).isEqualTo(value);
    }


    public void inputImageLogo(String path) {
        String image = "//input[@type='file']";
        chooseAttachmentFile(image, path);

    }

    public void clickStyle(String sStyle) {
        String xpathStyle = "//div[child::b[contains(.,'" + sStyle + "')]]";
        clickOnElement(xpathStyle);
    }

    public boolean isDialogSaveShow() {
        String xpath = "//div[@class='save-setting-fixed']";
        return isElementVisible(xpath, 50);
    }

    public void verifyMessageSuccess() {
        String xpathNotiSuccess = "//div[contains(.,'Setting was saved successfully')]";
        assertThat(isElementExist(xpathNotiSuccess, 30));
    }
}
