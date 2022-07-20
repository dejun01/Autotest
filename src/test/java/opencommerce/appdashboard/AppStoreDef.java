package opencommerce.appdashboard;

import com.opencommerce.shopbase.appstore.steps.HomeSteps;
import com.opencommerce.shopbase.common.steps.CommonHiveSteps;
import com.opencommerce.shopbase.dashboard.dashboard.steps.AppStoreSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.questions.ValueOf;
import net.thucydides.core.annotations.Steps;
import static com.opencommerce.shopbase.OrderVariable.*;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;

public class AppStoreDef {
    @Steps
    AppStoreSteps appStoreSteps;
    @Steps
    CommonHiveSteps commonHiveSteps;
    @Steps
    com.opencommerce.shopbase.hive_sbase.steps.AppStoreSteps hiveAppSteps;
    @Steps
    HomeSteps homeSteps;

    public String email = "";
    public String appAccessToken = "";
    public String shop = "";
    String appId = LoadObject.getProperty("appid");
    String collectionId = LoadObject.getProperty("collectionId");
    String categoryID = LoadObject.getProperty("categoryID");
    String featureID = LoadObject.getProperty("featureID");
    public int numberBefore;
    long currentTime = System.currentTimeMillis();


    @Given("^open app store page$")
    public void directToAppStorePage() {
        String urlAppStore = LoadObject.getProperty("url.appstore");
        appStoreSteps.openAppStorePage(urlAppStore);
        appStoreSteps.turnOffSitekitPopup();
    }

