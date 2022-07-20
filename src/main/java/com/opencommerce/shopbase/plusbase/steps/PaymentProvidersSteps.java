package com.opencommerce.shopbase.plusbase.steps;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import com.opencommerce.shopbase.plusbase.pages.PaymentProvidersPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.*;

public class PaymentProvidersSteps {

    PaymentProvidersPage paymentProvidersPage;

    @Step
    public String getNameAfterEdit(String name) {
        switch (name) {
            case "Statement descriptor":
                return paymentProvidersPage.getInfoAfterEdit("Enter your descriptor");
            case "Phone number":
                return paymentProvidersPage.getInfoAfterEdit("Enter your phone");
            case "Brand name":
                return paymentProvidersPage.getInfoAfterEdit("Enter your brand name");
            default:
                return "";
        }
    }

    @Step
    public String randomName() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    @Step
    public void editName(String notes, String name) {
        paymentProvidersPage.editInfo(notes, name);
    }

    @Step
    public String randomPhone() {
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber().substring(0, 11);
    }

    @Step
    public void clickBTSaveChanges() {
        paymentProvidersPage.clickBTSave();
    }

    public void verifyDisplayInforTemplateStore(String information) {
        String[] info = information.split(",");
        assertThat(paymentProvidersPage.getListInfo().get(0)).isEqualTo(info[0]);
        assertThat(paymentProvidersPage.getListInfo().get(1)).isEqualTo(info[1]);
        assertThat(paymentProvidersPage.getListInfo().get(2)).isEqualTo(info[2]);

    }

    @Step
    public void verifyShippingPrice(String sBaseRules, String sPrice) {
        assertThat(paymentProvidersPage.getShippingFee(sBaseRules)).isEqualTo(sPrice);
    }

    public void verifyRiskSMP(String risk) {
        assertThat(paymentProvidersPage.getRiskSMP()).isEqualTo(risk);
    }
}
