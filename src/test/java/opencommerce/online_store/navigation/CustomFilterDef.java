package opencommerce.online_store.navigation;

import com.opencommerce.shopbase.dashboard.online_store.navigation.steps.CustomFilterSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorV2Steps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class CustomFilterDef {
    @Steps
    CustomFilterSteps customFilterSteps;

    @Steps
    ThemeEditorV2Steps themeEditorV2Steps;

    String shop = LoadObject.getProperty("shop");
    int countOption;
    int countOptionAfterRemove;

    @When("^setting status filter \"([^\"]*)\"$")
    public void settingStatusFilter(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean enableFiltering = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Enable filtering"));
            String filterTitle = SessionData.getDataTbVal(dataTable, row, "Filter title");
            themeEditorV2Steps.checkCheckBoxWithLabel("Enable filtering", enableFiltering);
            if(!filterTitle.isEmpty()){
                themeEditorV2Steps.inputTextBoxWithLabel("Filter title", filterTitle);
            }
        }
    }

    @Then("^verify show filter on storefront \"([^\"]*)\"$")
    public void verifyShowFilterSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            Boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Status"));
            String filterTitle = SessionData.getDataTbVal(dataTable, row, "Filter title");
            customFilterSteps.reloadPage();
            customFilterSteps.verifyShowDroplistFilter(status);
            if(status) {
                customFilterSteps.clickDroplistFilter();
                if (!filterTitle.isEmpty()) {
                    customFilterSteps.verifyFilterTitle(filterTitle.toUpperCase());
                }
            }
        }
    }

    @When("^setting option filter \"([^\"]*)\"$")
    public void settingOptionFilter(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            if(action.equalsIgnoreCase("Remove all")){
                countOption = customFilterSteps.countOption();
                for(int i=0; i<countOption; i++)
                    customFilterSteps.removeOption();
                countOptionAfterRemove = customFilterSteps.countOption();
                customFilterSteps.verifyFilter(countOptionAfterRemove, 0);
            }
            else{
                customFilterSteps.clickButton("Add filter");
                if(customFilterSteps.existShowMore())
                    customFilterSteps.clickButton("Show more");
                int countOptionPopup = customFilterSteps.countOptionPopup();
                for(int i=1; i<=countOptionPopup; i++)
                    customFilterSteps.addFilter(i);
                customFilterSteps.clickButton("Save");
                countOption = customFilterSteps.countOption();
                customFilterSteps.verifyFilter(countOption, countOptionPopup);
            }
        }
    }

    @When("^click droplist Filter$")
    public void clickDroplist() {
        customFilterSteps.clickDroplistFilter();
    }

    @Then("^verify show option in drawer filter \"([^\"]*)\"$")
    public void verifyShowOptionSF (String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            Boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Status"));
            if(status){
                customFilterSteps.clickDroplistFilter();
                customFilterSteps.existOptionFilter(status);
            }
            else customFilterSteps.verifyShowDroplistFilter(status);
        }
    }

    @And("^filter option on SF \"([^\"]*)\"$")
    public void filterOptionSF (String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String availability = SessionData.getDataTbVal(dataTable, row, "availability");
            String priceFrom = SessionData.getDataTbVal(dataTable, row, "price from");
            String priceTo = SessionData.getDataTbVal(dataTable, row, "price to");
            String productTypes = SessionData.getDataTbVal(dataTable, row, "product type");
            String brand = SessionData.getDataTbVal(dataTable, row, "brand");
            String color = SessionData.getDataTbVal(dataTable, row, "color");
            String size = SessionData.getDataTbVal(dataTable, row, "size");
            String style = SessionData.getDataTbVal(dataTable, row, "style");
            String clearAll = SessionData.getDataTbVal(dataTable, row, "Clear all");
            String apply = SessionData.getDataTbVal(dataTable, row, "Apply");

            if(!availability.isEmpty()){
                customFilterSteps.checkCheckboxFilter("Available only", Boolean.parseBoolean(availability));
            }
            customFilterSteps.inputPrice("From", priceFrom);
            customFilterSteps.inputPrice("To", priceTo);
            if(!productTypes.isEmpty()){
                String[] productTypeList = productTypes.split(",");
                for (String s : productTypeList) {
                    themeEditorV2Steps.checkCheckBoxWithLabel(s, true);
                }
//                themeEditorV2Steps.checkCheckboxWithSpan(productTypes, true);
            }
            if(!brand.isEmpty()){
                customFilterSteps.checkCheckboxFilter(brand, true);
            }
            if(!color.isEmpty()){
                customFilterSteps.checkCheckboxFilter(color, true);
            }
            if(!size.isEmpty()){
                customFilterSteps.checkCheckboxFilter(size, true);
            }
            if(!style.isEmpty()){
                customFilterSteps.checkCheckboxFilter(style, true);
            }
            if(!clearAll.isEmpty()){
                customFilterSteps.clickButton("Clear all");
            }
            if(!apply.isEmpty()){
                customFilterSteps.clickButton("Apply");
            }
        }
    }

    @Then("^verify tag filter \"([^\"]*)\"$")
    public void verifyTagFilter (String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            if(tags.isEmpty()){
                customFilterSteps.verifyNotShowTag(false);
            }
            else {
                String[] tagList = tags.split(",");
                for (int i = 0; i < tagList.length; i++) {
                    customFilterSteps.verifyShowTag(tagList[i]);
                }
                customFilterSteps.verifyShowTag("Clear all");
            }
        }
    }

    @Then("^verify filter results \"([^\"]*)\"$")
    public void verifyFilterResult (String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String results = SessionData.getDataTbVal(dataTable, row, "Result");
            if(results.equalsIgnoreCase("No matching products found")){
                customFilterSteps.verifyText(results);
            }
            else {
                String[] resultList = results.split(",");
                for (int i = 0; i < resultList.length; i++) {
                    customFilterSteps.verifyResult(resultList[i]);
                }
            }
        }
    }

}
