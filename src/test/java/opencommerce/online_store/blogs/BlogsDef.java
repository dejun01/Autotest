package opencommerce.online_store.blogs;

import com.opencommerce.shopbase.dashboard.apps.review.steps.AllReviewsSteps;
import com.opencommerce.shopbase.dashboard.online_store.blogs.steps.BlogsSteps;
import com.opencommerce.shopbase.storefront.steps.shop.ProductSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class BlogsDef {
    @Steps
    BlogsSteps blogsSteps;
    @Steps
    AllReviewsSteps allReviewsSteps;
    @Steps
    ProductSteps productSteps;

    @When("verify blogs on storefront  {string}")
    public void verifyBlogsOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String containerType = SessionData.getDataTbVal(dataTable, row, "Container type");
            boolean showSidebar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sidebar"));
            String placement = SessionData.getDataTbVal(dataTable, row, "Placement");
            String layout = SessionData.getDataTbVal(dataTable, row, "Layout");
            String postsToShow = SessionData.getDataTbVal(dataTable, row, "Posts to show");
            String postsPerRow = SessionData.getDataTbVal(dataTable, row, "Posts per row");
            blogsSteps.verifyContainerType("Blog", Boolean.parseBoolean(containerType));
            blogsSteps.verifyShowSidebar("Blog", showSidebar);
            if (showSidebar) {
                blogsSteps.verifyPlacement("Blog", Boolean.parseBoolean(placement));
            }
            blogsSteps.verifyLayout(layout);
            blogsSteps.verifyPostsToShow(postsToShow);
            if (layout.equalsIgnoreCase("Grid")) {
                blogsSteps.verifyPostsPerRow(postsPerRow);
            }
        }
    }

    @When("verify blogs post on storefront  {string}")
    public void verifyBlogsPostOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String containerType = SessionData.getDataTbVal(dataTable, row, "Container type");
            boolean showSidebar = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show sidebar"));
            String placement = SessionData.getDataTbVal(dataTable, row, "Placement");
            String design = SessionData.getDataTbVal(dataTable, row, "Design");
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));
            boolean showDate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show date"));
            boolean showTagsArticleContent = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags article content"));
            boolean showSocial = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show social"));
            boolean showRelatedArticle = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show related article"));
            boolean showComments = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show comments"));
            String contentAlignment = SessionData.getDataTbVal(dataTable, row, "Content alignment");
            boolean showTagsRelatedArticles = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show tags related articles"));
            String showPublishedDate = SessionData.getDataTbVal(dataTable, row, "Show published date");
            boolean showExcerpt = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show excerpt"));
            boolean showReadMoreLink = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show read more link"));
            blogsSteps.verifyContainerType("Blog post", Boolean.parseBoolean(containerType));
            blogsSteps.verifyShowSidebar("Blog post", showSidebar);
            if (showSidebar) {
                blogsSteps.verifyPlacement("Blog post", Boolean.parseBoolean(placement));
            }
            blogsSteps.verifyDesign(design);
            blogsSteps.verifyShowAuthorDate("By", showAuthor);
            blogsSteps.verifyShowAuthorDate("On", showDate);
            blogsSteps.verifyShowTagsArticleContent(showTagsArticleContent);
            blogsSteps.verifyShowSocial(showSocial);
            blogsSteps.verifyShowRelatedArticle(showRelatedArticle);
            blogsSteps.verifyShowComments(showComments);
            if (showRelatedArticle) {
                blogsSteps.verifyContentAlignment(contentAlignment);
                blogsSteps.verifyShowTagsRelatedArticles(showTagsRelatedArticles);
                blogsSteps.verifyShowPublishedDate(showPublishedDate);
                blogsSteps.verifyShowExcerpt(showExcerpt);
                blogsSteps.verifyShowReadMoreLink(showReadMoreLink);
            }
        }
    }

    @And("remove all blog post")
    public void removeAllBlogPost() {
        blogsSteps.clickCheckBox();
        blogsSteps.clickButtonAction();
        blogsSteps.clickDeleteBlog();
        blogsSteps.verifyDeleteBlog();
    }

    @When("create blog posts {string}")
    public void createBlogPosts(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String visibility = SessionData.getDataTbVal(dataTable, row, "Visibility");
            String featuredImage = SessionData.getDataTbVal(dataTable, row, "Featured image");
            String author = SessionData.getDataTbVal(dataTable, row, "Author");
            String blog = SessionData.getDataTbVal(dataTable, row, "Blog");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");
            blogsSteps.clickButtonAddBlogPost();
            blogsSteps.inputTitle(title);
//            blogsSteps.inputContent(content);
            blogsSteps.selectVissibility(visibility);
            if (!featuredImage.isEmpty()) {
                blogsSteps.uploadFeaturedImage("Featured image", featuredImage);
            }
            blogsSteps.selectAuthor(author);
            blogsSteps.setectBlog(blog);
            blogsSteps.addTags(tags);
            blogsSteps.clickButtonSave();
            blogsSteps.verifyMess(title, message);
        }

    }

    @And("back to blog posts page")
    public void backToBlogPostsPage() {
        blogsSteps.backToBlogPostsPage();
    }

    @Then("verify blogs post on SF {string}")
    public void verifyBlogsPostOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String visibility = SessionData.getDataTbVal(dataTable, row, "Visibility");
            String author = SessionData.getDataTbVal(dataTable, row, "Author");
            String blog = SessionData.getDataTbVal(dataTable, row, "Blog");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            productSteps.openPage(url);
            blogsSteps.verifyVisibility(visibility);
            if (!visibility.equalsIgnoreCase("Hidden")) {
                blogsSteps.verifyBlogTitle(title);
//                blogsSteps.verifyContent(content);
                blogsSteps.verifyAuthor(author);
                blogsSteps.verifyBlog(blog);
                blogsSteps.verifyTags(tags);
            }
        }
    }

    @And("open blog post {string}")
    public void openBlogPost(String titleBlog) {
        blogsSteps.openBlogPost(titleBlog);
    }

    @And("edit blog post")
    public void editBlogPost(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String visibility = SessionData.getDataTbVal(dataTable, row, "Visibility");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            blogsSteps.inputTitle(title);
//            blogsSteps.inputContent(content);
            blogsSteps.selectVissibility(visibility);
            blogsSteps.addTags(tags);
            blogsSteps.clickButtonSave();
        }
    }

    @Then("verify blog post edited on SF")
    public void verifyBlogPostEditedOnSF(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String content = SessionData.getDataTbVal(dataTable, row, "Content");
            String visibility = SessionData.getDataTbVal(dataTable, row, "Visibility");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
            productSteps.openPage(url);
            blogsSteps.verifyVisibility(visibility);
            if (!visibility.equalsIgnoreCase("Hidden")) {
                blogsSteps.verifyBlogTitle(title);
//                blogsSteps.verifyContent(content);
                blogsSteps.verifyTags(tags);
            }

        }
    }

    @And("choose filter blog posts in dashboard as {string}")
    public void chooseFilterBlogPostsInDashboardAs(String dataKey, List<List<String>> dataTable) {
        allReviewsSteps.clickBtnFilter();
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String option = SessionData.getDataTbVal(dataTable, row, "Option");
            String value = SessionData.getDataTbVal(dataTable, row, "value");
            allReviewsSteps.chooseOption(option);
            if (option.equals("Visibility") | option.equals("Image")) {
                blogsSteps.chooseValue(value);
            }
            if (option.equals("Blog") | option.equals("Author")) {
                blogsSteps.selectValue(option, value);
            }
            if (option.equals("Tagged with")) {
                blogsSteps.inputTags(option,value);
            }

        }
    }

    @Then("verify list blog post after filter as {string}")
    public void verifyListBlogPostAfterFilterAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String listBlogPostShow = SessionData.getDataTbVal(dataTable, row, "Blog post show ");
            String listBlogPostDontShow = SessionData.getDataTbVal(dataTable, row, "Blog post don't show");
            if (!listBlogPostShow.equals("")) {
                String[] blogPostShow = listBlogPostShow.split(",");
                for (String s : blogPostShow) {
                    blogsSteps.verifyBlogPostShow(s, true);
                }
            }
            if (!listBlogPostDontShow.equals("")) {
                String[] blogPostDontShow = listBlogPostDontShow.split(",");
                for (String s : blogPostDontShow) {
                    blogsSteps.verifyBlogPostShow(s, false);
                }
            }
        }
    }

    @And("action to blog posts as {string}")
    public void actionToBlogPostsAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String blogPost = SessionData.getDataTbVal(dataTable, row, "Blog post");
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            blogsSteps.searchAndCheckBlogPost(blogPost);
            blogsSteps.clickButtonAction();
            allReviewsSteps.selectAction(action);
            if (action.equalsIgnoreCase("Delete")){
                blogsSteps.clickButtonDelete();
            }
        }
    }

    @And("add tags blog post as {string}")
    public void addTagsBlogPostAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String action = SessionData.getDataTbVal(dataTable, row, "Action");
            String tags = SessionData.getDataTbVal(dataTable, row, "Tags");
                blogsSteps.enterTags(tags);
                blogsSteps.clickButtonApplyChanges();
        }
    }

    @Then("verify blogs post show on SF {string}")
    public void verifyBlogsPostShowOnSF(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            boolean show = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show"));
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            productSteps.openPage(url);
            blogsSteps.verifyBlogPostShowOnSF(show);
        }
    }

    @When("verify blogs roller on storefront {string}")
    public void verifyBlogsRollerOnStorefront(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String layoutOnLargeScreen = SessionData.getDataTbVal(dataTable, row, "Layout");
            boolean showFeatureImage = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show feature image"));
            String featureImageHeight = SessionData.getDataTbVal(dataTable, row, "Feature image height");
            int showDate = Integer.parseInt(SessionData.getDataTbVal(dataTable, row, "Show date"));
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));

            blogsSteps.verifyLayoutOnLargeScreen(layoutOnLargeScreen);
            if (layoutOnLargeScreen.equalsIgnoreCase("Grid")){
                blogsSteps.verifyFeatureImageHeightBlog(featureImageHeight);
            }

            blogsSteps.verifyShowFeatureImage(showFeatureImage);
            blogsSteps.verifyShowAuthorBlog(showAuthor);
            blogsSteps.verifyShowDateBlog(showDate);
        }
    }


    @When("verify blogs post roller on storefront {string}")
    public void verifyBlogsPostRollerOnStoreFront(String dataKey, List<List<String>> dataTable){
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()){
            String featureImageHeight = SessionData.getDataTbVal(dataTable, row, "Feature image height");
            String showDate = SessionData.getDataTbVal(dataTable, row, "Show date");
            boolean showAuthor = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Show author"));

            blogsSteps.verifyFeatureImageHeightBlogPost(featureImageHeight);
            blogsSteps.verifyShowDateAuthorBlogPost(showDate, showAuthor);
        }
    }
}
