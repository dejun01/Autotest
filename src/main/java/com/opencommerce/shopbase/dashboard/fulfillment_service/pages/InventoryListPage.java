package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static com.opencommerce.shopbase.OrderVariable.*;

public class InventoryListPage extends SBasePageObject {

    public static ArrayList actExportProductInventoryList = new ArrayList<>();
    public boolean flag = false;
    public String url = LoadObject.getProperty("webdriver.base.url");
    public String emailLogin = LoadObject.getProperty("staff.name");
    public String nameFilter = "";
    public HashMap<String, String> infoSave = new HashMap<>();

    public InventoryListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterValueFilter(String value) {
        String xpath = "//input[@type='number']";
        waitClearAndType(xpath, value);
        infoSave.put("value", value);
    }

    public void clickStatusInventory(String status) {
        String xpath = "//div[contains(@class,'sidebar-body')]//p[contains(text(),'" + status + "')]";
        waitElementToBeVisible(xpath, 10);
        clickOnElement(xpath);
        infoSave.put("status", status);
    }

    public void clickCondition(String condition) {
        String xpathCondition = "//div[contains(@class,'s-collapse-item is-active')]//child::span[normalize-space(text())= '" + condition + "']";
        clickOnElement(xpathCondition);
        if (condition.equals(">=")) {
            flag = true;
        }
        infoSave.put("condition", condition);

    }

    public void verifyDataSearchWithCondition(String conditionValue, String title) {
        String xpath = "//div[@class='product-list']//tbody/tr";
        int count = countElementByXpath(xpath);
        int exp = Integer.parseInt(conditionValue);
        int index = getLocation(title);
//        String xpathCondiFilLabel = "//span[contains(@class,'s-tag')]//div";
//        String conditionFilterLabel = infoSave.get("status") + " " + infoSave.get("condition") + " " +infoSave.get("value");
//        assertThat(conditionFilterLabel).isEqualTo(getText(xpathCondiFilLabel));

        for (int j = 1; j <= count; j++) {
            String xpathResult = xpath + "[" + j + "]/td";
            int countResult = countElementByXpath(xpathResult);
            if (countResult > 1) {
                String xpathValue = xpathResult + "[" + index + "]";
                String val = getText(xpathValue);
                if (!val.equals("")) {
                    int act = Integer.parseInt(val);
                    if ((flag && act < exp) || (!flag && act > exp)) {
                        fail();
                    }
                }
            } else {
                xpathResult += "//p[contains(@class, 'text-center')]";
                assertThat("No products found").isEqualTo(getText(xpathResult));
            }
        }
    }

    private int getLocation(String title) {
        return countElementByXpath("//table/thead/tr/th[descendant::div[contains(text(), '"+title+"')]]//preceding-sibling::th") + 1;
    }

    public void verifyMessage(String message) {
        String xpath = "//p[contains(@class, 'text-error')]";
        assertThat(message).isEqualTo(getText(xpath));
    }

    public void verifyDownloadFileMatchingTemplate(ArrayList expExportProductList) {
        int sizeAct = expExportProductList.size();
        for (int ind = 0; ind < sizeAct; ind++) {
            Map<String, String> actualResult = (Map<String, String>) expExportProductList.get(ind);
            Map<String, String> expectedResult = (Map<String, String>) expExportProductInventoryList.get(ind);

            for (Map.Entry<String, String> pair : actualResult.entrySet()) {
                assertThat(expectedResult.get(pair.getKey())).isEqualToIgnoringCase(pair.getValue());
            }

        }
    }

    public void clickButtonOnPopup(String buttonName) {
        waitABit(4000);
        String xpath = "//div[@id='modal-export-order']//div[@class='s-modal-footer']//button/span[contains(text(),'" + buttonName + "')]";
        clickOnElement(xpath);
    }

    public void verifyNotiExportEqual(String noti, String email) {
        if(!email.isEmpty()) {
            noti += emailLogin;
        }
        String xpath = "//div[@class='s-toast is-dark is-bottom']/div";
        waitElementToBeVisible(xpath);
        assertThat(noti).isEqualToIgnoringCase(getText(xpath));
    }

