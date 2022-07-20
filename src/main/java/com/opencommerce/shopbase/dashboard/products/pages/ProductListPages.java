package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductListPages extends SBasePageObject {
    private int MAX_RETRY_TIME = 15;
    private String xpathSubject;

    public ProductListPages(WebDriver driver) {
        super(driver);
    }

    public void clickBtnAddProduct() {
        String xpath = "//div[contains(@class,'action-header')]//*[text()[normalize-space()='Add product' or normalize-space()='添加商品']]";
        if (isElementExist(xpath)) {
            waitElementToBeClickable(xpath);
            clickOnElement(xpath);
        }

    }

    public boolean searchProduct(String nameProduct) {
        String xpathTable = "//table[@id='all-products']";
        String xPathSearch = "//input[@placeholder='Search products']|//input[@placeholder='Search variants']|//input[@placeholder='Search campaigns by name']|//input[@placeholder='搜索商品']";
        waitElementToBeVisible(xPathSearch);
        waitElementToBeVisible(xpathTable);
        waitClearAndTypeThenEnter(xPathSearch, nameProduct);
        String xpathLoading = "//div[@class='table-wapper']//div[@class='s-loading-table']";
        waitForElementNotPresent(xpathLoading);
        waitProduct(0);
        return isElementExist("//table[@id='all-products']//tbody//tr[descendant::*[text()='" + nameProduct + "']]");
    }

    public void waitProduct(int currentRetryTime) {
        String xpathTable = "//table[@id='all-products']";
        try {
            waitElementToBeVisible(xpathTable);
        } catch (Exception e) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                waitABit(10000);
                waitProduct(currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }
    }

    public void clickProductName(String productName) {
        String xPath = "//div[normalize-space()='" + productName + "']";
        waitElementToBeVisible(xPath);
        clickOnElement("//div[normalize-space()='" + productName + "']");
        waitForPageLoad();
        refreshPage();
    }

    public boolean isNoDataFound() {
        String xpath = "//p[text()='Could not find any products matching']";
        return isElementExist(xpath);
    }

    public void verifyProductNotFound() {
        assertThat(isNoDataFound()).isEqualTo(true);
    }

    public void selectAllProductCrossPage() {
        String xPathStatus = "//span[contains(@data-label,'Select all') or contains(@data-label,'选择所有商品')]//input";
        String xPath = "//span[contains(@data-label,'Select all') or contains(@data-label,'选择所有商品')]//span[contains(@class,'check')]";
        String xPath_all = "//span[contains(text(),'Select all') or contains(@data-label,'选择所有商品')]";
        verifyCheckedThenClick(xPathStatus, xPath, true);
        if (isElementExist(xPath_all)) {
            waitForPageLoad();
            clickOnElementByJs(xPath_all);
            waitABit(3000);
        }
    }

    public void selectAllProduct() {
        String xPathStatus = "//span[contains(@data-label,'Select all')]//input";
        String xPath = "//span[contains(@data-label,'Select all')]//span[contains(@class,'check')]";
        verifyCheckedThenClick(xPathStatus, xPath, true);
    }

    public void chooseAction(String action) {
        String xPath = "//*[contains(@class,'dropdown-item') and normalize-space()='" + action + "']";
        waitUntilElementVisible(xPath, 15);
        clickOnElement(xPath);
        waitABit(1000);
    }


    public void chooseActionDeleteProduct() {
        String xpath = "//*[contains(@class,'dropdown-item') and (normalize-space()='Delete selected products' or normalize-space()='删除所选商品')]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void verifyNoDataFound() {
        assertThat(isNoDataFound()).isEqualTo(true);
    }

    public void verifyNoProductFound() {
        String xpath = "//div[@class='text-center color-gray']//p[normalize-space()='Could not find any products matching' or normalize-space()='找不到匹配的商品']|//h4[normalize-space()='3 ways to add your first products']|//div[contains(@class,'no-product-content')]";
        int index = 1;
        while (!isElementVisible(xpath, 3) && index <= 20) {
            refreshPage();
            index ++;
        }
        waitElementToBeVisible(xpath);
        verifyElementVisible(xpath, true);
    }

    public boolean hasProduct() {
        String xpath = "//tbody//div[@class='product-name']";
        return isElementVisible(xpath, 5);
    }

    public boolean importEnoughProduct(String totalExpect) {
        String xPathStatus = "//span[@data-label='Select all products']//input";
        String xPath = "//span[@data-label='Select all products']//span[contains(@class,'check')]";
        String xPath_all = "//span[contains(text(),'Select all')]";
        String totalActual;
        while (!hasProduct()) {
            refreshPage();
            waitForPageLoad();
        }
        verifyCheckedThenClick(xPathStatus, xPath, true);
        if (isElementExist(xPath_all)) {
            totalActual = getTextValue(xPath_all);
        } else {
            totalActual = getTextValue("//span[contains(text(),'products selected') or contains(text(),'product selected')]");
        }
        return totalActual.contains(totalExpect);
    }

    public void clickBtnCancel() {
        String xpath = "//button[@class='s-button is-outline']";
        String xpath_error = "//button[@class='s-button btn-confirm-delete is-primary']";
        if (isElementExist(xpath_error)) {
            clickOnElement(xpath_error);
        } else {
            clickOnElement(xpath);
        }
    }

    public void clickBtnClose() {
        String xpath = "//button[@class='s-modal-close is-large']";
        clickOnElement(xpath);
    }

    public void clickBtnImport() {
        String xPath = "//button[@type='button']//descendant::span[contains(text(),'Import')]";
        clickOnElement(xPath);
    }

    public void clickBtnImportPB() {
        String xPath = "(//button[@type='button']//descendant::span[contains(text(),'Import')])[2]";
        clickOnElement(xPath);
        waitUntilElementInvisible(xPath, 20);
    }

    public void clickUploadFile() {
        String xpathDuplicate = "//p[normalize-space()='Duplicate file import']";
        String xpathUpload = "//span[contains(text(),'Upload File')]";
        waitUntilElementVisible(xpathUpload, 30);
        clickOnElement(xpathUpload);
        waitForEverythingComplete(30);
        if (isElementExist(xpathDuplicate)) {
            clickBtn("Upload file anyway");
        }
        waitForEverythingComplete();
    }

    public void verifyContentOfMailImportProduct(String content) {
        waitForElementToPresent("//iframe[@id='html_msg_body']");
        switchToIFrame("//iframe[@id='html_msg_body']");
        String xPath = "//h1[normalize-space()='" + content + "']";
        verifyElementVisible(xPath, true);
    }

    public void verifyContentOfMailImportProductError(String error) {
        waitForElementToPresent("//iframe[@id='html_msg_body']");
        switchToIFrame("//iframe[@id='html_msg_body']");
        String xPath = "//p[contains(text(),'" + error + "')]";
        verifyElementVisible(xPath, true);
    }

    public void verifyTitleProductList(String productTitle) {
        String xPath = "(//div[@class='product-name'])[1]";
        String productName = XH(xPath).getText().trim();
        assertThat(productName).isEqualTo(productTitle);
    }

    public void chooseFileCSV(String fileName) {
        String xpath = "//input[@type='file' and @accept='.zip, .csv']";
        chooseAttachmentFile(xpath, fileName);
    }

    public void verifyMessage(String errorMessage) {
        String xPath_ErrorMessage = "//div[@class='s-media-content']";
        String xPath_header = "//p[@class='s-modal-card-title' and normalize-space()='CSV errors found']";
        if (isElementExist(xPath_header)) {
            verifyElementContainsText(xPath_ErrorMessage, errorMessage);
        }
    }

    public void selectAllProductNextPage() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        String xPathCheckbox_2 = "//div[@class='action-table']//span[@class='s-check']";
        jse.executeScript("scroll(250, 0)");
        clickOnElement(xPathCheckbox_2);
        waitForEverythingComplete();
    }

    public void nextPage() {
        String xPathNext = "//a[@class='s-pagination-next']//span";
        clickOnElement(xPathNext);
        waitForEverythingComplete();

    }

    public void clickExport() {
        String xPath = "//div[@id='export-product-modal']//button[@class='s-button is-primary']";
        clickOnElement(xPath);
    }

    public void verifyMessageExportSuccess(String msg) {
        waitForTextToAppear(msg, 10000);
    }

    public void verifyReceivedMail(String emailAddress, String subject) {
        boolean check = openMailBox(emailAddress, subject);
        int retryTimes = 0;
        while (!check && retryTimes <= MAX_RETRY_TIME) {
            {
                refreshPage();
                String xpath = "//button[normalize-space()='GO']";
                waitUntilElementVisible(xpath, 20);
                clickOnElement(xpath);
                retryTimes++;
                waitABit(12000);
                check = openMailBox(emailAddress, subject);
            }
        }
        assertThat(check).isTrue();
        clickOnElement(xpathSubject);
        waitForEverythingComplete();
    }

    public void openSubjectMail(String subject) {
        xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";
        boolean check = isElementExist(xpathSubject);
        int retryTimes = 0;
        while (!check && retryTimes <= MAX_RETRY_TIME) {
            {
                refreshPage();
                String xpath = "//button[normalize-space()='GO']";
                waitUntilElementVisible(xpath, 20);
                clickOnElement(xpath);
                retryTimes++;
                waitABit(12000);
                check = isElementExist(xpathSubject);
            }
        }
        clickOnElement(xpathSubject);
        waitForEverythingComplete();
    }

    public boolean openMailBox(String emailAddress, String subject) {
        openUrl("https://www.mailinator.com");
        String xpathEmail = "(//input[contains(@placeholder,'Enter Public Mailinator Inbox')])[1]";
        xpathSubject = "(//*[contains(text(),'" + subject + "')])[1]";
        String xpathTime = "//tbody//td[contains(text(),'min') or contains(text(),'just now') or contains(text(),'hour')]";

        WebElement inputTextbox = getDriver().findElement(By.xpath(xpathEmail));
        inputTextbox.clear();
        inputTextbox.sendKeys(emailAddress);
        inputTextbox.sendKeys(Keys.ENTER);

        verifyElementVisible(xpathTime, true);
        return isElementVisible(xpathSubject, 10);
    }

    public void loginMailBox(String emailAddress) {
        openUrl("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddress);
    }

    public void verifyContentOfMailExportProduct() {
        waitForElementToPresent("//iframe[@id='html_msg_body']");
        switchToIFrame("//iframe[@id='html_msg_body']");
        String xPath = "//h1[normalize-space()='Finish exporting a product list.']";
        String xPath1 = "//a[text()='Download']";
        verifyElementVisible(xPath, true);
        verifyElementVisible(xPath1, true);
    }

    public void waitProductListingPageDisplayed() {
        waitUntilElementVisible("//table[@id='all-products']//tbody", 20);
        refreshPage();
    }

    public void verifyMessageDeleteProduct(String message) {
        waitForTextToAppear(message);
    }

    public void verifyContentOfMail(String content) {
        waitForElementToPresent("//iframe[@id='msg_body']");
        switchToIFrame("//iframe[@id='msg_body']");
        String xPath = "//td//p[normalize-space()=\"" + content + "\"]";
        verifyElementVisible(xPath, true);
    }

    public void selectTab(String tab) {
        String xpath = "//span[@class='text-center']//p[normalize-space()='" + tab + "']";
        waitUntilElementVisible(xpath, 20);
        clickOnElement(xpath);
        waitABit(5000);
        refreshPage();
    }

    public void verifyListProduct(String listProduct) {
        refreshPage();
        String xPath = "//div[@class='product-name']";
        List<String> listProductActual = getListText(xPath);
        assertThat(listProductActual).isEqualTo(Arrays.asList(listProduct.split(",")));
    }

    public void verifyStatus(String product, String status) {
        if (status.contains("Available")) {
            status = "";
        }
        String xPath = "//div[@class='product-name' and descendant::span[normalize-space()='" + product + "']]//following-sibling::div";
        verifyElementText(xPath, status);
        waitABit(1000);
    }

    public void selectProduct(String title) {
        String xPath = "//td[@class='cursor-default no-padding-important' and descendant::span[normalize-space()='" + title + "']]//preceding-sibling::td//input";
        clickOnElementByJs(xPath);
    }

    public void verifyTotalProduct(String total) {
        String xPath = "//span[@class='color-gray-draker type--bold']";
        assertThat(getText(xPath)).isEqualTo(total);
    }

    public void unSelectProduct(int numberOfProduct) {
        for (int i = 1; i <= numberOfProduct; i++) {
            String xPath = "//table[@id='all-products']//tr[" + i + "]//td[@class='cursor-default no-padding-important' and descendant::span]//preceding-sibling::td//input";
            clickOnElementByJs(xPath);
            waitABit(1000);
        }
    }

    public int countProductByProductName(String title) {
        String xpath = "//table[@id='all-products']//tbody//tr[descendant::*[normalize-space()=\"" + title + "\"]]";
        return countElementByXpath(xpath);
    }

    public void verifyProductInforInProductList(String productName, String label, String value) {
        String xPathCol = "//table[@id='all-products']//thead/tr/th[descendant-or-self::*[normalize-space()='" + label + "']]/preceding-sibling::th";
        int colNumber = countElementByXpath(xPathCol) + 1;
        String xpath = "(//table[@id='all-products']//tbody//tr[descendant::*[normalize-space()=\"" + productName + "\"]])[1]//td[" + colNumber + "]";
        verifyElementText(xpath, value);
    }

    public void inputTags(String tags) {
        String xPath = "//div[@class='s-input']//input[@placeholder='Vintage, cotton, summer']";
        waitTypeAndEnter(xPath, tags);
    }

    public void addCollection(String collection) {
        String xPath = "//div[@class='s-mb8 s-mt8 s-input']//input[@placeholder='Search for collections']";
        waitTypeAndEnter(xPath, collection);
    }

    public void selectCollection(String collection) {
        String xPath = "//div[text()='" + collection + "']//preceding-sibling::label//span[@class='s-check']";
        clickOnElementByJs(xPath);
    }

    public void filterByValue(String filter, String value) {
        String xPath = "//div[@class='sidebar-filter__item s-w100']//p[normalize-space()='" + filter + "']";
        String xPath_input_value = "//div[@role='tab' and descendant::p[normalize-space()='" + filter + "']]//following-sibling::div[@role='tabpanel']//input";
        String xPath_value = "//div[@class='s-dropdown-menu is-opened-bottom']//span[contains(normalize-space(),'" + value + "')]";

        waitElementToBePresent(xPath);
        clickOnElementByJs(xPath);
        waitABit(1000);
        clickOnElement(xPath_input_value);
        waitUntilElementVisible(xPath_value, 10);
        clickOnElementByJs(xPath_value);
    }

    public void filterByConditionValue(String filter, String condition, String value) {
        String xPath = "//div[@class='sidebar-filter__item s-w100']//p[normalize-space()='" + filter + "']";
        String xPath_child = "//div[@role='tab' and descendant::p[normalize-space()='" + filter + "']]//following-sibling::div[@role='tabpanel']//span[normalize-space()='" + condition + "']//preceding-sibling::span";
        String xPath_value = "//div[@role='tab' and descendant::p[normalize-space()='" + filter + "']]//following-sibling::div[@role='tabpanel']//input[@class='s-input__inner']";

        clickOnElementByJs(xPath);
        waitABit(1000);
        clickOnElement(xPath_child);
        waitABit(1000);
        inputSlowly(xPath_value, value);
    }

    public void filterByCondition(String filter, String condition) {
        String xPath = "//div[@class='sidebar-filter__item s-w100']//p[normalize-space()='" + filter + "']";
        String xPath_condition = "//div[@role='tab' and descendant::p[normalize-space()='" + filter + "']]//following-sibling::div[@role='tabpanel']//span[normalize-space()=\"" + condition + "\"]//preceding-sibling::span";
        waitElementToBePresent(xPath);
        clickOnElementByJs(xPath);
        waitABit(1000);
        clickOnElement(xPath_condition);
    }

    public void confirmPassDelete(String pass) {
        String xPath = "//div[@class='s-form-item__content']//input";
        if (isElementExist(xPath)) {
            waitTypeAndEnter(xPath, pass);
            waitForPageLoad();
        }
    }

    public void clickBtnStatusImport() {
        String xPath = "//div[@class='s-dropdown-trigger']//button[@class='s-button is-outline ocean is-default']";
        clickOnElement(xPath);
        waitForPageLoad();
    }

    public void verifyStatusImport(String nameFile, String status) {
        String xPath = "//div[descendant::span[contains(.,'" + nameFile + "')]]//following-sibling::div//span[@class='']";
        assertThat(getTextValue(xPath)).isEqualTo(status);
    }

    public void verifyImport(String nameFile, String information) {
        String[] list = information.split(",");
        for (String info : list) {
            String xPath = "//div[@class='row s-pt16 border-top' and descendant::span[contains(text(),'" + nameFile + "')]]//following-sibling::div[contains(.,'" + info + "')]";
            isElementExist(xPath);
        }
    }

    public void verifyProductStoreImport() {
        String xpath = "//div[@class='product-block m-b-md']";
        verifyElementPresent(xpath, true);
    }

    //Clone Product by Duc Dao
    public static int quantity;
    public String xpathCheckboxAll = "//span[contains(@data-label,'Select all')]//span[@class='s-check']";
    public String xpathCheckboxAllStatus = "//span[contains(@data-label,'Select all')]//input";
    public String xpathActionButton = "//button[child::span[normalize-space()='Action']]";
    public String buttonImportProcess = "//button[@id='icon-process']";
    public String sourceShop = "";
    public String firstShop = LoadObject.getProperty("firstShop");

    public void selectProducts(String numberOfProduct) {
        quantity = Integer.parseInt(numberOfProduct);
        for (int i = 1; i <= quantity; i++) {
            String xpathCheckbox = "(//table[@id='all-products']//tbody//span[@class='s-check'])[" + i + "]";
            String xpathCheckboxStatus = "(//table[@id='all-products']//tbody//input)[" + i + "]";
            verifyCheckedThenClick(xpathCheckboxStatus, xpathCheckbox, true);
        }
    }

    public void clickActionButton() {
        waitElementToBeVisible(xpathActionButton);
        clickOnElement(xpathActionButton);
    }

    public void clickImportProducts(String typeShop) {
        String xpathImportAction = "//div[@class='s-dropdown-menu']//span[normalize-space()='Import " + typeShop + " to another store']";
        waitElementToBeVisible(xpathImportAction);
        clickOnElement(xpathImportAction);
    }

    public void chooseAStore(String desStore) {
        String xpathButtonChooseStore = "//div[@class='s-modal is-active']//div[@id='select-shops']//button[@type='button']";
        waitElementToBeVisible(xpathButtonChooseStore);
        clickOnElement(xpathButtonChooseStore);
        String xpathDestinationShop = "//span[@class='s-dropdown-item']//p[text()='" + desStore + "']";
        waitElementToBeVisible(xpathDestinationShop);
        clickOnElement(xpathDestinationShop);
    }

    public void clickImport() {
        String xpathClickImportButton = "(//button[@class='s-button is-primary']//span[@class='s-flex s-flex--align-center'])[2]";
        waitElementToBeVisible(xpathClickImportButton);
        clickOnElement(xpathClickImportButton);
        waitForElementNotAppear(xpathClickImportButton);
    }

    public void selectShop(String DashboardShop) {
        String xpathSelectDesShop = "//ul[@class='ui-login-domain-platform']//li[@class='shopify']//a[@href='/open/?shop_data=" + DashboardShop + ".stag.myshopbase.net&shop_name=" + DashboardShop + "&id_location=custom_en']";
        waitElementToBeVisible(xpathSelectDesShop);
        clickOnElement(xpathSelectDesShop);
    }

    public void verifyImportProcess(String status, int currentRetry) {
        refreshPage();
        closePopup();
        waitElementToBeVisible(buttonImportProcess);
        clickOnElement(buttonImportProcess);
        String ProcessStatus = "(//span[@class='s-dropdown-item']//span[contains(@class,'status-tag')])[1]";
        String actualStatus = getText(ProcessStatus).trim();
        boolean check = actualStatus.equals(status);
        while (!check && currentRetry <= MAX_RETRY_TIME) {
            waitABit(5000);
            refreshPage();
            currentRetry++;
            check = actualStatus.equals(status);
        }
        clickOnElement(buttonImportProcess);
    }

    public void verifyProductQuantityOfDesShop(String actualQuantity) {
        refreshPage();
        verifyCheckedThenClick(xpathCheckboxAllStatus, xpathCheckboxAll, true);
        String xpathProductQuantity = "//span[@class='s-control-label']//span[@class='color-gray-draker type--bold']";
        String productSelected = getText(xpathProductQuantity);
        String allProduct = productSelected.split(" ")[0];
        assertThat(allProduct).isEqualTo(actualQuantity);
    }

    public void deleteAllProducts() {
        String xpathDeleteProduct = "//div[@class='s-dropdown-menu']//div[@class='s-dropdown-content']//span[normalize-space()='Delete selected products']";
        String xpathDeleteButton = "//button[@class='s-button btn-confirm-delete is-danger']";
        if (isElementVisible(xpathCheckboxAll, 3)) {
            verifyCheckedThenClick(xpathCheckboxAllStatus, xpathCheckboxAll, true);
            waitElementToBeVisible(xpathCheckboxAll);
            clickOnElement(xpathActionButton);
            waitElementToBeVisible(xpathActionButton);
            clickOnElement(xpathDeleteProduct);
            clickOnElement(xpathDeleteButton);
        }
        waitFor(5);
        refreshPage();
        verifyElementVisible(xpathCheckboxAll, false);
    }

    public void verifyImportProcess() {
        String importProductTitle = "Import Products";
        refreshPage();
        waitElementToBeVisible(buttonImportProcess);
        clickOnElement(buttonImportProcess);
        sourceShop = firstShop;
        String xpathSourceShop = "(//span[@class='s-dropdown-item']//span[contains(.,'From')])[1]";
        String xpathImportProducts = "(//span[@class='s-dropdown-item']//div[@class='text-bold text-capitalize'])[1]";
        String sourceShopCurrent = getText(xpathSourceShop);
        String importProductTitleActual = getText(xpathImportProducts);
        String sourceShopActual = sourceShopCurrent.split(" ")[1];
        assertThat(sourceShopActual).isEqualTo(sourceShop);
        assertThat(importProductTitleActual).isEqualTo(importProductTitle);
    }

    public void clearFilter() {
        String xpath = "//div[@class='d-flex flex-wrap']//span[@class='s-tag']//i";
        List<WebElementFacade> listTags = findAll(By.xpath(xpath));
        for (WebElementFacade element : listTags) {
            scrollIntoElementView(element).click();
        }
    }

    public void clickOnTab(String nameTab) {
        String xpath = "//span[@class='text-center']//p[text()='" + nameTab + "']";
        clickOnElement(xpath);
        waitForEverythingComplete();
    }

    public void filterCondition(String condition) {
        String xpathCondition = "//p[contains(text(),'" + condition + "')]";
        scrollToElement(xpathCondition);
        clickOnElement(xpathCondition);
    }

    public void filterSubCondition(String subCondition) {
        String xpath = "//input[@name='mapped_product']//parent::label//span[contains(text(),'" + subCondition + "')]";
        scrollToElement(xpath);
        clickOnElement(xpath);
    }

    public void enterNameTemplateFilter(String nameTemplateFilter) {
        String xpath = "//div[contains(@class,'popover-activated')]//input";
        waitClearAndTypeThenEnter(xpath, nameTemplateFilter);
    }

    public void verifyDisplayTemplateNew(String templateName) {
        String xpath = "//p[text()='" + templateName + "']";
        verifyElementVisible(xpath, true);
    }

    public void verifyDateFilter(String name) {
        String xpath = "//div[@class='product-name']//span[text()='" + name + "']";
        verifyElementVisible(xpath, true);
    }

    public void clickLink(String action) {
        String xpath = "//div[@class='product-name']//span[text()='" + action + "']";
        clickOnElement(xpath);
    }

    public String getNamePage() {
        String xpath = "//h2[@class='warehouse__heading__warehouse__title']";
        return getText(xpath);
    }

    public void clickActionEdit() {
        String xpath = "//i[contains(@class,'mdi-pencil')]";
        clickOnElement(xpath);
    }

    public void clickActionDelete() {
        String xpath = "//i[contains(@class,'mdi-delete')]";
        clickOnElement(xpath);
    }

    public void verifyNotDisplayTemplateFilter(String templateName) {
        String xpath = "//p[text()='" + templateName + "']";
        verifyElementVisible(xpath, false);
    }

    public void clickSave() {
        String xPath = "//div[@class='s-modal-footer']//button[@class='s-button is-primary']";
        waitUntilElementVisible(xPath, 30);
        clickOnElement(xPath);
    }

    public void importUrlProduct() {
        String xPath = "//div[@class='md-list-item-content md-ripple']//p[normalize-space()='Import From URLs']";
        clickOnElement(xPath);
    }

    public void selectPlatformAndType(String platform) {
        String xPath = "//label[normalize-space()='" + platform + "']//preceding-sibling::div";
        waitUntilElementVisible(xPath, 10);
        clickOnElement(xPath);
    }

    public void inputUrl(String url) {
        String xPath = "//div[contains(@class,'md-field md-layout-item')]//textarea";
        waitClearAndType(xPath, url);
    }

    public void importProduct() {
        String xPath = "//div[contains(@class,'md-layout-item')]//button";
        clickOnElement(xPath);
    }

    public void clickImportList() {
        String xPath = "//div[@class='md-list-item-content md-ripple']//p[normalize-space()='Import List']";
        clickOnElement(xPath);
    }

    public void verifyStatusImportApp(String product, String status) {
        String xPath = "//td[@class='md-table-cell' and child::div[normalize-space()='" + product + "']]//following-sibling::td[1]";
        assertThat(getText(xPath)).isEqualTo(status);
    }

    public void verifyNoteImportApp(String product, String note) {
        String xPath = "//td[@class='md-table-cell' and child::div[normalize-space()='" + product + "']]//following-sibling::td[3]//div";
        assertThat(getText(xPath)).isEqualTo(note);
    }

    public int getNumberProductOfList() {
        return countElementByXpath("//tr//div[@class='product-name']");
    }

    public void verifyMessageCloneProduct(String message) {
        String xpath = "//div[@class='s-alert is-yellow']//p";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public void verifyTotalProductClone(String totalProducts) {
        String xpath = "//div[@class='s-modal-body']//p[@class='s-mb12']//b";
        assertThat(getText(xpath)).isEqualTo(totalProducts);
    }

    public void selectActionClone(String action) {
        String xpathButton = "//div[child::p[contains(normalize-space(),'If the importing handle exists')]]//following-sibling::div//button";
        String xpathAction = "//div[@class='s-dropdown-menu']//span[normalize-space()='" + action + "']";
        clickOnElement(xpathButton);
        clickOnElement(xpathAction);
    }

    public void selectKeepID(String check) {
        String xpath = "//div[@class='s-modal-body']//span[@class='s-check']";
        String xpathStatus = "//div[@class='s-modal-body']//input";
        verifyCheckedThenClick(xpathStatus, xpath, Boolean.parseBoolean(check));
    }

    public void clickBtnAction() {
        String xpath = "//button[child::span[normalize-space()='Action' or normalize-space()='操作']]";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void clickBtnDelete() {
        String xpath = "//button[@class='s-button is-danger is-danger'] | //button[@class='s-button btn-confirm-delete is-danger']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }

    public void closePopup() {
        String xpath = "//div[@class='onboarding-popup active']//div[@class='button-close']";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }
    }
    public void clickPaymentStatusInHive() {
        String xpath="(//select[@data-sonata-select2='false'])[2]";
        clickOnElement(xpath);
    }
    public void verifyListPaymentStatus(String status) {
        String xpath="(//select[@data-sonata-select2='false'])[2]//option";
        int countXpath = countElementByXpath(xpath);
        for(int i=1; i<=countXpath; i++){
            assertThat(getText(xpath)).isEqualTo(status);
        }
    }
    public void selectPlatformTypeInHive(String platform){
        String xpathCheckBox="//label//span[normalize-space()='"+platform+"']//preceding-sibling::input";
        String xpathCheckBoxStatus="//label//span[normalize-space()="+platform+"]";
        verifyCheckedThenClick(xpathCheckBoxStatus, xpathCheckBox, true);
    }

    public void verifyProductsInSearchProducts(String sNumber) {
        Integer number = Integer.parseInt(sNumber);
        String xpath="//div[@class='product-item']";
        Integer countXpath = countElementByXpath(xpath);
        assertThat(countXpath).isEqualTo(number);
    }



    public void openPageDeactiveShopbasePayments() {
        String xpathOpen = "//span[normalize-space()='Payouts:']";
        clickOnElement(xpathOpen);
    }

    public void verifyMessageDetail(String msg){
        waitElementToBeVisible(buttonImportProcess);
        clickOnElement(buttonImportProcess);
        String xpathMessageDetail = "(//span[@class='s-dropdown-item']//div[@class = 'row col-xs-12 s-pt8 s-pb8'])[1]";
        String actualMsg = getText(xpathMessageDetail);
        System.out.println(actualMsg);
        assertThat(msg.equals(actualMsg)).isEqualTo(true);
    }

    public void verifyVariantColor(String sColor) {
        String xpathVariantColor = "//td[@class='group-title']//span[@class='type--bold']";
        assertThat(getText(xpathVariantColor)).isEqualTo(sColor);
    }

}
