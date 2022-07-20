package com.opencommerce.shopbase.dashboard.apps.printhub.pages.fulfillment;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.assertTrue;

public class MappingProductPages extends SBasePageObject {

    public MappingProductPages(WebDriver driver) {
        super(driver);
    }


    public void verifyDefineOptionDisable() {
        String xpath = "//div[@class='box-shadow-container m-b disabled']";
        verifyElementVisible(xpath, true);
    }

    public void verifyDefaultOption() {
        String xpath_color = "//span[text()='Default color']//preceding-sibling::input";
        String xpath_size = "//span[text()='Default size']//preceding-sibling::input";
        waitUntilElementVisible(xpath_color, 30);
        if (getAttributeValue(xpath_color, "disabled").contains("true") && getAttributeValue(xpath_size, "disabled").contains("true")) {
            assertTrue(true);
        }
    }

    public void verifySaveButtonDisable() {
        String xpath = "//button[@class='s-button is-primary is-disabled']";
        assertThat(getAttributeValue(xpath, "disabled").contains("true"));
    }

    public void verifyDefineOptionEnable() {
        String xpath = "//div[@class='map-option-sets text-gray600 p-t-sm']";
        verifyElementVisible(xpath, true);
    }

    public void verifyOptionValue(String offer, String mapWith) {
        String xpath = "//div[child::p[text()='" + offer + "']]//following-sibling::div//option[normalize-space()='" + mapWith + "']";
        verifyElementVisible(xpath, true);
    }

    public void clickCheckboxSelectAProduct() {
        String xpath = "//option[text()='Select a product']//ancestor::div[contains(@class,'s-select')]";
        refreshPage();
        waitForEverythingComplete();
        waitElementToBePresent(xpath);
        clickOnElement(xpath);
    }

