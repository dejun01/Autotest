package com.opencommerce.shopbase.dashboard.online_store.blogs.pages;

import common.SBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BlogsPage extends SBasePageObject {
    public BlogsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyContainerType(String section, boolean iSType) {
        if (section.equalsIgnoreCase("Blog")) {
            verifyElementPresent("//*[contains(@class,'blog-page container-fluid')]", iSType);
        } else {
            verifyElementPresent("//*[contains(@class,'blog-post-page container-fluid')]", iSType);
        }
    }

    public void verifyShowSidebar(String section, boolean showSidebar) {
        verifyElementPresent("//*[contains(@class,'blog-page__sidebar')]", showSidebar);
    }

    public void verifyPlacement(String section, boolean placement) {
        if (section.equalsIgnoreCase("Blog")) {
            verifyElementPresent("//*[contains(@class,'blog-page')]//div[contains(@class,'justify-space-between row-reverse')]", placement);
        } else {
            verifyElementPresent("//*[contains(@class,'blog-post-page')]//div[contains(@class,'justify-space-between row-reverse')]", placement);
        }
    }

    public void verifyLayout(String layout) {
        if (layout.equalsIgnoreCase("List")) {
            verifyElementPresent("//*[contains(@class,'blog-page')]//div[@class='row']", false);
        } else {
            verifyElementPresent("//*[contains(@class,'blog-page')]//div[@class='row']", true);
        }
    }

    public void verifyPostsToShow(String postsToShow) {
        int count = countElementByXpath("//*[contains(@class,'blog-page')]//h2[contains(@class,'blog-page__card-title')]");
        assertThat(count).isEqualTo(Integer.parseInt(postsToShow));
    }

    public void verifyPostsPerRow(String postsPerRow) {
        verifyElementPresent("(//*[contains(@class,'blog-page')]//div[contains(@class,'col-lg-" + postsPerRow + "')])[1]", true);
    }

    public void verifyDesign(String design) {
        if (design.equalsIgnoreCase("Layout 1")) {
            verifyElementPresent("//*[contains(@class,'blog-post-page')]//div[contains(@class,'blog-post-page__container--inside')]", true);
        } else {
            verifyElementPresent("//*[contains(@class,'blog-post-page')]//div[contains(@class,'blog-post-page__body--outside')]", true);
        }

    }

    public void verifyShowAuthorDate(String text, boolean showAuthor) {
        String textAR = getText("//*[contains(@class,'blog-post-page')]//p[contains(@class,'blog-post-page__body')]");
        if (showAuthor) {
            assertThat(textAR).contains(text);
        } else {
            assertThat(textAR).doesNotContain(text);
        }
    }

    public void verifyShowTagsArticleContent(boolean showTagsArticleContent) {
        verifyElementPresent("//*[contains(@class,'blog-post-page')]//span[contains(@class,'blog-post-page__body--inside__tags')]", showTagsArticleContent);
    }

    public void verifyShowSocial(boolean showSocial) {
        verifyElementPresent("//*[contains(@class,'blog-post-page__body')]//div[contains(@class,'align-center')]", showSocial);
    }

    public void verifyShowRelatedArticle(boolean showRelatedArticle) {
        verifyElementPresent("//*[contains(@class,'blog-post-page')]//div[contains(@class,'blog-page__sidebar')]", showRelatedArticle);
    }

    public void verifyShowComments(boolean showComments) {
        verifyElementPresent("//*[contains(@class,'blog-post-page')]//div[contains(@class,'comment-block')]", showComments);
    }

    public void verifyContentAlignment(String contentAlignment) {
        if (contentAlignment.equalsIgnoreCase("Center")) {
            verifyElementPresent("//*[contains(@class,'blog-post-page__related')]//div[contains(@class,'related-post text-align-center')]", true);
        } else {
            verifyElementPresent("//*[contains(@class,'blog-post-page__related')]//div[contains(@class,'related-post text-align-center')]", false);
        }
    }

    public void verifyShowTagsRelatedArticles(boolean showTagsRelatedArticles) {
        verifyElementPresent("//*[contains(@class,'blog-post-page__related')]//a[contains(@class,'related-post__tags')]", showTagsRelatedArticles);
    }

    public void verifyShowPublishedDate(String showPublishedDate) {
        int count = countElementByXpath("(//*[contains(@class,'blog-post-page__related')]//div[contains(@class,'related-post__credit')])[1]//span");
        assertThat(count).isEqualTo(Integer.parseInt(showPublishedDate));
    }

    public void verifyShowExcerpt(boolean showExcerpt) {
        verifyElementPresent("//*[contains(@class,'blog-post-page__related')]//div[contains(@class,'related-post__excerpt')]", showExcerpt);
    }

    public void verifyShowReadMoreLink(boolean showReadMoreLink) {
        verifyElementPresent("//*[contains(@class,'blog-post-page__related')]//a[contains(@class,'related-post__read-more')]", showReadMoreLink);
    }

    public void clickCheckBox() {
        if (isElementExist("//table[contains(@class,'table-condensed')]//label[@class='s-checkbox']"))
            clickOnElement("//table[contains(@class,'table-condensed')]//label[@class='s-checkbox']");
    }

    public void clickButtonAction() {
        clickOnElementByJs("//table[contains(@class,'table-condensed')]//button");
    }

    public void clickDeleteBlog() {
        clickOnElementByJs("//table[contains(@class,'table-condensed')]//span[normalize-space()='Delete selected blog posts']");
        clickOnElementByJs("//div[contains(@class,'s-modal-content')]//button[normalize-space()='Delete']");
    }

    public void verifyDeleteBlog() {
        verifyElementPresent("//section[contains(@class,'empty-state')]", true);
    }

    public void clickButtonAddBlogPost() {
        if (isElementExist("//section[contains(@class,'empty-state')]//button")) {
            clickOnElementByJs("//section[contains(@class,'empty-state')]//button");

        } else {
            clickOnElementByJs("//div[contains(@class,'order-title')]//button");
        }
    }

    public void inputTitle(String title) {
        clearAndInputTextByJS("//div[child::label[normalize-space()='Title']]//following-sibling::div//input", title);
    }

    public void inputContent(String content) {
        switchToIFrame("//div[contains(@class,'tox-sidebar-wrap')]//iframe");
//        $("//body[@id='tinymce']").clear();
        $("//body[@id='tinymce']/p").sendKeys(content);
        switchToIFrameDefault();
    }

    public void uploadFeaturedImage(String label, String featuredImage) {
        if (isElementExist("//div[contains(@class,'card__header hide-when-printing') and descendant::div[normalize-space()='"+label+"']]//following-sibling::div//button"))
        {
            clickOnElement("//div[contains(@class,'card__header hide-when-printing') and descendant::div[normalize-space()='"+label+"']]//following-sibling::div//button");
        }
        String xpath = "//div[contains(@class,'card__header hide-when-printing') and descendant::div[normalize-space()='" + label + "']]//following-sibling::div//input[@type='file']";
        changeStyle(xpath);
        chooseAttachmentFile(xpath, featuredImage);
        waitABit(1000);
    }

    public void addTags(String tags) {
        if (isElementExist("//div[contains(@class,'tag-list-items')]//a")) {
            clickOnElement("//div[contains(@class,'tag-list-items')]//a");
        }
        if (!tags.isEmpty()) {
            waitClearAndTypeThenEnter("//div[child::label[normalize-space()='Tags']]//parent::div//following-sibling::div//input", tags);
            if (isElementExist("//div[contains(@class,'s-dropdown-menu is-opened-top')]//i")) {
                clickOnElementByJs("//div[contains(@class,'s-dropdown-menu is-opened-top')]//i");
            } else {
                clickOnElementByJs("//div[contains(@class,'s-dropdown-content')]//span[child::span[normalize-space()='" + tags + "']]");
            }
        }
    }

    public void clickButtonSave() {
        clickOnElementByJs("//div[contains(@class,'save-setting-fixed')]//button[child::span[normalize-space()='Save']]");
    }

    public void verifyMess(String title) {
        waitForPageLoad();
        verifyElementText("//div[contains(@class,'s-alert__content')]//span", title + " created");
    }

    public void backToBlogPostsPage() {
        clickOnElement("//ol[contains(@class,'s-breadcrumb')]//a");
    }

    public void openBlogPostOnSF(String title) {
        hoverOnElement("//span[normalize-space()='" + title + "']");
        clickOnElementByJs("//div[contains(@class,'s-flex-item') and descendant::span[normalize-space()='" + title + "']]//following-sibling::span//i");
        waitForPageLoad();
    }

    public void verifyVisibility(String visibility) {
        if (visibility.equalsIgnoreCase("Hidden")) {
            verifyElementPresent("//h2[contains(@class,'notfound-template__title')]", true);
        } else {
            verifyElementPresent("//h2[contains(@class,'notfound-template__title')]", false);

        }
    }

    public void verifyBlogTitle(String title) {
        if (!title.isEmpty()) {
            String xpath = "//*[contains(@class,'blog-post-page')]//h1[contains(@class,'heading')]";
            String textAR = getDriver().findElement(By.xpath(xpath)).getText();
            assertThat(textAR).contains(title);
        }
    }

    public void verifyContent(String content) {
        if (!content.isEmpty())
            verifyElementText("//div[contains(@class,'blog-post-page__body')]//p", content);
    }

    public void verifyAuthor(String author) {
        String textAR = getText("//*[contains(@class,'blog-post-page')]//p[contains(@class,'blog-post-page__body')]");
        assertThat(textAR).contains(author);
    }

    public void verifyBlog(String blog) {
        verifyElementText("(//div[contains(@class,'blog-post-page container')]//span[contains(@class,'breadcrumb_link')])[2]//a",blog);
    }

    public void verifyTags(String tags) {
        if(!tags.isEmpty()){
            verifyElementText("//div[contains(@class,'blog-post-page__body')]//a",tags);
        }
    }

    public void openBlogPost(String titleBlog) {
        clickOnElement("//a[child::span[normalize-space()='"+titleBlog+"']]");
    }

    public void chooseValue(String value) {
        String xpathCheckbox = "//div[@class='radio-group-item' and descendant::span[contains(text(),'" + value + "')]]//label";
        String xpathStatus = "//div[@class='radio-group-item' and descendant::span[contains(text(),'" + value + "')]]//input";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, true);
    }

    public void inputTags(String option,String value) {
        clearAndInputTextByJS("//div[@class='s-collapse-item is-active' and descendant::p[contains(text(),'"+option+"')]]//input",value);
    }

    public void verifyBlogPostShow(String blogPostShow, boolean isShow) {
        waitForPageLoad();
        verifyElementPresent("//a[child::span[normalize-space()='"+blogPostShow+"']]",isShow);
    }

    public void inputKeySearchAndEnter(String blogPost) {
        waitTypeAndEnter("//div[contains(@class,'s-input s-input--prefix')]//input",blogPost);

    }

    public void checkChooseBlogPost(String blogPost) {
        checkCheckbox("//td[descendant::span[normalize-space()='"+blogPost+"']]//preceding-sibling::td[contains(@class,'text-right cursor-default')]",true);
    }

    public void enterTags(String tags) {
        waitTypeAndEnter("//div[contains(@class,'s-taginput control')]//input",tags);
    }

    public void clickButtonApplyChanges() {
        clickOnElement("//div[contains(@class,'s-modal-footer')]//button[contains(@class,'s-button is-primary')]");
    }

    public void clickButtonDelete() {
        clickOnElement("//button[@class='s-button is-danger']");
    }

    public void verifyBlogPostShowOnSF(boolean show) {
        verifyElementPresent("//h2[contains(@class,'notfound-template__title')]",show);
    }

    public void verifyLayoutOnLargeScreen(String layout) {
        verifyElementPresent("//div[contains(@class, 'blog-page')]//div[@class='row']//div[contains(@class,'" + layout + "')]", true);
    }

    public void verifyFeatureImageHeightBlog(String featureImageHeight) {
        verifyElementPresent("//div[contains(@class,'blog-page__card-image--" + featureImageHeight.toLowerCase() + "')]", true);
    }

    public void verifyShowFeatureImage(boolean showFeatureImage) {
        verifyElementPresent("//div[contains(@class,'blog-page')]//div[@class='row']//img", showFeatureImage);
    }

    public void verifyShowAuthorBlog(boolean showAuthor) {
        verifyElementPresent("//p[contains(@class, 'blog-page__publish-date')]//span[contains(text(), 'By')]", showAuthor);
    }

    public void verifyShowDateBlog(int showDate) {
        int count = countElementByXpath("(//p[contains(@class, 'blog-page__publish-date')])[1]//span");
        assertThat(count).isEqualTo(showDate);
    }

    public void verifyFeatureImageHeightBlogPost(String featureImageHeight) {
        verifyElementPresent("//img[contains(@class,'blog-post-page__image " + featureImageHeight + "')]", true);
    }

    public void verifyShowDateAuthorBlogPost(String showDate, boolean showAuthor) {

        if (showDate.isEmpty() && !showAuthor){
            verifyElementPresent("//div[contains(@class, 'blog-post-page__body--above')]//p", false);
        } else {
            verifyElementContainsText("//div[contains(@class, 'blog-post-page__body--above')]//p", showDate);
        }
    }
}
