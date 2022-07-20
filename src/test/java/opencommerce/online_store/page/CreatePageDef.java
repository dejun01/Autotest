package opencommerce.online_store.page;

import com.opencommerce.shopbase.storefront.steps.createpage.CreatePageStep;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import java.util.List;


public class CreatePageDef {
    @Steps
    CreatePageStep createPagesSteps;

    @Then("^create new page and edit page after created \"([^\"]*)\"$")
    public void inputDataforCreateNewPage(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String oldTitle = SessionData.getDataTbVal(dataTable, row, "Old title");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String text = SessionData.getDataTbVal(dataTable, row, "Text");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String video = SessionData.getDataTbVal(dataTable, row, "Video");
            String template = SessionData.getDataTbVal(dataTable, row, "Template");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String editUrl = SessionData.getDataTbVal(dataTable, row, "URL");

            if (oldTitle.isEmpty()) {
                createPagesSteps.clickToAddPageBtn();
            } else {
                createPagesSteps.clickToNewPage(oldTitle);
            }
            createPagesSteps.enterInputTitle(title);
            createPagesSteps.insertLink(link, text);
            createPagesSteps.insertVideoLink(video);
            createPagesSteps.selectPageTemplate(template);
            createPagesSteps.checkToVisibilityRadioBtn(status);
            createPagesSteps.enterInputEditUrl(editUrl);
            createPagesSteps.clickToSaveBtn();
        }
    }

    @And("^verify page created on dashboard \"([^\"]*)\"$")
    public void verifyNewPageCreatedOnDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");

            if (title.isEmpty()) {
                createPagesSteps.verifyErrorMessage();
            } else {
                createPagesSteps.switchBackToPages();
            }
            switch (status) {
                case "Visible":
                    createPagesSteps.verifyNewPageCreatedOnDashboard(title);
                    break;
                case "Hidden":
                    createPagesSteps.verifyNewPageCreatedOnDashboard(title);
                    createPagesSteps.verifyPageStatus(title);
                    break;
                case "Deleted":
                    createPagesSteps.verifyDeletedPage(title);
                    break;
                default:
                    createPagesSteps.verifyAllPagesDeleted();
            }
        }
    }

    @And("^verify page created on storefront \"([^\"]*)\"$")
    public void verifyPageOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String link = SessionData.getDataTbVal(dataTable, row, "Link");
            String video = SessionData.getDataTbVal(dataTable, row, "Video");
            String template = SessionData.getDataTbVal(dataTable, row, "Template");
            boolean isVisible = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isVisible"));
            String editUrl = SessionData.getDataTbVal(dataTable, row, "URL");

            createPagesSteps.goToNewPageURL(title, editUrl);
            if (!isVisible) {
                createPagesSteps.verifyPageNotFound();
            } else {
                createPagesSteps.verifyNewPageTitle(title);
                if (!template.isEmpty()) {
                    createPagesSteps.verifyInsertedLink(link);
                    createPagesSteps.verifyVideoLink(video);
                    createPagesSteps.verifyPageTemplate(template);
                }
            }
        }
    }

    @Then("^do actions with new page \"([^\"]*)\"$")
    public void actionsWithNewPageOnDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");

            if (title.equals("All pages")) {
                createPagesSteps.selectAllPages();
            } else {
                createPagesSteps.selectPageCreatedOnDashboard(title);
            }
            createPagesSteps.actionWithNewPage(action);
        }
    }
}
