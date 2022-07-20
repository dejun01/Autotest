package opencommerce.online_store.domains;

import com.opencommerce.shopbase.dashboard.authen.steps.LoginDashboardSteps;
import com.opencommerce.shopbase.dashboard.online_store.domains.steps.CDNChinaSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ManageThemeSteps;
import com.opencommerce.shopbase.dashboard.products.steps.CollectionListSteps;
import com.opencommerce.shopbase.dashboard.products.steps.ProductListSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CDNChinaDef{
    @Steps
    CDNChinaSteps cdnChinaSteps;
    @Steps
    ProductListSteps productListSteps;
    @Steps
    CollectionListSteps collectionListSteps;
    @Steps
    LoginDashboardSteps loginSteps;
    @Steps
    ManageThemeSteps manageThemeSteps;

    @Then("switch server to {string}")
    public void switchToChinaServer(String server){
        cdnChinaSteps.switchToChina(server);
    }

    @And("switch language to {string}")
    public void switchLanguage(String language){
        cdnChinaSteps.switchLanguage(language);
    }

    @When("^user navigate to screen \"([^\"]*)\"$")
    public void navigateToScreen(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String screen = SessionData.getDataTbVal(dataTable, row, "Screen");
            String _tabs[] = screen.split(">");
            int level = _tabs.length;
            for (int i = 0; i < level; i++) {
                cdnChinaSteps.switchToTab(_tabs[i]);
            }
            cdnChinaSteps.waitUntilInvisibleLoading(5);
        }
    }

    @When("^search product or collection or page on dashboard \"([^\"]*)\"$")
    public void searchText(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String nameProduct = SessionData.getDataTbVal(dataTable, row, "Name product");
            String nameCollection = SessionData.getDataTbVal(dataTable, row, "Name collection");
            String namePage = SessionData.getDataTbVal(dataTable, row, "Name page");
            if(!nameProduct.isEmpty()){
                productListSteps.searchProduct(nameProduct);
                productListSteps.chooseProduct(nameProduct);
            }
            if(!nameCollection.isEmpty()){
                collectionListSteps.searchCollection(nameCollection);
                collectionListSteps.chooseCollection(nameCollection);
            }
            if(!namePage.isEmpty()){
                cdnChinaSteps.searchPage(namePage);
                cdnChinaSteps.choosePage(namePage);
            }
        }
    }

    @Then("^preview storefront from dashboard \"([^\"]*)\"$")
    public void previewSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            switch (action){
                case "Click preview theme in sidebar":
                    cdnChinaSteps.openThemeInSideBar();
                    break;
                case "Click Preview theme in theme editor":
                    manageThemeSteps.openActionsDropDown("Inside");
                    manageThemeSteps.selectAction("Inside", "Preview");
                default:
                    cdnChinaSteps.clickOnAction("View");
                    break;
            }
        }
    }

    @Then("^verify show China domain on SF \"([^\"]*)\"$")
    public void verifyShowChinaDomainOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            String domain = loginSteps.getDomainLocation();
            assertThat(domain).contains("shopbase.net.cn");
            if(!page.equalsIgnoreCase("Homepage"))
                cdnChinaSteps.verifyNameProductAndCollection(page);
        }
    }

    @Then("verify text on dashboard {string} is displayed")
    public void verify_text_on_dashboard_is_displayed(String key){
        cdnChinaSteps.verifyTextHomeIsTranslated(key);
    }
}
