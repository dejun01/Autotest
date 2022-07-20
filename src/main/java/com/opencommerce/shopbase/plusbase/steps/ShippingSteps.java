package com.opencommerce.shopbase.plusbase.steps;

import com.opencommerce.shopbase.plusbase.pages.ShippingPage;
import net.thucydides.core.annotations.Step;

public class ShippingSteps {
    ShippingPage shippingPage;

    @Step
    public void editRate(String shipping, String beforeRate) {
        shippingPage.clickCheckboxFreeShipRate(shipping);
        shippingPage.editRate(shipping, beforeRate);

    }

    @Step
    public String getValueAfterEdit(String shipping) {
        return shippingPage.getValueAfterEdit(shipping);
    }
    @Step
    public void enterProfileName(String profileName) {
        shippingPage.enterProfileName(profileName);
    }
    @Step
    public void clickBtnSaveChangesProfile(String profileName) {
        shippingPage.clickBtn("Save changes");
        shippingPage.waitUntilUpdateProfileSuccessfully(profileName);
    }
    @Step
    public void creatANewProfile() {
        shippingPage.clickLinkTextWithLabel("+ Create new profile", 1);
    }
    @Step
    public void clickBtnCreateShippingZone() {
        shippingPage.clickLinkTextWithLabel("Create Shipping Zone",1);
    }
    @Step
    public void enterZoneName(String zoneName) {
        if (!zoneName.isEmpty())
            shippingPage.enterInputFieldWithLabel("Zone name", zoneName);
    }
    @Step
    public void searchThenChooseCountries(String sCountry) {
        if (sCountry.contains(",")) {
            String[] countries = sCountry.split(",");
            for (String country : countries) {
                shippingPage.searchCountries(country);
                shippingPage.chooseCountries(country.trim());
            }
        } else {
            shippingPage.searchCountries(sCountry);
            shippingPage.chooseCountries(sCountry);
        }
    }
    @Step
    public void clickBtnDone() {
        shippingPage.clickBtnDone();
    }
    @Step
    public void clickBtnAddRate() {
        shippingPage.clickBtnAddRate();
    }
    @Step
    public void enterRateName(String sRateName) {
        shippingPage.waitUntilElementVisible(shippingPage.xPathInputFieldWithLabel("", "RATE NAME", 1), 5);
        shippingPage.enterInputFieldWithLabel("RATE NAME", sRateName);
    }
    public String price(String price) {
        return shippingPage.price(price);
    }
    @Step
    public void enterPrice(String price) {
        shippingPage.enterInputFieldWithLabel("Price", price(price));
    }
    @Step
    public void addProductRules(String sku){
        shippingPage.enterInputFieldWithLabel("Enter keywords",sku);
    }
    @Step
    public void enterCondition(String additionalCondition){
        shippingPage.enterInputFieldWithLabel("Per additional item",price(additionalCondition));
    }

}
