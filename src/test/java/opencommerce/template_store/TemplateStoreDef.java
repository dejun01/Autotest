package opencommerce.template_store;

import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ManageThemeSteps;
import com.opencommerce.shopbase.dashboard.template_store.steps.TemplateStoreSteps;
import com.opencommerce.shopbase.hive_pbase.step.HiveStep;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class TemplateStoreDef {
    @Steps
    TemplateStoreSteps templateStoreSteps;

    @Steps
    HiveStep hiveStep;

    @Steps
    ManageThemeSteps manageThemeSteps;

    public String nameTemplate = "";
    public String nameStore = "";
    public String nameFirstTemplate = "";
    public String themeFirstTemplate = "";
    public String categoriesFirstTemplate = "";
    public int countAllTemplate = 0;
    public int countTemplateIndustry = 0;
    public int countTemplateTheme = 0;
    public int countTemplateIndustryTheme = 0;

    @When("^click on button \"([^\"]*)\" template store$")
    public void clickButtonLogin(String label){
        templateStoreSteps.clickBtn(label);
    }

    @Then("^verify show Preview template$")
    public void verifyShowPreviewTemplate(){
        nameTemplate = templateStoreSteps.getNameTemplateFirst();
        templateStoreSteps.clickBtnPreviewFirst ();
        templateStoreSteps.verifyPreviewTemplate(nameTemplate);
    }

    @When("^click button \"([^\"]*)\" on preview detail$")
    public void clickButtonOnPreviewDetail(String label) {
        templateStoreSteps.clickBtnOnPreviewDetail(label);
    }

    @And("^do action in popup$")
    public void doActionInPopup(List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String shopName = SessionData.getDataTbVal(dataTable, row, "Shop");
            Boolean status = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Status"));
            if (shopName.equalsIgnoreCase("shop")) {
                nameStore = LoadObject.getProperty(shopName);
            }
            templateStoreSteps.selectShop(nameStore);
            templateStoreSteps.clickBtnInPopup(action);
        }
    }

    @Then("^verify add template successfully$")
    public void verifyAddTemplateSuccess(){
        templateStoreSteps.verifyMessageAddTemplate(nameTemplate + " was added to your store");
        manageThemeSteps.verifyLastSaved("Just now");
    }

    @When("^filter template active and visible$")
    public void filterTemplateActiveVisible(){
        hiveStep.switchToTab("Theme");
        hiveStep.switchToTab("Templates");
        hiveStep.clickButtonFilter();
        hiveStep.checkCheckboxInDropdown("visible");
        hiveStep.checkCheckboxInDropdown("active");
        hiveStep.clickAndSelectValue("Visible","yes");
        hiveStep.clickAndSelectValue("Active","yes");
        hiveStep.clickButtonFilter();
        hiveStep.clickBtnFilterData();
    }

    @Then("^get detail information first template store$")
    public void getInforFirstTemplateStore() {
        nameFirstTemplate = hiveStep.getNameTemplate(1);
        themeFirstTemplate = hiveStep.getThemeTemplate(1);
        categoriesFirstTemplate = hiveStep.getCategoryTemplate(1);
        countAllTemplate = hiveStep.getNumberTemplate();
    }

    @Then("^get detail information template store$")
    public void getInforTemplateStore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String industry = SessionData.getDataTbVal(dataTable, row, "industry");
            String theme = SessionData.getDataTbVal(dataTable, row, "theme");
            if(!industry.isEmpty() && theme.isEmpty()){
                countTemplateIndustry = hiveStep.getTemplate(industry, 7);
            }
            if(!theme.isEmpty() && industry.isEmpty()){
                countTemplateTheme = hiveStep.getTemplate(theme, 5);
            }
            if(!industry.isEmpty() && !theme.isEmpty()){
                countTemplateIndustryTheme = hiveStep.getTemplateIndustryTheme(theme, industry);
            }
        }
    }

    @Then("^verify filter template$")
    public void verifyFilterTemplate(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "action");
            String industry = SessionData.getDataTbVal(dataTable, row, "industry");
            String theme = SessionData.getDataTbVal(dataTable, row, "theme");
            templateStoreSteps.clickClearAll();
            templateStoreSteps.verifyResultCountTemplate(countAllTemplate);

            if(action.equalsIgnoreCase("check")){
                if(!industry.isEmpty() && theme.isEmpty()){
                    templateStoreSteps.checkOption(industry, true);
                    templateStoreSteps.verifyResultCountTemplate(countTemplateIndustry);
                }
                if(!theme.isEmpty() && industry.isEmpty()){
                    templateStoreSteps.checkOption(theme, true);
                    templateStoreSteps.verifyResultCountTemplate(countTemplateTheme);
                }
                if(!industry.isEmpty() && !theme.isEmpty()){
                    templateStoreSteps.checkOption(industry, true);
                    templateStoreSteps.checkOption(theme, true);
                    templateStoreSteps.verifyResultCountTemplate(countTemplateIndustryTheme);
                }
            }
            else {
                templateStoreSteps.clickClearAll();
                templateStoreSteps.searchTemplate(nameFirstTemplate);
                templateStoreSteps.verifyNameTemplate(nameFirstTemplate);
            }
        }
    }


    @When("^search and click template store$")
    public void searchAndClickTemplate() {
        templateStoreSteps.clickButtonSearch();
        templateStoreSteps.enterTemplate(nameFirstTemplate);
        templateStoreSteps.clickNameTemplate(nameFirstTemplate);
    }

    @Then("^verify detail template$")
    public void verifyDetailTemplate(){
        templateStoreSteps.verifyNameTemplateDetail(nameFirstTemplate);
        templateStoreSteps.verifyThemeTemplate(themeFirstTemplate);
        templateStoreSteps.verifyCategory(categoriesFirstTemplate);
    }

    @When("^click on button view all template store$")
    public void clickOnButtonViewAll(){
        templateStoreSteps.clickAllTemplate();
    }
}
