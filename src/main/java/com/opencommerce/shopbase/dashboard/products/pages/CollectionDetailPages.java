package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionDetailPages extends SBasePageObject {
    public CollectionDetailPages(WebDriver driver) {
        super(driver);
    }

    public void enterCollectionDescription(String description) {
        String xpath = "//*[ancestor::*[preceding-sibling::*[text()[normalize-space()='Description (optional)']]]]//div[contains(@class, 'redactor-styles')]";
        waitForEverythingComplete();
        waitABit(5000);
        try {
            waitElementToBeVisible(xpath);
        } catch (Throwable t) {
            refreshPage();
            waitABit(5000);
            waitElementToBeVisible(xpath);
        }
        waitClearAndType(xpath, description);
    }

    public void selectDdProductProperties(String prodProperties) {
        selectDdlWithLabel("Conditions", prodProperties, 1);
    }

    public void selectDdColCondition(String cons) {
        selectDdlWithLabel("Conditions", cons, 2);
    }

    public void inputConditionValue(String conValue) {
        String xpath = "//div[child::h4[normalize-space()='Conditions']]//input[@id='Property']";
        waitClearAndType(xpath, conValue);
    }

    public void clickButtonOnStickyBar(String buttonName) {
        waitUntilElementVisible("//div[@class='container']", 20);
        clickBtn("//div[@class='container']", buttonName, 1);
    }

    public void verifyMessageCollection(String message) {
        String xpath = "//div[text()='" + message + "']";
        waitUntilElementVisible(xpath, 20);
        waitForElementNotAppear(xpath);
    }

    public void clickBreadcrumbCollections() {
        String xpath = "//ol[contains(@class, 's-breadcrumb')]//a[text()[normalize-space()='Collections']]";
        clickOnElement(xpath);
    }

    public void addProductToCollection(String title) {
        String xPathCheck = "//div[@class='item-title' and contains(text(), '" + title + "')]//preceding-sibling::label//span[@class='s-check']";
        waitUntilElementVisible(xPathCheck, 20);
        clickOnElement(xPathCheck);
    }

    public void clickSave() {
        String xPath = "//div[@class='s-modal-footer']//button[@class='s-button is-primary']";
        clickOnElement(xPath);
    }

    public void verifyProductOfCollection(String nameProduct) {
        String xPath = "//td//a[@target='_blank' and normalize-space()='" + nameProduct + "']";
        refreshPage();
        if (isElementExist(xPath)) {
            verifyElementText(xPath, nameProduct);
        }
    }

    public void chooseTypeSort(String sortType) {
        String xPath = "//div[@class='s-select order-select']//select//option[text()[normalize-space() ='" + sortType + "']]";
        String xpath_list = "//div[@class='product-page']//tbody[@class='product-table']//tr | //div[@class='product-table']//div[@class='product-item']";
        int i = 0;
        while (!isElementExist(xpath_list)) {
            waitABit(15000);
            refreshPage();
            i++;
            if (i == 5)
                break;
        }
        clickOnElement(xPath);
    }

    public void clickAnyWhere() {
        String xPath = "//div[@class='col-md-4 col-xs-12']";
        clickOnElement(xPath);
    }

    public void verifyConditionValue(String value) {
        String xpath = "//div[child::h4[normalize-space()='Conditions']]//input[@id='Property']";
        assertThat(getTextValue(xpath)).isEqualTo(value);
    }

    public void deleteCondition() {
        String xPath = "//div[@class='row']//button[@class='s-button is-default']";
        if (isElementExist(xPath)) {
            clickOnElement(xPath);
        }
    }

    public void verifyMessageError(String message) {
        String xPath_ErrorMessage = "//div[@class='s-alert s-mt24 is-red']//span[@class='s-alert__title is-bold']";
        waitElementToBeVisible(xPath_ErrorMessage);
        verifyElementVisible(xPath_ErrorMessage, true);
        verifyElementContainsText(xPath_ErrorMessage, message);
    }

    public void verifySort(String nameProduct) {
        List<String> listProduct;
        String xPath = "//div[@class='product-title']";
        String xPathManual = "//td//a[@target='_blank']";
        if (isElementExist(xPathManual)) {
            listProduct = getListText(xPathManual);
        } else {
            listProduct = getListText(xPath);
        }
        assertThat(listProduct).isEqualTo(Arrays.asList(nameProduct.split(",")));
    }

    public void editWebsiteSeo() {
        String xPath = "//div[@class='s-flex-item']//button[@slot='edit-link']";
        clickOnElement(xPath);
    }

    public void verifyHandle(String handle) {
        String xPath = "//div[child::label[text()='URL and handle']]//following-sibling::div[@class='s-form-item__content']//input";
        assertThat(getValue(xPath)).isEqualTo(handle);
    }

    public void editHandle(String handle) {
        String xPath = "//div[child::label[text()='URL and handle']]//following-sibling::div[@class='s-form-item__content']//input";
        waitClearAndType(xPath, handle);
    }

    public void selectProduct(String title) {
        String xPathCheck = "//td[child::a[contains(text(), '" + title + "')]]//preceding-sibling::td//span[@class='s-check']";
        waitUntilElementVisible(xPathCheck, 20);
        clickOnElement(xPathCheck);
    }

    public void selectAction(String action) {
        String xpath = "//div[@class='action-table']//select";
        selectDdlByXpath(xpath, action);
    }

    public void clickMove() {
        String xpath = "//button[@type='button' and child::span[normalize-space()='Move']]";
        clickOnElement(xpath);
        waitABit(2000);
    }

    public void verifyStatus(String product, String status) {
        String xpath = "//td[child::a[normalize-space()='" + product + "']]//following-sibling::td//span[normalize-space()='" + status + "']";
        verifyElementPresent(xpath, true);
    }

    public void verifyStatus(String position) {
        String xpath = "//div[@class='action-table']//div[@class='setting-page']//input";
        waitClearAndType(xpath, position);
    }

    public boolean verifySyncEnoughProduct(String numberProduct) {
        String xpath = "//div[@class='index' and normalize-space()='" + numberProduct + "']";
        return isElementExist(xpath);
    }

    public void showMoreProduct() {
        String xpath = "//div[@class='text-center']//a";
        while (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void verifyProductAvailability(String product, String iconOnlineStore, String iconSearchEngine) {
        String xpathIconOnlineStore = "//td[child::a[normalize-space()='" + product + "']]//following-sibling::td//span[normalize-space()='Online store on listing pages.']//span";
        String xpathIconSearchEngine = "//td[child::a[normalize-space()='" + product + "']]//following-sibling::td//span[normalize-space()='Search engine Bot Crawlers, Sitemap files.']//span";
        assertThat(getAttributeValue(xpathIconOnlineStore, "class")).contains(iconOnlineStore);
        assertThat(getAttributeValue(xpathIconSearchEngine, "class")).contains(iconSearchEngine);
    }

    public void clickRefresh() {
        String xpath = "//button[child::span[normalize-space()='Refresh']]";
        if (isElementExist(xpath))
            clickOnElement(xpath);
    }

    public void clickAddProductThumbnail() {
        String xpath = "//a[text()[normalize-space()='+ Add another condition']]";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void addProductThumbnail(String option, String value, int row) {
        String xpath_name = "(//div[@class='s-input']//input[@placeholder='Color'])[" + row + "]";
        String xpath_value = "(//div[@class='s-input']//input[@placeholder='White'])[" + row + "]";
        waitClearAndType(xpath_name, option);
        waitClearAndType(xpath_value, value);
    }

    public void verifyCollectionHasProducts(String sNumberCollection) {
        String xpath="//tbody[@class='product-table']//tr";
        int numberCollectionActual=countElementByXpath(xpath);
        int numberCollectionExpected=Integer.parseInt(sNumberCollection);
        assertThat(numberCollectionActual).isEqualTo(numberCollectionExpected);
    }

    public void clickShowMore() {
        String xpath="//div[@class='text-center']//a[normalize-space()='Show more products']";
        if(countElementByXpath(xpath)==1){
            clickOnElement(xpath);
        }
    }
}