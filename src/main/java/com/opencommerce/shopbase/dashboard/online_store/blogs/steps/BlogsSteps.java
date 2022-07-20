package com.opencommerce.shopbase.dashboard.online_store.blogs.steps;

import com.opencommerce.shopbase.dashboard.online_store.blogs.pages.BlogsPage;

public class BlogsSteps {
    BlogsPage blogsPage;

    public void verifyContainerType(String section, boolean iSType) {
        blogsPage.verifyContainerType(section, iSType);
    }

    public void verifyShowSidebar(String section, boolean showSidebar) {
        blogsPage.verifyShowSidebar(section, showSidebar);
    }

    public void verifyPlacement(String section, boolean placement) {
        blogsPage.verifyPlacement(section, placement);
    }

    public void verifyLayout(String layout) {
        blogsPage.verifyLayout(layout);
    }

    public void verifyPostsToShow(String postsToShow) {
        blogsPage.verifyPostsToShow(postsToShow);
    }

    public void verifyPostsPerRow(String postsPerRow) {
        blogsPage.verifyPostsPerRow(postsPerRow);
    }

    public void verifyDesign(String design) {
        blogsPage.verifyDesign(design);
    }

    public void verifyShowAuthorDate(String expect, boolean showAuthor) {
        blogsPage.verifyShowAuthorDate(expect, showAuthor);
    }

    public void verifyShowTagsArticleContent(boolean showTagsArticleContent) {
        blogsPage.verifyShowTagsArticleContent(showTagsArticleContent);
    }

    public void verifyShowSocial(boolean showSocial) {
        blogsPage.verifyShowSocial(showSocial);
    }

    public void verifyShowRelatedArticle(boolean showRelatedArticle) {
        blogsPage.verifyShowRelatedArticle(showRelatedArticle);
    }

    public void verifyShowComments(boolean showComments) {
        blogsPage.verifyShowComments(showComments);
    }

    public void verifyContentAlignment(String contentAlignment) {
        blogsPage.verifyContentAlignment(contentAlignment);
    }

    public void verifyShowTagsRelatedArticles(boolean showTagsRelatedArticles) {
        blogsPage.verifyShowTagsRelatedArticles(showTagsRelatedArticles);
    }

    public void verifyShowPublishedDate(String showPublishedDate) {
        blogsPage.verifyShowPublishedDate(showPublishedDate);
    }

    public void verifyShowExcerpt(boolean showExcerpt) {
        blogsPage.verifyShowExcerpt(showExcerpt);
    }

    public void verifyShowReadMoreLink(boolean showReadMoreLink) {
        blogsPage.verifyShowReadMoreLink(showReadMoreLink);
    }

    public void clickCheckBox() {
        blogsPage.clickCheckBox();
    }

    public void clickButtonAction() {
        blogsPage.clickButtonAction();
    }

    public void clickDeleteBlog() {
        blogsPage.clickDeleteBlog();
    }

    public void verifyDeleteBlog() {
        blogsPage.verifyDeleteBlog();
    }

    public void clickButtonAddBlogPost() {
        blogsPage.clickButtonAddBlogPost();
    }

    public void inputTitle(String title) {
        blogsPage.inputTitle(title);
    }

    public void inputContent(String content) {
        blogsPage.inputContent(content);
    }

    public void selectVissibility(String visibility) {
        blogsPage.selectRadioButtonWithLabel(visibility, true);
    }

    public void uploadFeaturedImage(String label, String featuredImage) {
        blogsPage.uploadFeaturedImage(label, featuredImage);
    }

    public void selectAuthor(String author) {
        blogsPage.selectDdlWithLabel("Author", author);
    }

    public void setectBlog(String blog) {
        blogsPage.selectDdlWithLabel("Blog", blog);
    }

    public void addTags(String tags) {
        blogsPage.addTags(tags);
    }

    public void clickButtonSave() {
        blogsPage.clickButtonSave();
    }

    public void verifyMess(String title, String message) {
        if (!message.isEmpty()) {
            blogsPage.verifyMess(title);
        }
    }

    public void backToBlogPostsPage() {
        blogsPage.backToBlogPostsPage();
    }

    public void openBlogPostOnSF(String title) {
        blogsPage.openBlogPostOnSF(title);
    }

    public void verifyVisibility(String visibility) {
        blogsPage.verifyVisibility(visibility);
    }

    public void verifyBlogTitle(String title) {
        blogsPage.verifyBlogTitle(title);
    }

    public void verifyContent(String content) {
        blogsPage.verifyContent(content);
    }

    public void verifyAuthor(String author) {
        blogsPage.verifyAuthor(author);
    }

    public void verifyBlog(String blog) {
        blogsPage.verifyBlog(blog);
    }

    public void verifyTags(String tags) {
        blogsPage.verifyTags(tags);
    }

    public void openBlogPost(String titleBlog) {
        blogsPage.openBlogPost(titleBlog);
    }

    public void chooseValue(String value) {
        blogsPage.chooseValue(value);
    }

    public void selectValue(String option, String value) {
        if (option.equalsIgnoreCase("Author")) {
            blogsPage.selectDdlWithLabel(option, value, 2);
        }else {
            blogsPage.selectDdlWithLabel(option, value, 1);
        }
    }

    public void inputTags(String option, String value) {
        blogsPage.inputTags(option, value);
    }

    public void verifyBlogPostShow(String blogPostShow, boolean isShow) {
        blogsPage.verifyBlogPostShow(blogPostShow, isShow);
    }

    public void searchAndCheckBlogPost(String blogPost) {
        blogsPage.inputKeySearchAndEnter(blogPost);
        blogsPage.checkChooseBlogPost(blogPost);
    }

    public void enterTags(String tags) {
        blogsPage.enterTags(tags);
    }

    public void clickButtonApplyChanges() {
        blogsPage.clickButtonApplyChanges();
    }

    public void clickButtonDelete() {
        blogsPage.clickButtonDelete();
    }

    public void verifyBlogPostShowOnSF(Boolean show) {
        blogsPage.verifyBlogPostShowOnSF(show);

    }

    public void verifyLayoutOnLargeScreen(String layout){
        blogsPage.verifyLayoutOnLargeScreen(layout);
    }
    public void verifyFeatureImageHeightBlog(String featureImageHeight){
        blogsPage.verifyFeatureImageHeightBlog(featureImageHeight);
    }

    public void verifyShowFeatureImage(boolean showFeatureImage){
        blogsPage.verifyShowFeatureImage(showFeatureImage);
    }

    public void verifyShowAuthorBlog(boolean showAuthor){
        blogsPage.verifyShowAuthorBlog(showAuthor);
    }

    public void verifyShowDateBlog(int showDate){
        blogsPage.verifyShowDateBlog(showDate);
    }

    public void verifyFeatureImageHeightBlogPost(String featureImageHeight) {
       blogsPage.verifyFeatureImageHeightBlogPost(featureImageHeight);
    }

    public void verifyShowDateAuthorBlogPost(String showDate, boolean showAuthor) {
        blogsPage.verifyShowDateAuthorBlogPost(showDate, showAuthor);
    }

}

