package opencommerce.online_store.navigation;

import com.opencommerce.shopbase.storefront.steps.navigation.NavigationStep;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class NavigationDef {
    @Steps
    NavigationStep navigationStep;
    String shop = LoadObject.getProperty("shop");

    @And("^user create new navigation menu$")
    public void userCreateNewNavigationMenu(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String oldTitle = SessionData.getDataTbVal(dataTable, row, "Old Title");
            String oldName = SessionData.getDataTbVal(dataTable, row, "Old name");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String name = SessionData.getDataTbVal(dataTable, row, "Name");
            String firstLink = SessionData.getDataTbVal(dataTable, row, "Link 1");
            String secondLink = SessionData.getDataTbVal(dataTable, row, "Link 2");
            String legal = SessionData.getDataTbVal(dataTable, row, "Legal template");

            if (oldTitle.isEmpty()) {
                navigationStep.clickToAddMenuBtn();
            } else {
                navigationStep.clickToMenuOnDashboard(oldTitle);
            }
            navigationStep.inputMenuTitle(title);
            if (!oldName.equals("Home")) {
                if (oldName.isEmpty()) {
                    navigationStep.clickToAddMenuItemBtn();
                } else {
                    navigationStep.clickToEditMenuItemBtn(oldName);
                }
                navigationStep.inputMenuItemName(name);
                if(!legal.equals("RETURN")){
                    if (firstLink.equals("Products")) {
                        navigationStep.removeOldLink();
                    }
                    navigationStep.selectFirstItemLink(firstLink);
                    navigationStep.selectSecondItemLink(secondLink);
                }
                navigationStep.clickToAddItemBtn();
            } else {
                navigationStep.removeMenuItem(oldName);
            }
            if(!title.isEmpty()){
                navigationStep.clickToSaveBtn("Save menu");
            }else {
                navigationStep.verifySaveBtnDisabled();
            }
            switch (legal) {
                case "RETURN":
                    navigationStep.settingLegalTemplate();
                    navigationStep.addFromTemplate(secondLink);
                    break;
                case "Refund to customers":
                    navigationStep.settingLegalTemplate();
                    navigationStep.inputNewTemplate(secondLink, legal);
                    break;
                default:
                    break;
            }
            navigationStep.switchBackToNavigationPage(legal);
        }
    }

    @Then("^verify menu added on dashboard$")
    public void verifyMenuAddedOnDB(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            boolean isDisplayed = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is Displayed"));
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            navigationStep.verifyMenuCreatedOnDashboard(title, isDisplayed);
        }
    }

    @And("^customize theme to show menu on store front$")
    public void customizeThemeToShowMenuOnSF(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            navigationStep.goToThemesSettingPage();
            navigationStep.clickToCustomizeBtn();
            navigationStep.editHeaderSetting();
            navigationStep.selectMenuInHeaderSetting(title);
        }
    }

    @And("^verify navigation menu on store front$")
    public void verifyNavigationMenuOnSF(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String menuName = SessionData.getDataTbVal(dataTable, row, "Name");
            String firstLink = SessionData.getDataTbVal(dataTable, row, "Link 1");
            String secondLink = SessionData.getDataTbVal(dataTable, row, "Link 2");
            String legal = SessionData.getDataTbVal(dataTable, row, "Legal template");

            navigationStep.openOnlineStore();
            navigationStep.verifyMenuDisplayedOnSF(menuName);
            if(firstLink.isEmpty()){
                navigationStep.verifyMenuItemDeleted(menuName);
            }else {
                navigationStep.clickToMenuItemOnSF(menuName);
            }
            navigationStep.verifyUrlRedirectOnSF(firstLink, secondLink);
            if(!legal.isEmpty()){
                navigationStep.verifyLegalTemplate(legal);
            }
            navigationStep.closeSFWindow();
        }
    }

    @And("^user delete navigation menu$")
    public void deleteNavigationMenu(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            navigationStep.clickToMenuOnDashboard(title);
            if(title.equals("Main menu")){
                navigationStep.verifyDeleteBtnUndisplayed();
            }else {
                navigationStep.clickToDeleteMenuBtn("Delete menu");
            }
        }
    }

    @And("^user click on \"([^\"]*)\" button$")
    public void userClickOnBtn(String button){
        navigationStep.clickBtnOnNavigationPage(button);
    }

    @Then("^verify page locks checked on SF$")
    public void verifyPagelocksCheckedOnSF(List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String pages = SessionData.getDataTbVal(dataTable, row, "Pages");
            String linkPages = SessionData.getDataTbVal(dataTable, row, "Link pages");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "is Show"));

            navigationStep.checkToPageLocksOnDB(pages);
            navigationStep.clickToSavePageLocksBtn();
            navigationStep.openUrlInNewWindow(linkPages);
            if(isShow){
                navigationStep.verifyPageContentShowOnSF(title);
            }else{
                navigationStep.verifyPageLocked();
            }
            navigationStep.closeSFWindow();
        }
    }

    @Then("^verify URL redirect created on dashboard$")
    public void userCreateAnUrlRedirect(List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String redirectFrom = SessionData.getDataTbVal(dataTable, row, "Redirect from");
            String redirectTo = SessionData.getDataTbVal(dataTable, row, "Redirect to");
            String msg = SessionData.getDataTbVal(dataTable, row, "Message");
            String errorMsg = SessionData.getDataTbVal(dataTable, row, "Error");
            String oldRedirect = SessionData.getDataTbVal(dataTable, row, "Old redirect");
            Boolean isCorrect = Boolean.valueOf(SessionData.getDataTbVal(dataTable, row, "is Correct domain"));

            if(oldRedirect.isEmpty()){
                navigationStep.clickToCreateUrlBtn();
            }else{
                navigationStep.searchOnRedirectList(oldRedirect);
                navigationStep.clickToEditUrlRedirect(oldRedirect);
            }
            navigationStep.inputLinkToRedirectTextbox("Redirect from", redirectFrom);
            navigationStep.inputLinkToRedirectTextbox("Redirect to", redirectTo);
            navigationStep.clickToSaveBtn("Save redirect");
            if(!msg.isEmpty()){
                navigationStep.verifyMessage(msg);
                if(!errorMsg.isEmpty()) {
                    navigationStep.verifyErrorMessage(errorMsg);
                }
            }
            if(!isCorrect){
                navigationStep.clickToDiscardBtn();
                navigationStep.backToRedirectList();
            }else{
                navigationStep.backToRedirectList();
                navigationStep.searchOnRedirectList(redirectFrom);
                navigationStep.verifyUrlRedirectCreatedOnDB(redirectFrom, redirectTo);
                navigationStep.searchOnRedirectList("");
            }
        }
    }

    @And("^verify URL redirect created on SF$")
    public void verifyUrlRedirectCreated(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String redirectFrom = SessionData.getDataTbVal(dataTable, row, "Redirect from");
            String redirectTo = SessionData.getDataTbVal(dataTable, row, "Redirect to");

            navigationStep.openUrlInNewWindow(redirectFrom);
            if(!redirectTo.isEmpty()){
                navigationStep.verifyUrlPresent(redirectTo);
            }else {
                navigationStep.verifyPageNotFound();
            }

        }
    }

    @And("^verify Url redirect deleted on dashboard$")
    public void verifyUrlRedirectDeletedOnDB(List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String redirectFrom = SessionData.getDataTbVal(dataTable, row, "Redirect from");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            if(action.equals("Delete")){
                navigationStep.searchOnRedirectList(redirectFrom);
                navigationStep.selectRedirectOnList(redirectFrom);
                navigationStep.deleteRedirectSelected();
                navigationStep.verifyUrlRedirectDeletedOnDB(redirectFrom);
            }else {
                navigationStep.selectAllRedirect();
                navigationStep.deleteRedirectSelected();
                navigationStep.verifyRedirectListEmpty();
            }
        }
    }
}
