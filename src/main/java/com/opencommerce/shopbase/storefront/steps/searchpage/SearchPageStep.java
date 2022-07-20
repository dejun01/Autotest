package com.opencommerce.shopbase.storefront.steps.searchpage;

import com.opencommerce.shopbase.storefront.pages.searchpage.SearchPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class SearchPageStep extends ScenarioSteps {
    SearchPage searchPage;
    String shop = LoadObject.getProperty("shop");


    @Step
    public void inputToSearchBox(String value) {
        searchPage.inputToSearchBox(value);
    }

    @Step
    public void clickOnSearchIcon() {
        searchPage.clickOnSearchIcon();
    }

    @Step
    public void verifySearchResult() {
        searchPage.verifySearchResult();
    }

    @Step
    public void verifyNoResultNoti(String prod) {
        searchPage.verifyNoResultNoti(prod);
    }

    @Step
    public void verifyMatchedResult(String value) {
        searchPage.verifyMatchedResult(value);
    }

    @Step
    public void verifyKeywordMatchedProduct(String value) {
        searchPage.verifyKeywordMatchedProduct(value);
    }
}
