package com.opencommerce.shopbase.dashboard.apps.review.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AllreviewsPage extends SBasePageObject {
    public AllreviewsPage(WebDriver driver) {
        super(driver);
    }

    public void selectAllReviews() {
        String xpathCheckbox = "(//label[@class='s-checkbox']//span)[1]";
        clickOnElement(xpathCheckbox);
        waitForEverythingComplete();
        String xpathSelectAllReview = "//span[@class='s-flex s-flex--align-center text-blue400' and contains(text(),'across all pages')]";
        boolean selectAllIsShow = isElementExist(xpathSelectAllReview);
        if (selectAllIsShow) {
            clickOnElement(xpathSelectAllReview);
        }
    }

    public void selectDelete() {
        clickOnElementByJs("//div[@class='s-dropdown-content']//span[@class='s-dropdown-item' and contains(text(),'Delete')]");
    }

    public void verifyDeletedAllReviews() {
        String xPathText = "//div[@class='s-heading s-mt16' and contains(text(),'You have no reviews yet')]";
        int i = 1;
        while (!isElementExist(xPathText) && i <= 5) {
            waitABit(5000);
            refreshPage();
            i++;
        }
        verifyElementPresent("//div[@class='s-flex s-flex--align-center']", false);
    }

    public void chooseProduct(String product) {
        boolean productIsSelected = isElementExist("//a[@class= 's-delete is-small']");
        if (productIsSelected) {
            clickOnElement("//a[@class= 's-delete is-small']");
        }
        waitClearAndType("//input[@placeholder = 'Search product to import reviews' or @placeholder ='Search product']", product);
        waitElementToBeVisible("//div[contains(text(),'" + product + "')]");
        clickOnElement("//div[contains(text(),'" + product + "')]");
    }

    public void enterURL(String urls) {
        String[] url = urls.split(";");
        if (url.length > 0) {
            for (int i = 0; i < url.length; i++) {
                if (i > 0) {
                    clickAddMoreLink();
                }
                int j = i + 1;
                waitClearAndTypeThenEnter("(//input[@placeholder = 'Enter URL of product in AliExpress'])[" + j + "]", url[i]);
            }
        }
    }

    public void clickBtnImport(int index) {
        String xpathBtnImportReview = "(//button[@class='s-button is-outline'])[" + index + "]";
        waitElementToBeVisible(xpathBtnImportReview);
        clickOnElement(xpathBtnImportReview);
    }

    public void clickAddMoreLink() {
        clickOnElementByJs("//*[contains(text(),\"Add more\")]");
    }

    public void chooseStar(String star) {
        selectDdlByXpath("//div[@class='s-select s-w100']//select", star);
    }

    public void inputMaxNumber(String maxNumber) {
        waitClearAndType("//div[@class='col-md-6' and descendant::label[normalize-space()='Maximum number of reviews per link']]//input", maxNumber);
    }

    public void chooseCountry(String country) {
        checkCheckboxWithLabel("" + country + "");
    }

    public void checkKeepOriginalDate() {
        checkCheckboxWithLabel("Keep original date");
    }

    public void clickBtnGetReviews() {
        clickBtn("Get reviews");
    }

    public void clickBtnCheckNow() {
        clickBtn("Check now");
    }

    public void checkAutoPublish(Boolean autoPublish) {
        String label = "Auto-publish reviews";
        checkToggleOnSetting(label, autoPublish);
    }

    public void checkToggleOnSetting(String label, boolean isCheck) {
        String xpathStatus = "//div[@class='s-flex s-flex--align-center s-flex--justify-space-between' and descendant::div[contains(text(),'" + label + "')]]//label//input";
        String checkBoxElement = "//div[@class='s-flex s-flex--align-center s-flex--justify-space-between' and descendant::div[contains(text(),'" + label + "')]]//label";
        verifyCheckedThenClick(xpathStatus, checkBoxElement, isCheck);
    }

    public void selectMinimumStar(String minimumStar) {
        selectDdlWithLabel("Auto publish reviews on website with minimum star rating:", minimumStar);
    }

    public void inputKeywordBlock(String keywordBlock) {
        waitClearAndType("//div[@class='s-textarea']/textarea", keywordBlock);
    }

    public void clickSettingsOnMenu() {
        clickOnElement("//a[@href='/admin/apps/review/setting']");
    }

    public void verifyContent(String title, String content) {
        String xpath = "//tr[descendant::div[@class='s-paragraph type--bold' and contains(text(),'" + title + "')]]//div[@class='s-extend-paragraph']";
        verifyElementText(xpath,content);

    }

    public void verifyStatus(String title, String status) {
        String xpathStatus = "//tr[descendant::div[@class='s-paragraph type--bold' and contains(text(),'" + title + "')]]//div[@class='s-w100']//span[contains(text(),'lished')]";
        String statusValue = getTextValue(xpathStatus).trim();
        assertThat(statusValue).isEqualTo(status);
    }

    public void verifyYourName(String title, String yourName) {
        String xpathYourName = "//tr[descendant::div[@class='s-paragraph type--bold' and contains(text(),'" + title + "')]]//div[@class='s-paragraph']";
        String yourNameValue = getTextValue(xpathYourName).trim();
        assertThat(yourNameValue).isEqualTo(yourName);
    }

    public void inputKeySearchAndEnter(String review) {
        waitTypeAndEnter("//div[@class='s-input s-input--prefix']/input", review);
    }

    public void clickChooseReview(String review) {
        clickOnElementByJs("//div[@class='s-flex s-flex--align-center' and descendant::div[text()='" + review + "']]");
    }

    public void editRating(String rating) {
        String xpathRating = "//div[@class='s-form-item__content' and preceding-sibling::div[@class='s-form-item__wrap-label' and descendant::label[contains(text(),'Rating')]]]//span[" + rating + "]//*";
        clickOnElementByJs(xpathRating);
    }

    public void editReviewType(String reviewType) {
        String xpathReviewType = "//div[@class='s-form-item__content' and preceding-sibling::div[@class='s-form-item__wrap-label' and descendant::label[contains(text(),'type')]]]//select";
        waitElementToBeVisible(xpathReviewType);
        selectDdlByXpath(xpathReviewType, reviewType);
    }

    public void editProduct(String product) {
        if(isElementExist("//div[contains(@class,'taginput-containe')]//a[contains(@class,'s-delete')]")){
            clickOnElement("//div[contains(@class,'taginput-containe')]//a[contains(@class,'s-delete')]");
        }
        inputSlowly("//input[@placeholder='Type a keyword to search']", product);
        clickOnElementByJs("//div[@class='s-dropdown-item__content' and contains(text(),'" + product + "')]");
    }

    public void verifyAliExpressFilterPopupDislay() {
        verifyElementText("//div[@class='s-modal is-active s-modal--scroll-body']//h4", "Import reviews from AliExpress");
    }

    public void verifyErrorMessage(String msg) {
        String[] mes = msg.split(";");
        for (int i = 0; i < mes.length; i++) {
            int j = i + 1;
            verifyElementText("(//div[@class='s-form-item__error'])[" + j + "]", mes[i]);
        }
    }

    public void chooseFileCSV(String fileName) {
        String xpathInput = "//div[@class='s-modal-body']//input[@type='file']";
        chooseAttachmentFile(xpathInput, fileName);
    }

    public void chooseOverwrite() {
        String xpathStatus = "//label[@class='s-checkbox s-mt8']//input";
        String xpathCheckbox = "//label[@class='s-checkbox s-mt8']//span[@class='s-check']";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, true);
    }

    public void clickBtnFilter() {
        clickOnElement("//button[@class='s-button s-ml16 is-outline']");
    }

    public void chooseOption(String option) {
        clickOnElement("//div[@class='sidebar-filter__item s-w100' and descendant::p[contains(text(),'" + option + "')]]//p");
    }

    public void chooseValue(String value) {
        String xpathCheckbox = "//div[@class='s-w100' and descendant::span[contains(text(),'" + value + "')]]//label";
        String xpathStatus = "//div[@class='s-w100' and descendant::span[contains(text(),'" + value + "')]]//input";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, true);

    }

    public void selectValue(String option, String value) {
        selectDdlWithLabel(option, value);
    }

    public void verifyReviewShow(String reviewShow, boolean isShow) {
        verifyElementPresent("//div[@class='s-paragraph type--bold' and text()='" + reviewShow + "']", isShow);
    }

    public void selectAction(String action) {
        clickOnElementByJs("//div[@class='s-dropdown-content']//span[@class='s-dropdown-item' and contains(text(),'" + action + "')]");
    }

    public void clickBtnAction() {
        clickOnElementByJs("//button[@class='s-button is-outline' and descendant::span[contains(text(),'Action')]]");
    }

    public void checkChooseReview(String review) {
        clickOnElementByJs("//div[@class='s-flex s-flex--align-center' and descendant::div[contains(text(),'" + review + "')]]//input");
    }

    public void selectFirstProductGroup(){
        waitElementToBeVisible("//div[@class='s-flex s-flex--align-center']");
        clickOnElementByJs("//div[@class='s-flex s-flex--align-center']//a");
    }

    public void deleteSelectedProductGroup() {
        waitElementToBeVisible("//button[@class='s-button is-danger']");
        clickOnElementByJs("//button[@class='s-button is-danger']");
        clickOnElementByJs("(//button[@class='s-button is-danger'])[2]");
    }

    public int countProductGroup(){
        waitForEverythingComplete();
        return countElementByXpath("//div[@class='s-flex s-flex--align-center']");
    }

    public void verifyGroupsNumberEquals0(int groupNumber) {
        int groupNumberValue = countProductGroup();
        assertThat(groupNumberValue).isEqualTo(groupNumber);
    }

    public void selectGroupType(String groupType) {
        selectDdlByXpath("//div[@class='s-select s-w100']//select", groupType);
    }

    public void inputValueGroup(String value) {
        waitClearAndType("//input[@placeholder='Type a keyword to search collections' or @placeholder='Type a keyword to search tags' or @placeholder='Type a keyword to search vendors']", value);
        waitElementToBeVisible("//*[contains(text(),'" + value + "')]");
        clickOnElementByJs("//*[contains(text(),'" + value + "')]");
    }

    public void checkActivate(boolean groupStatus) {
        checkCheckboxOnReviewApp(groupStatus);
    }

    public void checkCheckboxOnReviewApp(boolean isCheck) {
        String xpathStatus = "//label[@class='s-checkbox']//input";
        String xpathCheckbox = "//label[@class='s-checkbox']";
        verifyCheckedThenClick(xpathStatus, xpathCheckbox, isCheck);
    }

    public void clickBtnAddProductGroup() {
        clickOnElementByJs("//button[@class='s-button is-primary' and descendant::span[contains(text(),'Add product group')]]");
    }

    public void clickBtnSave() {
        String xpath = "//button[@class='s-button is-primary' and descendant::span[contains(text(),'Save')]]";
        waitForElementToPresent(xpath);
        clickOnElement(xpath);
        waitElementToBePresent("//div[contains(text(),'successfully')]");
    }

    public void chooseGroup(String nameGroup) {
        clickOnElement("//div[@class='s-flex s-flex--align-center']//a[contains(text(),'" + nameGroup + "')]");
    }

    public void editGroupBy(String groupBy) {
        selectDdlByXpath("//div[@class='s-select s-w100']//select", groupBy);
    }

    public void confirmDelete() {
        clickOnElementByJs("//button[@class='s-button btn-confirm-delete is-primary']");
    }

    public void inputTextField(String label, String value) {
        String xPath = "//div[contains(@class,'s-form-item') and descendant::label[contains(text(),'" + label + "')]]//*[@class='s-input__inner' or @class='s-textarea__inner']";
        waitForElementToPresent(xPath);
        waitTypeOnElement(xPath, value);
    }

    public void editTextField(String label, String value) {
        String xpath = "//div[contains(@class,'s-form-item') and descendant::label[contains(text(),'" + label + "')]]//*[@class='s-input__inner' or @class='s-textarea__inner']";
        waitClearAndType(xpath, value);
    }

    public List<String> getReviewInformation(int i) {
        String nameOfUser, title, body, date;
        int ratingNumber;
        nameOfUser = getText("(//div[@class='s-paragraph'])[" + i + "]").trim();
        title = getText("(//div[@class='s-paragraph type--bold'])[" + i + "]").trim();
        body = getText("(//div[@class='s-extend-paragraph'])[" + i + "]").trim();
        date = getText("(//span[@class='s-caption'])[" + i + "]").trim();
        ratingNumber = countElementByXpath("//tr[" + i + "]//span[@class='s-icon is-xsmall page-reviews__star text-gray100 text-yellow400']");
        String rating = String.valueOf(ratingNumber);
        String[] arr = {nameOfUser, title, rating, body, date};
        return Arrays.asList(arr);
    }

    public void checkChooseGroup(String groupName) {
        clickOnElement("//div[@class='s-flex s-flex--align-center' and descendant::a[contains(text(),'" + groupName + "')]]//label");
    }

    public int countReviews() {
        return countElementByXpath("//table[contains(@class,'page-reviews__table')]//tbody//tr[child::td[not(@class='no-product')]]");
    }

    public void verifyMsgShow(String msg) {
//        waitForAllTextToAppear(msg);
        waitForTextToAppear(msg, 5000);
    }

    public void checkNotification(boolean notification) {
        String label = "Notifications";
        checkToggleOnSetting(label, notification);
    }

    public void inputEmail(String email) {
        waitClearAndType("//div[@class='col-xs-8']//input[@type ='text']", email);
    }

    public void checkSendNotificationBadReview(boolean sendNotificationBadReview) {
        checkCheckboxOnReviewApp(sendNotificationBadReview);
    }

    public void chooseAllReviewInPage() {
        String xpathCheckbox = "(//label[@class='s-checkbox']//span)[1]";
        clickOnElement(xpathCheckbox);
    }

    public void verifyRating(String title, int rating) {
        String xpath = "//tr[descendant::div[@class='s-paragraph type--bold' and text()='" + title + "']]//span[@class='s-icon is-xsmall page-reviews__star text-gray100 text-yellow400']";
        int ratingActual = countElementByXpath(xpath);
        assertThat(ratingActual).isEqualTo(rating);

    }

    public boolean showBtnCheckNow() {
        return isElementExist("//button[normalize-space()='Check now' or descendant::*[normalize-space()='Check reviews']]");
    }
}
