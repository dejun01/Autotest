package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;

public class SpecificDescriptionPages extends SBasePageObject
{


    public void clickButtonUpdateDescription(String baseName) {
        String xpath = "//div[@class='product' and descendant::span[normalize-space()='"+baseName+"']]//following-sibling::div[contains(@class,'s-dropdown')]//button";
        String xpathUpdateDesc = "//div[@class='product' and descendant::span[normalize-space()='"+baseName+"']]//following-sibling::div[contains(@class,'s-dropdown')]//span[normalize-space()='Update description']";
        clickOnElement(xpath);
        clickOnElement(xpathUpdateDesc);
    }


    public void verifySEODescriptionIsDisplayedExactly() {
        String xpath = "//div[@class='type-container']//div[normalize-space()='General description AOP Beach Shorts update 1 AOP Multi Piece Zip Hoodie Update 1']";
        verifyElementPresent(xpath,true);
    }

    public void updateBaseDescription(String baseDescription) {
        waitForEverythingComplete();
        waitABit(5000);
        String xPathIframe = "//iframe[@class='tox-edit-area__iframe']";

        try {
            waitElementToBeVisible(xPathIframe);
        } catch (Exception e) {
            waitABit(5000);
            waitElementToBeVisible(xPathIframe);
        }

        switchToIFrame(xPathIframe);
        String xPathTinyMCE = "//body[@id='tinymce']";
        waitElementToBeVisible(xPathTinyMCE);
        XH(xPathTinyMCE).clear();
        XH(xPathTinyMCE).sendKeys(baseDescription.trim());
        waitABit(1000);
        switchToIFrameDefault();
        clickOnElement("((//*[child::*[contains(text(),'Title')]]|//*[contains(text(),'Title')])/following-sibling::div//input)[1]");
    }
}
