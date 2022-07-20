package opencommerce.online_store.themes;

import com.opencommerce.shopbase.common.pages.CommonThemeEditorPage;
import com.opencommerce.shopbase.common.steps.CommonThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.CodeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ManageThemeSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.ThemeEditorV2Steps;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ManageThemeDef {
    @Steps
    ManageThemeSteps manageThemeSteps;
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;
    @Steps
    HomePageSteps homePageSteps;
    @Steps
    ThemeEditorSteps themeEditorSteps;
    @Steps
    CodeEditorSteps codeEditorSteps;
    @Steps
    ThemeEditorV2Steps themeEditorV2Steps;

    String themeID;

    @When("^create new theme \"([^\"]*)\"$")
    public void selectThemeWantToCreate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String theme = SessionData.getDataTbVal(dataTable, row, "Theme");

            manageThemeSteps.selectTabThemes();
            manageThemeSteps.selectTheme(theme);
            manageThemeSteps.clickButtonAddTheme();
        }
    }

    @When("^verify created theme \"([^\"]*)\"$")
    public void verifyCreatedTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String themeName = SessionData.getDataTbVal(dataTable, row, "Theme name");
            String baseTheme = SessionData.getDataTbVal(dataTable, row, "Base theme");
            String lastSaved = SessionData.getDataTbVal(dataTable, row, "Last saved");
            boolean typeThemePB = manageThemeSteps.verifyTypeThemePB();

            manageThemeSteps.verifyThemeName(themeName);

            if (!lastSaved.isEmpty()) {
                manageThemeSteps.verifyLastSaved(lastSaved);
                manageThemeSteps.clickCustomizeCopyTheme();
                int _countMenu = 0;
                int _countTitle = 0;

                if (typeThemePB) {
                    switch (baseTheme) {
                        case "Inside":
                            themeEditorSteps.openSessionBlock("Header");
                            manageThemeSteps.verifyHeaderThemeInside(false);
                            themeEditorSteps.openSessionBlock("Footer");
                            _countMenu = manageThemeSteps.countBlock("Menu");
                            manageThemeSteps.verifyBlockFooterPB(manageThemeSteps.countDroplistBlockFooter(_countMenu, "Menu"));
                            break;
                        case "Roller":
                            themeEditorSteps.openSessionBlock("Footer");
                            _countTitle = manageThemeSteps.countBlock("Title");
                            manageThemeSteps.verifyBlockFooterPB(manageThemeSteps.countDroplistBlockFooter(_countTitle, "Title"));
                            _countMenu = manageThemeSteps.countBlock("Menu");
                            manageThemeSteps.verifyBlockFooterPB(manageThemeSteps.countDroplistBlockFooter(_countMenu, "Menu"));
                            break;
                    }
                } else {
                    switch (baseTheme) {
                        case "Inside":
                            themeEditorSteps.openSessionBlock("Header");
                            manageThemeSteps.verifyHeaderThemeInside(true);
                            themeEditorSteps.openSessionBlock("Footer");
                            _countMenu = manageThemeSteps.countBlock("Menu");
                            manageThemeSteps.verifyBlockFooterSB(manageThemeSteps.countDroplistBlockFooter(_countMenu, "Menu"), _countMenu);
                            break;
                        case "Roller":
                            themeEditorSteps.openSessionBlock("Footer");
                            _countTitle = manageThemeSteps.countBlock("Title");
                            manageThemeSteps.verifyBlockFooterSB(manageThemeSteps.countDroplistBlockFooter(_countTitle, "Title"), _countTitle);
                            _countMenu = manageThemeSteps.countBlock("Menu");
                            manageThemeSteps.verifyBlockFooterSB(manageThemeSteps.countDroplistBlockFooter(_countMenu, "Menu"), _countMenu);
                            break;
                    }
                }
                manageThemeSteps.closeCustomizeTheme();
            }
        }
    }

    @When("^do actions with theme on block More themes \"([^\"]*)\"$")
    public void selectActionManageTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String themeName = SessionData.getDataTbVal(dataTable, row, "Theme name");
            String newName = SessionData.getDataTbVal(dataTable, row, "New name");
            String themeCopyID = SessionData.getDataTbVal(dataTable, row, "Theme id");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            String shopCopyDomain = SessionData.getDataTbVal(dataTable, row, "Shop domain");
            String shopCopyName = SessionData.getDataTbVal(dataTable, row, "Shop name");

            switch (action) {
                case "Publish":
                    themeID = manageThemeSteps.getThemeID(themeName);
                    manageThemeSteps.openActionsDropDown(themeName);
                    manageThemeSteps.selectAction(themeName, action);
                    reviewInProductAdminSteps.clickButtonOnPopup("Save");
                    break;
                case "Remove":
                    themeID = manageThemeSteps.getThemeID(themeName);
                    manageThemeSteps.openActionsDropDown(themeName);
                    manageThemeSteps.selectAction(themeName, action);
                    reviewInProductAdminSteps.clickButtonOnPopup("Remove");
                    manageThemeSteps.verifyRemovedTheme(themeID, false);
                    break;
                case "Rename":
                    manageThemeSteps.openActionsDropDown(themeName);
                    manageThemeSteps.selectAction(themeName, action);
                    manageThemeSteps.inputThemeName(newName);
                    reviewInProductAdminSteps.clickButtonOnPopup("Save");
                    break;
                case "Copy theme":
                    if (themeCopyID.isEmpty() && !themeName.isEmpty() && shopCopyName.isEmpty()) {
                        themeCopyID = manageThemeSteps.getThemeID(themeName);
                    }
                    if (themeCopyID.isEmpty() && !themeName.isEmpty() && !shopCopyName.isEmpty()) {
                        String accessToken = manageThemeSteps.getAccessTokenShopBase(shopCopyName);
                        themeCopyID = manageThemeSteps.getThemeIDByAPI(accessToken, themeName, shopCopyDomain);
                    }
                    manageThemeSteps.clickButtonCopyATheme();
                    manageThemeSteps.enterThemeId(themeCopyID);
                    manageThemeSteps.clickButtonCopyTheme(action);
                    manageThemeSteps.verifyShowMessage(message);
                    break;
                case "Edit code":
                    manageThemeSteps.openActionsDropDown(themeName);
                    manageThemeSteps.selectAction(themeName, action);
                    codeEditorSteps.verifyEditorCodePage(themeName);
                    break;
                default:
                    themeID = manageThemeSteps.getThemeID(themeName);
                    manageThemeSteps.openActionsDropDown(themeName);
                    manageThemeSteps.selectAction(themeName, action);
            }
        }
    }

    @When("^do actions with block Current theme \"([^\"]*)\"$")
    public void selectActionManageCurrentTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            manageThemeSteps.openActionsDropDownOfCurrentTheme();
            manageThemeSteps.selectActionOfCurrentTheme(action);
        }
    }

    @When("^remove all themes")
    public void removeAllThemes() {
        int countTheme = manageThemeSteps.countTheme();
        for (int i = 0; i < countTheme; i++) {
            manageThemeSteps.openActionsDropDown();
            manageThemeSteps.selectAction("Remove");
            reviewInProductAdminSteps.clickButtonOnPopup("Remove");
        }
        manageThemeSteps.verifyRemoveAllThemes();
    }

    @When("^verify current theme ID on dashboard \"([^\"]*)\"$")
    public void verifyCurrentThemeIDOnDB(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String theme = SessionData.getDataTbVal(dataTable, row, "Theme name");
            homePageSteps.refreshpage();
            manageThemeSteps.verifyTitlePublishedTheme(theme);
            manageThemeSteps.verifyIDPublishedTheme(themeID);
        }
    }

    @When("^click \"([^\"]*)\" button on dashboard")
    public void clickButtonOnDashboard(String label) {
        manageThemeSteps.clickButtonOnDashboard(label);
    }

    @When("^verify theme on sf \"([^\"]*)\"$")
    public void verifyThemeOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String baseTheme = SessionData.getDataTbVal(dataTable, row, "Base theme");

            manageThemeSteps.verifyThemeVersion(baseTheme);
            manageThemeSteps.verifyThemeID(themeID);
            manageThemeSteps.verifyStyleOnSF();
        }
    }

    @And("verify block password on Theme {string}")
    public void verifyBlockPasswordOnTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String isShowPasswordOnTheme = SessionData.getDataTbVal(dataTable, row, "ShowPasswordOnTheme");
            manageThemeSteps.verifyBlockPWProtection(isShowPasswordOnTheme);
        }
    }

    @When("^verify sections in theme editor \"([^\"]*)\"$")
    public void verifySectionsThemeEditor(String dataKey, List<List<String>> dataTable) {
        manageThemeSteps.clickButtonCustomize();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String themeName = SessionData.getDataTbVal(dataTable, row, "Theme name");
            String pagePreview = SessionData.getDataTbVal(dataTable, row, "Page Preview");
            String sections = SessionData.getDataTbVal(dataTable, row, "Sections");
            if (themeName.equals("Inside")) {
                themeEditorV2Steps.openPreviewPage(pagePreview);
            } else
                themeEditorSteps.choosePreview(pagePreview);
            String[] section = sections.split(",");
            for (String s : section) {
                manageThemeSteps.verifyText(s, true);
            }
        }
    }

    @Then("go to cart page")
    public void goToCartPage() {
        manageThemeSteps.goToCartPage();
    }

    @Steps
    CommonThemeEditorSteps commonThemeEditorSteps;

//    @Then("test")
//    public void test() throws AWTException {
//        commonThemeEditorSteps.dragAndDropBlock();
//    }
}
