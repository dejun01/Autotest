package com.opencommerce.shopbase.plusbase.steps;
import com.opencommerce.shopbase.plusbase.pages.BuyDomainPage;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyDomainSteps {
    BuyDomainPage buyDomainPage;

    @Step
    public void clickBtn(String buttonName) {
        buyDomainPage.clickBtnAction(buttonName);
    }

    @Step
    public void clickBtnConnectExistingDomain(String domain) {
        buyDomainPage.clickLinkTextWithLabel("Connect Existing Domain");
        buyDomainPage.inputDomain(domain);
    }

    @Step
    public void verifyAlert(String domain) {
//        assertThat(buyDomainPage.getAlerts()).isEqualTo("Domain" + " " + domain + " " + "cannot be connect to this store. Please read our domain policy and try other domain to connect.");
        assertThat(buyDomainPage.getAlerts()).isEqualTo("External domains purchased from other sources cannot be connected. Please read our domain policy and try again.");
    }

    @Step
    public void verifyMessage(String message) {
        String act = buyDomainPage.getMessage();
        assertThat(act).isEqualTo(message);
    }

    @Step
    public void verifyInfodomainWithTitle(String label, String val, String title) {
        buyDomainPage.verifyInfodomainWithTitle(label, val, title);
    }

    @Step
    public void removeDomain(String title, String domain, String btnName) {
        buyDomainPage.removeDomain(title, domain, btnName);
    }

    @Step
    public void verifydomaindashboard() {
        buyDomainPage.verifydomaindashboard();
    }

    public String getDomainName() {
        return buyDomainPage.getDomainName();
    }

    public void clickConnectExistingDomain() {
        buyDomainPage.clickConnectExistingDomain("Connect Existing Domain");

    }
}
