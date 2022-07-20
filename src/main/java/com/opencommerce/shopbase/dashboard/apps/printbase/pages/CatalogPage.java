package com.opencommerce.shopbase.dashboard.apps.printbase.pages;

import common.SBasePageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class CatalogPage extends SBasePageObject {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    String theFirstProduct = "(//div[@class='phub-catalog']//div[@class='body']//div[@class='prod-wrap'])[1]";

    public float getProductPrice() {
       Float price = Float.parseFloat(getText(theFirstProduct + "//div[@class='price-wrap']//span[contains(@class,'price')]").replace("$", ""));
        return price;
    }
    public float getBaseProductPrice(String sproduct){
        String xpath ="//div[@class='product-click-action m-b-xxs' and child::span[normalize-space()='"+sproduct+"']]//following-sibling::div[@class='price-wrap']//span[contains(@class,'price')]";
        Float price = Float.parseFloat(getText(xpath).replace("$", ""));
        return price;
    }

    String xpathProductBase = "//span[normalize-space() ='%s']/ancestor::div[@class='prod-wrap']";

    public void verifyProductInCatalog(String productBase) {
        String xpath = String.format(xpathProductBase, productBase);
        verifyElementPresent(xpath, true);
    }

    public float getProductPriceForProduct(String productBase) {
        String xpath = String.format(xpathProductBase, productBase) + "//span[contains(@class,'price')]";
        Float price = Float.parseFloat(getText(xpath).replace("$", ""));
        return price;
    }

    public void verifyProcessingTimeForProductBase(String productBase, String processing) {
        String xpath = String.format(xpathProductBase, productBase) + "//div[@class='supplier-info']/span";
        assertEquals("Processing Time: " + processing, getText(xpath));
    }

    public void verifyShippingNoteForProduct(String productBase) {
        String xpath = String.format(xpathProductBase, productBase) + "//span[@class='unit kit-paragraph-medium']";
        assertEquals("(w/ shipping)", getText(xpath));
    }

    public void selectShowPriceWithShipping(boolean isshowPriceWithShipping) {
        checkCheckboxWithLabel("Show price with shipping", 1, isshowPriceWithShipping);
    }

    public void selectShippingAddress(String shippingAddress) {
        String dropDownList = "//div[contains(@class,'dropdown-trigger')]";
        clickOnElement(dropDownList + "//button[@type='button']");
        clickOnElement("//div[@class='s-dropdown-menu'][not(@style='display: none;')]//span[text()[normalize-space()='" + shippingAddress + "']]");
    }
    public void selectAddress(String address) {
        clickOnElement("//div[@class='s-select']//select");
        clickOnElement("//div[@class='s-select']//option[normalize-space()='"+address+"']");
    }


    public void verifyProcessingTime() {
        verifyTextPresent("Processing Time: 2 ~ 7 Days", true);
    }

    public void verifyShippingNote() {
        verifyTextPresent("(w/ shipping)", true);
    }

    public void clickTabName(String tabName) {

        String categories = "//*[@class='phub-catalog']//div[@class='product-catagory']//span[text()[normalize-space()='" + tabName + "']]";
        String xpath = categories + "//ancestor::li";
        String status = getDriver().findElement(By.xpath(xpath)).getAttribute("class");
        if (status.equalsIgnoreCase("is-active") == false) {
            clickOnElement(categories);
            waitForEverythingComplete(10);
        }

    }


}