    public void getProductInventoryDefault() {
        actExportProductInventoryList = new ArrayList();
        String xpath = "//div[@class='product-list']/div/table/thead/tr[@class='tr--no-hover']/th";
        int count = countElementByXpath(xpath);
        String xpathVariant = "//div[@class='product-list']//tbody/tr[@class='tr--no-hover']";
        int countVariant = countElementByXpath(xpathVariant);
        for (int ind = 1; ind <= countVariant; ind++) {
            String xpathItem = xpathVariant + "[" + ind + "]//tbody/tr";
            int countItem = countElementByXpath(xpathItem);
            for (int i = 1; i <= countItem; i++) {
                HashMap<String, String> actExportProductInventory = new HashMap<>();
                for (int j = 2; j < count ; j++) {
                    String xpathTextHeader = "(" + xpath + ")[" + j + "]";
                    String xpathText = xpathItem + "[" + i + "]/td[" + j + "]";
                    String header = getText(xpathTextHeader).isEmpty() ? "" : getText(xpathTextHeader).replace("\n", " ");
                    if("WAREHOUSE PRODUCT".equals(header)) {
                        String xpathNameProductParent = xpathText + "//span[contains(@class, 'product-list__name')]";
                        String xpathTextVariant = xpathText + "//span[contains(@class, 'product-list__sub-name')]";
                        actExportProductInventory.put("Product Name", getText(xpathNameProductParent));
                        actExportProductInventory.put("Variant Name", getText(xpathTextVariant));
                    } else {
                        actExportProductInventory.put(header, getText(xpathText));
                    }
                }
                actExportProductInventoryList.add(actExportProductInventory);
            }
        }
    }

    public void searchProductWith(String search) {
        String xpath = "//input[@placeholder='Search products']";
        waitElementToBeVisible(xpath);
        waitClearAndTypeThenEnter(xpath, search);
        waitForEverythingComplete();
    }

    public void clickElementProductInInventory() {
        String xpath = "//div[@class='product-list']//tr[not(@class)]";
        waitElementToBeVisible(xpath);
        for (int i = 1; i <= countElementByXpath(xpath); i++) {
            String xpathProduct = xpath + "[" + i + "]";
            clickOnElement(xpathProduct);
        }
    }

    public void clickRadioExportWith(String searchFilter) {
        selectRadioButtonWithLabel(searchFilter, true);
    }

    public void selectProductExport() {
        String xpath = "//td[@class='order-select']";
        waitForEverythingComplete();
        clickOnElement(xpath);
    }
    public void chooseProductImport() {
        String xpath = "//div[@class='product-list']//tbody/tr[not(@class)][1]/td//label";
        try {
            checkCheckbox(xpath, true);
        }catch (Exception e) {
            waitElementToBeVisible(xpath, 40);
            checkCheckbox(xpath, true);
        }


    }

    public void clickButtonImportStore(String buttonName) {
        clickOnElement("//div[contains(@class,'popover-activated')]/span[contains(text(), '"+buttonName+"')]");
    }
    public boolean verifyImportProductSuccess() {
        if(!isElementVisible("//img[@class='']", 1)) {
            return true;
        }
        return false;
    }

    public void searchProductImport(String prodcut) {
        String xpath = "//div[@class='s-input s-input--prefix']/input[contains(@placeholder,'Search product')]";
        try {
            waitClearAndType(xpath, prodcut);
        }catch (Throwable e) {
            refreshPage();
            waitABit(4000);
            waitClearAndType(xpath, prodcut);
        }
    }

    public void verifyProdcut(String product) {
        int index = countElement("PRODUCT");
        String xpath = "//tbody/tr[1]/td["+index+"]//div[contains(@class,'product-name')]//span[not(@class)]";
        try {
            assertThat(getText(xpath)).contains(product);
        } catch (Throwable t) {
            searchProductWith(product);
            assertThat(getText(xpath)).contains(product);
        }
    }

    public void verifyStatus() {
        int index = countElement("PRODUCT");
        String xpath = "//tbody/tr[1]/td["+index+"]//div[contains(@class,'product-status')]";
        assertThat("Unavailable").isEqualTo(getText(xpath));
    }

    private int countElement(String colName) {
        return countElementByXpath("//th[child::div[contains(text(), '"+colName+"')]]//preceding-sibling::th") + 1;
    }

    public void verifyOtherInforOfProduct(String colNames) {
        String cols[] = colNames.split(",");
        for(String col : cols) {
            int index = countElementByXpath("//th[contains(text(), '"+col+"')]//preceding-sibling::th") + 1;
            String xpathType = "//tbody/tr[1]/td["+index+"]/a";
            assertThat("").isEqualTo(getText(xpathType));
        }
    }

    public void chooseMultipleProductImport() {
        String xpath = "//tbody/tr[not(@class)]";
        int count = 2;
        for(int i =1; i<=count; i++) {
            String xpathClick = xpath + "["+i+"]/td[contains(@class,'order-select')]//span[contains(@class, 's-check')]";
            String XpathNameProd = xpath + "["+i+"]/td//span[contains(@class,'product-list__name')]";
            productMultiple.add(getText(XpathNameProd));
            clickOnElement(xpathClick);
        }
    }

    public void clickButtonAction() {
        String xpath = "//div[contains(@class, 'orders-actions-menu')]//span[contains(text(), 'Actions')]";
        clickOnElement(xpath);
    }

    public void clickButtonImportMultipleStore() {
        String xpathImport = "//div[contains(@class, 'orders-actions-menu')]//span[contains(text(), 'Add to store')]";
        clickOnElement(xpathImport);
    }

