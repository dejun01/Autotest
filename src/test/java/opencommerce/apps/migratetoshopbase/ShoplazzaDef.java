package opencommerce.apps.migratetoshopbase;

import com.opencommerce.shopbase.dashboard.apps.migratetoshopbase.ShoplazaSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ShoplazzaDef {
    String shopname = LoadObject.getProperty("shopname");

    @Steps
    ShoplazaSteps shoplazaSteps;

    @When("^navigate to \"([^\"]*)\" in migrate app$")
    public void navigateToTabInMigrateApp(String tabName) {
        shoplazaSteps.switchtoWindowMigrateApp();
        shoplazaSteps.loginMigrateApp(shopname);
        shoplazaSteps.navigateToTab(tabName);
    }

    @And("import product from \"([^\"]*)\" by enter URLs")
    public void importProductFromShoplazzaByEnterURLs(String platform, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String url = SessionData.getDataTbVal(dataTable, row, "URLs");
            shoplazaSteps.selectPlatform(platform);
            shoplazaSteps.selectDataInput("Enter URLs");
            shoplazaSteps.enterURL(url);
            shoplazaSteps.clickImport();
            shoplazaSteps.navigateToTab("Import List");
            shoplazaSteps.verifyQueueImport(url);
        }
        shoplazaSteps.switchtoShopBase();
    }

    @And("^import product from \"([^\"]*)\" by file csv is \"([^\"]*)\"$")
    public void importProductFromShoplazzaByFileCsvIs(String platform, String file,  List<List<String>> dataTable) {
        String url = "";
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
             url = SessionData.getDataTbVal(dataTable, row, "URLs in file");
        }
        shoplazaSteps.selectPlatform(platform);
        shoplazaSteps.selectDataInput("Upload CSV file");
        shoplazaSteps.choosefileCSV(file);
        shoplazaSteps.clickImport();
        shoplazaSteps.navigateToTab("Import List");
        shoplazaSteps.verifyQueueImport(url);
        shoplazaSteps.switchtoShopBase();
    }

}
