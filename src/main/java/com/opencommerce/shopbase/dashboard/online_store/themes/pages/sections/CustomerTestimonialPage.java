package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

public class CustomerTestimonialPage extends SBasePageObject {
    public CustomerTestimonialPage(WebDriver driver) {
        super(driver);
    }

    public void inputTextWithLabel(String label, String text) {
        String xpath = "(//div[@class='s-form-item text' and preceding-sibling::div[@class='help-label' and descendant::p[text()='" + label + "']]]//input)[1]";
        waitClearAndType(xpath, text);
    }

    public void selectValue(String label, String value) {
        String xpath = "(//div[@class='s-form-item select'and preceding-sibling::div[@class='help-label' and descendant::p[text()='" + label + "']]]//select)[1]";
        selectDdlByXpath(xpath, value);
    }

    public void clickContent() {
        String xpath = "//div[@class='s-flex s-flex--fill s-flex--align-center']";
        clickOnElement(xpath);
    }

    public void uploadImage(String image) {
        String xpath = "//input[@type='file']";
        chooseAttachmentFile(xpath, image);
    }

    public void checkBackground(String label, boolean isCheck) {
        String xpath = "(//div[@class='s-form-item checkbox' and descendant::span[contains(text(),'" + label + "')]])[1]";
        checkCheckbox(xpath, isCheck);
    }

    public void selectLink(String typeLink, String link) {
        String xpath = "//div[@class='s-taginput-container is-undefined']//span[@class='s-tag']";
        if (isElementExist(xpath)) {
            removeLink();
        }
        chooseTypeLink(typeLink);
        chooseLink(link);

    }

    public void chooseTypeLink(String typeLink) {
        inputSlowly("(//div[@class='s-autocomplete control']//input)[1]",typeLink);
        waitElementToBePresent("//div[@class='s-dropdown-content']//span[contains(text(),'" + typeLink + "')]");
        clickOnElementByJs("//div[@class='s-dropdown-content']//span[contains(text(),'" + typeLink + "')]");
        waitForEverythingComplete();
    }

    public void chooseLink(String link) {
        waitElementToBeVisible("(//div[@class='s-taginput-container is-undefined is-focusable']//input[@type ='text'])[1]");
        inputTextByJS("(//div[@class='s-taginput-container is-undefined is-focusable']//input[@type ='text'])[1]", link);
        waitUntilElementVisible("//div[@class='s-dropdown-item__content' and contains(text(),'"+link+"')]", 10);
        clickOnElement("//div[@class='s-dropdown-item__content' and contains(text(),'"+link+"')]");
    }

    public void removeLink() {
        String xpath = "//div[@class='s-taginput-container is-undefined']//a[@class='s-delete is-small']";
        while (isElementExist(xpath)) {
            clickOnElement("//div[@class='s-taginput-container is-undefined']//a[@class='s-delete is-small']");
        }
    }

    public void inputTestimonial(String testimonial) {
        waitClearAndType("(//textarea[@class='s-textarea__inner'])[1]",testimonial);
    }

    public void inputHeadline(String headline){
        waitClearAndType("//div[@currentlayout='layout_1' and descendant::label[normalize-space()='Section Headline']]//input", headline);
    }

    public void inputTestimonialInside(String testimonial){
        switchToIFrame("//div[contains(@class,'draggable-item')][1]//iframe");
        waitClearAndType("//body[@id='tinymce']", testimonial);
        switchToIFrameDefault();
    }

    public void selectTextAlignment(String textAlignment){
        clickOnElementByJs("//span[@class='text-center' and descendant::p[normalize-space()='"+textAlignment+"']]");
    }

    public void verifyHeadlineTestimonial(String headline){
        verifyElementContainsText("//div[contains(@class,'testimonial-heading')]", headline.toUpperCase());
    }

    public void verifyTestimonial(String testimonial){
        verifyElementText("//div[contains(@class,'testimonial-content')]//p", testimonial);
//        verifyElementContainsText("//div[contains(@class,'testimonial-content')]//p", testimonial);
    }

    public void verifyCustomer(String customer){
        verifyElementText("//div[contains(@class,'testimonial-name')]", customer);
//        verifyElementContainsText("//div[contains(@class,'testimonial-name')]", customer);
    }

    public void verifyNextBackButton(boolean isShow){
        verifyElementPresent("//section[contains(@class,'testimonial-carousel')]//span[contains(@class,'chevron-left')]", isShow);
    }

    public void verifyShowIndicator(boolean isShow){
        verifyElementPresent("//section[contains(@class,'testimonial-carousel')]//div[contains(@class,'pagination')]", isShow);
    }

    public void verifyBackgroundTestimonial(boolean darkenBackground) {
        verifyElementPresent("//div[contains(@class,'testimonial-slide')]//div[contains(@class,'testimonial-wrap--darken')]",darkenBackground);
    }
}
