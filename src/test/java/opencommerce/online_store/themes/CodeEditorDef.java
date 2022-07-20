package opencommerce.online_store.themes;

import com.opencommerce.shopbase.common.steps.CommonSteps;
import com.opencommerce.shopbase.dashboard.apps.review.steps.ReviewInProductAdminSteps;
import com.opencommerce.shopbase.dashboard.online_store.themes.steps.CodeEditorSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CodeEditorDef {
    @Steps
    CodeEditorSteps codeEditorSteps;
    @Steps
    ReviewInProductAdminSteps reviewInProductAdminSteps;

    @When("delete all themes on block More themes$")
    public void deleteAllThemes(){
        codeEditorSteps.deleteAllThemes();
    }

    @When("^open file \"([^\"]*)\"$")
    public void openFile(String filePath){
        codeEditorSteps.openFile(filePath);
    }

    @When("^edit code of file store theme \"([^\"]*)\"$")
    public void editCode(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String code = SessionData.getDataTbVal(dataTable, row, "Code");

            codeEditorSteps.editCode(code);
        }
    }

    @When("save file and verify log file revision$")
    public void saveAndVerifyLogFileRevision(){
        int before = codeEditorSteps.countFileRevision();
        codeEditorSteps.clickOnButton("Save");
        int after = codeEditorSteps.countFileRevision();
        assertThat(before + 1).isEqualTo(after);
        codeEditorSteps.verifyCurrentVersion("Current");
    }

    @When("^click on text link \"([^\"]*)\"$")
    public void clickOnTextLink(String textLink){
        codeEditorSteps.clickOnTextLink(textLink);
    }

    @When("^verify number of theme on block More themes \"([^\"]*)\"$")
    public void verifyNumberOfThemeInList(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            int numberOfTheme = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Number of theme"));
            String themesName = SessionData.getDataTbVal(dataTable, row, "Themes name");

            codeEditorSteps.verifyNumberOfThemeInList(numberOfTheme);
            codeEditorSteps.verifyThemesName(themesName);
        }
    }

    @When("^verify current theme \"([^\"]*)\"$")
    public void verifyCurrentTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String themesName = SessionData.getDataTbVal(dataTable, row, "Themes name");

            codeEditorSteps.verifyCurrentTheme(themesName);
        }
    }

    @When("roll back to the {string} version")
    public void selectTheFirstVersion(String version){
        codeEditorSteps.selectVersion(version);
    }

    @When("^verify show actions with theme \"([^\"]*)\"$")
    public void verifyShowActionWithTheme(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String themeName = SessionData.getDataTbVal(dataTable, row, "Theme name");
            boolean isShowAction = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShowAction"));

            codeEditorSteps.verifyShowActionWithTheme(themeName, action, isShowAction);
        }
    }

    @When("verify current version is {string}")
    public void verifyCurrentVersion(String version){
        codeEditorSteps.verifyCurrentVersion(version);
    }

    @When("verify show file {string}")
    public void verifyShowFile(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()){
            String file = SessionData.getDataTbVal(dataTable, row, "File");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));

            codeEditorSteps.verifyShowFile(file, isShow);
        }
    }

    @When("do action with file {string}")
    public void doActionWithFile(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String file = SessionData.getDataTbVal(dataTable, row, "File");

            String[] doAction = action.split(">");
            codeEditorSteps.openFile(file);
            codeEditorSteps.selectActionWithFile(file, doAction[0]);

            if(doAction[0].equals("Delete")){
                reviewInProductAdminSteps.clickButtonOnPopup("Delete");
            }else {
                codeEditorSteps.inputTextBoxOnPopup(doAction[1]);
                reviewInProductAdminSteps.clickButtonOnPopup("Rename");
            }
        }
    }

    @When("click on button {string}")
    public void clickOnButton(String buttonName){
        codeEditorSteps.clickOnButton(buttonName);
    }

    @When("verify text on dashboard {string}")
    public void verifyTextOnDashBoard(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String file = SessionData.getDataTbVal(dataTable, row, "File");
            boolean isShow = Boolean.parseBoolean( SessionData.getDataTbVal(dataTable, row, "isShow"));

            codeEditorSteps.openFile(file);
            codeEditorSteps.verifyTextOnDashBoard(text, isShow);
        }
    }

    @When("verify text on storefront {string}")
    public void verifyTextOnStorefront(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));
            String color = SessionData.getDataTbVal(dataTable, row, "Color");

            codeEditorSteps.verifyTextOnStorefront(text, isShow);
        }
    }

    @When("^click on back icon to navigate (.*) page$")
    public void clickOnBackIcon(String page){
        codeEditorSteps.clickOnBackIcon(page);
    }


    @And("search and select file {string}")
    public void searchAndSelectFile(String file){
        codeEditorSteps.clickOnIcon("Search");
        codeEditorSteps.searchAndSelectFile(file);
    }
}