package com.opencommerce.shopbase.storefront.steps.navigation;

import com.opencommerce.shopbase.storefront.pages.navigation.NavigationPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class NavigationStep extends ScenarioSteps {
    NavigationPage navigationPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void clickToAddMenuBtn() {
        navigationPage.clickToAddOrCreateBtn("Add menu");
    }

    @Step
    public void clickToMenuOnDashboard(String label) {
        navigationPage.clickToMenuOnDashboard(label);
    }

    @Step
    public void inputMenuTitle(String value) {
        navigationPage.inputMenuTitle(value);
    }


    @Step
    public void clickToAddMenuItemBtn() {
        navigationPage.clickToAddMenuItemBtn();
    }

    @Step
    public void clickToEditMenuItemBtn(String label) {
        navigationPage.clickToEditMenuItemBtn(label, "Edit");
    }

    @Step
    public void removeMenuItem(String title) {
        navigationPage.clickToRemoveMenuItem(title);
        navigationPage.confirmDeleteMenuBtn("OK");
    }

    @Step
    public void inputMenuItemName(String value) {
        navigationPage.inputMenuItemName(value);
    }

    @Step
    public void selectFirstItemLink(String value) {
        navigationPage.selectFirstLink(value);
    }

    @Step
    public void selectSecondItemLink(String value){
        if(!value.isEmpty()){
            navigationPage.selectSecondlink(value);
        }
    }

    @Step
    public void clickToAddItemBtn(){
        navigationPage.clickToAddBtn("Add");
    }

    @Step
    public void clickToSaveBtn(String label) {
        navigationPage.clickToSaveBtn(label);
    }

    @Step
    public void verifySaveBtnDisabled() {
        navigationPage.verifySaveBtnDisabled();
    }

    @Step
    public void switchBackToNavigationPage(String legal) {
        if(!legal.isEmpty()){
            navigationPage.switchToMenuTab("Online Store");
        }
        navigationPage.switchToTab("Navigation");
    }

    @Step
    public void verifyMenuCreatedOnDashboard(String value, boolean isCheck) {
        navigationPage.verifyMenuCreated(value, isCheck);
    }

    @Step
    public void goToThemesSettingPage() {
        navigationPage.switchToTab("Themes");
    }

    @Step
    public void clickToCustomizeBtn() {
        navigationPage.clickToCustomizeBtn("Customize");
    }

    @Step
    public void editHeaderSetting() {
        navigationPage.clickToHeaderSetting("Header");
    }

    @Step
    public void selectMenuInHeaderSetting(String value) {
        navigationPage.selectNewMenuCreated(value);
        navigationPage.clickToSaveThemeBtn("Save");
    }

    @Step
    public void openOnlineStore() {
        String pageUrl = ("https://" + shop);
        navigationPage.openUrlInNewTab(pageUrl);
        navigationPage.switchToNextTab();
    }

    @Step
    public void verifyMenuDisplayedOnSF(String menuTitle) {
        navigationPage.verifyMenuDisplayed(menuTitle, true);
    }

    @Step
    public void verifyMenuItemDeleted(String itemName) {
        navigationPage.verifyMenuItemDeleted(itemName);
    }

    @Step
    public void clickToMenuItemOnSF(String itemName) {
        navigationPage.clickToMenuItemOnSF(itemName);
    }

    @Step
    public void verifyUrlRedirectOnSF(String firstLink, String secondLink) {
        String menuUrl = (firstLink.toLowerCase() + "/" + secondLink.toLowerCase().replace(" ", "-"));
        String currentUrl = navigationPage.getCurrentUrl();
        if(!secondLink.isEmpty()){

            assertThat(currentUrl).isEqualTo("https://" + shop + "/" + menuUrl);
        }else {
            assertThat(currentUrl).isEqualTo("https://" + shop + "/");
        }
    }

    @Step
    public void closeSFWindow() {
        navigationPage.switchToTheFirstTab();
        String currentTab = getDriver().getWindowHandle();
        navigationPage.closeAllTabWithoutParent(currentTab);
    }

    @Step
    public void removeOldLink() {
        navigationPage.clickToRemoveOldLink();
    }

    @Step
    public void verifyDeleteBtnUndisplayed(){
        navigationPage.verifyDeleteBtnUndisplayed();
        navigationPage.switchToTab("Navigation");
    }

    @Step
    public void clickToDeleteMenuBtn(String label) {
        navigationPage.clickBtn(label);
        navigationPage.confirmDeleteMenuBtn(label);
    }

    @Step
    public void clickBtnOnNavigationPage(String label) {
        navigationPage.clickBtnOnNavigationPage(label);
    }

    @Step
    public void checkToPageLocksOnDB(String pages) {
        navigationPage.checkPagelocks(pages);
    }

    @Step
    public void clickToSavePageLocksBtn() {
        navigationPage.clickToSavePageLocksBtn("Save");
        navigationPage.waitToMsgInvisible("Page locks was updated");
    }

    @Step
    public void openUrlInNewWindow(String linkPages) {
        String pageURL = ("https://" + shop + "/" + linkPages);
        navigationPage.openUrlInNewTab(pageURL);
        navigationPage.waitForPageLoad();
        navigationPage.switchToNextTab();
    }

    @Step
    public void verifyPageContentShowOnSF(String title) {
        navigationPage.verifyPageContentShowOnSF(title);
    }

    @Step
    public void verifyPageNotFound() {
        navigationPage.verifyPageNotFound();
    }

    @Step
    public void clickToCreateUrlBtn() {
        navigationPage.clickToAddOrCreateBtn("Create URL redirect");
    }

    @Step
    public void inputLinkToRedirectTextbox(String label, String value) {
        navigationPage.inputLinkToTxtbox(label, value);
    }

    @Step
    public void verifyMessage(String label) {
        navigationPage.verifyMessage(label, true);
    }

    @Step
    public void verifyErrorMessage(String label) {
        navigationPage.verifyErrorMessage(label, true);
    }

    @Step
    public void searchOnRedirectList(String urlRedirect) {
        String  searchValue = (urlRedirect.replace("/", ""));
        navigationPage.searchUrlRedirectList("Search", searchValue);
    }

    @Step
    public void clickToDiscardBtn() {
        navigationPage.clickToDiscardBtn();
    }

    @Step
    public void backToRedirectList() {
        navigationPage.backToRedirectList();
    }

    @Step
    public void verifyUrlRedirectCreatedOnDB(String redirectFrom, String redirectTo ) {
        navigationPage.verifyUrlRedirectCreatedOnDB(redirectFrom, redirectTo, true);
    }


    @Step
    public void verifyUrlPresent(String redirectTo) {
        String currentUrl = navigationPage.getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://" + shop + redirectTo);
    }

    @Step
    public void clickToEditUrlRedirect(String redirectFrom) {
        navigationPage.clickToEditUrlRedirect(redirectFrom);
    }

    @Step
    public void selectRedirectOnList(String redirectFr) {
        navigationPage.selectRedirectOnList(redirectFr);
    }

    @Step
    public void deleteRedirectSelected() {
        navigationPage.clickToActionBtn();
        navigationPage.clickToDeleteItem();
    }

    @Step
    public void verifyUrlRedirectDeletedOnDB(String redirectFr) {
        searchOnRedirectList(redirectFr);
        navigationPage.verifyRedirectNotFoundOnList();
        searchOnRedirectList("");
    }

    @Step
    public void selectAllRedirect() {
        navigationPage.selectAllRedirect();
    }

    @Step
    public void verifyRedirectListEmpty() {
        navigationPage.verifyRedirectListEmpty();
    }

    @Step
    public void settingLegalTemplate() {
        navigationPage.switchToMenuTab("Settings");
        navigationPage.settingLegalTemplate("Legal");
    }

    @Step
    public void addFromTemplate(String label) {
        navigationPage.addFromTemplate(label);
        clickToSaveBtn("Save changes");
    }

    @Step
    public void inputNewTemplate(String label, String value) {
        navigationPage.inputNewTemplate(label, value);
        clickToSaveBtn("Save changes");
    }

    @Step
    public void verifyLegalTemplate(String legal) {
        navigationPage.verifyLegalTemplate(legal, true);
    }

    @Step
    public void verifyPageLocked() {
        navigationPage.verifyPageNotFound();
    }
}
