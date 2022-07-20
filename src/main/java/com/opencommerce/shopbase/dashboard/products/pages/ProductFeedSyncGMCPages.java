package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductFeedSyncGMCPages extends SBasePageObject {
    public ProductFeedSyncGMCPages(WebDriver driver) {
        super(driver);
    }

    public void clickCheckBoxExcludeVariationsOfAProduct() {
        String xPathStatus = "//label[normalize-space()='Exclude the variations matching']//input[@type='checkbox']";
        String xPathCheckBox = "//label[normalize-space()='Exclude the variations matching']//span[@class='s-check']";
        verifyCheckedThenClick(xPathStatus, xPathCheckBox, true);
    }

    public void downloadProductFeedUrl(int rowIndex, int colIndex) {
        clickOnElement("(//div[contains(@class,'product-page')]//table//tbody//tr[" + rowIndex + "]//td[" + colIndex + "])//span//a");
        waitABit(3000);
    }

    public int getColumnOfFeedListTableByTable(String colName) {
        return countElementByXpath("//div[contains(@class,'product-page')]//table//thead//th[normalize-space()='" + colName + "']//preceding-sibling::th") + 1;

    }

    public int getRowFeedUrl(String feedName) {
        int rowIndex = 0;
        String xpath = "//div[contains(@class,'product-page')]//table//tbody//tr[child::td[descendant::*[normalize-space()='" + feedName + "']]]";
        if (isElementExist(xpath)) {
            rowIndex = countElementByXpath(xpath + "/preceding-sibling::tr") + 1;
        }
        return rowIndex;
    }

    public void enterKeyWordMatching(String keyWord) {
        String xpathKeyWordMatching = "((//span[normalize-space()='Export only variations of a product matching'])/parent::label)//following-sibling::div";
        enterInputFieldWithLabel(xpathKeyWordMatching, "Enter keyword", keyWord, 1);
    }


    public void deleteAllProductFeed() {
        String xPathDeleteFeed = "(//td[normalize-space()='Ready']//following-sibling::td//i)[1]";
        clickOnElement(xPathDeleteFeed);
    }

    public void confirmDeleteFeed() {
        String xPathConfirmDeleteFeed = "//div[normalize-space()='Delete this product feed']//following-sibling::div//span[text()='Delete']";
        clickOnElement(xPathConfirmDeleteFeed);
    }

    public void checkGMCMethod(boolean isCheckGMC) {
        String xPathStatus = "//label[normalize-space()='Automatically upload product listings to Google Merchant Center']//input[@type='checkbox']";
        String xPathCheckbox = "//label[normalize-space()='Automatically upload product listings to Google Merchant Center']//span[@class='s-check']";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, isCheckGMC);
    }

    public void goToProductFeednameIs(String feedName) {
        String xpathFeedName = "//div[@class='product-page']//tbody//tr//td//span[normalize-space()='" + feedName + "']";
        clickOnElement(xpathFeedName);
    }

    public void verifyGMCInAllProductsFeedIsDisabled() {
        String xPathStatus = "//label[normalize-space()='Automatically upload product listings to Google Merchant Center']//input[@type='checkbox']";
        String xPathCheckbox = "//label[normalize-space()='Automatically upload product listings to Google Merchant Center']//span[@class='s-check']";
        verifyCheckedThenClick(xPathStatus, xPathCheckbox, false);
    }

    public boolean existFeedCanBeDelete() {
        String xpathDeleteFeed = "(//td[normalize-space()='Ready']//following-sibling::td//i)[1]";
        return isElementVisible(xpathDeleteFeed, 5);
    }

    public void verifyFeedStatus(boolean isEnable) {
        String xpath = "//label[normalize-space()='Automatically upload product listings to Google Merchant Center']//input[@type='checkbox']";
        assertThat(XH(xpath).isEnabled()).isEqualTo(isEnable);
    }
}
