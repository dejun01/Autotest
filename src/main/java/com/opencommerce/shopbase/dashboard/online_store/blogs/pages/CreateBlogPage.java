package com.opencommerce.shopbase.dashboard.online_store.blogs.pages;
import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CreateBlogPage extends SBasePageObject {
    public CreateBlogPage(WebDriver driver) {
        super(driver);
    }

    public void clickToAddBlogBtn() {
        clickOnElementByJs("//span[contains(.,'Add blog')]");

    }

    public void clickToEditBlog(String _label) {
        clickOnElement("//a[contains(.,'" + _label +"')]");

    }

    public void inputWithTitle(String lable, String blogTitle) {
        clearAndInputTextByJS("//div[child::label[normalize-space()='"+lable+"']]//following-sibling::div[contains(@class,'item__content')]//input", blogTitle);
    }

    public void inputMetaDescription(String metaDescription) {
        clearAndInputTextByJS("//div[@class='s-form-item']//textarea", metaDescription);
    }

    public void InputURL(String editURL) {
        clearTextByJS("//div[@class='s-input s-input-group s-input-group--prepend']//input");
        clearAndInputTextByJS("//div[@class='s-input s-input-group s-input-group--prepend']//input", editURL);
    }

    public void checkRadioBtnComment(String lable) {
        clickOnElement("//span[@class='s-check']/following-sibling::span[contains(.,'"+lable+"')]");
    }

    public void clickToSaveBtn() {
        clickOnElement("//div[@class='row save-setting-content']//span[contains(.,'Save')]");

    }

    public void verifyMsgErr() {
        verifyElementVisible("//span[text()='there is 1 error']",true);
    }


    public void verifyblogTitle(String blogTitle) {
        verifyElementText("//h1[contains(@class, 'mb32 h3 text-align-center-xs')]",blogTitle.toUpperCase());

    }

    public void clickCheckbox() {
        checkCheckbox("(//table[contains(@class,'table-condensed')]//label[@class='s-checkbox'])[1]",true);
    }

    public void clickBtnAction() {
        clickOnElement("//table[contains(@class,'table-condensed')]//button");
    }

    public void clickDeleteBlogs() {
        clickOnElementByJs("//table[contains(@class,'table-condensed')]//span[normalize-space()='Delete blogs']");
        clickOnElement("//div[contains(@class,'s-modal-content')]//button[normalize-space()='Delete']");
    }

    public void verifyDeletedBlogs() {
        verifyElementPresent("//div[@class='s-flex-item s-pb2']//*[contains(text(),'News')]", true);
    }

    public void verifyBlogTitle(String blogTitle) {
        String xPath = "(//div[@class='s-flex s-flex--spacing-tight'])[1]";
        String blogName = XH(xPath).getText().trim();
        Assertions.assertThat(blogName).isEqualTo(blogTitle);
    }

    public void verifyComment(String blogTitle, String comment) {
        String xPath = "(//td[descendant::a[normalize-space()='"+blogTitle+"']]//following-sibling::td[contains(@class,'page-date')]//span)[1]";
        verifyElementText(xPath,comment);

    }

    public void clickLinkSEO() {
        clickOnElement("//div[@class='s-flex-item']/button");
    }

    public void clickBtnCacel() {
        clickOnElement("//span[normalize-space()='Cancel']");
        if(isElementExist("//button[@class='s-button is-danger']//span[normalize-space()='Leave page']")){
            clickOnElement("//button[@class='s-button is-danger']//span[normalize-space()='Leave page']");
        }
        }

    public void searchBlog(String _value) {
        waitClearAndType("//input[@placeholder='Search blogs']", _value);
        waitABit(3000);
    }
}



