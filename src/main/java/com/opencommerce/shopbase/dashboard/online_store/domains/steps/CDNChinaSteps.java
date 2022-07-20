package com.opencommerce.shopbase.dashboard.online_store.domains.steps;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.online_store.domains.pages.CDNChinaPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

public class CDNChinaSteps extends CommonSteps {
    CDNChinaPage cdnChinaPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void switchToChina(String server){
        cdnChinaPage.openMenu();
        cdnChinaPage.switchToChina(server);
    }

    @Step
    public void searchPage(String title) {
        cdnChinaPage.searchPage(title);
    }

    @Step
    public void choosePage(String namePage) {
        cdnChinaPage.choosePage(namePage);
    }

    @Step
    public void clickOnAction(String button){
        cdnChinaPage.clickOnAction(button);
    }

    @Step
    public void openThemeInSideBar(){
        cdnChinaPage.openThemeInSideBar();
    }

    @Step
    public void verifyNameProductAndCollection(String text){
        cdnChinaPage.verifyNameProductAndCollection(text);
    }

    @Step
    public void verifyTextHomeIsTranslated(String input){
        cdnChinaPage.verifyTextHomeIsTranslated(input);
    }

    public void switchLanguage(String language) {
        cdnChinaPage.openMenu();
        cdnChinaPage.switchLanguage(language);
    }
}
