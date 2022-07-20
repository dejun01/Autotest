package com.opencommerce.shopbase.dashboard.apps.analytics.steps;

import com.opencommerce.shopbase.dashboard.apps.analytics.pages.CustomerSessionHistoryPage;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerSessionHistorySteps extends ScenarioSteps {
    CustomerSessionHistoryPage customerSessionPage;

    public void verifySourceIdentifier(String sourceidentifier) {
        customerSessionPage.verifySource("Source identifier", sourceidentifier);
    }

    public void verifyReferingsite(String referringsite) {
        customerSessionPage.verifySource("Referring site", referringsite);
    }

    public void verifyFirstPageVisited(String firstpagevisited) {
        customerSessionPage.verifyFirstPageVisited(firstpagevisited);
    }

    public void clickButtonViewAllSessions() {
        customerSessionPage.clickButtonViewAllSessions();
    }

    public int getTotalSessionsOfCustomer() {
        return customerSessionPage.getTotalSessionsOfCustomer();
    }

    public void verifyTotalSessionsChange(int totalSessionInit, int totalSessionsInc, int totalSessionsActual) {
        assertThat(totalSessionInit + totalSessionsInc).isEqualTo(totalSessionsActual);
    }

    public void verifyLastSessionDescription(int totalSessionsActual, String activity) {
        customerSessionPage.verifyLastSessionDescription(totalSessionsActual, activity);
    }

    public void verifySessionCovertToOrder(int totalSession, boolean b) {
        customerSessionPage.verifySessionCovertToOrder(totalSession, b);
    }

    public void verifyUTM(String source, String utmValue) {
        customerSessionPage.verifyUTM(source, utmValue);
    }

    public void clickLinkViewAllSessions() {
        customerSessionPage.clickLinkTextWithLabel("View all sessions");
    }

    public void clickButtonViewFullSession() {
        customerSessionPage.clickBtn("View full session");
    }
}


