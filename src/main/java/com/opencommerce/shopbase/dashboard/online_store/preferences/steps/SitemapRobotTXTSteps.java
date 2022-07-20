package com.opencommerce.shopbase.dashboard.online_store.preferences.steps;

import com.opencommerce.shopbase.dashboard.online_store.preferences.pages.SitemapRobotTXTPage;
import net.thucydides.core.annotations.Step;

public class SitemapRobotTXTSteps {
    SitemapRobotTXTPage sitemapRobotTXTPage;

    @Step
    public void verifyRobotStructure(String url) {
        sitemapRobotTXTPage.verifyRobotStructure(url);
    }

    @Step
    public void verifySitemapInRobotTXT(String shop) {
        sitemapRobotTXTPage.verifySitemapInRobotTXT(shop);
    }
}
