package com.opencommerce.shopbase.dashboard.fulfillment_service.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimDetailPage extends SBasePageObject {

    public ClaimDetailPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyDefaultPreferredSolution(String preferredSolution) {
        String xpath = "//label[child::span[text()[normalize-space()='" + preferredSolution + "']]]//input";
        verifyElementSelected(xpath, true);
    }

    public int getIndexLineItemClaim(String productName, String variantName) {
        String xpath = "//span[child::span[text()[normalize-space()='" + productName + "']] and span[text()[normalize-space()='" + variantName + "']]]//ancestor::tr/preceding-sibling::tr";
        int index = countElementByXpath(xpath) + 1;
        return index;
    }

    public String getProductNameClaimDetail(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//span[contains(@class,'product-list__name')]";
        return getText(xpath);
    }

    public String getVariantNameClaimDetail(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//span[@class='product-list__sub-name']";
        return getText(xpath);
    }

    public void verifyProductVariantNotShow(String productName, String variantName) {
        String xpath = "//span[child::span[text()[normalize-space()='" + productName + "']] and span[text()[normalize-space()='" + variantName + "']]]";
        verifyElementPresent(xpath, false);
    }

    public String getShippingInforClaimDetail() {
        String xpath = "//div[@class='claim']//child::div[descendant::strong[text()='Shipping information']]//following-sibling::div/span";
        return getText(xpath);
    }

    public int getColumClaimDetail(String col) {
        String xpath = "//th[text()='" + col + "']//preceding-sibling::th";
        int numberCol = countElementByXpath(xpath) + 1;
        return numberCol;
    }

    public String getDefaultQuantityClaimDetail(int index) {
        int col = getColumClaimDetail("Quantity");
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//td[" + col + "]//input";
        return getValue(xpath);
    }

    public String getStatusLineItemClaimDetail(int index) {
        int col = getColumClaimDetail("Status");
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//td[" + col + "]//span";
        return getText(xpath);
    }

    public String getTrackingNumberClaimDetail(int index) {
        int col = getColumClaimDetail("Tracking info");
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//td[" + col + "]//a[@class='text-tracking-number']";
        return getText(xpath);
    }

    public void unCheckLineItemClaim(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//span[@class='s-check']";
        clickOnElement(xpath);
    }

    public void selectPreferredSolution(String preferredSolution) {
        String xpath = "//label[child::span[text()[normalize-space()='" + preferredSolution + "']]]//span[1]";
        clickOnElement(xpath);
    }

    public void inputQuantityLineItemClaim(String quantity, int index) {
        int col = getColumClaimDetail("Quantity");
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//td[" + col + "]//input";
        waitClearAndTypeThenEnter(xpath, quantity);
        if (quantity.equals(0)) {
            String value = getValue(xpath);
            assertThat(value).isEqualTo(1);
        }
    }

    public void clickSelectReason(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//div[contains(@class,'select-reason')]//select";
        clickOnElement(xpath);
    }

    public void chooseReason(int index, String reason) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//div[contains(@class,'select-reason')]//select//option[text()[normalize-space()='" + reason + "']]";
        clickOnElement(xpath);
    }

    public void uploadEvidenceLineItemClaim(String fileName, int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//input[@type='file']";
        chooseAttachmentFile(xpath, fileName);
    }

    public void inputNoteClaim(String note, int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//textarea";
        waitClearAndType(xpath, note);
    }

    public void verifyDisableSubmitClaim(boolean isDisable) {
        String xpath = "//button[child::span[text()[normalize-space()='Submit your claim']] and @disabled='disabled']";
        verifyElementPresent(xpath, isDisable);
    }

    public void verifyNotiAfterSubmitClaim(String noti) {
        String xpath = "//div[text()='" + noti + "']";
        verifyElementPresent(xpath, true);
    }

    public String getNoteValue(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//textarea";
        return getValue(xpath);
    }

    public String getReasonForClaim(int index) {
        String xpath = "//div[@class='product-list']//tbody//tr[" + index + "]//div[contains(@class,'select-reason')]//select//option[1]";
        return getText(xpath);
    }

    public void unCheckLineItemClaims() {
        List<WebElement> xpath = getDriver().findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkBox : xpath) {
            focusAndClickElement(checkBox);
        }

    }
}