    public void verifyRadioClearFilter() {
        String xpathRadio = "//div[contains(@class, 'is-active')]//input[@type='radio']";
        selectRadioButtonPhub(xpathRadio, false);

    }

    public void verifyInputClearFilter() {
        String xpath = "//div[contains(@class, 'is-active')]//input[@class='s-input__inner']";
        verifyElementVisible(xpath, false);
    }

    public void verifyButtonClear() {
        String xpath = "//div[contains(@class, 'is-active')]//button[@disabled]";
        verifyElementVisible(xpath, true);

    }

    public void verifyButtonClearFilter() {
        String xpathClearFilter = "" +
                "" +
                "";
        verifyElementVisible(xpathClearFilter, true);
    }

    public void verifyButtonSaveFilet() {
        String xpathSaveFilter = "//span[contains(text(), 'Save filter')]//parent::button[@disabled]";
        verifyElementVisible(xpathSaveFilter, true);

    }

    public void enterInfoNameFilter() {
        String xpath = "//input[contains(@placeholder, 'Filter name')]";
        Date dateTimeNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhssmm");
        String strDate = formatter.format(dateTimeNow);
        nameFilter = "name-filter-"+strDate;
        waitClearAndType(xpath, nameFilter);
    }

    public void clickButtonSave() {
        String xpathAddDone = "//input[contains(@placeholder, 'Filter name')]//parent::div//following-sibling::button";
        clickOnElement(xpathAddDone);
    }

    public void verifyInfoSaveFilter() {
        String xpathFilter = "//div[contains(@class, 'filter-item')]//child::div[contains(text(), '"+nameFilter+"')]";
        waitElementToBeVisible(xpathFilter);
        verifyElementVisible(xpathFilter, true);
        clickOnElement(xpathFilter + "//following-sibling::i");
    }

    public void clickButton(String button) {
        clickBtn(button);
    }

    public int countIndexElement(String colName) {
        String xpath = "//div[@class='product-list']/div/table/thead/tr//th[child::*[normalize-space()='"+colName+"']]//preceding-sibling::th";
        return countElementByXpath(xpath) + 1;
    }

    public void clickActionButton() {
        clickOnElement("//div[@class='product-list']//tbody/tr[not(@class)][1]/td//button");
    }

    public void clickProduct(String product) {
        clickOnElement("//span[contains(text(), '"+product+"') and contains(@class, 'product-list__name')]");
    }

    String xpath = "//span[normalize-space( ) = '%s']//ancestor::tr[@class='tr--no-hover']//thead";
    public int countcol(String variant) {
        String xpathCol = String.format(xpath +"//th" , variant);
        return countElementByXpath(xpathCol);
    }

    public String getLabelInCol(int index, String variant) {
        String xpathLabel = String.format(xpath + "//th["+index+"]//div" , variant);
        return getText(xpathLabel).replace("\n", " ");
    }

    public Integer getValueInCol(int i, String variant) {
        return Integer.parseInt(getText("//span[normalize-space( ) = '"+variant+"']//ancestor::tr[@class='cursor-default']/td["+i+"]"));
    }

    public void searchProductInQuotation(String product) {
        waitClearAndType("//input[@placeholder='Search quotation by Quotation number, Source URL, Product name']", product);
    }

    public void switchTab(String tabName) {
        waitABit(4000);
        clickOnElement("//nav[contains(@class, 's-tabs-nav')]//p[contains(text(), '"+tabName+"')]");
    }

    public void moveToViewQuotation(String product) {
        waitABit(4000);
        clickOnElement("//a[contains(text(), '"+product+"')]//ancestor::tr/td[1]");
    }

    public void fillQuantity(String variant, String quantity) {
        String xpath = "//tr[descendant::span[contains(text(), '"+variant+"')]]//input[@type='number']";
        clearValueByJS("//input[@type='number']");
        try {
            waitElementToBeVisible(xpath);
            waitClearAndType(xpath, quantity);
        }catch (Throwable e) {
            waitABit(4000);
            refreshPage();
            waitClearAndType(xpath, quantity);
        }

    }

    public void moveToHomePage() {
        clickOnElement("//li[contains(@class, 'li-dashboard')]//i[contains(@class, 'mdi-home')]");
    }

    public void clickDeleteButton(String label) {
        clickOnElement("//button/span[contains(text(), '"+label+"')]");
    }

    public void moteToProductDetail(String product) {
        clickOnElement("//tr[1]//span[contains(text(), '"+product+"')]");
    }

    public void clickDeleteButtonOnPopup(String label) {
        clickOnElement("//button[contains(text(), '"+label+"')]");
    }

    public void moveToPage(String tabName) {
        clickOnElement("//a[contains(normalize-space(),'"+tabName+"')]");
    }
}
