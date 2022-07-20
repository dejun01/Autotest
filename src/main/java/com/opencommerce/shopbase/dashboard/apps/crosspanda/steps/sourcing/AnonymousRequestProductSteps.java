package com.opencommerce.shopbase.dashboard.apps.crosspanda.steps.sourcing;

import com.opencommerce.shopbase.dashboard.apps.crosspanda.pages.sourcing.AnonymousRequestProductPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AnonymousRequestProductSteps extends ScenarioSteps {
    AnonymousRequestProductPage anonymousRequestProductPage;

    @Step
    public void openAnonymousRequestProductPage() {
        anonymousRequestProductPage.open();
        anonymousRequestProductPage.waitForPageLoad();
        anonymousRequestProductPage.waitForEverythingComplete();
        anonymousRequestProductPage.maximizeWindow();
    }

    @Step
    public void verifyDataSupplier(String supplier) {
        anonymousRequestProductPage.clickLinkTextWithLabel("Choose your supplier");
        List<String> expSupplier = Arrays.asList(supplier.split(","));
        List<String> actualSupplier = anonymousRequestProductPage.getListValueDddlist();
        assertThat(actualSupplier).containsAll(expSupplier);
    }

    @Step
    public void verifyDataRevenue(String revenue) {
        anonymousRequestProductPage.clickLinkTextWithLabel("Choose your monthly revenue");
        List<String> expRevenue = Arrays.asList(revenue.split(";"));
        List<String> actualRevenue = anonymousRequestProductPage.getListValueDddlist();
        assertThat(actualRevenue).containsAll(expRevenue);
    }

    public void verifyLinkRequest(String link) {
        List<String> expLinks = Arrays.asList(link.split(","));
        List<String> actualLink = anonymousRequestProductPage.getListLinkRequest();
        assertThat(actualLink).containsAll(expLinks);

    }


    public void verifyContact(String contacts) {
        List<String> expContacts = Arrays.asList(contacts.split(","));
        List<String> actualContacts = anonymousRequestProductPage.getListContactType();
        assertThat(actualContacts).containsAll(expContacts);
    }

    public void selectSupplier(String supplier) {
        anonymousRequestProductPage.clickLinkTextWithLabel("Choose your supplier");
        anonymousRequestProductPage.clickValueSupplier(supplier);
    }

    public void selectRevenue(String revenue) {
        anonymousRequestProductPage.clickLinkTextWithLabel("Choose your monthly revenue");
        anonymousRequestProductPage.clickValueSupplier(revenue);
    }

    public void inputNote(String note) {
        anonymousRequestProductPage.enterNote(note);
    }

    public void inputFacebook(String facebook) {
        anonymousRequestProductPage.enterInputFieldWithLabel("Facebook link", facebook);
    }

    public void inputSkype(String skype) {
        anonymousRequestProductPage.enterInputFieldWithLabel("Skype account", skype);
    }

    public void inputPhone(String phone) {
        anonymousRequestProductPage.enterInputFieldWithLabel("Phone number", phone);
    }

    public void inputOther(String other) {
        anonymousRequestProductPage.enterInputFieldWithLabel("Other", other);
    }

    public void verifyMsg(String msg) {
        String[] msgs = msg.split(",");
        for (String _msg : msgs) {
            anonymousRequestProductPage.verifyTextPresent(_msg, true);
        }
    }
}
