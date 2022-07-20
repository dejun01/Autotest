package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingProductPage extends SBasePageObject {
    public MappingProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickBTAction(String name) {
//        String xpath = "//span[contains(text(),'" + name + "')]//ancestor::td//following-sibling::td//ancestor::button//span[text()='Actions']";
//        waitUntilElementVisible(xpath, 8);
//        focusClickOnElement(xpath);
        clickBtn("Actions");
        String xpathSelect = "//div[contains(@class,'popover-activated')]//span[contains(text(),'Map product')]";
        clickOnElement(xpathSelect);
    }

    public void chooseStore(String name) {
        String xpath = "//span[text()='" + name + "']//parent::div/following-sibling::span";
        clickOnElement(xpath);
    }

    public void clickBTMapping() {
        String xpath = "//span[text()[normalize-space()='Map other product' or text()='Map product']]";
        focusClickOnElement(xpath);
    }

    public void enterNameOrder(String nameOrder) {
        String xpath = "//input[@placeholder='Search by product name']";
        waitClearAndTypeThenEnter(xpath, nameOrder);
        String xpathRadioBox = "//span[contains(text(),'" + nameOrder + "')]//ancestor::label//input";
        waitUntilElementVisible(xpathRadioBox, 5);
        focusClickOnElement(xpathRadioBox);

    }

    public void clickBTContinue() {
        String xpath = "//span[contains(text(),'Continue')]";
        clickOnElement(xpath);
    }

    public List<String> getListText() {
        String xpath = "//div[@class='item row']//div[@class='font-weight-bold']";
        return getListText(xpath);
    }

    public void mappingOrderSbase(String variantValueSbase) {
        waitABit(2000);
        String[] variantListSbase = variantValueSbase.split(";");
        for (String subVariant : variantListSbase) {
            String[] variants = subVariant.split(">");
            String variantNameSbase = variants[1];
            String xpathHeader = "//label[@class='s-checkbox']//child::span[contains(text(),'" + variantNameSbase + "')]";
            focusClickOnElement(xpathHeader);
        }
    }


    public void mappingOrderOdoo(String variantValueOdoo) {
        ArrayList<String> items = new ArrayList<>();
        String[] variantListOdoo = variantValueOdoo.split(";");
        for (String subVariantOdoo : variantListOdoo) {
            String[] variantsOdoo = subVariantOdoo.split(">");
            for (String variantName : variantsOdoo) {
                items.add(variantName);
            }
        }
        String xpath = "//label[@class='s-checkbox']//input";
        List<WebElement> listInput = getDriver().findElements(By.xpath(xpath));
        int current = 0;
        for (int h = 0; h < listInput.size(); h++) {
            if (listInput.get(h).isSelected()) {
                String xpath1 = "(//label[@class='s-checkbox']/parent::div//following-sibling::div)[" + (h + 1) + "]";
                focusClickOnElement(xpath1);
                String xpathSelectOption = "(//label[@class='s-checkbox']/parent::div//following-sibling::div)[" + (h + 1) + "]//option[@value='" + items.get(current) + "']";
                if (isElementExist(xpathSelectOption)) {
                    clickOnElement(xpathSelectOption);
                    current++;
                }
            }
        }
    }


    public void verifyDisplayMess(String mess) {
        String xpath = "//div[@class='s-notices is-bottom']";
        isElementExist(xpath);
        assertThat(getText(xpath)).isEqualTo(mess);
        waitABit(5000);
    }

    public void clickBTMappingProductRedirect() {
        String xpath = "//ol[contains(@class,'s-breadcrumb')]//a[text()[normalize-space()='Map product']]";
        if (isElementExist(xpath)) {
            clickOnElement(xpath);
        }

    }

    public void clickSave() {
        String xpath = "//span[text()[normalize-space()='Save changes']]";
        clickOnElement(xpath);
        waitABit(5000);
    }

    public String getTextErr() {
        String xpath = "//div[contains(@class,'invalid-options')]";
        return getText(xpath);
    }

    public void clickRemoveProduct(String name) {
        String xpath = "//a[contains(text(),'" + name + "')]/ancestor::div//following-sibling::div//div[contains(text(),'Remove')]";
        focusClickOnElement(xpath);
        String xpath2 = "//button[contains(@class,'is-primary')]//span[contains(text(),'Remove')]";
        focusClickOnElement(xpath2);
        waitABit(3000);
    }

    public int verifyListPro() {
        String xpath = "//div[@class='d-flex justify-content-space-between s-mb16']";
        return countElementByXpath(xpath);
    }

    public void clickEdit(String name) {
        String xpath = "//a[contains(text(),'" + name + "')]//ancestor::div[@class='row']//child::div//following-sibling::div[contains(text(),'Edit mapping')]";
        focusClickOnElement(xpath);
        waitForEverythingComplete();

    }

    public void clearData() {
        waitABit(2000);
        String xpath = "//label[@class='s-checkbox']//input";
        List<WebElement> listInput = getDriver().findElements(By.xpath(xpath));
        for (int i = 1; i < listInput.size(); i++) {
            if (listInput.get(i).isSelected()) {
                focusAndClickElement(listInput.get(i));
            }
        }
    }

    public void verifyInforMappingProd(String variantValueSbase, String variantValueOdoo) {
        waitABit(3000);
        ArrayList<String> items = new ArrayList<>();
        String[] variantListSbase = variantValueSbase.split(";");
        for (String subVariantSbase : variantListSbase) {
            String[] variantsSbase = subVariantSbase.split(">");
            for (String variantName : variantsSbase) {
                items.add(variantName);
            }
        }
        ArrayList<String> itemsOdoo = new ArrayList<>();
        String[] variantListOdoo = variantValueOdoo.split(";");
        for (String subVariantOdoo : variantListOdoo) {
            String[] variantsOdoo = subVariantOdoo.split(">");
            for (String variantName : variantsOdoo) {
                itemsOdoo.add(variantName);
            }
        }
        String xpath = "//label[@class='s-checkbox']//input";
        List<WebElement> listInput = getDriver().findElements(By.xpath(xpath));
        int current = 0;
        for (int i = 0; i < listInput.size(); i++) {
            if (listInput.get(i).isSelected()) {
                String getTextVariant = "(//label[@class='s-checkbox']//input)[" + (i + 1) + "]//following-sibling::span[2]";
                assertThat(getText(getTextVariant).trim()).isEqualTo(items.get(current));
                waitABit(2000);
                String getTextVariantOdoo = "(//label[@class='s-checkbox']/parent::div//following-sibling::div)[" + (i + 1) + "]//select";
                hoverOnElement(getTextVariantOdoo);
                String webElement = getSelectedValueDdl(getTextVariantOdoo);
                assertThat(webElement).isEqualTo(itemsOdoo.get(current));
                current++;
            }
        }
    }

    public void mappingOrderVariantOdoo(String variantValueOdoo) {
        ArrayList<HashMap<String, String>> items = new ArrayList<>();
        String[] variantListOdoo = variantValueOdoo.split(";");
        for (String subVariantOdoo : variantListOdoo) {
            HashMap<String, String> itemVariant = new HashMap<>();
            String[] variantsOdoo = subVariantOdoo.split(">");
            itemVariant.put("variant", variantsOdoo[0]);
            itemVariant.put("itemVariant", variantsOdoo[1]);
            items.add(itemVariant);
        }
        String xpathSel = "//div[contains(@class, 'option-item')]";
        int count = countElementByXpath(xpathSel);
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            selectDdlByXpath(xpathSel + "[" + index + "]//div[contains(@class,'main-item')]//select", items.get(i).get("variant"));
            selectDdlByXpath(xpathSel + "[" + index + "]//div[contains(@class,'sub-items')]//select", items.get(i).get("itemVariant"));
        }
    }

    public void searchProductImport(String nameProductOdoo) {
        String xpath = "//div[@class='s-input s-input--prefix']/input[contains(@placeholder,'Search product')]";
        waitClearAndTypeThenEnter(xpath, nameProductOdoo);
    }

    public void verifyDisableSave(String button) {
        String el = "//button[@disabled='disabled']//child::span[text()='" + button + "']";
        waitUntilElementVisible(el, 10);
        verifyElementPresent(el, true);
    }
}







