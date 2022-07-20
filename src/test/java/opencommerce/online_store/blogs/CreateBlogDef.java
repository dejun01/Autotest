package opencommerce.online_store.blogs;

import com.opencommerce.shopbase.dashboard.online_store.blogs.steps.CreateBlogSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import com.opencommerce.shopbase.common.pages.CommonPage;

import java.util.List;

public class CreateBlogDef {
    CommonPage commonPage;

    @Steps
    CreateBlogSteps createBlogSteps;

    @And("delete all blog")
    public void deleteAllBlog() {
        createBlogSteps.clickCheckbox();
        createBlogSteps.clickBtnAction();
        createBlogSteps.clickDeleteBlogs();
    }

    @Then("verify all blog deleted")
    public void verifyAllBlogDeleted() {
        createBlogSteps.verifyDeleteAllBlogs();
    }

    @Then("^create new blog and edit blog after created \"([^\"]*)\"$")
    public void createBlogAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String oldTitle = SessionData.getDataTbVal(dataTable, row, "Old Title");
            String blogTitle = SessionData.getDataTbVal(dataTable, row, "Blog Title");
            String pageTitle = SessionData.getDataTbVal(dataTable, row, "Page title");
            String metaDescription = SessionData.getDataTbVal(dataTable, row, "Meta description");
            String editURL = SessionData.getDataTbVal(dataTable, row, "URL");
            String comment = SessionData.getDataTbVal(dataTable, row, "Comment");

            if (oldTitle.isEmpty()) {
                createBlogSteps.clickToAddBlogBtn();
            } else {
                createBlogSteps.clickToNewBlog(oldTitle);
            }
            createBlogSteps.enterInputTitle("Title",blogTitle);
            createBlogSteps.clickLinkSEO();
            createBlogSteps.enterInputTitle("Page title", pageTitle);
            createBlogSteps.enterInputMetaDescription(metaDescription);
            createBlogSteps.enterInputUrlAndHandle(editURL);
            createBlogSteps.checkToVisibilityRadioBtn(comment);
            createBlogSteps.clickToSaveBtn();
            commonPage.waitForEverythingComplete(10);
            createBlogSteps.clickBtnCancel();
        }
    }

    @And("^verify blog created on dashboard \"([^\"]*)\"$")
    public void verifyNewBlogCreatedOnDashboard(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String blogTitle = SessionData.getDataTbVal(dataTable, row, "Blog Title");
            String comment = SessionData.getDataTbVal(dataTable, row, "Comment");

            if (blogTitle.isEmpty()) {
                createBlogSteps.verifyErrorMessage();
            }
            createBlogSteps.verifyBlogTitleDB(blogTitle);
            createBlogSteps.verifyComment(blogTitle, comment);
        }
    }

    @And("verify blog created on storefront {string}")
    public void verifyBlogCreatedOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String blogTitle = SessionData.getDataTbVal(dataTable, row, "Blog Title");
            String editUrl = SessionData.getDataTbVal(dataTable, row, "URL");
            createBlogSteps.goToNewBlogUrl(blogTitle, editUrl);
            createBlogSteps.verifyblogTitle(blogTitle);

        }
    }
}




