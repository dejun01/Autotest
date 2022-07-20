package opencommerce.online_store.preferences;

import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.EditLocaleSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class EditLocaleDef {
    @Steps
    EditLocaleSteps editLocaleSteps;

    @And("^user direct to edit language pack$")
    public void userDirectToEditLanguagePack() {
        editLocaleSteps.goToEditPackFromThemeAction();
    }

    @Then("^verify language dropdown$")
    public void verifyLanguageDropdown(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Theme or App");
            String page = SessionData.getDataTbVal(dataTable, row, "Page");
            int allLang = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Count language supported"));

            editLocaleSteps.selectThemeOrApp(page, title);
            editLocaleSteps.checkAllLang(page);
            int countLang = editLocaleSteps.countAllLang(page);
            editLocaleSteps.verifyAllLangInDdlList(countLang, allLang);
        }
    }

    @And("^verify language selected on edit pack$")
    public void verifyLanguageSelectedOnEditPack(List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Theme or App");
            String lang = SessionData.getDataTbVal(dataTable, row, "Language");
            String key = SessionData.getDataTbVal(dataTable, row, "Key");
            String phrase = SessionData.getDataTbVal(dataTable, row, "Phrase");
            String editKey = SessionData.getDataTbVal(dataTable, row, "Edit key");

            if(key.isEmpty()){
                editLocaleSteps.selectThemeAndLangOnPrePage(title, lang);
                editLocaleSteps.directToEditPack();
                editLocaleSteps.verifySelectedOptionOnEditPack(title, lang);
            }else{
                editLocaleSteps.selectThemeAndLangOnEditPack(title, lang);
                editLocaleSteps.searchKeyOnTable(key);
                switch (key){
                    case "no result":
                        editLocaleSteps.verifyEmptyResult();
                        break;
                    case "share":
                        if(phrase.isEmpty()){
                            editLocaleSteps.editLangPhrase(key, "");
                            editLocaleSteps.verifyEditedPhrase("");
                        }else {
                            editLocaleSteps.verifyEditedPhrase(phrase);
                        }
                        break;
                    default:
                        editLocaleSteps.editLangPhrase(key, editKey);
                        editLocaleSteps.saveEditAction(key);
                        editLocaleSteps.waitToSavedMessInvisible();
                        editLocaleSteps.searchKeyOnTable(key);
                        editLocaleSteps.verifyEditedPhrase(editKey);
                        break;
                }
                editLocaleSteps.searchKeyOnTable("");
            }
        }
    }

    @And("^verify key displayed on page$")
    public void verifyKeyDisplayedOnPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            int rowOnPage = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Row"));

            editLocaleSteps.selectRowPerPage(rowOnPage);
            int allKey = editLocaleSteps.countAllKey();
            editLocaleSteps.verifyAllKeyDisplayedOnPage(allKey, rowOnPage);
        }
    }

    @And("^verify sort action$")
    public void verifySortAction(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String col = SessionData.getDataTbVal(dataTable, row, "Column");
            String sort = SessionData.getDataTbVal(dataTable, row, "Sort");

            editLocaleSteps.sortKeyOnTable(col, sort);
            if(sort.equals("A-Z")){
                editLocaleSteps.verifySortAscendingAction();
            }else {
                editLocaleSteps.verifySortDescendingAction();
            }
        }
    }

    @And("^verify language edited on SF$")
    public void verifyLanguageEditedOnSf(List<List<String>> dataTable)  {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String lang = SessionData.getDataTbVal(dataTable, row, "Language");
            String editKey = SessionData.getDataTbVal(dataTable, row, "Edit key");
            String prod = SessionData.getDataTbVal(dataTable, row, "Product");

            editLocaleSteps.openShopOnSF(prod);
            editLocaleSteps.clickOnLangBtn();
            editLocaleSteps.selectLangOnSF(lang);
            if(editKey.equals("Lugar")){
                editLocaleSteps.clickOnLangBtn();
                editLocaleSteps.verifyThemePhrase(editKey);
                editLocaleSteps.closePopup();
            }else {
                editLocaleSteps.verifyAppPhrase(editKey);
            }
        }
    }
}

