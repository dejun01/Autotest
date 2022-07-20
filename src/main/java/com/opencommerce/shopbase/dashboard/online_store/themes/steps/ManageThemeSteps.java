package com.opencommerce.shopbase.dashboard.online_store.themes.steps;

import com.opencommerce.shopbase.dashboard.dashboard.steps.models.Themes.Themes;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ManageThemePage;
import common.utilities.LoadObject;
import com.opencommerce.shopbase.dashboard.online_store.themes.pages.ThemeEditorPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import static net.serenitybdd.rest.SerenityRest.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ManageThemeSteps {
    ManageThemePage manageThemePage;
    ThemeEditorPage themeEditorPage;

    @Step
    public void selectTabThemes() {
        manageThemePage.selectTabThemes();
    }

    @Step
    public void selectTheme(String theme) {
        manageThemePage.selectTheme(theme);
    }

    @Step
    public void clickButtonAddTheme() {
        manageThemePage.clickButtonAddTheme();
    }

    @Step
    public void verifyThemeName(String theme) {
        manageThemePage.verifyThemeName(theme);
    }

    @Step
    public void verifyLastSaved(String lastSaved) {
        manageThemePage.verifyLastSaved(lastSaved);
    }

    @Step
    public void openActionsDropDown(String theme) {
        manageThemePage.openActionsDropDown(theme);
    }

    @Step
    public void openActionsDropDown() {
        manageThemePage.openActionsDropDown();
    }

    @Step
    public void selectAction(String theme, String action) {
        manageThemePage.selectAction(theme, action);
    }

    @Step
    public void selectAction(String action) {
        manageThemePage.selectAction(action);
    }

    @Step
    public void verifyTitlePublishedTheme(String theme) {
        manageThemePage.verifyTitlePublishedTheme(theme);
    }

    @Step
    public void clickButtonOnDashboard(String label) {
        manageThemePage.clickButtonOnDashboard(label);
    }

    @Step
    public void inputThemeName(String name) {
        manageThemePage.inputThemeName(name);
    }

    @Step
    public void verifyThemeNameOnSF(String themeName) {
        manageThemePage.verifyThemeNameOnSF(themeName);
    }

    @Step
    public void verifyIDPublishedTheme(String themeID) {
        String expect = "Theme ID: " + themeID;
        manageThemePage.verifyIDPublishedTheme(expect);
    }

    public String getThemeID(String theme) {
        String actualStringID = manageThemePage.getThemeID(theme);
        String[] id = actualStringID.split(":");
        return id[id.length - 1].trim();
    }

    public void verifyRemovedTheme(String themeID, boolean isShow) {
        String strThemeID = "Theme ID: " + themeID;
        manageThemePage.verifyRemovedTheme(strThemeID, isShow);
    }

    public JsonPath getThemesAndNormalize(String accessToken, String shop) {
        String api = "https://" + shop + "/admin/themes.json";
        JsonPath themesList = manageThemePage.getAPISbase(api, accessToken);
        Themes[] desThemes = themesList.getObject("shop_themes", Themes[].class);
        for (int i = 0; i < desThemes.length; i++) {
            desThemes[i].normalizeThemes();
        }
        return themesList;
    }

    @Step
    public void verifyShowTheme() {
        manageThemePage.waitForPageLoad();
        manageThemePage.verifyShowTheme();
    }

    @Step
    public void clickButtonCopyTheme(String label) {
        manageThemePage.clickButtonCopyTheme(label);
    }

    @Step
    public void clickButtonCopyATheme() {
        manageThemePage.clickButtonCopyATheme();
    }

    @Step
    public void clickButtonCustomize() {
        manageThemePage.clickButtonCustomize();
    }

    @Step
    public void verifyText(String section, boolean isPresent){
        manageThemePage.verifyText(section, isPresent);
    }

    @Step
    public void verifyOpenAddSection(){
        manageThemePage.openAddSection("+ Add section above");
        manageThemePage.clickButtonBack();
        manageThemePage.openAddSection("+ Add section below");
    }

    @Step
    public void openSectionCart(){
        manageThemePage.clickButtonBack();
        themeEditorPage.navigateToSectionsTab();
    }

    @Step
    public void enterThemeId(String _value) {
        manageThemePage.enterThemeId(_value);
    }

    @Step
    public void verifyShowMessage(String message) {
        manageThemePage.verifyShowMessage(message);
    }

    @Step
    public String getThemeIDByAPI(String accessToken, String themeName, String shopDomainCopy) {
        String getShopDomain = LoadObject.getProperty(shopDomainCopy);
        String api = "https://" + getShopDomain + "/admin/themes.json?access_token="+ accessToken;
        JsonPath themes = manageThemePage.getAPISbase(api, accessToken);
        String themeID = themes.get("shop_themes.findAll{it.name=='" + themeName + "'}.id[0]").toString();
        return themeID;
    }

    public String getAccessTokenShopBase(String shopname) {
        String getShopName = LoadObject.getProperty(shopname);
        String username = "";
        String pwd = "";
        if(shopname.equalsIgnoreCase("shopnamecopy4")){
            username = LoadObject.getProperty("user4.name");
            pwd = LoadObject.getProperty("user4.pwd");
        }
        else{
            username = LoadObject.getProperty("user.name");
            pwd = LoadObject.getProperty("user.pwd");
        }
        return manageThemePage.getAccessTokenShopBase(username,pwd,getShopName);
    }

    @Step
    public void clickCustomizeCopyTheme() {
        manageThemePage.clickCustomizeCopyTheme();
    }

    @Step
    public void verifyHeaderThemeInside (Boolean isLayoutMinimal) {
        manageThemePage.verifyHeaderThemeInside(isLayoutMinimal);

    }

    @Step
    public boolean verifyTypeThemePB () {
        return manageThemePage.verifyTypeThemePB();
    }

    @Step
    public int countBlock(String block){
        return manageThemePage.countBlock(block);
    }

    @Step
    public void clickBlock(int i, String block){
        manageThemePage.clickBlock(i,block);
    }

    @Step
    public boolean isDroplistBlockExist(String block){
        return manageThemePage.isDroplistBlockExist(block);
    }

    @Step
    public void verifyBlockFooterPB(int countDroplistOfBlock){
        manageThemePage.verifyBlockFooterPB(countDroplistOfBlock);
    }

    public int countDroplistBlockFooter(int _countBlock, String block){
        int countDroplistOfBlock = 0;
        for(int i=1; i<=_countBlock; i++){
            clickBlock(i, block);
            if(isDroplistBlockExist(block))
                countDroplistOfBlock++;
        }
        return countDroplistOfBlock;
    }

    @Step
    public void verifyBlockFooterSB(int countDroplistOfMenu, int _countMenu){
        manageThemePage.verifyBlockFooterSB(countDroplistOfMenu, _countMenu);
    }

    @Step
    public void closeCustomizeTheme(){
        manageThemePage.closeCustomizeTheme();
    }

    @Step
    public void verifyStyleOnSF() {
        String apiStyle = getAPIStyleOnSF();

        Response response = given().header("Content-Type", "application/json").get(apiStyle);
        Assertions.assertThat(response.getStatusCode()).isBetween(200, 201);
    }

    public String getAPIStyleOnSF(){
        String shop = LoadObject.getProperty("shop");
        JsonPath jsonPath = manageThemePage.getAPI("https://" + shop + "/api/bootstrap/next.json");
        return (String) manageThemePage.getData(jsonPath, "result.theme.cdn.style");
    }

    @Step
    public void verifyThemeVersion(String baseTheme) {
        String shop = LoadObject.getProperty("shop");
        JsonPath jsonPath = manageThemePage.getAPI("https://" + shop + "/api/bootstrap/next.json");
        int actualVersion = (int) manageThemePage.getData(jsonPath, "result.theme.id");

        int expectVersion;
        if(baseTheme.equalsIgnoreCase("Bassy")){
            expectVersion = 1;
        }else if (baseTheme.equalsIgnoreCase("Roller")){
            expectVersion = 2;
        }else {
            expectVersion = 3;
        }

        assertThat(actualVersion).isEqualTo(expectVersion);
    }

    @Step
    public void verifyThemeID(String themeID) {
        String shop = LoadObject.getProperty("shop");
        JsonPath jsonPath = manageThemePage.getAPI("https://" + shop + "/api/bootstrap/next.json");
        int actualID = (int) manageThemePage.getData(jsonPath, "result.theme.shop_theme_id");

        assertThat(actualID).isEqualTo(Integer.parseInt(themeID));
    }

    public int countTheme() {
        return manageThemePage.countTheme();
    }

    public void verifyRemoveAllThemes() {
        manageThemePage.verifyRemoveAllThemes();
    }
    public void verifyBlockPWProtection(String isStastus) {
        boolean statusBlock = Boolean.parseBoolean(isStastus);
        manageThemePage.verifyBtnDisablePasswordDisplay(statusBlock);
    }

    public void goToCartPage() {
        String shop = LoadObject.getProperty("shop");
        String currentUrl = manageThemePage.getCurrentUrl();
        if(!currentUrl.equals("https://" + shop + "/cart")){
            manageThemePage.openUrl("https://" + shop + "/cart");
        }
    }

    @Step
    public void selectActionOfCurrentTheme(String action) {
        manageThemePage.selectActionOfCurrentTheme(action);
    }

    @Step
    public void openActionsDropDownOfCurrentTheme() {
        manageThemePage.openActionsDropDownOfCurrentTheme();
    }
}