package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UploadImagesProductPages extends SBasePageObject {
    public UploadImagesProductPages(WebDriver driver) {
        super(driver);
    }

    public void clickCheckBoxByGroupName(String groupName) {
        String xpathStatus = "//td[@class='group-title' and child::span[contains(normalize-space(),'" + groupName + "')]]//preceding-sibling::td//input";
        String xpathCheck = "//td[@class='group-title' and child::span[contains(normalize-space(),'" + groupName + "')]]//preceding-sibling::td//span[@class='s-check']";
        verifyCheckedThenClick(xpathStatus, xpathCheck, true);
    }

    public void selectUpdateImages() {
        String xpathAction = "//table[@id='all-variants']//thead//tr//button[@type='button']//span[normalize-space()='Action']";
        String xpathUpdateImages = "//table[@id='all-variants']//thead//div[@class='s-dropdown-content']//span[contains(normalize-space(),'Update images')]";
        clickOnElement(xpathAction);
        clickOnElement(xpathUpdateImages);
    }

//    public void clickButtonUpdateImages() {
//        String xpath = "//label[contains(@class,'s-upload')]//div//following-sibling::input";
//        changeStyle(xpath);
//        chooseAttachmentFile(xpath, );
//    }

    public void verifyMockupAssignForBaseProduct(String sTotalMockup) {
        String xpath = "//div[@class='img m-sm normalize selected']//i[contains(@class,'mdi-check-circle')]";
        waitUntilElementVisible(xpath, 10);
        assertThat(countElementByXpath(xpath)).isEqualTo(Integer.parseInt(sTotalMockup));
    }

    public void viewVariant() {
        String xpath = "//table[@id='all-variants']//tbody//tr//td//span[@class='image__wrapper']";
        clickOnElement(xpath);
    }
}
