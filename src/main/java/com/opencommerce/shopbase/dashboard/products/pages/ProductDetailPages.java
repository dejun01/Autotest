package com.opencommerce.shopbase.dashboard.products.pages;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.*;
import org.junit.Assert;
import java.io.File;
import java.util.*;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailPages extends SBasePageObject {
    public ProductDetailPages(WebDriver driver) {
        super(driver);
    }

    public int MAX_RETRY_TIME = 10;
    public static int numberOfArtwork;
    String shop = LoadObject.getProperty("shop");
    String shopID;
    Calendar instance = Calendar.getInstance();

    public void verifyMaxLengthOfProductTitle(String length) {
        String xPath = "((//*[child::*[contains(text(),'Title')]]|//*[contains(text(),'Title')])/following-sibling::div//input)[1]";
        waitUntilElementVisible(xPath, 15);
        verifyMaxLength(xPath, length);
    }

    public void verifyProductErrorMessage(String errorMessage) {
        waitForTextToAppear(errorMessage);
    }

    public void verifyNoMessage() {
        String xPathMessage = "//span[@class='s-alert__title']";
        verifyElementVisible(xPathMessage, false);
    }

    public void enterTitle(String title) {
        String xPath = "((//*[child::*[contains(text(),'Title')]]|//*[contains(text(),'Title')]|//*[contains(text(),'标题')])/following-sibling::div//input)[1]";
        waitElementToBeVisible(xPath);
        clearValueByJS(xPath);
        waitClearAndType(xPath, title);
    }

    public void enterDescription(String description) {
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
        XH(xPathTinyMCE).sendKeys(description.trim());
        waitABit(1000);
        switchToIFrameDefault();
    }

    public void verifyMessageProductCreated(String message) {
        waitForTextToAppear(message);
    }

    public void verifyMessageProductSavedSuccessfully() {
        String xPath = "//div[@class='s-notices is-bottom']//div[text()[normalize-space()='Product was successfully saved!']]";
        waitElementToBeVisible(xPath);
    }

    public void verifyMessageProductSaved(String message) {
        String xpath = "//div[text()='" + message + "']";
        waitElementToBeVisible(xpath, 10);
    }

    public void waitProductListingPageDisplayed() {
        waitUntilElementVisible("//table[@id='all-products']//tbody", 5);
    }

    public void clickAddImageURL() {
        String xpath = "//a[normalize-space()='Add media from URL' or normalize-space()='Add image from URL' or normalize-space()='从URL添加图片']";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
    }

    public void searchProduct(String keyword) {
        String xpath = "//input[@placeholder='Search products']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, keyword);
        waitForEverythingComplete();
    }

    public void chooseProduct(String nameProduct) {
        String xPath = "(//div[@class='product-name' and normalize-space()='" + nameProduct + "']|//div[@class='product-name']//*[normalize-space()='" + nameProduct + "'])[1]";
        try {
            waitUntilElementVisible(xPath, 30);
            clickOnElement(xPath);
            waitUntilElementVisible("//div[contains(@class,'title-description white-bg')]//h2[contains(@class,'heading-section')][1]", 10);
        } catch (StaleElementReferenceException ex) {
            waitUntilElementVisible(xPath, 30);
            clickOnElement(xPath);
            waitUntilElementVisible("//div[contains(@class,'title-description white-bg')]//h2[contains(@class,'heading-section')][1]", 10);
        }
    }

    public void searchCollection(String colName) {
        String xPath = "//input[@type='text' and @placeholder='Search for collections']";
        waitUntilElementVisible(xPath, 10);
        waitClearAndType(xPath, colName);
    }

    public void chooseCollection(String colName) {
        String xPath = "//div[@class='s-form-item' and child::div//label[text()='Collections']]//span[text()[normalize-space()='" + colName + "']]";
        waitUntilElementVisible(xPath, 10);
        waitForElementFinishRendering(xPath);
        try {
            clickOnElement(xPath);
        } catch (Throwable t) {
            clickOnElementByJs(xPath);
        }
        waitForEverythingComplete();
    }

    public void addProductOrganizationInfo(String label, String value) {
        String xpath = "//div[contains(@class, 'title-description')]";
        String xpathInput = xPathInputFieldWithLabel(xpath, label, 1);
        waitUntilElementVisible(xpathInput, 20);
        waitClearAndType(xpathInput, value);
        XH(xpathInput).sendKeys(Keys.ENTER);
    }

    public void navigateToAllProductScreen(String tab) {
        String xPath = "//ul[@class='menu_level_1']//li//*[text()[normalize-space()='" + tab + "']]";
        String xPathSearch = "//input[@type='text' and @placeholder='Search products']";
        String xPathName = "//a[@href ='/admin/products']/preceding::a[@href ='/admin/products']";
        clickOnElement(xPath);
        waitElementToBeVisible(xPathName);
        waitForEverythingComplete();
        waitElementToBeVisible(xPathSearch);
    }

    // not working while page > 2
    public void selectAllProduct() {
        String xPathStatus = "//span[@data-label='Select all products']//input";
        String xPath = "//span[@data-label='Select all products']//span[contains(@class,'s-check')]";
        verifyCheckedThenClick(xPathStatus, xPath, true);
    }

    public void chooseAction(String action) {
        String xPath = "//*[contains(@class,'dropdown-item') and normalize-space()='" + action + "']";
        waitForEverythingComplete();
        clickOnElement(xPath);
    }

    public void enterSKU(String sku) {
        String xPath = "//div[@class='s-form-item']//input[@id='sku']";
        scrollToElement(xPath);
        waitClearAndType(xPath, sku);
    }

    public void enterBarcode(String barcode, int currentRetryTime) {
        String xPath = "//input[@id='barcode']";
        waitClearAndType(xPath, barcode);
        waitABit(1000);
        if (!XH(xPath).getValue().equals(barcode) && currentRetryTime < MAX_RETRY_TIME) {
            enterBarcode(barcode, currentRetryTime + 1);
        } else if (currentRetryTime >= MAX_RETRY_TIME) {
            System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
        }
    }

    public void waitUntilFieldIsDisplayed(String _labelName, int timeout) {
        String _parentXpath = "";
        String xpath = "((" + _parentXpath + "//input[@id='" + _labelName + "' or @placeholder='" + _labelName + "' or contains(text(),'" + _labelName + "') or preceding-sibling::*[text()[normalize-space()='" + _labelName + "']] or preceding::*[text()[normalize-space()='" + _labelName + "']] or @name='" + _labelName + "'or preceding-sibling::*[text()[normalize-space()='" + _labelName + "']]])[1]|(" + _parentXpath + "//*[descendant-or-self::*[text()='" + _labelName + "']]/following-sibling::*//input)[1])[1]";
        waitUntilElementVisible(xpath, timeout);
        scrollToElement(xpath);
    }

    public void scrollToViewLable(String label) {
        String xpath = "(//a[ text()[normalize-space()='" + label + "'] or child::span[normalize-space()='" + label + "']]|//div[@class='s-form-item__wrap-label']//label[text()='" + label + "'])[1]";
        scrollIntoElementView(xpath);
    }

    public void disableProduct() {
        String xPath = "//*[normalize-space()='Hide product']";
        if (isElementExist(xPath, 5)) {
            clickOnElement(xPath);
        }
    }

    public void enableProduct() {
        String xPath = "//*[normalize-space()='Show product']";
        if (isElementExist(xPath, 5)) {
            clickOnElement(xPath);
        }
    }

    public boolean isSavedChange() {
        waitABit(7000);
        return getDriver().findElement(By.xpath("//*[text()[normalize-space()='Save changes']]")).isDisplayed();
    }

    public void clickBreadcrumbProducts() {
        String xpath = "//ol[@class='breadcrumb']//span[text()[normalize-space()='Products']]";
        scrollToElement(xpath);
        clickOnElement(xpath);
        waitProductListingPageDisplayed();
    }

    public void verifyTags(String tag, boolean check) {
        String xpath = "//div[contains(@class, 'tag-list-items')]//span[text()[normalize-space()='" + tag + "']]";
        assertThat(isElementExist(xpath)).isEqualTo(check);
    }

    public void verifyTotalImage(String total) {
        String xpath = "//div[contains(@class,'section-image')]//h3";
        assertThat(getText(xpath)).contains(total);
    }

    public void verifyProductType(String productType) {
        String xpath = "//input[@placeholder='Product type' or @placeholder='商品类型']";
        waitUntilElementVisible(xpath, 5);
        assertThat(getValue(xpath)).isEqualToIgnoringCase(productType);
    }

    public void verifyVendor(String vendor) {
        String xpath = "//input[@placeholder=\"Nikola's Electrical Supplies\" or @placeholder='Nikola 的电器用品 ']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(vendor);
    }

    public void verifySKU(String sku) {
        String xpath = "//input[@id='sku']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(sku);
    }

    public void verifyPrice(String price) {
        String xpath = "//input[@id='price']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(price);
    }

    public void countImageVariant(String numberImage) {
        String xpath = "//div[contains(@class,'img m-sm')]//img";
        assertThat(countElementByXpath(xpath)).isEqualTo(Integer.parseInt(numberImage));
    }

    public void verifyDescription(String description) {
        waitForEverythingComplete();
        waitABit(5000);
        String xPathIframe = "//iframe[@class='tox-edit-area__iframe']";
        waitElementToBeVisible(xPathIframe);
        switchToIFrame(xPathIframe);
        String xPathTinyMCE = "//body[@id='tinymce']";
        waitElementToBeVisible(xPathTinyMCE);
        String currentDesc = XH(xPathTinyMCE).getText();
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + currentDesc);
        assertThat(currentDesc).isEqualToIgnoringCase(description);
        switchToIFrameDefault();
    }

    public void verifyProductTitle(String title) {
        String xpath = "//input[@placeholder='Short Sleeve T-Shirt']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(title);
    }

    public void verifyCompareAtPrice(String comparePrice) {
        String xpath = "//input[@id='compare_price']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(comparePrice);
    }

    public void verifyCostPerItem(String costPerItem) {
        String xpath = "//input[@id='cost_price']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(costPerItem);
    }

    public void verifyBarCode(String barCode) {
        String xpath = "//input[@id='barcode']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(barCode);
    }

    public void verifyImageURL(String imageURL) {
        String xpath = "//img[@src='" + imageURL + "']";
        waitUntilElementVisible(xpath, 5);
        verifyElementVisible(xpath, true);
    }

    public void verifyQuantity(String quantity) {
        String xpath = "//input[@id='quantity']";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(quantity);
    }

    public void verifyWeight(String weight) {
        String xpath = "//p[normalize-space()='Weight' or normalize-space()='重量']//following-sibling::div//input";
        waitUntilElementVisible(xpath, 5);
        assertThat(XH(xpath).getValue()).isEqualToIgnoringCase(weight);
    }

    public void verifyWeightUnit(String weightUnit) {
        String xpath = "//div[child::h3[text()[normalize-space()='Weight']]]//select";
        waitUntilElementVisible(xpath, 5);
        String selectedOption = getSelectedValueDdl(xpath);
        assertThat(selectedOption).isEqualToIgnoringCase(weightUnit);
    }

    public void verifyInventoryPolicy(String inventoryPolicy) {
        String xpath = "//div[@class='s-form-item' and child::div//label[text()='Inventory policy']]//select";
        waitUntilElementVisible(xpath, 5);
        String selectedOption = getSelectedValueDdl(xpath);
        assertThat(selectedOption).isEqualToIgnoringCase(inventoryPolicy);
    }

    public void enterProductType(String productType) {
        String xpath = "//input[@placeholder='Product type' or @placeholder='商品类型']";
        waitClearAndType(xpath, productType);
    }

    public void enterVendor(String vendor) {
        String xpath = "//input[@placeholder=\"Nikola's Electrical Supplies\" or @placeholder='Nikola 的电器用品 ']";
        waitClearAndType(xpath, vendor);
    }

    public void verifydisableProduct(String isDisable) {
        String xpathWhenEnable = "//*[normalize-space()='Hide product']";
        String xpathWhenDisable = "//*[normalize-space()='Show product']";
        if (Boolean.parseBoolean(isDisable)) {
            waitUntilElementVisible(xpathWhenDisable, 5);
            verifyElementVisible(xpathWhenDisable, true);
        } else {
            waitUntilElementVisible(xpathWhenEnable, 5);
            verifyElementVisible(xpathWhenEnable, true);
        }
    }

    public void verifyName(String nameProduct) {
        String xPathName = "//h1[contains(@class,'large')]";
        waitElementToBeVisible(xPathName, 20);
        waitABit(5000);
        verifyElementText(xPathName, nameProduct);
    }

    public void verifyValueFieldWithLabel(String title, String nameProduct) {
        verifyElementText(xPathInputFieldWithLabel("", title, 1), nameProduct);
    }


    public String getCurrentURL() {
        clickOnElement("//button[normalize-space()='Edit website SEO']");
        return getTextValue("//div[@class='s-form-item' and descendant::label[normalize-space()='URL and handle']]//input");
    }

    public void enterNewURL(String newURL) {
        waitClearAndType("//section[@class='card search-engine' and descendant::div[@class='type-container']]//div[@class='s-input s-input-group s-input-group--prepend']//input", newURL);
    }

    public void verifySaveProductSuccess() {
        verifyElementVisible("//div[@class='s-notices is-bottom']//div[normalize-space()='Product was successfully saved!']", true);
    }

    public void clickSaveChangesOrDiscard(String btn) {
        String xPath = "//*[text()[normalize-space()='" + btn + "']]";
        String xPathImage = "//div[contains(@class,'title-description')]//h3[contains(normalize-space(),'Media') or contains(normalize-space(),'Image')]";
        if (isElementVisible(xPath, 15)) {
            clickOnElement(xPath);
          //  waitElementToBeVisible(xPathImage, 20);
        }
        waitABit(5000);
    }

    public void verifyProductSaveSuccessfully() {
        String xPathMsg = "//*[text()[normalize-space()='Product was created successfully!'] " +
                "or text()[normalize-space()='Products was successfully saved!'] " +
                "or text()[normalize-space()='Variants was successfully saved!'] " +
                "or text()[normalize-space()='Product was successfully saved!']]";
        verifyElementVisible(xPathMsg, true);
    }

    public void verifyCollectionOfProduct(String nameCollection) {
        String xPathTag = "//div[@class='tag-list-items']/span";
        verifyElementText(xPathTag, nameCollection);
    }

    public void waitAndRefreshPage() {
        waitABit(5000);
        refreshPage();
        waitABit(5000);
    }

    public void clickInputTags(int currentRetryTime) {
        String xPathTag = "//div[child::div[normalize-space()='Tags']]//following-sibling::div//div[contains(@class,'tag')]//div[@class='s-input']";
        String xPathFrequentTag = "//div[normalize-space()='Frequently used tags']";
        waitUntilElementVisible(xPathTag, 10);

        try {
            clickOnElement(xPathTag);
            waitElementToBeVisible(xPathFrequentTag);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            if (currentRetryTime < MAX_RETRY_TIME) {
                refreshPage();
                clickInputTags(currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }

        waitForElementFinishRendering(xPathFrequentTag);
        verifyElementVisible(xPathFrequentTag, true);
    }

    public void verifyFrequentlyTags(String tag1, String tag2, String tag3) {
        verifyElementVisible("//span[@class='s-dropdown-item' and child::span[normalize-space()='" + tag1 + "']]", true);
        verifyElementVisible("//span[@class='s-dropdown-item' and child::span[normalize-space()='" + tag2 + "']]", true);
        verifyElementVisible("//span[@class='s-dropdown-item' and child::span[normalize-space()='" + tag3 + "']]", true);
    }

    public void addTags(String tag1) {
        clickOnElement("//span[@class='s-dropdown-item' and child::span[normalize-space()='" + tag1 + "']]");
        verifyElementVisible("//span[contains(@class,'applied-tag') and normalize-space()='" + tag1 + "']", true);
    }

    public void clickViewAllTags() {
        clickOnElement("//span[normalize-space()='View all tags']");
        String xPath = "//h2[normalize-space()='Tags' and @class='title']";
        waitElementToBeVisible(xPath);
        verifyElementVisible(xPath, true);
    }

    public void verifyAppliedTagsOnPopup(String tag1) {
        verifyElementVisible("//h3[normalize-space()='Applied tags']//following-sibling::div/span[normalize-space()='" + tag1 + "']", true);
        verifyElementVisible("//h3[normalize-space()='All tags']//following-sibling::div//span[@class='s-tag available-tag is-medium is-disabled' and normalize-space()='" + tag1 + "']", true);
    }

    public void verifyAvailableTagsOnPopup(String tag2, String tag3) {
        verifyElementVisible("//h3[normalize-space()='All tags']//following-sibling::div//span[@class='s-tag available-tag is-medium' and normalize-space()='" + tag2 + "']", true);
        verifyElementVisible("//h3[normalize-space()='All tags']//following-sibling::div//span[@class='s-tag available-tag is-medium' and normalize-space()='" + tag3 + "']", true);
    }

    public void removedTags(String tag1) {
        clickOnElement("//h3[normalize-space()='Applied tags']//following-sibling::div//span[normalize-space()='" + tag1 + "']//a");
        verifyElementVisible("//h3[normalize-space()='All tags']//following-sibling::div//span[@class='s-tag available-tag is-medium' and normalize-space()='" + tag1 + "']", true);
    }

    public void clickUnAppliedTags(String tag2) {
        clickOnElement("//h3[normalize-space()='All tags']//following-sibling::div//span[@class='s-tag available-tag is-medium' and normalize-space()='" + tag2 + "']");
        verifyElementVisible("//h3[normalize-space()='Applied tags']//following-sibling::div/span[normalize-space()='" + tag2 + "']", true);
    }

    public void clickApplyChange() {
        clickOnElement("//span[normalize-space()='Apply changes']");
        waitForElementNotVisible("//h2[normalize-space()='Tags' and @class='title']");
    }

    public void verifyAddedTagOnProductDetailPage(String tag2) {
        verifyElementVisible("//span[contains(@class,'applied-tag') and normalize-space()='" + tag2 + "']", true);
    }

    public void verifyRemovedTagOnProductDetailPage(String tag1) {
        verifyElementVisible("//span[contains(@class,'applied-tag') and normalize-space()='" + tag1 + "']", false);
    }

    public void clickDuplicateOnProductDetail() {
        String xPath = "//button[child::span[normalize-space()='Duplicate']]";
        waitUntilElementVisible(xPath, 20);
        clickOnElement(xPath);
    }

    public void verifyOpenPopupDuplicateProduct() {
        String xPathPopup = "//p[normalize-space()='Duplicate this product?']";
        verifyElementVisible(xPathPopup, true);
    }

    public void clickDuplicateOnPopupDuplicateProduct() {
        String xPath = "//div[@class='modal-action']//button[child::span[normalize-space()='Duplicate']]";
        clickOnElement(xPath);
    }

    public void verifyDuplicateProductSuccessfully(String nameProduct) {
        String xPath = "//h1[normalize-space()='Copy of " + nameProduct + "']";
        verifyElementVisible(xPath, true);
    }

    public void enterNewOptionSet(String optionSet, int index) {
        String xPath = "(//input[@id='option-name'])[" + index + "]";
        waitElementToBeVisible(xPath);
        waitClearAndType(xPath, optionSet);
    }

    public void enterNewOptionValue(String optionValue, int index) {
        String xPath = "(//div[child::input[@id='option-name']]/following-sibling::div//input)[" + index + "]";
        clearValueByJS(xPath);
        if (optionValue.contains(",")) {
            String[] value = optionValue.split(",");
            for (String option : value) {
                waitABit(1000);
                XH(xPath).sendKeys(option);
                XH(xPath).sendKeys(Keys.TAB);
//                waitTypeAndTab(xPath, option);
            }
        } else {
            waitABit(1000);
            waitTypeAndTab(xPath, optionValue);
        }
    }

    public void verifyHasVariant() {
        String tableVariant = "//table[@id='all-variants']";
        verifyElementPresent(tableVariant, true);
    }

    public void verifyHasNoVariant() {
        String tableVariant = "//table[@id='all-variants']";
        verifyElementPresent(tableVariant, false);
    }

    public void verifyVariantValue(String variant, String value, int index) {
        String[] optionValue = variant.split(",");
        int multi = optionValue.length;
        String xpath = "";
        if (!variant.equals("")) {
            if (multi == 1) {
                xpath = "(//tr[child::td[text()='" + variant + "']])//td[" + index + "]";
                if (value.contains("PH") || value.contains("PB")) {
                    assertThat(getText(xpath)).contains(value);
                } else {
                    verifyElementText(xpath, value);
                }
            } else if (multi == 2) {
                index += 1;
                xpath = "(//tr[child::td[text()='" + optionValue[0].trim() + "'] and child::td[text()='" + optionValue[1].trim() + "']]//td)[" + index + "]";
                if (value.contains("PH") || value.contains("PB")) {
                    assertThat(getText(xpath)).contains(value);
                } else {
                    verifyElementText(xpath, value);
                }
            } else if (multi == 3) {
                index += 2;
                xpath = "(//tr[child::td[text()='" + optionValue[0].trim() + "'] and child::td[text()='" + optionValue[1].trim() + "'] and child::td[text()='" + optionValue[2].trim() + "']]//td)[" + index + "]";
                if (value.contains("PH") || value.contains("PB")) {
                    assertThat(getText(xpath)).contains(value);
                } else {
                    verifyElementText(xpath, value);
                }
            }
        }
    }

    public void enterOptionValue(String optionValue) {
        String xPath = "//*[normalize-space()='Options']//following-sibling::*//input[@id='option1']";
        waitElementToBeVisible(xPath, 10);
        waitClearAndType(xPath, optionValue);
    }

    public void verifyNoButtonSave() {
        String xPath = "//button[normalize-space()='Save changes']";
        verifyElementVisible(xPath, false);
    }

    public void verifyHasButtonSave() {
        String xPath = "//button[normalize-space()='Save changes']";
        verifyElementVisible(xPath, true);
    }

    public void verifyAddVariantSuccess() {
        String xPath = "//div[@class='s-notices is-bottom']";
        verifyElementText(xPath, "Variant has been added successfully!");
    }

    public void clickEditVariant() {
        String xPath = "//span[@data-label='Edit']//button";
        if (isElementVisible(xPath, 5)) {
            clickOnElement(xPath);
        }
        waitABit(3000);
    }

    public void clickEditVariantWithValue(String variant) {
        String[] optionValue = variant.split(";");
        int multi = optionValue.length;
        String xpath = "";
        if (!variant.equals("")) {
            if (multi == 1) {
                xpath = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "']])//span[@data-label='Edit']";
                clickOnElement(xpath);
            } else if (multi == 2) {
                xpath = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "']])//span[@data-label='Edit']";
                clickOnElement(xpath);
            } else if (multi == 3) {
                xpath = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "'] and child::td[normalize-space()='" + optionValue[2].trim() + "']])//span[@data-label='Edit']";
                clickOnElement(xpath);
            }
        }
    }

    public void selectVariantOnVariantDetailPage(String variant) {
        String[] optionValue = variant.split(";");
        int multi = optionValue.length;
        String variantTitle = "";
        for (int i = 0; i < multi; i++) {
            variantTitle += optionValue[i].trim();
            if (i < multi - 1) {
                variantTitle += " / ";
            }
        }
        String xpath = "//div[contains(@class,'title-description variant') and child::div[normalize-space()='" + variantTitle + "']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifyOptionSet(String optionSet) {
        String[] optionSetList = optionSet.split(";");
        int multi = optionSetList.length;
        for (int i = 1; i <= multi; i++) {
            String xPath = "(//label[@class='s-form-item__label' and contains(@for,'option')])[" + i + "]";
            verifyElementText(xPath, optionSetList[i - 1].trim());
        }
    }

    public void verifyOptionValue(String optionValue) {
        String[] optionValueList = optionValue.split(";");
        int multi = optionValueList.length;
        for (int i = 1; i <= multi; i++) {
            String xPath = "(//div[@class='s-form-item__content']//input[contains(@id,'option')])[" + i + "]";
            verifyElementText(xPath, optionValueList[i - 1].trim());
        }
    }

    public void verifyPageTitle(String variant, String nameProduct) {
        String xPath = "//div[@class='add-variant-page']//h1//div";
        String xPath1 = "(//div[contains(@class,'title-description')]//p)[1]";
        String[] optionValue = variant.split(";");
        int multi = optionValue.length;
        String variantTitle = "";
        for (int i = 0; i < multi; i++) {
            variantTitle += optionValue[i].trim();
            if (i < multi - 1) {
                variantTitle += " / ";
            }
        }
        verifyElementText(xPath, variantTitle);
        verifyElementText(xPath1, nameProduct);
    }

    public void verifyNumberOfVariant(String numberOfVariant) {
        String xPath = "(//div[contains(@class,'title-description')]//p)[2]";
        String number = XH(xPath).getText().trim().split(" ")[0];
        assertThat(number).isEqualTo(numberOfVariant);
    }

    public void verifyTrackQuantity(String trackQuantity) {
        String xpath = "//div[child::label[text()='Inventory policy']]//following-sibling::div//select";
        assertThat(getSelectedValue(xpath)).isEqualTo(trackQuantity);
    }

    public void verifyAllowOverselling(String allowOverselling) {
        String xpathQuantity = "//input[@id='quantity']";
        String xpathAllowOverselling = "//span[normalize-space()=\"Allow customers to purchase this product when it's out of stock\"]//preceding-sibling::input";
        if (isElementVisible(xpathQuantity, 10)) {
            switch (allowOverselling) {
                case "Yes":
                    verifyElementSelected(xpathAllowOverselling, true);
                    break;
                case "No":
                    verifyElementSelected(xpathAllowOverselling, false);
                    break;
                default:
                    fail();
            }
        }
    }

    public void clickBtnAddImage() {
        String xpath = "//button[child::span[normalize-space()='Add media' or normalize-space()='Add image' or normalize-space()='添加图片']]";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
        String xpathLoading = "//div[contains(@class,'section-image')]//div[@class='spinner-css']";
        waitForElementNotAppear(xpathLoading);
    }

    public void verifyEditVariantSuccess() {
        String xPath = "//div[@class='s-notices is-bottom']//div//div[text()='Variant has been updated successfully!']";
        waitElementToBeVisible(xPath);
        verifyElementPresent(xPath, true);
    }

    public void selectDuplicateVariants(String optionSet) {
        String xpath = "//div[@class='s-dropdown-menu']//span[normalize-space()='...in another " + optionSet + "']";
        waitForElementFinishRendering(xpath);
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void enterNewValue(String optionSet, String newOptionValue) {
        waitClearAndType("((//label[contains(text(),'" + optionSet + " for duplicated variants')]//parent::div)//following-sibling::div)//input", newOptionValue);
    }

    public void verifyMessage(String message) {
        waitForTextToAppear(message, 15000);
    }

    public void clickAddAnotherOption() {
        clickOnElement("//button[normalize-space()='Add another option']");
    }

    public void selectVariant(String variant) {
        String[] optionValue = variant.split(",");
        int multi = optionValue.length;
        String xpathStatus = "";
        String xpathLabel = "";
        if (!variant.equals("")) {
            if (multi == 1) {
                xpathStatus = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "']])//input";
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "']])//span[@class='s-check']";
                verifyCheckedThenClick(xpathStatus, xpathLabel, true);
            } else if (multi == 2) {
                xpathStatus = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "']])//input";
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "']])//span[@class='s-check']";
                verifyCheckedThenClick(xpathStatus, xpathLabel, true);
            } else if (multi == 3) {
                xpathStatus = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "'] and child::td[normalize-space()='" + optionValue[2].trim() + "']])//input";
                xpathLabel = "(//tr[child::td[normalize-space()='" + optionValue[0].trim() + "'] and child::td[normalize-space()='" + optionValue[1].trim() + "'] and child::td[normalize-space()='" + optionValue[2].trim() + "']])//span[@class='s-check']";
                verifyCheckedThenClick(xpathStatus, xpathLabel, true);
            }
        }
        waitABit(2000);
    }

    public void clickAddAnotherOptionInEditoption() {
        clickOnElement("//div[normalize-space()='Add another option']");
    }

    public void enterAnotherOption(String optionSet) {
        String xpath = "(//div[@class='s-form-item__content']//div[@class='s-input']//input[@placeholder=''])";
        int numItem = countElementByXpath(xpath);
        xpath = xpath + "[" + numItem + "]";
        XH(xpath).clear();
        inputSlowly(xpath, optionSet);
    }

    public void enterAnotherOptionValue(String optionSet, String optionValue) {
        String xpath = "//input[@placeholder='Default " + optionSet + "']";
        waitClearAndType(xpath, optionValue);
    }

    public void verifyMessageUpdateOption(String message) {
        waitElementToBeVisible("//div[contains(text(),'" + message + "')]");
    }

    public int countElementByXpath(String xpath) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        int count = elements.size();
        return count;
    }

    public int countImageVariantByXpath(String _xpathOrSelector) {
        int n = 0;
        if (isElementExist(_xpathOrSelector)) {
            n = findAll(_xpathOrSelector).size();
        }
        return n;
    }

    public void enterNewOption(String oldOptionSet, String newOptionSet) {
        String xpathAll = "(//div[@class='s-modal-body']//input[@type='text'])";
        String xpathExpect = "";
        List<WebElement> elements = getDriver().findElements(By.xpath(xpathAll));
        int count = elements.size();
        int index;
        for (int i = 1; i <= count; i++) {
            String xpath = "(//div[@class='s-modal-body']//input[@type='text'])[" + i + "]";
            if (oldOptionSet.equals(getTextValue(xpath))) {
                index = i;
                xpathExpect = "(//div[@class='s-modal-body']//input[@type='text'])[" + index + "]";
            }
        }
        waitClearAndType(xpathExpect, newOptionSet);
    }

    public void deleteOption(String optionSet, String optionValue) {
        String xpathAll = "//div[@class='s-modal-wrapper']//form//div[contains(@class,'row')]";
        String xpathTitlePopup1 = "//p[text()='Delete option value?']";
        String xpathTitlePopup2 = "//p[normalize-space()='Edit options']";
        List<WebElement> elements = getDriver().findElements(By.xpath(xpathAll));
        int count = elements.size();
        int index;
        for (int i = 1; i <= count; i++) {
            String xpath = "(//div[@class='s-modal-wrapper']//form//div[contains(@class,'row')])[" + i + "]//input";
            if (optionSet.equals(getTextValue(xpath))) {
                index = i;
                String xpathDelete = "(//div[@class='s-modal-wrapper']//form//div[contains(@class,'row')])[" + index + "]//img";
                String xpathRow = "(//div[@class='s-modal-wrapper']//form//div[contains(@class,'row')])[" + index + "]//span[contains(@class,'tag-list-item') and normalize-space()='" + optionValue + "']//a";
                if (isElementVisible(xpathDelete, 5)) {
                    clickOnElement(xpathDelete);
                    clickBtn("Delete");
                    waitForElementNotAppear(xpathTitlePopup2);
                } else {
                    clickOnElement(xpathRow);
                    clickBtn("Delete");
                    verifyMessage("Deleted variants");
                    clickBtn("Cancel");
                    waitForElementNotAppear(xpathTitlePopup2);
                }
            }
        }
    }

    public void verifyDefaultPageTitle(String pageTitle) {
        String xpath = "//div[child::label[text()='Page title']]//following-sibling::div//input";
        waitElementToBeVisible(xpath);
        String val = getAttributeValue(xpath, "placeholder");
        assertThat(val).isEqualTo(pageTitle);
    }

    public void verifyDefaultMetaDescription(String metaDescription) {
        String xpath = "//div[child::label[text()='Meta description']]//following-sibling::div//textarea";
        waitElementToBeVisible(xpath);
        String val = getAttributeValue(xpath, "placeholder").replace("\n", "").replace("\u00a0", "");
        assertThat(val).isEqualTo(metaDescription);
    }

    public void verifyDefaultURLAndHandle(String urlAndHandle) {
        String xpathDomainProductSF = "//div[child::label[text()='URL and handle']]//following-sibling::div//div[@class='s-input-group__prepend']";
        String xpathHandle = "//div[child::label[text()='URL and handle']]//following-sibling::div//input";
        String url = "https://" + shop + "/products/";
        assertThat(url).isEqualTo(getText(xpathDomainProductSF));
        assertThat(urlAndHandle.replace("_", "-").toLowerCase()).isEqualTo(getTextValue(xpathHandle));
    }

    public void enterPageTitle(String pageTitle) {
        String xpath = "//div[child::label[text()='Page title']]//following-sibling::div//input";
        waitElementToBeVisible(xpath);
        XH(xpath).clear();
        waitClearAndType(xpath, pageTitle);
    }

    public void enterMetaDescription(String metaDescription) {
        String xpath = "//div[child::label[text()='Meta description']]//following-sibling::div//textarea";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, metaDescription);
    }

    public void enterUrlAndHandle(String urlAndHandle) {
        String xpath = "//div[child::label[text()='URL and handle']]//following-sibling::div//input";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, urlAndHandle);
    }

    public void verifyDataPageTitle(String pageTitle) {
        String xpath = "//div[child::label[text()='Page title']]//following-sibling::div//input";
        waitElementToBeVisible(xpath);
        assertThat(getTextValue(xpath)).isEqualTo(pageTitle);
    }

    public void verifyLengthSeo(String title, String length) {
        String xpath = "//div[child::label[normalize-space()='" + title + "']]//following-sibling::div//small";
        assertThat(getTextValue(xpath)).isEqualTo(length);
    }

    public void verifyDataMetaDescription(String metaDescription) {
        String xpath = "//div[child::label[text()='Meta description']]//following-sibling::div//textarea";
        waitElementToBeVisible(xpath);
        assertThat(getValue(xpath)).isEqualTo(metaDescription);
    }

    public void verifyDataURLAndHandle(String urlAndHandle) {
        String xpathDomainProductSF = "//div[child::label[text()='URL and handle']]//following-sibling::div//div[@class='s-input-group__prepend']";
        String xpathHandle = "//div[child::label[text()='URL and handle']]//following-sibling::div//input";
        String url = "https://" + shop + "/products/";
        assertThat(url).isEqualTo(getText(xpathDomainProductSF));
        assertThat(urlAndHandle.replace("_", "-").toLowerCase()).isEqualTo(getTextValue(xpathHandle));
    }

    public void inputTag(String tag, int currentRetryTime) {
        String xPathInputTag = "//div[child::div[normalize-space()='Tags' or normalize-space()='标签']]//following-sibling::div//div[contains(@class,'tag')]//div[@class='s-input']//input";
        waitElementToBeVisible(xPathInputTag);
        waitForElementFinishRendering(xPathInputTag);
        XH(xPathInputTag).clear();
        waitClearAndType(xPathInputTag, tag);
        XH(xPathInputTag).sendKeys(Keys.TAB);
        XH(xPathInputTag).sendKeys(Keys.ENTER);

        try {
            verifyTag(tag);
        } catch (Throwable t) {
            String xpath = "//div[contains(@class, 'tag-list-items tag-list-items--has-tooltip')]//a[@role='button']";

            while (isElementVisible(xpath, 2)) {
                clickOnElement(xpath);
            }

            if (currentRetryTime < MAX_RETRY_TIME) {
                inputTag(tag, currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }
    }

    public void enterPrice(String enterPrice) {
        String xPath = "//div[@class='s-form-item']//input[@id='price']";
        waitClearAndType(xPath, enterPrice);
    }

    public void enterCompareAtPrice(String comparePrice) {
        String xPath = "//div[@class='s-form-item']//input[@id='compare_price']";
        waitClearAndType(xPath, comparePrice);
    }

    public void enterCostPerItem(String costPerItem) {
        String xPath = "//div[@class='s-form-item']//input[@id='cost_price']";
        waitClearAndType(xPath, costPerItem);
    }

    public void verifyCollections(String nameCollection) {
        String xPath = "//div[@class='s-form-item'][descendant::*[normalize-space()='Collections']]//div[@class='tag-list-items']//span[@class='']";
        if (isElementExist(xPath)) {
            List<String> listCollection = getListText(xPath);
            assertThat(listCollection).isEqualTo(Arrays.asList(nameCollection.split(",")));
        }
    }

    public void verifyTag(String tag) {
        String xpathTag = "";
        String[] tagList = tag.split(",");
        for (int i = 0; i < tagList.length; i++) {
            xpathTag = "//div[contains(@class, 'tag-list-items')]//span[text()[normalize-space()='" + tagList[i].trim() + "']]";
            waitElementToBeVisible(xpathTag);
            verifyElementVisible(xpathTag, true);
        }
    }

    public void verifyProductDetailPage() {
        String xPath = "//div[@class='row product-info']";
        waitElementToBeVisible(xPath);
        verifyElementVisible(xPath, true);
    }

    public void addProductByApi(String shop, String title, String accessToken) {
        String api = "https://" + shop + "/admin/products.json";
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("title", title);
        JsonObject requestParamsProduct = new JsonObject();
        requestParamsProduct.add("product", requestParams);
        System.out.println("url: " + api + "?access_token=" + accessToken);
        waitABit(10000);
        postAPI(api, accessToken, requestParamsProduct, 0);
    }

    public Response postAPI(String url, String accessTokenShop, JsonObject requestParamsProduct, int currentRetryTime) {
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.queryParam("access_token", accessTokenShop);
        request.header("Content-Type", "application/json");
        request.body(requestParamsProduct.toString());
        try {
            Response response = request.post();
            if (response.getStatusCode() == 200) {
                System.out.println("Post API successfully");
            }
            if (response.getStatusCode() == 400) {
                assertTrue(true);
                System.out.println("CREATE_PRODUCT_LIMIT_PER_DAY_REACHED");
            }
            return response;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(10000);
                System.out.println("currentRetryTime: " + (currentRetryTime += 1));
                return postAPI(url, accessTokenShop, requestParamsProduct, currentRetryTime);
            } else {
                System.out.println("Reach to max retry, but it is still fail");
                Assert.fail();
                return null;
            }
        }
    }

    public void deleteTags() {
        String xpath = "//span[@class='s-tag tag-list-item applied-tag is-medium']//following-sibling::a";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifySaveButtonDisable() {
        String xpath = "//button[@class='s-button is-primary is-disabled']";
        assertThat(getAttributeValue(xpath, "disabled")).contains("true");
    }

    public void verifyProductMapped() {
        String xpath = "//span[text()='Mapped']";
        verifyElementPresent(xpath, true);
    }

    public void verifyButtonDisable(String button, String status, String title) {
        String xpath_1 = "//a[normalize-space()='" + button + "']";
        String xpath_2 = "//button[@class='s-button is-default' and normalize-space()='" + button + "']";
        if (isElementExist(xpath_1)) {
            assertThat(getAttributeValue(xpath_1, "class")).contains(status);
        } else {
            assertThat(getAttributeValue(xpath_2, "disabled")).contains("true");
            waitABit(1000);
            assertThat(getAttributeValue(xpath_2, "title")).isEqualTo(title);
        }
    }

    public void editVariant() {
        String xpath = "//td//button[@class='s-button no-border is-default' and descendant::i[@class='mdi mdi-pencil mdi-18px']]";
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void openProductDetail() {
        waitProductListingPageDisplayed();
        String xpath = "//div[@class='product-name'][1]";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
        waitForPageLoad();
        refreshPage();
    }

    public void viewProduct() {
        String xpath = "//div[@class='action-bar__item']//a";
        waitUntilElementVisible(xpath, 30);
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void addImage(String image) {
        String xpath = "//input[@type='file' and @accept='image/jpeg,image/png,image/webp,image/gif,image/jpg,video/mp4,video/mpx,video/quicktime'] | //input[@type='file' and @accept='image/jpeg,image/png,image/webp,image/gif,image/jpg']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(2000);
    }

    public void deleteAllImage() {
        String xpath = "//div[@class='overlay d-flex align-items-end justify-content-center']";
        for (int i = 1; i <= countElementByXpath(xpath); i++) {
            hoverOnElement(xpath);
            clickOnElement("//div//i[@class='icon-big cursor-pointer m-l-sm']");
        }
    }

    public void verifyAddImage(String total) {
        String xpath = "//h3[contains(text(),'Media') or contains(text(),'图片')]";
        waitABit(5000);
        verifyElementContainsText(xpath, total);
    }

    public void clickImageVariant(String variant) {
        String xpath = "//td[text()='" + variant + "']//preceding-sibling::td//span[contains(@class,'image__wrapper')]//img";
        clickOnElement(xpath);
        waitForPageLoad();
    }

    public void selectImageInPopup(String qty) {
        String xpath = "//div[@class='s-modal-wrapper']//div[@class='img m-sm' or @class='img m-sm selected']";
        for (int i = 1; i <= Integer.parseInt(qty); i++) {
            String xpathImage = "(" + xpath + ")[" + i + "]";
            clickOnElement(xpathImage);
        }
        waitForPageLoad();
    }

    public void selectImageVariant(String image) {
        String xpath = "//label[contains(@class,'s-upload s-control s-button')]//input";
        chooseAttachmentFile(xpath, "phub" + File.separator + image);
        waitABit(5000);
    }

    public void previewImageVariant(String image) {
        String xpath_image = "//div[@class='modal-body-container']//div[@class='overlay d-flex align-items-end justify-content-center']";
        String xpath_preview = "//div[@class='modal-body-container']//div[@class='overlay d-flex align-items-end justify-content-center']//i[@class='icon cursor-pointer']";
        hoverOnElement(xpath_image);
        clickOnElement(xpath_preview);
        waitABit(1000);
        verifyElementPresent("//div[@class='s-modal-header']//p[normalize-space()='Preview Image']", true);
    }

    public void verifyImageVariant(String totalImageAdded) {
        String xpath = "//div[@class='d-flex justify-content-space-between align-items-center' and child::h3[normalize-space()='Images (" + totalImageAdded + ")']]";
        verifyElementPresent(xpath, true);
    }

    public void verifyNumberOfVariantImage(String varGroup, int imageQty) {
        String xpathVar = "//table[@id='all-variants']//tr[child::td[contains(text(),'" + varGroup + "')]]";
        int count = countElementByXpath(xpathVar);
        for (int i = 1; i <= count; i++) {
            String xpath = "(" + xpathVar + "//div[@class='image__variant-count'])[" + i + "]";
            getText(xpath);
            assertThat(getText(xpath)).isEqualTo("+" + (imageQty - 1));
        }
    }

    public void reVerifyNumberOfVariantImage(String varGroup, int imgUnCheck, int imageQty) {
        String xpathVar = "//table[@id='all-variants']//tr[child::td[contains(text(),'" + varGroup + "')]]";
        int count = countElementByXpath(xpathVar);
        for (int i = 1; i <= count; i++) {
            String xpath = "(" + xpathVar + "//div[@class='image__variant-count'])[" + i + "]";
            getText(xpath);
            assertThat(getText(xpath)).isEqualTo("+" + (imageQty - imgUnCheck));
        }
    }

    public void reVerifyNumberOfOneVarImage(String optionValue, String optionValueNext, int imgUnCheck, int imageQty) {
        String xpath = "//tbody//tr[child::td[normalize-space()='" + optionValue + "'] and child::td[normalize-space()='" + optionValueNext + "']]//div";
        getText(xpath);
        assertThat(getText(xpath)).isEqualTo("+" + (imageQty - imgUnCheck));

    }

    public void verifyNumberOfOneVariantImage(String optionValue, String optionValueNext, int imageQty) {
        String xpath = "//tbody//tr[child::td[normalize-space()='" + optionValue + "'] and child::td[normalize-space()='" + optionValueNext + "']]//div";
        getText(xpath);
        assertThat(getText(xpath)).isEqualTo("+" + (imageQty - 1));
    }

    public void verifyGroupVariant(boolean check) {
        String xpathStatus = "//div[@class='s-flex s-flex--align-center product__variant__group']//input";
        String xpathLabel = "//div[@class='s-flex s-flex--align-center product__variant__group']//span[@class='s-check']";
        String xpathGroup = "//td[@class='group-title']//span[normalize-space()='Group:']";
        verifyCheckedThenClick(xpathStatus, xpathLabel, check);
        verifyElementPresent(xpathGroup, check);
    }

    public boolean chooseImageVisible() {
        String xpath = "//*[@class='s-section']";
        return isElementExist(xpath, 20);
    }

    public void verifyMessageProduct(String message) {
        String xpath = "//div[@class='s-alert__description message-error']//span";
        verifyElementText(xpath, message);
    }

    //Verify Clone product Phub Variant
    public void verifyImageLink() {
        String xpathNumberOfImage = "//h3[contains(@class,'heading-section')]";
        String images = getText(xpathNumberOfImage);
        int numberOfImage = Integer.parseInt(images.split(" ")[1].split("\\(")[1]);
        for (int i = 1; i <= numberOfImage; i++) {
            String xpathImageLink = "(//div[contains(@class,'section-image')]//img)[" + i + "]";
            String imageLink = getAttributeValue(xpathImageLink, "src");
            JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
            String shopID_Curent = jsExec.executeScript("return sbShopId").toString();
            try {
                assertThat(imageLink).contains(shopID);
            } catch (Throwable e) {
                assertThat(imageLink).contains(shopID_Curent);
            }
        }
    }

    public String getShopID() {
        JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
        shopID = jsExec.executeScript("return sbShopId").toString();
        return shopID;
    }

    public void verifyImagesVariant() {
        String xpathNumberOfImagesVariant = "//tbody//img";
        String linkImagesVariant = getAttributeValue(xpathNumberOfImagesVariant, "src");
        if (!linkImagesVariant.contains("product_thumb-placeholder")) {
            int numberOfImagesVariant = countElementByXpath(xpathNumberOfImagesVariant);
            for (int i = 1; i <= numberOfImagesVariant; i++) {
                String xpathLinkImagesVariant = "(" + xpathNumberOfImagesVariant + ")[" + i + "]";
                String linkImage = getAttributeValue(xpathLinkImagesVariant, "src");
                JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
                String shopID_Curent = jsExec.executeScript("return sbShopId").toString();
                try {
                    assertThat(linkImage).contains(shopID);
                } catch (Throwable e) {
                    assertThat(linkImage).contains(shopID_Curent);
                }
            }
        }
    }

    public int getNumberOfImage() {
        String xpathNumberOfImage = "//div[@class='wrapper']//div[@class='img m-sm']";
        return countElementByXpath(xpathNumberOfImage);
    }

    public int getNumberOfArtwork() {
        String xpathNumberOfArtwork = "//div[@class='artwork-list']//div[@class='artwork']";
        numberOfArtwork = countElementByXpath(xpathNumberOfArtwork);
        return numberOfArtwork;
    }

    public void verifyArtworkLink() {
        for (int i = 1; i <= numberOfArtwork; i++) {
            String xpathArtworkLink = "(//div[@class='artwork-image']//img)[" + i + "]";
            String artworkLinh = getAttributeValue(xpathArtworkLink, "src");
            JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
            String shopId = jsExec.executeScript("return sbShopId").toString();
            assertThat(artworkLinh).contains(shopId);
        }
    }

    public void verifyImageCustomOption() {
        String xpathNumberOfImageCustomOption = "//div[contains(@class,'base-picture__element')]";
        int numberOfImageCustomOption = countElementByXpath(xpathNumberOfImageCustomOption);
        for (int i = 1; i <= numberOfImageCustomOption; i++) {
            String xpathImageCustomOptionLink = "//span[@class='wrapper-image']//img[@src]";
            String ImageCustomOptionLink = getAttributeValue(xpathImageCustomOptionLink, "src");
            JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
            String shopId = jsExec.executeScript("return window.__INITIAL_STATE__.shop.id").toString();
            try {
                assertThat(ImageCustomOptionLink).contains(shopId);
            } catch (Throwable e) {
                assertThat(ImageCustomOptionLink).contains(shopID);
            }
        }
    }

    public void clickMoreOptions() {
        String xpath = "//div[contains(@class,'title-description')]//div[@class='control-bar text-right']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void clickMapProduct(String nameApp) {
        String xpath = "//p[normalize-space()='" + nameApp + "']//following-sibling::button";
        clickOnElement(xpath);
    }

    String xPath = "//div[@role='tab' and descendant::div[normalize-space()='%s']]//following-sibling::div[@role='tabpanel']";

    public void inputNameCO(String customOption, String name) {
        String xpath = String.format(xPath, customOption) + "//div[@class='s-form-item__wrap-label' and child::label[normalize-space()='Name']]//following-sibling::div//input";
        waitClearAndType(xpath, name);
    }

    public String getValueCO(String nameCO, String name) {
        String xpath = String.format(xPath, nameCO) + "//div[child::label[contains(.,'" + name + "')]]/following-sibling::div//input";
        return getTextValue(xpath);
    }

    public void selectType(String customOption, String type) {
        String xpath = String.format(xPath, customOption) + "//div[child::label[contains(text(),'Type')]]//following-sibling::div//select";
        selectByText(xpath, type);
    }

    public String getValueTypeCOSelected(String nameCO) {
        String xpath = String.format(xPath, nameCO) + "//div[child::label[contains(text(),'Type')]]//following-sibling::div//select";
        return getValueSelected(xpath);

    }

    public String getTargetLayer(String nameCO) {
        String xpath = String.format(xPath, nameCO) + "//div[child::label[contains(text(),'Target layer')]]//following-sibling::div//select";
        return getValueSelected(xpath);
    }

    public void verifyDefaultValueTypeCOChecked(String nameCO, int ordinalNumberValue) {
        String xpath = String.format(xPath, nameCO) + "//div[child::div[normalize-space()='Value']]/following-sibling::div[" + ordinalNumberValue + "]//label//input";
        verifyElementSelected(xpath, true);
    }

    public String getValueTypeCOChecked(String nameCO, int ordinalNumberValue) {
        String xpath = String.format(xPath, nameCO) + "//div[child::div[normalize-space()='Value']]/following-sibling::div[" + ordinalNumberValue + "]//div[contains(@class,'s-form-item__content')]//input";
        return getValue(xpath);
    }

    public String getValueCheckboxTypeCO(String nameCO, int ordinalNumberValue) {
        String xpath = String.format(xPath, nameCO) + "//div[child::div[normalize-space()='Value']]//span[@class='s-tag'][" + ordinalNumberValue + "]//span";
        return getTextValue(xpath);
    }

    public void hideOption(String customOption) {
        String xpath = "//div[contains(@class,'s-sub-title') and descendant::div[normalize-space()='" + customOption + "']]//following-sibling::div//i";
        clickOnElement(xpath);
    }

    public void selectCheckbox(String customOption, String allowFollowing) {
        String xpath_check = String.format(xPath, customOption) + "//span[contains(normalize-space(),'" + allowFollowing + "')]//preceding-sibling::input";
        String xpath = String.format(xPath, customOption) + "//span[contains(normalize-space(),'" + allowFollowing + "')]//preceding-sibling::span";
        verifyCheckedThenClick(xpath_check, xpath, true);
    }

    public void enterLabelCo(String customOption, String label) {
        waitForEverythingComplete();
        waitABit(5000);
        String xPathIframe = String.format(xPath, customOption) + "//iframe";
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
        XH(xPathTinyMCE).sendKeys(label.trim());
        waitABit(1000);
        switchToIFrameDefault();
    }

    public void verifyLabelCO(String nameCO, String lableCO) {
        String xPathIframe = String.format(xPath, nameCO) + "//iframe";
        try {
            waitElementToBeVisible(xPathIframe);
        } catch (Exception e) {
            waitElementToBeVisible(xPathIframe);
        }
        switchToIFrame(xPathIframe);
        String xPathTinyMCE = "//body[@id='tinymce']/p[contains(text(),'" + lableCO + "')]";
        assertThat(isElementExist(xPathTinyMCE)).isEqualTo(true);
        switchToIFrameDefault();
    }

    public int countAllPhoto() {
        String xpath = "//div[@class='wrapper']//div[@class='img m-sm']";
        return countElementByXpath(xpath);
    }

    public int countVariantImages() {
        String xpath = "//i[@class='icon-fl-t-r mdi mdi-check-circle mdi-23px']";
        return countImageVariantByXpath(xpath);
    }

    public int countNumberOfPhotosOnStoreFront() {
        String xpath = "//div[@class='VueCarousel-inner']//div[contains(@class,'thumbnail')]";
        return countElementByXpath(xpath);
    }

    public void verifyCustomOptionInProductDetail(String sCOName, String isCO) {
        String xpath = "//div[@class=\"s-sub-title fqdHmz\" and contains(.,\"" + sCOName + "\")]";
        boolean is = false;
        if (isCO.equalsIgnoreCase("true"))
            is = true;
        assertThat(isElementExist(xpath)).isEqualTo(is);
    }

    public void inputValue(String customOption, String values) {
        String[] list = values.split(",");
        for (int i = 0; i < list.length; i++) {
            String xpath = "(//div[@role='tab' and descendant::div[normalize-space()='" + customOption + "']]//following-sibling::div[@role='tabpanel']//div[contains(@class,'s-flex--align-center') and child::div[normalize-space()='Value']]//following-sibling::div//input[@type='text'])[%d]";
            waitClearAndType(String.format(xpath, i + 1), list[i]);
        }
    }

    public void collapseCO(String customOption) {
        String xpath = "//div[contains(@class,'s-sub-title') and descendant::div[normalize-space()='" + customOption + "']]//following-sibling::div//div[@class='dvxhwQ']";
        clickOnElement(xpath);
    }

    public void addConditionalLogic(String customOption) {
        String xpath = "//div[contains(@class,'s-sub-title') and descendant::div[normalize-space()='" + customOption + "']]//following-sibling::div//span[@class='s-mr8']";
        waitUntilElementVisible(xpath, 20);
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void selectCondition(String condition) {
        String xpath = "//div[@class='d-flex']//select";
        selectDdlByXpath(xpath, condition);
    }

    public void selectValue(String value) {
        String xpath = "//div[@class='s-select f-1 ruleIndex0']//select";
        selectDdlByXpath(xpath, value);
    }

    public void selectShowValue(String showValue, int number) {
        String xpath = "//div[@class='col-xs-8 condition-then-show__body']//div[@class='col-xs-12 s-mb12'][" + number + "]//select";
        waitUntilElementVisible(xpath, 10);
        selectDdlByXpath(xpath, showValue);
    }

    public void clickSave() {
        String xpath = "//button[@class='s-button is-primary']//span[normalize-space()='Save']";
        clickOnElement(xpath);
    }

    String xpathbtn = "//button[@type and contains(.,\"%s\")]";

    // Verify show btn create preview , print file or both
    public void verifyShowBtn(String btnName, boolean bol) {
        String xpath = String.format(xpathbtn, btnName);
        assertThat(isElementExist(xpath, 50)).isEqualTo(bol);

    }

    public void clickBtnCO(String btnName) {
        String btn = String.format(xpathbtn, btnName);
        assertThat(isElementExist(btn, 50)).isEqualTo(true);
        clickOnElement(btn);
    }

    // verrify image preview or  preview
    public void verifyShowImagePersonalize(String name, boolean isShow) {
        String xpath = "//div[child::h3[contains(.,'" + name + "')]]/following-sibling::div//img";
        assertThat(isElementExist(xpath, 50)).isEqualTo(isShow);
    }

    public void clickOpenOrCloseCODetail(String sCOName, boolean bol) {
        String xpathNoActive = "//div[text()[normalize-space()='" + sCOName + "']/ancestor::div[@class='s-collapse-item draggable-item']]";
        String xpathActive = "//div[text()[normalize-space()='" + sCOName + "']/ancestor::div[@class='s-collapse-item draggable-item is-active']]";
        if (bol)
            if (isElementExist(xpathNoActive))
                clickOnElement(xpathNoActive);
            else assertThat(isElementExist(xpathActive)).isEqualTo(true);
        else if (isElementExist(xpathActive))
            clickOnElement(xpathActive);
        else assertThat(isElementExist(xpathNoActive)).isEqualTo(true);
    }

    public void clickProductAvailability(String label, boolean check) {
        String xpathStatus = "//label[descendant::span[normalize-space()='" + label + "']]//input";
        String xpathCheck = "//label[descendant::span[normalize-space()='" + label + "']]//span[@class='s-check']";
        verifyCheckedThenClick(xpathStatus, xpathCheck, check);
    }

    public String getHandleUrlPro() {
        String xpath = "//div[@class='google__url']";
        scrollToElement(xpath);
        return getText(xpath).trim();

    }

    public void inputNameVariant(String name) {
        String xpath = "//div[@class='s-form-item__content']//input[@id='option1']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndType(xpath, name);
    }

    public void clickAddSeo() {
        String xpathEditSeo = "//button[contains(@class, 'button') and normalize-space() = 'Edit website SEO']";
        clickOnElement(xpathEditSeo);
    }


    public void chooseWeightUnit(String weightUnit) {
        String xpath = "//p[contains(., 'Weight') or contains(., '重量')]//following::div[contains(@class, 'select')]//option[@value='" + weightUnit + "']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void enableGenerateSKU(boolean enable) {
        String xpathInput = "//div[@id='generationSKU-preview']//label[@class='s-switch']//input";
        String xpathSpan = "//div[@id='generationSKU-preview']//label[@class='s-switch']//span[@class='s-check']";
        verifyCheckedThenClick(xpathInput, xpathSpan, enable);
    }

    public void inputTemplate(String template) {
        String xpath = "//div[@id='generationSKU-preview']//div[@class='s-input']//input";
        waitClearAndType(xpath, template);
    }

    public void clickSetting(String setting, boolean check) {
        String xpathInput = "//div[@id='generationSKU-preview']//label[@class='s-checkbox']//span[contains(normalize-space(),'" + setting + "')]//preceding-sibling::input";
        String xpathSpan = "//div[@id='generationSKU-preview']//label[@class='s-checkbox']//span[contains(normalize-space(),'" + setting + "')]//preceding-sibling::span";
        verifyCheckedThenClick(xpathInput, xpathSpan, check);
    }

    public String getListImage() {
        String xpath = "//div[contains(@class,'section-image')]//img";
        int count = countElementByXpath(xpath);
        String listIamge = null;
        for (int i = 1; i <= count; i++) {
            xpath = "(//div[contains(@class,'section-image')]//img)[%d]";
            String linkImg = getAttributeValue(String.format(xpath, i), "src");
            String img = linkImg.substring(linkImg.lastIndexOf("@") + 1);
            if (listIamge == null) {
                listIamge = img;
            } else {
                listIamge = listIamge + "," + img;
            }
        }
        return listIamge;
    }

    public int getListMedia() {
        String xpath = "//div[contains(@class,'section-image')]//div[contains(@class,'media__container')]";
        return countElementByXpath(xpath);
    }

    public void inputInventoryPolicy(String policy) {
        String xPath = "//div[child::div[normalize-space()='Inventory policy' or normalize-space()='库存政策']]//following-sibling::div//select";
        waitElementToBeVisible(xPath);
        selectDdlByXpath(xPath, policy);
    }

    public void enterQuantity(String quantity) {
        String xPath = "//div[@class='s-form-item']//input[@id='quantity']";
        waitClearAndType(xPath, quantity);
    }

    public void selectAllow(String allow) {
        String xpathInput = "//div[contains(@class,'title-description') and child::h3[normalize-space()='Inventory']]//input[@type='checkbox']";
        String xpath = "//div[contains(@class,'title-description') and child::h3[normalize-space()='Inventory']]//span[@class='s-check']";
        verifyCheckedThenClick(xpathInput, xpath, Boolean.parseBoolean(allow));
    }

    public void enterWeight(String weight) {
        String xPath = "//p[normalize-space()='Weight' or normalize-space()='重量']//following-sibling::div//input";
        waitClearAndType(xPath, weight);
    }

    public void navigateToCollectionScreen(String tab) {
        String xPath = "//ul[@class='menu_level_1']//li//*[text()[normalize-space()='" + tab + "']]";
        String xPathSearch = "//input[@type='text' and @placeholder='Search collections']";
        String xPathName = "//a[@href ='/admin/products']/preceding::a[@href ='/admin/products']";
        clickOnElement(xPath);
        waitElementToBeVisible(xPathName);
        waitForEverythingComplete();
        waitElementToBeVisible(xPathSearch);
    }

    public void enterImageURL(String imagesURL) {
        String xpath = "//div[child::label[text()='Paste media URL' or text()='Paste image URL' or text()='粘贴图片网址']]//following-sibling::div//input";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, imagesURL);
    }

    public void clickSaveChanges() {
        String xPath = "//*[text()[normalize-space()='Save changes' or normalize-space()='保存更改']]";
        if (isElementVisible(xPath, 15)) {
            clickOnElement(xPath);
        }
        waitForEverythingComplete();
        waitABit(5000);
    }

    public void verifyTitle(String nameProduct) {
        String xPath = "((//*[child::*[contains(text(),'Title')]]|//*[contains(text(),'Title')]|//*[contains(text(),'标题')])/following-sibling::div//input)[1]";
        waitElementToBeVisible(xPath);
        assertThat(getValue(xPath)).isEqualTo(nameProduct);
    }

    public void selectImageOnPictureChoice(String imageName) {
        String xpath = "//label[normalize-space()='Drop image to upload']//input";
        chooseMultipleAttachmentFiles(xpath, imageName);
    }

    public void addNewOptionOnCondtionalLogic() {
        String xpath = "//span[normalize-space()='Add new option']";
        clickOnElement(xpath);
    }

    public void verifyConditionalLogicAfDuplicate(String customName, String customeNameCondition, boolean isShow) {
        String xpath = "//div[contains(@class,'s-sub-title fqdHmz')]//div[text()='" + customName + "']/..//span[text()='Parent: " + customeNameCondition + "']";
        verifyElementPresent(xpath, isShow);
    }

    public void inputValueInChekboxConditional(String valueCheckbox) {
        String[] list = valueCheckbox.split(",");
        for (int i = 0; i < list.length; i++) {
            String xpath = "//input[@placeholder='Separate options with comma']";
            waitClearAndTypeThenEnter(String.format(xpath, i + 1), list[i]);
        }
    }

    public void clickDeleteIcon(String qty) {
        String xpath = "//i[contains(@class,'icon-big cursor-pointer]";
        for (int i = 1; i <= Integer.parseInt(qty); i++) {
            String xpathImage = "(" + xpath + ")[" + i + "]";
            clickOnElement(xpathImage);
        }
        waitForPageLoad();
    }

    public void clickDeleteCO() {
        String xpath = "//div[@class='s-collapse-item__content']//i[contains(@class,'mdi-delete')]";
        clickOnElement(xpath);
    }

    public void clickToExpandFilterOptionsInHiveSbase() {
        String xpath = "//div[@class='show-more-filter']//a";
        clickOnElement(xpath);
    }

    public void inputShopIDInHive(String shopID) {
        String xpath = "//input[@placeholder='Enter shop id here, separate by comma and not space']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndType(xpath, shopID);
    }

    public void clickToPlatformType(String platformType) {
        String xpath = "//input[@type='checkbox']//following-sibling::span[normalize-space()='" + platformType + "']";
        waitUntilElementVisible(xpath, 10);
        clickOnElement(xpath);
    }

    public void verifyTotalProductsInProductModerationListPage(String totalProduct) {
        String xpath = "//div[@class='product-item-info row']";
        int count = countElementByXpath(xpath);
        assertThat(count).isEqualTo(Integer.parseInt(totalProduct));
    }

    public void verifyListProductsInProductModerationListPage(String listProductExpect) {
        String xPath = "//div[@class='product-grid-header row' and descendant::p[normalize-space()='Title']]//following-sibling::div//div//div[3]//p";
        List<String> listProductAct = getListText(xPath);
        assertThat(listProductAct).isEqualTo(Arrays.asList(listProductExpect.split(",")));
    }

    public void verifyDisableEditCampPhhub(String isDisable, String _sCOName) {
        String xpath[] = new String[4];
        //Name CO
        xpath[0] = String.format(xPath, _sCOName) + "//div[@class='s-form-item__wrap-label' and child::label[normalize-space()='Name']]//following-sibling::div//input";
        //Label CO
        xpath[1] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Label')]]//following-sibling::div//input";
        //Type CO
        xpath[2] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Type')]]//following-sibling::div//select";
        //Targetlayer
        xpath[3] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Target layer')]]//following-sibling::div//select[@disabled]";

        for (int i = 0; i < xpath.length; i++) {
            assertThat(getAttributeValue(xpath[i], "disabled")).isEqualTo(isDisable);
        }
    }

    public void verifyDisableAllowCharacter(String isDisable, String _sCOName) {
        String xpath[] = new String[4];
        xpath[0] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Allow the following characters')]]//following-sibling::div//*[@value='characters']/parent::label";
        xpath[1] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Allow the following characters')]]//following-sibling::div//*[@value='numbers']/parent::label";
        xpath[2] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Allow the following characters')]]//following-sibling::div//*[@value='symbol']/parent::label";
        xpath[3] = String.format(xPath, _sCOName) + "//div[child::label[contains(text(),'Allow the following characters')]]//following-sibling::div//*[@value='emoji']/parent::label";

        for (int i = 0; i < xpath.length; i++) {
            assertThat(getAttributeValue(xpath[i], "disabled")).isEqualTo(isDisable);
        }
    }

    public void verifyDisableCOradio(String isDisable, String values, String _sCOName) {
        String[] list = values.split(",");
        String xpath = "(//div[@role='tab' and descendant::div[normalize-space()='" + _sCOName + "']]//following-sibling::div[@role='tabpanel']//div[contains(@class,'s-flex--align-center') and child::div[normalize-space()='Value']]//following-sibling::div//input[@type='text'])[%d]";
        for (int i = 1; i <= list.length; i++) {
            xpath = String.format(xpath, i);
            assertThat(getAttributeValue(xpath, "disabled")).isEqualTo(isDisable);
        }
    }

    public void verifyDisableCheckboxCO(String isDisable) {
        String xpath = "//input[@placeholder='Separate options with comma']";
        assertThat(getAttributeValue(xpath, "disabled")).isEqualTo(isDisable);
    }

    public void verifyDisablePictureChoice() {
        String xpath = "//label[normalize-space()='Drop image to upload']//input";
        assertThat(isElementExist(xpath)).isEqualTo(false);
    }

    public void verifyButtonPersonalization(String buttonName) {
        String xpath = "//button[child::span[normalize-space()='" + buttonName + "']]";
        verifyElementVisible(xpath, true);
    }

    public void clickCreatePersonalization() {
        String xpath = "//button[child::span[normalize-space()='Create personalization']]";
        clickOnElement(xpath);
        waitForEverythingComplete();
        waitABit(5000);
    }

    public void clickEditWebsiteSEO() {
        String xpath = "//section[@class='card search-engine']//button";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public String getImageVariant() {
        String xpathImageLink = "//div[@class='modal-body-container']//div[@class='img m-sm selected'][1]//img";
        String linkImage = getAttributeValue(xpathImageLink, "src");
        return linkImage.substring(linkImage.lastIndexOf("@") + 1);
    }

    public void clickSaveImage() {
        String xpath = "//div[@class='s-modal-footer']//button[@class='s-button is-primary']//span[normalize-space()='Save']";
        clickOnElement(xpath);
    }

    public void selectImageVariant(String variant, String positionImage) {
        String[] optionValue = variant.split(",");
        int multi = optionValue.length;
        String xpath = "";
        String xpath_select = "//div[@class='modal-body-container']//div[@class='img m-sm'][" + Integer.parseInt(positionImage) + "]";
        if (multi == 1) {
            xpath = "//tr[child::td[normalize-space()='" + optionValue[0] + "']]//img";
        } else if (multi == 2) {
            xpath = "//tr[child::td[normalize-space()='" + optionValue[0] + "'] and child::td[normalize-space()='" + optionValue[1] + "']]//img";
        } else if (multi == 3) {
            xpath = "//tr[child::td[normalize-space()='" + optionValue[0] + "'] and child::td[normalize-space()='" + optionValue[1] + "'] and child::td[normalize-space()='" + optionValue[2] + "']]//img";
        }
        clickOnElement(xpath);
        clickOnElement(xpath_select);
    }

    public void clickAddProductButton() {
        clickBtn("Add product");
    }

    public void verifyProductsInProductModerationListPage(int number) {
        String xpath = "//div[@class='product-item-info row']";
        int count = countElementByXpath(xpath);
        assertThat(count).isEqualTo(number);
    }

    public int getDateCurrent() {
        return instance.get(Calendar.DATE);
    }

    public int getMonthCurrent() {
        return instance.get(Calendar.MONTH);
    }

    public int getYearCurrent() {
        return instance.get(Calendar.YEAR);
    }

    public boolean getLeapYear(int y) {
        if (((y % 4 == 0 && y % 100 != 0) || y % 400 == 0))
            return true;
        return false;
    }
    public int countDaysOfMonth(int m, int y) {
        int countDays = 0;
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                countDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                countDays = 30;
                break;
            case 2:
                if (getLeapYear(y))
                    countDays = 29;
                else
                    countDays = 28;
                break;

        }
        return countDays;
    }

    public void inputCreatedFrom() {
        int date = getDateCurrent();
        int month = getMonthCurrent();
        int year = getYearCurrent();
        String inputTimeCurrent = date + "/" + month + "/" + year;
        String xpath = "//input[@id='createFrom' or @name='createFrom']";
        waitClearAndTypeThenEnter(xpath, inputTimeCurrent);
    }

    public String getNextDate() {
        int d = getDateCurrent();
        int m = getMonthCurrent();
        int y = getYearCurrent();
        int nd = d, nm = m, ny = y;
        if (y > 0 && m > 0 && m < 12 && d > 0 && d <= (countDaysOfMonth(m, y))) {
            nd = d + 1;
            if (m != 12 && d == countDaysOfMonth(m, y)) {
                nd = 1;
                nm = m + 1;
            } else if (m == 12 && d == countDaysOfMonth(m, y)) {
                nd = 1;
                ny = y + 1;
                nm = 1;
            } else if (m == 2) {
                if (getLeapYear(y)) {
                    if (d == 29) {
                        nd = 1;
                        nm = 3;
                    }
                } else {
                    if (d == 28) {
                        nd = 1;
                        nm = 3;
                    }
                }
            }
        }
        String nextDate = nd + "/" + nm + "/" + ny;
        return nextDate;

    }

    public void inputCreatedTo() {
        String inputCreatedto = getNextDate();
        String xpath = "//input[@id='createTo' or @name='createTo']";
        waitClearAndTypeThenEnter(xpath, inputCreatedto);
    }
    public void searchProductByNamePlus(String sProducts) {
        String xpath = "//input[@placeholder='Search by product name']";
        waitUntilElementVisible(xpath, 10);
        waitClearAndTypeThenEnter(xpath, sProducts);
        waitForEverythingComplete();
    }
    public void selectProductPlusInCatalog(String sProducts) {
        String xpath="//div[normalize-space()='"+sProducts+"']//preceding-sibling::div[@title='Select this item to import to store']//div[@class='sb-image__select sb-absolute sb-text-center sb-d-inline-block sb-image__select--selected']";
        clickOnElement(xpath);
    }
    public void inputTitlePlus(String sProduct, String title) {
        String xPath = "//input[@placeholder='"+sProduct+"']";
        waitElementToBeVisible(xPath);
        clearValueByJS(xPath);
        waitClearAndType(xPath, title);
    }

    public void selectPaymentStatusInHive(String paymentStatus) {
        String xpath = "(//select[@data-sonata-select2='false'])[2]";
        selectDdlByXpath(xpath, paymentStatus);
    }
}