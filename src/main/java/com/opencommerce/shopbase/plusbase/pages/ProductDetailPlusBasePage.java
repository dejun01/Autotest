package com.opencommerce.shopbase.plusbase.pages;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
public class ProductDetailPlusBasePage extends SBasePageObject {
    public ProductDetailPlusBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void userNavigateScreen(String tabName) {
        String xpath = "//a[@class='router-link-active']//span[contains(text(), '" + tabName + "')]";
        try {
            focusClickOnElement(xpath);
        } catch (Throwable e) {
            waitABit(4000);
            focusClickOnElement(xpath);
        }
    }

    public void searchProductPlBase(String product) {
        waitClearAndTypeThenEnter("//input[@placeholder='Search products']", product);
    }

    public void clickProductPlBase(String productName) {
        String xpath = "//div[contains(text(),'" + productName + "')]";
        clickOnElement(xpath);
    }

    public String getProductName() {
        String xpath = "//div[contains(@class,'sb-title')]//div[contains(@class,'sb-font')]//div";
        return getText(xpath);
    }

    public String getTags() {
        String xpath = "//div[text()='Tags']//ancestor::div[@role='tablist']//div[contains(@class,'sb-tag')]/div";
        return getText(xpath);
    }

    public String getProcessingTime() {
        String xpath = "//div[text()='Processing time']//parent::div[contains(@class,'cpd-variant__processing-time')]//div[contains(@class,'content')]";
        return getText(xpath);
    }

    public String getProductCost() {
        String xpath = "//div[text()='Product cost']/parent::div//div[@class='sb-text-small']";
        return getText(xpath);
    }

    public String getSellingPrice() {
        String xpath = "//div[text()='Selling price']/parent::div//div[contains(@class,'sb-text-small')]";
        return getText(xpath);
    }

    public float getprofitMargin() {
        String xpath = "//div[text()='Profit margin']/parent::div//div[contains(@class,'sb-text-small')]";
        return getPrice(xpath);
    }

    public String getDescription() {
        String xpath = "//div[contains(@class,'cpd-description__content')]//p";
        return getText(xpath).trim();
    }

    public String getAboutThisProduct() {
        String xpath = "//div[contains(@class,'sb-tab-panel')]//div/p[1]";
        return getText(xpath);
    }

    public void clickVariant(String option, String optionValue) {
        String xpath = "//div[text()='" + option + "']/parent::div//button[child::span[text()[normalize-space()='" + optionValue + "']]]";
        clickOnElement(xpath);
    }

    public String getShipTo() {
        String xpath = "";
        return getText(xpath);
    }

    public void selectShipTo(String country) {
        selectDdlWithLabel("Ship to", country);
    }

    public String getShippingMethod() {
        String xpath = "";
        return getText(xpath);
    }

    public String getShippingFee() {
        String xpath = "";
        return getText(xpath);
    }

    public String getEstimatedDeliveryTime() {
        String xpath = "";
        return getText(xpath);
    }

    public void clickBTImportToYourStore() {
        String xpath = "//span[contains(text(),'Import to your store')]";
        clickOnElement(xpath);
    }

    public void directToImportListPage() {
        String xpath = "//div[contains(text(),'Import List')]";
        clickOnElement(xpath);
    }

    public void selectCheckboxAllProduct() {
        String xpath = "//th[contains(@class,'flex checkbox-col')]//label//span[@class='s-check']";
        String xpath_Status = "//th[contains(@class,'flex checkbox-col')]//label//input";
        verifyCheckedThenClick(xpath_Status,xpath,true);
    }

    public void verifyContentOfMailPlbaseImportProduct(String content) {
        waitForElementToPresent("//iframe[@id='html_msg_body']");
        switchToIFrame("//iframe[@id='html_msg_body']");
        String xPath = "//div[normalize-space()='" + content + "']";
        verifyElementVisible(xPath, true);
    }

    public void clickDownLoadCSVTemplate() {
        String xpath = "//p[@class='m-t']//a";
        clickOnElement(xpath);
    }


}
