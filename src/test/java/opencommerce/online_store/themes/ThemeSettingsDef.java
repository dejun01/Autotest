package opencommerce.online_store.themes;

import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings.CartSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections.*;
import com.opencommerce.shopbase.storefront.steps.shop.*;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.settings.GlobalSettingsSteps;

import java.util.List;

public class ThemeSettingsDef {
    @Steps
    GlobalSettingsSteps globalSettingsSteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    HeaderSteps headerSteps;
    @Steps
    SearchPageSteps searchPageSteps;
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    CollectionDetailSteps collectionDetailSteps;
    @Steps
    ProductSteps productSteps;
    @Steps
    CartSteps cartSteps;

    String theme = LoadObject.getProperty("theme");

    @And("user change header layout as {string}")
    public void userChangeHeaderLayoutAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            themeEditorSteps.navigateToSectionsTab();
            themeEditorSteps.openSessionBlock("Header");
            headerSteps.chooseLayoutHeaderInside(layout);
            themeEditorSteps.saveSetting();
            themeEditorSteps.closeSessionSetting();
        }
    }

    @And("user change search settings as {string}")
    public void userChangeSearchSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean enableSort = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable sort"));
            String logic = SessionData.getDataTbVal(dataTable, row, "Default logic");
            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickSection("Global settings");
            globalSettingsSteps.checkEnableSort(enableSort);
            globalSettingsSteps.selectLogic(logic);
            themeEditorSteps.saveSetting();
        }

    }

    @And("verify show sort on Store front as {string}")
    public void verifyShowSortOnStoreFrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String keySearch = SessionData.getDataTbVal(dataTable, row, "Key search");
            boolean showSortModalSearch = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sort on modal search"));
            boolean showSortSearchPage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sort on search result page"));
            String logic = SessionData.getDataTbVal(dataTable, row, "Default logic");
            String param = SessionData.getDataTbVal(dataTable, row, "Param");
            searchPageSteps.inputKeySearch(keySearch);
            if (showSortModalSearch) {
                searchPageSteps.verifyShowSearchResult(true);
            }
            if (theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
                searchPageSteps.verifySortOnModalSearch(showSortModalSearch, logic);
                searchPageSteps.enterSearch();
            }
            if (theme.equals("roller")) {
                searchPageSteps.clickBtnSearch();
            }
            searchPageSteps.verifySortOnSearchPage(showSortSearchPage, logic);
            if (!param.isEmpty()) {
                searchPageSteps.verifyParam(keySearch, param);
            }
        }
    }

    @And("input key search with {string}")
    public void inputKeySearchWith(String key) {
        searchPageSteps.inputKeySearch(key);
        if (theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
            searchPageSteps.enterSearch();
        } else {
            searchPageSteps.clickBtnSearch();
        }
    }

    @Then("choose sort option and verify sort result as {string}")
    public void chooseOptionAndVerifyResult(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Sort option");
            String result = SessionData.getDataTbVal(dataTable, row, "Sort result");
            String products = SessionData.getDataTbVal(dataTable, row, "Products handle");
            String keySearch = SessionData.getDataTbVal(dataTable, row, "Key search");
            searchPageSteps.chooseOption(option);
            searchPageSteps.verifySearchResult(result,keySearch);
            //     searchPageSteps.verifyProducts(products);
        }
    }

    @And("user change global settings as {string}")
    public void userChangeGlobalSettingsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean sortProduct = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sort in collection page"));
            String pagination = SessionData.getDataTbVal(dataTable, row, "Pagination");
            String imgDisplay = SessionData.getDataTbVal(dataTable, row, "Product image display");
            String contentAlign = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            boolean showSale = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sales banner"));
            boolean showAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Add to cart button"));
            String shape = SessionData.getDataTbVal(dataTable, row, "Shape");
            boolean showTopIcon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable back to top icon"));
            themeEditorSteps.clickSettingTab();
            themeEditorSteps.clickSection("Global settings");
            globalSettingsSteps.checkSortProduct(sortProduct);
            globalSettingsSteps.selectPagination(pagination);
            globalSettingsSteps.selectImgDisplay(imgDisplay);
            globalSettingsSteps.selectContentAlign(contentAlign);
            globalSettingsSteps.checkShowSale(showSale);
            globalSettingsSteps.checkShowAddToCart(showAddToCart);
            globalSettingsSteps.selectShape(shape);
            globalSettingsSteps.checkShowTopIcon(showTopIcon);
            themeEditorSteps.saveSetting();
        }
    }

    @And("verify global setting on storefront as {string}")
    public void verifyGlobalSettingOnStorefrontAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            boolean showSort = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sort in collection page"));
            String pagination = SessionData.getDataTbVal(dataTable, row, "Pagination");
            String imgDisplay = SessionData.getDataTbVal(dataTable, row, "Product image display");
            String contentAlign = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            boolean showSale = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sales banner"));
            boolean showAddToCart = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show Add to cart button"));
            String shape = SessionData.getDataTbVal(dataTable, row, "Shape");
            boolean showTopIcon = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable back to top icon"));
            productSteps.openPage(page);
            if (page.equalsIgnoreCase("collections/summer")) {
                collectionDetailSteps.verifyShowSort(showSort);
                collectionDetailSteps.verifyPagination(pagination);
            }
            homePageSteps.verifyImgProductCard(imgDisplay);
            homePageSteps.verifyAlignProductCard(contentAlign);
            homePageSteps.verifyShowSale(showSale);
            homePageSteps.verifyShowBtnAddToCart(showAddToCart);
            homePageSteps.verifyShape(shape);
            homePageSteps.verifyShowTopIcon(showTopIcon);
        }
    }

    @When("^user navigate to \"([^\"]*)\" tab$")
    public void userNavigateTab(String tabName){
        themeEditorSteps.clickSettingTab();
    }

    @When("^select section \"([^\"]*)\"$")
    public void selectSection(String sectionName){
        themeEditorSteps.clickSection(sectionName);
    }

    @When("^change setting cart goal \"([^\"]*)\"$")
    public void changeSectionCartGoal(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String message = SessionData.getDataTbVal(dataTable, row, "Motivational message");
            boolean enableCartGoal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable cart goal"));
            String amount = SessionData.getDataTbVal(dataTable, row, "Goal amount");
            String goalReached = SessionData.getDataTbVal(dataTable, row, "Goal reached");

            cartSteps.checkCheckBoxWithLabel("Enable Cart Goal", enableCartGoal);
            cartSteps.inputTextBoxWithLabel("Motivational message", message);
            cartSteps.inputTextBoxWithLabel("Goal amount", amount);
            cartSteps.inputTextBoxWithLabel("Goal reached", goalReached);
            themeEditorSteps.saveSetting();
        }
    }

    @When("^verify cart goal work on sf \"([^\"]*)\"$")
    public void verifyMotivationalMessage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String message = SessionData.getDataTbVal(dataTable, row, "Motivational message");
            boolean enableCartGoal = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable cart goal"));
            double amount = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Goal amount"));
            String goalReached = SessionData.getDataTbVal(dataTable, row, "Goal reached");
            String products = SessionData.getDataTbVal(dataTable, row, "Products");
            double totalPrice = Double.parseDouble(SessionData.getDataTbVal(dataTable, row, "Total price"));

            productSteps.addMultipleProductsToCart(products);
            productSteps.goToCart();

            if(enableCartGoal) {
                cartSteps.verifyCartGoalOnSF(message, goalReached, amount, totalPrice);
            }else {
                cartSteps.verifyShowCartGoalOnSF(enableCartGoal);
            }
        }
    }


}

