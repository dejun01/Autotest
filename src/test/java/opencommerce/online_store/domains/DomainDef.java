package opencommerce.online_store.domains;

import com.opencommerce.shopbase.dashboard.online_store.domains.steps.DomainSteps;
import com.opencommerce.shopbase.storefront.steps.shop.HomePageSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
public class DomainDef {
    @Steps
    DomainSteps domainSteps;
    @Steps
    HomePageSteps homePageSteps;
    String env = LoadObject.getProperty("env");
    String shop = LoadObject.getProperty("shop");

    public static double domainPrice;
    public static String domainURL = "";

    @When("^user connect domain$")
    public void user_connect_domain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String domainProd = SessionData.getDataTbVal(dataTable, row, "Domain Production");
            String domainStag = SessionData.getDataTbVal(dataTable, row, "Domain Staging");
            String msg = SessionData.getDataTbVal(dataTable, row, "Error msg");

            String domain;
            if(env.equals("prod")){
                domain = domainProd;
            }else {
                domain = domainStag;
            }

            domainSteps.clickConnectExistingDomain();
            domainSteps.enterDomain(domain);
            domainSteps.clickBtnNext();
            if (msg.isEmpty()) {
                domainSteps.clickBtnVerifyConnection();
                domainSteps.verifyConnectdomainSuccessfully(domain);
                domainSteps.verifyDomainPagehasDomainsBlock();
            } else {
                domainSteps.verifyMsg(msg);
            }
            domainSteps.clickDomains();
        }

    }

    @Then("^remove all domain from list domain$")
    public void remove_domain_from_list_domain() {
        List<String> domains = domainSteps.getListDomains();
        int countDomain = domainSteps.countDomainInList();

        if (!(domains == null)) {
            for(int i = 0; i < countDomain-1; i++) {
                domainSteps.clickRemoveDomain();
                domainSteps.clickRemoveOnConfirmPopup();
                domainSteps.verifyRemoveDomainSuccessfully();
            }
        }
        domainSteps.verifyDomainPageOnlyHasPrimarydomain();
    }

    @When("user change primary domain to an connected domain is")
    public void user_change_primacy_domain_to_an_connected_domain_is(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String domainProd = SessionData.getDataTbVal(dataTable, row, "Domain Production");
            String domainProductionTest = SessionData.getDataTbVal(dataTable, row, "Domain Production test.Test");
            String domainStag = SessionData.getDataTbVal(dataTable, row, "Domain Staging");

            String domain;
            if(env.equals("prod")){
                domain = domainProd;
            }else {
                domain = domainStag;
            }

            domainSteps.clickChangePrimaryDomain();
            domainSteps.chooseDomain(domain);
            domainSteps.clickSave();
            domainSteps.verifyChangePrimaryDomainSuccessfully(domain);
        }
    }

    @When("^verify to enable redirection domain is \"([^\"]*)\"$")
    public void verifyToEnableRedirectionDomainIs(String isEnableRedirection) {
        String primaryDomain = domainSteps.getCurrentPrimaryDomain();
        List<String> domains = domainSteps.getListDomains();
        domainSteps.enableRedirectDomain(Boolean.parseBoolean(isEnableRedirection));
        // nhâp primary domain ko bị redirect sang domain khác
        homePageSteps.verifyRedirectDomainToPrimaryDomain(primaryDomain, primaryDomain, Boolean.parseBoolean(isEnableRedirection));
        if (!(domains == null))
            for (String domain : domains) {
                homePageSteps.verifyRedirectDomainToPrimaryDomain(domain, primaryDomain, Boolean.parseBoolean(isEnableRedirection));
            }
    }

    @Then("verify domain navigate from dashboard")
    public void verifyDomainNavigateFromDashboard(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String urlInput = (SessionData.getDataTbVal(dataTable, row, "Url Input"));
            String urlOutput = SessionData.getDataTbVal(dataTable, row, "Url Output");
            String primaryDomain = domainSteps.getCurrentPrimaryDomain();
            String urlIn = primaryDomain + urlInput;
            String urlOut = "https://" + primaryDomain + urlOutput;
            domainSteps.verifyDomainNavigationFromDB(urlIn, urlOut);
            homePageSteps.closeBrowser();


        }

    }

    @And("^Buy new domain \"([^\"]*)\"$")
    public void buyNewDomain(String domainBuy) {
        if (domainBuy.matches("@(.*)@")) {
            long currentTime = System.currentTimeMillis();
            domainURL = domainBuy.replaceAll("@", "") + "-" + currentTime + ".info";
        } else {
            domainURL = domainBuy;
        }

        domainSteps.buyNewDomain(domainURL);
        domainPrice = domainSteps.getDomainPrice();
        domainSteps.clickBtnBuyDomain();
    }

    @Then("verify primary domain is")
    public void verifyPrimaryDomain(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String domainProd = SessionData.getDataTbVal(dataTable, row, "Domain Production");
            String domainStag = SessionData.getDataTbVal(dataTable, row, "Domain Staging");
            String domainProductionTest = SessionData.getDataTbVal(dataTable, row, "Domain Production test.Test");

            String domain;
            if(env.equals("prod")){
                domain = domainProd;
            }else {
                domain = domainStag;
            }

            domainSteps.verifyPrimaryDomain(domain);
        }
    }

    @And("verify redirect domain onshopbase with enable redirection domain is {string}")
    public void verifyRedirectDomainOnshopbaseWithEnableRedirectionDomainIs(String isEnableRedirection) {
        String primaryDomain = domainSteps.getCurrentPrimaryDomain();
        domainSteps.verifyRedirectionWithDomainOnshopbase(primaryDomain,shop);
        domainSteps.enableRedirectDomain(Boolean.parseBoolean(isEnableRedirection));
        domainSteps.verifyRedirectionWithDomainOnshopbase(primaryDomain,shop);
    }

    @Then("verify primary domain on storefront")
    public void verifyPrimaryDomainOnSF(){
        domainSteps.verifyPrimaryDomainOnSF();
    }
}
