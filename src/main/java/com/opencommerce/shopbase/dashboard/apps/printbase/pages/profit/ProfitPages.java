package com.opencommerce.shopbase.dashboard.apps.printbase.pages.profit;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfitPages extends SBasePageObject {
    public ProfitPages(WebDriver driver) {
        super(driver);
    }

    public float getBaseCostVariant(String products, String variants) {
        String xpathBaseCost = "//div[text()[normalize-space()='" + products + "']]//ancestor::*[@class='body-row']//following-sibling::tr//td[text()[normalize-space()='" + variants + "']]/following-sibling::*[contains(@class,'cost-body')]";
        String getValue = getText(xpathBaseCost);
        float baseCost = Float.parseFloat(getValue.replace("$", ""));
        return baseCost;
    }

    public void clickOrderNumber(String numberOrder) {
        clickOnElement("//*[@class='cursor-default']//a[@class='order-link'][text()[normalize-space()='" + numberOrder + "']]");
        waitForEverythingComplete();
        waitABit(3000);
    }

    public float getSubToTalInAdminShop() {
        String xpathSubtotal = "//*[text()[normalize-space()='Subtotal']]//ancestor::tbody//div";
        float subTotal = Float.parseFloat(getText(xpathSubtotal).replace("$", "").replace(" ", ""));
        return subTotal;
    }


    public float getProcessingRate() {
        String xpathProcessingRate = "//tbody//*[contains(text(),'Processing Fee')]";
        float processingRate = Float.parseFloat(getText(xpathProcessingRate).replace("Processing Fee ", "").replace("%", "").replace("(","").replace(")",""));
        return processingRate;
    }

    public float getProfitInAdminShop() {
        String xpathProfit = "//*[text()[normalize-space()='PROFIT']]//ancestor::tbody//div";
        float profit = Float.parseFloat(getText(xpathProfit).replace("$", "").replace(" ", ""));
        return profit;
    }

    public void waitForTitleViewShopDisplay() {
        waitUntilElementVisible("//div[@class='row balance-manager']//*[text()[normalize-space()='View history']]", 30);
    }

    public void verifyDetailField(String numberOrder,Float profit) {
        String xpathContent = "(//div[@class='row balance-manager']//span[text()[normalize-space()='PrintBase order collecting']])[1]";
        clickOnElement(xpathContent);
        waitForEverythingComplete(60);

        String xpathTitle = "//div[@class='heading']//*[text()[normalize-space()='Invoice detail']]";
        waitForElementToPresent(xpathTitle);

        String xpath = "//div[@class='d-flex flex-column']//div[contains(text(),'Charged from the order "+numberOrder+"')]";
        verifyElementPresent(xpath, true);

        String xpathAmount = "(//div[@class='d-flex flex-column'])[3]//div[2]";
        Float profitActual = getPrice(xpathAmount);
        Assert.assertEquals(profitActual, profit);


    }

    public void verifyAmount(Float profit) {
        String xpathAmount = "(//span[text()[normalize-space()='PrintBase order collecting']]/following::td)[1]/span";
        Float profitActual = getPrice(xpathAmount);
        Assert.assertEquals(profitActual, profit);
    }

    public void verifyFee() {
        String xpathFee = "(//*[@class='text-right']//span)[2]";
        String fee = getText(xpathFee);
        Assert.assertEquals(fee, "$0.00");
    }

    public void verifyNet(float profit) {
        String xpathAmount = "(//*[@class='text-right']//span)[3]";
        float netActual = Float.parseFloat(getText(xpathAmount).replace("$", ""));
        assertThat(netActual).isEqualTo(profit);
    }

    public void clickBtnAddToCart() {
        String xpath = "(//button[preceding-sibling::*[text()[normalize-space()='Add to cart']] or descendant-or-self::*[text()[normalize-space()='Add to cart']]])[1]";
        String getAttribute = getAttributeValue(xpath,"style");
        if(getAttribute.contains("display: none;")){
            clickBtn("Add to cart",2);
        }else {
            clickBtn("Add to cart");
        }
    }

}
