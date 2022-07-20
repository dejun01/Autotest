package com.opencommerce.shopbase.dashboard.apps.printhub.pages.campaigns;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class CampaignPages extends SBasePageObject {
    public CampaignPages(WebDriver driver) {
        super(driver);
    }

    public void switchToTabOnCatalog(String sCategory) {
        String xPath = "//div[@class='product-catagory']";
        if (!getAttributeValue(xPath, "class").contains("is-active")) {
            clickOnElement(xPath + "//span[text()='" + sCategory + "']");
        }
    }

    public void clickBtnAddProductToCampaign(String product) {
        String xpathProduct = "//div[@class='prod-wrap' and child::div[@class='product']//span[@data-label='" + product + "']]";
        clickOnElement(xpathProduct + "//span[contains(text(), 'Add product')]");
        verifyElementPresent(xPathBtn(xpathProduct, "Remove product", 1), true);
    }

    public void clickBtnNewCampaign() {
        String xpath = "//button[@type='button']//span[text()[normalize-space()='New campaign']]";
        if (isElementExist(xpath) == true) {
            clickBtn("New campaign");
        } else {
            clickBtn("New campaign");
        }
    }

    public void verifyShowMessage(String message) {
        String xpath = "//div[@class='s-notices is-bottom']//div[text()[normalize-space()='" + message + "']]";
        waitUntilElementVisible(xpath, 90);
    }

    public void chooseProductToAddDesign(String baseProduct) {
        String xPath = "//div[contains(@class, 'phub-design') and child::p//span[text()='" + baseProduct + "']]";
        if (!getAttributeValue(xPath, "class").contains("is-active")) {
            clickOnElement("//span[text()='" + baseProduct + "']");
        }
    }

    public void clickBtnAddArtwork(String frontOrback) {
        String xPath = "//button[child::span[text()[normalize-space()='" + frontOrback + "']]]";
        if (!getAttributeValue(xPath, "class").contains("is-active")) {
            clickOnElement(xPath + "//span[text()[normalize-space()='" + frontOrback + "']]");
        }
        clickOnElement("//div[@class='artwork-btn']");
    }

    public void addArtworkToCampaign(String artwork) {
        String xpath = "(//input[@type='file'])[1]";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, "phub" + File.separator + artwork);
        clearTextByJS(xpath);
        waitForEverythingComplete();
        if (isElementExist("//div[contains(@class,'artwork-progress-bar')]")) {
            waitUntilElementInvisible("//div[contains(@class,'artwork-progress-bar')]", 90);
            waitABit(2000);
        }

        String artworkname = artwork.replace(".png", "");
        String imageArtwork = "(//*[contains(text(),'" + artwork + "')]//ancestor::div[contains(@class,'artwork ')]//div[@class='img-container'])[1]";
        if (isElementExist(imageArtwork)) {
            clickOnElement(imageArtwork);
        }
        if (isElementExist("//div[contains(@class,'in-process-animation')]", 5)) {
            waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 90);
            waitUntilElementVisible("//div[@class='phub-design']//*[contains(@class,'fix-display')]//*[text()[normalize-space()='Change artwork']]", 90);
        }
    }

    public void selectProductColors(String baseProduct, int number) {
        String xPath = "(//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[@class='color-section']//div[@class='s-dropdown-body']//span)[" + number + "]";
        if (!getStatusProductColor(baseProduct, number).contains("active")) {
            clickOnElement(xPath);
        }
    }

    public void unselectProductColors(String baseProduct, int number) {
        String xPath = "(//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[@class='color-section']//div[@class='s-dropdown-body']//span)[" + number + "]";
        if (getStatusProductColor(baseProduct, number).contains("active")) {
            clickOnElement(xPath);
        }
    }


    public void openAndCloseColorTable(String sProducts) {
        String xpath = "//span[text()='" + sProducts + "']//ancestor::div[contains(@class,'phub-design-container')]//button[@class='color-section__add-color']";
        clickOnElement(xpath);
    }

    public void selectSizeOfProduct(String baseProduct, String size, boolean isCheck) {
        boolean status = false;
        String xpath = "//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[contains(@class,'select-size')]//span[text()[normalize-space()='" + size + "']]//parent::button";
        if (getStatusProductSize(baseProduct, size).contains("active")) {
            status = true;
        }
        if (status != isCheck) {
            clickOnElement(xpath);
        }
    }

    private String getStatusProductSize(String baseProduct, String size) {
        String xpath = "//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[contains(@class,'select-size')]//span[text()[normalize-space()='" + size + "']]//parent::button";
        return getAttributeValue(xpath, "class");
    }

    public String getProductSizeText(String product, int number) {
        String xpath = "(//span[text()='Ladies T-shirt']//ancestor::div[contains(@class,'phub-design')]//div[contains(@class, 'select-size')]//span)[" + number + "]";
        return getTextValue(xpath);
    }

    private String getStatusProductColor(String baseProduct, int number) {
        String xpathColor = "(//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[@class='color-section']//div[@class='s-dropdown-body']//span)[" + number + "]";
        return getAttributeValue(xpathColor, "class");
    }

    public void hoverColorButton(String baseProduct, int number) {
        String xpathColor = "(//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[@class='color-section']//div[@class='s-dropdown-body']//span)[" + number + "]";
        hoverOnElement(xpathColor);
    }

    public String getColorName(String baseProduct) {
        String xPath = "//div[contains(@class, 'phub-design') and child::div[contains(@class, 'phub-design-container')]//span[text()='" + baseProduct + "']]" +
                "//div[@class='color-section']//div[@class='s-dropdown-header']//span";
        return getText(xPath);
    }

    public int countProductSize(String sProduct) {
        String xpathSize = "//span[text()='" + sProduct + "']//ancestor::div[contains(@class,'phub-design')]//div[contains(@class,'select-size')]//button";
        return countElementByXpath(xpathSize);
    }

    public int countNumberOfProductColor(String baseProduct) {
        String xpathSize = "//span[text()='" + baseProduct + "']//ancestor::div[contains(@class,'phub-design')]//div[@class='s-dropdown-body']//span[contains(@class, 's-dropdown-item')]";
        return countElementByXpath(xpathSize);
    }

    public void clickBtnContinue() {
        String xpath = "//button//span[text()[normalize-space()='Continue']]";
        clickOnElement(xpath);
    }

    public void enterDescription(String sDescription) {
        waitClearAndType("//div[@placeholder='Enter description for your campaign']", sDescription);
    }

    public void clickEnableSizeChart() {
        clickOnElement("//button//span[text()[normalize-space()='Enable widget on my store']]");
    }

    public void enterTags(String sTag) {
        String xpath = "//input[@placeholder='Separate your keywords using a comma']";
        waitTypeAndEnter(xpath, sTag);
    }

    public void verifyTabActive(String tabname) {
        String xpath = "//div[@class='step-button active' and child::*[text()='" + tabname + "']]";
        waitUntilElementVisible(xpath, 15);
        verifyElementPresent(xpath, true);
    }

    public void enterSalePrice(String baseProduct, String salePrice) {
        String xpath = "//tr[@class='body-row' and //div[text()='" + baseProduct + "']]//input[@name='sale-parent']";
        waitClearAndType(xpath, salePrice);
    }

    public void enterCompareAtPrice(String baseProduct, String comparePrice) {
        String xpath = "//tr[@class='body-row' and //div[text()='" + baseProduct + "']]//input[@name='compare-parent']";
        waitClearAndType(xpath, comparePrice);
    }

    public void waitUntilImageLoadingSuccess() {
        waitUntilElementInvisible("//div[contains(@class,'in-process-animation')]", 500);
    }
    public void verifyCampaignDisplayed(String campaign) {
        String xpath = "//div[@class='product-name']//span[text()='" + campaign + "']";
        verifyElementPresent(xpath, true);
    }
}
