package com.opencommerce.shopbase.storefront.pages.gateway;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardPayPage extends SBasePageObject {
    public CardPayPage(WebDriver driver) {
        super(driver);
    }

    public void inputCardNumber(String cardNo){
        String xPath = "//input[@id='input-card-number']";
        clickThenTypeCharByChar(xPath,cardNo);
    }

    public void inputCardHolder(String cardHolderName){
        String xPath = "//input[@id='input-card-holder']";
        clickThenTypeCharByChar(xPath,cardHolderName);
    }

    public void inputCVV(String cvv){
        String xPath = "//input[@id='input-card-cvc']";
        clickThenTypeCharByChar(xPath,cvv);
    }

    private void selectExpiredMonth(String month){
        String xPath_Select = "//select[@id='card-expires-month']";
        String xPath_Option = xPath_Select+"//option[text()='"+ month +"' or @value='"+ month +"']";
        clickOnElement(xPath_Option);
        clickOnElement(xPath_Select);
    }

    private void selectExpiredYear(String year){
        if(year.length()<=2){
            year = "20"+year;
        }
        String xPath_Select = "//select[@id='card-expires-year']";
        String xPath_Option = xPath_Select+"//option[text()='"+ year +"' or @value='"+ year +"']";
        clickOnElement(xPath_Option);
        clickOnElement(xPath_Select);
    }

    public void selectExpires(String expries){
        String month = "";
        String year = "";
        if (expries.contains("/")) {
            String[] arrExpries = expries.split("/");
            month = arrExpries[0];
            year = arrExpries[1];
        }
        selectExpiredMonth(month);
        selectExpiredYear(year);
    }

    public void verifyTotalAmount(String expectedAmt){
        String xPath_Amt = "//*[@id='total-amount']";
        String xPath_Currency = "//*[@id='currency']";
        String actual_TotalAmt = getTextValue(xPath_Amt);
        String actual_Currency = getTextValue(xPath_Currency);
        String currencySymbol = String.valueOf(expectedAmt.charAt(0));
        String[] arrExpectedAmt = expectedAmt.split("\\s");
        String currency = "";
        if(arrExpectedAmt.length>1) {
            currency = arrExpectedAmt[1];
        }
        if(!isNumeric(currencySymbol)){
            expectedAmt = expectedAmt.replace(currencySymbol,"").replace(currency,"").trim();
        }
        assertThat(expectedAmt).containsIgnoringCase(actual_TotalAmt);
        if(!currency.isEmpty()){
            assertThat(currency).containsIgnoringCase(actual_Currency);
        }
    }

    public void submit3DS(String status){
        clickOnElementByID(status);
        waitForEverythingComplete();
//        waitABit(5000);
    }

    public void waitUntilBackToShopBase() {
        waitElementToBeVisible("//h2[@class='os-header__title' and text()[normalize-space()='Thank you!']]", 60);
    }
}