    @When("^search app and verify result$")
    public void searchAppAndVerifyResult(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String value_search = SessionData.getDataTbVal(dataTable, row, "Value search");
            String result = SessionData.getDataTbVal(dataTable, row, "Result");
            appStoreSteps.searchApp(value_search);
            if (result.equals("No data")) {
                appStoreSteps.verifySearchNoData(value_search);
            } else {
                appStoreSteps.verifySearchResult(value_search);
            }
        }
    }

    @When("^select appname \"([^\"]*)\"$")
    public void selectAppname(String appName) {
        appStoreSteps.selectAppname(appName);
    }

    @And("^enter info into Start your free trial fields$")
    public void enterInfoIntoStartYourFreeTrialFields(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String pwd = SessionData.getDataTbVal(dataTable, row, "Password");
            String shopname = SessionData.getDataTbVal(dataTable, row, "Shopname");

            appStoreSteps.enterPassword(pwd);
            if (shopname.matches("@(.*)@")) {
                long currentTime = System.currentTimeMillis();
                shopname = shopname.replaceAll("@", "") + "-" + currentTime;
                appStoreSteps.enterShopname(shopname);
            } else {
                appStoreSteps.enterShopname(shopname);
            }
        }
        appStoreSteps.clickSignupButton();
    }

    @Then("^verify direct to homedashboard$")
    public void verifyDirectToHomedashboard() {
        appStoreSteps.verifyDirectToHomedashboard();
    }

    @Then("^Delete \"([^\"]*)\" app$")
    public void deleteApp(String appName) {
        if (appStoreSteps.isAppInstalled(appName)) {
            appStoreSteps.deleteApp(appName);
        }
    }

    @And("^Install selected app \"([^\"]*)\"$")
    public void installSelectedApp(String appName) {
        if (!appStoreSteps.isAppInstalled(appName)) {
            appStoreSteps.goToAppStore();
            appStoreSteps.switchToNewTab();
            appStoreSteps.turnOffSitekitPopup();
            appStoreSteps.searchAppToInstall(appName);
            appStoreSteps.addApp();
            appStoreSteps.installApp();
            appAccessToken = appStoreSteps.getAppAccessToken();
        }
    }

    @And("^Verify app \"([^\"]*)\" installed is \"([^\"]*)\"$")
    public void verifyAppInstalled(String appName, String isInstall) {
        appStoreSteps.goToAppDashboard();
        appStoreSteps.switchToOldTab();
        appStoreSteps.verifyAppInstalled(appName, Boolean.parseBoolean(isInstall));
    }

    @And("^Register webhook \"([^\"]*)\" with App's access token on address \"([^\"]*)\"$")
    public void registerWebhookWithAppSAccessToken(String topic, String address) {
        shop = LoadObject.getProperty("shop");
        appStoreSteps.registerWebhookWithAppSAccessToken(shop, topic, appAccessToken, address);
    }

    @And("^Verify webhook on address \"([^\"]*)\"$")
    public void verifyWebhookOnAddress(String address) throws Exception {
        appStoreSteps.verifyWebhookOnAddress(address, numberBefore, shop);
    }

    @Then("^Get number of webhook before on address \"([^\"]*)\"$")
    public void getNumberOfWebhookBeforeOnAddress(String address) {
        numberBefore = appStoreSteps.getNumberOfWebhookBeforeOnAddress(address);
    }

    //    select app to open dashboard app on installed app list
    @Then("^Select app \"([^\"]*)\" on Apps list$")
    public void selectAppOnAppsList(String appName) {
        appStoreSteps.selectApp(appName);
    }

    @Then("Edit a collections in hive")
    public void editCollectionsInHive(List<List<String>> dataTable) {
        commonHiveSteps.clickEdit(collectionId);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean isActive = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Active"));
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String slug = SessionData.getDataTbVal(dataTable, row, "Slug");
            String desc = SessionData.getDataTbVal(dataTable, row, "Description") + currentTime;
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            String displayType = SessionData.getDataTbVal(dataTable, row, "Display Type");
            String typeApp = SessionData.getDataTbVal(dataTable, row, "Type App");
            String icon = SessionData.getDataTbVal(dataTable, row, "Icon");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            System.out.println("msg: " + msg);

            commonHiveSteps.checkCheckbox("Active", isActive);
            if (!name.isEmpty()) {
                if (!msg.equalsIgnoreCase("name is exist"))
                    name += currentTime + "";
            }
            if (!slug.isEmpty()) {
                if (!msg.equalsIgnoreCase("slug is exist"))
                    slug += currentTime + "";
            }
            commonHiveSteps.enterInput("Collection's name", name);
            commonHiveSteps.enterInput("Slug", slug);
            commonHiveSteps.enterInput("Description", desc);
            commonHiveSteps.enterInput("Priority", priority);
            commonHiveSteps.selectDroplist("Display Type", displayType);
            commonHiveSteps.selectDroplist("Type App", typeApp);
//            commonHiveSteps.uploadImage("Icon", icon);
            commonHiveSteps.clickUpdateAndClose();
            if (!msg.equalsIgnoreCase("Success")) {
                commonHiveSteps.verifyDetailPageDisplay("Original Version");
            } else {
                commonHiveSteps.verifyNameDisplay(name);
                String id = commonHiveSteps.getIdItem(name);
                commonHiveSteps.verifyActive(id, isActive);
                commonHiveSteps.verifyFiledDisplay(id, slug);
                commonHiveSteps.verifyFiledDisplay(id, priority);
                commonHiveSteps.verifyFiledDisplay(id, desc);
                List<String> info = homeSteps.getPageInformation("collection", slug);
                assertThat(info.get(0)).isEqualTo(collectionId);
                assertThat(info.get(1)).isEqualTo(name);
                assertThat(info.get(2)).isEqualTo(desc);
                if (isActive) {
                    assertThat(info.get(4)).isEqualTo(String.valueOf(isActive));
                } else {
                    assertThat(info.get(4)).isEqualTo("0");
                }
                assertThat(info.get(5)).isEqualTo(priority);
                assertThat(info.get(6)).isEqualTo(displayType);
                commonHiveSteps.clickEdit(collectionId);

            }
        }
    }

    @Then("Edit a category in hive")
    public void editCategoryInHive(List<List<String>> dataTable) {
        commonHiveSteps.clickEdit(categoryID);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            Boolean isActive = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Active"));
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String slug = SessionData.getDataTbVal(dataTable, row, "Slug");
            String desc = SessionData.getDataTbVal(dataTable, row, "Description") + currentTime;
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            String icon = SessionData.getDataTbVal(dataTable, row, "Icon");
            String background = SessionData.getDataTbVal(dataTable, row, "Background");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            System.out.println("msg: " + msg);

            commonHiveSteps.checkCheckbox("Active", isActive);
            if (!name.isEmpty()) {
                if (!msg.equalsIgnoreCase("name is exist"))
                    name += currentTime + "";
            }
            if (!slug.isEmpty()) {
                if (!msg.equalsIgnoreCase("slug is exist")) {
                    slug += currentTime + "";
                }
            }
            commonHiveSteps.enterInput("Category's name", name);
            commonHiveSteps.enterInput("Slug", slug);
            commonHiveSteps.enterInput("Description", desc);
            commonHiveSteps.enterInput("Priority", priority);
//            commonHiveSteps.uploadImage("Icon", icon);
//            commonHiveSteps.uploadImage("Background", background);
            commonHiveSteps.clickUpdateAndClose();
            if (!msg.equalsIgnoreCase("Success")) {
                commonHiveSteps.verifyDetailPageDisplay("Original Version");
            } else {
                commonHiveSteps.verifyNameDisplay(name);
                commonHiveSteps.verifyActive(categoryID, isActive);
                commonHiveSteps.verifyFiledDisplay(categoryID, slug);
                commonHiveSteps.verifyFiledDisplay(categoryID, priority);
                List<String> info = homeSteps.getPageInformation("category", slug);
                assertThat(info.get(0)).isEqualTo(categoryID);
                assertThat(info.get(1)).isEqualTo(name);
                assertThat(info.get(2)).isEqualTo(desc);
                if (isActive) {
                    assertThat(info.get(4)).isEqualTo(String.valueOf(isActive));
                } else {
                    assertThat(info.get(4)).isEqualTo("0");
                }
                assertThat(info.get(5)).isEqualTo(priority);
                commonHiveSteps.clickEdit(categoryID);
            }
        }
    }

    @Then("Edit a feature list in hive")
    public void editAFeatureListInHive(List<List<String>> dataTable) {
        commonHiveSteps.clickEdit(featureID);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {

            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String slug = SessionData.getDataTbVal(dataTable, row, "Slug");
            String desc = SessionData.getDataTbVal(dataTable, row, "Description");
            String logo = SessionData.getDataTbVal(dataTable, row, "Logo");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String categoryType = SessionData.getDataTbVal(dataTable, row, "Category Type");
            String collectionType = SessionData.getDataTbVal(dataTable, row, "Collection Type");
            String ctaText = SessionData.getDataTbVal(dataTable, row, "CTA Text");
            String ctaLink = SessionData.getDataTbVal(dataTable, row, "CTA Link");
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            String searchTerm = SessionData.getDataTbVal(dataTable, row, "Search Term");
            String spLink = SessionData.getDataTbVal(dataTable, row, "Get Support Link");
            String webURL = SessionData.getDataTbVal(dataTable, row, "Website URL");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String screenshot = SessionData.getDataTbVal(dataTable, row, "Feature Screenshot");
            String status = SessionData.getDataTbVal(dataTable, row, "Approve");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            System.out.println("msg: " + msg);

            if (!name.isEmpty()) {
                if (!msg.equalsIgnoreCase("name is exist"))
                    name += currentTime + "";
            }
            if (!slug.isEmpty()) {
                if (!slug.equalsIgnoreCase("slug is exist"))
                    slug += currentTime + "";
            }
            commonHiveSteps.enterInput("Feature name", name);
            commonHiveSteps.enterInput("Feature slug", slug);
            commonHiveSteps.enterTextarea("Short-Description", desc);
//            commonHiveSteps.uploadImage("Feature Logo", logo);
//            commonHiveSteps.uploadImage("Feature Image", image);
            if (!categoryType.isEmpty()) {
                commonHiveSteps.selectDroplist("Category Type", categoryType);
            }
            if (!collectionType.isEmpty()) {
                commonHiveSteps.selectDroplist("Collection Type", collectionType);
            }
            commonHiveSteps.enterInput("CTA Text", ctaText);
            commonHiveSteps.enterInput("CTA Link", ctaLink);
            commonHiveSteps.enterInput("Priority", priority);
            commonHiveSteps.enterInput("Search Term", searchTerm);
            commonHiveSteps.enterInput("Get Support Link", spLink);
            commonHiveSteps.enterInput("Website URL", webURL);
            commonHiveSteps.enterTextarea("Description", content);
//            commonHiveSteps.uploadImage("Feature Screenshot", screenshot);
            commonHiveSteps.selectDroplist("Approve to be public on App Store", status);

            commonHiveSteps.clickUpdateAndClose();
            if (!msg.equalsIgnoreCase("Success")) {
                commonHiveSteps.verifyDetailPageDisplay("Original Version");
            } else {
                commonHiveSteps.verifyNameDisplay(name);
                String id = commonHiveSteps.getIdItem(name);
                commonHiveSteps.verifyFiledDisplay(id, ctaLink);
                commonHiveSteps.verifyFiledDisplay(id, categoryType);
                List<String> appInfo = homeSteps.getAppDetailbyID(featureID);
                assertThat(appInfo.get(1)).isEqualTo(name);
                assertThat(appInfo.get(4)).isEqualTo(slug);
                assertThat(appInfo.get(14)).isEqualTo(ctaLink);
                assertThat(appInfo.get(15)).isEqualTo(priority);
                assertThat(appInfo.get(16)).isEqualTo(searchTerm);
                assertThat(appInfo.get(11)).isEqualTo(spLink);
                assertThat(appInfo.get(10)).isEqualTo(webURL);
                assertThat(appInfo.get(9)).isEqualTo(content);
                assertThat(appInfo.get(17)).isEqualTo(ctaText);
                commonHiveSteps.clickEdit(featureID);
            }
        }
    }

    @Then("Edit a apps list in hive")
    public void editAAppsListInHive(List<List<String>> dataTable) {
        commonHiveSteps.clickEdit(appId);
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String slug = SessionData.getDataTbVal(dataTable, row, "Slug");
            String shortDesc = SessionData.getDataTbVal(dataTable, row, "Short-Description") + currentTime;
            String logo = SessionData.getDataTbVal(dataTable, row, "App Logo");
            String image = SessionData.getDataTbVal(dataTable, row, "App Image");
            String categoryType = SessionData.getDataTbVal(dataTable, row, "Category Type");
            String collectionType = SessionData.getDataTbVal(dataTable, row, "Collection Type");
            String ctaTextBefore = SessionData.getDataTbVal(dataTable, row, "CTA Text 1") + currentTime;
            String ctaTextAfter = SessionData.getDataTbVal(dataTable, row, "CTA Text 2") + currentTime;
            String ctaLink = SessionData.getDataTbVal(dataTable, row, "CTA Link") + currentTime;
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");
            String searchTerm = SessionData.getDataTbVal(dataTable, row, "Search Term") + currentTime;
            String supportLink = SessionData.getDataTbVal(dataTable, row, "Get Support Link") + currentTime;
            String webURL = SessionData.getDataTbVal(dataTable, row, "Website URL") + currentTime;
            String content = SessionData.getDataTbVal(dataTable, row, "Content") + currentTime;
            String screenshot = SessionData.getDataTbVal(dataTable, row, "App Screenshot");
            String status = SessionData.getDataTbVal(dataTable, row, "Approve");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            System.out.println("Msg: " + msg);

//            if (!name.isEmpty()) {
//                name = name + currentTime;
//            }
//            commonHiveSteps.enterInput("App Name", name);
//            if (!slug.isEmpty()) {
//                slug = slug + currentTime;
//            }
//            commonHiveSteps.enterInput("App slug", slug);
//            commonHiveSteps.enterTextarea("Short-Description", shortDesc);
//            commonHiveSteps.uploadImage("App Logo", logo);
//            commonHiveSteps.uploadImage("App Image", image);
            if (!categoryType.isEmpty()) {
                commonHiveSteps.selectDroplist("Category Type", categoryType);
            }
            if (!collectionType.isEmpty()) {
                commonHiveSteps.selectDroplist("Collection Type", collectionType);
            }

//            hiveAppSteps.enterCtaBeforeInstallApp(ctaTextBefore);
//            hiveAppSteps.enterCtaAfterInstallApp(ctaTextAfter);
//            commonHiveSteps.enterInput("CTA Link", ctaLink);
//            commonHiveSteps.enterInput("Priority", priority);
//            commonHiveSteps.enterInput("Search Term", searchTerm);
//            commonHiveSteps.enterInput("Get Support Link", supportLink);
//            commonHiveSteps.enterInput("Website URL", webURL);
//            hiveAppSteps.enterContent(content);


//            commonHiveSteps.uploadImage("App Screenshot", screenshot);
            commonHiveSteps.selectDroplist("Approve to be public on App Store", status);
            commonHiveSteps.clickUpdateAndClose();
            if (!msg.equalsIgnoreCase("Success")) {
                commonHiveSteps.verifyDetailPageDisplay("Apps List");
            } else {
                commonHiveSteps.verifyNameDisplay(name);
                commonHiveSteps.verifyFiledDisplay(appId, status);
                commonHiveSteps.verifyFiledDisplay(appId, categoryType);
                commonHiveSteps.verifyFiledDisplay(appId, priority);
                List<String> appInfo = homeSteps.getAppDetailbyID(appId);
                assertThat(appInfo.get(1)).isEqualTo(name);
                assertThat(appInfo.get(4)).isEqualTo(slug);
                assertThat(appInfo.get(14)).isEqualTo(ctaLink);
                assertThat(appInfo.get(15)).isEqualTo(priority);
                assertThat(appInfo.get(16)).isEqualTo(searchTerm);
                assertThat(appInfo.get(11)).isEqualTo(supportLink);
                assertThat(appInfo.get(10)).isEqualTo(webURL);
                assertThat(appInfo.get(9)).isEqualTo(content);
                assertThat(appInfo.get(5)).isEqualTo(ctaTextBefore);
                assertThat(appInfo.get(6)).isEqualTo(ctaTextAfter);
                assertThat(appInfo.get(7)).isEqualTo(shortDesc);
                assertThat(appInfo.get(11)).isEqualTo(supportLink);
                commonHiveSteps.clickEdit(appId);
            }
        }
    }

    @Then("Input data for base product")
    public void inputDataForBaseProduct(List<List<String>> dataTable) {
        System.out.println("Start");

        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String color = SessionData.getDataTbVal(dataTable, row, "Color");
            String size = SessionData.getDataTbVal(dataTable, row, "Size");
            String cost = SessionData.getDataTbVal(dataTable, row, "Cost");
            String weight = SessionData.getDataTbVal(dataTable, row, "Weight");
            commonHiveSteps.inputDataForBaseProduct(color, size, weight, cost);
        }

        commonHiveSteps.clickBtnUpdate();
        System.out.println("Done");
    }

    @Then("Get data of base product")
    public void getDataOfBaseProduct() {
        commonHiveSteps.getDataOfBaseProduct();
    }

    @And("Verify disable app in Plusbase store as {string}")
    public void verifyDisableAppInPlusbaseStoreAs(String dataKey, List<List<String>> dataTable) {
        String shopName = LoadObject.getProperty("shopname");
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String appName = SessionData.getDataTbVal(dataTable, row, "App");
            String noti = SessionData.getDataTbVal(dataTable, row, "Noti");
            appStoreSteps.goToAppStore();
            appStoreSteps.switchToNewTab();
            appStoreSteps.turnOffSitekitPopup();
            appStoreSteps.searchAppAndSelectAppPlusbase(appName);
            appStoreSteps.addApp();
            appStoreSteps.verifyBuildInScreen(shopName,appName, noti );
        }
    }
}