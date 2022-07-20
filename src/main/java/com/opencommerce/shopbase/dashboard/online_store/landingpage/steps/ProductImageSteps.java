package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.FeaturedProductPages;
import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.ProductImagePages;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

public class ProductImageSteps extends ScenarioSteps {
    ProductImagePages productImagePages;

    public void verifyRatio(String ratio) {
        productImagePages.verifyRatio(ratio);
    }

    public void verifyOverlayColorImage(String overlayColorImage) {
        productImagePages.verifyOverlayColorImage(overlayColorImage);
    }

    public void verifyProductImagePosition(String productImagePosition) {
        productImagePages.verifyProductImagePosition(productImagePosition);
    }
}
