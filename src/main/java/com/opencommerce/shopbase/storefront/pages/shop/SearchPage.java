package com.opencommerce.shopbase.storefront.pages.shop;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import static org.assertj.core.api.Java6Assertions.assertThat;

public class SearchPage extends SBasePageObject {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    String theme = LoadObject.getProperty("theme");
    String shop = LoadObject.getProperty("shop");

    public void verifyShowSort(boolean isShow) {
        String xpath ;
        if (theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
            xpath =  "//div[contains(@class,'flex')]//div[contains(@class,'base-select')]";
        }else{
            xpath = "//div[contains(@class,'flex-wrap')]//div[contains(@class,'base-select')]";
        }
        verifyElementPresent(xpath, isShow);
    }

    public void clickSearchIcon() {
        String xpath = "//div[@class='header-wrapper']//a[@class='search-icon relative flex align-center justify-center']//*[@class='svg-container']";
        clickOnElementByJs(xpath);
    }

    public void inputKeySearch(String keySearch) {
        String xpath = "//div[@class='relative input-base w-100' or @class='flex flex-wrap']//input[@type ='search' or @placeholder='Enter keywords...']";
        waitClearAndType(xpath, keySearch);
    }

    public void clickOnSearchBox(){
        String xpath = "//input[@type ='search' or @placeholder='Enter keywords...']";
        clickOnElementByJs(xpath);
    }

    public void verifyDefaultLogic(String logic) {
        String xpath;
        if(theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
            xpath = "//div[contains(@class,'flex')]//div[contains(@class,'base-select')]//select";
        }else {
            xpath = "//div[contains(@class,'flex-wrap')]//div[contains(@class,'base-select')]//select";
        }
        String defaultLogic = getSelectedValueDdl(xpath);
        assertThat(defaultLogic).isEqualTo(logic);
    }

    public void verifyShowSearchResult(boolean isShow) {
        String xpath = "//div[@class='search-modal__products mb16 pb16']";
        verifyElementPresent(xpath, isShow);
    }

    public void verifyParam(String keySearch, String param) {
        String currentUrl = getCurrentUrl();
        String url = "https://" + LoadObject.getProperty("shop") + "/search?" + param + "&q=" + keySearch + "";
        assertThat(currentUrl).isEqualTo(url);
    }

    public void chooseOption(String option) {
        String xpath = "//div[@class='base-select flex relative' or @class='select-box search-sort w-100 mt8']//select";
        selectDdlByXpath(xpath, option);
    }

    public void verifySearchResult(String result, String keySearch) {
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl).contains("https://"+shop+ "/search?" + result+keySearch);
    }

    public void verifyProducts(String product, int j) {
        String xpath = "(//div[@class='collection-product-wrap relative']//a)[" + j + "]";
        String handleAR = getAttributeValue(xpath, "href");
        String handleER = "/products/" + product + "";
        assertThat(handleAR).isEqualTo(handleER);
    }

    public void clickBtnSearch() {
        String xpath = "//button[contains(@class,'icon-fallback-text')]";
        clickOnElement(xpath);
    }

    public void enterSearch() {
        String xpath ="//div[@class='relative input-base w-100' or @class='flex flex-wrap']//input[@type ='search' or @placeholder='Enter keywords...']";
        XH(xpath).sendKeys(Keys.ENTER);
    }
}
