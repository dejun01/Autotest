package opencommerce.appstore;

import com.opencommerce.shopbase.appstore.steps.AppDetailSteps;
import com.opencommerce.shopbase.appstore.steps.CollectionSteps;
import com.opencommerce.shopbase.appstore.steps.HomeSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.AppStoreSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AppStoreDef {
    @Steps
    AppStoreSteps asStep;
    @Steps
    HomeSteps homeSteps;
    @Steps
    AppDetailSteps appSteps;
    @Steps
    CollectionSteps collectionSteps;
    public String email = "";
    public String shop = "";

    @Then("^verify app list empty page$")
    public void verifyAppListEmpty() {
        asStep.verifyElementOnPage();
    }

    @When("^verify action click any app name$")
    public void verifyActionClickAnyAppName() {
        asStep.verifyActionClickAnyAppName();
    }


    @Then("^implement uninstall app \"([^\"]*)\"$")
    public void uninstall_app_DSC(String appName) {
        asStep.uninstallApp(appName);

    }


    @Then("^verify fulfillment service of \"([^\"]*)\" after uninstalling app DSC should be updated on order details$")
    public void verify_fulfillment_servive_on_order_details(String productName) {
        asStep.verifyFulfillmentServiceOnOrderDetail(productName);
    }

    @And("^select app \"([^\"]*)\" on Apps list$")
    public void selectAppOnAppsList(String appName) {
        asStep.selectApp(appName);
    }

    @And("^select Print Hub on Apps list$")
    public void selectPrintHubOnAppsList() {
        asStep.selectPrintHubOnAppsList();
    }

    @And("verify list Collection in sidebar menu")
    public void verifyListCollectionInSidebarMenu() {
        List<String> collectionInAPI = homeSteps.get4Collection();
        homeSteps.verifyListCollectionInSidebarMenu(collectionInAPI);
    }

    @Given("^open \"([^\"]*)\" in App store$")
    public void openInAppStore(String page) {
        String url = LoadObject.getProperty("appstorelink");
        if (page.contains("App"))
            url = url + (page.split(">")[1]);
        else if (page.contains("Collection"))
            url = url + "collection/" + (page.split(">")[1]);
        else if (page.contains("Category"))
            url = url + "category/" + (page.split(">")[1]);
        homeSteps.navigateTolink(url);
        homeSteps.maximizeWindow();
    }

    @Then("verify list categories display in home appstore")
    public void verifyListCategoriesDisplayInHomeAppstore() {
        HashMap<String, List<String>> categoryAPI = homeSteps.getListCategory();
        homeSteps.verifyCategoryInHome(categoryAPI);
    }

    @And("verify list category in sidebar menu")
    public void verifyListCategoryInSidebarMenu() {
        HashMap<String, List<String>> category = homeSteps.getListCategory();
        List<String> categoryName = new ArrayList<>();
        for (String i : category.keySet()) {
            categoryName.add((category.get(i)).get(0));
        }
        homeSteps.verifyListCategoryInSidebarMenu(categoryName);

    }

    @When("verify list collections display in home appstore")
    public void verifyListCollectionsDisplayInHomeAppstore() {
        HashMap<String, List<String>> colAPI = homeSteps.getListCollection();
        homeSteps.verifyCollectionInHome(colAPI);
    }

    @And("verify apps of each collection")
    public void verifyAppsOfEachCollection() {
        List<String> collection = homeSteps.getlistCollectionInHome();
        for (String i : collection) {
            HashMap<String, List<String>> actApp = homeSteps.getListAppOfCollectionInHome(i);
            HashMap<String, List<String>> apiApp = homeSteps.getAppsEachCollectionInHomeByApi(i);
            homeSteps.verifyAppsOfEachCollection(actApp, apiApp);
        }
    }

    @When("search app in App store")
    public void searchAppInAppStore(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String appName = SessionData.getDataTbVal(dataTable, row, "App name");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            homeSteps.enterKeyword(appName);
            homeSteps.verifyResult(result, appName);
        }
    }

    @When("^verify App's information of \"([^\"]*)\"$")
    public void verifyAppSInformation(String appName) {
        String id = homeSteps.getAppID(appName);
        homeSteps.clickApp(appName);
        List<String> appinfo = homeSteps.getAppDetailbyID(id);
        appSteps.verifyLogo(appinfo.get(0));
        appSteps.verifyName(appinfo.get(1));
        appSteps.verifyRating(appinfo.get(2), appinfo.get(3));
        appSteps.verifyHandle(appinfo.get(4));
        appSteps.verifyCTABeforeInstall(appinfo.get(5));
//        appSteps.verifyCTAAfterInstall(appinfo.get(6));
        appSteps.verifyShortDesc(appinfo.get(7));
        appSteps.verifyScreenShot(appinfo.get(8));
        appSteps.verifyDesciption(appinfo.get(9));
//        appSteps.verifyDevWeb(appinfo.get(10));
//        appSteps.verifySupportURL(appinfo.get(11));
        appSteps.verifyPrice(appinfo.get(12), appinfo.get(13));
    }

    @When("^verify \"([^\"]*)\"'s information is \"([^\"]*)\" in listing page$")
    public void verifyCollectionSInformation(String pageType, String name) {
        String handle = "";
        if (pageType.equalsIgnoreCase("Collection")) {
            handle = homeSteps.getCollectionHandle(name);
        } else {
            handle = homeSteps.getCategoryHandle(name);
        }
        HashMap<Integer, List<String>> apps = homeSteps.getListAppOfPageByApi(pageType, handle);
        int count = collectionSteps.getNumberAppOfPage();
        assertThat(apps.size()).isEqualTo(count);
        collectionSteps.verifyPageTile(name);
        collectionSteps.verifyDescOfpage(pageType, handle);
        for (int i : apps.keySet()) {
            List<String> appInfo = apps.get(i);
            System.out.println("appInfo: " + appInfo);
            collectionSteps.verifyAppName(appInfo.get(0), (i + 1));
            collectionSteps.verifyShortDescription(appInfo.get(1), (i + 1));
            collectionSteps.verifyPrice(appInfo.get(2), appInfo.get(3), (i + 1));
            collectionSteps.verifyRating(appInfo.get(0), appInfo.get(4), appInfo.get(5));
            collectionSteps.verifyLogo(appInfo.get(6), (i + 1));
        }
    }

    @When("^click see all of collection is \"([^\"]*)\" in Homepage$")
    public void clickSeeAllOfCollectionIsInHomepage(String collectionName) {
        Boolean isExisted = homeSteps.isExistedSeeAll(collectionName);
        if (isExisted) {
            homeSteps.clickSeeAll(collectionName);
        } else {
            homeSteps.navigateToCollectionPage(collectionName);
        }
    }

    @Then("^click category is \"([^\"]*)\" in sidebar menu$")
    public void clickCategoryIsInSidebarMenu(String name) {
        homeSteps.clickCategoryName(name);
    }
}
