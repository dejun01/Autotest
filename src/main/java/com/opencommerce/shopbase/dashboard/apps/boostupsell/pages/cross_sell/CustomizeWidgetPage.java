package com.opencommerce.shopbase.dashboard.apps.boostupsell.pages.cross_sell;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CustomizeWidgetPage extends SBasePageObject {
    public CustomizeWidgetPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPopupConfirm() {
        verifyElementPresent(iD("reset-default"), true);
    }

    public void clickBtnReset() {
        clickOnElement("//div[@id='reset-default']//button//span[text()='Reset']");
    }

    public void enterHeader(String sHeader) {
        waitTypeOnElement("//div[@class='textMessage s-input']//input[@type='text']", sHeader);
    }

    public void selectDDLWidget(String labelName, String value) {
        String xpath = "//div[@class='s-content' and descendant::label[text()='" + labelName + "']]//div[@class='row']//select";
        selectDdlByXpath(xpath, value);
    }

    public void selectNumberOfProductsToBeShown(String sNumberOfProducts) {
        String xpath = "//div[child::label[text()[normalize-space()='Number of products to be shown']]]//select";
        selectDdlByXpath(xpath, sNumberOfProducts);
    }

    public void selectMaxProductsPerSlide(String maxProducts) {
        String xpath = "//div[child::label[text()[normalize-space()='Max products per slide']]]//select";
        selectDdlByXpath(xpath, maxProducts);
    }

    public void turnOnAddToCartButton(boolean isTurnOnAddCartButton) {
        String xpathToggle = "//div[@class='add-cart-button']";
        verifyCheckedThenClick(xpathToggle + "//input[@type='checkbox']", xpathToggle + "//span[@class='s-check']", isTurnOnAddCartButton);
    }

    String xpathListStyle = "//div[@class='s-content' and descendant::label[text()='%s']]//div[contains(@class,'settingStylefont')]";

    public int getNumberOfStyle(String labelName) {
        return countElementByXpath(String.format(xpathListStyle + "//ul//li[not(contains(@class,'color'))]", labelName));
    }


    public String getStyleByIndex(String labelName, int i) {
        return getAttributeValue("(" + String.format(xpathListStyle, labelName) + "//li[not(contains(@class,'color'))])[" + i + "]", "class").replace("text-", "").replace("align-", "");

    }

    public void checkStyleFont(String labelName, String style, boolean isCheck) {
        String xpath = "(//div[@class='s-content' and descendant::label[text()='" + labelName + "']]//div[contains(@class,'settingStylefont')]//li[contains(@class,'" + style + "')])";
        verifyCheckedThenClick(xpath + "//input", xpath + "//i", isCheck);
    }

    String xpathPlaceWidget = "//section[contains(@class,'choose-page-show')]// div[@class='show-content']/div[@class='m-b-sm widget-checkbox-wrapper']";

    public int getNumberOfPage() {
        return countElementByXpath(xpathPlaceWidget);
    }

    public String getPageByIndex(int i) {
        return getText(xpathPlaceWidget + "[" + i + "]");
    }

    public void choosePageShowWidget(String page, boolean isCheck) {
        checkCheckboxWithLabel(xpathPlaceWidget, page, 1, isCheck);
    }
}
