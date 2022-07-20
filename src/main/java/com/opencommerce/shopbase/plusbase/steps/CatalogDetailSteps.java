package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.CatalogDetailPage;
import net.thucydides.core.annotations.Step;

public class CatalogDetailSteps {
    CatalogDetailPage catalogDetailPage;

    @Step
    public void verifyUIOfShipping() {
        catalogDetailPage.verifyTextPresent("Shipping information", true);

    }

    @Step
    public void verifyFee(String countries, String price, String additional, String rateName) {
        if (countries.contains(",")) {
            String[] sCountries = countries.split(",");
            for (String country : sCountries) {
                catalogDetailPage.verifyShippingFee(country.trim(), price, additional, rateName);
            }
        } else {
            catalogDetailPage.verifyShippingFee(countries, price, additional, rateName);
        }

    }

    @Step
    public void clickProduct(String productName) {
        catalogDetailPage.clickProduct(productName);
    }

    @Step
    public void selectVariant(String variant) {
//        Color:Beige/Size:S
        if (variant.contains("/")) {
            String[] listValue = variant.split("/");
            for (String value : listValue) {
                catalogDetailPage.clickVariantValue(value);
            }
        }else {
            catalogDetailPage.clickVariantValue(variant);
        }
    }

    @Step
    public float getFirstItemOfVariant(String variant) {
        selectVariant(variant);
        float firstItem = catalogDetailPage.getFirstItem();
        return firstItem;
    }

    @Step
    public float getAdditionalItem(String variant) {
        selectVariant(variant);
        float additionalItem = catalogDetailPage.getAdditionalItem();
        return additionalItem;
    }
}

