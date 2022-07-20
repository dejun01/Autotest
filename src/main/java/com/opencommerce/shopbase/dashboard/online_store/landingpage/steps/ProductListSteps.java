package com.opencommerce.shopbase.dashboard.online_store.landingpage.steps;

import com.opencommerce.shopbase.dashboard.online_store.landingpage.pages.ProductListPages;
import net.thucydides.core.steps.ScenarioSteps;

public class ProductListSteps extends ScenarioSteps {
    ProductListPages productListPages;

    public void verifyHighlightProduct(boolean highlightThisProduct, int index) {
        productListPages.verifyHighlightProduct(highlightThisProduct,index);
    }

    public void verifyHighlightText(String highlightText, int index) {
        productListPages.verifyHighlightText(highlightText,index);
    }

    public void verifyShowTitle(boolean showTitle, int index) {
        productListPages.verifyShowTitle(showTitle,index);
    }

    public void verifyTitle(String title, boolean showTitle, String product, int index) {
        productListPages.verifyTitle(title,showTitle,product,index);
    }

    public void verifyShowPrice(boolean showPrice, int index) {
        productListPages.verifyShowPrice(showPrice,index);
    }

    public void verifyShowCompareAtPrice(boolean showCompareAtPrice, int index) {
        productListPages.verifyShowCompareAtPrice(showCompareAtPrice,index);
    }

    public void verifyShowSaleTag(boolean showSaleTag, int index) {
        productListPages.verifyShowSaleTag(showSaleTag,index);
    }

    public void verifySaleType(String saleType, int index) {
        productListPages.verifySaleType(saleType,index);
    }

    public void verifyShowRatings(boolean showRatings, int index) {
        productListPages.verifyShowRatings(showRatings,index);
    }

    public void verifyShowVariantPicker(boolean showVariantPicker, int index) {
        productListPages.verifyShowVariantPicker(showVariantPicker,index);
    }

    public void verifyShowQuantity(boolean showQuantity, int index) {
        productListPages.verifyShowQuantity(showQuantity,index);
    }

    public void verifyDefaultQuantity(String defaultQuantity, int index) {
        productListPages.verifyDefaultQuantity(defaultQuantity,index);
    }

    public void verifyButtonLabel(String buttonLabel, int index) {
        productListPages.verifyButtonLabel(buttonLabel,index);
    }

    public void verifyDestination(String destination, int index) {
        productListPages.verifyDestination(destination,index);
    }

    public void verifyShowTrustBadge(boolean showTrustBadge, int index) {
        productListPages.verifyShowTrustBadge(showTrustBadge,index);
    }
}
