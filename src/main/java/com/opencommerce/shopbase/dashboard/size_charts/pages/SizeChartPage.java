package com.opencommerce.shopbase.dashboard.size_charts.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class SizeChartPage extends SBasePageObject {
    public SizeChartPage(WebDriver driver) {
        super(driver);
    }

    public int MAX_RETRY_TIME = 15;

    public void enableSizeChart() {
        String xpath = "//div[child::p[text()='Disable size chart widget']]//preceding-sibling::label//span[@class='s-check']";
        if (isElementExist(xpath)) {
            clickOnElementByJs(xpath);
        }
    }

    public void disableSizeChart() {
        String xpath = "//div[child::p[text()='Enable size chart widget']]//preceding-sibling::label//span[@class='s-check']";
        if (isElementExist(xpath)) {
            clickOnElementByJs(xpath);
        }
    }

    public void inputStyle(String style) {
        String xpath = "//div[@class='s-mb-xs s-input']//input[@placeholder='Size chart style']";
        inputSlowly(xpath, style);
    }

    public void addImage(String image) {
        String xpath = "//input[@type='file' and @accept='image/*']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, image);
        waitABit(2000);
    }

    public void verifyMessage(String message) {
        String xpath = "//p[@class='text-error']";
        assertThat(getText(xpath)).isEqualTo(message);
    }

    public int countNumberOfSizeChart() {
        String xpath = "//div[@id='table-sizechart']//table//tbody//tr[not(@class='tr--no-hover')]";
        return countElementByXpath(xpath);
    }

    public void selectAllSizeChart() {
        String xPath = "//label[@class='s-checkbox']//span[@class='s-check']";
        clickOnElementByJs(xPath);
    }

    public void chooseAction(String action) {
        String xPath = "//*[contains(@class,'dropdown-item') and normalize-space()='" + action + "']";
        waitForEverythingComplete();
        clickOnElement(xPath);
        waitABit(1000);
    }

    public void verifyMsgNoSizeChart() {
        String xpath = "//div[@id='table-sizechart']//table//tbody//tr//p";
        verifyElementText(xpath, "You have no size chart yet");
    }

    public void verifyShowSizeChart(boolean show) {
        String xpath = "//div[contains(@id,'detail-contents')]//p[normalize-space()='Size Guide'] | //label[contains(@class,'product__size') and normalize-space()='Size Guide']";
        int retryTimes = 0;
        if (show) {
            while (!isElementExist(xpath) && retryTimes <= MAX_RETRY_TIME) {
                waitABit(15000);
                refreshPage();
                retryTimes++;
            }
        } else {
            while (isElementExist(xpath) && retryTimes <= MAX_RETRY_TIME) {
                waitABit(15000);
                refreshPage();
                retryTimes++;
            }
        }
        verifyElementVisible(xpath, show);
    }

    public void selectDdProductProperties(String prodProperties) {
        selectDdlWithLabel("Filter", prodProperties, 1);
    }

    public void selectDdColCondition(String cons) {
        selectDdlWithLabel("Filter", cons, 2);
    }

    public void inputConditionValue(String conValue) {
        String xpath = "//input[@id='Property']";
        waitClearAndType(xpath, conValue);
    }

    public String getTag() {
        String xpath = "//input[@placeholder='Size guide']";
        return getTextValue(xpath);
    }
}