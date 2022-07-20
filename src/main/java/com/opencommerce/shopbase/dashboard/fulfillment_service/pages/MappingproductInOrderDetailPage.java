package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class MappingproductInOrderDetailPage extends SBasePageObject {
    public MappingproductInOrderDetailPage(WebDriver webDriver) {
        super(webDriver);
    }


    public String getError() {
        String xpath = "//div[contains(@class,'text-color-danger')]";
        return getText(xpath);

    }

    public void verifyBTSaveIsDisable() {
        String xpath = "//button[@disabled='disabled' and child::span[contains(text(), 'Set')]]";
        verifyElementVisible(xpath, true);
    }

    public void verifyNotErr() {
        String xpath = "//div[contains(@class,'text-color-danger')]";
        verifyElementVisible(xpath, false);

    }

    public void verifyDisplayBTSet() {
        String xpath = "//button[@disabled='disabled' and child::span[contains(text(), 'Set')]]";
        verifyElementVisible(xpath, false);

    }

    public String getPage() {
        String xpath = "//div[@class='warehouse__heading m-b']//h2";
        return getText(xpath);
    }

    public ArrayList<String> getListProductOdooInInventory() {
        waitABit(5000);
        String xpath = "//tbody/tr";
        int index = getIndexOfColumn("WAREHOUSE PRODUCT");
        ArrayList<String> product = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String childXpath = xpath + "[" + i + "]/td[" + index + "]//a[contains(@class,'warehouse-product__name')]";
            product.add(getText(childXpath));
        }
        return product;
    }
    public ArrayList<String> getListProductOdooInMappingOderDetail() {
        ArrayList<String> product = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String childXpath = "(//div[@class='list-sbcn-product']//div//div[@class='s-tooltip-fixed'])[" + i + "]";
            product.add(getText(childXpath));
        }
        return product;
    }

    public void clickChangeMapping(String change_mapping) {
        String xpath  = "//span[text()='"+change_mapping+"']";
        focusClickOnElement(xpath);
    }

    public void clickBtnAction() {
        String xpath = "//button[child::span[text()='Actions']]";
        focusClickOnElement(xpath);
    }
}

