package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.SearchPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class SearchPageSteps extends ScenarioSteps {
    SearchPage searchPage;
    String theme = LoadObject.getProperty("theme");

    @Step
    public void inputKeySearch(String keySearch) {
        if(theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
            searchPage.clickSearchIcon();
        }else {
            searchPage.clickOnSearchBox();
        }
        searchPage.inputKeySearch(keySearch);
    }

    @Step
    public void verifySortOnModalSearch(boolean showSortModalSearch, String logic) {
        searchPage.verifyShowSort(showSortModalSearch);
        if (showSortModalSearch) {
            searchPage.verifyDefaultLogic(logic);
        }
    }

    @Step
    public void enterSearch() {
        searchPage.enterSearch();
    }

    @Step
    public void verifyShowSearchResult(boolean isShow) {
        searchPage.verifyShowSearchResult(isShow);
    }

    @Step
    public void verifySortOnSearchPage(boolean showSortSeachPage, String logic) {
        searchPage.verifyShowSort(showSortSeachPage);
        if (showSortSeachPage) {
            searchPage.verifyDefaultLogic(logic);
        }
    }

    @Step
    public void verifyParam(String keySerch, String param) {
        searchPage.verifyParam(keySerch, param);
    }

    @Step
    public void chooseOption(String option) {
        searchPage.chooseOption(option);
    }

    @Step
    public void verifySearchResult(String result, String keySearch) {
        searchPage.verifySearchResult(result, keySearch);
    }

    @Step
    public void verifyProducts(String products) {
        String[] product = products.split(",");
        for (int i = 0; i < product.length; i++) {
            int j = i + 1;
            searchPage.verifyProducts(product[i], j);
        }
    }

    @Step
    public void clickBtnSearch() {
        searchPage.clickBtnSearch();
    }
}
