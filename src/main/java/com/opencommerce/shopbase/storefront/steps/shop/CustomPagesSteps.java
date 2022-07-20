package com.opencommerce.shopbase.storefront.steps.shop;

import com.opencommerce.shopbase.storefront.pages.shop.CustomPagesPage;
import common.utilities.LoadObject;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomPagesSteps {
    CustomPagesPage customPagesPage;
    String shop = LoadObject.getProperty("shop");

    @Step
    public void checkAppyRedirectPageOnSF(String currentURL, String newURL, String page, boolean isCreate) {
        customPagesPage.openUrlInNewTab("");
        customPagesPage.switchToWindowWithIndex(1);
        String url = ("https://" + shop + "/pages/" + currentURL);
        customPagesPage.navigateToUrl(url);
        String exactURL = customPagesPage.getCurrentUrl();
        String url1 = ("https://" + shop + "/pages/" + newURL);
        customPagesPage.waitForEverythingComplete();
        if(isCreate==true){
            assertThat(exactURL).isEqualTo(url1);
            customPagesPage.verifyPageDisplay(page);
        }else {
            assertThat(exactURL).isEqualTo(url);
            customPagesPage.verifyPageNotFound();
        }
        customPagesPage.closeBrowser();
        customPagesPage.switchToWindowWithIndex(0);
    }

    @Step
    public void backtoPage() {
        customPagesPage.switchToTab("Online Store");
        customPagesPage.switchToTab("Pages");
    }
}
