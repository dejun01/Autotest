package opencommerce.online_store.preferences;

import com.opencommerce.shopbase.dashboard.online_store.preferences.steps.SitemapRobotTXTSteps;
import common.utilities.LoadObject;
import common.utilities.SessionData;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class SitemapRobotTXTDef {
    @Steps
    SitemapRobotTXTSteps sitemapRobotTXTSteps;

    @Then("verify status and sitemap in Robots.txt file structure")
    public void verifyStatusAndSitemap(List<List<String>> dataTable) {
        String shopDomain = getShopDomain("shop");
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String url = SessionData.getDataTbVal(dataTable, row, "URL");
            if(status.equalsIgnoreCase("Disallow")){
                sitemapRobotTXTSteps.verifyRobotStructure(url);
            }
            sitemapRobotTXTSteps.verifySitemapInRobotTXT(shopDomain);
        }
    }
    private String getShopDomain(String shopType) {
        String shopDomain = LoadObject.getProperty("shop");
        if (!shopType.equals("shop")) {
            shopDomain = LoadObject.getProperty(shopType);
        }
        return shopDomain;
    }
}

