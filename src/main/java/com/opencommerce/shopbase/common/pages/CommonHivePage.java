package com.opencommerce.shopbase.common.pages;

import common.SBasePageObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonHivePage extends SBasePageObject {
    public CommonHivePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyPageTitleDisplay(String page) {
        verifyElementText("//h4", page);
    }

    public void selectDroplist(String label, String value) {
        String xpath = "//div[@class='form-group' and child::label[normalize-space()='" + label + "']]/div";
        clickOnElement(xpath);
//        String _xpath = "(//div[contains(@class,'select2-drop-active') and descendant::label[normalize-space()='" + label + "']]//*[normalize-space()='" + value + "'])[1]";
        String _xpath = "(//div[contains(@class,'select2-drop-active')]//*[normalize-space()='" + value + "'])[1]";
        clickOnElement(_xpath);
    }

    public void uploadImage(String label, String image) {
        String xpath = "//div[child::label[normalize-space()='" + label + "']]//input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(1000);
    }

    public void scrollIntoViewInput(String label, int res) {
        String xpath = xPathInputFieldWithLabel("", label, res);
        scrollIntoElementView(xpath);
    }

    public void scrollIntoViewCheckbox(String label) {
        String xpath = "(//*[child::*[normalize-space()='" + label + "']]//input[contains(@type,'checkbox')])|(//*[text()[normalize-space()='" + label + "']]//ancestor::label//input[contains(@type,'checkbox')])";
        scrollIntoElementView(xpath);
    }

    public void verifyNameDisplay(String value) {
        verifyElementPresent("//tr//td[normalize-space()='" + value + "']", true);
    }

    public void verifyNameDisplay(String id, String value) {
        verifyElementPresent("//tr//td[@objectid='" + id + "' and normalize-space()='" + value + "']", true);
    }

    public String getIdItem(String name) {
        return getAttributeValue("//tr//td//a[normalize-space()='" + name + "']//parent::td", "objectid");
    }

    public void verifyFiledDisplay(String id, String value) {
        verifyElementPresent("//tr//td[@objectid='" + id + "' and normalize-space()='" + value + "']", true);
    }

    public void verifyActive(String id, Boolean isActive) {
        if (isActive) {
            verifyElementPresent("//tr//td[@objectid='" + id + "' and normalize-space()='yes']", true);
        } else verifyElementPresent("//tr//td[@objectid='" + id + "' and normalize-space()='no']", true);
    }

    public void clickTab(String tabName) {
        clickOnElement("//*[normalize-space()='" + tabName + "']");
    }

    public void closeDatePicker(String label) {
        clickOnElement("//*[normalize-space()='" + label + "']");
    }

    public void clickEdit(String id) {
        clickOnElement("//tr[td[@objectid='" + id + "']]//a[normalize-space()='Edit']");
    }

    public void clickUpdateAndClose() {
        clickOnElement("//*[normalize-space()='Update and close']");
    }

    public void inputDataForBaseProduct(String color, String size, String weight, String cost) {
//        String xpath = "//a[normalize-space()='Product variants']";
//        waitElementToBeVisible(xpath);
//        clickOnElement(xpath);
//        System.out.println("Start");

        String xpathCost = "//table//tr[descendant::input[@value='" + color + "'] and descendant::input[@value='" + size + "']]//td[contains(@class,'variants-garmentCost')]//input";
        String xpathWeigh = "//table//tr[descendant::input[@value='" + color + "'] and descendant::input[@value='" + size + "']]//td[contains(@class,'variants-weight') and not(contains(@class,'variants-weightUnit'))]//input";

//        waitElementToBeVisible(xpathWeigh);
//        waitElementToBeVisible(xpathCost);
        try {
            WebElement eleWeight = getDriver().findElement(By.xpath(xpathWeigh));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", eleWeight);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=\"" + weight + "\"", eleWeight);

            WebElement eleCost = getDriver().findElement(By.xpath(xpathCost));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", eleCost);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=\"" + cost + "\"", eleCost);

            System.out.println(" | " + color + " | " + size + " | " + weight + " | " + cost + " | ");
        } catch (Throwable t) {
            System.out.println("Missing: " + color + " - " + size);
        }

//        eleWeight.click();
//        eleWeight.clear();
//        eleWeight.sendKeys(weight);

//        eleCost.click();
//        eleCost.clear();
//        eleCost.sendKeys(cost);

//        waitClearAndType(xpathCost, cost);
//        waitClearAndType(xpathWeigh, weight);

//        XH(xpathWeigh).clear();
//        XH(xpathWeigh).type(weight);
//        XH(xpathCost).clear();
//        XH(xpathCost).type(cost);

//        System.out.println("Done");
    }

    public void getDataOfBaseProduct() {
//        String xpath = "//a[normalize-space()='Product variants']";
//        waitElementToBeVisible(xpath);
//        clickOnElement(xpath);
        String xpath = "//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table";
        waitElementToBeVisible(xpath);

        System.out.println("Start");

        WebElement table = getDriver().findElement(By.xpath("//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table"));
        Document doc = Jsoup.parse(table.getAttribute("innerHTML"));
        StringBuilder variant = new StringBuilder();

        for (Element rows : doc.select("input")) {
            if (rows.id().contains("option1")) {
                variant.append(" | ");
                variant.append(rows.val());
            }

            if (rows.id().contains("option2")) {
                variant.append(" | ");
                variant.append(rows.val());
            }

            if (rows.id().contains("weight") && !rows.id().contains("weightUnit")) {
                variant.append(" | ");
                variant.append(rows.val());
            }

            if (rows.id().contains("garmentCost")) {
                variant.append(" | ");
                variant.append(rows.val());
                variant.append(" | ");
                System.out.println(variant);
                variant = new StringBuilder();
            }
        }

//        String xpathLineVarAll = "//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table//tbody//tr";
//        int qtyLineVar = countElementByXpath(xpathLineVarAll);
//        for (int i = 1; i <= qtyLineVar; i++) {
//            String xpathLineVarColor = "(//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table//tbody//tr)[" + i + "]//td[contains(@class,'variants-option1')]//input";
//            String xpathLineVarSize = "(//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table//tbody//tr)[" + i + "]//td[contains(@class,'variants-option2')]//input";
//            String xpathLineVarWeight = "(//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table//tbody//tr)[" + i + "]//td[contains(@class,'variants-weight') and not(contains(@class,'variants-weightUnit'))]//input";
//            String xpathLineVarCost = "(//div[@id='sonata-ba-field-container-sabe1c53656_variants']//table//tbody//tr)[" + i + "]//td[contains(@class,'variants-garmentCost')]//input";
//            System.out.println(" | " + getValue(xpathLineVarColor) + " | " + getValue(xpathLineVarSize) + " | " + getValue(xpathLineVarWeight) + " | " + getValue(xpathLineVarCost) + " | ");
//        }
        System.out.println("Done");
    }

    public void clickBtnUpdate() {
        String xpath = "//button[normalize-space()='Update']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
        String xpathMsg = "//div[@class='alert alert-success alert-dismissable']";
        waitElementToBeVisible(xpathMsg);
    }
}