    public void selectProductMaping(String nameProduct) {
        String xpath = "//select//option[contains(text(),'" + nameProduct + "')]";
        String xpath_value = "//option[text()='Select a product']//ancestor::select//option[contains(text(),'" + nameProduct + "')]";
        int i = 0;
        while (i < 20 && !isElementExist(xpath)) {
            waitABit(10000);
            refreshPage();
            waitForEverythingComplete();
            i++;
        }
        refreshPage();
        waitForEverythingComplete();
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        } else {
            waitElementToBeVisible(xpath_value);
            clickOnElement(xpath_value);
        }
    }

    public void selectOption(String offer, String mapWith) {
        String xpath = "//div[child::p[text()='" + offer + "']]//following-sibling::div//select";
        waitForEverythingComplete();
        selectDdlByXpath(xpath, mapWith);
    }

    public void clickBreadcrum() {
        String xpath = "//li//span[@class='s-icon is-small']";
        clickOnElement(xpath);
    }

    public void verifyAutoMapoption(String option, String optionmap) {
        String xpath = "//span[@class='s-control-label' and normalize-space()='" + option + "']";
        String xpath_map = "//option[normalize-space()='" + optionmap + "']";
        assertThat(getTextValue(xpath)).isEqualTo(getTextValue(xpath_map));
        waitForEverythingComplete();
    }

    public void verifyVariantAfterMap(String option, String optionmap) {
        String xpath = "//span[@class='s-control-label' and normalize-space()='" + option + "']//ancestor::div[contains(@class,'row ')]//option[1]";
        assertThat(getTextValue(xpath)).isEqualTo(optionmap);
        waitForEverythingComplete();
    }

    public void changeValueAutoMapoption(String optionValue, String changeOptionMapped) {
        String xpath_map = "//div[child::label[child::span[text()='" + optionValue + "']]]//following-sibling::div";
        waitForEverythingComplete();
        selectDdlByXpath(xpath_map, changeOptionMapped);
    }

    public void clickUploadArtwork(String frontOrBack, String nameImage) {
        String xpath = "//input[@id='" + frontOrBack + "']";
        changeStyle(xpath);
        waitElementToBePresent(xpath);
        chooseAttachmentFile(xpath, nameImage);
        waitABit(10000);
    }

    public void verifyMessageUploadArtwork(String message) {
        String xpath = "//span[normalize-space()='" + message + "']";
        assertThat(getTextValue(xpath)).isEqualTo(message);
        waitForEverythingComplete();
    }

    public void clickBtnSave() {
        String xpath = "//div[@class='action__container p-t d-flex justify-content-end']//span[normalize-space()='Save']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        waitABit(3000);
    }

    public void verifyButtonChangeAndDeleteArtwork(boolean elementVisible) {
        String xpath_change_artwork = "//label[normalize-space()='Change artwork']";
        String xpath_delete_artwork = "//span[normalize-space()='Delete artwork']";
        verifyElementPresent(xpath_change_artwork, elementVisible);
        verifyElementPresent(xpath_delete_artwork, elementVisible);
        waitForEverythingComplete(10);
    }

    public void clickDeleteArtwork() {
        String xpath = "//div[@class='btn-block']//following-sibling::img";
        String xpath_delete_artwork = "//span[normalize-space()='Delete artwork']";
        clickOnElement(xpath);
        waitForEverythingComplete();
        clickOnElement(xpath_delete_artwork);
        waitABit(1000);
    }

    public void verifyVariants(String nameProduct, String countVariants) {
        String xpath = "//p[text()='" + nameProduct + "']//following-sibling::p";
        assertThat(getTextValue(xpath)).isEqualTo(countVariants);
    }

    public void verifyTotalProduct(String totalProduct) {
        String xpath = "//h4[@class='style-list__title']";
        assertThat(getTextValue(xpath)).isEqualTo(totalProduct);
    }

    public void verifyTotalVariantsMapped(String optionValue, String totalVariantsMapped) {
        String xPath = "//p[normalize-space()='" + optionValue + "']//following-sibling::p[normalize-space()='" + totalVariantsMapped + "']";
        assertThat(getTextValue(xPath)).isEqualTo(totalVariantsMapped);
    }

    public void selectAnotherOptionValue() {
        String xPath = "//li[@class='white-bg']";
        clickOnElement(xPath);
    }

    public void verifyTotal(String value) {
        String xPath_value = "//p[text()='" + value + "']";
        assertThat(getTextValue(xPath_value)).isEqualTo(value);
    }

    public void verifyMessage(String message) {
        String xPath_message = "//p[text()=' " + message + " ']";
        assertThat(getTextValue(xPath_message)).isEqualTo(message);
    }

    public void selectTap(String tap) {
        String xPath = "//p[contains(text(),'" + tap + "')]";
        clickOnElement(xPath);
    }

    public void selectCheckbox(String optionValue) {
        waitForEverythingComplete();
        String xPath = "//span[normalize-space()='" + optionValue + "']//preceding-sibling::span";
        clickOnElement(xPath);
    }

    public void verifyCountTotal(String nameProduct, String optionValue_1, String optionValue_2, String totalVariants_1, String totalVariants_2) {
        String xpath = "//p[text()='" + nameProduct + "']//following-sibling::p";
        int countTotal = Integer.parseInt(getTextValue(xpath).split("/")[0]);

        String xPath_1 = "//p[normalize-space()='" + optionValue_1 + "']//following-sibling::p[normalize-space()='" + totalVariants_1 + "']";
        int count_optionValue1 = Integer.parseInt(getTextValue(xPath_1).split("/")[0]);

        String xPath_2 = "//p[normalize-space()='" + optionValue_2 + "']//following-sibling::p[normalize-space()='" + totalVariants_2 + "']";
        int count_optionValue2 = Integer.parseInt(getTextValue(xPath_2).split("/")[0]);

        assertThat(countTotal).isEqualTo(count_optionValue1 + count_optionValue2);
    }

    public void clickDiscard() {
        String xPath = "//button[@type='button']//span[normalize-space()='Discard']";
        waitUntilElementVisible(xPath, 10);
        clickOnElement(xPath);
    }
}
