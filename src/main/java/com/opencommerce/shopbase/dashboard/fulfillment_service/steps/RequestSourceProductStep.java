package com.opencommerce.shopbase.dashboard.fulfillment_service.steps;

import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.RequestListPage;
import com.opencommerce.shopbase.dashboard.fulfillment_service.pages.RequestSourceProductPage;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RequestSourceProductStep extends ScenarioSteps {
    RequestSourceProductPage requestSourceProductPage;

    public String getTheFirstQuotation() {
        String xpath = "//tr[1]//td[1]//span[@class='name']";
        String quotationID = requestSourceProductPage.getText(xpath);
        return quotationID;
    }

    public void goToPage(String namePage) {
        requestSourceProductPage.clickBtn(namePage);
    }

    public void enterProductURLs(String link) {
        String[] urls = link.split(",");
        int count = urls.length;
        if (count > 2) {
            for (int i = 2; i < count; i++) {
                requestSourceProductPage.addMoreLinks();
            }
        }
        for (int y = 0; y < count; y++) {
            requestSourceProductPage.inputProductLink(urls[y], y);
        }
    }

    public void submitRequest() {
        requestSourceProductPage.clickBtn("Submit request");
    }

    public void verifyMsgRequestQuotation(String msg) {
        String actMsg = requestSourceProductPage.getMsgRequestQuotation();
        assertThat(actMsg).isEqualTo(msg);
    }

    public void verifyDisableSubmitRequest() {
        requestSourceProductPage.verifyDisableSubmitRequest();
    }


    public void inputEmailToCustomerInfor(String email) {
        requestSourceProductPage.enterInputFieldWithLabel("Email address", email);
    }

    public void inputSkypeToCustomerInfor(String skype) {
        requestSourceProductPage.enterInputFieldWithLabel("Skype ID", skype);
    }

    public void inputPhoneToCustomerInfor(String phone) {
        requestSourceProductPage.enterInputFieldWithLabel("Your phone number", phone);
    }

    public void inputFBToCustomerInfor(String fb) {
        requestSourceProductPage.enterInputFieldWithLabel("Facebook URL", fb);
    }

    public void verifyMsgInvalidCustomerInfor(String msg) {
        String actMsg = requestSourceProductPage.getMsgRequestQuotation();
        assertThat(actMsg).isEqualTo(msg);
    }

    public void verifyDisableSaveCustomerInfor() {
        requestSourceProductPage.verifyDisableSaveCustomerInfor();
    }

    public void saveCustomerInfor() {
        requestSourceProductPage.clickBtn("Save");
    }

    public void verifyDataCustomerInfor(String email, String skype, String phone, String fb) {
        requestSourceProductPage.verifyDataCustomerInfor(email, skype, phone, fb);
    }
}
