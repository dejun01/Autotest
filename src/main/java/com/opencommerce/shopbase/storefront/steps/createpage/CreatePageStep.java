package com.opencommerce.shopbase.storefront.steps.createpage;

import com.opencommerce.shopbase.storefront.pages.createpage.CreatePagePage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreatePageStep extends ScenarioSteps {
    CreatePagePage createPagesPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void clickToAddPageBtn() {
        createPagesPage.clickToAddPageBtn();
    }

    @Step
    public void enterInputTitle(String titleVal) {
        createPagesPage.enterInputTitle("text", titleVal);
    }

    //insert link cho page
    @Step
    public void insertLink(String value, String noteVal) {
        createPagesPage.clickIconInsert();
        createPagesPage.enterInputLinkTextbox("URL", value);
        createPagesPage.enterInputLinkTextbox("Title", noteVal);
        createPagesPage.clickToInsertBtn();

    }

    //insert video vào page
    @Step
    public void insertVideoLink(String value) {
        createPagesPage.clickToVideoBtn();
        createPagesPage.enterInputVideoLink("Source", value);
    }

    @Step
    public void selectPageTemplate(String value) {
        createPagesPage.selectPageTemplate(value);
    }

    @Step
    public void checkToVisibilityRadioBtn(String label) {
        createPagesPage.checkToVisibilityRadioBtn(label);
    }

    @Step
    public void enterInputEditUrl(String value) {
        boolean seoBtn = createPagesPage.seoBtnIsDisplayed();
        if (seoBtn) {
            createPagesPage.clickToSeoBtn();
        }
        createPagesPage.enterInputEditUrl(value);
    }

    @Step
    public void clickToSaveBtn() {
        createPagesPage.clickToSaveBtn();
    }

    @Step
    public void switchBackToPages() {
        createPagesPage.switchToTab("Pages");
    }

    @Step
    public void verifyNewPageCreatedOnDashboard(String title) {
        createPagesPage.seacrhPage(title);
        createPagesPage.verifyNewPageCreatedOnDashboard(title);
    }

    @Step
    public void verifyPageStatus(String label) {
        createPagesPage.verifyPageStatus(label, true);
    }

    @Step
    public void goToNewPageURL(String title, String editUrl) {
        String pageUrl = ("https://" + shop + "/pages/");
        String defaultUrl = (title.toLowerCase().replace(" ", "-"));
        if (editUrl.isEmpty()) {
            createPagesPage.openUrl(pageUrl + defaultUrl);
        } else {
            createPagesPage.openUrl(pageUrl + editUrl);
        }
    }

    @Step
    public void verifyPageNotFound() {
        createPagesPage.verifyPageNotFound();
    }

    @Step
    public void verifyNewPageTitle(String label) {
        createPagesPage.verifyNewPageTitle(label, true);
    }

    @Step
    public void verifyInsertedLink(String label) {
        createPagesPage.verifyInsertedLink(label, true);
    }

    @Step
    public void verifyVideoLink(String label) {
        createPagesPage.videoLinkInserted(label, true);
    }

    @Step
    public void verifyPageTemplate(String template) {
        switch (template) {
            case "Review page":
                createPagesPage.verifyReviewFormVisible(true);
                break;
            case "Contact page":
                createPagesPage.verifyContactFormVisible(true);
                break;
            default:
        }
    }

    @Step
    public void clickToNewPage(String label) {
        createPagesPage.clickToEditedPage(label);
    }

    @Step
    public void selectAllPages() {
        createPagesPage.checkToAllPagesCheckbox();
    }

    @Step
    public void selectPageCreatedOnDashboard(String label) {
        createPagesPage.checkToNewPageCheckbox(label);
    }

    @Step
    public void actionWithNewPage(String label) {
        createPagesPage.clickToActionBtn();
        createPagesPage.clickToActionItem(label);
        if (label.equals("Delete")) {
            createPagesPage.clickToDeleteBtn(label);
        }
    }

    @Step
    public void verifyDeletedPage(String title) {
        createPagesPage.seacrhPage(title);
        createPagesPage.verifyNoPagesFound("No Pages found");
        createPagesPage.seacrhPage("");
        createPagesPage.verifyDeletedPage(title, false);
    }

    @Step
    public void verifyAllPagesDeleted() {
        createPagesPage.verifyAllPagesDeleted(false);
    }

    @Step
    public void verifyErrorMessage() {
        createPagesPage.verifyMsgErr();
    }
}
