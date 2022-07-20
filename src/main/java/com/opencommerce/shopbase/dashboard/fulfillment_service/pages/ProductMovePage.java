package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import com.opencommerce.shopbase.ProductVariable;
import common.SBasePageObject;
import common.utilities.DateTimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opencommerce.shopbase.ProductVariable.variantList;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductMovePage extends SBasePageObject {
    public ProductMovePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static ArrayList actExportVariantInventoryList = new ArrayList<>();


    public List<String> getNameTextInventory() {
        String xpath = "//table[@class='table pos-rlt']//tbody//tr";
        return getListText(xpath);
    }

    public void clickViewInventoryDetail(String nameProduct) {
        String xpath = "//span[text()[normalize-space()='"+nameProduct+"']]//ancestor::div[contains(@class,'s-tooltip-fixed')]//following::span[@data-label='See product details']//i";
        if (isElementExist(xpath)) {
            focusClickOnElement(xpath);
            waitForEverythingComplete();
        }
    }

    public boolean verifyVariantInventory(String variant) {
        waitABit(2000);
        String xpath = "//div[@id='product-moves']//div[@class='s-collapse-item']";
        for (String textVariant : getListText(xpath)) {
            if (textVariant.replace(" / ", "/").equals(variant)) {
                return true;
            }
            System.out.println(false);
        }
        return false;
    }

    public void clickBTViewVariantDetail(String variant) {
        String xpath = "//div[@id='product-moves']//div[@class='s-collapse-item']//span[text()='" + variant + "']";
        clickOnElement(xpath);
        waitForEverythingComplete();


    }

    public String getTextProductOfVariant(String variant, String value) {
        String xpath = "//span[text()='" + variant + "']//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//div[contains(@class,'" + value + "')]";
        return getText(xpath);
    }

    public void enterValue(String value) {
//        String xpath = "//input[@placeholder='Search product variant. reference']";
//        waitClearAndTypeThenEnter(xpath, value);
        enterInputFieldWithLabel("Search product variant. reference", value);
        waitForEverythingComplete();
    }

    public int getTextListVariant(String variant) {
        String xpath = "//div[@id='product-moves']//div[@class='s-collapse-item']//span[text()='" + variant.replace("/", " / ") + "']";
        return countElementByXpath(xpath);
    }

    public void clickBTExport() {
        String xpath = "//button[@class='s-button s-button fw-normal is-outline is-button']//span[text() = 'Export']";
        clickOnElement(xpath);

    }

    public void getTextInfoSubVariant(String date, String type, String expected, String quantity, String status) {
        String xpath = "//div[contains(@class, 'custom-collapse-header')]/div";
        String xpathItem = "//div[contains(@class, 'custom-collapse-body')]";
        int countHeader = countElementByXpath(xpath);
        int countItem = countElementByXpath(xpathItem);
        for (int j = 1; j < countItem; j++) {
            for (int i = 1; i <= countHeader; i++) {
                String xpathItemHeader = xpath + "[" + i + "]";
                String xpathItemCont = xpathItem + "[" + j + "]/div[" + i + "]";
                if ("DATE".equals(xpathItemHeader)) {
                    assertThat(getText(xpathItemCont)).isEqualTo(date);
                }
                if ("TYPE".equals(xpathItemHeader)) {
                    assertThat(getText(xpathItemCont)).isEqualTo(type);
                }
                if ("REFERENCE".equals(xpathItemHeader)) {
                    assertThat(getText(xpathItemCont)).isEqualTo(expected);
                }
                if ("QUANTITY".equals(xpathItemHeader)) {
                    assertThat(getText(xpathItemCont)).isEqualTo(quantity);
                }
                if ("STATUS".equals(xpathItemHeader)) {
                    assertThat(getText(xpathItemCont)).isEqualTo(status);
                }
            }
        }

    }

    public void getInfoVariantInventory() {
        String xpath = "//div[contains(@class,'custom-collapse-header')]/div";

        int countHeader = countElementByXpath(xpath) - 1;
        for (String subVariant : variantList) {
            String xpathItem = "//span[contains(text(), '" + subVariant + "')]//ancestor::div[contains(@class, 's-collapse-item is-active')]//div[contains(@class, 'custom-collapse-body')]";
            int countItem = countElementByXpath(xpathItem);
            for (int j = 1; j <= countItem; j++) {
                HashMap<String, String> actExportVariantInventory = new HashMap<>();
                for (int i = 1; i <= countHeader; i++) {
                    String xpathItemHeader = xpath + "[" + i + "]";
                    String xpathItemCont = xpathItem + "[" + j + "]/div[" + i + "]";
                    actExportVariantInventory.put("Product Name", ProductVariable.productName);

                    actExportVariantInventory.put("Product Variant Name", subVariant);
                    if ("DATE".equals(getText(xpathItemHeader))) {
                        actExportVariantInventory.put("Date", getText(xpathItemCont).substring(0, getText(xpathItemCont).lastIndexOf(" ")));
                    }
                    if ("TYPE".equals(getText(xpathItemHeader))) {
                        actExportVariantInventory.put("Type", getText(xpathItemCont));
                    }
                    if ("REFERENCE".equals(getText(xpathItemHeader))) {
                        actExportVariantInventory.put("Reference", getText(xpathItemCont));
                    }
                    if ("QUANTITY".equals(getText(xpathItemHeader))) {
                        actExportVariantInventory.put("Quantity", getText(xpathItemCont));
                    }

                }
                actExportVariantInventoryList.add(actExportVariantInventory);
            }
        }

    }

    public String getTextNoti() {
        waitABit(2000);
        String xpath = "//div[@class='s-toast is-dark is-bottom']";
        return getText(xpath);
    }

    public void verifyDownloadExport(ArrayList expExportProdcutList) {
        int size = expExportProdcutList.size();
        for (int ind = 0; ind < size; ind++) {
            Map<String, String> actualResult = (Map<String, String>) expExportProdcutList.get(ind);
            Map<String, String> expectedResult = (Map<String, String>) actExportVariantInventoryList.get(ind);
            for (Map.Entry<String, String> pair : actualResult.entrySet()) {
                assertThat(expectedResult.get(pair.getKey())).isEqualToIgnoringCase(pair.getValue());
            }
        }
    }

    public String getTextMessDownloadSuccess() {
        String xpath = "//div[@class='s-toast is-dark is-bottom']";
        return getText(xpath);

    }

    public void clickSubmit() {
        String xpath = "//div[@class='s-modal-footer']//span[contains(text(),'Export')]";
        clickOnElement(xpath);

    }

    public void clickCheckbox(String name) {
        String xpath = "//div[contains(@class,'export-template')]//span[@class='s-control-label' and contains(text(),'" + name + "')]";
        clickOnElement(xpath);

    }

    public void clickViewInventoryByName(String name) {
        String xpath = "//span[text()='" + name + "']";
        clickOnElement(xpath);
    }

    public void clickChooseAmountExport(String name) {
        waitABit(3000);
        String xpathInputAmount = "//div[@class='s-select']";
        focusClickOnElement(xpathInputAmount);
        String xpathOption = "//option[@value='" + name + "']";
        clickOnElement(xpathOption);

    }

    public void accTab(String stock_move) {
        String xpath = "//p[contains(text(),'" + stock_move + "')]";
        clickOnElement(xpath);
    }
}


