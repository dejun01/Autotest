package com.opencommerce.shopbase.dashboard.online_store.themes.steps.sections;

import com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections.CustomerTestimonialPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CustomerTestimonialSteps extends ScenarioSteps {
    CustomerTestimonialPage customerTestimonialPage;


    @Step
    public void inputTitle(String title) {
        customerTestimonialPage.inputTextWithLabel("Testimonial title", title);
    }

    @Step
    public void inputText(String text) {
        customerTestimonialPage.inputTextWithLabel("Alt text", text);
    }

    @Step
    public void inputCustomer(String customer) {
        customerTestimonialPage.inputTextWithLabel("Customer name", customer);
    }

    @Step
    public void inputStore(String store) {
        customerTestimonialPage.inputTextWithLabel("Store name", store);
    }

    @Step
    public void selectAnimation(String animation) {
        customerTestimonialPage.selectValue("Text animation", animation);
    }

    @Step
    public void selectAlignment(String alignment) {
        customerTestimonialPage.selectValue("Text alignment", alignment);
    }

    @Step
    public void clickContent() {
        customerTestimonialPage.clickContent();
    }

    @Step
    public void uploadImage(String image) {
        customerTestimonialPage.uploadImage(image);
    }

    @Step
    public void checkUseImage(boolean useImage) {
        customerTestimonialPage.checkBackground("Use image", useImage);
    }

    @Step
    public void checkDarkenBackground(boolean darkenBackground) {
        customerTestimonialPage.checkBackground("Darken background image", darkenBackground);
    }

    @Step
    public void inputTestimonial(String testimonial) {
        customerTestimonialPage.inputTestimonial(testimonial);
    }

   @Step
   public void selectLink(String typeLink, String link) {
       customerTestimonialPage.selectLink(typeLink,link);
   }

    public void clickBtnSave() {
        customerTestimonialPage.clickBtn("Save");
    }

    @Step
    public void checkCheckBoxWithLabel(String label, boolean isCheck){
        customerTestimonialPage.checkCheckboxWithLabel(label, isCheck);
    }

    @Step
    public void inputHeadline(String headline){
        customerTestimonialPage.inputHeadline(headline);
    }

    @Step
    public void inputTestimonialInside(String testimonial){
        customerTestimonialPage.inputTestimonialInside(testimonial);
    }

    @Step
    public void selectTextAlignment(String textAlignment){
        customerTestimonialPage.selectTextAlignment(textAlignment);
    }

    @Step
    public void verifyHeadlineTestimonial(String headline){
        customerTestimonialPage.verifyHeadlineTestimonial(headline);
    }

    @Step
    public void verifyTestimonial(String testimonial){
        customerTestimonialPage.verifyTestimonial(testimonial);
    }

    @Step
    public void verifyCustomer(String customer){
        customerTestimonialPage.verifyCustomer(customer);
    }

    @Step
    public void verifyNextBackButton(boolean isShow){
        customerTestimonialPage.verifyNextBackButton(isShow);
    }

    @Step
    public void verifyShowIndicator(boolean isShow){
        customerTestimonialPage.verifyShowIndicator(isShow);
    }

    public void verifyBackgroundTestimonial(boolean darkenBackground) {
        customerTestimonialPage.verifyBackgroundTestimonial( darkenBackground);
    }
}
