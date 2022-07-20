package com.opencommerce.shopbase.dashboard.online_store.blogs.steps;

import com.opencommerce.shopbase.dashboard.online_store.blogs.pages.CreateBlogPage;
import common.utilities.LoadObject;
import net.thucydides.core.steps.ScenarioSteps;

public class CreateBlogSteps extends ScenarioSteps {
    CreateBlogPage createBlogPage;
    String shop = LoadObject.getProperty("shop");

    public void clickToAddBlogBtn() {
        createBlogPage.clickToAddBlogBtn();
    }

    public void clickToNewBlog(String lable) {
        createBlogPage.clickToEditBlog(lable);
    }


    public void enterInputTitle(String lable, String blogTitle) {
        if (!blogTitle.isEmpty()) {
            createBlogPage.inputWithTitle(lable, blogTitle);
        }
    }

    public void enterInputMetaDescription(String metaDescription) {
        if(!metaDescription.isEmpty()){
            createBlogPage.inputMetaDescription(metaDescription);
        }
    }

    public void enterInputUrlAndHandle(String editURL) {
        createBlogPage.InputURL(editURL);
    }

    public void checkToVisibilityRadioBtn(String lable) {
     createBlogPage.checkRadioBtnComment(lable);
    }

    public void clickToSaveBtn() {
        createBlogPage.clickToSaveBtn();
    }

    public void verifyErrorMessage() {
        createBlogPage.verifyMsgErr();
    }

    public void goToNewBlogUrl(String blogTitle, String editUrl) {
        String blogUrl = ("https://" + shop + "/blogs/");
        String defaultUrl = (blogTitle.toLowerCase().replace(" ", "-"));
        if(editUrl.isEmpty()){
            createBlogPage.openUrl(blogUrl + defaultUrl);
        }else{
            createBlogPage.openUrl(blogUrl + editUrl);
        }
    }

    public void verifyblogTitle(String blogTitle) {
        createBlogPage.verifyblogTitle(blogTitle);
    }

    public void clickCheckbox() {
        createBlogPage.clickCheckbox();
    }

    public void clickBtnAction() {
        createBlogPage.clickBtnAction();

    }

    public void clickDeleteBlogs() {
        createBlogPage.clickDeleteBlogs();
    }

    public void verifyDeleteAllBlogs() {
        createBlogPage.verifyDeletedBlogs();
    }

    public void verifyBlogTitleDB(String blogTitle) {
        createBlogPage.searchBlog(blogTitle);
        createBlogPage.verifyBlogTitle(blogTitle);
    }

    public void verifyComment(String blogTitle, String comment) {
        createBlogPage.verifyComment(blogTitle,comment);
    }


    public void clickLinkSEO() {
        createBlogPage.clickLinkSEO();
    }

    public void clickBtnCancel() {
        createBlogPage.clickBtnCacel();
    }
}
